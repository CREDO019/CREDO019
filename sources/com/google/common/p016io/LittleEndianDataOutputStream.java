package com.google.common.p016io;

import com.google.common.base.Preconditions;
import com.google.common.primitives.Longs;
import java.io.DataOutput;
import java.io.DataOutputStream;
import java.io.FilterOutputStream;
import java.io.IOException;
import java.io.OutputStream;

@ElementTypesAreNonnullByDefault
/* renamed from: com.google.common.io.LittleEndianDataOutputStream */
/* loaded from: classes3.dex */
public final class LittleEndianDataOutputStream extends FilterOutputStream implements DataOutput {
    public LittleEndianDataOutputStream(OutputStream outputStream) {
        super(new DataOutputStream((OutputStream) Preconditions.checkNotNull(outputStream)));
    }

    @Override // java.io.FilterOutputStream, java.io.OutputStream, java.io.DataOutput
    public void write(byte[] bArr, int r3, int r4) throws IOException {
        this.out.write(bArr, r3, r4);
    }

    @Override // java.io.DataOutput
    public void writeBoolean(boolean z) throws IOException {
        ((DataOutputStream) this.out).writeBoolean(z);
    }

    @Override // java.io.DataOutput
    public void writeByte(int r2) throws IOException {
        ((DataOutputStream) this.out).writeByte(r2);
    }

    @Override // java.io.DataOutput
    @Deprecated
    public void writeBytes(String str) throws IOException {
        ((DataOutputStream) this.out).writeBytes(str);
    }

    @Override // java.io.DataOutput
    public void writeChar(int r1) throws IOException {
        writeShort(r1);
    }

    @Override // java.io.DataOutput
    public void writeChars(String str) throws IOException {
        for (int r0 = 0; r0 < str.length(); r0++) {
            writeChar(str.charAt(r0));
        }
    }

    @Override // java.io.DataOutput
    public void writeDouble(double d) throws IOException {
        writeLong(Double.doubleToLongBits(d));
    }

    @Override // java.io.DataOutput
    public void writeFloat(float f) throws IOException {
        writeInt(Float.floatToIntBits(f));
    }

    @Override // java.io.DataOutput
    public void writeInt(int r3) throws IOException {
        this.out.write(r3 & 255);
        this.out.write((r3 >> 8) & 255);
        this.out.write((r3 >> 16) & 255);
        this.out.write((r3 >> 24) & 255);
    }

    @Override // java.io.DataOutput
    public void writeLong(long j) throws IOException {
        byte[] byteArray = Longs.toByteArray(Long.reverseBytes(j));
        write(byteArray, 0, byteArray.length);
    }

    @Override // java.io.DataOutput
    public void writeShort(int r3) throws IOException {
        this.out.write(r3 & 255);
        this.out.write((r3 >> 8) & 255);
    }

    @Override // java.io.DataOutput
    public void writeUTF(String str) throws IOException {
        ((DataOutputStream) this.out).writeUTF(str);
    }

    @Override // java.io.FilterOutputStream, java.io.OutputStream, java.io.Closeable, java.lang.AutoCloseable
    public void close() throws IOException {
        this.out.close();
    }
}
