package expo.modules.kotlin.functions;

import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.InlineMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.CoroutineScope;

/* compiled from: AsyncFunctionBuilder.kt */
@Metadata(m184d1 = {"\u0000\u0016\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0011\n\u0000\u0010\u0000\u001a\u0004\u0018\u00010\u0001\"\u0006\b\u0000\u0010\u0002\u0018\u0001\"\u0006\b\u0001\u0010\u0003\u0018\u0001\"\u0006\b\u0002\u0010\u0004\u0018\u0001*\u00020\u00052\u0010\u0010\u0006\u001a\f\u0012\b\b\u0001\u0012\u0004\u0018\u00010\u00010\u0007H\u008a@¨\u0006\b"}, m183d2 = {"<anonymous>", "", "R", "P0", "P1", "Lkotlinx/coroutines/CoroutineScope;", "it", "", "expo/modules/kotlin/functions/AsyncFunctionBuilder$SuspendBody$5"}, m182k = 3, m181mv = {1, 6, 0}, m179xi = 176)
@DebugMetadata(m175c = "expo.modules.kotlin.functions.AsyncFunctionBuilderKt$Coroutine$$inlined$SuspendBody$3", m174f = "AsyncFunctionBuilder.kt", m173i = {}, m172l = {26}, m171m = "invokeSuspend", m170n = {}, m169s = {})
/* loaded from: classes4.dex */
public final class AsyncFunctionBuilderKt$Coroutine$$inlined$SuspendBody$3 extends SuspendLambda implements Function3<CoroutineScope, Object[], Continuation<? super Object>, Object> {
    final /* synthetic */ Function3 $block;
    /* synthetic */ Object L$0;
    int label;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public AsyncFunctionBuilderKt$Coroutine$$inlined$SuspendBody$3(Function3 function3, Continuation continuation) {
        super(3, continuation);
        this.$block = function3;
    }

    @Override // kotlin.jvm.functions.Function3
    public /* bridge */ /* synthetic */ Object invoke(CoroutineScope coroutineScope, Object[] objArr, Continuation<? super Object> continuation) {
        return invoke2(coroutineScope, objArr, (Continuation<Object>) continuation);
    }

    /* renamed from: invoke  reason: avoid collision after fix types in other method */
    public final Object invoke2(CoroutineScope coroutineScope, Object[] objArr, Continuation<Object> continuation) {
        AsyncFunctionBuilderKt$Coroutine$$inlined$SuspendBody$3 asyncFunctionBuilderKt$Coroutine$$inlined$SuspendBody$3 = new AsyncFunctionBuilderKt$Coroutine$$inlined$SuspendBody$3(this.$block, continuation);
        asyncFunctionBuilderKt$Coroutine$$inlined$SuspendBody$3.L$0 = objArr;
        return asyncFunctionBuilderKt$Coroutine$$inlined$SuspendBody$3.invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        Object obj2 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int r1 = this.label;
        if (r1 == 0) {
            ResultKt.throwOnFailure(obj);
            Object[] objArr = (Object[]) this.L$0;
            Function3 function3 = this.$block;
            Object obj3 = objArr[0];
            Intrinsics.reifiedOperationMarker(1, "P0");
            Object obj4 = objArr[0];
            Intrinsics.reifiedOperationMarker(1, "P1");
            this.label = 1;
            obj = function3.invoke(obj3, obj4, this);
            if (obj == obj2) {
                return obj2;
            }
        } else if (r1 != 1) {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        } else {
            ResultKt.throwOnFailure(obj);
        }
        return obj;
    }

    public final Object invokeSuspend$$forInline(Object obj) {
        Object[] objArr = (Object[]) this.L$0;
        Function3 function3 = this.$block;
        Object obj2 = objArr[0];
        Intrinsics.reifiedOperationMarker(1, "P0");
        Object obj3 = objArr[0];
        Intrinsics.reifiedOperationMarker(1, "P1");
        InlineMarker.mark(0);
        Object invoke = function3.invoke(obj2, obj3, this);
        InlineMarker.mark(1);
        return invoke;
    }
}
