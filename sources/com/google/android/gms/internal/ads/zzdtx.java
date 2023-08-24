package com.google.android.gms.internal.ads;

import javax.annotation.ParametersAreNonnullByDefault;

/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
public final class zzdtx implements zzbqf {
    private final zzdef zza;
    private final zzccc zzb;
    private final String zzc;
    private final String zzd;

    public zzdtx(zzdef zzdefVar, zzfcs zzfcsVar) {
        this.zza = zzdefVar;
        this.zzb = zzfcsVar.zzm;
        this.zzc = zzfcsVar.zzk;
        this.zzd = zzfcsVar.zzl;
    }

    @Override // com.google.android.gms.internal.ads.zzbqf
    @ParametersAreNonnullByDefault
    public final void zza(zzccc zzcccVar) {
        int r4;
        String str;
        zzccc zzcccVar2 = this.zzb;
        if (zzcccVar2 != null) {
            zzcccVar = zzcccVar2;
        }
        if (zzcccVar != null) {
            str = zzcccVar.zza;
            r4 = zzcccVar.zzb;
        } else {
            r4 = 1;
            str = "";
        }
        this.zza.zzd(new zzcbn(str, r4), this.zzc, this.zzd);
    }

    @Override // com.google.android.gms.internal.ads.zzbqf
    public final void zzb() {
        this.zza.zze();
    }

    @Override // com.google.android.gms.internal.ads.zzbqf
    public final void zzc() {
        this.zza.zzf();
    }
}
