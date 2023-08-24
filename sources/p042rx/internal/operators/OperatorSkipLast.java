package p042rx.internal.operators;

import java.util.ArrayDeque;
import java.util.Deque;
import p042rx.Observable;
import p042rx.Subscriber;

/* renamed from: rx.internal.operators.OperatorSkipLast */
/* loaded from: classes6.dex */
public class OperatorSkipLast<T> implements Observable.Operator<T, T> {
    final int count;

    @Override // p042rx.functions.Func1
    public /* bridge */ /* synthetic */ Object call(Object obj) {
        return call((Subscriber) ((Subscriber) obj));
    }

    public OperatorSkipLast(int r2) {
        if (r2 < 0) {
            throw new IndexOutOfBoundsException("count could not be negative");
        }
        this.count = r2;
    }

    public Subscriber<? super T> call(final Subscriber<? super T> subscriber) {
        return (Subscriber<T>) new Subscriber<T>(subscriber) { // from class: rx.internal.operators.OperatorSkipLast.1
            private final Deque<Object> deque = new ArrayDeque();

            @Override // p042rx.Observer
            public void onCompleted() {
                subscriber.onCompleted();
            }

            @Override // p042rx.Observer
            public void onError(Throwable th) {
                subscriber.onError(th);
            }

            /* JADX WARN: Multi-variable type inference failed */
            @Override // p042rx.Observer
            public void onNext(T t) {
                if (OperatorSkipLast.this.count == 0) {
                    subscriber.onNext(t);
                    return;
                }
                if (this.deque.size() == OperatorSkipLast.this.count) {
                    subscriber.onNext(NotificationLite.getValue(this.deque.removeFirst()));
                } else {
                    request(1L);
                }
                this.deque.offerLast(NotificationLite.next(t));
            }
        };
    }
}
