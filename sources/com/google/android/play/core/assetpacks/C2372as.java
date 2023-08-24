package com.google.android.play.core.assetpacks;

import android.content.Context;
import com.google.android.play.core.internal.InterfaceC2556cn;

/* renamed from: com.google.android.play.core.assetpacks.as */
/* loaded from: classes3.dex */
public final class C2372as implements InterfaceC2556cn<C2371ar> {

    /* renamed from: a */
    private final InterfaceC2556cn<Context> f438a;

    /* renamed from: b */
    private final InterfaceC2556cn<C2406bz> f439b;

    public C2372as(InterfaceC2556cn<Context> interfaceC2556cn, InterfaceC2556cn<C2406bz> interfaceC2556cn2) {
        this.f438a = interfaceC2556cn;
        this.f439b = interfaceC2556cn2;
    }

    @Override // com.google.android.play.core.internal.InterfaceC2556cn
    /* renamed from: a */
    public final /* bridge */ /* synthetic */ C2371ar mo473a() {
        return new C2371ar(((C2474s) this.f438a).mo473a(), this.f439b.mo473a());
    }
}
