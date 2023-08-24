package androidx.emoji2.text.flatbuffer;

import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;
import kotlin.UShort;
import kotlin.text.Typography;
import org.bouncycastle.asn1.cmc.BodyPartID;

/* loaded from: classes.dex */
public class FlexBuffers {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    private static final ReadBuf EMPTY_BB = new ArrayReadWriteBuf(new byte[]{0}, 1);
    public static final int FBT_BLOB = 25;
    public static final int FBT_BOOL = 26;
    public static final int FBT_FLOAT = 3;
    public static final int FBT_INDIRECT_FLOAT = 8;
    public static final int FBT_INDIRECT_INT = 6;
    public static final int FBT_INDIRECT_UINT = 7;
    public static final int FBT_INT = 1;
    public static final int FBT_KEY = 4;
    public static final int FBT_MAP = 9;
    public static final int FBT_NULL = 0;
    public static final int FBT_STRING = 5;
    public static final int FBT_UINT = 2;
    public static final int FBT_VECTOR = 10;
    public static final int FBT_VECTOR_BOOL = 36;
    public static final int FBT_VECTOR_FLOAT = 13;
    public static final int FBT_VECTOR_FLOAT2 = 18;
    public static final int FBT_VECTOR_FLOAT3 = 21;
    public static final int FBT_VECTOR_FLOAT4 = 24;
    public static final int FBT_VECTOR_INT = 11;
    public static final int FBT_VECTOR_INT2 = 16;
    public static final int FBT_VECTOR_INT3 = 19;
    public static final int FBT_VECTOR_INT4 = 22;
    public static final int FBT_VECTOR_KEY = 14;
    public static final int FBT_VECTOR_STRING_DEPRECATED = 15;
    public static final int FBT_VECTOR_UINT = 12;
    public static final int FBT_VECTOR_UINT2 = 17;
    public static final int FBT_VECTOR_UINT3 = 20;
    public static final int FBT_VECTOR_UINT4 = 23;

    /* JADX INFO: Access modifiers changed from: package-private */
    public static boolean isTypeInline(int r1) {
        return r1 <= 3 || r1 == 26;
    }

    static boolean isTypedVector(int r1) {
        return (r1 >= 11 && r1 <= 15) || r1 == 36;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static boolean isTypedVectorElementType(int r2) {
        return (r2 >= 1 && r2 <= 4) || r2 == 26;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int toTypedVector(int r1, int r2) {
        if (r2 != 0) {
            if (r2 != 2) {
                if (r2 != 3) {
                    if (r2 != 4) {
                        return 0;
                    }
                    return (r1 - 1) + 22;
                }
                return (r1 - 1) + 19;
            }
            return (r1 - 1) + 16;
        }
        return (r1 - 1) + 11;
    }

    static int toTypedVectorElementType(int r0) {
        return (r0 - 11) + 1;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static int indirect(ReadBuf readBuf, int r3, int r4) {
        return (int) (r3 - readUInt(readBuf, r3, r4));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static long readUInt(ReadBuf readBuf, int r2, int r3) {
        if (r3 != 1) {
            if (r3 != 2) {
                if (r3 != 4) {
                    if (r3 != 8) {
                        return -1L;
                    }
                    return readBuf.getLong(r2);
                }
                return Unsigned.intToUnsignedLong(readBuf.getInt(r2));
            }
            return Unsigned.shortToUnsignedInt(readBuf.getShort(r2));
        }
        return Unsigned.byteToUnsignedInt(readBuf.get(r2));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static int readInt(ReadBuf readBuf, int r1, int r2) {
        return (int) readLong(readBuf, r1, r2);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static long readLong(ReadBuf readBuf, int r2, int r3) {
        int r1;
        if (r3 == 1) {
            r1 = readBuf.get(r2);
        } else if (r3 == 2) {
            r1 = readBuf.getShort(r2);
        } else if (r3 != 4) {
            if (r3 != 8) {
                return -1L;
            }
            return readBuf.getLong(r2);
        } else {
            r1 = readBuf.getInt(r2);
        }
        return r1;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static double readDouble(ReadBuf readBuf, int r2, int r3) {
        if (r3 != 4) {
            if (r3 != 8) {
                return -1.0d;
            }
            return readBuf.getDouble(r2);
        }
        return readBuf.getFloat(r2);
    }

    @Deprecated
    public static Reference getRoot(ByteBuffer byteBuffer) {
        return getRoot(byteBuffer.hasArray() ? new ArrayReadWriteBuf(byteBuffer.array(), byteBuffer.limit()) : new ByteBufferReadWriteBuf(byteBuffer));
    }

    public static Reference getRoot(ReadBuf readBuf) {
        int limit = readBuf.limit() - 1;
        byte b = readBuf.get(limit);
        int r0 = limit - 1;
        return new Reference(readBuf, r0 - b, b, Unsigned.byteToUnsignedInt(readBuf.get(r0)));
    }

    /* loaded from: classes.dex */
    public static class Reference {
        private static final Reference NULL_REFERENCE = new Reference(FlexBuffers.EMPTY_BB, 0, 1, 0);

        /* renamed from: bb */
        private ReadBuf f40bb;
        private int byteWidth;
        private int end;
        private int parentWidth;
        private int type;

        Reference(ReadBuf readBuf, int r10, int r11, int r12) {
            this(readBuf, r10, r11, 1 << (r12 & 3), r12 >> 2);
        }

        Reference(ReadBuf readBuf, int r2, int r3, int r4, int r5) {
            this.f40bb = readBuf;
            this.end = r2;
            this.parentWidth = r3;
            this.byteWidth = r4;
            this.type = r5;
        }

        public int getType() {
            return this.type;
        }

        public boolean isNull() {
            return this.type == 0;
        }

        public boolean isBoolean() {
            return this.type == 26;
        }

        public boolean isNumeric() {
            return isIntOrUInt() || isFloat();
        }

        public boolean isIntOrUInt() {
            return isInt() || isUInt();
        }

        public boolean isFloat() {
            int r0 = this.type;
            return r0 == 3 || r0 == 8;
        }

        public boolean isInt() {
            int r0 = this.type;
            return r0 == 1 || r0 == 6;
        }

        public boolean isUInt() {
            int r0 = this.type;
            return r0 == 2 || r0 == 7;
        }

        public boolean isString() {
            return this.type == 5;
        }

        public boolean isKey() {
            return this.type == 4;
        }

        public boolean isVector() {
            int r0 = this.type;
            return r0 == 10 || r0 == 9;
        }

        public boolean isTypedVector() {
            return FlexBuffers.isTypedVector(this.type);
        }

        public boolean isMap() {
            return this.type == 9;
        }

        public boolean isBlob() {
            return this.type == 25;
        }

        public int asInt() {
            long readUInt;
            int r0 = this.type;
            if (r0 == 1) {
                return FlexBuffers.readInt(this.f40bb, this.end, this.parentWidth);
            }
            if (r0 == 2) {
                readUInt = FlexBuffers.readUInt(this.f40bb, this.end, this.parentWidth);
            } else if (r0 == 3) {
                return (int) FlexBuffers.readDouble(this.f40bb, this.end, this.parentWidth);
            } else {
                if (r0 == 5) {
                    return Integer.parseInt(asString());
                }
                if (r0 == 6) {
                    ReadBuf readBuf = this.f40bb;
                    return FlexBuffers.readInt(readBuf, FlexBuffers.indirect(readBuf, this.end, this.parentWidth), this.byteWidth);
                } else if (r0 != 7) {
                    if (r0 == 8) {
                        ReadBuf readBuf2 = this.f40bb;
                        return (int) FlexBuffers.readDouble(readBuf2, FlexBuffers.indirect(readBuf2, this.end, this.parentWidth), this.byteWidth);
                    } else if (r0 != 10) {
                        if (r0 != 26) {
                            return 0;
                        }
                        return FlexBuffers.readInt(this.f40bb, this.end, this.parentWidth);
                    } else {
                        return asVector().size();
                    }
                } else {
                    ReadBuf readBuf3 = this.f40bb;
                    readUInt = FlexBuffers.readUInt(readBuf3, FlexBuffers.indirect(readBuf3, this.end, this.parentWidth), this.parentWidth);
                }
            }
            return (int) readUInt;
        }

        public long asUInt() {
            int r0 = this.type;
            if (r0 == 2) {
                return FlexBuffers.readUInt(this.f40bb, this.end, this.parentWidth);
            }
            if (r0 != 1) {
                if (r0 != 3) {
                    if (r0 != 10) {
                        if (r0 != 26) {
                            if (r0 != 5) {
                                if (r0 == 6) {
                                    ReadBuf readBuf = this.f40bb;
                                    return FlexBuffers.readLong(readBuf, FlexBuffers.indirect(readBuf, this.end, this.parentWidth), this.byteWidth);
                                } else if (r0 == 7) {
                                    ReadBuf readBuf2 = this.f40bb;
                                    return FlexBuffers.readUInt(readBuf2, FlexBuffers.indirect(readBuf2, this.end, this.parentWidth), this.byteWidth);
                                } else if (r0 != 8) {
                                    return 0L;
                                } else {
                                    ReadBuf readBuf3 = this.f40bb;
                                    return (long) FlexBuffers.readDouble(readBuf3, FlexBuffers.indirect(readBuf3, this.end, this.parentWidth), this.parentWidth);
                                }
                            }
                            return Long.parseLong(asString());
                        }
                        return FlexBuffers.readInt(this.f40bb, this.end, this.parentWidth);
                    }
                    return asVector().size();
                }
                return (long) FlexBuffers.readDouble(this.f40bb, this.end, this.parentWidth);
            }
            return FlexBuffers.readLong(this.f40bb, this.end, this.parentWidth);
        }

        public long asLong() {
            int r0 = this.type;
            if (r0 == 1) {
                return FlexBuffers.readLong(this.f40bb, this.end, this.parentWidth);
            }
            if (r0 != 2) {
                if (r0 != 3) {
                    if (r0 == 5) {
                        try {
                            return Long.parseLong(asString());
                        } catch (NumberFormatException unused) {
                            return 0L;
                        }
                    } else if (r0 == 6) {
                        ReadBuf readBuf = this.f40bb;
                        return FlexBuffers.readLong(readBuf, FlexBuffers.indirect(readBuf, this.end, this.parentWidth), this.byteWidth);
                    } else if (r0 == 7) {
                        ReadBuf readBuf2 = this.f40bb;
                        return FlexBuffers.readUInt(readBuf2, FlexBuffers.indirect(readBuf2, this.end, this.parentWidth), this.parentWidth);
                    } else if (r0 == 8) {
                        ReadBuf readBuf3 = this.f40bb;
                        return (long) FlexBuffers.readDouble(readBuf3, FlexBuffers.indirect(readBuf3, this.end, this.parentWidth), this.byteWidth);
                    } else if (r0 != 10) {
                        if (r0 != 26) {
                            return 0L;
                        }
                        return FlexBuffers.readInt(this.f40bb, this.end, this.parentWidth);
                    } else {
                        return asVector().size();
                    }
                }
                return (long) FlexBuffers.readDouble(this.f40bb, this.end, this.parentWidth);
            }
            return FlexBuffers.readUInt(this.f40bb, this.end, this.parentWidth);
        }

        public double asFloat() {
            int r0 = this.type;
            if (r0 == 3) {
                return FlexBuffers.readDouble(this.f40bb, this.end, this.parentWidth);
            }
            if (r0 != 1) {
                if (r0 != 2) {
                    if (r0 == 5) {
                        return Double.parseDouble(asString());
                    }
                    if (r0 == 6) {
                        ReadBuf readBuf = this.f40bb;
                        return FlexBuffers.readInt(readBuf, FlexBuffers.indirect(readBuf, this.end, this.parentWidth), this.byteWidth);
                    } else if (r0 == 7) {
                        ReadBuf readBuf2 = this.f40bb;
                        return FlexBuffers.readUInt(readBuf2, FlexBuffers.indirect(readBuf2, this.end, this.parentWidth), this.byteWidth);
                    } else if (r0 == 8) {
                        ReadBuf readBuf3 = this.f40bb;
                        return FlexBuffers.readDouble(readBuf3, FlexBuffers.indirect(readBuf3, this.end, this.parentWidth), this.byteWidth);
                    } else if (r0 == 10) {
                        return asVector().size();
                    } else {
                        if (r0 != 26) {
                            return 0.0d;
                        }
                    }
                }
                return FlexBuffers.readUInt(this.f40bb, this.end, this.parentWidth);
            }
            return FlexBuffers.readInt(this.f40bb, this.end, this.parentWidth);
        }

        public Key asKey() {
            if (isKey()) {
                ReadBuf readBuf = this.f40bb;
                return new Key(readBuf, FlexBuffers.indirect(readBuf, this.end, this.parentWidth), this.byteWidth);
            }
            return Key.empty();
        }

        public String asString() {
            if (isString()) {
                int indirect = FlexBuffers.indirect(this.f40bb, this.end, this.parentWidth);
                ReadBuf readBuf = this.f40bb;
                int r2 = this.byteWidth;
                return this.f40bb.getString(indirect, (int) FlexBuffers.readUInt(readBuf, indirect - r2, r2));
            } else if (isKey()) {
                int indirect2 = FlexBuffers.indirect(this.f40bb, this.end, this.byteWidth);
                int r1 = indirect2;
                while (this.f40bb.get(r1) != 0) {
                    r1++;
                }
                return this.f40bb.getString(indirect2, r1 - indirect2);
            } else {
                return "";
            }
        }

        public Map asMap() {
            if (isMap()) {
                ReadBuf readBuf = this.f40bb;
                return new Map(readBuf, FlexBuffers.indirect(readBuf, this.end, this.parentWidth), this.byteWidth);
            }
            return Map.empty();
        }

        public Vector asVector() {
            if (isVector()) {
                ReadBuf readBuf = this.f40bb;
                return new Vector(readBuf, FlexBuffers.indirect(readBuf, this.end, this.parentWidth), this.byteWidth);
            }
            int r0 = this.type;
            if (r0 == 15) {
                ReadBuf readBuf2 = this.f40bb;
                return new TypedVector(readBuf2, FlexBuffers.indirect(readBuf2, this.end, this.parentWidth), this.byteWidth, 4);
            } else if (FlexBuffers.isTypedVector(r0)) {
                ReadBuf readBuf3 = this.f40bb;
                return new TypedVector(readBuf3, FlexBuffers.indirect(readBuf3, this.end, this.parentWidth), this.byteWidth, FlexBuffers.toTypedVectorElementType(this.type));
            } else {
                return Vector.empty();
            }
        }

        public Blob asBlob() {
            if (isBlob() || isString()) {
                ReadBuf readBuf = this.f40bb;
                return new Blob(readBuf, FlexBuffers.indirect(readBuf, this.end, this.parentWidth), this.byteWidth);
            }
            return Blob.empty();
        }

        public boolean asBoolean() {
            return isBoolean() ? this.f40bb.get(this.end) != 0 : asUInt() != 0;
        }

        public String toString() {
            return toString(new StringBuilder(128)).toString();
        }

        StringBuilder toString(StringBuilder sb) {
            int r0 = this.type;
            if (r0 != 36) {
                switch (r0) {
                    case 0:
                        sb.append("null");
                        return sb;
                    case 1:
                    case 6:
                        sb.append(asLong());
                        return sb;
                    case 2:
                    case 7:
                        sb.append(asUInt());
                        return sb;
                    case 3:
                    case 8:
                        sb.append(asFloat());
                        return sb;
                    case 4:
                        Key asKey = asKey();
                        sb.append(Typography.quote);
                        StringBuilder key = asKey.toString(sb);
                        key.append(Typography.quote);
                        return key;
                    case 5:
                        sb.append(Typography.quote);
                        sb.append(asString());
                        sb.append(Typography.quote);
                        return sb;
                    case 9:
                        return asMap().toString(sb);
                    case 10:
                        return asVector().toString(sb);
                    case 11:
                    case 12:
                    case 13:
                    case 14:
                    case 15:
                        break;
                    case 16:
                    case 17:
                    case 18:
                    case 19:
                    case 20:
                    case 21:
                    case 22:
                    case 23:
                    case 24:
                        throw new FlexBufferException("not_implemented:" + this.type);
                    case 25:
                        return asBlob().toString(sb);
                    case 26:
                        sb.append(asBoolean());
                        return sb;
                    default:
                        return sb;
                }
            }
            sb.append(asVector());
            return sb;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public static abstract class Object {

        /* renamed from: bb */
        ReadBuf f39bb;
        int byteWidth;
        int end;

        public abstract StringBuilder toString(StringBuilder sb);

        Object(ReadBuf readBuf, int r2, int r3) {
            this.f39bb = readBuf;
            this.end = r2;
            this.byteWidth = r3;
        }

        public String toString() {
            return toString(new StringBuilder(128)).toString();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public static abstract class Sized extends Object {
        protected final int size;

        Sized(ReadBuf readBuf, int r2, int r3) {
            super(readBuf, r2, r3);
            this.size = FlexBuffers.readInt(this.f39bb, r2 - r3, r3);
        }

        public int size() {
            return this.size;
        }
    }

    /* loaded from: classes.dex */
    public static class Blob extends Sized {
        static final /* synthetic */ boolean $assertionsDisabled = false;
        static final Blob EMPTY = new Blob(FlexBuffers.EMPTY_BB, 1, 1);

        @Override // androidx.emoji2.text.flatbuffer.FlexBuffers.Sized
        public /* bridge */ /* synthetic */ int size() {
            return super.size();
        }

        Blob(ReadBuf readBuf, int r2, int r3) {
            super(readBuf, r2, r3);
        }

        public static Blob empty() {
            return EMPTY;
        }

        public ByteBuffer data() {
            ByteBuffer wrap = ByteBuffer.wrap(this.f39bb.data());
            wrap.position(this.end);
            wrap.limit(this.end + size());
            return wrap.asReadOnlyBuffer().slice();
        }

        public byte[] getBytes() {
            int size = size();
            byte[] bArr = new byte[size];
            for (int r2 = 0; r2 < size; r2++) {
                bArr[r2] = this.f39bb.get(this.end + r2);
            }
            return bArr;
        }

        public byte get(int r3) {
            return this.f39bb.get(this.end + r3);
        }

        @Override // androidx.emoji2.text.flatbuffer.FlexBuffers.Object
        public String toString() {
            return this.f39bb.getString(this.end, size());
        }

        @Override // androidx.emoji2.text.flatbuffer.FlexBuffers.Object
        public StringBuilder toString(StringBuilder sb) {
            sb.append(Typography.quote);
            sb.append(this.f39bb.getString(this.end, size()));
            sb.append(Typography.quote);
            return sb;
        }
    }

    /* loaded from: classes.dex */
    public static class Key extends Object {
        private static final Key EMPTY = new Key(FlexBuffers.EMPTY_BB, 0, 0);

        Key(ReadBuf readBuf, int r2, int r3) {
            super(readBuf, r2, r3);
        }

        public static Key empty() {
            return EMPTY;
        }

        @Override // androidx.emoji2.text.flatbuffer.FlexBuffers.Object
        public StringBuilder toString(StringBuilder sb) {
            sb.append(toString());
            return sb;
        }

        @Override // androidx.emoji2.text.flatbuffer.FlexBuffers.Object
        public String toString() {
            int r0 = this.end;
            while (this.f39bb.get(r0) != 0) {
                r0++;
            }
            return this.f39bb.getString(this.end, r0 - this.end);
        }

        int compareTo(byte[] bArr) {
            byte b;
            byte b2;
            int r0 = this.end;
            int r1 = 0;
            do {
                b = this.f39bb.get(r0);
                b2 = bArr[r1];
                if (b == 0) {
                    return b - b2;
                }
                r0++;
                r1++;
                if (r1 == bArr.length) {
                    return b - b2;
                }
            } while (b == b2);
            return b - b2;
        }

        public boolean equals(java.lang.Object obj) {
            if (obj instanceof Key) {
                Key key = (Key) obj;
                return key.end == this.end && key.byteWidth == this.byteWidth;
            }
            return false;
        }

        public int hashCode() {
            return this.end ^ this.byteWidth;
        }
    }

    /* loaded from: classes.dex */
    public static class Map extends Vector {
        private static final Map EMPTY_MAP = new Map(FlexBuffers.EMPTY_BB, 1, 1);

        Map(ReadBuf readBuf, int r2, int r3) {
            super(readBuf, r2, r3);
        }

        public static Map empty() {
            return EMPTY_MAP;
        }

        public Reference get(String str) {
            return get(str.getBytes(StandardCharsets.UTF_8));
        }

        public Reference get(byte[] bArr) {
            KeyVector keys = keys();
            int size = keys.size();
            int binarySearch = binarySearch(keys, bArr);
            if (binarySearch < 0 || binarySearch >= size) {
                return Reference.NULL_REFERENCE;
            }
            return get(binarySearch);
        }

        public KeyVector keys() {
            int r0 = this.end - (this.byteWidth * 3);
            return new KeyVector(new TypedVector(this.f39bb, FlexBuffers.indirect(this.f39bb, r0, this.byteWidth), FlexBuffers.readInt(this.f39bb, r0 + this.byteWidth, this.byteWidth), 4));
        }

        public Vector values() {
            return new Vector(this.f39bb, this.end, this.byteWidth);
        }

        @Override // androidx.emoji2.text.flatbuffer.FlexBuffers.Vector, androidx.emoji2.text.flatbuffer.FlexBuffers.Object
        public StringBuilder toString(StringBuilder sb) {
            sb.append("{ ");
            KeyVector keys = keys();
            int size = size();
            Vector values = values();
            for (int r3 = 0; r3 < size; r3++) {
                sb.append(Typography.quote);
                sb.append(keys.get(r3).toString());
                sb.append("\" : ");
                sb.append(values.get(r3).toString());
                if (r3 != size - 1) {
                    sb.append(", ");
                }
            }
            sb.append(" }");
            return sb;
        }

        private int binarySearch(KeyVector keyVector, byte[] bArr) {
            int size = keyVector.size() - 1;
            int r1 = 0;
            while (r1 <= size) {
                int r2 = (r1 + size) >>> 1;
                int compareTo = keyVector.get(r2).compareTo(bArr);
                if (compareTo < 0) {
                    r1 = r2 + 1;
                } else if (compareTo <= 0) {
                    return r2;
                } else {
                    size = r2 - 1;
                }
            }
            return -(r1 + 1);
        }
    }

    /* loaded from: classes.dex */
    public static class Vector extends Sized {
        private static final Vector EMPTY_VECTOR = new Vector(FlexBuffers.EMPTY_BB, 1, 1);

        @Override // androidx.emoji2.text.flatbuffer.FlexBuffers.Sized
        public /* bridge */ /* synthetic */ int size() {
            return super.size();
        }

        @Override // androidx.emoji2.text.flatbuffer.FlexBuffers.Object
        public /* bridge */ /* synthetic */ String toString() {
            return super.toString();
        }

        Vector(ReadBuf readBuf, int r2, int r3) {
            super(readBuf, r2, r3);
        }

        public static Vector empty() {
            return EMPTY_VECTOR;
        }

        public boolean isEmpty() {
            return this == EMPTY_VECTOR;
        }

        @Override // androidx.emoji2.text.flatbuffer.FlexBuffers.Object
        public StringBuilder toString(StringBuilder sb) {
            sb.append("[ ");
            int size = size();
            for (int r1 = 0; r1 < size; r1++) {
                get(r1).toString(sb);
                if (r1 != size - 1) {
                    sb.append(", ");
                }
            }
            sb.append(" ]");
            return sb;
        }

        public Reference get(int r10) {
            long size = size();
            long j = r10;
            if (j >= size) {
                return Reference.NULL_REFERENCE;
            }
            return new Reference(this.f39bb, this.end + (r10 * this.byteWidth), this.byteWidth, Unsigned.byteToUnsignedInt(this.f39bb.get((int) (this.end + (size * this.byteWidth) + j))));
        }
    }

    /* loaded from: classes.dex */
    public static class TypedVector extends Vector {
        private static final TypedVector EMPTY_VECTOR = new TypedVector(FlexBuffers.EMPTY_BB, 1, 1, 1);
        private final int elemType;

        TypedVector(ReadBuf readBuf, int r2, int r3, int r4) {
            super(readBuf, r2, r3);
            this.elemType = r4;
        }

        public static TypedVector empty() {
            return EMPTY_VECTOR;
        }

        public boolean isEmptyVector() {
            return this == EMPTY_VECTOR;
        }

        public int getElemType() {
            return this.elemType;
        }

        @Override // androidx.emoji2.text.flatbuffer.FlexBuffers.Vector
        public Reference get(int r8) {
            if (r8 >= size()) {
                return Reference.NULL_REFERENCE;
            }
            return new Reference(this.f39bb, this.end + (r8 * this.byteWidth), this.byteWidth, 1, this.elemType);
        }
    }

    /* loaded from: classes.dex */
    public static class KeyVector {
        private final TypedVector vec;

        KeyVector(TypedVector typedVector) {
            this.vec = typedVector;
        }

        public Key get(int r5) {
            if (r5 >= size()) {
                return Key.EMPTY;
            }
            return new Key(this.vec.f39bb, FlexBuffers.indirect(this.vec.f39bb, this.vec.end + (r5 * this.vec.byteWidth), this.vec.byteWidth), 1);
        }

        public int size() {
            return this.vec.size();
        }

        public String toString() {
            StringBuilder sb = new StringBuilder();
            sb.append('[');
            for (int r1 = 0; r1 < this.vec.size(); r1++) {
                this.vec.get(r1).toString(sb);
                if (r1 != this.vec.size() - 1) {
                    sb.append(", ");
                }
            }
            sb.append("]");
            return sb.toString();
        }
    }

    /* loaded from: classes.dex */
    public static class FlexBufferException extends RuntimeException {
        /* JADX INFO: Access modifiers changed from: package-private */
        public FlexBufferException(String str) {
            super(str);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public static class Unsigned {
        /* JADX INFO: Access modifiers changed from: package-private */
        public static int byteToUnsignedInt(byte b) {
            return b & 255;
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        public static long intToUnsignedLong(int r4) {
            return r4 & BodyPartID.bodyIdMax;
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        public static int shortToUnsignedInt(short s) {
            return s & UShort.MAX_VALUE;
        }

        Unsigned() {
        }
    }
}
