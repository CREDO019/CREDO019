package p042rx.internal.subscriptions;

import java.util.concurrent.atomic.AtomicReference;
import p042rx.Subscription;
import p042rx.exceptions.Exceptions;
import p042rx.functions.Cancellable;
import p042rx.plugins.RxJavaHooks;

/* renamed from: rx.internal.subscriptions.CancellableSubscription */
/* loaded from: classes6.dex */
public final class CancellableSubscription extends AtomicReference<Cancellable> implements Subscription {
    private static final long serialVersionUID = 5718521705281392066L;

    public CancellableSubscription(Cancellable cancellable) {
        super(cancellable);
    }

    @Override // p042rx.Subscription
    public boolean isUnsubscribed() {
        return get() == null;
    }

    @Override // p042rx.Subscription
    public void unsubscribe() {
        Cancellable andSet;
        if (get() == null || (andSet = getAndSet(null)) == null) {
            return;
        }
        try {
            andSet.cancel();
        } catch (Exception e) {
            Exceptions.throwIfFatal(e);
            RxJavaHooks.onError(e);
        }
    }
}
