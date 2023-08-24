package p042rx.internal.operators;

import java.util.concurrent.atomic.AtomicBoolean;
import p042rx.Completable;
import p042rx.CompletableEmitter;
import p042rx.CompletableSubscriber;
import p042rx.Subscription;
import p042rx.exceptions.Exceptions;
import p042rx.functions.Action1;
import p042rx.functions.Cancellable;
import p042rx.internal.subscriptions.CancellableSubscription;
import p042rx.internal.subscriptions.SequentialSubscription;
import p042rx.plugins.RxJavaHooks;

/* renamed from: rx.internal.operators.CompletableFromEmitter */
/* loaded from: classes6.dex */
public final class CompletableFromEmitter implements Completable.OnSubscribe {
    final Action1<CompletableEmitter> producer;

    public CompletableFromEmitter(Action1<CompletableEmitter> action1) {
        this.producer = action1;
    }

    @Override // p042rx.functions.Action1
    public void call(CompletableSubscriber completableSubscriber) {
        FromEmitter fromEmitter = new FromEmitter(completableSubscriber);
        completableSubscriber.onSubscribe(fromEmitter);
        try {
            this.producer.call(fromEmitter);
        } catch (Throwable th) {
            Exceptions.throwIfFatal(th);
            fromEmitter.onError(th);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: rx.internal.operators.CompletableFromEmitter$FromEmitter */
    /* loaded from: classes6.dex */
    public static final class FromEmitter extends AtomicBoolean implements CompletableEmitter, Subscription {
        private static final long serialVersionUID = 5539301318568668881L;
        final CompletableSubscriber actual;
        final SequentialSubscription resource = new SequentialSubscription();

        public FromEmitter(CompletableSubscriber completableSubscriber) {
            this.actual = completableSubscriber;
        }

        @Override // p042rx.CompletableEmitter
        public void onCompleted() {
            if (compareAndSet(false, true)) {
                try {
                    this.actual.onCompleted();
                } finally {
                    this.resource.unsubscribe();
                }
            }
        }

        @Override // p042rx.CompletableEmitter
        public void onError(Throwable th) {
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

        @Override // p042rx.CompletableEmitter
        public void setSubscription(Subscription subscription) {
            this.resource.update(subscription);
        }

        @Override // p042rx.CompletableEmitter
        public void setCancellation(Cancellable cancellable) {
            setSubscription(new CancellableSubscription(cancellable));
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
    }
}
