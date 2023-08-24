package kotlinx.coroutines.flow.internal;

import kotlin.Metadata;
import kotlinx.coroutines.flow.FlowCollector;

/* compiled from: FlowExceptions.common.kt */
@Metadata(m184d1 = {"\u0000\u001a\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u001a\u0011\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0001H\u0081\b\u001a\u0018\u0010\u0003\u001a\u00020\u0004*\u00020\u00052\n\u0010\u0006\u001a\u0006\u0012\u0002\b\u00030\u0007H\u0000¨\u0006\b"}, m183d2 = {"checkIndexOverflow", "", "index", "checkOwnership", "", "Lkotlinx/coroutines/flow/internal/AbortFlowException;", "owner", "Lkotlinx/coroutines/flow/FlowCollector;", "kotlinx-coroutines-core"}, m182k = 2, m181mv = {1, 6, 0}, m179xi = 48)
/* loaded from: classes5.dex */
public final class FlowExceptions_commonKt {
    public static final void checkOwnership(FlowExceptions flowExceptions, FlowCollector<?> flowCollector) {
        if (flowExceptions.getOwner() != flowCollector) {
            throw flowExceptions;
        }
    }

    public static final int checkIndexOverflow(int r1) {
        if (r1 >= 0) {
            return r1;
        }
        throw new ArithmeticException("Index overflow has happened");
    }
}
