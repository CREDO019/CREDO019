package com.polidea.rxandroidble.exceptions;

import java.util.UUID;

/* loaded from: classes3.dex */
public class BleServiceNotFoundException extends BleException {
    private final UUID serviceUUID;

    public BleServiceNotFoundException(UUID r3) {
        super("BLE Service not found with UUID " + r3);
        this.serviceUUID = r3;
    }

    public UUID getServiceUUID() {
        return this.serviceUUID;
    }
}
