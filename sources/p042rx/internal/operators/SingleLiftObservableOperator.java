package p042rx.internal.operators;

import p042rx.Observable;
import p042rx.Single;
import p042rx.SingleSubscriber;
import p042rx.Subscriber;
import p042rx.exceptions.Exceptions;
import p042rx.internal.operators.SingleFromObservable;
import p042rx.internal.producers.SingleProducer;
import p042rx.plugins.RxJavaHooks;

/* renamed from: rx.internal.operators.SingleLiftObservableOperator */
/* loaded from: classes6.dex */
public final class SingleLiftObservableOperator<T, R> implements Single.OnSubscribe<R> {
    final Observable.Operator<? extends R, ? super T> lift;
    final Single.OnSubscribe<T> source;

    @Override // p042rx.functions.Action1
    public /* bridge */ /* synthetic */ void call(Object obj) {
        call((SingleSubscriber) ((SingleSubscriber) obj));
    }

    public SingleLiftObservableOperator(Single.OnSubscribe<T> onSubscribe, Observable.Operator<? extends R, ? super T> operator) {
        this.source = onSubscribe;
        this.lift = operator;
    }

    public void call(SingleSubscriber<? super R> singleSubscriber) {
        SingleFromObservable.WrapSingleIntoSubscriber wrapSingleIntoSubscriber = new SingleFromObservable.WrapSingleIntoSubscriber(singleSubscriber);
        singleSubscriber.add(wrapSingleIntoSubscriber);
        try {
            Subscriber<? super T> call = RxJavaHooks.onSingleLift(this.lift).call(wrapSingleIntoSubscriber);
            SingleSubscriber wrap = wrap(call);
            call.onStart();
            this.source.call(wrap);
        } catch (Throwable th) {
            Exceptions.throwOrReport(th, singleSubscriber);
        }
    }

    public static <T> SingleSubscriber<T> wrap(Subscriber<T> subscriber) {
        WrapSubscriberIntoSingle wrapSubscriberIntoSingle = new WrapSubscriberIntoSingle(subscriber);
        subscriber.add(wrapSubscriberIntoSingle);
        return wrapSubscriberIntoSingle;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: rx.internal.operators.SingleLiftObservableOperator$WrapSubscriberIntoSingle */
    /* loaded from: classes6.dex */
    public static final class WrapSubscriberIntoSingle<T> extends SingleSubscriber<T> {
        final Subscriber<? super T> actual;

        /* JADX INFO: Access modifiers changed from: package-private */
        public WrapSubscriberIntoSingle(Subscriber<? super T> subscriber) {
            this.actual = subscriber;
        }

        @Override // p042rx.SingleSubscriber
        public void onSuccess(T t) {
            this.actual.setProducer(new SingleProducer(this.actual, t));
        }

        @Override // p042rx.SingleSubscriber
        public void onError(Throwable th) {
            this.actual.onError(th);
        }
    }
}
