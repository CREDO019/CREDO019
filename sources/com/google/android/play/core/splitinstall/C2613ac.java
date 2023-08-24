package com.google.android.play.core.splitinstall;

import com.google.android.play.core.internal.C2532bq;
import com.google.android.play.core.internal.InterfaceC2556cn;

/* renamed from: com.google.android.play.core.splitinstall.ac */
/* loaded from: classes3.dex */
public final class C2613ac implements InterfaceC2556cn<SplitInstallManager> {

    /* renamed from: a */
    private final C2672y f947a;

    /* renamed from: b */
    private final InterfaceC2556cn<C2644i> f948b;

    public C2613ac(C2672y c2672y, InterfaceC2556cn<C2644i> interfaceC2556cn) {
        this.f947a = c2672y;
        this.f948b = interfaceC2556cn;
    }

    @Override // com.google.android.play.core.internal.InterfaceC2556cn
    /* renamed from: a */
    public final /* bridge */ /* synthetic */ SplitInstallManager mo473a() {
        C2644i mo473a = this.f948b.mo473a();
        C2532bq.m736b(mo473a);
        return mo473a;
    }
}
