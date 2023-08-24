package com.google.android.play.core.tasks;

import java.util.concurrent.Executor;

/* renamed from: com.google.android.play.core.tasks.b */
/* loaded from: classes3.dex */
final class C2675b<ResultT> implements InterfaceC2680g<ResultT> {

    /* renamed from: a */
    private final Executor f1094a;

    /* renamed from: b */
    private final Object f1095b = new Object();

    /* renamed from: c */
    private final OnCompleteListener<ResultT> f1096c;

    public C2675b(Executor executor, OnCompleteListener<ResultT> onCompleteListener) {
        this.f1094a = executor;
        this.f1096c = onCompleteListener;
    }

    @Override // com.google.android.play.core.tasks.InterfaceC2680g
    /* renamed from: a */
    public final void mo461a(Task<ResultT> task) {
        synchronized (this.f1095b) {
            if (this.f1096c == null) {
                return;
            }
            this.f1094a.execute(new RunnableC2674a(this, task));
        }
    }
}
