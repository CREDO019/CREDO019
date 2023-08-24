package com.google.android.exoplayer2.extractor.avi;

import com.google.android.exoplayer2.util.Log;
import com.google.android.exoplayer2.util.ParsableByteArray;
import com.google.android.exoplayer2.util.Util;

/* loaded from: classes2.dex */
final class AviStreamHeaderChunk implements AviChunk {
    private static final String TAG = "AviStreamHeaderChunk";
    public final int initialFrames;
    public final int length;
    public final int rate;
    public final int scale;
    public final int streamType;
    public final int suggestedBufferSize;

    @Override // com.google.android.exoplayer2.extractor.avi.AviChunk
    public int getType() {
        return AviExtractor.FOURCC_strh;
    }

    public static AviStreamHeaderChunk parseFrom(ParsableByteArray parsableByteArray) {
        int readLittleEndianInt = parsableByteArray.readLittleEndianInt();
        parsableByteArray.skipBytes(12);
        int readLittleEndianInt2 = parsableByteArray.readLittleEndianInt();
        int readLittleEndianInt3 = parsableByteArray.readLittleEndianInt();
        int readLittleEndianInt4 = parsableByteArray.readLittleEndianInt();
        parsableByteArray.skipBytes(4);
        int readLittleEndianInt5 = parsableByteArray.readLittleEndianInt();
        int readLittleEndianInt6 = parsableByteArray.readLittleEndianInt();
        parsableByteArray.skipBytes(8);
        return new AviStreamHeaderChunk(readLittleEndianInt, readLittleEndianInt2, readLittleEndianInt3, readLittleEndianInt4, readLittleEndianInt5, readLittleEndianInt6);
    }

    private AviStreamHeaderChunk(int r1, int r2, int r3, int r4, int r5, int r6) {
        this.streamType = r1;
        this.initialFrames = r2;
        this.scale = r3;
        this.rate = r4;
        this.length = r5;
        this.suggestedBufferSize = r6;
    }

    public int getTrackType() {
        int r0 = this.streamType;
        if (r0 != 1935960438) {
            if (r0 != 1935963489) {
                if (r0 != 1937012852) {
                    Log.m1132w(TAG, "Found unsupported streamType fourCC: " + Integer.toHexString(this.streamType));
                    return -1;
                }
                return 3;
            }
            return 1;
        }
        return 2;
    }

    public float getFrameRate() {
        return this.rate / this.scale;
    }

    public long getDurationUs() {
        return Util.scaleLargeTimestamp(this.length, this.scale * 1000000, this.rate);
    }
}
