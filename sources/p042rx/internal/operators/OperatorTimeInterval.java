package p042rx.internal.operators;

import p042rx.Observable;
import p042rx.Scheduler;
import p042rx.Subscriber;
import p042rx.schedulers.TimeInterval;

/* renamed from: rx.internal.operators.OperatorTimeInterval */
/* loaded from: classes6.dex */
public final class OperatorTimeInterval<T> implements Observable.Operator<TimeInterval<T>, T> {
    final Scheduler scheduler;

    @Override // p042rx.functions.Func1
    public /* bridge */ /* synthetic */ Object call(Object obj) {
        return call((Subscriber) ((Subscriber) obj));
    }

    public OperatorTimeInterval(Scheduler scheduler) {
        this.scheduler = scheduler;
    }

    public Subscriber<? super T> call(final Subscriber<? super TimeInterval<T>> subscriber) {
        return (Subscriber<T>) new Subscriber<T>(subscriber) { // from class: rx.internal.operators.OperatorTimeInterval.1
            private long lastTimestamp;

            {
                this.lastTimestamp = OperatorTimeInterval.this.scheduler.now();
            }

            @Override // p042rx.Observer
            public void onNext(T t) {
                long now = OperatorTimeInterval.this.scheduler.now();
                subscriber.onNext(new TimeInterval(now - this.lastTimestamp, t));
                this.lastTimestamp = now;
            }

            @Override // p042rx.Observer
            public void onCompleted() {
                subscriber.onCompleted();
            }

            @Override // p042rx.Observer
            public void onError(Throwable th) {
                subscriber.onError(th);
            }
        };
    }
}
