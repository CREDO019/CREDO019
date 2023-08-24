package com.google.android.exoplayer2.extractor.mkv;

import com.google.android.exoplayer2.extractor.ExtractorInput;
import com.google.android.exoplayer2.util.ParsableByteArray;
import java.io.IOException;

/* loaded from: classes2.dex */
final class Sniffer {
    private static final int ID_EBML = 440786851;
    private static final int SEARCH_LENGTH = 1024;
    private int peekLength;
    private final ParsableByteArray scratch = new ParsableByteArray(8);

    public boolean sniff(ExtractorInput extractorInput) throws IOException {
        long readUint;
        int r5;
        long length = extractorInput.getLength();
        long j = 1024;
        int r8 = (length > (-1L) ? 1 : (length == (-1L) ? 0 : -1));
        if (r8 != 0 && length <= 1024) {
            j = length;
        }
        int r52 = (int) j;
        extractorInput.peekFully(this.scratch.getData(), 0, 4);
        long readUnsignedInt = this.scratch.readUnsignedInt();
        this.peekLength = 4;
        while (readUnsignedInt != 440786851) {
            int r7 = this.peekLength + 1;
            this.peekLength = r7;
            if (r7 == r52) {
                return false;
            }
            extractorInput.peekFully(this.scratch.getData(), 0, 1);
            readUnsignedInt = ((readUnsignedInt << 8) & (-256)) | (this.scratch.getData()[0] & 255);
        }
        long readUint2 = readUint(extractorInput);
        long j2 = this.peekLength;
        if (readUint2 == Long.MIN_VALUE) {
            return false;
        }
        if (r8 != 0 && j2 + readUint2 >= length) {
            return false;
        }
        while (true) {
            int r2 = this.peekLength;
            long j3 = j2 + readUint2;
            if (r2 >= j3) {
                return ((long) r2) == j3;
            } else if (readUint(extractorInput) != Long.MIN_VALUE && (readUint(extractorInput)) >= 0 && readUint <= 2147483647L) {
                if (r5 != 0) {
                    int r3 = (int) readUint;
                    extractorInput.advancePeekPosition(r3);
                    this.peekLength += r3;
                }
            }
        }
    }

    private long readUint(ExtractorInput extractorInput) throws IOException {
        int r1 = 0;
        extractorInput.peekFully(this.scratch.getData(), 0, 1);
        int r0 = this.scratch.getData()[0] & 255;
        if (r0 == 0) {
            return Long.MIN_VALUE;
        }
        int r3 = 128;
        int r4 = 0;
        while ((r0 & r3) == 0) {
            r3 >>= 1;
            r4++;
        }
        int r02 = r0 & (~r3);
        extractorInput.peekFully(this.scratch.getData(), 1, r4);
        while (r1 < r4) {
            r1++;
            r02 = (this.scratch.getData()[r1] & 255) + (r02 << 8);
        }
        this.peekLength += r4 + 1;
        return r02;
    }
}
