package com.polidea.rxandroidble.exceptions;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.Date;

/* loaded from: classes3.dex */
public class BleScanException extends BleException {
    public static final int BLUETOOTH_CANNOT_START = 0;
    public static final int BLUETOOTH_DISABLED = 1;
    public static final int BLUETOOTH_NOT_AVAILABLE = 2;
    public static final int LOCATION_PERMISSION_MISSING = 3;
    public static final int LOCATION_SERVICES_DISABLED = 4;
    public static final int SCAN_FAILED_ALREADY_STARTED = 5;
    public static final int SCAN_FAILED_APPLICATION_REGISTRATION_FAILED = 6;
    public static final int SCAN_FAILED_FEATURE_UNSUPPORTED = 8;
    public static final int SCAN_FAILED_INTERNAL_ERROR = 7;
    public static final int SCAN_FAILED_OUT_OF_HARDWARE_RESOURCES = 9;
    public static final int UNDOCUMENTED_SCAN_THROTTLE = 2147483646;
    public static final int UNKNOWN_ERROR_CODE = Integer.MAX_VALUE;
    private final int reason;
    private final Date retryDateSuggestion;

    @Retention(RetentionPolicy.SOURCE)
    /* loaded from: classes3.dex */
    public @interface Reason {
    }

    private static String reasonDescription(int r1) {
        if (r1 != 2147483646) {
            switch (r1) {
                case 0:
                    return "Bluetooth cannot start";
                case 1:
                    return "Bluetooth disabled";
                case 2:
                    return "Bluetooth not available";
                case 3:
                    return "Location Permission missing";
                case 4:
                    return "Location Services disabled";
                case 5:
                    return "Scan failed because it has already started";
                case 6:
                    return "Scan failed because application registration failed";
                case 7:
                    return "Scan failed because of an internal error";
                case 8:
                    return "Scan failed because feature unsupported";
                case 9:
                    return "Scan failed because out of hardware resources";
                default:
                    return "Unknown error";
            }
        }
        return "Undocumented scan throttle";
    }

    public BleScanException(int r3) {
        super(createMessage(r3, null));
        this.reason = r3;
        this.retryDateSuggestion = null;
    }

    public BleScanException(int r2, Date date) {
        super(createMessage(r2, date));
        this.reason = r2;
        this.retryDateSuggestion = date;
    }

    public BleScanException(int r3, Throwable th) {
        super(createMessage(r3, null), th);
        this.reason = r3;
        this.retryDateSuggestion = null;
    }

    public int getReason() {
        return this.reason;
    }

    public Date getRetryDateSuggestion() {
        return this.retryDateSuggestion;
    }

    private static String createMessage(int r2, Date date) {
        return reasonDescription(r2) + " (code " + r2 + ")" + retryDateSuggestionIfExists(date);
    }

    private static String retryDateSuggestionIfExists(Date date) {
        if (date == null) {
            return "";
        }
        return ", suggested retry date is " + date;
    }
}
