package com.google.android.gms.internal.ads;

import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicReference;

/* compiled from: com.google.android.gms:play-services-ads-lite@@21.2.0 */
/* loaded from: classes2.dex */
public final class zzblf {
    private static final AtomicReference zzb = new AtomicReference();
    private static final AtomicReference zzc = new AtomicReference();
    static final AtomicBoolean zza = new AtomicBoolean();

    /* JADX INFO: Access modifiers changed from: package-private */
    public static zzbld zza() {
        return (zzbld) zzb.get();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static zzble zzb() {
        return (zzble) zzc.get();
    }

    public static void zzc(zzbld zzbldVar) {
        zzb.set(zzbldVar);
    }
}
