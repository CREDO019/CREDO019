package kotlinx.coroutines.flow;

import kotlin.Metadata;
import kotlin.coroutines.CoroutineContext;
import kotlinx.coroutines.channels.BufferOverflow;
import kotlinx.coroutines.flow.internal.ChannelFlowOperatorImpl;
import kotlinx.coroutines.internal.Symbol;

/* compiled from: SharedFlow.kt */
@Metadata(m184d1 = {"\u0000L\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0000\n\u0002\u0010\u0011\n\u0000\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0003\u001a0\u0010\u0004\u001a\b\u0012\u0004\u0012\u0002H\u00060\u0005\"\u0004\b\u0000\u0010\u00062\b\b\u0002\u0010\u0007\u001a\u00020\b2\b\b\u0002\u0010\t\u001a\u00020\b2\b\b\u0002\u0010\n\u001a\u00020\u000b\u001a6\u0010\f\u001a\b\u0012\u0004\u0012\u0002H\u00060\r\"\u0004\b\u0000\u0010\u0006*\b\u0012\u0004\u0012\u0002H\u00060\u000e2\u0006\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\b2\u0006\u0010\n\u001a\u00020\u000bH\u0000\u001a#\u0010\u0012\u001a\u0004\u0018\u00010\u0013*\n\u0012\u0006\u0012\u0004\u0018\u00010\u00130\u00142\u0006\u0010\u0015\u001a\u00020\u0016H\u0002¢\u0006\u0002\u0010\u0017\u001a+\u0010\u0018\u001a\u00020\u0019*\n\u0012\u0006\u0012\u0004\u0018\u00010\u00130\u00142\u0006\u0010\u0015\u001a\u00020\u00162\b\u0010\u001a\u001a\u0004\u0018\u00010\u0013H\u0002¢\u0006\u0002\u0010\u001b\"\u0016\u0010\u0000\u001a\u00020\u00018\u0000X\u0081\u0004¢\u0006\b\n\u0000\u0012\u0004\b\u0002\u0010\u0003¨\u0006\u001c"}, m183d2 = {"NO_VALUE", "Lkotlinx/coroutines/internal/Symbol;", "getNO_VALUE$annotations", "()V", "MutableSharedFlow", "Lkotlinx/coroutines/flow/MutableSharedFlow;", "T", "replay", "", "extraBufferCapacity", "onBufferOverflow", "Lkotlinx/coroutines/channels/BufferOverflow;", "fuseSharedFlow", "Lkotlinx/coroutines/flow/Flow;", "Lkotlinx/coroutines/flow/SharedFlow;", "context", "Lkotlin/coroutines/CoroutineContext;", "capacity", "getBufferAt", "", "", "index", "", "([Ljava/lang/Object;J)Ljava/lang/Object;", "setBufferAt", "", "item", "([Ljava/lang/Object;JLjava/lang/Object;)V", "kotlinx-coroutines-core"}, m182k = 2, m181mv = {1, 6, 0}, m179xi = 48)
/* loaded from: classes5.dex */
public final class SharedFlowKt {
    public static final Symbol NO_VALUE = new Symbol("NO_VALUE");

    public static final /* synthetic */ Object access$getBufferAt(Object[] objArr, long j) {
        return getBufferAt(objArr, j);
    }

    public static final /* synthetic */ void access$setBufferAt(Object[] objArr, long j, Object obj) {
        setBufferAt(objArr, j, obj);
    }

    public static /* synthetic */ void getNO_VALUE$annotations() {
    }

    public static /* synthetic */ MutableSharedFlow MutableSharedFlow$default(int r1, int r2, BufferOverflow bufferOverflow, int r4, Object obj) {
        if ((r4 & 1) != 0) {
            r1 = 0;
        }
        if ((r4 & 2) != 0) {
            r2 = 0;
        }
        if ((r4 & 4) != 0) {
            bufferOverflow = BufferOverflow.SUSPEND;
        }
        return MutableSharedFlow(r1, r2, bufferOverflow);
    }

    public static final <T> MutableSharedFlow<T> MutableSharedFlow(int r3, int r4, BufferOverflow bufferOverflow) {
        boolean z = true;
        if (!(r3 >= 0)) {
            throw new IllegalArgumentException(("replay cannot be negative, but was " + r3).toString());
        }
        if (!(r4 >= 0)) {
            throw new IllegalArgumentException(("extraBufferCapacity cannot be negative, but was " + r4).toString());
        }
        if (r3 <= 0 && r4 <= 0 && bufferOverflow != BufferOverflow.SUSPEND) {
            z = false;
        }
        if (!z) {
            throw new IllegalArgumentException(("replay or extraBufferCapacity must be positive with non-default onBufferOverflow strategy " + bufferOverflow).toString());
        }
        int r42 = r4 + r3;
        if (r42 < 0) {
            r42 = Integer.MAX_VALUE;
        }
        return new SharedFlowImpl(r3, r42, bufferOverflow);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Object getBufferAt(Object[] objArr, long j) {
        return objArr[(objArr.length - 1) & ((int) j)];
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void setBufferAt(Object[] objArr, long j, Object obj) {
        objArr[(objArr.length - 1) & ((int) j)] = obj;
    }

    public static final <T> Flow<T> fuseSharedFlow(SharedFlow<? extends T> sharedFlow, CoroutineContext coroutineContext, int r3, BufferOverflow bufferOverflow) {
        if ((r3 == 0 || r3 == -3) && bufferOverflow == BufferOverflow.SUSPEND) {
            return sharedFlow;
        }
        return new ChannelFlowOperatorImpl(sharedFlow, coroutineContext, r3, bufferOverflow);
    }
}