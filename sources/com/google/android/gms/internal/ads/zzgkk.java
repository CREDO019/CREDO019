package com.google.android.gms.internal.ads;

/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
public final class zzgkk extends zzgon implements zzgpy {
    private static final zzgkk zzb;
    private String zze = "";
    private zzgjl zzf;

    static {
        zzgkk zzgkkVar = new zzgkk();
        zzb = zzgkkVar;
        zzgon.zzaP(zzgkk.class, zzgkkVar);
    }

    private zzgkk() {
    }

    public static zzgkk zzd() {
        return zzb;
    }

    public static zzgkk zze(zzgnf zzgnfVar, zzgnz zzgnzVar) throws zzgoz {
        return (zzgkk) zzgon.zzaD(zzb, zzgnfVar, zzgnzVar);
    }

    public final zzgjl zza() {
        zzgjl zzgjlVar = this.zzf;
        return zzgjlVar == null ? zzgjl.zzd() : zzgjlVar;
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
                    return new zzgkj(null);
                }
                return new zzgkk();
            }
            return zzaO(zzb, "\u0000\u0002\u0000\u0000\u0001\u0002\u0002\u0000\u0000\u0000\u0001Ȉ\u0002\t", new Object[]{"zze", "zzf"});
        }
        return (byte) 1;
    }

    public final String zzf() {
        return this.zze;
    }

    public final boolean zzg() {
        return this.zzf != null;
    }
}
