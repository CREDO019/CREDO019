package androidx.emoji2.text.flatbuffer;

import androidx.emoji2.text.flatbuffer.MetadataItem;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;

/* loaded from: classes.dex */
public final class MetadataList extends Table {
    public static void ValidateVersion() {
        Constants.FLATBUFFERS_1_12_0();
    }

    public static MetadataList getRootAsMetadataList(ByteBuffer byteBuffer) {
        return getRootAsMetadataList(byteBuffer, new MetadataList());
    }

    public static MetadataList getRootAsMetadataList(ByteBuffer byteBuffer, MetadataList metadataList) {
        byteBuffer.order(ByteOrder.LITTLE_ENDIAN);
        return metadataList.__assign(byteBuffer.getInt(byteBuffer.position()) + byteBuffer.position(), byteBuffer);
    }

    public void __init(int r1, ByteBuffer byteBuffer) {
        __reset(r1, byteBuffer);
    }

    public MetadataList __assign(int r1, ByteBuffer byteBuffer) {
        __init(r1, byteBuffer);
        return this;
    }

    public int version() {
        int __offset = __offset(4);
        if (__offset != 0) {
            return this.f43bb.getInt(__offset + this.bb_pos);
        }
        return 0;
    }

    public MetadataItem list(int r2) {
        return list(new MetadataItem(), r2);
    }

    public MetadataItem list(MetadataItem metadataItem, int r3) {
        int __offset = __offset(6);
        if (__offset != 0) {
            return metadataItem.__assign(__indirect(__vector(__offset) + (r3 * 4)), this.f43bb);
        }
        return null;
    }

    public int listLength() {
        int __offset = __offset(6);
        if (__offset != 0) {
            return __vector_len(__offset);
        }
        return 0;
    }

    public MetadataItem.Vector listVector() {
        return listVector(new MetadataItem.Vector());
    }

    public MetadataItem.Vector listVector(MetadataItem.Vector vector) {
        int __offset = __offset(6);
        if (__offset != 0) {
            return vector.__assign(__vector(__offset), 4, this.f43bb);
        }
        return null;
    }

    public String sourceSha() {
        int __offset = __offset(8);
        if (__offset != 0) {
            return __string(__offset + this.bb_pos);
        }
        return null;
    }

    public ByteBuffer sourceShaAsByteBuffer() {
        return __vector_as_bytebuffer(8, 1);
    }

    public ByteBuffer sourceShaInByteBuffer(ByteBuffer byteBuffer) {
        return __vector_in_bytebuffer(byteBuffer, 8, 1);
    }

    public static int createMetadataList(FlatBufferBuilder flatBufferBuilder, int r2, int r3, int r4) {
        flatBufferBuilder.startTable(3);
        addSourceSha(flatBufferBuilder, r4);
        addList(flatBufferBuilder, r3);
        addVersion(flatBufferBuilder, r2);
        return endMetadataList(flatBufferBuilder);
    }

    public static void startMetadataList(FlatBufferBuilder flatBufferBuilder) {
        flatBufferBuilder.startTable(3);
    }

    public static void addVersion(FlatBufferBuilder flatBufferBuilder, int r2) {
        flatBufferBuilder.addInt(0, r2, 0);
    }

    public static void addList(FlatBufferBuilder flatBufferBuilder, int r3) {
        flatBufferBuilder.addOffset(1, r3, 0);
    }

    public static int createListVector(FlatBufferBuilder flatBufferBuilder, int[] r3) {
        flatBufferBuilder.startVector(4, r3.length, 4);
        for (int length = r3.length - 1; length >= 0; length--) {
            flatBufferBuilder.addOffset(r3[length]);
        }
        return flatBufferBuilder.endVector();
    }

    public static void startListVector(FlatBufferBuilder flatBufferBuilder, int r2) {
        flatBufferBuilder.startVector(4, r2, 4);
    }

    public static void addSourceSha(FlatBufferBuilder flatBufferBuilder, int r3) {
        flatBufferBuilder.addOffset(2, r3, 0);
    }

    public static int endMetadataList(FlatBufferBuilder flatBufferBuilder) {
        return flatBufferBuilder.endTable();
    }

    public static void finishMetadataListBuffer(FlatBufferBuilder flatBufferBuilder, int r1) {
        flatBufferBuilder.finish(r1);
    }

    public static void finishSizePrefixedMetadataListBuffer(FlatBufferBuilder flatBufferBuilder, int r1) {
        flatBufferBuilder.finishSizePrefixed(r1);
    }

    /* loaded from: classes.dex */
    public static final class Vector extends BaseVector {
        public Vector __assign(int r1, int r2, ByteBuffer byteBuffer) {
            __reset(r1, r2, byteBuffer);
            return this;
        }

        public MetadataList get(int r2) {
            return get(new MetadataList(), r2);
        }

        public MetadataList get(MetadataList metadataList, int r3) {
            return metadataList.__assign(MetadataList.__indirect(__element(r3), this.f37bb), this.f37bb);
        }
    }
}
