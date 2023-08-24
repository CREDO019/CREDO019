package com.polidea.rxandroidble.internal.serialization;

import bleshadow.javax.inject.Inject;
import bleshadow.javax.inject.Named;
import com.polidea.rxandroidble.exceptions.BleDisconnectedException;
import com.polidea.rxandroidble.exceptions.BleException;
import com.polidea.rxandroidble.internal.RxBleLog;
import com.polidea.rxandroidble.internal.connection.ConnectionScope;
import com.polidea.rxandroidble.internal.connection.ConnectionSubscriptionWatcher;
import com.polidea.rxandroidble.internal.connection.DisconnectionRouterOutput;
import com.polidea.rxandroidble.internal.operations.Operation;
import com.polidea.rxandroidble.internal.util.OperationLogger;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;
import p042rx.Emitter;
import p042rx.Observable;
import p042rx.Scheduler;
import p042rx.Subscription;
import p042rx.functions.Action1;
import p042rx.functions.Cancellable;

@ConnectionScope
/* loaded from: classes3.dex */
public class ConnectionOperationQueueImpl implements ConnectionOperationQueue, ConnectionSubscriptionWatcher {
    private final String deviceMacAddress;
    private final DisconnectionRouterOutput disconnectionRouterOutput;
    private Subscription disconnectionThrowableSubscription;
    private final Future<?> runnableFuture;
    private final OperationPriorityFifoBlockingQueue queue = new OperationPriorityFifoBlockingQueue();
    private volatile boolean shouldRun = true;
    private BleException disconnectionException = null;

    /* JADX INFO: Access modifiers changed from: package-private */
    @Inject
    public ConnectionOperationQueueImpl(@Named("mac-address") String str, DisconnectionRouterOutput disconnectionRouterOutput, @Named("executor_connection_queue") ExecutorService executorService, @Named("bluetooth_interaction") final Scheduler scheduler) {
        this.deviceMacAddress = str;
        this.disconnectionRouterOutput = disconnectionRouterOutput;
        this.runnableFuture = executorService.submit(new Runnable() { // from class: com.polidea.rxandroidble.internal.serialization.ConnectionOperationQueueImpl.1
            @Override // java.lang.Runnable
            public void run() {
                while (ConnectionOperationQueueImpl.this.shouldRun) {
                    try {
                        FIFORunnableEntry<?> take = ConnectionOperationQueueImpl.this.queue.take();
                        Operation<?> operation = take.operation;
                        long currentTimeMillis = System.currentTimeMillis();
                        OperationLogger.logOperationStarted(operation);
                        QueueSemaphore queueSemaphore = new QueueSemaphore();
                        take.emitter.setSubscription(take.run(queueSemaphore, scheduler));
                        queueSemaphore.awaitRelease();
                        OperationLogger.logOperationFinished(operation, currentTimeMillis, System.currentTimeMillis());
                    } catch (InterruptedException e) {
                        synchronized (ConnectionOperationQueueImpl.this) {
                            if (!ConnectionOperationQueueImpl.this.shouldRun) {
                                break;
                            }
                            RxBleLog.m240e(e, "Error while processing connection operation queue", new Object[0]);
                        }
                    }
                }
                ConnectionOperationQueueImpl.this.flushQueue();
                RxBleLog.m243d("Terminated.", new Object[0]);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public synchronized void flushQueue() {
        while (!this.queue.isEmpty()) {
            this.queue.takeNow().emitter.onError(this.disconnectionException);
        }
    }

    @Override // com.polidea.rxandroidble.internal.serialization.ClientOperationQueue
    public synchronized <T> Observable<T> queue(final Operation<T> operation) {
        if (!this.shouldRun) {
            return Observable.error(this.disconnectionException);
        }
        return Observable.create(new Action1<Emitter<T>>() { // from class: com.polidea.rxandroidble.internal.serialization.ConnectionOperationQueueImpl.2
            @Override // p042rx.functions.Action1
            public /* bridge */ /* synthetic */ void call(Object obj) {
                call((Emitter) ((Emitter) obj));
            }

            public void call(Emitter<T> emitter) {
                final FIFORunnableEntry fIFORunnableEntry = new FIFORunnableEntry(operation, emitter);
                emitter.setCancellation(new Cancellable() { // from class: com.polidea.rxandroidble.internal.serialization.ConnectionOperationQueueImpl.2.1
                    @Override // p042rx.functions.Cancellable
                    public void cancel() throws Exception {
                        if (ConnectionOperationQueueImpl.this.queue.remove(fIFORunnableEntry)) {
                            OperationLogger.logOperationRemoved(operation);
                        }
                    }
                });
                OperationLogger.logOperationQueued(operation);
                ConnectionOperationQueueImpl.this.queue.add(fIFORunnableEntry);
            }
        }, Emitter.BackpressureMode.NONE);
    }

    @Override // com.polidea.rxandroidble.internal.serialization.ConnectionOperationQueue
    public synchronized void terminate(BleException bleException) {
        if (this.disconnectionException != null) {
            return;
        }
        RxBleLog.m239i("Connection operations queue to be terminated (" + this.deviceMacAddress + ')', new Object[0]);
        this.shouldRun = false;
        this.disconnectionException = bleException;
        this.runnableFuture.cancel(true);
    }

    @Override // com.polidea.rxandroidble.internal.connection.ConnectionSubscriptionWatcher
    public void onConnectionSubscribed() {
        this.disconnectionThrowableSubscription = this.disconnectionRouterOutput.asValueOnlyObservable().subscribe(new Action1<BleException>() { // from class: com.polidea.rxandroidble.internal.serialization.ConnectionOperationQueueImpl.3
            @Override // p042rx.functions.Action1
            public void call(BleException bleException) {
                ConnectionOperationQueueImpl.this.terminate(bleException);
            }
        });
    }

    @Override // com.polidea.rxandroidble.internal.connection.ConnectionSubscriptionWatcher
    public void onConnectionUnsubscribed() {
        this.disconnectionThrowableSubscription.unsubscribe();
        this.disconnectionThrowableSubscription = null;
        terminate(new BleDisconnectedException(this.deviceMacAddress, -1));
    }
}
