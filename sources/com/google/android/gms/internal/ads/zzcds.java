package com.google.android.gms.internal.ads;

import com.google.android.gms.common.util.Clock;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
public final class zzcds {
    private final Clock zza;
    private final com.google.android.gms.ads.internal.util.zzg zzb;
    private final zzces zzc;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzcds(Clock clock, com.google.android.gms.ads.internal.util.zzg zzgVar, zzces zzcesVar) {
        this.zza = clock;
        this.zzb = zzgVar;
        this.zzc = zzcesVar;
    }

    public final void zza() {
        if (((Boolean) com.google.android.gms.ads.internal.client.zzay.zzc().zzb(zzbiy.zzao)).booleanValue()) {
            this.zzc.zzt();
        }
    }

    public final void zzb(int r6, long j) {
        if (((Boolean) com.google.android.gms.ads.internal.client.zzay.zzc().zzb(zzbiy.zzan)).booleanValue()) {
            return;
        }
        if (j - this.zzb.zzf() < 0) {
            com.google.android.gms.ads.internal.util.zze.zza("Receiving npa decision in the past, ignoring.");
            return;
        }
        if (!((Boolean) com.google.android.gms.ads.internal.client.zzay.zzc().zzb(zzbiy.zzao)).booleanValue()) {
            this.zzb.zzK(-1);
            this.zzb.zzL(j);
        } else {
            this.zzb.zzK(r6);
            this.zzb.zzL(j);
        }
        zza();
    }
}
