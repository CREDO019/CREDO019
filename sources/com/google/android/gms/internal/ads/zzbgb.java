package com.google.android.gms.internal.ads;

/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
public final class zzbgb extends zzgon implements zzgpy {
    private static final zzbgb zzb;
    private int zze;
    private int zzf;
    private int zzh;
    private zzbhl zzj;
    private zzbft zzl;
    private zzbfw zzm;
    private zzbgp zzn;
    private zzbex zzo;
    private zzbgz zzp;
    private zzbig zzq;
    private zzbfg zzr;
    private String zzg = "";
    private int zzi = 1000;
    private zzgov zzk = zzaI();

    static {
        zzbgb zzbgbVar = new zzbgb();
        zzb = zzbgbVar;
        zzgon.zzaP(zzbgb.class, zzbgbVar);
    }

    private zzbgb() {
    }

    public static zzbga zzd() {
        return (zzbga) zzb.zzay();
    }

    public static /* synthetic */ void zzg(zzbgb zzbgbVar, String str) {
        str.getClass();
        zzbgbVar.zze |= 2;
        zzbgbVar.zzg = str;
    }

    public static /* synthetic */ void zzh(zzbgb zzbgbVar, Iterable iterable) {
        zzgov zzgovVar = zzbgbVar.zzk;
        if (!zzgovVar.zzc()) {
            int size = zzgovVar.size();
            zzbgbVar.zzk = zzgovVar.zza(size == 0 ? 10 : size + size);
        }
        zzgmo.zzat(iterable, zzbgbVar.zzk);
    }

    public static /* synthetic */ void zzj(zzbgb zzbgbVar, zzbft zzbftVar) {
        zzbftVar.getClass();
        zzbgbVar.zzl = zzbftVar;
        zzbgbVar.zze |= 32;
    }

    public static /* synthetic */ void zzk(zzbgb zzbgbVar, zzbex zzbexVar) {
        zzbexVar.getClass();
        zzbgbVar.zzo = zzbexVar;
        zzbgbVar.zze |= 256;
    }

    public static /* synthetic */ void zzl(zzbgb zzbgbVar, zzbgz zzbgzVar) {
        zzbgzVar.getClass();
        zzbgbVar.zzp = zzbgzVar;
        zzbgbVar.zze |= 512;
    }

    public static /* synthetic */ void zzm(zzbgb zzbgbVar, zzbig zzbigVar) {
        zzbigVar.getClass();
        zzbgbVar.zzq = zzbigVar;
        zzbgbVar.zze |= 1024;
    }

    public static /* synthetic */ void zzn(zzbgb zzbgbVar, zzbfg zzbfgVar) {
        zzbfgVar.getClass();
        zzbgbVar.zzr = zzbfgVar;
        zzbgbVar.zze |= 2048;
    }

    public final zzbex zza() {
        zzbex zzbexVar = this.zzo;
        return zzbexVar == null ? zzbex.zzc() : zzbexVar;
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
                    return new zzbga(null);
                }
                return new zzbgb();
            }
            return zzaO(zzb, "\u0001\r\u0000\u0001\t\u0015\r\u0000\u0001\u0000\tင\u0000\nဈ\u0001\u000bဋ\u0002\fဌ\u0003\rဉ\u0004\u000e\u0015\u000fဉ\u0005\u0010ဉ\u0006\u0011ဉ\u0007\u0012ဉ\b\u0013ဉ\t\u0014ဉ\n\u0015ဉ\u000b", new Object[]{"zze", "zzf", "zzg", "zzh", "zzi", zzbfy.zza, "zzj", "zzk", "zzl", "zzm", "zzn", "zzo", "zzp", "zzq", "zzr"});
        }
        return (byte) 1;
    }

    public final zzbft zzc() {
        zzbft zzbftVar = this.zzl;
        return zzbftVar == null ? zzbft.zzc() : zzbftVar;
    }

    public final String zzf() {
        return this.zzg;
    }
}
