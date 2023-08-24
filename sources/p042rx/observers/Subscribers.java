package p042rx.observers;

import p042rx.Observer;
import p042rx.Subscriber;
import p042rx.exceptions.OnErrorNotImplementedException;
import p042rx.functions.Action0;
import p042rx.functions.Action1;

/* renamed from: rx.observers.Subscribers */
/* loaded from: classes6.dex */
public final class Subscribers {
    private Subscribers() {
        throw new IllegalStateException("No instances!");
    }

    public static <T> Subscriber<T> empty() {
        return from(Observers.empty());
    }

    public static <T> Subscriber<T> from(final Observer<? super T> observer) {
        return new Subscriber<T>() { // from class: rx.observers.Subscribers.1
            @Override // p042rx.Observer
            public void onCompleted() {
                Observer.this.onCompleted();
            }

            @Override // p042rx.Observer
            public void onError(Throwable th) {
                Observer.this.onError(th);
            }

            @Override // p042rx.Observer
            public void onNext(T t) {
                Observer.this.onNext(t);
            }
        };
    }

    public static <T> Subscriber<T> create(final Action1<? super T> action1) {
        if (action1 == null) {
            throw new IllegalArgumentException("onNext can not be null");
        }
        return new Subscriber<T>() { // from class: rx.observers.Subscribers.2
            @Override // p042rx.Observer
            public final void onCompleted() {
            }

            @Override // p042rx.Observer
            public final void onError(Throwable th) {
                throw new OnErrorNotImplementedException(th);
            }

            @Override // p042rx.Observer
            public final void onNext(T t) {
                Action1.this.call(t);
            }
        };
    }

    public static <T> Subscriber<T> create(final Action1<? super T> action1, final Action1<Throwable> action12) {
        if (action1 != null) {
            if (action12 == null) {
                throw new IllegalArgumentException("onError can not be null");
            }
            return new Subscriber<T>() { // from class: rx.observers.Subscribers.3
                @Override // p042rx.Observer
                public final void onCompleted() {
                }

                @Override // p042rx.Observer
                public final void onError(Throwable th) {
                    Action1.this.call(th);
                }

                @Override // p042rx.Observer
                public final void onNext(T t) {
                    action1.call(t);
                }
            };
        }
        throw new IllegalArgumentException("onNext can not be null");
    }

    public static <T> Subscriber<T> create(final Action1<? super T> action1, final Action1<Throwable> action12, final Action0 action0) {
        if (action1 != null) {
            if (action12 != null) {
                if (action0 == null) {
                    throw new IllegalArgumentException("onComplete can not be null");
                }
                return new Subscriber<T>() { // from class: rx.observers.Subscribers.4
                    @Override // p042rx.Observer
                    public final void onCompleted() {
                        Action0.this.call();
                    }

                    @Override // p042rx.Observer
                    public final void onError(Throwable th) {
                        action12.call(th);
                    }

                    @Override // p042rx.Observer
                    public final void onNext(T t) {
                        action1.call(t);
                    }
                };
            }
            throw new IllegalArgumentException("onError can not be null");
        }
        throw new IllegalArgumentException("onNext can not be null");
    }

    public static <T> Subscriber<T> wrap(final Subscriber<? super T> subscriber) {
        return new Subscriber<T>(subscriber) { // from class: rx.observers.Subscribers.5
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
                subscriber.onNext(t);
            }
        };
    }
}
