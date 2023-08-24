package com.onesignal;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: BackgroundRunnable.kt */
@Metadata(m185bv = {1, 0, 3}, m184d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\b\u0010\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\b\u0010\u0003\u001a\u00020\u0004H\u0016¨\u0006\u0005"}, m183d2 = {"Lcom/onesignal/BackgroundRunnable;", "Ljava/lang/Runnable;", "()V", "run", "", "onesignal_release"}, m182k = 1, m181mv = {1, 4, 2})
/* loaded from: classes3.dex */
public class BackgroundRunnable implements Runnable {
    @Override // java.lang.Runnable
    public void run() {
        Thread currentThread = Thread.currentThread();
        Intrinsics.checkNotNullExpressionValue(currentThread, "Thread.currentThread()");
        currentThread.setPriority(10);
    }
}
