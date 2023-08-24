package com.google.android.gms.internal.ads;

/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
public final class zzbfd extends zzgon implements zzgpy {
    private static final zzbfd zzb;
    private int zze;
    private boolean zzf;
    private int zzg;

    static {
        zzbfd zzbfdVar = new zzbfd();
        zzb = zzbfdVar;
        zzgon.zzaP(zzbfd.class, zzbfdVar);
    }

    private zzbfd() {
    }

    public static zzbfc zza() {
        return (zzbfc) zzb.zzay();
    }

    public static zzbfd zzd() {
        return zzb;
    }

    public static /* synthetic */ void zze(zzbfd zzbfdVar, boolean z) {
        zzbfdVar.zze |= 1;
        zzbfdVar.zzf = z;
    }

    public static /* synthetic */ void zzf(zzbfd zzbfdVar, int r2) {
        zzbfdVar.zze |= 2;
        zzbfdVar.zzg = r2;
    }

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
                    return new zzbfc(null);
                }
                return new zzbfd();
            }
            return zzaO(zzb, "\u0001\u0002\u0000\u0001\u0001\u0002\u0002\u0000\u0000\u0000\u0001ဇ\u0000\u0002ဋ\u0001", new Object[]{"zze", "zzf", "zzg"});
        }
        return (byte) 1;
    }
}
