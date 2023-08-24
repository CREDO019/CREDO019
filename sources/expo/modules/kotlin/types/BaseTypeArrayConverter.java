package expo.modules.kotlin.types;

import expo.modules.kotlin.jni.CppType;
import expo.modules.kotlin.jni.ExpectedType;
import expo.modules.kotlin.jni.JavaScriptTypedArray;
import expo.modules.kotlin.typedarray.TypedArray;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: TypedArrayTypeConverter.kt */
@Metadata(m184d1 = {"\u00000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\b&\u0018\u0000*\b\b\u0000\u0010\u0001*\u00020\u00022\b\u0012\u0004\u0012\u0002H\u00010\u0003B\r\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J\u0015\u0010\u0007\u001a\u00028\u00002\u0006\u0010\b\u001a\u00020\tH\u0016¢\u0006\u0002\u0010\nJ\b\u0010\u000b\u001a\u00020\fH\u0016J\b\u0010\r\u001a\u00020\u0005H\u0016J\u0015\u0010\u000e\u001a\u00028\u00002\u0006\u0010\b\u001a\u00020\u000fH&¢\u0006\u0002\u0010\u0010¨\u0006\u0011"}, m183d2 = {"Lexpo/modules/kotlin/types/BaseTypeArrayConverter;", "T", "Lexpo/modules/kotlin/typedarray/TypedArray;", "Lexpo/modules/kotlin/types/TypeConverter;", "isOptional", "", "(Z)V", "convertNonOptional", "value", "", "(Ljava/lang/Object;)Lexpo/modules/kotlin/typedarray/TypedArray;", "getCppRequiredTypes", "Lexpo/modules/kotlin/jni/ExpectedType;", "isTrivial", "wrapJavaScriptTypedArray", "Lexpo/modules/kotlin/jni/JavaScriptTypedArray;", "(Lexpo/modules/kotlin/jni/JavaScriptTypedArray;)Lexpo/modules/kotlin/typedarray/TypedArray;", "expo-modules-core_release"}, m182k = 1, m181mv = {1, 6, 0}, m179xi = 48)
/* loaded from: classes4.dex */
public abstract class BaseTypeArrayConverter<T extends TypedArray> extends TypeConverter<T> {
    @Override // expo.modules.kotlin.types.TypeConverter
    public boolean isTrivial() {
        return false;
    }

    public abstract T wrapJavaScriptTypedArray(JavaScriptTypedArray javaScriptTypedArray);

    public BaseTypeArrayConverter(boolean z) {
        super(z);
    }

    @Override // expo.modules.kotlin.types.TypeConverter
    public T convertNonOptional(Object value) {
        Intrinsics.checkNotNullParameter(value, "value");
        return wrapJavaScriptTypedArray((JavaScriptTypedArray) value);
    }

    @Override // expo.modules.kotlin.types.TypeConverter
    public ExpectedType getCppRequiredTypes() {
        return new ExpectedType(CppType.TYPED_ARRAY);
    }
}
