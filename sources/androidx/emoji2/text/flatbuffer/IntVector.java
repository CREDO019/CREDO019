package androidx.emoji2.text.flatbuffer;

import java.nio.ByteBuffer;
import org.bouncycastle.asn1.cmc.BodyPartID;

/* loaded from: classes.dex */
public final class IntVector extends BaseVector {
    public IntVector __assign(int r2, ByteBuffer byteBuffer) {
        __reset(r2, 4, byteBuffer);
        return this;
    }

    public int get(int r2) {
        return this.f37bb.getInt(__element(r2));
    }

    public long getAsUnsigned(int r5) {
        return get(r5) & BodyPartID.bodyIdMax;
    }
}
