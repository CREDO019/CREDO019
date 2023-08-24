package com.google.zxing.qrcode.encoder;

import com.google.zxing.EncodeHintType;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitArray;
import com.google.zxing.common.CharacterSetECI;
import com.google.zxing.common.reedsolomon.GenericGF;
import com.google.zxing.common.reedsolomon.ReedSolomonEncoder;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;
import com.google.zxing.qrcode.decoder.Mode;
import com.google.zxing.qrcode.decoder.Version;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Map;

/* loaded from: classes3.dex */
public final class Encoder {
    private static final int[] ALPHANUMERIC_TABLE = {-1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 36, -1, -1, -1, 37, 38, -1, -1, -1, -1, 39, 40, -1, 41, 42, 43, 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 44, -1, -1, -1, -1, -1, -1, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30, 31, 32, 33, 34, 35, -1, -1, -1, -1, -1};
    static final String DEFAULT_BYTE_MODE_ENCODING = "ISO-8859-1";

    private Encoder() {
    }

    private static int calculateMaskPenalty(ByteMatrix byteMatrix) {
        return MaskUtil.applyMaskPenaltyRule1(byteMatrix) + MaskUtil.applyMaskPenaltyRule2(byteMatrix) + MaskUtil.applyMaskPenaltyRule3(byteMatrix) + MaskUtil.applyMaskPenaltyRule4(byteMatrix);
    }

    public static QRCode encode(String str, ErrorCorrectionLevel errorCorrectionLevel) throws WriterException {
        return encode(str, errorCorrectionLevel, null);
    }

    public static QRCode encode(String str, ErrorCorrectionLevel errorCorrectionLevel, Map<EncodeHintType, ?> map) throws WriterException {
        Version recommendVersion;
        CharacterSetECI characterSetECIByName;
        boolean z = true;
        boolean z2 = map != null && map.containsKey(EncodeHintType.CHARACTER_SET);
        String obj = z2 ? map.get(EncodeHintType.CHARACTER_SET).toString() : "ISO-8859-1";
        Mode chooseMode = chooseMode(str, obj);
        BitArray bitArray = new BitArray();
        if (chooseMode == Mode.BYTE && z2 && (characterSetECIByName = CharacterSetECI.getCharacterSetECIByName(obj)) != null) {
            appendECI(characterSetECIByName, bitArray);
        }
        if (((map == null || !map.containsKey(EncodeHintType.GS1_FORMAT)) ? false : false) && Boolean.valueOf(map.get(EncodeHintType.GS1_FORMAT).toString()).booleanValue()) {
            appendModeInfo(Mode.FNC1_FIRST_POSITION, bitArray);
        }
        appendModeInfo(chooseMode, bitArray);
        BitArray bitArray2 = new BitArray();
        appendBytes(str, chooseMode, bitArray2, obj);
        if (map != null && map.containsKey(EncodeHintType.QR_VERSION)) {
            recommendVersion = Version.getVersionForNumber(Integer.parseInt(map.get(EncodeHintType.QR_VERSION).toString()));
            if (!willFit(calculateBitsNeeded(chooseMode, bitArray, bitArray2, recommendVersion), recommendVersion, errorCorrectionLevel)) {
                throw new WriterException("Data too big for requested version");
            }
        } else {
            recommendVersion = recommendVersion(errorCorrectionLevel, chooseMode, bitArray, bitArray2);
        }
        BitArray bitArray3 = new BitArray();
        bitArray3.appendBitArray(bitArray);
        appendLengthInfo(chooseMode == Mode.BYTE ? bitArray2.getSizeInBytes() : str.length(), recommendVersion, chooseMode, bitArray3);
        bitArray3.appendBitArray(bitArray2);
        Version.ECBlocks eCBlocksForLevel = recommendVersion.getECBlocksForLevel(errorCorrectionLevel);
        int totalCodewords = recommendVersion.getTotalCodewords() - eCBlocksForLevel.getTotalECCodewords();
        terminateBits(totalCodewords, bitArray3);
        BitArray interleaveWithECBytes = interleaveWithECBytes(bitArray3, recommendVersion.getTotalCodewords(), totalCodewords, eCBlocksForLevel.getNumBlocks());
        QRCode qRCode = new QRCode();
        qRCode.setECLevel(errorCorrectionLevel);
        qRCode.setMode(chooseMode);
        qRCode.setVersion(recommendVersion);
        int dimensionForVersion = recommendVersion.getDimensionForVersion();
        ByteMatrix byteMatrix = new ByteMatrix(dimensionForVersion, dimensionForVersion);
        int chooseMaskPattern = chooseMaskPattern(interleaveWithECBytes, errorCorrectionLevel, recommendVersion, byteMatrix);
        qRCode.setMaskPattern(chooseMaskPattern);
        MatrixUtil.buildMatrix(interleaveWithECBytes, errorCorrectionLevel, recommendVersion, chooseMaskPattern, byteMatrix);
        qRCode.setMatrix(byteMatrix);
        return qRCode;
    }

    private static Version recommendVersion(ErrorCorrectionLevel errorCorrectionLevel, Mode mode, BitArray bitArray, BitArray bitArray2) throws WriterException {
        return chooseVersion(calculateBitsNeeded(mode, bitArray, bitArray2, chooseVersion(calculateBitsNeeded(mode, bitArray, bitArray2, Version.getVersionForNumber(1)), errorCorrectionLevel)), errorCorrectionLevel);
    }

    private static int calculateBitsNeeded(Mode mode, BitArray bitArray, BitArray bitArray2, Version version) {
        return bitArray.getSize() + mode.getCharacterCountBits(version) + bitArray2.getSize();
    }

    static int getAlphanumericCode(int r2) {
        int[] r0 = ALPHANUMERIC_TABLE;
        if (r2 < r0.length) {
            return r0[r2];
        }
        return -1;
    }

    public static Mode chooseMode(String str) {
        return chooseMode(str, null);
    }

    private static Mode chooseMode(String str, String str2) {
        if ("Shift_JIS".equals(str2) && isOnlyDoubleByteKanji(str)) {
            return Mode.KANJI;
        }
        boolean z = false;
        boolean z2 = false;
        for (int r6 = 0; r6 < str.length(); r6++) {
            char charAt = str.charAt(r6);
            if (charAt >= '0' && charAt <= '9') {
                z2 = true;
            } else if (getAlphanumericCode(charAt) == -1) {
                return Mode.BYTE;
            } else {
                z = true;
            }
        }
        if (z) {
            return Mode.ALPHANUMERIC;
        }
        if (z2) {
            return Mode.NUMERIC;
        }
        return Mode.BYTE;
    }

    private static boolean isOnlyDoubleByteKanji(String str) {
        try {
            byte[] bytes = str.getBytes("Shift_JIS");
            int length = bytes.length;
            if (length % 2 != 0) {
                return false;
            }
            for (int r2 = 0; r2 < length; r2 += 2) {
                int r3 = bytes[r2] & 255;
                if ((r3 < 129 || r3 > 159) && (r3 < 224 || r3 > 235)) {
                    return false;
                }
            }
            return true;
        } catch (UnsupportedEncodingException unused) {
            return false;
        }
    }

    private static int chooseMaskPattern(BitArray bitArray, ErrorCorrectionLevel errorCorrectionLevel, Version version, ByteMatrix byteMatrix) throws WriterException {
        int r0 = Integer.MAX_VALUE;
        int r1 = -1;
        for (int r2 = 0; r2 < 8; r2++) {
            MatrixUtil.buildMatrix(bitArray, errorCorrectionLevel, version, r2, byteMatrix);
            int calculateMaskPenalty = calculateMaskPenalty(byteMatrix);
            if (calculateMaskPenalty < r0) {
                r1 = r2;
                r0 = calculateMaskPenalty;
            }
        }
        return r1;
    }

    private static Version chooseVersion(int r3, ErrorCorrectionLevel errorCorrectionLevel) throws WriterException {
        for (int r0 = 1; r0 <= 40; r0++) {
            Version versionForNumber = Version.getVersionForNumber(r0);
            if (willFit(r3, versionForNumber, errorCorrectionLevel)) {
                return versionForNumber;
            }
        }
        throw new WriterException("Data too big");
    }

    private static boolean willFit(int r1, Version version, ErrorCorrectionLevel errorCorrectionLevel) {
        return version.getTotalCodewords() - version.getECBlocksForLevel(errorCorrectionLevel).getTotalECCodewords() >= (r1 + 7) / 8;
    }

    static void terminateBits(int r4, BitArray bitArray) throws WriterException {
        int r0 = r4 << 3;
        if (bitArray.getSize() > r0) {
            throw new WriterException("data bits cannot fit in the QR Code" + bitArray.getSize() + " > " + r0);
        }
        for (int r2 = 0; r2 < 4 && bitArray.getSize() < r0; r2++) {
            bitArray.appendBit(false);
        }
        int size = bitArray.getSize() & 7;
        if (size > 0) {
            while (size < 8) {
                bitArray.appendBit(false);
                size++;
            }
        }
        int sizeInBytes = r4 - bitArray.getSizeInBytes();
        for (int r1 = 0; r1 < sizeInBytes; r1++) {
            bitArray.appendBits((r1 & 1) == 0 ? 236 : 17, 8);
        }
        if (bitArray.getSize() != r0) {
            throw new WriterException("Bits size does not equal capacity");
        }
    }

    static void getNumDataBytesAndNumECBytesForBlockID(int r6, int r7, int r8, int r9, int[] r10, int[] r11) throws WriterException {
        if (r9 >= r8) {
            throw new WriterException("Block ID too large");
        }
        int r0 = r6 % r8;
        int r1 = r8 - r0;
        int r2 = r6 / r8;
        int r3 = r2 + 1;
        int r72 = r7 / r8;
        int r4 = r72 + 1;
        int r22 = r2 - r72;
        int r32 = r3 - r4;
        if (r22 != r32) {
            throw new WriterException("EC bytes mismatch");
        }
        if (r8 != r1 + r0) {
            throw new WriterException("RS blocks mismatch");
        }
        if (r6 != ((r72 + r22) * r1) + ((r4 + r32) * r0)) {
            throw new WriterException("Total bytes mismatch");
        }
        if (r9 < r1) {
            r10[0] = r72;
            r11[0] = r22;
            return;
        }
        r10[0] = r4;
        r11[0] = r32;
    }

    static BitArray interleaveWithECBytes(BitArray bitArray, int r18, int r19, int r20) throws WriterException {
        if (bitArray.getSizeInBytes() != r19) {
            throw new WriterException("Number of bits and data bytes does not match");
        }
        ArrayList<BlockPair> arrayList = new ArrayList(r20);
        int r12 = 0;
        int r13 = 0;
        int r14 = 0;
        for (int r11 = 0; r11 < r20; r11++) {
            int[] r15 = new int[1];
            int[] r5 = new int[1];
            getNumDataBytesAndNumECBytesForBlockID(r18, r19, r20, r11, r15, r5);
            int r0 = r15[0];
            byte[] bArr = new byte[r0];
            bitArray.toBytes(r12 << 3, bArr, 0, r0);
            byte[] generateECBytes = generateECBytes(bArr, r5[0]);
            arrayList.add(new BlockPair(bArr, generateECBytes));
            r13 = Math.max(r13, r0);
            r14 = Math.max(r14, generateECBytes.length);
            r12 += r15[0];
        }
        if (r19 != r12) {
            throw new WriterException("Data bytes does not match offset");
        }
        BitArray bitArray2 = new BitArray();
        for (int r1 = 0; r1 < r13; r1++) {
            for (BlockPair blockPair : arrayList) {
                byte[] dataBytes = blockPair.getDataBytes();
                if (r1 < dataBytes.length) {
                    bitArray2.appendBits(dataBytes[r1], 8);
                }
            }
        }
        for (int r10 = 0; r10 < r14; r10++) {
            for (BlockPair blockPair2 : arrayList) {
                byte[] errorCorrectionBytes = blockPair2.getErrorCorrectionBytes();
                if (r10 < errorCorrectionBytes.length) {
                    bitArray2.appendBits(errorCorrectionBytes[r10], 8);
                }
            }
        }
        if (r18 == bitArray2.getSizeInBytes()) {
            return bitArray2;
        }
        throw new WriterException("Interleaving error: " + r18 + " and " + bitArray2.getSizeInBytes() + " differ.");
    }

    static byte[] generateECBytes(byte[] bArr, int r6) {
        int length = bArr.length;
        int[] r1 = new int[length + r6];
        for (int r3 = 0; r3 < length; r3++) {
            r1[r3] = bArr[r3] & 255;
        }
        new ReedSolomonEncoder(GenericGF.QR_CODE_FIELD_256).encode(r1, r6);
        byte[] bArr2 = new byte[r6];
        for (int r2 = 0; r2 < r6; r2++) {
            bArr2[r2] = (byte) r1[length + r2];
        }
        return bArr2;
    }

    static void appendModeInfo(Mode mode, BitArray bitArray) {
        bitArray.appendBits(mode.getBits(), 4);
    }

    static void appendLengthInfo(int r1, Version version, Mode mode, BitArray bitArray) throws WriterException {
        int characterCountBits = mode.getCharacterCountBits(version);
        int r0 = 1 << characterCountBits;
        if (r1 >= r0) {
            throw new WriterException(r1 + " is bigger than " + (r0 - 1));
        }
        bitArray.appendBits(r1, characterCountBits);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: com.google.zxing.qrcode.encoder.Encoder$1 */
    /* loaded from: classes3.dex */
    public static /* synthetic */ class C33851 {
        static final /* synthetic */ int[] $SwitchMap$com$google$zxing$qrcode$decoder$Mode;

        static {
            int[] r0 = new int[Mode.values().length];
            $SwitchMap$com$google$zxing$qrcode$decoder$Mode = r0;
            try {
                r0[Mode.NUMERIC.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$google$zxing$qrcode$decoder$Mode[Mode.ALPHANUMERIC.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$com$google$zxing$qrcode$decoder$Mode[Mode.BYTE.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                $SwitchMap$com$google$zxing$qrcode$decoder$Mode[Mode.KANJI.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
        }
    }

    static void appendBytes(String str, Mode mode, BitArray bitArray, String str2) throws WriterException {
        int r0 = C33851.$SwitchMap$com$google$zxing$qrcode$decoder$Mode[mode.ordinal()];
        if (r0 == 1) {
            appendNumericBytes(str, bitArray);
        } else if (r0 == 2) {
            appendAlphanumericBytes(str, bitArray);
        } else if (r0 == 3) {
            append8BitBytes(str, bitArray, str2);
        } else if (r0 == 4) {
            appendKanjiBytes(str, bitArray);
        } else {
            throw new WriterException("Invalid mode: ".concat(String.valueOf(mode)));
        }
    }

    static void appendNumericBytes(CharSequence charSequence, BitArray bitArray) {
        int length = charSequence.length();
        int r1 = 0;
        while (r1 < length) {
            int charAt = charSequence.charAt(r1) - '0';
            int r3 = r1 + 2;
            if (r3 < length) {
                bitArray.appendBits((charAt * 100) + ((charSequence.charAt(r1 + 1) - '0') * 10) + (charSequence.charAt(r3) - '0'), 10);
                r1 += 3;
            } else {
                r1++;
                if (r1 < length) {
                    bitArray.appendBits((charAt * 10) + (charSequence.charAt(r1) - '0'), 7);
                    r1 = r3;
                } else {
                    bitArray.appendBits(charAt, 4);
                }
            }
        }
    }

    static void appendAlphanumericBytes(CharSequence charSequence, BitArray bitArray) throws WriterException {
        int length = charSequence.length();
        int r1 = 0;
        while (r1 < length) {
            int alphanumericCode = getAlphanumericCode(charSequence.charAt(r1));
            if (alphanumericCode == -1) {
                throw new WriterException();
            }
            int r4 = r1 + 1;
            if (r4 < length) {
                int alphanumericCode2 = getAlphanumericCode(charSequence.charAt(r4));
                if (alphanumericCode2 == -1) {
                    throw new WriterException();
                }
                bitArray.appendBits((alphanumericCode * 45) + alphanumericCode2, 11);
                r1 += 2;
            } else {
                bitArray.appendBits(alphanumericCode, 6);
                r1 = r4;
            }
        }
    }

    static void append8BitBytes(String str, BitArray bitArray, String str2) throws WriterException {
        try {
            for (byte b : str.getBytes(str2)) {
                bitArray.appendBits(b, 8);
            }
        } catch (UnsupportedEncodingException e) {
            throw new WriterException(e);
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:17:0x0035 A[LOOP:0: B:4:0x0008->B:17:0x0035, LOOP_END] */
    /* JADX WARN: Removed duplicated region for block: B:27:0x0044 A[SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    static void appendKanjiBytes(java.lang.String r6, com.google.zxing.common.BitArray r7) throws com.google.zxing.WriterException {
        /*
            java.lang.String r0 = "Shift_JIS"
            byte[] r6 = r6.getBytes(r0)     // Catch: java.io.UnsupportedEncodingException -> L4d
            int r0 = r6.length
            r1 = 0
        L8:
            if (r1 >= r0) goto L4c
            r2 = r6[r1]
            r2 = r2 & 255(0xff, float:3.57E-43)
            int r3 = r1 + 1
            r3 = r6[r3]
            r3 = r3 & 255(0xff, float:3.57E-43)
            int r2 = r2 << 8
            r2 = r2 | r3
            r3 = 33088(0x8140, float:4.6366E-41)
            r4 = -1
            if (r2 < r3) goto L24
            r5 = 40956(0x9ffc, float:5.7392E-41)
            if (r2 > r5) goto L24
        L22:
            int r2 = r2 - r3
            goto L33
        L24:
            r3 = 57408(0xe040, float:8.0446E-41)
            if (r2 < r3) goto L32
            r3 = 60351(0xebbf, float:8.457E-41)
            if (r2 > r3) goto L32
            r3 = 49472(0xc140, float:6.9325E-41)
            goto L22
        L32:
            r2 = -1
        L33:
            if (r2 == r4) goto L44
            int r3 = r2 >> 8
            int r3 = r3 * 192
            r2 = r2 & 255(0xff, float:3.57E-43)
            int r3 = r3 + r2
            r2 = 13
            r7.appendBits(r3, r2)
            int r1 = r1 + 2
            goto L8
        L44:
            com.google.zxing.WriterException r6 = new com.google.zxing.WriterException
            java.lang.String r7 = "Invalid byte sequence"
            r6.<init>(r7)
            throw r6
        L4c:
            return
        L4d:
            r6 = move-exception
            com.google.zxing.WriterException r7 = new com.google.zxing.WriterException
            r7.<init>(r6)
            throw r7
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.zxing.qrcode.encoder.Encoder.appendKanjiBytes(java.lang.String, com.google.zxing.common.BitArray):void");
    }

    private static void appendECI(CharacterSetECI characterSetECI, BitArray bitArray) {
        bitArray.appendBits(Mode.ECI.getBits(), 4);
        bitArray.appendBits(characterSetECI.getValue(), 8);
    }
}
