package com.google.android.gms.internal.ads;

import android.support.p001v4.media.session.PlaybackStateCompat;
import java.io.IOException;

/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
public class zzyr {
    protected final zzyl zza;
    protected final zzyq zzb;
    protected zzyn zzc;
    private final int zzd;

    /* JADX INFO: Access modifiers changed from: protected */
    public zzyr(zzyo zzyoVar, zzyq zzyqVar, long j, long j2, long j3, long j4, long j5, long j6, int r31) {
        this.zzb = zzyqVar;
        this.zzd = r31;
        this.zza = new zzyl(zzyoVar, j, 0L, j3, j4, j5, j6);
    }

    protected static final int zzf(zzzg zzzgVar, long j, zzaaf zzaafVar) {
        if (j == zzzgVar.zzf()) {
            return 0;
        }
        zzaafVar.zza = j;
        return 1;
    }

    protected static final boolean zzg(zzzg zzzgVar, long j) throws IOException {
        long zzf = j - zzzgVar.zzf();
        if (zzf < 0 || zzf > PlaybackStateCompat.ACTION_SET_REPEAT_MODE) {
            return false;
        }
        ((zzyv) zzzgVar).zzo((int) zzf, false);
        return true;
    }

    public final int zza(zzzg zzzgVar, zzaaf zzaafVar) throws IOException {
        long j;
        long j2;
        long j3;
        long j4;
        int r2;
        long j5;
        long j6;
        long j7;
        long j8;
        long j9;
        long j10;
        long j11;
        while (true) {
            zzyn zzynVar = this.zzc;
            zzdd.zzb(zzynVar);
            j = zzynVar.zzf;
            j2 = zzynVar.zzg;
            j3 = zzynVar.zzh;
            if (j2 - j > this.zzd) {
                if (zzg(zzzgVar, j3)) {
                    zzzgVar.zzj();
                    zzyq zzyqVar = this.zzb;
                    j4 = zzynVar.zzb;
                    zzyp zza = zzyqVar.zza(zzzgVar, j4);
                    r2 = zza.zzb;
                    if (r2 == -3) {
                        zzc(false, j3);
                        return zzf(zzzgVar, j3, zzaafVar);
                    } else if (r2 == -2) {
                        j10 = zza.zzc;
                        j11 = zza.zzd;
                        zzyn.zzh(zzynVar, j10, j11);
                    } else if (r2 == -1) {
                        j8 = zza.zzc;
                        j9 = zza.zzd;
                        zzyn.zzg(zzynVar, j8, j9);
                    } else {
                        j5 = zza.zzd;
                        zzg(zzzgVar, j5);
                        j6 = zza.zzd;
                        zzc(true, j6);
                        j7 = zza.zzd;
                        return zzf(zzzgVar, j7, zzaafVar);
                    }
                } else {
                    return zzf(zzzgVar, j3, zzaafVar);
                }
            } else {
                zzc(false, j);
                return zzf(zzzgVar, j, zzaafVar);
            }
        }
    }

    public final zzaai zzb() {
        return this.zza;
    }

    protected final void zzc(boolean z, long j) {
        this.zzc = null;
        this.zzb.zzb();
    }

    public final void zzd(long j) {
        long j2;
        long j3;
        long j4;
        long j5;
        long j6;
        zzyn zzynVar = this.zzc;
        if (zzynVar != null) {
            j6 = zzynVar.zza;
            if (j6 == j) {
                return;
            }
        }
        long zzf = this.zza.zzf(j);
        zzyl zzylVar = this.zza;
        j2 = zzylVar.zzc;
        j3 = zzylVar.zzd;
        j4 = zzylVar.zze;
        j5 = zzylVar.zzf;
        this.zzc = new zzyn(j, zzf, 0L, j2, j3, j4, j5);
    }

    public final boolean zze() {
        return this.zzc != null;
    }
}
