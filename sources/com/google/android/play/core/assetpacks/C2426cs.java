package com.google.android.play.core.assetpacks;

import com.google.android.play.core.internal.C2494af;

/* JADX INFO: Access modifiers changed from: package-private */
/* renamed from: com.google.android.play.core.assetpacks.cs */
/* loaded from: classes3.dex */
public final class C2426cs {

    /* renamed from: a */
    private static final C2494af f629a = new C2494af("ExtractorTaskFinder");

    /* renamed from: b */
    private final C2423cp f630b;

    /* renamed from: c */
    private final C2382bb f631c;

    /* renamed from: d */
    private final C2391bk f632d;

    /* JADX INFO: Access modifiers changed from: package-private */
    public C2426cs(C2423cp c2423cp, C2382bb c2382bb, C2391bk c2391bk) {
        this.f630b = c2423cp;
        this.f631c = c2382bb;
        this.f632d = c2391bk;
    }

    /* renamed from: a */
    private final boolean m920a(C2420cm c2420cm, C2421cn c2421cn) {
        C2382bb c2382bb = this.f631c;
        C2419cl c2419cl = c2420cm.f609c;
        return c2382bb.m981e(c2419cl.f602a, c2420cm.f608b, c2419cl.f603b, c2421cn.f610a).exists();
    }

    /* renamed from: a */
    private static boolean m919a(C2421cn c2421cn) {
        int r2 = c2421cn.f615f;
        return r2 == 1 || r2 == 2;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Code restructure failed: missing block: B:101:0x040f, code lost:
        if (r0 != null) goto L150;
     */
    /* JADX WARN: Code restructure failed: missing block: B:64:0x0218, code lost:
        r0 = com.google.android.play.core.assetpacks.C2426cs.f629a;
        r4 = new java.lang.Object[r8];
        r4[r7] = java.lang.Integer.valueOf(r6.f607a);
        r4[1] = r6.f609c.f602a;
        r4[2] = r10.f610a;
        r0.m808a("Found extraction task for patch for session %s, pack %s, slice %s.", r4);
     */
    /* JADX WARN: Code restructure failed: missing block: B:65:0x0235, code lost:
        r11 = r34.f631c;
        r4 = r6.f609c;
        r0 = new java.io.FileInputStream(r11.m981e(r4.f602a, r6.f608b, r4.f603b, r10.f610a));
     */
    /* JADX WARN: Code restructure failed: missing block: B:66:0x024c, code lost:
        r9 = r6.f607a;
        r11 = r6.f609c;
        r4 = new com.google.android.play.core.assetpacks.C2399bs(r9, r11.f602a, r6.f608b, r11.f603b, r10.f610a, 0, 0, 1, r11.f605d, r11.f604c, r0);
     */
    /* JADX WARN: Code restructure failed: missing block: B:67:0x027b, code lost:
        r0 = move-exception;
     */
    /* JADX WARN: Code restructure failed: missing block: B:69:0x02a1, code lost:
        throw new com.google.android.play.core.assetpacks.C2402bv(java.lang.String.format("Error finding patch, session %s packName %s sliceId %s", java.lang.Integer.valueOf(r6.f607a), r6.f609c.f602a, r10.f610a), r0, r6.f607a);
     */
    /* renamed from: a */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final com.google.android.play.core.assetpacks.C2425cr m921a() {
        /*
            Method dump skipped, instructions count: 1067
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.play.core.assetpacks.C2426cs.m921a():com.google.android.play.core.assetpacks.cr");
    }
}
