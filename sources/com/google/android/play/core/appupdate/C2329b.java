package com.google.android.play.core.appupdate;

import android.content.Context;
import com.google.android.play.core.internal.InterfaceC2556cn;

/* renamed from: com.google.android.play.core.appupdate.b */
/* loaded from: classes3.dex */
public final class C2329b implements InterfaceC2556cn<C2328a> {

    /* renamed from: a */
    private final InterfaceC2556cn<Context> f295a;

    public C2329b(InterfaceC2556cn<Context> interfaceC2556cn) {
        this.f295a = interfaceC2556cn;
    }

    @Override // com.google.android.play.core.internal.InterfaceC2556cn
    /* renamed from: a */
    public final /* bridge */ /* synthetic */ C2328a mo473a() {
        return new C2328a(((C2336i) this.f295a).mo473a());
    }
}
