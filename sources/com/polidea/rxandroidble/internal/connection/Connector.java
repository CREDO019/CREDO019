package com.polidea.rxandroidble.internal.connection;

import com.polidea.rxandroidble.ConnectionSetup;
import com.polidea.rxandroidble.RxBleConnection;
import p042rx.Observable;

/* loaded from: classes3.dex */
public interface Connector {
    Observable<RxBleConnection> prepareConnection(ConnectionSetup connectionSetup);
}
