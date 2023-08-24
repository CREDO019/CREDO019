package com.google.zxing;

/* loaded from: classes3.dex */
public abstract class LuminanceSource {
    private final int height;
    private final int width;

    public abstract byte[] getMatrix();

    public abstract byte[] getRow(int r1, byte[] bArr);

    public boolean isCropSupported() {
        return false;
    }

    public boolean isRotateSupported() {
        return false;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public LuminanceSource(int r1, int r2) {
        this.width = r1;
        this.height = r2;
    }

    public final int getWidth() {
        return this.width;
    }

    public final int getHeight() {
        return this.height;
    }

    public LuminanceSource crop(int r1, int r2, int r3, int r4) {
        throw new UnsupportedOperationException("This luminance source does not support cropping.");
    }

    public LuminanceSource invert() {
        return new InvertedLuminanceSource(this);
    }

    public LuminanceSource rotateCounterClockwise() {
        throw new UnsupportedOperationException("This luminance source does not support rotation by 90 degrees.");
    }

    public LuminanceSource rotateCounterClockwise45() {
        throw new UnsupportedOperationException("This luminance source does not support rotation by 45 degrees.");
    }

    public final String toString() {
        int r0 = this.width;
        byte[] bArr = new byte[r0];
        StringBuilder sb = new StringBuilder(this.height * (r0 + 1));
        for (int r3 = 0; r3 < this.height; r3++) {
            bArr = getRow(r3, bArr);
            for (int r4 = 0; r4 < this.width; r4++) {
                int r5 = bArr[r4] & 255;
                sb.append(r5 < 64 ? '#' : r5 < 128 ? '+' : r5 < 192 ? '.' : ' ');
            }
            sb.append('\n');
        }
        return sb.toString();
    }
}
