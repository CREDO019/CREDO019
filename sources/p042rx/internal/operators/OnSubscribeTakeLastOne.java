package p042rx.internal.operators;

import p042rx.Observable;
import p042rx.Subscriber;

/* renamed from: rx.internal.operators.OnSubscribeTakeLastOne */
/* loaded from: classes6.dex */
public final class OnSubscribeTakeLastOne<T> implements Observable.OnSubscribe<T> {
    final Observable<T> source;

    @Override // p042rx.functions.Action1
    public /* bridge */ /* synthetic */ void call(Object obj) {
        call((Subscriber) ((Subscriber) obj));
    }

    public OnSubscribeTakeLastOne(Observable<T> observable) {
        this.source = observable;
    }

    public void call(Subscriber<? super T> subscriber) {
        new TakeLastOneSubscriber(subscriber).subscribeTo(this.source);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: rx.internal.operators.OnSubscribeTakeLastOne$TakeLastOneSubscriber */
    /* loaded from: classes6.dex */
    public static final class TakeLastOneSubscriber<T> extends DeferredScalarSubscriber<T, T> {
        static final Object EMPTY = new Object();

        /* JADX WARN: Type inference failed for: r1v1, types: [R, java.lang.Object] */
        public TakeLastOneSubscriber(Subscriber<? super T> subscriber) {
            super(subscriber);
            this.value = EMPTY;
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // p042rx.Observer
        public void onNext(T t) {
            this.value = t;
        }

        @Override // p042rx.internal.operators.DeferredScalarSubscriber, p042rx.Observer
        public void onCompleted() {
            Object obj = this.value;
            if (obj == EMPTY) {
                complete();
            } else {
                complete(obj);
            }
        }
    }
}
