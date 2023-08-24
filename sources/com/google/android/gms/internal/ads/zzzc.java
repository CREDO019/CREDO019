package com.google.android.gms.internal.ads;

import com.facebook.imagepipeline.memory.BitmapCounterConfig;
import com.google.android.exoplayer2.audio.AacUtil;
import com.google.android.exoplayer2.audio.OpusUtil;
import com.google.android.exoplayer2.util.MimeTypes;
import java.util.Arrays;

/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
public final class zzzc {
    public static final /* synthetic */ int zza = 0;
    private static final int[] zzb = {1, 2, 2, 2, 2, 3, 3, 4, 4, 5, 6, 6, 6, 7, 8, 8};
    private static final int[] zzc = {-1, 8000, AacUtil.AAC_HE_V1_MAX_RATE_BYTES_PER_SECOND, 32000, -1, -1, 11025, 22050, 44100, -1, -1, 12000, 24000, OpusUtil.SAMPLE_RATE, -1, -1};
    private static final int[] zzd = {64, 112, 128, 192, 224, 256, BitmapCounterConfig.DEFAULT_MAX_BITMAP_COUNT, 448, 512, 640, 768, 896, 1024, 1152, 1280, 1536, 1920, 2048, 2304, 2560, 2688, 2816, 2823, 2944, 3072, 3840, 4096, 6144, 7680};

    public static zzaf zza(byte[] bArr, String str, String str2, zzx zzxVar) {
        zzec zzecVar;
        if (bArr[0] == Byte.MAX_VALUE) {
            zzecVar = new zzec(bArr, bArr.length);
        } else {
            byte[] copyOf = Arrays.copyOf(bArr, bArr.length);
            byte b = copyOf[0];
            if (b == -2 || b == -1) {
                for (int r0 = 0; r0 < copyOf.length - 1; r0 += 2) {
                    byte b2 = copyOf[r0];
                    int r4 = r0 + 1;
                    copyOf[r0] = copyOf[r4];
                    copyOf[r4] = b2;
                }
            }
            int length = copyOf.length;
            zzecVar = new zzec(copyOf, length);
            if (copyOf[0] == 31) {
                zzec zzecVar2 = new zzec(copyOf, length);
                while (zzecVar2.zza() >= 16) {
                    zzecVar2.zzj(2);
                    zzecVar.zze(zzecVar2.zzc(14), 14);
                }
            }
            zzecVar.zzg(copyOf, copyOf.length);
        }
        zzecVar.zzj(60);
        int r6 = zzb[zzecVar.zzc(6)];
        int r3 = zzc[zzecVar.zzc(4)];
        int zzc2 = zzecVar.zzc(5);
        int r1 = zzc2 < 29 ? (zzd[zzc2] * 1000) / 2 : -1;
        zzecVar.zzj(10);
        int r9 = zzecVar.zzc(2) > 0 ? 1 : 0;
        zzad zzadVar = new zzad();
        zzadVar.zzH(str);
        zzadVar.zzS(MimeTypes.AUDIO_DTS);
        zzadVar.zzv(r1);
        zzadVar.zzw(r6 + r9);
        zzadVar.zzT(r3);
        zzadVar.zzB(null);
        zzadVar.zzK(str2);
        return zzadVar.zzY();
    }
}
