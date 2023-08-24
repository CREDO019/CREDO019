package com.facebook.react.util;

import com.facebook.common.logging.FLog;
import com.facebook.react.bridge.ReactContext;
import com.facebook.react.common.ReactConstants;

/* loaded from: classes.dex */
public class RNLog {
    public static final int ADVICE = 4;
    public static final int ERROR = 6;
    public static final int LOG = 2;
    public static final int MINIMUM_LEVEL_FOR_UI = 5;
    public static final int TRACE = 3;
    public static final int WARN = 5;

    private static String levelToString(int r2) {
        String str = "log";
        if (r2 != 2 && r2 != 3) {
            str = "warn";
            if (r2 != 4 && r2 != 5) {
                return r2 != 6 ? "none" : "error";
            }
        }
        return str;
    }

    /* renamed from: l */
    public static void m1251l(String str) {
        FLog.m1316i(ReactConstants.TAG, str);
    }

    /* renamed from: t */
    public static void m1250t(String str) {
        FLog.m1316i(ReactConstants.TAG, str);
    }

    /* renamed from: a */
    public static void m1254a(String str) {
        FLog.m1288w(ReactConstants.TAG, "(ADVICE)" + str);
    }

    /* renamed from: w */
    public static void m1249w(ReactContext reactContext, String str) {
        logInternal(reactContext, str, 5);
        FLog.m1288w(ReactConstants.TAG, str);
    }

    /* renamed from: e */
    public static void m1253e(ReactContext reactContext, String str) {
        logInternal(reactContext, str, 6);
        FLog.m1328e(ReactConstants.TAG, str);
    }

    /* renamed from: e */
    public static void m1252e(String str) {
        FLog.m1328e(ReactConstants.TAG, str);
    }

    private static void logInternal(ReactContext reactContext, String str, int r3) {
        if (r3 < 5 || reactContext == null || str == null) {
            return;
        }
        ((RCTLog) reactContext.getJSModule(RCTLog.class)).logIfNoNativeHook(levelToString(r3), str);
    }
}
