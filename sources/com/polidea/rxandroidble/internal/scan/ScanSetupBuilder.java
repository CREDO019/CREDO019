package com.polidea.rxandroidble.internal.scan;

import com.polidea.rxandroidble.scan.ScanFilter;
import com.polidea.rxandroidble.scan.ScanSettings;

/* loaded from: classes3.dex */
public interface ScanSetupBuilder {
    ScanSetup build(ScanSettings scanSettings, ScanFilter... scanFilterArr);
}
