package androidx.emoji2.text.flatbuffer;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;

/* loaded from: classes.dex */
public final class MetadataItem extends Table {
    public static void ValidateVersion() {
        Constants.FLATBUFFERS_1_12_0();
    }

    public static MetadataItem getRootAsMetadataItem(ByteBuffer byteBuffer) {
        return getRootAsMetadataItem(byteBuffer, new MetadataItem());
    }

    public static MetadataItem getRootAsMetadataItem(ByteBuffer byteBuffer, MetadataItem metadataItem) {
        byteBuffer.order(ByteOrder.LITTLE_ENDIAN);
        return metadataItem.__assign(byteBuffer.getInt(byteBuffer.position()) + byteBuffer.position(), byteBuffer);
    }

    public void __init(int r1, ByteBuffer byteBuffer) {
        __reset(r1, byteBuffer);
    }

    public MetadataItem __assign(int r1, ByteBuffer byteBuffer) {
        __init(r1, byteBuffer);
        return this;
    }

    /* renamed from: id */
    public int m1395id() {
        int __offset = __offset(4);
        if (__offset != 0) {
            return this.f43bb.getInt(__offset + this.bb_pos);
        }
        return 0;
    }

    public boolean emojiStyle() {
        int __offset = __offset(6);
        return (__offset == 0 || this.f43bb.get(__offset + this.bb_pos) == 0) ? false : true;
    }

    public short sdkAdded() {
        int __offset = __offset(8);
        if (__offset != 0) {
            return this.f43bb.getShort(__offset + this.bb_pos);
        }
        return (short) 0;
    }

    public short compatAdded() {
        int __offset = __offset(10);
        if (__offset != 0) {
            return this.f43bb.getShort(__offset + this.bb_pos);
        }
        return (short) 0;
    }

    public short width() {
        int __offset = __offset(12);
        if (__offset != 0) {
            return this.f43bb.getShort(__offset + this.bb_pos);
        }
        return (short) 0;
    }

    public short height() {
        int __offset = __offset(14);
        if (__offset != 0) {
            return this.f43bb.getShort(__offset + this.bb_pos);
        }
        return (short) 0;
    }

    public int codepoints(int r3) {
        int __offset = __offset(16);
        if (__offset != 0) {
            return this.f43bb.getInt(__vector(__offset) + (r3 * 4));
        }
        return 0;
    }

    public int codepointsLength() {
        int __offset = __offset(16);
        if (__offset != 0) {
            return __vector_len(__offset);
        }
        return 0;
    }

    public IntVector codepointsVector() {
        return codepointsVector(new IntVector());
    }

    public IntVector codepointsVector(IntVector intVector) {
        int __offset = __offset(16);
        if (__offset != 0) {
            return intVector.__assign(__vector(__offset), this.f43bb);
        }
        return null;
    }

    public ByteBuffer codepointsAsByteBuffer() {
        return __vector_as_bytebuffer(16, 4);
    }

    public ByteBuffer codepointsInByteBuffer(ByteBuffer byteBuffer) {
        return __vector_in_bytebuffer(byteBuffer, 16, 4);
    }

    public static int createMetadataItem(FlatBufferBuilder flatBufferBuilder, int r2, boolean z, short s, short s2, short s3, short s4, int r8) {
        flatBufferBuilder.startTable(7);
        addCodepoints(flatBufferBuilder, r8);
        addId(flatBufferBuilder, r2);
        addHeight(flatBufferBuilder, s4);
        addWidth(flatBufferBuilder, s3);
        addCompatAdded(flatBufferBuilder, s2);
        addSdkAdded(flatBufferBuilder, s);
        addEmojiStyle(flatBufferBuilder, z);
        return endMetadataItem(flatBufferBuilder);
    }

    public static void startMetadataItem(FlatBufferBuilder flatBufferBuilder) {
        flatBufferBuilder.startTable(7);
    }

    public static void addId(FlatBufferBuilder flatBufferBuilder, int r2) {
        flatBufferBuilder.addInt(0, r2, 0);
    }

    public static void addEmojiStyle(FlatBufferBuilder flatBufferBuilder, boolean z) {
        flatBufferBuilder.addBoolean(1, z, false);
    }

    public static void addSdkAdded(FlatBufferBuilder flatBufferBuilder, short s) {
        flatBufferBuilder.addShort(2, s, 0);
    }

    public static void addCompatAdded(FlatBufferBuilder flatBufferBuilder, short s) {
        flatBufferBuilder.addShort(3, s, 0);
    }

    public static void addWidth(FlatBufferBuilder flatBufferBuilder, short s) {
        flatBufferBuilder.addShort(4, s, 0);
    }

    public static void addHeight(FlatBufferBuilder flatBufferBuilder, short s) {
        flatBufferBuilder.addShort(5, s, 0);
    }

    public static void addCodepoints(FlatBufferBuilder flatBufferBuilder, int r3) {
        flatBufferBuilder.addOffset(6, r3, 0);
    }

    public static int createCodepointsVector(FlatBufferBuilder flatBufferBuilder, int[] r3) {
        flatBufferBuilder.startVector(4, r3.length, 4);
        for (int length = r3.length - 1; length >= 0; length--) {
            flatBufferBuilder.addInt(r3[length]);
        }
        return flatBufferBuilder.endVector();
    }

    public static void startCodepointsVector(FlatBufferBuilder flatBufferBuilder, int r2) {
        flatBufferBuilder.startVector(4, r2, 4);
    }

    public static int endMetadataItem(FlatBufferBuilder flatBufferBuilder) {
        return flatBufferBuilder.endTable();
    }

    /* loaded from: classes.dex */
    public static final class Vector extends BaseVector {
        public Vector __assign(int r1, int r2, ByteBuffer byteBuffer) {
            __reset(r1, r2, byteBuffer);
            return this;
        }

        public MetadataItem get(int r2) {
            return get(new MetadataItem(), r2);
        }

        public MetadataItem get(MetadataItem metadataItem, int r3) {
            return metadataItem.__assign(MetadataItem.__indirect(__element(r3), this.f37bb), this.f37bb);
        }
    }
}
