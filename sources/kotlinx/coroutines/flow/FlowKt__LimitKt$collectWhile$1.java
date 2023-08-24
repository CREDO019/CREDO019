package kotlinx.coroutines.flow;

import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: Limit.kt */
@Metadata(m182k = 3, m181mv = {1, 6, 0}, m179xi = 176)
@DebugMetadata(m175c = "kotlinx.coroutines.flow.FlowKt__LimitKt", m174f = "Limit.kt", m173i = {0}, m172l = {136}, m171m = "collectWhile", m170n = {"collector"}, m169s = {"L$0"})
/* loaded from: classes5.dex */
public final class FlowKt__LimitKt$collectWhile$1<T> extends ContinuationImpl {
    Object L$0;
    int label;
    /* synthetic */ Object result;

    /* JADX INFO: Access modifiers changed from: package-private */
    public FlowKt__LimitKt$collectWhile$1(Continuation<? super FlowKt__LimitKt$collectWhile$1> continuation) {
        super(continuation);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        this.result = obj;
        this.label |= Integer.MIN_VALUE;
        return Limit.collectWhile(null, null, this);
    }
}
