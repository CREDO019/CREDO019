package com.google.zxing.pdf417.decoder;

/* loaded from: classes3.dex */
final class Codeword {
    private static final int BARCODE_ROW_UNKNOWN = -1;
    private final int bucket;
    private final int endX;
    private int rowNumber = -1;
    private final int startX;
    private final int value;

    /* JADX INFO: Access modifiers changed from: package-private */
    public Codeword(int r2, int r3, int r4, int r5) {
        this.startX = r2;
        this.endX = r3;
        this.bucket = r4;
        this.value = r5;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public boolean hasValidRowNumber() {
        return isValidRowNumber(this.rowNumber);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public boolean isValidRowNumber(int r2) {
        return r2 != -1 && this.bucket == (r2 % 3) * 3;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void setRowNumberAsRowIndicatorColumn() {
        this.rowNumber = ((this.value / 30) * 3) + (this.bucket / 3);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public int getWidth() {
        return this.endX - this.startX;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public int getStartX() {
        return this.startX;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public int getEndX() {
        return this.endX;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public int getBucket() {
        return this.bucket;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public int getValue() {
        return this.value;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public int getRowNumber() {
        return this.rowNumber;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void setRowNumber(int r1) {
        this.rowNumber = r1;
    }

    public String toString() {
        return this.rowNumber + "|" + this.value;
    }
}
