package com.google.android.exoplayer2.extractor.ogg;

import com.google.android.exoplayer2.extractor.ExtractorInput;
import com.google.android.exoplayer2.extractor.ExtractorUtil;
import com.google.android.exoplayer2.util.Assertions;
import com.google.android.exoplayer2.util.ParsableByteArray;
import java.io.IOException;
import java.util.Arrays;

/* loaded from: classes2.dex */
final class OggPacket {
    private boolean populated;
    private int segmentCount;
    private final OggPageHeader pageHeader = new OggPageHeader();
    private final ParsableByteArray packetArray = new ParsableByteArray(new byte[OggPageHeader.MAX_PAGE_PAYLOAD], 0);
    private int currentSegmentIndex = -1;

    public void reset() {
        this.pageHeader.reset();
        this.packetArray.reset(0);
        this.currentSegmentIndex = -1;
        this.populated = false;
    }

    public boolean populate(ExtractorInput extractorInput) throws IOException {
        int r3;
        Assertions.checkState(extractorInput != null);
        if (this.populated) {
            this.populated = false;
            this.packetArray.reset(0);
        }
        while (!this.populated) {
            if (this.currentSegmentIndex < 0) {
                if (!this.pageHeader.skipToNextPage(extractorInput) || !this.pageHeader.populate(extractorInput, true)) {
                    return false;
                }
                int r2 = this.pageHeader.headerSize;
                if ((this.pageHeader.type & 1) == 1 && this.packetArray.limit() == 0) {
                    r2 += calculatePacketSize(0);
                    r3 = this.segmentCount + 0;
                } else {
                    r3 = 0;
                }
                if (!ExtractorUtil.skipFullyQuietly(extractorInput, r2)) {
                    return false;
                }
                this.currentSegmentIndex = r3;
            }
            int calculatePacketSize = calculatePacketSize(this.currentSegmentIndex);
            int r32 = this.currentSegmentIndex + this.segmentCount;
            if (calculatePacketSize > 0) {
                ParsableByteArray parsableByteArray = this.packetArray;
                parsableByteArray.ensureCapacity(parsableByteArray.limit() + calculatePacketSize);
                if (!ExtractorUtil.readFullyQuietly(extractorInput, this.packetArray.getData(), this.packetArray.limit(), calculatePacketSize)) {
                    return false;
                }
                ParsableByteArray parsableByteArray2 = this.packetArray;
                parsableByteArray2.setLimit(parsableByteArray2.limit() + calculatePacketSize);
                this.populated = this.pageHeader.laces[r32 + (-1)] != 255;
            }
            if (r32 == this.pageHeader.pageSegmentCount) {
                r32 = -1;
            }
            this.currentSegmentIndex = r32;
        }
        return true;
    }

    public OggPageHeader getPageHeader() {
        return this.pageHeader;
    }

    public ParsableByteArray getPayload() {
        return this.packetArray;
    }

    public void trimPayload() {
        if (this.packetArray.getData().length == 65025) {
            return;
        }
        ParsableByteArray parsableByteArray = this.packetArray;
        parsableByteArray.reset(Arrays.copyOf(parsableByteArray.getData(), Math.max((int) OggPageHeader.MAX_PAGE_PAYLOAD, this.packetArray.limit())), this.packetArray.limit());
    }

    private int calculatePacketSize(int r5) {
        int r0 = 0;
        this.segmentCount = 0;
        while (this.segmentCount + r5 < this.pageHeader.pageSegmentCount) {
            int[] r1 = this.pageHeader.laces;
            int r2 = this.segmentCount;
            this.segmentCount = r2 + 1;
            int r12 = r1[r2 + r5];
            r0 += r12;
            if (r12 != 255) {
                break;
            }
        }
        return r0;
    }
}
