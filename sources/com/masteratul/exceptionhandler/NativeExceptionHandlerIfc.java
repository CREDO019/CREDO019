package com.masteratul.exceptionhandler;

import java.lang.Thread;

/* loaded from: classes3.dex */
public interface NativeExceptionHandlerIfc {
    void handleNativeException(Thread thread, Throwable th, Thread.UncaughtExceptionHandler uncaughtExceptionHandler);
}
