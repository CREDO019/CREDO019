package expo.modules.kotlin.typedarray;

import com.facebook.react.uimanager.ViewProps;
import com.google.android.exoplayer2.source.rtsp.SessionDescription;
import expo.modules.kotlin.jni.JavaScriptTypedArray;
import expo.modules.kotlin.jni.TypedArrayKind;
import expo.modules.kotlin.typedarray.GenericTypedArray;
import java.nio.ByteBuffer;
import java.util.Iterator;
import kotlin.Metadata;
import kotlin.ULong;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: ConcreteTypedArrays.kt */
@Metadata(m184d1 = {"\u0000b\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u0012\n\u0002\b\u0003\n\u0002\u0010\n\n\u0002\b\u0002\n\u0002\u0010\t\n\u0000\n\u0002\u0010\u0005\n\u0000\n\u0002\u0010\u0006\n\u0000\n\u0002\u0010\u0007\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\b\u0018\u00002\u00020\u00012\b\u0012\u0004\u0012\u00020\u00030\u0002B\r\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J!\u0010\u0013\u001a\u00020\u00032\u0006\u0010\u0014\u001a\u00020\bH\u0096\u0002ø\u0001\u0000ø\u0001\u0001ø\u0001\u0002¢\u0006\u0004\b\u0015\u0010\u0016J!\u0010\u0017\u001a\u00020\u00182\u0006\u0010\u0019\u001a\u00020\u001a2\u0006\u0010\u001b\u001a\u00020\b2\u0006\u0010\u001c\u001a\u00020\bH\u0096\u0001J\u0011\u0010\u001d\u001a\u00020\u001e2\u0006\u0010\u001b\u001a\u00020\bH\u0096\u0001J\u0011\u0010\u001f\u001a\u00020\b2\u0006\u0010\u001b\u001a\u00020\bH\u0096\u0001J\u0011\u0010 \u001a\u00020!2\u0006\u0010\u001b\u001a\u00020\bH\u0096\u0001J\u0011\u0010\"\u001a\u00020#2\u0006\u0010\u001b\u001a\u00020\bH\u0096\u0001J\u0011\u0010$\u001a\u00020%2\u0006\u0010\u001b\u001a\u00020\bH\u0096\u0001J\u0011\u0010&\u001a\u00020'2\u0006\u0010\u001b\u001a\u00020\bH\u0096\u0001J&\u0010(\u001a\u00020\u00182\u0006\u0010\u0014\u001a\u00020\b2\u0006\u0010)\u001a\u00020\u0003H\u0096\u0002ø\u0001\u0000ø\u0001\u0002¢\u0006\u0004\b*\u0010+J\t\u0010,\u001a\u00020-H\u0096\u0001J!\u0010.\u001a\u00020\u00182\u0006\u0010\u0019\u001a\u00020\u001a2\u0006\u0010\u001b\u001a\u00020\b2\u0006\u0010\u001c\u001a\u00020\bH\u0096\u0001J\u0019\u0010/\u001a\u00020\u00182\u0006\u0010\u001b\u001a\u00020\b2\u0006\u0010)\u001a\u00020\u001eH\u0096\u0001J\u0019\u00100\u001a\u00020\u00182\u0006\u0010\u001b\u001a\u00020\b2\u0006\u0010)\u001a\u00020\bH\u0096\u0001J\u0019\u00101\u001a\u00020\u00182\u0006\u0010\u001b\u001a\u00020\b2\u0006\u0010)\u001a\u00020!H\u0096\u0001J\u0019\u00102\u001a\u00020\u00182\u0006\u0010\u001b\u001a\u00020\b2\u0006\u0010)\u001a\u00020#H\u0096\u0001J\u0019\u00103\u001a\u00020\u00182\u0006\u0010\u001b\u001a\u00020\b2\u0006\u0010)\u001a\u00020%H\u0096\u0001J\u0019\u00104\u001a\u00020\u00182\u0006\u0010\u001b\u001a\u00020\b2\u0006\u0010)\u001a\u00020'H\u0096\u0001R\u0012\u0010\u0007\u001a\u00020\bX\u0096\u0005¢\u0006\u0006\u001a\u0004\b\t\u0010\nR\u0012\u0010\u000b\u001a\u00020\bX\u0096\u0005¢\u0006\u0006\u001a\u0004\b\f\u0010\nR\u0012\u0010\r\u001a\u00020\u000eX\u0096\u0005¢\u0006\u0006\u001a\u0004\b\u000f\u0010\u0010R\u0012\u0010\u0011\u001a\u00020\bX\u0096\u0005¢\u0006\u0006\u001a\u0004\b\u0012\u0010\nR\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000ø\u0001\u0000\u0082\u0002\u000f\n\u0002\b\u0019\n\u0002\b!\n\u0005\b¡\u001e0\u0001¨\u00065"}, m183d2 = {"Lexpo/modules/kotlin/typedarray/BigUint64Array;", "Lexpo/modules/kotlin/typedarray/TypedArray;", "Lexpo/modules/kotlin/typedarray/GenericTypedArray;", "Lkotlin/ULong;", "rawArray", "Lexpo/modules/kotlin/jni/JavaScriptTypedArray;", "(Lexpo/modules/kotlin/jni/JavaScriptTypedArray;)V", "byteLength", "", "getByteLength", "()I", "byteOffset", "getByteOffset", "kind", "Lexpo/modules/kotlin/jni/TypedArrayKind;", "getKind", "()Lexpo/modules/kotlin/jni/TypedArrayKind;", SessionDescription.ATTR_LENGTH, "getLength", "get", "index", "get-I7RO_PI", "(I)J", "read", "", "buffer", "", ViewProps.POSITION, "size", "read2Byte", "", "read4Byte", "read8Byte", "", "readByte", "", "readDouble", "", "readFloat", "", "set", "value", "set-2TYgG_w", "(IJ)V", "toDirectBuffer", "Ljava/nio/ByteBuffer;", "write", "write2Byte", "write4Byte", "write8Byte", "writeByte", "writeDouble", "writeFloat", "expo-modules-core_release"}, m182k = 1, m181mv = {1, 6, 0}, m179xi = 48)
/* loaded from: classes4.dex */
public final class BigUint64Array implements TypedArray, GenericTypedArray<ULong> {
    private final JavaScriptTypedArray rawArray;

    @Override // expo.modules.kotlin.typedarray.TypedArray
    public int getByteLength() {
        return this.rawArray.getByteLength();
    }

    @Override // expo.modules.kotlin.typedarray.TypedArray
    public int getByteOffset() {
        return this.rawArray.getByteOffset();
    }

    @Override // expo.modules.kotlin.typedarray.TypedArray
    public TypedArrayKind getKind() {
        return this.rawArray.getKind();
    }

    @Override // expo.modules.kotlin.typedarray.TypedArray
    public int getLength() {
        return this.rawArray.getLength();
    }

    @Override // expo.modules.kotlin.typedarray.TypedArray
    public void read(byte[] buffer, int r3, int r4) {
        Intrinsics.checkNotNullParameter(buffer, "buffer");
        this.rawArray.read(buffer, r3, r4);
    }

    @Override // expo.modules.kotlin.typedarray.TypedArray
    public short read2Byte(int r2) {
        return this.rawArray.read2Byte(r2);
    }

    @Override // expo.modules.kotlin.typedarray.TypedArray
    public int read4Byte(int r2) {
        return this.rawArray.read4Byte(r2);
    }

    @Override // expo.modules.kotlin.typedarray.TypedArray
    public long read8Byte(int r3) {
        return this.rawArray.read8Byte(r3);
    }

    @Override // expo.modules.kotlin.typedarray.TypedArray
    public byte readByte(int r2) {
        return this.rawArray.readByte(r2);
    }

    @Override // expo.modules.kotlin.typedarray.TypedArray
    public double readDouble(int r3) {
        return this.rawArray.readDouble(r3);
    }

    @Override // expo.modules.kotlin.typedarray.TypedArray
    public float readFloat(int r2) {
        return this.rawArray.readFloat(r2);
    }

    @Override // expo.modules.kotlin.typedarray.TypedArray
    public ByteBuffer toDirectBuffer() {
        return this.rawArray.toDirectBuffer();
    }

    @Override // expo.modules.kotlin.typedarray.TypedArray
    public void write(byte[] buffer, int r3, int r4) {
        Intrinsics.checkNotNullParameter(buffer, "buffer");
        this.rawArray.write(buffer, r3, r4);
    }

    @Override // expo.modules.kotlin.typedarray.TypedArray
    public void write2Byte(int r2, short s) {
        this.rawArray.write2Byte(r2, s);
    }

    @Override // expo.modules.kotlin.typedarray.TypedArray
    public void write4Byte(int r2, int r3) {
        this.rawArray.write4Byte(r2, r3);
    }

    @Override // expo.modules.kotlin.typedarray.TypedArray
    public void write8Byte(int r2, long j) {
        this.rawArray.write8Byte(r2, j);
    }

    @Override // expo.modules.kotlin.typedarray.TypedArray
    public void writeByte(int r2, byte b) {
        this.rawArray.writeByte(r2, b);
    }

    @Override // expo.modules.kotlin.typedarray.TypedArray
    public void writeDouble(int r2, double d) {
        this.rawArray.writeDouble(r2, d);
    }

    @Override // expo.modules.kotlin.typedarray.TypedArray
    public void writeFloat(int r2, float f) {
        this.rawArray.writeFloat(r2, f);
    }

    public BigUint64Array(JavaScriptTypedArray rawArray) {
        Intrinsics.checkNotNullParameter(rawArray, "rawArray");
        this.rawArray = rawArray;
    }

    @Override // expo.modules.kotlin.typedarray.GenericTypedArray
    public /* bridge */ /* synthetic */ ULong get(int r3) {
        return ULong.m1915boximpl(m1677getI7RO_PI(r3));
    }

    @Override // expo.modules.kotlin.typedarray.GenericTypedArray, java.lang.Iterable
    public Iterator<ULong> iterator() {
        return GenericTypedArray.DefaultImpls.iterator(this);
    }

    @Override // expo.modules.kotlin.typedarray.GenericTypedArray
    public /* bridge */ /* synthetic */ void set(int r3, ULong uLong) {
        m1678set2TYgG_w(r3, uLong.m1972unboximpl());
    }

    /* renamed from: get-I7RO_PI  reason: not valid java name */
    public long m1677getI7RO_PI(int r3) {
        BigUint64Array bigUint64Array = this;
        if (r3 >= 0 && r3 < bigUint64Array.getLength()) {
            return ULong.m1921constructorimpl(read8Byte(r3 * 8));
        }
        throw new IndexOutOfBoundsException();
    }

    /* renamed from: set-2TYgG_w  reason: not valid java name */
    public void m1678set2TYgG_w(int r2, long j) {
        BigUint64Array bigUint64Array = this;
        if (r2 >= 0 && r2 < bigUint64Array.getLength()) {
            write8Byte(r2 * 8, j);
            return;
        }
        throw new IndexOutOfBoundsException();
    }
}
