package p042rx.subscriptions;

import java.util.concurrent.Future;
import p042rx.Subscription;
import p042rx.functions.Action0;

/* renamed from: rx.subscriptions.Subscriptions */
/* loaded from: classes6.dex */
public final class Subscriptions {
    private static final Unsubscribed UNSUBSCRIBED = new Unsubscribed();

    private Subscriptions() {
        throw new IllegalStateException("No instances!");
    }

    public static Subscription empty() {
        return BooleanSubscription.create();
    }

    public static Subscription unsubscribed() {
        return UNSUBSCRIBED;
    }

    public static Subscription create(Action0 action0) {
        return BooleanSubscription.create(action0);
    }

    public static Subscription from(Future<?> future) {
        return new FutureSubscription(future);
    }

    /* renamed from: rx.subscriptions.Subscriptions$FutureSubscription */
    /* loaded from: classes6.dex */
    static final class FutureSubscription implements Subscription {

        /* renamed from: f */
        final Future<?> f2590f;

        public FutureSubscription(Future<?> future) {
            this.f2590f = future;
        }

        @Override // p042rx.Subscription
        public void unsubscribe() {
            this.f2590f.cancel(true);
        }

        @Override // p042rx.Subscription
        public boolean isUnsubscribed() {
            return this.f2590f.isCancelled();
        }
    }

    public static CompositeSubscription from(Subscription... subscriptionArr) {
        return new CompositeSubscription(subscriptionArr);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: rx.subscriptions.Subscriptions$Unsubscribed */
    /* loaded from: classes6.dex */
    public static final class Unsubscribed implements Subscription {
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
