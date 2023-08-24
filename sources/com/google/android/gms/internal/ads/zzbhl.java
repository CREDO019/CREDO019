package com.google.android.gms.internal.ads;

/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
public final class zzbhl extends zzgon implements zzgpy {
    private static final zzbhl zzb;
    private int zze;
    private int zzf;
    private int zzg;
    private int zzh;

    static {
        zzbhl zzbhlVar = new zzbhl();
        zzb = zzbhlVar;
        zzgon.zzaP(zzbhl.class, zzbhlVar);
    }

    private zzbhl() {
    }

    public static zzbhk zza() {
        return (zzbhk) zzb.zzay();
    }

    public static /* synthetic */ void zzd(zzbhl zzbhlVar, int r2) {
        zzbhlVar.zze |= 1;
        zzbhlVar.zzf = r2;
    }

    public static /* synthetic */ void zze(zzbhl zzbhlVar, int r2) {
        zzbhlVar.zze |= 2;
        zzbhlVar.zzg = r2;
    }

    public static /* synthetic */ void zzf(zzbhl zzbhlVar, int r2) {
        zzbhlVar.zze |= 4;
        zzbhlVar.zzh = r2;
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
                    return new zzbhk(null);
                }
                return new zzbhl();
            }
            return zzaO(zzb, "\u0001\u0003\u0000\u0001\u0001\u0003\u0003\u0000\u0000\u0000\u0001င\u0000\u0002င\u0001\u0003င\u0002", new Object[]{"zze", "zzf", "zzg", "zzh"});
        }
        return (byte) 1;
    }
}
