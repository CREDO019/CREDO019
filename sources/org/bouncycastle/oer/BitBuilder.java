package org.bouncycastle.oer;

import com.google.common.primitives.SignedBytes;
import java.io.IOException;
import java.io.OutputStream;
import java.math.BigInteger;
import org.bouncycastle.util.Arrays;

/* loaded from: classes5.dex */
public class BitBuilder {
    private static final byte[] bits = {Byte.MIN_VALUE, SignedBytes.MAX_POWER_OF_TWO, 32, 16, 8, 4, 2, 1};
    byte[] buf = new byte[1];
    int pos = 0;

    protected void finalize() throws Throwable {
        zero();
        super.finalize();
    }

    public void pad() {
        int r0 = this.pos;
        this.pos = r0 + (r0 % 8);
    }

    public int write(OutputStream outputStream) throws IOException {
        int r0 = this.pos;
        int r02 = (r0 + (r0 % 8)) / 8;
        outputStream.write(this.buf, 0, r02);
        outputStream.flush();
        return r02;
    }

    public void write7BitBytes(int r8) {
        boolean z = false;
        for (int r1 = 4; r1 >= 0; r1--) {
            if (!z && ((-33554432) & r8) != 0) {
                z = true;
            }
            if (z) {
                writeBit(r1).writeBits(r8, 32, 7);
            }
            r8 <<= 7;
        }
    }

    public void write7BitBytes(BigInteger bigInteger) {
        int r3;
        int bitLength = (bigInteger.bitLength() + (bigInteger.bitLength() % 8)) / 8;
        BigInteger shiftLeft = BigInteger.valueOf(254L).shiftLeft(bitLength * 8);
        boolean z = false;
        while (bitLength >= 0) {
            if (!z && bigInteger.and(shiftLeft).compareTo(BigInteger.ZERO) != 0) {
                z = true;
            }
            if (z) {
                writeBit(bitLength).writeBits(bigInteger.and(shiftLeft).shiftRight(r3 - 8).intValue(), 8, 7);
            }
            bigInteger = bigInteger.shiftLeft(7);
            bitLength--;
        }
    }

    public int writeAndClear(OutputStream outputStream) throws IOException {
        int r0 = this.pos;
        int r02 = (r0 + (r0 % 8)) / 8;
        outputStream.write(this.buf, 0, r02);
        outputStream.flush();
        zero();
        return r02;
    }

    public BitBuilder writeBit(int r5) {
        int r0 = this.pos;
        int r1 = r0 / 8;
        byte[] bArr = this.buf;
        if (r1 >= bArr.length) {
            byte[] bArr2 = new byte[bArr.length + 4];
            System.arraycopy(bArr, 0, bArr2, 0, r0 / 8);
            Arrays.clear(this.buf);
            this.buf = bArr2;
        }
        if (r5 == 0) {
            byte[] bArr3 = this.buf;
            int r02 = this.pos;
            int r12 = r02 / 8;
            bArr3[r12] = (byte) ((~bits[r02 % 8]) & bArr3[r12]);
        } else {
            byte[] bArr4 = this.buf;
            int r03 = this.pos;
            int r13 = r03 / 8;
            bArr4[r13] = (byte) (bits[r03 % 8] | bArr4[r13]);
        }
        this.pos++;
        return this;
    }

    public BitBuilder writeBits(long j, int r9) {
        for (int r92 = r9 - 1; r92 >= 0; r92--) {
            writeBit(((1 << r92) & j) > 0 ? 1 : 0);
        }
        return this;
    }

    public BitBuilder writeBits(long j, int r9, int r10) {
        for (int r0 = r9 - 1; r0 >= r9 - r10; r0--) {
            writeBit(((1 << r0) & j) != 0 ? 1 : 0);
        }
        return this;
    }

    public void zero() {
        Arrays.clear(this.buf);
        this.pos = 0;
    }
}
