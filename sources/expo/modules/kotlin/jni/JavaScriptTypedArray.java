package expo.modules.kotlin.jni;

import com.facebook.jni.HybridData;
import com.facebook.react.uimanager.ViewProps;
import com.google.android.exoplayer2.source.rtsp.SessionDescription;
import expo.modules.kotlin.typedarray.TypedArray;
import java.nio.ByteBuffer;
import java.util.NoSuchElementException;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.Metadata;
import kotlin.jvm.functions.Functions;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: JavaScriptTypedArray.kt */
@Metadata(m184d1 = {"\u0000\\\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u0012\n\u0002\b\u0003\n\u0002\u0010\n\n\u0002\b\u0002\n\u0002\u0010\t\n\u0000\n\u0002\u0010\u0005\n\u0000\n\u0002\u0010\u0006\n\u0000\n\u0002\u0010\u0007\n\u0000\n\u0002\u0018\u0002\n\u0002\b\t\b\u0007\u0018\u00002\u00020\u00012\u00020\u0002B\u000f\b\u0007\u0012\u0006\u0010\u0003\u001a\u00020\u0004¢\u0006\u0002\u0010\u0005J\t\u0010\u0017\u001a\u00020\u0007H\u0082 J!\u0010\u0018\u001a\u00020\u00192\u0006\u0010\u001a\u001a\u00020\u001b2\u0006\u0010\u001c\u001a\u00020\u00072\u0006\u0010\u001d\u001a\u00020\u0007H\u0096 J\u0011\u0010\u001e\u001a\u00020\u001f2\u0006\u0010\u001c\u001a\u00020\u0007H\u0096 J\u0011\u0010 \u001a\u00020\u00072\u0006\u0010\u001c\u001a\u00020\u0007H\u0096 J\u0011\u0010!\u001a\u00020\"2\u0006\u0010\u001c\u001a\u00020\u0007H\u0096 J\u0011\u0010#\u001a\u00020$2\u0006\u0010\u001c\u001a\u00020\u0007H\u0096 J\u0011\u0010%\u001a\u00020&2\u0006\u0010\u001c\u001a\u00020\u0007H\u0096 J\u0011\u0010'\u001a\u00020(2\u0006\u0010\u001c\u001a\u00020\u0007H\u0096 J\t\u0010)\u001a\u00020*H\u0096 J!\u0010+\u001a\u00020\u00192\u0006\u0010\u001a\u001a\u00020\u001b2\u0006\u0010\u001c\u001a\u00020\u00072\u0006\u0010\u001d\u001a\u00020\u0007H\u0096 J\u0019\u0010,\u001a\u00020\u00192\u0006\u0010\u001c\u001a\u00020\u00072\u0006\u0010-\u001a\u00020\u001fH\u0096 J\u0019\u0010.\u001a\u00020\u00192\u0006\u0010\u001c\u001a\u00020\u00072\u0006\u0010-\u001a\u00020\u0007H\u0096 J\u0019\u0010/\u001a\u00020\u00192\u0006\u0010\u001c\u001a\u00020\u00072\u0006\u0010-\u001a\u00020\"H\u0096 J\u0019\u00100\u001a\u00020\u00192\u0006\u0010\u001c\u001a\u00020\u00072\u0006\u0010-\u001a\u00020$H\u0096 J\u0019\u00101\u001a\u00020\u00192\u0006\u0010\u001c\u001a\u00020\u00072\u0006\u0010-\u001a\u00020&H\u0096 J\u0019\u00102\u001a\u00020\u00192\u0006\u0010\u001c\u001a\u00020\u00072\u0006\u0010-\u001a\u00020(H\u0096 R\u001b\u0010\u0006\u001a\u00020\u00078VX\u0096\u0084\u0002¢\u0006\f\n\u0004\b\n\u0010\u000b\u001a\u0004\b\b\u0010\tR\u001b\u0010\f\u001a\u00020\u00078VX\u0096\u0084\u0002¢\u0006\f\n\u0004\b\u000e\u0010\u000b\u001a\u0004\b\r\u0010\tR\u001b\u0010\u000f\u001a\u00020\u00108VX\u0096\u0084\u0002¢\u0006\f\n\u0004\b\u0013\u0010\u000b\u001a\u0004\b\u0011\u0010\u0012R\u001b\u0010\u0014\u001a\u00020\u00078VX\u0096\u0084\u0002¢\u0006\f\n\u0004\b\u0016\u0010\u000b\u001a\u0004\b\u0015\u0010\t¨\u00063"}, m183d2 = {"Lexpo/modules/kotlin/jni/JavaScriptTypedArray;", "Lexpo/modules/kotlin/jni/JavaScriptObject;", "Lexpo/modules/kotlin/typedarray/TypedArray;", "hybridData", "Lcom/facebook/jni/HybridData;", "(Lcom/facebook/jni/HybridData;)V", "byteLength", "", "getByteLength", "()I", "byteLength$delegate", "Lkotlin/Lazy;", "byteOffset", "getByteOffset", "byteOffset$delegate", "kind", "Lexpo/modules/kotlin/jni/TypedArrayKind;", "getKind", "()Lexpo/modules/kotlin/jni/TypedArrayKind;", "kind$delegate", SessionDescription.ATTR_LENGTH, "getLength", "length$delegate", "getRawKind", "read", "", "buffer", "", ViewProps.POSITION, "size", "read2Byte", "", "read4Byte", "read8Byte", "", "readByte", "", "readDouble", "", "readFloat", "", "toDirectBuffer", "Ljava/nio/ByteBuffer;", "write", "write2Byte", "value", "write4Byte", "write8Byte", "writeByte", "writeDouble", "writeFloat", "expo-modules-core_release"}, m182k = 1, m181mv = {1, 6, 0}, m179xi = 48)
/* loaded from: classes4.dex */
public final class JavaScriptTypedArray extends JavaScriptObject implements TypedArray {
    private final Lazy byteLength$delegate;
    private final Lazy byteOffset$delegate;
    private final Lazy kind$delegate;
    private final Lazy length$delegate;

    /* JADX INFO: Access modifiers changed from: private */
    public final native int getRawKind();

    @Override // expo.modules.kotlin.typedarray.TypedArray
    public native void read(byte[] bArr, int r2, int r3);

    @Override // expo.modules.kotlin.typedarray.TypedArray
    public native short read2Byte(int r1);

    @Override // expo.modules.kotlin.typedarray.TypedArray
    public native int read4Byte(int r1);

    @Override // expo.modules.kotlin.typedarray.TypedArray
    public native long read8Byte(int r1);

    @Override // expo.modules.kotlin.typedarray.TypedArray
    public native byte readByte(int r1);

    @Override // expo.modules.kotlin.typedarray.TypedArray
    public native double readDouble(int r1);

    @Override // expo.modules.kotlin.typedarray.TypedArray
    public native float readFloat(int r1);

    @Override // expo.modules.kotlin.typedarray.TypedArray
    public native ByteBuffer toDirectBuffer();

    @Override // expo.modules.kotlin.typedarray.TypedArray
    public native void write(byte[] bArr, int r2, int r3);

    @Override // expo.modules.kotlin.typedarray.TypedArray
    public native void write2Byte(int r1, short s);

    @Override // expo.modules.kotlin.typedarray.TypedArray
    public native void write4Byte(int r1, int r2);

    @Override // expo.modules.kotlin.typedarray.TypedArray
    public native void write8Byte(int r1, long j);

    @Override // expo.modules.kotlin.typedarray.TypedArray
    public native void writeByte(int r1, byte b);

    @Override // expo.modules.kotlin.typedarray.TypedArray
    public native void writeDouble(int r1, double d);

    @Override // expo.modules.kotlin.typedarray.TypedArray
    public native void writeFloat(int r1, float f);

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public JavaScriptTypedArray(HybridData hybridData) {
        super(hybridData);
        Intrinsics.checkNotNullParameter(hybridData, "hybridData");
        this.kind$delegate = LazyKt.lazy(new Functions<TypedArrayKind>() { // from class: expo.modules.kotlin.jni.JavaScriptTypedArray$kind$2
            /* JADX INFO: Access modifiers changed from: package-private */
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Functions
            public final TypedArrayKind invoke() {
                int rawKind;
                boolean z;
                rawKind = JavaScriptTypedArray.this.getRawKind();
                TypedArrayKind[] values = TypedArrayKind.values();
                int length = values.length;
                int r4 = 0;
                while (r4 < length) {
                    TypedArrayKind typedArrayKind = values[r4];
                    r4++;
                    if (typedArrayKind.getValue() == rawKind) {
                        z = true;
                        continue;
                    } else {
                        z = false;
                        continue;
                    }
                    if (z) {
                        return typedArrayKind;
                    }
                }
                throw new NoSuchElementException("Array contains no element matching the predicate.");
            }
        });
        this.length$delegate = LazyKt.lazy(new Functions<Integer>() { // from class: expo.modules.kotlin.jni.JavaScriptTypedArray$length$2
            /* JADX INFO: Access modifiers changed from: package-private */
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Functions
            public final Integer invoke() {
                return Integer.valueOf((int) JavaScriptTypedArray.this.getProperty(SessionDescription.ATTR_LENGTH).getDouble());
            }
        });
        this.byteLength$delegate = LazyKt.lazy(new Functions<Integer>() { // from class: expo.modules.kotlin.jni.JavaScriptTypedArray$byteLength$2
            /* JADX INFO: Access modifiers changed from: package-private */
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Functions
            public final Integer invoke() {
                return Integer.valueOf((int) JavaScriptTypedArray.this.getProperty("byteLength").getDouble());
            }
        });
        this.byteOffset$delegate = LazyKt.lazy(new Functions<Integer>() { // from class: expo.modules.kotlin.jni.JavaScriptTypedArray$byteOffset$2
            /* JADX INFO: Access modifiers changed from: package-private */
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Functions
            public final Integer invoke() {
                return Integer.valueOf((int) JavaScriptTypedArray.this.getProperty("byteOffset").getDouble());
            }
        });
    }

    @Override // expo.modules.kotlin.typedarray.TypedArray
    public TypedArrayKind getKind() {
        return (TypedArrayKind) this.kind$delegate.getValue();
    }

    @Override // expo.modules.kotlin.typedarray.TypedArray
    public int getLength() {
        return ((Number) this.length$delegate.getValue()).intValue();
    }

    @Override // expo.modules.kotlin.typedarray.TypedArray
    public int getByteLength() {
        return ((Number) this.byteLength$delegate.getValue()).intValue();
    }

    @Override // expo.modules.kotlin.typedarray.TypedArray
    public int getByteOffset() {
        return ((Number) this.byteOffset$delegate.getValue()).intValue();
    }
}
