package com.google.android.play.core.internal;

import com.google.android.play.core.tasks.C2682i;

/* JADX INFO: Access modifiers changed from: package-private */
/* renamed from: com.google.android.play.core.internal.ai */
/* loaded from: classes3.dex */
public final class C2497ai extends AbstractRunnableC2495ag {

    /* renamed from: a */
    final /* synthetic */ AbstractRunnableC2495ag f808a;

    /* renamed from: b */
    final /* synthetic */ C2504ap f809b;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public C2497ai(C2504ap c2504ap, C2682i c2682i, AbstractRunnableC2495ag abstractRunnableC2495ag) {
        super(c2682i);
        this.f809b = c2504ap;
        this.f808a = abstractRunnableC2495ag;
    }

    @Override // com.google.android.play.core.internal.AbstractRunnableC2495ag
    /* renamed from: a */
    public final void mo565a() {
        C2504ap.m797a(this.f809b, this.f808a);
    }
}
