package com.google.android.exoplayer2.extractor.p011ts;

import com.google.android.exoplayer2.extractor.ExtractorOutput;
import com.google.android.exoplayer2.extractor.p011ts.TsPayloadReader;
import com.google.android.exoplayer2.util.ParsableByteArray;
import com.google.android.exoplayer2.util.TimestampAdjuster;
import com.google.android.exoplayer2.util.Util;

/* renamed from: com.google.android.exoplayer2.extractor.ts.SectionReader */
/* loaded from: classes2.dex */
public final class SectionReader implements TsPayloadReader {
    private static final int DEFAULT_SECTION_BUFFER_LENGTH = 32;
    private static final int MAX_SECTION_LENGTH = 4098;
    private static final int SECTION_HEADER_LENGTH = 3;
    private int bytesRead;
    private final SectionPayloadReader reader;
    private final ParsableByteArray sectionData = new ParsableByteArray(32);
    private boolean sectionSyntaxIndicator;
    private int totalSectionLength;
    private boolean waitingForPayloadStart;

    public SectionReader(SectionPayloadReader sectionPayloadReader) {
        this.reader = sectionPayloadReader;
    }

    @Override // com.google.android.exoplayer2.extractor.p011ts.TsPayloadReader
    public void init(TimestampAdjuster timestampAdjuster, ExtractorOutput extractorOutput, TsPayloadReader.TrackIdGenerator trackIdGenerator) {
        this.reader.init(timestampAdjuster, extractorOutput, trackIdGenerator);
        this.waitingForPayloadStart = true;
    }

    @Override // com.google.android.exoplayer2.extractor.p011ts.TsPayloadReader
    public void seek() {
        this.waitingForPayloadStart = true;
    }

    @Override // com.google.android.exoplayer2.extractor.p011ts.TsPayloadReader
    public void consume(ParsableByteArray parsableByteArray, int r8) {
        boolean z = (r8 & 1) != 0;
        int position = z ? parsableByteArray.getPosition() + parsableByteArray.readUnsignedByte() : -1;
        if (this.waitingForPayloadStart) {
            if (!z) {
                return;
            }
            this.waitingForPayloadStart = false;
            parsableByteArray.setPosition(position);
            this.bytesRead = 0;
        }
        while (parsableByteArray.bytesLeft() > 0) {
            int r82 = this.bytesRead;
            if (r82 < 3) {
                if (r82 == 0) {
                    int readUnsignedByte = parsableByteArray.readUnsignedByte();
                    parsableByteArray.setPosition(parsableByteArray.getPosition() - 1);
                    if (readUnsignedByte == 255) {
                        this.waitingForPayloadStart = true;
                        return;
                    }
                }
                int min = Math.min(parsableByteArray.bytesLeft(), 3 - this.bytesRead);
                parsableByteArray.readBytes(this.sectionData.getData(), this.bytesRead, min);
                int r4 = this.bytesRead + min;
                this.bytesRead = r4;
                if (r4 == 3) {
                    this.sectionData.setPosition(0);
                    this.sectionData.setLimit(3);
                    this.sectionData.skipBytes(1);
                    int readUnsignedByte2 = this.sectionData.readUnsignedByte();
                    int readUnsignedByte3 = this.sectionData.readUnsignedByte();
                    this.sectionSyntaxIndicator = (readUnsignedByte2 & 128) != 0;
                    this.totalSectionLength = (((readUnsignedByte2 & 15) << 8) | readUnsignedByte3) + 3;
                    int capacity = this.sectionData.capacity();
                    int r3 = this.totalSectionLength;
                    if (capacity < r3) {
                        this.sectionData.ensureCapacity(Math.min(4098, Math.max(r3, this.sectionData.capacity() * 2)));
                    }
                }
            } else {
                int min2 = Math.min(parsableByteArray.bytesLeft(), this.totalSectionLength - this.bytesRead);
                parsableByteArray.readBytes(this.sectionData.getData(), this.bytesRead, min2);
                int r32 = this.bytesRead + min2;
                this.bytesRead = r32;
                int r83 = this.totalSectionLength;
                if (r32 != r83) {
                    continue;
                } else {
                    if (this.sectionSyntaxIndicator) {
                        if (Util.crc32(this.sectionData.getData(), 0, this.totalSectionLength, -1) != 0) {
                            this.waitingForPayloadStart = true;
                            return;
                        }
                        this.sectionData.setLimit(this.totalSectionLength - 4);
                    } else {
                        this.sectionData.setLimit(r83);
                    }
                    this.sectionData.setPosition(0);
                    this.reader.consume(this.sectionData);
                    this.bytesRead = 0;
                }
            }
        }
    }
}
