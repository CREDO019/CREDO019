package com.facebook.react.animated;

import android.util.SparseArray;
import com.facebook.common.logging.FLog;
import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.Callback;
import com.facebook.react.bridge.JSApplicationCausedNativeException;
import com.facebook.react.bridge.JSApplicationIllegalArgumentException;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactNoCrashSoftException;
import com.facebook.react.bridge.ReactSoftExceptionLogger;
import com.facebook.react.bridge.ReadableArray;
import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.bridge.UIManager;
import com.facebook.react.bridge.UiThreadUtil;
import com.facebook.react.bridge.WritableMap;
import com.facebook.react.modules.core.DeviceEventManagerModule;
import com.facebook.react.uimanager.UIManagerHelper;
import com.facebook.react.uimanager.ViewProps;
import com.facebook.react.uimanager.events.Event;
import com.facebook.react.uimanager.events.EventDispatcher;
import com.facebook.react.uimanager.events.EventDispatcherListener;
import com.google.android.exoplayer2.source.rtsp.SessionDescription;
import com.google.android.exoplayer2.text.ttml.TtmlNode;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;

/* loaded from: classes.dex */
public class NativeAnimatedNodesManager implements EventDispatcherListener {
    private static final String TAG = "NativeAnimatedNodesManager";
    private final ReactApplicationContext mReactApplicationContext;
    private final SparseArray<AnimatedNode> mAnimatedNodes = new SparseArray<>();
    private final SparseArray<AnimationDriver> mActiveAnimations = new SparseArray<>();
    private final SparseArray<AnimatedNode> mUpdatedNodes = new SparseArray<>();
    private final Map<String, List<EventAnimationDriver>> mEventDrivers = new HashMap();
    private int mAnimatedGraphBFSColor = 0;
    private final List<AnimatedNode> mRunUpdateNodeList = new LinkedList();
    private boolean mEventListenerInitializedForFabric = false;
    private boolean mEventListenerInitializedForNonFabric = false;
    private boolean mWarnedAboutGraphTraversal = false;

    public NativeAnimatedNodesManager(ReactApplicationContext reactApplicationContext) {
        this.mReactApplicationContext = reactApplicationContext;
    }

    public void initializeEventListenerForUIManagerType(int r3) {
        if (r3 == 2) {
            if (this.mEventListenerInitializedForFabric) {
                return;
            }
        } else if (this.mEventListenerInitializedForNonFabric) {
            return;
        }
        UIManager uIManager = UIManagerHelper.getUIManager(this.mReactApplicationContext, r3);
        if (uIManager != null) {
            ((EventDispatcher) uIManager.getEventDispatcher()).addListener(this);
            if (r3 == 2) {
                this.mEventListenerInitializedForFabric = true;
            } else {
                this.mEventListenerInitializedForNonFabric = true;
            }
        }
    }

    public AnimatedNode getNodeById(int r2) {
        return this.mAnimatedNodes.get(r2);
    }

    public boolean hasActiveAnimations() {
        return this.mActiveAnimations.size() > 0 || this.mUpdatedNodes.size() > 0;
    }

    public void createAnimatedNode(int r3, ReadableMap readableMap) {
        AnimatedNode trackingAnimatedNode;
        if (this.mAnimatedNodes.get(r3) != null) {
            throw new JSApplicationIllegalArgumentException("createAnimatedNode: Animated node [" + r3 + "] already exists");
        }
        String string = readableMap.getString(SessionDescription.ATTR_TYPE);
        if (TtmlNode.TAG_STYLE.equals(string)) {
            trackingAnimatedNode = new StyleAnimatedNode(readableMap, this);
        } else if ("value".equals(string)) {
            trackingAnimatedNode = new ValueAnimatedNode(readableMap);
        } else if ("color".equals(string)) {
            trackingAnimatedNode = new ColorAnimatedNode(readableMap, this, this.mReactApplicationContext);
        } else if ("props".equals(string)) {
            trackingAnimatedNode = new PropsAnimatedNode(readableMap, this);
        } else if ("interpolation".equals(string)) {
            trackingAnimatedNode = new InterpolationAnimatedNode(readableMap);
        } else if ("addition".equals(string)) {
            trackingAnimatedNode = new AdditionAnimatedNode(readableMap, this);
        } else if ("subtraction".equals(string)) {
            trackingAnimatedNode = new SubtractionAnimatedNode(readableMap, this);
        } else if ("division".equals(string)) {
            trackingAnimatedNode = new DivisionAnimatedNode(readableMap, this);
        } else if ("multiplication".equals(string)) {
            trackingAnimatedNode = new MultiplicationAnimatedNode(readableMap, this);
        } else if ("modulus".equals(string)) {
            trackingAnimatedNode = new ModulusAnimatedNode(readableMap, this);
        } else if ("diffclamp".equals(string)) {
            trackingAnimatedNode = new DiffClampAnimatedNode(readableMap, this);
        } else if (ViewProps.TRANSFORM.equals(string)) {
            trackingAnimatedNode = new TransformAnimatedNode(readableMap, this);
        } else if ("tracking".equals(string)) {
            trackingAnimatedNode = new TrackingAnimatedNode(readableMap, this);
        } else {
            throw new JSApplicationIllegalArgumentException("Unsupported node type: " + string);
        }
        trackingAnimatedNode.mTag = r3;
        this.mAnimatedNodes.put(r3, trackingAnimatedNode);
        this.mUpdatedNodes.put(r3, trackingAnimatedNode);
    }

    public void updateAnimatedNodeConfig(int r3, ReadableMap readableMap) {
        AnimatedNode animatedNode = this.mAnimatedNodes.get(r3);
        if (animatedNode == null) {
            throw new JSApplicationIllegalArgumentException("updateAnimatedNode: Animated node [" + r3 + "] does not exist");
        } else if (animatedNode instanceof AnimatedNodeWithUpdateableConfig) {
            stopAnimationsForNode(animatedNode);
            ((AnimatedNodeWithUpdateableConfig) animatedNode).onUpdateConfig(readableMap);
            this.mUpdatedNodes.put(r3, animatedNode);
        }
    }

    public void dropAnimatedNode(int r2) {
        this.mAnimatedNodes.remove(r2);
        this.mUpdatedNodes.remove(r2);
    }

    public void startListeningToAnimatedNodeValue(int r3, AnimatedNodeValueListener animatedNodeValueListener) {
        AnimatedNode animatedNode = this.mAnimatedNodes.get(r3);
        if (animatedNode == null || !(animatedNode instanceof ValueAnimatedNode)) {
            throw new JSApplicationIllegalArgumentException("startListeningToAnimatedNodeValue: Animated node [" + r3 + "] does not exist, or is not a 'value' node");
        }
        ((ValueAnimatedNode) animatedNode).setValueListener(animatedNodeValueListener);
    }

    public void stopListeningToAnimatedNodeValue(int r4) {
        AnimatedNode animatedNode = this.mAnimatedNodes.get(r4);
        if (animatedNode == null || !(animatedNode instanceof ValueAnimatedNode)) {
            throw new JSApplicationIllegalArgumentException("startListeningToAnimatedNodeValue: Animated node [" + r4 + "] does not exist, or is not a 'value' node");
        }
        ((ValueAnimatedNode) animatedNode).setValueListener(null);
    }

    public void setAnimatedNodeValue(int r3, double d) {
        AnimatedNode animatedNode = this.mAnimatedNodes.get(r3);
        if (animatedNode == null || !(animatedNode instanceof ValueAnimatedNode)) {
            throw new JSApplicationIllegalArgumentException("setAnimatedNodeValue: Animated node [" + r3 + "] does not exist, or is not a 'value' node");
        }
        stopAnimationsForNode(animatedNode);
        ((ValueAnimatedNode) animatedNode).mValue = d;
        this.mUpdatedNodes.put(r3, animatedNode);
    }

    public void setAnimatedNodeOffset(int r3, double d) {
        AnimatedNode animatedNode = this.mAnimatedNodes.get(r3);
        if (animatedNode == null || !(animatedNode instanceof ValueAnimatedNode)) {
            throw new JSApplicationIllegalArgumentException("setAnimatedNodeOffset: Animated node [" + r3 + "] does not exist, or is not a 'value' node");
        }
        ((ValueAnimatedNode) animatedNode).mOffset = d;
        this.mUpdatedNodes.put(r3, animatedNode);
    }

    public void flattenAnimatedNodeOffset(int r4) {
        AnimatedNode animatedNode = this.mAnimatedNodes.get(r4);
        if (animatedNode == null || !(animatedNode instanceof ValueAnimatedNode)) {
            throw new JSApplicationIllegalArgumentException("flattenAnimatedNodeOffset: Animated node [" + r4 + "] does not exist, or is not a 'value' node");
        }
        ((ValueAnimatedNode) animatedNode).flattenOffset();
    }

    public void extractAnimatedNodeOffset(int r4) {
        AnimatedNode animatedNode = this.mAnimatedNodes.get(r4);
        if (animatedNode == null || !(animatedNode instanceof ValueAnimatedNode)) {
            throw new JSApplicationIllegalArgumentException("extractAnimatedNodeOffset: Animated node [" + r4 + "] does not exist, or is not a 'value' node");
        }
        ((ValueAnimatedNode) animatedNode).extractOffset();
    }

    public void startAnimatingNode(int r4, int r5, ReadableMap readableMap, Callback callback) {
        AnimationDriver decayAnimation;
        AnimatedNode animatedNode = this.mAnimatedNodes.get(r5);
        if (animatedNode == null) {
            throw new JSApplicationIllegalArgumentException("startAnimatingNode: Animated node [" + r5 + "] does not exist");
        } else if (!(animatedNode instanceof ValueAnimatedNode)) {
            throw new JSApplicationIllegalArgumentException("startAnimatingNode: Animated node [" + r5 + "] should be of type " + ValueAnimatedNode.class.getName());
        } else {
            AnimationDriver animationDriver = this.mActiveAnimations.get(r4);
            if (animationDriver != null) {
                animationDriver.resetConfig(readableMap);
                return;
            }
            String string = readableMap.getString(SessionDescription.ATTR_TYPE);
            if ("frames".equals(string)) {
                decayAnimation = new FrameBasedAnimationDriver(readableMap);
            } else if ("spring".equals(string)) {
                decayAnimation = new SpringAnimation(readableMap);
            } else if ("decay".equals(string)) {
                decayAnimation = new DecayAnimation(readableMap);
            } else {
                throw new JSApplicationIllegalArgumentException("startAnimatingNode: Unsupported animation type [" + r5 + "]: " + string);
            }
            decayAnimation.mId = r4;
            decayAnimation.mEndCallback = callback;
            decayAnimation.mAnimatedValue = (ValueAnimatedNode) animatedNode;
            this.mActiveAnimations.put(r4, decayAnimation);
        }
    }

    private void stopAnimationsForNode(AnimatedNode animatedNode) {
        int r1 = 0;
        while (r1 < this.mActiveAnimations.size()) {
            AnimationDriver valueAt = this.mActiveAnimations.valueAt(r1);
            if (animatedNode.equals(valueAt.mAnimatedValue)) {
                if (valueAt.mEndCallback != null) {
                    WritableMap createMap = Arguments.createMap();
                    createMap.putBoolean("finished", false);
                    valueAt.mEndCallback.invoke(createMap);
                } else if (this.mReactApplicationContext != null) {
                    WritableMap createMap2 = Arguments.createMap();
                    createMap2.putInt("animationId", valueAt.mId);
                    createMap2.putBoolean("finished", false);
                    ((DeviceEventManagerModule.RCTDeviceEventEmitter) this.mReactApplicationContext.getJSModule(DeviceEventManagerModule.RCTDeviceEventEmitter.class)).emit("onNativeAnimatedModuleAnimationFinished", createMap2);
                }
                this.mActiveAnimations.removeAt(r1);
                r1--;
            }
            r1++;
        }
    }

    public void stopAnimation(int r6) {
        for (int r1 = 0; r1 < this.mActiveAnimations.size(); r1++) {
            AnimationDriver valueAt = this.mActiveAnimations.valueAt(r1);
            if (valueAt.mId == r6) {
                if (valueAt.mEndCallback != null) {
                    WritableMap createMap = Arguments.createMap();
                    createMap.putBoolean("finished", false);
                    valueAt.mEndCallback.invoke(createMap);
                } else if (this.mReactApplicationContext != null) {
                    WritableMap createMap2 = Arguments.createMap();
                    createMap2.putInt("animationId", valueAt.mId);
                    createMap2.putBoolean("finished", false);
                    ((DeviceEventManagerModule.RCTDeviceEventEmitter) this.mReactApplicationContext.getJSModule(DeviceEventManagerModule.RCTDeviceEventEmitter.class)).emit("onNativeAnimatedModuleAnimationFinished", createMap2);
                }
                this.mActiveAnimations.removeAt(r1);
                return;
            }
        }
    }

    public void connectAnimatedNodes(int r4, int r5) {
        AnimatedNode animatedNode = this.mAnimatedNodes.get(r4);
        if (animatedNode == null) {
            throw new JSApplicationIllegalArgumentException("connectAnimatedNodes: Animated node with tag (parent) [" + r4 + "] does not exist");
        }
        AnimatedNode animatedNode2 = this.mAnimatedNodes.get(r5);
        if (animatedNode2 == null) {
            throw new JSApplicationIllegalArgumentException("connectAnimatedNodes: Animated node with tag (child) [" + r5 + "] does not exist");
        }
        animatedNode.addChild(animatedNode2);
        this.mUpdatedNodes.put(r5, animatedNode2);
    }

    public void disconnectAnimatedNodes(int r4, int r5) {
        AnimatedNode animatedNode = this.mAnimatedNodes.get(r4);
        if (animatedNode == null) {
            throw new JSApplicationIllegalArgumentException("disconnectAnimatedNodes: Animated node with tag (parent) [" + r4 + "] does not exist");
        }
        AnimatedNode animatedNode2 = this.mAnimatedNodes.get(r5);
        if (animatedNode2 == null) {
            throw new JSApplicationIllegalArgumentException("disconnectAnimatedNodes: Animated node with tag (child) [" + r5 + "] does not exist");
        }
        animatedNode.removeChild(animatedNode2);
        this.mUpdatedNodes.put(r5, animatedNode2);
    }

    public void connectAnimatedNodeToView(int r4, int r5) {
        AnimatedNode animatedNode = this.mAnimatedNodes.get(r4);
        if (animatedNode == null) {
            throw new JSApplicationIllegalArgumentException("connectAnimatedNodeToView: Animated node with tag [" + r4 + "] does not exist");
        } else if (!(animatedNode instanceof PropsAnimatedNode)) {
            throw new JSApplicationIllegalArgumentException("connectAnimatedNodeToView: Animated node connected to view [" + r5 + "] should be of type " + PropsAnimatedNode.class.getName());
        } else {
            ReactApplicationContext reactApplicationContext = this.mReactApplicationContext;
            if (reactApplicationContext == null) {
                throw new IllegalStateException("connectAnimatedNodeToView: Animated node could not be connected, no ReactApplicationContext: " + r5);
            }
            UIManager uIManagerForReactTag = UIManagerHelper.getUIManagerForReactTag(reactApplicationContext, r5);
            if (uIManagerForReactTag == null) {
                ReactSoftExceptionLogger.logSoftException(TAG, new ReactNoCrashSoftException("connectAnimatedNodeToView: Animated node could not be connected to UIManager - uiManager disappeared for tag: " + r5));
                return;
            }
            ((PropsAnimatedNode) animatedNode).connectToView(r5, uIManagerForReactTag);
            this.mUpdatedNodes.put(r4, animatedNode);
        }
    }

    public void disconnectAnimatedNodeFromView(int r3, int r4) {
        AnimatedNode animatedNode = this.mAnimatedNodes.get(r3);
        if (animatedNode == null) {
            throw new JSApplicationIllegalArgumentException("disconnectAnimatedNodeFromView: Animated node with tag [" + r3 + "] does not exist");
        } else if (!(animatedNode instanceof PropsAnimatedNode)) {
            throw new JSApplicationIllegalArgumentException("disconnectAnimatedNodeFromView: Animated node connected to view [" + r4 + "] should be of type " + PropsAnimatedNode.class.getName());
        } else {
            ((PropsAnimatedNode) animatedNode).disconnectFromView(r4);
        }
    }

    public void getValue(int r4, Callback callback) {
        AnimatedNode animatedNode = this.mAnimatedNodes.get(r4);
        if (animatedNode == null || !(animatedNode instanceof ValueAnimatedNode)) {
            throw new JSApplicationIllegalArgumentException("getValue: Animated node with tag [" + r4 + "] does not exist or is not a 'value' node");
        }
        double value = ((ValueAnimatedNode) animatedNode).getValue();
        if (callback != null) {
            callback.invoke(Double.valueOf(value));
        } else if (this.mReactApplicationContext == null) {
        } else {
            WritableMap createMap = Arguments.createMap();
            createMap.putInt("tag", r4);
            createMap.putDouble("value", value);
            ((DeviceEventManagerModule.RCTDeviceEventEmitter) this.mReactApplicationContext.getJSModule(DeviceEventManagerModule.RCTDeviceEventEmitter.class)).emit("onNativeAnimatedModuleGetValue", createMap);
        }
    }

    public void restoreDefaultValues(int r3) {
        AnimatedNode animatedNode = this.mAnimatedNodes.get(r3);
        if (animatedNode == null) {
            return;
        }
        if (!(animatedNode instanceof PropsAnimatedNode)) {
            throw new JSApplicationIllegalArgumentException("Animated node connected to view [?] should be of type " + PropsAnimatedNode.class.getName());
        }
        ((PropsAnimatedNode) animatedNode).restoreDefaultValues();
    }

    public void addAnimatedEventToView(int r5, String str, ReadableMap readableMap) {
        int r0 = readableMap.getInt("animatedValueTag");
        AnimatedNode animatedNode = this.mAnimatedNodes.get(r0);
        if (animatedNode == null) {
            throw new JSApplicationIllegalArgumentException("addAnimatedEventToView: Animated node with tag [" + r0 + "] does not exist");
        } else if (!(animatedNode instanceof ValueAnimatedNode)) {
            throw new JSApplicationIllegalArgumentException("addAnimatedEventToView: Animated node on view [" + r5 + "] connected to event (" + str + ") should be of type " + ValueAnimatedNode.class.getName());
        } else {
            ReadableArray array = readableMap.getArray("nativeEventPath");
            ArrayList arrayList = new ArrayList(array.size());
            for (int r2 = 0; r2 < array.size(); r2++) {
                arrayList.add(array.getString(r2));
            }
            EventAnimationDriver eventAnimationDriver = new EventAnimationDriver(arrayList, (ValueAnimatedNode) animatedNode);
            String str2 = r5 + str;
            if (this.mEventDrivers.containsKey(str2)) {
                this.mEventDrivers.get(str2).add(eventAnimationDriver);
                return;
            }
            ArrayList arrayList2 = new ArrayList(1);
            arrayList2.add(eventAnimationDriver);
            this.mEventDrivers.put(str2, arrayList2);
        }
    }

    public void removeAnimatedEventFromView(int r4, String str, int r6) {
        String str2 = r4 + str;
        if (this.mEventDrivers.containsKey(str2)) {
            List<EventAnimationDriver> list = this.mEventDrivers.get(str2);
            if (list.size() == 1) {
                this.mEventDrivers.remove(r4 + str);
                return;
            }
            ListIterator<EventAnimationDriver> listIterator = list.listIterator();
            while (listIterator.hasNext()) {
                if (listIterator.next().mValueNode.mTag == r6) {
                    listIterator.remove();
                    return;
                }
            }
        }
    }

    @Override // com.facebook.react.uimanager.events.EventDispatcherListener
    public void onEventDispatch(final Event event) {
        if (UiThreadUtil.isOnUiThread()) {
            handleEvent(event);
        } else {
            UiThreadUtil.runOnUiThread(new Runnable() { // from class: com.facebook.react.animated.NativeAnimatedNodesManager.1
                @Override // java.lang.Runnable
                public void run() {
                    NativeAnimatedNodesManager.this.handleEvent(event);
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void handleEvent(Event event) {
        ReactApplicationContext reactApplicationContext;
        UIManager uIManager;
        if (this.mEventDrivers.isEmpty() || (reactApplicationContext = this.mReactApplicationContext) == null || (uIManager = UIManagerHelper.getUIManager(reactApplicationContext, event.getUIManagerType())) == null) {
            return;
        }
        String resolveCustomDirectEventName = uIManager.resolveCustomDirectEventName(event.getEventName());
        if (resolveCustomDirectEventName == null) {
            resolveCustomDirectEventName = "";
        }
        Map<String, List<EventAnimationDriver>> map = this.mEventDrivers;
        List<EventAnimationDriver> list = map.get(event.getViewTag() + resolveCustomDirectEventName);
        if (list != null) {
            for (EventAnimationDriver eventAnimationDriver : list) {
                stopAnimationsForNode(eventAnimationDriver.mValueNode);
                event.dispatch(eventAnimationDriver);
                this.mRunUpdateNodeList.add(eventAnimationDriver.mValueNode);
            }
            updateNodes(this.mRunUpdateNodeList);
            this.mRunUpdateNodeList.clear();
        }
    }

    public void runUpdates(long j) {
        UiThreadUtil.assertOnUiThread();
        for (int r1 = 0; r1 < this.mUpdatedNodes.size(); r1++) {
            this.mRunUpdateNodeList.add(this.mUpdatedNodes.valueAt(r1));
        }
        this.mUpdatedNodes.clear();
        boolean z = false;
        for (int r12 = 0; r12 < this.mActiveAnimations.size(); r12++) {
            AnimationDriver valueAt = this.mActiveAnimations.valueAt(r12);
            valueAt.runAnimationStep(j);
            this.mRunUpdateNodeList.add(valueAt.mAnimatedValue);
            if (valueAt.mHasFinished) {
                z = true;
            }
        }
        updateNodes(this.mRunUpdateNodeList);
        this.mRunUpdateNodeList.clear();
        if (z) {
            for (int size = this.mActiveAnimations.size() - 1; size >= 0; size--) {
                AnimationDriver valueAt2 = this.mActiveAnimations.valueAt(size);
                if (valueAt2.mHasFinished) {
                    if (valueAt2.mEndCallback != null) {
                        WritableMap createMap = Arguments.createMap();
                        createMap.putBoolean("finished", true);
                        valueAt2.mEndCallback.invoke(createMap);
                    } else if (this.mReactApplicationContext != null) {
                        WritableMap createMap2 = Arguments.createMap();
                        createMap2.putInt("animationId", valueAt2.mId);
                        createMap2.putBoolean("finished", true);
                        DeviceEventManagerModule.RCTDeviceEventEmitter rCTDeviceEventEmitter = (DeviceEventManagerModule.RCTDeviceEventEmitter) this.mReactApplicationContext.getJSModule(DeviceEventManagerModule.RCTDeviceEventEmitter.class);
                        if (rCTDeviceEventEmitter != null) {
                            rCTDeviceEventEmitter.emit("onNativeAnimatedModuleAnimationFinished", createMap2);
                        }
                    }
                    this.mActiveAnimations.removeAt(size);
                }
            }
        }
    }

    private void updateNodes(List<AnimatedNode> list) {
        int r0 = this.mAnimatedGraphBFSColor + 1;
        this.mAnimatedGraphBFSColor = r0;
        if (r0 == 0) {
            this.mAnimatedGraphBFSColor = r0 + 1;
        }
        ArrayDeque arrayDeque = new ArrayDeque();
        int r4 = 0;
        for (AnimatedNode animatedNode : list) {
            int r6 = animatedNode.mBFSColor;
            int r7 = this.mAnimatedGraphBFSColor;
            if (r6 != r7) {
                animatedNode.mBFSColor = r7;
                r4++;
                arrayDeque.add(animatedNode);
            }
        }
        while (!arrayDeque.isEmpty()) {
            AnimatedNode animatedNode2 = (AnimatedNode) arrayDeque.poll();
            if (animatedNode2.mChildren != null) {
                for (int r5 = 0; r5 < animatedNode2.mChildren.size(); r5++) {
                    AnimatedNode animatedNode3 = animatedNode2.mChildren.get(r5);
                    animatedNode3.mActiveIncomingNodes++;
                    int r72 = animatedNode3.mBFSColor;
                    int r8 = this.mAnimatedGraphBFSColor;
                    if (r72 != r8) {
                        animatedNode3.mBFSColor = r8;
                        r4++;
                        arrayDeque.add(animatedNode3);
                    }
                }
            }
        }
        int r2 = this.mAnimatedGraphBFSColor + 1;
        this.mAnimatedGraphBFSColor = r2;
        if (r2 == 0) {
            this.mAnimatedGraphBFSColor = r2 + 1;
        }
        int r52 = 0;
        for (AnimatedNode animatedNode4 : list) {
            if (animatedNode4.mActiveIncomingNodes == 0) {
                int r73 = animatedNode4.mBFSColor;
                int r82 = this.mAnimatedGraphBFSColor;
                if (r73 != r82) {
                    animatedNode4.mBFSColor = r82;
                    r52++;
                    arrayDeque.add(animatedNode4);
                }
            }
        }
        int r22 = 0;
        while (!arrayDeque.isEmpty()) {
            AnimatedNode animatedNode5 = (AnimatedNode) arrayDeque.poll();
            try {
                animatedNode5.update();
                if (animatedNode5 instanceof PropsAnimatedNode) {
                    ((PropsAnimatedNode) animatedNode5).updateView();
                }
            } catch (JSApplicationCausedNativeException e) {
                FLog.m1327e(TAG, "Native animation workaround, frame lost as result of race condition", e);
            }
            if (animatedNode5 instanceof ValueAnimatedNode) {
                ((ValueAnimatedNode) animatedNode5).onValueUpdate();
            }
            if (animatedNode5.mChildren != null) {
                for (int r74 = 0; r74 < animatedNode5.mChildren.size(); r74++) {
                    AnimatedNode animatedNode6 = animatedNode5.mChildren.get(r74);
                    animatedNode6.mActiveIncomingNodes--;
                    if (animatedNode6.mBFSColor != this.mAnimatedGraphBFSColor && animatedNode6.mActiveIncomingNodes == 0) {
                        animatedNode6.mBFSColor = this.mAnimatedGraphBFSColor;
                        r52++;
                        arrayDeque.add(animatedNode6);
                    } else if (animatedNode6.mBFSColor == this.mAnimatedGraphBFSColor) {
                        r22++;
                    }
                }
            }
        }
        if (r4 != r52) {
            if (this.mWarnedAboutGraphTraversal) {
                return;
            }
            this.mWarnedAboutGraphTraversal = true;
            FLog.m1328e(TAG, "Detected animation cycle or disconnected graph. ");
            for (AnimatedNode animatedNode7 : list) {
                FLog.m1328e(TAG, animatedNode7.prettyPrintWithChildren());
            }
            IllegalStateException illegalStateException = new IllegalStateException("Looks like animated nodes graph has " + (r22 > 0 ? "cycles (" + r22 + ")" : "disconnected regions") + ", there are " + r4 + " but toposort visited only " + r52);
            boolean z = this.mEventListenerInitializedForFabric;
            if (z && r22 == 0) {
                ReactSoftExceptionLogger.logSoftException(TAG, new ReactNoCrashSoftException(illegalStateException));
                return;
            } else if (z) {
                ReactSoftExceptionLogger.logSoftException(TAG, new ReactNoCrashSoftException(illegalStateException));
                return;
            } else {
                throw illegalStateException;
            }
        }
        this.mWarnedAboutGraphTraversal = false;
    }
}
