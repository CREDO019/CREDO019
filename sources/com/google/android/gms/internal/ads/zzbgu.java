package com.google.android.gms.internal.ads;

import java.util.Iterator;
import java.util.List;

/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
public final class zzbgu extends zzgon implements zzgpy {
    private static final zzgot zzb = new zzbgs();
    private static final zzbgu zze;
    private int zzf;
    private long zzg;
    private int zzh;
    private long zzi;
    private long zzj;
    private zzgos zzk = zzaG();
    private zzbgp zzl;
    private int zzm;
    private int zzn;
    private int zzo;
    private int zzp;
    private int zzq;
    private int zzr;
    private long zzs;

    static {
        zzbgu zzbguVar = new zzbgu();
        zze = zzbguVar;
        zzgon.zzaP(zzbgu.class, zzbguVar);
    }

    private zzbgu() {
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ void zzA(zzbgu zzbguVar, int r1) {
        zzbguVar.zzn = r1 - 1;
        zzbguVar.zzf |= 64;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ void zzB(zzbgu zzbguVar, int r1) {
        zzbguVar.zzo = r1 - 1;
        zzbguVar.zzf |= 128;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ void zzC(zzbgu zzbguVar, int r1) {
        zzbguVar.zzq = r1 - 1;
        zzbguVar.zzf |= 512;
    }

    public static zzbgt zzg() {
        return (zzbgt) zze.zzay();
    }

    public static zzbgu zzi(byte[] bArr) throws zzgoz {
        return (zzbgu) zzgon.zzaC(zze, bArr);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ void zzl(zzbgu zzbguVar, long j) {
        zzbguVar.zzf |= 1;
        zzbguVar.zzg = j;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ void zzm(zzbgu zzbguVar, long j) {
        zzbguVar.zzf |= 4;
        zzbguVar.zzi = j;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ void zzn(zzbgu zzbguVar, long j) {
        zzbguVar.zzf |= 8;
        zzbguVar.zzj = j;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ void zzo(zzbgu zzbguVar, Iterable iterable) {
        zzgos zzgosVar = zzbguVar.zzk;
        if (!zzgosVar.zzc()) {
            zzbguVar.zzk = zzgon.zzaH(zzgosVar);
        }
        Iterator it = iterable.iterator();
        while (it.hasNext()) {
            zzbguVar.zzk.zzh(((zzbfj) it.next()).zza());
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ void zzp(zzbgu zzbguVar, zzbgp zzbgpVar) {
        zzbgpVar.getClass();
        zzbguVar.zzl = zzbgpVar;
        zzbguVar.zzf |= 16;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ void zzq(zzbgu zzbguVar, int r2) {
        zzbguVar.zzf |= 256;
        zzbguVar.zzp = r2;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ void zzr(zzbgu zzbguVar, zzbgy zzbgyVar) {
        zzbguVar.zzr = zzbgyVar.zza();
        zzbguVar.zzf |= 1024;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ void zzs(zzbgu zzbguVar, long j) {
        zzbguVar.zzf |= 2048;
        zzbguVar.zzs = j;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ void zzy(zzbgu zzbguVar, int r1) {
        zzbguVar.zzh = r1 - 1;
        zzbguVar.zzf |= 2;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ void zzz(zzbgu zzbguVar, int r1) {
        zzbguVar.zzm = r1 - 1;
        zzbguVar.zzf |= 32;
    }

    public final int zza() {
        return this.zzp;
    }

    @Override // com.google.android.gms.internal.ads.zzgon
    protected final Object zzb(int r6, Object obj, Object obj2) {
        int r62 = r6 - 1;
        if (r62 != 0) {
            if (r62 == 2) {
                zzgor zzgorVar = zzbfy.zza;
                return zzaO(zze, "\u0001\r\u0000\u0001\u0001\r\r\u0000\u0001\u0000\u0001ဂ\u0000\u0002ဌ\u0001\u0003ဂ\u0002\u0004ဂ\u0003\u0005\u001e\u0006ဉ\u0004\u0007ဌ\u0005\bဌ\u0006\tဌ\u0007\nင\b\u000bဌ\t\fဌ\n\rဂ\u000b", new Object[]{"zzf", "zzg", "zzh", zzbfy.zza, "zzi", "zzj", "zzk", zzbfj.zzc(), "zzl", "zzm", zzgorVar, "zzn", zzgorVar, "zzo", zzgorVar, "zzp", "zzq", zzgorVar, "zzr", zzbgy.zzc(), "zzs"});
            } else if (r62 != 3) {
                if (r62 != 4) {
                    if (r62 != 5) {
                        return null;
                    }
                    return zze;
                }
                return new zzbgt(null);
            } else {
                return new zzbgu();
            }
        }
        return (byte) 1;
    }

    public final long zzc() {
        return this.zzj;
    }

    public final long zzd() {
        return this.zzi;
    }

    public final long zze() {
        return this.zzg;
    }

    public final zzbgp zzf() {
        zzbgp zzbgpVar = this.zzl;
        return zzbgpVar == null ? zzbgp.zzd() : zzbgpVar;
    }

    public final zzbgy zzj() {
        zzbgy zzb2 = zzbgy.zzb(this.zzr);
        return zzb2 == null ? zzbgy.UNSPECIFIED : zzb2;
    }

    public final List zzk() {
        return new zzgou(this.zzk, zzb);
    }

    public final int zzt() {
        int zza = zzbfz.zza(this.zzn);
        if (zza == 0) {
            return 1;
        }
        return zza;
    }

    public final int zzu() {
        int zza = zzbfz.zza(this.zzo);
        if (zza == 0) {
            return 1;
        }
        return zza;
    }

    public final int zzv() {
        int zza = zzbfz.zza(this.zzq);
        if (zza == 0) {
            return 1;
        }
        return zza;
    }

    public final int zzw() {
        int zza = zzbfz.zza(this.zzh);
        if (zza == 0) {
            return 1;
        }
        return zza;
    }

    public final int zzx() {
        int zza = zzbfz.zza(this.zzm);
        if (zza == 0) {
            return 1;
        }
        return zza;
    }
}
