package com.google.android.play.core.splitinstall;

import android.content.Context;
import com.google.android.play.core.internal.C2554cl;
import com.google.android.play.core.internal.InterfaceC2556cn;
import com.google.android.play.core.splitinstall.testing.C2667k;
import com.google.android.play.core.splitinstall.testing.FakeSplitInstallManager;
import java.io.File;

/* renamed from: com.google.android.play.core.splitinstall.c */
/* loaded from: classes3.dex */
public final class C2638c implements InterfaceC2648m {

    /* renamed from: a */
    private InterfaceC2556cn<Context> f986a;

    /* renamed from: b */
    private InterfaceC2556cn<C2632av> f987b;

    /* renamed from: c */
    private InterfaceC2556cn<C2656t> f988c;

    /* renamed from: d */
    private InterfaceC2556cn<C2652p> f989d;

    /* renamed from: e */
    private InterfaceC2556cn<C2634ax> f990e;

    /* renamed from: f */
    private InterfaceC2556cn<C2670w> f991f;

    /* renamed from: g */
    private InterfaceC2556cn<File> f992g;

    /* renamed from: h */
    private InterfaceC2556cn<FakeSplitInstallManager> f993h;

    /* renamed from: i */
    private InterfaceC2556cn<C2644i> f994i;

    /* renamed from: j */
    private InterfaceC2556cn<SplitInstallManager> f995j;

    /* JADX INFO: Access modifiers changed from: package-private */
    public /* synthetic */ C2638c(C2672y c2672y) {
        C2673z c2673z = new C2673z(c2672y);
        this.f986a = c2673z;
        this.f987b = C2554cl.m711a(new C2633aw(c2673z));
        this.f988c = C2554cl.m711a(new C2612ab(c2672y));
        this.f989d = C2554cl.m711a(C2653q.m509a(this.f986a));
        InterfaceC2556cn<C2634ax> m711a = C2554cl.m711a(new C2635ay(this.f986a));
        this.f990e = m711a;
        this.f991f = C2554cl.m711a(new C2671x(this.f987b, this.f988c, this.f989d, m711a));
        InterfaceC2556cn<File> m711a2 = C2554cl.m711a(new C2611aa(this.f986a));
        this.f992g = m711a2;
        InterfaceC2556cn<FakeSplitInstallManager> m711a3 = C2554cl.m711a(new C2667k(this.f986a, m711a2, this.f989d));
        this.f993h = m711a3;
        InterfaceC2556cn<C2644i> m711a4 = C2554cl.m711a(new C2645j(this.f991f, m711a3, this.f992g));
        this.f994i = m711a4;
        this.f995j = C2554cl.m711a(new C2613ac(c2672y, m711a4));
    }

    @Override // com.google.android.play.core.splitinstall.InterfaceC2648m
    /* renamed from: a */
    public final SplitInstallManager mo522a() {
        return this.f995j.mo473a();
    }

    @Override // com.google.android.play.core.splitinstall.InterfaceC2648m
    /* renamed from: b */
    public final File mo521b() {
        return this.f992g.mo473a();
    }
}
