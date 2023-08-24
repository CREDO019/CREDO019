package com.google.android.exoplayer2.extractor;

import android.util.Base64;
import com.google.android.exoplayer2.ParserException;
import com.google.android.exoplayer2.extractor.p011ts.PsExtractor;
import com.google.android.exoplayer2.metadata.Metadata;
import com.google.android.exoplayer2.metadata.flac.PictureFrame;
import com.google.android.exoplayer2.metadata.vorbis.VorbisComment;
import com.google.android.exoplayer2.util.Log;
import com.google.android.exoplayer2.util.ParsableByteArray;
import com.google.android.exoplayer2.util.Util;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/* loaded from: classes2.dex */
public final class VorbisUtil {
    private static final String TAG = "VorbisUtil";

    public static int iLog(int r1) {
        int r0 = 0;
        while (r1 > 0) {
            r0++;
            r1 >>>= 1;
        }
        return r0;
    }

    /* loaded from: classes2.dex */
    public static final class CommentHeader {
        public final String[] comments;
        public final int length;
        public final String vendor;

        public CommentHeader(String str, String[] strArr, int r3) {
            this.vendor = str;
            this.comments = strArr;
            this.length = r3;
        }
    }

    /* loaded from: classes2.dex */
    public static final class VorbisIdHeader {
        public final int bitrateMaximum;
        public final int bitrateMinimum;
        public final int bitrateNominal;
        public final int blockSize0;
        public final int blockSize1;
        public final int channels;
        public final byte[] data;
        public final boolean framingFlag;
        public final int sampleRate;
        public final int version;

        public VorbisIdHeader(int r1, int r2, int r3, int r4, int r5, int r6, int r7, int r8, boolean z, byte[] bArr) {
            this.version = r1;
            this.channels = r2;
            this.sampleRate = r3;
            this.bitrateMaximum = r4;
            this.bitrateNominal = r5;
            this.bitrateMinimum = r6;
            this.blockSize0 = r7;
            this.blockSize1 = r8;
            this.framingFlag = z;
            this.data = bArr;
        }
    }

    /* loaded from: classes2.dex */
    public static final class Mode {
        public final boolean blockFlag;
        public final int mapping;
        public final int transformType;
        public final int windowType;

        public Mode(boolean z, int r2, int r3, int r4) {
            this.blockFlag = z;
            this.windowType = r2;
            this.transformType = r3;
            this.mapping = r4;
        }
    }

    public static VorbisIdHeader readVorbisIdentificationHeader(ParsableByteArray parsableByteArray) throws ParserException {
        verifyVorbisHeaderCapturePattern(1, parsableByteArray, false);
        int readLittleEndianUnsignedIntToInt = parsableByteArray.readLittleEndianUnsignedIntToInt();
        int readUnsignedByte = parsableByteArray.readUnsignedByte();
        int readLittleEndianUnsignedIntToInt2 = parsableByteArray.readLittleEndianUnsignedIntToInt();
        int readLittleEndianInt = parsableByteArray.readLittleEndianInt();
        if (readLittleEndianInt <= 0) {
            readLittleEndianInt = -1;
        }
        int readLittleEndianInt2 = parsableByteArray.readLittleEndianInt();
        if (readLittleEndianInt2 <= 0) {
            readLittleEndianInt2 = -1;
        }
        int readLittleEndianInt3 = parsableByteArray.readLittleEndianInt();
        if (readLittleEndianInt3 <= 0) {
            readLittleEndianInt3 = -1;
        }
        int readUnsignedByte2 = parsableByteArray.readUnsignedByte();
        return new VorbisIdHeader(readLittleEndianUnsignedIntToInt, readUnsignedByte, readLittleEndianUnsignedIntToInt2, readLittleEndianInt, readLittleEndianInt2, readLittleEndianInt3, (int) Math.pow(2.0d, readUnsignedByte2 & 15), (int) Math.pow(2.0d, (readUnsignedByte2 & PsExtractor.VIDEO_STREAM_MASK) >> 4), (parsableByteArray.readUnsignedByte() & 1) > 0, Arrays.copyOf(parsableByteArray.getData(), parsableByteArray.limit()));
    }

    public static CommentHeader readVorbisCommentHeader(ParsableByteArray parsableByteArray) throws ParserException {
        return readVorbisCommentHeader(parsableByteArray, true, true);
    }

    public static CommentHeader readVorbisCommentHeader(ParsableByteArray parsableByteArray, boolean z, boolean z2) throws ParserException {
        if (z) {
            verifyVorbisHeaderCapturePattern(3, parsableByteArray, false);
        }
        String readString = parsableByteArray.readString((int) parsableByteArray.readLittleEndianUnsignedInt());
        int length = 11 + readString.length();
        long readLittleEndianUnsignedInt = parsableByteArray.readLittleEndianUnsignedInt();
        String[] strArr = new String[(int) readLittleEndianUnsignedInt];
        int r1 = length + 4;
        for (int r0 = 0; r0 < readLittleEndianUnsignedInt; r0++) {
            strArr[r0] = parsableByteArray.readString((int) parsableByteArray.readLittleEndianUnsignedInt());
            r1 = r1 + 4 + strArr[r0].length();
        }
        if (z2 && (parsableByteArray.readUnsignedByte() & 1) == 0) {
            throw ParserException.createForMalformedContainer("framing bit expected to be set", null);
        }
        return new CommentHeader(readString, strArr, r1 + 1);
    }

    public static Metadata parseVorbisComments(List<String> list) {
        ArrayList arrayList = new ArrayList();
        for (int r2 = 0; r2 < list.size(); r2++) {
            String str = list.get(r2);
            String[] splitAtFirst = Util.splitAtFirst(str, "=");
            if (splitAtFirst.length != 2) {
                Log.m1132w(TAG, "Failed to parse Vorbis comment: " + str);
            } else if (splitAtFirst[0].equals("METADATA_BLOCK_PICTURE")) {
                try {
                    arrayList.add(PictureFrame.fromPictureBlock(new ParsableByteArray(Base64.decode(splitAtFirst[1], 0))));
                } catch (RuntimeException e) {
                    Log.m1131w(TAG, "Failed to parse vorbis picture", e);
                }
            } else {
                arrayList.add(new VorbisComment(splitAtFirst[0], splitAtFirst[1]));
            }
        }
        if (arrayList.isEmpty()) {
            return null;
        }
        return new Metadata(arrayList);
    }

    public static boolean verifyVorbisHeaderCapturePattern(int r4, ParsableByteArray parsableByteArray, boolean z) throws ParserException {
        if (parsableByteArray.bytesLeft() < 7) {
            if (z) {
                return false;
            }
            throw ParserException.createForMalformedContainer("too short header: " + parsableByteArray.bytesLeft(), null);
        } else if (parsableByteArray.readUnsignedByte() != r4) {
            if (z) {
                return false;
            }
            throw ParserException.createForMalformedContainer("expected header type " + Integer.toHexString(r4), null);
        } else if (parsableByteArray.readUnsignedByte() == 118 && parsableByteArray.readUnsignedByte() == 111 && parsableByteArray.readUnsignedByte() == 114 && parsableByteArray.readUnsignedByte() == 98 && parsableByteArray.readUnsignedByte() == 105 && parsableByteArray.readUnsignedByte() == 115) {
            return true;
        } else {
            if (z) {
                return false;
            }
            throw ParserException.createForMalformedContainer("expected characters 'vorbis'", null);
        }
    }

    public static Mode[] readVorbisModes(ParsableByteArray parsableByteArray, int r5) throws ParserException {
        verifyVorbisHeaderCapturePattern(5, parsableByteArray, false);
        int readUnsignedByte = parsableByteArray.readUnsignedByte() + 1;
        VorbisBitArray vorbisBitArray = new VorbisBitArray(parsableByteArray.getData());
        vorbisBitArray.skipBits(parsableByteArray.getPosition() * 8);
        for (int r4 = 0; r4 < readUnsignedByte; r4++) {
            readBook(vorbisBitArray);
        }
        int readBits = vorbisBitArray.readBits(6) + 1;
        for (int r1 = 0; r1 < readBits; r1++) {
            if (vorbisBitArray.readBits(16) != 0) {
                throw ParserException.createForMalformedContainer("placeholder of time domain transforms not zeroed out", null);
            }
        }
        readFloors(vorbisBitArray);
        readResidues(vorbisBitArray);
        readMappings(r5, vorbisBitArray);
        Mode[] readModes = readModes(vorbisBitArray);
        if (vorbisBitArray.readBit()) {
            return readModes;
        }
        throw ParserException.createForMalformedContainer("framing bit after modes not set as expected", null);
    }

    private static Mode[] readModes(VorbisBitArray vorbisBitArray) {
        int readBits = vorbisBitArray.readBits(6) + 1;
        Mode[] modeArr = new Mode[readBits];
        for (int r2 = 0; r2 < readBits; r2++) {
            modeArr[r2] = new Mode(vorbisBitArray.readBit(), vorbisBitArray.readBits(16), vorbisBitArray.readBits(16), vorbisBitArray.readBits(8));
        }
        return modeArr;
    }

    private static void readMappings(int r11, VorbisBitArray vorbisBitArray) throws ParserException {
        int readBits = vorbisBitArray.readBits(6) + 1;
        for (int r3 = 0; r3 < readBits; r3++) {
            int readBits2 = vorbisBitArray.readBits(16);
            if (readBits2 != 0) {
                Log.m1136e(TAG, "mapping type other than 0 not supported: " + readBits2);
            } else {
                int readBits3 = vorbisBitArray.readBit() ? vorbisBitArray.readBits(4) + 1 : 1;
                if (vorbisBitArray.readBit()) {
                    int readBits4 = vorbisBitArray.readBits(8) + 1;
                    for (int r8 = 0; r8 < readBits4; r8++) {
                        int r9 = r11 - 1;
                        vorbisBitArray.skipBits(iLog(r9));
                        vorbisBitArray.skipBits(iLog(r9));
                    }
                }
                if (vorbisBitArray.readBits(2) != 0) {
                    throw ParserException.createForMalformedContainer("to reserved bits must be zero after mapping coupling steps", null);
                }
                if (readBits3 > 1) {
                    for (int r6 = 0; r6 < r11; r6++) {
                        vorbisBitArray.skipBits(4);
                    }
                }
                for (int r5 = 0; r5 < readBits3; r5++) {
                    vorbisBitArray.skipBits(8);
                    vorbisBitArray.skipBits(8);
                    vorbisBitArray.skipBits(8);
                }
            }
        }
    }

    private static void readResidues(VorbisBitArray vorbisBitArray) throws ParserException {
        int readBits = vorbisBitArray.readBits(6) + 1;
        for (int r4 = 0; r4 < readBits; r4++) {
            if (vorbisBitArray.readBits(16) > 2) {
                throw ParserException.createForMalformedContainer("residueType greater than 2 is not decodable", null);
            }
            vorbisBitArray.skipBits(24);
            vorbisBitArray.skipBits(24);
            vorbisBitArray.skipBits(24);
            int readBits2 = vorbisBitArray.readBits(6) + 1;
            vorbisBitArray.skipBits(8);
            int[] r7 = new int[readBits2];
            for (int r8 = 0; r8 < readBits2; r8++) {
                r7[r8] = ((vorbisBitArray.readBit() ? vorbisBitArray.readBits(5) : 0) * 8) + vorbisBitArray.readBits(3);
            }
            for (int r82 = 0; r82 < readBits2; r82++) {
                for (int r9 = 0; r9 < 8; r9++) {
                    if ((r7[r82] & (1 << r9)) != 0) {
                        vorbisBitArray.skipBits(8);
                    }
                }
            }
        }
    }

    private static void readFloors(VorbisBitArray vorbisBitArray) throws ParserException {
        int readBits = vorbisBitArray.readBits(6) + 1;
        for (int r4 = 0; r4 < readBits; r4++) {
            int readBits2 = vorbisBitArray.readBits(16);
            if (readBits2 == 0) {
                vorbisBitArray.skipBits(8);
                vorbisBitArray.skipBits(16);
                vorbisBitArray.skipBits(16);
                vorbisBitArray.skipBits(6);
                vorbisBitArray.skipBits(8);
                int readBits3 = vorbisBitArray.readBits(4) + 1;
                for (int r6 = 0; r6 < readBits3; r6++) {
                    vorbisBitArray.skipBits(8);
                }
            } else if (readBits2 != 1) {
                throw ParserException.createForMalformedContainer("floor type greater than 1 not decodable: " + readBits2, null);
            } else {
                int readBits4 = vorbisBitArray.readBits(5);
                int r62 = -1;
                int[] r9 = new int[readBits4];
                for (int r10 = 0; r10 < readBits4; r10++) {
                    r9[r10] = vorbisBitArray.readBits(4);
                    if (r9[r10] > r62) {
                        r62 = r9[r10];
                    }
                }
                int r63 = r62 + 1;
                int[] r102 = new int[r63];
                for (int r11 = 0; r11 < r63; r11++) {
                    r102[r11] = vorbisBitArray.readBits(3) + 1;
                    int readBits5 = vorbisBitArray.readBits(2);
                    if (readBits5 > 0) {
                        vorbisBitArray.skipBits(8);
                    }
                    for (int r13 = 0; r13 < (1 << readBits5); r13++) {
                        vorbisBitArray.skipBits(8);
                    }
                }
                vorbisBitArray.skipBits(2);
                int readBits6 = vorbisBitArray.readBits(4);
                int r8 = 0;
                int r112 = 0;
                for (int r7 = 0; r7 < readBits4; r7++) {
                    r8 += r102[r9[r7]];
                    while (r112 < r8) {
                        vorbisBitArray.skipBits(readBits6);
                        r112++;
                    }
                }
            }
        }
    }

    private static CodeBook readBook(VorbisBitArray vorbisBitArray) throws ParserException {
        if (vorbisBitArray.readBits(24) != 5653314) {
            throw ParserException.createForMalformedContainer("expected code book to start with [0x56, 0x43, 0x42] at " + vorbisBitArray.getPosition(), null);
        }
        int readBits = vorbisBitArray.readBits(16);
        int readBits2 = vorbisBitArray.readBits(24);
        long[] jArr = new long[readBits2];
        boolean readBit = vorbisBitArray.readBit();
        long j = 0;
        if (!readBit) {
            boolean readBit2 = vorbisBitArray.readBit();
            for (int r3 = 0; r3 < readBits2; r3++) {
                if (readBit2) {
                    if (vorbisBitArray.readBit()) {
                        jArr[r3] = vorbisBitArray.readBits(5) + 1;
                    } else {
                        jArr[r3] = 0;
                    }
                } else {
                    jArr[r3] = vorbisBitArray.readBits(5) + 1;
                }
            }
        } else {
            int readBits3 = vorbisBitArray.readBits(5) + 1;
            int r10 = 0;
            while (r10 < readBits2) {
                int readBits4 = vorbisBitArray.readBits(iLog(readBits2 - r10));
                for (int r12 = 0; r12 < readBits4 && r10 < readBits2; r12++) {
                    jArr[r10] = readBits3;
                    r10++;
                }
                readBits3++;
            }
        }
        int readBits5 = vorbisBitArray.readBits(4);
        if (readBits5 > 2) {
            throw ParserException.createForMalformedContainer("lookup type greater than 2 not decodable: " + readBits5, null);
        }
        if (readBits5 == 1 || readBits5 == 2) {
            vorbisBitArray.skipBits(32);
            vorbisBitArray.skipBits(32);
            int readBits6 = vorbisBitArray.readBits(4) + 1;
            vorbisBitArray.skipBits(1);
            if (readBits5 != 1) {
                j = readBits2 * readBits;
            } else if (readBits != 0) {
                j = mapType1QuantValues(readBits2, readBits);
            }
            vorbisBitArray.skipBits((int) (j * readBits6));
        }
        return new CodeBook(readBits, readBits2, jArr, readBits5, readBit);
    }

    private static long mapType1QuantValues(long j, long j2) {
        return (long) Math.floor(Math.pow(j, 1.0d / j2));
    }

    private VorbisUtil() {
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes2.dex */
    public static final class CodeBook {
        public final int dimensions;
        public final int entries;
        public final boolean isOrdered;
        public final long[] lengthMap;
        public final int lookupType;

        public CodeBook(int r1, int r2, long[] jArr, int r4, boolean z) {
            this.dimensions = r1;
            this.entries = r2;
            this.lengthMap = jArr;
            this.lookupType = r4;
            this.isOrdered = z;
        }
    }
}
