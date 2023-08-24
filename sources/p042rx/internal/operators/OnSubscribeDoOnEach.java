package p042rx.internal.operators;

import java.util.Arrays;
import p042rx.Observable;
import p042rx.Observer;
import p042rx.Subscriber;
import p042rx.exceptions.CompositeException;
import p042rx.exceptions.Exceptions;
import p042rx.plugins.RxJavaHooks;

/* renamed from: rx.internal.operators.OnSubscribeDoOnEach */
/* loaded from: classes6.dex */
public class OnSubscribeDoOnEach<T> implements Observable.OnSubscribe<T> {
    private final Observer<? super T> doOnEachObserver;
    private final Observable<T> source;

    @Override // p042rx.functions.Action1
    public /* bridge */ /* synthetic */ void call(Object obj) {
        call((Subscriber) ((Subscriber) obj));
    }

    public OnSubscribeDoOnEach(Observable<T> observable, Observer<? super T> observer) {
        this.source = observable;
        this.doOnEachObserver = observer;
    }

    public void call(Subscriber<? super T> subscriber) {
        this.source.unsafeSubscribe(new DoOnEachSubscriber(subscriber, this.doOnEachObserver));
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: rx.internal.operators.OnSubscribeDoOnEach$DoOnEachSubscriber */
    /* loaded from: classes6.dex */
    public static final class DoOnEachSubscriber<T> extends Subscriber<T> {
        private final Observer<? super T> doOnEachObserver;
        private boolean done;
        private final Subscriber<? super T> subscriber;

        DoOnEachSubscriber(Subscriber<? super T> subscriber, Observer<? super T> observer) {
            super(subscriber);
            this.subscriber = subscriber;
            this.doOnEachObserver = observer;
        }

        @Override // p042rx.Observer
        public void onCompleted() {
            if (this.done) {
                return;
            }
            try {
                this.doOnEachObserver.onCompleted();
                this.done = true;
                this.subscriber.onCompleted();
            } catch (Throwable th) {
                Exceptions.throwOrReport(th, this);
            }
        }

        @Override // p042rx.Observer
        public void onError(Throwable th) {
            if (this.done) {
                RxJavaHooks.onError(th);
                return;
            }
            this.done = true;
            try {
                this.doOnEachObserver.onError(th);
                this.subscriber.onError(th);
            } catch (Throwable th2) {
                Exceptions.throwIfFatal(th2);
                this.subscriber.onError(new CompositeException(Arrays.asList(th, th2)));
            }
        }

        @Override // p042rx.Observer
        public void onNext(T t) {
            if (this.done) {
                return;
            }
            try {
                this.doOnEachObserver.onNext(t);
                this.subscriber.onNext(t);
            } catch (Throwable th) {
                Exceptions.throwOrReport(th, this, t);
            }
        }
    }
}
