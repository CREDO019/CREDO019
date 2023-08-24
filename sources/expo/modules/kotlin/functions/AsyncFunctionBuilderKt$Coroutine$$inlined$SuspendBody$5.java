package expo.modules.kotlin.functions;

import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.functions.Function5;
import kotlin.jvm.internal.InlineMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.CoroutineScope;

/* compiled from: AsyncFunctionBuilder.kt */
@Metadata(m184d1 = {"\u0000\u0016\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0011\n\u0000\u0010\u0000\u001a\u0004\u0018\u00010\u0001\"\u0006\b\u0000\u0010\u0002\u0018\u0001\"\u0006\b\u0001\u0010\u0003\u0018\u0001\"\u0006\b\u0002\u0010\u0004\u0018\u0001\"\u0006\b\u0003\u0010\u0005\u0018\u0001\"\u0006\b\u0004\u0010\u0006\u0018\u0001*\u00020\u00072\u0010\u0010\b\u001a\f\u0012\b\b\u0001\u0012\u0004\u0018\u00010\u00010\tH\u008a@¨\u0006\n"}, m183d2 = {"<anonymous>", "", "R", "P0", "P1", "P2", "P3", "Lkotlinx/coroutines/CoroutineScope;", "it", "", "expo/modules/kotlin/functions/AsyncFunctionBuilder$SuspendBody$9"}, m182k = 3, m181mv = {1, 6, 0}, m179xi = 176)
@DebugMetadata(m175c = "expo.modules.kotlin.functions.AsyncFunctionBuilderKt$Coroutine$$inlined$SuspendBody$5", m174f = "AsyncFunctionBuilder.kt", m173i = {}, m172l = {38}, m171m = "invokeSuspend", m170n = {}, m169s = {})
/* loaded from: classes4.dex */
public final class AsyncFunctionBuilderKt$Coroutine$$inlined$SuspendBody$5 extends SuspendLambda implements Function3<CoroutineScope, Object[], Continuation<? super Object>, Object> {
    final /* synthetic */ Function5 $block;
    /* synthetic */ Object L$0;
    int label;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public AsyncFunctionBuilderKt$Coroutine$$inlined$SuspendBody$5(Function5 function5, Continuation continuation) {
        super(3, continuation);
        this.$block = function5;
    }

    @Override // kotlin.jvm.functions.Function3
    public /* bridge */ /* synthetic */ Object invoke(CoroutineScope coroutineScope, Object[] objArr, Continuation<? super Object> continuation) {
        return invoke2(coroutineScope, objArr, (Continuation<Object>) continuation);
    }

    /* renamed from: invoke  reason: avoid collision after fix types in other method */
    public final Object invoke2(CoroutineScope coroutineScope, Object[] objArr, Continuation<Object> continuation) {
        AsyncFunctionBuilderKt$Coroutine$$inlined$SuspendBody$5 asyncFunctionBuilderKt$Coroutine$$inlined$SuspendBody$5 = new AsyncFunctionBuilderKt$Coroutine$$inlined$SuspendBody$5(this.$block, continuation);
        asyncFunctionBuilderKt$Coroutine$$inlined$SuspendBody$5.L$0 = objArr;
        return asyncFunctionBuilderKt$Coroutine$$inlined$SuspendBody$5.invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        Object obj2 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int r1 = this.label;
        if (r1 == 0) {
            ResultKt.throwOnFailure(obj);
            Object[] objArr = (Object[]) this.L$0;
            Function5 function5 = this.$block;
            Object obj3 = objArr[0];
            Intrinsics.reifiedOperationMarker(1, "P0");
            Object obj4 = objArr[1];
            Intrinsics.reifiedOperationMarker(1, "P1");
            Object obj5 = objArr[2];
            Intrinsics.reifiedOperationMarker(1, "P2");
            Object obj6 = objArr[3];
            Intrinsics.reifiedOperationMarker(1, "P3");
            this.label = 1;
            obj = function5.invoke(obj3, obj4, obj5, obj6, this);
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
        Function5 function5 = this.$block;
        Object obj2 = objArr[0];
        Intrinsics.reifiedOperationMarker(1, "P0");
        Object obj3 = objArr[1];
        Intrinsics.reifiedOperationMarker(1, "P1");
        Object obj4 = objArr[2];
        Intrinsics.reifiedOperationMarker(1, "P2");
        Object obj5 = objArr[3];
        Intrinsics.reifiedOperationMarker(1, "P3");
        InlineMarker.mark(0);
        Object invoke = function5.invoke(obj2, obj3, obj4, obj5, this);
        InlineMarker.mark(1);
        return invoke;
    }
}
