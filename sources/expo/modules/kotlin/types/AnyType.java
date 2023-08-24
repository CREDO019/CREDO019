package expo.modules.kotlin.types;

import expo.modules.kotlin.jni.ExpectedType;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.Metadata;
import kotlin.jvm.functions.Functions;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.KType;

/* compiled from: AnyType.kt */
@Metadata(m184d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u0012\u0010\r\u001a\u0004\u0018\u00010\u00012\b\u0010\u000e\u001a\u0004\u0018\u00010\u0001J\u0006\u0010\u000f\u001a\u00020\u0010R\u001f\u0010\u0005\u001a\u0006\u0012\u0002\b\u00030\u00068BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\t\u0010\n\u001a\u0004\b\u0007\u0010\bR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\f¨\u0006\u0011"}, m183d2 = {"Lexpo/modules/kotlin/types/AnyType;", "", "kType", "Lkotlin/reflect/KType;", "(Lkotlin/reflect/KType;)V", "converter", "Lexpo/modules/kotlin/types/TypeConverter;", "getConverter", "()Lexpo/modules/kotlin/types/TypeConverter;", "converter$delegate", "Lkotlin/Lazy;", "getKType", "()Lkotlin/reflect/KType;", "convert", "value", "getCppRequiredTypes", "Lexpo/modules/kotlin/jni/ExpectedType;", "expo-modules-core_release"}, m182k = 1, m181mv = {1, 6, 0}, m179xi = 48)
/* loaded from: classes4.dex */
public final class AnyType {
    private final Lazy converter$delegate;
    private final KType kType;

    public AnyType(KType kType) {
        Intrinsics.checkNotNullParameter(kType, "kType");
        this.kType = kType;
        this.converter$delegate = LazyKt.lazy(new Functions<TypeConverter<?>>() { // from class: expo.modules.kotlin.types.AnyType$converter$2
            /* JADX INFO: Access modifiers changed from: package-private */
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Functions
            public final TypeConverter<?> invoke() {
                return TypeConverterProviderImpl.INSTANCE.obtainTypeConverter(AnyType.this.getKType());
            }
        });
    }

    public final KType getKType() {
        return this.kType;
    }

    private final TypeConverter<?> getConverter() {
        return (TypeConverter) this.converter$delegate.getValue();
    }

    public final Object convert(Object obj) {
        return getConverter().convert(obj);
    }

    public final ExpectedType getCppRequiredTypes() {
        return getConverter().getCppRequiredTypes();
    }
}
