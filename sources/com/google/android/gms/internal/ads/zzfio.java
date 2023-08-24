package com.google.android.gms.internal.ads;

import android.content.Context;

/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
public final class zzfio implements zzdjv, zzddo, zzdjz {
    private final zzfjc zza;
    private final zzfir zzb;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzfio(Context context, zzfjc zzfjcVar) {
        this.zza = zzfjcVar;
        this.zzb = zzfiq.zza(context, 13);
    }

    @Override // com.google.android.gms.internal.ads.zzdjz
    public final void zza() {
    }

    @Override // com.google.android.gms.internal.ads.zzdjz
    public final void zzb() {
        if (((Boolean) zzbkh.zzd.zze()).booleanValue()) {
            zzfjc zzfjcVar = this.zza;
            zzfir zzfirVar = this.zzb;
            zzfirVar.zze(true);
            zzfjcVar.zza(zzfirVar);
        }
    }

    @Override // com.google.android.gms.internal.ads.zzdjv
    public final void zzf() {
    }

    @Override // com.google.android.gms.internal.ads.zzdjv
    public final void zzg() {
        if (((Boolean) zzbkh.zzd.zze()).booleanValue()) {
            this.zzb.zzf();
        }
    }

    @Override // com.google.android.gms.internal.ads.zzddo
    public final void zzk(com.google.android.gms.ads.internal.client.zze zzeVar) {
        if (((Boolean) zzbkh.zzd.zze()).booleanValue()) {
            zzfjc zzfjcVar = this.zza;
            zzfir zzfirVar = this.zzb;
            zzfirVar.zze(false);
            zzfjcVar.zza(zzfirVar);
        }
    }
}
