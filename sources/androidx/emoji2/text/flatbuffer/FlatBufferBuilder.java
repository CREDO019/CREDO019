package androidx.emoji2.text.flatbuffer;

import java.io.IOException;
import java.io.InputStream;
import java.nio.BufferUnderflowException;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.Arrays;

/* loaded from: classes.dex */
public class FlatBufferBuilder {
    static final /* synthetic */ boolean $assertionsDisabled = false;

    /* renamed from: bb */
    ByteBuffer f38bb;
    ByteBufferFactory bb_factory;
    boolean finished;
    boolean force_defaults;
    int minalign;
    boolean nested;
    int num_vtables;
    int object_start;
    int space;
    final Utf8 utf8;
    int vector_num_elems;
    int[] vtable;
    int vtable_in_use;
    int[] vtables;

    /* loaded from: classes.dex */
    public static abstract class ByteBufferFactory {
        public abstract ByteBuffer newByteBuffer(int r1);

        public void releaseByteBuffer(ByteBuffer byteBuffer) {
        }
    }

    public FlatBufferBuilder(int r3, ByteBufferFactory byteBufferFactory) {
        this(r3, byteBufferFactory, null, Utf8.getDefault());
    }

    public FlatBufferBuilder(int r4, ByteBufferFactory byteBufferFactory, ByteBuffer byteBuffer, Utf8 utf8) {
        this.minalign = 1;
        this.vtable = null;
        this.vtable_in_use = 0;
        this.nested = false;
        this.finished = false;
        this.vtables = new int[16];
        this.num_vtables = 0;
        this.vector_num_elems = 0;
        this.force_defaults = false;
        r4 = r4 <= 0 ? 1 : r4;
        this.bb_factory = byteBufferFactory;
        if (byteBuffer != null) {
            this.f38bb = byteBuffer;
            byteBuffer.clear();
            this.f38bb.order(ByteOrder.LITTLE_ENDIAN);
        } else {
            this.f38bb = byteBufferFactory.newByteBuffer(r4);
        }
        this.utf8 = utf8;
        this.space = this.f38bb.capacity();
    }

    public FlatBufferBuilder(int r4) {
        this(r4, HeapByteBufferFactory.INSTANCE, null, Utf8.getDefault());
    }

    public FlatBufferBuilder() {
        this(1024);
    }

    public FlatBufferBuilder(ByteBuffer byteBuffer, ByteBufferFactory byteBufferFactory) {
        this(byteBuffer.capacity(), byteBufferFactory, byteBuffer, Utf8.getDefault());
    }

    public FlatBufferBuilder(ByteBuffer byteBuffer) {
        this(byteBuffer, new HeapByteBufferFactory());
    }

    public FlatBufferBuilder init(ByteBuffer byteBuffer, ByteBufferFactory byteBufferFactory) {
        this.bb_factory = byteBufferFactory;
        this.f38bb = byteBuffer;
        byteBuffer.clear();
        this.f38bb.order(ByteOrder.LITTLE_ENDIAN);
        this.minalign = 1;
        this.space = this.f38bb.capacity();
        this.vtable_in_use = 0;
        this.nested = false;
        this.finished = false;
        this.object_start = 0;
        this.num_vtables = 0;
        this.vector_num_elems = 0;
        return this;
    }

    /* loaded from: classes.dex */
    public static final class HeapByteBufferFactory extends ByteBufferFactory {
        public static final HeapByteBufferFactory INSTANCE = new HeapByteBufferFactory();

        @Override // androidx.emoji2.text.flatbuffer.FlatBufferBuilder.ByteBufferFactory
        public ByteBuffer newByteBuffer(int r2) {
            return ByteBuffer.allocate(r2).order(ByteOrder.LITTLE_ENDIAN);
        }
    }

    public static boolean isFieldPresent(Table table, int r1) {
        return table.__offset(r1) != 0;
    }

    public void clear() {
        this.space = this.f38bb.capacity();
        this.f38bb.clear();
        this.minalign = 1;
        while (true) {
            int r0 = this.vtable_in_use;
            if (r0 <= 0) {
                this.vtable_in_use = 0;
                this.nested = false;
                this.finished = false;
                this.object_start = 0;
                this.num_vtables = 0;
                this.vector_num_elems = 0;
                return;
            }
            int[] r2 = this.vtable;
            int r02 = r0 - 1;
            this.vtable_in_use = r02;
            r2[r02] = 0;
        }
    }

    static ByteBuffer growByteBuffer(ByteBuffer byteBuffer, ByteBufferFactory byteBufferFactory) {
        int capacity = byteBuffer.capacity();
        if (((-1073741824) & capacity) != 0) {
            throw new AssertionError("FlatBuffers: cannot grow buffer beyond 2 gigabytes.");
        }
        int r1 = capacity == 0 ? 1 : capacity << 1;
        byteBuffer.position(0);
        ByteBuffer newByteBuffer = byteBufferFactory.newByteBuffer(r1);
        newByteBuffer.position(newByteBuffer.clear().capacity() - capacity);
        newByteBuffer.put(byteBuffer);
        return newByteBuffer;
    }

    public int offset() {
        return this.f38bb.capacity() - this.space;
    }

    public void pad(int r5) {
        for (int r1 = 0; r1 < r5; r1++) {
            ByteBuffer byteBuffer = this.f38bb;
            int r3 = this.space - 1;
            this.space = r3;
            byteBuffer.put(r3, (byte) 0);
        }
    }

    public void prep(int r5, int r6) {
        if (r5 > this.minalign) {
            this.minalign = r5;
        }
        int r0 = ((~((this.f38bb.capacity() - this.space) + r6)) + 1) & (r5 - 1);
        while (this.space < r0 + r5 + r6) {
            int capacity = this.f38bb.capacity();
            ByteBuffer byteBuffer = this.f38bb;
            ByteBuffer growByteBuffer = growByteBuffer(byteBuffer, this.bb_factory);
            this.f38bb = growByteBuffer;
            if (byteBuffer != growByteBuffer) {
                this.bb_factory.releaseByteBuffer(byteBuffer);
            }
            this.space += this.f38bb.capacity() - capacity;
        }
        pad(r0);
    }

    public void putBoolean(boolean z) {
        ByteBuffer byteBuffer = this.f38bb;
        int r1 = this.space - 1;
        this.space = r1;
        byteBuffer.put(r1, z ? (byte) 1 : (byte) 0);
    }

    public void putByte(byte b) {
        ByteBuffer byteBuffer = this.f38bb;
        int r1 = this.space - 1;
        this.space = r1;
        byteBuffer.put(r1, b);
    }

    public void putShort(short s) {
        ByteBuffer byteBuffer = this.f38bb;
        int r1 = this.space - 2;
        this.space = r1;
        byteBuffer.putShort(r1, s);
    }

    public void putInt(int r3) {
        ByteBuffer byteBuffer = this.f38bb;
        int r1 = this.space - 4;
        this.space = r1;
        byteBuffer.putInt(r1, r3);
    }

    public void putLong(long j) {
        ByteBuffer byteBuffer = this.f38bb;
        int r1 = this.space - 8;
        this.space = r1;
        byteBuffer.putLong(r1, j);
    }

    public void putFloat(float f) {
        ByteBuffer byteBuffer = this.f38bb;
        int r1 = this.space - 4;
        this.space = r1;
        byteBuffer.putFloat(r1, f);
    }

    public void putDouble(double d) {
        ByteBuffer byteBuffer = this.f38bb;
        int r1 = this.space - 8;
        this.space = r1;
        byteBuffer.putDouble(r1, d);
    }

    public void addBoolean(boolean z) {
        prep(1, 0);
        putBoolean(z);
    }

    public void addByte(byte b) {
        prep(1, 0);
        putByte(b);
    }

    public void addShort(short s) {
        prep(2, 0);
        putShort(s);
    }

    public void addInt(int r3) {
        prep(4, 0);
        putInt(r3);
    }

    public void addLong(long j) {
        prep(8, 0);
        putLong(j);
    }

    public void addFloat(float f) {
        prep(4, 0);
        putFloat(f);
    }

    public void addDouble(double d) {
        prep(8, 0);
        putDouble(d);
    }

    public void addOffset(int r3) {
        prep(4, 0);
        putInt((offset() - r3) + 4);
    }

    public void startVector(int r1, int r2, int r3) {
        notNested();
        this.vector_num_elems = r2;
        int r12 = r1 * r2;
        prep(4, r12);
        prep(r3, r12);
        this.nested = true;
    }

    public int endVector() {
        if (!this.nested) {
            throw new AssertionError("FlatBuffers: endVector called without startVector");
        }
        this.nested = false;
        putInt(this.vector_num_elems);
        return offset();
    }

    public ByteBuffer createUnintializedVector(int r2, int r3, int r4) {
        int r0 = r2 * r3;
        startVector(r2, r3, r4);
        ByteBuffer byteBuffer = this.f38bb;
        int r32 = this.space - r0;
        this.space = r32;
        byteBuffer.position(r32);
        ByteBuffer order = this.f38bb.slice().order(ByteOrder.LITTLE_ENDIAN);
        order.limit(r0);
        return order;
    }

    public int createVectorOfTables(int[] r3) {
        notNested();
        startVector(4, r3.length, 4);
        for (int length = r3.length - 1; length >= 0; length--) {
            addOffset(r3[length]);
        }
        return endVector();
    }

    public <T extends Table> int createSortedVectorOfTables(T t, int[] r3) {
        t.sortTables(r3, this.f38bb);
        return createVectorOfTables(r3);
    }

    public int createString(CharSequence charSequence) {
        int encodedLength = this.utf8.encodedLength(charSequence);
        addByte((byte) 0);
        startVector(1, encodedLength, 1);
        ByteBuffer byteBuffer = this.f38bb;
        int r2 = this.space - encodedLength;
        this.space = r2;
        byteBuffer.position(r2);
        this.utf8.encodeUtf8(charSequence, this.f38bb);
        return endVector();
    }

    public int createString(ByteBuffer byteBuffer) {
        int remaining = byteBuffer.remaining();
        addByte((byte) 0);
        startVector(1, remaining, 1);
        ByteBuffer byteBuffer2 = this.f38bb;
        int r2 = this.space - remaining;
        this.space = r2;
        byteBuffer2.position(r2);
        this.f38bb.put(byteBuffer);
        return endVector();
    }

    public int createByteVector(byte[] bArr) {
        int length = bArr.length;
        startVector(1, length, 1);
        ByteBuffer byteBuffer = this.f38bb;
        int r2 = this.space - length;
        this.space = r2;
        byteBuffer.position(r2);
        this.f38bb.put(bArr);
        return endVector();
    }

    public int createByteVector(byte[] bArr, int r4, int r5) {
        startVector(1, r5, 1);
        ByteBuffer byteBuffer = this.f38bb;
        int r1 = this.space - r5;
        this.space = r1;
        byteBuffer.position(r1);
        this.f38bb.put(bArr, r4, r5);
        return endVector();
    }

    public int createByteVector(ByteBuffer byteBuffer) {
        int remaining = byteBuffer.remaining();
        startVector(1, remaining, 1);
        ByteBuffer byteBuffer2 = this.f38bb;
        int r2 = this.space - remaining;
        this.space = r2;
        byteBuffer2.position(r2);
        this.f38bb.put(byteBuffer);
        return endVector();
    }

    public void finished() {
        if (!this.finished) {
            throw new AssertionError("FlatBuffers: you can only access the serialized buffer after it has been finished by FlatBufferBuilder.finish().");
        }
    }

    public void notNested() {
        if (this.nested) {
            throw new AssertionError("FlatBuffers: object serialization must not be nested.");
        }
    }

    public void Nested(int r2) {
        if (r2 != offset()) {
            throw new AssertionError("FlatBuffers: struct must be serialized inline.");
        }
    }

    public void startTable(int r3) {
        notNested();
        int[] r0 = this.vtable;
        if (r0 == null || r0.length < r3) {
            this.vtable = new int[r3];
        }
        this.vtable_in_use = r3;
        Arrays.fill(this.vtable, 0, r3, 0);
        this.nested = true;
        this.object_start = offset();
    }

    public void addBoolean(int r2, boolean z, boolean z2) {
        if (this.force_defaults || z != z2) {
            addBoolean(z);
            slot(r2);
        }
    }

    public void addByte(int r2, byte b, int r4) {
        if (this.force_defaults || b != r4) {
            addByte(b);
            slot(r2);
        }
    }

    public void addShort(int r2, short s, int r4) {
        if (this.force_defaults || s != r4) {
            addShort(s);
            slot(r2);
        }
    }

    public void addInt(int r2, int r3, int r4) {
        if (this.force_defaults || r3 != r4) {
            addInt(r3);
            slot(r2);
        }
    }

    public void addLong(int r2, long j, long j2) {
        if (this.force_defaults || j != j2) {
            addLong(j);
            slot(r2);
        }
    }

    public void addFloat(int r4, float f, double d) {
        if (this.force_defaults || f != d) {
            addFloat(f);
            slot(r4);
        }
    }

    public void addDouble(int r2, double d, double d2) {
        if (this.force_defaults || d != d2) {
            addDouble(d);
            slot(r2);
        }
    }

    public void addOffset(int r2, int r3, int r4) {
        if (this.force_defaults || r3 != r4) {
            addOffset(r3);
            slot(r2);
        }
    }

    public void addStruct(int r1, int r2, int r3) {
        if (r2 != r3) {
            Nested(r2);
            slot(r1);
        }
    }

    public void slot(int r3) {
        this.vtable[r3] = offset();
    }

    public int endTable() {
        int r3;
        if (this.vtable == null || !this.nested) {
            throw new AssertionError("FlatBuffers: endTable called without startTable");
        }
        addInt(0);
        int offset = offset();
        int r2 = this.vtable_in_use - 1;
        while (r2 >= 0 && this.vtable[r2] == 0) {
            r2--;
        }
        int r32 = r2 + 1;
        while (r2 >= 0) {
            int[] r4 = this.vtable;
            addShort((short) (r4[r2] != 0 ? offset - r4[r2] : 0));
            r2--;
        }
        addShort((short) (offset - this.object_start));
        addShort((short) ((r32 + 2) * 2));
        int r33 = 0;
        loop2: while (true) {
            if (r33 >= this.num_vtables) {
                r3 = 0;
                break;
            }
            int capacity = this.f38bb.capacity() - this.vtables[r33];
            int r5 = this.space;
            short s = this.f38bb.getShort(capacity);
            if (s == this.f38bb.getShort(r5)) {
                for (int r7 = 2; r7 < s; r7 += 2) {
                    if (this.f38bb.getShort(capacity + r7) != this.f38bb.getShort(r5 + r7)) {
                        break;
                    }
                }
                r3 = this.vtables[r33];
                break loop2;
            }
            r33++;
        }
        if (r3 != 0) {
            int capacity2 = this.f38bb.capacity() - offset;
            this.space = capacity2;
            this.f38bb.putInt(capacity2, r3 - offset);
        } else {
            int r34 = this.num_vtables;
            int[] r42 = this.vtables;
            if (r34 == r42.length) {
                this.vtables = Arrays.copyOf(r42, r34 * 2);
            }
            int[] r22 = this.vtables;
            int r35 = this.num_vtables;
            this.num_vtables = r35 + 1;
            r22[r35] = offset();
            ByteBuffer byteBuffer = this.f38bb;
            byteBuffer.putInt(byteBuffer.capacity() - offset, offset() - offset);
        }
        this.nested = false;
        return offset;
    }

    public void required(int r3, int r4) {
        int capacity = this.f38bb.capacity() - r3;
        if (this.f38bb.getShort((capacity - this.f38bb.getInt(capacity)) + r4) != 0) {
            return;
        }
        throw new AssertionError("FlatBuffers: field " + r4 + " must be set");
    }

    protected void finish(int r4, boolean z) {
        prep(this.minalign, (z ? 4 : 0) + 4);
        addOffset(r4);
        if (z) {
            addInt(this.f38bb.capacity() - this.space);
        }
        this.f38bb.position(this.space);
        this.finished = true;
    }

    public void finish(int r2) {
        finish(r2, false);
    }

    public void finishSizePrefixed(int r2) {
        finish(r2, true);
    }

    protected void finish(int r4, String str, boolean z) {
        prep(this.minalign, (z ? 4 : 0) + 8);
        if (str.length() != 4) {
            throw new AssertionError("FlatBuffers: file identifier must be length 4");
        }
        for (int r0 = 3; r0 >= 0; r0--) {
            addByte((byte) str.charAt(r0));
        }
        finish(r4, z);
    }

    public void finish(int r2, String str) {
        finish(r2, str, false);
    }

    public void finishSizePrefixed(int r2, String str) {
        finish(r2, str, true);
    }

    public FlatBufferBuilder forceDefaults(boolean z) {
        this.force_defaults = z;
        return this;
    }

    public ByteBuffer dataBuffer() {
        finished();
        return this.f38bb;
    }

    @Deprecated
    private int dataStart() {
        finished();
        return this.space;
    }

    public byte[] sizedByteArray(int r2, int r3) {
        finished();
        byte[] bArr = new byte[r3];
        this.f38bb.position(r2);
        this.f38bb.get(bArr);
        return bArr;
    }

    public byte[] sizedByteArray() {
        return sizedByteArray(this.space, this.f38bb.capacity() - this.space);
    }

    public InputStream sizedInputStream() {
        finished();
        ByteBuffer duplicate = this.f38bb.duplicate();
        duplicate.position(this.space);
        duplicate.limit(this.f38bb.capacity());
        return new ByteBufferBackedInputStream(duplicate);
    }

    /* loaded from: classes.dex */
    static class ByteBufferBackedInputStream extends InputStream {
        ByteBuffer buf;

        public ByteBufferBackedInputStream(ByteBuffer byteBuffer) {
            this.buf = byteBuffer;
        }

        @Override // java.io.InputStream
        public int read() throws IOException {
            try {
                return this.buf.get() & 255;
            } catch (BufferUnderflowException unused) {
                return -1;
            }
        }
    }
}
