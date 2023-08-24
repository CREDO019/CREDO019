package com.google.android.play.core.assetpacks;

import com.google.android.play.core.internal.C2554cl;
import com.google.android.play.core.internal.InterfaceC2556cn;
import java.util.concurrent.Executor;

/* renamed from: com.google.android.play.core.assetpacks.cq */
/* loaded from: classes3.dex */
public final class C2424cq implements InterfaceC2556cn<C2423cp> {

    /* renamed from: a */
    private final InterfaceC2556cn<C2382bb> f623a;

    /* renamed from: b */
    private final InterfaceC2556cn<InterfaceC2478w> f624b;

    /* renamed from: c */
    private final InterfaceC2556cn<C2406bz> f625c;

    /* renamed from: d */
    private final InterfaceC2556cn<Executor> f626d;

    public C2424cq(InterfaceC2556cn<C2382bb> interfaceC2556cn, InterfaceC2556cn<InterfaceC2478w> interfaceC2556cn2, InterfaceC2556cn<C2406bz> interfaceC2556cn3, InterfaceC2556cn<Executor> interfaceC2556cn4) {
        this.f623a = interfaceC2556cn;
        this.f624b = interfaceC2556cn2;
        this.f625c = interfaceC2556cn3;
        this.f626d = interfaceC2556cn4;
    }

    @Override // com.google.android.play.core.internal.InterfaceC2556cn
    /* renamed from: a */
    public final /* bridge */ /* synthetic */ C2423cp mo473a() {
        C2382bb mo473a = this.f623a.mo473a();
        return new C2423cp(mo473a, C2554cl.m710b(this.f624b), this.f625c.mo473a(), C2554cl.m710b(this.f626d));
    }
}
