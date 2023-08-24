package com.google.android.gms.internal.vision;

/* compiled from: com.google.android.gms:play-services-vision-common@@19.0.0 */
/* loaded from: classes3.dex */
final class zzhr implements zzhz {
    private zzhz[] zzyl;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzhr(zzhz... zzhzVarArr) {
        this.zzyl = zzhzVarArr;
    }

    @Override // com.google.android.gms.internal.vision.zzhz
    public final boolean zza(Class<?> cls) {
        for (zzhz zzhzVar : this.zzyl) {
            if (zzhzVar.zza(cls)) {
                return true;
            }
        }
        return false;
    }

    @Override // com.google.android.gms.internal.vision.zzhz
    public final zzia zzb(Class<?> cls) {
        zzhz[] zzhzVarArr;
        for (zzhz zzhzVar : this.zzyl) {
            if (zzhzVar.zza(cls)) {
                return zzhzVar.zzb(cls);
            }
        }
        String valueOf = String.valueOf(cls.getName());
        throw new UnsupportedOperationException(valueOf.length() != 0 ? "No factory is available for message type: ".concat(valueOf) : new String("No factory is available for message type: "));
    }
}
