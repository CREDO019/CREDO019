package com.google.android.play.core.appupdate;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import com.google.android.play.core.install.InstallException;
import com.google.android.play.core.internal.C2494af;
import com.google.android.play.core.internal.C2504ap;
import com.google.android.play.core.internal.C2540by;
import com.google.android.play.core.internal.InterfaceC2567n;
import com.google.android.play.core.splitcompat.C2608p;
import com.google.android.play.core.tasks.C2682i;
import com.google.android.play.core.tasks.Task;
import com.google.android.play.core.tasks.Tasks;

/* JADX INFO: Access modifiers changed from: package-private */
/* renamed from: com.google.android.play.core.appupdate.p */
/* loaded from: classes3.dex */
public final class C2343p {

    /* renamed from: b */
    private static final C2494af f320b = new C2494af("AppUpdateService");

    /* renamed from: c */
    private static final Intent f321c = new Intent("com.google.android.play.core.install.BIND_UPDATE_SERVICE").setPackage("com.android.vending");

    /* renamed from: a */
    C2504ap<InterfaceC2567n> f322a;

    /* renamed from: d */
    private final String f323d;

    /* renamed from: e */
    private final Context f324e;

    /* renamed from: f */
    private final C2345r f325f;

    public C2343p(Context context, C2345r c2345r) {
        this.f323d = context.getPackageName();
        this.f324e = context;
        this.f325f = c2345r;
        if (C2540by.m723a(context)) {
            this.f322a = new C2504ap<>(C2608p.m576a(context), f320b, "AppUpdateService", f321c, C2337j.f308a);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: a */
    public static /* synthetic */ Bundle m1065a(C2343p c2343p, String str) {
        Integer num;
        Bundle bundle = new Bundle();
        bundle.putAll(m1060d());
        bundle.putString("package.name", str);
        try {
            num = Integer.valueOf(c2343p.f324e.getPackageManager().getPackageInfo(c2343p.f324e.getPackageName(), 0).versionCode);
        } catch (PackageManager.NameNotFoundException unused) {
            f320b.m806b("The current version of the app could not be retrieved", new Object[0]);
            num = null;
        }
        if (num != null) {
            bundle.putInt("app.version.code", num.intValue());
        }
        return bundle;
    }

    /* renamed from: c */
    private static <T> Task<T> m1061c() {
        f320b.m806b("onError(%d)", -9);
        return Tasks.m469a((Exception) new InstallException(-9));
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: d */
    public static Bundle m1060d() {
        Bundle bundle = new Bundle();
        bundle.putInt("playcore.version.code", 10800);
        return bundle;
    }

    /* renamed from: a */
    public final Task<AppUpdateInfo> m1064a(String str) {
        if (this.f322a == null) {
            return m1061c();
        }
        f320b.m805c("requestUpdateInfo(%s)", str);
        C2682i c2682i = new C2682i();
        this.f322a.m800a(new C2338k(this, c2682i, str, c2682i));
        return c2682i.m458a();
    }

    /* renamed from: b */
    public final Task<Void> m1062b(String str) {
        if (this.f322a == null) {
            return m1061c();
        }
        f320b.m805c("completeUpdate(%s)", str);
        C2682i c2682i = new C2682i();
        this.f322a.m800a(new C2339l(this, c2682i, c2682i, str));
        return c2682i.m458a();
    }
}
