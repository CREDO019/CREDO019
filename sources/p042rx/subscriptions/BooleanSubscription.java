package p042rx.subscriptions;

import java.util.concurrent.atomic.AtomicReference;
import p042rx.Subscription;
import p042rx.functions.Action0;

/* renamed from: rx.subscriptions.BooleanSubscription */
/* loaded from: classes6.dex */
public final class BooleanSubscription implements Subscription {
    static final Action0 EMPTY_ACTION = new Action0() { // from class: rx.subscriptions.BooleanSubscription.1
        @Override // p042rx.functions.Action0
        public void call() {
        }
    };
    final AtomicReference<Action0> actionRef;

    public BooleanSubscription() {
        this.actionRef = new AtomicReference<>();
    }

    private BooleanSubscription(Action0 action0) {
        this.actionRef = new AtomicReference<>(action0);
    }

    public static BooleanSubscription create() {
        return new BooleanSubscription();
    }

    public static BooleanSubscription create(Action0 action0) {
        return new BooleanSubscription(action0);
    }

    @Override // p042rx.Subscription
    public boolean isUnsubscribed() {
        return this.actionRef.get() == EMPTY_ACTION;
    }

    @Override // p042rx.Subscription
    public void unsubscribe() {
        Action0 andSet;
        Action0 action0 = this.actionRef.get();
        Action0 action02 = EMPTY_ACTION;
        if (action0 == action02 || (andSet = this.actionRef.getAndSet(action02)) == null || andSet == action02) {
            return;
        }
        andSet.call();
    }
}
