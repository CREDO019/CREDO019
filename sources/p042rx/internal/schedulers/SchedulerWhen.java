package p042rx.internal.schedulers;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicReference;
import p042rx.Completable;
import p042rx.CompletableSubscriber;
import p042rx.Observable;
import p042rx.Observer;
import p042rx.Scheduler;
import p042rx.Subscription;
import p042rx.functions.Action0;
import p042rx.functions.Func1;
import p042rx.internal.operators.BufferUntilSubscriber;
import p042rx.observers.SerializedObserver;
import p042rx.subjects.PublishSubject;
import p042rx.subscriptions.Subscriptions;

/* renamed from: rx.internal.schedulers.SchedulerWhen */
/* loaded from: classes6.dex */
public class SchedulerWhen extends Scheduler implements Subscription {
    static final Subscription SUBSCRIBED = new Subscription() { // from class: rx.internal.schedulers.SchedulerWhen.3
        @Override // p042rx.Subscription
        public boolean isUnsubscribed() {
            return false;
        }

        @Override // p042rx.Subscription
        public void unsubscribe() {
        }
    };
    static final Subscription UNSUBSCRIBED = Subscriptions.unsubscribed();
    private final Scheduler actualScheduler;
    private final Subscription subscription;
    private final Observer<Observable<Completable>> workerObserver;

    public SchedulerWhen(Func1<Observable<Observable<Completable>>, Completable> func1, Scheduler scheduler) {
        this.actualScheduler = scheduler;
        PublishSubject create = PublishSubject.create();
        this.workerObserver = new SerializedObserver(create);
        this.subscription = func1.call(create.onBackpressureBuffer()).subscribe();
    }

    @Override // p042rx.Subscription
    public void unsubscribe() {
        this.subscription.unsubscribe();
    }

    @Override // p042rx.Subscription
    public boolean isUnsubscribed() {
        return this.subscription.isUnsubscribed();
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // p042rx.Scheduler
    public Scheduler.Worker createWorker() {
        final Scheduler.Worker createWorker = this.actualScheduler.createWorker();
        BufferUntilSubscriber create = BufferUntilSubscriber.create();
        final SerializedObserver serializedObserver = new SerializedObserver(create);
        Object map = create.map(new Func1<ScheduledAction, Completable>() { // from class: rx.internal.schedulers.SchedulerWhen.1
            @Override // p042rx.functions.Func1
            public Completable call(final ScheduledAction scheduledAction) {
                return Completable.create(new Completable.OnSubscribe() { // from class: rx.internal.schedulers.SchedulerWhen.1.1
                    @Override // p042rx.functions.Action1
                    public void call(CompletableSubscriber completableSubscriber) {
                        completableSubscriber.onSubscribe(scheduledAction);
                        scheduledAction.call(createWorker, completableSubscriber);
                    }
                });
            }
        });
        Scheduler.Worker worker = new Scheduler.Worker() { // from class: rx.internal.schedulers.SchedulerWhen.2
            private final AtomicBoolean unsubscribed = new AtomicBoolean();

            @Override // p042rx.Subscription
            public void unsubscribe() {
                if (this.unsubscribed.compareAndSet(false, true)) {
                    createWorker.unsubscribe();
                    serializedObserver.onCompleted();
                }
            }

            @Override // p042rx.Subscription
            public boolean isUnsubscribed() {
                return this.unsubscribed.get();
            }

            @Override // p042rx.Scheduler.Worker
            public Subscription schedule(Action0 action0, long j, TimeUnit timeUnit) {
                DelayedAction delayedAction = new DelayedAction(action0, j, timeUnit);
                serializedObserver.onNext(delayedAction);
                return delayedAction;
            }

            @Override // p042rx.Scheduler.Worker
            public Subscription schedule(Action0 action0) {
                ImmediateAction immediateAction = new ImmediateAction(action0);
                serializedObserver.onNext(immediateAction);
                return immediateAction;
            }
        };
        this.workerObserver.onNext(map);
        return worker;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: rx.internal.schedulers.SchedulerWhen$ScheduledAction */
    /* loaded from: classes6.dex */
    public static abstract class ScheduledAction extends AtomicReference<Subscription> implements Subscription {
        protected abstract Subscription callActual(Scheduler.Worker worker, CompletableSubscriber completableSubscriber);

        public ScheduledAction() {
            super(SchedulerWhen.SUBSCRIBED);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void call(Scheduler.Worker worker, CompletableSubscriber completableSubscriber) {
            Subscription subscription = get();
            if (subscription != SchedulerWhen.UNSUBSCRIBED && subscription == SchedulerWhen.SUBSCRIBED) {
                Subscription callActual = callActual(worker, completableSubscriber);
                if (compareAndSet(SchedulerWhen.SUBSCRIBED, callActual)) {
                    return;
                }
                callActual.unsubscribe();
            }
        }

        @Override // p042rx.Subscription
        public boolean isUnsubscribed() {
            return get().isUnsubscribed();
        }

        @Override // p042rx.Subscription
        public void unsubscribe() {
            Subscription subscription;
            Subscription subscription2 = SchedulerWhen.UNSUBSCRIBED;
            do {
                subscription = get();
                if (subscription == SchedulerWhen.UNSUBSCRIBED) {
                    return;
                }
            } while (!compareAndSet(subscription, subscription2));
            if (subscription != SchedulerWhen.SUBSCRIBED) {
                subscription.unsubscribe();
            }
        }
    }

    /* renamed from: rx.internal.schedulers.SchedulerWhen$ImmediateAction */
    /* loaded from: classes6.dex */
    static class ImmediateAction extends ScheduledAction {
        private final Action0 action;

        public ImmediateAction(Action0 action0) {
            this.action = action0;
        }

        @Override // p042rx.internal.schedulers.SchedulerWhen.ScheduledAction
        protected Subscription callActual(Scheduler.Worker worker, CompletableSubscriber completableSubscriber) {
            return worker.schedule(new OnCompletedAction(this.action, completableSubscriber));
        }
    }

    /* renamed from: rx.internal.schedulers.SchedulerWhen$DelayedAction */
    /* loaded from: classes6.dex */
    static class DelayedAction extends ScheduledAction {
        private final Action0 action;
        private final long delayTime;
        private final TimeUnit unit;

        public DelayedAction(Action0 action0, long j, TimeUnit timeUnit) {
            this.action = action0;
            this.delayTime = j;
            this.unit = timeUnit;
        }

        @Override // p042rx.internal.schedulers.SchedulerWhen.ScheduledAction
        protected Subscription callActual(Scheduler.Worker worker, CompletableSubscriber completableSubscriber) {
            return worker.schedule(new OnCompletedAction(this.action, completableSubscriber), this.delayTime, this.unit);
        }
    }

    /* renamed from: rx.internal.schedulers.SchedulerWhen$OnCompletedAction */
    /* loaded from: classes6.dex */
    static class OnCompletedAction implements Action0 {
        private Action0 action;
        private CompletableSubscriber actionCompletable;

        public OnCompletedAction(Action0 action0, CompletableSubscriber completableSubscriber) {
            this.action = action0;
            this.actionCompletable = completableSubscriber;
        }

        @Override // p042rx.functions.Action0
        public void call() {
            try {
                this.action.call();
            } finally {
                this.actionCompletable.onCompleted();
            }
        }
    }
}
