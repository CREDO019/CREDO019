package com.google.android.gms.internal.ads;

/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
public final class zzamu extends zzgon implements zzgpy {
    private static final zzamu zzb;
    private int zze;
    private long zzu;
    private long zzv;
    private long zzf = -1;
    private long zzg = -1;
    private long zzh = -1;
    private long zzi = -1;
    private long zzj = -1;
    private long zzk = -1;
    private int zzl = 1000;
    private long zzm = -1;
    private long zzn = -1;
    private long zzo = -1;
    private int zzp = 1000;
    private long zzq = -1;
    private long zzr = -1;
    private long zzs = -1;
    private long zzt = -1;
    private long zzw = -1;
    private long zzx = -1;
    private long zzy = -1;
    private long zzz = -1;

    static {
        zzamu zzamuVar = new zzamu();
        zzb = zzamuVar;
        zzgon.zzaP(zzamu.class, zzamuVar);
    }

    private zzamu() {
    }

    public static zzamt zza() {
        return (zzamt) zzb.zzay();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ void zzd(zzamu zzamuVar, long j) {
        zzamuVar.zze |= 1;
        zzamuVar.zzf = j;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ void zze(zzamu zzamuVar, long j) {
        zzamuVar.zze |= 2;
        zzamuVar.zzg = j;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ void zzf(zzamu zzamuVar, long j) {
        zzamuVar.zze |= 4;
        zzamuVar.zzh = j;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ void zzg(zzamu zzamuVar, long j) {
        zzamuVar.zze |= 8;
        zzamuVar.zzi = j;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ void zzh(zzamu zzamuVar) {
        zzamuVar.zze &= -9;
        zzamuVar.zzi = -1L;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ void zzi(zzamu zzamuVar, long j) {
        zzamuVar.zze |= 16;
        zzamuVar.zzj = j;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ void zzj(zzamu zzamuVar, long j) {
        zzamuVar.zze |= 32;
        zzamuVar.zzk = j;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ void zzk(zzamu zzamuVar, long j) {
        zzamuVar.zze |= 128;
        zzamuVar.zzm = j;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ void zzl(zzamu zzamuVar, long j) {
        zzamuVar.zze |= 256;
        zzamuVar.zzn = j;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ void zzm(zzamu zzamuVar, long j) {
        zzamuVar.zze |= 512;
        zzamuVar.zzo = j;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ void zzn(zzamu zzamuVar, long j) {
        zzamuVar.zze |= 2048;
        zzamuVar.zzq = j;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ void zzo(zzamu zzamuVar, long j) {
        zzamuVar.zze |= 4096;
        zzamuVar.zzr = j;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ void zzp(zzamu zzamuVar, long j) {
        zzamuVar.zze |= 8192;
        zzamuVar.zzs = j;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ void zzq(zzamu zzamuVar, long j) {
        zzamuVar.zze |= 16384;
        zzamuVar.zzt = j;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ void zzr(zzamu zzamuVar, long j) {
        zzamuVar.zze |= 32768;
        zzamuVar.zzu = j;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ void zzs(zzamu zzamuVar, long j) {
        zzamuVar.zze |= 65536;
        zzamuVar.zzv = j;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ void zzt(zzamu zzamuVar, long j) {
        zzamuVar.zze |= 131072;
        zzamuVar.zzw = j;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ void zzu(zzamu zzamuVar, long j) {
        zzamuVar.zze |= 262144;
        zzamuVar.zzx = j;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ void zzv(zzamu zzamuVar, int r1) {
        zzamuVar.zzl = r1 - 1;
        zzamuVar.zze |= 64;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ void zzw(zzamu zzamuVar, int r1) {
        zzamuVar.zzp = r1 - 1;
        zzamuVar.zze |= 1024;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.google.android.gms.internal.ads.zzgon
    public final Object zzb(int r6, Object obj, Object obj2) {
        int r62 = r6 - 1;
        if (r62 != 0) {
            if (r62 == 2) {
                zzgor zzgorVar = zzand.zza;
                return zzaO(zzb, "\u0001\u0015\u0000\u0001\u0001\u0015\u0015\u0000\u0000\u0000\u0001ဂ\u0000\u0002ဂ\u0001\u0003ဂ\u0002\u0004ဂ\u0003\u0005ဂ\u0004\u0006ဂ\u0005\u0007ဌ\u0006\bဂ\u0007\tဂ\b\nဂ\t\u000bဌ\n\fဂ\u000b\rဂ\f\u000eဂ\r\u000fဂ\u000e\u0010ဂ\u000f\u0011ဂ\u0010\u0012ဂ\u0011\u0013ဂ\u0012\u0014ဂ\u0013\u0015ဂ\u0014", new Object[]{"zze", "zzf", "zzg", "zzh", "zzi", "zzj", "zzk", "zzl", zzgorVar, "zzm", "zzn", "zzo", "zzp", zzgorVar, "zzq", "zzr", "zzs", "zzt", "zzu", "zzv", "zzw", "zzx", "zzy", "zzz"});
            } else if (r62 != 3) {
                if (r62 != 4) {
                    if (r62 != 5) {
                        return null;
                    }
                    return zzb;
                }
                return new zzamt(null);
            } else {
                return new zzamu();
            }
        }
        return (byte) 1;
    }
}
