package p042rx.internal.operators;

import p042rx.Observable;
import p042rx.Producer;
import p042rx.Subscriber;
import p042rx.functions.Action1;

/* renamed from: rx.internal.operators.OperatorDoOnRequest */
/* loaded from: classes6.dex */
public class OperatorDoOnRequest<T> implements Observable.Operator<T, T> {
    final Action1<? super Long> request;

    @Override // p042rx.functions.Func1
    public /* bridge */ /* synthetic */ Object call(Object obj) {
        return call((Subscriber) ((Subscriber) obj));
    }

    public OperatorDoOnRequest(Action1<? super Long> action1) {
        this.request = action1;
    }

    public Subscriber<? super T> call(Subscriber<? super T> subscriber) {
        final ParentSubscriber parentSubscriber = new ParentSubscriber(subscriber);
        subscriber.setProducer(new Producer() { // from class: rx.internal.operators.OperatorDoOnRequest.1
            @Override // p042rx.Producer
            public void request(long j) {
                OperatorDoOnRequest.this.request.call(Long.valueOf(j));
                parentSubscriber.requestMore(j);
            }
        });
        subscriber.add(parentSubscriber);
        return parentSubscriber;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: rx.internal.operators.OperatorDoOnRequest$ParentSubscriber */
    /* loaded from: classes6.dex */
    public static final class ParentSubscriber<T> extends Subscriber<T> {
        private final Subscriber<? super T> child;

        ParentSubscriber(Subscriber<? super T> subscriber) {
            this.child = subscriber;
            request(0L);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void requestMore(long j) {
            request(j);
        }

        @Override // p042rx.Observer
        public void onCompleted() {
            this.child.onCompleted();
        }

        @Override // p042rx.Observer
        public void onError(Throwable th) {
            this.child.onError(th);
        }

        @Override // p042rx.Observer
        public void onNext(T t) {
            this.child.onNext(t);
        }
    }
}
