package com.google.zxing.qrcode.encoder;

import java.lang.reflect.Array;
import java.util.Arrays;

/* loaded from: classes3.dex */
public final class ByteMatrix {
    private final byte[][] bytes;
    private final int height;
    private final int width;

    public ByteMatrix(int r3, int r4) {
        this.bytes = (byte[][]) Array.newInstance(byte.class, r4, r3);
        this.width = r3;
        this.height = r4;
    }

    public int getHeight() {
        return this.height;
    }

    public int getWidth() {
        return this.width;
    }

    public byte get(int r2, int r3) {
        return this.bytes[r3][r2];
    }

    public byte[][] getArray() {
        return this.bytes;
    }

    public void set(int r2, int r3, byte b) {
        this.bytes[r3][r2] = b;
    }

    public void set(int r2, int r3, int r4) {
        this.bytes[r3][r2] = (byte) r4;
    }

    public void set(int r2, int r3, boolean z) {
        this.bytes[r3][r2] = z ? (byte) 1 : (byte) 0;
    }

    public void clear(byte b) {
        for (byte[] bArr : this.bytes) {
            Arrays.fill(bArr, b);
        }
    }

    public String toString() {
        StringBuilder sb = new StringBuilder((this.width * 2 * this.height) + 2);
        for (int r2 = 0; r2 < this.height; r2++) {
            byte[] bArr = this.bytes[r2];
            for (int r4 = 0; r4 < this.width; r4++) {
                byte b = bArr[r4];
                if (b == 0) {
                    sb.append(" 0");
                } else if (b == 1) {
                    sb.append(" 1");
                } else {
                    sb.append("  ");
                }
            }
            sb.append('\n');
        }
        return sb.toString();
    }
}
