package com.google.android.play.core.assetpacks;

import com.google.android.play.core.internal.C2494af;
import com.google.android.play.core.internal.InterfaceC2552cj;
import java.util.concurrent.atomic.AtomicBoolean;

/* JADX INFO: Access modifiers changed from: package-private */
/* renamed from: com.google.android.play.core.assetpacks.bw */
/* loaded from: classes3.dex */
public final class C2403bw {

    /* renamed from: a */
    private static final C2494af f555a = new C2494af("ExtractorLooper");

    /* renamed from: b */
    private final C2423cp f556b;

    /* renamed from: c */
    private final C2400bt f557c;

    /* renamed from: d */
    private final C2456dv f558d;

    /* renamed from: e */
    private final C2440df f559e;

    /* renamed from: f */
    private final C2444dj f560f;

    /* renamed from: g */
    private final C2449do f561g;

    /* renamed from: h */
    private final InterfaceC2552cj<InterfaceC2478w> f562h;

    /* renamed from: i */
    private final C2426cs f563i;

    /* renamed from: j */
    private final AtomicBoolean f564j = new AtomicBoolean(false);

    /* JADX INFO: Access modifiers changed from: package-private */
    public C2403bw(C2423cp c2423cp, InterfaceC2552cj<InterfaceC2478w> interfaceC2552cj, C2400bt c2400bt, C2456dv c2456dv, C2440df c2440df, C2444dj c2444dj, C2449do c2449do, C2426cs c2426cs) {
        this.f556b = c2423cp;
        this.f562h = interfaceC2552cj;
        this.f557c = c2400bt;
        this.f558d = c2456dv;
        this.f559e = c2440df;
        this.f560f = c2444dj;
        this.f561g = c2449do;
        this.f563i = c2426cs;
    }

    /* renamed from: a */
    private final void m950a(int r3, Exception exc) {
        try {
            this.f556b.m927d(r3);
            this.f556b.m941a(r3);
        } catch (C2402bv unused) {
            f555a.m806b("Error during error handling: %s", exc.getMessage());
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: a */
    public final void m951a() {
        C2494af c2494af = f555a;
        c2494af.m808a("Run extractor loop", new Object[0]);
        if (!this.f564j.compareAndSet(false, true)) {
            c2494af.m804d("runLoop already looping; return", new Object[0]);
            return;
        }
        while (true) {
            C2425cr c2425cr = null;
            try {
                c2425cr = this.f563i.m921a();
            } catch (C2402bv e) {
                f555a.m806b("Error while getting next extraction task: %s", e.getMessage());
                if (e.f554a >= 0) {
                    this.f562h.m713a().mo833a(e.f554a);
                    m950a(e.f554a, e);
                }
            }
            if (c2425cr == null) {
                this.f564j.set(false);
                return;
            }
            try {
                if (c2425cr instanceof C2399bs) {
                    this.f557c.m953a((C2399bs) c2425cr);
                } else if (c2425cr instanceof C2455du) {
                    this.f558d.m864a((C2455du) c2425cr);
                } else if (c2425cr instanceof C2439de) {
                    this.f559e.m892a((C2439de) c2425cr);
                } else if (c2425cr instanceof C2442dh) {
                    this.f560f.m889a((C2442dh) c2425cr);
                } else if (c2425cr instanceof C2448dn) {
                    this.f561g.m887a((C2448dn) c2425cr);
                } else {
                    f555a.m806b("Unknown task type: %s", c2425cr.getClass().getName());
                }
            } catch (Exception e2) {
                f555a.m806b("Error during extraction task: %s", e2.getMessage());
                this.f562h.m713a().mo833a(c2425cr.f627j);
                m950a(c2425cr.f627j, e2);
            }
        }
    }
}
