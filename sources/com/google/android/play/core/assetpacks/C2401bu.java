package com.google.android.play.core.assetpacks;

import com.google.android.play.core.internal.C2554cl;
import com.google.android.play.core.internal.InterfaceC2556cn;

/* renamed from: com.google.android.play.core.assetpacks.bu */
/* loaded from: classes3.dex */
public final class C2401bu implements InterfaceC2556cn<C2400bt> {

    /* renamed from: a */
    private final InterfaceC2556cn<C2382bb> f550a;

    /* renamed from: b */
    private final InterfaceC2556cn<InterfaceC2478w> f551b;

    /* renamed from: c */
    private final InterfaceC2556cn<C2376aw> f552c;

    /* renamed from: d */
    private final InterfaceC2556cn<C2406bz> f553d;

    public C2401bu(InterfaceC2556cn<C2382bb> interfaceC2556cn, InterfaceC2556cn<InterfaceC2478w> interfaceC2556cn2, InterfaceC2556cn<C2376aw> interfaceC2556cn3, InterfaceC2556cn<C2406bz> interfaceC2556cn4) {
        this.f550a = interfaceC2556cn;
        this.f551b = interfaceC2556cn2;
        this.f552c = interfaceC2556cn3;
        this.f553d = interfaceC2556cn4;
    }

    @Override // com.google.android.play.core.internal.InterfaceC2556cn
    /* renamed from: a */
    public final /* bridge */ /* synthetic */ C2400bt mo473a() {
        return new C2400bt(this.f550a.mo473a(), C2554cl.m710b(this.f551b), C2554cl.m710b(this.f552c), this.f553d.mo473a());
    }
}
