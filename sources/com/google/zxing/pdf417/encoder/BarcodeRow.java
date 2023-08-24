package com.google.zxing.pdf417.encoder;

/* loaded from: classes3.dex */
final class BarcodeRow {
    private int currentLocation = 0;
    private final byte[] row;

    /* JADX INFO: Access modifiers changed from: package-private */
    public BarcodeRow(int r1) {
        this.row = new byte[r1];
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void set(int r2, byte b) {
        this.row[r2] = b;
    }

    private void set(int r2, boolean z) {
        this.row[r2] = z ? (byte) 1 : (byte) 0;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void addBar(boolean z, int r5) {
        for (int r0 = 0; r0 < r5; r0++) {
            int r1 = this.currentLocation;
            this.currentLocation = r1 + 1;
            set(r1, z);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public byte[] getScaledRow(int r6) {
        int length = this.row.length * r6;
        byte[] bArr = new byte[length];
        for (int r2 = 0; r2 < length; r2++) {
            bArr[r2] = this.row[r2 / r6];
        }
        return bArr;
    }
}
