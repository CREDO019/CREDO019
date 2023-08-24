package p042rx.internal.operators;

import java.util.ArrayDeque;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicLong;
import p042rx.Observable;
import p042rx.Producer;
import p042rx.Scheduler;
import p042rx.Subscriber;
import p042rx.functions.Func1;

/* renamed from: rx.internal.operators.OperatorTakeLastTimed */
/* loaded from: classes6.dex */
public final class OperatorTakeLastTimed<T> implements Observable.Operator<T, T> {
    final long ageMillis;
    final int count;
    final Scheduler scheduler;

    @Override // p042rx.functions.Func1
    public /* bridge */ /* synthetic */ Object call(Object obj) {
        return call((Subscriber) ((Subscriber) obj));
    }

    public OperatorTakeLastTimed(long j, TimeUnit timeUnit, Scheduler scheduler) {
        this.ageMillis = timeUnit.toMillis(j);
        this.scheduler = scheduler;
        this.count = -1;
    }

    public OperatorTakeLastTimed(int r1, long j, TimeUnit timeUnit, Scheduler scheduler) {
        if (r1 < 0) {
            throw new IndexOutOfBoundsException("count could not be negative");
        }
        this.ageMillis = timeUnit.toMillis(j);
        this.scheduler = scheduler;
        this.count = r1;
    }

    public Subscriber<? super T> call(Subscriber<? super T> subscriber) {
        final TakeLastTimedSubscriber takeLastTimedSubscriber = new TakeLastTimedSubscriber(subscriber, this.count, this.ageMillis, this.scheduler);
        subscriber.add(takeLastTimedSubscriber);
        subscriber.setProducer(new Producer() { // from class: rx.internal.operators.OperatorTakeLastTimed.1
            @Override // p042rx.Producer
            public void request(long j) {
                takeLastTimedSubscriber.requestMore(j);
            }
        });
        return takeLastTimedSubscriber;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: rx.internal.operators.OperatorTakeLastTimed$TakeLastTimedSubscriber */
    /* loaded from: classes6.dex */
    public static final class TakeLastTimedSubscriber<T> extends Subscriber<T> implements Func1<Object, T> {
        final Subscriber<? super T> actual;
        final long ageMillis;
        final int count;
        final Scheduler scheduler;
        final AtomicLong requested = new AtomicLong();
        final ArrayDeque<Object> queue = new ArrayDeque<>();
        final ArrayDeque<Long> queueTimes = new ArrayDeque<>();

        public TakeLastTimedSubscriber(Subscriber<? super T> subscriber, int r2, long j, Scheduler scheduler) {
            this.actual = subscriber;
            this.count = r2;
            this.ageMillis = j;
            this.scheduler = scheduler;
        }

        @Override // p042rx.Observer
        public void onNext(T t) {
            if (this.count != 0) {
                long now = this.scheduler.now();
                if (this.queue.size() == this.count) {
                    this.queue.poll();
                    this.queueTimes.poll();
                }
                evictOld(now);
                this.queue.offer(NotificationLite.next(t));
                this.queueTimes.offer(Long.valueOf(now));
            }
        }

        protected void evictOld(long j) {
            long j2 = j - this.ageMillis;
            while (true) {
                Long peek = this.queueTimes.peek();
                if (peek == null || peek.longValue() >= j2) {
                    return;
                }
                this.queue.poll();
                this.queueTimes.poll();
            }
        }

        @Override // p042rx.Observer
        public void onError(Throwable th) {
            this.queue.clear();
            this.queueTimes.clear();
            this.actual.onError(th);
        }

        @Override // p042rx.Observer
        public void onCompleted() {
            evictOld(this.scheduler.now());
            this.queueTimes.clear();
            BackpressureUtils.postCompleteDone(this.requested, this.queue, this.actual, this);
        }

        @Override // p042rx.functions.Func1
        public T call(Object obj) {
            return (T) NotificationLite.getValue(obj);
        }

        void requestMore(long j) {
            BackpressureUtils.postCompleteRequest(this.requested, j, this.queue, this.actual, this);
        }
    }
}
