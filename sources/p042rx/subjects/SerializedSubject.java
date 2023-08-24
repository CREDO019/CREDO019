package p042rx.subjects;

import p042rx.Observable;
import p042rx.Subscriber;
import p042rx.observers.SerializedObserver;

/* renamed from: rx.subjects.SerializedSubject */
/* loaded from: classes6.dex */
public class SerializedSubject<T, R> extends Subject<T, R> {
    private final Subject<T, R> actual;
    private final SerializedObserver<T> observer;

    public SerializedSubject(final Subject<T, R> subject) {
        super(new Observable.OnSubscribe<R>() { // from class: rx.subjects.SerializedSubject.1
            @Override // p042rx.functions.Action1
            public /* bridge */ /* synthetic */ void call(Object obj) {
                call((Subscriber) ((Subscriber) obj));
            }

            public void call(Subscriber<? super R> subscriber) {
                Subject.this.unsafeSubscribe(subscriber);
            }
        });
        this.actual = subject;
        this.observer = new SerializedObserver<>(subject);
    }

    @Override // p042rx.Observer
    public void onCompleted() {
        this.observer.onCompleted();
    }

    @Override // p042rx.Observer
    public void onError(Throwable th) {
        this.observer.onError(th);
    }

    @Override // p042rx.Observer
    public void onNext(T t) {
        this.observer.onNext(t);
    }

    @Override // p042rx.subjects.Subject
    public boolean hasObservers() {
        return this.actual.hasObservers();
    }
}
