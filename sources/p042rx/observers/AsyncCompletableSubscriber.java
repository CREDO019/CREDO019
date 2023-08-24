package p042rx.observers;

import java.util.concurrent.atomic.AtomicReference;
import p042rx.CompletableSubscriber;
import p042rx.Subscription;
import p042rx.plugins.RxJavaHooks;

/* renamed from: rx.observers.AsyncCompletableSubscriber */
/* loaded from: classes6.dex */
public abstract class AsyncCompletableSubscriber implements CompletableSubscriber, Subscription {
    static final Unsubscribed UNSUBSCRIBED = new Unsubscribed();
    private final AtomicReference<Subscription> upstream = new AtomicReference<>();

    protected void onStart() {
    }

    @Override // p042rx.CompletableSubscriber
    public final void onSubscribe(Subscription subscription) {
        if (!this.upstream.compareAndSet(null, subscription)) {
            subscription.unsubscribe();
            if (this.upstream.get() != UNSUBSCRIBED) {
                RxJavaHooks.onError(new IllegalStateException("Subscription already set!"));
                return;
            }
            return;
        }
        onStart();
    }

    @Override // p042rx.Subscription
    public final boolean isUnsubscribed() {
        return this.upstream.get() == UNSUBSCRIBED;
    }

    protected final void clear() {
        this.upstream.set(UNSUBSCRIBED);
    }

    @Override // p042rx.Subscription
    public final void unsubscribe() {
        Subscription andSet;
        Subscription subscription = this.upstream.get();
        Unsubscribed unsubscribed = UNSUBSCRIBED;
        if (subscription == unsubscribed || (andSet = this.upstream.getAndSet(unsubscribed)) == null || andSet == unsubscribed) {
            return;
        }
        andSet.unsubscribe();
    }

    /* renamed from: rx.observers.AsyncCompletableSubscriber$Unsubscribed */
    /* loaded from: classes6.dex */
    static final class Unsubscribed implements Subscription {
        @Override // p042rx.Subscription
        public boolean isUnsubscribed() {
            return true;
        }

        @Override // p042rx.Subscription
        public void unsubscribe() {
        }

        Unsubscribed() {
        }
    }
}
