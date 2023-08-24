package com.google.android.gms.internal.ads;

/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
public final class zzghc extends zzgon implements zzgpy {
    private static final zzghc zzb;
    private int zze;
    private int zzf;

    static {
        zzghc zzghcVar = new zzghc();
        zzb = zzghcVar;
        zzgon.zzaP(zzghc.class, zzghcVar);
    }

    private zzghc() {
    }

    public static zzghb zzc() {
        return (zzghb) zzb.zzay();
    }

    public static zzghc zze(zzgnf zzgnfVar, zzgnz zzgnzVar) throws zzgoz {
        return (zzghc) zzgon.zzaD(zzb, zzgnfVar, zzgnzVar);
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
                    return new zzghb(null);
                }
                return new zzghc();
            }
            return zzaO(zzb, "\u0000\u0002\u0000\u0000\u0001\u0002\u0002\u0000\u0000\u0000\u0001\u000b\u0002\u000b", new Object[]{"zze", "zzf"});
        }
        return (byte) 1;
    }
}
