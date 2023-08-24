package com.google.android.play.core.assetpacks;

import android.content.Context;
import com.google.android.play.core.common.C2483a;
import com.google.android.play.core.common.C2485c;
import com.google.android.play.core.internal.C2553ck;
import com.google.android.play.core.internal.C2554cl;
import com.google.android.play.core.internal.InterfaceC2556cn;
import com.google.android.play.core.splitinstall.C2652p;
import com.google.android.play.core.splitinstall.C2653q;
import java.util.concurrent.Executor;

/* renamed from: com.google.android.play.core.assetpacks.br */
/* loaded from: classes3.dex */
public final class C2398br implements InterfaceC2353a {

    /* renamed from: a */
    private final C2469n f509a;

    /* renamed from: b */
    private InterfaceC2556cn<Context> f510b;

    /* renamed from: c */
    private InterfaceC2556cn<C2446dl> f511c;

    /* renamed from: d */
    private InterfaceC2556cn<C2382bb> f512d;

    /* renamed from: e */
    private InterfaceC2556cn<C2406bz> f513e;

    /* renamed from: f */
    private InterfaceC2556cn<C2371ar> f514f;

    /* renamed from: g */
    private InterfaceC2556cn<String> f515g;

    /* renamed from: h */
    private InterfaceC2556cn<InterfaceC2478w> f516h;

    /* renamed from: i */
    private InterfaceC2556cn<Executor> f517i;

    /* renamed from: j */
    private InterfaceC2556cn<C2423cp> f518j;

    /* renamed from: k */
    private InterfaceC2556cn<C2376aw> f519k;

    /* renamed from: l */
    private InterfaceC2556cn<C2400bt> f520l;

    /* renamed from: m */
    private InterfaceC2556cn<C2456dv> f521m;

    /* renamed from: n */
    private InterfaceC2556cn<C2440df> f522n;

    /* renamed from: o */
    private InterfaceC2556cn<C2444dj> f523o;

    /* renamed from: p */
    private InterfaceC2556cn<C2483a> f524p;

    /* renamed from: q */
    private InterfaceC2556cn<C2449do> f525q;

    /* renamed from: r */
    private InterfaceC2556cn<C2391bk> f526r;

    /* renamed from: s */
    private InterfaceC2556cn<C2426cs> f527s;

    /* renamed from: t */
    private InterfaceC2556cn<C2403bw> f528t;

    /* renamed from: u */
    private InterfaceC2556cn<C2394bn> f529u;

    /* renamed from: v */
    private InterfaceC2556cn<Executor> f530v;

    /* renamed from: w */
    private InterfaceC2556cn<C2433cz> f531w;

    /* renamed from: x */
    private InterfaceC2556cn<C2652p> f532x;

    /* renamed from: y */
    private InterfaceC2556cn<C2464j> f533y;

    /* renamed from: z */
    private InterfaceC2556cn<AssetPackManager> f534z;

    /* JADX INFO: Access modifiers changed from: package-private */
    public /* synthetic */ C2398br(C2469n c2469n) {
        C2409cb c2409cb;
        C2471p c2471p;
        C2396bp c2396bp;
        C2477v c2477v;
        this.f509a = c2469n;
        C2474s c2474s = new C2474s(c2469n);
        this.f510b = c2474s;
        InterfaceC2556cn<C2446dl> m711a = C2554cl.m711a(new C2447dm(c2474s));
        this.f511c = m711a;
        this.f512d = C2554cl.m711a(new C2383bc(this.f510b, m711a));
        c2409cb = C2408ca.f582a;
        InterfaceC2556cn<C2406bz> m711a2 = C2554cl.m711a(c2409cb);
        this.f513e = m711a2;
        this.f514f = C2554cl.m711a(new C2372as(this.f510b, m711a2));
        this.f515g = C2554cl.m711a(new C2475t(this.f510b));
        this.f516h = new C2553ck();
        c2471p = C2470o.f776a;
        InterfaceC2556cn<Executor> m711a3 = C2554cl.m711a(c2471p);
        this.f517i = m711a3;
        this.f518j = C2554cl.m711a(new C2424cq(this.f512d, this.f516h, this.f513e, m711a3));
        C2553ck c2553ck = new C2553ck();
        this.f519k = c2553ck;
        this.f520l = C2554cl.m711a(new C2401bu(this.f512d, this.f516h, c2553ck, this.f513e));
        this.f521m = C2554cl.m711a(new C2457dw(this.f512d));
        this.f522n = C2554cl.m711a(new C2441dg(this.f512d));
        this.f523o = C2554cl.m711a(new C2445dk(this.f512d, this.f516h, this.f518j, this.f517i, this.f513e));
        InterfaceC2556cn<C2483a> m711a4 = C2554cl.m711a(C2485c.m818b());
        this.f524p = m711a4;
        this.f525q = C2554cl.m711a(new C2450dp(this.f512d, this.f516h, m711a4));
        InterfaceC2556cn<C2391bk> m711a5 = C2554cl.m711a(new C2392bl(this.f516h));
        this.f526r = m711a5;
        InterfaceC2556cn<C2426cs> m711a6 = C2554cl.m711a(new C2427ct(this.f518j, this.f512d, m711a5));
        this.f527s = m711a6;
        this.f528t = C2554cl.m711a(new C2404bx(this.f518j, this.f516h, this.f520l, this.f521m, this.f522n, this.f523o, this.f525q, m711a6));
        c2396bp = C2395bo.f507a;
        this.f529u = C2554cl.m711a(c2396bp);
        c2477v = C2476u.f784a;
        InterfaceC2556cn<Executor> m711a7 = C2554cl.m711a(c2477v);
        this.f530v = m711a7;
        C2553ck.m712a(this.f519k, C2554cl.m711a(new C2377ax(this.f510b, this.f518j, this.f528t, this.f516h, this.f513e, this.f529u, this.f524p, this.f517i, m711a7)));
        InterfaceC2556cn<C2433cz> m711a8 = C2554cl.m711a(new C2435da(this.f515g, this.f519k, this.f513e, this.f510b, this.f511c, this.f517i));
        this.f531w = m711a8;
        C2553ck.m712a(this.f516h, C2554cl.m711a(new C2473r(this.f510b, this.f514f, m711a8)));
        InterfaceC2556cn<C2652p> m711a9 = C2554cl.m711a(C2653q.m509a(this.f510b));
        this.f532x = m711a9;
        InterfaceC2556cn<C2464j> m711a10 = C2554cl.m711a(new C2465k(this.f512d, this.f516h, this.f519k, m711a9, this.f518j, this.f513e, this.f529u, this.f517i));
        this.f533y = m711a10;
        this.f534z = C2554cl.m711a(new C2472q(m711a10, this.f510b));
    }

    @Override // com.google.android.play.core.assetpacks.InterfaceC2353a
    /* renamed from: a */
    public final AssetPackManager mo956a() {
        return this.f534z.mo473a();
    }

    @Override // com.google.android.play.core.assetpacks.InterfaceC2353a
    /* renamed from: a */
    public final void mo955a(AssetPackExtractionService assetPackExtractionService) {
        assetPackExtractionService.f371a = C2474s.m838a(this.f509a);
        assetPackExtractionService.f372b = this.f533y.mo473a();
        assetPackExtractionService.f373c = this.f512d.mo473a();
    }
}
