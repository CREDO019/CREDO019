package com.facebook.common.logging;

/* loaded from: classes.dex */
public interface LoggingDelegate {
    /* renamed from: d */
    void mo1284d(String tag, String msg);

    /* renamed from: d */
    void mo1283d(String tag, String msg, Throwable tr);

    /* renamed from: e */
    void mo1282e(String tag, String msg);

    /* renamed from: e */
    void mo1281e(String tag, String msg, Throwable tr);

    int getMinimumLoggingLevel();

    /* renamed from: i */
    void mo1280i(String tag, String msg);

    /* renamed from: i */
    void mo1279i(String tag, String msg, Throwable tr);

    boolean isLoggable(int level);

    void log(int priority, String tag, String msg);

    void setMinimumLoggingLevel(int level);

    /* renamed from: v */
    void mo1278v(String tag, String msg);

    /* renamed from: v */
    void mo1277v(String tag, String msg, Throwable tr);

    /* renamed from: w */
    void mo1276w(String tag, String msg);

    /* renamed from: w */
    void mo1275w(String tag, String msg, Throwable tr);

    void wtf(String tag, String msg);

    void wtf(String tag, String msg, Throwable tr);
}
