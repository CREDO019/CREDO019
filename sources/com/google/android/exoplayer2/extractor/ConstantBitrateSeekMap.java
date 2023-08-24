package com.google.android.exoplayer2.extractor;

import com.google.android.exoplayer2.C1856C;
import com.google.android.exoplayer2.extractor.SeekMap;

/* loaded from: classes2.dex */
public class ConstantBitrateSeekMap implements SeekMap {
    private final boolean allowSeeksIfLengthUnknown;
    private final int bitrate;
    private final long dataSize;
    private final long durationUs;
    private final long firstFrameBytePosition;
    private final int frameSize;
    private final long inputLength;

    public ConstantBitrateSeekMap(long j, long j2, int r13, int r14) {
        this(j, j2, r13, r14, false);
    }

    public ConstantBitrateSeekMap(long j, long j2, int r6, int r7, boolean z) {
        this.inputLength = j;
        this.firstFrameBytePosition = j2;
        this.frameSize = r7 == -1 ? 1 : r7;
        this.bitrate = r6;
        this.allowSeeksIfLengthUnknown = z;
        if (j == -1) {
            this.dataSize = -1L;
            this.durationUs = C1856C.TIME_UNSET;
            return;
        }
        this.dataSize = j - j2;
        this.durationUs = getTimeUsAtPosition(j, j2, r6);
    }

    @Override // com.google.android.exoplayer2.extractor.SeekMap
    public boolean isSeekable() {
        return this.dataSize != -1 || this.allowSeeksIfLengthUnknown;
    }

    @Override // com.google.android.exoplayer2.extractor.SeekMap
    public SeekMap.SeekPoints getSeekPoints(long j) {
        if (this.dataSize == -1 && !this.allowSeeksIfLengthUnknown) {
            return new SeekMap.SeekPoints(new SeekPoint(0L, this.firstFrameBytePosition));
        }
        long framePositionForTimeUs = getFramePositionForTimeUs(j);
        long timeUsAtPosition = getTimeUsAtPosition(framePositionForTimeUs);
        SeekPoint seekPoint = new SeekPoint(timeUsAtPosition, framePositionForTimeUs);
        if (this.dataSize != -1 && timeUsAtPosition < j) {
            int r11 = this.frameSize;
            if (r11 + framePositionForTimeUs < this.inputLength) {
                long j2 = framePositionForTimeUs + r11;
                return new SeekMap.SeekPoints(seekPoint, new SeekPoint(getTimeUsAtPosition(j2), j2));
            }
        }
        return new SeekMap.SeekPoints(seekPoint);
    }

    @Override // com.google.android.exoplayer2.extractor.SeekMap
    public long getDurationUs() {
        return this.durationUs;
    }

    public long getTimeUsAtPosition(long j) {
        return getTimeUsAtPosition(j, this.firstFrameBytePosition, this.bitrate);
    }

    private static long getTimeUsAtPosition(long j, long j2, int r4) {
        return ((Math.max(0L, j - j2) * 8) * 1000000) / r4;
    }

    private long getFramePositionForTimeUs(long j) {
        int r0 = this.frameSize;
        long j2 = (((j * this.bitrate) / 8000000) / r0) * r0;
        long j3 = this.dataSize;
        if (j3 != -1) {
            j2 = Math.min(j2, j3 - r0);
        }
        return this.firstFrameBytePosition + Math.max(j2, 0L);
    }
}
