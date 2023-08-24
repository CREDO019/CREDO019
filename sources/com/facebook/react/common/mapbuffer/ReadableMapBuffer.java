package com.facebook.react.common.mapbuffer;

import com.facebook.jni.HybridData;
import com.facebook.react.common.mapbuffer.MapBuffer;
import com.facebook.react.uimanager.ViewProps;
import com.google.android.exoplayer2.source.rtsp.SessionDescription;
import com.onesignal.shortcutbadger.impl.NewHtcHomeBadger;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.Iterator;
import kotlin.Metadata;
import kotlin.UShort;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.IntRange;
import kotlin.text.Charsets;

/* compiled from: ReadableMapBuffer.kt */
@Metadata(m184d1 = {"\u0000h\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0007\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0000\n\u0002\b\u0004\n\u0002\u0010\u0006\n\u0002\b\u0006\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010(\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0006\b\u0007\u0018\u0000 <2\u00020\u0001:\u0002<=B\u000f\b\u0013\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004B\u000f\b\u0012\u0012\u0006\u0010\u0005\u001a\u00020\u0006¢\u0006\u0002\u0010\u0007J\u0010\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\tH\u0016J\u0010\u0010\u0013\u001a\u00020\u00142\u0006\u0010\u0015\u001a\u00020\tH\u0016J\u0013\u0010\u0016\u001a\u00020\u00112\b\u0010\u0017\u001a\u0004\u0018\u00010\u0018H\u0096\u0002J\u0010\u0010\u0019\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\tH\u0016J\u0010\u0010\u001a\u001a\u00020\t2\u0006\u0010\u001b\u001a\u00020\tH\u0002J\u0010\u0010\u001c\u001a\u00020\u001d2\u0006\u0010\u0012\u001a\u00020\tH\u0016J\u0010\u0010\u001e\u001a\u00020\t2\u0006\u0010\u0012\u001a\u00020\tH\u0016J\u0010\u0010\u001f\u001a\u00020\t2\u0006\u0010\u0012\u001a\u00020\tH\u0016J\u0010\u0010 \u001a\u00020\t2\u0006\u0010!\u001a\u00020\tH\u0002J\u0010\u0010\"\u001a\u00020\u00002\u0006\u0010\u0012\u001a\u00020\tH\u0016J\u0010\u0010#\u001a\u00020$2\u0006\u0010\u0012\u001a\u00020\tH\u0016J\u0010\u0010%\u001a\u00020&2\u0006\u0010\u0012\u001a\u00020\tH\u0016J\u0018\u0010'\u001a\u00020\t2\u0006\u0010\u0012\u001a\u00020\t2\u0006\u0010(\u001a\u00020&H\u0002J\b\u0010)\u001a\u00020\tH\u0016J\t\u0010*\u001a\u00020\u0006H\u0082 J\u000f\u0010+\u001a\b\u0012\u0004\u0012\u00020\u00140,H\u0096\u0002J\u0010\u0010-\u001a\u00020\u00112\u0006\u0010.\u001a\u00020\tH\u0002J\u0010\u0010/\u001a\u00020&2\u0006\u0010!\u001a\u00020\tH\u0002J\u0010\u00100\u001a\u00020\u001d2\u0006\u0010.\u001a\u00020\tH\u0002J\b\u00101\u001a\u000202H\u0002J\u0010\u00103\u001a\u00020\t2\u0006\u0010.\u001a\u00020\tH\u0002J\u0010\u00104\u001a\u00020\u00002\u0006\u00105\u001a\u00020\tH\u0002J\u0010\u00106\u001a\u00020$2\u0006\u0010.\u001a\u00020\tH\u0002J \u00107\u001a\u0002082\u0006\u0010.\u001a\u00020\tH\u0002ø\u0001\u0000ø\u0001\u0001ø\u0001\u0002¢\u0006\u0004\b9\u0010:J\b\u0010;\u001a\u00020$H\u0016R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u0004¢\u0006\u0002\n\u0000R\u001e\u0010\n\u001a\u00020\t2\u0006\u0010\b\u001a\u00020\t@RX\u0096\u000e¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u0012\u0010\r\u001a\u0004\u0018\u00010\u00038\u0002X\u0083\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u000e\u001a\u00020\t8BX\u0082\u0004¢\u0006\u0006\u001a\u0004\b\u000f\u0010\f\u0082\u0002\u000f\n\u0002\b\u0019\n\u0002\b!\n\u0005\b¡\u001e0\u0001¨\u0006>"}, m183d2 = {"Lcom/facebook/react/common/mapbuffer/ReadableMapBuffer;", "Lcom/facebook/react/common/mapbuffer/MapBuffer;", "hybridData", "Lcom/facebook/jni/HybridData;", "(Lcom/facebook/jni/HybridData;)V", "buffer", "Ljava/nio/ByteBuffer;", "(Ljava/nio/ByteBuffer;)V", "<set-?>", "", NewHtcHomeBadger.COUNT, "getCount", "()I", "mHybridData", "offsetForDynamicData", "getOffsetForDynamicData", "contains", "", "key", "entryAt", "Lcom/facebook/react/common/mapbuffer/MapBuffer$Entry;", "offset", "equals", "other", "", "getBoolean", "getBucketIndexForKey", "intKey", "getDouble", "", "getInt", "getKeyOffset", "getKeyOffsetForBucketIndex", "bucketIndex", "getMapBuffer", "getString", "", "getType", "Lcom/facebook/react/common/mapbuffer/MapBuffer$DataType;", "getTypedValueOffsetForKey", "expected", "hashCode", "importByteBuffer", "iterator", "", "readBooleanValue", "bufferPosition", "readDataType", "readDoubleValue", "readHeader", "", "readIntValue", "readMapBufferValue", ViewProps.POSITION, "readStringValue", "readUnsignedShort", "Lkotlin/UShort;", "readUnsignedShort-BwKQO78", "(I)S", "toString", "Companion", "MapBufferEntry", "ReactAndroid_release"}, m182k = 1, m181mv = {1, 6, 0}, m179xi = 48)
/* loaded from: classes.dex */
public final class ReadableMapBuffer implements MapBuffer {
    private static final int ALIGNMENT = 254;
    private static final int BUCKET_SIZE = 12;
    public static final Companion Companion = new Companion(null);
    private static final int HEADER_SIZE = 8;
    private static final int TYPE_OFFSET = 2;
    private static final int VALUE_OFFSET = 4;
    private final ByteBuffer buffer;
    private int count;
    private final HybridData mHybridData;

    /* compiled from: ReadableMapBuffer.kt */
    @Metadata(m182k = 3, m181mv = {1, 6, 0}, m179xi = 48)
    /* loaded from: classes.dex */
    public /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] r0 = new int[MapBuffer.DataType.values().length];
            r0[MapBuffer.DataType.BOOL.ordinal()] = 1;
            r0[MapBuffer.DataType.INT.ordinal()] = 2;
            r0[MapBuffer.DataType.DOUBLE.ordinal()] = 3;
            r0[MapBuffer.DataType.STRING.ordinal()] = 4;
            r0[MapBuffer.DataType.MAP.ordinal()] = 5;
            $EnumSwitchMapping$0 = r0;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final int getKeyOffsetForBucketIndex(int r1) {
        return (r1 * 12) + 8;
    }

    private final native ByteBuffer importByteBuffer();

    @Override // com.facebook.react.common.mapbuffer.MapBuffer
    public int getCount() {
        return this.count;
    }

    private ReadableMapBuffer(HybridData hybridData) {
        this.mHybridData = hybridData;
        this.buffer = importByteBuffer();
        readHeader();
    }

    private ReadableMapBuffer(ByteBuffer byteBuffer) {
        this.mHybridData = null;
        this.buffer = byteBuffer;
        readHeader();
    }

    private final void readHeader() {
        if (this.buffer.getShort() != ALIGNMENT) {
            this.buffer.order(ByteOrder.LITTLE_ENDIAN);
        }
        this.count = m1492readUnsignedShortBwKQO78(this.buffer.position()) & UShort.MAX_VALUE;
    }

    private final int getOffsetForDynamicData() {
        return getKeyOffsetForBucketIndex(getCount());
    }

    private final int getBucketIndexForKey(int r9) {
        IntRange kEY_RANGE$ReactAndroid_release = MapBuffer.Companion.getKEY_RANGE$ReactAndroid_release();
        int r2 = 0;
        if (r9 <= kEY_RANGE$ReactAndroid_release.getLast() && kEY_RANGE$ReactAndroid_release.getFirst() <= r9) {
            short m2027constructorimpl = UShort.m2027constructorimpl((short) r9);
            int count = getCount() - 1;
            while (r2 <= count) {
                int r4 = (r2 + count) >>> 1;
                int m1492readUnsignedShortBwKQO78 = m1492readUnsignedShortBwKQO78(getKeyOffsetForBucketIndex(r4)) & UShort.MAX_VALUE;
                int r6 = 65535 & m2027constructorimpl;
                if (Intrinsics.compare(m1492readUnsignedShortBwKQO78, r6) < 0) {
                    r2 = r4 + 1;
                } else if (Intrinsics.compare(m1492readUnsignedShortBwKQO78, r6) <= 0) {
                    return r4;
                } else {
                    count = r4 - 1;
                }
            }
            return -1;
        }
        return -1;
    }

    private final MapBuffer.DataType readDataType(int r2) {
        return MapBuffer.DataType.values()[m1492readUnsignedShortBwKQO78(getKeyOffsetForBucketIndex(r2) + 2) & UShort.MAX_VALUE];
    }

    private final int getTypedValueOffsetForKey(int r5, MapBuffer.DataType dataType) {
        int bucketIndexForKey = getBucketIndexForKey(r5);
        if (!(bucketIndexForKey != -1)) {
            throw new IllegalArgumentException(Intrinsics.stringPlus("Key not found: ", Integer.valueOf(r5)).toString());
        }
        MapBuffer.DataType readDataType = readDataType(bucketIndexForKey);
        if (!(readDataType == dataType)) {
            throw new IllegalStateException(("Expected " + dataType + " for key: " + r5 + ", found " + readDataType + " instead.").toString());
        }
        return getKeyOffsetForBucketIndex(bucketIndexForKey) + 4;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: readUnsignedShort-BwKQO78  reason: not valid java name */
    public final short m1492readUnsignedShortBwKQO78(int r2) {
        return UShort.m2027constructorimpl(this.buffer.getShort(r2));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final double readDoubleValue(int r3) {
        return this.buffer.getDouble(r3);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final int readIntValue(int r2) {
        return this.buffer.getInt(r2);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final boolean readBooleanValue(int r2) {
        return readIntValue(r2) == 1;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final String readStringValue(int r4) {
        int offsetForDynamicData = getOffsetForDynamicData() + this.buffer.getInt(r4);
        int r42 = this.buffer.getInt(offsetForDynamicData);
        byte[] bArr = new byte[r42];
        this.buffer.position(offsetForDynamicData + 4);
        this.buffer.get(bArr, 0, r42);
        return new String(bArr, Charsets.UTF_8);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final ReadableMapBuffer readMapBufferValue(int r4) {
        int offsetForDynamicData = getOffsetForDynamicData() + this.buffer.getInt(r4);
        int r42 = this.buffer.getInt(offsetForDynamicData);
        byte[] bArr = new byte[r42];
        this.buffer.position(offsetForDynamicData + 4);
        this.buffer.get(bArr, 0, r42);
        ByteBuffer wrap = ByteBuffer.wrap(bArr);
        Intrinsics.checkNotNullExpressionValue(wrap, "wrap(newBuffer)");
        return new ReadableMapBuffer(wrap);
    }

    @Override // com.facebook.react.common.mapbuffer.MapBuffer
    public boolean contains(int r2) {
        return getBucketIndexForKey(r2) != -1;
    }

    @Override // com.facebook.react.common.mapbuffer.MapBuffer
    public int getKeyOffset(int r1) {
        return getBucketIndexForKey(r1);
    }

    @Override // com.facebook.react.common.mapbuffer.MapBuffer
    public MapBuffer.Entry entryAt(int r2) {
        return new MapBufferEntry(this, getKeyOffsetForBucketIndex(r2));
    }

    @Override // com.facebook.react.common.mapbuffer.MapBuffer
    public MapBuffer.DataType getType(int r3) {
        int bucketIndexForKey = getBucketIndexForKey(r3);
        if (!(bucketIndexForKey != -1)) {
            throw new IllegalArgumentException(Intrinsics.stringPlus("Key not found: ", Integer.valueOf(r3)).toString());
        }
        return readDataType(bucketIndexForKey);
    }

    @Override // com.facebook.react.common.mapbuffer.MapBuffer
    public int getInt(int r2) {
        return readIntValue(getTypedValueOffsetForKey(r2, MapBuffer.DataType.INT));
    }

    @Override // com.facebook.react.common.mapbuffer.MapBuffer
    public double getDouble(int r3) {
        return readDoubleValue(getTypedValueOffsetForKey(r3, MapBuffer.DataType.DOUBLE));
    }

    @Override // com.facebook.react.common.mapbuffer.MapBuffer
    public String getString(int r2) {
        return readStringValue(getTypedValueOffsetForKey(r2, MapBuffer.DataType.STRING));
    }

    @Override // com.facebook.react.common.mapbuffer.MapBuffer
    public boolean getBoolean(int r2) {
        return readBooleanValue(getTypedValueOffsetForKey(r2, MapBuffer.DataType.BOOL));
    }

    @Override // com.facebook.react.common.mapbuffer.MapBuffer
    public ReadableMapBuffer getMapBuffer(int r2) {
        return readMapBufferValue(getTypedValueOffsetForKey(r2, MapBuffer.DataType.MAP));
    }

    public int hashCode() {
        this.buffer.rewind();
        return this.buffer.hashCode();
    }

    public boolean equals(Object obj) {
        if (obj instanceof ReadableMapBuffer) {
            ByteBuffer byteBuffer = this.buffer;
            ByteBuffer byteBuffer2 = ((ReadableMapBuffer) obj).buffer;
            if (byteBuffer == byteBuffer2) {
                return true;
            }
            byteBuffer.rewind();
            byteBuffer2.rewind();
            return Intrinsics.areEqual(byteBuffer, byteBuffer2);
        }
        return false;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder("{");
        Iterator<MapBuffer.Entry> it = iterator();
        while (it.hasNext()) {
            MapBuffer.Entry next = it.next();
            sb.append(next.getKey());
            sb.append('=');
            int r3 = WhenMappings.$EnumSwitchMapping$0[next.getType().ordinal()];
            if (r3 == 1) {
                sb.append(next.getBooleanValue());
            } else if (r3 == 2) {
                sb.append(next.getIntValue());
            } else if (r3 == 3) {
                sb.append(next.getDoubleValue());
            } else if (r3 == 4) {
                sb.append(next.getStringValue());
            } else if (r3 == 5) {
                sb.append(next.getMapBufferValue().toString());
            }
            sb.append(',');
        }
        sb.append('}');
        String sb2 = sb.toString();
        Intrinsics.checkNotNullExpressionValue(sb2, "builder.toString()");
        return sb2;
    }

    @Override // java.lang.Iterable
    public Iterator<MapBuffer.Entry> iterator() {
        return new ReadableMapBuffer$iterator$1(this);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* compiled from: ReadableMapBuffer.kt */
    @Metadata(m184d1 = {"\u0000B\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u0006\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0002\b\u0082\u0004\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u0010\u0010\u001e\u001a\u00020\u001f2\u0006\u0010 \u001a\u00020\u001bH\u0002R\u0014\u0010\u0005\u001a\u00020\u00068VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u0007\u0010\bR\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\t\u001a\u00020\n8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u000b\u0010\fR\u0014\u0010\r\u001a\u00020\u00038VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u000e\u0010\u000fR\u0014\u0010\u0010\u001a\u00020\u00038VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u0011\u0010\u000fR\u0014\u0010\u0012\u001a\u00020\u00138VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u0014\u0010\u0015R\u0014\u0010\u0016\u001a\u00020\u00178VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u0018\u0010\u0019R\u0014\u0010\u001a\u001a\u00020\u001b8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u001c\u0010\u001d¨\u0006!"}, m183d2 = {"Lcom/facebook/react/common/mapbuffer/ReadableMapBuffer$MapBufferEntry;", "Lcom/facebook/react/common/mapbuffer/MapBuffer$Entry;", "bucketOffset", "", "(Lcom/facebook/react/common/mapbuffer/ReadableMapBuffer;I)V", "booleanValue", "", "getBooleanValue", "()Z", "doubleValue", "", "getDoubleValue", "()D", "intValue", "getIntValue", "()I", "key", "getKey", "mapBufferValue", "Lcom/facebook/react/common/mapbuffer/MapBuffer;", "getMapBufferValue", "()Lcom/facebook/react/common/mapbuffer/MapBuffer;", "stringValue", "", "getStringValue", "()Ljava/lang/String;", SessionDescription.ATTR_TYPE, "Lcom/facebook/react/common/mapbuffer/MapBuffer$DataType;", "getType", "()Lcom/facebook/react/common/mapbuffer/MapBuffer$DataType;", "assertType", "", "expected", "ReactAndroid_release"}, m182k = 1, m181mv = {1, 6, 0}, m179xi = 48)
    /* loaded from: classes.dex */
    public final class MapBufferEntry implements MapBuffer.Entry {
        private final int bucketOffset;
        final /* synthetic */ ReadableMapBuffer this$0;

        public MapBufferEntry(ReadableMapBuffer this$0, int r3) {
            Intrinsics.checkNotNullParameter(this$0, "this$0");
            this.this$0 = this$0;
            this.bucketOffset = r3;
        }

        private final void assertType(MapBuffer.DataType dataType) {
            MapBuffer.DataType type = getType();
            if (dataType == type) {
                return;
            }
            throw new IllegalStateException(("Expected " + dataType + " for key: " + getKey() + " found " + type + " instead.").toString());
        }

        @Override // com.facebook.react.common.mapbuffer.MapBuffer.Entry
        public int getKey() {
            return this.this$0.m1492readUnsignedShortBwKQO78(this.bucketOffset) & UShort.MAX_VALUE;
        }

        @Override // com.facebook.react.common.mapbuffer.MapBuffer.Entry
        public MapBuffer.DataType getType() {
            return MapBuffer.DataType.values()[this.this$0.m1492readUnsignedShortBwKQO78(this.bucketOffset + 2) & UShort.MAX_VALUE];
        }

        @Override // com.facebook.react.common.mapbuffer.MapBuffer.Entry
        public double getDoubleValue() {
            assertType(MapBuffer.DataType.DOUBLE);
            return this.this$0.readDoubleValue(this.bucketOffset + 4);
        }

        @Override // com.facebook.react.common.mapbuffer.MapBuffer.Entry
        public int getIntValue() {
            assertType(MapBuffer.DataType.INT);
            return this.this$0.readIntValue(this.bucketOffset + 4);
        }

        @Override // com.facebook.react.common.mapbuffer.MapBuffer.Entry
        public boolean getBooleanValue() {
            assertType(MapBuffer.DataType.BOOL);
            return this.this$0.readBooleanValue(this.bucketOffset + 4);
        }

        @Override // com.facebook.react.common.mapbuffer.MapBuffer.Entry
        public String getStringValue() {
            assertType(MapBuffer.DataType.STRING);
            return this.this$0.readStringValue(this.bucketOffset + 4);
        }

        @Override // com.facebook.react.common.mapbuffer.MapBuffer.Entry
        public MapBuffer getMapBufferValue() {
            assertType(MapBuffer.DataType.MAP);
            return this.this$0.readMapBufferValue(this.bucketOffset + 4);
        }
    }

    /* compiled from: ReadableMapBuffer.kt */
    @Metadata(m184d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0005\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000¨\u0006\t"}, m183d2 = {"Lcom/facebook/react/common/mapbuffer/ReadableMapBuffer$Companion;", "", "()V", "ALIGNMENT", "", "BUCKET_SIZE", "HEADER_SIZE", "TYPE_OFFSET", "VALUE_OFFSET", "ReactAndroid_release"}, m182k = 1, m181mv = {1, 6, 0}, m179xi = 48)
    /* loaded from: classes.dex */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }
    }

    static {
        MapBufferSoLoader.staticInit();
    }
}
