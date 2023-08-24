package com.google.android.exoplayer2.util;

import com.google.common.base.Charsets;
import java.nio.ByteBuffer;
import java.nio.charset.Charset;
import java.util.Arrays;
import okio.Utf8;

/* loaded from: classes2.dex */
public final class ParsableByteArray {
    private byte[] data;
    private int limit;
    private int position;

    public ParsableByteArray() {
        this.data = Util.EMPTY_BYTE_ARRAY;
    }

    public ParsableByteArray(int r2) {
        this.data = new byte[r2];
        this.limit = r2;
    }

    public ParsableByteArray(byte[] bArr) {
        this.data = bArr;
        this.limit = bArr.length;
    }

    public ParsableByteArray(byte[] bArr, int r2) {
        this.data = bArr;
        this.limit = r2;
    }

    public void reset(int r2) {
        reset(capacity() < r2 ? new byte[r2] : this.data, r2);
    }

    public void reset(byte[] bArr) {
        reset(bArr, bArr.length);
    }

    public void reset(byte[] bArr, int r2) {
        this.data = bArr;
        this.limit = r2;
        this.position = 0;
    }

    public void ensureCapacity(int r2) {
        if (r2 > capacity()) {
            this.data = Arrays.copyOf(this.data, r2);
        }
    }

    public int bytesLeft() {
        return this.limit - this.position;
    }

    public int limit() {
        return this.limit;
    }

    public void setLimit(int r2) {
        Assertions.checkArgument(r2 >= 0 && r2 <= this.data.length);
        this.limit = r2;
    }

    public int getPosition() {
        return this.position;
    }

    public void setPosition(int r2) {
        Assertions.checkArgument(r2 >= 0 && r2 <= this.limit);
        this.position = r2;
    }

    public byte[] getData() {
        return this.data;
    }

    public int capacity() {
        return this.data.length;
    }

    public void skipBytes(int r2) {
        setPosition(this.position + r2);
    }

    public void readBytes(ParsableBitArray parsableBitArray, int r4) {
        readBytes(parsableBitArray.data, 0, r4);
        parsableBitArray.setPosition(0);
    }

    public void readBytes(byte[] bArr, int r4, int r5) {
        System.arraycopy(this.data, this.position, bArr, r4, r5);
        this.position += r5;
    }

    public void readBytes(ByteBuffer byteBuffer, int r4) {
        byteBuffer.put(this.data, this.position, r4);
        this.position += r4;
    }

    public int peekUnsignedByte() {
        return this.data[this.position] & 255;
    }

    public char peekChar() {
        byte[] bArr = this.data;
        int r1 = this.position;
        return (char) ((bArr[r1 + 1] & 255) | ((bArr[r1] & 255) << 8));
    }

    public int readUnsignedByte() {
        byte[] bArr = this.data;
        int r1 = this.position;
        this.position = r1 + 1;
        return bArr[r1] & 255;
    }

    public int readUnsignedShort() {
        byte[] bArr = this.data;
        int r1 = this.position;
        int r2 = r1 + 1;
        this.position = r2;
        this.position = r2 + 1;
        return (bArr[r2] & 255) | ((bArr[r1] & 255) << 8);
    }

    public int readLittleEndianUnsignedShort() {
        byte[] bArr = this.data;
        int r1 = this.position;
        int r2 = r1 + 1;
        this.position = r2;
        this.position = r2 + 1;
        return ((bArr[r2] & 255) << 8) | (bArr[r1] & 255);
    }

    public short readShort() {
        byte[] bArr = this.data;
        int r1 = this.position;
        int r2 = r1 + 1;
        this.position = r2;
        this.position = r2 + 1;
        return (short) ((bArr[r2] & 255) | ((bArr[r1] & 255) << 8));
    }

    public short readLittleEndianShort() {
        byte[] bArr = this.data;
        int r1 = this.position;
        int r2 = r1 + 1;
        this.position = r2;
        this.position = r2 + 1;
        return (short) (((bArr[r2] & 255) << 8) | (bArr[r1] & 255));
    }

    public int readUnsignedInt24() {
        byte[] bArr = this.data;
        int r1 = this.position;
        int r2 = r1 + 1;
        this.position = r2;
        int r3 = r2 + 1;
        this.position = r3;
        int r12 = ((bArr[r1] & 255) << 16) | ((bArr[r2] & 255) << 8);
        this.position = r3 + 1;
        return (bArr[r3] & 255) | r12;
    }

    public int readInt24() {
        byte[] bArr = this.data;
        int r1 = this.position;
        int r2 = r1 + 1;
        this.position = r2;
        int r3 = r2 + 1;
        this.position = r3;
        int r12 = (((bArr[r1] & 255) << 24) >> 8) | ((bArr[r2] & 255) << 8);
        this.position = r3 + 1;
        return (bArr[r3] & 255) | r12;
    }

    public int readLittleEndianInt24() {
        byte[] bArr = this.data;
        int r1 = this.position;
        int r2 = r1 + 1;
        this.position = r2;
        int r3 = r2 + 1;
        this.position = r3;
        int r12 = (bArr[r1] & 255) | ((bArr[r2] & 255) << 8);
        this.position = r3 + 1;
        return ((bArr[r3] & 255) << 16) | r12;
    }

    public int readLittleEndianUnsignedInt24() {
        byte[] bArr = this.data;
        int r1 = this.position;
        int r2 = r1 + 1;
        this.position = r2;
        int r3 = r2 + 1;
        this.position = r3;
        int r12 = (bArr[r1] & 255) | ((bArr[r2] & 255) << 8);
        this.position = r3 + 1;
        return ((bArr[r3] & 255) << 16) | r12;
    }

    public long readUnsignedInt() {
        byte[] bArr = this.data;
        int r1 = this.position;
        int r2 = r1 + 1;
        this.position = r2;
        int r12 = r2 + 1;
        this.position = r12;
        int r4 = r12 + 1;
        this.position = r4;
        this.position = r4 + 1;
        return ((bArr[r1] & 255) << 24) | ((bArr[r2] & 255) << 16) | ((bArr[r12] & 255) << 8) | (bArr[r4] & 255);
    }

    public long readLittleEndianUnsignedInt() {
        byte[] bArr = this.data;
        int r1 = this.position;
        int r2 = r1 + 1;
        this.position = r2;
        int r12 = r2 + 1;
        this.position = r12;
        int r4 = r12 + 1;
        this.position = r4;
        this.position = r4 + 1;
        return (bArr[r1] & 255) | ((bArr[r2] & 255) << 8) | ((bArr[r12] & 255) << 16) | ((bArr[r4] & 255) << 24);
    }

    public int readInt() {
        byte[] bArr = this.data;
        int r1 = this.position;
        int r2 = r1 + 1;
        this.position = r2;
        int r3 = r2 + 1;
        this.position = r3;
        int r12 = ((bArr[r1] & 255) << 24) | ((bArr[r2] & 255) << 16);
        int r22 = r3 + 1;
        this.position = r22;
        int r13 = r12 | ((bArr[r3] & 255) << 8);
        this.position = r22 + 1;
        return (bArr[r22] & 255) | r13;
    }

    public int readLittleEndianInt() {
        byte[] bArr = this.data;
        int r1 = this.position;
        int r2 = r1 + 1;
        this.position = r2;
        int r3 = r2 + 1;
        this.position = r3;
        int r12 = (bArr[r1] & 255) | ((bArr[r2] & 255) << 8);
        int r22 = r3 + 1;
        this.position = r22;
        int r13 = r12 | ((bArr[r3] & 255) << 16);
        this.position = r22 + 1;
        return ((bArr[r22] & 255) << 24) | r13;
    }

    public long readLong() {
        byte[] bArr = this.data;
        int r1 = this.position;
        int r2 = r1 + 1;
        this.position = r2;
        int r12 = r2 + 1;
        this.position = r12;
        int r4 = r12 + 1;
        this.position = r4;
        int r3 = r4 + 1;
        this.position = r3;
        int r42 = r3 + 1;
        this.position = r42;
        int r32 = r42 + 1;
        this.position = r32;
        int r43 = r32 + 1;
        this.position = r43;
        this.position = r43 + 1;
        return ((bArr[r1] & 255) << 56) | ((bArr[r2] & 255) << 48) | ((bArr[r12] & 255) << 40) | ((bArr[r4] & 255) << 32) | ((bArr[r3] & 255) << 24) | ((bArr[r42] & 255) << 16) | ((bArr[r32] & 255) << 8) | (bArr[r43] & 255);
    }

    public long readLittleEndianLong() {
        byte[] bArr = this.data;
        int r1 = this.position;
        int r2 = r1 + 1;
        this.position = r2;
        int r12 = r2 + 1;
        this.position = r12;
        int r4 = r12 + 1;
        this.position = r4;
        int r3 = r4 + 1;
        this.position = r3;
        int r42 = r3 + 1;
        this.position = r42;
        int r32 = r42 + 1;
        this.position = r32;
        int r43 = r32 + 1;
        this.position = r43;
        this.position = r43 + 1;
        return (bArr[r1] & 255) | ((bArr[r2] & 255) << 8) | ((bArr[r12] & 255) << 16) | ((bArr[r4] & 255) << 24) | ((bArr[r3] & 255) << 32) | ((bArr[r42] & 255) << 40) | ((bArr[r32] & 255) << 48) | ((bArr[r43] & 255) << 56);
    }

    public int readUnsignedFixedPoint1616() {
        byte[] bArr = this.data;
        int r1 = this.position;
        int r2 = r1 + 1;
        this.position = r2;
        int r3 = r2 + 1;
        this.position = r3;
        int r0 = (bArr[r2] & 255) | ((bArr[r1] & 255) << 8);
        this.position = r3 + 2;
        return r0;
    }

    public int readSynchSafeInt() {
        return (readUnsignedByte() << 21) | (readUnsignedByte() << 14) | (readUnsignedByte() << 7) | readUnsignedByte();
    }

    public int readUnsignedIntToInt() {
        int readInt = readInt();
        if (readInt >= 0) {
            return readInt;
        }
        throw new IllegalStateException("Top bit not zero: " + readInt);
    }

    public int readLittleEndianUnsignedIntToInt() {
        int readLittleEndianInt = readLittleEndianInt();
        if (readLittleEndianInt >= 0) {
            return readLittleEndianInt;
        }
        throw new IllegalStateException("Top bit not zero: " + readLittleEndianInt);
    }

    public long readUnsignedLongToLong() {
        long readLong = readLong();
        if (readLong >= 0) {
            return readLong;
        }
        throw new IllegalStateException("Top bit not zero: " + readLong);
    }

    public float readFloat() {
        return Float.intBitsToFloat(readInt());
    }

    public double readDouble() {
        return Double.longBitsToDouble(readLong());
    }

    public String readString(int r2) {
        return readString(r2, Charsets.UTF_8);
    }

    public String readString(int r4, Charset charset) {
        String str = new String(this.data, this.position, r4, charset);
        this.position += r4;
        return str;
    }

    public String readNullTerminatedString(int r4) {
        if (r4 == 0) {
            return "";
        }
        int r0 = this.position;
        int r1 = (r0 + r4) - 1;
        String fromUtf8Bytes = Util.fromUtf8Bytes(this.data, r0, (r1 >= this.limit || this.data[r1] != 0) ? r4 : r4 - 1);
        this.position += r4;
        return fromUtf8Bytes;
    }

    public String readNullTerminatedString() {
        return readDelimiterTerminatedString((char) 0);
    }

    public String readDelimiterTerminatedString(char c) {
        if (bytesLeft() == 0) {
            return null;
        }
        int r0 = this.position;
        while (r0 < this.limit && this.data[r0] != c) {
            r0++;
        }
        byte[] bArr = this.data;
        int r1 = this.position;
        String fromUtf8Bytes = Util.fromUtf8Bytes(bArr, r1, r0 - r1);
        this.position = r0;
        if (r0 < this.limit) {
            this.position = r0 + 1;
        }
        return fromUtf8Bytes;
    }

    public String readLine() {
        if (bytesLeft() == 0) {
            return null;
        }
        int r0 = this.position;
        while (r0 < this.limit && !Util.isLinebreak(this.data[r0])) {
            r0++;
        }
        int r1 = this.position;
        if (r0 - r1 >= 3) {
            byte[] bArr = this.data;
            if (bArr[r1] == -17 && bArr[r1 + 1] == -69 && bArr[r1 + 2] == -65) {
                this.position = r1 + 3;
            }
        }
        byte[] bArr2 = this.data;
        int r2 = this.position;
        String fromUtf8Bytes = Util.fromUtf8Bytes(bArr2, r2, r0 - r2);
        this.position = r0;
        int r22 = this.limit;
        if (r0 == r22) {
            return fromUtf8Bytes;
        }
        byte[] bArr3 = this.data;
        if (bArr3[r0] == 13) {
            int r02 = r0 + 1;
            this.position = r02;
            if (r02 == r22) {
                return fromUtf8Bytes;
            }
        }
        int r03 = this.position;
        if (bArr3[r03] == 10) {
            this.position = r03 + 1;
        }
        return fromUtf8Bytes;
    }

    public long readUtf8EncodedLong() {
        int r5;
        int r2;
        byte b;
        int r6;
        long j = this.data[this.position];
        int r3 = 7;
        while (true) {
            if (r3 < 0) {
                break;
            }
            if (((1 << r3) & j) != 0) {
                r3--;
            } else if (r3 < 6) {
                j &= r6 - 1;
                r2 = 7 - r3;
            } else if (r3 == 7) {
                r2 = 1;
            }
        }
        r2 = 0;
        if (r2 == 0) {
            throw new NumberFormatException("Invalid UTF-8 sequence first byte: " + j);
        }
        for (r5 = 1; r5 < r2; r5++) {
            if ((this.data[this.position + r5] & 192) != 128) {
                throw new NumberFormatException("Invalid UTF-8 sequence continuation byte: " + j);
            }
            j = (j << 6) | (b & Utf8.REPLACEMENT_BYTE);
        }
        this.position += r2;
        return j;
    }
}
