package com.google.android.play.core.internal;

/* renamed from: com.google.android.play.core.internal.ck */
/* loaded from: classes3.dex */
public final class C2553ck<T> implements InterfaceC2556cn<T> {

    /* renamed from: a */
    private InterfaceC2556cn<T> f854a;

    /* renamed from: a */
    public static <T> void m712a(InterfaceC2556cn<T> interfaceC2556cn, InterfaceC2556cn<T> interfaceC2556cn2) {
        C2532bq.m745a(interfaceC2556cn2);
        C2553ck c2553ck = (C2553ck) interfaceC2556cn;
        if (c2553ck.f854a != null) {
            throw new IllegalStateException();
        }
        c2553ck.f854a = interfaceC2556cn2;
    }

    @Override // com.google.android.play.core.internal.InterfaceC2556cn
    /* renamed from: a */
    public final T mo473a() {
        InterfaceC2556cn<T> interfaceC2556cn = this.f854a;
        if (interfaceC2556cn != null) {
            return interfaceC2556cn.mo473a();
        }
        throw new IllegalStateException();
    }
}
