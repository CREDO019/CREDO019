package p042rx.internal.operators;

import java.util.concurrent.atomic.AtomicBoolean;
import p042rx.Observable;
import p042rx.Producer;
import p042rx.Subscriber;

/* renamed from: rx.internal.operators.OperatorElementAt */
/* loaded from: classes6.dex */
public final class OperatorElementAt<T> implements Observable.Operator<T, T> {
    final T defaultValue;
    final boolean hasDefault;
    final int index;

    @Override // p042rx.functions.Func1
    public /* bridge */ /* synthetic */ Object call(Object obj) {
        return call((Subscriber) ((Subscriber) obj));
    }

    public OperatorElementAt(int r3) {
        this(r3, null, false);
    }

    public OperatorElementAt(int r2, T t) {
        this(r2, t, true);
    }

    private OperatorElementAt(int r1, T t, boolean z) {
        if (r1 < 0) {
            throw new IndexOutOfBoundsException(r1 + " is out of bounds");
        }
        this.index = r1;
        this.defaultValue = t;
        this.hasDefault = z;
    }

    public Subscriber<? super T> call(final Subscriber<? super T> subscriber) {
        Subscriber subscriber2 = (Subscriber<T>) new Subscriber<T>() { // from class: rx.internal.operators.OperatorElementAt.1
            private int currentIndex;

            @Override // p042rx.Observer
            public void onNext(T t) {
                int r0 = this.currentIndex;
                this.currentIndex = r0 + 1;
                if (r0 == OperatorElementAt.this.index) {
                    subscriber.onNext(t);
                    subscriber.onCompleted();
                    unsubscribe();
                }
            }

            @Override // p042rx.Observer
            public void onError(Throwable th) {
                subscriber.onError(th);
            }

            @Override // p042rx.Observer
            public void onCompleted() {
                if (this.currentIndex <= OperatorElementAt.this.index) {
                    if (OperatorElementAt.this.hasDefault) {
                        subscriber.onNext(OperatorElementAt.this.defaultValue);
                        subscriber.onCompleted();
                        return;
                    }
                    Subscriber subscriber3 = subscriber;
                    subscriber3.onError(new IndexOutOfBoundsException(OperatorElementAt.this.index + " is out of bounds"));
                }
            }

            @Override // p042rx.Subscriber, p042rx.observers.AssertableSubscriber
            public void setProducer(Producer producer) {
                subscriber.setProducer(new InnerProducer(producer));
            }
        };
        subscriber.add(subscriber2);
        return subscriber2;
    }

    /* renamed from: rx.internal.operators.OperatorElementAt$InnerProducer */
    /* loaded from: classes6.dex */
    static class InnerProducer extends AtomicBoolean implements Producer {
        private static final long serialVersionUID = 1;
        final Producer actual;

        public InnerProducer(Producer producer) {
            this.actual = producer;
        }

        @Override // p042rx.Producer
        public void request(long j) {
            int r2 = (j > 0L ? 1 : (j == 0L ? 0 : -1));
            if (r2 < 0) {
                throw new IllegalArgumentException("n >= 0 required");
            }
            if (r2 <= 0 || !compareAndSet(false, true)) {
                return;
            }
            this.actual.request(Long.MAX_VALUE);
        }
    }
}
