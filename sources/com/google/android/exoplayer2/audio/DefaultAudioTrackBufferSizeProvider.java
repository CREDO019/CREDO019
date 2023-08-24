package com.google.android.exoplayer2.audio;

import com.google.android.exoplayer2.audio.DefaultAudioSink;
import com.google.android.exoplayer2.util.Util;
import com.google.common.primitives.Ints;

/* loaded from: classes2.dex */
public class DefaultAudioTrackBufferSizeProvider implements DefaultAudioSink.AudioTrackBufferSizeProvider {
    private static final int AC3_BUFFER_MULTIPLICATION_FACTOR = 2;
    private static final int MAX_PCM_BUFFER_DURATION_US = 750000;
    private static final int MIN_PCM_BUFFER_DURATION_US = 250000;
    private static final int OFFLOAD_BUFFER_DURATION_US = 50000000;
    private static final int PASSTHROUGH_BUFFER_DURATION_US = 250000;
    private static final int PCM_BUFFER_MULTIPLICATION_FACTOR = 4;
    public final int ac3BufferMultiplicationFactor;
    protected final int maxPcmBufferDurationUs;
    protected final int minPcmBufferDurationUs;
    protected final int offloadBufferDurationUs;
    protected final int passthroughBufferDurationUs;
    protected final int pcmBufferMultiplicationFactor;

    /* loaded from: classes2.dex */
    public static class Builder {
        private int minPcmBufferDurationUs = 250000;
        private int maxPcmBufferDurationUs = DefaultAudioTrackBufferSizeProvider.MAX_PCM_BUFFER_DURATION_US;
        private int pcmBufferMultiplicationFactor = 4;
        private int passthroughBufferDurationUs = 250000;
        private int offloadBufferDurationUs = DefaultAudioTrackBufferSizeProvider.OFFLOAD_BUFFER_DURATION_US;
        private int ac3BufferMultiplicationFactor = 2;

        public Builder setMinPcmBufferDurationUs(int r1) {
            this.minPcmBufferDurationUs = r1;
            return this;
        }

        public Builder setMaxPcmBufferDurationUs(int r1) {
            this.maxPcmBufferDurationUs = r1;
            return this;
        }

        public Builder setPcmBufferMultiplicationFactor(int r1) {
            this.pcmBufferMultiplicationFactor = r1;
            return this;
        }

        public Builder setPassthroughBufferDurationUs(int r1) {
            this.passthroughBufferDurationUs = r1;
            return this;
        }

        public Builder setOffloadBufferDurationUs(int r1) {
            this.offloadBufferDurationUs = r1;
            return this;
        }

        public Builder setAc3BufferMultiplicationFactor(int r1) {
            this.ac3BufferMultiplicationFactor = r1;
            return this;
        }

        public DefaultAudioTrackBufferSizeProvider build() {
            return new DefaultAudioTrackBufferSizeProvider(this);
        }
    }

    protected DefaultAudioTrackBufferSizeProvider(Builder builder) {
        this.minPcmBufferDurationUs = builder.minPcmBufferDurationUs;
        this.maxPcmBufferDurationUs = builder.maxPcmBufferDurationUs;
        this.pcmBufferMultiplicationFactor = builder.pcmBufferMultiplicationFactor;
        this.passthroughBufferDurationUs = builder.passthroughBufferDurationUs;
        this.offloadBufferDurationUs = builder.offloadBufferDurationUs;
        this.ac3BufferMultiplicationFactor = builder.ac3BufferMultiplicationFactor;
    }

    @Override // com.google.android.exoplayer2.audio.DefaultAudioSink.AudioTrackBufferSizeProvider
    public int getBufferSizeInBytes(int r1, int r2, int r3, int r4, int r5, double d) {
        return (((Math.max(r1, (int) (get1xBufferSizeInBytes(r1, r2, r3, r4, r5) * d)) + r4) - 1) / r4) * r4;
    }

    protected int get1xBufferSizeInBytes(int r1, int r2, int r3, int r4, int r5) {
        if (r3 != 0) {
            if (r3 != 1) {
                if (r3 == 2) {
                    return getPassthroughBufferSizeInBytes(r2);
                }
                throw new IllegalArgumentException();
            }
            return getOffloadBufferSizeInBytes(r2);
        }
        return getPcmBufferSizeInBytes(r1, r5, r4);
    }

    protected int getPcmBufferSizeInBytes(int r3, int r4, int r5) {
        return Util.constrainValue(r3 * this.pcmBufferMultiplicationFactor, durationUsToBytes(this.minPcmBufferDurationUs, r4, r5), durationUsToBytes(this.maxPcmBufferDurationUs, r4, r5));
    }

    protected int getPassthroughBufferSizeInBytes(int r5) {
        int r0 = this.passthroughBufferDurationUs;
        if (r5 == 5) {
            r0 *= this.ac3BufferMultiplicationFactor;
        }
        return Ints.checkedCast((r0 * getMaximumEncodedRateBytesPerSecond(r5)) / 1000000);
    }

    protected int getOffloadBufferSizeInBytes(int r5) {
        return Ints.checkedCast((this.offloadBufferDurationUs * getMaximumEncodedRateBytesPerSecond(r5)) / 1000000);
    }

    protected static int durationUsToBytes(int r2, int r3, int r4) {
        return Ints.checkedCast(((r2 * r3) * r4) / 1000000);
    }

    protected static int getMaximumEncodedRateBytesPerSecond(int r0) {
        switch (r0) {
            case 5:
                return Ac3Util.AC3_MAX_RATE_BYTES_PER_SECOND;
            case 6:
            case 18:
                return Ac3Util.E_AC3_MAX_RATE_BYTES_PER_SECOND;
            case 7:
                return DtsUtil.DTS_MAX_RATE_BYTES_PER_SECOND;
            case 8:
                return DtsUtil.DTS_HD_MAX_RATE_BYTES_PER_SECOND;
            case 9:
                return MpegAudioUtil.MAX_RATE_BYTES_PER_SECOND;
            case 10:
                return AacUtil.AAC_LC_MAX_RATE_BYTES_PER_SECOND;
            case 11:
                return AacUtil.AAC_HE_V1_MAX_RATE_BYTES_PER_SECOND;
            case 12:
                return AacUtil.AAC_HE_V2_MAX_RATE_BYTES_PER_SECOND;
            case 13:
            default:
                throw new IllegalArgumentException();
            case 14:
                return Ac3Util.TRUEHD_MAX_RATE_BYTES_PER_SECOND;
            case 15:
                return 8000;
            case 16:
                return AacUtil.AAC_XHE_MAX_RATE_BYTES_PER_SECOND;
            case 17:
                return Ac4Util.MAX_RATE_BYTES_PER_SECOND;
        }
    }
}
