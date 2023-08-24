package com.polidea.rxandroidble.internal.connection;

import bleshadow.dagger.Module;
import bleshadow.dagger.Provides;
import bleshadow.javax.inject.Named;
import bleshadow.javax.inject.Provider;
import com.polidea.rxandroidble.ConnectionSetup;
import com.polidea.rxandroidble.Timeout;
import com.polidea.rxandroidble.internal.connection.ConnectionComponent;
import com.polidea.rxandroidble.internal.operations.TimeoutConfiguration;
import com.polidea.rxandroidble.internal.util.CharacteristicPropertiesParser;
import p042rx.Scheduler;

@Module
/* loaded from: classes3.dex */
public class ConnectionModule {
    public static final String OPERATION_TIMEOUT = "operation-timeout";
    final boolean autoConnect;
    private final Timeout operationTimeout;
    final boolean suppressOperationCheck;

    /* JADX INFO: Access modifiers changed from: package-private */
    public ConnectionModule(ConnectionSetup connectionSetup) {
        this.autoConnect = connectionSetup.autoConnect;
        this.suppressOperationCheck = connectionSetup.suppressOperationCheck;
        this.operationTimeout = connectionSetup.operationTimeout;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Named(ConnectionComponent.NamedBooleans.AUTO_CONNECT)
    @ConnectionScope
    @Provides
    public boolean provideAutoConnect() {
        return this.autoConnect;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Named("operation-timeout")
    @Provides
    public TimeoutConfiguration providesOperationTimeoutConf(@Named("timeout") Scheduler scheduler) {
        return new TimeoutConfiguration(this.operationTimeout.timeout, this.operationTimeout.timeUnit, scheduler);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Provides
    public IllegalOperationHandler provideIllegalOperationHandler(Provider<LoggingIllegalOperationHandler> provider, Provider<ThrowingIllegalOperationHandler> provider2) {
        if (this.suppressOperationCheck) {
            return provider.get();
        }
        return provider2.get();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Provides
    public CharacteristicPropertiesParser provideCharacteristicPropertiesParser() {
        return new CharacteristicPropertiesParser(1, 2, 4, 8, 16, 32, 64);
    }
}
