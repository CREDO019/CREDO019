package com.google.android.play.core.appupdate;

import android.content.Context;
import com.google.android.play.core.internal.InterfaceC2556cn;

/* renamed from: com.google.android.play.core.appupdate.q */
/* loaded from: classes3.dex */
public final class C2344q implements InterfaceC2556cn<C2343p> {

    /* renamed from: a */
    private final InterfaceC2556cn<Context> f326a;

    /* renamed from: b */
    private final InterfaceC2556cn<C2345r> f327b;

    public C2344q(InterfaceC2556cn<Context> interfaceC2556cn, InterfaceC2556cn<C2345r> interfaceC2556cn2) {
        this.f326a = interfaceC2556cn;
        this.f327b = interfaceC2556cn2;
    }

    @Override // com.google.android.play.core.internal.InterfaceC2556cn
    /* renamed from: a */
    public final /* bridge */ /* synthetic */ C2343p mo473a() {
        return new C2343p(((C2336i) this.f326a).mo473a(), this.f327b.mo473a());
    }
}
