package com.google.android.exoplayer2.extractor.mkv;

import com.google.android.exoplayer2.extractor.ExtractorInput;
import java.io.IOException;

/* loaded from: classes2.dex */
final class VarintReader {
    private static final int STATE_BEGIN_READING = 0;
    private static final int STATE_READ_CONTENTS = 1;
    private static final long[] VARINT_LENGTH_MASKS = {128, 64, 32, 16, 8, 4, 2, 1};
    private int length;
    private final byte[] scratch = new byte[8];
    private int state;

    public void reset() {
        this.state = 0;
        this.length = 0;
    }

    public long readUnsignedVarint(ExtractorInput extractorInput, boolean z, boolean z2, int r7) throws IOException {
        if (this.state == 0) {
            if (!extractorInput.readFully(this.scratch, 0, 1, z)) {
                return -1L;
            }
            int parseUnsignedVarintLength = parseUnsignedVarintLength(this.scratch[0] & 255);
            this.length = parseUnsignedVarintLength;
            if (parseUnsignedVarintLength == -1) {
                throw new IllegalStateException("No valid varint length mask found");
            }
            this.state = 1;
        }
        int r5 = this.length;
        if (r5 > r7) {
            this.state = 0;
            return -2L;
        }
        if (r5 != 1) {
            extractorInput.readFully(this.scratch, 1, r5 - 1);
        }
        this.state = 0;
        return assembleVarint(this.scratch, this.length, z2);
    }

    public int getLastLength() {
        return this.length;
    }

    public static int parseUnsignedVarintLength(int r6) {
        int r0 = 0;
        while (true) {
            long[] jArr = VARINT_LENGTH_MASKS;
            if (r0 >= jArr.length) {
                return -1;
            }
            if ((jArr[r0] & r6) != 0) {
                return r0 + 1;
            }
            r0++;
        }
    }

    public static long assembleVarint(byte[] bArr, int r7, boolean z) {
        long j = bArr[0] & 255;
        if (z) {
            j &= ~VARINT_LENGTH_MASKS[r7 - 1];
        }
        for (int r8 = 1; r8 < r7; r8++) {
            j = (j << 8) | (bArr[r8] & 255);
        }
        return j;
    }
}
