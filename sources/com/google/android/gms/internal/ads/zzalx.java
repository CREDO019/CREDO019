package com.google.android.gms.internal.ads;

/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
public final class zzalx extends zzgon implements zzgpy {
    private static final zzalx zzb;
    private int zze;
    private long zzg;
    private long zzk;
    private long zzl;
    private long zzn;
    private int zzr;
    private String zzf = "";
    private String zzh = "";
    private String zzi = "";
    private String zzj = "";
    private String zzm = "";
    private String zzo = "";
    private String zzp = "";
    private zzgow zzq = zzaJ();

    static {
        zzalx zzalxVar = new zzalx();
        zzb = zzalxVar;
        zzgon.zzaP(zzalx.class, zzalxVar);
    }

    private zzalx() {
    }

    public static zzalt zza() {
        return (zzalt) zzb.zzay();
    }

    public static /* synthetic */ void zzd(zzalx zzalxVar, long j) {
        zzalxVar.zze |= 2;
        zzalxVar.zzg = j;
    }

    public static /* synthetic */ void zze(zzalx zzalxVar, String str) {
        str.getClass();
        zzalxVar.zze |= 4;
        zzalxVar.zzh = str;
    }

    public static /* synthetic */ void zzf(zzalx zzalxVar, String str) {
        str.getClass();
        zzalxVar.zze |= 8;
        zzalxVar.zzi = str;
    }

    public static /* synthetic */ void zzg(zzalx zzalxVar, String str) {
        zzalxVar.zze |= 16;
        zzalxVar.zzj = str;
    }

    public static /* synthetic */ void zzh(zzalx zzalxVar, String str) {
        zzalxVar.zze |= 1024;
        zzalxVar.zzp = str;
    }

    public static /* synthetic */ void zzi(zzalx zzalxVar, String str) {
        str.getClass();
        zzalxVar.zze |= 1;
        zzalxVar.zzf = str;
    }

    public static /* synthetic */ void zzj(zzalx zzalxVar, int r1) {
        zzalxVar.zzr = r1 - 1;
        zzalxVar.zze |= 2048;
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
                    return new zzalt(null);
                }
                return new zzalx();
            }
            return zzaO(zzb, "\u0001\r\u0000\u0001\u0001\r\r\u0000\u0001\u0000\u0001ဈ\u0000\u0002ဂ\u0001\u0003ဈ\u0002\u0004ဈ\u0003\u0005ဈ\u0004\u0006ဂ\u0005\u0007ဂ\u0006\bဈ\u0007\tဂ\b\nဈ\t\u000bဈ\n\f\u001b\rဌ\u000b", new Object[]{"zze", "zzf", "zzg", "zzh", "zzi", "zzj", "zzk", "zzl", "zzm", "zzn", "zzo", "zzp", "zzq", zzalv.class, "zzr", zzalw.zza});
        }
        return (byte) 1;
    }
}
