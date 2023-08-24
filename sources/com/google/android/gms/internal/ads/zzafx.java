package com.google.android.gms.internal.ads;

/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
final class zzafx implements zzaai {
    final /* synthetic */ zzafy zza;

    /* JADX INFO: Access modifiers changed from: package-private */
    public /* synthetic */ zzafx(zzafy zzafyVar, zzafw zzafwVar) {
        this.zza = zzafyVar;
    }

    @Override // com.google.android.gms.internal.ads.zzaai
    public final long zze() {
        zzagk zzagkVar;
        long j;
        zzafy zzafyVar = this.zza;
        zzagkVar = zzafyVar.zzd;
        j = zzafyVar.zzf;
        return zzagkVar.zzf(j);
    }

    @Override // com.google.android.gms.internal.ads.zzaai
    public final zzaag zzg(long j) {
        zzagk zzagkVar;
        long j2;
        long j3;
        long j4;
        long j5;
        long j6;
        long j7;
        zzafy zzafyVar = this.zza;
        zzagkVar = zzafyVar.zzd;
        long zzg = zzagkVar.zzg(j);
        j2 = zzafyVar.zzb;
        j3 = zzafyVar.zzc;
        j4 = zzafyVar.zzb;
        j5 = zzafyVar.zzf;
        j6 = zzafyVar.zzb;
        j7 = zzafyVar.zzc;
        zzaaj zzaajVar = new zzaaj(j, zzel.zzr((-30000) + j2 + ((zzg * (j3 - j4)) / j5), j6, j7 - 1));
        return new zzaag(zzaajVar, zzaajVar);
    }

    @Override // com.google.android.gms.internal.ads.zzaai
    public final boolean zzh() {
        return true;
    }
}
