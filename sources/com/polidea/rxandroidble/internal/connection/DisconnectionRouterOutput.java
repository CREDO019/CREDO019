package com.polidea.rxandroidble.internal.connection;

import com.polidea.rxandroidble.exceptions.BleException;
import p042rx.Observable;

/* loaded from: classes3.dex */
public interface DisconnectionRouterOutput {
    <T> Observable<T> asErrorOnlyObservable();

    Observable<BleException> asValueOnlyObservable();
}
