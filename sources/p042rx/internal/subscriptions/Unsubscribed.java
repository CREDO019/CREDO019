package p042rx.internal.subscriptions;

import p042rx.Subscription;

/* renamed from: rx.internal.subscriptions.Unsubscribed */
/* loaded from: classes6.dex */
public enum Unsubscribed implements Subscription {
    INSTANCE;

    @Override // p042rx.Subscription
    public boolean isUnsubscribed() {
        return true;
    }

    @Override // p042rx.Subscription
    public void unsubscribe() {
    }
}
