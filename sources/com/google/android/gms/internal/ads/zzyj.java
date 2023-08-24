package com.google.android.gms.internal.ads;

import com.google.android.exoplayer2.PlaybackException;
import com.google.common.primitives.SignedBytes;

/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
public final class zzyj {
    public static final /* synthetic */ int zza = 0;
    private static final int[] zzb = {PlaybackException.ERROR_CODE_IO_NETWORK_CONNECTION_TIMEOUT, 2000, 1920, 1601, 1600, 1001, 1000, 960, 800, 800, 480, 400, 400, 2048};

    /* JADX WARN: Code restructure failed: missing block: B:42:0x0093, code lost:
        if (r11 != 11) goto L44;
     */
    /* JADX WARN: Code restructure failed: missing block: B:45:0x0098, code lost:
        if (r11 != 11) goto L44;
     */
    /* JADX WARN: Code restructure failed: missing block: B:48:0x009d, code lost:
        if (r11 != 8) goto L44;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static com.google.android.gms.internal.ads.zzyi zza(com.google.android.gms.internal.ads.zzec r11) {
        /*
            r0 = 16
            int r1 = r11.zzc(r0)
            int r0 = r11.zzc(r0)
            r2 = 4
            r3 = 65535(0xffff, float:9.1834E-41)
            if (r0 != r3) goto L18
            r0 = 24
            int r0 = r11.zzc(r0)
            r3 = 7
            goto L19
        L18:
            r3 = 4
        L19:
            int r0 = r0 + r3
            r3 = 44097(0xac41, float:6.1793E-41)
            if (r1 != r3) goto L21
            int r0 = r0 + 2
        L21:
            r7 = r0
            r0 = 2
            int r1 = r11.zzc(r0)
            r3 = 0
            r4 = 3
            if (r1 != r4) goto L3d
            r1 = 0
        L2c:
            int r5 = r11.zzc(r0)
            int r1 = r1 + r5
            boolean r5 = r11.zzl()
            if (r5 != 0) goto L39
            int r1 = r1 + r4
            goto L3d
        L39:
            int r1 = r1 + 1
            int r1 = r1 << r0
            goto L2c
        L3d:
            r5 = 10
            int r5 = r11.zzc(r5)
            boolean r6 = r11.zzl()
            if (r6 == 0) goto L52
            int r6 = r11.zzc(r4)
            if (r6 <= 0) goto L52
            r11.zzj(r0)
        L52:
            boolean r6 = r11.zzl()
            r8 = 44100(0xac44, float:6.1797E-41)
            r9 = 48000(0xbb80, float:6.7262E-41)
            r10 = 1
            if (r10 == r6) goto L63
            r6 = 44100(0xac44, float:6.1797E-41)
            goto L66
        L63:
            r6 = 48000(0xbb80, float:6.7262E-41)
        L66:
            int r11 = r11.zzc(r2)
            if (r6 != r8) goto L76
            r8 = 13
            if (r11 != r8) goto L76
            int[] r11 = com.google.android.gms.internal.ads.zzyj.zzb
            r11 = r11[r8]
            r8 = r11
            goto La6
        L76:
            if (r6 != r9) goto La5
            r8 = 14
            if (r11 >= r8) goto La5
            int[] r3 = com.google.android.gms.internal.ads.zzyj.zzb
            r3 = r3[r11]
            int r5 = r5 % 5
            r8 = 8
            if (r5 == r10) goto L9b
            r9 = 11
            if (r5 == r0) goto L96
            if (r5 == r4) goto L9b
            if (r5 == r2) goto L8f
            goto La0
        L8f:
            if (r11 == r4) goto La2
            if (r11 == r8) goto La2
            if (r11 != r9) goto La0
            goto La2
        L96:
            if (r11 == r8) goto La2
            if (r11 != r9) goto La0
            goto La2
        L9b:
            if (r11 == r4) goto La2
            if (r11 != r8) goto La0
            goto La2
        La0:
            r8 = r3
            goto La6
        La2:
            int r3 = r3 + 1
            goto La0
        La5:
            r8 = 0
        La6:
            com.google.android.gms.internal.ads.zzyi r11 = new com.google.android.gms.internal.ads.zzyi
            r5 = 2
            r9 = 0
            r3 = r11
            r4 = r1
            r3.<init>(r4, r5, r6, r7, r8, r9)
            return r11
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.ads.zzyj.zza(com.google.android.gms.internal.ads.zzec):com.google.android.gms.internal.ads.zzyi");
    }

    public static void zzb(int r2, zzed zzedVar) {
        zzedVar.zzC(7);
        byte[] zzH = zzedVar.zzH();
        zzH[0] = -84;
        zzH[1] = SignedBytes.MAX_POWER_OF_TWO;
        zzH[2] = -1;
        zzH[3] = -1;
        zzH[4] = (byte) ((r2 >> 16) & 255);
        zzH[5] = (byte) ((r2 >> 8) & 255);
        zzH[6] = (byte) (r2 & 255);
    }
}
