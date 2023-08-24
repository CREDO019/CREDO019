package com.thunkable.live.newarchitecture.components;

import com.facebook.jni.HybridData;
import com.facebook.react.fabric.ComponentFactory;
import com.facebook.soloader.SoLoader;

/* loaded from: classes4.dex */
public class MainComponentsRegistry {
    private final HybridData mHybridData;

    private native HybridData initHybrid(ComponentFactory componentFactory);

    static {
        SoLoader.loadLibrary("fabricjni");
    }

    private MainComponentsRegistry(ComponentFactory componentFactory) {
        this.mHybridData = initHybrid(componentFactory);
    }

    public static MainComponentsRegistry register(ComponentFactory componentFactory) {
        return new MainComponentsRegistry(componentFactory);
    }
}
