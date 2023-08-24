package com.google.android.play.core.assetpacks;

import com.google.android.play.core.internal.C2532bq;
import com.google.android.play.core.internal.InterfaceC2556cn;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/* renamed from: com.google.android.play.core.assetpacks.v */
/* loaded from: classes3.dex */
public final class C2477v implements InterfaceC2556cn<Executor> {
    /* renamed from: b */
    public static Executor m835b() {
        ExecutorService newSingleThreadExecutor = Executors.newSingleThreadExecutor(ThreadFactoryC2467m.f772a);
        C2532bq.m736b(newSingleThreadExecutor);
        return newSingleThreadExecutor;
    }

    @Override // com.google.android.play.core.internal.InterfaceC2556cn
    /* renamed from: a */
    public final /* bridge */ /* synthetic */ Executor mo473a() {
        return m835b();
    }
}
