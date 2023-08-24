package p042rx.internal.operators;

import java.util.concurrent.atomic.AtomicBoolean;
import p042rx.Single;
import p042rx.SingleEmitter;
import p042rx.SingleSubscriber;
import p042rx.Subscription;
import p042rx.exceptions.Exceptions;
import p042rx.functions.Action1;
import p042rx.functions.Cancellable;
import p042rx.internal.subscriptions.CancellableSubscription;
import p042rx.internal.subscriptions.SequentialSubscription;
import p042rx.plugins.RxJavaHooks;

/* renamed from: rx.internal.operators.SingleFromEmitter */
/* loaded from: classes6.dex */
public final class SingleFromEmitter<T> implements Single.OnSubscribe<T> {
    final Action1<SingleEmitter<T>> producer;

    @Override // p042rx.functions.Action1
    public /* bridge */ /* synthetic */ void call(Object obj) {
        call((SingleSubscriber) ((SingleSubscriber) obj));
    }

    public SingleFromEmitter(Action1<SingleEmitter<T>> action1) {
        this.producer = action1;
    }

    public void call(SingleSubscriber<? super T> singleSubscriber) {
        SingleEmitterImpl singleEmitterImpl = new SingleEmitterImpl(singleSubscriber);
        singleSubscriber.add(singleEmitterImpl);
        try {
            this.producer.call(singleEmitterImpl);
        } catch (Throwable th) {
            Exceptions.throwIfFatal(th);
            singleEmitterImpl.onError(th);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: rx.internal.operators.SingleFromEmitter$SingleEmitterImpl */
    /* loaded from: classes6.dex */
    public static final class SingleEmitterImpl<T> extends AtomicBoolean implements SingleEmitter<T>, Subscription {
        private static final long serialVersionUID = 8082834163465882809L;
        final SingleSubscriber<? super T> actual;
        final SequentialSubscription resource = new SequentialSubscription();

        SingleEmitterImpl(SingleSubscriber<? super T> singleSubscriber) {
            this.actual = singleSubscriber;
        }

        @Override // p042rx.Subscription
        public void unsubscribe() {
            if (compareAndSet(false, true)) {
                this.resource.unsubscribe();
            }
        }

        @Override // p042rx.Subscription
        public boolean isUnsubscribed() {
            return get();
        }

        @Override // p042rx.SingleEmitter
        public void onSuccess(T t) {
            if (compareAndSet(false, true)) {
                try {
                    this.actual.onSuccess(t);
                } finally {
                    this.resource.unsubscribe();
                }
            }
        }

        @Override // p042rx.SingleEmitter
        public void onError(Throwable th) {
            if (th == null) {
                th = new NullPointerException();
            }
            if (compareAndSet(false, true)) {
                try {
                    this.actual.onError(th);
                    return;
                } finally {
                    this.resource.unsubscribe();
                }
            }
            RxJavaHooks.onError(th);
        }

        @Override // p042rx.SingleEmitter
        public void setSubscription(Subscription subscription) {
            this.resource.update(subscription);
        }

        @Override // p042rx.SingleEmitter
        public void setCancellation(Cancellable cancellable) {
            setSubscription(new CancellableSubscription(cancellable));
        }
    }
}
