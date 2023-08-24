package androidx.emoji2.text.flatbuffer;

import java.nio.ByteBuffer;

/* loaded from: classes.dex */
public final class BooleanVector extends BaseVector {
    public BooleanVector __assign(int r2, ByteBuffer byteBuffer) {
        __reset(r2, 1, byteBuffer);
        return this;
    }

    public boolean get(int r2) {
        return this.f37bb.get(__element(r2)) != 0;
    }
}
