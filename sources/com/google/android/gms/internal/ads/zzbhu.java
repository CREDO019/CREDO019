package com.google.android.gms.internal.ads;

/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
public final class zzbhu extends zzgon implements zzgpy {
    private static final zzbhu zzb;
    private int zze;
    private zzbhl zzf;
    private int zzg = 1000;
    private zzbhs zzh;
    private zzbhj zzi;

    static {
        zzbhu zzbhuVar = new zzbhu();
        zzb = zzbhuVar;
        zzgon.zzaP(zzbhu.class, zzbhuVar);
    }

    private zzbhu() {
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
                    return new zzbht(null);
                }
                return new zzbhu();
            }
            return zzaO(zzb, "\u0001\u0004\u0000\u0001\u0001\u0004\u0004\u0000\u0000\u0000\u0001ဉ\u0000\u0002ဌ\u0001\u0003ဉ\u0002\u0004ဉ\u0003", new Object[]{"zze", "zzf", "zzg", zzbfy.zza, "zzh", "zzi"});
        }
        return (byte) 1;
    }
}
