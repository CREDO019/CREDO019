package androidx.collection;

/* loaded from: classes.dex */
public final class CircularIntArray {
    private int mCapacityBitmask;
    private int[] mElements;
    private int mHead;
    private int mTail;

    private void doubleCapacity() {
        int[] r0 = this.mElements;
        int length = r0.length;
        int r2 = this.mHead;
        int r3 = length - r2;
        int r4 = length << 1;
        if (r4 < 0) {
            throw new RuntimeException("Max array capacity exceeded");
        }
        int[] r5 = new int[r4];
        System.arraycopy(r0, r2, r5, 0, r3);
        System.arraycopy(this.mElements, 0, r5, r3, this.mHead);
        this.mElements = r5;
        this.mHead = 0;
        this.mTail = length;
        this.mCapacityBitmask = r4 - 1;
    }

    public CircularIntArray() {
        this(8);
    }

    public CircularIntArray(int r3) {
        if (r3 < 1) {
            throw new IllegalArgumentException("capacity must be >= 1");
        }
        if (r3 > 1073741824) {
            throw new IllegalArgumentException("capacity must be <= 2^30");
        }
        r3 = Integer.bitCount(r3) != 1 ? Integer.highestOneBit(r3 - 1) << 1 : r3;
        this.mCapacityBitmask = r3 - 1;
        this.mElements = new int[r3];
    }

    public void addFirst(int r3) {
        int r0 = (this.mHead - 1) & this.mCapacityBitmask;
        this.mHead = r0;
        this.mElements[r0] = r3;
        if (r0 == this.mTail) {
            doubleCapacity();
        }
    }

    public void addLast(int r3) {
        int[] r0 = this.mElements;
        int r1 = this.mTail;
        r0[r1] = r3;
        int r32 = this.mCapacityBitmask & (r1 + 1);
        this.mTail = r32;
        if (r32 == this.mHead) {
            doubleCapacity();
        }
    }

    public int popFirst() {
        int r0 = this.mHead;
        if (r0 == this.mTail) {
            throw new ArrayIndexOutOfBoundsException();
        }
        int r1 = this.mElements[r0];
        this.mHead = (r0 + 1) & this.mCapacityBitmask;
        return r1;
    }

    public int popLast() {
        int r0 = this.mHead;
        int r1 = this.mTail;
        if (r0 == r1) {
            throw new ArrayIndexOutOfBoundsException();
        }
        int r02 = this.mCapacityBitmask & (r1 - 1);
        int r12 = this.mElements[r02];
        this.mTail = r02;
        return r12;
    }

    public void clear() {
        this.mTail = this.mHead;
    }

    public void removeFromStart(int r2) {
        if (r2 <= 0) {
            return;
        }
        if (r2 > size()) {
            throw new ArrayIndexOutOfBoundsException();
        }
        this.mHead = this.mCapacityBitmask & (this.mHead + r2);
    }

    public void removeFromEnd(int r2) {
        if (r2 <= 0) {
            return;
        }
        if (r2 > size()) {
            throw new ArrayIndexOutOfBoundsException();
        }
        this.mTail = this.mCapacityBitmask & (this.mTail - r2);
    }

    public int getFirst() {
        int r0 = this.mHead;
        if (r0 == this.mTail) {
            throw new ArrayIndexOutOfBoundsException();
        }
        return this.mElements[r0];
    }

    public int getLast() {
        int r0 = this.mHead;
        int r1 = this.mTail;
        if (r0 == r1) {
            throw new ArrayIndexOutOfBoundsException();
        }
        return this.mElements[(r1 - 1) & this.mCapacityBitmask];
    }

    public int get(int r3) {
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
