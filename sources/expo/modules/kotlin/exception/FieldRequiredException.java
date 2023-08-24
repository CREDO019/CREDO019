package expo.modules.kotlin.exception;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.KProperty1;

/* compiled from: CodedException.kt */
@Metadata(m184d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0000\u0018\u00002\u00020\u0001B\u0015\u0012\u000e\u0010\u0002\u001a\n\u0012\u0002\b\u0003\u0012\u0002\b\u00030\u0003¢\u0006\u0002\u0010\u0004¨\u0006\u0005"}, m183d2 = {"Lexpo/modules/kotlin/exception/FieldRequiredException;", "Lexpo/modules/kotlin/exception/CodedException;", "property", "Lkotlin/reflect/KProperty1;", "(Lkotlin/reflect/KProperty1;)V", "expo-modules-core_release"}, m182k = 1, m181mv = {1, 6, 0}, m179xi = 48)
/* loaded from: classes4.dex */
public final class FieldRequiredException extends CodedException {
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public FieldRequiredException(KProperty1<?, ?> property) {
        super("Value for field '" + property + "' is required, got nil", null, 2, null);
        Intrinsics.checkNotNullParameter(property, "property");
    }
}
