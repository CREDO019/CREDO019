package com.google.android.gms.vision;

import android.util.Log;

/* compiled from: com.google.android.gms:play-services-vision-common@@19.0.0 */
/* renamed from: com.google.android.gms.vision.L */
/* loaded from: classes3.dex */
public class C2148L {
    public static final String TAG = "Vision";

    /* renamed from: v */
    public static int m1077v(String str, Object... objArr) {
        if (Log.isLoggable(TAG, 2)) {
            return Log.v(TAG, String.format(str, objArr));
        }
        return 0;
    }

    /* renamed from: d */
    public static int m1082d(String str, Object... objArr) {
        if (Log.isLoggable(TAG, 3)) {
            return Log.d(TAG, String.format(str, objArr));
        }
        return 0;
    }

    /* renamed from: d */
    public static int m1081d(Throwable th, String str, Object... objArr) {
        if (Log.isLoggable(TAG, 3)) {
            return Log.d(TAG, String.format(str, objArr), th);
        }
        return 0;
    }

    /* renamed from: i */
    public static int m1078i(String str, Object... objArr) {
        if (Log.isLoggable(TAG, 4)) {
            return Log.i(TAG, String.format(str, objArr));
        }
        return 0;
    }

    /* renamed from: e */
    public static int m1080e(String str, Object... objArr) {
        if (Log.isLoggable(TAG, 6)) {
            return Log.e(TAG, String.format(str, objArr));
        }
        return 0;
    }

    /* renamed from: e */
    public static int m1079e(Throwable th, String str, Object... objArr) {
        if (Log.isLoggable(TAG, 6)) {
            if (Log.isLoggable(TAG, 3)) {
                return Log.e(TAG, String.format(str, objArr), th);
            }
            String format = String.format(str, objArr);
            String valueOf = String.valueOf(th);
            StringBuilder sb = new StringBuilder(String.valueOf(format).length() + 2 + String.valueOf(valueOf).length());
            sb.append(format);
            sb.append(": ");
            sb.append(valueOf);
            return Log.e(TAG, sb.toString());
        }
        return 0;
    }

    /* renamed from: w */
    public static int m1076w(String str, Object... objArr) {
        if (Log.isLoggable(TAG, 5)) {
            return Log.w(TAG, String.format(str, objArr));
        }
        return 0;
    }

    /* renamed from: w */
    public static int m1075w(Throwable th, String str, Object... objArr) {
        if (Log.isLoggable(TAG, 5)) {
            if (Log.isLoggable(TAG, 3)) {
                return Log.w(TAG, String.format(str, objArr), th);
            }
            String format = String.format(str, objArr);
            String valueOf = String.valueOf(th);
            StringBuilder sb = new StringBuilder(String.valueOf(format).length() + 2 + String.valueOf(valueOf).length());
            sb.append(format);
            sb.append(": ");
            sb.append(valueOf);
            return Log.w(TAG, sb.toString());
        }
        return 0;
    }
}
