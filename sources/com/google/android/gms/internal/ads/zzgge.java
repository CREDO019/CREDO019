package com.google.android.gms.internal.ads;

/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
public final class zzgge extends zzgon implements zzgpy {
    private static final zzgge zzb;
    private int zze;
    private zzggk zzf;
    private zzgnf zzg = zzgnf.zzb;

    static {
        zzgge zzggeVar = new zzgge();
        zzb = zzggeVar;
        zzgon.zzaP(zzgge.class, zzggeVar);
    }

    private zzgge() {
    }

    public static zzggd zzc() {
        return (zzggd) zzb.zzay();
    }

    public static zzgge zze(zzgnf zzgnfVar, zzgnz zzgnzVar) throws zzgoz {
        return (zzgge) zzgon.zzaD(zzb, zzgnfVar, zzgnzVar);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ void zzi(zzgge zzggeVar, zzggk zzggkVar) {
        zzggkVar.getClass();
        zzggeVar.zzf = zzggkVar;
    }

    public final int zza() {
        return this.zze;
    }

    /* JADX INFO: Access modifiers changed from: protected */
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
                    return new zzggd(null);
                }
                return new zzgge();
            }
            return zzaO(zzb, "\u0000\u0003\u0000\u0000\u0001\u0003\u0003\u0000\u0000\u0000\u0001\u000b\u0002\t\u0003\n", new Object[]{"zze", "zzf", "zzg"});
        }
        return (byte) 1;
    }

    public final zzggk zzf() {
        zzggk zzggkVar = this.zzf;
        return zzggkVar == null ? zzggk.zze() : zzggkVar;
    }

    public final zzgnf zzg() {
        return this.zzg;
    }
}
