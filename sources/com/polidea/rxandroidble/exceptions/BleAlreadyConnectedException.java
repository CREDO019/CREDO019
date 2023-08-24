package com.polidea.rxandroidble.exceptions;

/* loaded from: classes3.dex */
public class BleAlreadyConnectedException extends BleException {
    public BleAlreadyConnectedException(String str) {
        super("Already connected to device with MAC address " + str);
    }
}
