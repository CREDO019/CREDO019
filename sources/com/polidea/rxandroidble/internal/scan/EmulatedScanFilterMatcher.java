package com.polidea.rxandroidble.internal.scan;

import com.polidea.rxandroidble.scan.ScanFilter;

/* loaded from: classes3.dex */
public class EmulatedScanFilterMatcher {
    private final ScanFilter[] scanFilters;

    public EmulatedScanFilterMatcher(ScanFilter... scanFilterArr) {
        this.scanFilters = scanFilterArr;
    }

    public boolean matches(RxBleInternalScanResult rxBleInternalScanResult) {
        ScanFilter[] scanFilterArr = this.scanFilters;
        if (scanFilterArr == null || scanFilterArr.length == 0) {
            return true;
        }
        for (ScanFilter scanFilter : scanFilterArr) {
            if (scanFilter.matches(rxBleInternalScanResult)) {
                return true;
            }
        }
        return false;
    }
}
