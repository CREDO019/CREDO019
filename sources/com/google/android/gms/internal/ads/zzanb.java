package com.google.android.gms.internal.ads;

/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
public final class zzanb extends zzgon implements zzgpy {
    private static final zzanb zzb;
    private int zze;
    private zzgnf zzf = zzgnf.zzb;
    private zzgnf zzg;
    private zzgnf zzh;
    private zzgnf zzi;

    static {
        zzanb zzanbVar = new zzanb();
        zzb = zzanbVar;
        zzgon.zzaP(zzanb.class, zzanbVar);
    }

    private zzanb() {
        zzgnf zzgnfVar = zzgnf.zzb;
        this.zzg = zzgnfVar;
        this.zzh = zzgnfVar;
        this.zzi = zzgnfVar;
    }

    public static zzana zza() {
        return (zzana) zzb.zzay();
    }

    public static zzanb zzd(byte[] bArr, zzgnz zzgnzVar) throws zzgoz {
        return (zzanb) zzgon.zzaF(zzb, bArr, zzgnzVar);
    }

    public static /* synthetic */ void zzi(zzanb zzanbVar, zzgnf zzgnfVar) {
        zzanbVar.zze |= 1;
        zzanbVar.zzf = zzgnfVar;
    }

    public static /* synthetic */ void zzj(zzanb zzanbVar, zzgnf zzgnfVar) {
        zzanbVar.zze |= 2;
        zzanbVar.zzg = zzgnfVar;
    }

    public static /* synthetic */ void zzk(zzanb zzanbVar, zzgnf zzgnfVar) {
        zzanbVar.zze |= 4;
        zzanbVar.zzh = zzgnfVar;
    }

    public static /* synthetic */ void zzl(zzanb zzanbVar, zzgnf zzgnfVar) {
        zzanbVar.zze |= 8;
        zzanbVar.zzi = zzgnfVar;
    }

    @Override // com.google.android.gms.internal.ads.zzgon
    public final Object zzb(int r5, Object obj, Object obj2) {
        int r52 = r5 - 1;
        if (r52 != 0) {
            if (r52 != 2) {
                if (r52 != 3) {
                    if (r52 != 4) {
                        if (r52 != 5) {
                            return null;
                        }
                        return zzb;
                    }
                    return new zzana(null);
                }
                return new zzanb();
            }
            return zzaO(zzb, "\u0001\u0004\u0000\u0001\u0001\u0004\u0004\u0000\u0000\u0000\u0001ည\u0000\u0002ည\u0001\u0003ည\u0002\u0004ည\u0003", new Object[]{"zze", "zzf", "zzg", "zzh", "zzi"});
        }
        return (byte) 1;
    }

    public final zzgnf zze() {
        return this.zzf;
    }

    public final zzgnf zzf() {
        return this.zzg;
    }

    public final zzgnf zzg() {
        return this.zzi;
    }

    public final zzgnf zzh() {
        return this.zzh;
    }
}
