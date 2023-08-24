package com.google.android.gms.internal.ads;

/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
public final class zzghi extends zzgon implements zzgpy {
    private static final zzghi zzb;

    static {
        zzghi zzghiVar = new zzghi();
        zzb = zzghiVar;
        zzgon.zzaP(zzghi.class, zzghiVar);
    }

    private zzghi() {
    }

    public static zzghi zzc() {
        return zzb;
    }

    public static zzghi zzd(zzgnf zzgnfVar, zzgnz zzgnzVar) throws zzgoz {
        return (zzghi) zzgon.zzaD(zzb, zzgnfVar, zzgnzVar);
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
                    return new zzghh(null);
                }
                return new zzghi();
            }
            return zzaO(zzb, "\u0000\u0000", null);
        }
        return (byte) 1;
    }
}
