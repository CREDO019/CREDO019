package com.google.android.play.core.splitcompat;

import android.os.Build;
import android.util.Log;
import com.google.android.play.core.internal.C2551ci;
import java.io.File;
import java.io.IOException;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

/* renamed from: com.google.android.play.core.splitcompat.k */
/* loaded from: classes3.dex */
public final class C2603k {

    /* renamed from: a */
    public static final /* synthetic */ int f919a = 0;

    /* renamed from: b */
    private static final Pattern f920b = Pattern.compile("lib/([^/]+)/(.*\\.so)$");

    /* renamed from: c */
    private final C2595c f921c;

    /* JADX INFO: Access modifiers changed from: package-private */
    public C2603k(C2595c c2595c) throws IOException {
        this.f921c = c2595c;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: a */
    public static /* synthetic */ Set m581a(C2603k c2603k, Set set, C2609q c2609q, ZipFile zipFile) throws IOException {
        HashSet hashSet = new HashSet();
        c2603k.m578a(c2609q, set, new C2599g(hashSet, c2609q, zipFile));
        return hashSet;
    }

    /* renamed from: a */
    private static void m579a(C2609q c2609q, InterfaceC2600h interfaceC2600h) throws IOException {
        ZipFile zipFile;
        String[] strArr;
        String format;
        try {
            zipFile = new ZipFile(c2609q.m574a());
            try {
                String m573b = c2609q.m573b();
                HashMap hashMap = new HashMap();
                Enumeration<? extends ZipEntry> entries = zipFile.entries();
                while (entries.hasMoreElements()) {
                    ZipEntry nextElement = entries.nextElement();
                    Matcher matcher = f920b.matcher(nextElement.getName());
                    if (matcher.matches()) {
                        String group = matcher.group(1);
                        String group2 = matcher.group(2);
                        Log.d("SplitCompat", String.format("NativeLibraryExtractor: split '%s' has native library '%s' for ABI '%s'", m573b, group2, group));
                        Set set = (Set) hashMap.get(group);
                        if (set == null) {
                            set = new HashSet();
                            hashMap.put(group, set);
                        }
                        set.add(new C2602j(nextElement, group2));
                    }
                }
                HashMap hashMap2 = new HashMap();
                for (String str : Build.SUPPORTED_ABIS) {
                    if (hashMap.containsKey(str)) {
                        Log.d("SplitCompat", String.format("NativeLibraryExtractor: there are native libraries for supported ABI %s; will use this ABI", str));
                        for (C2602j c2602j : (Set) hashMap.get(str)) {
                            if (hashMap2.containsKey(c2602j.f917a)) {
                                format = String.format("NativeLibraryExtractor: skipping library %s for ABI %s; already present for a better ABI", c2602j.f917a, str);
                            } else {
                                hashMap2.put(c2602j.f917a, c2602j);
                                format = String.format("NativeLibraryExtractor: using library %s for ABI %s", c2602j.f917a, str);
                            }
                            Log.d("SplitCompat", format);
                        }
                    } else {
                        Log.d("SplitCompat", String.format("NativeLibraryExtractor: there are no native libraries for supported ABI %s", str));
                    }
                }
                interfaceC2600h.mo585a(zipFile, new HashSet(hashMap2.values()));
                zipFile.close();
            } catch (IOException e) {
                e = e;
                if (zipFile != null) {
                    try {
                        zipFile.close();
                    } catch (IOException e2) {
                        C2551ci.m714a(e, e2);
                    }
                }
                throw e;
            }
        } catch (IOException e3) {
            e = e3;
            zipFile = null;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: a */
    public final void m578a(C2609q c2609q, Set<C2602j> set, InterfaceC2601i interfaceC2601i) throws IOException {
        for (C2602j c2602j : set) {
            File m604a = this.f921c.m604a(c2609q.m573b(), c2602j.f917a);
            boolean z = false;
            if (m604a.exists() && m604a.length() == c2602j.f918b.getSize()) {
                z = true;
            }
            interfaceC2601i.mo584a(c2602j, m604a, z);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: a */
    public final Set<File> m583a() throws IOException {
        Log.d("SplitCompat", "NativeLibraryExtractor: synchronizing native libraries");
        Set<C2609q> m597d = this.f921c.m597d();
        for (String str : this.f921c.m594e()) {
            Iterator<C2609q> it = m597d.iterator();
            while (true) {
                if (it.hasNext()) {
                    if (it.next().m573b().equals(str)) {
                        break;
                    }
                } else {
                    Log.i("SplitCompat", String.format("NativeLibraryExtractor: extracted split '%s' has no corresponding split; deleting", str));
                    this.f921c.m595d(str);
                    break;
                }
            }
        }
        HashSet hashSet = new HashSet();
        for (C2609q c2609q : m597d) {
            HashSet hashSet2 = new HashSet();
            m579a(c2609q, new C2598f(this, hashSet2, c2609q));
            for (File file : this.f921c.m593e(c2609q.m573b())) {
                if (!hashSet2.contains(file)) {
                    Log.i("SplitCompat", String.format("NativeLibraryExtractor: file '%s' found in split '%s' that is not in the split file '%s'; removing", file.getAbsolutePath(), c2609q.m573b(), c2609q.m574a().getAbsolutePath()));
                    this.f921c.m602b(file);
                }
            }
            hashSet.addAll(hashSet2);
        }
        return hashSet;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: a */
    public final Set<File> m580a(C2609q c2609q) throws IOException {
        AtomicBoolean atomicBoolean = new AtomicBoolean(true);
        HashSet hashSet = new HashSet();
        m579a(c2609q, new C2597e(this, c2609q, hashSet, atomicBoolean));
        if (atomicBoolean.get()) {
            return hashSet;
        }
        return null;
    }
}
