package com.google.android.gms.internal.ads;

/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
public final class zzgke extends zzgon implements zzgpy {
    private static final zzgke zzb;
    private String zze = "";

    static {
        zzgke zzgkeVar = new zzgke();
        zzb = zzgkeVar;
        zzgon.zzaP(zzgke.class, zzgkeVar);
    }

    private zzgke() {
    }

    public static zzgke zzc() {
        return zzb;
    }

    public static zzgke zzd(zzgnf zzgnfVar, zzgnz zzgnzVar) throws zzgoz {
        return (zzgke) zzgon.zzaD(zzb, zzgnfVar, zzgnzVar);
    }

    /* JADX INFO: Access modifiers changed from: protected */
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
                    return new zzgkd(null);
                }
                return new zzgke();
            }
            return zzaO(zzb, "\u0000\u0001\u0000\u0000\u0001\u0001\u0001\u0000\u0000\u0000\u0001Ȉ", new Object[]{"zze"});
        }
        return (byte) 1;
    }

    public final String zze() {
        return this.zze;
    }
}
