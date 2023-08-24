package com.google.android.gms.internal.ads;

import android.content.Context;

/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
public final class zzdbq implements zzdft {
    private final Context zza;
    private final zzfdn zzb;
    private final zzcgt zzc;
    private final com.google.android.gms.ads.internal.util.zzg zzd;
    private final zzdzq zze;
    private final zzfje zzf;

    public zzdbq(Context context, zzfdn zzfdnVar, zzcgt zzcgtVar, com.google.android.gms.ads.internal.util.zzg zzgVar, zzdzq zzdzqVar, zzfje zzfjeVar) {
        this.zza = context;
        this.zzb = zzfdnVar;
        this.zzc = zzcgtVar;
        this.zzd = zzgVar;
        this.zze = zzdzqVar;
        this.zzf = zzfjeVar;
    }

    @Override // com.google.android.gms.internal.ads.zzdft
    public final void zzb(zzfde zzfdeVar) {
    }

    @Override // com.google.android.gms.internal.ads.zzdft
    public final void zzbE(zzcba zzcbaVar) {
        if (((Boolean) com.google.android.gms.ads.internal.client.zzay.zzc().zzb(zzbiy.zzdb)).booleanValue()) {
            com.google.android.gms.ads.internal.zzt.zza().zzc(this.zza, this.zzc, this.zzb.zzf, this.zzd.zzh(), this.zzf);
        }
        this.zze.zzr();
    }
}
