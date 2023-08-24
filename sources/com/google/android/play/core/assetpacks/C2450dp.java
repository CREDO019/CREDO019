package com.google.android.play.core.assetpacks;

import com.google.android.play.core.common.C2483a;
import com.google.android.play.core.internal.C2554cl;
import com.google.android.play.core.internal.InterfaceC2556cn;

/* renamed from: com.google.android.play.core.assetpacks.dp */
/* loaded from: classes3.dex */
public final class C2450dp implements InterfaceC2556cn<C2449do> {

    /* renamed from: a */
    private final InterfaceC2556cn<C2382bb> f715a;

    /* renamed from: b */
    private final InterfaceC2556cn<InterfaceC2478w> f716b;

    /* renamed from: c */
    private final InterfaceC2556cn<C2483a> f717c;

    public C2450dp(InterfaceC2556cn<C2382bb> interfaceC2556cn, InterfaceC2556cn<InterfaceC2478w> interfaceC2556cn2, InterfaceC2556cn<C2483a> interfaceC2556cn3) {
        this.f715a = interfaceC2556cn;
        this.f716b = interfaceC2556cn2;
        this.f717c = interfaceC2556cn3;
    }

    @Override // com.google.android.play.core.internal.InterfaceC2556cn
    /* renamed from: a */
    public final /* bridge */ /* synthetic */ C2449do mo473a() {
        return new C2449do(this.f715a.mo473a(), C2554cl.m710b(this.f716b), this.f717c.mo473a());
    }
}
