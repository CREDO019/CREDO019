package androidx.emoji2.text.flatbuffer;

import java.nio.ByteBuffer;

/* loaded from: classes.dex */
public final class StringVector extends BaseVector {
    private Utf8 utf8 = Utf8.getDefault();

    public StringVector __assign(int r1, int r2, ByteBuffer byteBuffer) {
        __reset(r1, r2, byteBuffer);
        return this;
    }

    public String get(int r3) {
        return Table.__string(__element(r3), this.f37bb, this.utf8);
    }
}
