package com.google.android.gms.internal.ads;

/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
final class zzabx implements zzaai {
    final /* synthetic */ zzaai zza;
    final /* synthetic */ zzaby zzb;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzabx(zzaby zzabyVar, zzaai zzaaiVar) {
        this.zzb = zzabyVar;
        this.zza = zzaaiVar;
    }

    @Override // com.google.android.gms.internal.ads.zzaai
    public final long zze() {
        return this.zza.zze();
    }

    @Override // com.google.android.gms.internal.ads.zzaai
    public final zzaag zzg(long j) {
        long j2;
        long j3;
        zzaag zzg = this.zza.zzg(j);
        zzaaj zzaajVar = zzg.zza;
        long j4 = zzaajVar.zzb;
        long j5 = zzaajVar.zzc;
        j2 = this.zzb.zzb;
        zzaaj zzaajVar2 = new zzaaj(j4, j5 + j2);
        zzaaj zzaajVar3 = zzg.zzb;
        long j6 = zzaajVar3.zzb;
        long j7 = zzaajVar3.zzc;
        j3 = this.zzb.zzb;
        return new zzaag(zzaajVar2, new zzaaj(j6, j7 + j3));
    }

    @Override // com.google.android.gms.internal.ads.zzaai
    public final boolean zzh() {
        return this.zza.zzh();
    }
}
