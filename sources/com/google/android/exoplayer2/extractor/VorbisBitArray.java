package com.google.android.exoplayer2.extractor;

import com.google.android.exoplayer2.util.Assertions;

/* loaded from: classes2.dex */
public final class VorbisBitArray {
    private int bitOffset;
    private final int byteLimit;
    private int byteOffset;
    private final byte[] data;

    public VorbisBitArray(byte[] bArr) {
        this.data = bArr;
        this.byteLimit = bArr.length;
    }

    public void reset() {
        this.byteOffset = 0;
        this.bitOffset = 0;
    }

    public boolean readBit() {
        boolean z = (((this.data[this.byteOffset] & 255) >> this.bitOffset) & 1) == 1;
        skipBits(1);
        return z;
    }

    public int readBits(int r7) {
        int r0 = this.byteOffset;
        int min = Math.min(r7, 8 - this.bitOffset);
        int r3 = r0 + 1;
        int r02 = ((this.data[r0] & 255) >> this.bitOffset) & (255 >> (8 - min));
        while (min < r7) {
            r02 |= (this.data[r3] & 255) << min;
            min += 8;
            r3++;
        }
        int r03 = r02 & ((-1) >>> (32 - r7));
        skipBits(r7);
        return r03;
    }

    public void skipBits(int r4) {
        int r0 = r4 / 8;
        int r1 = this.byteOffset + r0;
        this.byteOffset = r1;
        int r2 = this.bitOffset + (r4 - (r0 * 8));
        this.bitOffset = r2;
        if (r2 > 7) {
            this.byteOffset = r1 + 1;
            this.bitOffset = r2 - 8;
        }
        assertValidOffset();
    }

    public int getPosition() {
        return (this.byteOffset * 8) + this.bitOffset;
    }

    public void setPosition(int r2) {
        int r0 = r2 / 8;
        this.byteOffset = r0;
        this.bitOffset = r2 - (r0 * 8);
        assertValidOffset();
    }

    public int bitsLeft() {
        return ((this.byteLimit - this.byteOffset) * 8) - this.bitOffset;
    }

    private void assertValidOffset() {
        int r1;
        int r0 = this.byteOffset;
        Assertions.checkState(r0 >= 0 && (r0 < (r1 = this.byteLimit) || (r0 == r1 && this.bitOffset == 0)));
    }
}
