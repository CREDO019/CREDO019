package com.google.android.gms.internal.ads;

/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
public final class zzgks extends zzgon implements zzgpy {
    private static final zzgks zzb;
    private int zze;
    private zzgnf zzf = zzgnf.zzb;

    static {
        zzgks zzgksVar = new zzgks();
        zzb = zzgksVar;
        zzgon.zzaP(zzgks.class, zzgksVar);
    }

    private zzgks() {
    }

    public static zzgkr zzc() {
        return (zzgkr) zzb.zzay();
    }

    public static zzgks zze(zzgnf zzgnfVar, zzgnz zzgnzVar) throws zzgoz {
        return (zzgks) zzgon.zzaD(zzb, zzgnfVar, zzgnzVar);
    }

    public final int zza() {
        return this.zze;
    }

    /* JADX INFO: Access modifiers changed from: protected */
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
                    return new zzgkr(null);
                }
                return new zzgks();
            }
            return zzaO(zzb, "\u0000\u0002\u0000\u0000\u0001\u0003\u0002\u0000\u0000\u0000\u0001\u000b\u0003\n", new Object[]{"zze", "zzf"});
        }
        return (byte) 1;
    }

    public final zzgnf zzf() {
        return this.zzf;
    }
}
