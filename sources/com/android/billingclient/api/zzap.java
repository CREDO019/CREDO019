package com.android.billingclient.api;

import android.text.TextUtils;

/* compiled from: com.android.billingclient:billing@@4.0.0 */
/* loaded from: classes.dex */
public final class zzap {
    private String zza;

    private zzap() {
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public /* synthetic */ zzap(zzao zzaoVar) {
    }

    public final zzap zza(String str) {
        this.zza = str;
        return this;
    }

    public final zzaq zzb() {
        if (TextUtils.isEmpty(this.zza)) {
            throw new IllegalArgumentException("SKU must be set.");
        }
        return new zzaq(this.zza, null, null);
    }
}
