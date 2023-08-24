package p042rx.internal.schedulers;

import java.util.concurrent.Future;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicReference;
import p042rx.Subscription;
import p042rx.exceptions.OnErrorNotImplementedException;
import p042rx.functions.Action0;
import p042rx.internal.util.SubscriptionList;
import p042rx.plugins.RxJavaHooks;
import p042rx.subscriptions.CompositeSubscription;

/* renamed from: rx.internal.schedulers.ScheduledAction */
/* loaded from: classes6.dex */
public final class ScheduledAction extends AtomicReference<Thread> implements Runnable, Subscription {
    private static final long serialVersionUID = -3962399486978279857L;
    final Action0 action;
    final SubscriptionList cancel;

    public ScheduledAction(Action0 action0) {
        this.action = action0;
        this.cancel = new SubscriptionList();
    }

    public ScheduledAction(Action0 action0, CompositeSubscription compositeSubscription) {
        this.action = action0;
        this.cancel = new SubscriptionList(new Remover(this, compositeSubscription));
    }

    public ScheduledAction(Action0 action0, SubscriptionList subscriptionList) {
        this.action = action0;
        this.cancel = new SubscriptionList(new Remover2(this, subscriptionList));
    }

    @Override // java.lang.Runnable
    public void run() {
        try {
            try {
                lazySet(Thread.currentThread());
                this.action.call();
            } catch (OnErrorNotImplementedException e) {
                signalError(new IllegalStateException("Exception thrown on Scheduler.Worker thread. Add `onError` handling.", e));
            } catch (Throwable th) {
                signalError(new IllegalStateException("Fatal Exception thrown on Scheduler.Worker thread.", th));
            }
        } finally {
            unsubscribe();
        }
    }

    void signalError(Throwable th) {
        RxJavaHooks.onError(th);
        Thread currentThread = Thread.currentThread();
        currentThread.getUncaughtExceptionHandler().uncaughtException(currentThread, th);
    }

    @Override // p042rx.Subscription
    public boolean isUnsubscribed() {
        return this.cancel.isUnsubscribed();
    }

    @Override // p042rx.Subscription
    public void unsubscribe() {
        if (this.cancel.isUnsubscribed()) {
            return;
        }
        this.cancel.unsubscribe();
    }

    public void add(Subscription subscription) {
        this.cancel.add(subscription);
    }

    public void add(Future<?> future) {
        this.cancel.add(new FutureCompleter(future));
    }

    public void addParent(CompositeSubscription compositeSubscription) {
        this.cancel.add(new Remover(this, compositeSubscription));
    }

    public void addParent(SubscriptionList subscriptionList) {
        this.cancel.add(new Remover2(this, subscriptionList));
    }

    /* renamed from: rx.internal.schedulers.ScheduledAction$FutureCompleter */
    /* loaded from: classes6.dex */
    final class FutureCompleter implements Subscription {

        /* renamed from: f */
        private final Future<?> f2571f;

        FutureCompleter(Future<?> future) {
            this.f2571f = future;
        }

        @Override // p042rx.Subscription
        public void unsubscribe() {
            if (ScheduledAction.this.get() != Thread.currentThread()) {
                this.f2571f.cancel(true);
            } else {
                this.f2571f.cancel(false);
            }
        }

        @Override // p042rx.Subscription
        public boolean isUnsubscribed() {
            return this.f2571f.isCancelled();
        }
    }

    /* renamed from: rx.internal.schedulers.ScheduledAction$Remover */
    /* loaded from: classes6.dex */
    static final class Remover extends AtomicBoolean implements Subscription {
        private static final long serialVersionUID = 247232374289553518L;
        final CompositeSubscription parent;

        /* renamed from: s */
        final ScheduledAction f2572s;

        public Remover(ScheduledAction scheduledAction, CompositeSubscription compositeSubscription) {
            this.f2572s = scheduledAction;
            this.parent = compositeSubscription;
        }

        @Override // p042rx.Subscription
        public boolean isUnsubscribed() {
            return this.f2572s.isUnsubscribed();
        }

        @Override // p042rx.Subscription
        public void unsubscribe() {
            if (compareAndSet(false, true)) {
                this.parent.remove(this.f2572s);
            }
        }
    }

    /* renamed from: rx.internal.schedulers.ScheduledAction$Remover2 */
    /* loaded from: classes6.dex */
    static final class Remover2 extends AtomicBoolean implements Subscription {
        private static final long serialVersionUID = 247232374289553518L;
        final SubscriptionList parent;

        /* renamed from: s */
        final ScheduledAction f2573s;

        public Remover2(ScheduledAction scheduledAction, SubscriptionList subscriptionList) {
            this.f2573s = scheduledAction;
            this.parent = subscriptionList;
        }

        @Override // p042rx.Subscription
        public boolean isUnsubscribed() {
            return this.f2573s.isUnsubscribed();
        }

        @Override // p042rx.Subscription
        public void unsubscribe() {
            if (compareAndSet(false, true)) {
                this.parent.remove(this.f2573s);
            }
        }
    }
}
