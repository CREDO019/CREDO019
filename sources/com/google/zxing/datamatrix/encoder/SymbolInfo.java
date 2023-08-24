package com.google.zxing.datamatrix.encoder;

import com.canhub.cropper.CropImage;
import com.google.zxing.Dimension;

/* loaded from: classes3.dex */
public class SymbolInfo {
    static final SymbolInfo[] PROD_SYMBOLS;
    private static SymbolInfo[] symbols;
    private final int dataCapacity;
    private final int dataRegions;
    private final int errorCodewords;
    public final int matrixHeight;
    public final int matrixWidth;
    private final boolean rectangular;
    private final int rsBlockData;
    private final int rsBlockError;

    static {
        SymbolInfo[] symbolInfoArr = {new SymbolInfo(false, 3, 5, 8, 8, 1), new SymbolInfo(false, 5, 7, 10, 10, 1), new SymbolInfo(true, 5, 7, 16, 6, 1), new SymbolInfo(false, 8, 10, 12, 12, 1), new SymbolInfo(true, 10, 11, 14, 6, 2), new SymbolInfo(false, 12, 12, 14, 14, 1), new SymbolInfo(true, 16, 14, 24, 10, 1), new SymbolInfo(false, 18, 14, 16, 16, 1), new SymbolInfo(false, 22, 18, 18, 18, 1), new SymbolInfo(true, 22, 18, 16, 10, 2), new SymbolInfo(false, 30, 20, 20, 20, 1), new SymbolInfo(true, 32, 24, 16, 14, 2), new SymbolInfo(false, 36, 24, 22, 22, 1), new SymbolInfo(false, 44, 28, 24, 24, 1), new SymbolInfo(true, 49, 28, 22, 14, 2), new SymbolInfo(false, 62, 36, 14, 14, 4), new SymbolInfo(false, 86, 42, 16, 16, 4), new SymbolInfo(false, 114, 48, 18, 18, 4), new SymbolInfo(false, 144, 56, 20, 20, 4), new SymbolInfo(false, 174, 68, 22, 22, 4), new SymbolInfo(false, CropImage.CROP_IMAGE_ACTIVITY_RESULT_ERROR_CODE, 84, 24, 24, 4, 102, 42), new SymbolInfo(false, 280, 112, 14, 14, 16, 140, 56), new SymbolInfo(false, 368, 144, 16, 16, 16, 92, 36), new SymbolInfo(false, 456, 192, 18, 18, 16, 114, 48), new SymbolInfo(false, 576, 224, 20, 20, 16, 144, 56), new SymbolInfo(false, 696, 272, 22, 22, 16, 174, 68), new SymbolInfo(false, 816, 336, 24, 24, 16, 136, 56), new SymbolInfo(false, 1050, 408, 18, 18, 36, 175, 68), new SymbolInfo(false, 1304, 496, 20, 20, 36, 163, 62), new DataMatrixSymbolInfo144()};
        PROD_SYMBOLS = symbolInfoArr;
        symbols = symbolInfoArr;
    }

    public static void overrideSymbolSet(SymbolInfo[] symbolInfoArr) {
        symbols = symbolInfoArr;
    }

    public SymbolInfo(boolean z, int r11, int r12, int r13, int r14, int r15) {
        this(z, r11, r12, r13, r14, r15, r11, r12);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public SymbolInfo(boolean z, int r2, int r3, int r4, int r5, int r6, int r7, int r8) {
        this.rectangular = z;
        this.dataCapacity = r2;
        this.errorCodewords = r3;
        this.matrixWidth = r4;
        this.matrixHeight = r5;
        this.dataRegions = r6;
        this.rsBlockData = r7;
        this.rsBlockError = r8;
    }

    public static SymbolInfo lookup(int r2) {
        return lookup(r2, SymbolShapeHint.FORCE_NONE, true);
    }

    public static SymbolInfo lookup(int r1, SymbolShapeHint symbolShapeHint) {
        return lookup(r1, symbolShapeHint, true);
    }

    public static SymbolInfo lookup(int r0, boolean z, boolean z2) {
        return lookup(r0, z ? SymbolShapeHint.FORCE_NONE : SymbolShapeHint.FORCE_SQUARE, z2);
    }

    private static SymbolInfo lookup(int r1, SymbolShapeHint symbolShapeHint, boolean z) {
        return lookup(r1, symbolShapeHint, null, null, z);
    }

    public static SymbolInfo lookup(int r6, SymbolShapeHint symbolShapeHint, Dimension dimension, Dimension dimension2, boolean z) {
        SymbolInfo[] symbolInfoArr;
        for (SymbolInfo symbolInfo : symbols) {
            if (!(symbolShapeHint == SymbolShapeHint.FORCE_SQUARE && symbolInfo.rectangular) && ((symbolShapeHint != SymbolShapeHint.FORCE_RECTANGLE || symbolInfo.rectangular) && ((dimension == null || (symbolInfo.getSymbolWidth() >= dimension.getWidth() && symbolInfo.getSymbolHeight() >= dimension.getHeight())) && ((dimension2 == null || (symbolInfo.getSymbolWidth() <= dimension2.getWidth() && symbolInfo.getSymbolHeight() <= dimension2.getHeight())) && r6 <= symbolInfo.dataCapacity)))) {
                return symbolInfo;
            }
        }
        if (z) {
            throw new IllegalArgumentException("Can't find a symbol arrangement that matches the message. Data codewords: ".concat(String.valueOf(r6)));
        }
        return null;
    }

    private int getHorizontalDataRegions() {
        int r0 = this.dataRegions;
        int r1 = 1;
        if (r0 != 1) {
            r1 = 2;
            if (r0 != 2 && r0 != 4) {
                if (r0 != 16) {
                    if (r0 == 36) {
                        return 6;
                    }
                    throw new IllegalStateException("Cannot handle this number of data regions");
                }
                return 4;
            }
        }
        return r1;
    }

    private int getVerticalDataRegions() {
        int r0 = this.dataRegions;
        if (r0 == 1 || r0 == 2) {
            return 1;
        }
        if (r0 != 4) {
            if (r0 != 16) {
                if (r0 == 36) {
                    return 6;
                }
                throw new IllegalStateException("Cannot handle this number of data regions");
            }
            return 4;
        }
        return 2;
    }

    public final int getSymbolDataWidth() {
        return getHorizontalDataRegions() * this.matrixWidth;
    }

    public final int getSymbolDataHeight() {
        return getVerticalDataRegions() * this.matrixHeight;
    }

    public final int getSymbolWidth() {
        return getSymbolDataWidth() + (getHorizontalDataRegions() << 1);
    }

    public final int getSymbolHeight() {
        return getSymbolDataHeight() + (getVerticalDataRegions() << 1);
    }

    public int getCodewordCount() {
        return this.dataCapacity + this.errorCodewords;
    }

    public int getInterleavedBlockCount() {
        return this.dataCapacity / this.rsBlockData;
    }

    public final int getDataCapacity() {
        return this.dataCapacity;
    }

    public final int getErrorCodewords() {
        return this.errorCodewords;
    }

    public int getDataLengthForInterleavedBlock(int r1) {
        return this.rsBlockData;
    }

    public final int getErrorLengthForInterleavedBlock(int r1) {
        return this.rsBlockError;
    }

    public final String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(this.rectangular ? "Rectangular Symbol:" : "Square Symbol:");
        sb.append(" data region ");
        sb.append(this.matrixWidth);
        sb.append('x');
        sb.append(this.matrixHeight);
        sb.append(", symbol size ");
        sb.append(getSymbolWidth());
        sb.append('x');
        sb.append(getSymbolHeight());
        sb.append(", symbol data size ");
        sb.append(getSymbolDataWidth());
        sb.append('x');
        sb.append(getSymbolDataHeight());
        sb.append(", codewords ");
        sb.append(this.dataCapacity);
        sb.append('+');
        sb.append(this.errorCodewords);
        return sb.toString();
    }
}
