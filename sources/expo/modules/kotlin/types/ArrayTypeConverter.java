package expo.modules.kotlin.types;

import com.facebook.react.bridge.Dynamic;
import com.facebook.react.bridge.ReadableArray;
import com.facebook.react.bridge.ReadableType;
import expo.modules.core.errors.CodedException;
import expo.modules.kotlin.exception.CollectionElementCastException;
import expo.modules.kotlin.exception.UnexpectedException;
import expo.modules.kotlin.jni.ExpectedType;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.JvmClassMapping;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
import kotlin.reflect.KClass;
import kotlin.reflect.KClassifier;
import kotlin.reflect.KType;
import kotlin.reflect.KTypeProjection;

/* compiled from: ArrayTypeConverter.kt */
@Metadata(m184d1 = {"\u0000H\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0011\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\u0018\u00002\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u00020\u0001B\u0015\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\u0006\u0010\u0005\u001a\u00020\u0006¢\u0006\u0002\u0010\u0007J\u0019\u0010\n\u001a\u0006\u0012\u0002\b\u00030\u00022\u0006\u0010\u000b\u001a\u00020\fH\u0016¢\u0006\u0002\u0010\rJ\u0019\u0010\u000e\u001a\u0006\u0012\u0002\b\u00030\u00022\u0006\u0010\u000b\u001a\u00020\u000fH\u0016¢\u0006\u0002\u0010\u0010J\u001d\u0010\u0011\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\f0\u00022\u0006\u0010\u0012\u001a\u00020\u0013H\u0002¢\u0006\u0002\u0010\u0014J\b\u0010\u0015\u001a\u00020\u0016H\u0016J\b\u0010\u0017\u001a\u00020\u0018H\u0016R\u0012\u0010\b\u001a\u0006\u0012\u0002\b\u00030\tX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0019"}, m183d2 = {"Lexpo/modules/kotlin/types/ArrayTypeConverter;", "Lexpo/modules/kotlin/types/DynamicAwareTypeConverters;", "", "converterProvider", "Lexpo/modules/kotlin/types/TypeConverterProvider;", "arrayType", "Lkotlin/reflect/KType;", "(Lexpo/modules/kotlin/types/TypeConverterProvider;Lkotlin/reflect/KType;)V", "arrayElementConverter", "Lexpo/modules/kotlin/types/TypeConverter;", "convertFromAny", "value", "", "(Ljava/lang/Object;)[Ljava/lang/Object;", "convertFromDynamic", "Lcom/facebook/react/bridge/Dynamic;", "(Lcom/facebook/react/bridge/Dynamic;)[Ljava/lang/Object;", "createTypedArray", "size", "", "(I)[Ljava/lang/Object;", "getCppRequiredTypes", "Lexpo/modules/kotlin/jni/ExpectedType;", "isTrivial", "", "expo-modules-core_release"}, m182k = 1, m181mv = {1, 6, 0}, m179xi = 48)
/* loaded from: classes4.dex */
public final class ArrayTypeConverter extends DynamicAwareTypeConverters<Object[]> {
    private final TypeConverter<?> arrayElementConverter;
    private final KType arrayType;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public ArrayTypeConverter(TypeConverterProvider converterProvider, KType arrayType) {
        super(arrayType.isMarkedNullable());
        Intrinsics.checkNotNullParameter(converterProvider, "converterProvider");
        Intrinsics.checkNotNullParameter(arrayType, "arrayType");
        this.arrayType = arrayType;
        KType type = ((KTypeProjection) CollectionsKt.first((List<? extends Object>) arrayType.getArguments())).getType();
        if (type != null) {
            this.arrayElementConverter = converterProvider.obtainTypeConverter(type);
            return;
        }
        throw new IllegalArgumentException("The array type should contain the type of the elements.".toString());
    }

    @Override // expo.modules.kotlin.types.DynamicAwareTypeConverters
    public Object[] convertFromDynamic(Dynamic value) {
        Intrinsics.checkNotNullParameter(value, "value");
        ReadableArray asArray = value.asArray();
        Object[] createTypedArray = createTypedArray(asArray.size());
        int size = asArray.size();
        int r3 = 0;
        while (r3 < size) {
            int r4 = r3 + 1;
            Dynamic dynamic = asArray.getDynamic(r3);
            Intrinsics.checkNotNullExpressionValue(dynamic, "jsArray\n        .getDynamic(i)");
            try {
                try {
                    try {
                        Object convert = this.arrayElementConverter.convert(dynamic);
                        dynamic.recycle();
                        createTypedArray[r3] = convert;
                        r3 = r4;
                    } catch (CodedException e) {
                        String code = e.getCode();
                        Intrinsics.checkNotNullExpressionValue(code, "e.code");
                        expo.modules.kotlin.exception.CodedException codedException = new expo.modules.kotlin.exception.CodedException(code, e.getMessage(), e.getCause());
                        KType kType = this.arrayType;
                        KType type = ((KTypeProjection) CollectionsKt.first((List<? extends Object>) kType.getArguments())).getType();
                        Intrinsics.checkNotNull(type);
                        ReadableType type2 = dynamic.getType();
                        Intrinsics.checkNotNullExpressionValue(type2, "type");
                        throw new CollectionElementCastException(kType, type, type2, codedException);
                    } catch (Throwable th) {
                        KType kType2 = this.arrayType;
                        KType type3 = ((KTypeProjection) CollectionsKt.first((List<? extends Object>) kType2.getArguments())).getType();
                        Intrinsics.checkNotNull(type3);
                        ReadableType type4 = dynamic.getType();
                        Intrinsics.checkNotNullExpressionValue(type4, "type");
                        throw new CollectionElementCastException(kType2, type3, type4, new UnexpectedException(th));
                    }
                } catch (expo.modules.kotlin.exception.CodedException e2) {
                    KType kType3 = this.arrayType;
                    KType type5 = ((KTypeProjection) CollectionsKt.first((List<? extends Object>) kType3.getArguments())).getType();
                    Intrinsics.checkNotNull(type5);
                    ReadableType type6 = dynamic.getType();
                    Intrinsics.checkNotNullExpressionValue(type6, "type");
                    throw new CollectionElementCastException(kType3, type5, type6, e2);
                }
            } catch (Throwable th2) {
                dynamic.recycle();
                throw th2;
            }
        }
        return createTypedArray;
    }

    @Override // expo.modules.kotlin.types.DynamicAwareTypeConverters
    public Object[] convertFromAny(Object value) {
        Intrinsics.checkNotNullParameter(value, "value");
        if (this.arrayElementConverter.isTrivial()) {
            return (Object[]) value;
        }
        Object[] objArr = (Object[]) value;
        ArrayList arrayList = new ArrayList(objArr.length);
        int length = objArr.length;
        int r3 = 0;
        while (r3 < length) {
            Object obj = objArr[r3];
            r3++;
            try {
                arrayList.add(this.arrayElementConverter.convert(obj));
            } catch (CodedException e) {
                String code = e.getCode();
                Intrinsics.checkNotNullExpressionValue(code, "e.code");
                expo.modules.kotlin.exception.CodedException codedException = new expo.modules.kotlin.exception.CodedException(code, e.getMessage(), e.getCause());
                KType kType = this.arrayType;
                KType type = ((KTypeProjection) CollectionsKt.first((List<? extends Object>) kType.getArguments())).getType();
                Intrinsics.checkNotNull(type);
                Intrinsics.checkNotNull(obj);
                throw new CollectionElementCastException(kType, type, Reflection.getOrCreateKotlinClass(obj.getClass()), codedException);
            } catch (expo.modules.kotlin.exception.CodedException e2) {
                KType kType2 = this.arrayType;
                KType type2 = ((KTypeProjection) CollectionsKt.first((List<? extends Object>) kType2.getArguments())).getType();
                Intrinsics.checkNotNull(type2);
                Intrinsics.checkNotNull(obj);
                throw new CollectionElementCastException(kType2, type2, Reflection.getOrCreateKotlinClass(obj.getClass()), e2);
            } catch (Throwable th) {
                KType kType3 = this.arrayType;
                KType type3 = ((KTypeProjection) CollectionsKt.first((List<? extends Object>) kType3.getArguments())).getType();
                Intrinsics.checkNotNull(type3);
                Intrinsics.checkNotNull(obj);
                throw new CollectionElementCastException(kType3, type3, Reflection.getOrCreateKotlinClass(obj.getClass()), new UnexpectedException(th));
            }
        }
        Object[] array = arrayList.toArray(new Object[0]);
        Objects.requireNonNull(array, "null cannot be cast to non-null type kotlin.Array<T of kotlin.collections.ArraysKt__ArraysJVMKt.toTypedArray>");
        return array;
    }

    private final Object[] createTypedArray(int r3) {
        KType type = ((KTypeProjection) CollectionsKt.first((List<? extends Object>) this.arrayType.getArguments())).getType();
        Intrinsics.checkNotNull(type);
        KClassifier classifier = type.getClassifier();
        Objects.requireNonNull(classifier, "null cannot be cast to non-null type kotlin.reflect.KClass<*>");
        Object newInstance = Array.newInstance(JvmClassMapping.getJavaClass((KClass) classifier), r3);
        Objects.requireNonNull(newInstance, "null cannot be cast to non-null type kotlin.Array<kotlin.Any?>");
        return (Object[]) newInstance;
    }

    @Override // expo.modules.kotlin.types.TypeConverter
    public ExpectedType getCppRequiredTypes() {
        return ExpectedType.Companion.forPrimitiveArray(this.arrayElementConverter.getCppRequiredTypes());
    }

    @Override // expo.modules.kotlin.types.TypeConverter
    public boolean isTrivial() {
        return this.arrayElementConverter.isTrivial();
    }
}
