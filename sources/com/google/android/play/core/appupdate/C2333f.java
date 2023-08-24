package com.google.android.play.core.appupdate;

import android.content.Context;
import com.google.android.play.core.internal.InterfaceC2556cn;

/* renamed from: com.google.android.play.core.appupdate.f */
/* loaded from: classes3.dex */
public final class C2333f implements InterfaceC2556cn<C2332e> {

    /* renamed from: a */
    private final InterfaceC2556cn<C2343p> f302a;

    /* renamed from: b */
    private final InterfaceC2556cn<C2328a> f303b;

    /* renamed from: c */
    private final InterfaceC2556cn<Context> f304c;

    public C2333f(InterfaceC2556cn<C2343p> interfaceC2556cn, InterfaceC2556cn<C2328a> interfaceC2556cn2, InterfaceC2556cn<Context> interfaceC2556cn3) {
        this.f302a = interfaceC2556cn;
        this.f303b = interfaceC2556cn2;
        this.f304c = interfaceC2556cn3;
    }

    @Override // com.google.android.play.core.internal.InterfaceC2556cn
    /* renamed from: a */
    public final /* bridge */ /* synthetic */ C2332e mo473a() {
        return new C2332e(this.f302a.mo473a(), this.f303b.mo473a(), ((C2336i) this.f304c).mo473a());
    }
}
