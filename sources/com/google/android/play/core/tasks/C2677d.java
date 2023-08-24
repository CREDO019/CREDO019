package com.google.android.play.core.tasks;

import java.util.concurrent.Executor;

/* JADX INFO: Access modifiers changed from: package-private */
/* renamed from: com.google.android.play.core.tasks.d */
/* loaded from: classes3.dex */
public final class C2677d<ResultT> implements InterfaceC2680g<ResultT> {

    /* renamed from: a */
    private final Executor f1099a;

    /* renamed from: b */
    private final Object f1100b = new Object();

    /* renamed from: c */
    private final OnFailureListener f1101c;

    public C2677d(Executor executor, OnFailureListener onFailureListener) {
        this.f1099a = executor;
        this.f1101c = onFailureListener;
    }

    @Override // com.google.android.play.core.tasks.InterfaceC2680g
    /* renamed from: a */
    public final void mo461a(Task<ResultT> task) {
        if (task.isSuccessful()) {
            return;
        }
        synchronized (this.f1100b) {
            if (this.f1101c == null) {
                return;
            }
            this.f1099a.execute(new RunnableC2676c(this, task));
        }
    }
}
