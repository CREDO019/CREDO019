package p042rx.internal.operators;

import java.util.concurrent.TimeUnit;
import p042rx.Observable;
import p042rx.Scheduler;
import p042rx.Subscriber;
import p042rx.exceptions.Exceptions;
import p042rx.functions.Action0;

/* renamed from: rx.internal.operators.OnSubscribeTimerOnce */
/* loaded from: classes6.dex */
public final class OnSubscribeTimerOnce implements Observable.OnSubscribe<Long> {
    final Scheduler scheduler;
    final long time;
    final TimeUnit unit;

    public OnSubscribeTimerOnce(long j, TimeUnit timeUnit, Scheduler scheduler) {
        this.time = j;
        this.unit = timeUnit;
        this.scheduler = scheduler;
    }

    @Override // p042rx.functions.Action1
    public void call(final Subscriber<? super Long> subscriber) {
        Scheduler.Worker createWorker = this.scheduler.createWorker();
        subscriber.add(createWorker);
        createWorker.schedule(new Action0() { // from class: rx.internal.operators.OnSubscribeTimerOnce.1
            @Override // p042rx.functions.Action0
            public void call() {
                try {
                    subscriber.onNext(0L);
                    subscriber.onCompleted();
                } catch (Throwable th) {
                    Exceptions.throwOrReport(th, subscriber);
                }
            }
        }, this.time, this.unit);
    }
}
