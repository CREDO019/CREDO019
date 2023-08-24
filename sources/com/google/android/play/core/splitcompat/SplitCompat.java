package com.google.android.play.core.splitcompat;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Build;
import android.util.Log;
import com.google.android.play.core.internal.C2507as;
import com.google.android.play.core.internal.C2508at;
import com.google.android.play.core.internal.C2510av;
import com.google.android.play.core.internal.C2530bo;
import com.google.android.play.core.internal.C2551ci;
import com.google.android.play.core.internal.InterfaceC2509au;
import com.google.android.play.core.splitinstall.C2651o;
import com.google.android.play.core.splitinstall.C2652p;
import com.google.android.play.core.splitinstall.EnumC2647l;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.concurrent.atomic.AtomicReference;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

/* loaded from: classes3.dex */
public class SplitCompat {

    /* renamed from: a */
    private static final AtomicReference<SplitCompat> f898a = new AtomicReference<>(null);

    /* renamed from: b */
    private final C2595c f899b;

    /* renamed from: c */
    private final Set<String> f900c = new HashSet();

    /* renamed from: d */
    private final C2593a f901d;

    private SplitCompat(Context context) {
        try {
            C2595c c2595c = new C2595c(context);
            this.f899b = c2595c;
            this.f901d = new C2593a(c2595c);
        } catch (PackageManager.NameNotFoundException e) {
            throw new C2530bo(e);
        }
    }

    /* renamed from: a */
    public static boolean m618a() {
        return f898a.get() != null;
    }

    /* renamed from: a */
    public static boolean m617a(Context context) {
        return m616a(context, true);
    }

    /* renamed from: a */
    private static boolean m616a(Context context, boolean z) {
        if (m614b()) {
            return false;
        }
        AtomicReference<SplitCompat> atomicReference = f898a;
        boolean compareAndSet = atomicReference.compareAndSet(null, new SplitCompat(context));
        SplitCompat splitCompat = atomicReference.get();
        if (compareAndSet) {
            EnumC2647l.f1005a.m523a(new C2507as(context, C2608p.m577a(), new C2508at(context, splitCompat.f899b, new C2510av(), null), splitCompat.f899b, new C2608p()));
            C2651o.m517a(new C2604l(splitCompat));
            C2608p.m577a().execute(new RunnableC2605m(context));
        }
        try {
            splitCompat.m613b(context, z);
            return true;
        } catch (Exception e) {
            Log.e("SplitCompat", "Error installing additional splits", e);
            return false;
        }
    }

    /* renamed from: b */
    private final synchronized void m613b(Context context, boolean z) throws IOException {
        ZipFile zipFile;
        if (z) {
            this.f899b.m608a();
        } else {
            C2608p.m577a().execute(new RunnableC2606n(this));
        }
        String packageName = context.getPackageName();
        try {
            PackageInfo packageInfo = context.getPackageManager().getPackageInfo(packageName, 0);
            List<String> arrayList = packageInfo.splitNames == null ? new ArrayList() : Arrays.asList(packageInfo.splitNames);
            Set<C2609q> m597d = this.f899b.m597d();
            HashSet hashSet = new HashSet();
            Iterator<C2609q> it = m597d.iterator();
            while (it.hasNext()) {
                String m573b = it.next().m573b();
                if (arrayList.contains(m573b)) {
                    if (z) {
                        this.f899b.m591f(m573b);
                    } else {
                        hashSet.add(m573b);
                    }
                    it.remove();
                }
            }
            if (!hashSet.isEmpty()) {
                C2608p.m577a().execute(new RunnableC2607o(this, hashSet));
            }
            HashSet hashSet2 = new HashSet();
            for (C2609q c2609q : m597d) {
                String m573b2 = c2609q.m573b();
                if (!C2652p.m513b(m573b2)) {
                    hashSet2.add(m573b2);
                }
            }
            for (String str : arrayList) {
                if (!C2652p.m513b(str)) {
                    hashSet2.add(str);
                }
            }
            HashSet<C2609q> hashSet3 = new HashSet(m597d.size());
            for (C2609q c2609q2 : m597d) {
                if (!C2652p.m515a(c2609q2.m573b())) {
                    String m573b3 = c2609q2.m573b();
                    if (hashSet2.contains(C2652p.m515a(m573b3) ? "" : m573b3.split("\\.config\\.", 2)[0])) {
                    }
                }
                hashSet3.add(c2609q2);
            }
            C2603k c2603k = new C2603k(this.f899b);
            InterfaceC2509au m776a = C2510av.m776a();
            ClassLoader classLoader = context.getClassLoader();
            if (z) {
                m776a.mo757a(classLoader, c2603k.m583a());
            } else {
                Iterator it2 = hashSet3.iterator();
                while (it2.hasNext()) {
                    Set<File> m580a = c2603k.m580a((C2609q) it2.next());
                    if (m580a == null) {
                        it2.remove();
                    } else {
                        m776a.mo757a(classLoader, m580a);
                    }
                }
            }
            HashSet hashSet4 = new HashSet();
            for (C2609q c2609q3 : hashSet3) {
                try {
                    zipFile = new ZipFile(c2609q3.m574a());
                    try {
                        ZipEntry entry = zipFile.getEntry("classes.dex");
                        zipFile.close();
                        if (entry != null && !m776a.mo758a(classLoader, this.f899b.m598c(c2609q3.m573b()), c2609q3.m574a(), z)) {
                            String valueOf = String.valueOf(c2609q3.m574a());
                            StringBuilder sb = new StringBuilder(String.valueOf(valueOf).length() + 24);
                            sb.append("split was not installed ");
                            sb.append(valueOf);
                            Log.w("SplitCompat", sb.toString());
                        }
                        hashSet4.add(c2609q3.m574a());
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
            C2593a.m609b(context, hashSet4);
            HashSet hashSet5 = new HashSet();
            for (C2609q c2609q4 : hashSet3) {
                if (hashSet4.contains(c2609q4.m574a())) {
                    String m573b4 = c2609q4.m573b();
                    StringBuilder sb2 = new StringBuilder(String.valueOf(m573b4).length() + 30);
                    sb2.append("Split '");
                    sb2.append(m573b4);
                    sb2.append("' installation emulated");
                    Log.d("SplitCompat", sb2.toString());
                    hashSet5.add(c2609q4.m573b());
                } else {
                    String m573b5 = c2609q4.m573b();
                    StringBuilder sb3 = new StringBuilder(String.valueOf(m573b5).length() + 35);
                    sb3.append("Split '");
                    sb3.append(m573b5);
                    sb3.append("' installation not emulated.");
                    Log.d("SplitCompat", sb3.toString());
                }
            }
            synchronized (this.f900c) {
                this.f900c.addAll(hashSet5);
            }
        } catch (PackageManager.NameNotFoundException e4) {
            throw new IOException(String.format("Cannot load data for application '%s'", packageName), e4);
        }
    }

    /* renamed from: b */
    private static boolean m614b() {
        return Build.VERSION.SDK_INT < 21;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: c */
    public final Set<String> m611c() {
        HashSet hashSet;
        synchronized (this.f900c) {
            hashSet = new HashSet(this.f900c);
        }
        return hashSet;
    }

    public static boolean install(Context context) {
        return m616a(context, false);
    }

    public static boolean installActivity(Context context) {
        if (m614b()) {
            return false;
        }
        SplitCompat splitCompat = f898a.get();
        if (splitCompat != null) {
            return splitCompat.f901d.m610a(context, splitCompat.m611c());
        }
        throw new IllegalStateException("SplitCompat.installActivity can only be called if SplitCompat.install is first called at startup on application context.");
    }
}
