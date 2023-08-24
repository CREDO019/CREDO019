package p042rx.internal.util.atomic;

import java.util.Collection;
import java.util.Iterator;
import java.util.Objects;
import java.util.Queue;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.AtomicReferenceArray;
import p042rx.internal.util.unsafe.Pow2;

/* renamed from: rx.internal.util.atomic.SpscExactAtomicArrayQueue */
/* loaded from: classes6.dex */
public final class SpscExactAtomicArrayQueue<T> extends AtomicReferenceArray<T> implements Queue<T> {
    private static final long serialVersionUID = 6210984603741293445L;
    final int capacitySkip;
    final AtomicLong consumerIndex;
    final int mask;
    final AtomicLong producerIndex;

    public SpscExactAtomicArrayQueue(int r3) {
        super(Pow2.roundToPowerOfTwo(r3));
        int length = length();
        this.mask = length - 1;
        this.capacitySkip = length - r3;
        this.producerIndex = new AtomicLong();
        this.consumerIndex = new AtomicLong();
    }

    @Override // java.util.Queue
    public boolean offer(T t) {
        Objects.requireNonNull(t);
        long j = this.producerIndex.get();
        int r2 = this.mask;
        if (get(((int) (this.capacitySkip + j)) & r2) != null) {
            return false;
        }
        this.producerIndex.lazySet(j + 1);
        lazySet(r2 & ((int) j), t);
        return true;
    }

    @Override // java.util.Queue
    public T poll() {
        long j = this.consumerIndex.get();
        int r2 = ((int) j) & this.mask;
        T t = get(r2);
        if (t == null) {
            return null;
        }
        this.consumerIndex.lazySet(j + 1);
        lazySet(r2, null);
        return t;
    }

    @Override // java.util.Queue
    public T peek() {
        return get(this.mask & ((int) this.consumerIndex.get()));
    }

    @Override // java.util.Collection
    public void clear() {
        while (true) {
            if (poll() == null && isEmpty()) {
                return;
            }
        }
    }

    @Override // java.util.Collection
    public boolean isEmpty() {
        return this.producerIndex == this.consumerIndex;
    }

    @Override // java.util.Collection
    public int size() {
        long j = this.consumerIndex.get();
        while (true) {
            long j2 = this.producerIndex.get();
            long j3 = this.consumerIndex.get();
            if (j == j3) {
                return (int) (j2 - j3);
            }
            j = j3;
        }
    }

    @Override // java.util.Collection
    public boolean contains(Object obj) {
        throw new UnsupportedOperationException();
    }

    @Override // java.util.Collection, java.lang.Iterable
    public Iterator<T> iterator() {
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