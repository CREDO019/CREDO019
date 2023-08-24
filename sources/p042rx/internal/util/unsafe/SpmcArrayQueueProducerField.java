package p042rx.internal.util.unsafe;

/* compiled from: SpmcArrayQueue.java */
/* renamed from: rx.internal.util.unsafe.SpmcArrayQueueProducerField */
/* loaded from: classes6.dex */
abstract class SpmcArrayQueueProducerField<E> extends SpmcArrayQueueL1Pad<E> {
    protected static final long P_INDEX_OFFSET = UnsafeAccess.addressOf(SpmcArrayQueueProducerField.class, "producerIndex");
    private volatile long producerIndex;

    /* JADX INFO: Access modifiers changed from: protected */
    public final long lvProducerIndex() {
        return this.producerIndex;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final void soTail(long j) {
        UnsafeAccess.UNSAFE.putOrderedLong(this, P_INDEX_OFFSET, j);
    }

    public SpmcArrayQueueProducerField(int r1) {
        super(r1);
    }
}
