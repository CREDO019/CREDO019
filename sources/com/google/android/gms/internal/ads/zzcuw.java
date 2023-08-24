package com.google.android.gms.internal.ads;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
public final class zzcuw implements zzfyk {
    final /* synthetic */ String zza;
    final /* synthetic */ zzcux zzb;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzcuw(zzcux zzcuxVar, String str) {
        this.zzb = zzcuxVar;
        this.zza = str;
    }

    @Override // com.google.android.gms.internal.ads.zzfyk
    public final void zza(Throwable th) {
        zzfdw zzfdwVar;
        zzfjq zzfjqVar;
        zzfde zzfdeVar;
        zzfcs zzfcsVar;
        zzfcs zzfcsVar2;
        zzcux zzcuxVar = this.zzb;
        zzfdwVar = zzcuxVar.zzh;
        zzfjqVar = zzcuxVar.zzg;
        zzfdeVar = zzcuxVar.zze;
        zzfcsVar = zzcuxVar.zzf;
        String str = this.zza;
        zzfcsVar2 = zzcuxVar.zzf;
        zzfdwVar.zza(zzfjqVar.zzd(zzfdeVar, zzfcsVar, false, str, null, zzfcsVar2.zzd));
    }

    @Override // com.google.android.gms.internal.ads.zzfyk
    public final /* bridge */ /* synthetic */ void zzb(Object obj) {
        zzfdw zzfdwVar;
        zzfjq zzfjqVar;
        zzfde zzfdeVar;
        zzfcs zzfcsVar;
        zzfcs zzfcsVar2;
        String str = (String) obj;
        zzcux zzcuxVar = this.zzb;
        zzfdwVar = zzcuxVar.zzh;
        zzfjqVar = zzcuxVar.zzg;
        zzfdeVar = zzcuxVar.zze;
        zzfcsVar = zzcuxVar.zzf;
        String str2 = this.zza;
        zzfcsVar2 = zzcuxVar.zzf;
        zzfdwVar.zza(zzfjqVar.zzd(zzfdeVar, zzfcsVar, false, str2, str, zzfcsVar2.zzd));
    }
}
