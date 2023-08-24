package com.polidea.multiplatformbleadapter.utils;

import android.bluetooth.BluetoothGatt;
import com.polidea.rxandroidble.RxBleCustomOperation;
import com.polidea.rxandroidble.internal.connection.RxBleGattCallback;
import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;
import p042rx.Observable;
import p042rx.Scheduler;

/* loaded from: classes3.dex */
public class RefreshGattCustomOperation implements RxBleCustomOperation<Boolean> {
    @Override // com.polidea.rxandroidble.RxBleCustomOperation
    public Observable<Boolean> asObservable(final BluetoothGatt bluetoothGatt, RxBleGattCallback rxBleGattCallback, Scheduler scheduler) throws Throwable {
        return Observable.amb(Observable.fromCallable(new Callable<Boolean>() { // from class: com.polidea.multiplatformbleadapter.utils.RefreshGattCustomOperation.1
            /* JADX WARN: Can't rename method to resolve collision */
            /* JADX WARN: Removed duplicated region for block: B:17:0x0046  */
            /* JADX WARN: Removed duplicated region for block: B:18:0x0049  */
            @Override // java.util.concurrent.Callable
            /*
                Code decompiled incorrectly, please refer to instructions dump.
                To view partially-correct code enable 'Show inconsistent code' option in preferences
            */
            public java.lang.Boolean call() throws java.lang.Exception {
                /*
                    r6 = this;
                    r0 = 0
                    android.bluetooth.BluetoothGatt r1 = r2     // Catch: java.lang.Exception -> L37
                    java.lang.Class r1 = r1.getClass()     // Catch: java.lang.Exception -> L37
                    java.lang.String r2 = "refresh"
                    java.lang.Class[] r3 = new java.lang.Class[r0]     // Catch: java.lang.Exception -> L37
                    java.lang.reflect.Method r1 = r1.getMethod(r2, r3)     // Catch: java.lang.Exception -> L37
                    if (r1 != 0) goto L1a
                    java.lang.String r1 = "Could not find function BluetoothGatt.refresh()"
                    java.lang.Object[] r2 = new java.lang.Object[r0]     // Catch: java.lang.Exception -> L37
                    com.polidea.rxandroidble.internal.RxBleLog.m243d(r1, r2)     // Catch: java.lang.Exception -> L37
                    r1 = 0
                    goto L41
                L1a:
                    android.bluetooth.BluetoothGatt r2 = r2     // Catch: java.lang.Exception -> L37
                    java.lang.Object[] r3 = new java.lang.Object[r0]     // Catch: java.lang.Exception -> L37
                    java.lang.Object r1 = r1.invoke(r2, r3)     // Catch: java.lang.Exception -> L37
                    java.lang.Boolean r1 = (java.lang.Boolean) r1     // Catch: java.lang.Exception -> L37
                    boolean r1 = r1.booleanValue()     // Catch: java.lang.Exception -> L37
                    if (r1 != 0) goto L41
                    java.lang.String r2 = "BluetoothGatt.refresh() returned false"
                    java.lang.Object[] r3 = new java.lang.Object[r0]     // Catch: java.lang.Exception -> L32
                    com.polidea.rxandroidble.internal.RxBleLog.m243d(r2, r3)     // Catch: java.lang.Exception -> L32
                    goto L41
                L32:
                    r2 = move-exception
                    r5 = r2
                    r2 = r1
                    r1 = r5
                    goto L39
                L37:
                    r1 = move-exception
                    r2 = 0
                L39:
                    java.lang.Object[] r3 = new java.lang.Object[r0]
                    java.lang.String r4 = "Could not call function BluetoothGatt.refresh()"
                    com.polidea.rxandroidble.internal.RxBleLog.m242d(r1, r4, r3)
                    r1 = r2
                L41:
                    r2 = 1
                    java.lang.Object[] r2 = new java.lang.Object[r2]
                    if (r1 == 0) goto L49
                    java.lang.String r3 = "Success"
                    goto L4b
                L49:
                    java.lang.String r3 = "Failure"
                L4b:
                    r2[r0] = r3
                    java.lang.String r0 = "Calling BluetoothGatt.refresh() status: %s"
                    com.polidea.rxandroidble.internal.RxBleLog.m239i(r0, r2)
                    java.lang.Boolean r0 = java.lang.Boolean.valueOf(r1)
                    return r0
                */
                throw new UnsupportedOperationException("Method not decompiled: com.polidea.multiplatformbleadapter.utils.RefreshGattCustomOperation.CallableC37161.call():java.lang.Boolean");
            }
        }).subscribeOn(scheduler).delay(1L, TimeUnit.SECONDS, scheduler), rxBleGattCallback.observeDisconnect());
    }
}
