package com.google.android.gms.internal.ads;

/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
public final class zzgfj extends zzgon implements zzgpy {
    private static final zzgfj zzb;
    private int zze;
    private zzgfm zzf;

    static {
        zzgfj zzgfjVar = new zzgfj();
        zzb = zzgfjVar;
        zzgon.zzaP(zzgfj.class, zzgfjVar);
    }

    private zzgfj() {
    }

    public static zzgfi zzc() {
        return (zzgfi) zzb.zzay();
    }

    public static zzgfj zze(zzgnf zzgnfVar, zzgnz zzgnzVar) throws zzgoz {
        return (zzgfj) zzgon.zzaD(zzb, zzgnfVar, zzgnzVar);
    }

    public static /* synthetic */ void zzh(zzgfj zzgfjVar, zzgfm zzgfmVar) {
        zzgfmVar.getClass();
        zzgfjVar.zzf = zzgfmVar;
    }

    public final int zza() {
        return this.zze;
    }

    @Override // com.google.android.gms.internal.ads.zzgon
    public final Object zzb(int r2, Object obj, Object obj2) {
        int r22 = r2 - 1;
        if (r22 != 0) {
            if (r22 != 2) {
                if (r22 != 3) {
                    if (r22 != 4) {
                        if (r22 != 5) {
                            return null;
                        }
                        return zzb;
                    }
                    return new zzgfi(null);
                }
                return new zzgfj();
            }
            return zzaO(zzb, "\u0000\u0002\u0000\u0000\u0001\u0002\u0002\u0000\u0000\u0000\u0001\u000b\u0002\t", new Object[]{"zze", "zzf"});
        }
        return (byte) 1;
    }

    public final zzgfm zzf() {
        zzgfm zzgfmVar = this.zzf;
        return zzgfmVar == null ? zzgfm.zze() : zzgfmVar;
    }
}
