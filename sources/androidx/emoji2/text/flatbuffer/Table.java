package androidx.emoji2.text.flatbuffer;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.Arrays;
import java.util.Comparator;

/* loaded from: classes.dex */
public class Table {

    /* renamed from: bb */
    protected ByteBuffer f43bb;
    protected int bb_pos;
    Utf8 utf8 = Utf8.getDefault();
    private int vtable_size;
    private int vtable_start;

    protected int keysCompare(Integer num, Integer num2, ByteBuffer byteBuffer) {
        return 0;
    }

    public ByteBuffer getByteBuffer() {
        return this.f43bb;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public int __offset(int r3) {
        if (r3 < this.vtable_size) {
            return this.f43bb.getShort(this.vtable_start + r3);
        }
        return 0;
    }

    protected static int __offset(int r1, int r2, ByteBuffer byteBuffer) {
        int capacity = byteBuffer.capacity() - r2;
        return byteBuffer.getShort((r1 + capacity) - byteBuffer.getInt(capacity)) + capacity;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public int __indirect(int r2) {
        return r2 + this.f43bb.getInt(r2);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public static int __indirect(int r0, ByteBuffer byteBuffer) {
        return r0 + byteBuffer.getInt(r0);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public String __string(int r3) {
        return __string(r3, this.f43bb, this.utf8);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public static String __string(int r1, ByteBuffer byteBuffer, Utf8 utf8) {
        int r12 = r1 + byteBuffer.getInt(r1);
        return utf8.decodeUtf8(byteBuffer, r12 + 4, byteBuffer.getInt(r12));
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public int __vector_len(int r2) {
        int r22 = r2 + this.bb_pos;
        return this.f43bb.getInt(r22 + this.f43bb.getInt(r22));
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public int __vector(int r2) {
        int r22 = r2 + this.bb_pos;
        return r22 + this.f43bb.getInt(r22) + 4;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public ByteBuffer __vector_as_bytebuffer(int r3, int r4) {
        int __offset = __offset(r3);
        if (__offset == 0) {
            return null;
        }
        ByteBuffer order = this.f43bb.duplicate().order(ByteOrder.LITTLE_ENDIAN);
        int __vector = __vector(__offset);
        order.position(__vector);
        order.limit(__vector + (__vector_len(__offset) * r4));
        return order;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public ByteBuffer __vector_in_bytebuffer(ByteBuffer byteBuffer, int r3, int r4) {
        int __offset = __offset(r3);
        if (__offset == 0) {
            return null;
        }
        int __vector = __vector(__offset);
        byteBuffer.rewind();
        byteBuffer.limit((__vector_len(__offset) * r4) + __vector);
        byteBuffer.position(__vector);
        return byteBuffer;
    }

    protected Table __union(Table table, int r3) {
        return __union(table, r3, this.f43bb);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public static Table __union(Table table, int r1, ByteBuffer byteBuffer) {
        table.__reset(__indirect(r1, byteBuffer), byteBuffer);
        return table;
    }

    protected static boolean __has_identifier(ByteBuffer byteBuffer, String str) {
        if (str.length() == 4) {
            for (int r2 = 0; r2 < 4; r2++) {
                if (str.charAt(r2) != ((char) byteBuffer.get(byteBuffer.position() + 4 + r2))) {
                    return false;
                }
            }
            return true;
        }
        throw new AssertionError("FlatBuffers: file identifier must be length 4");
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void sortTables(int[] r5, final ByteBuffer byteBuffer) {
        Integer[] numArr = new Integer[r5.length];
        for (int r2 = 0; r2 < r5.length; r2++) {
            numArr[r2] = Integer.valueOf(r5[r2]);
        }
        Arrays.sort(numArr, new Comparator<Integer>() { // from class: androidx.emoji2.text.flatbuffer.Table.1
            @Override // java.util.Comparator
            public int compare(Integer num, Integer num2) {
                return Table.this.keysCompare(num, num2, byteBuffer);
            }
        });
        for (int r1 = 0; r1 < r5.length; r1++) {
            r5[r1] = numArr[r1].intValue();
        }
    }

    protected static int compareStrings(int r8, int r9, ByteBuffer byteBuffer) {
        int r82 = r8 + byteBuffer.getInt(r8);
        int r92 = r9 + byteBuffer.getInt(r9);
        int r0 = byteBuffer.getInt(r82);
        int r1 = byteBuffer.getInt(r92);
        int r83 = r82 + 4;
        int r93 = r92 + 4;
        int min = Math.min(r0, r1);
        for (int r3 = 0; r3 < min; r3++) {
            int r4 = r3 + r83;
            int r6 = r3 + r93;
            if (byteBuffer.get(r4) != byteBuffer.get(r6)) {
                return byteBuffer.get(r4) - byteBuffer.get(r6);
            }
        }
        return r0 - r1;
    }

    protected static int compareStrings(int r7, byte[] bArr, ByteBuffer byteBuffer) {
        int r72 = r7 + byteBuffer.getInt(r7);
        int r0 = byteBuffer.getInt(r72);
        int length = bArr.length;
        int r73 = r72 + 4;
        int min = Math.min(r0, length);
        for (int r3 = 0; r3 < min; r3++) {
            int r4 = r3 + r73;
            if (byteBuffer.get(r4) != bArr[r3]) {
                return byteBuffer.get(r4) - bArr[r3];
            }
        }
        return r0 - length;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void __reset(int r1, ByteBuffer byteBuffer) {
        this.f43bb = byteBuffer;
        if (byteBuffer != null) {
            this.bb_pos = r1;
            int r12 = r1 - byteBuffer.getInt(r1);
            this.vtable_start = r12;
            this.vtable_size = this.f43bb.getShort(r12);
            return;
        }
        this.bb_pos = 0;
        this.vtable_start = 0;
        this.vtable_size = 0;
    }

    public void __reset() {
        __reset(0, null);
    }
}
