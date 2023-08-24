package com.polidea.rxandroidble.internal.connection;

import bleshadow.javax.inject.Inject;
import com.polidea.rxandroidble.internal.operations.DisconnectOperation;
import com.polidea.rxandroidble.internal.serialization.ClientOperationQueue;
import p042rx.functions.Actions;

@ConnectionScope
/* loaded from: classes3.dex */
class DisconnectAction implements ConnectionSubscriptionWatcher {
    private final ClientOperationQueue clientOperationQueue;
    private final DisconnectOperation operationDisconnect;

    @Override // com.polidea.rxandroidble.internal.connection.ConnectionSubscriptionWatcher
    public void onConnectionSubscribed() {
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Inject
    public DisconnectAction(ClientOperationQueue clientOperationQueue, DisconnectOperation disconnectOperation) {
        this.clientOperationQueue = clientOperationQueue;
        this.operationDisconnect = disconnectOperation;
    }

    @Override // com.polidea.rxandroidble.internal.connection.ConnectionSubscriptionWatcher
    public void onConnectionUnsubscribed() {
        this.clientOperationQueue.queue(this.operationDisconnect).subscribe(Actions.empty(), Actions.toAction1(Actions.empty()));
    }
}
