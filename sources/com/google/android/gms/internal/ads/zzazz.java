package com.google.android.gms.internal.ads;

import android.util.Pair;
import com.google.android.exoplayer2.audio.AacUtil;
import com.google.android.exoplayer2.audio.OpusUtil;

/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
public final class zzazz {
    private static final byte[] zza = {0, 0, 0, 1};
    private static final int[] zzb = {96000, 88200, 64000, OpusUtil.SAMPLE_RATE, 44100, 32000, 24000, 22050, AacUtil.AAC_HE_V1_MAX_RATE_BYTES_PER_SECOND, 12000, 11025, 8000, 7350};
    private static final int[] zzc = {0, 1, 2, 3, 4, 5, 6, 8, -1, -1, -1, 7, 8, -1, 8, -1};

    public static Pair zza(byte[] bArr) {
        zzbaf zzbafVar = new zzbaf(bArr, bArr.length);
        int zzc2 = zzc(zzbafVar);
        int zzd = zzd(zzbafVar);
        int zza2 = zzbafVar.zza(4);
        if (zzc2 == 5 || zzc2 == 29) {
            zzd = zzd(zzbafVar);
            if (zzc(zzbafVar) == 22) {
                zza2 = zzbafVar.zza(4);
            }
        }
        int r5 = zzc[zza2];
        zzazy.zzc(r5 != -1);
        return Pair.create(Integer.valueOf(zzd), Integer.valueOf(r5));
    }

    public static byte[] zzb(byte[] bArr, int r5, int r6) {
        byte[] bArr2 = new byte[r6 + 4];
        System.arraycopy(zza, 0, bArr2, 0, 4);
        System.arraycopy(bArr, r5, bArr2, 4, r6);
        return bArr2;
    }

    private static int zzc(zzbaf zzbafVar) {
        int zza2 = zzbafVar.zza(5);
        return zza2 == 31 ? zzbafVar.zza(6) + 32 : zza2;
    }

    private static int zzd(zzbaf zzbafVar) {
        int zza2 = zzbafVar.zza(4);
        if (zza2 == 15) {
            return zzbafVar.zza(24);
        }
        zzazy.zzc(zza2 < 13);
        return zzb[zza2];
    }
}
