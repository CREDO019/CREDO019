package p042rx.internal.operators;

import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import p042rx.Observable;
import p042rx.Subscriber;
import p042rx.exceptions.Exceptions;
import p042rx.functions.Action0;
import p042rx.internal.producers.SingleProducer;
import p042rx.subscriptions.Subscriptions;

/* renamed from: rx.internal.operators.OnSubscribeToObservableFuture */
/* loaded from: classes6.dex */
public final class OnSubscribeToObservableFuture {
    private OnSubscribeToObservableFuture() {
        throw new IllegalStateException("No instances!");
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: rx.internal.operators.OnSubscribeToObservableFuture$ToObservableFuture */
    /* loaded from: classes6.dex */
    public static class ToObservableFuture<T> implements Observable.OnSubscribe<T> {
        final Future<? extends T> that;
        private final long time;
        private final TimeUnit unit;

        @Override // p042rx.functions.Action1
        public /* bridge */ /* synthetic */ void call(Object obj) {
            call((Subscriber) ((Subscriber) obj));
        }

        public ToObservableFuture(Future<? extends T> future) {
            this.that = future;
            this.time = 0L;
            this.unit = null;
        }

        public ToObservableFuture(Future<? extends T> future, long j, TimeUnit timeUnit) {
            this.that = future;
            this.time = j;
            this.unit = timeUnit;
        }

        public void call(Subscriber<? super T> subscriber) {
            subscriber.add(Subscriptions.create(new Action0() { // from class: rx.internal.operators.OnSubscribeToObservableFuture.ToObservableFuture.1
                @Override // p042rx.functions.Action0
                public void call() {
                    ToObservableFuture.this.that.cancel(true);
                }
            }));
            try {
                if (subscriber.isUnsubscribed()) {
                    return;
                }
                TimeUnit timeUnit = this.unit;
                subscriber.setProducer(new SingleProducer(subscriber, timeUnit == null ? this.that.get() : this.that.get(this.time, timeUnit)));
            } catch (Throwable th) {
                if (subscriber.isUnsubscribed()) {
                    return;
                }
                Exceptions.throwOrReport(th, subscriber);
            }
        }
    }

    public static <T> Observable.OnSubscribe<T> toObservableFuture(Future<? extends T> future) {
        return new ToObservableFuture(future);
    }

    public static <T> Observable.OnSubscribe<T> toObservableFuture(Future<? extends T> future, long j, TimeUnit timeUnit) {
        return new ToObservableFuture(future, j, timeUnit);
    }
}
