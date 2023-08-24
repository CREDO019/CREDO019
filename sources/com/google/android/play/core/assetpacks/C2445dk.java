package com.google.android.play.core.assetpacks;

import com.google.android.play.core.internal.C2554cl;
import com.google.android.play.core.internal.InterfaceC2556cn;
import java.util.concurrent.Executor;

/* renamed from: com.google.android.play.core.assetpacks.dk */
/* loaded from: classes3.dex */
public final class C2445dk implements InterfaceC2556cn<C2444dj> {

    /* renamed from: a */
    private final InterfaceC2556cn<C2382bb> f694a;

    /* renamed from: b */
    private final InterfaceC2556cn<InterfaceC2478w> f695b;

    /* renamed from: c */
    private final InterfaceC2556cn<C2423cp> f696c;

    /* renamed from: d */
    private final InterfaceC2556cn<Executor> f697d;

    /* renamed from: e */
    private final InterfaceC2556cn<C2406bz> f698e;

    public C2445dk(InterfaceC2556cn<C2382bb> interfaceC2556cn, InterfaceC2556cn<InterfaceC2478w> interfaceC2556cn2, InterfaceC2556cn<C2423cp> interfaceC2556cn3, InterfaceC2556cn<Executor> interfaceC2556cn4, InterfaceC2556cn<C2406bz> interfaceC2556cn5) {
        this.f694a = interfaceC2556cn;
        this.f695b = interfaceC2556cn2;
        this.f696c = interfaceC2556cn3;
        this.f697d = interfaceC2556cn4;
        this.f698e = interfaceC2556cn5;
    }

    @Override // com.google.android.play.core.internal.InterfaceC2556cn
    /* renamed from: a */
    public final /* bridge */ /* synthetic */ C2444dj mo473a() {
        C2382bb mo473a = this.f694a.mo473a();
        return new C2444dj(mo473a, C2554cl.m710b(this.f695b), this.f696c.mo473a(), C2554cl.m710b(this.f697d), this.f698e.mo473a());
    }
}
