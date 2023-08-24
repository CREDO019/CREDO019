package com.polidea.rxandroidble.internal;

import android.os.DeadObjectException;
import com.polidea.rxandroidble.exceptions.BleException;
import com.polidea.rxandroidble.internal.operations.Operation;
import com.polidea.rxandroidble.internal.serialization.QueueReleaseInterface;
import p042rx.Emitter;
import p042rx.Observable;
import p042rx.functions.Action1;

/* loaded from: classes3.dex */
public abstract class QueueOperation<T> implements Operation<T> {
    protected abstract void protectedRun(Emitter<T> emitter, QueueReleaseInterface queueReleaseInterface) throws Throwable;

    protected abstract BleException provideException(DeadObjectException deadObjectException);

    @Override // java.lang.Comparable
    public /* bridge */ /* synthetic */ int compareTo(Operation<?> operation) {
        return compareTo2((Operation) operation);
    }

    @Override // com.polidea.rxandroidble.internal.operations.Operation
    public final Observable<T> run(final QueueReleaseInterface queueReleaseInterface) {
        return Observable.create(new Action1<Emitter<T>>() { // from class: com.polidea.rxandroidble.internal.QueueOperation.1
            @Override // p042rx.functions.Action1
            public /* bridge */ /* synthetic */ void call(Object obj) {
                call((Emitter) ((Emitter) obj));
            }

            public void call(Emitter<T> emitter) {
                try {
                    QueueOperation.this.protectedRun(emitter, queueReleaseInterface);
                } catch (DeadObjectException e) {
                    emitter.onError(QueueOperation.this.provideException(e));
                } catch (Throwable th) {
                    emitter.onError(th);
                }
            }
        }, Emitter.BackpressureMode.NONE);
    }

    @Override // com.polidea.rxandroidble.internal.operations.Operation
    public Priority definedPriority() {
        return Priority.NORMAL;
    }

    /* renamed from: compareTo  reason: avoid collision after fix types in other method */
    public int compareTo2(Operation operation) {
        return operation.definedPriority().priority - definedPriority().priority;
    }
}
