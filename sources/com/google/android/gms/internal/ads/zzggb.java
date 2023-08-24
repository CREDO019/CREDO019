package com.google.android.gms.internal.ads;

/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
public final class zzggb extends zzgon implements zzgpy {
    private static final zzggb zzb;
    private int zze;

    static {
        zzggb zzggbVar = new zzggb();
        zzb = zzggbVar;
        zzgon.zzaP(zzggb.class, zzggbVar);
    }

    private zzggb() {
    }

    public static zzgga zzc() {
        return (zzgga) zzb.zzay();
    }

    public static zzggb zze() {
        return zzb;
    }

    public final int zza() {
        return this.zze;
    }

    @Override // com.google.android.gms.internal.ads.zzgon
    public final Object zzb(int r1, Object obj, Object obj2) {
        int r12 = r1 - 1;
        if (r12 != 0) {
            if (r12 != 2) {
                if (r12 != 3) {
                    if (r12 != 4) {
                        if (r12 != 5) {
                            return null;
                        }
                        return zzb;
                    }
                    return new zzgga(null);
                }
                return new zzggb();
            }
            return zzaO(zzb, "\u0000\u0001\u0000\u0000\u0001\u0001\u0001\u0000\u0000\u0000\u0001\u000b", new Object[]{"zze"});
        }
        return (byte) 1;
    }
}
