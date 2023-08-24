package com.google.android.gms.internal.ads;

/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
public final class zzarr extends zzgon implements zzgpy {
    private static final zzarr zzb;
    private int zze;
    private zzaru zzf;
    private zzgnf zzg = zzgnf.zzb;
    private zzgnf zzh = zzgnf.zzb;

    static {
        zzarr zzarrVar = new zzarr();
        zzb = zzarrVar;
        zzgon.zzaP(zzarr.class, zzarrVar);
    }

    private zzarr() {
    }

    public static zzarr zzc(zzgnf zzgnfVar, zzgnz zzgnzVar) throws zzgoz {
        return (zzarr) zzgon.zzaD(zzb, zzgnfVar, zzgnzVar);
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
                    return new zzarq(null);
                }
                return new zzarr();
            }
            return zzaO(zzb, "\u0001\u0003\u0000\u0001\u0001\u0003\u0003\u0000\u0000\u0000\u0001ဉ\u0000\u0002ည\u0001\u0003ည\u0002", new Object[]{"zze", "zzf", "zzg", "zzh"});
        }
        return (byte) 1;
    }

    public final zzaru zzd() {
        zzaru zzaruVar = this.zzf;
        return zzaruVar == null ? zzaru.zzg() : zzaruVar;
    }

    public final zzgnf zze() {
        return this.zzh;
    }

    public final zzgnf zzf() {
        return this.zzg;
    }
}
