package p042rx.internal.operators;

import java.util.concurrent.atomic.AtomicLong;
import p042rx.Observable;
import p042rx.Producer;
import p042rx.Subscriber;

/* renamed from: rx.internal.operators.OnSubscribeRange */
/* loaded from: classes6.dex */
public final class OnSubscribeRange implements Observable.OnSubscribe<Integer> {
    private final int endIndex;
    private final int startIndex;

    public OnSubscribeRange(int r1, int r2) {
        this.startIndex = r1;
        this.endIndex = r2;
    }

    @Override // p042rx.functions.Action1
    public void call(Subscriber<? super Integer> subscriber) {
        subscriber.setProducer(new RangeProducer(subscriber, this.startIndex, this.endIndex));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: rx.internal.operators.OnSubscribeRange$RangeProducer */
    /* loaded from: classes6.dex */
    public static final class RangeProducer extends AtomicLong implements Producer {
        private static final long serialVersionUID = 4114392207069098388L;
        private final Subscriber<? super Integer> childSubscriber;
        private long currentIndex;
        private final int endOfRange;

        RangeProducer(Subscriber<? super Integer> subscriber, int r2, int r3) {
            this.childSubscriber = subscriber;
            this.currentIndex = r2;
            this.endOfRange = r3;
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
            long j2 = this.endOfRange + 1;
            long j3 = this.currentIndex;
            Subscriber<? super Integer> subscriber = this.childSubscriber;
            do {
                long j4 = 0;
                while (true) {
                    if (j4 != j && j3 != j2) {
                        if (subscriber.isUnsubscribed()) {
                            return;
                        }
                        subscriber.onNext(Integer.valueOf((int) j3));
                        j3++;
                        j4++;
                    } else if (subscriber.isUnsubscribed()) {
                        return;
                    } else {
                        if (j3 == j2) {
                            subscriber.onCompleted();
                            return;
                        }
                        j = get();
                        if (j == j4) {
                            this.currentIndex = j3;
                            j = addAndGet(-j4);
                        }
                    }
                }
            } while (j != 0);
        }

        void fastPath() {
            long j = this.endOfRange + 1;
            Subscriber<? super Integer> subscriber = this.childSubscriber;
            for (long j2 = this.currentIndex; j2 != j; j2++) {
                if (subscriber.isUnsubscribed()) {
                    return;
                }
                subscriber.onNext(Integer.valueOf((int) j2));
            }
            if (subscriber.isUnsubscribed()) {
                return;
            }
            subscriber.onCompleted();
        }
    }
}
