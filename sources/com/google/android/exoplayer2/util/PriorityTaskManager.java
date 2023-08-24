package com.google.android.exoplayer2.util;

import java.io.IOException;
import java.util.Collections;
import java.util.PriorityQueue;

/* loaded from: classes2.dex */
public final class PriorityTaskManager {
    private final Object lock = new Object();
    private final PriorityQueue<Integer> queue = new PriorityQueue<>(10, Collections.reverseOrder());
    private int highestPriority = Integer.MIN_VALUE;

    /* loaded from: classes2.dex */
    public static class PriorityTooLowException extends IOException {
        public PriorityTooLowException(int r3, int r4) {
            super("Priority too low [priority=" + r3 + ", highest=" + r4 + "]");
        }
    }

    public void add(int r4) {
        synchronized (this.lock) {
            this.queue.add(Integer.valueOf(r4));
            this.highestPriority = Math.max(this.highestPriority, r4);
        }
    }

    public void proceed(int r3) throws InterruptedException {
        synchronized (this.lock) {
            while (this.highestPriority != r3) {
                this.lock.wait();
            }
        }
    }

    public boolean proceedNonBlocking(int r3) {
        boolean z;
        synchronized (this.lock) {
            z = this.highestPriority == r3;
        }
        return z;
    }

    public void proceedOrThrow(int r4) throws PriorityTooLowException {
        synchronized (this.lock) {
            if (this.highestPriority != r4) {
                throw new PriorityTooLowException(r4, this.highestPriority);
            }
        }
    }

    public void remove(int r3) {
        synchronized (this.lock) {
            this.queue.remove(Integer.valueOf(r3));
            this.highestPriority = this.queue.isEmpty() ? Integer.MIN_VALUE : ((Integer) Util.castNonNull(this.queue.peek())).intValue();
            this.lock.notifyAll();
        }
    }
}
