package com.google.android.play.core.assetpacks;

import com.google.android.play.core.internal.C2494af;
import java.io.File;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;

/* JADX INFO: Access modifiers changed from: package-private */
/* renamed from: com.google.android.play.core.assetpacks.dv */
/* loaded from: classes3.dex */
public final class C2456dv {

    /* renamed from: a */
    private static final C2494af f737a = new C2494af("VerifySliceTaskHandler");

    /* renamed from: b */
    private final C2382bb f738b;

    /* JADX INFO: Access modifiers changed from: package-private */
    public C2456dv(C2382bb c2382bb) {
        this.f738b = c2382bb;
    }

    /* renamed from: a */
    private final void m863a(C2455du c2455du, File file) {
        try {
            File m977f = this.f738b.m977f(c2455du.f628k, c2455du.f733a, c2455du.f734b, c2455du.f735c);
            if (!m977f.exists()) {
                throw new C2402bv(String.format("Cannot find metadata files for slice %s.", c2455du.f735c), c2455du.f627j);
            }
            try {
                if (!C2436db.m905a(C2454dt.m866a(file, m977f)).equals(c2455du.f736d)) {
                    throw new C2402bv(String.format("Verification failed for slice %s.", c2455du.f735c), c2455du.f627j);
                }
                f737a.m805c("Verification of slice %s of pack %s successful.", c2455du.f735c, c2455du.f628k);
            } catch (IOException e) {
                throw new C2402bv(String.format("Could not digest file during verification for slice %s.", c2455du.f735c), e, c2455du.f627j);
            } catch (NoSuchAlgorithmException e2) {
                throw new C2402bv("SHA256 algorithm not supported.", e2, c2455du.f627j);
            }
        } catch (IOException e3) {
            throw new C2402bv(String.format("Could not reconstruct slice archive during verification for slice %s.", c2455du.f735c), e3, c2455du.f627j);
        }
    }

    /* renamed from: a */
    public final void m864a(C2455du c2455du) {
        File m1002a = this.f738b.m1002a(c2455du.f628k, c2455du.f733a, c2455du.f734b, c2455du.f735c);
        if (!m1002a.exists()) {
            throw new C2402bv(String.format("Cannot find unverified files for slice %s.", c2455du.f735c), c2455du.f627j);
        }
        m863a(c2455du, m1002a);
        File m994b = this.f738b.m994b(c2455du.f628k, c2455du.f733a, c2455du.f734b, c2455du.f735c);
        if (!m994b.exists()) {
            m994b.mkdirs();
        }
        if (!m1002a.renameTo(m994b)) {
            throw new C2402bv(String.format("Failed to move slice %s after verification.", c2455du.f735c), c2455du.f627j);
        }
    }
}
