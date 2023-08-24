package expo.modules.kotlin.exception;

import com.facebook.react.util.JSStackTrace;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: CodedException.kt */
@Metadata(m184d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0000\u0018\u00002\u00020\u0001B\u001d\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0006¢\u0006\u0002\u0010\u0007¨\u0006\b"}, m183d2 = {"Lexpo/modules/kotlin/exception/FunctionCallException;", "Lexpo/modules/kotlin/exception/DecoratedException;", JSStackTrace.METHOD_NAME_KEY, "", "moduleName", "cause", "Lexpo/modules/kotlin/exception/CodedException;", "(Ljava/lang/String;Ljava/lang/String;Lexpo/modules/kotlin/exception/CodedException;)V", "expo-modules-core_release"}, m182k = 1, m181mv = {1, 6, 0}, m179xi = 48)
/* loaded from: classes4.dex */
public final class FunctionCallException extends DecoratedException {
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public FunctionCallException(String methodName, String moduleName, CodedException cause) {
        super("Call to function '" + moduleName + "." + methodName + "' has been rejected.", cause);
        Intrinsics.checkNotNullParameter(methodName, "methodName");
        Intrinsics.checkNotNullParameter(moduleName, "moduleName");
        Intrinsics.checkNotNullParameter(cause, "cause");
    }
}
