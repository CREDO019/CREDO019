package kotlinx.coroutines.sync;

import androidx.concurrent.futures.C0255xc40028dd;
import com.google.android.exoplayer2.text.ttml.TtmlNode;
import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;
import java.util.concurrent.atomic.AtomicLongFieldUpdater;
import java.util.concurrent.atomic.AtomicReferenceFieldUpdater;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugProbes;
import kotlin.jvm.functions.Function1;
import kotlinx.coroutines.CancellableContinuation;
import kotlinx.coroutines.CancellableContinuationImpl;
import kotlinx.coroutines.CancellableContinuationKt;
import kotlinx.coroutines.Debug;
import kotlinx.coroutines.internal.ConcurrentLinkedList;
import kotlinx.coroutines.internal.ConcurrentLinkedListNode;
import kotlinx.coroutines.internal.Segment;
import kotlinx.coroutines.internal.SegmentOrClosed;
import kotlinx.coroutines.internal.Symbol;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: Semaphore.kt */
@Metadata(m184d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u000b\n\u0002\u0018\u0002\n\u0002\u0010\u0003\n\u0002\b\u0004\n\u0002\u0018\u0002\b\u0002\u0018\u00002\u00020\u001eB\u0017\u0012\u0006\u0010\u0002\u001a\u00020\u0001\u0012\u0006\u0010\u0003\u001a\u00020\u0001¢\u0006\u0004\b\u0004\u0010\u0005J\u0013\u0010\u0007\u001a\u00020\u0006H\u0096@ø\u0001\u0000¢\u0006\u0004\b\u0007\u0010\bJ\u0013\u0010\t\u001a\u00020\u0006H\u0082@ø\u0001\u0000¢\u0006\u0004\b\t\u0010\bJ\u001d\u0010\r\u001a\u00020\f2\f\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\u00060\nH\u0002¢\u0006\u0004\b\r\u0010\u000eJ\u000f\u0010\u000f\u001a\u00020\u0006H\u0016¢\u0006\u0004\b\u000f\u0010\u0010J\u000f\u0010\u0011\u001a\u00020\fH\u0016¢\u0006\u0004\b\u0011\u0010\u0012J\u000f\u0010\u0013\u001a\u00020\fH\u0002¢\u0006\u0004\b\u0013\u0010\u0012J\u0019\u0010\u0014\u001a\u00020\f*\b\u0012\u0004\u0012\u00020\u00060\nH\u0002¢\u0006\u0004\b\u0014\u0010\u000eR\u0014\u0010\u0017\u001a\u00020\u00018VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u0015\u0010\u0016R \u0010\u001a\u001a\u000e\u0012\u0004\u0012\u00020\u0019\u0012\u0004\u0012\u00020\u00060\u00188\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u001a\u0010\u001bR\u0014\u0010\u0002\u001a\u00020\u00018\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0002\u0010\u001c\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006\u001d"}, m183d2 = {"Lkotlinx/coroutines/sync/SemaphoreImpl;", "", "permits", "acquiredPermits", "<init>", "(II)V", "", "acquire", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "acquireSlowPath", "Lkotlinx/coroutines/CancellableContinuation;", "cont", "", "addAcquireToQueue", "(Lkotlinx/coroutines/CancellableContinuation;)Z", "release", "()V", "tryAcquire", "()Z", "tryResumeNextFromQueue", "tryResumeAcquire", "getAvailablePermits", "()I", "availablePermits", "Lkotlin/Function1;", "", "onCancellationRelease", "Lkotlin/jvm/functions/Function1;", "I", "kotlinx-coroutines-core", "Lkotlinx/coroutines/sync/Semaphore;"}, m182k = 1, m181mv = {1, 6, 0}, m179xi = 48)
/* loaded from: classes5.dex */
public final class SemaphoreImpl implements Semaphore {
    volatile /* synthetic */ int _availablePermits;
    private volatile /* synthetic */ long deqIdx = 0;
    private volatile /* synthetic */ long enqIdx = 0;
    private volatile /* synthetic */ Object head;
    private final Function1<Throwable, Unit> onCancellationRelease;
    private final int permits;
    private volatile /* synthetic */ Object tail;
    private static final /* synthetic */ AtomicReferenceFieldUpdater head$FU = AtomicReferenceFieldUpdater.newUpdater(SemaphoreImpl.class, Object.class, TtmlNode.TAG_HEAD);
    private static final /* synthetic */ AtomicLongFieldUpdater deqIdx$FU = AtomicLongFieldUpdater.newUpdater(SemaphoreImpl.class, "deqIdx");
    private static final /* synthetic */ AtomicReferenceFieldUpdater tail$FU = AtomicReferenceFieldUpdater.newUpdater(SemaphoreImpl.class, Object.class, "tail");
    private static final /* synthetic */ AtomicLongFieldUpdater enqIdx$FU = AtomicLongFieldUpdater.newUpdater(SemaphoreImpl.class, "enqIdx");
    static final /* synthetic */ AtomicIntegerFieldUpdater _availablePermits$FU = AtomicIntegerFieldUpdater.newUpdater(SemaphoreImpl.class, "_availablePermits");

    public SemaphoreImpl(int r6, int r7) {
        this.permits = r6;
        boolean z = true;
        if (!(r6 > 0)) {
            throw new IllegalArgumentException(("Semaphore should have at least 1 permit, but had " + r6).toString());
        }
        if (!((r7 < 0 || r7 > r6) ? false : false)) {
            throw new IllegalArgumentException(("The number of acquired permits should be in 0.." + r6).toString());
        }
        SemaphoreSegment semaphoreSegment = new SemaphoreSegment(0L, null, 2);
        this.head = semaphoreSegment;
        this.tail = semaphoreSegment;
        this._availablePermits = r6 - r7;
        this.onCancellationRelease = new Function1<Throwable, Unit>() { // from class: kotlinx.coroutines.sync.SemaphoreImpl$onCancellationRelease$1
            /* JADX INFO: Access modifiers changed from: package-private */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(Throwable th) {
                invoke2(th);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke  reason: avoid collision after fix types in other method */
            public final void invoke2(Throwable th) {
                SemaphoreImpl.this.release();
            }
        };
    }

    @Override // kotlinx.coroutines.sync.Semaphore
    public int getAvailablePermits() {
        return Math.max(this._availablePermits, 0);
    }

    @Override // kotlinx.coroutines.sync.Semaphore
    public Object acquire(Continuation<? super Unit> continuation) {
        if (_availablePermits$FU.getAndDecrement(this) > 0) {
            return Unit.INSTANCE;
        }
        Object acquireSlowPath = acquireSlowPath(continuation);
        return acquireSlowPath == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? acquireSlowPath : Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r8v6 */
    public final boolean addAcquireToQueue(CancellableContinuation<? super Unit> cancellableContinuation) {
        int r5;
        Object m3258constructorimpl;
        SemaphoreSegment createSegment;
        int r52;
        Symbol symbol;
        Symbol symbol2;
        Symbol symbol3;
        boolean z;
        SemaphoreSegment semaphoreSegment = (SemaphoreSegment) this.tail;
        long andIncrement = enqIdx$FU.getAndIncrement(this);
        r5 = SemaphoreKt.SEGMENT_SIZE;
        long j = andIncrement / r5;
        do {
            SemaphoreSegment semaphoreSegment2 = semaphoreSegment;
            while (true) {
                if (semaphoreSegment2.getId() < j || semaphoreSegment2.getRemoved()) {
                    Object nextOrClosed = semaphoreSegment2.getNextOrClosed();
                    if (nextOrClosed == ConcurrentLinkedList.CLOSED) {
                        m3258constructorimpl = SegmentOrClosed.m3258constructorimpl(ConcurrentLinkedList.CLOSED);
                        break;
                    }
                    SemaphoreSegment semaphoreSegment3 = (Segment) ((ConcurrentLinkedListNode) nextOrClosed);
                    if (semaphoreSegment3 == null) {
                        createSegment = SemaphoreKt.createSegment(semaphoreSegment2.getId() + 1, semaphoreSegment2);
                        semaphoreSegment3 = createSegment;
                        if (semaphoreSegment2.trySetNext(semaphoreSegment3)) {
                            if (semaphoreSegment2.getRemoved()) {
                                semaphoreSegment2.remove();
                            }
                        }
                    }
                    semaphoreSegment2 = semaphoreSegment3;
                } else {
                    m3258constructorimpl = SegmentOrClosed.m3258constructorimpl(semaphoreSegment2);
                    break;
                }
            }
            if (SegmentOrClosed.m3263isClosedimpl(m3258constructorimpl)) {
                break;
            }
            Segment m3261getSegmentimpl = SegmentOrClosed.m3261getSegmentimpl(m3258constructorimpl);
            while (true) {
                Segment segment = (Segment) this.tail;
                if (segment.getId() >= m3261getSegmentimpl.getId()) {
                    break;
                } else if (!m3261getSegmentimpl.tryIncPointers$kotlinx_coroutines_core()) {
                    z = false;
                    continue;
                    break;
                } else if (C0255xc40028dd.m1422m(tail$FU, this, segment, m3261getSegmentimpl)) {
                    if (segment.decPointers$kotlinx_coroutines_core()) {
                        segment.remove();
                    }
                } else if (m3261getSegmentimpl.decPointers$kotlinx_coroutines_core()) {
                    m3261getSegmentimpl.remove();
                }
            }
            z = true;
            continue;
        } while (!z);
        SemaphoreSegment semaphoreSegment4 = (SemaphoreSegment) SegmentOrClosed.m3261getSegmentimpl(m3258constructorimpl);
        r52 = SemaphoreKt.SEGMENT_SIZE;
        int r4 = (int) (andIncrement % r52);
        if (!semaphoreSegment4.acquirers.compareAndSet(r4, null, cancellableContinuation)) {
            symbol = SemaphoreKt.PERMIT;
            symbol2 = SemaphoreKt.TAKEN;
            if (semaphoreSegment4.acquirers.compareAndSet(r4, symbol, symbol2)) {
                cancellableContinuation.resume(Unit.INSTANCE, this.onCancellationRelease);
                return true;
            }
            if (Debug.getASSERTIONS_ENABLED()) {
                Object obj = semaphoreSegment4.acquirers.get(r4);
                symbol3 = SemaphoreKt.BROKEN;
                if (!(obj == symbol3)) {
                    throw new AssertionError();
                }
            }
            return false;
        }
        cancellableContinuation.invokeOnCancellation(new CancelSemaphoreAcquisitionHandler(semaphoreSegment4, r4));
        return true;
    }

    /* JADX WARN: Code restructure failed: missing block: B:18:0x0059, code lost:
        r6 = true;
     */
    /* JADX WARN: Code restructure failed: missing block: B:60:0x0075, code lost:
        continue;
     */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r6v6 */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private final boolean tryResumeNextFromQueue() {
        /*
            Method dump skipped, instructions count: 263
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlinx.coroutines.sync.SemaphoreImpl.tryResumeNextFromQueue():boolean");
    }

    private final boolean tryResumeAcquire(CancellableContinuation<? super Unit> cancellableContinuation) {
        Object tryResume = cancellableContinuation.tryResume(Unit.INSTANCE, null, this.onCancellationRelease);
        if (tryResume == null) {
            return false;
        }
        cancellableContinuation.completeResume(tryResume);
        return true;
    }

    @Override // kotlinx.coroutines.sync.Semaphore
    public boolean tryAcquire() {
        int r0;
        do {
            r0 = this._availablePermits;
            if (r0 <= 0) {
                return false;
            }
        } while (!_availablePermits$FU.compareAndSet(this, r0, r0 - 1));
        return true;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final Object acquireSlowPath(Continuation<? super Unit> continuation) {
        CancellableContinuationImpl orCreateCancellableContinuation = CancellableContinuationKt.getOrCreateCancellableContinuation(IntrinsicsKt.intercepted(continuation));
        CancellableContinuationImpl cancellableContinuationImpl = orCreateCancellableContinuation;
        while (true) {
            if (!addAcquireToQueue(cancellableContinuationImpl)) {
                if (_availablePermits$FU.getAndDecrement(this) > 0) {
                    cancellableContinuationImpl.resume(Unit.INSTANCE, this.onCancellationRelease);
                    break;
                }
            } else {
                break;
            }
        }
        Object result = orCreateCancellableContinuation.getResult();
        if (result == IntrinsicsKt.getCOROUTINE_SUSPENDED()) {
            DebugProbes.probeCoroutineSuspended(continuation);
        }
        return result == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? result : Unit.INSTANCE;
    }

    @Override // kotlinx.coroutines.sync.Semaphore
    public void release() {
        while (true) {
            int r0 = this._availablePermits;
            if (!(r0 < this.permits)) {
                throw new IllegalStateException(("The number of released permits cannot be greater than " + this.permits).toString());
            }
            if (_availablePermits$FU.compareAndSet(this, r0, r0 + 1) && (r0 >= 0 || tryResumeNextFromQueue())) {
                return;
            }
        }
    }
}
