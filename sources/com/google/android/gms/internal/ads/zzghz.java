package com.google.android.gms.internal.ads;

/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
public final class zzghz extends zzgon implements zzgpy {
    private static final zzghz zzb;
    private int zze;
    private zzght zzf;
    private zzgnf zzg = zzgnf.zzb;
    private zzgnf zzh = zzgnf.zzb;

    static {
        zzghz zzghzVar = new zzghz();
        zzb = zzghzVar;
        zzgon.zzaP(zzghz.class, zzghzVar);
    }

    private zzghz() {
    }

    public static zzghy zzd() {
        return (zzghy) zzb.zzay();
    }

    public static zzghz zzf() {
        return zzb;
    }

    public static zzghz zzg(zzgnf zzgnfVar, zzgnz zzgnzVar) throws zzgoz {
        return (zzghz) zzgon.zzaD(zzb, zzgnfVar, zzgnzVar);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ void zzk(zzghz zzghzVar, zzght zzghtVar) {
        zzghtVar.getClass();
        zzghzVar.zzf = zzghtVar;
    }

    public final int zza() {
        return this.zze;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.google.android.gms.internal.ads.zzgon
    public final Object zzb(int r4, Object obj, Object obj2) {
        int r42 = r4 - 1;
        if (r42 != 0) {
            if (r42 != 2) {
                if (r42 != 3) {
                    if (r42 != 4) {
                        if (r42 != 5) {
                            return null;
                        }
                        return zzb;
                    }
                    return new zzghy(null);
                }
                return new zzghz();
            }
            return zzaO(zzb, "\u0000\u0004\u0000\u0000\u0001\u0004\u0004\u0000\u0000\u0000\u0001\u000b\u0002\t\u0003\n\u0004\n", new Object[]{"zze", "zzf", "zzg", "zzh"});
        }
        return (byte) 1;
    }

    public final zzght zzc() {
        zzght zzghtVar = this.zzf;
        return zzghtVar == null ? zzght.zze() : zzghtVar;
    }

    public final zzgnf zzh() {
        return this.zzg;
    }

    public final zzgnf zzi() {
        return this.zzh;
    }
}
