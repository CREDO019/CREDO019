package com.polidea.rxandroidble.internal.serialization;

import java.util.Iterator;
import java.util.concurrent.PriorityBlockingQueue;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes3.dex */
public class OperationPriorityFifoBlockingQueue {

    /* renamed from: q */
    private final PriorityBlockingQueue<FIFORunnableEntry> f1323q = new PriorityBlockingQueue<>();

    public void add(FIFORunnableEntry fIFORunnableEntry) {
        this.f1323q.add(fIFORunnableEntry);
    }

    public FIFORunnableEntry<?> take() throws InterruptedException {
        return this.f1323q.take();
    }

    public FIFORunnableEntry<?> takeNow() {
        return this.f1323q.poll();
    }

    public boolean isEmpty() {
        return this.f1323q.isEmpty();
    }

    public boolean remove(FIFORunnableEntry fIFORunnableEntry) {
        Iterator<FIFORunnableEntry> it = this.f1323q.iterator();
        while (it.hasNext()) {
            FIFORunnableEntry next = it.next();
            if (next == fIFORunnableEntry) {
                return this.f1323q.remove(next);
            }
        }
        return false;
    }
}
