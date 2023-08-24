package com.google.android.gms.internal.ads;

/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
public final class zzbhb extends zzgon implements zzgpy {
    private static final zzbhb zzb;
    private int zze;
    private int zzf = 1000;
    private int zzg = 1000;
    private int zzh;
    private int zzi;
    private int zzj;
    private int zzk;
    private int zzl;
    private int zzm;
    private int zzn;
    private int zzo;
    private zzbhd zzp;

    static {
        zzbhb zzbhbVar = new zzbhb();
        zzb = zzbhbVar;
        zzgon.zzaP(zzbhb.class, zzbhbVar);
    }

    private zzbhb() {
    }

    @Override // com.google.android.gms.internal.ads.zzgon
    public final Object zzb(int r6, Object obj, Object obj2) {
        int r62 = r6 - 1;
        if (r62 != 0) {
            if (r62 == 2) {
                zzgor zzgorVar = zzbfy.zza;
                return zzaO(zzb, "\u0001\u000b\u0000\u0001\u0001\u000b\u000b\u0000\u0000\u0000\u0001ဌ\u0000\u0002ဌ\u0001\u0003င\u0002\u0004င\u0003\u0005င\u0004\u0006င\u0005\u0007င\u0006\bင\u0007\tင\b\nင\t\u000bဉ\n", new Object[]{"zze", "zzf", zzgorVar, "zzg", zzgorVar, "zzh", "zzi", "zzj", "zzk", "zzl", "zzm", "zzn", "zzo", "zzp"});
            } else if (r62 != 3) {
                if (r62 != 4) {
                    if (r62 != 5) {
                        return null;
                    }
                    return zzb;
                }
                return new zzbha(null);
            } else {
                return new zzbhb();
            }
        }
        return (byte) 1;
    }
}
