package p042rx.internal.util;

import java.io.PrintStream;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicIntegerArray;
import java.util.concurrent.atomic.AtomicReference;
import java.util.concurrent.atomic.AtomicReferenceArray;
import p042rx.Subscription;
import p042rx.functions.Func1;

/* renamed from: rx.internal.util.IndexedRingBuffer */
/* loaded from: classes6.dex */
public final class IndexedRingBuffer<E> implements Subscription {
    static final int SIZE;
    private final ElementSection<E> elements = new ElementSection<>();
    private final IndexSection removed = new IndexSection();
    final AtomicInteger index = new AtomicInteger();
    final AtomicInteger removedIndex = new AtomicInteger();

    @Override // p042rx.Subscription
    public boolean isUnsubscribed() {
        return false;
    }

    static {
        int r0 = PlatformDependent.isAndroid() ? 8 : 128;
        String property = System.getProperty("rx.indexed-ring-buffer.size");
        if (property != null) {
            try {
                r0 = Integer.parseInt(property);
            } catch (NumberFormatException e) {
                PrintStream printStream = System.err;
                printStream.println("Failed to set 'rx.indexed-ring-buffer.size' with value " + property + " => " + e.getMessage());
            }
        }
        SIZE = r0;
    }

    public static <T> IndexedRingBuffer<T> getInstance() {
        return new IndexedRingBuffer<>();
    }

    public void releaseToPool() {
        int r0 = this.index.get();
        int r3 = 0;
        loop0: for (ElementSection<E> elementSection = this.elements; elementSection != null; elementSection = elementSection.next.get()) {
            int r4 = 0;
            while (r4 < SIZE) {
                if (r3 >= r0) {
                    break loop0;
                }
                elementSection.array.set(r4, null);
                r4++;
                r3++;
            }
        }
        this.index.set(0);
        this.removedIndex.set(0);
    }

    @Override // p042rx.Subscription
    public void unsubscribe() {
        releaseToPool();
    }

    IndexedRingBuffer() {
    }

    public int add(E e) {
        int indexForAdd = getIndexForAdd();
        int r1 = SIZE;
        if (indexForAdd < r1) {
            this.elements.array.set(indexForAdd, e);
            return indexForAdd;
        }
        getElementSection(indexForAdd).array.set(indexForAdd % r1, e);
        return indexForAdd;
    }

    public E remove(int r4) {
        E andSet;
        int r0 = SIZE;
        if (r4 < r0) {
            andSet = this.elements.array.getAndSet(r4, null);
        } else {
            andSet = getElementSection(r4).array.getAndSet(r4 % r0, null);
        }
        pushRemovedIndex(r4);
        return andSet;
    }

    private IndexSection getIndexSection(int r3) {
        int r0 = SIZE;
        if (r3 < r0) {
            return this.removed;
        }
        int r32 = r3 / r0;
        IndexSection indexSection = this.removed;
        for (int r1 = 0; r1 < r32; r1++) {
            indexSection = indexSection.getNext();
        }
        return indexSection;
    }

    private ElementSection<E> getElementSection(int r3) {
        int r0 = SIZE;
        if (r3 < r0) {
            return this.elements;
        }
        int r32 = r3 / r0;
        ElementSection<E> elementSection = this.elements;
        for (int r1 = 0; r1 < r32; r1++) {
            elementSection = elementSection.getNext();
        }
        return elementSection;
    }

    private synchronized int getIndexForAdd() {
        int andIncrement;
        int indexFromPreviouslyRemoved = getIndexFromPreviouslyRemoved();
        if (indexFromPreviouslyRemoved >= 0) {
            int r1 = SIZE;
            if (indexFromPreviouslyRemoved < r1) {
                andIncrement = this.removed.getAndSet(indexFromPreviouslyRemoved, -1);
            } else {
                andIncrement = getIndexSection(indexFromPreviouslyRemoved).getAndSet(indexFromPreviouslyRemoved % r1, -1);
            }
            if (andIncrement == this.index.get()) {
                this.index.getAndIncrement();
            }
        } else {
            andIncrement = this.index.getAndIncrement();
        }
        return andIncrement;
    }

    private synchronized int getIndexFromPreviouslyRemoved() {
        int r0;
        int r2;
        do {
            r0 = this.removedIndex.get();
            if (r0 <= 0) {
                return -1;
            }
            r2 = r0 - 1;
        } while (!this.removedIndex.compareAndSet(r0, r2));
        return r2;
    }

    private synchronized void pushRemovedIndex(int r3) {
        int andIncrement = this.removedIndex.getAndIncrement();
        int r1 = SIZE;
        if (andIncrement < r1) {
            this.removed.set(andIncrement, r3);
        } else {
            getIndexSection(andIncrement).set(andIncrement % r1, r3);
        }
    }

    public int forEach(Func1<? super E, Boolean> func1) {
        return forEach(func1, 0);
    }

    public int forEach(Func1<? super E, Boolean> func1, int r5) {
        int forEach = forEach(func1, r5, this.index.get());
        if (r5 > 0 && forEach == this.index.get()) {
            return forEach(func1, 0, r5);
        }
        if (forEach == this.index.get()) {
            return 0;
        }
        return forEach;
    }

    private int forEach(Func1<? super E, Boolean> func1, int r7, int r8) {
        ElementSection<E> elementSection;
        int r1;
        int r0 = this.index.get();
        ElementSection<E> elementSection2 = this.elements;
        int r2 = SIZE;
        if (r7 >= r2) {
            ElementSection<E> elementSection3 = getElementSection(r7);
            r1 = r7;
            r7 %= r2;
            elementSection = elementSection3;
        } else {
            elementSection = elementSection2;
            r1 = r7;
        }
        loop0: while (elementSection != null) {
            while (r7 < SIZE) {
                if (r1 >= r0 || r1 >= r8) {
                    break loop0;
                }
                Object obj = (E) elementSection.array.get(r7);
                if (obj != null && !func1.call(obj).booleanValue()) {
                    return r1;
                }
                r7++;
                r1++;
            }
            elementSection = elementSection.next.get();
            r7 = 0;
        }
        return r1;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: rx.internal.util.IndexedRingBuffer$ElementSection */
    /* loaded from: classes6.dex */
    public static final class ElementSection<E> {
        final AtomicReferenceArray<E> array = new AtomicReferenceArray<>(IndexedRingBuffer.SIZE);
        final AtomicReference<ElementSection<E>> next = new AtomicReference<>();

        ElementSection() {
        }

        ElementSection<E> getNext() {
            if (this.next.get() != null) {
                return this.next.get();
            }
            ElementSection<E> elementSection = new ElementSection<>();
            return this.next.compareAndSet(null, elementSection) ? elementSection : this.next.get();
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: rx.internal.util.IndexedRingBuffer$IndexSection */
    /* loaded from: classes6.dex */
    public static class IndexSection {
        private final AtomicIntegerArray unsafeArray = new AtomicIntegerArray(IndexedRingBuffer.SIZE);
        private final AtomicReference<IndexSection> _next = new AtomicReference<>();

        IndexSection() {
        }

        public int getAndSet(int r2, int r3) {
            return this.unsafeArray.getAndSet(r2, r3);
        }

        public void set(int r2, int r3) {
            this.unsafeArray.set(r2, r3);
        }

        IndexSection getNext() {
            if (this._next.get() != null) {
                return this._next.get();
            }
            IndexSection indexSection = new IndexSection();
            return this._next.compareAndSet(null, indexSection) ? indexSection : this._next.get();
        }
    }
}
