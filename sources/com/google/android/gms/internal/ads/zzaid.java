package com.google.android.gms.internal.ads;

import android.util.Log;
import com.google.android.exoplayer2.C1856C;
import java.io.IOException;

/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
final class zzaid {
    private boolean zzc;
    private boolean zzd;
    private boolean zze;
    private final zzej zza = new zzej(0);
    private long zzf = C1856C.TIME_UNSET;
    private long zzg = C1856C.TIME_UNSET;
    private long zzh = C1856C.TIME_UNSET;
    private final zzed zzb = new zzed();

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzaid(int r3) {
    }

    private final int zze(zzzg zzzgVar) {
        zzed zzedVar = this.zzb;
        byte[] bArr = zzel.zzf;
        int length = bArr.length;
        zzedVar.zzD(bArr, 0);
        this.zzc = true;
        zzzgVar.zzj();
        return 0;
    }

    public final int zza(zzzg zzzgVar, zzaaf zzaafVar, int r15) throws IOException {
        if (r15 <= 0) {
            zze(zzzgVar);
            return 0;
        }
        boolean z = this.zze;
        long j = C1856C.TIME_UNSET;
        if (!z) {
            long zzd = zzzgVar.zzd();
            int min = (int) Math.min(112800L, zzd);
            long j2 = zzd - min;
            if (zzzgVar.zzf() != j2) {
                zzaafVar.zza = j2;
                return 1;
            }
            this.zzb.zzC(min);
            zzzgVar.zzj();
            ((zzyv) zzzgVar).zzm(this.zzb.zzH(), 0, min, false);
            zzed zzedVar = this.zzb;
            int zzc = zzedVar.zzc();
            int zzd2 = zzedVar.zzd();
            int r3 = zzd2 - 188;
            while (true) {
                if (r3 < zzc) {
                    break;
                }
                byte[] zzH = zzedVar.zzH();
                int r8 = -4;
                int r9 = 0;
                while (true) {
                    if (r8 > 4) {
                        break;
                    }
                    int r10 = (r8 * 188) + r3;
                    if (r10 < zzc || r10 >= zzd2 || zzH[r10] != 71) {
                        r9 = 0;
                    } else {
                        r9++;
                        if (r9 == 5) {
                            long zzb = zzain.zzb(zzedVar, r3, r15);
                            if (zzb != C1856C.TIME_UNSET) {
                                j = zzb;
                                break;
                            }
                        }
                    }
                    r8++;
                }
                r3--;
            }
            this.zzg = j;
            this.zze = true;
            return 0;
        } else if (this.zzg == C1856C.TIME_UNSET) {
            zze(zzzgVar);
            return 0;
        } else if (this.zzd) {
            long j3 = this.zzf;
            if (j3 == C1856C.TIME_UNSET) {
                zze(zzzgVar);
                return 0;
            }
            long zzb2 = this.zza.zzb(this.zzg) - this.zza.zzb(j3);
            this.zzh = zzb2;
            if (zzb2 < 0) {
                Log.w("TsDurationReader", "Invalid duration: " + zzb2 + ". Using TIME_UNSET instead.");
                this.zzh = C1856C.TIME_UNSET;
            }
            zze(zzzgVar);
            return 0;
        } else {
            int min2 = (int) Math.min(112800L, zzzgVar.zzd());
            if (zzzgVar.zzf() != 0) {
                zzaafVar.zza = 0L;
                return 1;
            }
            this.zzb.zzC(min2);
            zzzgVar.zzj();
            ((zzyv) zzzgVar).zzm(this.zzb.zzH(), 0, min2, false);
            zzed zzedVar2 = this.zzb;
            int zzc2 = zzedVar2.zzc();
            int zzd3 = zzedVar2.zzd();
            while (true) {
                if (zzc2 >= zzd3) {
                    break;
                }
                if (zzedVar2.zzH()[zzc2] == 71) {
                    long zzb3 = zzain.zzb(zzedVar2, zzc2, r15);
                    if (zzb3 != C1856C.TIME_UNSET) {
                        j = zzb3;
                        break;
                    }
                }
                zzc2++;
            }
            this.zzf = j;
            this.zzd = true;
            return 0;
        }
    }

    public final long zzb() {
        return this.zzh;
    }

    public final zzej zzc() {
        return this.zza;
    }

    public final boolean zzd() {
        return this.zzc;
    }
}
