package com.google.android.play.core.assetpacks;

import android.content.Context;
import com.google.android.play.core.common.C2483a;
import com.google.android.play.core.internal.C2554cl;
import com.google.android.play.core.internal.InterfaceC2556cn;
import java.util.concurrent.Executor;

/* renamed from: com.google.android.play.core.assetpacks.ax */
/* loaded from: classes3.dex */
public final class C2377ax implements InterfaceC2556cn<C2376aw> {

    /* renamed from: a */
    private final InterfaceC2556cn<Context> f456a;

    /* renamed from: b */
    private final InterfaceC2556cn<C2423cp> f457b;

    /* renamed from: c */
    private final InterfaceC2556cn<C2403bw> f458c;

    /* renamed from: d */
    private final InterfaceC2556cn<InterfaceC2478w> f459d;

    /* renamed from: e */
    private final InterfaceC2556cn<C2406bz> f460e;

    /* renamed from: f */
    private final InterfaceC2556cn<C2394bn> f461f;

    /* renamed from: g */
    private final InterfaceC2556cn<C2483a> f462g;

    /* renamed from: h */
    private final InterfaceC2556cn<Executor> f463h;

    /* renamed from: i */
    private final InterfaceC2556cn<Executor> f464i;

    public C2377ax(InterfaceC2556cn<Context> interfaceC2556cn, InterfaceC2556cn<C2423cp> interfaceC2556cn2, InterfaceC2556cn<C2403bw> interfaceC2556cn3, InterfaceC2556cn<InterfaceC2478w> interfaceC2556cn4, InterfaceC2556cn<C2406bz> interfaceC2556cn5, InterfaceC2556cn<C2394bn> interfaceC2556cn6, InterfaceC2556cn<C2483a> interfaceC2556cn7, InterfaceC2556cn<Executor> interfaceC2556cn8, InterfaceC2556cn<Executor> interfaceC2556cn9) {
        this.f456a = interfaceC2556cn;
        this.f457b = interfaceC2556cn2;
        this.f458c = interfaceC2556cn3;
        this.f459d = interfaceC2556cn4;
        this.f460e = interfaceC2556cn5;
        this.f461f = interfaceC2556cn6;
        this.f462g = interfaceC2556cn7;
        this.f463h = interfaceC2556cn8;
        this.f464i = interfaceC2556cn9;
    }

    @Override // com.google.android.play.core.internal.InterfaceC2556cn
    /* renamed from: a */
    public final /* bridge */ /* synthetic */ C2376aw mo473a() {
        return new C2376aw(((C2474s) this.f456a).mo473a(), this.f457b.mo473a(), this.f458c.mo473a(), C2554cl.m710b(this.f459d), this.f460e.mo473a(), this.f461f.mo473a(), this.f462g.mo473a(), C2554cl.m710b(this.f463h), C2554cl.m710b(this.f464i));
    }
}
