package com.google.android.play.core.tasks;

import android.os.Handler;
import android.os.Looper;
import java.util.concurrent.Executor;

/* renamed from: com.google.android.play.core.tasks.l */
/* loaded from: classes3.dex */
final class ExecutorC2685l implements Executor {

    /* renamed from: a */
    private final Handler f1111a = new Handler(Looper.getMainLooper());

    @Override // java.util.concurrent.Executor
    public final void execute(Runnable runnable) {
        this.f1111a.post(runnable);
    }
}
