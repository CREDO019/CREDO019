package com.google.android.exoplayer2.util;

import androidx.core.view.MotionEventCompat;
import com.google.common.base.Charsets;
import java.nio.charset.Charset;

/* loaded from: classes2.dex */
public final class ParsableBitArray {
    private int bitOffset;
    private int byteLimit;
    private int byteOffset;
    public byte[] data;

    public ParsableBitArray() {
        this.data = Util.EMPTY_BYTE_ARRAY;
    }

    public ParsableBitArray(byte[] bArr) {
        this(bArr, bArr.length);
    }

    public ParsableBitArray(byte[] bArr, int r2) {
        this.data = bArr;
        this.byteLimit = r2;
    }

    public void reset(byte[] bArr) {
        reset(bArr, bArr.length);
    }

    public void reset(ParsableByteArray parsableByteArray) {
        reset(parsableByteArray.getData(), parsableByteArray.limit());
        setPosition(parsableByteArray.getPosition() * 8);
    }

    public void reset(byte[] bArr, int r2) {
        this.data = bArr;
        this.byteOffset = 0;
        this.bitOffset = 0;
        this.byteLimit = r2;
    }

    public int bitsLeft() {
        return ((this.byteLimit - this.byteOffset) * 8) - this.bitOffset;
    }

    public int getPosition() {
        return (this.byteOffset * 8) + this.bitOffset;
    }

    public int getBytePosition() {
        Assertions.checkState(this.bitOffset == 0);
        return this.byteOffset;
    }

    public void setPosition(int r2) {
        int r0 = r2 / 8;
        this.byteOffset = r0;
        this.bitOffset = r2 - (r0 * 8);
        assertValidOffset();
    }

    public void skipBit() {
        int r0 = this.bitOffset + 1;
        this.bitOffset = r0;
        if (r0 == 8) {
            this.bitOffset = 0;
            this.byteOffset++;
        }
        assertValidOffset();
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

    public boolean readBit() {
        boolean z = (this.data[this.byteOffset] & (128 >> this.bitOffset)) != 0;
        skipBit();
        return z;
    }

    public int readBits(int r8) {
        int r2;
        if (r8 == 0) {
            return 0;
        }
        this.bitOffset += r8;
        int r1 = 0;
        while (true) {
            r2 = this.bitOffset;
            if (r2 <= 8) {
                break;
            }
            int r22 = r2 - 8;
            this.bitOffset = r22;
            byte[] bArr = this.data;
            int r4 = this.byteOffset;
            this.byteOffset = r4 + 1;
            r1 |= (bArr[r4] & 255) << r22;
        }
        byte[] bArr2 = this.data;
        int r5 = this.byteOffset;
        int r82 = ((-1) >>> (32 - r8)) & (r1 | ((bArr2[r5] & 255) >> (8 - r2)));
        if (r2 == 8) {
            this.bitOffset = 0;
            this.byteOffset = r5 + 1;
        }
        assertValidOffset();
        return r82;
    }

    public long readBitsToLong(int r3) {
        if (r3 <= 32) {
            return Util.toUnsignedLong(readBits(r3));
        }
        return Util.toLong(readBits(r3 - 32), readBits(32));
    }

    public void readBits(byte[] bArr, int r9, int r10) {
        int r0 = (r10 >> 3) + r9;
        while (r9 < r0) {
            byte[] bArr2 = this.data;
            int r4 = this.byteOffset;
            int r5 = r4 + 1;
            this.byteOffset = r5;
            byte b = bArr2[r4];
            int r6 = this.bitOffset;
            bArr[r9] = (byte) (b << r6);
            bArr[r9] = (byte) (((255 & bArr2[r5]) >> (8 - r6)) | bArr[r9]);
            r9++;
        }
        int r92 = r10 & 7;
        if (r92 == 0) {
            return;
        }
        bArr[r0] = (byte) (bArr[r0] & (255 >> r92));
        int r102 = this.bitOffset;
        if (r102 + r92 > 8) {
            int r3 = bArr[r0];
            byte[] bArr3 = this.data;
            int r52 = this.byteOffset;
            this.byteOffset = r52 + 1;
            bArr[r0] = (byte) (r3 | ((bArr3[r52] & 255) << r102));
            this.bitOffset = r102 - 8;
        }
        int r103 = this.bitOffset + r92;
        this.bitOffset = r103;
        byte[] bArr4 = this.data;
        int r42 = this.byteOffset;
        bArr[r0] = (byte) (((byte) (((255 & bArr4[r42]) >> (8 - r103)) << (8 - r92))) | bArr[r0]);
        if (r103 == 8) {
            this.bitOffset = 0;
            this.byteOffset = r42 + 1;
        }
        assertValidOffset();
    }

    public void byteAlign() {
        if (this.bitOffset == 0) {
            return;
        }
        this.bitOffset = 0;
        this.byteOffset++;
        assertValidOffset();
    }

    public void readBytes(byte[] bArr, int r4, int r5) {
        Assertions.checkState(this.bitOffset == 0);
        System.arraycopy(this.data, this.byteOffset, bArr, r4, r5);
        this.byteOffset += r5;
        assertValidOffset();
    }

    public void skipBytes(int r2) {
        Assertions.checkState(this.bitOffset == 0);
        this.byteOffset += r2;
        assertValidOffset();
    }

    public String readBytesAsString(int r2) {
        return readBytesAsString(r2, Charsets.UTF_8);
    }

    public String readBytesAsString(int r3, Charset charset) {
        byte[] bArr = new byte[r3];
        readBytes(bArr, 0, r3);
        return new String(bArr, charset);
    }

    public void putInt(int r9, int r10) {
        if (r10 < 32) {
            r9 &= (1 << r10) - 1;
        }
        int min = Math.min(8 - this.bitOffset, r10);
        int r3 = this.bitOffset;
        int r4 = (8 - r3) - min;
        int r32 = (MotionEventCompat.ACTION_POINTER_INDEX_MASK >> r3) | ((1 << r4) - 1);
        byte[] bArr = this.data;
        int r6 = this.byteOffset;
        bArr[r6] = (byte) (r32 & bArr[r6]);
        int r1 = r10 - min;
        bArr[r6] = (byte) (((r9 >>> r1) << r4) | bArr[r6]);
        int r62 = r6 + 1;
        while (r1 > 8) {
            this.data[r62] = (byte) (r9 >>> (r1 - 8));
            r1 -= 8;
            r62++;
        }
        int r2 = 8 - r1;
        byte[] bArr2 = this.data;
        bArr2[r62] = (byte) (bArr2[r62] & ((1 << r2) - 1));
        bArr2[r62] = (byte) (((r9 & ((1 << r1) - 1)) << r2) | bArr2[r62]);
        skipBits(r10);
        assertValidOffset();
    }

    private void assertValidOffset() {
        int r1;
        int r0 = this.byteOffset;
        Assertions.checkState(r0 >= 0 && (r0 < (r1 = this.byteLimit) || (r0 == r1 && this.bitOffset == 0)));
    }
}
