package expo.modules.kotlin.functions;

import com.facebook.react.bridge.ReadableArray;
import expo.modules.core.errors.CodedException;
import expo.modules.kotlin.ModuleHolder;
import expo.modules.kotlin.Promise;
import expo.modules.kotlin.exception.FunctionCallException;
import expo.modules.kotlin.exception.UnexpectedException;
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

/* compiled from: AsyncFunction.kt */
@Metadata(m184d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@"}, m183d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, m182k = 3, m181mv = {1, 6, 0}, m179xi = 48)
@DebugMetadata(m175c = "expo.modules.kotlin.functions.AsyncFunction$call$1", m174f = "AsyncFunction.kt", m173i = {}, m172l = {}, m171m = "invokeSuspend", m170n = {}, m169s = {})
/* loaded from: classes4.dex */
final class AsyncFunction$call$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ ReadableArray $args;
    final /* synthetic */ ModuleHolder $holder;
    final /* synthetic */ Promise $promise;
    int label;
    final /* synthetic */ AsyncFunction this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public AsyncFunction$call$1(Promise promise, AsyncFunction asyncFunction, ModuleHolder moduleHolder, ReadableArray readableArray, Continuation<? super AsyncFunction$call$1> continuation) {
        super(2, continuation);
        this.$promise = promise;
        this.this$0 = asyncFunction;
        this.$holder = moduleHolder;
        this.$args = readableArray;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new AsyncFunction$call$1(this.$promise, this.this$0, this.$holder, this.$args, continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((AsyncFunction$call$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        IntrinsicsKt.getCOROUTINE_SUSPENDED();
        if (this.label == 0) {
            ResultKt.throwOnFailure(obj);
            try {
                AsyncFunction asyncFunction = this.this$0;
                ModuleHolder moduleHolder = this.$holder;
                try {
                    asyncFunction.callUserImplementation$expo_modules_core_release(this.$args, this.$promise);
                    Unit unit = Unit.INSTANCE;
                } catch (CodedException e) {
                    String code = e.getCode();
                    Intrinsics.checkNotNullExpressionValue(code, "e.code");
                    throw new FunctionCallException(asyncFunction.getName(), moduleHolder.getName(), new expo.modules.kotlin.exception.CodedException(code, e.getMessage(), e.getCause()));
                } catch (expo.modules.kotlin.exception.CodedException e2) {
                    throw new FunctionCallException(asyncFunction.getName(), moduleHolder.getName(), e2);
                } catch (Throwable th) {
                    throw new FunctionCallException(asyncFunction.getName(), moduleHolder.getName(), new UnexpectedException(th));
                }
            } catch (expo.modules.kotlin.exception.CodedException e3) {
                this.$promise.reject(e3);
            } catch (Throwable th2) {
                this.$promise.reject(new UnexpectedException(th2));
            }
            return Unit.INSTANCE;
        }
        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
    }
}
