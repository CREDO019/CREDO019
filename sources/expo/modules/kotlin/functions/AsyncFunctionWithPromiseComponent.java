package expo.modules.kotlin.functions;

import com.facebook.react.bridge.BaseJavaModule;
import com.facebook.react.bridge.ReadableArray;
import com.google.android.exoplayer2.text.ttml.TtmlNode;
import expo.modules.kotlin.Promise;
import expo.modules.kotlin.exception.CodedException;
import expo.modules.kotlin.types.AnyType;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: AsyncFunctionWithPromiseComponent.kt */
@Metadata(m184d1 = {"\u0000>\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0011\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u00002\u00020\u0001B]\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005\u0012@\u0010\u0007\u001a<\u0012\u001d\u0012\u001b\u0012\b\b\u0001\u0012\u0004\u0018\u00010\t0\u0005¢\u0006\f\b\n\u0012\b\b\u0002\u0012\u0004\b\b(\u000b\u0012\u0013\u0012\u00110\f¢\u0006\f\b\n\u0012\b\b\u0002\u0012\u0004\b\b(\r\u0012\u0004\u0012\u00020\u000e0\b¢\u0006\u0002\u0010\u000fJ\u001d\u0010\u0010\u001a\u00020\u000e2\u0006\u0010\u000b\u001a\u00020\u00112\u0006\u0010\r\u001a\u00020\fH\u0010¢\u0006\u0002\b\u0012J'\u0010\u0010\u001a\u00020\u000e2\u000e\u0010\u000b\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\t0\u00052\u0006\u0010\r\u001a\u00020\fH\u0010¢\u0006\u0004\b\u0012\u0010\u0013RH\u0010\u0007\u001a<\u0012\u001d\u0012\u001b\u0012\b\b\u0001\u0012\u0004\u0018\u00010\t0\u0005¢\u0006\f\b\n\u0012\b\b\u0002\u0012\u0004\b\b(\u000b\u0012\u0013\u0012\u00110\f¢\u0006\f\b\n\u0012\b\b\u0002\u0012\u0004\b\b(\r\u0012\u0004\u0012\u00020\u000e0\bX\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0014"}, m183d2 = {"Lexpo/modules/kotlin/functions/AsyncFunctionWithPromiseComponent;", "Lexpo/modules/kotlin/functions/AsyncFunction;", "name", "", "desiredArgsTypes", "", "Lexpo/modules/kotlin/types/AnyType;", TtmlNode.TAG_BODY, "Lkotlin/Function2;", "", "Lkotlin/ParameterName;", "args", "Lexpo/modules/kotlin/Promise;", BaseJavaModule.METHOD_TYPE_PROMISE, "", "(Ljava/lang/String;[Lexpo/modules/kotlin/types/AnyType;Lkotlin/jvm/functions/Function2;)V", "callUserImplementation", "Lcom/facebook/react/bridge/ReadableArray;", "callUserImplementation$expo_modules_core_release", "([Ljava/lang/Object;Lexpo/modules/kotlin/Promise;)V", "expo-modules-core_release"}, m182k = 1, m181mv = {1, 6, 0}, m179xi = 48)
/* loaded from: classes4.dex */
public final class AsyncFunctionWithPromiseComponent extends AsyncFunction {
    private final Function2<Object[], Promise, Unit> body;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    /* JADX WARN: Multi-variable type inference failed */
    public AsyncFunctionWithPromiseComponent(String name, AnyType[] desiredArgsTypes, Function2<? super Object[], ? super Promise, Unit> body) {
        super(name, desiredArgsTypes);
        Intrinsics.checkNotNullParameter(name, "name");
        Intrinsics.checkNotNullParameter(desiredArgsTypes, "desiredArgsTypes");
        Intrinsics.checkNotNullParameter(body, "body");
        this.body = body;
    }

    @Override // expo.modules.kotlin.functions.AsyncFunction
    public void callUserImplementation$expo_modules_core_release(ReadableArray args, Promise promise) throws CodedException {
        Intrinsics.checkNotNullParameter(args, "args");
        Intrinsics.checkNotNullParameter(promise, "promise");
        this.body.invoke(convertArgs(args), promise);
    }

    @Override // expo.modules.kotlin.functions.AsyncFunction
    public void callUserImplementation$expo_modules_core_release(Object[] args, Promise promise) {
        Intrinsics.checkNotNullParameter(args, "args");
        Intrinsics.checkNotNullParameter(promise, "promise");
        this.body.invoke(convertArgs(args), promise);
    }
}
