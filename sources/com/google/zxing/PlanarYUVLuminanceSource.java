package com.google.zxing;

import androidx.core.view.ViewCompat;

/* loaded from: classes3.dex */
public final class PlanarYUVLuminanceSource extends LuminanceSource {
    private static final int THUMBNAIL_SCALE_FACTOR = 2;
    private final int dataHeight;
    private final int dataWidth;
    private final int left;
    private final int top;
    private final byte[] yuvData;

    @Override // com.google.zxing.LuminanceSource
    public boolean isCropSupported() {
        return true;
    }

    public PlanarYUVLuminanceSource(byte[] bArr, int r3, int r4, int r5, int r6, int r7, int r8, boolean z) {
        super(r7, r8);
        if (r5 + r7 > r3 || r6 + r8 > r4) {
            throw new IllegalArgumentException("Crop rectangle does not fit within image data.");
        }
        this.yuvData = bArr;
        this.dataWidth = r3;
        this.dataHeight = r4;
        this.left = r5;
        this.top = r6;
        if (z) {
            reverseHorizontal(r7, r8);
        }
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
        System.arraycopy(this.yuvData, ((r4 + this.top) * this.dataWidth) + this.left, bArr, 0, width);
        return bArr;
    }

    @Override // com.google.zxing.LuminanceSource
    public byte[] getMatrix() {
        int width = getWidth();
        int height = getHeight();
        int r2 = this.dataWidth;
        if (width == r2 && height == this.dataHeight) {
            return this.yuvData;
        }
        int r3 = width * height;
        byte[] bArr = new byte[r3];
        int r5 = (this.top * r2) + this.left;
        if (width == r2) {
            System.arraycopy(this.yuvData, r5, bArr, 0, r3);
            return bArr;
        }
        for (int r6 = 0; r6 < height; r6++) {
            System.arraycopy(this.yuvData, r5, bArr, r6 * width, width);
            r5 += this.dataWidth;
        }
        return bArr;
    }

    @Override // com.google.zxing.LuminanceSource
    public LuminanceSource crop(int r11, int r12, int r13, int r14) {
        return new PlanarYUVLuminanceSource(this.yuvData, this.dataWidth, this.dataHeight, this.left + r11, this.top + r12, r13, r14, false);
    }

    public int[] renderThumbnail() {
        int width = getWidth() / 2;
        int height = getHeight() / 2;
        int[] r2 = new int[width * height];
        byte[] bArr = this.yuvData;
        int r4 = (this.top * this.dataWidth) + this.left;
        for (int r6 = 0; r6 < height; r6++) {
            int r7 = r6 * width;
            for (int r8 = 0; r8 < width; r8++) {
                r2[r7 + r8] = ((bArr[(r8 << 1) + r4] & 255) * 65793) | ViewCompat.MEASURED_STATE_MASK;
            }
            r4 += this.dataWidth << 1;
        }
        return r2;
    }

    public int getThumbnailWidth() {
        return getWidth() / 2;
    }

    public int getThumbnailHeight() {
        return getHeight() / 2;
    }

    private void reverseHorizontal(int r9, int r10) {
        byte[] bArr = this.yuvData;
        int r1 = (this.top * this.dataWidth) + this.left;
        int r2 = 0;
        while (r2 < r10) {
            int r3 = (r9 / 2) + r1;
            int r4 = (r1 + r9) - 1;
            int r5 = r1;
            while (r5 < r3) {
                byte b = bArr[r5];
                bArr[r5] = bArr[r4];
                bArr[r4] = b;
                r5++;
                r4--;
            }
            r2++;
            r1 += this.dataWidth;
        }
    }
}
