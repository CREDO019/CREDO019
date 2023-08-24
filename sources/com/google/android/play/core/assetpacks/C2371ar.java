package com.google.android.play.core.assetpacks;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.ParcelFileDescriptor;
import android.os.Parcelable;
import com.google.android.play.core.internal.C2494af;
import com.google.android.play.core.internal.C2504ap;
import com.google.android.play.core.internal.C2540by;
import com.google.android.play.core.internal.InterfaceC2572s;
import com.google.android.play.core.splitcompat.C2608p;
import com.google.android.play.core.tasks.C2682i;
import com.google.android.play.core.tasks.OnSuccessListener;
import com.google.android.play.core.tasks.Task;
import com.google.android.play.core.tasks.Tasks;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicBoolean;

/* JADX INFO: Access modifiers changed from: package-private */
/* renamed from: com.google.android.play.core.assetpacks.ar */
/* loaded from: classes3.dex */
public final class C2371ar implements InterfaceC2478w {

    /* renamed from: a */
    private static final C2494af f431a = new C2494af("AssetPackServiceImpl");

    /* renamed from: b */
    private static final Intent f432b = new Intent("com.google.android.play.core.assetmoduleservice.BIND_ASSET_MODULE_SERVICE").setPackage("com.android.vending");

    /* renamed from: c */
    private final String f433c;

    /* renamed from: d */
    private final C2406bz f434d;

    /* renamed from: e */
    private C2504ap<InterfaceC2572s> f435e;

    /* renamed from: f */
    private C2504ap<InterfaceC2572s> f436f;

    /* renamed from: g */
    private final AtomicBoolean f437g = new AtomicBoolean();

    /* JADX INFO: Access modifiers changed from: package-private */
    public C2371ar(Context context, C2406bz c2406bz) {
        this.f433c = context.getPackageName();
        this.f434d = c2406bz;
        if (C2540by.m723a(context)) {
            Context m576a = C2608p.m576a(context);
            C2494af c2494af = f431a;
            Intent intent = f432b;
            this.f435e = new C2504ap<>(m576a, c2494af, "AssetPackService", intent, C2479x.f785a);
            this.f436f = new C2504ap<>(C2608p.m576a(context), c2494af, "AssetPackService-keepAlive", intent, C2480y.f786a);
        }
        f431a.m808a("AssetPackService initiated.", new Object[0]);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: a */
    public static /* synthetic */ ArrayList m1027a(Collection collection) {
        ArrayList arrayList = new ArrayList(collection.size());
        Iterator it = collection.iterator();
        while (it.hasNext()) {
            Bundle bundle = new Bundle();
            bundle.putString("module_name", (String) it.next());
            arrayList.add(bundle);
        }
        return arrayList;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: a */
    public static /* synthetic */ List m1028a(C2371ar c2371ar, List list) {
        ArrayList arrayList = new ArrayList();
        int size = list.size();
        for (int r3 = 0; r3 < size; r3++) {
            AssetPackState next = AssetPackStates.m1035a((Bundle) list.get(r3), c2371ar.f434d).packStates().values().iterator().next();
            if (next == null) {
                f431a.m806b("onGetSessionStates: Bundle contained no pack.", new Object[0]);
            }
            if (C2436db.m909a(next.status())) {
                arrayList.add(next.name());
            }
        }
        return arrayList;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: a */
    public final void m1031a(int r11, String str, int r13) {
        if (this.f435e == null) {
            throw new C2402bv("The Play Store app is not installed or is an unofficial version.", r11);
        }
        f431a.m805c("notifyModuleCompleted", new Object[0]);
        C2682i c2682i = new C2682i();
        this.f435e.m800a(new C2360ag(this, c2682i, r11, str, c2682i, r13));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: b */
    public static /* synthetic */ Bundle m1022b(Map map) {
        Bundle m1014e = m1014e();
        ArrayList<? extends Parcelable> arrayList = new ArrayList<>();
        for (Map.Entry entry : map.entrySet()) {
            Bundle bundle = new Bundle();
            bundle.putString("installed_asset_module_name", (String) entry.getKey());
            bundle.putLong("installed_asset_module_version", ((Long) entry.getValue()).longValue());
            arrayList.add(bundle);
        }
        m1014e.putParcelableArrayList("installed_asset_module", arrayList);
        return m1014e;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: c */
    public static Bundle m1020c(int r2) {
        Bundle bundle = new Bundle();
        bundle.putInt("session_id", r2);
        return bundle;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: c */
    public static Bundle m1019c(int r1, String str) {
        Bundle m1020c = m1020c(r1);
        m1020c.putString("module_name", str);
        return m1020c;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: c */
    public static /* synthetic */ Bundle m1018c(int r0, String str, String str2, int r3) {
        Bundle m1019c = m1019c(r0, str);
        m1019c.putString("slice_id", str2);
        m1019c.putInt("chunk_number", r3);
        return m1019c;
    }

    /* renamed from: d */
    private static <T> Task<T> m1016d() {
        f431a.m806b("onError(%d)", -11);
        return Tasks.m469a((Exception) new AssetPackException(-11));
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: e */
    public static Bundle m1014e() {
        Bundle bundle = new Bundle();
        bundle.putInt("playcore_version_code", 10800);
        ArrayList<Integer> arrayList = new ArrayList<>();
        arrayList.add(0);
        arrayList.add(1);
        bundle.putIntegerArrayList("supported_compression_formats", arrayList);
        bundle.putIntegerArrayList("supported_patch_formats", new ArrayList<>());
        return bundle;
    }

    @Override // com.google.android.play.core.assetpacks.InterfaceC2478w
    /* renamed from: a */
    public final Task<AssetPackStates> mo828a(List<String> list, InterfaceC2379az interfaceC2379az, Map<String, Long> map) {
        if (this.f435e == null) {
            return m1016d();
        }
        f431a.m805c("getPackStates(%s)", list);
        C2682i c2682i = new C2682i();
        this.f435e.m800a(new C2358ae(this, c2682i, list, map, c2682i, interfaceC2379az));
        return c2682i.m458a();
    }

    @Override // com.google.android.play.core.assetpacks.InterfaceC2478w
    /* renamed from: a */
    public final Task<AssetPackStates> mo827a(List<String> list, List<String> list2, Map<String, Long> map) {
        if (this.f435e == null) {
            return m1016d();
        }
        f431a.m805c("startDownload(%s)", list2);
        C2682i c2682i = new C2682i();
        this.f435e.m800a(new C2355ab(this, c2682i, list2, map, c2682i, list));
        c2682i.m458a().addOnSuccessListener(new OnSuccessListener(this) { // from class: com.google.android.play.core.assetpacks.z

            /* renamed from: a */
            private final C2371ar f787a;

            /* JADX INFO: Access modifiers changed from: package-private */
            {
                this.f787a = this;
            }

            @Override // com.google.android.play.core.tasks.OnSuccessListener
            public final void onSuccess(Object obj) {
                AssetPackStates assetPackStates = (AssetPackStates) obj;
                this.f787a.mo834a();
            }
        });
        return c2682i.m458a();
    }

    @Override // com.google.android.play.core.assetpacks.InterfaceC2478w
    /* renamed from: a */
    public final Task<List<String>> mo826a(Map<String, Long> map) {
        if (this.f435e == null) {
            return m1016d();
        }
        f431a.m805c("syncPacks", new Object[0]);
        C2682i c2682i = new C2682i();
        this.f435e.m800a(new C2357ad(this, c2682i, map, c2682i));
        return c2682i.m458a();
    }

    @Override // com.google.android.play.core.assetpacks.InterfaceC2478w
    /* renamed from: a */
    public final synchronized void mo834a() {
        if (this.f436f == null) {
            f431a.m804d("Keep alive connection manager is not initialized.", new Object[0]);
            return;
        }
        C2494af c2494af = f431a;
        c2494af.m805c("keepAlive", new Object[0]);
        if (!this.f437g.compareAndSet(false, true)) {
            c2494af.m805c("Service is already kept alive.", new Object[0]);
            return;
        }
        C2682i c2682i = new C2682i();
        this.f436f.m800a(new C2363aj(this, c2682i, c2682i));
    }

    @Override // com.google.android.play.core.assetpacks.InterfaceC2478w
    /* renamed from: a */
    public final void mo833a(int r4) {
        if (this.f435e == null) {
            throw new C2402bv("The Play Store app is not installed or is an unofficial version.", r4);
        }
        f431a.m805c("notifySessionFailed", new Object[0]);
        C2682i c2682i = new C2682i();
        this.f435e.m800a(new C2361ah(this, c2682i, r4, c2682i));
    }

    @Override // com.google.android.play.core.assetpacks.InterfaceC2478w
    /* renamed from: a */
    public final void mo832a(int r2, String str) {
        m1031a(r2, str, 10);
    }

    @Override // com.google.android.play.core.assetpacks.InterfaceC2478w
    /* renamed from: a */
    public final void mo831a(int r12, String str, String str2, int r15) {
        if (this.f435e == null) {
            throw new C2402bv("The Play Store app is not installed or is an unofficial version.", r12);
        }
        f431a.m805c("notifyChunkTransferred", new Object[0]);
        C2682i c2682i = new C2682i();
        this.f435e.m800a(new C2359af(this, c2682i, r12, str, str2, r15, c2682i));
    }

    @Override // com.google.android.play.core.assetpacks.InterfaceC2478w
    /* renamed from: a */
    public final void mo830a(String str) {
        if (this.f435e == null) {
            return;
        }
        f431a.m805c("removePack(%s)", str);
        C2682i c2682i = new C2682i();
        this.f435e.m800a(new C2354aa(this, c2682i, str, c2682i));
    }

    @Override // com.google.android.play.core.assetpacks.InterfaceC2478w
    /* renamed from: a */
    public final void mo829a(List<String> list) {
        if (this.f435e == null) {
            return;
        }
        f431a.m805c("cancelDownloads(%s)", list);
        C2682i c2682i = new C2682i();
        this.f435e.m800a(new C2356ac(this, c2682i, list, c2682i));
    }

    @Override // com.google.android.play.core.assetpacks.InterfaceC2478w
    /* renamed from: b */
    public final Task<ParcelFileDescriptor> mo825b(int r12, String str, String str2, int r15) {
        if (this.f435e == null) {
            return m1016d();
        }
        f431a.m805c("getChunkFileDescriptor(%s, %s, %d, session=%d)", str, str2, Integer.valueOf(r15), Integer.valueOf(r12));
        C2682i c2682i = new C2682i();
        this.f435e.m800a(new C2362ai(this, c2682i, r12, str, str2, r15, c2682i));
        return c2682i.m458a();
    }
}
