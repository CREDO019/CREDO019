package com.google.android.gms.internal.ads;

import android.content.Context;

/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
public final class zzduw {
    private final zzcmz zza;
    private final Context zzb;
    private final zzapb zzc;
    private final zzbjx zzd;
    private final zzcgt zze;
    private final com.google.android.gms.ads.internal.zza zzf;
    private final zzbel zzg;
    private final zzdfn zzh;

    public zzduw(zzcmz zzcmzVar, Context context, zzapb zzapbVar, zzbjx zzbjxVar, zzcgt zzcgtVar, com.google.android.gms.ads.internal.zza zzaVar, zzbel zzbelVar, zzdfn zzdfnVar) {
        this.zza = zzcmzVar;
        this.zzb = context;
        this.zzc = zzapbVar;
        this.zzd = zzbjxVar;
        this.zze = zzcgtVar;
        this.zzf = zzaVar;
        this.zzg = zzbelVar;
        this.zzh = zzdfnVar;
    }

    public final zzcmn zza(com.google.android.gms.ads.internal.client.zzq zzqVar, zzfcs zzfcsVar, zzfcv zzfcvVar) throws zzcmy {
        return zzcmz.zza(this.zzb, zzcoc.zzc(zzqVar), zzqVar.zza, false, false, this.zzc, this.zzd, this.zze, null, new zzdul(this), this.zzf, this.zzg, zzfcsVar, zzfcvVar);
    }
}
