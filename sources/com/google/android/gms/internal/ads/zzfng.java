package com.google.android.gms.internal.ads;

/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
public final class zzfng extends zzgon implements zzgpy {
    private static final zzfng zzb;
    private int zze;
    private int zzf;
    private String zzg = "";
    private String zzh = "";
    private zzfnc zzi;

    static {
        zzfng zzfngVar = new zzfng();
        zzb = zzfngVar;
        zzgon.zzaP(zzfng.class, zzfngVar);
    }

    private zzfng() {
    }

    public static zzfne zza() {
        return (zzfne) zzb.zzay();
    }

    public static /* synthetic */ zzfng zzc() {
        return zzb;
    }

    public static /* synthetic */ void zzd(zzfng zzfngVar, String str) {
        str.getClass();
        zzfngVar.zze |= 2;
        zzfngVar.zzg = str;
    }

    public static /* synthetic */ void zze(zzfng zzfngVar, zzfnc zzfncVar) {
        zzfncVar.getClass();
        zzfngVar.zzi = zzfncVar;
        zzfngVar.zze |= 8;
    }

    public static /* synthetic */ void zzf(zzfng zzfngVar, int r2) {
        zzfngVar.zzf = 1;
        zzfngVar.zze = 1 | zzfngVar.zze;
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
                    return new zzfne(null);
                }
                return new zzfng();
            }
            return zzaO(zzb, "\u0001\u0004\u0000\u0001\u0001\u0004\u0004\u0000\u0000\u0000\u0001ဌ\u0000\u0002ဈ\u0001\u0003ဈ\u0002\u0004ဉ\u0003", new Object[]{"zze", "zzf", zzfnf.zza, "zzg", "zzh", "zzi"});
        }
        return (byte) 1;
    }
}
