package com.google.zxing.common;

/* loaded from: classes3.dex */
public final class BitSource {
    private int bitOffset;
    private int byteOffset;
    private final byte[] bytes;

    public BitSource(byte[] bArr) {
        this.bytes = bArr;
    }

    public int getBitOffset() {
        return this.bitOffset;
    }

    public int getByteOffset() {
        return this.byteOffset;
    }

    public int readBits(int r10) {
        if (r10 <= 0 || r10 > 32 || r10 > available()) {
            throw new IllegalArgumentException(String.valueOf(r10));
        }
        int r0 = this.bitOffset;
        int r1 = 0;
        if (r0 > 0) {
            int r4 = 8 - r0;
            int r5 = r10 < r4 ? r10 : r4;
            int r42 = r4 - r5;
            byte[] bArr = this.bytes;
            int r8 = this.byteOffset;
            int r43 = (((255 >> (8 - r5)) << r42) & bArr[r8]) >> r42;
            r10 -= r5;
            int r02 = r0 + r5;
            this.bitOffset = r02;
            if (r02 == 8) {
                this.bitOffset = 0;
                this.byteOffset = r8 + 1;
            }
            r1 = r43;
        }
        if (r10 > 0) {
            while (r10 >= 8) {
                int r03 = r1 << 8;
                byte[] bArr2 = this.bytes;
                int r44 = this.byteOffset;
                r1 = (bArr2[r44] & 255) | r03;
                this.byteOffset = r44 + 1;
                r10 -= 8;
            }
            if (r10 > 0) {
                int r04 = 8 - r10;
                int r12 = (r1 << r10) | ((((255 >> r04) << r04) & this.bytes[this.byteOffset]) >> r04);
                this.bitOffset += r10;
                return r12;
            }
            return r1;
        }
        return r1;
    }

    public int available() {
        return ((this.bytes.length - this.byteOffset) * 8) - this.bitOffset;
    }
}
