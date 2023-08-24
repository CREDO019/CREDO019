package kotlinx.coroutines.flow;

import com.onesignal.shortcutbadger.impl.NewHtcHomeBadger;
import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: Count.kt */
@Metadata(m182k = 3, m181mv = {1, 6, 0}, m179xi = 48)
@DebugMetadata(m175c = "kotlinx.coroutines.flow.FlowKt__CountKt", m174f = "Count.kt", m173i = {0}, m172l = {30}, m171m = NewHtcHomeBadger.COUNT, m170n = {"i"}, m169s = {"L$0"})
/* loaded from: classes5.dex */
public final class FlowKt__CountKt$count$3<T> extends ContinuationImpl {
    Object L$0;
    int label;
    /* synthetic */ Object result;

    /* JADX INFO: Access modifiers changed from: package-private */
    public FlowKt__CountKt$count$3(Continuation<? super FlowKt__CountKt$count$3> continuation) {
        super(continuation);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        this.result = obj;
        this.label |= Integer.MIN_VALUE;
        return FlowKt.count(null, null, this);
    }
}
