package com.google.android.exoplayer2.mediacodec;

import java.util.NoSuchElementException;

/* loaded from: classes2.dex */
final class IntArrayQueue {
    private static final int DEFAULT_INITIAL_CAPACITY = 16;
    private int[] data;
    private int wrapAroundMask;
    private int headIndex = 0;
    private int tailIndex = -1;
    private int size = 0;

    public IntArrayQueue() {
        int[] r0 = new int[16];
        this.data = r0;
        this.wrapAroundMask = r0.length - 1;
    }

    public void add(int r3) {
        if (this.size == this.data.length) {
            doubleArraySize();
        }
        int r0 = (this.tailIndex + 1) & this.wrapAroundMask;
        this.tailIndex = r0;
        this.data[r0] = r3;
        this.size++;
    }

    public int remove() {
        int r0 = this.size;
        if (r0 == 0) {
            throw new NoSuchElementException();
        }
        int[] r1 = this.data;
        int r2 = this.headIndex;
        int r12 = r1[r2];
        this.headIndex = (r2 + 1) & this.wrapAroundMask;
        this.size = r0 - 1;
        return r12;
    }

    public int size() {
        return this.size;
    }

    public boolean isEmpty() {
        return this.size == 0;
    }

    public void clear() {
        this.headIndex = 0;
        this.tailIndex = -1;
        this.size = 0;
    }

    public int capacity() {
        return this.data.length;
    }

    private void doubleArraySize() {
        int[] r0 = this.data;
        int length = r0.length << 1;
        if (length < 0) {
            throw new IllegalStateException();
        }
        int[] r1 = new int[length];
        int length2 = r0.length;
        int r3 = this.headIndex;
        int r2 = length2 - r3;
        System.arraycopy(r0, r3, r1, 0, r2);
        System.arraycopy(this.data, 0, r1, r2, r3);
        this.headIndex = 0;
        this.tailIndex = this.size - 1;
        this.data = r1;
        this.wrapAroundMask = r1.length - 1;
    }
}
