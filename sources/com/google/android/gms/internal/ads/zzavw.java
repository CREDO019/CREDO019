package com.google.android.gms.internal.ads;

/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
final class zzavw implements zzavu {
    private final int zza;
    private final int zzb;
    private final zzbag zzc;

    public zzavw(zzavr zzavrVar) {
        zzbag zzbagVar = zzavrVar.zza;
        this.zzc = zzbagVar;
        zzbagVar.zzv(12);
        this.zza = zzbagVar.zzi();
        this.zzb = zzbagVar.zzi();
    }

    @Override // com.google.android.gms.internal.ads.zzavu
    public final int zza() {
        return this.zzb;
    }

    @Override // com.google.android.gms.internal.ads.zzavu
    public final int zzb() {
        int r0 = this.zza;
        return r0 == 0 ? this.zzc.zzi() : r0;
    }

    @Override // com.google.android.gms.internal.ads.zzavu
    public final boolean zzc() {
        return this.zza != 0;
    }
}
