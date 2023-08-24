package com.google.android.play.core.internal;

import android.util.Log;
import com.google.android.play.core.splitinstall.C2646k;
import io.jsonwebtoken.Header;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/* renamed from: com.google.android.play.core.internal.ba */
/* loaded from: classes3.dex */
final class C2516ba implements InterfaceC2509au {
    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: a */
    public static InterfaceC2514az m772a() {
        return new C2511aw();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: a */
    public static Object m771a(ClassLoader classLoader) {
        return C2532bq.m742a(classLoader, "pathList", Object.class).m756a();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: a */
    public static boolean m770a(ClassLoader classLoader, File file, File file2, boolean z, InterfaceC2514az interfaceC2514az, String str, InterfaceC2513ay interfaceC2513ay) {
        ArrayList<IOException> arrayList = new ArrayList<>();
        Object m771a = m771a(classLoader);
        C2531bp m735b = C2532bq.m735b(m771a, "dexElements", Object.class);
        List<Object> asList = Arrays.asList((Object[]) m735b.m756a());
        ArrayList arrayList2 = new ArrayList();
        for (Object obj : asList) {
            arrayList2.add((File) C2532bq.m742a(obj, str, File.class).m756a());
        }
        if (arrayList2.contains(file2)) {
            return true;
        }
        if (!z && !interfaceC2513ay.mo759a(m771a, file2, file)) {
            String valueOf = String.valueOf(file2.getPath());
            Log.w("SplitCompat", valueOf.length() != 0 ? "Should be optimized ".concat(valueOf) : new String("Should be optimized "));
            return false;
        }
        m735b.m754a((Collection) Arrays.asList(interfaceC2514az.mo767a(m771a, new ArrayList<>(Collections.singleton(file2)), file, arrayList)));
        if (arrayList.isEmpty()) {
            return true;
        }
        C2530bo c2530bo = new C2530bo("DexPathList.makeDexElement failed");
        int size = arrayList.size();
        for (int r3 = 0; r3 < size; r3++) {
            IOException iOException = arrayList.get(r3);
            Log.e("SplitCompat", "DexPathList.makeDexElement failed", iOException);
            C2551ci.m714a(c2530bo, iOException);
        }
        C2532bq.m735b(m771a, "dexElementsSuppressedExceptions", IOException.class).m754a((Collection) arrayList);
        throw c2530bo;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: b */
    public static InterfaceC2513ay m769b() {
        return new C2512ax();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: b */
    public static void m768b(ClassLoader classLoader, Set<File> set) {
        if (set.isEmpty()) {
            return;
        }
        HashSet hashSet = new HashSet();
        for (File file : set) {
            String valueOf = String.valueOf(file.getParentFile().getAbsolutePath());
            Log.d("Splitcompat", valueOf.length() != 0 ? "Adding native library parent directory: ".concat(valueOf) : new String("Adding native library parent directory: "));
            hashSet.add(file.getParentFile());
        }
        C2531bp m735b = C2532bq.m735b(m771a(classLoader), "nativeLibraryDirectories", File.class);
        hashSet.removeAll(Arrays.asList((File[]) m735b.m756a()));
        synchronized (C2646k.class) {
            int size = hashSet.size();
            StringBuilder sb = new StringBuilder(30);
            sb.append("Adding directories ");
            sb.append(size);
            Log.d("Splitcompat", sb.toString());
            m735b.m752b(hashSet);
        }
    }

    @Override // com.google.android.play.core.internal.InterfaceC2509au
    /* renamed from: a */
    public final void mo757a(ClassLoader classLoader, Set<File> set) {
        m768b(classLoader, set);
    }

    @Override // com.google.android.play.core.internal.InterfaceC2509au
    /* renamed from: a */
    public final boolean mo758a(ClassLoader classLoader, File file, File file2, boolean z) {
        return m770a(classLoader, file, file2, z, m772a(), Header.COMPRESSION_ALGORITHM, m769b());
    }
}
