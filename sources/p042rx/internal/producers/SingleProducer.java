package p042rx.internal.producers;

import java.util.concurrent.atomic.AtomicBoolean;
import p042rx.Producer;
import p042rx.Subscriber;
import p042rx.exceptions.Exceptions;

/* renamed from: rx.internal.producers.SingleProducer */
/* loaded from: classes6.dex */
public final class SingleProducer<T> extends AtomicBoolean implements Producer {
    private static final long serialVersionUID = -3353584923995471404L;
    final Subscriber<? super T> child;
    final T value;

    public SingleProducer(Subscriber<? super T> subscriber, T t) {
        this.child = subscriber;
        this.value = t;
    }

    @Override // p042rx.Producer
    public void request(long j) {
        int r2 = (j > 0L ? 1 : (j == 0L ? 0 : -1));
        if (r2 < 0) {
            throw new IllegalArgumentException("n >= 0 required");
        }
        if (r2 != 0 && compareAndSet(false, true)) {
            Subscriber<? super T> subscriber = this.child;
            if (subscriber.isUnsubscribed()) {
                return;
            }
            Object obj = (T) this.value;
            try {
                subscriber.onNext(obj);
                if (subscriber.isUnsubscribed()) {
                    return;
                }
                subscriber.onCompleted();
            } catch (Throwable th) {
                Exceptions.throwOrReport(th, subscriber, obj);
            }
        }
    }
}
