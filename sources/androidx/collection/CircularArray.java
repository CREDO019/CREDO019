package androidx.collection;

/* loaded from: classes.dex */
public final class CircularArray<E> {
    private int mCapacityBitmask;
    private E[] mElements;
    private int mHead;
    private int mTail;

    private void doubleCapacity() {
        E[] eArr = this.mElements;
        int length = eArr.length;
        int r2 = this.mHead;
        int r3 = length - r2;
        int r4 = length << 1;
        if (r4 < 0) {
            throw new RuntimeException("Max array capacity exceeded");
        }
        Object[] objArr = new Object[r4];
        System.arraycopy(eArr, r2, objArr, 0, r3);
        System.arraycopy(this.mElements, 0, objArr, r3, this.mHead);
        this.mElements = (E[]) objArr;
        this.mHead = 0;
        this.mTail = length;
        this.mCapacityBitmask = r4 - 1;
    }

    public CircularArray() {
        this(8);
    }

    public CircularArray(int r3) {
        if (r3 < 1) {
            throw new IllegalArgumentException("capacity must be >= 1");
        }
        if (r3 > 1073741824) {
            throw new IllegalArgumentException("capacity must be <= 2^30");
        }
        r3 = Integer.bitCount(r3) != 1 ? Integer.highestOneBit(r3 - 1) << 1 : r3;
        this.mCapacityBitmask = r3 - 1;
        this.mElements = (E[]) new Object[r3];
    }

    public void addFirst(E e) {
        int r0 = (this.mHead - 1) & this.mCapacityBitmask;
        this.mHead = r0;
        this.mElements[r0] = e;
        if (r0 == this.mTail) {
            doubleCapacity();
        }
    }

    public void addLast(E e) {
        E[] eArr = this.mElements;
        int r1 = this.mTail;
        eArr[r1] = e;
        int r3 = this.mCapacityBitmask & (r1 + 1);
        this.mTail = r3;
        if (r3 == this.mHead) {
            doubleCapacity();
        }
    }

    public E popFirst() {
        int r0 = this.mHead;
        if (r0 == this.mTail) {
            throw new ArrayIndexOutOfBoundsException();
        }
        E[] eArr = this.mElements;
        E e = eArr[r0];
        eArr[r0] = null;
        this.mHead = (r0 + 1) & this.mCapacityBitmask;
        return e;
    }

    public E popLast() {
        int r0 = this.mHead;
        int r1 = this.mTail;
        if (r0 == r1) {
            throw new ArrayIndexOutOfBoundsException();
        }
        int r02 = this.mCapacityBitmask & (r1 - 1);
        E[] eArr = this.mElements;
        E e = eArr[r02];
        eArr[r02] = null;
        this.mTail = r02;
        return e;
    }

    public void clear() {
        removeFromStart(size());
    }

    public void removeFromStart(int r5) {
        if (r5 <= 0) {
            return;
        }
        if (r5 > size()) {
            throw new ArrayIndexOutOfBoundsException();
        }
        int length = this.mElements.length;
        int r1 = this.mHead;
        if (r5 < length - r1) {
            length = r1 + r5;
        }
        while (r1 < length) {
            this.mElements[r1] = null;
            r1++;
        }
        int r12 = this.mHead;
        int r0 = length - r12;
        int r52 = r5 - r0;
        this.mHead = this.mCapacityBitmask & (r12 + r0);
        if (r52 > 0) {
            for (int r02 = 0; r02 < r52; r02++) {
                this.mElements[r02] = null;
            }
            this.mHead = r52;
        }
    }

    public void removeFromEnd(int r5) {
        int r2;
        if (r5 <= 0) {
            return;
        }
        if (r5 > size()) {
            throw new ArrayIndexOutOfBoundsException();
        }
        int r1 = this.mTail;
        int r0 = r5 < r1 ? r1 - r5 : 0;
        int r12 = r0;
        while (true) {
            r2 = this.mTail;
            if (r12 >= r2) {
                break;
            }
            this.mElements[r12] = null;
            r12++;
        }
        int r02 = r2 - r0;
        int r52 = r5 - r02;
        this.mTail = r2 - r02;
        if (r52 > 0) {
            int length = this.mElements.length;
            this.mTail = length;
            int r03 = length - r52;
            for (int r53 = r03; r53 < this.mTail; r53++) {
                this.mElements[r53] = null;
            }
            this.mTail = r03;
        }
    }

    public E getFirst() {
        int r0 = this.mHead;
        if (r0 == this.mTail) {
            throw new ArrayIndexOutOfBoundsException();
        }
        return this.mElements[r0];
    }

    public E getLast() {
        int r0 = this.mHead;
        int r1 = this.mTail;
        if (r0 == r1) {
            throw new ArrayIndexOutOfBoundsException();
        }
        return this.mElements[(r1 - 1) & this.mCapacityBitmask];
    }

    public E get(int r3) {
        if (r3 < 0 || r3 >= size()) {
            throw new ArrayIndexOutOfBoundsException();
        }
        return this.mElements[this.mCapacityBitmask & (this.mHead + r3)];
    }

    public int size() {
        return (this.mTail - this.mHead) & this.mCapacityBitmask;
    }

    public boolean isEmpty() {
        return this.mHead == this.mTail;
    }
}
