package com.google.android.gms.internal.p015authapi;

import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Build;

/* compiled from: com.google.android.gms:play-services-auth@@19.2.0 */
/* renamed from: com.google.android.gms.internal.auth-api.zbay */
/* loaded from: classes2.dex */
public final class zbay {
    public static final int zba;

    static {
        int r1 = 33554432;
        if (Build.VERSION.SDK_INT < 31 && (Build.VERSION.SDK_INT < 30 || Build.VERSION.CODENAME.length() != 1 || Build.VERSION.CODENAME.charAt(0) < 'S' || Build.VERSION.CODENAME.charAt(0) > 'Z')) {
            r1 = 0;
        }
        zba = r1;
    }

    public static PendingIntent zba(Context context, int r1, Intent intent, int r3) {
        return PendingIntent.getActivity(context, 2000, intent, r3);
    }
}
