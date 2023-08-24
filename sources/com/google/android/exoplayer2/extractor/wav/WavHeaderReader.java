package com.google.android.exoplayer2.extractor.wav;

import android.util.Pair;
import com.google.android.exoplayer2.ParserException;
import com.google.android.exoplayer2.audio.WavUtil;
import com.google.android.exoplayer2.extractor.ExtractorInput;
import com.google.android.exoplayer2.util.Assertions;
import com.google.android.exoplayer2.util.Log;
import com.google.android.exoplayer2.util.ParsableByteArray;
import com.google.android.exoplayer2.util.Util;
import java.io.IOException;

/* loaded from: classes2.dex */
final class WavHeaderReader {
    private static final String TAG = "WavHeaderReader";

    public static boolean checkFileType(ExtractorInput extractorInput) throws IOException {
        ParsableByteArray parsableByteArray = new ParsableByteArray(8);
        ChunkHeader peek = ChunkHeader.peek(extractorInput, parsableByteArray);
        if (peek.f222id == 1380533830 || peek.f222id == 1380333108) {
            extractorInput.peekFully(parsableByteArray.getData(), 0, 4);
            parsableByteArray.setPosition(0);
            int readInt = parsableByteArray.readInt();
            if (readInt != 1463899717) {
                Log.m1136e(TAG, "Unsupported form type: " + readInt);
                return false;
            }
            return true;
        }
        return false;
    }

    public static long readRf64SampleDataSize(ExtractorInput extractorInput) throws IOException {
        ParsableByteArray parsableByteArray = new ParsableByteArray(8);
        ChunkHeader peek = ChunkHeader.peek(extractorInput, parsableByteArray);
        if (peek.f222id != 1685272116) {
            extractorInput.resetPeekPosition();
            return -1L;
        }
        extractorInput.advancePeekPosition(8);
        parsableByteArray.setPosition(0);
        extractorInput.peekFully(parsableByteArray.getData(), 0, 8);
        long readLittleEndianLong = parsableByteArray.readLittleEndianLong();
        extractorInput.skipFully(((int) peek.size) + 8);
        return readLittleEndianLong;
    }

    public static WavFormat readFormat(ExtractorInput extractorInput) throws IOException {
        byte[] bArr;
        ParsableByteArray parsableByteArray = new ParsableByteArray(16);
        ChunkHeader skipToChunk = skipToChunk(WavUtil.FMT_FOURCC, extractorInput, parsableByteArray);
        Assertions.checkState(skipToChunk.size >= 16);
        extractorInput.peekFully(parsableByteArray.getData(), 0, 16);
        parsableByteArray.setPosition(0);
        int readLittleEndianUnsignedShort = parsableByteArray.readLittleEndianUnsignedShort();
        int readLittleEndianUnsignedShort2 = parsableByteArray.readLittleEndianUnsignedShort();
        int readLittleEndianUnsignedIntToInt = parsableByteArray.readLittleEndianUnsignedIntToInt();
        int readLittleEndianUnsignedIntToInt2 = parsableByteArray.readLittleEndianUnsignedIntToInt();
        int readLittleEndianUnsignedShort3 = parsableByteArray.readLittleEndianUnsignedShort();
        int readLittleEndianUnsignedShort4 = parsableByteArray.readLittleEndianUnsignedShort();
        int r0 = ((int) skipToChunk.size) - 16;
        if (r0 > 0) {
            byte[] bArr2 = new byte[r0];
            extractorInput.peekFully(bArr2, 0, r0);
            bArr = bArr2;
        } else {
            bArr = Util.EMPTY_BYTE_ARRAY;
        }
        extractorInput.skipFully((int) (extractorInput.getPeekPosition() - extractorInput.getPosition()));
        return new WavFormat(readLittleEndianUnsignedShort, readLittleEndianUnsignedShort2, readLittleEndianUnsignedIntToInt, readLittleEndianUnsignedIntToInt2, readLittleEndianUnsignedShort3, readLittleEndianUnsignedShort4, bArr);
    }

    public static Pair<Long, Long> skipToSampleData(ExtractorInput extractorInput) throws IOException {
        extractorInput.resetPeekPosition();
        ChunkHeader skipToChunk = skipToChunk(1684108385, extractorInput, new ParsableByteArray(8));
        extractorInput.skipFully(8);
        return Pair.create(Long.valueOf(extractorInput.getPosition()), Long.valueOf(skipToChunk.size));
    }

    private static ChunkHeader skipToChunk(int r6, ExtractorInput extractorInput, ParsableByteArray parsableByteArray) throws IOException {
        ChunkHeader peek = ChunkHeader.peek(extractorInput, parsableByteArray);
        while (peek.f222id != r6) {
            Log.m1132w(TAG, "Ignoring unknown WAV chunk: " + peek.f222id);
            long j = peek.size + 8;
            if (j > 2147483647L) {
                throw ParserException.createForUnsupportedContainerFeature("Chunk is too large (~2GB+) to skip; id: " + peek.f222id);
            }
            extractorInput.skipFully((int) j);
            peek = ChunkHeader.peek(extractorInput, parsableByteArray);
        }
        return peek;
    }

    private WavHeaderReader() {
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes2.dex */
    public static final class ChunkHeader {
        public static final int SIZE_IN_BYTES = 8;

        /* renamed from: id */
        public final int f222id;
        public final long size;

        private ChunkHeader(int r1, long j) {
            this.f222id = r1;
            this.size = j;
        }

        public static ChunkHeader peek(ExtractorInput extractorInput, ParsableByteArray parsableByteArray) throws IOException {
            extractorInput.peekFully(parsableByteArray.getData(), 0, 8);
            parsableByteArray.setPosition(0);
            return new ChunkHeader(parsableByteArray.readInt(), parsableByteArray.readLittleEndianUnsignedInt());
        }
    }
}
