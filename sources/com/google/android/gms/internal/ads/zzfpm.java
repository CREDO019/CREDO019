package com.google.android.gms.internal.ads;

/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
final class zzfpm extends zzfpy {
    private String zza;
    private String zzb;

    @Override // com.google.android.gms.internal.ads.zzfpy
    public final zzfpy zza(String str) {
        this.zzb = str;
        return this;
    }

    @Override // com.google.android.gms.internal.ads.zzfpy
    public final zzfpy zzb(String str) {
        this.zza = str;
        return this;
    }

    @Override // com.google.android.gms.internal.ads.zzfpy
    public final zzfpz zzc() {
        return new zzfpo(this.zza, this.zzb, null);
    }
}
