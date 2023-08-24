package com.google.android.gms.auth;

import android.content.Intent;

/* loaded from: classes2.dex */
public class GooglePlayServicesAvailabilityException extends UserRecoverableAuthException {
    private final int zzu;

    /* JADX INFO: Access modifiers changed from: package-private */
    public GooglePlayServicesAvailabilityException(int r1, String str, Intent intent) {
        super(str, intent);
        this.zzu = r1;
    }

    public int getConnectionStatusCode() {
        return this.zzu;
    }
}
