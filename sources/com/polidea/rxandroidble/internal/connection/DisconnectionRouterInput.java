package com.polidea.rxandroidble.internal.connection;

import com.polidea.rxandroidble.exceptions.BleDisconnectedException;
import com.polidea.rxandroidble.exceptions.BleGattException;

/* loaded from: classes3.dex */
interface DisconnectionRouterInput {
    void onDisconnectedException(BleDisconnectedException bleDisconnectedException);

    void onGattConnectionStateException(BleGattException bleGattException);
}
