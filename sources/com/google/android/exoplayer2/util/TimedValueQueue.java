package com.google.android.exoplayer2.util;

import java.util.Arrays;

/* loaded from: classes2.dex */
public final class TimedValueQueue<V> {
    private static final int INITIAL_BUFFER_SIZE = 10;
    private int first;
    private int size;
    private long[] timestamps;
    private V[] values;

    public TimedValueQueue() {
        this(10);
    }

    public TimedValueQueue(int r2) {
        this.timestamps = new long[r2];
        this.values = (V[]) newArray(r2);
    }

    public synchronized void add(long j, V v) {
        clearBufferOnTimeDiscontinuity(j);
        doubleCapacityIfFull();
        addUnchecked(j, v);
    }

    public synchronized void clear() {
        this.first = 0;
        this.size = 0;
        Arrays.fill(this.values, (Object) null);
    }

    public synchronized int size() {
        return this.size;
    }

    public synchronized V pollFirst() {
        return this.size == 0 ? null : popFirst();
    }

    public synchronized V pollFloor(long j) {
        return poll(j, true);
    }

    public synchronized V poll(long j) {
        return poll(j, false);
    }

    private V poll(long j, boolean z) {
        V v = null;
        long j2 = Long.MAX_VALUE;
        while (this.size > 0) {
            long j3 = j - this.timestamps[this.first];
            if (j3 < 0 && (z || (-j3) >= j2)) {
                break;
            }
            v = popFirst();
            j2 = j3;
        }
        return v;
    }

    private V popFirst() {
        Assertions.checkState(this.size > 0);
        V[] vArr = this.values;
        int r2 = this.first;
        V v = vArr[r2];
        vArr[r2] = null;
        this.first = (r2 + 1) % vArr.length;
        this.size--;
        return v;
    }

    private void clearBufferOnTimeDiscontinuity(long j) {
        int r0 = this.size;
        if (r0 > 0) {
            if (j <= this.timestamps[((this.first + r0) - 1) % this.values.length]) {
                clear();
            }
        }
    }

    private void doubleCapacityIfFull() {
        int length = this.values.length;
        if (this.size < length) {
            return;
        }
        int r1 = length * 2;
        long[] jArr = new long[r1];
        V[] vArr = (V[]) newArray(r1);
        int r3 = this.first;
        int r0 = length - r3;
        System.arraycopy(this.timestamps, r3, jArr, 0, r0);
        System.arraycopy(this.values, this.first, vArr, 0, r0);
        int r32 = this.first;
        if (r32 > 0) {
            System.arraycopy(this.timestamps, 0, jArr, r0, r32);
            System.arraycopy(this.values, 0, vArr, r0, this.first);
        }
        this.timestamps = jArr;
        this.values = vArr;
        this.first = 0;
    }

    private void addUnchecked(long j, V v) {
        int r0 = this.first;
        int r1 = this.size;
        V[] vArr = this.values;
        int length = (r0 + r1) % vArr.length;
        this.timestamps[length] = j;
        vArr[length] = v;
        this.size = r1 + 1;
    }

    private static <V> V[] newArray(int r0) {
        return (V[]) new Object[r0];
    }
}
