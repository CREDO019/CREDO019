package expo.modules.kotlin.functions;

import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.functions.Function4;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.CoroutineScope;

/* compiled from: AsyncFunctionBuilder.kt */
@Metadata(m184d1 = {"\u0000\u0014\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0011\u0010\u0000\u001a\u0004\u0018\u00010\u0001\"\u0006\b\u0000\u0010\u0002\u0018\u0001\"\u0006\b\u0001\u0010\u0003\u0018\u0001\"\u0006\b\u0002\u0010\u0004\u0018\u0001\"\u0006\b\u0003\u0010\u0005\u0018\u0001*\u00020\u00062\u0010\u0010\u0007\u001a\f\u0012\b\b\u0001\u0012\u0004\u0018\u00010\u00010\bH\u008a@"}, m183d2 = {"<anonymous>", "", "R", "P0", "P1", "P2", "Lkotlinx/coroutines/CoroutineScope;", "it", ""}, m182k = 3, m181mv = {1, 6, 0}, m179xi = 176)
@DebugMetadata(m175c = "expo.modules.kotlin.functions.AsyncFunctionBuilder$SuspendBody$7", m174f = "AsyncFunctionBuilder.kt", m173i = {}, m172l = {32}, m171m = "invokeSuspend", m170n = {}, m169s = {})
/* loaded from: classes4.dex */
public final class AsyncFunctionBuilder$SuspendBody$7 extends SuspendLambda implements Function3<CoroutineScope, Object[], Continuation<? super Object>, Object> {
    final /* synthetic */ Function4<P0, P1, P2, Continuation<? super R>, Object> $block;
    /* synthetic */ Object L$0;
    int label;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    /* JADX WARN: Multi-variable type inference failed */
    public AsyncFunctionBuilder$SuspendBody$7(Function4<? super P0, ? super P1, ? super P2, ? super Continuation<? super R>, ? extends Object> function4, Continuation<? super AsyncFunctionBuilder$SuspendBody$7> continuation) {
        super(3, continuation);
        this.$block = function4;
    }

    @Override // kotlin.jvm.functions.Function3
    public /* bridge */ /* synthetic */ Object invoke(CoroutineScope coroutineScope, Object[] objArr, Continuation<? super Object> continuation) {
        return invoke2(coroutineScope, objArr, (Continuation<Object>) continuation);
    }

    /* renamed from: invoke  reason: avoid collision after fix types in other method */
    public final Object invoke2(CoroutineScope coroutineScope, Object[] objArr, Continuation<Object> continuation) {
        AsyncFunctionBuilder$SuspendBody$7 asyncFunctionBuilder$SuspendBody$7 = new AsyncFunctionBuilder$SuspendBody$7(this.$block, continuation);
        asyncFunctionBuilder$SuspendBody$7.L$0 = objArr;
        return asyncFunctionBuilder$SuspendBody$7.invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        Object obj2 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int r1 = this.label;
        if (r1 == 0) {
            ResultKt.throwOnFailure(obj);
            Object[] objArr = (Object[]) this.L$0;
            Function4<P0, P1, P2, Continuation<? super R>, Object> function4 = this.$block;
            Object obj3 = objArr[0];
            Intrinsics.reifiedOperationMarker(1, "P0");
            Object obj4 = objArr[1];
            Intrinsics.reifiedOperationMarker(1, "P1");
            Object obj5 = objArr[2];
            Intrinsics.reifiedOperationMarker(1, "P2");
            this.label = 1;
            obj = function4.invoke(obj3, obj4, obj5, this);
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
        Function4<P0, P1, P2, Continuation<? super R>, Object> function4 = this.$block;
        Object obj2 = objArr[0];
        Intrinsics.reifiedOperationMarker(1, "P0");
        Object obj3 = objArr[1];
        Intrinsics.reifiedOperationMarker(1, "P1");
        Object obj4 = objArr[2];
        Intrinsics.reifiedOperationMarker(1, "P2");
        return function4.invoke(obj2, obj3, obj4, this);
    }
}
