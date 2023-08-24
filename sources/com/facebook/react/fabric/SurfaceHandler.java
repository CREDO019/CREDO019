package com.facebook.react.fabric;

import com.facebook.react.bridge.NativeMap;

/* loaded from: classes.dex */
public interface SurfaceHandler {
    String getModuleName();

    int getSurfaceId();

    boolean isRunning();

    void setLayoutConstraints(int r1, int r2, int r3, int r4, boolean z, boolean z2, float f);

    void setMountable(boolean z);

    void setProps(NativeMap nativeMap);

    void setSurfaceId(int r1);

    void start();

    void stop();
}
