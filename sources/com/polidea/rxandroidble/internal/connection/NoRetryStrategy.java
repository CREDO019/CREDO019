package com.polidea.rxandroidble.internal.connection;

import com.polidea.rxandroidble.RxBleConnection;
import p042rx.Observable;
import p042rx.functions.Func1;

/* loaded from: classes3.dex */
public class NoRetryStrategy implements RxBleConnection.WriteOperationRetryStrategy {
    @Override // p042rx.functions.Func1
    public Observable<RxBleConnection.WriteOperationRetryStrategy.LongWriteFailure> call(Observable<RxBleConnection.WriteOperationRetryStrategy.LongWriteFailure> observable) {
        return observable.flatMap(new Func1<RxBleConnection.WriteOperationRetryStrategy.LongWriteFailure, Observable<RxBleConnection.WriteOperationRetryStrategy.LongWriteFailure>>() { // from class: com.polidea.rxandroidble.internal.connection.NoRetryStrategy.1
            @Override // p042rx.functions.Func1
            public Observable<RxBleConnection.WriteOperationRetryStrategy.LongWriteFailure> call(RxBleConnection.WriteOperationRetryStrategy.LongWriteFailure longWriteFailure) {
                return Observable.error(longWriteFailure.getCause());
            }
        });
    }
}
