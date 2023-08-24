package com.google.android.play.core.splitcompat;

import android.content.Context;
import android.content.pm.PackageManager;
import android.util.Log;
import com.google.android.play.core.internal.C2510av;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/* renamed from: com.google.android.play.core.splitcompat.c */
/* loaded from: classes3.dex */
public final class C2595c {

    /* renamed from: a */
    private final long f903a;

    /* renamed from: b */
    private final Context f904b;

    /* renamed from: c */
    private File f905c;

    public C2595c(Context context) throws PackageManager.NameNotFoundException {
        this.f904b = context;
        this.f903a = context.getPackageManager().getPackageInfo(context.getPackageName(), 0).versionCode;
    }

    /* renamed from: a */
    private static File m606a(File file, String str) throws IOException {
        File file2 = new File(file, str);
        if (file2.getCanonicalPath().startsWith(file.getCanonicalPath())) {
            return file2;
        }
        throw new IllegalArgumentException("split ID cannot be placed in target directory");
    }

    /* renamed from: c */
    public static void m599c(File file) throws IOException {
        File[] listFiles;
        if (file.isDirectory() && (listFiles = file.listFiles()) != null) {
            for (File file2 : listFiles) {
                m599c(file2);
            }
        }
        if (file.exists() && !file.delete()) {
            throw new IOException(String.format("Failed to delete '%s'", file.getAbsolutePath()));
        }
    }

    /* renamed from: d */
    private static void m596d(File file) throws IOException {
        if (file.exists()) {
            if (!file.isDirectory()) {
                throw new IllegalArgumentException("File input must be directory when it exists.");
            }
            return;
        }
        file.mkdirs();
        if (file.isDirectory()) {
            return;
        }
        String valueOf = String.valueOf(file.getAbsolutePath());
        throw new IOException(valueOf.length() != 0 ? "Unable to create directory: ".concat(valueOf) : new String("Unable to create directory: "));
    }

    /* renamed from: f */
    private final File m592f() throws IOException {
        File file = new File(m590g(), "verified-splits");
        m596d(file);
        return file;
    }

    /* renamed from: g */
    private final File m590g() throws IOException {
        File file = new File(m588h(), Long.toString(this.f903a));
        m596d(file);
        return file;
    }

    /* renamed from: g */
    private final File m589g(String str) throws IOException {
        File m606a = m606a(m586i(), str);
        m596d(m606a);
        return m606a;
    }

    /* renamed from: h */
    private final File m588h() throws IOException {
        if (this.f905c == null) {
            Context context = this.f904b;
            if (context == null) {
                throw new IllegalStateException("context must be non-null to populate null filesDir");
            }
            this.f905c = context.getFilesDir();
        }
        File file = new File(this.f905c, "splitcompat");
        m596d(file);
        return file;
    }

    /* renamed from: h */
    private static String m587h(String str) {
        return String.valueOf(str).concat(".apk");
    }

    /* renamed from: i */
    private final File m586i() throws IOException {
        File file = new File(m590g(), "native-libraries");
        m596d(file);
        return file;
    }

    /* renamed from: a */
    public final File m607a(File file) throws IOException {
        return m606a(m592f(), file.getName());
    }

    /* renamed from: a */
    public final File m605a(String str) throws IOException {
        return m606a(m600c(), m587h(str));
    }

    /* renamed from: a */
    public final File m604a(String str, String str2) throws IOException {
        return m606a(m589g(str), str2);
    }

    /* renamed from: a */
    public final void m608a() throws IOException {
        File m588h = m588h();
        String[] list = m588h.list();
        if (list != null) {
            for (String str : list) {
                if (!str.equals(Long.toString(this.f903a))) {
                    File file = new File(m588h, str);
                    String valueOf = String.valueOf(file);
                    long j = this.f903a;
                    StringBuilder sb = new StringBuilder(String.valueOf(valueOf).length() + 118);
                    sb.append("FileStorage: removing directory for different version code (directory = ");
                    sb.append(valueOf);
                    sb.append(", current version code = ");
                    sb.append(j);
                    sb.append(")");
                    Log.d("SplitCompat", sb.toString());
                    m599c(file);
                }
            }
        }
    }

    /* renamed from: b */
    public final File m603b() throws IOException {
        return new File(m590g(), "lock.tmp");
    }

    /* renamed from: b */
    public final File m601b(String str) throws IOException {
        return m606a(m592f(), m587h(str));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: b */
    public final void m602b(File file) throws IOException {
        C2510av.m773a(file.getParentFile().getParentFile().equals(m586i()), "File to remove is not a native library");
        m599c(file);
    }

    /* renamed from: c */
    public final File m600c() throws IOException {
        File file = new File(m590g(), "unverified-splits");
        m596d(file);
        return file;
    }

    /* renamed from: c */
    public final File m598c(String str) throws IOException {
        File file = new File(m590g(), "dex");
        m596d(file);
        File m606a = m606a(file, str);
        m596d(m606a);
        return m606a;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: d */
    public final Set<C2609q> m597d() throws IOException {
        String name;
        File m592f = m592f();
        HashSet hashSet = new HashSet();
        File[] listFiles = m592f.listFiles();
        if (listFiles != null) {
            for (File file : listFiles) {
                if (file.isFile() && file.getName().endsWith(".apk")) {
                    hashSet.add(new C2609q(file, file.getName().substring(0, name.length() - 4)));
                }
            }
        }
        return hashSet;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: d */
    public final void m595d(String str) throws IOException {
        m599c(m589g(str));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: e */
    public final List<String> m594e() throws IOException {
        ArrayList arrayList = new ArrayList();
        File[] listFiles = m586i().listFiles();
        if (listFiles != null) {
            for (File file : listFiles) {
                if (file.isDirectory()) {
                    arrayList.add(file.getName());
                }
            }
        }
        return arrayList;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: e */
    public final Set<File> m593e(String str) throws IOException {
        HashSet hashSet = new HashSet();
        File[] listFiles = m589g(str).listFiles();
        if (listFiles != null) {
            for (File file : listFiles) {
                if (file.isFile()) {
                    hashSet.add(file);
                }
            }
        }
        return hashSet;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: f */
    public final void m591f(String str) throws IOException {
        m599c(m601b(str));
    }
}
