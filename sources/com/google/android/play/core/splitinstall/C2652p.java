package com.google.android.play.core.splitinstall;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.os.Build;
import android.os.Bundle;
import com.google.android.play.core.internal.C2494af;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/* renamed from: com.google.android.play.core.splitinstall.p */
/* loaded from: classes3.dex */
public final class C2652p {

    /* renamed from: a */
    private static final C2494af f1010a = new C2494af("SplitInstallInfoProvider");

    /* renamed from: b */
    private final Context f1011b;

    /* renamed from: c */
    private final String f1012c;

    public C2652p(Context context) {
        this.f1011b = context;
        this.f1012c = context.getPackageName();
    }

    public C2652p(Context context, String str) {
        this.f1011b = context;
        this.f1012c = str;
    }

    /* renamed from: a */
    public static boolean m515a(String str) {
        return str.startsWith("config.");
    }

    /* renamed from: b */
    public static boolean m513b(String str) {
        return m515a(str) || str.contains(".config.");
    }

    /* renamed from: d */
    private final Set<String> m511d() {
        HashSet hashSet = new HashSet();
        Bundle m510e = m510e();
        if (m510e != null) {
            String string = m510e.getString("com.android.dynamic.apk.fused.modules");
            if (string == null || string.isEmpty()) {
                f1010a.m808a("App has no fused modules.", new Object[0]);
            } else {
                Collections.addAll(hashSet, string.split(",", -1));
                hashSet.remove("");
                hashSet.remove("base");
            }
        }
        if (Build.VERSION.SDK_INT >= 21) {
            String[] strArr = null;
            try {
                PackageInfo packageInfo = this.f1011b.getPackageManager().getPackageInfo(this.f1012c, 0);
                if (packageInfo != null) {
                    strArr = packageInfo.splitNames;
                }
            } catch (PackageManager.NameNotFoundException unused) {
                f1010a.m804d("App is not found in PackageManager", new Object[0]);
            }
            if (strArr != null) {
                f1010a.m808a("Adding splits from package manager: %s", Arrays.toString(strArr));
                Collections.addAll(hashSet, strArr);
            } else {
                f1010a.m808a("No splits are found or app cannot be found in package manager.", new Object[0]);
            }
            InterfaceC2650n m518a = C2651o.m518a();
            if (m518a != null) {
                hashSet.addAll(m518a.mo519a());
            }
        }
        return hashSet;
    }

    /* renamed from: e */
    private final Bundle m510e() {
        try {
            ApplicationInfo applicationInfo = this.f1011b.getPackageManager().getApplicationInfo(this.f1012c, 128);
            if (applicationInfo == null || applicationInfo.metaData == null) {
                f1010a.m808a("App has no applicationInfo or metaData", new Object[0]);
                return null;
            }
            return applicationInfo.metaData;
        } catch (PackageManager.NameNotFoundException unused) {
            f1010a.m804d("App is not found in PackageManager", new Object[0]);
            return null;
        }
    }

    /* renamed from: a */
    public final Set<String> m516a() {
        HashSet hashSet = new HashSet();
        for (String str : m511d()) {
            if (!m513b(str)) {
                hashSet.add(str);
            }
        }
        return hashSet;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: b */
    public final Set<String> m514b() {
        C2643h m512c = m512c();
        if (m512c == null) {
            return null;
        }
        HashSet hashSet = new HashSet();
        Set<String> m511d = m511d();
        m511d.add("");
        Set<String> m516a = m516a();
        m516a.add("");
        for (Map.Entry<String, Set<String>> entry : m512c.m527a(m516a).entrySet()) {
            if (m511d.containsAll(entry.getValue())) {
                hashSet.add(entry.getKey());
            }
        }
        return hashSet;
    }

    /* renamed from: c */
    public final C2643h m512c() {
        Bundle m510e = m510e();
        if (m510e == null) {
            f1010a.m804d("No metadata found in Context.", new Object[0]);
            return null;
        }
        int r0 = m510e.getInt("com.android.vending.splits");
        if (r0 == 0) {
            f1010a.m804d("No metadata found in AndroidManifest.", new Object[0]);
            return null;
        }
        try {
            C2643h m535a = new C2636az(this.f1011b.getResources().getXml(r0)).m535a();
            if (m535a == null) {
                f1010a.m804d("Can't parse languages metadata.", new Object[0]);
            }
            return m535a;
        } catch (Resources.NotFoundException unused) {
            f1010a.m804d("Resource with languages metadata doesn't exist.", new Object[0]);
            return null;
        }
    }
}
