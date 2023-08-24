package com.google.android.gms.internal.ads;

import android.util.DisplayMetrics;
import android.view.View;
import android.view.WindowManager;
import java.util.HashMap;
import java.util.Map;

/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
final class zzboy implements zzbpq {
    @Override // com.google.android.gms.internal.ads.zzbpq
    public final /* bridge */ /* synthetic */ void zza(Object obj, Map map) {
        zzcmn zzcmnVar = (zzcmn) obj;
        com.google.android.gms.ads.internal.zzt.zzq();
        DisplayMetrics zzr = com.google.android.gms.ads.internal.util.zzs.zzr((WindowManager) zzcmnVar.getContext().getSystemService("window"));
        int r1 = zzr.widthPixels;
        int r7 = zzr.heightPixels;
        int[] r2 = new int[2];
        HashMap hashMap = new HashMap();
        ((View) zzcmnVar).getLocationInWindow(r2);
        hashMap.put("xInPixels", Integer.valueOf(r2[0]));
        hashMap.put("yInPixels", Integer.valueOf(r2[1]));
        hashMap.put("windowWidthInPixels", Integer.valueOf(r1));
        hashMap.put("windowHeightInPixels", Integer.valueOf(r7));
        zzcmnVar.zzd("locationReady", hashMap);
        com.google.android.gms.ads.internal.util.zze.zzj("GET LOCATION COMPILED");
    }
}
