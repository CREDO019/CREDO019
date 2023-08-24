package com.google.zxing.oned;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.ChecksumException;
import com.google.zxing.DecodeHintType;
import com.google.zxing.FormatException;
import com.google.zxing.NotFoundException;
import com.google.zxing.ReaderException;
import com.google.zxing.Result;
import com.google.zxing.ResultMetadataType;
import com.google.zxing.ResultPoint;
import com.google.zxing.ResultPointCallback;
import com.google.zxing.common.BitArray;
import java.util.Arrays;
import java.util.Map;

/* loaded from: classes3.dex */
public abstract class UPCEANReader extends OneDReader {
    static final int[][] L_AND_G_PATTERNS;
    static final int[][] L_PATTERNS;
    private static final float MAX_AVG_VARIANCE = 0.48f;
    private static final float MAX_INDIVIDUAL_VARIANCE = 0.7f;
    static final int[] START_END_PATTERN = {1, 1, 1};
    static final int[] MIDDLE_PATTERN = {1, 1, 1, 1, 1};
    static final int[] END_PATTERN = {1, 1, 1, 1, 1, 1};
    private final StringBuilder decodeRowStringBuffer = new StringBuilder(20);
    private final UPCEANExtensionSupport extensionReader = new UPCEANExtensionSupport();
    private final EANManufacturerOrgSupport eanManSupport = new EANManufacturerOrgSupport();

    /* JADX INFO: Access modifiers changed from: protected */
    public abstract int decodeMiddle(BitArray bitArray, int[] r2, StringBuilder sb) throws NotFoundException;

    abstract BarcodeFormat getBarcodeFormat();

    static {
        int[][] r4 = {new int[]{3, 2, 1, 1}, new int[]{2, 2, 2, 1}, new int[]{2, 1, 2, 2}, new int[]{1, 4, 1, 1}, new int[]{1, 1, 3, 2}, new int[]{1, 2, 3, 1}, new int[]{1, 1, 1, 4}, new int[]{1, 3, 1, 2}, new int[]{1, 2, 1, 3}, new int[]{3, 1, 1, 2}};
        L_PATTERNS = r4;
        int[][] r1 = new int[20];
        L_AND_G_PATTERNS = r1;
        System.arraycopy(r4, 0, r1, 0, 10);
        for (int r3 = 10; r3 < 20; r3++) {
            int[] r12 = L_PATTERNS[r3 - 10];
            int[] r2 = new int[r12.length];
            for (int r42 = 0; r42 < r12.length; r42++) {
                r2[r42] = r12[(r12.length - r42) - 1];
            }
            L_AND_G_PATTERNS[r3] = r2;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int[] findStartGuardPattern(BitArray bitArray) throws NotFoundException {
        int[] r0 = new int[START_END_PATTERN.length];
        int[] r2 = null;
        boolean z = false;
        int r4 = 0;
        while (!z) {
            int[] r22 = START_END_PATTERN;
            Arrays.fill(r0, 0, r22.length, 0);
            r2 = findGuardPattern(bitArray, r4, false, r22, r0);
            int r42 = r2[0];
            int r5 = r2[1];
            int r6 = r42 - (r5 - r42);
            if (r6 >= 0) {
                z = bitArray.isRange(r6, r42, false);
            }
            r4 = r5;
        }
        return r2;
    }

    @Override // com.google.zxing.oned.OneDReader
    public Result decodeRow(int r2, BitArray bitArray, Map<DecodeHintType, ?> map) throws NotFoundException, ChecksumException, FormatException {
        return decodeRow(r2, bitArray, findStartGuardPattern(bitArray), map);
    }

    public Result decodeRow(int r12, BitArray bitArray, int[] r14, Map<DecodeHintType, ?> map) throws NotFoundException, ChecksumException, FormatException {
        int r122;
        String lookupCountryIdentifier;
        ResultPointCallback resultPointCallback = map == null ? null : (ResultPointCallback) map.get(DecodeHintType.NEED_RESULT_POINT_CALLBACK);
        boolean z = true;
        if (resultPointCallback != null) {
            resultPointCallback.foundPossibleResultPoint(new ResultPoint((r14[0] + r14[1]) / 2.0f, r12));
        }
        StringBuilder sb = this.decodeRowStringBuffer;
        sb.setLength(0);
        int decodeMiddle = decodeMiddle(bitArray, r14, sb);
        if (resultPointCallback != null) {
            resultPointCallback.foundPossibleResultPoint(new ResultPoint(decodeMiddle, r12));
        }
        int[] decodeEnd = decodeEnd(bitArray, decodeMiddle);
        if (resultPointCallback != null) {
            resultPointCallback.foundPossibleResultPoint(new ResultPoint((decodeEnd[0] + decodeEnd[1]) / 2.0f, r12));
        }
        int r1 = decodeEnd[1];
        int r7 = (r1 - decodeEnd[0]) + r1;
        if (r7 >= bitArray.getSize() || !bitArray.isRange(r1, r7, false)) {
            throw NotFoundException.getNotFoundInstance();
        }
        String sb2 = sb.toString();
        if (sb2.length() < 8) {
            throw FormatException.getFormatInstance();
        }
        if (!checkChecksum(sb2)) {
            throw ChecksumException.getChecksumInstance();
        }
        BarcodeFormat barcodeFormat = getBarcodeFormat();
        float f = r12;
        Result result = new Result(sb2, null, new ResultPoint[]{new ResultPoint((r14[1] + r14[0]) / 2.0f, f), new ResultPoint((decodeEnd[1] + decodeEnd[0]) / 2.0f, f)}, barcodeFormat);
        try {
            Result decodeRow = this.extensionReader.decodeRow(r12, bitArray, decodeEnd[1]);
            result.putMetadata(ResultMetadataType.UPC_EAN_EXTENSION, decodeRow.getText());
            result.putAllMetadata(decodeRow.getResultMetadata());
            result.addResultPoints(decodeRow.getResultPoints());
            r122 = decodeRow.getText().length();
        } catch (ReaderException unused) {
            r122 = 0;
        }
        int[] r0 = map != null ? (int[]) map.get(DecodeHintType.ALLOWED_EAN_EXTENSIONS) : null;
        if (r0 != null) {
            int length = r0.length;
            int r142 = 0;
            while (true) {
                if (r142 >= length) {
                    z = false;
                    break;
                } else if (r122 == r0[r142]) {
                    break;
                } else {
                    r142++;
                }
            }
            if (!z) {
                throw NotFoundException.getNotFoundInstance();
            }
        }
        if ((barcodeFormat == BarcodeFormat.EAN_13 || barcodeFormat == BarcodeFormat.UPC_A) && (lookupCountryIdentifier = this.eanManSupport.lookupCountryIdentifier(sb2)) != null) {
            result.putMetadata(ResultMetadataType.POSSIBLE_COUNTRY, lookupCountryIdentifier);
        }
        return result;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public boolean checkChecksum(String str) throws FormatException {
        return checkStandardUPCEANChecksum(str);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static boolean checkStandardUPCEANChecksum(CharSequence charSequence) throws FormatException {
        int length = charSequence.length();
        if (length == 0) {
            return false;
        }
        int r0 = length - 1;
        return getStandardUPCEANChecksum(charSequence.subSequence(0, r0)) == Character.digit(charSequence.charAt(r0), 10);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int getStandardUPCEANChecksum(CharSequence charSequence) throws FormatException {
        int length = charSequence.length();
        int r2 = 0;
        for (int r1 = length - 1; r1 >= 0; r1 -= 2) {
            int charAt = charSequence.charAt(r1) - '0';
            if (charAt < 0 || charAt > 9) {
                throw FormatException.getFormatInstance();
            }
            r2 += charAt;
        }
        int r22 = r2 * 3;
        for (int r0 = length - 2; r0 >= 0; r0 -= 2) {
            int charAt2 = charSequence.charAt(r0) - '0';
            if (charAt2 < 0 || charAt2 > 9) {
                throw FormatException.getFormatInstance();
            }
            r22 += charAt2;
        }
        return (1000 - r22) % 10;
    }

    int[] decodeEnd(BitArray bitArray, int r4) throws NotFoundException {
        return findGuardPattern(bitArray, r4, false, START_END_PATTERN);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int[] findGuardPattern(BitArray bitArray, int r2, boolean z, int[] r4) throws NotFoundException {
        return findGuardPattern(bitArray, r2, z, r4, new int[r4.length]);
    }

    private static int[] findGuardPattern(BitArray bitArray, int r10, boolean z, int[] r12, int[] r13) throws NotFoundException {
        int size = bitArray.getSize();
        int nextUnset = z ? bitArray.getNextUnset(r10) : bitArray.getNextSet(r10);
        int length = r12.length;
        boolean z2 = z;
        int r4 = 0;
        int r11 = nextUnset;
        while (nextUnset < size) {
            if (bitArray.get(nextUnset) != z2) {
                r13[r4] = r13[r4] + 1;
            } else {
                if (r4 != length - 1) {
                    r4++;
                } else if (patternMatchVariance(r13, r12, 0.7f) < MAX_AVG_VARIANCE) {
                    return new int[]{r11, nextUnset};
                } else {
                    r11 += r13[0] + r13[1];
                    int r5 = r4 - 1;
                    System.arraycopy(r13, 2, r13, 0, r5);
                    r13[r5] = 0;
                    r13[r4] = 0;
                    r4--;
                }
                r13[r4] = 1;
                z2 = !z2;
            }
            nextUnset++;
        }
        throw NotFoundException.getNotFoundInstance();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int decodeDigit(BitArray bitArray, int[] r5, int r6, int[][] r7) throws NotFoundException {
        recordPattern(bitArray, r6, r5);
        int length = r7.length;
        float f = MAX_AVG_VARIANCE;
        int r0 = -1;
        for (int r1 = 0; r1 < length; r1++) {
            float patternMatchVariance = patternMatchVariance(r5, r7[r1], 0.7f);
            if (patternMatchVariance < f) {
                r0 = r1;
                f = patternMatchVariance;
            }
        }
        if (r0 >= 0) {
            return r0;
        }
        throw NotFoundException.getNotFoundInstance();
    }
}
