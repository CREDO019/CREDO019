package com.google.android.gms.internal.ads;

/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
public final class zzbex extends zzgon implements zzgpy {
    private static final zzbex zzb;
    private int zze;
    private int zzf;
    private zzbfn zzh;
    private zzbfp zzi;
    private zzbfr zzk;
    private zzbhb zzl;
    private zzbgr zzm;
    private zzbgf zzn;
    private zzbgh zzo;
    private int zzg = 1000;
    private zzgow zzj = zzaJ();
    private zzgow zzp = zzaJ();

    static {
        zzbex zzbexVar = new zzbex();
        zzb = zzbexVar;
        zzgon.zzaP(zzbex.class, zzbexVar);
    }

    private zzbex() {
    }

    public static zzbex zzc() {
        return zzb;
    }

    public static /* synthetic */ void zze(zzbex zzbexVar, zzbev zzbevVar) {
        zzbexVar.zzf = zzbevVar.zza();
        zzbexVar.zze |= 1;
    }

    public static /* synthetic */ void zzf(zzbex zzbexVar, zzbfp zzbfpVar) {
        zzbfpVar.getClass();
        zzbexVar.zzi = zzbfpVar;
        zzbexVar.zze |= 8;
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
                    return new zzbew(null);
                }
                return new zzbex();
            }
            return zzaO(zzb, "\u0001\u000b\u0000\u0001\u0007\u0011\u000b\u0000\u0002\u0000\u0007ဌ\u0000\bဌ\u0001\tဉ\u0002\nဉ\u0003\u000b\u001b\fဉ\u0004\rဉ\u0005\u000eဉ\u0006\u000fဉ\u0007\u0010ဉ\b\u0011\u001b", new Object[]{"zze", "zzf", zzbev.zzc(), "zzg", zzbfy.zza, "zzh", "zzi", "zzj", zzbfl.class, "zzk", "zzl", "zzm", "zzn", "zzo", "zzp", zzbhn.class});
        }
        return (byte) 1;
    }

    public final zzbfp zzd() {
        zzbfp zzbfpVar = this.zzi;
        return zzbfpVar == null ? zzbfp.zzc() : zzbfpVar;
    }
}
