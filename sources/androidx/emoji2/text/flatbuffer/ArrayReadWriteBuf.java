package androidx.emoji2.text.flatbuffer;

import com.google.common.base.Ascii;
import java.util.Arrays;

/* loaded from: classes.dex */
public class ArrayReadWriteBuf implements ReadWriteBuf {
    private byte[] buffer;
    private int writePos;

    public ArrayReadWriteBuf() {
        this(10);
    }

    public ArrayReadWriteBuf(int r1) {
        this(new byte[r1]);
    }

    public ArrayReadWriteBuf(byte[] bArr) {
        this.buffer = bArr;
        this.writePos = 0;
    }

    public ArrayReadWriteBuf(byte[] bArr, int r2) {
        this.buffer = bArr;
        this.writePos = r2;
    }

    @Override // androidx.emoji2.text.flatbuffer.ReadBuf
    public boolean getBoolean(int r2) {
        return this.buffer[r2] != 0;
    }

    @Override // androidx.emoji2.text.flatbuffer.ReadBuf
    public byte get(int r2) {
        return this.buffer[r2];
    }

    @Override // androidx.emoji2.text.flatbuffer.ReadBuf
    public short getShort(int r3) {
        byte[] bArr = this.buffer;
        return (short) ((bArr[r3] & 255) | (bArr[r3 + 1] << 8));
    }

    @Override // androidx.emoji2.text.flatbuffer.ReadBuf
    public int getInt(int r4) {
        byte[] bArr = this.buffer;
        return (bArr[r4] & 255) | (bArr[r4 + 3] << Ascii.CAN) | ((bArr[r4 + 2] & 255) << 16) | ((bArr[r4 + 1] & 255) << 8);
    }

    @Override // androidx.emoji2.text.flatbuffer.ReadBuf
    public long getLong(int r9) {
        byte[] bArr = this.buffer;
        int r1 = r9 + 1;
        int r92 = r1 + 1;
        int r3 = r92 + 1;
        int r93 = r3 + 1;
        int r32 = r93 + 1;
        int r94 = r32 + 1;
        return (bArr[r9] & 255) | ((bArr[r1] & 255) << 8) | ((bArr[r92] & 255) << 16) | ((bArr[r3] & 255) << 24) | ((bArr[r93] & 255) << 32) | ((bArr[r32] & 255) << 40) | ((255 & bArr[r94]) << 48) | (bArr[r94 + 1] << 56);
    }

    @Override // androidx.emoji2.text.flatbuffer.ReadBuf
    public float getFloat(int r1) {
        return Float.intBitsToFloat(getInt(r1));
    }

    @Override // androidx.emoji2.text.flatbuffer.ReadBuf
    public double getDouble(int r3) {
        return Double.longBitsToDouble(getLong(r3));
    }

    @Override // androidx.emoji2.text.flatbuffer.ReadBuf
    public String getString(int r2, int r3) {
        return Utf8Safe.decodeUtf8Array(this.buffer, r2, r3);
    }

    @Override // androidx.emoji2.text.flatbuffer.ReadBuf
    public byte[] data() {
        return this.buffer;
    }

    @Override // androidx.emoji2.text.flatbuffer.ReadWriteBuf
    public void putBoolean(boolean z) {
        setBoolean(this.writePos, z);
        this.writePos++;
    }

    @Override // androidx.emoji2.text.flatbuffer.ReadWriteBuf
    public void put(byte[] bArr, int r3, int r4) {
        set(this.writePos, bArr, r3, r4);
        this.writePos += r4;
    }

    @Override // androidx.emoji2.text.flatbuffer.ReadWriteBuf
    public void put(byte b) {
        set(this.writePos, b);
        this.writePos++;
    }

    @Override // androidx.emoji2.text.flatbuffer.ReadWriteBuf
    public void putShort(short s) {
        setShort(this.writePos, s);
        this.writePos += 2;
    }

    @Override // androidx.emoji2.text.flatbuffer.ReadWriteBuf
    public void putInt(int r2) {
        setInt(this.writePos, r2);
        this.writePos += 4;
    }

    @Override // androidx.emoji2.text.flatbuffer.ReadWriteBuf
    public void putLong(long j) {
        setLong(this.writePos, j);
        this.writePos += 8;
    }

    @Override // androidx.emoji2.text.flatbuffer.ReadWriteBuf
    public void putFloat(float f) {
        setFloat(this.writePos, f);
        this.writePos += 4;
    }

    @Override // androidx.emoji2.text.flatbuffer.ReadWriteBuf
    public void putDouble(double d) {
        setDouble(this.writePos, d);
        this.writePos += 8;
    }

    @Override // androidx.emoji2.text.flatbuffer.ReadWriteBuf
    public void setBoolean(int r1, boolean z) {
        set(r1, z ? (byte) 1 : (byte) 0);
    }

    @Override // androidx.emoji2.text.flatbuffer.ReadWriteBuf
    public void set(int r2, byte b) {
        requestCapacity(r2 + 1);
        this.buffer[r2] = b;
    }

    @Override // androidx.emoji2.text.flatbuffer.ReadWriteBuf
    public void set(int r2, byte[] bArr, int r4, int r5) {
        requestCapacity((r5 - r4) + r2);
        System.arraycopy(bArr, r4, this.buffer, r2, r5);
    }

    @Override // androidx.emoji2.text.flatbuffer.ReadWriteBuf
    public void setShort(int r4, short s) {
        requestCapacity(r4 + 2);
        byte[] bArr = this.buffer;
        bArr[r4] = (byte) (s & 255);
        bArr[r4 + 1] = (byte) ((s >> 8) & 255);
    }

    @Override // androidx.emoji2.text.flatbuffer.ReadWriteBuf
    public void setInt(int r4, int r5) {
        requestCapacity(r4 + 4);
        byte[] bArr = this.buffer;
        int r1 = r4 + 1;
        bArr[r4] = (byte) (r5 & 255);
        int r42 = r1 + 1;
        bArr[r1] = (byte) ((r5 >> 8) & 255);
        bArr[r42] = (byte) ((r5 >> 16) & 255);
        bArr[r42 + 1] = (byte) ((r5 >> 24) & 255);
    }

    @Override // androidx.emoji2.text.flatbuffer.ReadWriteBuf
    public void setLong(int r5, long j) {
        requestCapacity(r5 + 8);
        int r0 = (int) j;
        byte[] bArr = this.buffer;
        int r2 = r5 + 1;
        bArr[r5] = (byte) (r0 & 255);
        int r52 = r2 + 1;
        bArr[r2] = (byte) ((r0 >> 8) & 255);
        int r22 = r52 + 1;
        bArr[r52] = (byte) ((r0 >> 16) & 255);
        int r53 = r22 + 1;
        bArr[r22] = (byte) ((r0 >> 24) & 255);
        int r7 = (int) (j >> 32);
        int r6 = r53 + 1;
        bArr[r53] = (byte) (r7 & 255);
        int r54 = r6 + 1;
        bArr[r6] = (byte) ((r7 >> 8) & 255);
        bArr[r54] = (byte) ((r7 >> 16) & 255);
        bArr[r54 + 1] = (byte) ((r7 >> 24) & 255);
    }

    @Override // androidx.emoji2.text.flatbuffer.ReadWriteBuf
    public void setFloat(int r4, float f) {
        requestCapacity(r4 + 4);
        int floatToRawIntBits = Float.floatToRawIntBits(f);
        byte[] bArr = this.buffer;
        int r1 = r4 + 1;
        bArr[r4] = (byte) (floatToRawIntBits & 255);
        int r42 = r1 + 1;
        bArr[r1] = (byte) ((floatToRawIntBits >> 8) & 255);
        bArr[r42] = (byte) ((floatToRawIntBits >> 16) & 255);
        bArr[r42 + 1] = (byte) ((floatToRawIntBits >> 24) & 255);
    }

    @Override // androidx.emoji2.text.flatbuffer.ReadWriteBuf
    public void setDouble(int r5, double d) {
        requestCapacity(r5 + 8);
        long doubleToRawLongBits = Double.doubleToRawLongBits(d);
        int r0 = (int) doubleToRawLongBits;
        byte[] bArr = this.buffer;
        int r2 = r5 + 1;
        bArr[r5] = (byte) (r0 & 255);
        int r52 = r2 + 1;
        bArr[r2] = (byte) ((r0 >> 8) & 255);
        int r22 = r52 + 1;
        bArr[r52] = (byte) ((r0 >> 16) & 255);
        int r53 = r22 + 1;
        bArr[r22] = (byte) ((r0 >> 24) & 255);
        int r7 = (int) (doubleToRawLongBits >> 32);
        int r6 = r53 + 1;
        bArr[r53] = (byte) (r7 & 255);
        int r54 = r6 + 1;
        bArr[r6] = (byte) ((r7 >> 8) & 255);
        bArr[r54] = (byte) ((r7 >> 16) & 255);
        bArr[r54 + 1] = (byte) ((r7 >> 24) & 255);
    }

    @Override // androidx.emoji2.text.flatbuffer.ReadWriteBuf, androidx.emoji2.text.flatbuffer.ReadBuf
    public int limit() {
        return this.writePos;
    }

    @Override // androidx.emoji2.text.flatbuffer.ReadWriteBuf
    public int writePosition() {
        return this.writePos;
    }

    @Override // androidx.emoji2.text.flatbuffer.ReadWriteBuf
    public boolean requestCapacity(int r4) {
        byte[] bArr = this.buffer;
        if (bArr.length > r4) {
            return true;
        }
        int length = bArr.length;
        this.buffer = Arrays.copyOf(bArr, length + (length >> 1));
        return true;
    }
}
