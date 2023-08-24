package p042rx.internal.operators;

import java.util.concurrent.atomic.AtomicLong;
import p042rx.Observable;
import p042rx.Producer;
import p042rx.Subscriber;

/* renamed from: rx.internal.operators.OnSubscribeFromArray */
/* loaded from: classes6.dex */
public final class OnSubscribeFromArray<T> implements Observable.OnSubscribe<T> {
    final T[] array;

    @Override // p042rx.functions.Action1
    public /* bridge */ /* synthetic */ void call(Object obj) {
        call((Subscriber) ((Subscriber) obj));
    }

    public OnSubscribeFromArray(T[] tArr) {
        this.array = tArr;
    }

    public void call(Subscriber<? super T> subscriber) {
        subscriber.setProducer(new FromArrayProducer(subscriber, this.array));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: rx.internal.operators.OnSubscribeFromArray$FromArrayProducer */
    /* loaded from: classes6.dex */
    public static final class FromArrayProducer<T> extends AtomicLong implements Producer {
        private static final long serialVersionUID = 3534218984725836979L;
        final T[] array;
        final Subscriber<? super T> child;
        int index;

        public FromArrayProducer(Subscriber<? super T> subscriber, T[] tArr) {
            this.child = subscriber;
            this.array = tArr;
        }

        @Override // p042rx.Producer
        public void request(long j) {
            int r2 = (j > 0L ? 1 : (j == 0L ? 0 : -1));
            if (r2 < 0) {
                throw new IllegalArgumentException("n >= 0 required but it was " + j);
            } else if (j == Long.MAX_VALUE) {
                if (BackpressureUtils.getAndAddRequest(this, j) == 0) {
                    fastPath();
                }
            } else if (r2 == 0 || BackpressureUtils.getAndAddRequest(this, j) != 0) {
            } else {
                slowPath(j);
            }
        }

        void fastPath() {
            Subscriber<? super T> subscriber = this.child;
            for (T t : this.array) {
                Object obj = (Object) t;
                if (subscriber.isUnsubscribed()) {
                    return;
                }
                subscriber.onNext(obj);
            }
            if (subscriber.isUnsubscribed()) {
                return;
            }
            subscriber.onCompleted();
        }

        void slowPath(long j) {
            Subscriber<? super T> subscriber = this.child;
            T[] tArr = this.array;
            int length = tArr.length;
            int r3 = this.index;
            do {
                long j2 = 0;
                while (true) {
                    if (j != 0 && r3 != length) {
                        if (subscriber.isUnsubscribed()) {
                            return;
                        }
                        subscriber.onNext((Object) tArr[r3]);
                        r3++;
                        if (r3 == length) {
                            if (subscriber.isUnsubscribed()) {
                                return;
                            }
                            subscriber.onCompleted();
                            return;
                        }
                        j--;
                        j2--;
                    } else {
                        j = get() + j2;
                        if (j == 0) {
                            this.index = r3;
                            j = addAndGet(j2);
                        }
                    }
                }
            } while (j != 0);
        }
    }
}
