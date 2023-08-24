package p042rx.internal.operators;

import java.util.concurrent.atomic.AtomicLong;
import p042rx.Observable;
import p042rx.Producer;
import p042rx.Subscriber;
import p042rx.plugins.RxJavaHooks;

/* renamed from: rx.internal.operators.OperatorTake */
/* loaded from: classes6.dex */
public final class OperatorTake<T> implements Observable.Operator<T, T> {
    final int limit;

    @Override // p042rx.functions.Func1
    public /* bridge */ /* synthetic */ Object call(Object obj) {
        return call((Subscriber) ((Subscriber) obj));
    }

    public OperatorTake(int r4) {
        if (r4 < 0) {
            throw new IllegalArgumentException("limit >= 0 required but it was " + r4);
        }
        this.limit = r4;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: rx.internal.operators.OperatorTake$1 */
    /* loaded from: classes6.dex */
    public class C56801 extends Subscriber<T> {
        boolean completed;
        int count;
        final /* synthetic */ Subscriber val$child;

        C56801(Subscriber subscriber) {
            this.val$child = subscriber;
        }

        @Override // p042rx.Observer
        public void onCompleted() {
            if (this.completed) {
                return;
            }
            this.completed = true;
            this.val$child.onCompleted();
        }

        @Override // p042rx.Observer
        public void onError(Throwable th) {
            if (!this.completed) {
                this.completed = true;
                try {
                    this.val$child.onError(th);
                    return;
                } finally {
                    unsubscribe();
                }
            }
            RxJavaHooks.onError(th);
        }

        @Override // p042rx.Observer
        public void onNext(T t) {
            if (isUnsubscribed()) {
                return;
            }
            int r0 = this.count;
            this.count = r0 + 1;
            if (r0 < OperatorTake.this.limit) {
                boolean z = this.count == OperatorTake.this.limit;
                this.val$child.onNext(t);
                if (!z || this.completed) {
                    return;
                }
                this.completed = true;
                try {
                    this.val$child.onCompleted();
                } finally {
                    unsubscribe();
                }
            }
        }

        @Override // p042rx.Subscriber, p042rx.observers.AssertableSubscriber
        public void setProducer(final Producer producer) {
            this.val$child.setProducer(new Producer() { // from class: rx.internal.operators.OperatorTake.1.1
                final AtomicLong requested = new AtomicLong(0);

                @Override // p042rx.Producer
                public void request(long j) {
                    long j2;
                    long min;
                    if (j <= 0 || C56801.this.completed) {
                        return;
                    }
                    do {
                        j2 = this.requested.get();
                        min = Math.min(j, OperatorTake.this.limit - j2);
                        if (min == 0) {
                            return;
                        }
                    } while (!this.requested.compareAndSet(j2, j2 + min));
                    producer.request(min);
                }
            });
        }
    }

    public Subscriber<? super T> call(Subscriber<? super T> subscriber) {
        C56801 c56801 = new C56801(subscriber);
        if (this.limit == 0) {
            subscriber.onCompleted();
            c56801.unsubscribe();
        }
        subscriber.add(c56801);
        return c56801;
    }
}
