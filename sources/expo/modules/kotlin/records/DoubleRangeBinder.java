package expo.modules.kotlin.records;

import java.lang.annotation.Annotation;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.KType;

/* compiled from: ValidationBinder.kt */
@Metadata(m184d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u001b\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0000\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u001c\u0010\u0003\u001a\u0006\u0012\u0002\b\u00030\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\bH\u0016¨\u0006\t"}, m183d2 = {"Lexpo/modules/kotlin/records/DoubleRangeBinder;", "Lexpo/modules/kotlin/records/ValidationBinder;", "()V", "bind", "Lexpo/modules/kotlin/records/FieldValidator;", "annotation", "", "fieldType", "Lkotlin/reflect/KType;", "expo-modules-core_release"}, m182k = 1, m181mv = {1, 6, 0}, m179xi = 48)
/* loaded from: classes4.dex */
public final class DoubleRangeBinder implements ValidationBinder {
    @Override // expo.modules.kotlin.records.ValidationBinder
    public FieldValidator<?> bind(Annotation annotation, KType fieldType) {
        Intrinsics.checkNotNullParameter(annotation, "annotation");
        Intrinsics.checkNotNullParameter(fieldType, "fieldType");
        Validators validators = (Validators) annotation;
        return new NumericRangeValidator(Double.valueOf(validators.from()), Double.valueOf(validators.m222to()), validators.fromInclusive(), validators.toInclusive());
    }
}
