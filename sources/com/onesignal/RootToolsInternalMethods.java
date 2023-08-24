package com.onesignal;

import java.io.File;

/* loaded from: classes3.dex */
class RootToolsInternalMethods {
    RootToolsInternalMethods() {
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static boolean isRooted() {
        String[] strArr = {"/sbin/", "/system/bin/", "/system/xbin/", "/data/local/xbin/", "/data/local/bin/", "/system/sd/xbin/", "/system/bin/failsafe/", "/data/local/"};
        for (int r2 = 0; r2 < 8; r2++) {
            try {
                String str = strArr[r2];
                if (new File(str + "su").exists()) {
                    return true;
                }
            } catch (Throwable unused) {
            }
        }
        return false;
    }
}
