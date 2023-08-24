package com.google.android.play.core.assetpacks;

import com.google.android.play.core.internal.C2532bq;
import com.google.android.play.core.internal.InterfaceC2556cn;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/* renamed from: com.google.android.play.core.assetpacks.p */
/* loaded from: classes3.dex */
public final class C2471p implements InterfaceC2556cn<Executor> {
    /* renamed from: b */
    public static Executor m839b() {
        ExecutorService newSingleThreadExecutor = Executors.newSingleThreadExecutor(ThreadFactoryC2466l.f771a);
        C2532bq.m736b(newSingleThreadExecutor);
        return newSingleThreadExecutor;
    }

    @Override // com.google.android.play.core.internal.InterfaceC2556cn
    /* renamed from: a */
    public final /* bridge */ /* synthetic */ Executor mo473a() {
        return m839b();
    }
}
