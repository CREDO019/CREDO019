package kotlin.reflect.full;

import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: KCallables.kt */
@Metadata(m182k = 3, m181mv = {1, 6, 0}, m179xi = 48)
@DebugMetadata(m175c = "kotlin.reflect.full.KCallables", m174f = "KCallables.kt", m173i = {0, 0, 0}, m172l = {74}, m171m = "callSuspendBy", m170n = {"$this$callSuspendBy", "args", "kCallable"}, m169s = {"L$0", "L$1", "L$2"})
/* loaded from: classes5.dex */
public final class KCallables$callSuspendBy$1<R> extends ContinuationImpl {
    Object L$0;
    Object L$1;
    Object L$2;
    int label;
    /* synthetic */ Object result;

    /* JADX INFO: Access modifiers changed from: package-private */
    public KCallables$callSuspendBy$1(Continuation<? super KCallables$callSuspendBy$1> continuation) {
        super(continuation);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        this.result = obj;
        this.label |= Integer.MIN_VALUE;
        return KCallables.callSuspendBy(null, null, this);
    }
}