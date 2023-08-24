package com.google.zxing.qrcode.decoder;

/* loaded from: classes3.dex */
final class FormatInformation {
    private final byte dataMask;
    private final ErrorCorrectionLevel errorCorrectionLevel;
    private static final int FORMAT_INFO_MASK_QR = 21522;
    private static final int[][] FORMAT_INFO_DECODE_LOOKUP = {new int[]{FORMAT_INFO_MASK_QR, 0}, new int[]{20773, 1}, new int[]{24188, 2}, new int[]{23371, 3}, new int[]{17913, 4}, new int[]{16590, 5}, new int[]{20375, 6}, new int[]{19104, 7}, new int[]{30660, 8}, new int[]{29427, 9}, new int[]{32170, 10}, new int[]{30877, 11}, new int[]{26159, 12}, new int[]{25368, 13}, new int[]{27713, 14}, new int[]{26998, 15}, new int[]{5769, 16}, new int[]{5054, 17}, new int[]{7399, 18}, new int[]{6608, 19}, new int[]{1890, 20}, new int[]{597, 21}, new int[]{3340, 22}, new int[]{2107, 23}, new int[]{13663, 24}, new int[]{12392, 25}, new int[]{16177, 26}, new int[]{14854, 27}, new int[]{9396, 28}, new int[]{8579, 29}, new int[]{11994, 30}, new int[]{11245, 31}};

    private FormatInformation(int r2) {
        this.errorCorrectionLevel = ErrorCorrectionLevel.forBits((r2 >> 3) & 3);
        this.dataMask = (byte) (r2 & 7);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int numBitsDiffering(int r0, int r1) {
        return Integer.bitCount(r0 ^ r1);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static FormatInformation decodeFormatInformation(int r1, int r2) {
        FormatInformation doDecodeFormatInformation = doDecodeFormatInformation(r1, r2);
        return doDecodeFormatInformation != null ? doDecodeFormatInformation : doDecodeFormatInformation(r1 ^ FORMAT_INFO_MASK_QR, r2 ^ FORMAT_INFO_MASK_QR);
    }

    private static FormatInformation doDecodeFormatInformation(int r10, int r11) {
        int[][] r0;
        int numBitsDiffering;
        int r3 = Integer.MAX_VALUE;
        int r5 = 0;
        for (int[] r6 : FORMAT_INFO_DECODE_LOOKUP) {
            int r7 = r6[0];
            if (r7 == r10 || r7 == r11) {
                return new FormatInformation(r6[1]);
            }
            int numBitsDiffering2 = numBitsDiffering(r10, r7);
            if (numBitsDiffering2 < r3) {
                r5 = r6[1];
                r3 = numBitsDiffering2;
            }
            if (r10 != r11 && (numBitsDiffering = numBitsDiffering(r11, r7)) < r3) {
                r5 = r6[1];
                r3 = numBitsDiffering;
            }
        }
        if (r3 <= 3) {
            return new FormatInformation(r5);
        }
        return null;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public ErrorCorrectionLevel getErrorCorrectionLevel() {
        return this.errorCorrectionLevel;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public byte getDataMask() {
        return this.dataMask;
    }

    public int hashCode() {
        return (this.errorCorrectionLevel.ordinal() << 3) | this.dataMask;
    }

    public boolean equals(Object obj) {
        if (obj instanceof FormatInformation) {
            FormatInformation formatInformation = (FormatInformation) obj;
            return this.errorCorrectionLevel == formatInformation.errorCorrectionLevel && this.dataMask == formatInformation.dataMask;
        }
        return false;
    }
}
