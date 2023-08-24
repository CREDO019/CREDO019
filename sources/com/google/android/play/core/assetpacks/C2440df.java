package com.google.android.play.core.assetpacks;

import com.google.android.play.core.internal.C2494af;
import java.io.File;
import java.io.IOException;

/* JADX INFO: Access modifiers changed from: package-private */
/* renamed from: com.google.android.play.core.assetpacks.df */
/* loaded from: classes3.dex */
public final class C2440df {

    /* renamed from: a */
    private static final C2494af f683a = new C2494af("MergeSliceTaskHandler");

    /* renamed from: b */
    private final C2382bb f684b;

    /* JADX INFO: Access modifiers changed from: package-private */
    public C2440df(C2382bb c2382bb) {
        this.f684b = c2382bb;
    }

    /* renamed from: a */
    private static void m891a(File file, File file2) {
        File[] listFiles;
        if (!file.isDirectory()) {
            if (file2.exists()) {
                String valueOf = String.valueOf(file2);
                StringBuilder sb = new StringBuilder(String.valueOf(valueOf).length() + 51);
                sb.append("File clashing with existing file from other slice: ");
                sb.append(valueOf);
                throw new C2402bv(sb.toString());
            } else if (file.renameTo(file2)) {
                return;
            } else {
                String valueOf2 = String.valueOf(file);
                StringBuilder sb2 = new StringBuilder(String.valueOf(valueOf2).length() + 21);
                sb2.append("Unable to move file: ");
                sb2.append(valueOf2);
                throw new C2402bv(sb2.toString());
            }
        }
        file2.mkdirs();
        for (File file3 : file.listFiles()) {
            m891a(file3, new File(file2, file3.getName()));
        }
        if (file.delete()) {
            return;
        }
        String valueOf3 = String.valueOf(file);
        StringBuilder sb3 = new StringBuilder(String.valueOf(valueOf3).length() + 28);
        sb3.append("Unable to delete directory: ");
        sb3.append(valueOf3);
        throw new C2402bv(sb3.toString());
    }

    /* renamed from: a */
    public final void m892a(C2439de c2439de) {
        File m994b = this.f684b.m994b(c2439de.f628k, c2439de.f680a, c2439de.f681b, c2439de.f682c);
        if (!m994b.exists()) {
            throw new C2402bv(String.format("Cannot find verified files for slice %s.", c2439de.f682c), c2439de.f627j);
        }
        File m990c = this.f684b.m990c(c2439de.f628k, c2439de.f680a, c2439de.f681b);
        if (!m990c.exists()) {
            m990c.mkdirs();
        }
        m891a(m994b, m990c);
        try {
            this.f684b.m1003a(c2439de.f628k, c2439de.f680a, c2439de.f681b, this.f684b.m986d(c2439de.f628k, c2439de.f680a, c2439de.f681b) + 1);
        } catch (IOException e) {
            f683a.m806b("Writing merge checkpoint failed with %s.", e.getMessage());
            throw new C2402bv("Writing merge checkpoint failed.", e, c2439de.f627j);
        }
    }
}
