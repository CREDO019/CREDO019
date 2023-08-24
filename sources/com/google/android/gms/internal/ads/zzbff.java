package com.google.android.gms.internal.ads;

/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
public final class zzbff extends zzgon implements zzgpy {
    private static final zzbff zzb;
    private int zze;
    private boolean zzf;
    private boolean zzg;
    private int zzh;

    static {
        zzbff zzbffVar = new zzbff();
        zzb = zzbffVar;
        zzgon.zzaP(zzbff.class, zzbffVar);
    }

    private zzbff() {
    }

    public static zzbfe zza() {
        return (zzbfe) zzb.zzay();
    }

    public static /* synthetic */ void zzd(zzbff zzbffVar, boolean z) {
        zzbffVar.zze |= 1;
        zzbffVar.zzf = z;
    }

    public static /* synthetic */ void zze(zzbff zzbffVar, boolean z) {
        zzbffVar.zze |= 2;
        zzbffVar.zzg = z;
    }

    public static /* synthetic */ void zzf(zzbff zzbffVar, int r2) {
        zzbffVar.zze |= 4;
        zzbffVar.zzh = r2;
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
                    return new zzbfe(null);
                }
                return new zzbff();
            }
            return zzaO(zzb, "\u0001\u0003\u0000\u0001\u0001\u0003\u0003\u0000\u0000\u0000\u0001ဇ\u0000\u0002ဇ\u0001\u0003ဋ\u0002", new Object[]{"zze", "zzf", "zzg", "zzh"});
        }
        return (byte) 1;
    }
}
