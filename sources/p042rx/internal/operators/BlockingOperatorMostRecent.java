package p042rx.internal.operators;

import java.util.Iterator;
import java.util.NoSuchElementException;
import p042rx.Observable;
import p042rx.Subscriber;
import p042rx.exceptions.Exceptions;

/* renamed from: rx.internal.operators.BlockingOperatorMostRecent */
/* loaded from: classes6.dex */
public final class BlockingOperatorMostRecent {
    private BlockingOperatorMostRecent() {
        throw new IllegalStateException("No instances!");
    }

    public static <T> Iterable<T> mostRecent(final Observable<? extends T> observable, final T t) {
        return new Iterable<T>() { // from class: rx.internal.operators.BlockingOperatorMostRecent.1
            @Override // java.lang.Iterable
            public Iterator<T> iterator() {
                MostRecentObserver mostRecentObserver = new MostRecentObserver(t);
                observable.subscribe((Subscriber) mostRecentObserver);
                return mostRecentObserver.getIterable();
            }
        };
    }

    /* renamed from: rx.internal.operators.BlockingOperatorMostRecent$MostRecentObserver */
    /* loaded from: classes6.dex */
    static final class MostRecentObserver<T> extends Subscriber<T> {
        volatile Object value;

        MostRecentObserver(T t) {
            this.value = NotificationLite.next(t);
        }

        @Override // p042rx.Observer
        public void onCompleted() {
            this.value = NotificationLite.completed();
        }

        @Override // p042rx.Observer
        public void onError(Throwable th) {
            this.value = NotificationLite.error(th);
        }

        @Override // p042rx.Observer
        public void onNext(T t) {
            this.value = NotificationLite.next(t);
        }

        public Iterator<T> getIterable() {
            return new Iterator<T>() { // from class: rx.internal.operators.BlockingOperatorMostRecent.MostRecentObserver.1
                private Object buf;

                @Override // java.util.Iterator
                public boolean hasNext() {
                    Object obj = MostRecentObserver.this.value;
                    this.buf = obj;
                    return !NotificationLite.isCompleted(obj);
                }

                @Override // java.util.Iterator
                public T next() {
                    try {
                        if (this.buf == null) {
                            this.buf = MostRecentObserver.this.value;
                        }
                        if (NotificationLite.isCompleted(this.buf)) {
                            throw new NoSuchElementException();
                        }
                        if (NotificationLite.isError(this.buf)) {
                            throw Exceptions.propagate(NotificationLite.getError(this.buf));
                        }
                        return (T) NotificationLite.getValue(this.buf);
                    } finally {
                        this.buf = null;
                    }
                }

                @Override // java.util.Iterator
                public void remove() {
                    throw new UnsupportedOperationException("Read only iterator");
                }
            };
        }
    }
}
