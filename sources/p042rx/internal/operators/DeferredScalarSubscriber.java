package p042rx.internal.operators;

import java.util.concurrent.atomic.AtomicInteger;
import p042rx.Observable;
import p042rx.Producer;
import p042rx.Subscriber;

/* renamed from: rx.internal.operators.DeferredScalarSubscriber */
/* loaded from: classes6.dex */
public abstract class DeferredScalarSubscriber<T, R> extends Subscriber<T> {
    static final int HAS_REQUEST_HAS_VALUE = 3;
    static final int HAS_REQUEST_NO_VALUE = 1;
    static final int NO_REQUEST_HAS_VALUE = 2;
    static final int NO_REQUEST_NO_VALUE = 0;
    protected final Subscriber<? super R> actual;
    protected boolean hasValue;
    final AtomicInteger state = new AtomicInteger();
    protected R value;

    public DeferredScalarSubscriber(Subscriber<? super R> subscriber) {
        this.actual = subscriber;
    }

    @Override // p042rx.Observer
    public void onError(Throwable th) {
        this.value = null;
        this.actual.onError(th);
    }

    @Override // p042rx.Observer
    public void onCompleted() {
        if (this.hasValue) {
            complete(this.value);
        } else {
            complete();
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final void complete() {
        this.actual.onCompleted();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final void complete(R r) {
        Subscriber<? super R> subscriber = this.actual;
        do {
            int r1 = this.state.get();
            if (r1 == 2 || r1 == 3 || subscriber.isUnsubscribed()) {
                return;
            }
            if (r1 == 1) {
                subscriber.onNext(r);
                if (!subscriber.isUnsubscribed()) {
                    subscriber.onCompleted();
                }
                this.state.lazySet(3);
                return;
            }
            this.value = r;
        } while (!this.state.compareAndSet(0, 2));
    }

    final void downstreamRequest(long j) {
        int r2 = (j > 0L ? 1 : (j == 0L ? 0 : -1));
        if (r2 < 0) {
            throw new IllegalArgumentException("n >= 0 required but it was " + j);
        } else if (r2 != 0) {
            Subscriber<? super R> subscriber = this.actual;
            do {
                int r5 = this.state.get();
                if (r5 == 1 || r5 == 3 || subscriber.isUnsubscribed()) {
                    return;
                }
                if (r5 == 2) {
                    if (this.state.compareAndSet(2, 3)) {
                        subscriber.onNext((R) this.value);
                        if (subscriber.isUnsubscribed()) {
                            return;
                        }
                        subscriber.onCompleted();
                        return;
                    }
                    return;
                }
            } while (!this.state.compareAndSet(0, 1));
        }
    }

    @Override // p042rx.Subscriber, p042rx.observers.AssertableSubscriber
    public final void setProducer(Producer producer) {
        producer.request(Long.MAX_VALUE);
    }

    public final void subscribeTo(Observable<? extends T> observable) {
        setupDownstream();
        observable.unsafeSubscribe(this);
    }

    final void setupDownstream() {
        Subscriber<? super R> subscriber = this.actual;
        subscriber.add(this);
        subscriber.setProducer(new InnerProducer(this));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: rx.internal.operators.DeferredScalarSubscriber$InnerProducer */
    /* loaded from: classes6.dex */
    public static final class InnerProducer implements Producer {
        final DeferredScalarSubscriber<?, ?> parent;

        public InnerProducer(DeferredScalarSubscriber<?, ?> deferredScalarSubscriber) {
            this.parent = deferredScalarSubscriber;
        }

        @Override // p042rx.Producer
        public void request(long j) {
            this.parent.downstreamRequest(j);
        }
    }
}
