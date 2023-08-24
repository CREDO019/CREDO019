package com.amplitude.api;

import android.util.Log;

/* loaded from: classes.dex */
public class AmplitudeLog {
    protected static AmplitudeLog instance = new AmplitudeLog();
    private volatile boolean enableLogging = true;
    private volatile int logLevel = 4;
    private AmplitudeLogCallback amplitudeLogCallback = null;

    public static AmplitudeLog getLogger() {
        return instance;
    }

    private AmplitudeLog() {
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public AmplitudeLog setEnableLogging(boolean z) {
        this.enableLogging = z;
        return instance;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public AmplitudeLog setLogLevel(int r1) {
        this.logLevel = r1;
        return instance;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: d */
    public int m1370d(String str, String str2) {
        if (!this.enableLogging || this.logLevel > 3) {
            return 0;
        }
        return Log.d(str, str2);
    }

    /* renamed from: d */
    int m1369d(String str, String str2, Throwable th) {
        if (!this.enableLogging || this.logLevel > 3) {
            return 0;
        }
        return Log.d(str, str2, th);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: e */
    public int m1368e(String str, String str2) {
        if (!this.enableLogging || this.logLevel > 6) {
            return 0;
        }
        AmplitudeLogCallback amplitudeLogCallback = this.amplitudeLogCallback;
        if (amplitudeLogCallback != null) {
            amplitudeLogCallback.onError(str, str2);
        }
        return Log.e(str, str2);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: e */
    public int m1367e(String str, String str2, Throwable th) {
        if (!this.enableLogging || this.logLevel > 6) {
            return 0;
        }
        AmplitudeLogCallback amplitudeLogCallback = this.amplitudeLogCallback;
        if (amplitudeLogCallback != null) {
            amplitudeLogCallback.onError(str, str2);
        }
        return Log.e(str, str2, th);
    }

    String getStackTraceString(Throwable th) {
        return Log.getStackTraceString(th);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: i */
    public int m1366i(String str, String str2) {
        if (!this.enableLogging || this.logLevel > 4) {
            return 0;
        }
        return Log.i(str, str2);
    }

    /* renamed from: i */
    int m1365i(String str, String str2, Throwable th) {
        if (!this.enableLogging || this.logLevel > 4) {
            return 0;
        }
        return Log.i(str, str2, th);
    }

    boolean isLoggable(String str, int r2) {
        return Log.isLoggable(str, r2);
    }

    int println(int r1, String str, String str2) {
        return Log.println(r1, str, str2);
    }

    /* renamed from: v */
    int m1364v(String str, String str2) {
        if (!this.enableLogging || this.logLevel > 2) {
            return 0;
        }
        return Log.v(str, str2);
    }

    /* renamed from: v */
    int m1363v(String str, String str2, Throwable th) {
        if (!this.enableLogging || this.logLevel > 2) {
            return 0;
        }
        return Log.v(str, str2, th);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: w */
    public int m1362w(String str, String str2) {
        if (!this.enableLogging || this.logLevel > 5) {
            return 0;
        }
        return Log.w(str, str2);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: w */
    public int m1360w(String str, Throwable th) {
        if (!this.enableLogging || this.logLevel > 5) {
            return 0;
        }
        return Log.w(str, th);
    }

    /* renamed from: w */
    int m1361w(String str, String str2, Throwable th) {
        if (!this.enableLogging || this.logLevel > 5) {
            return 0;
        }
        return Log.w(str, str2, th);
    }

    int wtf(String str, String str2) {
        if (!this.enableLogging || this.logLevel > 7) {
            return 0;
        }
        return Log.wtf(str, str2);
    }

    int wtf(String str, Throwable th) {
        if (!this.enableLogging || this.logLevel > 7) {
            return 0;
        }
        return Log.wtf(str, th);
    }

    int wtf(String str, String str2, Throwable th) {
        if (!this.enableLogging || this.logLevel > 7) {
            return 0;
        }
        return Log.wtf(str, str2, th);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void setAmplitudeLogCallback(AmplitudeLogCallback amplitudeLogCallback) {
        this.amplitudeLogCallback = amplitudeLogCallback;
    }
}
