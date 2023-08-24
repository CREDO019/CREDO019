package androidx.emoji2.text.flatbuffer;

import java.nio.ByteBuffer;

/* loaded from: classes.dex */
public final class UnionVector extends BaseVector {
    public UnionVector __assign(int r1, int r2, ByteBuffer byteBuffer) {
        __reset(r1, r2, byteBuffer);
        return this;
    }

    public Table get(Table table, int r3) {
        return Table.__union(table, __element(r3), this.f37bb);
    }
}
