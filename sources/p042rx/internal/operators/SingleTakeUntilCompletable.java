package p042rx.internal.operators;

import java.util.concurrent.CancellationException;
import java.util.concurrent.atomic.AtomicBoolean;
import p042rx.Completable;
import p042rx.CompletableSubscriber;
import p042rx.Single;
import p042rx.SingleSubscriber;
import p042rx.Subscription;
import p042rx.plugins.RxJavaHooks;

/* renamed from: rx.internal.operators.SingleTakeUntilCompletable */
/* loaded from: classes6.dex */
public final class SingleTakeUntilCompletable<T> implements Single.OnSubscribe<T> {
    final Completable other;
    final Single.OnSubscribe<T> source;

    @Override // p042rx.functions.Action1
    public /* bridge */ /* synthetic */ void call(Object obj) {
        call((SingleSubscriber) ((SingleSubscriber) obj));
    }

    public SingleTakeUntilCompletable(Single.OnSubscribe<T> onSubscribe, Completable completable) {
        this.source = onSubscribe;
        this.other = completable;
    }

    public void call(SingleSubscriber<? super T> singleSubscriber) {
        TakeUntilSourceSubscriber takeUntilSourceSubscriber = new TakeUntilSourceSubscriber(singleSubscriber);
        singleSubscriber.add(takeUntilSourceSubscriber);
        this.other.subscribe(takeUntilSourceSubscriber);
        this.source.call(takeUntilSourceSubscriber);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: rx.internal.operators.SingleTakeUntilCompletable$TakeUntilSourceSubscriber */
    /* loaded from: classes6.dex */
    public static final class TakeUntilSourceSubscriber<T> extends SingleSubscriber<T> implements CompletableSubscriber {
        final SingleSubscriber<? super T> actual;
        final AtomicBoolean once = new AtomicBoolean();

        TakeUntilSourceSubscriber(SingleSubscriber<? super T> singleSubscriber) {
            this.actual = singleSubscriber;
        }

        @Override // p042rx.CompletableSubscriber
        public void onSubscribe(Subscription subscription) {
            add(subscription);
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

        @Override // p042rx.CompletableSubscriber
        public void onCompleted() {
            onError(new CancellationException("Single::takeUntil(Completable) - Stream was canceled before emitting a terminal event."));
        }
    }
}
