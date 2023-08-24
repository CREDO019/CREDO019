package com.google.android.gms.internal.ads;

/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
public final class zzggw extends zzgon implements zzgpy {
    private static final zzggw zzb;
    private int zze;
    private int zzf;

    static {
        zzggw zzggwVar = new zzggw();
        zzb = zzggwVar;
        zzgon.zzaP(zzggw.class, zzggwVar);
    }

    private zzggw() {
    }

    public static zzggv zzc() {
        return (zzggv) zzb.zzay();
    }

    public static zzggw zze(zzgnf zzgnfVar, zzgnz zzgnzVar) throws zzgoz {
        return (zzggw) zzgon.zzaD(zzb, zzgnfVar, zzgnzVar);
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
                    return new zzggv(null);
                }
                return new zzggw();
            }
            return zzaO(zzb, "\u0000\u0002\u0000\u0000\u0001\u0002\u0002\u0000\u0000\u0000\u0001\u000b\u0002\u000b", new Object[]{"zzf", "zze"});
        }
        return (byte) 1;
    }
}
