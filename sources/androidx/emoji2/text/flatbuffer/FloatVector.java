package androidx.emoji2.text.flatbuffer;

import java.nio.ByteBuffer;

/* loaded from: classes.dex */
public final class FloatVector extends BaseVector {
    public FloatVector __assign(int r2, ByteBuffer byteBuffer) {
        __reset(r2, 4, byteBuffer);
        return this;
    }

    public float get(int r2) {
        return this.f37bb.getFloat(__element(r2));
    }
}
