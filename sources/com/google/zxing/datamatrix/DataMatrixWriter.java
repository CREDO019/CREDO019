package com.google.zxing.datamatrix;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.Dimension;
import com.google.zxing.EncodeHintType;
import com.google.zxing.Writer;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.datamatrix.encoder.DefaultPlacement;
import com.google.zxing.datamatrix.encoder.ErrorCorrection;
import com.google.zxing.datamatrix.encoder.HighLevelEncoder;
import com.google.zxing.datamatrix.encoder.SymbolInfo;
import com.google.zxing.datamatrix.encoder.SymbolShapeHint;
import com.google.zxing.qrcode.encoder.ByteMatrix;
import java.util.Map;

/* loaded from: classes3.dex */
public final class DataMatrixWriter implements Writer {
    @Override // com.google.zxing.Writer
    public BitMatrix encode(String str, BarcodeFormat barcodeFormat, int r9, int r10) {
        return encode(str, barcodeFormat, r9, r10, null);
    }

    @Override // com.google.zxing.Writer
    public BitMatrix encode(String str, BarcodeFormat barcodeFormat, int r6, int r7, Map<EncodeHintType, ?> map) {
        Dimension dimension;
        if (str.isEmpty()) {
            throw new IllegalArgumentException("Found empty contents");
        }
        if (barcodeFormat == BarcodeFormat.DATA_MATRIX) {
            if (r6 < 0 || r7 < 0) {
                throw new IllegalArgumentException("Requested dimensions can't be negative: " + r6 + 'x' + r7);
            }
            SymbolShapeHint symbolShapeHint = SymbolShapeHint.FORCE_NONE;
            Dimension dimension2 = null;
            if (map != null) {
                SymbolShapeHint symbolShapeHint2 = (SymbolShapeHint) map.get(EncodeHintType.DATA_MATRIX_SHAPE);
                if (symbolShapeHint2 != null) {
                    symbolShapeHint = symbolShapeHint2;
                }
                Dimension dimension3 = (Dimension) map.get(EncodeHintType.MIN_SIZE);
                if (dimension3 == null) {
                    dimension3 = null;
                }
                dimension = (Dimension) map.get(EncodeHintType.MAX_SIZE);
                if (dimension == null) {
                    dimension = null;
                }
                dimension2 = dimension3;
            } else {
                dimension = null;
            }
            String encodeHighLevel = HighLevelEncoder.encodeHighLevel(str, symbolShapeHint, dimension2, dimension);
            SymbolInfo lookup = SymbolInfo.lookup(encodeHighLevel.length(), symbolShapeHint, dimension2, dimension, true);
            DefaultPlacement defaultPlacement = new DefaultPlacement(ErrorCorrection.encodeECC200(encodeHighLevel, lookup), lookup.getSymbolDataWidth(), lookup.getSymbolDataHeight());
            defaultPlacement.place();
            return encodeLowLevel(defaultPlacement, lookup, r6, r7);
        }
        throw new IllegalArgumentException("Can only encode DATA_MATRIX, but got ".concat(String.valueOf(barcodeFormat)));
    }

    private static BitMatrix encodeLowLevel(DefaultPlacement defaultPlacement, SymbolInfo symbolInfo, int r13, int r14) {
        int symbolDataWidth = symbolInfo.getSymbolDataWidth();
        int symbolDataHeight = symbolInfo.getSymbolDataHeight();
        ByteMatrix byteMatrix = new ByteMatrix(symbolInfo.getSymbolWidth(), symbolInfo.getSymbolHeight());
        int r5 = 0;
        for (int r4 = 0; r4 < symbolDataHeight; r4++) {
            if (r4 % symbolInfo.matrixHeight == 0) {
                int r8 = 0;
                for (int r6 = 0; r6 < symbolInfo.getSymbolWidth(); r6++) {
                    byteMatrix.set(r8, r5, r6 % 2 == 0);
                    r8++;
                }
                r5++;
            }
            int r82 = 0;
            for (int r62 = 0; r62 < symbolDataWidth; r62++) {
                if (r62 % symbolInfo.matrixWidth == 0) {
                    byteMatrix.set(r82, r5, true);
                    r82++;
                }
                byteMatrix.set(r82, r5, defaultPlacement.getBit(r62, r4));
                r82++;
                if (r62 % symbolInfo.matrixWidth == symbolInfo.matrixWidth - 1) {
                    byteMatrix.set(r82, r5, r4 % 2 == 0);
                    r82++;
                }
            }
            r5++;
            if (r4 % symbolInfo.matrixHeight == symbolInfo.matrixHeight - 1) {
                int r83 = 0;
                for (int r63 = 0; r63 < symbolInfo.getSymbolWidth(); r63++) {
                    byteMatrix.set(r83, r5, true);
                    r83++;
                }
                r5++;
            }
        }
        return convertByteMatrixToBitMatrix(byteMatrix, r13, r14);
    }

    private static BitMatrix convertByteMatrixToBitMatrix(ByteMatrix byteMatrix, int r11, int r12) {
        BitMatrix bitMatrix;
        int width = byteMatrix.getWidth();
        int height = byteMatrix.getHeight();
        int max = Math.max(r11, width);
        int max2 = Math.max(r12, height);
        int min = Math.min(max / width, max2 / height);
        int r2 = (max - (width * min)) / 2;
        int r3 = (max2 - (height * min)) / 2;
        if (r12 < height || r11 < width) {
            bitMatrix = new BitMatrix(width, height);
            r2 = 0;
            r3 = 0;
        } else {
            bitMatrix = new BitMatrix(r11, r12);
        }
        bitMatrix.clear();
        int r112 = 0;
        while (r112 < height) {
            int r7 = r2;
            int r122 = 0;
            while (r122 < width) {
                if (byteMatrix.get(r122, r112) == 1) {
                    bitMatrix.setRegion(r7, r3, min, min);
                }
                r122++;
                r7 += min;
            }
            r112++;
            r3 += min;
        }
        return bitMatrix;
    }
}
