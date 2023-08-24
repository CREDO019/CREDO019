package com.polidea.rxandroidble.internal.util;

import com.polidea.rxandroidble.internal.RxBleLog;

/* loaded from: classes3.dex */
public class CharacteristicPropertiesParser {
    private final int[] possibleProperties = getPossibleProperties();
    private final int propertyBroadcast;
    private final int propertyIndicate;
    private final int propertyNotify;
    private final int propertyRead;
    private final int propertySignedWrite;
    private final int propertyWrite;
    private final int propertyWriteNoResponse;

    private static boolean propertiesIntContains(int r0, int r1) {
        return (r0 & r1) != 0;
    }

    public CharacteristicPropertiesParser(int r1, int r2, int r3, int r4, int r5, int r6, int r7) {
        this.propertyBroadcast = r1;
        this.propertyRead = r2;
        this.propertyWriteNoResponse = r3;
        this.propertyWrite = r4;
        this.propertyNotify = r5;
        this.propertyIndicate = r6;
        this.propertySignedWrite = r7;
    }

    public String propertiesIntToString(int r7) {
        int[] r1;
        StringBuilder sb = new StringBuilder();
        sb.append("[ ");
        for (int r4 : this.possibleProperties) {
            if (propertiesIntContains(r7, r4)) {
                sb.append(propertyToString(r4));
                sb.append(" ");
            }
        }
        sb.append("]");
        return sb.toString();
    }

    private int[] getPossibleProperties() {
        return new int[]{this.propertyBroadcast, this.propertyRead, this.propertyWriteNoResponse, this.propertyWrite, this.propertyNotify, this.propertyIndicate, this.propertySignedWrite};
    }

    private String propertyToString(int r3) {
        if (r3 == this.propertyRead) {
            return "READ";
        }
        if (r3 == this.propertyWrite) {
            return "WRITE";
        }
        if (r3 == this.propertyWriteNoResponse) {
            return "WRITE_NO_RESPONSE";
        }
        if (r3 == this.propertySignedWrite) {
            return "SIGNED_WRITE";
        }
        if (r3 == this.propertyIndicate) {
            return "INDICATE";
        }
        if (r3 == this.propertyBroadcast) {
            return "BROADCAST";
        }
        if (r3 == this.propertyNotify) {
            return "NOTIFY";
        }
        if (r3 == 0) {
            return "";
        }
        RxBleLog.m241e("Unknown property specified", new Object[0]);
        return "UNKNOWN (" + r3 + " -> check android.bluetooth.BluetoothGattCharacteristic)";
    }
}
