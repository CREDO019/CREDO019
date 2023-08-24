package p042rx.internal.util.unsafe;

/* compiled from: SpmcArrayQueue.java */
/* renamed from: rx.internal.util.unsafe.SpmcArrayQueueProducerIndexCacheField */
/* loaded from: classes6.dex */
abstract class SpmcArrayQueueProducerIndexCacheField<E> extends SpmcArrayQueueMidPad<E> {
    private volatile long producerIndexCache;

    public SpmcArrayQueueProducerIndexCacheField(int r1) {
        super(r1);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final long lvProducerIndexCache() {
        return this.producerIndexCache;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final void svProducerIndexCache(long j) {
        this.producerIndexCache = j;
    }
}
