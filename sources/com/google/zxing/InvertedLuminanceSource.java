package com.google.zxing;

/* loaded from: classes3.dex */
public final class InvertedLuminanceSource extends LuminanceSource {
    private final LuminanceSource delegate;

    public InvertedLuminanceSource(LuminanceSource luminanceSource) {
        super(luminanceSource.getWidth(), luminanceSource.getHeight());
        this.delegate = luminanceSource;
    }

    @Override // com.google.zxing.LuminanceSource
    public byte[] getRow(int r3, byte[] bArr) {
        byte[] row = this.delegate.getRow(r3, bArr);
        int width = getWidth();
        for (int r0 = 0; r0 < width; r0++) {
            row[r0] = (byte) (255 - (row[r0] & 255));
        }
        return row;
    }

    @Override // com.google.zxing.LuminanceSource
    public byte[] getMatrix() {
        byte[] matrix = this.delegate.getMatrix();
        int width = getWidth() * getHeight();
        byte[] bArr = new byte[width];
        for (int r3 = 0; r3 < width; r3++) {
            bArr[r3] = (byte) (255 - (matrix[r3] & 255));
        }
        return bArr;
    }

    @Override // com.google.zxing.LuminanceSource
    public boolean isCropSupported() {
        return this.delegate.isCropSupported();
    }

    @Override // com.google.zxing.LuminanceSource
    public LuminanceSource crop(int r3, int r4, int r5, int r6) {
        return new InvertedLuminanceSource(this.delegate.crop(r3, r4, r5, r6));
    }

    @Override // com.google.zxing.LuminanceSource
    public boolean isRotateSupported() {
        return this.delegate.isRotateSupported();
    }

    @Override // com.google.zxing.LuminanceSource
    public LuminanceSource invert() {
        return this.delegate;
    }

    @Override // com.google.zxing.LuminanceSource
    public LuminanceSource rotateCounterClockwise() {
        return new InvertedLuminanceSource(this.delegate.rotateCounterClockwise());
    }

    @Override // com.google.zxing.LuminanceSource
    public LuminanceSource rotateCounterClockwise45() {
        return new InvertedLuminanceSource(this.delegate.rotateCounterClockwise45());
    }
}
