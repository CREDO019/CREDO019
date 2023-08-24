package com.google.android.exoplayer2.extractor.p011ts;

import com.google.android.exoplayer2.C1856C;
import com.google.android.exoplayer2.extractor.ExtractorInput;
import com.google.android.exoplayer2.extractor.PositionHolder;
import com.google.android.exoplayer2.util.Log;
import com.google.android.exoplayer2.util.ParsableByteArray;
import com.google.android.exoplayer2.util.TimestampAdjuster;
import com.google.android.exoplayer2.util.Util;
import java.io.IOException;

/* renamed from: com.google.android.exoplayer2.extractor.ts.TsDurationReader */
/* loaded from: classes2.dex */
final class TsDurationReader {
    private static final String TAG = "TsDurationReader";
    private boolean isDurationRead;
    private boolean isFirstPcrValueRead;
    private boolean isLastPcrValueRead;
    private final int timestampSearchBytes;
    private final TimestampAdjuster pcrTimestampAdjuster = new TimestampAdjuster(0);
    private long firstPcrValue = C1856C.TIME_UNSET;
    private long lastPcrValue = C1856C.TIME_UNSET;
    private long durationUs = C1856C.TIME_UNSET;
    private final ParsableByteArray packetBuffer = new ParsableByteArray();

    /* JADX INFO: Access modifiers changed from: package-private */
    public TsDurationReader(int r3) {
        this.timestampSearchBytes = r3;
    }

    public boolean isDurationReadFinished() {
        return this.isDurationRead;
    }

    public int readDuration(ExtractorInput extractorInput, PositionHolder positionHolder, int r9) throws IOException {
        if (r9 <= 0) {
            return finishReadDuration(extractorInput);
        }
        if (!this.isLastPcrValueRead) {
            return readLastPcrValue(extractorInput, positionHolder, r9);
        }
        if (this.lastPcrValue == C1856C.TIME_UNSET) {
            return finishReadDuration(extractorInput);
        }
        if (!this.isFirstPcrValueRead) {
            return readFirstPcrValue(extractorInput, positionHolder, r9);
        }
        long j = this.firstPcrValue;
        if (j == C1856C.TIME_UNSET) {
            return finishReadDuration(extractorInput);
        }
        long adjustTsTimestamp = this.pcrTimestampAdjuster.adjustTsTimestamp(this.lastPcrValue) - this.pcrTimestampAdjuster.adjustTsTimestamp(j);
        this.durationUs = adjustTsTimestamp;
        if (adjustTsTimestamp < 0) {
            Log.m1132w(TAG, "Invalid duration: " + this.durationUs + ". Using TIME_UNSET instead.");
            this.durationUs = C1856C.TIME_UNSET;
        }
        return finishReadDuration(extractorInput);
    }

    public long getDurationUs() {
        return this.durationUs;
    }

    public TimestampAdjuster getPcrTimestampAdjuster() {
        return this.pcrTimestampAdjuster;
    }

    private int finishReadDuration(ExtractorInput extractorInput) {
        this.packetBuffer.reset(Util.EMPTY_BYTE_ARRAY);
        this.isDurationRead = true;
        extractorInput.resetPeekPosition();
        return 0;
    }

    private int readFirstPcrValue(ExtractorInput extractorInput, PositionHolder positionHolder, int r11) throws IOException {
        int min = (int) Math.min(this.timestampSearchBytes, extractorInput.getLength());
        long j = 0;
        if (extractorInput.getPosition() != j) {
            positionHolder.position = j;
            return 1;
        }
        this.packetBuffer.reset(min);
        extractorInput.resetPeekPosition();
        extractorInput.peekFully(this.packetBuffer.getData(), 0, min);
        this.firstPcrValue = readFirstPcrValueFromBuffer(this.packetBuffer, r11);
        this.isFirstPcrValueRead = true;
        return 0;
    }

    private long readFirstPcrValueFromBuffer(ParsableByteArray parsableByteArray, int r9) {
        int limit = parsableByteArray.limit();
        for (int position = parsableByteArray.getPosition(); position < limit; position++) {
            if (parsableByteArray.getData()[position] == 71) {
                long readPcrFromPacket = TsUtil.readPcrFromPacket(parsableByteArray, position, r9);
                if (readPcrFromPacket != C1856C.TIME_UNSET) {
                    return readPcrFromPacket;
                }
            }
        }
        return C1856C.TIME_UNSET;
    }

    private int readLastPcrValue(ExtractorInput extractorInput, PositionHolder positionHolder, int r10) throws IOException {
        long length = extractorInput.getLength();
        int min = (int) Math.min(this.timestampSearchBytes, length);
        long j = length - min;
        if (extractorInput.getPosition() != j) {
            positionHolder.position = j;
            return 1;
        }
        this.packetBuffer.reset(min);
        extractorInput.resetPeekPosition();
        extractorInput.peekFully(this.packetBuffer.getData(), 0, min);
        this.lastPcrValue = readLastPcrValueFromBuffer(this.packetBuffer, r10);
        this.isLastPcrValueRead = true;
        return 0;
    }

    private long readLastPcrValueFromBuffer(ParsableByteArray parsableByteArray, int r10) {
        int position = parsableByteArray.getPosition();
        int limit = parsableByteArray.limit();
        for (int r2 = limit - 188; r2 >= position; r2--) {
            if (TsUtil.isStartOfTsPacket(parsableByteArray.getData(), position, limit, r2)) {
                long readPcrFromPacket = TsUtil.readPcrFromPacket(parsableByteArray, r2, r10);
                if (readPcrFromPacket != C1856C.TIME_UNSET) {
                    return readPcrFromPacket;
                }
            }
        }
        return C1856C.TIME_UNSET;
    }
}
