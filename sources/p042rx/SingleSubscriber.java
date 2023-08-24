package p042rx;

import p042rx.internal.util.SubscriptionList;

/* renamed from: rx.SingleSubscriber */
/* loaded from: classes4.dex */
public abstract class SingleSubscriber<T> implements Subscription {

    /* renamed from: cs */
    private final SubscriptionList f2548cs = new SubscriptionList();

    public abstract void onError(Throwable th);

    public abstract void onSuccess(T t);

    public final void add(Subscription subscription) {
        this.f2548cs.add(subscription);
    }

    @Override // p042rx.Subscription
    public final void unsubscribe() {
        this.f2548cs.unsubscribe();
    }

    @Override // p042rx.Subscription
    public final boolean isUnsubscribed() {
        return this.f2548cs.isUnsubscribed();
    }
}
