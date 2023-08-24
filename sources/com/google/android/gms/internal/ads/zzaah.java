package com.google.android.gms.internal.ads;

/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
public class zzaah implements zzaai {
    private final long zza;
    private final zzaag zzb;

    public zzaah(long j, long j2) {
        this.zza = j;
        zzaaj zzaajVar = j2 == 0 ? zzaaj.zza : new zzaaj(0L, j2);
        this.zzb = new zzaag(zzaajVar, zzaajVar);
    }

    @Override // com.google.android.gms.internal.ads.zzaai
    public final long zze() {
        return this.zza;
    }

    @Override // com.google.android.gms.internal.ads.zzaai
    public final zzaag zzg(long j) {
        return this.zzb;
    }

    @Override // com.google.android.gms.internal.ads.zzaai
    public final boolean zzh() {
        return false;
    }
}
