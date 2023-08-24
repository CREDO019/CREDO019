package androidx.emoji2.text.flatbuffer;

import java.nio.ByteBuffer;
import kotlin.UShort;

/* loaded from: classes.dex */
public final class ShortVector extends BaseVector {
    public ShortVector __assign(int r2, ByteBuffer byteBuffer) {
        __reset(r2, 2, byteBuffer);
        return this;
    }

    public short get(int r2) {
        return this.f37bb.getShort(__element(r2));
    }

    public int getAsUnsigned(int r2) {
        return get(r2) & UShort.MAX_VALUE;
    }
}
