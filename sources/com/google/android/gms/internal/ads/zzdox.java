package com.google.android.gms.internal.ads;

import androidx.collection.SimpleArrayMap;

/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
public final class zzdox {
    zzbnb zza;
    zzbmy zzb;
    zzbno zzc;
    zzbnl zzd;
    zzbsg zze;
    final SimpleArrayMap zzf = new SimpleArrayMap();
    final SimpleArrayMap zzg = new SimpleArrayMap();

    public final zzdox zza(zzbmy zzbmyVar) {
        this.zzb = zzbmyVar;
        return this;
    }

    public final zzdox zzb(zzbnb zzbnbVar) {
        this.zza = zzbnbVar;
        return this;
    }

    public final zzdox zzc(String str, zzbnh zzbnhVar, zzbne zzbneVar) {
        this.zzf.put(str, zzbnhVar);
        if (zzbneVar != null) {
            this.zzg.put(str, zzbneVar);
        }
        return this;
    }

    public final zzdox zzd(zzbsg zzbsgVar) {
        this.zze = zzbsgVar;
        return this;
    }

    public final zzdox zze(zzbnl zzbnlVar) {
        this.zzd = zzbnlVar;
        return this;
    }

    public final zzdox zzf(zzbno zzbnoVar) {
        this.zzc = zzbnoVar;
        return this;
    }

    public final zzdoz zzg() {
        return new zzdoz(this);
    }
}
