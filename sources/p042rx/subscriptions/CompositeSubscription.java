package p042rx.subscriptions;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import p042rx.Subscription;
import p042rx.exceptions.Exceptions;

/* renamed from: rx.subscriptions.CompositeSubscription */
/* loaded from: classes6.dex */
public final class CompositeSubscription implements Subscription {
    private Set<Subscription> subscriptions;
    private volatile boolean unsubscribed;

    public CompositeSubscription() {
    }

    public CompositeSubscription(Subscription... subscriptionArr) {
        this.subscriptions = new HashSet(Arrays.asList(subscriptionArr));
    }

    @Override // p042rx.Subscription
    public boolean isUnsubscribed() {
        return this.unsubscribed;
    }

    public void add(Subscription subscription) {
        if (subscription.isUnsubscribed()) {
            return;
        }
        if (!this.unsubscribed) {
            synchronized (this) {
                if (!this.unsubscribed) {
                    if (this.subscriptions == null) {
                        this.subscriptions = new HashSet(4);
                    }
                    this.subscriptions.add(subscription);
                    return;
                }
            }
        }
        subscription.unsubscribe();
    }

    public void addAll(Subscription... subscriptionArr) {
        int r1 = 0;
        if (!this.unsubscribed) {
            synchronized (this) {
                if (!this.unsubscribed) {
                    if (this.subscriptions == null) {
                        this.subscriptions = new HashSet(subscriptionArr.length);
                    }
                    int length = subscriptionArr.length;
                    while (r1 < length) {
                        Subscription subscription = subscriptionArr[r1];
                        if (!subscription.isUnsubscribed()) {
                            this.subscriptions.add(subscription);
                        }
                        r1++;
                    }
                    return;
                }
            }
        }
        int length2 = subscriptionArr.length;
        while (r1 < length2) {
            subscriptionArr[r1].unsubscribe();
            r1++;
        }
    }

    public void remove(Subscription subscription) {
        Set<Subscription> set;
        if (this.unsubscribed) {
            return;
        }
        synchronized (this) {
            if (!this.unsubscribed && (set = this.subscriptions) != null) {
                boolean remove = set.remove(subscription);
                if (remove) {
                    subscription.unsubscribe();
                }
            }
        }
    }

    public void clear() {
        Set<Subscription> set;
        if (this.unsubscribed) {
            return;
        }
        synchronized (this) {
            if (!this.unsubscribed && (set = this.subscriptions) != null) {
                this.subscriptions = null;
                unsubscribeFromAll(set);
            }
        }
    }

    @Override // p042rx.Subscription
    public void unsubscribe() {
        if (this.unsubscribed) {
            return;
        }
        synchronized (this) {
            if (this.unsubscribed) {
                return;
            }
            this.unsubscribed = true;
            Set<Subscription> set = this.subscriptions;
            this.subscriptions = null;
            unsubscribeFromAll(set);
        }
    }

    private static void unsubscribeFromAll(Collection<Subscription> collection) {
        if (collection == null) {
            return;
        }
        ArrayList arrayList = null;
        for (Subscription subscription : collection) {
            try {
                subscription.unsubscribe();
            } catch (Throwable th) {
                if (arrayList == null) {
                    arrayList = new ArrayList();
                }
                arrayList.add(th);
            }
        }
        Exceptions.throwIfAny(arrayList);
    }

    public boolean hasSubscriptions() {
        Set<Subscription> set;
        boolean z = false;
        if (this.unsubscribed) {
            return false;
        }
        synchronized (this) {
            if (!this.unsubscribed && (set = this.subscriptions) != null && !set.isEmpty()) {
                z = true;
            }
        }
        return z;
    }
}
