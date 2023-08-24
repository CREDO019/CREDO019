package com.polidea.rxandroidble.internal.serialization;

import com.polidea.rxandroidble.internal.RxBleLog;
import java.util.concurrent.atomic.AtomicBoolean;

/* loaded from: classes3.dex */
class QueueSemaphore implements QueueReleaseInterface, QueueAwaitReleaseInterface {
    private final AtomicBoolean isReleased = new AtomicBoolean(false);

    @Override // com.polidea.rxandroidble.internal.serialization.QueueAwaitReleaseInterface
    public synchronized void awaitRelease() throws InterruptedException {
        while (!this.isReleased.get()) {
            try {
                wait();
            } catch (InterruptedException e) {
                if (!this.isReleased.get()) {
                    RxBleLog.m234w(e, "Queue's awaitRelease() has been interrupted abruptly while it wasn't released by the release() method.", new Object[0]);
                }
            }
        }
    }

    @Override // com.polidea.rxandroidble.internal.serialization.QueueReleaseInterface
    public synchronized void release() {
        if (this.isReleased.compareAndSet(false, true)) {
            notify();
        }
    }
}
