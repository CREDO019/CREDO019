package com.reactnativegooglesignin;

import com.facebook.react.bridge.WritableMap;

/* loaded from: classes3.dex */
public class PendingAuthRecovery {
    private WritableMap userProperties;

    public PendingAuthRecovery(WritableMap writableMap) {
        this.userProperties = writableMap;
    }

    public WritableMap getUserProperties() {
        return this.userProperties;
    }
}
