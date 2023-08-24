package com.google.android.gms.internal.ads;

/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
public final class zzgfp extends zzgon implements zzgpy {
    private static final zzgfp zzb;
    private int zze;
    private zzgfv zzf;
    private zzgij zzg;

    static {
        zzgfp zzgfpVar = new zzgfp();
        zzb = zzgfpVar;
        zzgon.zzaP(zzgfp.class, zzgfpVar);
    }

    private zzgfp() {
    }

    public static zzgfo zzc() {
        return (zzgfo) zzb.zzay();
    }

    public static zzgfp zze(zzgnf zzgnfVar, zzgnz zzgnzVar) throws zzgoz {
        return (zzgfp) zzgon.zzaD(zzb, zzgnfVar, zzgnzVar);
    }

    public static /* synthetic */ void zzi(zzgfp zzgfpVar, zzgfv zzgfvVar) {
        zzgfvVar.getClass();
        zzgfpVar.zzf = zzgfvVar;
    }

    public static /* synthetic */ void zzj(zzgfp zzgfpVar, zzgij zzgijVar) {
        zzgijVar.getClass();
        zzgfpVar.zzg = zzgijVar;
    }

    public final int zza() {
        return this.zze;
    }

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
                    return new zzgfo(null);
                }
                return new zzgfp();
            }
            return zzaO(zzb, "\u0000\u0003\u0000\u0000\u0001\u0003\u0003\u0000\u0000\u0000\u0001\u000b\u0002\t\u0003\t", new Object[]{"zze", "zzf", "zzg"});
        }
        return (byte) 1;
    }

    public final zzgfv zzf() {
        zzgfv zzgfvVar = this.zzf;
        return zzgfvVar == null ? zzgfv.zze() : zzgfvVar;
    }

    public final zzgij zzg() {
        zzgij zzgijVar = this.zzg;
        return zzgijVar == null ? zzgij.zze() : zzgijVar;
    }
}
