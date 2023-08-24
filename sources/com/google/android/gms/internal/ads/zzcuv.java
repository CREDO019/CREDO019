package com.google.android.gms.internal.ads;

import android.content.Context;
import java.util.List;

/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
final class zzcuv implements zzfyk {
    final /* synthetic */ zzcux zza;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzcuv(zzcux zzcuxVar) {
        this.zza = zzcuxVar;
    }

    @Override // com.google.android.gms.internal.ads.zzfyk
    public final void zza(Throwable th) {
    }

    @Override // com.google.android.gms.internal.ads.zzfyk
    public final /* bridge */ /* synthetic */ void zzb(Object obj) {
        zzfdw zzfdwVar;
        zzfjq zzfjqVar;
        zzfde zzfdeVar;
        zzfcs zzfcsVar;
        zzfcs zzfcsVar2;
        Context context;
        String str = (String) obj;
        zzcux zzcuxVar = this.zza;
        zzfdwVar = zzcuxVar.zzh;
        zzfjqVar = zzcuxVar.zzg;
        zzfdeVar = zzcuxVar.zze;
        zzfcsVar = zzcuxVar.zzf;
        zzfcsVar2 = zzcuxVar.zzf;
        List zzd = zzfjqVar.zzd(zzfdeVar, zzfcsVar, false, "", str, zzfcsVar2.zzc);
        zzcfw zzp = com.google.android.gms.ads.internal.zzt.zzp();
        context = this.zza.zza;
        zzfdwVar.zzc(zzd, true == zzp.zzv(context) ? 2 : 1);
    }
}
