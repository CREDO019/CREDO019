package com.google.android.play.core.tasks;

import java.util.concurrent.Executor;

/* JADX INFO: Access modifiers changed from: package-private */
/* renamed from: com.google.android.play.core.tasks.f */
/* loaded from: classes3.dex */
public final class C2679f<ResultT> implements InterfaceC2680g<ResultT> {

    /* renamed from: a */
    private final Executor f1104a;

    /* renamed from: b */
    private final Object f1105b = new Object();

    /* renamed from: c */
    private final OnSuccessListener<? super ResultT> f1106c;

    public C2679f(Executor executor, OnSuccessListener<? super ResultT> onSuccessListener) {
        this.f1104a = executor;
        this.f1106c = onSuccessListener;
    }

    @Override // com.google.android.play.core.tasks.InterfaceC2680g
    /* renamed from: a */
    public final void mo461a(Task<ResultT> task) {
        if (task.isSuccessful()) {
            synchronized (this.f1105b) {
                if (this.f1106c == null) {
                    return;
                }
                this.f1104a.execute(new RunnableC2678e(this, task));
            }
        }
    }
}
