package com.google.android.gms.ads.search;

import android.os.Bundle;
import com.google.android.gms.ads.internal.client.zzdq;
import com.google.android.gms.ads.mediation.NetworkExtras;

/* compiled from: com.google.android.gms:play-services-ads-lite@@21.2.0 */
/* loaded from: classes2.dex */
public final class zzb {
    private final zzdq zza = new zzdq();
    private String zzb;

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* bridge */ /* synthetic */ zzdq zza(zzb zzbVar) {
        return zzbVar.zza;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* bridge */ /* synthetic */ String zzf(zzb zzbVar) {
        return zzbVar.zzb;
    }

    public final zzb zzb(Class cls, Bundle bundle) {
        this.zza.zzr(cls, bundle);
        return this;
    }

    public final zzb zzc(NetworkExtras networkExtras) {
        this.zza.zzv(networkExtras);
        return this;
    }

    public final zzb zzd(Class cls, Bundle bundle) {
        this.zza.zzu(cls, bundle);
        return this;
    }

    public final zzb zze(String str) {
        this.zzb = str;
        return this;
    }
}
