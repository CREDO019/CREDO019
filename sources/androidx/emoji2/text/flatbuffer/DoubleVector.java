package androidx.emoji2.text.flatbuffer;

import java.nio.ByteBuffer;

/* loaded from: classes.dex */
public final class DoubleVector extends BaseVector {
    public DoubleVector __assign(int r2, ByteBuffer byteBuffer) {
        __reset(r2, 8, byteBuffer);
        return this;
    }

    public double get(int r3) {
        return this.f37bb.getDouble(__element(r3));
    }
}
