package com.polidea.multiplatformbleadapter.utils;

import com.polidea.multiplatformbleadapter.utils.Constants;

/* loaded from: classes3.dex */
public class LogLevel {
    public static String fromLogLevel(int r1) {
        return r1 != 2 ? r1 != 3 ? r1 != 4 ? r1 != 5 ? r1 != 6 ? Constants.BluetoothLogLevel.NONE : Constants.BluetoothLogLevel.ERROR : "Warning" : Constants.BluetoothLogLevel.INFO : Constants.BluetoothLogLevel.DEBUG : Constants.BluetoothLogLevel.VERBOSE;
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    public static int toLogLevel(String str) {
        char c;
        switch (str.hashCode()) {
            case -1505867908:
                if (str.equals("Warning")) {
                    c = 3;
                    break;
                }
                c = 65535;
                break;
            case 2283726:
                if (str.equals(Constants.BluetoothLogLevel.INFO)) {
                    c = 2;
                    break;
                }
                c = 65535;
                break;
            case 2433880:
                if (str.equals(Constants.BluetoothLogLevel.NONE)) {
                    c = 5;
                    break;
                }
                c = 65535;
                break;
            case 65906227:
                if (str.equals(Constants.BluetoothLogLevel.DEBUG)) {
                    c = 1;
                    break;
                }
                c = 65535;
                break;
            case 67232232:
                if (str.equals(Constants.BluetoothLogLevel.ERROR)) {
                    c = 4;
                    break;
                }
                c = 65535;
                break;
            case 2015760738:
                if (str.equals(Constants.BluetoothLogLevel.VERBOSE)) {
                    c = 0;
                    break;
                }
                c = 65535;
                break;
            default:
                c = 65535;
                break;
        }
        if (c != 0) {
            if (c != 1) {
                if (c != 2) {
                    if (c != 3) {
                        return c != 4 ? Integer.MAX_VALUE : 6;
                    }
                    return 5;
                }
                return 4;
            }
            return 3;
        }
        return 2;
    }
}
