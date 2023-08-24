package com.google.android.gms.internal.ads;

/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
public final class zzgfv extends zzgon implements zzgpy {
    private static final zzgfv zzb;
    private int zze;
    private zzggb zzf;
    private zzgnf zzg = zzgnf.zzb;

    static {
        zzgfv zzgfvVar = new zzgfv();
        zzb = zzgfvVar;
        zzgon.zzaP(zzgfv.class, zzgfvVar);
    }

    private zzgfv() {
    }

    public static zzgfu zzc() {
        return (zzgfu) zzb.zzay();
    }

    public static zzgfv zze() {
        return zzb;
    }

    public static zzgfv zzf(zzgnf zzgnfVar, zzgnz zzgnzVar) throws zzgoz {
        return (zzgfv) zzgon.zzaD(zzb, zzgnfVar, zzgnzVar);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ void zzj(zzgfv zzgfvVar, zzggb zzggbVar) {
        zzggbVar.getClass();
        zzgfvVar.zzf = zzggbVar;
    }

    public final int zza() {
        return this.zze;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.google.android.gms.internal.ads.zzgon
    public final Object zzb(int r3, Object obj, Object obj2) {
        int r32 = r3 - 1;
        if (r32 != 0) {
            if (r32 != 2) {
                if (r32 != 3) {
                    if (r32 != 4) {
                        if (r32 != 5) {
                            return null;
                        }
                        return zzb;
                    }
                    return new zzgfu(null);
                }
                return new zzgfv();
            }
            return zzaO(zzb, "\u0000\u0003\u0000\u0000\u0001\u0003\u0003\u0000\u0000\u0000\u0001\u000b\u0002\t\u0003\n", new Object[]{"zze", "zzf", "zzg"});
        }
        return (byte) 1;
    }

    public final zzggb zzg() {
        zzggb zzggbVar = this.zzf;
        return zzggbVar == null ? zzggb.zze() : zzggbVar;
    }

    public final zzgnf zzh() {
        return this.zzg;
    }
}
