package com.google.zxing.oned;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.FormatException;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import java.util.Map;

/* loaded from: classes3.dex */
public final class EAN13Writer extends UPCEANWriter {
    private static final int CODE_WIDTH = 95;

    @Override // com.google.zxing.oned.OneDimensionalCodeWriter, com.google.zxing.Writer
    public BitMatrix encode(String str, BarcodeFormat barcodeFormat, int r4, int r5, Map<EncodeHintType, ?> map) throws WriterException {
        if (barcodeFormat != BarcodeFormat.EAN_13) {
            throw new IllegalArgumentException("Can only encode EAN_13, but got ".concat(String.valueOf(barcodeFormat)));
        }
        return super.encode(str, barcodeFormat, r4, r5, map);
    }

    @Override // com.google.zxing.oned.OneDimensionalCodeWriter
    public boolean[] encode(String str) {
        int length = str.length();
        if (length == 12) {
            try {
                str = str + UPCEANReader.getStandardUPCEANChecksum(str);
            } catch (FormatException e) {
                throw new IllegalArgumentException(e);
            }
        } else if (length == 13) {
            try {
                if (!UPCEANReader.checkStandardUPCEANChecksum(str)) {
                    throw new IllegalArgumentException("Contents do not pass checksum");
                }
            } catch (FormatException unused) {
                throw new IllegalArgumentException("Illegal contents");
            }
        } else {
            throw new IllegalArgumentException("Requested contents should be 12 or 13 digits long, but got ".concat(String.valueOf(length)));
        }
        int r2 = EAN13Reader.FIRST_DIGIT_ENCODINGS[Character.digit(str.charAt(0), 10)];
        boolean[] zArr = new boolean[95];
        int appendPattern = appendPattern(zArr, 0, UPCEANReader.START_END_PATTERN, true) + 0;
        for (int r7 = 1; r7 <= 6; r7++) {
            int digit = Character.digit(str.charAt(r7), 10);
            if (((r2 >> (6 - r7)) & 1) == 1) {
                digit += 10;
            }
            appendPattern += appendPattern(zArr, appendPattern, UPCEANReader.L_AND_G_PATTERNS[digit], false);
        }
        int appendPattern2 = appendPattern + appendPattern(zArr, appendPattern, UPCEANReader.MIDDLE_PATTERN, false);
        for (int r0 = 7; r0 <= 12; r0++) {
            appendPattern2 += appendPattern(zArr, appendPattern2, UPCEANReader.L_PATTERNS[Character.digit(str.charAt(r0), 10)], true);
        }
        appendPattern(zArr, appendPattern2, UPCEANReader.START_END_PATTERN, true);
        return zArr;
    }
}
