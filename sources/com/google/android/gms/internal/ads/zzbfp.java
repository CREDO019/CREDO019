package com.google.android.gms.internal.ads;

/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
public final class zzbfp extends zzgon implements zzgpy {
    private static final zzbfp zzb;
    private int zze;
    private String zzf = "";
    private zzgow zzg = zzaJ();
    private int zzh = 1000;
    private int zzi = 1000;
    private int zzj = 1000;

    static {
        zzbfp zzbfpVar = new zzbfp();
        zzb = zzbfpVar;
        zzgon.zzaP(zzbfp.class, zzbfpVar);
    }

    private zzbfp() {
    }

    public static zzbfp zzc() {
        return zzb;
    }

    public static /* synthetic */ void zzd(zzbfp zzbfpVar, String str) {
        str.getClass();
        zzbfpVar.zze |= 1;
        zzbfpVar.zzf = str;
    }

    @Override // com.google.android.gms.internal.ads.zzgon
    public final Object zzb(int r6, Object obj, Object obj2) {
        int r62 = r6 - 1;
        if (r62 != 0) {
            if (r62 == 2) {
                zzgor zzgorVar = zzbfy.zza;
                return zzaO(zzb, "\u0001\u0005\u0000\u0001\u0001\u0005\u0005\u0000\u0001\u0000\u0001ဈ\u0000\u0002\u001b\u0003ဌ\u0001\u0004ဌ\u0002\u0005ဌ\u0003", new Object[]{"zze", "zzf", "zzg", zzbfl.class, "zzh", zzgorVar, "zzi", zzgorVar, "zzj", zzgorVar});
            } else if (r62 != 3) {
                if (r62 != 4) {
                    if (r62 != 5) {
                        return null;
                    }
                    return zzb;
                }
                return new zzbfo(null);
            } else {
                return new zzbfp();
            }
        }
        return (byte) 1;
    }
}
