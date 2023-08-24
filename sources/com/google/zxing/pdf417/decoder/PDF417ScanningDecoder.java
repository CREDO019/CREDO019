package com.google.zxing.pdf417.decoder;

import com.google.zxing.ChecksumException;
import com.google.zxing.FormatException;
import com.google.zxing.NotFoundException;
import com.google.zxing.ResultPoint;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.common.DecoderResult;
import com.google.zxing.common.detector.MathUtils;
import com.google.zxing.pdf417.PDF417Common;
import com.google.zxing.pdf417.decoder.p018ec.ErrorCorrection;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Formatter;

/* loaded from: classes3.dex */
public final class PDF417ScanningDecoder {
    private static final int CODEWORD_SKEW_SIZE = 2;
    private static final int MAX_EC_CODEWORDS = 512;
    private static final int MAX_ERRORS = 3;
    private static final ErrorCorrection errorCorrection = new ErrorCorrection();

    private static boolean checkCodewordSkew(int r0, int r1, int r2) {
        return r1 + (-2) <= r0 && r0 <= r2 + 2;
    }

    private static int getNumberOfECCodeWords(int r1) {
        return 2 << r1;
    }

    private PDF417ScanningDecoder() {
    }

    public static DecoderResult decode(BitMatrix bitMatrix, ResultPoint resultPoint, ResultPoint resultPoint2, ResultPoint resultPoint3, ResultPoint resultPoint4, int r27, int r28) throws NotFoundException, FormatException, ChecksumException {
        DetectionResultColumn detectionResultRowIndicatorColumn;
        int r20;
        int r12;
        int r19;
        DetectionResultRowIndicatorColumn detectionResultRowIndicatorColumn2 = null;
        DetectionResultRowIndicatorColumn detectionResultRowIndicatorColumn3 = null;
        DetectionResult detectionResult = null;
        BoundingBox boundingBox = new BoundingBox(bitMatrix, resultPoint, resultPoint2, resultPoint3, resultPoint4);
        for (int r11 = 0; r11 < 2; r11++) {
            if (resultPoint != null) {
                detectionResultRowIndicatorColumn2 = getRowIndicatorColumn(bitMatrix, boundingBox, resultPoint, true, r27, r28);
            }
            if (resultPoint3 != null) {
                detectionResultRowIndicatorColumn3 = getRowIndicatorColumn(bitMatrix, boundingBox, resultPoint3, false, r27, r28);
            }
            detectionResult = merge(detectionResultRowIndicatorColumn2, detectionResultRowIndicatorColumn3);
            if (detectionResult == null) {
                throw NotFoundException.getNotFoundInstance();
            }
            if (r11 == 0 && detectionResult.getBoundingBox() != null && (detectionResult.getBoundingBox().getMinY() < boundingBox.getMinY() || detectionResult.getBoundingBox().getMaxY() > boundingBox.getMaxY())) {
                boundingBox = detectionResult.getBoundingBox();
            } else {
                detectionResult.setBoundingBox(boundingBox);
                break;
            }
        }
        int barcodeColumnCount = detectionResult.getBarcodeColumnCount() + 1;
        detectionResult.setDetectionResultColumn(0, detectionResultRowIndicatorColumn2);
        detectionResult.setDetectionResultColumn(barcodeColumnCount, detectionResultRowIndicatorColumn3);
        boolean z = detectionResultRowIndicatorColumn2 != null;
        int r2 = r27;
        int r6 = r28;
        for (int r7 = 1; r7 <= barcodeColumnCount; r7++) {
            int r8 = z ? r7 : barcodeColumnCount - r7;
            if (detectionResult.getDetectionResultColumn(r8) == null) {
                if (r8 == 0 || r8 == barcodeColumnCount) {
                    detectionResultRowIndicatorColumn = new DetectionResultRowIndicatorColumn(boundingBox, r8 == 0);
                } else {
                    detectionResultRowIndicatorColumn = new DetectionResultColumn(boundingBox);
                }
                detectionResult.setDetectionResultColumn(r8, detectionResultRowIndicatorColumn);
                int r15 = -1;
                int minY = boundingBox.getMinY();
                int r13 = -1;
                while (minY <= boundingBox.getMaxY()) {
                    int startColumn = getStartColumn(detectionResult, r8, minY, z);
                    if (startColumn >= 0 && startColumn <= boundingBox.getMaxX()) {
                        r19 = startColumn;
                    } else if (r13 != r15) {
                        r19 = r13;
                    } else {
                        r20 = r13;
                        r12 = minY;
                        r13 = r20;
                        minY = r12 + 1;
                        r15 = -1;
                    }
                    r20 = r13;
                    int r23 = minY;
                    Codeword detectCodeword = detectCodeword(bitMatrix, boundingBox.getMinX(), boundingBox.getMaxX(), z, r19, r23, r2, r6);
                    r12 = r23;
                    if (detectCodeword != null) {
                        detectionResultRowIndicatorColumn.setCodeword(r12, detectCodeword);
                        r2 = Math.min(r2, detectCodeword.getWidth());
                        r6 = Math.max(r6, detectCodeword.getWidth());
                        r13 = r19;
                        minY = r12 + 1;
                        r15 = -1;
                    }
                    r13 = r20;
                    minY = r12 + 1;
                    r15 = -1;
                }
            }
        }
        return createDecoderResult(detectionResult);
    }

    private static DetectionResult merge(DetectionResultRowIndicatorColumn detectionResultRowIndicatorColumn, DetectionResultRowIndicatorColumn detectionResultRowIndicatorColumn2) throws NotFoundException {
        BarcodeMetadata barcodeMetadata;
        if ((detectionResultRowIndicatorColumn == null && detectionResultRowIndicatorColumn2 == null) || (barcodeMetadata = getBarcodeMetadata(detectionResultRowIndicatorColumn, detectionResultRowIndicatorColumn2)) == null) {
            return null;
        }
        return new DetectionResult(barcodeMetadata, BoundingBox.merge(adjustBoundingBox(detectionResultRowIndicatorColumn), adjustBoundingBox(detectionResultRowIndicatorColumn2)));
    }

    private static BoundingBox adjustBoundingBox(DetectionResultRowIndicatorColumn detectionResultRowIndicatorColumn) throws NotFoundException {
        int[] rowHeights;
        if (detectionResultRowIndicatorColumn == null || (rowHeights = detectionResultRowIndicatorColumn.getRowHeights()) == null) {
            return null;
        }
        int max = getMax(rowHeights);
        int r3 = 0;
        int r5 = 0;
        for (int r6 : rowHeights) {
            r5 += max - r6;
            if (r6 > 0) {
                break;
            }
        }
        Codeword[] codewords = detectionResultRowIndicatorColumn.getCodewords();
        for (int r4 = 0; r5 > 0 && codewords[r4] == null; r4++) {
            r5--;
        }
        for (int length = rowHeights.length - 1; length >= 0; length--) {
            r3 += max - rowHeights[length];
            if (rowHeights[length] > 0) {
                break;
            }
        }
        for (int length2 = codewords.length - 1; r3 > 0 && codewords[length2] == null; length2--) {
            r3--;
        }
        return detectionResultRowIndicatorColumn.getBoundingBox().addMissingRows(r5, r3, detectionResultRowIndicatorColumn.isLeft());
    }

    private static int getMax(int[] r4) {
        int r1 = -1;
        for (int r3 : r4) {
            r1 = Math.max(r1, r3);
        }
        return r1;
    }

    private static BarcodeMetadata getBarcodeMetadata(DetectionResultRowIndicatorColumn detectionResultRowIndicatorColumn, DetectionResultRowIndicatorColumn detectionResultRowIndicatorColumn2) {
        BarcodeMetadata barcodeMetadata;
        BarcodeMetadata barcodeMetadata2;
        if (detectionResultRowIndicatorColumn == null || (barcodeMetadata = detectionResultRowIndicatorColumn.getBarcodeMetadata()) == null) {
            if (detectionResultRowIndicatorColumn2 == null) {
                return null;
            }
            return detectionResultRowIndicatorColumn2.getBarcodeMetadata();
        } else if (detectionResultRowIndicatorColumn2 == null || (barcodeMetadata2 = detectionResultRowIndicatorColumn2.getBarcodeMetadata()) == null || barcodeMetadata.getColumnCount() == barcodeMetadata2.getColumnCount() || barcodeMetadata.getErrorCorrectionLevel() == barcodeMetadata2.getErrorCorrectionLevel() || barcodeMetadata.getRowCount() == barcodeMetadata2.getRowCount()) {
            return barcodeMetadata;
        } else {
            return null;
        }
    }

    private static DetectionResultRowIndicatorColumn getRowIndicatorColumn(BitMatrix bitMatrix, BoundingBox boundingBox, ResultPoint resultPoint, boolean z, int r19, int r20) {
        int endX;
        DetectionResultRowIndicatorColumn detectionResultRowIndicatorColumn = new DetectionResultRowIndicatorColumn(boundingBox, z);
        int r11 = 0;
        while (r11 < 2) {
            int r12 = r11 == 0 ? 1 : -1;
            int x = (int) resultPoint.getX();
            for (int y = (int) resultPoint.getY(); y <= boundingBox.getMaxY() && y >= boundingBox.getMinY(); y += r12) {
                Codeword detectCodeword = detectCodeword(bitMatrix, 0, bitMatrix.getWidth(), z, x, y, r19, r20);
                if (detectCodeword != null) {
                    detectionResultRowIndicatorColumn.setCodeword(y, detectCodeword);
                    if (z) {
                        endX = detectCodeword.getStartX();
                    } else {
                        endX = detectCodeword.getEndX();
                    }
                    x = endX;
                }
            }
            r11++;
        }
        return detectionResultRowIndicatorColumn;
    }

    private static void adjustCodewordCount(DetectionResult detectionResult, BarcodeValue[][] barcodeValueArr) throws NotFoundException {
        BarcodeValue barcodeValue = barcodeValueArr[0][1];
        int[] value = barcodeValue.getValue();
        int barcodeColumnCount = (detectionResult.getBarcodeColumnCount() * detectionResult.getBarcodeRowCount()) - getNumberOfECCodeWords(detectionResult.getBarcodeECLevel());
        if (value.length != 0) {
            if (value[0] != barcodeColumnCount) {
                barcodeValue.setValue(barcodeColumnCount);
            }
        } else if (barcodeColumnCount <= 0 || barcodeColumnCount > 928) {
            throw NotFoundException.getNotFoundInstance();
        } else {
            barcodeValue.setValue(barcodeColumnCount);
        }
    }

    private static DecoderResult createDecoderResult(DetectionResult detectionResult) throws FormatException, ChecksumException, NotFoundException {
        BarcodeValue[][] createBarcodeMatrix = createBarcodeMatrix(detectionResult);
        adjustCodewordCount(detectionResult, createBarcodeMatrix);
        ArrayList arrayList = new ArrayList();
        int[] r2 = new int[detectionResult.getBarcodeRowCount() * detectionResult.getBarcodeColumnCount()];
        ArrayList arrayList2 = new ArrayList();
        ArrayList arrayList3 = new ArrayList();
        for (int r6 = 0; r6 < detectionResult.getBarcodeRowCount(); r6++) {
            int r7 = 0;
            while (r7 < detectionResult.getBarcodeColumnCount()) {
                int r9 = r7 + 1;
                int[] value = createBarcodeMatrix[r6][r9].getValue();
                int barcodeColumnCount = (detectionResult.getBarcodeColumnCount() * r6) + r7;
                if (value.length == 0) {
                    arrayList.add(Integer.valueOf(barcodeColumnCount));
                } else if (value.length == 1) {
                    r2[barcodeColumnCount] = value[0];
                } else {
                    arrayList3.add(Integer.valueOf(barcodeColumnCount));
                    arrayList2.add(value);
                }
                r7 = r9;
            }
        }
        int size = arrayList2.size();
        int[][] r62 = new int[size];
        for (int r5 = 0; r5 < size; r5++) {
            r62[r5] = (int[]) arrayList2.get(r5);
        }
        return createDecoderResultFromAmbiguousValues(detectionResult.getBarcodeECLevel(), r2, PDF417Common.toIntArray(arrayList), PDF417Common.toIntArray(arrayList3), r62);
    }

    private static DecoderResult createDecoderResultFromAmbiguousValues(int r8, int[] r9, int[] r10, int[] r11, int[][] r12) throws FormatException, ChecksumException {
        int length = r11.length;
        int[] r1 = new int[length];
        int r2 = 100;
        while (true) {
            int r3 = r2 - 1;
            if (r2 > 0) {
                for (int r4 = 0; r4 < length; r4++) {
                    r9[r11[r4]] = r12[r4][r1[r4]];
                }
                try {
                    return decodeCodewords(r9, r8, r10);
                } catch (ChecksumException unused) {
                    if (length == 0) {
                        throw ChecksumException.getChecksumInstance();
                    }
                    int r42 = 0;
                    while (true) {
                        if (r42 >= length) {
                            break;
                        } else if (r1[r42] < r12[r42].length - 1) {
                            r1[r42] = r1[r42] + 1;
                            break;
                        } else {
                            r1[r42] = 0;
                            if (r42 == length - 1) {
                                throw ChecksumException.getChecksumInstance();
                            }
                            r42++;
                        }
                    }
                    r2 = r3;
                }
            } else {
                throw ChecksumException.getChecksumInstance();
            }
        }
    }

    private static BarcodeValue[][] createBarcodeMatrix(DetectionResult detectionResult) {
        DetectionResultColumn[] detectionResultColumns;
        Codeword[] codewords;
        int rowNumber;
        BarcodeValue[][] barcodeValueArr = (BarcodeValue[][]) Array.newInstance(BarcodeValue.class, detectionResult.getBarcodeRowCount(), detectionResult.getBarcodeColumnCount() + 2);
        for (int r2 = 0; r2 < barcodeValueArr.length; r2++) {
            for (int r3 = 0; r3 < barcodeValueArr[r2].length; r3++) {
                barcodeValueArr[r2][r3] = new BarcodeValue();
            }
        }
        int r4 = 0;
        for (DetectionResultColumn detectionResultColumn : detectionResult.getDetectionResultColumns()) {
            if (detectionResultColumn != null) {
                for (Codeword codeword : detectionResultColumn.getCodewords()) {
                    if (codeword != null && (rowNumber = codeword.getRowNumber()) >= 0 && rowNumber < barcodeValueArr.length) {
                        barcodeValueArr[rowNumber][r4].setValue(codeword.getValue());
                    }
                }
            }
            r4++;
        }
        return barcodeValueArr;
    }

    private static boolean isValidBarcodeColumn(DetectionResult detectionResult, int r2) {
        return r2 >= 0 && r2 <= detectionResult.getBarcodeColumnCount() + 1;
    }

    private static int getStartColumn(DetectionResult detectionResult, int r7, int r8, boolean z) {
        Codeword[] codewords;
        int r0 = z ? 1 : -1;
        int r2 = r7 - r0;
        Codeword codeword = isValidBarcodeColumn(detectionResult, r2) ? detectionResult.getDetectionResultColumn(r2).getCodeword(r8) : null;
        if (codeword != null) {
            return z ? codeword.getEndX() : codeword.getStartX();
        }
        Codeword codewordNearby = detectionResult.getDetectionResultColumn(r7).getCodewordNearby(r8);
        if (codewordNearby != null) {
            return z ? codewordNearby.getStartX() : codewordNearby.getEndX();
        }
        if (isValidBarcodeColumn(detectionResult, r2)) {
            codewordNearby = detectionResult.getDetectionResultColumn(r2).getCodewordNearby(r8);
        }
        if (codewordNearby != null) {
            return z ? codewordNearby.getEndX() : codewordNearby.getStartX();
        }
        int r1 = 0;
        while (true) {
            r7 -= r0;
            if (isValidBarcodeColumn(detectionResult, r7)) {
                for (Codeword codeword2 : detectionResult.getDetectionResultColumn(r7).getCodewords()) {
                    if (codeword2 != null) {
                        return (z ? codeword2.getEndX() : codeword2.getStartX()) + (r0 * r1 * (codeword2.getEndX() - codeword2.getStartX()));
                    }
                }
                r1++;
            } else {
                BoundingBox boundingBox = detectionResult.getBoundingBox();
                return z ? boundingBox.getMinX() : boundingBox.getMaxX();
            }
        }
    }

    private static Codeword detectCodeword(BitMatrix bitMatrix, int r8, int r9, boolean z, int r11, int r12, int r13, int r14) {
        int r10;
        int decodedValue;
        int codeword;
        int adjustCodewordStartColumn = adjustCodewordStartColumn(bitMatrix, r8, r9, z, r11, r12);
        int[] moduleBitCount = getModuleBitCount(bitMatrix, r8, r9, z, adjustCodewordStartColumn, r12);
        if (moduleBitCount == null) {
            return null;
        }
        int sum = MathUtils.sum(moduleBitCount);
        if (z) {
            r10 = adjustCodewordStartColumn + sum;
        } else {
            for (int r102 = 0; r102 < moduleBitCount.length / 2; r102++) {
                int r122 = moduleBitCount[r102];
                moduleBitCount[r102] = moduleBitCount[(moduleBitCount.length - 1) - r102];
                moduleBitCount[(moduleBitCount.length - 1) - r102] = r122;
            }
            adjustCodewordStartColumn -= sum;
            r10 = adjustCodewordStartColumn;
        }
        if (checkCodewordSkew(sum, r13, r14) && (codeword = PDF417Common.getCodeword((decodedValue = PDF417CodewordDecoder.getDecodedValue(moduleBitCount)))) != -1) {
            return new Codeword(adjustCodewordStartColumn, r10, getCodewordBucketNumber(decodedValue), codeword);
        }
        return null;
    }

    /* JADX WARN: Removed duplicated region for block: B:12:0x0015  */
    /* JADX WARN: Removed duplicated region for block: B:27:0x0027 A[EDGE_INSN: B:27:0x0027->B:16:0x0027 ?: BREAK  , SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private static int[] getModuleBitCount(com.google.zxing.common.BitMatrix r7, int r8, int r9, boolean r10, int r11, int r12) {
        /*
            r0 = 8
            int[] r1 = new int[r0]
            r2 = 1
            if (r10 == 0) goto L9
            r3 = 1
            goto La
        L9:
            r3 = -1
        La:
            r4 = 0
            r5 = r10
        Lc:
            if (r10 == 0) goto L11
            if (r11 >= r9) goto L27
            goto L13
        L11:
            if (r11 < r8) goto L27
        L13:
            if (r4 >= r0) goto L27
            boolean r6 = r7.get(r11, r12)
            if (r6 != r5) goto L22
            r6 = r1[r4]
            int r6 = r6 + r2
            r1[r4] = r6
            int r11 = r11 + r3
            goto Lc
        L22:
            int r4 = r4 + 1
            r5 = r5 ^ 1
            goto Lc
        L27:
            if (r4 == r0) goto L34
            if (r10 == 0) goto L2c
            r8 = r9
        L2c:
            if (r11 != r8) goto L32
            r7 = 7
            if (r4 != r7) goto L32
            goto L34
        L32:
            r7 = 0
            return r7
        L34:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.zxing.pdf417.decoder.PDF417ScanningDecoder.getModuleBitCount(com.google.zxing.common.BitMatrix, int, int, boolean, int, int):int[]");
    }

    /* JADX WARN: Code restructure failed: missing block: B:22:0x0022, code lost:
        continue;
     */
    /* JADX WARN: Code restructure failed: missing block: B:23:0x0022, code lost:
        continue;
     */
    /* JADX WARN: Code restructure failed: missing block: B:24:0x0022, code lost:
        continue;
     */
    /* JADX WARN: Removed duplicated region for block: B:14:0x0017  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private static int adjustCodewordStartColumn(com.google.zxing.common.BitMatrix r5, int r6, int r7, boolean r8, int r9, int r10) {
        /*
            if (r8 == 0) goto L4
            r0 = -1
            goto L5
        L4:
            r0 = 1
        L5:
            r1 = 0
            r2 = r9
        L7:
            r3 = 2
            if (r1 >= r3) goto L28
        La:
            if (r8 == 0) goto Lf
            if (r2 < r6) goto L22
            goto L11
        Lf:
            if (r2 >= r7) goto L22
        L11:
            boolean r4 = r5.get(r2, r10)
            if (r8 != r4) goto L22
            int r4 = r9 - r2
            int r4 = java.lang.Math.abs(r4)
            if (r4 <= r3) goto L20
            return r9
        L20:
            int r2 = r2 + r0
            goto La
        L22:
            int r0 = -r0
            r8 = r8 ^ 1
            int r1 = r1 + 1
            goto L7
        L28:
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.zxing.pdf417.decoder.PDF417ScanningDecoder.adjustCodewordStartColumn(com.google.zxing.common.BitMatrix, int, int, boolean, int, int):int");
    }

    private static DecoderResult decodeCodewords(int[] r2, int r3, int[] r4) throws FormatException, ChecksumException {
        if (r2.length == 0) {
            throw FormatException.getFormatInstance();
        }
        int r0 = 1 << (r3 + 1);
        int correctErrors = correctErrors(r2, r4, r0);
        verifyCodewordCount(r2, r0);
        DecoderResult decode = DecodedBitStreamParser.decode(r2, String.valueOf(r3));
        decode.setErrorsCorrected(Integer.valueOf(correctErrors));
        decode.setErasures(Integer.valueOf(r4.length));
        return decode;
    }

    private static int correctErrors(int[] r2, int[] r3, int r4) throws ChecksumException {
        if ((r3 != null && r3.length > (r4 / 2) + 3) || r4 < 0 || r4 > 512) {
            throw ChecksumException.getChecksumInstance();
        }
        return errorCorrection.decode(r2, r4, r3);
    }

    private static void verifyCodewordCount(int[] r3, int r4) throws FormatException {
        if (r3.length < 4) {
            throw FormatException.getFormatInstance();
        }
        int r1 = r3[0];
        if (r1 > r3.length) {
            throw FormatException.getFormatInstance();
        }
        if (r1 == 0) {
            if (r4 < r3.length) {
                r3[0] = r3.length - r4;
                return;
            }
            throw FormatException.getFormatInstance();
        }
    }

    private static int[] getBitCountForCodeword(int r4) {
        int[] r0 = new int[8];
        int r1 = 0;
        int r2 = 7;
        while (true) {
            int r3 = r4 & 1;
            if (r3 != r1) {
                r2--;
                if (r2 < 0) {
                    return r0;
                }
                r1 = r3;
            }
            r0[r2] = r0[r2] + 1;
            r4 >>= 1;
        }
    }

    private static int getCodewordBucketNumber(int r0) {
        return getCodewordBucketNumber(getBitCountForCodeword(r0));
    }

    private static int getCodewordBucketNumber(int[] r2) {
        return ((((r2[0] - r2[2]) + r2[4]) - r2[6]) + 9) % 9;
    }

    public static String toString(BarcodeValue[][] barcodeValueArr) {
        Formatter formatter = new Formatter();
        for (int r2 = 0; r2 < barcodeValueArr.length; r2++) {
            try {
                formatter.format("Row %2d: ", Integer.valueOf(r2));
                for (int r3 = 0; r3 < barcodeValueArr[r2].length; r3++) {
                    BarcodeValue barcodeValue = barcodeValueArr[r2][r3];
                    if (barcodeValue.getValue().length == 0) {
                        formatter.format("        ", null);
                    } else {
                        formatter.format("%4d(%2d)", Integer.valueOf(barcodeValue.getValue()[0]), barcodeValue.getConfidence(barcodeValue.getValue()[0]));
                    }
                }
                formatter.format("%n", new Object[0]);
            } catch (Throwable th) {
                try {
                    throw th;
                } catch (Throwable th2) {
                    try {
                        formatter.close();
                    } catch (Throwable th3) {
                        th.addSuppressed(th3);
                    }
                    throw th2;
                }
            }
        }
        String formatter2 = formatter.toString();
        formatter.close();
        return formatter2;
    }
}
