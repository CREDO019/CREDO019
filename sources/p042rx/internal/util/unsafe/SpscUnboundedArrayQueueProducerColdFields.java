package p042rx.internal.util.unsafe;

/* compiled from: SpscUnboundedArrayQueue.java */
/* renamed from: rx.internal.util.unsafe.SpscUnboundedArrayQueueProducerColdFields */
/* loaded from: classes6.dex */
abstract class SpscUnboundedArrayQueueProducerColdFields<E> extends SpscUnboundedArrayQueueProducerFields<E> {
    protected E[] producerBuffer;
    protected long producerLookAhead;
    protected int producerLookAheadStep;
    protected long producerMask;
}
