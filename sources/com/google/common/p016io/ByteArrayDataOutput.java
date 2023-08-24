package com.google.common.p016io;

import java.io.DataOutput;

@ElementTypesAreNonnullByDefault
/* renamed from: com.google.common.io.ByteArrayDataOutput */
/* loaded from: classes3.dex */
public interface ByteArrayDataOutput extends DataOutput {
    byte[] toByteArray();

    @Override // java.io.DataOutput
    void write(int r1);

    @Override // java.io.DataOutput
    void write(byte[] bArr);

    @Override // java.io.DataOutput
    void write(byte[] bArr, int r2, int r3);

    @Override // java.io.DataOutput
    void writeBoolean(boolean z);

    @Override // java.io.DataOutput
    void writeByte(int r1);

    @Override // java.io.DataOutput
    @Deprecated
    void writeBytes(String str);

    @Override // java.io.DataOutput
    void writeChar(int r1);

    @Override // java.io.DataOutput
    void writeChars(String str);

    @Override // java.io.DataOutput
    void writeDouble(double d);

    @Override // java.io.DataOutput
    void writeFloat(float f);

    @Override // java.io.DataOutput
    void writeInt(int r1);

    @Override // java.io.DataOutput
    void writeLong(long j);

    @Override // java.io.DataOutput
    void writeShort(int r1);

    @Override // java.io.DataOutput
    void writeUTF(String str);
}
