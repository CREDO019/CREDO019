package com.google.android.play.core.internal;

import com.google.android.play.core.tasks.C2682i;

/* renamed from: com.google.android.play.core.internal.ag */
/* loaded from: classes3.dex */
public abstract class AbstractRunnableC2495ag implements Runnable {

    /* renamed from: a */
    private final C2682i<?> f806a;

    /* JADX INFO: Access modifiers changed from: package-private */
    public AbstractRunnableC2495ag() {
        this.f806a = null;
    }

    public AbstractRunnableC2495ag(C2682i<?> c2682i) {
        this.f806a = c2682i;
    }

    /* renamed from: a */
    protected abstract void mo565a();

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: b */
    public final C2682i<?> m803b() {
        return this.f806a;
    }

    @Override // java.lang.Runnable
    public final void run() {
        try {
            mo565a();
        } catch (Exception e) {
            C2682i<?> c2682i = this.f806a;
            if (c2682i != null) {
                c2682i.m455b(e);
            }
        }
    }
}
