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
@Metadata(m184d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u000b\b\u0007\u0018\u0000*\b\b\u0000\u0010\u0001*\u00020\u0002*\b\b\u0001\u0010\u0003*\u00020\u0002*\b\b\u0002\u0010\u0004*\u00020\u00022\u001a\u0012\u0016\u0012\u0014\u0012\u0004\u0012\u0002H\u0001\u0012\u0004\u0012\u0002H\u0003\u0012\u0004\u0012\u0002H\u00040\u00060\u0005B\u0015\u0012\u0006\u0010\u0007\u001a\u00020\b\u0012\u0006\u0010\t\u001a\u00020\n¢\u0006\u0002\u0010\u000bJ\"\u0010\u0016\u001a\u0014\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u0001\u0012\u0004\u0012\u00028\u00020\u00062\u0006\u0010\u0017\u001a\u00020\u0002H\u0016J\b\u0010\u0018\u001a\u00020\u000eH\u0016R\u000e\u0010\f\u001a\u00020\nX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\u000eX\u0082\u0004¢\u0006\u0002\n\u0000R\u0012\u0010\u000f\u001a\u0006\u0012\u0002\b\u00030\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0010\u001a\u00020\nX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0011\u001a\u00020\u000eX\u0082\u0004¢\u0006\u0002\n\u0000R\u0012\u0010\u0012\u001a\u0006\u0012\u0002\b\u00030\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0013\u001a\u00020\nX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0014\u001a\u00020\u000eX\u0082\u0004¢\u0006\u0002\n\u0000R\u0012\u0010\u0015\u001a\u0006\u0012\u0002\b\u00030\u0005X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0019"}, m183d2 = {"Lexpo/modules/kotlin/types/EitherOfThreeTypeConverter;", "FirstType", "", "SecondType", "ThirdType", "Lexpo/modules/kotlin/types/TypeConverter;", "Lexpo/modules/kotlin/types/EitherOfThree;", "converterProvider", "Lexpo/modules/kotlin/types/TypeConverterProvider;", "eitherType", "Lkotlin/reflect/KType;", "(Lexpo/modules/kotlin/types/TypeConverterProvider;Lkotlin/reflect/KType;)V", "firstJavaType", "firstType", "Lexpo/modules/kotlin/jni/ExpectedType;", "firstTypeConverter", "secondJavaType", "secondType", "secondTypeConverter", "thirdJavaType", "thirdType", "thirdTypeConverter", "convertNonOptional", "value", "getCppRequiredTypes", "expo-modules-core_release"}, m182k = 1, m181mv = {1, 6, 0}, m179xi = 48)
/* loaded from: classes4.dex */
public final class EitherOfThreeTypeConverter<FirstType, SecondType, ThirdType> extends TypeConverter<EitherOfThree<FirstType, SecondType, ThirdType>> {
    private final KType firstJavaType;
    private final ExpectedType firstType;
    private final TypeConverter<?> firstTypeConverter;
    private final KType secondJavaType;
    private final ExpectedType secondType;
    private final TypeConverter<?> secondTypeConverter;
    private final KType thirdJavaType;
    private final ExpectedType thirdType;
    private final TypeConverter<?> thirdTypeConverter;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public EitherOfThreeTypeConverter(TypeConverterProvider converterProvider, KType eitherType) {
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
        KType type2 = kTypeProjection2 == null ? null : kTypeProjection2.getType();
        if (type2 == null) {
            throw new IllegalArgumentException("Required value was null.".toString());
        }
        this.secondJavaType = type2;
        KTypeProjection kTypeProjection3 = (KTypeProjection) CollectionsKt.getOrNull(eitherType.getArguments(), 2);
        KType type3 = kTypeProjection3 != null ? kTypeProjection3.getType() : null;
        if (type3 == null) {
            throw new IllegalArgumentException("Required value was null.".toString());
        }
        this.thirdJavaType = type3;
        TypeConverter<?> obtainTypeConverter = converterProvider.obtainTypeConverter(type);
        this.firstTypeConverter = obtainTypeConverter;
        TypeConverter<?> obtainTypeConverter2 = converterProvider.obtainTypeConverter(type2);
        this.secondTypeConverter = obtainTypeConverter2;
        TypeConverter<?> obtainTypeConverter3 = converterProvider.obtainTypeConverter(type3);
        this.thirdTypeConverter = obtainTypeConverter3;
        this.firstType = obtainTypeConverter.getCppRequiredTypes();
        this.secondType = obtainTypeConverter2.getCppRequiredTypes();
        this.thirdType = obtainTypeConverter3.getCppRequiredTypes();
    }

    @Override // expo.modules.kotlin.types.TypeConverter
    public EitherOfThree<FirstType, SecondType, ThirdType> convertNonOptional(final Object value) {
        Intrinsics.checkNotNullParameter(value, "value");
        Function2<SingleType[], TypeConverter<?>, EitherOfThree<FirstType, SecondType, ThirdType>> function2 = new Function2<SingleType[], TypeConverter<?>, EitherOfThree<FirstType, SecondType, ThirdType>>() { // from class: expo.modules.kotlin.types.EitherOfThreeTypeConverter$convertNonOptional$convertValueIfNeeded$1
            /* JADX INFO: Access modifiers changed from: package-private */
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(2);
            }

            @Override // kotlin.jvm.functions.Function2
            public final EitherOfThree<FirstType, SecondType, ThirdType> invoke(SingleType[] types, TypeConverter<?> converter) {
                TypeConverter typeConverter;
                Intrinsics.checkNotNullParameter(types, "types");
                Intrinsics.checkNotNullParameter(converter, "converter");
                int length = types.length;
                int r1 = 0;
                while (r1 < length) {
                    SingleType singleType = types[r1];
                    r1++;
                    if (singleType.getExpectedCppType$expo_modules_core_release().getClazz().isInstance(value)) {
                        typeConverter = ((EitherOfThreeTypeConverter) this).firstTypeConverter;
                        if (typeConverter.isTrivial()) {
                            return new EitherOfThree<>(value);
                        }
                        Object convert = converter.convert(value);
                        Intrinsics.checkNotNull(convert);
                        return new EitherOfThree<>(convert);
                    }
                }
                return null;
            }
        };
        EitherOfThree<FirstType, SecondType, ThirdType> invoke = function2.invoke(this.firstType.getPossibleTypes(), this.firstTypeConverter);
        if (invoke == null && (invoke = function2.invoke(this.secondType.getPossibleTypes(), this.secondTypeConverter)) == null && (invoke = function2.invoke(this.thirdType.getPossibleTypes(), this.thirdTypeConverter)) == null) {
            KType kType = this.firstJavaType;
            KType kType2 = this.secondJavaType;
            KType kType3 = this.thirdJavaType;
            throw new TypeCastException("Cannot cast '" + value + "' to 'EitherOfThree<" + kType + ", " + kType2 + ", " + kType3 + ">'");
        }
        return invoke;
    }

    @Override // expo.modules.kotlin.types.TypeConverter
    public ExpectedType getCppRequiredTypes() {
        return this.firstType.plus(this.secondType).plus(this.thirdType);
    }
}
