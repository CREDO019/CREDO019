package expo.modules.kotlin.functions;

import androidx.core.app.NotificationCompat;
import com.facebook.react.bridge.ReadableArray;
import com.google.android.exoplayer2.text.ttml.TtmlNode;
import expo.modules.kotlin.AppContext;
import expo.modules.kotlin.exception.CodedException;
import expo.modules.kotlin.exception.FunctionCallException;
import expo.modules.kotlin.exception.UnexpectedException;
import expo.modules.kotlin.jni.ExpectedType;
import expo.modules.kotlin.jni.JNIFunctionBody;
import expo.modules.kotlin.jni.JavaScriptModuleObject;
import expo.modules.kotlin.types.AnyType;
import expo.modules.kotlin.types.JSTypeConverter;
import java.util.Objects;
import kotlin.Metadata;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: SyncFunctionComponent.kt */
@Metadata(m184d1 = {"\u0000F\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0011\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u00002\u00020\u0001BJ\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005\u0012-\u0010\u0007\u001a)\u0012\u001d\u0012\u001b\u0012\b\b\u0001\u0012\u0004\u0018\u00010\t0\u0005¢\u0006\f\b\n\u0012\b\b\u0002\u0012\u0004\b\b(\u000b\u0012\u0006\u0012\u0004\u0018\u00010\t0\b¢\u0006\u0002\u0010\fJ\u001d\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u0012H\u0010¢\u0006\u0002\b\u0013J\u0010\u0010\u0014\u001a\u0004\u0018\u00010\t2\u0006\u0010\u000b\u001a\u00020\u0015J\u001d\u0010\u0014\u001a\u0004\u0018\u00010\t2\u000e\u0010\u000b\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\t0\u0005¢\u0006\u0002\u0010\u0016R5\u0010\u0007\u001a)\u0012\u001d\u0012\u001b\u0012\b\b\u0001\u0012\u0004\u0018\u00010\t0\u0005¢\u0006\f\b\n\u0012\b\b\u0002\u0012\u0004\b\b(\u000b\u0012\u0006\u0012\u0004\u0018\u00010\t0\bX\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0017"}, m183d2 = {"Lexpo/modules/kotlin/functions/SyncFunctionComponent;", "Lexpo/modules/kotlin/functions/AnyFunction;", "name", "", "desiredArgsTypes", "", "Lexpo/modules/kotlin/types/AnyType;", TtmlNode.TAG_BODY, "Lkotlin/Function1;", "", "Lkotlin/ParameterName;", "args", "(Ljava/lang/String;[Lexpo/modules/kotlin/types/AnyType;Lkotlin/jvm/functions/Function1;)V", "attachToJSObject", "", "appContext", "Lexpo/modules/kotlin/AppContext;", "jsObject", "Lexpo/modules/kotlin/jni/JavaScriptModuleObject;", "attachToJSObject$expo_modules_core_release", NotificationCompat.CATEGORY_CALL, "Lcom/facebook/react/bridge/ReadableArray;", "([Ljava/lang/Object;)Ljava/lang/Object;", "expo-modules-core_release"}, m182k = 1, m181mv = {1, 6, 0}, m179xi = 48)
/* loaded from: classes4.dex */
public final class SyncFunctionComponent extends AnyFunction {
    private final Function1<Object[], Object> body;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    /* JADX WARN: Multi-variable type inference failed */
    public SyncFunctionComponent(String name, AnyType[] desiredArgsTypes, Function1<? super Object[], ? extends Object> body) {
        super(name, desiredArgsTypes);
        Intrinsics.checkNotNullParameter(name, "name");
        Intrinsics.checkNotNullParameter(desiredArgsTypes, "desiredArgsTypes");
        Intrinsics.checkNotNullParameter(body, "body");
        this.body = body;
    }

    public final Object call(ReadableArray args) throws CodedException {
        Intrinsics.checkNotNullParameter(args, "args");
        return this.body.invoke(convertArgs(args));
    }

    public final Object call(Object[] args) {
        Intrinsics.checkNotNullParameter(args, "args");
        return this.body.invoke(convertArgs(args));
    }

    @Override // expo.modules.kotlin.functions.AnyFunction
    public void attachToJSObject$expo_modules_core_release(AppContext appContext, final JavaScriptModuleObject jsObject) {
        Intrinsics.checkNotNullParameter(appContext, "appContext");
        Intrinsics.checkNotNullParameter(jsObject, "jsObject");
        String name = getName();
        int argsCount$expo_modules_core_release = getArgsCount$expo_modules_core_release();
        Object[] array = getCppRequiredTypes().toArray(new ExpectedType[0]);
        Objects.requireNonNull(array, "null cannot be cast to non-null type kotlin.Array<T of kotlin.collections.ArraysKt__ArraysJVMKt.toTypedArray>");
        jsObject.registerSyncFunction(name, argsCount$expo_modules_core_release, (ExpectedType[]) array, new JNIFunctionBody() { // from class: expo.modules.kotlin.functions.SyncFunctionComponent$$ExternalSyntheticLambda0
            @Override // expo.modules.kotlin.jni.JNIFunctionBody
            public final Object invoke(Object[] objArr) {
                Object m1674attachToJSObject$lambda2;
                m1674attachToJSObject$lambda2 = SyncFunctionComponent.m1674attachToJSObject$lambda2(SyncFunctionComponent.this, jsObject, objArr);
                return m1674attachToJSObject$lambda2;
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: attachToJSObject$lambda-2  reason: not valid java name */
    public static final Object m1674attachToJSObject$lambda2(SyncFunctionComponent this$0, JavaScriptModuleObject jsObject, Object[] args) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(jsObject, "$jsObject");
        Intrinsics.checkNotNullParameter(args, "args");
        try {
            return JSTypeConverter.convertToJSValue$default(JSTypeConverter.INSTANCE, this$0.call(args), null, 2, null);
        } catch (expo.modules.core.errors.CodedException e) {
            String code = e.getCode();
            Intrinsics.checkNotNullExpressionValue(code, "e.code");
            throw new FunctionCallException(this$0.getName(), jsObject.getName(), new CodedException(code, e.getMessage(), e.getCause()));
        } catch (CodedException e2) {
            throw new FunctionCallException(this$0.getName(), jsObject.getName(), e2);
        } catch (Throwable th) {
            throw new FunctionCallException(this$0.getName(), jsObject.getName(), new UnexpectedException(th));
        }
    }
}
