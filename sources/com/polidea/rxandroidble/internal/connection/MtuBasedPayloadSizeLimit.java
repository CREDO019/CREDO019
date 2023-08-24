package com.polidea.rxandroidble.internal.connection;

import bleshadow.javax.inject.Inject;
import bleshadow.javax.inject.Named;
import com.polidea.rxandroidble.RxBleConnection;

@ConnectionScope
/* loaded from: classes3.dex */
class MtuBasedPayloadSizeLimit implements PayloadSizeLimitProvider {
    private final int gattWriteMtuOverhead;
    private final RxBleConnection rxBleConnection;

    /* JADX INFO: Access modifiers changed from: package-private */
    @Inject
    public MtuBasedPayloadSizeLimit(RxBleConnection rxBleConnection, @Named("GATT_WRITE_MTU_OVERHEAD") int r2) {
        this.rxBleConnection = rxBleConnection;
        this.gattWriteMtuOverhead = r2;
    }

    @Override // com.polidea.rxandroidble.internal.connection.PayloadSizeLimitProvider
    public int getPayloadSizeLimit() {
        return this.rxBleConnection.getMtu() - this.gattWriteMtuOverhead;
    }
}
