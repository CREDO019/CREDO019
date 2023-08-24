package com.google.zxing.pdf417;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.Writer;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.pdf417.encoder.Compaction;
import com.google.zxing.pdf417.encoder.Dimensions;
import com.google.zxing.pdf417.encoder.PDF417;
import java.lang.reflect.Array;
import java.nio.charset.Charset;
import java.util.Map;

/* loaded from: classes3.dex */
public final class PDF417Writer implements Writer {
    private static final int DEFAULT_ERROR_CORRECTION_LEVEL = 2;
    private static final int WHITE_SPACE = 30;

    @Override // com.google.zxing.Writer
    public BitMatrix encode(String str, BarcodeFormat barcodeFormat, int r10, int r11, Map<EncodeHintType, ?> map) throws WriterException {
        int r3;
        int r6;
        if (barcodeFormat != BarcodeFormat.PDF_417) {
            throw new IllegalArgumentException("Can only encode PDF_417, but got ".concat(String.valueOf(barcodeFormat)));
        }
        PDF417 pdf417 = new PDF417();
        if (map != null) {
            if (map.containsKey(EncodeHintType.PDF417_COMPACT)) {
                pdf417.setCompact(Boolean.valueOf(map.get(EncodeHintType.PDF417_COMPACT).toString()).booleanValue());
            }
            if (map.containsKey(EncodeHintType.PDF417_COMPACTION)) {
                pdf417.setCompaction(Compaction.valueOf(map.get(EncodeHintType.PDF417_COMPACTION).toString()));
            }
            if (map.containsKey(EncodeHintType.PDF417_DIMENSIONS)) {
                Dimensions dimensions = (Dimensions) map.get(EncodeHintType.PDF417_DIMENSIONS);
                pdf417.setDimensions(dimensions.getMaxCols(), dimensions.getMinCols(), dimensions.getMaxRows(), dimensions.getMinRows());
            }
            int parseInt = map.containsKey(EncodeHintType.MARGIN) ? Integer.parseInt(map.get(EncodeHintType.MARGIN).toString()) : 30;
            int parseInt2 = map.containsKey(EncodeHintType.ERROR_CORRECTION) ? Integer.parseInt(map.get(EncodeHintType.ERROR_CORRECTION).toString()) : 2;
            if (map.containsKey(EncodeHintType.CHARACTER_SET)) {
                pdf417.setEncoding(Charset.forName(map.get(EncodeHintType.CHARACTER_SET).toString()));
            }
            r6 = parseInt;
            r3 = parseInt2;
        } else {
            r3 = 2;
            r6 = 30;
        }
        return bitMatrixFromEncoder(pdf417, str, r3, r10, r11, r6);
    }

    @Override // com.google.zxing.Writer
    public BitMatrix encode(String str, BarcodeFormat barcodeFormat, int r9, int r10) throws WriterException {
        return encode(str, barcodeFormat, r9, r10, null);
    }

    private static BitMatrix bitMatrixFromEncoder(PDF417 pdf417, String str, int r6, int r7, int r8, int r9) throws WriterException {
        boolean z;
        pdf417.generateBarcodeLogic(str, r6);
        byte[][] scaledMatrix = pdf417.getBarcodeMatrix().getScaledMatrix(1, 4);
        if ((r8 > r7) != (scaledMatrix[0].length < scaledMatrix.length)) {
            scaledMatrix = rotateArray(scaledMatrix);
            z = true;
        } else {
            z = false;
        }
        int length = r7 / scaledMatrix[0].length;
        int length2 = r8 / scaledMatrix.length;
        if (length >= length2) {
            length = length2;
        }
        if (length > 1) {
            byte[][] scaledMatrix2 = pdf417.getBarcodeMatrix().getScaledMatrix(length, length << 2);
            if (z) {
                scaledMatrix2 = rotateArray(scaledMatrix2);
            }
            return bitMatrixFromBitArray(scaledMatrix2, r9);
        }
        return bitMatrixFromBitArray(scaledMatrix, r9);
    }

    private static BitMatrix bitMatrixFromBitArray(byte[][] bArr, int r9) {
        int r3 = r9 * 2;
        BitMatrix bitMatrix = new BitMatrix(bArr[0].length + r3, bArr.length + r3);
        bitMatrix.clear();
        int height = (bitMatrix.getHeight() - r9) - 1;
        int r4 = 0;
        while (r4 < bArr.length) {
            byte[] bArr2 = bArr[r4];
            for (int r6 = 0; r6 < bArr[0].length; r6++) {
                if (bArr2[r6] == 1) {
                    bitMatrix.set(r6 + r9, height);
                }
            }
            r4++;
            height--;
        }
        return bitMatrix;
    }

    private static byte[][] rotateArray(byte[][] bArr) {
        byte[][] bArr2 = (byte[][]) Array.newInstance(byte.class, bArr[0].length, bArr.length);
        for (int r2 = 0; r2 < bArr.length; r2++) {
            int length = (bArr.length - r2) - 1;
            for (int r5 = 0; r5 < bArr[0].length; r5++) {
                bArr2[r5][length] = bArr[r2][r5];
            }
        }
        return bArr2;
    }
}
