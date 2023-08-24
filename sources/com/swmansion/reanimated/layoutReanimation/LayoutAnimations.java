package com.swmansion.reanimated.layoutReanimation;

import com.facebook.jni.HybridData;
import com.facebook.react.bridge.ReactApplicationContext;
import com.swmansion.reanimated.ReanimatedModule;
import java.lang.ref.WeakReference;
import java.util.Map;

/* loaded from: classes3.dex */
public class LayoutAnimations {
    private WeakReference<ReactApplicationContext> mContext;
    private final HybridData mHybridData = initHybrid();

    private native HybridData initHybrid();

    public native boolean isLayoutAnimationEnabled();

    public native void removeConfigForTag(int r1);

    public native void startAnimationForTag(int r1, String str, Map<String, String> map);

    static {
        System.loadLibrary("reanimated");
    }

    public LayoutAnimations(ReactApplicationContext reactApplicationContext) {
        this.mContext = new WeakReference<>(reactApplicationContext);
    }

    private void notifyAboutEnd(int r3, int r4) {
        ReactApplicationContext reactApplicationContext = this.mContext.get();
        if (reactApplicationContext != null) {
            ((ReanimatedModule) reactApplicationContext.getNativeModule(ReanimatedModule.class)).getNodesManager().getAnimationsManager().notifyAboutEnd(r3, r4 != 0);
        }
    }

    private void notifyAboutProgress(Map<String, Object> map, int r4) {
        ReactApplicationContext reactApplicationContext = this.mContext.get();
        if (reactApplicationContext != null) {
            ((ReanimatedModule) reactApplicationContext.getNativeModule(ReanimatedModule.class)).getNodesManager().getAnimationsManager().notifyAboutProgress(map, Integer.valueOf(r4));
        }
    }
}
