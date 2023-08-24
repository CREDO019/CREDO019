package com.google.zxing.pdf417.encoder;

import java.lang.reflect.Array;

/* loaded from: classes3.dex */
public final class BarcodeMatrix {
    private int currentRow;
    private final int height;
    private final BarcodeRow[] matrix;
    private final int width;

    /* JADX INFO: Access modifiers changed from: package-private */
    public BarcodeMatrix(int r6, int r7) {
        BarcodeRow[] barcodeRowArr = new BarcodeRow[r6];
        this.matrix = barcodeRowArr;
        int length = barcodeRowArr.length;
        for (int r1 = 0; r1 < length; r1++) {
            this.matrix[r1] = new BarcodeRow(((r7 + 4) * 17) + 1);
        }
        this.width = r7 * 17;
        this.height = r6;
        this.currentRow = -1;
    }

    void set(int r2, int r3, byte b) {
        this.matrix[r3].set(r2, b);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void startRow() {
        this.currentRow++;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public BarcodeRow getCurrentRow() {
        return this.matrix[this.currentRow];
    }

    public byte[][] getMatrix() {
        return getScaledMatrix(1, 1);
    }

    public byte[][] getScaledMatrix(int r8, int r9) {
        byte[][] bArr = (byte[][]) Array.newInstance(byte.class, this.height * r9, this.width * r8);
        int r2 = this.height * r9;
        for (int r1 = 0; r1 < r2; r1++) {
            bArr[(r2 - r1) - 1] = this.matrix[r1 / r9].getScaledRow(r8);
        }
        return bArr;
    }
}
