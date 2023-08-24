package com.google.android.play.core.appupdate;

import android.content.Context;
import java.io.File;

/* JADX INFO: Access modifiers changed from: package-private */
/* renamed from: com.google.android.play.core.appupdate.r */
/* loaded from: classes3.dex */
public final class C2345r {

    /* renamed from: a */
    private final Context f328a;

    /* JADX INFO: Access modifiers changed from: package-private */
    public C2345r(Context context) {
        this.f328a = context;
    }

    /* renamed from: a */
    private static long m1058a(File file) {
        if (file.isDirectory()) {
            File[] listFiles = file.listFiles();
            long j = 0;
            if (listFiles != null) {
                for (File file2 : listFiles) {
                    j += m1058a(file2);
                }
            }
            return j;
        }
        return file.length();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: a */
    public final long m1059a() {
        return m1058a(new File(this.f328a.getFilesDir(), "assetpacks"));
    }
}
