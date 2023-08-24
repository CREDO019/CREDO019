package com.google.zxing;

/* loaded from: classes3.dex */
public final class RGBLuminanceSource extends LuminanceSource {
    private final int dataHeight;
    private final int dataWidth;
    private final int left;
    private final byte[] luminances;
    private final int top;

    @Override // com.google.zxing.LuminanceSource
    public boolean isCropSupported() {
        return true;
    }

    public RGBLuminanceSource(int r5, int r6, int[] r7) {
        super(r5, r6);
        this.dataWidth = r5;
        this.dataHeight = r6;
        this.left = 0;
        this.top = 0;
        int r52 = r5 * r6;
        this.luminances = new byte[r52];
        for (int r0 = 0; r0 < r52; r0++) {
            int r62 = r7[r0];
            this.luminances[r0] = (byte) (((((r62 >> 16) & 255) + ((r62 >> 7) & 510)) + (r62 & 255)) / 4);
        }
    }

    private RGBLuminanceSource(byte[] bArr, int r2, int r3, int r4, int r5, int r6, int r7) {
        super(r6, r7);
        if (r6 + r4 > r2 || r7 + r5 > r3) {
            throw new IllegalArgumentException("Crop rectangle does not fit within image data.");
        }
        this.luminances = bArr;
        this.dataWidth = r2;
        this.dataHeight = r3;
        this.left = r4;
        this.top = r5;
    }

    @Override // com.google.zxing.LuminanceSource
    public byte[] getRow(int r4, byte[] bArr) {
        if (r4 < 0 || r4 >= getHeight()) {
            throw new IllegalArgumentException("Requested row is outside the image: ".concat(String.valueOf(r4)));
        }
        int width = getWidth();
        if (bArr == null || bArr.length < width) {
            bArr = new byte[width];
        }
        System.arraycopy(this.luminances, ((r4 + this.top) * this.dataWidth) + this.left, bArr, 0, width);
        return bArr;
    }

    @Override // com.google.zxing.LuminanceSource
    public byte[] getMatrix() {
        int width = getWidth();
        int height = getHeight();
        int r2 = this.dataWidth;
        if (width == r2 && height == this.dataHeight) {
            return this.luminances;
        }
        int r3 = width * height;
        byte[] bArr = new byte[r3];
        int r5 = (this.top * r2) + this.left;
        if (width == r2) {
            System.arraycopy(this.luminances, r5, bArr, 0, r3);
            return bArr;
        }
        for (int r6 = 0; r6 < height; r6++) {
            System.arraycopy(this.luminances, r5, bArr, r6 * width, width);
            r5 += this.dataWidth;
        }
        return bArr;
    }

    @Override // com.google.zxing.LuminanceSource
    public LuminanceSource crop(int r10, int r11, int r12, int r13) {
        return new RGBLuminanceSource(this.luminances, this.dataWidth, this.dataHeight, this.left + r10, this.top + r11, r12, r13);
    }
}
