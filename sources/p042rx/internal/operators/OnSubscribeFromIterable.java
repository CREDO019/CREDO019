package p042rx.internal.operators;

import java.util.Iterator;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicLong;
import p042rx.Observable;
import p042rx.Producer;
import p042rx.Subscriber;
import p042rx.exceptions.Exceptions;

/* renamed from: rx.internal.operators.OnSubscribeFromIterable */
/* loaded from: classes6.dex */
public final class OnSubscribeFromIterable<T> implements Observable.OnSubscribe<T> {

    /* renamed from: is */
    final Iterable<? extends T> f2555is;

    @Override // p042rx.functions.Action1
    public /* bridge */ /* synthetic */ void call(Object obj) {
        call((Subscriber) ((Subscriber) obj));
    }

    public OnSubscribeFromIterable(Iterable<? extends T> iterable) {
        Objects.requireNonNull(iterable, "iterable must not be null");
        this.f2555is = iterable;
    }

    public void call(Subscriber<? super T> subscriber) {
        try {
            Iterator<? extends T> it = this.f2555is.iterator();
            boolean hasNext = it.hasNext();
            if (subscriber.isUnsubscribed()) {
                return;
            }
            if (!hasNext) {
                subscriber.onCompleted();
            } else {
                subscriber.setProducer(new IterableProducer(subscriber, it));
            }
        } catch (Throwable th) {
            Exceptions.throwOrReport(th, subscriber);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: rx.internal.operators.OnSubscribeFromIterable$IterableProducer */
    /* loaded from: classes6.dex */
    public static final class IterableProducer<T> extends AtomicLong implements Producer {
        private static final long serialVersionUID = -8730475647105475802L;

        /* renamed from: it */
        private final Iterator<? extends T> f2556it;

        /* renamed from: o */
        private final Subscriber<? super T> f2557o;

        /* JADX INFO: Access modifiers changed from: package-private */
        public IterableProducer(Subscriber<? super T> subscriber, Iterator<? extends T> it) {
            this.f2557o = subscriber;
            this.f2556it = it;
        }

        @Override // p042rx.Producer
        public void request(long j) {
            if (get() == Long.MAX_VALUE) {
                return;
            }
            if (j == Long.MAX_VALUE && compareAndSet(0L, Long.MAX_VALUE)) {
                fastPath();
            } else if (j <= 0 || BackpressureUtils.getAndAddRequest(this, j) != 0) {
            } else {
                slowPath(j);
            }
        }

        void slowPath(long j) {
            Subscriber<? super T> subscriber = this.f2557o;
            Iterator<? extends T> it = this.f2556it;
            do {
                long j2 = 0;
                while (true) {
                    if (j2 != j) {
                        if (subscriber.isUnsubscribed()) {
                            return;
                        }
                        try {
                            subscriber.onNext((T) it.next());
                            if (subscriber.isUnsubscribed()) {
                                return;
                            }
                            try {
                                if (!it.hasNext()) {
                                    if (subscriber.isUnsubscribed()) {
                                        return;
                                    }
                                    subscriber.onCompleted();
                                    return;
                                }
                                j2++;
                            } catch (Throwable th) {
                                Exceptions.throwOrReport(th, subscriber);
                                return;
                            }
                        } catch (Throwable th2) {
                            Exceptions.throwOrReport(th2, subscriber);
                            return;
                        }
                    } else {
                        j = get();
                        if (j2 == j) {
                            j = BackpressureUtils.produced(this, j2);
                        }
                    }
                }
            } while (j != 0);
        }

        void fastPath() {
            Subscriber<? super T> subscriber = this.f2557o;
            Iterator<? extends T> it = this.f2556it;
            while (!subscriber.isUnsubscribed()) {
                try {
                    subscriber.onNext((T) it.next());
                    if (subscriber.isUnsubscribed()) {
                        return;
                    }
                    try {
                        if (!it.hasNext()) {
                            if (subscriber.isUnsubscribed()) {
                                return;
                            }
                            subscriber.onCompleted();
                            return;
                        }
                    } catch (Throwable th) {
                        Exceptions.throwOrReport(th, subscriber);
                        return;
                    }
                } catch (Throwable th2) {
                    Exceptions.throwOrReport(th2, subscriber);
                    return;
                }
            }
        }
    }
}
