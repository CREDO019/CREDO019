package com.google.android.play.core.assetpacks;

import android.content.Context;
import android.content.pm.PackageManager;
import com.google.android.play.core.internal.C2494af;

/* JADX INFO: Access modifiers changed from: package-private */
/* renamed from: com.google.android.play.core.assetpacks.dl */
/* loaded from: classes3.dex */
public final class C2446dl {

    /* renamed from: a */
    private static final C2494af f699a = new C2494af("PackageStateCache");

    /* renamed from: b */
    private final Context f700b;

    /* renamed from: c */
    private int f701c = -1;

    /* JADX INFO: Access modifiers changed from: package-private */
    public C2446dl(Context context) {
        this.f700b = context;
    }

    /* renamed from: a */
    public final synchronized int m888a() {
        if (this.f701c == -1) {
            try {
                this.f701c = this.f700b.getPackageManager().getPackageInfo(this.f700b.getPackageName(), 0).versionCode;
            } catch (PackageManager.NameNotFoundException unused) {
                f699a.m806b("The current version of the app could not be retrieved", new Object[0]);
            }
        }
        return this.f701c;
    }
}
