package p042rx.internal.util.atomic;

import java.util.AbstractQueue;
import java.util.Iterator;
import java.util.concurrent.atomic.AtomicReferenceArray;
import p042rx.internal.util.unsafe.Pow2;

/* renamed from: rx.internal.util.atomic.AtomicReferenceArrayQueue */
/* loaded from: classes6.dex */
abstract class AtomicReferenceArrayQueue<E> extends AbstractQueue<E> {
    protected final AtomicReferenceArray<E> buffer;
    protected final int mask;

    /* JADX INFO: Access modifiers changed from: protected */
    public final int calcElementOffset(long j, int r3) {
        return ((int) j) & r3;
    }

    public AtomicReferenceArrayQueue(int r2) {
        int roundToPowerOfTwo = Pow2.roundToPowerOfTwo(r2);
        this.mask = roundToPowerOfTwo - 1;
        this.buffer = new AtomicReferenceArray<>(roundToPowerOfTwo);
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.lang.Iterable
    public Iterator<E> iterator() {
        throw new UnsupportedOperationException();
    }

    @Override // java.util.AbstractQueue, java.util.AbstractCollection, java.util.Collection
    public void clear() {
        while (true) {
            if (poll() == null && isEmpty()) {
                return;
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final int calcElementOffset(long j) {
        return this.mask & ((int) j);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final E lvElement(AtomicReferenceArray<E> atomicReferenceArray, int r2) {
        return atomicReferenceArray.get(r2);
    }

    protected final E lpElement(AtomicReferenceArray<E> atomicReferenceArray, int r2) {
        return atomicReferenceArray.get(r2);
    }

    protected final E lpElement(int r2) {
        return this.buffer.get(r2);
    }

    protected final void spElement(AtomicReferenceArray<E> atomicReferenceArray, int r2, E e) {
        atomicReferenceArray.lazySet(r2, e);
    }

    protected final void spElement(int r2, E e) {
        this.buffer.lazySet(r2, e);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final void soElement(AtomicReferenceArray<E> atomicReferenceArray, int r2, E e) {
        atomicReferenceArray.lazySet(r2, e);
    }

    protected final void soElement(int r2, E e) {
        this.buffer.lazySet(r2, e);
    }

    protected final void svElement(AtomicReferenceArray<E> atomicReferenceArray, int r2, E e) {
        atomicReferenceArray.set(r2, e);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final E lvElement(int r2) {
        return lvElement(this.buffer, r2);
    }
}
