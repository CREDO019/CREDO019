package com.google.android.play.core.splitinstall;

import android.content.Context;
import com.google.android.play.core.internal.InterfaceC2556cn;

/* renamed from: com.google.android.play.core.splitinstall.q */
/* loaded from: classes3.dex */
public final class C2653q implements InterfaceC2556cn<C2652p> {

    /* renamed from: a */
    private final InterfaceC2556cn<Context> f1013a;

    public C2653q(InterfaceC2556cn<Context> interfaceC2556cn) {
        this.f1013a = interfaceC2556cn;
    }

    /* renamed from: a */
    public static C2653q m509a(InterfaceC2556cn<Context> interfaceC2556cn) {
        return new C2653q(interfaceC2556cn);
    }

    @Override // com.google.android.play.core.internal.InterfaceC2556cn
    /* renamed from: a */
    public final /* bridge */ /* synthetic */ C2652p mo473a() {
        return new C2652p(this.f1013a.mo473a());
    }
}
