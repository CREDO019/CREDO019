package androidx.emoji2.text.flatbuffer;

import java.nio.ByteBuffer;

/* loaded from: classes.dex */
public final class LongVector extends BaseVector {
    public LongVector __assign(int r2, ByteBuffer byteBuffer) {
        __reset(r2, 8, byteBuffer);
        return this;
    }

    public long get(int r3) {
        return this.f37bb.getLong(__element(r3));
    }
}
