package com.google.android.exoplayer2.audio;

import com.google.android.exoplayer2.util.Util;

/* loaded from: classes2.dex */
public final class WavUtil {
    public static final int DATA_FOURCC = 1684108385;
    public static final int DS64_FOURCC = 1685272116;
    public static final int FMT_FOURCC = 1718449184;
    public static final int RF64_FOURCC = 1380333108;
    public static final int RIFF_FOURCC = 1380533830;
    public static final int TYPE_ALAW = 6;
    public static final int TYPE_FLOAT = 3;
    public static final int TYPE_IMA_ADPCM = 17;
    public static final int TYPE_MLAW = 7;
    public static final int TYPE_PCM = 1;
    public static final int TYPE_WAVE_FORMAT_EXTENSIBLE = 65534;
    public static final int WAVE_FOURCC = 1463899717;

    public static int getTypeForPcmEncoding(int r2) {
        if (r2 == 2 || r2 == 3) {
            return 1;
        }
        if (r2 != 4) {
            if (r2 == 536870912 || r2 == 805306368) {
                return 1;
            }
            throw new IllegalArgumentException();
        }
        return 3;
    }

    public static int getPcmEncodingForType(int r2, int r3) {
        if (r2 != 1) {
            if (r2 == 3) {
                return r3 == 32 ? 4 : 0;
            } else if (r2 != 65534) {
                return 0;
            }
        }
        return Util.getPcmEncoding(r3);
    }

    private WavUtil() {
    }
}
