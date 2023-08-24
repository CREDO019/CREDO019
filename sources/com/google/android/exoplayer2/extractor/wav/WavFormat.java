package com.google.android.exoplayer2.extractor.wav;

/* loaded from: classes2.dex */
final class WavFormat {
    public final int averageBytesPerSecond;
    public final int bitsPerSample;
    public final int blockSize;
    public final byte[] extraData;
    public final int formatType;
    public final int frameRateHz;
    public final int numChannels;

    public WavFormat(int r1, int r2, int r3, int r4, int r5, int r6, byte[] bArr) {
        this.formatType = r1;
        this.numChannels = r2;
        this.frameRateHz = r3;
        this.averageBytesPerSecond = r4;
        this.blockSize = r5;
        this.bitsPerSample = r6;
        this.extraData = bArr;
    }
}
