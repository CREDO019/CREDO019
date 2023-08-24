package com.google.android.exoplayer2.util;

import com.google.common.base.Ascii;
import java.nio.ByteBuffer;
import java.util.Arrays;

/* loaded from: classes2.dex */
public final class NalUnitUtil {
    public static final int EXTENDED_SAR = 255;
    private static final int H264_NAL_UNIT_TYPE_SEI = 6;
    private static final int H264_NAL_UNIT_TYPE_SPS = 7;
    private static final int H265_NAL_UNIT_TYPE_PREFIX_SEI = 39;
    public static final int NAL_UNIT_TYPE_AUD = 9;
    public static final int NAL_UNIT_TYPE_IDR = 5;
    public static final int NAL_UNIT_TYPE_NON_IDR = 1;
    public static final int NAL_UNIT_TYPE_PARTITION_A = 2;
    public static final int NAL_UNIT_TYPE_PPS = 8;
    public static final int NAL_UNIT_TYPE_SEI = 6;
    public static final int NAL_UNIT_TYPE_SPS = 7;
    private static final String TAG = "NalUnitUtil";
    public static final byte[] NAL_START_CODE = {0, 0, 0, 1};
    public static final float[] ASPECT_RATIO_IDC_VALUES = {1.0f, 1.0f, 1.0909091f, 0.90909094f, 1.4545455f, 1.2121212f, 2.1818182f, 1.8181819f, 2.909091f, 2.4242425f, 1.6363636f, 1.3636364f, 1.939394f, 1.6161616f, 1.3333334f, 1.5f, 2.0f};
    private static final Object scratchEscapePositionsLock = new Object();
    private static int[] scratchEscapePositions = new int[10];

    /* loaded from: classes2.dex */
    public static final class SpsData {
        public final int constraintsFlagsAndReservedZero2Bits;
        public final boolean deltaPicOrderAlwaysZeroFlag;
        public final boolean frameMbsOnlyFlag;
        public final int frameNumLength;
        public final int height;
        public final int levelIdc;
        public final int maxNumRefFrames;
        public final int picOrderCntLsbLength;
        public final int picOrderCountType;
        public final float pixelWidthHeightRatio;
        public final int profileIdc;
        public final boolean separateColorPlaneFlag;
        public final int seqParameterSetId;
        public final int width;

        public SpsData(int r1, int r2, int r3, int r4, int r5, int r6, int r7, float f, boolean z, boolean z2, int r11, int r12, int r13, boolean z3) {
            this.profileIdc = r1;
            this.constraintsFlagsAndReservedZero2Bits = r2;
            this.levelIdc = r3;
            this.seqParameterSetId = r4;
            this.maxNumRefFrames = r5;
            this.width = r6;
            this.height = r7;
            this.pixelWidthHeightRatio = f;
            this.separateColorPlaneFlag = z;
            this.frameMbsOnlyFlag = z2;
            this.frameNumLength = r11;
            this.picOrderCountType = r12;
            this.picOrderCntLsbLength = r13;
            this.deltaPicOrderAlwaysZeroFlag = z3;
        }
    }

    /* loaded from: classes2.dex */
    public static final class H265SpsData {
        public final int[] constraintBytes;
        public final int generalLevelIdc;
        public final int generalProfileCompatibilityFlags;
        public final int generalProfileIdc;
        public final int generalProfileSpace;
        public final boolean generalTierFlag;
        public final int height;
        public final float pixelWidthHeightRatio;
        public final int seqParameterSetId;
        public final int width;

        public H265SpsData(int r1, boolean z, int r3, int r4, int[] r5, int r6, int r7, int r8, int r9, float f) {
            this.generalProfileSpace = r1;
            this.generalTierFlag = z;
            this.generalProfileIdc = r3;
            this.generalProfileCompatibilityFlags = r4;
            this.constraintBytes = r5;
            this.generalLevelIdc = r6;
            this.seqParameterSetId = r7;
            this.width = r8;
            this.height = r9;
            this.pixelWidthHeightRatio = f;
        }
    }

    /* loaded from: classes2.dex */
    public static final class PpsData {
        public final boolean bottomFieldPicOrderInFramePresentFlag;
        public final int picParameterSetId;
        public final int seqParameterSetId;

        public PpsData(int r1, int r2, boolean z) {
            this.picParameterSetId = r1;
            this.seqParameterSetId = r2;
            this.bottomFieldPicOrderInFramePresentFlag = z;
        }
    }

    public static int unescapeStream(byte[] bArr, int r9) {
        int r92;
        synchronized (scratchEscapePositionsLock) {
            int r2 = 0;
            int r3 = 0;
            while (r2 < r9) {
                try {
                    r2 = findNextUnescapeIndex(bArr, r2, r9);
                    if (r2 < r9) {
                        int[] r4 = scratchEscapePositions;
                        if (r4.length <= r3) {
                            scratchEscapePositions = Arrays.copyOf(r4, r4.length * 2);
                        }
                        scratchEscapePositions[r3] = r2;
                        r2 += 3;
                        r3++;
                    }
                } catch (Throwable th) {
                    throw th;
                }
            }
            r92 = r9 - r3;
            int r42 = 0;
            int r5 = 0;
            for (int r22 = 0; r22 < r3; r22++) {
                int r6 = scratchEscapePositions[r22] - r5;
                System.arraycopy(bArr, r5, bArr, r42, r6);
                int r43 = r42 + r6;
                int r7 = r43 + 1;
                bArr[r43] = 0;
                r42 = r7 + 1;
                bArr[r7] = 0;
                r5 += r6 + 3;
            }
            System.arraycopy(bArr, r5, bArr, r42, r92 - r42);
        }
        return r92;
    }

    public static void discardToSps(ByteBuffer byteBuffer) {
        int position = byteBuffer.position();
        int r2 = 0;
        int r3 = 0;
        while (true) {
            int r4 = r2 + 1;
            if (r4 < position) {
                int r5 = byteBuffer.get(r2) & 255;
                if (r3 == 3) {
                    if (r5 == 1 && (byteBuffer.get(r4) & Ascii.f1131US) == 7) {
                        ByteBuffer duplicate = byteBuffer.duplicate();
                        duplicate.position(r2 - 3);
                        duplicate.limit(position);
                        byteBuffer.position(0);
                        byteBuffer.put(duplicate);
                        return;
                    }
                } else if (r5 == 0) {
                    r3++;
                }
                if (r5 != 0) {
                    r3 = 0;
                }
                r2 = r4;
            } else {
                byteBuffer.clear();
                return;
            }
        }
    }

    public static boolean isNalUnitSei(String str, byte b) {
        if (MimeTypes.VIDEO_H264.equals(str) && (b & Ascii.f1131US) == 6) {
            return true;
        }
        return MimeTypes.VIDEO_H265.equals(str) && ((b & 126) >> 1) == 39;
    }

    public static int getNalUnitType(byte[] bArr, int r1) {
        return bArr[r1 + 3] & Ascii.f1131US;
    }

    public static int getH265NalUnitType(byte[] bArr, int r1) {
        return (bArr[r1 + 3] & 126) >> 1;
    }

    public static SpsData parseSpsNalUnit(byte[] bArr, int r1, int r2) {
        return parseSpsNalUnitPayload(bArr, r1 + 1, r2);
    }

    /* JADX WARN: Removed duplicated region for block: B:56:0x00d7  */
    /* JADX WARN: Removed duplicated region for block: B:59:0x00e9  */
    /* JADX WARN: Removed duplicated region for block: B:76:0x0138  */
    /* JADX WARN: Removed duplicated region for block: B:81:0x014b  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static com.google.android.exoplayer2.util.NalUnitUtil.SpsData parseSpsNalUnitPayload(byte[] r22, int r23, int r24) {
        /*
            Method dump skipped, instructions count: 371
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.exoplayer2.util.NalUnitUtil.parseSpsNalUnitPayload(byte[], int, int):com.google.android.exoplayer2.util.NalUnitUtil$SpsData");
    }

    public static H265SpsData parseH265SpsNalUnit(byte[] bArr, int r1, int r2) {
        return parseH265SpsNalUnitPayload(bArr, r1 + 2, r2);
    }

    public static H265SpsData parseH265SpsNalUnitPayload(byte[] bArr, int r23, int r24) {
        ParsableNalUnitBitArray parsableNalUnitBitArray = new ParsableNalUnitBitArray(bArr, r23, r24);
        parsableNalUnitBitArray.skipBits(4);
        int readBits = parsableNalUnitBitArray.readBits(3);
        parsableNalUnitBitArray.skipBit();
        int readBits2 = parsableNalUnitBitArray.readBits(2);
        boolean readBit = parsableNalUnitBitArray.readBit();
        int readBits3 = parsableNalUnitBitArray.readBits(5);
        int r9 = 0;
        for (int r10 = 0; r10 < 32; r10++) {
            if (parsableNalUnitBitArray.readBit()) {
                r9 |= 1 << r10;
            }
        }
        int[] r11 = new int[6];
        for (int r13 = 0; r13 < 6; r13++) {
            r11[r13] = parsableNalUnitBitArray.readBits(8);
        }
        int readBits4 = parsableNalUnitBitArray.readBits(8);
        int r15 = 0;
        for (int r102 = 0; r102 < readBits; r102++) {
            if (parsableNalUnitBitArray.readBit()) {
                r15 += 89;
            }
            if (parsableNalUnitBitArray.readBit()) {
                r15 += 8;
            }
        }
        parsableNalUnitBitArray.skipBits(r15);
        if (readBits > 0) {
            parsableNalUnitBitArray.skipBits((8 - readBits) * 2);
        }
        int readUnsignedExpGolombCodedInt = parsableNalUnitBitArray.readUnsignedExpGolombCodedInt();
        int readUnsignedExpGolombCodedInt2 = parsableNalUnitBitArray.readUnsignedExpGolombCodedInt();
        if (readUnsignedExpGolombCodedInt2 == 3) {
            parsableNalUnitBitArray.skipBit();
        }
        int readUnsignedExpGolombCodedInt3 = parsableNalUnitBitArray.readUnsignedExpGolombCodedInt();
        int readUnsignedExpGolombCodedInt4 = parsableNalUnitBitArray.readUnsignedExpGolombCodedInt();
        if (parsableNalUnitBitArray.readBit()) {
            int readUnsignedExpGolombCodedInt5 = parsableNalUnitBitArray.readUnsignedExpGolombCodedInt();
            int readUnsignedExpGolombCodedInt6 = parsableNalUnitBitArray.readUnsignedExpGolombCodedInt();
            int readUnsignedExpGolombCodedInt7 = parsableNalUnitBitArray.readUnsignedExpGolombCodedInt();
            int readUnsignedExpGolombCodedInt8 = parsableNalUnitBitArray.readUnsignedExpGolombCodedInt();
            readUnsignedExpGolombCodedInt3 -= ((readUnsignedExpGolombCodedInt2 == 1 || readUnsignedExpGolombCodedInt2 == 2) ? 2 : 1) * (readUnsignedExpGolombCodedInt5 + readUnsignedExpGolombCodedInt6);
            readUnsignedExpGolombCodedInt4 -= (readUnsignedExpGolombCodedInt2 == 1 ? 2 : 1) * (readUnsignedExpGolombCodedInt7 + readUnsignedExpGolombCodedInt8);
        }
        parsableNalUnitBitArray.readUnsignedExpGolombCodedInt();
        parsableNalUnitBitArray.readUnsignedExpGolombCodedInt();
        int readUnsignedExpGolombCodedInt9 = parsableNalUnitBitArray.readUnsignedExpGolombCodedInt();
        for (int r5 = parsableNalUnitBitArray.readBit() ? 0 : readBits; r5 <= readBits; r5++) {
            parsableNalUnitBitArray.readUnsignedExpGolombCodedInt();
            parsableNalUnitBitArray.readUnsignedExpGolombCodedInt();
            parsableNalUnitBitArray.readUnsignedExpGolombCodedInt();
        }
        parsableNalUnitBitArray.readUnsignedExpGolombCodedInt();
        parsableNalUnitBitArray.readUnsignedExpGolombCodedInt();
        parsableNalUnitBitArray.readUnsignedExpGolombCodedInt();
        parsableNalUnitBitArray.readUnsignedExpGolombCodedInt();
        parsableNalUnitBitArray.readUnsignedExpGolombCodedInt();
        parsableNalUnitBitArray.readUnsignedExpGolombCodedInt();
        if (parsableNalUnitBitArray.readBit() && parsableNalUnitBitArray.readBit()) {
            skipH265ScalingList(parsableNalUnitBitArray);
        }
        parsableNalUnitBitArray.skipBits(2);
        if (parsableNalUnitBitArray.readBit()) {
            parsableNalUnitBitArray.skipBits(8);
            parsableNalUnitBitArray.readUnsignedExpGolombCodedInt();
            parsableNalUnitBitArray.readUnsignedExpGolombCodedInt();
            parsableNalUnitBitArray.skipBit();
        }
        skipShortTermReferencePictureSets(parsableNalUnitBitArray);
        if (parsableNalUnitBitArray.readBit()) {
            for (int r52 = 0; r52 < parsableNalUnitBitArray.readUnsignedExpGolombCodedInt(); r52++) {
                parsableNalUnitBitArray.skipBits(readUnsignedExpGolombCodedInt9 + 4 + 1);
            }
        }
        parsableNalUnitBitArray.skipBits(2);
        float f = 1.0f;
        if (parsableNalUnitBitArray.readBit()) {
            if (parsableNalUnitBitArray.readBit()) {
                int readBits5 = parsableNalUnitBitArray.readBits(8);
                if (readBits5 == 255) {
                    int readBits6 = parsableNalUnitBitArray.readBits(16);
                    int readBits7 = parsableNalUnitBitArray.readBits(16);
                    if (readBits6 != 0 && readBits7 != 0) {
                        f = readBits6 / readBits7;
                    }
                } else {
                    float[] fArr = ASPECT_RATIO_IDC_VALUES;
                    if (readBits5 < fArr.length) {
                        f = fArr[readBits5];
                    } else {
                        Log.m1132w(TAG, "Unexpected aspect_ratio_idc value: " + readBits5);
                    }
                }
            }
            if (parsableNalUnitBitArray.readBit()) {
                parsableNalUnitBitArray.skipBit();
            }
            if (parsableNalUnitBitArray.readBit()) {
                parsableNalUnitBitArray.skipBits(4);
                if (parsableNalUnitBitArray.readBit()) {
                    parsableNalUnitBitArray.skipBits(24);
                }
            }
            if (parsableNalUnitBitArray.readBit()) {
                parsableNalUnitBitArray.readUnsignedExpGolombCodedInt();
                parsableNalUnitBitArray.readUnsignedExpGolombCodedInt();
            }
            parsableNalUnitBitArray.skipBit();
            if (parsableNalUnitBitArray.readBit()) {
                readUnsignedExpGolombCodedInt4 *= 2;
            }
        }
        return new H265SpsData(readBits2, readBit, readBits3, r9, r11, readBits4, readUnsignedExpGolombCodedInt, readUnsignedExpGolombCodedInt3, readUnsignedExpGolombCodedInt4, f);
    }

    public static PpsData parsePpsNalUnit(byte[] bArr, int r1, int r2) {
        return parsePpsNalUnitPayload(bArr, r1 + 1, r2);
    }

    public static PpsData parsePpsNalUnitPayload(byte[] bArr, int r2, int r3) {
        ParsableNalUnitBitArray parsableNalUnitBitArray = new ParsableNalUnitBitArray(bArr, r2, r3);
        int readUnsignedExpGolombCodedInt = parsableNalUnitBitArray.readUnsignedExpGolombCodedInt();
        int readUnsignedExpGolombCodedInt2 = parsableNalUnitBitArray.readUnsignedExpGolombCodedInt();
        parsableNalUnitBitArray.skipBit();
        return new PpsData(readUnsignedExpGolombCodedInt, readUnsignedExpGolombCodedInt2, parsableNalUnitBitArray.readBit());
    }

    public static int findNalUnit(byte[] bArr, int r8, int r9, boolean[] zArr) {
        int r0 = r9 - r8;
        Assertions.checkState(r0 >= 0);
        if (r0 == 0) {
            return r9;
        }
        if (zArr[0]) {
            clearPrefixFlags(zArr);
            return r8 - 3;
        } else if (r0 > 1 && zArr[1] && bArr[r8] == 1) {
            clearPrefixFlags(zArr);
            return r8 - 2;
        } else if (r0 > 2 && zArr[2] && bArr[r8] == 0 && bArr[r8 + 1] == 1) {
            clearPrefixFlags(zArr);
            return r8 - 1;
        } else {
            int r4 = r9 - 1;
            int r82 = r8 + 2;
            while (r82 < r4) {
                if ((bArr[r82] & 254) == 0) {
                    int r5 = r82 - 2;
                    if (bArr[r5] == 0 && bArr[r82 - 1] == 0 && bArr[r82] == 1) {
                        clearPrefixFlags(zArr);
                        return r5;
                    }
                    r82 -= 2;
                }
                r82 += 3;
            }
            zArr[0] = r0 <= 2 ? !(r0 != 2 ? !(zArr[1] && bArr[r4] == 1) : !(zArr[2] && bArr[r9 + (-2)] == 0 && bArr[r4] == 1)) : bArr[r9 + (-3)] == 0 && bArr[r9 + (-2)] == 0 && bArr[r4] == 1;
            zArr[1] = r0 <= 1 ? zArr[2] && bArr[r4] == 0 : bArr[r9 + (-2)] == 0 && bArr[r4] == 0;
            zArr[2] = bArr[r4] == 0;
            return r9;
        }
    }

    public static void clearPrefixFlags(boolean[] zArr) {
        zArr[0] = false;
        zArr[1] = false;
        zArr[2] = false;
    }

    private static int findNextUnescapeIndex(byte[] bArr, int r3, int r4) {
        while (r3 < r4 - 2) {
            if (bArr[r3] == 0 && bArr[r3 + 1] == 0 && bArr[r3 + 2] == 3) {
                return r3;
            }
            r3++;
        }
        return r4;
    }

    private static void skipScalingList(ParsableNalUnitBitArray parsableNalUnitBitArray, int r4) {
        int r0 = 8;
        int r1 = 8;
        for (int r2 = 0; r2 < r4; r2++) {
            if (r0 != 0) {
                r0 = ((parsableNalUnitBitArray.readSignedExpGolombCodedInt() + r1) + 256) % 256;
            }
            if (r0 != 0) {
                r1 = r0;
            }
        }
    }

    private static void skipH265ScalingList(ParsableNalUnitBitArray parsableNalUnitBitArray) {
        for (int r1 = 0; r1 < 4; r1++) {
            int r3 = 0;
            while (r3 < 6) {
                int r5 = 1;
                if (!parsableNalUnitBitArray.readBit()) {
                    parsableNalUnitBitArray.readUnsignedExpGolombCodedInt();
                } else {
                    int min = Math.min(64, 1 << ((r1 << 1) + 4));
                    if (r1 > 1) {
                        parsableNalUnitBitArray.readSignedExpGolombCodedInt();
                    }
                    for (int r6 = 0; r6 < min; r6++) {
                        parsableNalUnitBitArray.readSignedExpGolombCodedInt();
                    }
                }
                if (r1 == 3) {
                    r5 = 3;
                }
                r3 += r5;
            }
        }
    }

    private static void skipShortTermReferencePictureSets(ParsableNalUnitBitArray parsableNalUnitBitArray) {
        int readUnsignedExpGolombCodedInt = parsableNalUnitBitArray.readUnsignedExpGolombCodedInt();
        int[] r2 = new int[0];
        int[] r3 = new int[0];
        int r4 = -1;
        int r5 = -1;
        int r6 = 0;
        while (r6 < readUnsignedExpGolombCodedInt) {
            if (r6 != 0 && parsableNalUnitBitArray.readBit()) {
                int r8 = r4 + r5;
                int readUnsignedExpGolombCodedInt2 = (1 - ((parsableNalUnitBitArray.readBit() ? 1 : 0) * 2)) * (parsableNalUnitBitArray.readUnsignedExpGolombCodedInt() + 1);
                int r10 = r8 + 1;
                boolean[] zArr = new boolean[r10];
                for (int r12 = 0; r12 <= r8; r12++) {
                    if (!parsableNalUnitBitArray.readBit()) {
                        zArr[r12] = parsableNalUnitBitArray.readBit();
                    } else {
                        zArr[r12] = true;
                    }
                }
                int[] r7 = new int[r10];
                int[] r102 = new int[r10];
                int r13 = 0;
                for (int r122 = r5 - 1; r122 >= 0; r122--) {
                    int r14 = r3[r122] + readUnsignedExpGolombCodedInt2;
                    if (r14 < 0 && zArr[r4 + r122]) {
                        r7[r13] = r14;
                        r13++;
                    }
                }
                if (readUnsignedExpGolombCodedInt2 < 0 && zArr[r8]) {
                    r7[r13] = readUnsignedExpGolombCodedInt2;
                    r13++;
                }
                for (int r123 = 0; r123 < r4; r123++) {
                    int r142 = r2[r123] + readUnsignedExpGolombCodedInt2;
                    if (r142 < 0 && zArr[r123]) {
                        r7[r13] = r142;
                        r13++;
                    }
                }
                int[] copyOf = Arrays.copyOf(r7, r13);
                int r143 = 0;
                for (int r124 = r4 - 1; r124 >= 0; r124--) {
                    int r15 = r2[r124] + readUnsignedExpGolombCodedInt2;
                    if (r15 > 0 && zArr[r124]) {
                        r102[r143] = r15;
                        r143++;
                    }
                }
                if (readUnsignedExpGolombCodedInt2 > 0 && zArr[r8]) {
                    r102[r143] = readUnsignedExpGolombCodedInt2;
                    r143++;
                }
                for (int r22 = 0; r22 < r5; r22++) {
                    int r82 = r3[r22] + readUnsignedExpGolombCodedInt2;
                    if (r82 > 0 && zArr[r4 + r22]) {
                        r102[r143] = r82;
                        r143++;
                    }
                }
                r3 = Arrays.copyOf(r102, r143);
                r2 = copyOf;
                r4 = r13;
                r5 = r143;
            } else {
                int readUnsignedExpGolombCodedInt3 = parsableNalUnitBitArray.readUnsignedExpGolombCodedInt();
                int readUnsignedExpGolombCodedInt4 = parsableNalUnitBitArray.readUnsignedExpGolombCodedInt();
                int[] r42 = new int[readUnsignedExpGolombCodedInt3];
                for (int r52 = 0; r52 < readUnsignedExpGolombCodedInt3; r52++) {
                    r42[r52] = parsableNalUnitBitArray.readUnsignedExpGolombCodedInt() + 1;
                    parsableNalUnitBitArray.skipBit();
                }
                int[] r53 = new int[readUnsignedExpGolombCodedInt4];
                for (int r83 = 0; r83 < readUnsignedExpGolombCodedInt4; r83++) {
                    r53[r83] = parsableNalUnitBitArray.readUnsignedExpGolombCodedInt() + 1;
                    parsableNalUnitBitArray.skipBit();
                }
                r4 = readUnsignedExpGolombCodedInt3;
                r2 = r42;
                r5 = readUnsignedExpGolombCodedInt4;
                r3 = r53;
            }
            r6++;
        }
    }

    private NalUnitUtil() {
    }
}
