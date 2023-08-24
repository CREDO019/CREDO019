package com.google.android.play.core.internal;

import com.google.android.play.core.splitinstall.C2646k;
import io.jsonwebtoken.Header;
import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/* renamed from: com.google.android.play.core.internal.bf */
/* loaded from: classes3.dex */
final class C2521bf implements InterfaceC2509au {
    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: a */
    public static InterfaceC2514az m766a() {
        return new C2518bc();
    }

    /* renamed from: a */
    public static void m765a(ClassLoader classLoader, Set<File> set, InterfaceC2520be interfaceC2520be) {
        if (set.isEmpty()) {
            return;
        }
        HashSet hashSet = new HashSet();
        for (File file : set) {
            hashSet.add(file.getParentFile());
        }
        Object m771a = C2516ba.m771a(classLoader);
        C2531bp m742a = C2532bq.m742a(m771a, "nativeLibraryDirectories", List.class);
        synchronized (C2646k.class) {
            ArrayList arrayList = new ArrayList((Collection) m742a.m756a());
            hashSet.removeAll(arrayList);
            arrayList.addAll(hashSet);
            m742a.m755a((C2531bp) arrayList);
        }
        ArrayList arrayList2 = new ArrayList();
        Object[] mo762a = interfaceC2520be.mo762a(m771a, new ArrayList(hashSet), arrayList2);
        if (arrayList2.isEmpty()) {
            synchronized (C2646k.class) {
                C2532bq.m735b(m771a, "nativeLibraryPathElements", Object.class).m752b(Arrays.asList(mo762a));
            }
            return;
        }
        C2530bo c2530bo = new C2530bo("Error in makePathElements");
        int size = arrayList2.size();
        for (int r0 = 0; r0 < size; r0++) {
            C2551ci.m714a(c2530bo, arrayList2.get(r0));
        }
        throw c2530bo;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: b */
    public static InterfaceC2520be m764b() {
        return new C2519bd();
    }

    /* renamed from: b */
    public static boolean m763b(ClassLoader classLoader, File file, File file2, boolean z) {
        return C2516ba.m770a(classLoader, file, file2, z, m766a(), Header.COMPRESSION_ALGORITHM, C2516ba.m769b());
    }

    @Override // com.google.android.play.core.internal.InterfaceC2509au
    /* renamed from: a */
    public final void mo757a(ClassLoader classLoader, Set<File> set) {
        m765a(classLoader, set, m764b());
    }

    @Override // com.google.android.play.core.internal.InterfaceC2509au
    /* renamed from: a */
    public final boolean mo758a(ClassLoader classLoader, File file, File file2, boolean z) {
        return m763b(classLoader, file, file2, z);
    }
}
