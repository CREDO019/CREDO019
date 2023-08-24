package androidx.emoji2.text.flatbuffer;

import java.nio.ByteBuffer;

/* loaded from: classes.dex */
public class Struct {

    /* renamed from: bb */
    protected ByteBuffer f42bb;
    protected int bb_pos;

    protected void __reset(int r1, ByteBuffer byteBuffer) {
        this.f42bb = byteBuffer;
        if (byteBuffer != null) {
            this.bb_pos = r1;
        } else {
            this.bb_pos = 0;
        }
    }

    public void __reset() {
        __reset(0, null);
    }
}
