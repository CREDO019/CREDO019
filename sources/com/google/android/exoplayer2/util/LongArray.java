package com.google.android.exoplayer2.util;

import java.util.Arrays;

/* loaded from: classes2.dex */
public final class LongArray {
    private static final int DEFAULT_INITIAL_CAPACITY = 32;
    private int size;
    private long[] values;

    public LongArray() {
        this(32);
    }

    public LongArray(int r1) {
        this.values = new long[r1];
    }

    public void add(long j) {
        int r0 = this.size;
        long[] jArr = this.values;
        if (r0 == jArr.length) {
            this.values = Arrays.copyOf(jArr, r0 * 2);
        }
        long[] jArr2 = this.values;
        int r1 = this.size;
        this.size = r1 + 1;
        jArr2[r1] = j;
    }

    public long get(int r4) {
        if (r4 < 0 || r4 >= this.size) {
            throw new IndexOutOfBoundsException("Invalid index " + r4 + ", size is " + this.size);
        }
        return this.values[r4];
    }

    public int size() {
        return this.size;
    }

    public long[] toArray() {
        return Arrays.copyOf(this.values, this.size);
    }
}
