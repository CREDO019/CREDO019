package com.google.android.gms.internal.ads;

import com.google.android.exoplayer2.audio.AacUtil;
import com.google.android.exoplayer2.audio.OpusUtil;

/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
public final class zzyd {
    public static final /* synthetic */ int zza = 0;
    private static final int[] zzb = {96000, 88200, 64000, OpusUtil.SAMPLE_RATE, 44100, 32000, 24000, 22050, AacUtil.AAC_HE_V1_MAX_RATE_BYTES_PER_SECOND, 12000, 11025, 8000, 7350};
    private static final int[] zzc = {0, 1, 2, 3, 4, 5, 6, 8, -1, -1, -1, 7, 8, -1, 8, -1};

    public static zzyc zza(byte[] bArr) throws zzbu {
        return zzb(new zzec(bArr, bArr.length), false);
    }

    /* JADX WARN: Code restructure failed: missing block: B:53:0x00bb, code lost:
        if (r11 != 3) goto L55;
     */
    /* JADX WARN: Removed duplicated region for block: B:38:0x0096  */
    /* JADX WARN: Removed duplicated region for block: B:51:0x00b5  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static com.google.android.gms.internal.ads.zzyc zzb(com.google.android.gms.internal.ads.zzec r11, boolean r12) throws com.google.android.gms.internal.ads.zzbu {
        /*
            Method dump skipped, instructions count: 270
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.ads.zzyd.zzb(com.google.android.gms.internal.ads.zzec, boolean):com.google.android.gms.internal.ads.zzyc");
    }

    private static int zzc(zzec zzecVar) {
        int zzc2 = zzecVar.zzc(5);
        return zzc2 == 31 ? zzecVar.zzc(6) + 32 : zzc2;
    }

    private static int zzd(zzec zzecVar) throws zzbu {
        int zzc2 = zzecVar.zzc(4);
        if (zzc2 == 15) {
            return zzecVar.zzc(24);
        }
        if (zzc2 < 13) {
            return zzb[zzc2];
        }
        throw zzbu.zza(null, null);
    }
}
