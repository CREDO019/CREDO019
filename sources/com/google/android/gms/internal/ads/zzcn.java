package com.google.android.gms.internal.ads;

import android.util.Pair;
import com.facebook.imageutils.JfifUtil;
import com.google.android.exoplayer2.C1856C;
import java.util.Objects;

/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
public abstract class zzcn {
    public static final zzcn zza = new zzci();
    public static final zzn zzb = new zzn() { // from class: com.google.android.gms.internal.ads.zzch
    };

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof zzcn) {
            zzcn zzcnVar = (zzcn) obj;
            if (zzcnVar.zzc() == zzc() && zzcnVar.zzb() == zzb()) {
                zzcm zzcmVar = new zzcm();
                zzck zzckVar = new zzck();
                zzcm zzcmVar2 = new zzcm();
                zzck zzckVar2 = new zzck();
                for (int r6 = 0; r6 < zzc(); r6++) {
                    if (!zze(r6, zzcmVar, 0L).equals(zzcnVar.zze(r6, zzcmVar2, 0L))) {
                        return false;
                    }
                }
                for (int r1 = 0; r1 < zzb(); r1++) {
                    if (!zzd(r1, zzckVar, true).equals(zzcnVar.zzd(r1, zzckVar2, true))) {
                        return false;
                    }
                }
                return true;
            }
            return false;
        }
        return false;
    }

    public final int hashCode() {
        zzcm zzcmVar = new zzcm();
        zzck zzckVar = new zzck();
        int zzc = zzc() + JfifUtil.MARKER_EOI;
        for (int r4 = 0; r4 < zzc(); r4++) {
            zzc = (zzc * 31) + zze(r4, zzcmVar, 0L).hashCode();
        }
        int zzb2 = (zzc * 31) + zzb();
        for (int r3 = 0; r3 < zzb(); r3++) {
            zzb2 = (zzb2 * 31) + zzd(r3, zzckVar, true).hashCode();
        }
        return zzb2;
    }

    public abstract int zza(Object obj);

    public abstract int zzb();

    public abstract int zzc();

    public abstract zzck zzd(int r1, zzck zzckVar, boolean z);

    public abstract zzcm zze(int r1, zzcm zzcmVar, long j);

    public abstract Object zzf(int r1);

    public int zzg(boolean z) {
        return zzo() ? -1 : 0;
    }

    public int zzh(boolean z) {
        if (zzo()) {
            return -1;
        }
        return zzc() - 1;
    }

    public final int zzi(int r4, zzck zzckVar, zzcm zzcmVar, int r7, boolean z) {
        int r5 = zzd(r4, zzckVar, false).zzd;
        if (zze(r5, zzcmVar, 0L).zzp == r4) {
            int zzj = zzj(r5, r7, z);
            if (zzj == -1) {
                return -1;
            }
            return zze(zzj, zzcmVar, 0L).zzo;
        }
        return r4 + 1;
    }

    public int zzj(int r3, int r4, boolean z) {
        if (r4 == 0) {
            if (r3 == zzh(z)) {
                return -1;
            }
            return r3 + 1;
        } else if (r4 != 1) {
            if (r4 == 2) {
                return r3 == zzh(z) ? zzg(z) : r3 + 1;
            }
            throw new IllegalStateException();
        } else {
            return r3;
        }
    }

    public int zzk(int r1, int r2, boolean z) {
        if (r1 == zzg(false)) {
            return -1;
        }
        return r1 - 1;
    }

    public final Pair zzl(zzcm zzcmVar, zzck zzckVar, int r11, long j) {
        Pair zzm = zzm(zzcmVar, zzckVar, r11, j, 0L);
        Objects.requireNonNull(zzm);
        return zzm;
    }

    public final Pair zzm(zzcm zzcmVar, zzck zzckVar, int r10, long j, long j2) {
        zzdd.zza(r10, 0, zzc());
        zze(r10, zzcmVar, j2);
        if (j == C1856C.TIME_UNSET) {
            long j3 = zzcmVar.zzm;
            j = 0;
        }
        int r102 = zzcmVar.zzo;
        zzd(r102, zzckVar, false);
        while (r102 < zzcmVar.zzp) {
            long j4 = zzckVar.zzf;
            int r0 = (j > 0L ? 1 : (j == 0L ? 0 : -1));
            if (r0 == 0) {
                break;
            }
            int r4 = r102 + 1;
            long j5 = zzd(r4, zzckVar, false).zzf;
            if (r0 < 0) {
                break;
            }
            r102 = r4;
        }
        zzd(r102, zzckVar, true);
        long j6 = zzckVar.zzf;
        long j7 = zzckVar.zze;
        if (j7 != C1856C.TIME_UNSET) {
            j = Math.min(j, j7 - 1);
        }
        long max = Math.max(0L, j);
        Object obj = zzckVar.zzc;
        Objects.requireNonNull(obj);
        return Pair.create(obj, Long.valueOf(max));
    }

    public zzck zzn(Object obj, zzck zzckVar) {
        return zzd(zza(obj), zzckVar, true);
    }

    public final boolean zzo() {
        return zzc() == 0;
    }
}
