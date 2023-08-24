package com.google.android.play.core.assetpacks;

import com.google.android.play.core.internal.C2554cl;
import com.google.android.play.core.internal.InterfaceC2556cn;
import com.google.android.play.core.splitinstall.C2652p;
import java.util.concurrent.Executor;

/* renamed from: com.google.android.play.core.assetpacks.k */
/* loaded from: classes3.dex */
public final class C2465k implements InterfaceC2556cn<C2464j> {

    /* renamed from: a */
    private final InterfaceC2556cn<C2382bb> f763a;

    /* renamed from: b */
    private final InterfaceC2556cn<InterfaceC2478w> f764b;

    /* renamed from: c */
    private final InterfaceC2556cn<C2376aw> f765c;

    /* renamed from: d */
    private final InterfaceC2556cn<C2652p> f766d;

    /* renamed from: e */
    private final InterfaceC2556cn<C2423cp> f767e;

    /* renamed from: f */
    private final InterfaceC2556cn<C2406bz> f768f;

    /* renamed from: g */
    private final InterfaceC2556cn<C2394bn> f769g;

    /* renamed from: h */
    private final InterfaceC2556cn<Executor> f770h;

    public C2465k(InterfaceC2556cn<C2382bb> interfaceC2556cn, InterfaceC2556cn<InterfaceC2478w> interfaceC2556cn2, InterfaceC2556cn<C2376aw> interfaceC2556cn3, InterfaceC2556cn<C2652p> interfaceC2556cn4, InterfaceC2556cn<C2423cp> interfaceC2556cn5, InterfaceC2556cn<C2406bz> interfaceC2556cn6, InterfaceC2556cn<C2394bn> interfaceC2556cn7, InterfaceC2556cn<Executor> interfaceC2556cn8) {
        this.f763a = interfaceC2556cn;
        this.f764b = interfaceC2556cn2;
        this.f765c = interfaceC2556cn3;
        this.f766d = interfaceC2556cn4;
        this.f767e = interfaceC2556cn5;
        this.f768f = interfaceC2556cn6;
        this.f769g = interfaceC2556cn7;
        this.f770h = interfaceC2556cn8;
    }

    @Override // com.google.android.play.core.internal.InterfaceC2556cn
    /* renamed from: a */
    public final /* bridge */ /* synthetic */ C2464j mo473a() {
        return new C2464j(this.f763a.mo473a(), C2554cl.m710b(this.f764b), this.f765c.mo473a(), this.f766d.mo473a(), this.f767e.mo473a(), this.f768f.mo473a(), this.f769g.mo473a(), C2554cl.m710b(this.f770h));
    }
}
