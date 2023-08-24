package com.google.android.gms.internal.ads;

/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
public final class zzgtb extends zzgon implements zzgpy {
    private static final zzgtb zzb;
    private int zze;
    private int zzf;
    private String zzg = "";
    private zzgnf zzh = zzgnf.zzb;
    private zzgnf zzi = zzgnf.zzb;

    static {
        zzgtb zzgtbVar = new zzgtb();
        zzb = zzgtbVar;
        zzgon.zzaP(zzgtb.class, zzgtbVar);
    }

    private zzgtb() {
    }

    public static zzgsz zza() {
        return (zzgsz) zzb.zzay();
    }

    public static /* synthetic */ void zzd(zzgtb zzgtbVar, String str) {
        zzgtbVar.zze |= 2;
        zzgtbVar.zzg = "image/png";
    }

    public static /* synthetic */ void zze(zzgtb zzgtbVar, zzgnf zzgnfVar) {
        zzgnfVar.getClass();
        zzgtbVar.zze |= 4;
        zzgtbVar.zzh = zzgnfVar;
    }

    public static /* synthetic */ void zzf(zzgtb zzgtbVar, int r2) {
        zzgtbVar.zzf = 1;
        zzgtbVar.zze = 1 | zzgtbVar.zze;
    }

    @Override // com.google.android.gms.internal.ads.zzgon
    public final Object zzb(int r6, Object obj, Object obj2) {
        int r62 = r6 - 1;
        if (r62 != 0) {
            if (r62 != 2) {
                if (r62 != 3) {
                    if (r62 != 4) {
                        if (r62 != 5) {
                            return null;
                        }
                        return zzb;
                    }
                    return new zzgsz(null);
                }
                return new zzgtb();
            }
            return zzaO(zzb, "\u0001\u0004\u0000\u0001\u0001\u0004\u0004\u0000\u0000\u0000\u0001ဌ\u0000\u0002ဈ\u0001\u0003ည\u0002\u0004ည\u0003", new Object[]{"zze", "zzf", zzgta.zza, "zzg", "zzh", "zzi"});
        }
        return (byte) 1;
    }
}
