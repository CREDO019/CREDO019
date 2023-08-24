package org.bouncycastle.crypto.util;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.math.BigInteger;
import org.bouncycastle.util.Strings;

/* loaded from: classes5.dex */
class SSHBuilder {
    private final ByteArrayOutputStream bos = new ByteArrayOutputStream();

    public byte[] getBytes() {
        return this.bos.toByteArray();
    }

    public byte[] getPaddedBytes() {
        return getPaddedBytes(8);
    }

    public byte[] getPaddedBytes(int r3) {
        int size = this.bos.size() % r3;
        if (size != 0) {
            int r32 = r3 - size;
            for (int r0 = 1; r0 <= r32; r0++) {
                this.bos.write(r0);
            }
        }
        return this.bos.toByteArray();
    }

    public void u32(int r3) {
        this.bos.write((r3 >>> 24) & 255);
        this.bos.write((r3 >>> 16) & 255);
        this.bos.write((r3 >>> 8) & 255);
        this.bos.write(r3 & 255);
    }

    public void writeBigNum(BigInteger bigInteger) {
        writeBlock(bigInteger.toByteArray());
    }

    public void writeBlock(byte[] bArr) {
        u32(bArr.length);
        try {
            this.bos.write(bArr);
        } catch (IOException e) {
            throw new IllegalStateException(e.getMessage(), e);
        }
    }

    public void writeBytes(byte[] bArr) {
        try {
            this.bos.write(bArr);
        } catch (IOException e) {
            throw new IllegalStateException(e.getMessage(), e);
        }
    }

    public void writeString(String str) {
        writeBlock(Strings.toByteArray(str));
    }
}
