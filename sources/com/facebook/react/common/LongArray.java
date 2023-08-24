package com.facebook.react.common;

/* loaded from: classes.dex */
public class LongArray {
    private static final double INNER_ARRAY_GROWTH_FACTOR = 1.8d;
    private long[] mArray;
    private int mLength = 0;

    public static LongArray createWithInitialCapacity(int r1) {
        return new LongArray(r1);
    }

    private LongArray(int r1) {
        this.mArray = new long[r1];
    }

    public void add(long j) {
        growArrayIfNeeded();
        long[] jArr = this.mArray;
        int r1 = this.mLength;
        this.mLength = r1 + 1;
        jArr[r1] = j;
    }

    public long get(int r4) {
        if (r4 >= this.mLength) {
            throw new IndexOutOfBoundsException("" + r4 + " >= " + this.mLength);
        }
        return this.mArray[r4];
    }

    public void set(int r2, long j) {
        if (r2 >= this.mLength) {
            throw new IndexOutOfBoundsException("" + r2 + " >= " + this.mLength);
        }
        this.mArray[r2] = j;
    }

    public int size() {
        return this.mLength;
    }

    public boolean isEmpty() {
        return this.mLength == 0;
    }

    public void dropTail(int r4) {
        int r0 = this.mLength;
        if (r4 > r0) {
            throw new IndexOutOfBoundsException("Trying to drop " + r4 + " items from array of length " + this.mLength);
        }
        this.mLength = r0 - r4;
    }

    private void growArrayIfNeeded() {
        int r0 = this.mLength;
        if (r0 == this.mArray.length) {
            long[] jArr = new long[Math.max(r0 + 1, (int) (r0 * INNER_ARRAY_GROWTH_FACTOR))];
            System.arraycopy(this.mArray, 0, jArr, 0, this.mLength);
            this.mArray = jArr;
        }
    }
}
