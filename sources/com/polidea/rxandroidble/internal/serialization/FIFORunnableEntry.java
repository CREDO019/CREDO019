package com.polidea.rxandroidble.internal.serialization;

import com.polidea.rxandroidble.internal.operations.Operation;
import java.util.concurrent.atomic.AtomicLong;
import p042rx.Emitter;
import p042rx.Scheduler;
import p042rx.Subscription;

/* loaded from: classes3.dex */
class FIFORunnableEntry<T> implements Comparable<FIFORunnableEntry> {
    private static final AtomicLong SEQUENCE = new AtomicLong(0);
    final Emitter<T> emitter;
    final Operation<T> operation;
    private final long seqNum = SEQUENCE.getAndIncrement();

    /* JADX INFO: Access modifiers changed from: package-private */
    public FIFORunnableEntry(Operation<T> operation, Emitter<T> emitter) {
        this.operation = operation;
        this.emitter = emitter;
    }

    @Override // java.lang.Comparable
    public int compareTo(FIFORunnableEntry fIFORunnableEntry) {
        int compareTo = this.operation.compareTo(fIFORunnableEntry.operation);
        return (compareTo != 0 || fIFORunnableEntry.operation == this.operation) ? compareTo : this.seqNum < fIFORunnableEntry.seqNum ? -1 : 1;
    }

    public Subscription run(QueueSemaphore queueSemaphore, Scheduler scheduler) {
        return this.operation.run(queueSemaphore).subscribeOn(scheduler).unsubscribeOn(scheduler).subscribe(this.emitter);
    }
}
