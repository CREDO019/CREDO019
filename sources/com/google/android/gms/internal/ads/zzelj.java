package com.google.android.gms.internal.ads;

import com.google.android.gms.ads.AdError;

/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
final class zzelj implements zzdeh {
    boolean zza = false;
    final /* synthetic */ zzegn zzb;
    final /* synthetic */ zzchf zzc;
    final /* synthetic */ zzelk zzd;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzelj(zzelk zzelkVar, zzegn zzegnVar, zzchf zzchfVar) {
        this.zzd = zzelkVar;
        this.zzb = zzegnVar;
        this.zzc = zzchfVar;
    }

    private final synchronized void zze(com.google.android.gms.ads.internal.client.zze zzeVar) {
        int r1 = 1;
        if (true == ((Boolean) com.google.android.gms.ads.internal.client.zzay.zzc().zzb(zzbiy.zzey)).booleanValue()) {
            r1 = 3;
        }
        this.zzc.zze(new zzego(r1, zzeVar));
    }

    @Override // com.google.android.gms.internal.ads.zzdeh
    public final synchronized void zza(int r8) {
        if (this.zza) {
            return;
        }
        this.zza = true;
        zze(new com.google.android.gms.ads.internal.client.zze(r8, zzelk.zze(this.zzb.zza, r8), AdError.UNDEFINED_DOMAIN, null, null));
    }

    @Override // com.google.android.gms.internal.ads.zzdeh
    public final synchronized void zzb(com.google.android.gms.ads.internal.client.zze zzeVar) {
        if (this.zza) {
            return;
        }
        this.zza = true;
        zze(zzeVar);
    }

    @Override // com.google.android.gms.internal.ads.zzdeh
    public final synchronized void zzc(int r7, String str) {
        if (this.zza) {
            return;
        }
        this.zza = true;
        if (str == null) {
            str = zzelk.zze(this.zzb.zza, r7);
        }
        zze(new com.google.android.gms.ads.internal.client.zze(r7, str, AdError.UNDEFINED_DOMAIN, null, null));
    }

    @Override // com.google.android.gms.internal.ads.zzdeh
    public final synchronized void zzd() {
        this.zzc.zzd(null);
    }
}
