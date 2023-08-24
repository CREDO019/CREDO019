package expo.modules.kotlin.functions;

import expo.modules.core.errors.CodedException;
import expo.modules.kotlin.exception.FunctionCallException;
import expo.modules.kotlin.exception.UnexpectedException;
import expo.modules.kotlin.jni.JavaScriptModuleObject;
import expo.modules.kotlin.jni.PromiseImpl;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.CoroutineScope;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: AsyncFunction.kt */
@Metadata(m184d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@"}, m183d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, m182k = 3, m181mv = {1, 6, 0}, m179xi = 48)
@DebugMetadata(m175c = "expo.modules.kotlin.functions.AsyncFunction$attachToJSObject$2$1", m174f = "AsyncFunction.kt", m173i = {}, m172l = {}, m171m = "invokeSuspend", m170n = {}, m169s = {})
/* loaded from: classes4.dex */
public final class AsyncFunction$attachToJSObject$2$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ Object[] $args;
    final /* synthetic */ PromiseImpl $bridgePromise;
    final /* synthetic */ JavaScriptModuleObject $jsObject;
    int label;
    final /* synthetic */ AsyncFunction this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public AsyncFunction$attachToJSObject$2$1(PromiseImpl promiseImpl, AsyncFunction asyncFunction, JavaScriptModuleObject javaScriptModuleObject, Object[] objArr, Continuation<? super AsyncFunction$attachToJSObject$2$1> continuation) {
        super(2, continuation);
        this.$bridgePromise = promiseImpl;
        this.this$0 = asyncFunction;
        this.$jsObject = javaScriptModuleObject;
        this.$args = objArr;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new AsyncFunction$attachToJSObject$2$1(this.$bridgePromise, this.this$0, this.$jsObject, this.$args, continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((AsyncFunction$attachToJSObject$2$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        IntrinsicsKt.getCOROUTINE_SUSPENDED();
        if (this.label == 0) {
            ResultKt.throwOnFailure(obj);
            try {
                AsyncFunction asyncFunction = this.this$0;
                JavaScriptModuleObject javaScriptModuleObject = this.$jsObject;
                try {
                    asyncFunction.callUserImplementation$expo_modules_core_release(this.$args, this.$bridgePromise);
                    Unit unit = Unit.INSTANCE;
                } catch (CodedException e) {
                    String code = e.getCode();
                    Intrinsics.checkNotNullExpressionValue(code, "e.code");
                    throw new FunctionCallException(asyncFunction.getName(), javaScriptModuleObject.getName(), new expo.modules.kotlin.exception.CodedException(code, e.getMessage(), e.getCause()));
                } catch (expo.modules.kotlin.exception.CodedException e2) {
                    throw new FunctionCallException(asyncFunction.getName(), javaScriptModuleObject.getName(), e2);
                } catch (Throwable th) {
                    throw new FunctionCallException(asyncFunction.getName(), javaScriptModuleObject.getName(), new UnexpectedException(th));
                }
            } catch (expo.modules.kotlin.exception.CodedException e3) {
                this.$bridgePromise.reject(e3);
            } catch (Throwable th2) {
                this.$bridgePromise.reject(new UnexpectedException(th2));
            }
            return Unit.INSTANCE;
        }
        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
    }
}
