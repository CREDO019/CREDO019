package com.google.common.collect;

import com.google.common.base.Preconditions;
import com.google.common.math.IntMath;
import java.util.AbstractQueue;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.Queue;
import javax.annotation.CheckForNull;

@ElementTypesAreNonnullByDefault
/* loaded from: classes3.dex */
public final class MinMaxPriorityQueue<E> extends AbstractQueue<E> {
    private static final int DEFAULT_CAPACITY = 11;
    private static final int EVEN_POWERS_OF_TWO = 1431655765;
    private static final int ODD_POWERS_OF_TWO = -1431655766;
    private final MinMaxPriorityQueue<E>.Heap maxHeap;
    final int maximumSize;
    private final MinMaxPriorityQueue<E>.Heap minHeap;
    private int modCount;
    private Object[] queue;
    private int size;

    public static <E extends Comparable<E>> MinMaxPriorityQueue<E> create() {
        return new Builder(Ordering.natural()).create();
    }

    public static <E extends Comparable<E>> MinMaxPriorityQueue<E> create(Iterable<? extends E> iterable) {
        return new Builder(Ordering.natural()).create(iterable);
    }

    public static <B> Builder<B> orderedBy(Comparator<B> comparator) {
        return new Builder<>(comparator);
    }

    public static Builder<Comparable> expectedSize(int r3) {
        return new Builder(Ordering.natural()).expectedSize(r3);
    }

    public static Builder<Comparable> maximumSize(int r3) {
        return new Builder(Ordering.natural()).maximumSize(r3);
    }

    /* loaded from: classes3.dex */
    public static final class Builder<B> {
        private static final int UNSET_EXPECTED_SIZE = -1;
        private final Comparator<B> comparator;
        private int expectedSize;
        private int maximumSize;

        private Builder(Comparator<B> comparator) {
            this.expectedSize = -1;
            this.maximumSize = Integer.MAX_VALUE;
            this.comparator = (Comparator) Preconditions.checkNotNull(comparator);
        }

        public Builder<B> expectedSize(int r2) {
            Preconditions.checkArgument(r2 >= 0);
            this.expectedSize = r2;
            return this;
        }

        public Builder<B> maximumSize(int r2) {
            Preconditions.checkArgument(r2 > 0);
            this.maximumSize = r2;
            return this;
        }

        public <T extends B> MinMaxPriorityQueue<T> create() {
            return create(Collections.emptySet());
        }

        public <T extends B> MinMaxPriorityQueue<T> create(Iterable<? extends T> iterable) {
            MinMaxPriorityQueue<T> minMaxPriorityQueue = new MinMaxPriorityQueue<>(this, MinMaxPriorityQueue.initialQueueSize(this.expectedSize, this.maximumSize, iterable));
            for (T t : iterable) {
                minMaxPriorityQueue.offer(t);
            }
            return minMaxPriorityQueue;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public <T extends B> Ordering<T> ordering() {
            return Ordering.from(this.comparator);
        }
    }

    private MinMaxPriorityQueue(Builder<? super E> builder, int r5) {
        Ordering ordering = builder.ordering();
        MinMaxPriorityQueue<E>.Heap heap = new Heap(ordering);
        this.minHeap = heap;
        MinMaxPriorityQueue<E>.Heap heap2 = new Heap(ordering.reverse());
        this.maxHeap = heap2;
        heap.otherHeap = heap2;
        heap2.otherHeap = heap;
        this.maximumSize = ((Builder) builder).maximumSize;
        this.queue = new Object[r5];
    }

    @Override // java.util.AbstractCollection, java.util.Collection
    public int size() {
        return this.size;
    }

    @Override // java.util.AbstractQueue, java.util.AbstractCollection, java.util.Collection, java.util.Queue
    public boolean add(E e) {
        offer(e);
        return true;
    }

    @Override // java.util.AbstractQueue, java.util.AbstractCollection, java.util.Collection
    public boolean addAll(Collection<? extends E> collection) {
        boolean z = false;
        for (E e : collection) {
            offer(e);
            z = true;
        }
        return z;
    }

    @Override // java.util.Queue
    public boolean offer(E e) {
        Preconditions.checkNotNull(e);
        this.modCount++;
        int r0 = this.size;
        this.size = r0 + 1;
        growIfNeeded();
        heapForIndex(r0).bubbleUp(r0, e);
        return this.size <= this.maximumSize || pollLast() != e;
    }

    @Override // java.util.Queue
    @CheckForNull
    public E poll() {
        if (isEmpty()) {
            return null;
        }
        return removeAndGet(0);
    }

    E elementData(int r2) {
        E e = (E) this.queue[r2];
        Objects.requireNonNull(e);
        return e;
    }

    @Override // java.util.Queue
    @CheckForNull
    public E peek() {
        if (isEmpty()) {
            return null;
        }
        return elementData(0);
    }

    private int getMaxElementIndex() {
        int r0 = this.size;
        if (r0 != 1) {
            return (r0 == 2 || this.maxHeap.compareElements(1, 2) <= 0) ? 1 : 2;
        }
        return 0;
    }

    @CheckForNull
    public E pollFirst() {
        return poll();
    }

    public E removeFirst() {
        return remove();
    }

    @CheckForNull
    public E peekFirst() {
        return peek();
    }

    @CheckForNull
    public E pollLast() {
        if (isEmpty()) {
            return null;
        }
        return removeAndGet(getMaxElementIndex());
    }

    public E removeLast() {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }
        return removeAndGet(getMaxElementIndex());
    }

    @CheckForNull
    public E peekLast() {
        if (isEmpty()) {
            return null;
        }
        return elementData(getMaxElementIndex());
    }

    @CheckForNull
    MoveDesc<E> removeAt(int r7) {
        Preconditions.checkPositionIndex(r7, this.size);
        this.modCount++;
        int r0 = this.size - 1;
        this.size = r0;
        if (r0 == r7) {
            this.queue[r0] = null;
            return null;
        }
        E elementData = elementData(r0);
        int swapWithConceptuallyLastElement = heapForIndex(this.size).swapWithConceptuallyLastElement(elementData);
        if (swapWithConceptuallyLastElement == r7) {
            this.queue[this.size] = null;
            return null;
        }
        E elementData2 = elementData(this.size);
        this.queue[this.size] = null;
        MoveDesc<E> fillHole = fillHole(r7, elementData2);
        if (swapWithConceptuallyLastElement < r7) {
            if (fillHole == null) {
                return new MoveDesc<>(elementData, elementData2);
            }
            return new MoveDesc<>(elementData, fillHole.replaced);
        }
        return fillHole;
    }

    @CheckForNull
    private MoveDesc<E> fillHole(int r4, E e) {
        MinMaxPriorityQueue<E>.Heap heapForIndex = heapForIndex(r4);
        int fillHoleAt = heapForIndex.fillHoleAt(r4);
        int bubbleUpAlternatingLevels = heapForIndex.bubbleUpAlternatingLevels(fillHoleAt, e);
        if (bubbleUpAlternatingLevels == fillHoleAt) {
            return heapForIndex.tryCrossOverAndBubbleUp(r4, fillHoleAt, e);
        }
        if (bubbleUpAlternatingLevels < r4) {
            return new MoveDesc<>(e, elementData(r4));
        }
        return null;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes3.dex */
    public static class MoveDesc<E> {
        final E replaced;
        final E toTrickle;

        MoveDesc(E e, E e2) {
            this.toTrickle = e;
            this.replaced = e2;
        }
    }

    private E removeAndGet(int r2) {
        E elementData = elementData(r2);
        removeAt(r2);
        return elementData;
    }

    private MinMaxPriorityQueue<E>.Heap heapForIndex(int r1) {
        return isEvenLevel(r1) ? this.minHeap : this.maxHeap;
    }

    static boolean isEvenLevel(int r4) {
        int r42 = ~(~(r4 + 1));
        Preconditions.checkState(r42 > 0, "negative index");
        return (EVEN_POWERS_OF_TWO & r42) > (r42 & ODD_POWERS_OF_TWO);
    }

    boolean isIntact() {
        for (int r1 = 1; r1 < this.size; r1++) {
            if (!heapForIndex(r1).verifyIndex(r1)) {
                return false;
            }
        }
        return true;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes3.dex */
    public class Heap {
        final Ordering<E> ordering;
        MinMaxPriorityQueue<E>.Heap otherHeap;

        private int getLeftChildIndex(int r1) {
            return (r1 * 2) + 1;
        }

        private int getRightChildIndex(int r1) {
            return (r1 * 2) + 2;
        }

        Heap(Ordering<E> ordering) {
            this.ordering = ordering;
        }

        int compareElements(int r3, int r4) {
            return ((Ordering<E>) this.ordering).compare(MinMaxPriorityQueue.this.elementData(r3), MinMaxPriorityQueue.this.elementData(r4));
        }

        @CheckForNull
        MoveDesc<E> tryCrossOverAndBubbleUp(int r4, int r5, E e) {
            Object elementData;
            int crossOver = crossOver(r5, e);
            if (crossOver == r5) {
                return null;
            }
            if (crossOver < r4) {
                elementData = MinMaxPriorityQueue.this.elementData(r4);
            } else {
                elementData = MinMaxPriorityQueue.this.elementData(getParentIndex(r4));
            }
            if (this.otherHeap.bubbleUpAlternatingLevels(crossOver, e) < r4) {
                return new MoveDesc<>(e, elementData);
            }
            return null;
        }

        void bubbleUp(int r2, E e) {
            Heap heap;
            int crossOverUp = crossOverUp(r2, e);
            if (crossOverUp == r2) {
                crossOverUp = r2;
                heap = this;
            } else {
                heap = this.otherHeap;
            }
            heap.bubbleUpAlternatingLevels(crossOverUp, e);
        }

        int bubbleUpAlternatingLevels(int r4, E e) {
            while (r4 > 2) {
                int grandparentIndex = getGrandparentIndex(r4);
                Object elementData = MinMaxPriorityQueue.this.elementData(grandparentIndex);
                if (((Ordering<E>) this.ordering).compare(elementData, e) <= 0) {
                    break;
                }
                MinMaxPriorityQueue.this.queue[r4] = elementData;
                r4 = grandparentIndex;
            }
            MinMaxPriorityQueue.this.queue[r4] = e;
            return r4;
        }

        int findMin(int r3, int r4) {
            if (r3 >= MinMaxPriorityQueue.this.size) {
                return -1;
            }
            Preconditions.checkState(r3 > 0);
            int min = Math.min(r3, MinMaxPriorityQueue.this.size - r4) + r4;
            for (int r42 = r3 + 1; r42 < min; r42++) {
                if (compareElements(r42, r3) < 0) {
                    r3 = r42;
                }
            }
            return r3;
        }

        int findMinChild(int r2) {
            return findMin(getLeftChildIndex(r2), 2);
        }

        int findMinGrandChild(int r2) {
            int leftChildIndex = getLeftChildIndex(r2);
            if (leftChildIndex < 0) {
                return -1;
            }
            return findMin(getLeftChildIndex(leftChildIndex), 4);
        }

        int crossOverUp(int r6, E e) {
            int rightChildIndex;
            if (r6 == 0) {
                MinMaxPriorityQueue.this.queue[0] = e;
                return 0;
            }
            int parentIndex = getParentIndex(r6);
            Object elementData = MinMaxPriorityQueue.this.elementData(parentIndex);
            if (parentIndex != 0 && (rightChildIndex = getRightChildIndex(getParentIndex(parentIndex))) != parentIndex && getLeftChildIndex(rightChildIndex) >= MinMaxPriorityQueue.this.size) {
                Object elementData2 = MinMaxPriorityQueue.this.elementData(rightChildIndex);
                if (((Ordering<E>) this.ordering).compare(elementData2, elementData) < 0) {
                    parentIndex = rightChildIndex;
                    elementData = elementData2;
                }
            }
            if (((Ordering<E>) this.ordering).compare(elementData, e) < 0) {
                MinMaxPriorityQueue.this.queue[r6] = elementData;
                MinMaxPriorityQueue.this.queue[parentIndex] = e;
                return parentIndex;
            }
            MinMaxPriorityQueue.this.queue[r6] = e;
            return r6;
        }

        int swapWithConceptuallyLastElement(E e) {
            int rightChildIndex;
            int parentIndex = getParentIndex(MinMaxPriorityQueue.this.size);
            if (parentIndex != 0 && (rightChildIndex = getRightChildIndex(getParentIndex(parentIndex))) != parentIndex && getLeftChildIndex(rightChildIndex) >= MinMaxPriorityQueue.this.size) {
                Object elementData = MinMaxPriorityQueue.this.elementData(rightChildIndex);
                if (((Ordering<E>) this.ordering).compare(elementData, e) < 0) {
                    MinMaxPriorityQueue.this.queue[rightChildIndex] = e;
                    MinMaxPriorityQueue.this.queue[MinMaxPriorityQueue.this.size] = elementData;
                    return rightChildIndex;
                }
            }
            return MinMaxPriorityQueue.this.size;
        }

        int crossOver(int r4, E e) {
            int findMinChild = findMinChild(r4);
            if (findMinChild > 0 && ((Ordering<E>) this.ordering).compare(MinMaxPriorityQueue.this.elementData(findMinChild), e) < 0) {
                MinMaxPriorityQueue.this.queue[r4] = MinMaxPriorityQueue.this.elementData(findMinChild);
                MinMaxPriorityQueue.this.queue[findMinChild] = e;
                return findMinChild;
            }
            return crossOverUp(r4, e);
        }

        int fillHoleAt(int r4) {
            while (true) {
                int findMinGrandChild = findMinGrandChild(r4);
                if (findMinGrandChild <= 0) {
                    return r4;
                }
                MinMaxPriorityQueue.this.queue[r4] = MinMaxPriorityQueue.this.elementData(findMinGrandChild);
                r4 = findMinGrandChild;
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public boolean verifyIndex(int r4) {
            if (getLeftChildIndex(r4) >= MinMaxPriorityQueue.this.size || compareElements(r4, getLeftChildIndex(r4)) <= 0) {
                if (getRightChildIndex(r4) >= MinMaxPriorityQueue.this.size || compareElements(r4, getRightChildIndex(r4)) <= 0) {
                    if (r4 <= 0 || compareElements(r4, getParentIndex(r4)) <= 0) {
                        return r4 <= 2 || compareElements(getGrandparentIndex(r4), r4) <= 0;
                    }
                    return false;
                }
                return false;
            }
            return false;
        }

        private int getParentIndex(int r1) {
            return (r1 - 1) / 2;
        }

        private int getGrandparentIndex(int r1) {
            return getParentIndex(getParentIndex(r1));
        }
    }

    /* loaded from: classes3.dex */
    private class QueueIterator implements Iterator<E> {
        private boolean canRemove;
        private int cursor;
        private int expectedModCount;
        @CheckForNull
        private Queue<E> forgetMeNot;
        @CheckForNull
        private E lastFromForgetMeNot;
        private int nextCursor;
        @CheckForNull
        private List<E> skipMe;

        private QueueIterator() {
            this.cursor = -1;
            this.nextCursor = -1;
            this.expectedModCount = MinMaxPriorityQueue.this.modCount;
        }

        @Override // java.util.Iterator
        public boolean hasNext() {
            checkModCount();
            nextNotInSkipMe(this.cursor + 1);
            if (this.nextCursor >= MinMaxPriorityQueue.this.size()) {
                Queue<E> queue = this.forgetMeNot;
                return (queue == null || queue.isEmpty()) ? false : true;
            }
            return true;
        }

        @Override // java.util.Iterator
        public E next() {
            checkModCount();
            nextNotInSkipMe(this.cursor + 1);
            if (this.nextCursor < MinMaxPriorityQueue.this.size()) {
                int r0 = this.nextCursor;
                this.cursor = r0;
                this.canRemove = true;
                return (E) MinMaxPriorityQueue.this.elementData(r0);
            }
            if (this.forgetMeNot != null) {
                this.cursor = MinMaxPriorityQueue.this.size();
                E poll = this.forgetMeNot.poll();
                this.lastFromForgetMeNot = poll;
                if (poll != null) {
                    this.canRemove = true;
                    return poll;
                }
            }
            throw new NoSuchElementException("iterator moved past last element in queue.");
        }

        @Override // java.util.Iterator
        public void remove() {
            CollectPreconditions.checkRemove(this.canRemove);
            checkModCount();
            this.canRemove = false;
            this.expectedModCount++;
            if (this.cursor < MinMaxPriorityQueue.this.size()) {
                MoveDesc<E> removeAt = MinMaxPriorityQueue.this.removeAt(this.cursor);
                if (removeAt != null) {
                    if (this.forgetMeNot == null || this.skipMe == null) {
                        this.forgetMeNot = new ArrayDeque();
                        this.skipMe = new ArrayList(3);
                    }
                    if (!foundAndRemovedExactReference(this.skipMe, removeAt.toTrickle)) {
                        this.forgetMeNot.add(removeAt.toTrickle);
                    }
                    if (!foundAndRemovedExactReference(this.forgetMeNot, removeAt.replaced)) {
                        this.skipMe.add(removeAt.replaced);
                    }
                }
                this.cursor--;
                this.nextCursor--;
                return;
            }
            E e = this.lastFromForgetMeNot;
            Objects.requireNonNull(e);
            Preconditions.checkState(removeExact(e));
            this.lastFromForgetMeNot = null;
        }

        private boolean foundAndRemovedExactReference(Iterable<E> iterable, E e) {
            Iterator<E> it = iterable.iterator();
            while (it.hasNext()) {
                if (it.next() == e) {
                    it.remove();
                    return true;
                }
            }
            return false;
        }

        private boolean removeExact(Object obj) {
            for (int r1 = 0; r1 < MinMaxPriorityQueue.this.size; r1++) {
                if (MinMaxPriorityQueue.this.queue[r1] == obj) {
                    MinMaxPriorityQueue.this.removeAt(r1);
                    return true;
                }
            }
            return false;
        }

        private void checkModCount() {
            if (MinMaxPriorityQueue.this.modCount != this.expectedModCount) {
                throw new ConcurrentModificationException();
            }
        }

        /* JADX WARN: Multi-variable type inference failed */
        private void nextNotInSkipMe(int r3) {
            if (this.nextCursor < r3) {
                if (this.skipMe != null) {
                    while (r3 < MinMaxPriorityQueue.this.size() && foundAndRemovedExactReference(this.skipMe, MinMaxPriorityQueue.this.elementData(r3))) {
                        r3++;
                    }
                }
                this.nextCursor = r3;
            }
        }
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.lang.Iterable
    public Iterator<E> iterator() {
        return new QueueIterator();
    }

    @Override // java.util.AbstractQueue, java.util.AbstractCollection, java.util.Collection
    public void clear() {
        for (int r1 = 0; r1 < this.size; r1++) {
            this.queue[r1] = null;
        }
        this.size = 0;
    }

    @Override // java.util.AbstractCollection, java.util.Collection
    public Object[] toArray() {
        int r0 = this.size;
        Object[] objArr = new Object[r0];
        System.arraycopy(this.queue, 0, objArr, 0, r0);
        return objArr;
    }

    public Comparator<? super E> comparator() {
        return this.minHeap.ordering;
    }

    int capacity() {
        return this.queue.length;
    }

    static int initialQueueSize(int r1, int r2, Iterable<?> iterable) {
        if (r1 == -1) {
            r1 = 11;
        }
        if (iterable instanceof Collection) {
            r1 = Math.max(r1, ((Collection) iterable).size());
        }
        return capAtMaximumSize(r1, r2);
    }

    private void growIfNeeded() {
        if (this.size > this.queue.length) {
            Object[] objArr = new Object[calculateNewCapacity()];
            Object[] objArr2 = this.queue;
            System.arraycopy(objArr2, 0, objArr, 0, objArr2.length);
            this.queue = objArr;
        }
    }

    private int calculateNewCapacity() {
        int length = this.queue.length;
        return capAtMaximumSize(length < 64 ? (length + 1) * 2 : IntMath.checkedMultiply(length / 2, 3), this.maximumSize);
    }

    private static int capAtMaximumSize(int r0, int r1) {
        return Math.min(r0 - 1, r1) + 1;
    }
}
