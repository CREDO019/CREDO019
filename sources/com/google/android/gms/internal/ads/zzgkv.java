package com.google.android.gms.internal.ads;

/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
public final class zzgkv extends zzgon implements zzgpy {
    private static final zzgkv zzb;
    private int zze;

    static {
        zzgkv zzgkvVar = new zzgkv();
        zzb = zzgkvVar;
        zzgon.zzaP(zzgkv.class, zzgkvVar);
    }

    private zzgkv() {
    }

    public static zzgkv zzc() {
        return zzb;
    }

    public static zzgkv zzd(zzgnf zzgnfVar, zzgnz zzgnzVar) throws zzgoz {
        return (zzgkv) zzgon.zzaD(zzb, zzgnfVar, zzgnzVar);
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
                    return new zzgku(null);
                }
                return new zzgkv();
            }
            return zzaO(zzb, "\u0000\u0001\u0000\u0000\u0001\u0001\u0001\u0000\u0000\u0000\u0001\u000b", new Object[]{"zze"});
        }
        return (byte) 1;
    }
}
