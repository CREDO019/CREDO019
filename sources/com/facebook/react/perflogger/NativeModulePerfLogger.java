package com.facebook.react.perflogger;

import com.facebook.jni.HybridData;
import com.facebook.soloader.SoLoader;

/* loaded from: classes.dex */
public abstract class NativeModulePerfLogger {
    private static volatile boolean sIsSoLibraryLoaded;
    private final HybridData mHybridData;

    protected abstract HybridData initHybrid();

    public abstract void moduleCreateCacheHit(String str, int r2);

    public abstract void moduleCreateConstructEnd(String str, int r2);

    public abstract void moduleCreateConstructStart(String str, int r2);

    public abstract void moduleCreateEnd(String str, int r2);

    public abstract void moduleCreateFail(String str, int r2);

    public abstract void moduleCreateSetUpEnd(String str, int r2);

    public abstract void moduleCreateSetUpStart(String str, int r2);

    public abstract void moduleCreateStart(String str, int r2);

    public abstract void moduleDataCreateEnd(String str, int r2);

    public abstract void moduleDataCreateStart(String str, int r2);

    protected NativeModulePerfLogger() {
        maybeLoadOtherSoLibraries();
        maybeLoadSoLibrary();
        this.mHybridData = initHybrid();
    }

    private static synchronized void maybeLoadSoLibrary() {
        synchronized (NativeModulePerfLogger.class) {
            if (!sIsSoLibraryLoaded) {
                SoLoader.loadLibrary("reactperfloggerjni");
                sIsSoLibraryLoaded = true;
            }
        }
    }

    protected synchronized void maybeLoadOtherSoLibraries() {
    }
}
