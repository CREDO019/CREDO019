package p042rx.internal.operators;

import java.util.concurrent.CancellationException;
import java.util.concurrent.atomic.AtomicBoolean;
import p042rx.Observable;
import p042rx.Single;
import p042rx.SingleSubscriber;
import p042rx.Subscriber;
import p042rx.plugins.RxJavaHooks;

/* renamed from: rx.internal.operators.SingleTakeUntilObservable */
/* loaded from: classes6.dex */
public final class SingleTakeUntilObservable<T, U> implements Single.OnSubscribe<T> {
    final Observable<? extends U> other;
    final Single.OnSubscribe<T> source;

    @Override // p042rx.functions.Action1
    public /* bridge */ /* synthetic */ void call(Object obj) {
        call((SingleSubscriber) ((SingleSubscriber) obj));
    }

    public SingleTakeUntilObservable(Single.OnSubscribe<T> onSubscribe, Observable<? extends U> observable) {
        this.source = onSubscribe;
        this.other = observable;
    }

    public void call(SingleSubscriber<? super T> singleSubscriber) {
        TakeUntilSourceSubscriber takeUntilSourceSubscriber = new TakeUntilSourceSubscriber(singleSubscriber);
        singleSubscriber.add(takeUntilSourceSubscriber);
        this.other.subscribe((Subscriber<? super Object>) takeUntilSourceSubscriber.other);
        this.source.call(takeUntilSourceSubscriber);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: rx.internal.operators.SingleTakeUntilObservable$TakeUntilSourceSubscriber */
    /* loaded from: classes6.dex */
    public static final class TakeUntilSourceSubscriber<T, U> extends SingleSubscriber<T> {
        final SingleSubscriber<? super T> actual;
        final AtomicBoolean once = new AtomicBoolean();
        final Subscriber<U> other;

        TakeUntilSourceSubscriber(SingleSubscriber<? super T> singleSubscriber) {
            this.actual = singleSubscriber;
            OtherSubscriber otherSubscriber = new OtherSubscriber();
            this.other = otherSubscriber;
            add(otherSubscriber);
        }

        @Override // p042rx.SingleSubscriber
        public void onSuccess(T t) {
            if (this.once.compareAndSet(false, true)) {
                unsubscribe();
                this.actual.onSuccess(t);
            }
        }

        @Override // p042rx.SingleSubscriber
        public void onError(Throwable th) {
            if (this.once.compareAndSet(false, true)) {
                unsubscribe();
                this.actual.onError(th);
                return;
            }
            RxJavaHooks.onError(th);
        }

        /* renamed from: rx.internal.operators.SingleTakeUntilObservable$TakeUntilSourceSubscriber$OtherSubscriber */
        /* loaded from: classes6.dex */
        final class OtherSubscriber extends Subscriber<U> {
            OtherSubscriber() {
            }

            @Override // p042rx.Observer
            public void onNext(U u) {
                onCompleted();
            }

            @Override // p042rx.Observer
            public void onError(Throwable th) {
                TakeUntilSourceSubscriber.this.onError(th);
            }

            @Override // p042rx.Observer
            public void onCompleted() {
                onError(new CancellationException("Single::takeUntil(Observable) - Stream was canceled before emitting a terminal event."));
            }
        }
    }
}
