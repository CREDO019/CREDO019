package com.polidea.rxandroidble.internal.connection;

import android.bluetooth.BluetoothGattCharacteristic;
import bleshadow.javax.inject.Inject;
import com.polidea.rxandroidble.internal.util.CharacteristicPropertiesParser;
import java.util.Locale;

/* loaded from: classes3.dex */
public class IllegalOperationMessageCreator {
    private CharacteristicPropertiesParser propertiesParser;

    @Inject
    public IllegalOperationMessageCreator(CharacteristicPropertiesParser characteristicPropertiesParser) {
        this.propertiesParser = characteristicPropertiesParser;
    }

    public String createMismatchMessage(BluetoothGattCharacteristic bluetoothGattCharacteristic, int r6) {
        return String.format(Locale.getDefault(), "Characteristic %s supports properties: %s (%d) does not have any property matching %s (%d)", bluetoothGattCharacteristic.getUuid(), this.propertiesParser.propertiesIntToString(bluetoothGattCharacteristic.getProperties()), Integer.valueOf(bluetoothGattCharacteristic.getProperties()), this.propertiesParser.propertiesIntToString(r6), Integer.valueOf(r6));
    }
}
