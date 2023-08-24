package com.polidea.rxandroidble.internal.serialization;

import bleshadow.javax.inject.Inject;
import bleshadow.javax.inject.Named;
import com.polidea.rxandroidble.internal.RxBleLog;
import com.polidea.rxandroidble.internal.operations.Operation;
import com.polidea.rxandroidble.internal.util.OperationLogger;
import p042rx.Emitter;
import p042rx.Observable;
import p042rx.Scheduler;
import p042rx.functions.Action1;
import p042rx.functions.Cancellable;

/* loaded from: classes3.dex */
public class ClientOperationQueueImpl implements ClientOperationQueue {
    private OperationPriorityFifoBlockingQueue queue = new OperationPriorityFifoBlockingQueue();

    @Inject
    public ClientOperationQueueImpl(@Named("bluetooth_interaction") final Scheduler scheduler) {
        new Thread(new Runnable() { // from class: com.polidea.rxandroidble.internal.serialization.ClientOperationQueueImpl.1
            @Override // java.lang.Runnable
            public void run() {
                while (true) {
                    try {
                        FIFORunnableEntry<?> take = ClientOperationQueueImpl.this.queue.take();
                        Operation<?> operation = take.operation;
                        long currentTimeMillis = System.currentTimeMillis();
                        OperationLogger.logOperationStarted(operation);
                        QueueSemaphore queueSemaphore = new QueueSemaphore();
                        take.emitter.setSubscription(take.run(queueSemaphore, scheduler));
                        queueSemaphore.awaitRelease();
                        OperationLogger.logOperationFinished(operation, currentTimeMillis, System.currentTimeMillis());
                    } catch (InterruptedException e) {
                        RxBleLog.m240e(e, "Error while processing client operation queue", new Object[0]);
                    }
                }
            }
        }).start();
    }

    @Override // com.polidea.rxandroidble.internal.serialization.ClientOperationQueue
    public <T> Observable<T> queue(final Operation<T> operation) {
        return Observable.create(new Action1<Emitter<T>>() { // from class: com.polidea.rxandroidble.internal.serialization.ClientOperationQueueImpl.2
            @Override // p042rx.functions.Action1
            public /* bridge */ /* synthetic */ void call(Object obj) {
                call((Emitter) ((Emitter) obj));
            }

            public void call(Emitter<T> emitter) {
                final FIFORunnableEntry fIFORunnableEntry = new FIFORunnableEntry(operation, emitter);
                emitter.setCancellation(new Cancellable() { // from class: com.polidea.rxandroidble.internal.serialization.ClientOperationQueueImpl.2.1
                    @Override // p042rx.functions.Cancellable
                    public void cancel() throws Exception {
                        if (ClientOperationQueueImpl.this.queue.remove(fIFORunnableEntry)) {
                            OperationLogger.logOperationRemoved(operation);
                        }
                    }
                });
                OperationLogger.logOperationQueued(operation);
                ClientOperationQueueImpl.this.queue.add(fIFORunnableEntry);
            }
        }, Emitter.BackpressureMode.NONE);
    }
}
