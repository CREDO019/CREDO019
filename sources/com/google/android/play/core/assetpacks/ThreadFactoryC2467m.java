package com.google.android.play.core.assetpacks;

import java.util.concurrent.ThreadFactory;

/* renamed from: com.google.android.play.core.assetpacks.m */
/* loaded from: classes3.dex */
final /* synthetic */ class ThreadFactoryC2467m implements ThreadFactory {

    /* renamed from: a */
    static final ThreadFactory f772a = new ThreadFactoryC2467m();

    private ThreadFactoryC2467m() {
    }

    @Override // java.util.concurrent.ThreadFactory
    public final Thread newThread(Runnable runnable) {
        return new Thread(runnable, "UpdateListenerExecutor");
    }
}
