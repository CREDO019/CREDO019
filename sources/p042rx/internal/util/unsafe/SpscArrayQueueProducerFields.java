package p042rx.internal.util.unsafe;

/* compiled from: SpscArrayQueue.java */
/* renamed from: rx.internal.util.unsafe.SpscArrayQueueProducerFields */
/* loaded from: classes6.dex */
abstract class SpscArrayQueueProducerFields<E> extends SpscArrayQueueL1Pad<E> {
    protected static final long P_INDEX_OFFSET = UnsafeAccess.addressOf(SpscArrayQueueProducerFields.class, "producerIndex");
    protected long producerIndex;
    protected long producerLookAhead;

    public SpscArrayQueueProducerFields(int r1) {
        super(r1);
    }
}
