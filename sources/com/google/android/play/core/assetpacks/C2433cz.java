package com.google.android.play.core.assetpacks;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.ParcelFileDescriptor;
import android.os.Parcelable;
import com.google.android.play.core.common.LocalTestingException;
import com.google.android.play.core.internal.C2494af;
import com.google.android.play.core.internal.C2510av;
import com.google.android.play.core.internal.C2561h;
import com.google.android.play.core.internal.InterfaceC2552cj;
import com.google.android.play.core.tasks.C2682i;
import com.google.android.play.core.tasks.Task;
import com.google.android.play.core.tasks.Tasks;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FilenameFilter;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Executor;
import java.util.concurrent.atomic.AtomicInteger;

/* JADX INFO: Access modifiers changed from: package-private */
/* renamed from: com.google.android.play.core.assetpacks.cz */
/* loaded from: classes3.dex */
public final class C2433cz implements InterfaceC2478w {

    /* renamed from: a */
    private static final C2494af f650a = new C2494af("FakeAssetPackService");

    /* renamed from: h */
    private static final AtomicInteger f651h = new AtomicInteger(1);

    /* renamed from: b */
    private final String f652b;

    /* renamed from: c */
    private final C2376aw f653c;

    /* renamed from: d */
    private final C2406bz f654d;

    /* renamed from: e */
    private final Context f655e;

    /* renamed from: f */
    private final C2446dl f656f;

    /* renamed from: g */
    private final InterfaceC2552cj<Executor> f657g;

    /* renamed from: i */
    private final Handler f658i = new Handler(Looper.getMainLooper());

    /* JADX INFO: Access modifiers changed from: package-private */
    public C2433cz(File file, C2376aw c2376aw, C2406bz c2406bz, Context context, C2446dl c2446dl, InterfaceC2552cj<Executor> interfaceC2552cj) {
        this.f652b = file.getAbsolutePath();
        this.f653c = c2376aw;
        this.f654d = c2406bz;
        this.f655e = context;
        this.f656f = c2446dl;
        this.f657g = interfaceC2552cj;
    }

    /* renamed from: a */
    static long m918a(int r2, long j) {
        if (r2 != 2) {
            if (r2 == 3 || r2 == 4) {
                return j;
            }
            return 0L;
        }
        return j / 2;
    }

    /* renamed from: a */
    private final AssetPackState m914a(String str, int r16) throws LocalTestingException {
        long j = 0;
        for (File file : m910b(str)) {
            j += file.length();
        }
        return AssetPackState.m1037a(str, r16, 0, m918a(r16, j), j, this.f654d.m947b(str));
    }

    /* renamed from: a */
    private static String m915a(File file) throws LocalTestingException {
        try {
            return C2436db.m905a(Arrays.asList(file));
        } catch (IOException e) {
            throw new LocalTestingException(String.format("Could not digest file: %s.", file), e);
        } catch (NoSuchAlgorithmException e2) {
            throw new LocalTestingException("SHA256 algorithm not supported.", e2);
        }
    }

    /* renamed from: a */
    private final void m917a(int r13, String str, int r15) throws LocalTestingException {
        Bundle bundle = new Bundle();
        bundle.putInt("app_version_code", this.f656f.m888a());
        bundle.putInt("session_id", r13);
        File[] m910b = m910b(str);
        ArrayList<String> arrayList = new ArrayList<>();
        long j = 0;
        for (File file : m910b) {
            j += file.length();
            ArrayList<? extends Parcelable> arrayList2 = new ArrayList<>();
            arrayList2.add(r15 == 3 ? new Intent().setData(Uri.EMPTY) : null);
            String m775a = C2510av.m775a(file);
            bundle.putParcelableArrayList(C2561h.m698a("chunk_intents", str, m775a), arrayList2);
            bundle.putString(C2561h.m698a("uncompressed_hash_sha256", str, m775a), m915a(file));
            bundle.putLong(C2561h.m698a("uncompressed_size", str, m775a), file.length());
            arrayList.add(m775a);
        }
        bundle.putStringArrayList(C2561h.m699a("slice_ids", str), arrayList);
        bundle.putLong(C2561h.m699a("pack_version", str), this.f656f.m888a());
        bundle.putInt(C2561h.m699a("status", str), r15);
        bundle.putInt(C2561h.m699a("error_code", str), 0);
        bundle.putLong(C2561h.m699a("bytes_downloaded", str), m918a(r15, j));
        bundle.putLong(C2561h.m699a("total_bytes_to_download", str), j);
        bundle.putStringArrayList("pack_names", new ArrayList<>(Arrays.asList(str)));
        bundle.putLong("bytes_downloaded", m918a(r15, j));
        bundle.putLong("total_bytes_to_download", j);
        final Intent putExtra = new Intent("com.google.android.play.core.assetpacks.receiver.ACTION_SESSION_UPDATE").putExtra("com.google.android.play.core.assetpacks.receiver.EXTRA_SESSION_STATE", bundle);
        this.f658i.post(new Runnable(this, putExtra) { // from class: com.google.android.play.core.assetpacks.cy

            /* renamed from: a */
            private final C2433cz f648a;

            /* renamed from: b */
            private final Intent f649b;

            /* JADX INFO: Access modifiers changed from: package-private */
            {
                this.f648a = this;
                this.f649b = putExtra;
            }

            @Override // java.lang.Runnable
            public final void run() {
                this.f648a.m916a(this.f649b);
            }
        });
    }

    /* renamed from: b */
    private final File[] m910b(final String str) throws LocalTestingException {
        File file = new File(this.f652b);
        if (file.isDirectory()) {
            File[] listFiles = file.listFiles(new FilenameFilter(str) { // from class: com.google.android.play.core.assetpacks.cx

                /* renamed from: a */
                private final String f647a;

                /* JADX INFO: Access modifiers changed from: package-private */
                {
                    this.f647a = str;
                }

                @Override // java.io.FilenameFilter
                public final boolean accept(File file2, String str2) {
                    return str2.startsWith(String.valueOf(this.f647a).concat("-")) && str2.endsWith(".apk");
                }
            });
            if (listFiles != null) {
                if (listFiles.length != 0) {
                    for (File file2 : listFiles) {
                        if (C2510av.m775a(file2).equals(str)) {
                            return listFiles;
                        }
                    }
                    throw new LocalTestingException(String.format("No master slice available for pack '%s'.", str));
                }
                throw new LocalTestingException(String.format("No APKs available for pack '%s'.", str));
            }
            throw new LocalTestingException(String.format("Failed fetching APKs for pack '%s'.", str));
        }
        throw new LocalTestingException(String.format("Local testing directory '%s' not found.", file));
    }

    @Override // com.google.android.play.core.assetpacks.InterfaceC2478w
    /* renamed from: a */
    public final Task<AssetPackStates> mo828a(final List<String> list, final InterfaceC2379az interfaceC2379az, Map<String, Long> map) {
        f650a.m805c("getPackStates(%s)", list);
        final C2682i c2682i = new C2682i();
        this.f657g.m713a().execute(new Runnable(this, list, interfaceC2379az, c2682i) { // from class: com.google.android.play.core.assetpacks.cv

            /* renamed from: a */
            private final C2433cz f640a;

            /* renamed from: b */
            private final List f641b;

            /* renamed from: c */
            private final InterfaceC2379az f642c;

            /* renamed from: d */
            private final C2682i f643d;

            /* JADX INFO: Access modifiers changed from: package-private */
            {
                this.f640a = this;
                this.f641b = list;
                this.f642c = interfaceC2379az;
                this.f643d = c2682i;
            }

            @Override // java.lang.Runnable
            public final void run() {
                this.f640a.m913a(this.f641b, this.f642c, this.f643d);
            }
        });
        return c2682i.m458a();
    }

    @Override // com.google.android.play.core.assetpacks.InterfaceC2478w
    /* renamed from: a */
    public final Task<AssetPackStates> mo827a(final List<String> list, final List<String> list2, Map<String, Long> map) {
        f650a.m805c("startDownload(%s)", list2);
        final C2682i c2682i = new C2682i();
        this.f657g.m713a().execute(new Runnable(this, list2, c2682i, list) { // from class: com.google.android.play.core.assetpacks.cu

            /* renamed from: a */
            private final C2433cz f636a;

            /* renamed from: b */
            private final List f637b;

            /* renamed from: c */
            private final C2682i f638c;

            /* renamed from: d */
            private final List f639d;

            /* JADX INFO: Access modifiers changed from: package-private */
            {
                this.f636a = this;
                this.f637b = list2;
                this.f638c = c2682i;
                this.f639d = list;
            }

            @Override // java.lang.Runnable
            public final void run() {
                this.f636a.m912a(this.f637b, this.f638c, this.f639d);
            }
        });
        return c2682i.m458a();
    }

    @Override // com.google.android.play.core.assetpacks.InterfaceC2478w
    /* renamed from: a */
    public final Task<List<String>> mo826a(Map<String, Long> map) {
        f650a.m805c("syncPacks()", new Object[0]);
        return Tasks.m468a(new ArrayList());
    }

    @Override // com.google.android.play.core.assetpacks.InterfaceC2478w
    /* renamed from: a */
    public final void mo834a() {
        f650a.m805c("keepAlive", new Object[0]);
    }

    @Override // com.google.android.play.core.assetpacks.InterfaceC2478w
    /* renamed from: a */
    public final void mo833a(int r3) {
        f650a.m805c("notifySessionFailed", new Object[0]);
    }

    @Override // com.google.android.play.core.assetpacks.InterfaceC2478w
    /* renamed from: a */
    public final void mo832a(final int r4, final String str) {
        f650a.m805c("notifyModuleCompleted", new Object[0]);
        this.f657g.m713a().execute(new Runnable(this, r4, str) { // from class: com.google.android.play.core.assetpacks.cw

            /* renamed from: a */
            private final C2433cz f644a;

            /* renamed from: b */
            private final int f645b;

            /* renamed from: c */
            private final String f646c;

            /* JADX INFO: Access modifiers changed from: package-private */
            {
                this.f644a = this;
                this.f645b = r4;
                this.f646c = str;
            }

            @Override // java.lang.Runnable
            public final void run() {
                this.f644a.m911b(this.f645b, this.f646c);
            }
        });
    }

    @Override // com.google.android.play.core.assetpacks.InterfaceC2478w
    /* renamed from: a */
    public final void mo831a(int r1, String str, String str2, int r4) {
        f650a.m805c("notifyChunkTransferred", new Object[0]);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: a */
    public final /* synthetic */ void m916a(Intent intent) {
        this.f653c.mo507a(this.f655e, intent);
    }

    @Override // com.google.android.play.core.assetpacks.InterfaceC2478w
    /* renamed from: a */
    public final void mo830a(String str) {
        f650a.m805c("removePack(%s)", str);
    }

    @Override // com.google.android.play.core.assetpacks.InterfaceC2478w
    /* renamed from: a */
    public final void mo829a(List<String> list) {
        f650a.m805c("cancelDownload(%s)", list);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: a */
    public final /* synthetic */ void m913a(List list, InterfaceC2379az interfaceC2379az, C2682i c2682i) {
        HashMap hashMap = new HashMap();
        Iterator it = list.iterator();
        long j = 0;
        while (it.hasNext()) {
            String str = (String) it.next();
            try {
                AssetPackState m914a = m914a(str, interfaceC2379az.mo946a(8, str));
                j += m914a.totalBytesToDownload();
                hashMap.put(str, m914a);
            } catch (LocalTestingException e) {
                c2682i.m457a((Exception) e);
                return;
            }
        }
        c2682i.m456a((C2682i) AssetPackStates.m1036a(j, hashMap));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: a */
    public final /* synthetic */ void m912a(List list, C2682i c2682i, List list2) {
        HashMap hashMap = new HashMap();
        int size = list.size();
        long j = 0;
        for (int r8 = 0; r8 < size; r8++) {
            String str = (String) list.get(r8);
            try {
                AssetPackState m914a = m914a(str, 1);
                j += m914a.totalBytesToDownload();
                hashMap.put(str, m914a);
            } catch (LocalTestingException e) {
                c2682i.m457a((Exception) e);
                return;
            }
        }
        int size2 = list.size();
        for (int r82 = 0; r82 < size2; r82++) {
            String str2 = (String) list.get(r82);
            try {
                int andIncrement = f651h.getAndIncrement();
                m917a(andIncrement, str2, 1);
                m917a(andIncrement, str2, 2);
                m917a(andIncrement, str2, 3);
            } catch (LocalTestingException e2) {
                c2682i.m457a((Exception) e2);
                return;
            }
        }
        int size3 = list2.size();
        for (int r5 = 0; r5 < size3; r5++) {
            String str3 = (String) list2.get(r5);
            hashMap.put(str3, AssetPackState.m1037a(str3, 4, 0, 0L, 0L, 0.0d));
        }
        c2682i.m456a((C2682i) AssetPackStates.m1036a(j, hashMap));
    }

    @Override // com.google.android.play.core.assetpacks.InterfaceC2478w
    /* renamed from: b */
    public final Task<ParcelFileDescriptor> mo825b(int r7, String str, String str2, int r10) {
        File[] m910b;
        int r2;
        f650a.m805c("getChunkFileDescriptor(session=%d, %s, %s, %d)", Integer.valueOf(r7), str, str2, Integer.valueOf(r10));
        C2682i c2682i = new C2682i();
        try {
        } catch (LocalTestingException e) {
            f650a.m804d("getChunkFileDescriptor failed", e);
            c2682i.m457a((Exception) e);
        } catch (FileNotFoundException e2) {
            f650a.m804d("getChunkFileDescriptor failed", e2);
            c2682i.m457a((Exception) new LocalTestingException("Asset Slice file not found.", e2));
        }
        for (File file : m910b(str)) {
            if (C2510av.m775a(file).equals(str2)) {
                c2682i.m456a((C2682i) ParcelFileDescriptor.open(file, 268435456));
                return c2682i.m458a();
            }
        }
        throw new LocalTestingException(String.format("Local testing slice for '%s' not found.", str2));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: b */
    public final /* synthetic */ void m911b(int r3, String str) {
        try {
            m917a(r3, str, 4);
        } catch (LocalTestingException e) {
            f650a.m804d("notifyModuleCompleted failed", e);
        }
    }
}
