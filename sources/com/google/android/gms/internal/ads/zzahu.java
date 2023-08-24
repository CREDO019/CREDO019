package com.google.android.gms.internal.ads;

import android.util.Log;
import com.google.android.exoplayer2.C1856C;
import com.google.android.exoplayer2.audio.SilenceSkippingAudioProcessor;
import java.io.IOException;

/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
final class zzahu {
    private boolean zzc;
    private boolean zzd;
    private boolean zze;
    private final zzej zza = new zzej(0);
    private long zzf = C1856C.TIME_UNSET;
    private long zzg = C1856C.TIME_UNSET;
    private long zzh = C1856C.TIME_UNSET;
    private final zzed zzb = new zzed();

    public static long zzc(zzed zzedVar) {
        int zzc = zzedVar.zzc();
        if (zzedVar.zza() < 9) {
            return C1856C.TIME_UNSET;
        }
        byte[] bArr = new byte[9];
        zzedVar.zzB(bArr, 0, 9);
        zzedVar.zzF(zzc);
        if ((bArr[0] & 196) == 68 && (bArr[2] & 4) == 4 && (bArr[4] & 4) == 4 && (bArr[5] & 1) == 1 && (bArr[8] & 3) == 3) {
            long j = bArr[0];
            byte b = bArr[1];
            long j2 = bArr[2];
            return ((bArr[3] & 255) << 5) | ((j & 3) << 28) | (((56 & j) >> 3) << 30) | ((b & 255) << 20) | (((j2 & 248) >> 3) << 15) | ((j2 & 3) << 13) | ((bArr[4] & 248) >> 3);
        }
        return C1856C.TIME_UNSET;
    }

    private final int zzf(zzzg zzzgVar) {
        zzed zzedVar = this.zzb;
        byte[] bArr = zzel.zzf;
        int length = bArr.length;
        zzedVar.zzD(bArr, 0);
        this.zzc = true;
        zzzgVar.zzj();
        return 0;
    }

    private static final int zzg(byte[] bArr, int r3) {
        return (bArr[r3 + 3] & 255) | ((bArr[r3] & 255) << 24) | ((bArr[r3 + 1] & 255) << 16) | ((bArr[r3 + 2] & 255) << 8);
    }

    public final int zza(zzzg zzzgVar, zzaaf zzaafVar) throws IOException {
        boolean z = this.zze;
        long j = C1856C.TIME_UNSET;
        if (!z) {
            long zzd = zzzgVar.zzd();
            int min = (int) Math.min((long) SilenceSkippingAudioProcessor.DEFAULT_PADDING_SILENCE_US, zzd);
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
            int zzd2 = zzedVar.zzd() - 4;
            while (true) {
                if (zzd2 < zzc) {
                    break;
                }
                if (zzg(zzedVar.zzH(), zzd2) == 442) {
                    zzedVar.zzF(zzd2 + 4);
                    long zzc2 = zzc(zzedVar);
                    if (zzc2 != C1856C.TIME_UNSET) {
                        j = zzc2;
                        break;
                    }
                }
                zzd2--;
            }
            this.zzg = j;
            this.zze = true;
            return 0;
        } else if (this.zzg == C1856C.TIME_UNSET) {
            zzf(zzzgVar);
            return 0;
        } else if (this.zzd) {
            long j3 = this.zzf;
            if (j3 == C1856C.TIME_UNSET) {
                zzf(zzzgVar);
                return 0;
            }
            long zzb = this.zza.zzb(this.zzg) - this.zza.zzb(j3);
            this.zzh = zzb;
            if (zzb < 0) {
                Log.w("PsDurationReader", "Invalid duration: " + zzb + ". Using TIME_UNSET instead.");
                this.zzh = C1856C.TIME_UNSET;
            }
            zzf(zzzgVar);
            return 0;
        } else {
            int min2 = (int) Math.min((long) SilenceSkippingAudioProcessor.DEFAULT_PADDING_SILENCE_US, zzzgVar.zzd());
            if (zzzgVar.zzf() != 0) {
                zzaafVar.zza = 0L;
                return 1;
            }
            this.zzb.zzC(min2);
            zzzgVar.zzj();
            ((zzyv) zzzgVar).zzm(this.zzb.zzH(), 0, min2, false);
            zzed zzedVar2 = this.zzb;
            int zzc3 = zzedVar2.zzc();
            int zzd3 = zzedVar2.zzd();
            while (true) {
                if (zzc3 >= zzd3 - 3) {
                    break;
                }
                if (zzg(zzedVar2.zzH(), zzc3) == 442) {
                    zzedVar2.zzF(zzc3 + 4);
                    long zzc4 = zzc(zzedVar2);
                    if (zzc4 != C1856C.TIME_UNSET) {
                        j = zzc4;
                        break;
                    }
                }
                zzc3++;
            }
            this.zzf = j;
            this.zzd = true;
            return 0;
        }
    }

    public final long zzb() {
        return this.zzh;
    }

    public final zzej zzd() {
        return this.zza;
    }

    public final boolean zze() {
        return this.zzc;
    }
}
