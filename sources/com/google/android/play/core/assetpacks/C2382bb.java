package com.google.android.play.core.assetpacks;

import android.content.Context;
import android.content.pm.PackageInfo;
import com.google.android.play.core.internal.C2494af;
import com.google.android.play.core.internal.C2551ci;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

/* JADX INFO: Access modifiers changed from: package-private */
/* renamed from: com.google.android.play.core.assetpacks.bb */
/* loaded from: classes3.dex */
public final class C2382bb {

    /* renamed from: a */
    private static final C2494af f471a = new C2494af("AssetPackStorage");

    /* renamed from: b */
    private static final long f472b = TimeUnit.DAYS.toMillis(14);

    /* renamed from: c */
    private static final long f473c = TimeUnit.DAYS.toMillis(28);

    /* renamed from: d */
    private final Context f474d;

    /* renamed from: e */
    private final C2446dl f475e;

    /* JADX INFO: Access modifiers changed from: package-private */
    public C2382bb(Context context, C2446dl c2446dl) {
        this.f474d = context;
        this.f475e = c2446dl;
    }

    /* renamed from: a */
    private final File m1005a(String str, int r3) {
        return new File(m975g(str), String.valueOf(r3));
    }

    /* renamed from: a */
    private static List<String> m1008a(PackageInfo packageInfo, String str) {
        ArrayList arrayList = new ArrayList();
        if (packageInfo.splitNames == null) {
            return arrayList;
        }
        for (int r1 = (-Arrays.binarySearch(packageInfo.splitNames, str)) - 1; r1 < packageInfo.splitNames.length && packageInfo.splitNames[r1].startsWith(str); r1++) {
            arrayList.add(packageInfo.applicationInfo.splitSourceDirs[r1]);
        }
        return arrayList;
    }

    /* renamed from: a */
    private static void m1007a(File file) {
        File[] listFiles;
        if (file.listFiles() == null || file.listFiles().length <= 1) {
            return;
        }
        long m997b = m997b(file);
        for (File file2 : file.listFiles()) {
            if (!file2.getName().equals(String.valueOf(m997b)) && !file2.getName().equals("stale.tmp")) {
                m992c(file2);
            }
        }
    }

    /* renamed from: b */
    private static long m997b(File file) {
        File[] listFiles;
        if (file.exists()) {
            ArrayList arrayList = new ArrayList();
            try {
                for (File file2 : file.listFiles()) {
                    if (!file2.getName().equals("stale.tmp")) {
                        arrayList.add(Long.valueOf(file2.getName()));
                    }
                }
            } catch (NumberFormatException e) {
                f471a.m807a(e, "Corrupt asset pack directories.", new Object[0]);
            }
            if (arrayList.isEmpty()) {
                return -1L;
            }
            Collections.sort(arrayList);
            return ((Long) arrayList.get(arrayList.size() - 1)).longValue();
        }
        return -1L;
    }

    /* renamed from: c */
    private static boolean m992c(File file) {
        boolean z;
        File[] listFiles = file.listFiles();
        if (listFiles != null) {
            z = true;
            for (File file2 : listFiles) {
                z &= m992c(file2);
            }
        } else {
            z = true;
        }
        return file.delete() && true == z;
    }

    /* renamed from: g */
    private final File m975g(String str) {
        return new File(m971i(), str);
    }

    /* renamed from: g */
    private final File m974g(String str, int r3, long j) {
        return new File(m990c(str, r3, j), "merge.tmp");
    }

    /* renamed from: g */
    private final List<File> m976g() {
        File[] listFiles;
        ArrayList arrayList = new ArrayList();
        try {
        } catch (IOException e) {
            f471a.m806b("Could not process directory while scanning installed packs. %s", e);
        }
        if (m971i().exists() && m971i().listFiles() != null) {
            for (File file : m971i().listFiles()) {
                if (!file.getCanonicalPath().equals(m973h().getCanonicalPath())) {
                    arrayList.add(file);
                }
            }
            return arrayList;
        }
        return arrayList;
    }

    /* renamed from: h */
    private final File m973h() {
        return new File(m971i(), "_tmp");
    }

    /* renamed from: h */
    private final File m972h(String str, int r6, long j) {
        return new File(new File(new File(m973h(), str), String.valueOf(r6)), String.valueOf(j));
    }

    /* renamed from: i */
    private final File m971i() {
        return new File(this.f474d.getFilesDir(), "assetpacks");
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Removed duplicated region for block: B:24:0x0069  */
    /* JADX WARN: Removed duplicated region for block: B:25:0x0073  */
    /* renamed from: a */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final com.google.android.play.core.assetpacks.AssetLocation m1001a(java.lang.String r10, java.lang.String r11) {
        /*
            r9 = this;
            r0 = 0
            r1 = 0
            android.content.Context r2 = r9.f474d     // Catch: android.content.pm.PackageManager.NameNotFoundException -> L13
            android.content.pm.PackageManager r2 = r2.getPackageManager()     // Catch: android.content.pm.PackageManager.NameNotFoundException -> L13
            android.content.Context r3 = r9.f474d     // Catch: android.content.pm.PackageManager.NameNotFoundException -> L13
            java.lang.String r3 = r3.getPackageName()     // Catch: android.content.pm.PackageManager.NameNotFoundException -> L13
            android.content.pm.PackageInfo r2 = r2.getPackageInfo(r3, r1)     // Catch: android.content.pm.PackageManager.NameNotFoundException -> L13
            goto L1d
        L13:
            com.google.android.play.core.internal.af r2 = com.google.android.play.core.assetpacks.C2382bb.f471a
            java.lang.Object[] r3 = new java.lang.Object[r1]
            java.lang.String r4 = "Could not find PackageInfo."
            r2.m806b(r4, r3)
            r2 = r0
        L1d:
            r3 = 1
            if (r2 != 0) goto L22
            r4 = r0
            goto L87
        L22:
            java.util.ArrayList r4 = new java.util.ArrayList
            r4.<init>()
            int r5 = android.os.Build.VERSION.SDK_INT
            r6 = 21
            if (r5 >= r6) goto L35
            android.content.pm.ApplicationInfo r2 = r2.applicationInfo
            java.lang.String r2 = r2.sourceDir
            r4.add(r2)
            goto L87
        L35:
            java.lang.String[] r5 = r2.splitNames
            if (r5 == 0) goto L5b
            android.content.pm.ApplicationInfo r5 = r2.applicationInfo
            java.lang.String[] r5 = r5.splitSourceDirs
            if (r5 != 0) goto L40
            goto L5b
        L40:
            java.lang.String[] r5 = r2.splitNames
            int r5 = java.util.Arrays.binarySearch(r5, r10)
            if (r5 >= 0) goto L54
            com.google.android.play.core.internal.af r5 = com.google.android.play.core.assetpacks.C2382bb.f471a
            java.lang.Object[] r6 = new java.lang.Object[r3]
            r6[r1] = r10
            java.lang.String r7 = "Asset Pack '%s' is not installed."
            r5.m808a(r7, r6)
            goto L66
        L54:
            android.content.pm.ApplicationInfo r6 = r2.applicationInfo
            java.lang.String[] r6 = r6.splitSourceDirs
            r5 = r6[r5]
            goto L67
        L5b:
            com.google.android.play.core.internal.af r5 = com.google.android.play.core.assetpacks.C2382bb.f471a
            java.lang.Object[] r6 = new java.lang.Object[r3]
            r6[r1] = r10
            java.lang.String r7 = "No splits present for package %s."
            r5.m808a(r7, r6)
        L66:
            r5 = r0
        L67:
            if (r5 != 0) goto L73
            android.content.pm.ApplicationInfo r5 = r2.applicationInfo
            java.lang.String r5 = r5.sourceDir
            r4.add(r5)
            java.lang.String r5 = "config."
            goto L80
        L73:
            r4.add(r5)
            java.lang.String r5 = java.lang.String.valueOf(r10)
            java.lang.String r6 = ".config."
            java.lang.String r5 = r5.concat(r6)
        L80:
            java.util.List r2 = m1008a(r2, r5)
            r4.addAll(r2)
        L87:
            if (r4 != 0) goto L8a
            goto Lcc
        L8a:
            java.io.File r2 = new java.io.File
            java.lang.String r5 = "assets"
            r2.<init>(r5, r11)
            java.lang.String r2 = r2.getPath()
            int r5 = r4.size()
            r6 = 0
        L9a:
            r7 = 2
            if (r6 >= r5) goto Lbc
            java.lang.Object r8 = r4.get(r6)
            java.lang.String r8 = (java.lang.String) r8
            com.google.android.play.core.assetpacks.AssetLocation r7 = com.google.android.play.core.assetpacks.C2436db.m906a(r8, r2)     // Catch: java.io.IOException -> Lad
            int r6 = r6 + 1
            if (r7 == 0) goto L9a
            r0 = r7
            goto Lcc
        Lad:
            r10 = move-exception
            com.google.android.play.core.internal.af r2 = com.google.android.play.core.assetpacks.C2382bb.f471a
            java.lang.Object[] r4 = new java.lang.Object[r7]
            r4[r1] = r8
            r4[r3] = r11
            java.lang.String r11 = "Failed to parse APK file '%s' looking for asset '%s'."
            r2.m807a(r10, r11, r4)
            goto Lcc
        Lbc:
            com.google.android.play.core.internal.af r2 = com.google.android.play.core.assetpacks.C2382bb.f471a
            r5 = 3
            java.lang.Object[] r5 = new java.lang.Object[r5]
            r5[r1] = r11
            r5[r3] = r10
            r5[r7] = r4
            java.lang.String r10 = "The asset %s is not present in Asset Pack %s. Searched in APKs: %s"
            r2.m808a(r10, r5)
        Lcc:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.play.core.assetpacks.C2382bb.m1001a(java.lang.String, java.lang.String):com.google.android.play.core.assetpacks.AssetLocation");
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: a */
    public final AssetLocation m1000a(String str, String str2, AssetPackLocation assetPackLocation) {
        File file = new File(assetPackLocation.assetsPath(), str2);
        if (file.exists()) {
            return AssetLocation.m1044a(file.getPath(), 0L, file.length());
        }
        f471a.m808a("The asset %s is not present in Asset Pack %s. Searched in folder: %s", str2, str, assetPackLocation.assetsPath());
        return null;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: a */
    public final File m1004a(String str, int r3, long j) {
        return new File(m1005a(str, r3), String.valueOf(j));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: a */
    public final File m1002a(String str, int r5, long j, String str2) {
        return new File(new File(new File(m972h(str, r5, j), "_slices"), "_unverified"), str2);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: a */
    public final Map<String, AssetPackLocation> m1009a() {
        HashMap hashMap = new HashMap();
        try {
            for (File file : m976g()) {
                AssetPackLocation m996b = m996b(file.getName());
                if (m996b != null) {
                    hashMap.put(file.getName(), m996b);
                }
            }
        } catch (IOException e) {
            f471a.m806b("Could not process directory while scanning installed packs: %s", e);
        }
        return hashMap;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: a */
    public final void m1003a(String str, int r2, long j, int r5) throws IOException {
        File m974g = m974g(str, r2, j);
        Properties properties = new Properties();
        properties.put("numberOfMerges", String.valueOf(r5));
        m974g.getParentFile().mkdirs();
        m974g.createNewFile();
        FileOutputStream fileOutputStream = new FileOutputStream(m974g);
        properties.store(fileOutputStream, (String) null);
        fileOutputStream.close();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: a */
    public final void m999a(List<String> list) {
        int m888a = this.f475e.m888a();
        List<File> m976g = m976g();
        int size = m976g.size();
        for (int r3 = 0; r3 < size; r3++) {
            File file = m976g.get(r3);
            if (!list.contains(file.getName()) && m997b(file) != m888a) {
                m992c(file);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: a */
    public final boolean m1006a(String str) {
        return m991c(str) != null;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: b */
    public final AssetPackLocation m996b(String str) throws IOException {
        String m991c = m991c(str);
        if (m991c == null) {
            return null;
        }
        File file = new File(m991c, "assets");
        if (file.isDirectory()) {
            return AssetPackLocation.m1039a(m991c, file.getCanonicalPath());
        }
        f471a.m806b("Failed to find assets directory: %s", file);
        return null;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: b */
    public final File m995b(String str, int r3, long j) {
        return new File(m1004a(str, r3, j), "_metadata");
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: b */
    public final File m994b(String str, int r5, long j, String str2) {
        return new File(new File(new File(m972h(str, r5, j), "_slices"), "_verified"), str2);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: b */
    public final Map<String, Long> m998b() {
        HashMap hashMap = new HashMap();
        for (String str : m1009a().keySet()) {
            hashMap.put(str, Long.valueOf(m979f(str)));
        }
        return hashMap;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: c */
    public final File m990c(String str, int r3, long j) {
        return new File(m972h(str, r3, j), "_packs");
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: c */
    public final File m989c(String str, int r3, long j, String str2) {
        return new File(m977f(str, r3, j, str2), "checkpoint.dat");
    }

    /* renamed from: c */
    final String m991c(String str) throws IOException {
        int length;
        File file = new File(m971i(), str);
        if (!file.exists()) {
            f471a.m808a("Pack not found with pack name: %s", str);
            return null;
        }
        File file2 = new File(file, String.valueOf(this.f475e.m888a()));
        if (!file2.exists()) {
            f471a.m808a("Pack not found with pack name: %s app version: %s", str, Integer.valueOf(this.f475e.m888a()));
            return null;
        }
        File[] listFiles = file2.listFiles();
        if (listFiles == null || (length = listFiles.length) == 0) {
            f471a.m808a("No pack version found for pack name: %s app version: %s", str, Integer.valueOf(this.f475e.m888a()));
            return null;
        } else if (length > 1) {
            f471a.m806b("Multiple pack versions found for pack name: %s app version: %s", str, Integer.valueOf(this.f475e.m888a()));
            return null;
        } else {
            return listFiles[0].getCanonicalPath();
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: c */
    public final void m993c() {
        List<File> m976g = m976g();
        int size = m976g.size();
        for (int r3 = 0; r3 < size; r3++) {
            File file = m976g.get(r3);
            if (file.listFiles() != null) {
                m1007a(file);
                long m997b = m997b(file);
                if (this.f475e.m888a() != m997b) {
                    try {
                        new File(new File(file, String.valueOf(m997b)), "stale.tmp").createNewFile();
                    } catch (IOException unused) {
                        f471a.m806b("Could not write staleness marker.", new Object[0]);
                    }
                }
                for (File file2 : file.listFiles()) {
                    m1007a(file2);
                }
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: d */
    public final int m986d(String str, int r2, long j) throws IOException {
        File m974g = m974g(str, r2, j);
        if (m974g.exists()) {
            Properties properties = new Properties();
            FileInputStream fileInputStream = new FileInputStream(m974g);
            try {
                properties.load(fileInputStream);
                fileInputStream.close();
                if (properties.getProperty("numberOfMerges") != null) {
                    try {
                        return Integer.parseInt(properties.getProperty("numberOfMerges"));
                    } catch (NumberFormatException e) {
                        throw new C2402bv("Merge checkpoint file corrupt.", e);
                    }
                }
                throw new C2402bv("Merge checkpoint file corrupt.");
            } catch (Throwable th) {
                try {
                    fileInputStream.close();
                } catch (Throwable th2) {
                    C2551ci.m714a(th, th2);
                }
                throw th;
            }
        }
        return 0;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: d */
    public final File m985d(String str, int r3, long j, String str2) {
        return new File(m977f(str, r3, j, str2), "checkpoint_ext.dat");
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: d */
    public final void m988d() {
        File[] listFiles;
        List<File> m976g = m976g();
        int size = m976g.size();
        for (int r3 = 0; r3 < size; r3++) {
            File file = m976g.get(r3);
            if (file.listFiles() != null) {
                for (File file2 : file.listFiles()) {
                    File file3 = new File(file2, "stale.tmp");
                    if (file3.exists() && System.currentTimeMillis() - file3.lastModified() > f473c) {
                        m992c(file2);
                    }
                }
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: d */
    public final boolean m987d(String str) {
        if (m975g(str).exists()) {
            return m992c(m975g(str));
        }
        return true;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: e */
    public final int m983e(String str) {
        return (int) m997b(m975g(str));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: e */
    public final File m982e(String str, int r4, long j) {
        return new File(new File(m972h(str, r4, j), "_slices"), "_metadata");
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: e */
    public final File m981e(String str, int r3, long j, String str2) {
        return new File(m977f(str, r3, j, str2), "slice.zip");
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: e */
    public final void m984e() {
        File[] listFiles;
        if (m973h().exists()) {
            for (File file : m973h().listFiles()) {
                if (System.currentTimeMillis() - file.lastModified() > f472b) {
                    m992c(file);
                } else {
                    m1007a(file);
                }
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: f */
    public final long m979f(String str) {
        return m997b(m1005a(str, m983e(str)));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: f */
    public final File m977f(String str, int r3, long j, String str2) {
        return new File(m982e(str, r3, j), str2);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: f */
    public final void m980f() {
        m992c(m971i());
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: f */
    public final void m978f(String str, int r3, long j) {
        if (m972h(str, r3, j).exists()) {
            m992c(m972h(str, r3, j));
        }
    }
}
