package expo.modules.kotlin.records;

import com.google.firebase.messaging.Constants;
import expo.modules.kotlin.exception.ValidationException;
import java.lang.Comparable;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: FieldValidator.kt */
@Metadata(m184d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000f\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0002\b\u0003\u0018\u0000*\u000e\b\u0000\u0010\u0001*\b\u0012\u0004\u0012\u0002H\u00010\u00022\b\u0012\u0004\u0012\u0002H\u00010\u0003B%\u0012\u0006\u0010\u0004\u001a\u00028\u0000\u0012\u0006\u0010\u0005\u001a\u00028\u0000\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\u0007¢\u0006\u0002\u0010\tJ\u0015\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00028\u0000H\u0016¢\u0006\u0002\u0010\u000eR\u0010\u0010\u0004\u001a\u00028\u0000X\u0082\u0004¢\u0006\u0004\n\u0002\u0010\nR\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u0005\u001a\u00028\u0000X\u0082\u0004¢\u0006\u0004\n\u0002\u0010\nR\u000e\u0010\b\u001a\u00020\u0007X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u000f"}, m183d2 = {"Lexpo/modules/kotlin/records/NumericRangeValidator;", "T", "", "Lexpo/modules/kotlin/records/FieldValidator;", Constants.MessagePayloadKeys.FROM, "to", "fromInclusive", "", "toInclusive", "(Ljava/lang/Comparable;Ljava/lang/Comparable;ZZ)V", "Ljava/lang/Comparable;", "validate", "", "value", "(Ljava/lang/Comparable;)V", "expo-modules-core_release"}, m182k = 1, m181mv = {1, 6, 0}, m179xi = 48)
/* loaded from: classes4.dex */
public final class NumericRangeValidator<T extends Comparable<? super T>> implements FieldValidator<T> {
    private final T from;
    private final boolean fromInclusive;

    /* renamed from: to */
    private final T f1450to;
    private final boolean toInclusive;

    public NumericRangeValidator(T from, T to, boolean z, boolean z2) {
        Intrinsics.checkNotNullParameter(from, "from");
        Intrinsics.checkNotNullParameter(to, "to");
        this.from = from;
        this.f1450to = to;
        this.fromInclusive = z;
        this.toInclusive = z2;
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // expo.modules.kotlin.records.FieldValidator
    public /* bridge */ /* synthetic */ void validate(Object obj) {
        validate((NumericRangeValidator<T>) ((Comparable) obj));
    }

    public void validate(T value) {
        Intrinsics.checkNotNullParameter(value, "value");
        if (value.compareTo(this.from) < 0 || this.f1450to.compareTo(value) < 0 || ((Intrinsics.areEqual(value, this.from) && !this.fromInclusive) || (Intrinsics.areEqual(value, this.f1450to) && !this.toInclusive))) {
            T t = this.from;
            String str = this.fromInclusive ? "<=" : "<";
            String str2 = this.toInclusive ? "<=" : "<";
            T t2 = this.f1450to;
            throw new ValidationException("Value should be in range " + t + " " + str + " 'value' " + str2 + " " + t2 + ", got " + value);
        }
    }
}
