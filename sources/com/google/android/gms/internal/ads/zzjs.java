package com.google.android.gms.internal.ads;

import com.google.android.exoplayer2.C1856C;
import java.util.List;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
public final class zzjs {
    private static final zzsg zzt = new zzsg(new Object());
    public final zzcn zza;
    public final zzsg zzb;
    public final long zzc;
    public final long zzd;
    public final int zze;
    public final zzgy zzf;
    public final boolean zzg;
    public final zzue zzh;
    public final zzvx zzi;
    public final List zzj;
    public final zzsg zzk;
    public final boolean zzl;
    public final int zzm;
    public final zzby zzn;
    public final boolean zzo;
    public final boolean zzp;
    public volatile long zzq;
    public volatile long zzr;
    public volatile long zzs;

    public zzjs(zzcn zzcnVar, zzsg zzsgVar, long j, long j2, int r10, zzgy zzgyVar, boolean z, zzue zzueVar, zzvx zzvxVar, List list, zzsg zzsgVar2, boolean z2, int r18, zzby zzbyVar, long j3, long j4, long j5, boolean z3, boolean z4) {
        this.zza = zzcnVar;
        this.zzb = zzsgVar;
        this.zzc = j;
        this.zzd = j2;
        this.zze = r10;
        this.zzf = zzgyVar;
        this.zzg = z;
        this.zzh = zzueVar;
        this.zzi = zzvxVar;
        this.zzj = list;
        this.zzk = zzsgVar2;
        this.zzl = z2;
        this.zzm = r18;
        this.zzn = zzbyVar;
        this.zzq = j3;
        this.zzr = j4;
        this.zzs = j5;
        this.zzo = z3;
        this.zzp = z4;
    }

    public static zzjs zzh(zzvx zzvxVar) {
        zzcn zzcnVar = zzcn.zza;
        zzsg zzsgVar = zzt;
        return new zzjs(zzcnVar, zzsgVar, C1856C.TIME_UNSET, 0L, 1, null, false, zzue.zza, zzvxVar, zzfuv.zzo(), zzsgVar, false, 0, zzby.zza, 0L, 0L, 0L, false, false);
    }

    public static zzsg zzi() {
        return zzt;
    }

    public final zzjs zza(zzsg zzsgVar) {
        return new zzjs(this.zza, this.zzb, this.zzc, this.zzd, this.zze, this.zzf, this.zzg, this.zzh, this.zzi, this.zzj, zzsgVar, this.zzl, this.zzm, this.zzn, this.zzq, this.zzr, this.zzs, this.zzo, this.zzp);
    }

    public final zzjs zzb(zzsg zzsgVar, long j, long j2, long j3, long j4, zzue zzueVar, zzvx zzvxVar, List list) {
        return new zzjs(this.zza, zzsgVar, j2, j3, this.zze, this.zzf, this.zzg, zzueVar, zzvxVar, list, this.zzk, this.zzl, this.zzm, this.zzn, this.zzq, j4, j, this.zzo, this.zzp);
    }

    public final zzjs zzc(boolean z) {
        return new zzjs(this.zza, this.zzb, this.zzc, this.zzd, this.zze, this.zzf, this.zzg, this.zzh, this.zzi, this.zzj, this.zzk, this.zzl, this.zzm, this.zzn, this.zzq, this.zzr, this.zzs, z, this.zzp);
    }

    public final zzjs zzd(boolean z, int r29) {
        return new zzjs(this.zza, this.zzb, this.zzc, this.zzd, this.zze, this.zzf, this.zzg, this.zzh, this.zzi, this.zzj, this.zzk, z, r29, this.zzn, this.zzq, this.zzr, this.zzs, this.zzo, this.zzp);
    }

    public final zzjs zze(zzgy zzgyVar) {
        return new zzjs(this.zza, this.zzb, this.zzc, this.zzd, this.zze, zzgyVar, this.zzg, this.zzh, this.zzi, this.zzj, this.zzk, this.zzl, this.zzm, this.zzn, this.zzq, this.zzr, this.zzs, this.zzo, this.zzp);
    }

    public final zzjs zzf(int r29) {
        return new zzjs(this.zza, this.zzb, this.zzc, this.zzd, r29, this.zzf, this.zzg, this.zzh, this.zzi, this.zzj, this.zzk, this.zzl, this.zzm, this.zzn, this.zzq, this.zzr, this.zzs, this.zzo, this.zzp);
    }

    public final zzjs zzg(zzcn zzcnVar) {
        return new zzjs(zzcnVar, this.zzb, this.zzc, this.zzd, this.zze, this.zzf, this.zzg, this.zzh, this.zzi, this.zzj, this.zzk, this.zzl, this.zzm, this.zzn, this.zzq, this.zzr, this.zzs, this.zzo, this.zzp);
    }
}
