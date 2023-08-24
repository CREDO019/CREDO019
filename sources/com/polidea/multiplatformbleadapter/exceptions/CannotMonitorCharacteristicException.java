package com.polidea.multiplatformbleadapter.exceptions;

import com.polidea.multiplatformbleadapter.Characteristic;

/* loaded from: classes3.dex */
public class CannotMonitorCharacteristicException extends RuntimeException {
    private Characteristic characteristic;

    public CannotMonitorCharacteristicException(Characteristic characteristic) {
        this.characteristic = characteristic;
    }

    public Characteristic getCharacteristic() {
        return this.characteristic;
    }
}
