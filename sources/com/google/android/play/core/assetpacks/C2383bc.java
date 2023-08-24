package com.google.android.play.core.assetpacks;

import android.content.Context;
import com.google.android.play.core.internal.InterfaceC2556cn;

/* renamed from: com.google.android.play.core.assetpacks.bc */
/* loaded from: classes3.dex */
public final class C2383bc implements InterfaceC2556cn<C2382bb> {

    /* renamed from: a */
    private final InterfaceC2556cn<Context> f476a;

    /* renamed from: b */
    private final InterfaceC2556cn<C2446dl> f477b;

    public C2383bc(InterfaceC2556cn<Context> interfaceC2556cn, InterfaceC2556cn<C2446dl> interfaceC2556cn2) {
        this.f476a = interfaceC2556cn;
        this.f477b = interfaceC2556cn2;
    }

    @Override // com.google.android.play.core.internal.InterfaceC2556cn
    /* renamed from: a */
    public final /* bridge */ /* synthetic */ C2382bb mo473a() {
        return new C2382bb(((C2474s) this.f476a).mo473a(), this.f477b.mo473a());
    }
}
