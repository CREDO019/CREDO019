package com.google.android.gms.internal.ads;

/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
public final class zzggq extends zzgon implements zzgpy {
    private static final zzggq zzb;
    private int zze;
    private int zzf;

    static {
        zzggq zzggqVar = new zzggq();
        zzb = zzggqVar;
        zzgon.zzaP(zzggq.class, zzggqVar);
    }

    private zzggq() {
    }

    public static zzggp zzc() {
        return (zzggp) zzb.zzay();
    }

    public static zzggq zze(zzgnf zzgnfVar, zzgnz zzgnzVar) throws zzgoz {
        return (zzggq) zzgon.zzaD(zzb, zzgnfVar, zzgnzVar);
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
                    return new zzggp(null);
                }
                return new zzggq();
            }
            return zzaO(zzb, "\u0000\u0002\u0000\u0000\u0002\u0003\u0002\u0000\u0000\u0000\u0002\u000b\u0003\u000b", new Object[]{"zze", "zzf"});
        }
        return (byte) 1;
    }
}
