package expo.modules.kotlin.types;

import expo.modules.kotlin.jni.ExpectedType;
import expo.modules.kotlin.jni.SingleType;
import kotlin.Metadata;
import kotlin.TypeCastException;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.KType;
import kotlin.reflect.KTypeProjection;

/* compiled from: EitherTypeConverter.kt */
@Metadata(m184d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\b\b\u0007\u0018\u0000*\b\b\u0000\u0010\u0001*\u00020\u0002*\b\b\u0001\u0010\u0003*\u00020\u00022\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u0002H\u0001\u0012\u0004\u0012\u0002H\u00030\u00050\u0004B\u0015\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\t¢\u0006\u0002\u0010\nJ\u001c\u0010\u0012\u001a\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010\u00052\u0006\u0010\u0013\u001a\u00020\u0002H\u0016J\b\u0010\u0014\u001a\u00020\rH\u0016R\u000e\u0010\u000b\u001a\u00020\tX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\rX\u0082\u0004¢\u0006\u0002\n\u0000R\u0012\u0010\u000e\u001a\u0006\u0012\u0002\b\u00030\u0004X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u000f\u001a\u00020\tX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0010\u001a\u00020\rX\u0082\u0004¢\u0006\u0002\n\u0000R\u0012\u0010\u0011\u001a\u0006\u0012\u0002\b\u00030\u0004X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0015"}, m183d2 = {"Lexpo/modules/kotlin/types/EitherTypeConverter;", "FirstType", "", "SecondType", "Lexpo/modules/kotlin/types/TypeConverter;", "Lexpo/modules/kotlin/types/Either;", "converterProvider", "Lexpo/modules/kotlin/types/TypeConverterProvider;", "eitherType", "Lkotlin/reflect/KType;", "(Lexpo/modules/kotlin/types/TypeConverterProvider;Lkotlin/reflect/KType;)V", "firstJavaType", "firstType", "Lexpo/modules/kotlin/jni/ExpectedType;", "firstTypeConverter", "secondJavaType", "secondType", "secondTypeConverter", "convertNonOptional", "value", "getCppRequiredTypes", "expo-modules-core_release"}, m182k = 1, m181mv = {1, 6, 0}, m179xi = 48)
/* loaded from: classes4.dex */
public final class EitherTypeConverter<FirstType, SecondType> extends TypeConverter<Either<FirstType, SecondType>> {
    private final KType firstJavaType;
    private final ExpectedType firstType;
    private final TypeConverter<?> firstTypeConverter;
    private final KType secondJavaType;
    private final ExpectedType secondType;
    private final TypeConverter<?> secondTypeConverter;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public EitherTypeConverter(TypeConverterProvider converterProvider, KType eitherType) {
        super(eitherType.isMarkedNullable());
        Intrinsics.checkNotNullParameter(converterProvider, "converterProvider");
        Intrinsics.checkNotNullParameter(eitherType, "eitherType");
        KTypeProjection kTypeProjection = (KTypeProjection) CollectionsKt.getOrNull(eitherType.getArguments(), 0);
        KType type = kTypeProjection == null ? null : kTypeProjection.getType();
        if (type == null) {
            throw new IllegalArgumentException("Required value was null.".toString());
        }
        this.firstJavaType = type;
        KTypeProjection kTypeProjection2 = (KTypeProjection) CollectionsKt.getOrNull(eitherType.getArguments(), 1);
        KType type2 = kTypeProjection2 != null ? kTypeProjection2.getType() : null;
        if (type2 == null) {
            throw new IllegalArgumentException("Required value was null.".toString());
        }
        this.secondJavaType = type2;
        TypeConverter<?> obtainTypeConverter = converterProvider.obtainTypeConverter(type);
        this.firstTypeConverter = obtainTypeConverter;
        TypeConverter<?> obtainTypeConverter2 = converterProvider.obtainTypeConverter(type2);
        this.secondTypeConverter = obtainTypeConverter2;
        this.firstType = obtainTypeConverter.getCppRequiredTypes();
        this.secondType = obtainTypeConverter2.getCppRequiredTypes();
    }

    @Override // expo.modules.kotlin.types.TypeConverter
    public Either<FirstType, SecondType> convertNonOptional(final Object value) {
        Intrinsics.checkNotNullParameter(value, "value");
        Function2<SingleType[], TypeConverter<?>, Either<FirstType, SecondType>> function2 = new Function2<SingleType[], TypeConverter<?>, Either<FirstType, SecondType>>() { // from class: expo.modules.kotlin.types.EitherTypeConverter$convertNonOptional$convertValueIfNeeded$1
            /* JADX INFO: Access modifiers changed from: package-private */
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(2);
            }

            @Override // kotlin.jvm.functions.Function2
            public final Either<FirstType, SecondType> invoke(SingleType[] types, TypeConverter<?> converter) {
                TypeConverter typeConverter;
                Intrinsics.checkNotNullParameter(types, "types");
                Intrinsics.checkNotNullParameter(converter, "converter");
                int length = types.length;
                int r1 = 0;
                while (r1 < length) {
                    SingleType singleType = types[r1];
                    r1++;
                    if (singleType.getExpectedCppType$expo_modules_core_release().getClazz().isInstance(value)) {
                        typeConverter = ((EitherTypeConverter) this).firstTypeConverter;
                        if (typeConverter.isTrivial()) {
                            return new Either<>(value);
                        }
                        Object convert = converter.convert(value);
                        Intrinsics.checkNotNull(convert);
                        return new Either<>(convert);
                    }
                }
                return null;
            }
        };
        Either<FirstType, SecondType> invoke = function2.invoke(this.firstType.getPossibleTypes(), this.firstTypeConverter);
        if (invoke == null && (invoke = function2.invoke(this.secondType.getPossibleTypes(), this.secondTypeConverter)) == null) {
            KType kType = this.firstJavaType;
            KType kType2 = this.secondJavaType;
            throw new TypeCastException("Cannot cast '" + value + "' to 'Either<" + kType + ", " + kType2 + ">'");
        }
        return invoke;
    }

    @Override // expo.modules.kotlin.types.TypeConverter
    public ExpectedType getCppRequiredTypes() {
        return this.firstType.plus(this.secondType);
    }
}
