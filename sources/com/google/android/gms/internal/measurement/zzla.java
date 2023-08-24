package com.google.android.gms.internal.measurement;

/* compiled from: com.google.android.gms:play-services-measurement-base@@20.1.2 */
/* loaded from: classes3.dex */
final class zzla implements zzlh {
    private final zzlh[] zza;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzla(zzlh... zzlhVarArr) {
        this.zza = zzlhVarArr;
    }

    @Override // com.google.android.gms.internal.measurement.zzlh
    public final zzlg zzb(Class cls) {
        zzlh[] zzlhVarArr = this.zza;
        for (int r1 = 0; r1 < 2; r1++) {
            zzlh zzlhVar = zzlhVarArr[r1];
            if (zzlhVar.zzc(cls)) {
                return zzlhVar.zzb(cls);
            }
        }
        throw new UnsupportedOperationException("No factory is available for message type: ".concat(String.valueOf(cls.getName())));
    }

    @Override // com.google.android.gms.internal.measurement.zzlh
    public final boolean zzc(Class cls) {
        zzlh[] zzlhVarArr = this.zza;
        for (int r2 = 0; r2 < 2; r2++) {
            if (zzlhVarArr[r2].zzc(cls)) {
                return true;
            }
        }
        return false;
    }
}
