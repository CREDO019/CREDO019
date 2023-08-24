package expo.modules.kotlin.records;

import expo.modules.kotlin.exception.ValidationException;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: FieldValidator.kt */
@Metadata(m184d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u0015\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\u0006\u0010\u0005\u001a\u00020\u0004¢\u0006\u0002\u0010\u0006J\u0010\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\u0002H\u0016R\u000e\u0010\u0005\u001a\u00020\u0004X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\n"}, m183d2 = {"Lexpo/modules/kotlin/records/StringSizeValidator;", "Lexpo/modules/kotlin/records/FieldValidator;", "", "min", "", "max", "(II)V", "validate", "", "value", "expo-modules-core_release"}, m182k = 1, m181mv = {1, 6, 0}, m179xi = 48)
/* loaded from: classes4.dex */
public final class StringSizeValidator implements FieldValidator<String> {
    private final int max;
    private final int min;

    public StringSizeValidator(int r1, int r2) {
        this.min = r1;
        this.max = r2;
    }

    @Override // expo.modules.kotlin.records.FieldValidator
    public void validate(String value) {
        Intrinsics.checkNotNullParameter(value, "value");
        if (value.length() < this.min || value.length() > this.max) {
            int r1 = this.min;
            int r2 = this.max;
            int length = value.length();
            throw new ValidationException("Length of the string should be between " + r1 + " and " + r2 + ", got " + value + " (" + length + " characters)");
        }
    }
}
