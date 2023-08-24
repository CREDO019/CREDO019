package p042rx.subscriptions;

import p042rx.Subscription;
import p042rx.internal.subscriptions.SequentialSubscription;

/* renamed from: rx.subscriptions.SerialSubscription */
/* loaded from: classes6.dex */
public final class SerialSubscription implements Subscription {
    final SequentialSubscription state = new SequentialSubscription();

    @Override // p042rx.Subscription
    public boolean isUnsubscribed() {
        return this.state.isUnsubscribed();
    }

    @Override // p042rx.Subscription
    public void unsubscribe() {
        this.state.unsubscribe();
    }

    public void set(Subscription subscription) {
        if (subscription == null) {
            throw new IllegalArgumentException("Subscription can not be null");
        }
        this.state.update(subscription);
    }

    public Subscription get() {
        return this.state.current();
    }
}
