package com.google.android.gms.internal.ads;

/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
final class zzfpv extends zzfqo {
    private String zza;
    private String zzb;

    @Override // com.google.android.gms.internal.ads.zzfqo
    public final zzfqo zza(String str) {
        this.zzb = str;
        return this;
    }

    @Override // com.google.android.gms.internal.ads.zzfqo
    public final zzfqo zzb(String str) {
        this.zza = str;
        return this;
    }

    @Override // com.google.android.gms.internal.ads.zzfqo
    public final zzfqp zzc() {
        return new zzfpx(this.zza, this.zzb, null);
    }
}
