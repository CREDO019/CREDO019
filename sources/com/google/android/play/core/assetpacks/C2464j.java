package com.google.android.play.core.assetpacks;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import com.google.android.play.core.common.PlayCoreDialogWrapperActivity;
import com.google.android.play.core.internal.C2494af;
import com.google.android.play.core.internal.C2561h;
import com.google.android.play.core.internal.InterfaceC2552cj;
import com.google.android.play.core.listener.StateUpdatedListener;
import com.google.android.play.core.splitinstall.C2652p;
import com.google.android.play.core.tasks.C2682i;
import com.google.android.play.core.tasks.Task;
import com.google.android.play.core.tasks.Tasks;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Executor;

/* JADX INFO: Access modifiers changed from: package-private */
/* renamed from: com.google.android.play.core.assetpacks.j */
/* loaded from: classes3.dex */
public final class C2464j implements AssetPackManager {

    /* renamed from: a */
    private static final C2494af f752a = new C2494af("AssetPackManager");

    /* renamed from: b */
    private final C2382bb f753b;

    /* renamed from: c */
    private final InterfaceC2552cj<InterfaceC2478w> f754c;

    /* renamed from: d */
    private final C2376aw f755d;

    /* renamed from: e */
    private final C2652p f756e;

    /* renamed from: f */
    private final C2423cp f757f;

    /* renamed from: g */
    private final C2406bz f758g;

    /* renamed from: h */
    private final C2394bn f759h;

    /* renamed from: i */
    private final InterfaceC2552cj<Executor> f760i;

    /* renamed from: j */
    private final Handler f761j = new Handler(Looper.getMainLooper());

    /* renamed from: k */
    private boolean f762k;

    /* JADX INFO: Access modifiers changed from: package-private */
    public C2464j(C2382bb c2382bb, InterfaceC2552cj<InterfaceC2478w> interfaceC2552cj, C2376aw c2376aw, C2652p c2652p, C2423cp c2423cp, C2406bz c2406bz, C2394bn c2394bn, InterfaceC2552cj<Executor> interfaceC2552cj2) {
        this.f753b = c2382bb;
        this.f754c = interfaceC2552cj;
        this.f755d = c2376aw;
        this.f756e = c2652p;
        this.f757f = c2423cp;
        this.f758g = c2406bz;
        this.f759h = c2394bn;
        this.f760i = interfaceC2552cj2;
    }

    /* renamed from: c */
    private final void m845c() {
        this.f760i.m713a().execute(new Runnable(this) { // from class: com.google.android.play.core.assetpacks.e

            /* renamed from: a */
            private final C2464j f746a;

            /* JADX INFO: Access modifiers changed from: package-private */
            {
                this.f746a = this;
            }

            @Override // java.lang.Runnable
            public final void run() {
                this.f746a.m846b();
            }
        });
    }

    /* renamed from: d */
    private final void m844d() {
        this.f760i.m713a().execute(new RunnableC2460f(this));
        this.f762k = true;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: a */
    public final int m851a(int r3, String str) {
        if (this.f753b.m1006a(str) || r3 != 4) {
            if (!this.f753b.m1006a(str) || r3 == 4) {
                return r3;
            }
            return 4;
        }
        return 8;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: a */
    public final /* synthetic */ void m852a() {
        this.f753b.m988d();
        this.f753b.m993c();
        this.f753b.m984e();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: a */
    public final /* synthetic */ void m848a(String str, C2682i c2682i) {
        if (!this.f753b.m987d(str)) {
            c2682i.m457a((Exception) new IOException(String.format("Failed to remove pack %s.", str)));
            return;
        }
        c2682i.m456a((C2682i) null);
        this.f754c.m713a().mo830a(str);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: a */
    public final void m847a(boolean z) {
        boolean m638b = this.f755d.m638b();
        this.f755d.m639a(z);
        if (!z || m638b) {
            return;
        }
        m845c();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: b */
    public final /* synthetic */ void m846b() {
        C2382bb c2382bb = this.f753b;
        c2382bb.getClass();
        this.f754c.m713a().mo826a(this.f753b.m998b()).addOnSuccessListener(this.f760i.m713a(), C2461g.m853a(c2382bb)).addOnFailureListener(this.f760i.m713a(), C2462h.f749a);
    }

    @Override // com.google.android.play.core.assetpacks.AssetPackManager
    public final AssetPackStates cancel(List<String> list) {
        Map<String, Integer> m937a = this.f757f.m937a(list);
        HashMap hashMap = new HashMap();
        for (String str : list) {
            Integer num = m937a.get(str);
            hashMap.put(str, AssetPackState.m1037a(str, num == null ? 0 : num.intValue(), 0, 0L, 0L, 0.0d));
        }
        this.f754c.m713a().mo829a(list);
        return AssetPackStates.m1036a(0L, hashMap);
    }

    @Override // com.google.android.play.core.assetpacks.AssetPackManager
    public final void clearListeners() {
        this.f755d.m642a();
    }

    @Override // com.google.android.play.core.assetpacks.AssetPackManager
    public final Task<AssetPackStates> fetch(List<String> list) {
        Map<String, Long> m998b = this.f753b.m998b();
        ArrayList arrayList = new ArrayList(list);
        arrayList.removeAll(m998b.keySet());
        ArrayList arrayList2 = new ArrayList(list);
        arrayList2.removeAll(arrayList);
        if (arrayList.isEmpty()) {
            Bundle bundle = new Bundle();
            bundle.putInt("session_id", 0);
            bundle.putInt("error_code", 0);
            for (String str : list) {
                bundle.putInt(C2561h.m699a("status", str), 4);
                bundle.putInt(C2561h.m699a("error_code", str), 0);
                bundle.putLong(C2561h.m699a("total_bytes_to_download", str), 0L);
                bundle.putLong(C2561h.m699a("bytes_downloaded", str), 0L);
            }
            bundle.putStringArrayList("pack_names", new ArrayList<>(list));
            bundle.putLong("total_bytes_to_download", 0L);
            bundle.putLong("bytes_downloaded", 0L);
            return Tasks.m468a(AssetPackStates.m1035a(bundle, this.f758g));
        }
        return this.f754c.m713a().mo827a(arrayList2, arrayList, m998b);
    }

    @Override // com.google.android.play.core.assetpacks.AssetPackManager
    public final AssetLocation getAssetLocation(String str, String str2) {
        AssetPackLocation m996b;
        if (!this.f762k) {
            this.f760i.m713a().execute(new RunnableC2460f(this));
            this.f762k = true;
        }
        if (this.f753b.m1006a(str)) {
            try {
                m996b = this.f753b.m996b(str);
            } catch (IOException unused) {
            }
        } else {
            if (this.f756e.m516a().contains(str)) {
                m996b = AssetPackLocation.m1040a();
            }
            m996b = null;
        }
        if (m996b == null) {
            return null;
        }
        if (m996b.packStorageMethod() == 1) {
            return this.f753b.m1001a(str, str2);
        }
        if (m996b.packStorageMethod() == 0) {
            return this.f753b.m1000a(str, str2, m996b);
        }
        f752a.m808a("The asset %s is not present in Asset Pack %s", str2, str);
        return null;
    }

    @Override // com.google.android.play.core.assetpacks.AssetPackManager
    public final AssetPackLocation getPackLocation(String str) {
        if (!this.f762k) {
            m844d();
        }
        if (this.f753b.m1006a(str)) {
            try {
                return this.f753b.m996b(str);
            } catch (IOException unused) {
                return null;
            }
        } else if (this.f756e.m516a().contains(str)) {
            return AssetPackLocation.m1040a();
        } else {
            return null;
        }
    }

    @Override // com.google.android.play.core.assetpacks.AssetPackManager
    public final Map<String, AssetPackLocation> getPackLocations() {
        Map<String, AssetPackLocation> m1009a = this.f753b.m1009a();
        HashMap hashMap = new HashMap();
        for (String str : this.f756e.m516a()) {
            hashMap.put(str, AssetPackLocation.m1040a());
        }
        m1009a.putAll(hashMap);
        return m1009a;
    }

    @Override // com.google.android.play.core.assetpacks.AssetPackManager
    public final Task<AssetPackStates> getPackStates(List<String> list) {
        return this.f754c.m713a().mo828a(list, new InterfaceC2379az(this) { // from class: com.google.android.play.core.assetpacks.c

            /* renamed from: a */
            private final C2464j f581a;

            /* JADX INFO: Access modifiers changed from: package-private */
            {
                this.f581a = this;
            }

            @Override // com.google.android.play.core.assetpacks.InterfaceC2379az
            /* renamed from: a */
            public final int mo946a(int r2, String str) {
                return this.f581a.m851a(r2, str);
            }
        }, this.f753b.m998b());
    }

    @Override // com.google.android.play.core.assetpacks.AssetPackManager
    public final synchronized void registerListener(AssetPackStateUpdateListener assetPackStateUpdateListener) {
        boolean m638b = this.f755d.m638b();
        this.f755d.m641a((StateUpdatedListener) assetPackStateUpdateListener);
        if (m638b) {
            return;
        }
        m845c();
    }

    @Override // com.google.android.play.core.assetpacks.AssetPackManager
    public final Task<Void> removePack(final String str) {
        final C2682i c2682i = new C2682i();
        this.f760i.m713a().execute(new Runnable(this, str, c2682i) { // from class: com.google.android.play.core.assetpacks.d

            /* renamed from: a */
            private final C2464j f659a;

            /* renamed from: b */
            private final String f660b;

            /* renamed from: c */
            private final C2682i f661c;

            /* JADX INFO: Access modifiers changed from: package-private */
            {
                this.f659a = this;
                this.f660b = str;
                this.f661c = c2682i;
            }

            @Override // java.lang.Runnable
            public final void run() {
                this.f659a.m848a(this.f660b, this.f661c);
            }
        });
        return c2682i.m458a();
    }

    @Override // com.google.android.play.core.assetpacks.AssetPackManager
    public final Task<Integer> showCellularDataConfirmation(Activity activity) {
        if (this.f759h.m962a() == null) {
            return Tasks.m469a((Exception) new AssetPackException(-12));
        }
        Intent intent = new Intent(activity, PlayCoreDialogWrapperActivity.class);
        intent.putExtra("confirmation_intent", this.f759h.m962a());
        C2682i c2682i = new C2682i();
        intent.putExtra("result_receiver", new ResultReceiverC2463i(this, this.f761j, c2682i));
        activity.startActivity(intent);
        return c2682i.m458a();
    }

    @Override // com.google.android.play.core.assetpacks.AssetPackManager
    public final void unregisterListener(AssetPackStateUpdateListener assetPackStateUpdateListener) {
        this.f755d.m637b(assetPackStateUpdateListener);
    }
}
