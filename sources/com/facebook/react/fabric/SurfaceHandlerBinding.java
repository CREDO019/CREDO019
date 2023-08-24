package com.facebook.react.fabric;

import com.facebook.jni.HybridData;
import com.facebook.react.bridge.NativeMap;
import com.facebook.react.fabric.mounting.LayoutMetricsConversions;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/* loaded from: classes.dex */
public class SurfaceHandlerBinding implements SurfaceHandler {
    public static final int DISPLAY_MODE_HIDDEN = 2;
    public static final int DISPLAY_MODE_SUSPENDED = 1;
    public static final int DISPLAY_MODE_VISIBLE = 0;
    private static final int NO_SURFACE_ID = 0;
    private final HybridData mHybridData;

    @Retention(RetentionPolicy.SOURCE)
    /* loaded from: classes.dex */
    public @interface DisplayModeTypeDef {
    }

    private native String getModuleNameNative();

    private native int getSurfaceIdNative();

    private static native HybridData initHybrid(int r0, String str);

    private native boolean isRunningNative();

    private native void setDisplayModeNative(int r1);

    private native void setLayoutConstraintsNative(float f, float f2, float f3, float f4, float f5, float f6, boolean z, boolean z2, float f7);

    private native void setPropsNative(NativeMap nativeMap);

    private native void setSurfaceIdNative(int r1);

    private native void startNative();

    private native void stopNative();

    static {
        FabricSoLoader.staticInit();
    }

    public SurfaceHandlerBinding(String str) {
        this.mHybridData = initHybrid(0, str);
    }

    @Override // com.facebook.react.fabric.SurfaceHandler
    public int getSurfaceId() {
        return getSurfaceIdNative();
    }

    @Override // com.facebook.react.fabric.SurfaceHandler
    public void setSurfaceId(int r1) {
        setSurfaceIdNative(r1);
    }

    @Override // com.facebook.react.fabric.SurfaceHandler
    public String getModuleName() {
        return getModuleNameNative();
    }

    @Override // com.facebook.react.fabric.SurfaceHandler
    public void start() {
        startNative();
    }

    @Override // com.facebook.react.fabric.SurfaceHandler
    public void stop() {
        stopNative();
    }

    @Override // com.facebook.react.fabric.SurfaceHandler
    public boolean isRunning() {
        return isRunningNative();
    }

    @Override // com.facebook.react.fabric.SurfaceHandler
    public void setLayoutConstraints(int r11, int r12, int r13, int r14, boolean z, boolean z2, float f) {
        setLayoutConstraintsNative(LayoutMetricsConversions.CC.getMinSize(r11) / f, LayoutMetricsConversions.CC.getMaxSize(r11) / f, LayoutMetricsConversions.CC.getMinSize(r12) / f, LayoutMetricsConversions.CC.getMaxSize(r12) / f, r13 / f, r14 / f, z, z2, f);
    }

    @Override // com.facebook.react.fabric.SurfaceHandler
    public void setProps(NativeMap nativeMap) {
        setPropsNative(nativeMap);
    }

    @Override // com.facebook.react.fabric.SurfaceHandler
    public void setMountable(boolean z) {
        setDisplayModeNative(!z ? 1 : 0);
    }
}
