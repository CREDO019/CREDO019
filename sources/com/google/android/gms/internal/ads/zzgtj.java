package com.google.android.gms.internal.ads;

/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
public final class zzgtj extends zzgon implements zzgpy {
    private static final zzgtj zzb;
    private int zze;
    private long zzg;
    private boolean zzh;
    private int zzi;
    private String zzf = "";
    private String zzj = "";
    private String zzk = "";

    static {
        zzgtj zzgtjVar = new zzgtj();
        zzb = zzgtjVar;
        zzgon.zzaP(zzgtj.class, zzgtjVar);
    }

    private zzgtj() {
    }

    public static zzgti zza() {
        return (zzgti) zzb.zzay();
    }

    public static /* synthetic */ void zzd(zzgtj zzgtjVar, String str) {
        zzgtjVar.zze |= 1;
        zzgtjVar.zzf = str;
    }

    public static /* synthetic */ void zze(zzgtj zzgtjVar, long j) {
        zzgtjVar.zze |= 2;
        zzgtjVar.zzg = j;
    }

    public static /* synthetic */ void zzf(zzgtj zzgtjVar, boolean z) {
        zzgtjVar.zze |= 4;
        zzgtjVar.zzh = z;
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
                    return new zzgti(null);
                }
                return new zzgtj();
            }
            return zzaO(zzb, "\u0001\u0006\u0000\u0001\u0001\u0006\u0006\u0000\u0000\u0000\u0001ဈ\u0000\u0002ဂ\u0001\u0003ဇ\u0002\u0004ဌ\u0003\u0005ဈ\u0004\u0006ဈ\u0005", new Object[]{"zze", "zzf", "zzg", "zzh", "zzi", zzgtk.zza, "zzj", "zzk"});
        }
        return (byte) 1;
    }
}
