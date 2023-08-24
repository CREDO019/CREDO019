package com.google.android.gms.internal.ads;

/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
public final class zzghw extends zzgon implements zzgpy {
    private static final zzghw zzb;
    private int zze;
    private zzghz zzf;
    private zzgnf zzg = zzgnf.zzb;

    static {
        zzghw zzghwVar = new zzghw();
        zzb = zzghwVar;
        zzgon.zzaP(zzghw.class, zzghwVar);
    }

    private zzghw() {
    }

    public static zzghv zzc() {
        return (zzghv) zzb.zzay();
    }

    public static zzghw zze(zzgnf zzgnfVar, zzgnz zzgnzVar) throws zzgoz {
        return (zzghw) zzgon.zzaD(zzb, zzgnfVar, zzgnzVar);
    }

    public static /* synthetic */ void zzi(zzghw zzghwVar, zzghz zzghzVar) {
        zzghzVar.getClass();
        zzghwVar.zzf = zzghzVar;
    }

    public final int zza() {
        return this.zze;
    }

    @Override // com.google.android.gms.internal.ads.zzgon
    public final Object zzb(int r3, Object obj, Object obj2) {
        int r32 = r3 - 1;
        if (r32 != 0) {
            if (r32 != 2) {
                if (r32 != 3) {
                    if (r32 != 4) {
                        if (r32 != 5) {
                            return null;
                        }
                        return zzb;
                    }
                    return new zzghv(null);
                }
                return new zzghw();
            }
            return zzaO(zzb, "\u0000\u0003\u0000\u0000\u0001\u0003\u0003\u0000\u0000\u0000\u0001\u000b\u0002\t\u0003\n", new Object[]{"zze", "zzf", "zzg"});
        }
        return (byte) 1;
    }

    public final zzghz zzf() {
        zzghz zzghzVar = this.zzf;
        return zzghzVar == null ? zzghz.zzf() : zzghzVar;
    }

    public final zzgnf zzg() {
        return this.zzg;
    }
}
