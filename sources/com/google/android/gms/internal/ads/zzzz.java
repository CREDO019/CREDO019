package com.google.android.gms.internal.ads;

import com.facebook.imagepipeline.memory.BitmapCounterConfig;
import com.google.android.exoplayer2.audio.AacUtil;
import com.google.android.exoplayer2.audio.Ac3Util;
import com.google.android.exoplayer2.audio.DtsUtil;
import com.google.android.exoplayer2.audio.MpegAudioUtil;
import com.google.android.exoplayer2.audio.OpusUtil;
import com.google.android.exoplayer2.util.MimeTypes;

/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
public final class zzzz {
    private static final String[] zza = {MimeTypes.AUDIO_MPEG_L1, MimeTypes.AUDIO_MPEG_L2, MimeTypes.AUDIO_MPEG};
    private static final int[] zzb = {44100, OpusUtil.SAMPLE_RATE, 32000};
    private static final int[] zzc = {32000, 64000, 96000, 128000, 160000, DtsUtil.DTS_MAX_RATE_BYTES_PER_SECOND, 224000, AacUtil.AAC_XHE_MAX_RATE_BYTES_PER_SECOND, 288000, 320000, 352000, 384000, 416000, 448000};
    private static final int[] zzd = {32000, OpusUtil.SAMPLE_RATE, 56000, 64000, Ac3Util.AC3_MAX_RATE_BYTES_PER_SECOND, 96000, 112000, 128000, 144000, 160000, 176000, DtsUtil.DTS_MAX_RATE_BYTES_PER_SECOND, 224000, AacUtil.AAC_XHE_MAX_RATE_BYTES_PER_SECOND};
    private static final int[] zze = {32000, OpusUtil.SAMPLE_RATE, 56000, 64000, Ac3Util.AC3_MAX_RATE_BYTES_PER_SECOND, 96000, 112000, 128000, 160000, DtsUtil.DTS_MAX_RATE_BYTES_PER_SECOND, 224000, AacUtil.AAC_XHE_MAX_RATE_BYTES_PER_SECOND, 320000, 384000};
    private static final int[] zzf = {32000, MpegAudioUtil.MAX_RATE_BYTES_PER_SECOND, OpusUtil.SAMPLE_RATE, 56000, 64000, Ac3Util.AC3_MAX_RATE_BYTES_PER_SECOND, 96000, 112000, 128000, 160000, DtsUtil.DTS_MAX_RATE_BYTES_PER_SECOND, 224000, AacUtil.AAC_XHE_MAX_RATE_BYTES_PER_SECOND, 320000};
    private static final int[] zzg = {8000, AacUtil.AAC_HE_V1_MAX_RATE_BYTES_PER_SECOND, 24000, 32000, MpegAudioUtil.MAX_RATE_BYTES_PER_SECOND, OpusUtil.SAMPLE_RATE, 56000, 64000, Ac3Util.AC3_MAX_RATE_BYTES_PER_SECOND, 96000, 112000, 128000, 144000, 160000};

    public static int zzc(int r6) {
        int r0;
        int r3;
        if (!zzm(r6) || (r0 = (r6 >>> 19) & 3) == 1 || (r3 = (r6 >>> 17) & 3) == 0) {
            return -1;
        }
        int r4 = (r6 >>> 12) & 15;
        int r62 = (r6 >>> 10) & 3;
        if (r4 == 0 || r4 == 15 || r62 == 3) {
            return -1;
        }
        return zzl(r0, r3);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static int zzl(int r2, int r3) {
        if (r3 == 1) {
            return r2 == 3 ? 1152 : 576;
        } else if (r3 != 2) {
            return BitmapCounterConfig.DEFAULT_MAX_BITMAP_COUNT;
        } else {
            return 1152;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static boolean zzm(int r1) {
        return (r1 & (-2097152)) == -2097152;
    }

    public static int zzb(int r8) {
        int r0;
        int r4;
        int r5;
        int r6;
        int r1;
        if (!zzm(r8) || (r0 = (r8 >>> 19) & 3) == 1 || (r4 = (r8 >>> 17) & 3) == 0 || (r5 = (r8 >>> 12) & 15) == 0 || r5 == 15 || (r6 = (r8 >>> 10) & 3) == 3) {
            return -1;
        }
        int r62 = zzb[r6];
        if (r0 == 2) {
            r62 /= 2;
        } else if (r0 == 0) {
            r62 /= 4;
        }
        int r82 = (r8 >>> 9) & 1;
        if (r4 == 3) {
            return ((((r0 == 3 ? zzc[r5 - 1] : zzd[r5 - 1]) * 12) / r62) + r82) * 4;
        }
        if (r0 == 3) {
            r1 = r4 == 2 ? zze[r5 - 1] : zzf[r5 - 1];
        } else {
            r1 = zzg[r5 - 1];
        }
        if (r0 == 3) {
            return ((r1 * 144) / r62) + r82;
        }
        return (((r4 == 1 ? 72 : 144) * r1) / r62) + r82;
    }
}
