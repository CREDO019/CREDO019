package com.google.android.gms.ads.internal.util;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.telephony.TelephonyManager;

/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
public class zzv extends zzu {
    @Override // com.google.android.gms.ads.internal.util.zzaa
    public final void zzg(Context context) {
        NotificationChannel notificationChannel = new NotificationChannel("offline_notification_channel", "AdMob Offline Notifications", 2);
        notificationChannel.setShowBadge(false);
        ((NotificationManager) context.getSystemService(NotificationManager.class)).createNotificationChannel(notificationChannel);
    }

    @Override // com.google.android.gms.ads.internal.util.zzaa
    public final int zzh(Context context, TelephonyManager telephonyManager) {
        com.google.android.gms.ads.internal.zzt.zzq();
        return (zzs.zzx(context, "android.permission.ACCESS_NETWORK_STATE") && telephonyManager.isDataEnabled()) ? 2 : 1;
    }
}
