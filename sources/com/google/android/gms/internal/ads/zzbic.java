package com.google.android.gms.internal.ads;

/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
public final class zzbic extends zzgon implements zzgpy {
    private static final zzbic zzb;
    private int zze;
    private int zzf = 1000;
    private zzbhs zzg;
    private zzbhj zzh;

    static {
        zzbic zzbicVar = new zzbic();
        zzb = zzbicVar;
        zzgon.zzaP(zzbic.class, zzbicVar);
    }

    private zzbic() {
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
                    return new zzbib(null);
                }
                return new zzbic();
            }
            return zzaO(zzb, "\u0001\u0003\u0000\u0001\u0001\u0003\u0003\u0000\u0000\u0000\u0001ဌ\u0000\u0002ဉ\u0001\u0003ဉ\u0002", new Object[]{"zze", "zzf", zzbfy.zza, "zzg", "zzh"});
        }
        return (byte) 1;
    }
}
