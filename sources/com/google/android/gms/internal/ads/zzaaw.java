package com.google.android.gms.internal.ads;

/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
final class zzaaw implements zzaai {
    final /* synthetic */ zzaaz zza;
    private final long zzb;

    public zzaaw(zzaaz zzaazVar, long j) {
        this.zza = zzaazVar;
        this.zzb = j;
    }

    @Override // com.google.android.gms.internal.ads.zzaai
    public final long zze() {
        return this.zzb;
    }

    @Override // com.google.android.gms.internal.ads.zzaai
    public final zzaag zzg(long j) {
        zzabc[] zzabcVarArr;
        zzabc[] zzabcVarArr2;
        zzabc[] zzabcVarArr3;
        zzabcVarArr = this.zza.zzg;
        zzaag zza = zzabcVarArr[0].zza(j);
        int r1 = 1;
        while (true) {
            zzaaz zzaazVar = this.zza;
            zzabcVarArr2 = zzaazVar.zzg;
            if (r1 >= zzabcVarArr2.length) {
                return zza;
            }
            zzabcVarArr3 = zzaazVar.zzg;
            zzaag zza2 = zzabcVarArr3[r1].zza(j);
            if (zza2.zza.zzc < zza.zza.zzc) {
                zza = zza2;
            }
            r1++;
        }
    }

    @Override // com.google.android.gms.internal.ads.zzaai
    public final boolean zzh() {
        return true;
    }
}
