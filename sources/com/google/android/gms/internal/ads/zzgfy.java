package com.google.android.gms.internal.ads;

/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
public final class zzgfy extends zzgon implements zzgpy {
    private static final zzgfy zzb;
    private zzggb zze;
    private int zzf;

    static {
        zzgfy zzgfyVar = new zzgfy();
        zzb = zzgfyVar;
        zzgon.zzaP(zzgfy.class, zzgfyVar);
    }

    private zzgfy() {
    }

    public static zzgfx zzc() {
        return (zzgfx) zzb.zzay();
    }

    public static zzgfy zze() {
        return zzb;
    }

    public static zzgfy zzf(zzgnf zzgnfVar, zzgnz zzgnzVar) throws zzgoz {
        return (zzgfy) zzgon.zzaD(zzb, zzgnfVar, zzgnzVar);
    }

    public static /* synthetic */ void zzh(zzgfy zzgfyVar, zzggb zzggbVar) {
        zzggbVar.getClass();
        zzgfyVar.zze = zzggbVar;
    }

    public final int zza() {
        return this.zzf;
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
                    return new zzgfx(null);
                }
                return new zzgfy();
            }
            return zzaO(zzb, "\u0000\u0002\u0000\u0000\u0001\u0002\u0002\u0000\u0000\u0000\u0001\t\u0002\u000b", new Object[]{"zze", "zzf"});
        }
        return (byte) 1;
    }

    public final zzggb zzg() {
        zzggb zzggbVar = this.zze;
        return zzggbVar == null ? zzggb.zze() : zzggbVar;
    }
}
