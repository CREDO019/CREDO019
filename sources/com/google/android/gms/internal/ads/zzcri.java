package com.google.android.gms.internal.ads;

import android.content.Context;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
public final class zzcri implements zzdyx {
    private final Context zza;
    private final zzbqm zzb;
    private final zzcpu zzc;
    private final zzcri zzd = this;
    private final zzgve zze;
    private final zzgve zzf;
    private final zzgve zzg;
    private final zzgve zzh;

    /* JADX INFO: Access modifiers changed from: package-private */
    public /* synthetic */ zzcri(zzcpu zzcpuVar, Context context, zzbqm zzbqmVar, zzcrh zzcrhVar) {
        this.zzc = zzcpuVar;
        this.zza = context;
        this.zzb = zzbqmVar;
        zzgur zza = zzgus.zza(this);
        this.zze = zza;
        zzgur zza2 = zzgus.zza(zzbqmVar);
        this.zzf = zza2;
        zzdyt zzdytVar = new zzdyt(zza2);
        this.zzg = zzdytVar;
        this.zzh = zzguq.zzc(new zzdyv(zza, zzdytVar));
    }

    @Override // com.google.android.gms.internal.ads.zzdyx
    public final zzdyo zzb() {
        return new zzcrc(this.zzc, this.zzd, null);
    }

    @Override // com.google.android.gms.internal.ads.zzdyx
    public final zzdyu zzd() {
        return (zzdyu) this.zzh.zzb();
    }
}
