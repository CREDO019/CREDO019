package androidx.emoji2.text.flatbuffer;

import java.nio.ByteBuffer;

/* loaded from: classes.dex */
public class BaseVector {

    /* renamed from: bb */
    protected ByteBuffer f37bb;
    private int element_size;
    private int length;
    private int vector;

    protected int __vector() {
        return this.vector;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public int __element(int r3) {
        return this.vector + (r3 * this.element_size);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void __reset(int r1, int r2, ByteBuffer byteBuffer) {
        this.f37bb = byteBuffer;
        if (byteBuffer != null) {
            this.vector = r1;
            this.length = byteBuffer.getInt(r1 - 4);
            this.element_size = r2;
            return;
        }
        this.vector = 0;
        this.length = 0;
        this.element_size = 0;
    }

    public void reset() {
        __reset(0, 0, null);
    }

    public int length() {
        return this.length;
    }
}
