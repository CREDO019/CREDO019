package com.google.android.play.core.assetpacks;

import com.google.android.play.core.common.C2483a;
import com.google.android.play.core.internal.C2494af;
import com.google.android.play.core.internal.C2532bq;
import com.google.android.play.core.internal.InterfaceC2552cj;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.zip.GZIPInputStream;

/* JADX INFO: Access modifiers changed from: package-private */
/* renamed from: com.google.android.play.core.assetpacks.do */
/* loaded from: classes3.dex */
public final class C2449do {

    /* renamed from: a */
    private static final C2494af f711a = new C2494af("PatchSliceTaskHandler");

    /* renamed from: b */
    private final C2382bb f712b;

    /* renamed from: c */
    private final InterfaceC2552cj<InterfaceC2478w> f713c;

    /* renamed from: d */
    private final C2483a f714d;

    /* JADX INFO: Access modifiers changed from: package-private */
    public C2449do(C2382bb c2382bb, InterfaceC2552cj<InterfaceC2478w> interfaceC2552cj, C2483a c2483a) {
        this.f712b = c2382bb;
        this.f713c = interfaceC2552cj;
        this.f714d = c2483a;
    }

    /* renamed from: a */
    public final void m887a(C2448dn c2448dn) {
        File m1004a = this.f712b.m1004a(c2448dn.f628k, c2448dn.f703a, c2448dn.f704b);
        File file = new File(this.f712b.m995b(c2448dn.f628k, c2448dn.f703a, c2448dn.f704b), c2448dn.f708f);
        try {
            InputStream inputStream = c2448dn.f710h;
            if (c2448dn.f707e == 2) {
                inputStream = new GZIPInputStream(inputStream, 8192);
            }
            C2385be c2385be = new C2385be(m1004a, file);
            if (this.f714d.m821a()) {
                File m1002a = this.f712b.m1002a(c2448dn.f628k, c2448dn.f705c, c2448dn.f706d, c2448dn.f708f);
                if (!m1002a.exists()) {
                    m1002a.mkdirs();
                }
                C2452dr c2452dr = new C2452dr(this.f712b, c2448dn.f628k, c2448dn.f705c, c2448dn.f706d, c2448dn.f708f);
                C2532bq.m749a(c2385be, inputStream, new C2405by(m1002a, c2452dr), c2448dn.f709g);
                c2452dr.m872b(0);
            } else {
                File file2 = new File(this.f712b.m977f(c2448dn.f628k, c2448dn.f705c, c2448dn.f706d, c2448dn.f708f), "slice.zip.tmp");
                if (file2.getParentFile() != null && !file2.getParentFile().exists()) {
                    file2.getParentFile().mkdirs();
                }
                C2532bq.m749a(c2385be, inputStream, new FileOutputStream(file2), c2448dn.f709g);
                if (!file2.renameTo(this.f712b.m981e(c2448dn.f628k, c2448dn.f705c, c2448dn.f706d, c2448dn.f708f))) {
                    throw new C2402bv(String.format("Error moving patch for slice %s of pack %s.", c2448dn.f708f, c2448dn.f628k), c2448dn.f627j);
                }
            }
            inputStream.close();
            if (this.f714d.m821a()) {
                f711a.m805c("Patching and extraction finished for slice %s of pack %s.", c2448dn.f708f, c2448dn.f628k);
            } else {
                f711a.m805c("Patching finished for slice %s of pack %s.", c2448dn.f708f, c2448dn.f628k);
            }
            this.f713c.m713a().mo831a(c2448dn.f627j, c2448dn.f628k, c2448dn.f708f, 0);
            try {
                c2448dn.f710h.close();
            } catch (IOException unused) {
                f711a.m804d("Could not close file for slice %s of pack %s.", c2448dn.f708f, c2448dn.f628k);
            }
        } catch (IOException e) {
            f711a.m806b("IOException during patching %s.", e.getMessage());
            throw new C2402bv(String.format("Error patching slice %s of pack %s.", c2448dn.f708f, c2448dn.f628k), e, c2448dn.f627j);
        }
    }
}
