package p042rx.internal.util.atomic;

import java.util.Collection;
import java.util.Iterator;
import java.util.Objects;
import java.util.Queue;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.AtomicReferenceArray;
import p042rx.internal.util.unsafe.Pow2;

/* renamed from: rx.internal.util.atomic.SpscUnboundedAtomicArrayQueue */
/* loaded from: classes6.dex */
public final class SpscUnboundedAtomicArrayQueue<T> implements Queue<T> {
    AtomicReferenceArray<Object> consumerBuffer;
    final AtomicLong consumerIndex;
    int consumerMask;
    AtomicReferenceArray<Object> producerBuffer;
    final AtomicLong producerIndex;
    long producerLookAhead;
    int producerLookAheadStep;
    int producerMask;
    static final int MAX_LOOK_AHEAD_STEP = Integer.getInteger("jctools.spsc.max.lookahead.step", 4096).intValue();
    private static final Object HAS_NEXT = new Object();

    private static int calcDirectOffset(int r0) {
        return r0;
    }

    public SpscUnboundedAtomicArrayQueue(int r4) {
        int roundToPowerOfTwo = Pow2.roundToPowerOfTwo(Math.max(8, r4));
        int r0 = roundToPowerOfTwo - 1;
        this.producerIndex = new AtomicLong();
        this.consumerIndex = new AtomicLong();
        AtomicReferenceArray<Object> atomicReferenceArray = new AtomicReferenceArray<>(roundToPowerOfTwo + 1);
        this.producerBuffer = atomicReferenceArray;
        this.producerMask = r0;
        adjustLookAheadStep(roundToPowerOfTwo);
        this.consumerBuffer = atomicReferenceArray;
        this.consumerMask = r0;
        this.producerLookAhead = r0 - 1;
        soProducerIndex(0L);
    }

    @Override // java.util.Queue
    public boolean offer(T t) {
        Objects.requireNonNull(t);
        AtomicReferenceArray<Object> atomicReferenceArray = this.producerBuffer;
        long lpProducerIndex = lpProducerIndex();
        int r0 = this.producerMask;
        int calcWrappedOffset = calcWrappedOffset(lpProducerIndex, r0);
        if (lpProducerIndex < this.producerLookAhead) {
            return writeToQueue(atomicReferenceArray, t, lpProducerIndex, calcWrappedOffset);
        }
        long j = this.producerLookAheadStep + lpProducerIndex;
        if (lvElement(atomicReferenceArray, calcWrappedOffset(j, r0)) == null) {
            this.producerLookAhead = j - 1;
            return writeToQueue(atomicReferenceArray, t, lpProducerIndex, calcWrappedOffset);
        } else if (lvElement(atomicReferenceArray, calcWrappedOffset(1 + lpProducerIndex, r0)) != null) {
            return writeToQueue(atomicReferenceArray, t, lpProducerIndex, calcWrappedOffset);
        } else {
            resize(atomicReferenceArray, lpProducerIndex, calcWrappedOffset, t, r0);
            return true;
        }
    }

    private boolean writeToQueue(AtomicReferenceArray<Object> atomicReferenceArray, T t, long j, int r7) {
        soProducerIndex(j + 1);
        soElement(atomicReferenceArray, r7, t);
        return true;
    }

    private void resize(AtomicReferenceArray<Object> atomicReferenceArray, long j, int r8, T t, long j2) {
        AtomicReferenceArray<Object> atomicReferenceArray2 = new AtomicReferenceArray<>(atomicReferenceArray.length());
        this.producerBuffer = atomicReferenceArray2;
        this.producerLookAhead = (j2 + j) - 1;
        soProducerIndex(j + 1);
        soElement(atomicReferenceArray2, r8, t);
        soNext(atomicReferenceArray, atomicReferenceArray2);
        soElement(atomicReferenceArray, r8, HAS_NEXT);
    }

    private void soNext(AtomicReferenceArray<Object> atomicReferenceArray, AtomicReferenceArray<Object> atomicReferenceArray2) {
        soElement(atomicReferenceArray, calcDirectOffset(atomicReferenceArray.length() - 1), atomicReferenceArray2);
    }

    private AtomicReferenceArray<Object> lvNext(AtomicReferenceArray<Object> atomicReferenceArray) {
        return (AtomicReferenceArray) lvElement(atomicReferenceArray, calcDirectOffset(atomicReferenceArray.length() - 1));
    }

    @Override // java.util.Queue
    public T poll() {
        AtomicReferenceArray<Object> atomicReferenceArray = this.consumerBuffer;
        long lpConsumerIndex = lpConsumerIndex();
        int r3 = this.consumerMask;
        int calcWrappedOffset = calcWrappedOffset(lpConsumerIndex, r3);
        T t = (T) lvElement(atomicReferenceArray, calcWrappedOffset);
        boolean z = t == HAS_NEXT;
        if (t == null || z) {
            if (z) {
                return newBufferPoll(lvNext(atomicReferenceArray), lpConsumerIndex, r3);
            }
            return null;
        }
        soConsumerIndex(lpConsumerIndex + 1);
        soElement(atomicReferenceArray, calcWrappedOffset, null);
        return t;
    }

    private T newBufferPoll(AtomicReferenceArray<Object> atomicReferenceArray, long j, int r8) {
        this.consumerBuffer = atomicReferenceArray;
        int calcWrappedOffset = calcWrappedOffset(j, r8);
        T t = (T) lvElement(atomicReferenceArray, calcWrappedOffset);
        if (t == null) {
            return null;
        }
        soConsumerIndex(j + 1);
        soElement(atomicReferenceArray, calcWrappedOffset, null);
        return t;
    }

    @Override // java.util.Queue
    public T peek() {
        AtomicReferenceArray<Object> atomicReferenceArray = this.consumerBuffer;
        long lpConsumerIndex = lpConsumerIndex();
        int r3 = this.consumerMask;
        T t = (T) lvElement(atomicReferenceArray, calcWrappedOffset(lpConsumerIndex, r3));
        return t == HAS_NEXT ? newBufferPeek(lvNext(atomicReferenceArray), lpConsumerIndex, r3) : t;
    }

    @Override // java.util.Collection
    public void clear() {
        while (true) {
            if (poll() == null && isEmpty()) {
                return;
            }
        }
    }

    private T newBufferPeek(AtomicReferenceArray<Object> atomicReferenceArray, long j, int r4) {
        this.consumerBuffer = atomicReferenceArray;
        return (T) lvElement(atomicReferenceArray, calcWrappedOffset(j, r4));
    }

    @Override // java.util.Collection
    public int size() {
        long lvConsumerIndex = lvConsumerIndex();
        while (true) {
            long lvProducerIndex = lvProducerIndex();
            long lvConsumerIndex2 = lvConsumerIndex();
            if (lvConsumerIndex == lvConsumerIndex2) {
                return (int) (lvProducerIndex - lvConsumerIndex2);
            }
            lvConsumerIndex = lvConsumerIndex2;
        }
    }

    @Override // java.util.Collection
    public boolean isEmpty() {
        return lvProducerIndex() == lvConsumerIndex();
    }

    private void adjustLookAheadStep(int r2) {
        this.producerLookAheadStep = Math.min(r2 / 4, MAX_LOOK_AHEAD_STEP);
    }

    private long lvProducerIndex() {
        return this.producerIndex.get();
    }

    private long lvConsumerIndex() {
        return this.consumerIndex.get();
    }

    private long lpProducerIndex() {
        return this.producerIndex.get();
    }

    private long lpConsumerIndex() {
        return this.consumerIndex.get();
    }

    private void soProducerIndex(long j) {
        this.producerIndex.lazySet(j);
    }

    private void soConsumerIndex(long j) {
        this.consumerIndex.lazySet(j);
    }

    private static int calcWrappedOffset(long j, int r2) {
        return calcDirectOffset(((int) j) & r2);
    }

    private static void soElement(AtomicReferenceArray<Object> atomicReferenceArray, int r1, Object obj) {
        atomicReferenceArray.lazySet(r1, obj);
    }

    private static <E> Object lvElement(AtomicReferenceArray<Object> atomicReferenceArray, int r1) {
        return atomicReferenceArray.get(r1);
    }

    @Override // java.util.Collection, java.lang.Iterable
    public Iterator<T> iterator() {
        throw new UnsupportedOperationException();
    }

    @Override // java.util.Collection
    public boolean contains(Object obj) {
        throw new UnsupportedOperationException();
    }

    @Override // java.util.Collection
    public Object[] toArray() {
        throw new UnsupportedOperationException();
    }

    @Override // java.util.Collection
    public <E> E[] toArray(E[] eArr) {
        throw new UnsupportedOperationException();
    }

    @Override // java.util.Collection
    public boolean remove(Object obj) {
        throw new UnsupportedOperationException();
    }

    @Override // java.util.Collection
    public boolean containsAll(Collection<?> collection) {
        throw new UnsupportedOperationException();
    }

    @Override // java.util.Collection
    public boolean addAll(Collection<? extends T> collection) {
        throw new UnsupportedOperationException();
    }

    @Override // java.util.Collection
    public boolean removeAll(Collection<?> collection) {
        throw new UnsupportedOperationException();
    }

    @Override // java.util.Collection
    public boolean retainAll(Collection<?> collection) {
        throw new UnsupportedOperationException();
    }

    @Override // java.util.Queue, java.util.Collection
    public boolean add(T t) {
        throw new UnsupportedOperationException();
    }

    @Override // java.util.Queue
    public T remove() {
        throw new UnsupportedOperationException();
    }

    @Override // java.util.Queue
    public T element() {
        throw new UnsupportedOperationException();
    }
}