package com.google.android.play.core.assetpacks;

import java.util.concurrent.ThreadFactory;

/* renamed from: com.google.android.play.core.assetpacks.l */
/* loaded from: classes3.dex */
final /* synthetic */ class ThreadFactoryC2466l implements ThreadFactory {

    /* renamed from: a */
    static final ThreadFactory f771a = new ThreadFactoryC2466l();

    private ThreadFactoryC2466l() {
    }

    @Override // java.util.concurrent.ThreadFactory
    public final Thread newThread(Runnable runnable) {
        return new Thread(runnable, "AssetPackBackgroundExecutor");
    }
}
