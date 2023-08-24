package com.google.android.gms.internal.vision;

import android.content.Context;
import android.os.Build;
import android.os.Process;
import android.os.UserManager;
import android.util.Log;

/* compiled from: com.google.android.gms:play-services-vision-common@@19.0.0 */
/* loaded from: classes3.dex */
public class zzan {
    private static UserManager zzff;
    private static volatile boolean zzfg = !zzs();
    private static boolean zzfh = false;

    private zzan() {
    }

    public static boolean zzs() {
        return Build.VERSION.SDK_INT >= 24;
    }

    public static boolean isUserUnlocked(Context context) {
        return !zzs() || zzd(context);
    }

    private static boolean zzc(Context context) {
        boolean z;
        boolean z2 = true;
        int r1 = 1;
        while (true) {
            z = false;
            if (r1 > 2) {
                break;
            }
            if (zzff == null) {
                zzff = (UserManager) context.getSystemService(UserManager.class);
            }
            UserManager userManager = zzff;
            if (userManager == null) {
                return true;
            }
            try {
                if (userManager.isUserUnlocked()) {
                    break;
                } else if (userManager.isUserRunning(Process.myUserHandle())) {
                    z2 = false;
                }
            } catch (NullPointerException e) {
                Log.w("DirectBootUtils", "Failed to check if user is unlocked.", e);
                zzff = null;
                r1++;
            }
        }
        z = z2;
        if (z) {
            zzff = null;
        }
        return z;
    }

    private static boolean zzd(Context context) {
        if (zzfg) {
            return true;
        }
        synchronized (zzan.class) {
            if (zzfg) {
                return true;
            }
            boolean zzc = zzc(context);
            if (zzc) {
                zzfg = zzc;
            }
            return zzc;
        }
    }
}
