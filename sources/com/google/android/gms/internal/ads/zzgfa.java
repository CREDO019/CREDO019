package com.google.android.gms.internal.ads;

import java.util.Collections;
import java.util.HashMap;

/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
public final class zzgfa {
    private HashMap zza = new HashMap();

    public final zzgfc zza() {
        if (this.zza == null) {
            throw new IllegalStateException("cannot call build() twice");
        }
        zzgfc zzgfcVar = new zzgfc(Collections.unmodifiableMap(this.zza), null);
        this.zza = null;
        return zzgfcVar;
    }
}
