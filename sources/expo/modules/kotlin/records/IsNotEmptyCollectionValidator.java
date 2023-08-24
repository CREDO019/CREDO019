package expo.modules.kotlin.records;

import expo.modules.kotlin.exception.ValidationException;
import java.util.Collection;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: FieldValidator.kt */
@Metadata(m184d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u001e\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0002\u0018\u00002\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0003J\u0014\u0010\u0004\u001a\u00020\u00052\n\u0010\u0006\u001a\u0006\u0012\u0002\b\u00030\u0002H\u0016¨\u0006\u0007"}, m183d2 = {"Lexpo/modules/kotlin/records/IsNotEmptyCollectionValidator;", "Lexpo/modules/kotlin/records/FieldValidator;", "", "()V", "validate", "", "value", "expo-modules-core_release"}, m182k = 1, m181mv = {1, 6, 0}, m179xi = 48)
/* loaded from: classes4.dex */
public final class IsNotEmptyCollectionValidator implements FieldValidator<Collection<?>> {
    @Override // expo.modules.kotlin.records.FieldValidator
    public void validate(Collection<?> value) {
        Intrinsics.checkNotNullParameter(value, "value");
        if (value.isEmpty()) {
            throw new ValidationException("Collection is empty");
        }
    }
}
