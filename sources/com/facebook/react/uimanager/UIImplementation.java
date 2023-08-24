package com.facebook.react.uimanager;

import android.os.SystemClock;
import android.view.View;
import com.facebook.common.logging.FLog;
import com.facebook.infer.annotation.Assertions;
import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.Callback;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReadableArray;
import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.bridge.UiThreadUtil;
import com.facebook.react.bridge.WritableArray;
import com.facebook.react.common.ReactConstants;
import com.facebook.react.modules.i18nmanager.I18nUtil;
import com.facebook.react.uimanager.debug.NotThreadSafeViewHierarchyUpdateDebugListener;
import com.facebook.react.uimanager.events.EventDispatcher;
import com.facebook.systrace.Systrace;
import com.facebook.systrace.SystraceMessage;
import com.facebook.yoga.YogaDirection;
import java.util.List;
import java.util.Map;

/* loaded from: classes.dex */
public class UIImplementation {
    protected final EventDispatcher mEventDispatcher;
    private long mLastCalculateLayoutTime;
    protected LayoutUpdateListener mLayoutUpdateListener;
    private final int[] mMeasureBuffer;
    private final NativeViewHierarchyOptimizer mNativeViewHierarchyOptimizer;
    private final UIViewOperationQueue mOperationsQueue;
    protected final ReactApplicationContext mReactContext;
    protected final ShadowNodeRegistry mShadowNodeRegistry;
    private final ViewManagerRegistry mViewManagers;
    private volatile boolean mViewOperationsEnabled;
    protected Object uiImplementationThreadLock;

    /* loaded from: classes.dex */
    public interface LayoutUpdateListener {
        void onLayoutUpdated(ReactShadowNode reactShadowNode);
    }

    public void onHostDestroy() {
    }

    public UIImplementation(ReactApplicationContext reactApplicationContext, ViewManagerResolver viewManagerResolver, EventDispatcher eventDispatcher, int r5) {
        this(reactApplicationContext, new ViewManagerRegistry(viewManagerResolver), eventDispatcher, r5);
    }

    public UIImplementation(ReactApplicationContext reactApplicationContext, List<ViewManager> list, EventDispatcher eventDispatcher, int r5) {
        this(reactApplicationContext, new ViewManagerRegistry(list), eventDispatcher, r5);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public UIImplementation(ReactApplicationContext reactApplicationContext, ViewManagerRegistry viewManagerRegistry, EventDispatcher eventDispatcher, int r6) {
        this(reactApplicationContext, viewManagerRegistry, new UIViewOperationQueue(reactApplicationContext, new NativeViewHierarchyManager(viewManagerRegistry), r6), eventDispatcher);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public UIImplementation(ReactApplicationContext reactApplicationContext, ViewManagerRegistry viewManagerRegistry, UIViewOperationQueue uIViewOperationQueue, EventDispatcher eventDispatcher) {
        this.uiImplementationThreadLock = new Object();
        ShadowNodeRegistry shadowNodeRegistry = new ShadowNodeRegistry();
        this.mShadowNodeRegistry = shadowNodeRegistry;
        this.mMeasureBuffer = new int[4];
        this.mLastCalculateLayoutTime = 0L;
        this.mViewOperationsEnabled = true;
        this.mReactContext = reactApplicationContext;
        this.mViewManagers = viewManagerRegistry;
        this.mOperationsQueue = uIViewOperationQueue;
        this.mNativeViewHierarchyOptimizer = new NativeViewHierarchyOptimizer(uIViewOperationQueue, shadowNodeRegistry);
        this.mEventDispatcher = eventDispatcher;
    }

    protected ReactShadowNode createRootShadowNode() {
        ReactShadowNodeImpl reactShadowNodeImpl = new ReactShadowNodeImpl();
        if (I18nUtil.getInstance().isRTL(this.mReactContext)) {
            reactShadowNodeImpl.setLayoutDirection(YogaDirection.RTL);
        }
        reactShadowNodeImpl.setViewClassName("Root");
        return reactShadowNodeImpl;
    }

    protected ReactShadowNode createShadowNode(String str) {
        return this.mViewManagers.get(str).createShadowNodeInstance(this.mReactContext);
    }

    public final ReactShadowNode resolveShadowNode(int r2) {
        return this.mShadowNodeRegistry.getNode(r2);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final ViewManager resolveViewManager(String str) {
        return this.mViewManagers.getViewManagerIfExists(str);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public UIViewOperationQueue getUIViewOperationQueue() {
        return this.mOperationsQueue;
    }

    public void updateRootView(int r2, int r3, int r4) {
        ReactShadowNode node = this.mShadowNodeRegistry.getNode(r2);
        if (node == null) {
            FLog.m1288w(ReactConstants.TAG, "Tried to update non-existent root tag: " + r2);
            return;
        }
        updateRootView(node, r3, r4);
    }

    public void updateRootView(ReactShadowNode reactShadowNode, int r2, int r3) {
        reactShadowNode.setMeasureSpecs(r2, r3);
    }

    public <T extends View> void registerRootView(T t, int r5, ThemedReactContext themedReactContext) {
        synchronized (this.uiImplementationThreadLock) {
            final ReactShadowNode createRootShadowNode = createRootShadowNode();
            createRootShadowNode.setReactTag(r5);
            createRootShadowNode.setThemedContext(themedReactContext);
            themedReactContext.runOnNativeModulesQueueThread(new Runnable() { // from class: com.facebook.react.uimanager.UIImplementation.1
                @Override // java.lang.Runnable
                public void run() {
                    UIImplementation.this.mShadowNodeRegistry.addRootNode(createRootShadowNode);
                }
            });
            this.mOperationsQueue.addRootView(r5, t);
        }
    }

    public void removeRootView(int r2) {
        removeRootShadowNode(r2);
        this.mOperationsQueue.enqueueRemoveRootView(r2);
    }

    public void removeRootShadowNode(int r3) {
        synchronized (this.uiImplementationThreadLock) {
            this.mShadowNodeRegistry.removeRootNode(r3);
        }
    }

    public void updateNodeSize(int r2, int r3, int r4) {
        ReactShadowNode node = this.mShadowNodeRegistry.getNode(r2);
        if (node == null) {
            FLog.m1288w(ReactConstants.TAG, "Tried to update size of non-existent tag: " + r2);
            return;
        }
        node.setStyleWidth(r3);
        node.setStyleHeight(r4);
        dispatchViewUpdatesIfNeeded();
    }

    public void setViewLocalData(int r2, Object obj) {
        ReactShadowNode node = this.mShadowNodeRegistry.getNode(r2);
        if (node == null) {
            FLog.m1288w(ReactConstants.TAG, "Attempt to set local data for view with unknown tag: " + r2);
            return;
        }
        node.setLocalData(obj);
        dispatchViewUpdatesIfNeeded();
    }

    public void profileNextBatch() {
        this.mOperationsQueue.profileNextBatch();
    }

    public Map<String, Long> getProfiledBatchPerfCounters() {
        return this.mOperationsQueue.getProfiledBatchPerfCounters();
    }

    public void createView(int r6, String str, int r8, ReadableMap readableMap) {
        if (this.mViewOperationsEnabled) {
            synchronized (this.uiImplementationThreadLock) {
                ReactShadowNode createShadowNode = createShadowNode(str);
                ReactShadowNode node = this.mShadowNodeRegistry.getNode(r8);
                Assertions.assertNotNull(node, "Root node with tag " + r8 + " doesn't exist");
                createShadowNode.setReactTag(r6);
                createShadowNode.setViewClassName(str);
                createShadowNode.setRootTag(node.getReactTag());
                createShadowNode.setThemedContext(node.getThemedContext());
                this.mShadowNodeRegistry.addNode(createShadowNode);
                ReactStylesDiffMap reactStylesDiffMap = null;
                if (readableMap != null) {
                    reactStylesDiffMap = new ReactStylesDiffMap(readableMap);
                    createShadowNode.updateProperties(reactStylesDiffMap);
                }
                handleCreateView(createShadowNode, r8, reactStylesDiffMap);
            }
        }
    }

    protected void handleCreateView(ReactShadowNode reactShadowNode, int r3, ReactStylesDiffMap reactStylesDiffMap) {
        if (reactShadowNode.isVirtual()) {
            return;
        }
        this.mNativeViewHierarchyOptimizer.handleCreateView(reactShadowNode, reactShadowNode.getThemedContext(), reactStylesDiffMap);
    }

    public void updateView(int r2, String str, ReadableMap readableMap) {
        if (this.mViewOperationsEnabled) {
            if (this.mViewManagers.get(str) == null) {
                throw new IllegalViewOperationException("Got unknown view type: " + str);
            }
            ReactShadowNode node = this.mShadowNodeRegistry.getNode(r2);
            if (node == null) {
                throw new IllegalViewOperationException("Trying to update non-existent view with tag " + r2);
            } else if (readableMap != null) {
                ReactStylesDiffMap reactStylesDiffMap = new ReactStylesDiffMap(readableMap);
                node.updateProperties(reactStylesDiffMap);
                handleUpdateView(node, str, reactStylesDiffMap);
            }
        }
    }

    public void synchronouslyUpdateViewOnUIThread(int r2, ReactStylesDiffMap reactStylesDiffMap) {
        UiThreadUtil.assertOnUiThread();
        this.mOperationsQueue.getNativeViewHierarchyManager().updateProperties(r2, reactStylesDiffMap);
    }

    protected void handleUpdateView(ReactShadowNode reactShadowNode, String str, ReactStylesDiffMap reactStylesDiffMap) {
        if (reactShadowNode.isVirtual()) {
            return;
        }
        this.mNativeViewHierarchyOptimizer.handleUpdateView(reactShadowNode, str, reactStylesDiffMap);
    }

    /* JADX WARN: Code restructure failed: missing block: B:25:0x0049, code lost:
        if (r25 == null) goto L27;
     */
    /* JADX WARN: Code restructure failed: missing block: B:27:0x004f, code lost:
        if (r11 != r25.size()) goto L27;
     */
    /* JADX WARN: Code restructure failed: missing block: B:30:0x0059, code lost:
        throw new com.facebook.react.uimanager.IllegalViewOperationException("Size of addChildTags != size of addAtIndices!");
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void manageChildren(int r21, com.facebook.react.bridge.ReadableArray r22, com.facebook.react.bridge.ReadableArray r23, com.facebook.react.bridge.ReadableArray r24, com.facebook.react.bridge.ReadableArray r25, com.facebook.react.bridge.ReadableArray r26) {
        /*
            Method dump skipped, instructions count: 387
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.facebook.react.uimanager.UIImplementation.manageChildren(int, com.facebook.react.bridge.ReadableArray, com.facebook.react.bridge.ReadableArray, com.facebook.react.bridge.ReadableArray, com.facebook.react.bridge.ReadableArray, com.facebook.react.bridge.ReadableArray):void");
    }

    public void setChildren(int r5, ReadableArray readableArray) {
        if (this.mViewOperationsEnabled) {
            synchronized (this.uiImplementationThreadLock) {
                ReactShadowNode node = this.mShadowNodeRegistry.getNode(r5);
                for (int r1 = 0; r1 < readableArray.size(); r1++) {
                    ReactShadowNode node2 = this.mShadowNodeRegistry.getNode(readableArray.getInt(r1));
                    if (node2 == null) {
                        throw new IllegalViewOperationException("Trying to add unknown view tag: " + readableArray.getInt(r1));
                    }
                    node.addChildAt(node2, r1);
                }
                this.mNativeViewHierarchyOptimizer.handleSetChildren(node, readableArray);
            }
        }
    }

    public void replaceExistingNonRootView(int r10, int r11) {
        if (this.mShadowNodeRegistry.isRootNode(r10) || this.mShadowNodeRegistry.isRootNode(r11)) {
            throw new IllegalViewOperationException("Trying to add or replace a root tag!");
        }
        ReactShadowNode node = this.mShadowNodeRegistry.getNode(r10);
        if (node == null) {
            throw new IllegalViewOperationException("Trying to replace unknown view tag: " + r10);
        }
        ReactShadowNode parent = node.getParent();
        if (parent == null) {
            throw new IllegalViewOperationException("Node is not attached to a parent: " + r10);
        }
        int indexOf = parent.indexOf(node);
        if (indexOf < 0) {
            throw new IllegalStateException("Didn't find child tag in parent");
        }
        WritableArray createArray = Arguments.createArray();
        createArray.pushInt(r11);
        WritableArray createArray2 = Arguments.createArray();
        createArray2.pushInt(indexOf);
        WritableArray createArray3 = Arguments.createArray();
        createArray3.pushInt(indexOf);
        manageChildren(parent.getReactTag(), null, null, createArray, createArray2, createArray3);
    }

    public void removeSubviewsFromContainerWithID(int r9) {
        ReactShadowNode node = this.mShadowNodeRegistry.getNode(r9);
        if (node == null) {
            throw new IllegalViewOperationException("Trying to remove subviews of an unknown view tag: " + r9);
        }
        WritableArray createArray = Arguments.createArray();
        for (int r1 = 0; r1 < node.getChildCount(); r1++) {
            createArray.pushInt(r1);
        }
        manageChildren(r9, null, null, null, null, createArray);
    }

    public void findSubviewIn(int r2, float f, float f2, Callback callback) {
        this.mOperationsQueue.enqueueFindTargetForTouch(r2, f, f2, callback);
    }

    @Deprecated
    public void viewIsDescendantOf(int r3, int r4, Callback callback) {
        ReactShadowNode node = this.mShadowNodeRegistry.getNode(r3);
        ReactShadowNode node2 = this.mShadowNodeRegistry.getNode(r4);
        if (node == null || node2 == null) {
            callback.invoke(false);
        } else {
            callback.invoke(Boolean.valueOf(node.isDescendantOf(node2)));
        }
    }

    public void measure(int r2, Callback callback) {
        if (this.mViewOperationsEnabled) {
            this.mOperationsQueue.enqueueMeasure(r2, callback);
        }
    }

    public void measureInWindow(int r2, Callback callback) {
        if (this.mViewOperationsEnabled) {
            this.mOperationsQueue.enqueueMeasureInWindow(r2, callback);
        }
    }

    public void measureLayout(int r8, int r9, Callback callback, Callback callback2) {
        if (this.mViewOperationsEnabled) {
            try {
                measureLayout(r8, r9, this.mMeasureBuffer);
                callback2.invoke(Float.valueOf(PixelUtil.toDIPFromPixel(this.mMeasureBuffer[0])), Float.valueOf(PixelUtil.toDIPFromPixel(this.mMeasureBuffer[1])), Float.valueOf(PixelUtil.toDIPFromPixel(this.mMeasureBuffer[2])), Float.valueOf(PixelUtil.toDIPFromPixel(this.mMeasureBuffer[3])));
            } catch (IllegalViewOperationException e) {
                callback.invoke(e.getMessage());
            }
        }
    }

    public void measureLayoutRelativeToParent(int r9, Callback callback, Callback callback2) {
        if (this.mViewOperationsEnabled) {
            try {
                measureLayoutRelativeToParent(r9, this.mMeasureBuffer);
                callback2.invoke(Float.valueOf(PixelUtil.toDIPFromPixel(this.mMeasureBuffer[0])), Float.valueOf(PixelUtil.toDIPFromPixel(this.mMeasureBuffer[1])), Float.valueOf(PixelUtil.toDIPFromPixel(this.mMeasureBuffer[2])), Float.valueOf(PixelUtil.toDIPFromPixel(this.mMeasureBuffer[3])));
            } catch (IllegalViewOperationException e) {
                callback.invoke(e.getMessage());
            }
        }
    }

    public void dispatchViewUpdates(int r10) {
        SystraceMessage.beginSection(0L, "UIImplementation.dispatchViewUpdates").arg("batchId", r10).flush();
        long uptimeMillis = SystemClock.uptimeMillis();
        try {
            updateViewHierarchy();
            this.mNativeViewHierarchyOptimizer.onBatchComplete();
            this.mOperationsQueue.dispatchViewUpdates(r10, uptimeMillis, this.mLastCalculateLayoutTime);
        } finally {
            Systrace.endSection(0L);
        }
    }

    private void dispatchViewUpdatesIfNeeded() {
        if (this.mOperationsQueue.isEmpty()) {
            dispatchViewUpdates(-1);
        }
    }

    protected void updateViewHierarchy() {
        Systrace.beginSection(0L, "UIImplementation.updateViewHierarchy");
        for (int r3 = 0; r3 < this.mShadowNodeRegistry.getRootNodeCount(); r3++) {
            try {
                ReactShadowNode node = this.mShadowNodeRegistry.getNode(this.mShadowNodeRegistry.getRootTag(r3));
                if (node.getWidthMeasureSpec() != null && node.getHeightMeasureSpec() != null) {
                    SystraceMessage.beginSection(0L, "UIImplementation.notifyOnBeforeLayoutRecursive").arg("rootTag", node.getReactTag()).flush();
                    notifyOnBeforeLayoutRecursive(node);
                    Systrace.endSection(0L);
                    calculateRootLayout(node);
                    SystraceMessage.beginSection(0L, "UIImplementation.applyUpdatesRecursive").arg("rootTag", node.getReactTag()).flush();
                    applyUpdatesRecursive(node, 0.0f, 0.0f);
                    Systrace.endSection(0L);
                    LayoutUpdateListener layoutUpdateListener = this.mLayoutUpdateListener;
                    if (layoutUpdateListener != null) {
                        this.mOperationsQueue.enqueueLayoutUpdateFinished(node, layoutUpdateListener);
                    }
                }
            } finally {
                Systrace.endSection(0L);
            }
        }
    }

    public void setLayoutAnimationEnabledExperimental(boolean z) {
        this.mOperationsQueue.enqueueSetLayoutAnimationEnabled(z);
    }

    public void configureNextLayoutAnimation(ReadableMap readableMap, Callback callback) {
        this.mOperationsQueue.enqueueConfigureLayoutAnimation(readableMap, callback);
    }

    public void setJSResponder(int r4, boolean z) {
        ReactShadowNode node = this.mShadowNodeRegistry.getNode(r4);
        if (node == null) {
            return;
        }
        while (node.getNativeKind() == NativeKind.NONE) {
            node = node.getParent();
        }
        this.mOperationsQueue.enqueueSetJSResponder(node.getReactTag(), r4, z);
    }

    public void clearJSResponder() {
        this.mOperationsQueue.enqueueClearJSResponder();
    }

    @Deprecated
    public void dispatchViewManagerCommand(int r3, int r4, ReadableArray readableArray) {
        if (checkOrAssertViewExists(r3, "dispatchViewManagerCommand: " + r4)) {
            this.mOperationsQueue.enqueueDispatchCommand(r3, r4, readableArray);
        }
    }

    public void dispatchViewManagerCommand(int r3, String str, ReadableArray readableArray) {
        if (checkOrAssertViewExists(r3, "dispatchViewManagerCommand: " + str)) {
            this.mOperationsQueue.enqueueDispatchCommand(r3, str, readableArray);
        }
    }

    public void showPopupMenu(int r2, ReadableArray readableArray, Callback callback, Callback callback2) {
        if (checkOrAssertViewExists(r2, "showPopupMenu")) {
            this.mOperationsQueue.enqueueShowPopupMenu(r2, readableArray, callback, callback2);
        }
    }

    public void dismissPopupMenu() {
        this.mOperationsQueue.enqueueDismissPopupMenu();
    }

    public void sendAccessibilityEvent(int r2, int r3) {
        this.mOperationsQueue.enqueueSendAccessibilityEvent(r2, r3);
    }

    public void onHostResume() {
        this.mOperationsQueue.resumeFrameCallback();
    }

    public void onHostPause() {
        this.mOperationsQueue.pauseFrameCallback();
    }

    public void onCatalystInstanceDestroyed() {
        this.mViewOperationsEnabled = false;
    }

    public void setViewHierarchyUpdateDebugListener(NotThreadSafeViewHierarchyUpdateDebugListener notThreadSafeViewHierarchyUpdateDebugListener) {
        this.mOperationsQueue.setViewHierarchyUpdateDebugListener(notThreadSafeViewHierarchyUpdateDebugListener);
    }

    protected final void removeShadowNode(ReactShadowNode reactShadowNode) {
        removeShadowNodeRecursive(reactShadowNode);
        reactShadowNode.dispose();
    }

    private void removeShadowNodeRecursive(ReactShadowNode reactShadowNode) {
        NativeViewHierarchyOptimizer.handleRemoveNode(reactShadowNode);
        this.mShadowNodeRegistry.removeNode(reactShadowNode.getReactTag());
        for (int childCount = reactShadowNode.getChildCount() - 1; childCount >= 0; childCount--) {
            removeShadowNodeRecursive(reactShadowNode.getChildAt(childCount));
        }
        reactShadowNode.removeAndDisposeAllChildren();
    }

    private void measureLayout(int r5, int r6, int[] r7) {
        ReactShadowNode node = this.mShadowNodeRegistry.getNode(r5);
        ReactShadowNode node2 = this.mShadowNodeRegistry.getNode(r6);
        if (node == null || node2 == null) {
            StringBuilder sb = new StringBuilder();
            sb.append("Tag ");
            if (node != null) {
                r5 = r6;
            }
            sb.append(r5);
            sb.append(" does not exist");
            throw new IllegalViewOperationException(sb.toString());
        }
        if (node != node2) {
            for (ReactShadowNode parent = node.getParent(); parent != node2; parent = parent.getParent()) {
                if (parent == null) {
                    throw new IllegalViewOperationException("Tag " + r6 + " is not an ancestor of tag " + r5);
                }
            }
        }
        measureLayoutRelativeToVerifiedAncestor(node, node2, r7);
    }

    private void measureLayoutRelativeToParent(int r3, int[] r4) {
        ReactShadowNode node = this.mShadowNodeRegistry.getNode(r3);
        if (node == null) {
            throw new IllegalViewOperationException("No native view for tag " + r3 + " exists!");
        }
        ReactShadowNode parent = node.getParent();
        if (parent == null) {
            throw new IllegalViewOperationException("View with tag " + r3 + " doesn't have a parent!");
        }
        measureLayoutRelativeToVerifiedAncestor(node, parent, r4);
    }

    private void measureLayoutRelativeToVerifiedAncestor(ReactShadowNode reactShadowNode, ReactShadowNode reactShadowNode2, int[] r8) {
        int r1;
        int r2;
        if (reactShadowNode == reactShadowNode2 || reactShadowNode.isVirtual()) {
            r1 = 0;
            r2 = 0;
        } else {
            r1 = Math.round(reactShadowNode.getLayoutX());
            r2 = Math.round(reactShadowNode.getLayoutY());
            for (ReactShadowNode parent = reactShadowNode.getParent(); parent != reactShadowNode2; parent = parent.getParent()) {
                Assertions.assertNotNull(parent);
                assertNodeDoesNotNeedCustomLayoutForChildren(parent);
                r1 += Math.round(parent.getLayoutX());
                r2 += Math.round(parent.getLayoutY());
            }
            assertNodeDoesNotNeedCustomLayoutForChildren(reactShadowNode2);
        }
        r8[0] = r1;
        r8[1] = r2;
        r8[2] = reactShadowNode.getScreenWidth();
        r8[3] = reactShadowNode.getScreenHeight();
    }

    private boolean checkOrAssertViewExists(int r4, String str) {
        if (this.mShadowNodeRegistry.getNode(r4) != null) {
            return true;
        }
        FLog.m1288w(ReactConstants.TAG, "Unable to execute operation " + str + " on view with tag: " + r4 + ", since the view does not exist");
        return false;
    }

    private void assertNodeDoesNotNeedCustomLayoutForChildren(ReactShadowNode reactShadowNode) {
        ViewManager viewManager = (ViewManager) Assertions.assertNotNull(this.mViewManagers.get(reactShadowNode.getViewClass()));
        if (viewManager instanceof IViewManagerWithChildren) {
            IViewManagerWithChildren iViewManagerWithChildren = (IViewManagerWithChildren) viewManager;
            if (iViewManagerWithChildren == null || !iViewManagerWithChildren.needsCustomLayoutForChildren()) {
                return;
            }
            throw new IllegalViewOperationException("Trying to measure a view using measureLayout/measureLayoutRelativeToParent relative to an ancestor that requires custom layout for it's children (" + reactShadowNode.getViewClass() + "). Use measure instead.");
        }
        throw new IllegalViewOperationException("Trying to use view " + reactShadowNode.getViewClass() + " as a parent, but its Manager doesn't extends ViewGroupManager");
    }

    private void notifyOnBeforeLayoutRecursive(ReactShadowNode reactShadowNode) {
        if (reactShadowNode.hasUpdates()) {
            for (int r0 = 0; r0 < reactShadowNode.getChildCount(); r0++) {
                notifyOnBeforeLayoutRecursive(reactShadowNode.getChildAt(r0));
            }
            reactShadowNode.onBeforeLayout(this.mNativeViewHierarchyOptimizer);
        }
    }

    protected void calculateRootLayout(ReactShadowNode reactShadowNode) {
        SystraceMessage.beginSection(0L, "cssRoot.calculateLayout").arg("rootTag", reactShadowNode.getReactTag()).flush();
        long uptimeMillis = SystemClock.uptimeMillis();
        try {
            int intValue = reactShadowNode.getWidthMeasureSpec().intValue();
            int intValue2 = reactShadowNode.getHeightMeasureSpec().intValue();
            float f = Float.NaN;
            float size = View.MeasureSpec.getMode(intValue) == 0 ? Float.NaN : View.MeasureSpec.getSize(intValue);
            if (View.MeasureSpec.getMode(intValue2) != 0) {
                f = View.MeasureSpec.getSize(intValue2);
            }
            reactShadowNode.calculateLayout(size, f);
        } finally {
            Systrace.endSection(0L);
            this.mLastCalculateLayoutTime = SystemClock.uptimeMillis() - uptimeMillis;
        }
    }

    protected void applyUpdatesRecursive(ReactShadowNode reactShadowNode, float f, float f2) {
        if (reactShadowNode.hasUpdates()) {
            Iterable<? extends ReactShadowNode> calculateLayoutOnChildren = reactShadowNode.calculateLayoutOnChildren();
            if (calculateLayoutOnChildren != null) {
                for (ReactShadowNode reactShadowNode2 : calculateLayoutOnChildren) {
                    applyUpdatesRecursive(reactShadowNode2, reactShadowNode.getLayoutX() + f, reactShadowNode.getLayoutY() + f2);
                }
            }
            int reactTag = reactShadowNode.getReactTag();
            if (!this.mShadowNodeRegistry.isRootNode(reactTag) && reactShadowNode.dispatchUpdates(f, f2, this.mOperationsQueue, this.mNativeViewHierarchyOptimizer) && reactShadowNode.shouldNotifyOnLayout()) {
                this.mEventDispatcher.dispatchEvent(OnLayoutEvent.obtain(-1, reactTag, reactShadowNode.getScreenX(), reactShadowNode.getScreenY(), reactShadowNode.getScreenWidth(), reactShadowNode.getScreenHeight()));
            }
            reactShadowNode.markUpdateSeen();
            this.mNativeViewHierarchyOptimizer.onViewUpdatesCompleted(reactShadowNode);
        }
    }

    public void addUIBlock(UIBlock uIBlock) {
        this.mOperationsQueue.enqueueUIBlock(uIBlock);
    }

    public void prependUIBlock(UIBlock uIBlock) {
        this.mOperationsQueue.prependUIBlock(uIBlock);
    }

    public int resolveRootTagFromReactTag(int r4) {
        if (this.mShadowNodeRegistry.isRootNode(r4)) {
            return r4;
        }
        ReactShadowNode resolveShadowNode = resolveShadowNode(r4);
        if (resolveShadowNode != null) {
            return resolveShadowNode.getRootTag();
        }
        FLog.m1288w(ReactConstants.TAG, "Warning : attempted to resolve a non-existent react shadow node. reactTag=" + r4);
        return 0;
    }

    public void setLayoutUpdateListener(LayoutUpdateListener layoutUpdateListener) {
        this.mLayoutUpdateListener = layoutUpdateListener;
    }

    public void removeLayoutUpdateListener() {
        this.mLayoutUpdateListener = null;
    }
}
