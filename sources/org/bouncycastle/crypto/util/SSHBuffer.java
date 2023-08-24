package org.bouncycastle.crypto.util;

import java.math.BigInteger;
import org.bouncycastle.util.Arrays;
import org.bouncycastle.util.Strings;

/* loaded from: classes5.dex */
class SSHBuffer {
    private final byte[] buffer;
    private int pos = 0;

    public SSHBuffer(byte[] bArr) {
        this.buffer = bArr;
    }

    public SSHBuffer(byte[] bArr, byte[] bArr2) {
        this.buffer = bArr2;
        for (int r0 = 0; r0 != bArr.length; r0++) {
            if (bArr[r0] != bArr2[r0]) {
                throw new IllegalArgumentException("magic-number incorrect");
            }
        }
        this.pos += bArr.length;
    }

    public byte[] getBuffer() {
        return Arrays.clone(this.buffer);
    }

    public boolean hasRemaining() {
        return this.pos < this.buffer.length;
    }

    public BigInteger readBigNumPositive() {
        int readU32 = readU32();
        int r1 = this.pos;
        int r2 = r1 + readU32;
        byte[] bArr = this.buffer;
        if (r2 <= bArr.length) {
            int r0 = readU32 + r1;
            this.pos = r0;
            return new BigInteger(1, Arrays.copyOfRange(bArr, r1, r0));
        }
        throw new IllegalArgumentException("not enough data for big num");
    }

    public byte[] readBlock() {
        int readU32 = readU32();
        if (readU32 == 0) {
            return new byte[0];
        }
        int r1 = this.pos;
        byte[] bArr = this.buffer;
        if (r1 <= bArr.length - readU32) {
            int r0 = readU32 + r1;
            this.pos = r0;
            return Arrays.copyOfRange(bArr, r1, r0);
        }
        throw new IllegalArgumentException("not enough data for block");
    }

    public byte[] readPaddedBlock() {
        return readPaddedBlock(8);
    }

    public byte[] readPaddedBlock(int r6) {
        int r0;
        int readU32 = readU32();
        if (readU32 == 0) {
            return new byte[0];
        }
        int r1 = this.pos;
        byte[] bArr = this.buffer;
        if (r1 <= bArr.length - readU32) {
            if (readU32 % r6 == 0) {
                int r3 = r1 + readU32;
                this.pos = r3;
                if (readU32 > 0 && (r0 = bArr[r3 - 1] & 255) > 0 && r0 < r6) {
                    r3 -= r0;
                    int r62 = 1;
                    int r2 = r3;
                    while (r62 <= r0) {
                        if (r62 != (this.buffer[r2] & 255)) {
                            throw new IllegalArgumentException("incorrect padding");
                        }
                        r62++;
                        r2++;
                    }
                }
                return Arrays.copyOfRange(this.buffer, r1, r3);
            }
            throw new IllegalArgumentException("missing padding");
        }
        throw new IllegalArgumentException("not enough data for block");
    }

    public String readString() {
        return Strings.fromByteArray(readBlock());
    }

    public int readU32() {
        int r0 = this.pos;
        byte[] bArr = this.buffer;
        if (r0 <= bArr.length - 4) {
            int r2 = r0 + 1;
            this.pos = r2;
            int r3 = r2 + 1;
            this.pos = r3;
            int r02 = ((bArr[r0] & 255) << 24) | ((bArr[r2] & 255) << 16);
            int r22 = r3 + 1;
            this.pos = r22;
            int r03 = r02 | ((bArr[r3] & 255) << 8);
            this.pos = r22 + 1;
            return r03 | (bArr[r22] & 255);
        }
        throw new IllegalArgumentException("4 bytes for U32 exceeds buffer.");
    }

    public void skipBlock() {
        int readU32 = readU32();
        int r1 = this.pos;
        if (r1 > this.buffer.length - readU32) {
            throw new IllegalArgumentException("not enough data for block");
        }
        this.pos = r1 + readU32;
    }
}
