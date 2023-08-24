package com.polidea.rxandroidble.internal.connection;

import com.polidea.rxandroidble.RxBleConnection;
import p042rx.Observable;

/* loaded from: classes3.dex */
public class ImmediateSerializedBatchAckStrategy implements RxBleConnection.WriteOperationAckStrategy {
    @Override // p042rx.functions.Func1
    public Observable<Boolean> call(Observable<Boolean> observable) {
        return observable;
    }
}
