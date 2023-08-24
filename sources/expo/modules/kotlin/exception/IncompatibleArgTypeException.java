package expo.modules.kotlin.exception;

import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.KType;

/* compiled from: CodedException.kt */
@Metadata(m184d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0003\n\u0002\b\u0002\b\u0000\u0018\u00002\u00020\u0001B!\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u0006¢\u0006\u0002\u0010\u0007¨\u0006\b"}, m183d2 = {"Lexpo/modules/kotlin/exception/IncompatibleArgTypeException;", "Lexpo/modules/kotlin/exception/CodedException;", "argumentType", "Lkotlin/reflect/KType;", "desiredType", "cause", "", "(Lkotlin/reflect/KType;Lkotlin/reflect/KType;Ljava/lang/Throwable;)V", "expo-modules-core_release"}, m182k = 1, m181mv = {1, 6, 0}, m179xi = 48)
/* loaded from: classes4.dex */
public final class IncompatibleArgTypeException extends CodedException {
    public /* synthetic */ IncompatibleArgTypeException(KType kType, KType kType2, Throwable th, int r4, DefaultConstructorMarker defaultConstructorMarker) {
        this(kType, kType2, (r4 & 4) != 0 ? null : th);
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public IncompatibleArgTypeException(KType argumentType, KType desiredType, Throwable th) {
        super("Argument type '" + argumentType + "' is not compatible with expected type '" + desiredType + "'.", th);
        Intrinsics.checkNotNullParameter(argumentType, "argumentType");
        Intrinsics.checkNotNullParameter(desiredType, "desiredType");
    }
}
