package com.google.android.gms.internal.ads;

import java.util.Collections;
import java.util.Map;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
public abstract class zzgun implements zzgur {
    private final Map zza;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzgun(Map map) {
        this.zza = Collections.unmodifiableMap(map);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final Map zza() {
        return this.zza;
    }
}
