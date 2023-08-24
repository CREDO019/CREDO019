package com.onesignal;

import android.os.Build;

/* compiled from: BundleCompat.java */
/* loaded from: classes3.dex */
class BundleCompatFactory {
    BundleCompatFactory() {
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static BundleCompat getInstance() {
        if (Build.VERSION.SDK_INT >= 22) {
            return new BundleCompatPersistableBundle();
        }
        return new BundleCompatBundle();
    }
}
