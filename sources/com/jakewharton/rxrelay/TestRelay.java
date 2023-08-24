package com.jakewharton.rxrelay;

import com.jakewharton.rxrelay.RelaySubscriptionManager;
import java.util.concurrent.TimeUnit;
import p042rx.Observable;
import p042rx.Scheduler;
import p042rx.functions.Action0;
import p042rx.functions.Action1;
import p042rx.schedulers.TestScheduler;

/* loaded from: classes3.dex */
public final class TestRelay<T> extends Relay<T, T> {
    private final Scheduler.Worker innerScheduler;
    private final RelaySubscriptionManager<T> state;

    public static <T> TestRelay<T> create(TestScheduler testScheduler) {
        final RelaySubscriptionManager relaySubscriptionManager = new RelaySubscriptionManager();
        relaySubscriptionManager.onAdded = new Action1<RelaySubscriptionManager.RelayObserver<T>>() { // from class: com.jakewharton.rxrelay.TestRelay.1
            @Override // p042rx.functions.Action1
            public /* bridge */ /* synthetic */ void call(Object obj) {
                call((RelaySubscriptionManager.RelayObserver) ((RelaySubscriptionManager.RelayObserver) obj));
            }

            public void call(RelaySubscriptionManager.RelayObserver<T> relayObserver) {
                relayObserver.emitFirst(RelaySubscriptionManager.this.getLatest());
            }
        };
        return new TestRelay<>(relaySubscriptionManager, relaySubscriptionManager, testScheduler);
    }

    private TestRelay(Observable.OnSubscribe<T> onSubscribe, RelaySubscriptionManager<T> relaySubscriptionManager, TestScheduler testScheduler) {
        super(onSubscribe);
        this.state = relaySubscriptionManager;
        this.innerScheduler = testScheduler.createWorker();
    }

    @Override // p042rx.functions.Action1
    public void call(T t) {
        call(t, 0L);
    }

    void _call(T t) {
        for (RelaySubscriptionManager.RelayObserver<T> relayObserver : this.state.observers()) {
            relayObserver.onNext(t);
        }
    }

    public void call(final T t, long j) {
        this.innerScheduler.schedule(new Action0() { // from class: com.jakewharton.rxrelay.TestRelay.2
            /* JADX WARN: Multi-variable type inference failed */
            @Override // p042rx.functions.Action0
            public void call() {
                TestRelay.this._call(t);
            }
        }, j, TimeUnit.MILLISECONDS);
    }

    @Override // com.jakewharton.rxrelay.Relay
    public boolean hasObservers() {
        return this.state.observers().length > 0;
    }
}
