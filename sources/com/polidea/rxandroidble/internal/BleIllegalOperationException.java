package com.polidea.rxandroidble.internal;

import java.util.UUID;

/* loaded from: classes3.dex */
public class BleIllegalOperationException extends RuntimeException {
    public final UUID characteristicUUID;
    public final int neededProperties;
    public final int supportedProperties;

    public BleIllegalOperationException(String str, UUID r2, int r3, int r4) {
        super(str);
        this.characteristicUUID = r2;
        this.supportedProperties = r3;
        this.neededProperties = r4;
    }
}
