package expo.modules.kotlin.functions;

import expo.modules.kotlin.exception.CodedException;
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
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.CoroutineScopeKt;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: SuspendFunctionComponent.kt */
@Metadata(m184d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@"}, m183d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, m182k = 3, m181mv = {1, 6, 0}, m179xi = 48)
@DebugMetadata(m175c = "expo.modules.kotlin.functions.SuspendFunctionComponent$attachToJSObject$2$1", m174f = "SuspendFunctionComponent.kt", m173i = {0}, m172l = {64}, m171m = "invokeSuspend", m170n = {"$this$launch"}, m169s = {"L$0"})
/* loaded from: classes4.dex */
public final class SuspendFunctionComponent$attachToJSObject$2$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ Object[] $args;
    final /* synthetic */ PromiseImpl $bridgePromise;
    final /* synthetic */ JavaScriptModuleObject $jsObject;
    private /* synthetic */ Object L$0;
    Object L$1;
    Object L$2;
    Object L$3;
    int label;
    final /* synthetic */ SuspendFunctionComponent this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public SuspendFunctionComponent$attachToJSObject$2$1(PromiseImpl promiseImpl, SuspendFunctionComponent suspendFunctionComponent, JavaScriptModuleObject javaScriptModuleObject, Object[] objArr, Continuation<? super SuspendFunctionComponent$attachToJSObject$2$1> continuation) {
        super(2, continuation);
        this.$bridgePromise = promiseImpl;
        this.this$0 = suspendFunctionComponent;
        this.$jsObject = javaScriptModuleObject;
        this.$args = objArr;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        SuspendFunctionComponent$attachToJSObject$2$1 suspendFunctionComponent$attachToJSObject$2$1 = new SuspendFunctionComponent$attachToJSObject$2$1(this.$bridgePromise, this.this$0, this.$jsObject, this.$args, continuation);
        suspendFunctionComponent$attachToJSObject$2$1.L$0 = obj;
        return suspendFunctionComponent$attachToJSObject$2$1;
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((SuspendFunctionComponent$attachToJSObject$2$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        CoroutineScope coroutineScope;
        JavaScriptModuleObject javaScriptModuleObject;
        SuspendFunctionComponent suspendFunctionComponent;
        Throwable th;
        CodedException e;
        expo.modules.core.errors.CodedException e2;
        Function3 function3;
        PromiseImpl promiseImpl;
        Object obj2 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int r1 = this.label;
        try {
            if (r1 == 0) {
                ResultKt.throwOnFailure(obj);
                coroutineScope = (CoroutineScope) this.L$0;
                SuspendFunctionComponent suspendFunctionComponent2 = this.this$0;
                javaScriptModuleObject = this.$jsObject;
                Object[] objArr = this.$args;
                PromiseImpl promiseImpl2 = this.$bridgePromise;
                try {
                    function3 = suspendFunctionComponent2.body;
                    Object[] convertArgs = suspendFunctionComponent2.convertArgs(objArr);
                    this.L$0 = coroutineScope;
                    this.L$1 = suspendFunctionComponent2;
                    this.L$2 = javaScriptModuleObject;
                    this.L$3 = promiseImpl2;
                    this.label = 1;
                    Object invoke = function3.invoke(coroutineScope, convertArgs, this);
                    if (invoke == obj2) {
                        return obj2;
                    }
                    promiseImpl = promiseImpl2;
                    suspendFunctionComponent = suspendFunctionComponent2;
                    obj = invoke;
                } catch (expo.modules.core.errors.CodedException e3) {
                    suspendFunctionComponent = suspendFunctionComponent2;
                    e2 = e3;
                    String code = e2.getCode();
                    Intrinsics.checkNotNullExpressionValue(code, "e.code");
                    throw new FunctionCallException(suspendFunctionComponent.getName(), javaScriptModuleObject.getName(), new CodedException(code, e2.getMessage(), e2.getCause()));
                } catch (CodedException e4) {
                    suspendFunctionComponent = suspendFunctionComponent2;
                    e = e4;
                    throw new FunctionCallException(suspendFunctionComponent.getName(), javaScriptModuleObject.getName(), e);
                } catch (Throwable th2) {
                    suspendFunctionComponent = suspendFunctionComponent2;
                    th = th2;
                    throw new FunctionCallException(suspendFunctionComponent.getName(), javaScriptModuleObject.getName(), new UnexpectedException(th));
                }
            } else if (r1 != 1) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            } else {
                promiseImpl = (PromiseImpl) this.L$3;
                javaScriptModuleObject = (JavaScriptModuleObject) this.L$2;
                suspendFunctionComponent = (SuspendFunctionComponent) this.L$1;
                coroutineScope = (CoroutineScope) this.L$0;
                try {
                    ResultKt.throwOnFailure(obj);
                } catch (expo.modules.core.errors.CodedException e5) {
                    e2 = e5;
                    String code2 = e2.getCode();
                    Intrinsics.checkNotNullExpressionValue(code2, "e.code");
                    throw new FunctionCallException(suspendFunctionComponent.getName(), javaScriptModuleObject.getName(), new CodedException(code2, e2.getMessage(), e2.getCause()));
                } catch (CodedException e6) {
                    e = e6;
                    throw new FunctionCallException(suspendFunctionComponent.getName(), javaScriptModuleObject.getName(), e);
                } catch (Throwable th3) {
                    th = th3;
                    throw new FunctionCallException(suspendFunctionComponent.getName(), javaScriptModuleObject.getName(), new UnexpectedException(th));
                }
            }
            if (CoroutineScopeKt.isActive(coroutineScope)) {
                promiseImpl.resolve(obj);
            }
            Unit unit = Unit.INSTANCE;
        } catch (CodedException e7) {
            this.$bridgePromise.reject(e7);
        } catch (Throwable th4) {
            this.$bridgePromise.reject(new UnexpectedException(th4));
        }
        return Unit.INSTANCE;
    }
}
