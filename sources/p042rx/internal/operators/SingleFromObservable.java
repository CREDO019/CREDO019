package p042rx.internal.operators;

import java.util.NoSuchElementException;
import p042rx.Observable;
import p042rx.Single;
import p042rx.SingleSubscriber;
import p042rx.Subscriber;
import p042rx.plugins.RxJavaHooks;

/* renamed from: rx.internal.operators.SingleFromObservable */
/* loaded from: classes6.dex */
public final class SingleFromObservable<T> implements Single.OnSubscribe<T> {
    final Observable.OnSubscribe<T> source;

    @Override // p042rx.functions.Action1
    public /* bridge */ /* synthetic */ void call(Object obj) {
        call((SingleSubscriber) ((SingleSubscriber) obj));
    }

    public SingleFromObservable(Observable.OnSubscribe<T> onSubscribe) {
        this.source = onSubscribe;
    }

    public void call(SingleSubscriber<? super T> singleSubscriber) {
        WrapSingleIntoSubscriber wrapSingleIntoSubscriber = new WrapSingleIntoSubscriber(singleSubscriber);
        singleSubscriber.add(wrapSingleIntoSubscriber);
        this.source.call(wrapSingleIntoSubscriber);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: rx.internal.operators.SingleFromObservable$WrapSingleIntoSubscriber */
    /* loaded from: classes6.dex */
    public static final class WrapSingleIntoSubscriber<T> extends Subscriber<T> {
        static final int STATE_DONE = 2;
        static final int STATE_EMPTY = 0;
        static final int STATE_HAS_VALUE = 1;
        final SingleSubscriber<? super T> actual;
        int state;
        T value;

        /* JADX INFO: Access modifiers changed from: package-private */
        public WrapSingleIntoSubscriber(SingleSubscriber<? super T> singleSubscriber) {
            this.actual = singleSubscriber;
        }

        @Override // p042rx.Observer
        public void onNext(T t) {
            int r0 = this.state;
            if (r0 == 0) {
                this.state = 1;
                this.value = t;
            } else if (r0 == 1) {
                this.state = 2;
                this.actual.onError(new IndexOutOfBoundsException("The upstream produced more than one value"));
            }
        }

        @Override // p042rx.Observer
        public void onError(Throwable th) {
            if (this.state == 2) {
                RxJavaHooks.onError(th);
                return;
            }
            this.value = null;
            this.actual.onError(th);
        }

        @Override // p042rx.Observer
        public void onCompleted() {
            int r0 = this.state;
            if (r0 == 0) {
                this.actual.onError(new NoSuchElementException());
            } else if (r0 == 1) {
                this.state = 2;
                T t = this.value;
                this.value = null;
                this.actual.onSuccess(t);
            }
        }
    }
}
