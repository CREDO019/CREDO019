package com.onesignal;

/* loaded from: classes3.dex */
public interface OSLogger {
    void debug(String str);

    void error(String str);

    void error(String str, Throwable th);

    void info(String str);

    void verbose(String str);

    void warning(String str);
}
