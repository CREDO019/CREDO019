package com.facebook.react.common.mapbuffer;

import android.util.SparseArray;
import com.facebook.react.common.mapbuffer.MapBuffer;
import com.google.android.exoplayer2.source.rtsp.SessionDescription;
import com.onesignal.shortcutbadger.impl.NewHtcHomeBadger;
import java.util.Iterator;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.IntRange;

/* compiled from: WritableMapBuffer.kt */
@Metadata(m184d1 = {"\u0000Z\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0006\n\u0002\b\u0003\n\u0002\u0010\u0015\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0011\n\u0002\b\u0002\n\u0002\u0010(\n\u0002\b\n\b\u0007\u0018\u0000 (2\u00020\u0001:\u0002()B\u0005¢\u0006\u0002\u0010\u0002J\u0010\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\u0004H\u0016J\u0010\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u0004H\u0016J\u0010\u0010\u0010\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\u0004H\u0016J\u0010\u0010\u0011\u001a\u00020\u00122\u0006\u0010\f\u001a\u00020\u0004H\u0016J\u0010\u0010\u0013\u001a\u00020\u00042\u0006\u0010\f\u001a\u00020\u0004H\u0016J\u0010\u0010\u0014\u001a\u00020\u00042\u0006\u0010\f\u001a\u00020\u0004H\u0016J\b\u0010\u0015\u001a\u00020\u0016H\u0003J\u0010\u0010\u0017\u001a\u00020\u00012\u0006\u0010\f\u001a\u00020\u0004H\u0016J\u0010\u0010\u0018\u001a\u00020\u00192\u0006\u0010\f\u001a\u00020\u0004H\u0016J\u0010\u0010\u001a\u001a\u00020\u001b2\u0006\u0010\f\u001a\u00020\u0004H\u0016J\u0013\u0010\u001c\u001a\b\u0012\u0004\u0012\u00020\t0\u001dH\u0003¢\u0006\u0002\u0010\u001eJ\u000f\u0010\u001f\u001a\b\u0012\u0004\u0012\u00020\u000e0 H\u0096\u0002J\u0016\u0010!\u001a\u00020\u00002\u0006\u0010\f\u001a\u00020\u00042\u0006\u0010\"\u001a\u00020\u0001J\u0016\u0010!\u001a\u00020\u00002\u0006\u0010\f\u001a\u00020\u00042\u0006\u0010\"\u001a\u00020\u000bJ\u0016\u0010!\u001a\u00020\u00002\u0006\u0010\f\u001a\u00020\u00042\u0006\u0010\"\u001a\u00020\u0012J\u0016\u0010!\u001a\u00020\u00002\u0006\u0010\f\u001a\u00020\u00042\u0006\u0010\"\u001a\u00020\u0004J\u0016\u0010!\u001a\u00020\u00002\u0006\u0010\f\u001a\u00020\u00042\u0006\u0010\"\u001a\u00020\u0019J\u0018\u0010#\u001a\u00020\u00002\u0006\u0010\f\u001a\u00020\u00042\u0006\u0010\"\u001a\u00020\tH\u0002J(\u0010$\u001a\u0002H%\"\u0006\b\u0000\u0010%\u0018\u00012\u0006\u0010\f\u001a\u00020\u00042\b\u0010\"\u001a\u0004\u0018\u00010\tH\u0082\b¢\u0006\u0002\u0010&J\u0014\u0010'\u001a\u00020\u001b*\u00020\t2\u0006\u0010\f\u001a\u00020\u0004H\u0002R\u0014\u0010\u0003\u001a\u00020\u00048VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u0005\u0010\u0006R\u0014\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\t0\bX\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006*"}, m183d2 = {"Lcom/facebook/react/common/mapbuffer/WritableMapBuffer;", "Lcom/facebook/react/common/mapbuffer/MapBuffer;", "()V", NewHtcHomeBadger.COUNT, "", "getCount", "()I", "values", "Landroid/util/SparseArray;", "", "contains", "", "key", "entryAt", "Lcom/facebook/react/common/mapbuffer/MapBuffer$Entry;", "offset", "getBoolean", "getDouble", "", "getInt", "getKeyOffset", "getKeys", "", "getMapBuffer", "getString", "", "getType", "Lcom/facebook/react/common/mapbuffer/MapBuffer$DataType;", "getValues", "", "()[Ljava/lang/Object;", "iterator", "", "put", "value", "putInternal", "verifyValue", "T", "(ILjava/lang/Object;)Ljava/lang/Object;", "dataType", "Companion", "MapBufferEntry", "ReactAndroid_release"}, m182k = 1, m181mv = {1, 6, 0}, m179xi = 48)
/* loaded from: classes.dex */
public final class WritableMapBuffer implements MapBuffer {
    public static final Companion Companion = new Companion(null);
    private final SparseArray<Object> values = new SparseArray<>();

    public final WritableMapBuffer put(int r1, boolean z) {
        return putInternal(r1, Boolean.valueOf(z));
    }

    public final WritableMapBuffer put(int r1, int r2) {
        return putInternal(r1, Integer.valueOf(r2));
    }

    public final WritableMapBuffer put(int r1, double d) {
        return putInternal(r1, Double.valueOf(d));
    }

    public final WritableMapBuffer put(int r2, String value) {
        Intrinsics.checkNotNullParameter(value, "value");
        return putInternal(r2, value);
    }

    public final WritableMapBuffer put(int r2, MapBuffer value) {
        Intrinsics.checkNotNullParameter(value, "value");
        return putInternal(r2, value);
    }

    private final WritableMapBuffer putInternal(int r4, Object obj) {
        IntRange kEY_RANGE$ReactAndroid_release = MapBuffer.Companion.getKEY_RANGE$ReactAndroid_release();
        int first = kEY_RANGE$ReactAndroid_release.getFirst();
        boolean z = false;
        if (r4 <= kEY_RANGE$ReactAndroid_release.getLast() && first <= r4) {
            z = true;
        }
        if (!z) {
            throw new IllegalArgumentException("Only integers in [0;65535] range are allowed for keys.".toString());
        }
        this.values.put(r4, obj);
        return this;
    }

    @Override // com.facebook.react.common.mapbuffer.MapBuffer
    public int getCount() {
        return this.values.size();
    }

    @Override // com.facebook.react.common.mapbuffer.MapBuffer
    public boolean contains(int r2) {
        return this.values.get(r2) != null;
    }

    @Override // com.facebook.react.common.mapbuffer.MapBuffer
    public int getKeyOffset(int r2) {
        return this.values.indexOfKey(r2);
    }

    @Override // com.facebook.react.common.mapbuffer.MapBuffer
    public MapBuffer.Entry entryAt(int r2) {
        return new MapBufferEntry(this, r2);
    }

    @Override // com.facebook.react.common.mapbuffer.MapBuffer
    public MapBuffer.DataType getType(int r3) {
        Object value = this.values.get(r3);
        if (!(value != null)) {
            throw new IllegalArgumentException(Intrinsics.stringPlus("Key not found: ", Integer.valueOf(r3)).toString());
        }
        Intrinsics.checkNotNullExpressionValue(value, "value");
        return dataType(value, r3);
    }

    @Override // com.facebook.react.common.mapbuffer.MapBuffer
    public boolean getBoolean(int r4) {
        Object obj = this.values.get(r4);
        if (!(obj != null)) {
            throw new IllegalArgumentException(Intrinsics.stringPlus("Key not found: ", Integer.valueOf(r4)).toString());
        }
        if (!(obj instanceof Boolean)) {
            throw new IllegalStateException(("Expected " + Boolean.class + " for key: " + r4 + ", found " + obj.getClass() + " instead.").toString());
        }
        return ((Boolean) obj).booleanValue();
    }

    @Override // com.facebook.react.common.mapbuffer.MapBuffer
    public int getInt(int r4) {
        Object obj = this.values.get(r4);
        if (!(obj != null)) {
            throw new IllegalArgumentException(Intrinsics.stringPlus("Key not found: ", Integer.valueOf(r4)).toString());
        }
        if (!(obj instanceof Integer)) {
            throw new IllegalStateException(("Expected " + Integer.class + " for key: " + r4 + ", found " + obj.getClass() + " instead.").toString());
        }
        return ((Number) obj).intValue();
    }

    @Override // com.facebook.react.common.mapbuffer.MapBuffer
    public double getDouble(int r4) {
        Object obj = this.values.get(r4);
        if (!(obj != null)) {
            throw new IllegalArgumentException(Intrinsics.stringPlus("Key not found: ", Integer.valueOf(r4)).toString());
        }
        if (!(obj instanceof Double)) {
            throw new IllegalStateException(("Expected " + Double.class + " for key: " + r4 + ", found " + obj.getClass() + " instead.").toString());
        }
        return ((Number) obj).doubleValue();
    }

    @Override // com.facebook.react.common.mapbuffer.MapBuffer
    public String getString(int r4) {
        Object obj = this.values.get(r4);
        if (!(obj != null)) {
            throw new IllegalArgumentException(Intrinsics.stringPlus("Key not found: ", Integer.valueOf(r4)).toString());
        }
        if (!(obj instanceof String)) {
            throw new IllegalStateException(("Expected " + String.class + " for key: " + r4 + ", found " + obj.getClass() + " instead.").toString());
        }
        return (String) obj;
    }

    @Override // com.facebook.react.common.mapbuffer.MapBuffer
    public MapBuffer getMapBuffer(int r4) {
        Object obj = this.values.get(r4);
        if (!(obj != null)) {
            throw new IllegalArgumentException(Intrinsics.stringPlus("Key not found: ", Integer.valueOf(r4)).toString());
        }
        if (!(obj instanceof MapBuffer)) {
            throw new IllegalStateException(("Expected " + MapBuffer.class + " for key: " + r4 + ", found " + obj.getClass() + " instead.").toString());
        }
        return (MapBuffer) obj;
    }

    /* JADX WARN: Multi-variable type inference failed */
    private final /* synthetic */ <T> T verifyValue(int r4, Object obj) {
        if (!(obj != 0)) {
            throw new IllegalArgumentException(Intrinsics.stringPlus("Key not found: ", Integer.valueOf(r4)).toString());
        }
        Intrinsics.reifiedOperationMarker(3, "T");
        if (obj instanceof Object) {
            return obj;
        }
        StringBuilder sb = new StringBuilder();
        sb.append("Expected ");
        Intrinsics.reifiedOperationMarker(4, "T");
        sb.append(Object.class);
        sb.append(" for key: ");
        sb.append(r4);
        sb.append(", found ");
        sb.append(obj.getClass());
        sb.append(" instead.");
        throw new IllegalStateException(sb.toString().toString());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final MapBuffer.DataType dataType(Object obj, int r5) {
        if (obj instanceof Boolean) {
            return MapBuffer.DataType.BOOL;
        }
        if (obj instanceof Integer) {
            return MapBuffer.DataType.INT;
        }
        if (obj instanceof Double) {
            return MapBuffer.DataType.DOUBLE;
        }
        if (obj instanceof String) {
            return MapBuffer.DataType.STRING;
        }
        if (obj instanceof MapBuffer) {
            return MapBuffer.DataType.MAP;
        }
        throw new IllegalStateException("Key " + r5 + " has value of unknown type: " + obj.getClass());
    }

    @Override // java.lang.Iterable
    public Iterator<MapBuffer.Entry> iterator() {
        return new WritableMapBuffer$iterator$1(this);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* compiled from: WritableMapBuffer.kt */
    @Metadata(m184d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u0006\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0082\u0004\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004R\u0014\u0010\u0005\u001a\u00020\u00068VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u0007\u0010\bR\u0014\u0010\t\u001a\u00020\n8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u000b\u0010\fR\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\r\u001a\u00020\u00038VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u000e\u0010\u000fR\u0014\u0010\u0010\u001a\u00020\u0003X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u000fR\u0014\u0010\u0012\u001a\u00020\u00138VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u0014\u0010\u0015R\u0014\u0010\u0016\u001a\u00020\u00178VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u0018\u0010\u0019R\u0014\u0010\u001a\u001a\u00020\u001bX\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u001c\u0010\u001d¨\u0006\u001e"}, m183d2 = {"Lcom/facebook/react/common/mapbuffer/WritableMapBuffer$MapBufferEntry;", "Lcom/facebook/react/common/mapbuffer/MapBuffer$Entry;", "index", "", "(Lcom/facebook/react/common/mapbuffer/WritableMapBuffer;I)V", "booleanValue", "", "getBooleanValue", "()Z", "doubleValue", "", "getDoubleValue", "()D", "intValue", "getIntValue", "()I", "key", "getKey", "mapBufferValue", "Lcom/facebook/react/common/mapbuffer/MapBuffer;", "getMapBufferValue", "()Lcom/facebook/react/common/mapbuffer/MapBuffer;", "stringValue", "", "getStringValue", "()Ljava/lang/String;", SessionDescription.ATTR_TYPE, "Lcom/facebook/react/common/mapbuffer/MapBuffer$DataType;", "getType", "()Lcom/facebook/react/common/mapbuffer/MapBuffer$DataType;", "ReactAndroid_release"}, m182k = 1, m181mv = {1, 6, 0}, m179xi = 48)
    /* loaded from: classes.dex */
    public final class MapBufferEntry implements MapBuffer.Entry {
        private final int index;
        private final int key;
        final /* synthetic */ WritableMapBuffer this$0;
        private final MapBuffer.DataType type;

        public MapBufferEntry(WritableMapBuffer this$0, int r3) {
            Intrinsics.checkNotNullParameter(this$0, "this$0");
            this.this$0 = this$0;
            this.index = r3;
            this.key = this$0.values.keyAt(r3);
            Object valueAt = this$0.values.valueAt(r3);
            Intrinsics.checkNotNullExpressionValue(valueAt, "values.valueAt(index)");
            this.type = this$0.dataType(valueAt, getKey());
        }

        @Override // com.facebook.react.common.mapbuffer.MapBuffer.Entry
        public int getKey() {
            return this.key;
        }

        @Override // com.facebook.react.common.mapbuffer.MapBuffer.Entry
        public MapBuffer.DataType getType() {
            return this.type;
        }

        @Override // com.facebook.react.common.mapbuffer.MapBuffer.Entry
        public boolean getBooleanValue() {
            int key = getKey();
            Object valueAt = this.this$0.values.valueAt(this.index);
            if (!(valueAt != null)) {
                throw new IllegalArgumentException(Intrinsics.stringPlus("Key not found: ", Integer.valueOf(key)).toString());
            }
            if (!(valueAt instanceof Boolean)) {
                throw new IllegalStateException(("Expected " + Boolean.class + " for key: " + key + ", found " + valueAt.getClass() + " instead.").toString());
            }
            return ((Boolean) valueAt).booleanValue();
        }

        @Override // com.facebook.react.common.mapbuffer.MapBuffer.Entry
        public int getIntValue() {
            int key = getKey();
            Object valueAt = this.this$0.values.valueAt(this.index);
            if (!(valueAt != null)) {
                throw new IllegalArgumentException(Intrinsics.stringPlus("Key not found: ", Integer.valueOf(key)).toString());
            }
            if (!(valueAt instanceof Integer)) {
                throw new IllegalStateException(("Expected " + Integer.class + " for key: " + key + ", found " + valueAt.getClass() + " instead.").toString());
            }
            return ((Number) valueAt).intValue();
        }

        @Override // com.facebook.react.common.mapbuffer.MapBuffer.Entry
        public double getDoubleValue() {
            int key = getKey();
            Object valueAt = this.this$0.values.valueAt(this.index);
            if (!(valueAt != null)) {
                throw new IllegalArgumentException(Intrinsics.stringPlus("Key not found: ", Integer.valueOf(key)).toString());
            }
            if (!(valueAt instanceof Double)) {
                throw new IllegalStateException(("Expected " + Double.class + " for key: " + key + ", found " + valueAt.getClass() + " instead.").toString());
            }
            return ((Number) valueAt).doubleValue();
        }

        @Override // com.facebook.react.common.mapbuffer.MapBuffer.Entry
        public String getStringValue() {
            int key = getKey();
            Object valueAt = this.this$0.values.valueAt(this.index);
            if (!(valueAt != null)) {
                throw new IllegalArgumentException(Intrinsics.stringPlus("Key not found: ", Integer.valueOf(key)).toString());
            }
            if (!(valueAt instanceof String)) {
                throw new IllegalStateException(("Expected " + String.class + " for key: " + key + ", found " + valueAt.getClass() + " instead.").toString());
            }
            return (String) valueAt;
        }

        @Override // com.facebook.react.common.mapbuffer.MapBuffer.Entry
        public MapBuffer getMapBufferValue() {
            int key = getKey();
            Object valueAt = this.this$0.values.valueAt(this.index);
            if (!(valueAt != null)) {
                throw new IllegalArgumentException(Intrinsics.stringPlus("Key not found: ", Integer.valueOf(key)).toString());
            }
            if (!(valueAt instanceof MapBuffer)) {
                throw new IllegalStateException(("Expected " + MapBuffer.class + " for key: " + key + ", found " + valueAt.getClass() + " instead.").toString());
            }
            return (MapBuffer) valueAt;
        }
    }

    private final int[] getKeys() {
        int size = this.values.size();
        int[] r1 = new int[size];
        for (int r2 = 0; r2 < size; r2++) {
            r1[r2] = this.values.keyAt(r2);
        }
        return r1;
    }

    private final Object[] getValues() {
        int size = this.values.size();
        Object[] objArr = new Object[size];
        for (int r2 = 0; r2 < size; r2++) {
            Object valueAt = this.values.valueAt(r2);
            Intrinsics.checkNotNullExpressionValue(valueAt, "values.valueAt(it)");
            objArr[r2] = valueAt;
        }
        return objArr;
    }

    /* compiled from: WritableMapBuffer.kt */
    @Metadata(m184d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002¨\u0006\u0003"}, m183d2 = {"Lcom/facebook/react/common/mapbuffer/WritableMapBuffer$Companion;", "", "()V", "ReactAndroid_release"}, m182k = 1, m181mv = {1, 6, 0}, m179xi = 48)
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
