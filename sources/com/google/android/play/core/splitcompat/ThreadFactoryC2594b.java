package com.google.android.play.core.splitcompat;

import java.util.concurrent.ThreadFactory;

/* renamed from: com.google.android.play.core.splitcompat.b */
/* loaded from: classes3.dex */
final class ThreadFactoryC2594b implements ThreadFactory {
    @Override // java.util.concurrent.ThreadFactory
    public final Thread newThread(Runnable runnable) {
        return new Thread(runnable, "SplitCompatBackgroundThread");
    }
}
