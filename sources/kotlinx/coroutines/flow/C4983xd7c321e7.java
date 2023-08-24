package kotlinx.coroutines.flow;

import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.functions.Function4;
import kotlin.jvm.functions.Functions;
import kotlin.jvm.internal.InlineMarker;
import kotlinx.coroutines.flow.internal.Combine;

/* JADX INFO: Add missing generic type declarations: [R] */
/* compiled from: Zip.kt */
@Metadata(m184d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\u00020\u0001\"\u0006\b\u0000\u0010\u0002\u0018\u0001\"\u0004\b\u0001\u0010\u0003*\b\u0012\u0004\u0012\u0002H\u00030\u0004H\u008a@¨\u0006\u0005"}, m183d2 = {"<anonymous>", "", "T", "R", "Lkotlinx/coroutines/flow/FlowCollector;", "kotlinx/coroutines/flow/FlowKt__ZipKt$combineTransformUnsafe$1"}, m182k = 3, m181mv = {1, 6, 0}, m179xi = 48)
@DebugMetadata(m175c = "kotlinx.coroutines.flow.FlowKt__ZipKt$combineTransform$$inlined$combineTransformUnsafe$FlowKt__ZipKt$2", m174f = "Zip.kt", m173i = {}, m172l = {273}, m171m = "invokeSuspend", m170n = {}, m169s = {})
/* renamed from: kotlinx.coroutines.flow.FlowKt__ZipKt$combineTransform$$inlined$combineTransformUnsafe$FlowKt__ZipKt$2 */
/* loaded from: classes5.dex */
public final class C4983xd7c321e7<R> extends SuspendLambda implements Function2<FlowCollector<? super R>, Continuation<? super Unit>, Object> {
    final /* synthetic */ Flow[] $flows;
    final /* synthetic */ Function4 $transform$inlined;
    private /* synthetic */ Object L$0;
    int label;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public C4983xd7c321e7(Flow[] flowArr, Continuation continuation, Function4 function4) {
        super(2, continuation);
        this.$flows = flowArr;
        this.$transform$inlined = function4;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        C4983xd7c321e7 c4983xd7c321e7 = new C4983xd7c321e7(this.$flows, continuation, this.$transform$inlined);
        c4983xd7c321e7.L$0 = obj;
        return c4983xd7c321e7;
    }

    @Override // kotlin.jvm.functions.Function2
    public /* bridge */ /* synthetic */ Object invoke(Object obj, Continuation<? super Unit> continuation) {
        return invoke((FlowCollector) ((FlowCollector) obj), continuation);
    }

    public final Object invoke(FlowCollector<? super R> flowCollector, Continuation<? super Unit> continuation) {
        return ((C4983xd7c321e7) create(flowCollector, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    /* compiled from: Zip.kt */
    @Metadata(m184d1 = {"\u0000\u0016\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0011\n\u0000\u0010\u0000\u001a\u00020\u0001\"\u0006\b\u0000\u0010\u0002\u0018\u0001\"\u0004\b\u0001\u0010\u0003*\b\u0012\u0004\u0012\u0002H\u00030\u00042\f\u0010\u0005\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0006H\u008a@¨\u0006\u0007"}, m183d2 = {"<anonymous>", "", "T", "R", "Lkotlinx/coroutines/flow/FlowCollector;", "it", "", "kotlinx/coroutines/flow/FlowKt__ZipKt$combineTransformUnsafe$1$1"}, m182k = 3, m181mv = {1, 6, 0}, m179xi = 48)
    @DebugMetadata(m175c = "kotlinx.coroutines.flow.FlowKt__ZipKt$combineTransform$$inlined$combineTransformUnsafe$FlowKt__ZipKt$2$1", m174f = "Zip.kt", m173i = {}, m172l = {333}, m171m = "invokeSuspend", m170n = {}, m169s = {})
    /* renamed from: kotlinx.coroutines.flow.FlowKt__ZipKt$combineTransform$$inlined$combineTransformUnsafe$FlowKt__ZipKt$2$1 */
    /* loaded from: classes5.dex */
    public static final class C49841 extends SuspendLambda implements Function3<FlowCollector<? super R>, Object[], Continuation<? super Unit>, Object> {
        final /* synthetic */ Function4 $transform$inlined;
        private /* synthetic */ Object L$0;
        /* synthetic */ Object L$1;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public C49841(Continuation continuation, Function4 function4) {
            super(3, continuation);
            this.$transform$inlined = function4;
        }

        @Override // kotlin.jvm.functions.Function3
        public /* bridge */ /* synthetic */ Object invoke(Object obj, Object[] objArr, Continuation<? super Unit> continuation) {
            return invoke((FlowCollector) ((FlowCollector) obj), objArr, continuation);
        }

        public final Object invoke(FlowCollector<? super R> flowCollector, Object[] objArr, Continuation<? super Unit> continuation) {
            C49841 c49841 = new C49841(continuation, this.$transform$inlined);
            c49841.L$0 = flowCollector;
            c49841.L$1 = objArr;
            return c49841.invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            Object obj2 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int r1 = this.label;
            if (r1 == 0) {
                ResultKt.throwOnFailure(obj);
                Object[] objArr = (Object[]) this.L$1;
                Function4 function4 = this.$transform$inlined;
                Object obj3 = objArr[0];
                Object obj4 = objArr[1];
                this.label = 1;
                InlineMarker.mark(6);
                Object invoke = function4.invoke((FlowCollector) this.L$0, obj3, obj4, this);
                InlineMarker.mark(7);
                if (invoke == obj2) {
                    return obj2;
                }
            } else if (r1 != 1) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            } else {
                ResultKt.throwOnFailure(obj);
            }
            return Unit.INSTANCE;
        }
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        Functions nullArrayFactory$FlowKt__ZipKt;
        Object obj2 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int r1 = this.label;
        if (r1 == 0) {
            ResultKt.throwOnFailure(obj);
            Flow[] flowArr = this.$flows;
            nullArrayFactory$FlowKt__ZipKt = Zip.nullArrayFactory$FlowKt__ZipKt();
            this.label = 1;
            if (Combine.combineInternal((FlowCollector) this.L$0, flowArr, nullArrayFactory$FlowKt__ZipKt, new C49841(null, this.$transform$inlined), this) == obj2) {
                return obj2;
            }
        } else if (r1 != 1) {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        } else {
            ResultKt.throwOnFailure(obj);
        }
        return Unit.INSTANCE;
    }
}