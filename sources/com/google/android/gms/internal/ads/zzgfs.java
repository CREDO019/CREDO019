package com.google.android.gms.internal.ads;

/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
public final class zzgfs extends zzgon implements zzgpy {
    private static final zzgfs zzb;
    private zzgfy zze;
    private zzgim zzf;

    static {
        zzgfs zzgfsVar = new zzgfs();
        zzb = zzgfsVar;
        zzgon.zzaP(zzgfs.class, zzgfsVar);
    }

    private zzgfs() {
    }

    public static zzgfr zza() {
        return (zzgfr) zzb.zzay();
    }

    public static zzgfs zzd(zzgnf zzgnfVar, zzgnz zzgnzVar) throws zzgoz {
        return (zzgfs) zzgon.zzaD(zzb, zzgnfVar, zzgnzVar);
    }

    public static /* synthetic */ void zzg(zzgfs zzgfsVar, zzgfy zzgfyVar) {
        zzgfyVar.getClass();
        zzgfsVar.zze = zzgfyVar;
    }

    public static /* synthetic */ void zzh(zzgfs zzgfsVar, zzgim zzgimVar) {
        zzgimVar.getClass();
        zzgfsVar.zzf = zzgimVar;
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
                    return new zzgfr(null);
                }
                return new zzgfs();
            }
            return zzaO(zzb, "\u0000\u0002\u0000\u0000\u0001\u0002\u0002\u0000\u0000\u0000\u0001\t\u0002\t", new Object[]{"zze", "zzf"});
        }
        return (byte) 1;
    }

    public final zzgfy zze() {
        zzgfy zzgfyVar = this.zze;
        return zzgfyVar == null ? zzgfy.zze() : zzgfyVar;
    }

    public final zzgim zzf() {
        zzgim zzgimVar = this.zzf;
        return zzgimVar == null ? zzgim.zze() : zzgimVar;
    }
}
