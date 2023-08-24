package androidx.emoji2.text.flatbuffer;

import androidx.emoji2.text.flatbuffer.FlexBuffers;
import java.math.BigInteger;
import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;

/* loaded from: classes.dex */
public class FlexBuffersBuilder {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    public static final int BUILDER_FLAG_NONE = 0;
    public static final int BUILDER_FLAG_SHARE_ALL = 7;
    public static final int BUILDER_FLAG_SHARE_KEYS = 1;
    public static final int BUILDER_FLAG_SHARE_KEYS_AND_STRINGS = 3;
    public static final int BUILDER_FLAG_SHARE_KEY_VECTORS = 4;
    public static final int BUILDER_FLAG_SHARE_STRINGS = 2;
    private static final int WIDTH_16 = 1;
    private static final int WIDTH_32 = 2;
    private static final int WIDTH_64 = 3;
    private static final int WIDTH_8 = 0;

    /* renamed from: bb */
    private final ReadWriteBuf f41bb;
    private boolean finished;
    private final int flags;
    private Comparator<Value> keyComparator;
    private final HashMap<String, Integer> keyPool;
    private final ArrayList<Value> stack;
    private final HashMap<String, Integer> stringPool;

    public FlexBuffersBuilder(int r2) {
        this(new ArrayReadWriteBuf(r2), 1);
    }

    public FlexBuffersBuilder() {
        this(256);
    }

    @Deprecated
    public FlexBuffersBuilder(ByteBuffer byteBuffer, int r3) {
        this(new ArrayReadWriteBuf(byteBuffer.array()), r3);
    }

    public FlexBuffersBuilder(ReadWriteBuf readWriteBuf, int r3) {
        this.stack = new ArrayList<>();
        this.keyPool = new HashMap<>();
        this.stringPool = new HashMap<>();
        this.finished = false;
        this.keyComparator = new Comparator<Value>() { // from class: androidx.emoji2.text.flatbuffer.FlexBuffersBuilder.1
            @Override // java.util.Comparator
            public int compare(Value value, Value value2) {
                byte b;
                byte b2;
                int r32 = value.key;
                int r4 = value2.key;
                do {
                    b = FlexBuffersBuilder.this.f41bb.get(r32);
                    b2 = FlexBuffersBuilder.this.f41bb.get(r4);
                    if (b == 0) {
                        return b - b2;
                    }
                    r32++;
                    r4++;
                } while (b == b2);
                return b - b2;
            }
        };
        this.f41bb = readWriteBuf;
        this.flags = r3;
    }

    public FlexBuffersBuilder(ByteBuffer byteBuffer) {
        this(byteBuffer, 1);
    }

    public ReadWriteBuf getBuffer() {
        return this.f41bb;
    }

    public void putBoolean(boolean z) {
        putBoolean(null, z);
    }

    public void putBoolean(String str, boolean z) {
        this.stack.add(Value.bool(putKey(str), z));
    }

    private int putKey(String str) {
        if (str == null) {
            return -1;
        }
        int writePosition = this.f41bb.writePosition();
        if ((this.flags & 1) != 0) {
            Integer num = this.keyPool.get(str);
            if (num == null) {
                byte[] bytes = str.getBytes(StandardCharsets.UTF_8);
                this.f41bb.put(bytes, 0, bytes.length);
                this.f41bb.put((byte) 0);
                this.keyPool.put(str, Integer.valueOf(writePosition));
                return writePosition;
            }
            return num.intValue();
        }
        byte[] bytes2 = str.getBytes(StandardCharsets.UTF_8);
        this.f41bb.put(bytes2, 0, bytes2.length);
        this.f41bb.put((byte) 0);
        this.keyPool.put(str, Integer.valueOf(writePosition));
        return writePosition;
    }

    public void putInt(int r2) {
        putInt((String) null, r2);
    }

    public void putInt(String str, int r4) {
        putInt(str, r4);
    }

    public void putInt(String str, long j) {
        int putKey = putKey(str);
        if (-128 <= j && j <= 127) {
            this.stack.add(Value.int8(putKey, (int) j));
        } else if (-32768 <= j && j <= 32767) {
            this.stack.add(Value.int16(putKey, (int) j));
        } else if (-2147483648L <= j && j <= 2147483647L) {
            this.stack.add(Value.int32(putKey, (int) j));
        } else {
            this.stack.add(Value.int64(putKey, j));
        }
    }

    public void putInt(long j) {
        putInt((String) null, j);
    }

    public void putUInt(int r3) {
        putUInt(null, r3);
    }

    public void putUInt(long j) {
        putUInt(null, j);
    }

    public void putUInt64(BigInteger bigInteger) {
        putUInt64(null, bigInteger.longValue());
    }

    private void putUInt64(String str, long j) {
        this.stack.add(Value.uInt64(putKey(str), j));
    }

    private void putUInt(String str, long j) {
        Value uInt64;
        int putKey = putKey(str);
        int widthUInBits = widthUInBits(j);
        if (widthUInBits == 0) {
            uInt64 = Value.uInt8(putKey, (int) j);
        } else if (widthUInBits == 1) {
            uInt64 = Value.uInt16(putKey, (int) j);
        } else if (widthUInBits == 2) {
            uInt64 = Value.uInt32(putKey, (int) j);
        } else {
            uInt64 = Value.uInt64(putKey, j);
        }
        this.stack.add(uInt64);
    }

    public void putFloat(float f) {
        putFloat((String) null, f);
    }

    public void putFloat(String str, float f) {
        this.stack.add(Value.float32(putKey(str), f));
    }

    public void putFloat(double d) {
        putFloat((String) null, d);
    }

    public void putFloat(String str, double d) {
        this.stack.add(Value.float64(putKey(str), d));
    }

    public int putString(String str) {
        return putString(null, str);
    }

    public int putString(String str, String str2) {
        long j;
        int putKey = putKey(str);
        if ((this.flags & 2) != 0) {
            Integer num = this.stringPool.get(str2);
            if (num == null) {
                Value writeString = writeString(putKey, str2);
                this.stringPool.put(str2, Integer.valueOf((int) writeString.iValue));
                this.stack.add(writeString);
                j = writeString.iValue;
            } else {
                this.stack.add(Value.blob(putKey, num.intValue(), 5, widthUInBits(str2.length())));
                return num.intValue();
            }
        } else {
            Value writeString2 = writeString(putKey, str2);
            this.stack.add(writeString2);
            j = writeString2.iValue;
        }
        return (int) j;
    }

    private Value writeString(int r3, String str) {
        return writeBlob(r3, str.getBytes(StandardCharsets.UTF_8), 5, true);
    }

    static int widthUInBits(long j) {
        if (j <= FlexBuffers.Unsigned.byteToUnsignedInt((byte) -1)) {
            return 0;
        }
        if (j <= FlexBuffers.Unsigned.shortToUnsignedInt((short) -1)) {
            return 1;
        }
        return j <= FlexBuffers.Unsigned.intToUnsignedLong(-1) ? 2 : 3;
    }

    private Value writeBlob(int r6, byte[] bArr, int r8, boolean z) {
        int widthUInBits = widthUInBits(bArr.length);
        writeInt(bArr.length, align(widthUInBits));
        int writePosition = this.f41bb.writePosition();
        this.f41bb.put(bArr, 0, bArr.length);
        if (z) {
            this.f41bb.put((byte) 0);
        }
        return Value.blob(r6, writePosition, r8, widthUInBits);
    }

    private int align(int r4) {
        int r42 = 1 << r4;
        int paddingBytes = Value.paddingBytes(this.f41bb.writePosition(), r42);
        while (true) {
            int r1 = paddingBytes - 1;
            if (paddingBytes == 0) {
                return r42;
            }
            this.f41bb.put((byte) 0);
            paddingBytes = r1;
        }
    }

    private void writeInt(long j, int r4) {
        if (r4 == 1) {
            this.f41bb.put((byte) j);
        } else if (r4 == 2) {
            this.f41bb.putShort((short) j);
        } else if (r4 == 4) {
            this.f41bb.putInt((int) j);
        } else if (r4 != 8) {
        } else {
            this.f41bb.putLong(j);
        }
    }

    public int putBlob(byte[] bArr) {
        return putBlob(null, bArr);
    }

    public int putBlob(String str, byte[] bArr) {
        Value writeBlob = writeBlob(putKey(str), bArr, 25, false);
        this.stack.add(writeBlob);
        return (int) writeBlob.iValue;
    }

    public int startVector() {
        return this.stack.size();
    }

    public int endVector(String str, int r9, boolean z, boolean z2) {
        Value createVector = createVector(putKey(str), r9, this.stack.size() - r9, z, z2, null);
        while (this.stack.size() > r9) {
            ArrayList<Value> arrayList = this.stack;
            arrayList.remove(arrayList.size() - 1);
        }
        this.stack.add(createVector);
        return (int) createVector.iValue;
    }

    public ByteBuffer finish() {
        int align = align(this.stack.get(0).elemWidth(this.f41bb.writePosition(), 0));
        writeAny(this.stack.get(0), align);
        this.f41bb.put(this.stack.get(0).storedPackedType());
        this.f41bb.put((byte) align);
        this.finished = true;
        return ByteBuffer.wrap(this.f41bb.data(), 0, this.f41bb.writePosition());
    }

    private Value createVector(int r16, int r17, int r18, boolean z, boolean z2, Value value) {
        int r7;
        int r11;
        int r2 = r18;
        long j = r2;
        int max = Math.max(0, widthUInBits(j));
        if (value != null) {
            max = Math.max(max, value.elemWidth(this.f41bb.writePosition(), 0));
            r7 = 3;
        } else {
            r7 = 1;
        }
        int r8 = 4;
        int r12 = max;
        for (int r5 = r17; r5 < this.stack.size(); r5++) {
            r12 = Math.max(r12, this.stack.get(r5).elemWidth(this.f41bb.writePosition(), r5 + r7));
            if (z && r5 == r17) {
                r8 = this.stack.get(r5).type;
                if (!FlexBuffers.isTypedVectorElementType(r8)) {
                    throw new FlexBuffers.FlexBufferException("TypedVector does not support this element type");
                }
            }
        }
        int r9 = r17;
        int align = align(r12);
        if (value != null) {
            writeOffset(value.iValue, align);
            writeInt(1 << value.minBitWidth, align);
        }
        if (!z2) {
            writeInt(j, align);
        }
        int writePosition = this.f41bb.writePosition();
        for (int r4 = r9; r4 < this.stack.size(); r4++) {
            writeAny(this.stack.get(r4), align);
        }
        if (!z) {
            while (r9 < this.stack.size()) {
                this.f41bb.put(this.stack.get(r9).storedPackedType(r12));
                r9++;
            }
        }
        if (value != null) {
            r11 = 9;
        } else if (z) {
            if (!z2) {
                r2 = 0;
            }
            r11 = FlexBuffers.toTypedVector(r8, r2);
        } else {
            r11 = 10;
        }
        return new Value(r16, r11, r12, writePosition);
    }

    private void writeOffset(long j, int r5) {
        writeInt((int) (this.f41bb.writePosition() - j), r5);
    }

    private void writeAny(Value value, int r4) {
        int r0 = value.type;
        if (r0 != 0 && r0 != 1 && r0 != 2) {
            if (r0 == 3) {
                writeDouble(value.dValue, r4);
                return;
            } else if (r0 != 26) {
                writeOffset(value.iValue, r4);
                return;
            }
        }
        writeInt(value.iValue, r4);
    }

    private void writeDouble(double d, int r4) {
        if (r4 == 4) {
            this.f41bb.putFloat((float) d);
        } else if (r4 == 8) {
            this.f41bb.putDouble(d);
        }
    }

    public int startMap() {
        return this.stack.size();
    }

    public int endMap(String str, int r9) {
        int putKey = putKey(str);
        ArrayList<Value> arrayList = this.stack;
        Collections.sort(arrayList.subList(r9, arrayList.size()), this.keyComparator);
        Value createVector = createVector(putKey, r9, this.stack.size() - r9, false, false, createKeyVector(r9, this.stack.size() - r9));
        while (this.stack.size() > r9) {
            ArrayList<Value> arrayList2 = this.stack;
            arrayList2.remove(arrayList2.size() - 1);
        }
        this.stack.add(createVector);
        return (int) createVector.iValue;
    }

    private Value createKeyVector(int r14, int r15) {
        long j = r15;
        int max = Math.max(0, widthUInBits(j));
        int r152 = r14;
        while (r152 < this.stack.size()) {
            r152++;
            max = Math.max(max, Value.elemWidth(4, 0, this.stack.get(r152).key, this.f41bb.writePosition(), r152));
        }
        int align = align(max);
        writeInt(j, align);
        int writePosition = this.f41bb.writePosition();
        while (r14 < this.stack.size()) {
            int r1 = this.stack.get(r14).key;
            writeOffset(this.stack.get(r14).key, align);
            r14++;
        }
        return new Value(-1, FlexBuffers.toTypedVector(4, 0), max, writePosition);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public static class Value {
        static final /* synthetic */ boolean $assertionsDisabled = false;
        final double dValue;
        long iValue;
        int key;
        final int minBitWidth;
        final int type;

        private static byte packedType(int r0, int r1) {
            return (byte) (r0 | (r1 << 2));
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static int paddingBytes(int r0, int r1) {
            return ((~r0) + 1) & (r1 - 1);
        }

        Value(int r1, int r2, int r3, long j) {
            this.key = r1;
            this.type = r2;
            this.minBitWidth = r3;
            this.iValue = j;
            this.dValue = Double.MIN_VALUE;
        }

        Value(int r1, int r2, int r3, double d) {
            this.key = r1;
            this.type = r2;
            this.minBitWidth = r3;
            this.dValue = d;
            this.iValue = Long.MIN_VALUE;
        }

        static Value bool(int r7, boolean z) {
            return new Value(r7, 26, 0, z ? 1L : 0L);
        }

        static Value blob(int r7, int r8, int r9, int r10) {
            return new Value(r7, r9, r10, r8);
        }

        static Value int8(int r7, int r8) {
            return new Value(r7, 1, 0, r8);
        }

        static Value int16(int r7, int r8) {
            return new Value(r7, 1, 1, r8);
        }

        static Value int32(int r7, int r8) {
            return new Value(r7, 1, 2, r8);
        }

        static Value int64(int r7, long j) {
            return new Value(r7, 1, 3, j);
        }

        static Value uInt8(int r7, int r8) {
            return new Value(r7, 2, 0, r8);
        }

        static Value uInt16(int r7, int r8) {
            return new Value(r7, 2, 1, r8);
        }

        static Value uInt32(int r7, int r8) {
            return new Value(r7, 2, 2, r8);
        }

        static Value uInt64(int r7, long j) {
            return new Value(r7, 2, 3, j);
        }

        static Value float32(int r7, float f) {
            return new Value(r7, 3, 2, f);
        }

        static Value float64(int r7, double d) {
            return new Value(r7, 3, 3, d);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public byte storedPackedType() {
            return storedPackedType(0);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public byte storedPackedType(int r2) {
            return packedType(storedWidth(r2), this.type);
        }

        private int storedWidth(int r2) {
            if (FlexBuffers.isTypeInline(this.type)) {
                return Math.max(this.minBitWidth, r2);
            }
            return this.minBitWidth;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public int elemWidth(int r7, int r8) {
            return elemWidth(this.type, this.minBitWidth, this.iValue, r7, r8);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static int elemWidth(int r5, int r6, long j, int r9, int r10) {
            if (FlexBuffers.isTypeInline(r5)) {
                return r6;
            }
            for (int r52 = 1; r52 <= 32; r52 *= 2) {
                int widthUInBits = FlexBuffersBuilder.widthUInBits((int) (((paddingBytes(r9, r52) + r9) + (r10 * r52)) - j));
                if ((1 << widthUInBits) == r52) {
                    return widthUInBits;
                }
            }
            return 3;
        }
    }
}
