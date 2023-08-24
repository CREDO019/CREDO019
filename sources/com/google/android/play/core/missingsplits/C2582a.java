package com.google.android.play.core.missingsplits;

import android.content.ComponentName;
import android.content.Context;
import android.content.pm.ComponentInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import com.google.android.play.core.internal.C2494af;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/* renamed from: com.google.android.play.core.missingsplits.a */
/* loaded from: classes3.dex */
final class C2582a {

    /* renamed from: a */
    private static final C2494af f875a = new C2494af("MissingSplitsAppComponentsHelper");

    /* renamed from: b */
    private final Context f876b;

    /* renamed from: c */
    private final PackageManager f877c;

    /* JADX INFO: Access modifiers changed from: package-private */
    public C2582a(Context context, PackageManager packageManager) {
        this.f876b = context;
        this.f877c = packageManager;
    }

    /* renamed from: a */
    private final void m632a(List<ComponentInfo> list, int r6) {
        for (ComponentInfo componentInfo : list) {
            this.f877c.setComponentEnabledSetting(new ComponentName(componentInfo.packageName, componentInfo.name), r6, 1);
        }
    }

    /* renamed from: d */
    private final List<ComponentInfo> m629d() {
        try {
            ArrayList arrayList = new ArrayList();
            PackageInfo packageInfo = this.f877c.getPackageInfo(this.f876b.getPackageName(), 526);
            if (packageInfo.providers != null) {
                Collections.addAll(arrayList, packageInfo.providers);
            }
            if (packageInfo.receivers != null) {
                Collections.addAll(arrayList, packageInfo.receivers);
            }
            if (packageInfo.services != null) {
                Collections.addAll(arrayList, packageInfo.services);
            }
            return arrayList;
        } catch (PackageManager.NameNotFoundException e) {
            f875a.m804d("Failed to resolve own package : %s", e);
            return Collections.emptyList();
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: a */
    public final boolean m633a() {
        for (ComponentInfo componentInfo : m629d()) {
            if (this.f877c.getComponentEnabledSetting(new ComponentName(componentInfo.packageName, componentInfo.name)) != 2) {
                f875a.m808a("Not all non-activity components are disabled", new Object[0]);
                return false;
            }
        }
        f875a.m808a("All non-activity components are disabled", new Object[0]);
        return true;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: b */
    public final void m631b() {
        f875a.m805c("Disabling all non-activity components", new Object[0]);
        m632a(m629d(), 2);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: c */
    public final void m630c() {
        f875a.m805c("Resetting enabled state of all non-activity components", new Object[0]);
        m632a(m629d(), 0);
    }
}
