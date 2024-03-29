package com.polidea.rxandroidble.internal;

import bleshadow.dagger.internal.Factory;
import bleshadow.dagger.internal.Preconditions;
import bleshadow.javax.inject.Provider;
import com.jakewharton.rxrelay.BehaviorRelay;
import com.polidea.rxandroidble.RxBleConnection;
import com.polidea.rxandroidble.internal.connection.ConnectionStateChangeListener;

/* loaded from: classes3.dex */
public final class DeviceModule_ProvideConnectionStateChangeListenerFactory implements Factory<ConnectionStateChangeListener> {
    private final Provider<BehaviorRelay<RxBleConnection.RxBleConnectionState>> connectionStateBehaviorRelayProvider;

    public DeviceModule_ProvideConnectionStateChangeListenerFactory(Provider<BehaviorRelay<RxBleConnection.RxBleConnectionState>> provider) {
        this.connectionStateBehaviorRelayProvider = provider;
    }

    @Override // bleshadow.javax.inject.Provider
    public ConnectionStateChangeListener get() {
        return (ConnectionStateChangeListener) Preconditions.checkNotNull(DeviceModule.provideConnectionStateChangeListener(this.connectionStateBehaviorRelayProvider.get()), "Cannot return null from a non-@Nullable @Provides method");
    }

    public static DeviceModule_ProvideConnectionStateChangeListenerFactory create(Provider<BehaviorRelay<RxBleConnection.RxBleConnectionState>> provider) {
        return new DeviceModule_ProvideConnectionStateChangeListenerFactory(provider);
    }

    public static ConnectionStateChangeListener proxyProvideConnectionStateChangeListener(BehaviorRelay<RxBleConnection.RxBleConnectionState> behaviorRelay) {
        return (ConnectionStateChangeListener) Preconditions.checkNotNull(DeviceModule.provideConnectionStateChangeListener(behaviorRelay), "Cannot return null from a non-@Nullable @Provides method");
    }
}
