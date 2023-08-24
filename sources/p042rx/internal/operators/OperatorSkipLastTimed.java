package p042rx.internal.operators;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.concurrent.TimeUnit;
import p042rx.Observable;
import p042rx.Scheduler;
import p042rx.Subscriber;
import p042rx.schedulers.Timestamped;

/* renamed from: rx.internal.operators.OperatorSkipLastTimed */
/* loaded from: classes6.dex */
public class OperatorSkipLastTimed<T> implements Observable.Operator<T, T> {
    final Scheduler scheduler;
    final long timeInMillis;

    @Override // p042rx.functions.Func1
    public /* bridge */ /* synthetic */ Object call(Object obj) {
        return call((Subscriber) ((Subscriber) obj));
    }

    public OperatorSkipLastTimed(long j, TimeUnit timeUnit, Scheduler scheduler) {
        this.timeInMillis = timeUnit.toMillis(j);
        this.scheduler = scheduler;
    }

    public Subscriber<? super T> call(final Subscriber<? super T> subscriber) {
        return (Subscriber<T>) new Subscriber<T>(subscriber) { // from class: rx.internal.operators.OperatorSkipLastTimed.1
            private Deque<Timestamped<T>> buffer = new ArrayDeque();

            private void emitItemsOutOfWindow(long j) {
                long j2 = j - OperatorSkipLastTimed.this.timeInMillis;
                while (!this.buffer.isEmpty()) {
                    Timestamped<T> first = this.buffer.getFirst();
                    if (first.getTimestampMillis() >= j2) {
                        return;
                    }
                    this.buffer.removeFirst();
                    subscriber.onNext(first.getValue());
                }
            }

            @Override // p042rx.Observer
            public void onNext(T t) {
                long now = OperatorSkipLastTimed.this.scheduler.now();
                emitItemsOutOfWindow(now);
                this.buffer.offerLast(new Timestamped<>(now, t));
            }

            @Override // p042rx.Observer
            public void onError(Throwable th) {
                subscriber.onError(th);
            }

            @Override // p042rx.Observer
            public void onCompleted() {
                emitItemsOutOfWindow(OperatorSkipLastTimed.this.scheduler.now());
                subscriber.onCompleted();
            }
        };
    }
}
