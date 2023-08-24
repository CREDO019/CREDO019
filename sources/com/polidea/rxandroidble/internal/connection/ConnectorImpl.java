package com.polidea.rxandroidble.internal.connection;

import bleshadow.javax.inject.Inject;
import bleshadow.javax.inject.Named;
import com.polidea.rxandroidble.ConnectionSetup;
import com.polidea.rxandroidble.RxBleConnection;
import com.polidea.rxandroidble.internal.connection.ConnectionComponent;
import com.polidea.rxandroidble.internal.serialization.ClientOperationQueue;
import java.util.Set;
import java.util.concurrent.Callable;
import p042rx.Observable;
import p042rx.Scheduler;
import p042rx.functions.Action0;
import p042rx.functions.Func0;

/* loaded from: classes3.dex */
public class ConnectorImpl implements Connector {
    private final Scheduler callbacksScheduler;
    private final ClientOperationQueue clientOperationQueue;
    private final ConnectionComponent.Builder connectionComponentBuilder;

    @Inject
    public ConnectorImpl(ClientOperationQueue clientOperationQueue, ConnectionComponent.Builder builder, @Named("bluetooth_callbacks") Scheduler scheduler) {
        this.clientOperationQueue = clientOperationQueue;
        this.connectionComponentBuilder = builder;
        this.callbacksScheduler = scheduler;
    }

    @Override // com.polidea.rxandroidble.internal.connection.Connector
    public Observable<RxBleConnection> prepareConnection(final ConnectionSetup connectionSetup) {
        return Observable.defer(new Func0<Observable<RxBleConnection>>() { // from class: com.polidea.rxandroidble.internal.connection.ConnectorImpl.1
            @Override // p042rx.functions.Func0, java.util.concurrent.Callable
            public Observable<RxBleConnection> call() {
                final ConnectionComponent build = ConnectorImpl.this.connectionComponentBuilder.connectionModule(new ConnectionModule(connectionSetup)).build();
                Observable fromCallable = Observable.fromCallable(new Callable<RxBleConnection>() { // from class: com.polidea.rxandroidble.internal.connection.ConnectorImpl.1.1
                    /* JADX WARN: Can't rename method to resolve collision */
                    @Override // java.util.concurrent.Callable
                    public RxBleConnection call() throws Exception {
                        return build.rxBleConnection();
                    }
                });
                Observable queue = ConnectorImpl.this.clientOperationQueue.queue(build.connectOperation());
                Observable observeDisconnect = build.gattCallback().observeDisconnect();
                final Set<ConnectionSubscriptionWatcher> connectionSubscriptionWatchers = build.connectionSubscriptionWatchers();
                return Observable.merge(fromCallable.delaySubscription(queue), observeDisconnect).doOnSubscribe(new Action0() { // from class: com.polidea.rxandroidble.internal.connection.ConnectorImpl.1.3
                    @Override // p042rx.functions.Action0
                    public void call() {
                        for (ConnectionSubscriptionWatcher connectionSubscriptionWatcher : connectionSubscriptionWatchers) {
                            connectionSubscriptionWatcher.onConnectionSubscribed();
                        }
                    }
                }).doOnUnsubscribe(new Action0() { // from class: com.polidea.rxandroidble.internal.connection.ConnectorImpl.1.2
                    @Override // p042rx.functions.Action0
                    public void call() {
                        for (ConnectionSubscriptionWatcher connectionSubscriptionWatcher : connectionSubscriptionWatchers) {
                            connectionSubscriptionWatcher.onConnectionUnsubscribed();
                        }
                    }
                }).subscribeOn(ConnectorImpl.this.callbacksScheduler).unsubscribeOn(ConnectorImpl.this.callbacksScheduler);
            }
        });
    }
}
