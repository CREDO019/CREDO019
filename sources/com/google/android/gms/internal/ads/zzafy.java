package com.google.android.gms.internal.ads;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
public final class zzafy implements zzagf {
    private final zzage zza;
    private final long zzb;
    private final long zzc;
    private final zzagk zzd;
    private int zze;
    private long zzf;
    private long zzg;
    private long zzh;
    private long zzi;
    private long zzj;
    private long zzk;
    private long zzl;

    public zzafy(zzagk zzagkVar, long j, long j2, long j3, long j4, boolean z) {
        zzdd.zzd(j >= 0 && j2 > j);
        this.zzd = zzagkVar;
        this.zzb = j;
        this.zzc = j2;
        if (j3 == j2 - j || z) {
            this.zzf = j4;
            this.zze = 4;
        } else {
            this.zze = 0;
        }
        this.zza = new zzage();
    }

    @Override // com.google.android.gms.internal.ads.zzagf
    public final /* bridge */ /* synthetic */ zzaai zze() {
        if (this.zzf != 0) {
            return new zzafx(this, null);
        }
        return null;
    }

    @Override // com.google.android.gms.internal.ads.zzagf
    public final void zzg(long j) {
        this.zzh = zzel.zzr(j, 0L, this.zzf - 1);
        this.zze = 2;
        this.zzi = this.zzb;
        this.zzj = this.zzc;
        this.zzk = 0L;
        this.zzl = this.zzf;
    }

    /* JADX WARN: Removed duplicated region for block: B:37:0x00a8 A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:38:0x00a9  */
    @Override // com.google.android.gms.internal.ads.zzagf
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final long zzd(com.google.android.gms.internal.ads.zzzg r24) throws java.io.IOException {
        /*
            Method dump skipped, instructions count: 358
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.ads.zzafy.zzd(com.google.android.gms.internal.ads.zzzg):long");
    }
}
