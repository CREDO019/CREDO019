package com.google.android.gms.internal.ads;

/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
public final class zzbie extends zzgon implements zzgpy {
    private static final zzbie zzb;
    private int zze;
    private int zzf = 1000;
    private zzbhs zzg;

    static {
        zzbie zzbieVar = new zzbie();
        zzb = zzbieVar;
        zzgon.zzaP(zzbie.class, zzbieVar);
    }

    private zzbie() {
    }

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
                    return new zzbid(null);
                }
                return new zzbie();
            }
            return zzaO(zzb, "\u0001\u0002\u0000\u0001\u0001\u0002\u0002\u0000\u0000\u0000\u0001ဌ\u0000\u0002ဉ\u0001", new Object[]{"zze", "zzf", zzbfy.zza, "zzg"});
        }
        return (byte) 1;
    }
}
