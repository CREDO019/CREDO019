package com.polidea.rxandroidble.internal.util;

import com.polidea.rxandroidble.internal.serialization.QueueReleaseInterface;
import java.util.concurrent.atomic.AtomicBoolean;
import p042rx.Emitter;
import p042rx.Observer;
import p042rx.functions.Cancellable;

/* loaded from: classes3.dex */
public class QueueReleasingEmitterWrapper<T> implements Observer<T>, Cancellable {
    private final Emitter<T> emitter;
    private final AtomicBoolean isEmitterCanceled = new AtomicBoolean(false);
    private final QueueReleaseInterface queueReleaseInterface;

    public QueueReleasingEmitterWrapper(Emitter<T> emitter, QueueReleaseInterface queueReleaseInterface) {
        this.emitter = emitter;
        this.queueReleaseInterface = queueReleaseInterface;
        emitter.setCancellation(this);
    }

    @Override // p042rx.Observer
    public void onCompleted() {
        this.queueReleaseInterface.release();
        this.emitter.onCompleted();
    }

    @Override // p042rx.Observer
    public void onError(Throwable th) {
        this.queueReleaseInterface.release();
        this.emitter.onError(th);
    }

    @Override // p042rx.Observer
    public void onNext(T t) {
        this.emitter.onNext(t);
    }

    @Override // p042rx.functions.Cancellable
    public synchronized void cancel() throws Exception {
        this.isEmitterCanceled.set(true);
    }

    public synchronized boolean isWrappedEmitterUnsubscribed() {
        return this.isEmitterCanceled.get();
    }
}
