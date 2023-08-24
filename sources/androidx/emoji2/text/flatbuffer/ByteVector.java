package androidx.emoji2.text.flatbuffer;

import java.nio.ByteBuffer;

/* loaded from: classes.dex */
public final class ByteVector extends BaseVector {
    public ByteVector __assign(int r2, ByteBuffer byteBuffer) {
        __reset(r2, 1, byteBuffer);
        return this;
    }

    public byte get(int r2) {
        return this.f37bb.get(__element(r2));
    }

    public int getAsUnsigned(int r1) {
        return get(r1) & 255;
    }
}
