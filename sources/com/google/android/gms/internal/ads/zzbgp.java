package com.google.android.gms.internal.ads;

/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
public final class zzbgp extends zzgon implements zzgpy {
    private static final zzbgp zzb;
    private int zze;
    private int zzf;
    private int zzg;

    static {
        zzbgp zzbgpVar = new zzbgp();
        zzb = zzbgpVar;
        zzgon.zzaP(zzbgp.class, zzbgpVar);
    }

    private zzbgp() {
    }

    public static zzbgi zza() {
        return (zzbgi) zzb.zzay();
    }

    public static zzbgp zzd() {
        return zzb;
    }

    public static /* synthetic */ void zzi(zzbgp zzbgpVar, int r1) {
        zzbgpVar.zzf = r1 - 1;
        zzbgpVar.zze |= 1;
    }

    public static /* synthetic */ void zzj(zzbgp zzbgpVar, int r1) {
        zzbgpVar.zzg = r1 - 1;
        zzbgpVar.zze |= 2;
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
                    return new zzbgi(null);
                }
                return new zzbgp();
            }
            return zzaO(zzb, "\u0001\u0002\u0000\u0001\u0001\u0002\u0002\u0000\u0000\u0000\u0001ဌ\u0000\u0002ဌ\u0001", new Object[]{"zze", "zzf", zzbgn.zza, "zzg", zzbgk.zza});
        }
        return (byte) 1;
    }

    public final boolean zze() {
        return (this.zze & 2) != 0;
    }

    public final boolean zzf() {
        return (this.zze & 1) != 0;
    }

    public final int zzg() {
        int zza = zzbgl.zza(this.zzg);
        if (zza == 0) {
            return 1;
        }
        return zza;
    }

    public final int zzh() {
        int zza = zzbgo.zza(this.zzf);
        if (zza == 0) {
            return 1;
        }
        return zza;
    }
}
