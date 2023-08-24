package com.google.android.play.core.assetpacks;

import android.content.Context;
import com.google.android.play.core.internal.C2532bq;
import com.google.android.play.core.internal.C2554cl;
import com.google.android.play.core.internal.InterfaceC2556cn;

/* renamed from: com.google.android.play.core.assetpacks.r */
/* loaded from: classes3.dex */
public final class C2473r implements InterfaceC2556cn<InterfaceC2478w> {

    /* renamed from: a */
    private final InterfaceC2556cn<Context> f779a;

    /* renamed from: b */
    private final InterfaceC2556cn<C2371ar> f780b;

    /* renamed from: c */
    private final InterfaceC2556cn<C2433cz> f781c;

    public C2473r(InterfaceC2556cn<Context> interfaceC2556cn, InterfaceC2556cn<C2371ar> interfaceC2556cn2, InterfaceC2556cn<C2433cz> interfaceC2556cn3) {
        this.f779a = interfaceC2556cn;
        this.f780b = interfaceC2556cn2;
        this.f781c = interfaceC2556cn3;
    }

    @Override // com.google.android.play.core.internal.InterfaceC2556cn
    /* renamed from: a */
    public final /* bridge */ /* synthetic */ InterfaceC2478w mo473a() {
        InterfaceC2478w interfaceC2478w = (InterfaceC2478w) (C2469n.m841a(((C2474s) this.f779a).mo473a()) == null ? C2554cl.m710b(this.f780b).m713a() : C2554cl.m710b(this.f781c).m713a());
        C2532bq.m736b(interfaceC2478w);
        return interfaceC2478w;
    }
}
