package com.google.android.play.core.assetpacks;

import com.google.android.play.core.internal.InterfaceC2552cj;
import java.io.File;
import java.util.concurrent.Executor;

/* JADX INFO: Access modifiers changed from: package-private */
/* renamed from: com.google.android.play.core.assetpacks.dj */
/* loaded from: classes3.dex */
public final class C2444dj {

    /* renamed from: a */
    private final C2382bb f689a;

    /* renamed from: b */
    private final InterfaceC2552cj<InterfaceC2478w> f690b;

    /* renamed from: c */
    private final C2423cp f691c;

    /* renamed from: d */
    private final InterfaceC2552cj<Executor> f692d;

    /* renamed from: e */
    private final C2406bz f693e;

    /* JADX INFO: Access modifiers changed from: package-private */
    public C2444dj(C2382bb c2382bb, InterfaceC2552cj<InterfaceC2478w> interfaceC2552cj, C2423cp c2423cp, InterfaceC2552cj<Executor> interfaceC2552cj2, C2406bz c2406bz) {
        this.f689a = c2382bb;
        this.f690b = interfaceC2552cj;
        this.f691c = c2423cp;
        this.f692d = interfaceC2552cj2;
        this.f693e = c2406bz;
    }

    /* renamed from: a */
    public final void m889a(C2442dh c2442dh) {
        File m990c = this.f689a.m990c(c2442dh.f628k, c2442dh.f686a, c2442dh.f687b);
        File m982e = this.f689a.m982e(c2442dh.f628k, c2442dh.f686a, c2442dh.f687b);
        if (!m990c.exists() || !m982e.exists()) {
            throw new C2402bv(String.format("Cannot find pack files to move for pack %s.", c2442dh.f628k), c2442dh.f627j);
        }
        File m1004a = this.f689a.m1004a(c2442dh.f628k, c2442dh.f686a, c2442dh.f687b);
        m1004a.mkdirs();
        if (!m990c.renameTo(m1004a)) {
            throw new C2402bv("Cannot move merged pack files to final location.", c2442dh.f627j);
        }
        new File(this.f689a.m1004a(c2442dh.f628k, c2442dh.f686a, c2442dh.f687b), "merge.tmp").delete();
        File m995b = this.f689a.m995b(c2442dh.f628k, c2442dh.f686a, c2442dh.f687b);
        m995b.mkdirs();
        if (!m982e.renameTo(m995b)) {
            throw new C2402bv("Cannot move metadata files to final location.", c2442dh.f627j);
        }
        C2382bb c2382bb = this.f689a;
        c2382bb.getClass();
        this.f692d.m713a().execute(RunnableC2443di.m890a(c2382bb));
        this.f691c.m938a(c2442dh.f628k, c2442dh.f686a, c2442dh.f687b);
        this.f693e.m949a(c2442dh.f628k);
        this.f690b.m713a().mo832a(c2442dh.f627j, c2442dh.f628k);
    }
}
