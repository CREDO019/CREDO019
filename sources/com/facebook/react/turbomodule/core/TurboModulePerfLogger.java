package com.facebook.react.turbomodule.core;

import com.facebook.react.perflogger.NativeModulePerfLogger;
import com.facebook.soloader.SoLoader;
import javax.annotation.Nullable;

/* loaded from: classes.dex */
public class TurboModulePerfLogger {
    private static boolean sIsSoLibraryLoaded = false;
    @Nullable
    private static NativeModulePerfLogger sNativeModulePerfLogger;

    private static native void jniEnableCppLogging(NativeModulePerfLogger nativeModulePerfLogger);

    public static void moduleDataCreateStart(String str, int r2) {
        NativeModulePerfLogger nativeModulePerfLogger = sNativeModulePerfLogger;
        if (nativeModulePerfLogger != null) {
            nativeModulePerfLogger.moduleDataCreateStart(str, r2);
        }
    }

    public static void moduleDataCreateEnd(String str, int r2) {
        NativeModulePerfLogger nativeModulePerfLogger = sNativeModulePerfLogger;
        if (nativeModulePerfLogger != null) {
            nativeModulePerfLogger.moduleDataCreateEnd(str, r2);
        }
    }

    public static void moduleCreateStart(String str, int r2) {
        NativeModulePerfLogger nativeModulePerfLogger = sNativeModulePerfLogger;
        if (nativeModulePerfLogger != null) {
            nativeModulePerfLogger.moduleCreateStart(str, r2);
        }
    }

    public static void moduleCreateCacheHit(String str, int r2) {
        NativeModulePerfLogger nativeModulePerfLogger = sNativeModulePerfLogger;
        if (nativeModulePerfLogger != null) {
            nativeModulePerfLogger.moduleCreateCacheHit(str, r2);
        }
    }

    public static void moduleCreateConstructStart(String str, int r2) {
        NativeModulePerfLogger nativeModulePerfLogger = sNativeModulePerfLogger;
        if (nativeModulePerfLogger != null) {
            nativeModulePerfLogger.moduleCreateConstructStart(str, r2);
        }
    }

    public static void moduleCreateConstructEnd(String str, int r2) {
        NativeModulePerfLogger nativeModulePerfLogger = sNativeModulePerfLogger;
        if (nativeModulePerfLogger != null) {
            nativeModulePerfLogger.moduleCreateConstructEnd(str, r2);
        }
    }

    public static void moduleCreateSetUpStart(String str, int r2) {
        NativeModulePerfLogger nativeModulePerfLogger = sNativeModulePerfLogger;
        if (nativeModulePerfLogger != null) {
            nativeModulePerfLogger.moduleCreateSetUpStart(str, r2);
        }
    }

    public static void moduleCreateSetUpEnd(String str, int r2) {
        NativeModulePerfLogger nativeModulePerfLogger = sNativeModulePerfLogger;
        if (nativeModulePerfLogger != null) {
            nativeModulePerfLogger.moduleCreateSetUpEnd(str, r2);
        }
    }

    public static void moduleCreateEnd(String str, int r2) {
        NativeModulePerfLogger nativeModulePerfLogger = sNativeModulePerfLogger;
        if (nativeModulePerfLogger != null) {
            nativeModulePerfLogger.moduleCreateEnd(str, r2);
        }
    }

    public static void moduleCreateFail(String str, int r2) {
        NativeModulePerfLogger nativeModulePerfLogger = sNativeModulePerfLogger;
        if (nativeModulePerfLogger != null) {
            nativeModulePerfLogger.moduleCreateFail(str, r2);
        }
    }

    private static synchronized void maybeLoadSoLibrary() {
        synchronized (TurboModulePerfLogger.class) {
            if (!sIsSoLibraryLoaded) {
                SoLoader.loadLibrary("turbomodulejsijni");
                sIsSoLibraryLoaded = true;
            }
        }
    }

    public static void enableLogging(NativeModulePerfLogger nativeModulePerfLogger) {
        if (nativeModulePerfLogger != null) {
            sNativeModulePerfLogger = nativeModulePerfLogger;
            maybeLoadSoLibrary();
            jniEnableCppLogging(nativeModulePerfLogger);
        }
    }
}
