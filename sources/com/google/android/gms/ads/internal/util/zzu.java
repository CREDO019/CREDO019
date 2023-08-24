package com.google.android.gms.ads.internal.util;

import android.app.Activity;
import android.content.res.Configuration;
import android.util.DisplayMetrics;
import android.view.WindowManager;
import com.amplitude.api.DeviceInfo;
import com.google.android.gms.internal.ads.zzbiy;
import com.google.android.gms.internal.ads.zzcgg;

/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
public class zzu extends zzt {
    static final boolean zzf(int r0, int r1, int r2) {
        return Math.abs(r0 - r1) <= r2;
    }

    @Override // com.google.android.gms.ads.internal.util.zzaa
    public final boolean zze(Activity activity, Configuration configuration) {
        if (((Boolean) com.google.android.gms.ads.internal.client.zzay.zzc().zzb(zzbiy.zzdT)).booleanValue()) {
            if (((Boolean) com.google.android.gms.ads.internal.client.zzay.zzc().zzb(zzbiy.zzdV)).booleanValue()) {
                return activity.isInMultiWindowMode();
            }
            com.google.android.gms.ads.internal.client.zzaw.zzb();
            int zzw = zzcgg.zzw(activity, configuration.screenHeightDp);
            int zzw2 = zzcgg.zzw(activity, configuration.screenWidthDp);
            com.google.android.gms.ads.internal.zzt.zzq();
            DisplayMetrics zzr = zzs.zzr((WindowManager) activity.getApplicationContext().getSystemService("window"));
            int r3 = zzr.heightPixels;
            int r2 = zzr.widthPixels;
            int identifier = activity.getResources().getIdentifier("status_bar_height", "dimen", DeviceInfo.OS_NAME);
            int dimensionPixelSize = identifier > 0 ? activity.getResources().getDimensionPixelSize(identifier) : 0;
            int round = ((int) Math.round(activity.getResources().getDisplayMetrics().density + 0.5d)) * ((Integer) com.google.android.gms.ads.internal.client.zzay.zzc().zzb(zzbiy.zzdR)).intValue();
            return (zzf(r3, zzw + dimensionPixelSize, round) && zzf(r2, zzw2, round)) ? false : true;
        }
        return false;
    }
}
