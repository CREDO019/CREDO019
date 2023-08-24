package com.google.android.exoplayer2.audio;

import com.google.android.exoplayer2.util.MimeTypes;

/* loaded from: classes2.dex */
public final class MpegAudioUtil {
    public static final int MAX_FRAME_SIZE_BYTES = 4096;
    private static final int SAMPLES_PER_FRAME_L1 = 384;
    private static final int SAMPLES_PER_FRAME_L2 = 1152;
    private static final int SAMPLES_PER_FRAME_L3_V1 = 1152;
    private static final int SAMPLES_PER_FRAME_L3_V2 = 576;
    private static final String[] MIME_TYPE_BY_LAYER = {MimeTypes.AUDIO_MPEG_L1, MimeTypes.AUDIO_MPEG_L2, MimeTypes.AUDIO_MPEG};
    private static final int[] SAMPLING_RATE_V1 = {44100, OpusUtil.SAMPLE_RATE, 32000};
    private static final int[] BITRATE_V1_L1 = {32000, 64000, 96000, 128000, 160000, DtsUtil.DTS_MAX_RATE_BYTES_PER_SECOND, 224000, AacUtil.AAC_XHE_MAX_RATE_BYTES_PER_SECOND, 288000, 320000, 352000, 384000, 416000, 448000};
    private static final int[] BITRATE_V2_L1 = {32000, OpusUtil.SAMPLE_RATE, 56000, 64000, Ac3Util.AC3_MAX_RATE_BYTES_PER_SECOND, 96000, 112000, 128000, 144000, 160000, 176000, DtsUtil.DTS_MAX_RATE_BYTES_PER_SECOND, 224000, AacUtil.AAC_XHE_MAX_RATE_BYTES_PER_SECOND};
    private static final int[] BITRATE_V1_L2 = {32000, OpusUtil.SAMPLE_RATE, 56000, 64000, Ac3Util.AC3_MAX_RATE_BYTES_PER_SECOND, 96000, 112000, 128000, 160000, DtsUtil.DTS_MAX_RATE_BYTES_PER_SECOND, 224000, AacUtil.AAC_XHE_MAX_RATE_BYTES_PER_SECOND, 320000, 384000};
    public static final int MAX_RATE_BYTES_PER_SECOND = 40000;
    private static final int[] BITRATE_V1_L3 = {32000, MAX_RATE_BYTES_PER_SECOND, OpusUtil.SAMPLE_RATE, 56000, 64000, Ac3Util.AC3_MAX_RATE_BYTES_PER_SECOND, 96000, 112000, 128000, 160000, DtsUtil.DTS_MAX_RATE_BYTES_PER_SECOND, 224000, AacUtil.AAC_XHE_MAX_RATE_BYTES_PER_SECOND, 320000};
    private static final int[] BITRATE_V2 = {8000, AacUtil.AAC_HE_V1_MAX_RATE_BYTES_PER_SECOND, 24000, 32000, MAX_RATE_BYTES_PER_SECOND, OpusUtil.SAMPLE_RATE, 56000, 64000, Ac3Util.AC3_MAX_RATE_BYTES_PER_SECOND, 96000, 112000, 128000, 144000, 160000};

    /* JADX INFO: Access modifiers changed from: private */
    public static boolean isMagicPresent(int r1) {
        return (r1 & (-2097152)) == -2097152;
    }

    /* loaded from: classes2.dex */
    public static final class Header {
        public int bitrate;
        public int channels;
        public int frameSize;
        public String mimeType;
        public int sampleRate;
        public int samplesPerFrame;
        public int version;

        public boolean setForHeaderData(int r9) {
            int r0;
            int r4;
            int r5;
            int r6;
            if (!MpegAudioUtil.isMagicPresent(r9) || (r0 = (r9 >>> 19) & 3) == 1 || (r4 = (r9 >>> 17) & 3) == 0 || (r5 = (r9 >>> 12) & 15) == 0 || r5 == 15 || (r6 = (r9 >>> 10) & 3) == 3) {
                return false;
            }
            this.version = r0;
            this.mimeType = MpegAudioUtil.MIME_TYPE_BY_LAYER[3 - r4];
            int r1 = MpegAudioUtil.SAMPLING_RATE_V1[r6];
            this.sampleRate = r1;
            if (r0 == 2) {
                this.sampleRate = r1 / 2;
            } else if (r0 == 0) {
                this.sampleRate = r1 / 4;
            }
            int r12 = (r9 >>> 9) & 1;
            this.samplesPerFrame = MpegAudioUtil.getFrameSizeInSamples(r0, r4);
            if (r4 == 3) {
                int r02 = r0 == 3 ? MpegAudioUtil.BITRATE_V1_L1[r5 - 1] : MpegAudioUtil.BITRATE_V2_L1[r5 - 1];
                this.bitrate = r02;
                this.frameSize = (((r02 * 12) / this.sampleRate) + r12) * 4;
            } else {
                if (r0 == 3) {
                    int r03 = r4 == 2 ? MpegAudioUtil.BITRATE_V1_L2[r5 - 1] : MpegAudioUtil.BITRATE_V1_L3[r5 - 1];
                    this.bitrate = r03;
                    this.frameSize = ((r03 * 144) / this.sampleRate) + r12;
                } else {
                    int r04 = MpegAudioUtil.BITRATE_V2[r5 - 1];
                    this.bitrate = r04;
                    this.frameSize = (((r4 == 1 ? 72 : 144) * r04) / this.sampleRate) + r12;
                }
            }
            this.channels = ((r9 >> 6) & 3) == 3 ? 1 : 2;
            return true;
        }
    }

    public static int getFrameSize(int r7) {
        int r0;
        int r4;
        int r5;
        int r6;
        int r52;
        if (!isMagicPresent(r7) || (r0 = (r7 >>> 19) & 3) == 1 || (r4 = (r7 >>> 17) & 3) == 0 || (r5 = (r7 >>> 12) & 15) == 0 || r5 == 15 || (r6 = (r7 >>> 10) & 3) == 3) {
            return -1;
        }
        int r1 = SAMPLING_RATE_V1[r6];
        if (r0 == 2) {
            r1 /= 2;
        } else if (r0 == 0) {
            r1 /= 4;
        }
        int r72 = (r7 >>> 9) & 1;
        if (r4 == 3) {
            return ((((r0 == 3 ? BITRATE_V1_L1[r5 - 1] : BITRATE_V2_L1[r5 - 1]) * 12) / r1) + r72) * 4;
        }
        if (r0 == 3) {
            r52 = r4 == 2 ? BITRATE_V1_L2[r5 - 1] : BITRATE_V1_L3[r5 - 1];
        } else {
            r52 = BITRATE_V2[r5 - 1];
        }
        if (r0 == 3) {
            return ((r52 * 144) / r1) + r72;
        }
        return (((r4 == 1 ? 72 : 144) * r52) / r1) + r72;
    }

    public static int parseMpegAudioFrameSampleCount(int r6) {
        int r0;
        int r3;
        if (!isMagicPresent(r6) || (r0 = (r6 >>> 19) & 3) == 1 || (r3 = (r6 >>> 17) & 3) == 0) {
            return -1;
        }
        int r4 = (r6 >>> 12) & 15;
        int r62 = (r6 >>> 10) & 3;
        if (r4 == 0 || r4 == 15 || r62 == 3) {
            return -1;
        }
        return getFrameSizeInSamples(r0, r3);
    }

    private MpegAudioUtil() {
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static int getFrameSizeInSamples(int r3, int r4) {
        if (r4 == 1) {
            if (r3 == 3) {
                return 1152;
            }
            return SAMPLES_PER_FRAME_L3_V2;
        } else if (r4 != 2) {
            if (r4 == 3) {
                return 384;
            }
            throw new IllegalArgumentException();
        } else {
            return 1152;
        }
    }
}
