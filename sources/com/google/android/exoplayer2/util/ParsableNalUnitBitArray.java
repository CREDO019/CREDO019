package com.google.android.exoplayer2.util;

/* loaded from: classes2.dex */
public final class ParsableNalUnitBitArray {
    private int bitOffset;
    private int byteLimit;
    private int byteOffset;
    private byte[] data;

    public ParsableNalUnitBitArray(byte[] bArr, int r2, int r3) {
        reset(bArr, r2, r3);
    }

    public void reset(byte[] bArr, int r2, int r3) {
        this.data = bArr;
        this.byteOffset = r2;
        this.byteLimit = r3;
        this.bitOffset = 0;
        assertValidOffset();
    }

    public void skipBit() {
        int r0 = this.bitOffset + 1;
        this.bitOffset = r0;
        if (r0 == 8) {
            this.bitOffset = 0;
            int r02 = this.byteOffset;
            this.byteOffset = r02 + (shouldSkipByte(r02 + 1) ? 2 : 1);
        }
        assertValidOffset();
    }

    public void skipBits(int r5) {
        int r0 = this.byteOffset;
        int r1 = r5 / 8;
        int r2 = r0 + r1;
        this.byteOffset = r2;
        int r3 = this.bitOffset + (r5 - (r1 * 8));
        this.bitOffset = r3;
        if (r3 > 7) {
            this.byteOffset = r2 + 1;
            this.bitOffset = r3 - 8;
        }
        while (true) {
            r0++;
            if (r0 <= this.byteOffset) {
                if (shouldSkipByte(r0)) {
                    this.byteOffset++;
                    r0 += 2;
                }
            } else {
                assertValidOffset();
                return;
            }
        }
    }

    public boolean canReadBits(int r5) {
        int r0 = this.byteOffset;
        int r1 = r5 / 8;
        int r2 = r0 + r1;
        int r3 = (this.bitOffset + r5) - (r1 * 8);
        if (r3 > 7) {
            r2++;
            r3 -= 8;
        }
        while (true) {
            r0++;
            if (r0 > r2 || r2 >= this.byteLimit) {
                break;
            } else if (shouldSkipByte(r0)) {
                r2++;
                r0 += 2;
            }
        }
        int r02 = this.byteLimit;
        if (r2 >= r02) {
            return r2 == r02 && r3 == 0;
        }
        return true;
    }

    public boolean readBit() {
        boolean z = (this.data[this.byteOffset] & (128 >> this.bitOffset)) != 0;
        skipBit();
        return z;
    }

    public int readBits(int r10) {
        int r2;
        this.bitOffset += r10;
        int r1 = 0;
        while (true) {
            r2 = this.bitOffset;
            if (r2 <= 8) {
                break;
            }
            int r22 = r2 - 8;
            this.bitOffset = r22;
            byte[] bArr = this.data;
            int r6 = this.byteOffset;
            r1 |= (bArr[r6] & 255) << r22;
            if (!shouldSkipByte(r6 + 1)) {
                r3 = 1;
            }
            this.byteOffset = r6 + r3;
        }
        byte[] bArr2 = this.data;
        int r7 = this.byteOffset;
        int r102 = ((-1) >>> (32 - r10)) & (r1 | ((bArr2[r7] & 255) >> (8 - r2)));
        if (r2 == 8) {
            this.bitOffset = 0;
            this.byteOffset = r7 + (shouldSkipByte(r7 + 1) ? 2 : 1);
        }
        assertValidOffset();
        return r102;
    }

    public boolean canReadExpGolombCodedNum() {
        int r0 = this.byteOffset;
        int r1 = this.bitOffset;
        int r3 = 0;
        while (this.byteOffset < this.byteLimit && !readBit()) {
            r3++;
        }
        boolean z = this.byteOffset == this.byteLimit;
        this.byteOffset = r0;
        this.bitOffset = r1;
        return !z && canReadBits((r3 * 2) + 1);
    }

    public int readUnsignedExpGolombCodedInt() {
        return readExpGolombCodeNum();
    }

    public int readSignedExpGolombCodedInt() {
        int readExpGolombCodeNum = readExpGolombCodeNum();
        return (readExpGolombCodeNum % 2 == 0 ? -1 : 1) * ((readExpGolombCodeNum + 1) / 2);
    }

    private int readExpGolombCodeNum() {
        int r1 = 0;
        while (!readBit()) {
            r1++;
        }
        return ((1 << r1) - 1) + (r1 > 0 ? readBits(r1) : 0);
    }

    private boolean shouldSkipByte(int r5) {
        if (2 <= r5 && r5 < this.byteLimit) {
            byte[] bArr = this.data;
            if (bArr[r5] == 3 && bArr[r5 - 2] == 0 && bArr[r5 - 1] == 0) {
                return true;
            }
        }
        return false;
    }

    private void assertValidOffset() {
        int r1;
        int r0 = this.byteOffset;
        Assertions.checkState(r0 >= 0 && (r0 < (r1 = this.byteLimit) || (r0 == r1 && this.bitOffset == 0)));
    }
}
