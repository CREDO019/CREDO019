package com.google.android.gms.internal.ads;

import android.text.TextUtils;
import java.util.Map;

/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
final class zzbpc implements zzbpq {
    @Override // com.google.android.gms.internal.ads.zzbpq
    public final /* bridge */ /* synthetic */ void zza(Object obj, Map map) {
        zzcmn zzcmnVar = (zzcmn) obj;
        if (TextUtils.isEmpty((CharSequence) map.get("appId"))) {
            com.google.android.gms.ads.internal.util.zze.zza("Missing App Id, cannot show LMD Overlay without it");
            return;
        }
        zzfqj zzj = zzfqk.zzj();
        zzj.zzb((String) map.get("appId"));
        zzj.zzh(zzcmnVar.getWidth());
        zzj.zzg(zzcmnVar.zzH().getWindowToken());
        if (!map.containsKey("gravityX") || !map.containsKey("gravityY")) {
            zzj.zzd(81);
        } else {
            zzj.zzd(Integer.parseInt((String) map.get("gravityX")) | Integer.parseInt((String) map.get("gravityY")));
        }
        if (map.containsKey("verticalMargin")) {
            zzj.zze(Float.parseFloat((String) map.get("verticalMargin")));
        } else {
            zzj.zze(0.02f);
        }
        if (map.containsKey("enifd")) {
            zzj.zza((String) map.get("enifd"));
        }
        try {
            com.google.android.gms.ads.internal.zzt.zzk().zzj(zzcmnVar, zzj.zzi());
        } catch (NullPointerException e) {
            com.google.android.gms.ads.internal.zzt.zzp().zzt(e, "DefaultGmsgHandlers.ShowLMDOverlay");
            com.google.android.gms.ads.internal.util.zze.zza("Missing parameters for LMD Overlay show request");
        }
    }
}
