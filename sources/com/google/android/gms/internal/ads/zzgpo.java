package com.google.android.gms.internal.ads;

/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
final class zzgpo implements zzgpv {
    private final zzgpv[] zza;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzgpo(zzgpv... zzgpvVarArr) {
        this.zza = zzgpvVarArr;
    }

    @Override // com.google.android.gms.internal.ads.zzgpv
    public final zzgpu zzb(Class cls) {
        zzgpv[] zzgpvVarArr = this.zza;
        for (int r1 = 0; r1 < 2; r1++) {
            zzgpv zzgpvVar = zzgpvVarArr[r1];
            if (zzgpvVar.zzc(cls)) {
                return zzgpvVar.zzb(cls);
            }
        }
        throw new UnsupportedOperationException("No factory is available for message type: ".concat(String.valueOf(cls.getName())));
    }

    @Override // com.google.android.gms.internal.ads.zzgpv
    public final boolean zzc(Class cls) {
        zzgpv[] zzgpvVarArr = this.zza;
        for (int r2 = 0; r2 < 2; r2++) {
            if (zzgpvVarArr[r2].zzc(cls)) {
                return true;
            }
        }
        return false;
    }
}
