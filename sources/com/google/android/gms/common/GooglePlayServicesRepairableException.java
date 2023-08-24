package com.google.android.gms.common;

import android.content.Intent;

/* compiled from: com.google.android.gms:play-services-basement@@18.1.0 */
/* loaded from: classes2.dex */
public class GooglePlayServicesRepairableException extends UserRecoverableException {
    private final int zza;

    public GooglePlayServicesRepairableException(int r1, String str, Intent intent) {
        super(str, intent);
        this.zza = r1;
    }

    public int getConnectionStatusCode() {
        return this.zza;
    }
}
