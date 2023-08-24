package p042rx.internal.operators;

import p042rx.Observable;
import p042rx.Producer;
import p042rx.Subscriber;

/* renamed from: rx.internal.operators.OperatorSkip */
/* loaded from: classes6.dex */
public final class OperatorSkip<T> implements Observable.Operator<T, T> {
    final int toSkip;

    @Override // p042rx.functions.Func1
    public /* bridge */ /* synthetic */ Object call(Object obj) {
        return call((Subscriber) ((Subscriber) obj));
    }

    public OperatorSkip(int r4) {
        if (r4 < 0) {
            throw new IllegalArgumentException("n >= 0 required but it was " + r4);
        }
        this.toSkip = r4;
    }

    public Subscriber<? super T> call(final Subscriber<? super T> subscriber) {
        return (Subscriber<T>) new Subscriber<T>(subscriber) { // from class: rx.internal.operators.OperatorSkip.1
            int skipped;

            @Override // p042rx.Observer
            public void onCompleted() {
                subscriber.onCompleted();
            }

            @Override // p042rx.Observer
            public void onError(Throwable th) {
                subscriber.onError(th);
            }

            @Override // p042rx.Observer
            public void onNext(T t) {
                if (this.skipped >= OperatorSkip.this.toSkip) {
                    subscriber.onNext(t);
                } else {
                    this.skipped++;
                }
            }

            @Override // p042rx.Subscriber, p042rx.observers.AssertableSubscriber
            public void setProducer(Producer producer) {
                subscriber.setProducer(producer);
                producer.request(OperatorSkip.this.toSkip);
            }
        };
    }
}
