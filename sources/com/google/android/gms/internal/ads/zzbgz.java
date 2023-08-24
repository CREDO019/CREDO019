package com.google.android.gms.internal.ads;

/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
public final class zzbgz extends zzgon implements zzgpy {
    private static final zzbgz zzb;
    private int zze;
    private int zzg;
    private int zzh;
    private long zzi;
    private long zzl;
    private int zzm;
    private zzgow zzf = zzaJ();
    private String zzj = "";
    private String zzk = "";

    static {
        zzbgz zzbgzVar = new zzbgz();
        zzb = zzbgzVar;
        zzgon.zzaP(zzbgz.class, zzbgzVar);
    }

    private zzbgz() {
    }

    public static zzbgv zza() {
        return (zzbgv) zzb.zzay();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ void zzd(zzbgz zzbgzVar, Iterable iterable) {
        zzgow zzgowVar = zzbgzVar.zzf;
        if (!zzgowVar.zzc()) {
            zzbgzVar.zzf = zzgon.zzaK(zzgowVar);
        }
        zzgmo.zzat(iterable, zzbgzVar.zzf);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ void zze(zzbgz zzbgzVar, int r2) {
        zzbgzVar.zze |= 1;
        zzbgzVar.zzg = r2;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ void zzf(zzbgz zzbgzVar, int r2) {
        zzbgzVar.zze |= 2;
        zzbgzVar.zzh = r2;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ void zzg(zzbgz zzbgzVar, long j) {
        zzbgzVar.zze |= 4;
        zzbgzVar.zzi = j;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ void zzh(zzbgz zzbgzVar, String str) {
        str.getClass();
        zzbgzVar.zze |= 8;
        zzbgzVar.zzj = str;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ void zzi(zzbgz zzbgzVar, String str) {
        str.getClass();
        zzbgzVar.zze |= 16;
        zzbgzVar.zzk = str;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ void zzj(zzbgz zzbgzVar, long j) {
        zzbgzVar.zze |= 32;
        zzbgzVar.zzl = j;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ void zzk(zzbgz zzbgzVar, int r2) {
        zzbgzVar.zze |= 64;
        zzbgzVar.zzm = r2;
    }

    /* JADX INFO: Access modifiers changed from: protected */
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
                    return new zzbgv(null);
                }
                return new zzbgz();
            }
            return zzaO(zzb, "\u0001\b\u0000\u0001\u0001\b\b\u0000\u0001\u0000\u0001\u001b\u0002င\u0000\u0003င\u0001\u0004ဂ\u0002\u0005ဈ\u0003\u0006ဈ\u0004\u0007ဂ\u0005\bင\u0006", new Object[]{"zze", "zzf", zzbgu.class, "zzg", "zzh", "zzi", "zzj", "zzk", "zzl", "zzm"});
        }
        return (byte) 1;
    }
}
