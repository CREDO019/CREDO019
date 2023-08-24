package com.google.android.gms.internal.ads;

/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
public final class zzfjh extends zzgon implements zzgpy {
    private static final zzfjh zzb;
    private int zze;
    private boolean zzf;
    private long zzg;
    private int zzh;
    private int zzl;
    private int zzm;
    private int zzn;
    private long zzo;
    private int zzp;
    private String zzi = "";
    private String zzj = "";
    private String zzk = "";
    private String zzq = "";
    private String zzr = "";
    private String zzs = "";
    private String zzt = "";
    private String zzu = "";

    static {
        zzfjh zzfjhVar = new zzfjh();
        zzb = zzfjhVar;
        zzgon.zzaP(zzfjh.class, zzfjhVar);
    }

    private zzfjh() {
    }

    public static zzfjg zza() {
        return (zzfjg) zzb.zzay();
    }

    public static /* synthetic */ void zzd(zzfjh zzfjhVar, String str) {
        str.getClass();
        zzfjhVar.zzi = str;
    }

    public static /* synthetic */ void zze(zzfjh zzfjhVar, String str) {
        str.getClass();
        zzfjhVar.zzj = str;
    }

    public static /* synthetic */ void zzf(zzfjh zzfjhVar, String str) {
        str.getClass();
        zzfjhVar.zzk = str;
    }

    public static /* synthetic */ void zzj(zzfjh zzfjhVar, String str) {
        str.getClass();
        zzfjhVar.zzq = str;
    }

    public static /* synthetic */ void zzk(zzfjh zzfjhVar, String str) {
        str.getClass();
        zzfjhVar.zzr = str;
    }

    public static /* synthetic */ void zzl(zzfjh zzfjhVar, String str) {
        str.getClass();
        zzfjhVar.zzs = str;
    }

    public static /* synthetic */ void zzo(zzfjh zzfjhVar, String str) {
        str.getClass();
        zzfjhVar.zzu = str;
    }

    public static /* synthetic */ void zzr(zzfjh zzfjhVar, int r2) {
        if (r2 == 1) {
            throw new IllegalArgumentException("Can't get the number of an unknown enum value.");
        }
        zzfjhVar.zzm = r2 - 2;
    }

    public static /* synthetic */ void zzs(zzfjh zzfjhVar, int r2) {
        if (r2 == 1) {
            throw new IllegalArgumentException("Can't get the number of an unknown enum value.");
        }
        zzfjhVar.zzp = r2 - 2;
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
                    return new zzfjg(null);
                }
                return new zzfjh();
            }
            return zzaO(zzb, "\u0000\u0011\u0000\u0000\u0001\u0011\u0011\u0000\u0000\u0000\u0001\f\u0002\u0007\u0003\u0002\u0004\f\u0005Ȉ\u0006Ȉ\u0007Ȉ\b\u0004\t\f\n\u0004\u000b\u0002\f\f\rȈ\u000eȈ\u000fȈ\u0010Ȉ\u0011Ȉ", new Object[]{"zze", "zzf", "zzg", "zzh", "zzi", "zzj", "zzk", "zzl", "zzm", "zzn", "zzo", "zzp", "zzq", "zzr", "zzs", "zzt", "zzu"});
        }
        return (byte) 1;
    }
}
