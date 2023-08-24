package com.google.android.gms.internal.ads;

import java.util.Objects;

/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
final class zzcrc implements zzdyo {
    private final zzcpu zza;
    private final zzcri zzb;
    private Long zzc;
    private String zzd;

    /* JADX INFO: Access modifiers changed from: package-private */
    public /* synthetic */ zzcrc(zzcpu zzcpuVar, zzcri zzcriVar, zzcrb zzcrbVar) {
        this.zza = zzcpuVar;
        this.zzb = zzcriVar;
    }

    @Override // com.google.android.gms.internal.ads.zzdyo
    public final /* synthetic */ zzdyo zza(String str) {
        Objects.requireNonNull(str);
        this.zzd = str;
        return this;
    }

    @Override // com.google.android.gms.internal.ads.zzdyo
    public final /* bridge */ /* synthetic */ zzdyo zzb(long j) {
        this.zzc = Long.valueOf(j);
        return this;
    }

    @Override // com.google.android.gms.internal.ads.zzdyo
    public final zzdyp zzc() {
        zzguz.zzc(this.zzc, Long.class);
        zzguz.zzc(this.zzd, String.class);
        return new zzcre(this.zza, this.zzb, this.zzc, this.zzd, null);
    }
}
