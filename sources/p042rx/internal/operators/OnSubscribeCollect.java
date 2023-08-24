package p042rx.internal.operators;

import p042rx.Observable;
import p042rx.Subscriber;
import p042rx.exceptions.Exceptions;
import p042rx.functions.Action2;
import p042rx.functions.Func0;

/* renamed from: rx.internal.operators.OnSubscribeCollect */
/* loaded from: classes6.dex */
public final class OnSubscribeCollect<T, R> implements Observable.OnSubscribe<R> {
    final Func0<R> collectionFactory;
    final Action2<R, ? super T> collector;
    final Observable<T> source;

    @Override // p042rx.functions.Action1
    public /* bridge */ /* synthetic */ void call(Object obj) {
        call((Subscriber) ((Subscriber) obj));
    }

    public OnSubscribeCollect(Observable<T> observable, Func0<R> func0, Action2<R, ? super T> action2) {
        this.source = observable;
        this.collectionFactory = func0;
        this.collector = action2;
    }

    public void call(Subscriber<? super R> subscriber) {
        try {
            new CollectSubscriber(subscriber, this.collectionFactory.call(), this.collector).subscribeTo(this.source);
        } catch (Throwable th) {
            Exceptions.throwIfFatal(th);
            subscriber.onError(th);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: rx.internal.operators.OnSubscribeCollect$CollectSubscriber */
    /* loaded from: classes6.dex */
    public static final class CollectSubscriber<T, R> extends DeferredScalarSubscriberSafe<T, R> {
        final Action2<R, ? super T> collector;

        public CollectSubscriber(Subscriber<? super R> subscriber, R r, Action2<R, ? super T> action2) {
            super(subscriber);
            this.value = r;
            this.hasValue = true;
            this.collector = action2;
        }

        @Override // p042rx.Observer
        public void onNext(T t) {
            if (this.done) {
                return;
            }
            try {
                this.collector.call(this.value, t);
            } catch (Throwable th) {
                Exceptions.throwIfFatal(th);
                unsubscribe();
                onError(th);
            }
        }
    }
}
