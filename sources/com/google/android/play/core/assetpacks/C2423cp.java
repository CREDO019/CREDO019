package com.google.android.play.core.assetpacks;

import android.content.Intent;
import android.os.Bundle;
import com.google.android.play.core.internal.C2494af;
import com.google.android.play.core.internal.C2561h;
import com.google.android.play.core.internal.InterfaceC2552cj;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Executor;
import java.util.concurrent.locks.ReentrantLock;

/* JADX INFO: Access modifiers changed from: package-private */
/* renamed from: com.google.android.play.core.assetpacks.cp */
/* loaded from: classes3.dex */
public final class C2423cp {

    /* renamed from: a */
    private static final C2494af f616a = new C2494af("ExtractorSessionStoreView");

    /* renamed from: b */
    private final C2382bb f617b;

    /* renamed from: c */
    private final InterfaceC2552cj<InterfaceC2478w> f618c;

    /* renamed from: d */
    private final C2406bz f619d;

    /* renamed from: e */
    private final InterfaceC2552cj<Executor> f620e;

    /* renamed from: f */
    private final Map<Integer, C2420cm> f621f = new HashMap();

    /* renamed from: g */
    private final ReentrantLock f622g = new ReentrantLock();

    /* JADX INFO: Access modifiers changed from: package-private */
    public C2423cp(C2382bb c2382bb, InterfaceC2552cj<InterfaceC2478w> interfaceC2552cj, C2406bz c2406bz, InterfaceC2552cj<Executor> interfaceC2552cj2) {
        this.f617b = c2382bb;
        this.f618c = interfaceC2552cj;
        this.f619d = c2406bz;
        this.f620e = interfaceC2552cj2;
    }

    /* renamed from: a */
    private final <T> T m939a(InterfaceC2422co<T> interfaceC2422co) {
        try {
            m942a();
            return interfaceC2422co.mo943a();
        } finally {
            m936b();
        }
    }

    /* renamed from: d */
    private final Map<String, C2420cm> m925d(final List<String> list) {
        return (Map) m939a(new InterfaceC2422co(this, list) { // from class: com.google.android.play.core.assetpacks.cf

            /* renamed from: a */
            private final C2423cp f591a;

            /* renamed from: b */
            private final List f592b;

            /* JADX INFO: Access modifiers changed from: package-private */
            {
                this.f591a = this;
                this.f592b = list;
            }

            @Override // com.google.android.play.core.assetpacks.InterfaceC2422co
            /* renamed from: a */
            public final Object mo943a() {
                return this.f591a.m928c(this.f592b);
            }
        });
    }

    /* renamed from: e */
    private final C2420cm m924e(int r5) {
        Map<Integer, C2420cm> map = this.f621f;
        Integer valueOf = Integer.valueOf(r5);
        C2420cm c2420cm = map.get(valueOf);
        if (c2420cm != null) {
            return c2420cm;
        }
        throw new C2402bv(String.format("Could not find session %d while trying to get it", valueOf), r5);
    }

    /* renamed from: e */
    private static String m923e(Bundle bundle) {
        ArrayList<String> stringArrayList = bundle.getStringArrayList("pack_names");
        if (stringArrayList == null || stringArrayList.isEmpty()) {
            throw new C2402bv("Session without pack received.");
        }
        return stringArrayList.get(0);
    }

    /* renamed from: e */
    private static <T> List<T> m922e(List<T> list) {
        return list == null ? Collections.emptyList() : list;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: a */
    public final Map<String, Integer> m937a(final List<String> list) {
        return (Map) m939a(new InterfaceC2422co(this, list) { // from class: com.google.android.play.core.assetpacks.ci

            /* renamed from: a */
            private final C2423cp f597a;

            /* renamed from: b */
            private final List f598b;

            /* JADX INFO: Access modifiers changed from: package-private */
            {
                this.f597a = this;
                this.f598b = list;
            }

            @Override // com.google.android.play.core.assetpacks.InterfaceC2422co
            /* renamed from: a */
            public final Object mo943a() {
                return this.f597a.m932b(this.f598b);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: a */
    public final void m942a() {
        this.f622g.lock();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: a */
    public final void m941a(final int r2) {
        m939a(new InterfaceC2422co(this, r2) { // from class: com.google.android.play.core.assetpacks.ch

            /* renamed from: a */
            private final C2423cp f595a;

            /* renamed from: b */
            private final int f596b;

            /* JADX INFO: Access modifiers changed from: package-private */
            {
                this.f595a = this;
                this.f596b = r2;
            }

            @Override // com.google.android.play.core.assetpacks.InterfaceC2422co
            /* renamed from: a */
            public final Object mo943a() {
                this.f595a.m930c(this.f596b);
                return null;
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: a */
    public final void m938a(final String str, final int r9, final long j) {
        m939a(new InterfaceC2422co(this, str, r9, j) { // from class: com.google.android.play.core.assetpacks.ce

            /* renamed from: a */
            private final C2423cp f587a;

            /* renamed from: b */
            private final String f588b;

            /* renamed from: c */
            private final int f589c;

            /* renamed from: d */
            private final long f590d;

            /* JADX INFO: Access modifiers changed from: package-private */
            {
                this.f587a = this;
                this.f588b = str;
                this.f589c = r9;
                this.f590d = j;
            }

            @Override // com.google.android.play.core.assetpacks.InterfaceC2422co
            /* renamed from: a */
            public final Object mo943a() {
                this.f587a.m933b(this.f588b, this.f589c, this.f590d);
                return null;
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: a */
    public final boolean m940a(final Bundle bundle) {
        return ((Boolean) m939a(new InterfaceC2422co(this, bundle) { // from class: com.google.android.play.core.assetpacks.cc

            /* renamed from: a */
            private final C2423cp f583a;

            /* renamed from: b */
            private final Bundle f584b;

            /* JADX INFO: Access modifiers changed from: package-private */
            {
                this.f583a = this;
                this.f584b = bundle;
            }

            @Override // com.google.android.play.core.assetpacks.InterfaceC2422co
            /* renamed from: a */
            public final Object mo943a() {
                return this.f583a.m926d(this.f584b);
            }
        })).booleanValue();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: b */
    public final /* synthetic */ Map m932b(List list) {
        int r3;
        Map<String, C2420cm> m925d = m925d(list);
        HashMap hashMap = new HashMap();
        Iterator it = list.iterator();
        while (it.hasNext()) {
            String str = (String) it.next();
            final C2420cm c2420cm = m925d.get(str);
            if (c2420cm == null) {
                r3 = 8;
            } else {
                if (C2436db.m909a(c2420cm.f609c.f604c)) {
                    try {
                        c2420cm.f609c.f604c = 6;
                        this.f620e.m713a().execute(new Runnable(this, c2420cm) { // from class: com.google.android.play.core.assetpacks.cj

                            /* renamed from: a */
                            private final C2423cp f599a;

                            /* renamed from: b */
                            private final C2420cm f600b;

                            /* JADX INFO: Access modifiers changed from: package-private */
                            {
                                this.f599a = this;
                                this.f600b = c2420cm;
                            }

                            @Override // java.lang.Runnable
                            public final void run() {
                                this.f599a.m941a(this.f600b.f607a);
                            }
                        });
                        this.f619d.m949a(str);
                    } catch (C2402bv unused) {
                        f616a.m805c("Session %d with pack %s does not exist, no need to cancel.", Integer.valueOf(c2420cm.f607a), str);
                    }
                }
                r3 = c2420cm.f609c.f604c;
            }
            hashMap.put(str, Integer.valueOf(r3));
        }
        return hashMap;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: b */
    public final void m936b() {
        this.f622g.unlock();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: b */
    public final /* synthetic */ void m935b(int r2) {
        m924e(r2).f609c.f604c = 5;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: b */
    public final /* synthetic */ void m933b(String str, int r7, long j) {
        C2420cm c2420cm = m925d(Arrays.asList(str)).get(str);
        if (c2420cm == null || C2436db.m902b(c2420cm.f609c.f604c)) {
            f616a.m806b(String.format("Could not find pack %s while trying to complete it", str), new Object[0]);
        }
        this.f617b.m978f(str, r7, j);
        c2420cm.f609c.f604c = 4;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: b */
    public final boolean m934b(final Bundle bundle) {
        return ((Boolean) m939a(new InterfaceC2422co(this, bundle) { // from class: com.google.android.play.core.assetpacks.cd

            /* renamed from: a */
            private final C2423cp f585a;

            /* renamed from: b */
            private final Bundle f586b;

            /* JADX INFO: Access modifiers changed from: package-private */
            {
                this.f585a = this;
                this.f586b = bundle;
            }

            @Override // com.google.android.play.core.assetpacks.InterfaceC2422co
            /* renamed from: a */
            public final Object mo943a() {
                return this.f585a.m929c(this.f586b);
            }
        })).booleanValue();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: c */
    public final /* synthetic */ Boolean m929c(Bundle bundle) {
        boolean z;
        int r0 = bundle.getInt("session_id");
        if (r0 == 0) {
            return true;
        }
        Map<Integer, C2420cm> map = this.f621f;
        Integer valueOf = Integer.valueOf(r0);
        if (map.containsKey(valueOf)) {
            C2420cm c2420cm = this.f621f.get(valueOf);
            if (c2420cm.f609c.f604c == 6) {
                z = false;
            } else {
                z = !C2436db.m908a(c2420cm.f609c.f604c, bundle.getInt(C2561h.m699a("status", m923e(bundle))));
            }
            return Boolean.valueOf(z);
        }
        return true;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: c */
    public final Map<Integer, C2420cm> m931c() {
        return this.f621f;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: c */
    public final /* synthetic */ Map m928c(List list) {
        HashMap hashMap = new HashMap();
        for (C2420cm c2420cm : this.f621f.values()) {
            String str = c2420cm.f609c.f602a;
            if (list.contains(str)) {
                C2420cm c2420cm2 = (C2420cm) hashMap.get(str);
                if ((c2420cm2 == null ? -1 : c2420cm2.f607a) < c2420cm.f607a) {
                    hashMap.put(str, c2420cm);
                }
            }
        }
        return hashMap;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: c */
    public final /* synthetic */ void m930c(int r7) {
        C2420cm m924e = m924e(r7);
        if (!C2436db.m902b(m924e.f609c.f604c)) {
            throw new C2402bv(String.format("Could not safely delete session %d because it is not in a terminal state.", Integer.valueOf(r7)), r7);
        }
        C2382bb c2382bb = this.f617b;
        C2419cl c2419cl = m924e.f609c;
        c2382bb.m978f(c2419cl.f602a, m924e.f608b, c2419cl.f603b);
        C2419cl c2419cl2 = m924e.f609c;
        int r0 = c2419cl2.f604c;
        if (r0 == 5 || r0 == 6) {
            this.f617b.m987d(c2419cl2.f602a);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: d */
    public final /* synthetic */ Boolean m926d(Bundle bundle) {
        int r2 = bundle.getInt("session_id");
        boolean z = false;
        if (r2 == 0) {
            return false;
        }
        Map<Integer, C2420cm> map = this.f621f;
        Integer valueOf = Integer.valueOf(r2);
        if (map.containsKey(valueOf)) {
            C2420cm m924e = m924e(r2);
            int r7 = bundle.getInt(C2561h.m699a("status", m924e.f609c.f602a));
            if (C2436db.m908a(m924e.f609c.f604c, r7)) {
                f616a.m808a("Found stale update for session %s with status %d.", valueOf, Integer.valueOf(m924e.f609c.f604c));
                C2419cl c2419cl = m924e.f609c;
                String str = c2419cl.f602a;
                int r1 = c2419cl.f604c;
                if (r1 == 4) {
                    this.f618c.m713a().mo832a(r2, str);
                } else if (r1 == 5) {
                    this.f618c.m713a().mo833a(r2);
                } else if (r1 == 6) {
                    this.f618c.m713a().mo829a(Arrays.asList(str));
                }
            } else {
                m924e.f609c.f604c = r7;
                if (C2436db.m902b(r7)) {
                    m941a(r2);
                    this.f619d.m949a(m924e.f609c.f602a);
                } else {
                    List<C2421cn> list = m924e.f609c.f606e;
                    int size = list.size();
                    for (int r72 = 0; r72 < size; r72++) {
                        C2421cn c2421cn = list.get(r72);
                        ArrayList parcelableArrayList = bundle.getParcelableArrayList(C2561h.m698a("chunk_intents", m924e.f609c.f602a, c2421cn.f610a));
                        if (parcelableArrayList != null) {
                            for (int r11 = 0; r11 < parcelableArrayList.size(); r11++) {
                                if (parcelableArrayList.get(r11) != null && ((Intent) parcelableArrayList.get(r11)).getData() != null) {
                                    c2421cn.f613d.get(r11).f601a = true;
                                }
                            }
                        }
                    }
                }
            }
        } else {
            String m923e = m923e(bundle);
            long j = bundle.getLong(C2561h.m699a("pack_version", m923e));
            int r13 = bundle.getInt(C2561h.m699a("status", m923e));
            long j2 = bundle.getLong(C2561h.m699a("total_bytes_to_download", m923e));
            ArrayList<String> stringArrayList = bundle.getStringArrayList(C2561h.m699a("slice_ids", m923e));
            ArrayList arrayList = new ArrayList();
            Iterator it = m922e(stringArrayList).iterator();
            while (it.hasNext()) {
                String str2 = (String) it.next();
                ArrayList parcelableArrayList2 = bundle.getParcelableArrayList(C2561h.m698a("chunk_intents", m923e, str2));
                ArrayList arrayList2 = new ArrayList();
                for (Intent intent : m922e(parcelableArrayList2)) {
                    Iterator it2 = it;
                    if (intent != null) {
                        z = true;
                    }
                    arrayList2.add(new C2418ck(z));
                    it = it2;
                    z = false;
                }
                Iterator it3 = it;
                String string = bundle.getString(C2561h.m698a("uncompressed_hash_sha256", m923e, str2));
                long j3 = bundle.getLong(C2561h.m698a("uncompressed_size", m923e, str2));
                int r23 = bundle.getInt(C2561h.m698a("patch_format", m923e, str2), 0);
                arrayList.add(r23 != 0 ? new C2421cn(str2, string, j3, arrayList2, 0, r23) : new C2421cn(str2, string, j3, arrayList2, bundle.getInt(C2561h.m698a("compression_format", m923e, str2), 0), 0));
                it = it3;
                z = false;
            }
            this.f621f.put(Integer.valueOf(r2), new C2420cm(r2, bundle.getInt("app_version_code"), new C2419cl(m923e, j, r13, j2, arrayList)));
        }
        return true;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: d */
    public final void m927d(final int r2) {
        m939a(new InterfaceC2422co(this, r2) { // from class: com.google.android.play.core.assetpacks.cg

            /* renamed from: a */
            private final C2423cp f593a;

            /* renamed from: b */
            private final int f594b;

            /* JADX INFO: Access modifiers changed from: package-private */
            {
                this.f593a = this;
                this.f594b = r2;
            }

            @Override // com.google.android.play.core.assetpacks.InterfaceC2422co
            /* renamed from: a */
            public final Object mo943a() {
                this.f593a.m935b(this.f594b);
                return null;
            }
        });
    }
}
