package com.onesignal;

import com.onesignal.OneSignal;

/* loaded from: classes3.dex */
class OSLogWrapper implements OSLogger {
    @Override // com.onesignal.OSLogger
    public void verbose(String str) {
        OneSignal.Log(OneSignal.LOG_LEVEL.VERBOSE, str);
    }

    @Override // com.onesignal.OSLogger
    public void debug(String str) {
        OneSignal.Log(OneSignal.LOG_LEVEL.DEBUG, str);
    }

    @Override // com.onesignal.OSLogger
    public void info(String str) {
        OneSignal.Log(OneSignal.LOG_LEVEL.INFO, str);
    }

    @Override // com.onesignal.OSLogger
    public void warning(String str) {
        OneSignal.Log(OneSignal.LOG_LEVEL.WARN, str);
    }

    @Override // com.onesignal.OSLogger
    public void error(String str) {
        OneSignal.Log(OneSignal.LOG_LEVEL.ERROR, str);
    }

    @Override // com.onesignal.OSLogger
    public void error(String str, Throwable th) {
        OneSignal.Log(OneSignal.LOG_LEVEL.ERROR, str, th);
    }
}
