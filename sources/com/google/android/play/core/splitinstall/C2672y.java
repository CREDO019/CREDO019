package com.google.android.play.core.splitinstall;

import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Bundle;
import java.io.File;

/* renamed from: com.google.android.play.core.splitinstall.y */
/* loaded from: classes3.dex */
public final class C2672y {

    /* renamed from: a */
    private final Context f1087a;

    /* JADX INFO: Access modifiers changed from: package-private */
    public C2672y(Context context) {
        this.f1087a = context;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: a */
    public static File m475a(Context context) {
        String string;
        try {
            Bundle bundle = context.getPackageManager().getApplicationInfo(context.getPackageName(), 128).metaData;
            if (bundle == null || (string = bundle.getString("local_testing_dir")) == null) {
                return null;
            }
            return new File(context.getExternalFilesDir(null), string);
        } catch (PackageManager.NameNotFoundException unused) {
            return null;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: a */
    public final C2656t m476a() {
        return C2656t.m508a(this.f1087a);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: b */
    public final Context m474b() {
        return this.f1087a;
    }
}
