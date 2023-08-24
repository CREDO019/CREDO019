package com.polidea.rxandroidble.internal.util;

import p042rx.Observable;

/* loaded from: classes3.dex */
public class ActiveCharacteristicNotification {
    public final boolean isIndication;
    public final Observable<Observable<byte[]>> notificationObservable;

    public ActiveCharacteristicNotification(Observable<Observable<byte[]>> observable, boolean z) {
        this.notificationObservable = observable;
        this.isIndication = z;
    }
}
