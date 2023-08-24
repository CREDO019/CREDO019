package com.google.android.gms.internal.ads;

/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
public final class zzaru extends zzgon implements zzgpy {
    private static final zzaru zzb;
    private int zze;
    private String zzf = "";
    private String zzg = "";
    private long zzh;
    private long zzi;
    private long zzj;

    static {
        zzaru zzaruVar = new zzaru();
        zzb = zzaruVar;
        zzgon.zzaP(zzaru.class, zzaruVar);
    }

    private zzaru() {
    }

    public static zzart zze() {
        return (zzart) zzb.zzay();
    }

    public static zzaru zzg() {
        return zzb;
    }

    public static zzaru zzh(zzgnf zzgnfVar) throws zzgoz {
        return (zzaru) zzgon.zzaB(zzb, zzgnfVar);
    }

    public static zzaru zzi(zzgnf zzgnfVar, zzgnz zzgnzVar) throws zzgoz {
        return (zzaru) zzgon.zzaD(zzb, zzgnfVar, zzgnzVar);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ void zzl(zzaru zzaruVar, String str) {
        str.getClass();
        zzaruVar.zze |= 1;
        zzaruVar.zzf = str;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ void zzm(zzaru zzaruVar, long j) {
        zzaruVar.zze |= 16;
        zzaruVar.zzj = j;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ void zzn(zzaru zzaruVar, String str) {
        str.getClass();
        zzaruVar.zze |= 2;
        zzaruVar.zzg = str;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ void zzo(zzaru zzaruVar, long j) {
        zzaruVar.zze |= 4;
        zzaruVar.zzh = j;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ void zzp(zzaru zzaruVar, long j) {
        zzaruVar.zze |= 8;
        zzaruVar.zzi = j;
    }

    public final long zza() {
        return this.zzi;
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
                    return new zzart(null);
                }
                return new zzaru();
            }
            return zzaO(zzb, "\u0001\u0005\u0000\u0001\u0001\u0005\u0005\u0000\u0000\u0000\u0001ဈ\u0000\u0002ဈ\u0001\u0003ဃ\u0002\u0004ဃ\u0003\u0005ဃ\u0004", new Object[]{"zze", "zzf", "zzg", "zzh", "zzi", "zzj"});
        }
        return (byte) 1;
    }

    public final long zzc() {
        return this.zzh;
    }

    public final long zzd() {
        return this.zzj;
    }

    public final String zzj() {
        return this.zzg;
    }

    public final String zzk() {
        return this.zzf;
    }
}
