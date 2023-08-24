package com.google.android.play.core.appupdate;

import android.content.Context;
import com.google.android.play.core.internal.C2554cl;
import com.google.android.play.core.internal.InterfaceC2556cn;

/* renamed from: com.google.android.play.core.appupdate.y */
/* loaded from: classes3.dex */
public final class C2352y {

    /* renamed from: a */
    private InterfaceC2556cn<Context> f364a;

    /* renamed from: b */
    private InterfaceC2556cn<C2345r> f365b;

    /* renamed from: c */
    private InterfaceC2556cn<C2343p> f366c;

    /* renamed from: d */
    private InterfaceC2556cn<C2328a> f367d;

    /* renamed from: e */
    private InterfaceC2556cn<C2332e> f368e;

    /* renamed from: f */
    private InterfaceC2556cn<AppUpdateManager> f369f;

    /* JADX INFO: Access modifiers changed from: package-private */
    public /* synthetic */ C2352y(C2334g c2334g) {
        C2336i c2336i = new C2336i(c2334g);
        this.f364a = c2336i;
        InterfaceC2556cn<C2345r> m711a = C2554cl.m711a(new C2346s(c2336i));
        this.f365b = m711a;
        this.f366c = C2554cl.m711a(new C2344q(this.f364a, m711a));
        InterfaceC2556cn<C2328a> m711a2 = C2554cl.m711a(new C2329b(this.f364a));
        this.f367d = m711a2;
        InterfaceC2556cn<C2332e> m711a3 = C2554cl.m711a(new C2333f(this.f366c, m711a2, this.f364a));
        this.f368e = m711a3;
        this.f369f = C2554cl.m711a(new C2335h(m711a3));
    }

    /* renamed from: a */
    public final AppUpdateManager m1045a() {
        return this.f369f.mo473a();
    }
}
