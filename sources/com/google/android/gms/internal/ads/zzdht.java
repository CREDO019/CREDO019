package com.google.android.gms.internal.ads;

/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
public final class zzdht implements zzdft {
    private int zza = ((Integer) com.google.android.gms.ads.internal.client.zzay.zzc().zzb(zzbiy.zzba)).intValue();

    @Override // com.google.android.gms.internal.ads.zzdft
    public final synchronized void zzb(zzfde zzfdeVar) {
        if (((Boolean) com.google.android.gms.ads.internal.client.zzay.zzc().zzb(zzbiy.zzbb)).booleanValue()) {
            try {
                this.zza = zzfdeVar.zzb.zzb.zzc;
            } catch (NullPointerException unused) {
            }
        }
    }

    @Override // com.google.android.gms.internal.ads.zzdft
    public final void zzbE(zzcba zzcbaVar) {
    }

    public final synchronized int zzc() {
        return this.zza;
    }
}
