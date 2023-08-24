package com.google.common.hash;

import com.google.common.base.Preconditions;
import java.nio.ByteBuffer;
import java.nio.charset.Charset;

@ElementTypesAreNonnullByDefault
/* loaded from: classes3.dex */
abstract class AbstractHasher implements Hasher {
    @Override // com.google.common.hash.Hasher, com.google.common.hash.PrimitiveSink
    public /* bridge */ /* synthetic */ PrimitiveSink putByte(byte b) {
        PrimitiveSink putByte;
        putByte = putByte(b);
        return putByte;
    }

    @Override // com.google.common.hash.PrimitiveSink
    public final Hasher putBoolean(boolean z) {
        return putByte(z ? (byte) 1 : (byte) 0);
    }

    @Override // com.google.common.hash.PrimitiveSink
    public final Hasher putDouble(double d) {
        return putLong(Double.doubleToRawLongBits(d));
    }

    @Override // com.google.common.hash.PrimitiveSink
    public final Hasher putFloat(float f) {
        return putInt(Float.floatToRawIntBits(f));
    }

    @Override // com.google.common.hash.PrimitiveSink
    public Hasher putUnencodedChars(CharSequence charSequence) {
        int length = charSequence.length();
        for (int r1 = 0; r1 < length; r1++) {
            putChar(charSequence.charAt(r1));
        }
        return this;
    }

    @Override // com.google.common.hash.PrimitiveSink
    public Hasher putString(CharSequence charSequence, Charset charset) {
        return putBytes(charSequence.toString().getBytes(charset));
    }

    @Override // com.google.common.hash.PrimitiveSink
    public Hasher putBytes(byte[] bArr) {
        return putBytes(bArr, 0, bArr.length);
    }

    @Override // com.google.common.hash.PrimitiveSink
    public Hasher putBytes(byte[] bArr, int r4, int r5) {
        Preconditions.checkPositionIndexes(r4, r4 + r5, bArr.length);
        for (int r0 = 0; r0 < r5; r0++) {
            putByte(bArr[r4 + r0]);
        }
        return this;
    }

    @Override // com.google.common.hash.PrimitiveSink
    public Hasher putBytes(ByteBuffer byteBuffer) {
        if (byteBuffer.hasArray()) {
            putBytes(byteBuffer.array(), byteBuffer.arrayOffset() + byteBuffer.position(), byteBuffer.remaining());
            Java8Compatibility.position(byteBuffer, byteBuffer.limit());
        } else {
            for (int remaining = byteBuffer.remaining(); remaining > 0; remaining--) {
                putByte(byteBuffer.get());
            }
        }
        return this;
    }

    @Override // com.google.common.hash.PrimitiveSink
    public Hasher putShort(short s) {
        putByte((byte) s);
        putByte((byte) (s >>> 8));
        return this;
    }

    @Override // com.google.common.hash.PrimitiveSink
    public Hasher putInt(int r2) {
        putByte((byte) r2);
        putByte((byte) (r2 >>> 8));
        putByte((byte) (r2 >>> 16));
        putByte((byte) (r2 >>> 24));
        return this;
    }

    @Override // com.google.common.hash.PrimitiveSink
    public Hasher putLong(long j) {
        for (int r0 = 0; r0 < 64; r0 += 8) {
            putByte((byte) (j >>> r0));
        }
        return this;
    }

    @Override // com.google.common.hash.PrimitiveSink
    public Hasher putChar(char c) {
        putByte((byte) c);
        putByte((byte) (c >>> '\b'));
        return this;
    }

    @Override // com.google.common.hash.Hasher
    public <T> Hasher putObject(@ParametricNullness T t, Funnel<? super T> funnel) {
        funnel.funnel(t, this);
        return this;
    }
}
