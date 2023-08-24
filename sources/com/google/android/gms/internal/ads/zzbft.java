package com.google.android.gms.internal.ads;

/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
public final class zzbft extends zzgon implements zzgpy {
    private static final zzbft zzb;
    private int zze;
    private zzbhj zzg;
    private int zzh;
    private zzbhl zzi;
    private int zzj;
    private String zzf = "";
    private int zzk = 1000;
    private int zzl = 1000;
    private int zzm = 1000;

    static {
        zzbft zzbftVar = new zzbft();
        zzb = zzbftVar;
        zzgon.zzaP(zzbft.class, zzbftVar);
    }

    private zzbft() {
    }

    public static zzbft zzc() {
        return zzb;
    }

    public static /* synthetic */ void zzd(zzbft zzbftVar, String str) {
        zzbftVar.zze |= 1;
        zzbftVar.zzf = str;
    }

    public static /* synthetic */ void zze(zzbft zzbftVar, zzbhl zzbhlVar) {
        zzbhlVar.getClass();
        zzbftVar.zzi = zzbhlVar;
        zzbftVar.zze |= 8;
    }

    @Override // com.google.android.gms.internal.ads.zzgon
    public final Object zzb(int r6, Object obj, Object obj2) {
        int r62 = r6 - 1;
        if (r62 != 0) {
            if (r62 == 2) {
                zzgor zzgorVar = zzbfy.zza;
                return zzaO(zzb, "\u0001\b\u0000\u0001\u0001\b\b\u0000\u0000\u0000\u0001ဈ\u0000\u0002ဉ\u0001\u0003င\u0002\u0004ဉ\u0003\u0005င\u0004\u0006ဌ\u0005\u0007ဌ\u0006\bဌ\u0007", new Object[]{"zze", "zzf", "zzg", "zzh", "zzi", "zzj", "zzk", zzgorVar, "zzl", zzgorVar, "zzm", zzgorVar});
            } else if (r62 != 3) {
                if (r62 != 4) {
                    if (r62 != 5) {
                        return null;
                    }
                    return zzb;
                }
                return new zzbfs(null);
            } else {
                return new zzbft();
            }
        }
        return (byte) 1;
    }
}
