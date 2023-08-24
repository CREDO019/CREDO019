package com.polidea.rxandroidble.internal.connection;

import bleshadow.dagger.Subcomponent;
import com.polidea.rxandroidble.RxBleConnection;
import com.polidea.rxandroidble.internal.operations.ConnectOperation;
import java.util.Set;

@ConnectionScope
@Subcomponent(modules = {ConnectionModule.class, ConnectionModuleBinder.class})
/* loaded from: classes3.dex */
public interface ConnectionComponent {

    /* loaded from: classes3.dex */
    public interface Builder {
        ConnectionComponent build();

        Builder connectionModule(ConnectionModule connectionModule);
    }

    @ConnectionScope
    ConnectOperation connectOperation();

    @ConnectionScope
    Set<ConnectionSubscriptionWatcher> connectionSubscriptionWatchers();

    @ConnectionScope
    RxBleGattCallback gattCallback();

    @ConnectionScope
    RxBleConnection rxBleConnection();

    /* loaded from: classes3.dex */
    public static class NamedBooleans {
        public static final String AUTO_CONNECT = "autoConnect";
        public static final String SUPPRESS_OPERATION_CHECKS = "suppressOperationChecks";

        private NamedBooleans() {
        }
    }

    /* loaded from: classes3.dex */
    public static class NamedInts {
        static final String GATT_MTU_MINIMUM = "GATT_MTU_MINIMUM";
        static final String GATT_WRITE_MTU_OVERHEAD = "GATT_WRITE_MTU_OVERHEAD";

        private NamedInts() {
        }
    }
}
