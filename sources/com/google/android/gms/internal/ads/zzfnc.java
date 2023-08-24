package com.google.android.gms.internal.ads;

/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
public final class zzfnc extends zzgon implements zzgpy {
    private static final zzgot zzb = new zzfmz();
    private static final zzfnc zze;
    private int zzf;
    private zzgos zzg = zzaG();
    private String zzh = "";
    private String zzi = "";
    private String zzj = "";

    static {
        zzfnc zzfncVar = new zzfnc();
        zze = zzfncVar;
        zzgon.zzaP(zzfnc.class, zzfncVar);
    }

    private zzfnc() {
    }

    public static zzfnb zza() {
        return (zzfnb) zze.zzay();
    }

    public static /* synthetic */ void zzd(zzfnc zzfncVar, String str) {
        str.getClass();
        zzfncVar.zzf |= 1;
        zzfncVar.zzh = str;
    }

    public static /* synthetic */ void zze(zzfnc zzfncVar, int r2) {
        zzgos zzgosVar = zzfncVar.zzg;
        if (!zzgosVar.zzc()) {
            zzfncVar.zzg = zzgon.zzaH(zzgosVar);
        }
        zzfncVar.zzg.zzh(2);
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
                        return zze;
                    }
                    return new zzfnb(null);
                }
                return new zzfnc();
            }
            return zzaO(zze, "\u0001\u0004\u0000\u0001\u0001\u0004\u0004\u0000\u0001\u0000\u0001\u001e\u0002ဈ\u0000\u0003ဈ\u0001\u0004ဈ\u0002", new Object[]{"zzf", "zzg", zzfna.zza, "zzh", "zzi", "zzj"});
        }
        return (byte) 1;
    }
}
