package com.google.android.play.core.splitinstall;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import com.amplitude.api.Constants;
import com.google.android.play.core.internal.C2494af;
import com.google.android.play.core.internal.C2504ap;
import com.google.android.play.core.internal.C2540by;
import com.google.android.play.core.internal.InterfaceC2536bu;
import com.google.android.play.core.splitcompat.C2608p;
import com.google.android.play.core.tasks.C2682i;
import com.google.android.play.core.tasks.Task;
import com.google.android.play.core.tasks.Tasks;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

/* JADX INFO: Access modifiers changed from: package-private */
/* renamed from: com.google.android.play.core.splitinstall.av */
/* loaded from: classes3.dex */
public final class C2632av {

    /* renamed from: b */
    private static final C2494af f976b = new C2494af("SplitInstallService");

    /* renamed from: c */
    private static final Intent f977c = new Intent("com.google.android.play.core.splitinstall.BIND_SPLIT_INSTALL_SERVICE").setPackage("com.android.vending");

    /* renamed from: a */
    C2504ap<InterfaceC2536bu> f978a;

    /* renamed from: d */
    private final String f979d;

    public C2632av(Context context) {
        this.f979d = context.getPackageName();
        if (C2540by.m723a(context)) {
            this.f978a = new C2504ap<>(C2608p.m576a(context), f976b, "SplitInstallService", f977c, C2614ad.f949a);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: a */
    public static /* synthetic */ ArrayList m549a(Collection collection) {
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
    /* renamed from: b */
    public static /* synthetic */ Bundle m546b() {
        Bundle bundle = new Bundle();
        bundle.putInt("playcore_version_code", 10800);
        return bundle;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Multi-variable type inference failed */
    /* renamed from: b */
    public static /* synthetic */ ArrayList m544b(Collection collection) {
        ArrayList arrayList = new ArrayList(collection.size());
        int size = collection.size();
        for (int r2 = 0; r2 < size; r2++) {
            Bundle bundle = new Bundle();
            bundle.putString(Constants.AMP_TRACKING_OPTION_LANGUAGE, (String) collection.get(r2));
            arrayList.add(bundle);
        }
        return arrayList;
    }

    /* renamed from: d */
    private static <T> Task<T> m540d() {
        f976b.m806b("onError(%d)", -14);
        return Tasks.m469a((Exception) new SplitInstallException(-14));
    }

    /* renamed from: a */
    public final Task<List<SplitInstallSessionState>> m552a() {
        if (this.f978a == null) {
            return m540d();
        }
        f976b.m805c("getSessionStates", new Object[0]);
        C2682i c2682i = new C2682i();
        this.f978a.m800a(new C2621ak(this, c2682i, c2682i));
        return c2682i.m458a();
    }

    /* renamed from: a */
    public final Task<SplitInstallSessionState> m551a(int r5) {
        if (this.f978a == null) {
            return m540d();
        }
        f976b.m805c("getSessionState(%d)", Integer.valueOf(r5));
        C2682i c2682i = new C2682i();
        this.f978a.m800a(new C2620aj(this, c2682i, r5, c2682i));
        return c2682i.m458a();
    }

    /* renamed from: a */
    public final Task<Integer> m548a(Collection<String> collection, Collection<String> collection2) {
        if (this.f978a == null) {
            return m540d();
        }
        f976b.m805c("startInstall(%s,%s)", collection, collection2);
        C2682i c2682i = new C2682i();
        this.f978a.m800a(new C2615ae(this, c2682i, collection, collection2, c2682i));
        return c2682i.m458a();
    }

    /* renamed from: a */
    public final Task<Void> m547a(List<String> list) {
        if (this.f978a == null) {
            return m540d();
        }
        f976b.m805c("deferredUninstall(%s)", list);
        C2682i c2682i = new C2682i();
        this.f978a.m800a(new C2616af(this, c2682i, list, c2682i));
        return c2682i.m458a();
    }

    /* renamed from: b */
    public final Task<Void> m545b(int r5) {
        if (this.f978a == null) {
            return m540d();
        }
        f976b.m805c("cancelInstall(%d)", Integer.valueOf(r5));
        C2682i c2682i = new C2682i();
        this.f978a.m800a(new C2622al(this, c2682i, r5, c2682i));
        return c2682i.m458a();
    }

    /* renamed from: b */
    public final Task<Void> m543b(List<String> list) {
        if (this.f978a == null) {
            return m540d();
        }
        f976b.m805c("deferredInstall(%s)", list);
        C2682i c2682i = new C2682i();
        this.f978a.m800a(new C2617ag(this, c2682i, list, c2682i));
        return c2682i.m458a();
    }

    /* renamed from: c */
    public final Task<Void> m541c(List<String> list) {
        if (this.f978a == null) {
            return m540d();
        }
        f976b.m805c("deferredLanguageInstall(%s)", list);
        C2682i c2682i = new C2682i();
        this.f978a.m800a(new C2618ah(this, c2682i, list, c2682i));
        return c2682i.m458a();
    }

    /* renamed from: d */
    public final Task<Void> m539d(List<String> list) {
        if (this.f978a == null) {
            return m540d();
        }
        f976b.m805c("deferredLanguageUninstall(%s)", list);
        C2682i c2682i = new C2682i();
        this.f978a.m800a(new C2619ai(this, c2682i, list, c2682i));
        return c2682i.m458a();
    }
}
