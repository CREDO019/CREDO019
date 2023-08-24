package com.polidea.rxandroidble.exceptions;

import java.util.UUID;

/* loaded from: classes3.dex */
public class BleCharacteristicNotFoundException extends BleException {
    private final UUID charactersisticUUID;

    public BleCharacteristicNotFoundException(UUID r3) {
        super("Characteristic not found with UUID " + r3);
        this.charactersisticUUID = r3;
    }

    public UUID getCharactersisticUUID() {
        return this.charactersisticUUID;
    }
}
