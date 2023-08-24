package androidx.emoji2.text.flatbuffer;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;

/* loaded from: classes.dex */
public class ByteBufferReadWriteBuf implements ReadWriteBuf {
    private final ByteBuffer buffer;

    public ByteBufferReadWriteBuf(ByteBuffer byteBuffer) {
        this.buffer = byteBuffer;
        byteBuffer.order(ByteOrder.LITTLE_ENDIAN);
    }

    @Override // androidx.emoji2.text.flatbuffer.ReadBuf
    public boolean getBoolean(int r1) {
        return get(r1) != 0;
    }

    @Override // androidx.emoji2.text.flatbuffer.ReadBuf
    public byte get(int r2) {
        return this.buffer.get(r2);
    }

    @Override // androidx.emoji2.text.flatbuffer.ReadBuf
    public short getShort(int r2) {
        return this.buffer.getShort(r2);
    }

    @Override // androidx.emoji2.text.flatbuffer.ReadBuf
    public int getInt(int r2) {
        return this.buffer.getInt(r2);
    }

    @Override // androidx.emoji2.text.flatbuffer.ReadBuf
    public long getLong(int r3) {
        return this.buffer.getLong(r3);
    }

    @Override // androidx.emoji2.text.flatbuffer.ReadBuf
    public float getFloat(int r2) {
        return this.buffer.getFloat(r2);
    }

    @Override // androidx.emoji2.text.flatbuffer.ReadBuf
    public double getDouble(int r3) {
        return this.buffer.getDouble(r3);
    }

    @Override // androidx.emoji2.text.flatbuffer.ReadBuf
    public String getString(int r2, int r3) {
        return Utf8Safe.decodeUtf8Buffer(this.buffer, r2, r3);
    }

    @Override // androidx.emoji2.text.flatbuffer.ReadBuf
    public byte[] data() {
        return this.buffer.array();
    }

    @Override // androidx.emoji2.text.flatbuffer.ReadWriteBuf
    public void putBoolean(boolean z) {
        this.buffer.put(z ? (byte) 1 : (byte) 0);
    }

    @Override // androidx.emoji2.text.flatbuffer.ReadWriteBuf
    public void put(byte[] bArr, int r3, int r4) {
        this.buffer.put(bArr, r3, r4);
    }

    @Override // androidx.emoji2.text.flatbuffer.ReadWriteBuf
    public void put(byte b) {
        this.buffer.put(b);
    }

    @Override // androidx.emoji2.text.flatbuffer.ReadWriteBuf
    public void putShort(short s) {
        this.buffer.putShort(s);
    }

    @Override // androidx.emoji2.text.flatbuffer.ReadWriteBuf
    public void putInt(int r2) {
        this.buffer.putInt(r2);
    }

    @Override // androidx.emoji2.text.flatbuffer.ReadWriteBuf
    public void putLong(long j) {
        this.buffer.putLong(j);
    }

    @Override // androidx.emoji2.text.flatbuffer.ReadWriteBuf
    public void putFloat(float f) {
        this.buffer.putFloat(f);
    }

    @Override // androidx.emoji2.text.flatbuffer.ReadWriteBuf
    public void putDouble(double d) {
        this.buffer.putDouble(d);
    }

    @Override // androidx.emoji2.text.flatbuffer.ReadWriteBuf
    public void setBoolean(int r1, boolean z) {
        set(r1, z ? (byte) 1 : (byte) 0);
    }

    @Override // androidx.emoji2.text.flatbuffer.ReadWriteBuf
    public void set(int r2, byte b) {
        requestCapacity(r2 + 1);
        this.buffer.put(r2, b);
    }

    @Override // androidx.emoji2.text.flatbuffer.ReadWriteBuf
    public void set(int r3, byte[] bArr, int r5, int r6) {
        requestCapacity((r6 - r5) + r3);
        int position = this.buffer.position();
        this.buffer.position(r3);
        this.buffer.put(bArr, r5, r6);
        this.buffer.position(position);
    }

    @Override // androidx.emoji2.text.flatbuffer.ReadWriteBuf
    public void setShort(int r2, short s) {
        requestCapacity(r2 + 2);
        this.buffer.putShort(r2, s);
    }

    @Override // androidx.emoji2.text.flatbuffer.ReadWriteBuf
    public void setInt(int r2, int r3) {
        requestCapacity(r2 + 4);
        this.buffer.putInt(r2, r3);
    }

    @Override // androidx.emoji2.text.flatbuffer.ReadWriteBuf
    public void setLong(int r2, long j) {
        requestCapacity(r2 + 8);
        this.buffer.putLong(r2, j);
    }

    @Override // androidx.emoji2.text.flatbuffer.ReadWriteBuf
    public void setFloat(int r2, float f) {
        requestCapacity(r2 + 4);
        this.buffer.putFloat(r2, f);
    }

    @Override // androidx.emoji2.text.flatbuffer.ReadWriteBuf
    public void setDouble(int r2, double d) {
        requestCapacity(r2 + 8);
        this.buffer.putDouble(r2, d);
    }

    @Override // androidx.emoji2.text.flatbuffer.ReadWriteBuf
    public int writePosition() {
        return this.buffer.position();
    }

    @Override // androidx.emoji2.text.flatbuffer.ReadWriteBuf, androidx.emoji2.text.flatbuffer.ReadBuf
    public int limit() {
        return this.buffer.limit();
    }

    @Override // androidx.emoji2.text.flatbuffer.ReadWriteBuf
    public boolean requestCapacity(int r2) {
        return r2 <= this.buffer.limit();
    }
}
