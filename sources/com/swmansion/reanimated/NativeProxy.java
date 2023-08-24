package com.swmansion.reanimated;

import android.os.SystemClock;
import android.util.Log;
import com.facebook.jni.HybridData;
import com.facebook.react.ReactApplication;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReadableNativeArray;
import com.facebook.react.bridge.WritableArray;
import com.facebook.react.bridge.WritableMap;
import com.facebook.react.devsupport.interfaces.DevOptionHandler;
import com.facebook.react.turbomodule.core.CallInvokerHolderImpl;
import com.facebook.react.uimanager.UIManagerModule;
import com.facebook.react.uimanager.events.RCTEventEmitter;
import com.swmansion.common.GestureHandlerStateManager;
import com.swmansion.reanimated.NodesManager;
import com.swmansion.reanimated.keyboardObserver.ReanimatedKeyboardEventListener;
import com.swmansion.reanimated.layoutReanimation.AnimationsManager;
import com.swmansion.reanimated.layoutReanimation.LayoutAnimations;
import com.swmansion.reanimated.layoutReanimation.NativeMethodsHolder;
import com.swmansion.reanimated.sensor.ReanimatedSensorContainer;
import com.swmansion.reanimated.sensor.ReanimatedSensorType;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/* loaded from: classes3.dex */
public class NativeProxy {
    private final GestureHandlerStateManager gestureHandlerStateManager;
    private final WeakReference<ReactApplicationContext> mContext;
    private final HybridData mHybridData;
    private NodesManager mNodesManager;
    private Scheduler mScheduler;
    private ReanimatedKeyboardEventListener reanimatedKeyboardEventListener;
    private ReanimatedSensorContainer reanimatedSensorContainer;
    private Long firstUptime = Long.valueOf(SystemClock.uptimeMillis());
    private boolean slowAnimationsEnabled = false;

    private native HybridData initHybrid(long j, CallInvokerHolderImpl callInvokerHolderImpl, Scheduler scheduler, LayoutAnimations layoutAnimations);

    private native void installJSIBindings();

    public native boolean isAnyHandlerWaitingForEvent(String str);

    static {
        System.loadLibrary("reanimated");
    }

    /* loaded from: classes3.dex */
    public static class AnimationFrameCallback implements NodesManager.OnAnimationFrame {
        private final HybridData mHybridData;

        @Override // com.swmansion.reanimated.NodesManager.OnAnimationFrame
        public native void onAnimationFrame(double d);

        private AnimationFrameCallback(HybridData hybridData) {
            this.mHybridData = hybridData;
        }
    }

    /* loaded from: classes3.dex */
    public static class EventHandler implements RCTEventEmitter {
        private UIManagerModule.CustomEventNamesResolver mCustomEventNamesResolver;
        private final HybridData mHybridData;

        public native void receiveEvent(String str, WritableMap writableMap);

        @Override // com.facebook.react.uimanager.events.RCTEventEmitter
        public void receiveTouches(String str, WritableArray writableArray, WritableArray writableArray2) {
        }

        private EventHandler(HybridData hybridData) {
            this.mHybridData = hybridData;
        }

        @Override // com.facebook.react.uimanager.events.RCTEventEmitter
        public void receiveEvent(int r2, String str, WritableMap writableMap) {
            String resolveCustomEventName = this.mCustomEventNamesResolver.resolveCustomEventName(str);
            receiveEvent(r2 + resolveCustomEventName, writableMap);
        }
    }

    /* loaded from: classes3.dex */
    public static class SensorSetter {
        private final HybridData mHybridData;

        public native void sensorSetter(float[] fArr);

        private SensorSetter(HybridData hybridData) {
            this.mHybridData = hybridData;
        }
    }

    /* loaded from: classes3.dex */
    public static class KeyboardEventDataUpdater {
        private final HybridData mHybridData;

        public native void keyboardEventDataUpdater(int r1, int r2);

        private KeyboardEventDataUpdater(HybridData hybridData) {
            this.mHybridData = hybridData;
        }
    }

    public NativeProxy(ReactApplicationContext reactApplicationContext) {
        GestureHandlerStateManager gestureHandlerStateManager = null;
        this.mScheduler = null;
        CallInvokerHolderImpl callInvokerHolderImpl = (CallInvokerHolderImpl) reactApplicationContext.getCatalystInstance().getJSCallInvokerHolder();
        LayoutAnimations layoutAnimations = new LayoutAnimations(reactApplicationContext);
        this.mScheduler = new Scheduler(reactApplicationContext);
        this.mHybridData = initHybrid(reactApplicationContext.getJavaScriptContextHolder().get(), callInvokerHolderImpl, this.mScheduler, layoutAnimations);
        WeakReference<ReactApplicationContext> weakReference = new WeakReference<>(reactApplicationContext);
        this.mContext = weakReference;
        prepare(layoutAnimations);
        this.reanimatedSensorContainer = new ReanimatedSensorContainer(weakReference);
        this.reanimatedKeyboardEventListener = new ReanimatedKeyboardEventListener(weakReference);
        addDevMenuOption();
        try {
            gestureHandlerStateManager = (GestureHandlerStateManager) reactApplicationContext.getNativeModule(Class.forName("com.swmansion.gesturehandler.react.RNGestureHandlerModule"));
        } catch (ClassCastException | ClassNotFoundException unused) {
        }
        this.gestureHandlerStateManager = gestureHandlerStateManager;
    }

    public Scheduler getScheduler() {
        return this.mScheduler;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void toggleSlowAnimations() {
        boolean z = !this.slowAnimationsEnabled;
        this.slowAnimationsEnabled = z;
        if (z) {
            this.firstUptime = Long.valueOf(SystemClock.uptimeMillis());
        }
    }

    private void addDevMenuOption() {
        if (this.mContext.get().getApplicationContext() instanceof ReactApplication) {
            ((ReactApplication) this.mContext.get().getApplicationContext()).getReactNativeHost().getReactInstanceManager().getDevSupportManager().addCustomDevOption("Toggle slow animations (Reanimated)", new DevOptionHandler() { // from class: com.swmansion.reanimated.NativeProxy$$ExternalSyntheticLambda0
                @Override // com.facebook.react.devsupport.interfaces.DevOptionHandler
                public final void onOptionSelected() {
                    NativeProxy.this.toggleSlowAnimations();
                }
            });
        }
    }

    private void requestRender(AnimationFrameCallback animationFrameCallback) {
        this.mNodesManager.postOnAnimation(animationFrameCallback);
    }

    private void updateProps(int r2, Map<String, Object> map) {
        this.mNodesManager.updateProps(r2, map);
    }

    private String obtainProp(int r2, String str) {
        return this.mNodesManager.obtainProp(r2, str);
    }

    private void scrollTo(int r8, double d, double d2, boolean z) {
        this.mNodesManager.scrollTo(r8, d, d2, z);
    }

    private void setGestureState(int r2, int r3) {
        GestureHandlerStateManager gestureHandlerStateManager = this.gestureHandlerStateManager;
        if (gestureHandlerStateManager != null) {
            gestureHandlerStateManager.setGestureHandlerState(r2, r3);
        }
    }

    private long getCurrentTime() {
        if (this.slowAnimationsEnabled) {
            return this.firstUptime.longValue() + ((SystemClock.uptimeMillis() - this.firstUptime.longValue()) / 10);
        }
        return SystemClock.uptimeMillis();
    }

    private float[] measure(int r2) {
        return this.mNodesManager.measure(r2);
    }

    private void configureProps(ReadableNativeArray readableNativeArray, ReadableNativeArray readableNativeArray2) {
        this.mNodesManager.configureProps(convertProps(readableNativeArray), convertProps(readableNativeArray2));
    }

    private Set<String> convertProps(ReadableNativeArray readableNativeArray) {
        HashSet hashSet = new HashSet();
        ArrayList<Object> arrayList = readableNativeArray.toArrayList();
        for (int r1 = 0; r1 < arrayList.size(); r1++) {
            hashSet.add((String) arrayList.get(r1));
        }
        return hashSet;
    }

    private void registerEventHandler(EventHandler eventHandler) {
        eventHandler.mCustomEventNamesResolver = this.mNodesManager.getEventNameResolver();
        this.mNodesManager.registerEventHandler(eventHandler);
    }

    private int registerSensor(int r2, int r3, SensorSetter sensorSetter) {
        return this.reanimatedSensorContainer.registerSensor(ReanimatedSensorType.getInstanceById(r2), r3, sensorSetter);
    }

    private void unregisterSensor(int r2) {
        this.reanimatedSensorContainer.unregisterSensor(r2);
    }

    private int subscribeForKeyboardEvents(KeyboardEventDataUpdater keyboardEventDataUpdater) {
        return this.reanimatedKeyboardEventListener.subscribeForKeyboardEvents(keyboardEventDataUpdater);
    }

    private void unsubscribeFromKeyboardEvents(int r2) {
        this.reanimatedKeyboardEventListener.unsubscribeFromKeyboardEvents(r2);
    }

    public void onCatalystInstanceDestroy() {
        this.mScheduler.deactivate();
        this.mHybridData.resetNative();
    }

    public void prepare(final LayoutAnimations layoutAnimations) {
        if (C4139Utils.isChromeDebugger) {
            Log.w("[REANIMATED]", "You can not use LayoutAnimation with enabled Chrome Debugger");
            return;
        }
        this.mNodesManager = ((ReanimatedModule) this.mContext.get().getNativeModule(ReanimatedModule.class)).getNodesManager();
        installJSIBindings();
        AnimationsManager animationsManager = ((ReanimatedModule) this.mContext.get().getNativeModule(ReanimatedModule.class)).getNodesManager().getAnimationsManager();
        final WeakReference weakReference = new WeakReference(layoutAnimations);
        animationsManager.setNativeMethods(new NativeMethodsHolder() { // from class: com.swmansion.reanimated.NativeProxy.1
            @Override // com.swmansion.reanimated.layoutReanimation.NativeMethodsHolder
            public void startAnimationForTag(int r6, String str, HashMap<String, Float> hashMap) {
                LayoutAnimations layoutAnimations2 = (LayoutAnimations) weakReference.get();
                if (layoutAnimations2 != null) {
                    HashMap hashMap2 = new HashMap();
                    for (String str2 : hashMap.keySet()) {
                        hashMap2.put(str2, hashMap.get(str2).toString());
                    }
                    layoutAnimations2.startAnimationForTag(r6, str, hashMap2);
                }
            }

            @Override // com.swmansion.reanimated.layoutReanimation.NativeMethodsHolder
            public void removeConfigForTag(int r2) {
                LayoutAnimations layoutAnimations2 = (LayoutAnimations) weakReference.get();
                if (layoutAnimations2 != null) {
                    layoutAnimations2.removeConfigForTag(r2);
                }
            }

            @Override // com.swmansion.reanimated.layoutReanimation.NativeMethodsHolder
            public boolean isLayoutAnimationEnabled() {
                return layoutAnimations.isLayoutAnimationEnabled();
            }
        });
    }
}
