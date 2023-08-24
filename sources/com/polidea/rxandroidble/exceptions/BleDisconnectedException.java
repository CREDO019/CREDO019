package com.polidea.rxandroidble.exceptions;

import com.polidea.rxandroidble.utils.GattStatusParser;

/* loaded from: classes3.dex */
public class BleDisconnectedException extends BleException {
    public static final int UNKNOWN_STATUS = -1;
    public final String bluetoothDeviceAddress;
    public final int state;

    public static BleDisconnectedException adapterDisabled(String str) {
        return new BleDisconnectedException(new BleAdapterDisabledException(), str, -1);
    }

    @Deprecated
    public BleDisconnectedException() {
        this("", -1);
    }

    @Deprecated
    public BleDisconnectedException(Throwable th, String str) {
        this(th, str, -1);
    }

    @Deprecated
    public BleDisconnectedException(String str) {
        this(str, -1);
    }

    public BleDisconnectedException(Throwable th, String str, int r4) {
        super(createMessage(str, r4), th);
        this.bluetoothDeviceAddress = str;
        this.state = r4;
    }

    public BleDisconnectedException(String str, int r3) {
        super(createMessage(str, r3));
        this.bluetoothDeviceAddress = str;
        this.state = r3;
    }

    private static String createMessage(String str, int r4) {
        String gattCallbackStatusDescription = GattStatusParser.getGattCallbackStatusDescription(r4);
        return "Disconnected from " + str + " with status " + r4 + " (" + gattCallbackStatusDescription + ")";
    }
}
