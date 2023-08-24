package com.google.android.gms.internal.ads;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
public final class zzgqf {
    private static final zzgqf zza = new zzgqf();
    private final ConcurrentMap zzc = new ConcurrentHashMap();
    private final zzgqr zzb = new zzgpp();

    private zzgqf() {
    }

    public static zzgqf zza() {
        return zza;
    }

    public final zzgqq zzb(Class cls) {
        zzgox.zzf(cls, "messageType");
        zzgqq zzgqqVar = (zzgqq) this.zzc.get(cls);
        if (zzgqqVar == null) {
            zzgqqVar = this.zzb.zza(cls);
            zzgox.zzf(cls, "messageType");
            zzgox.zzf(zzgqqVar, "schema");
            zzgqq zzgqqVar2 = (zzgqq) this.zzc.putIfAbsent(cls, zzgqqVar);
            if (zzgqqVar2 != null) {
                return zzgqqVar2;
            }
        }
        return zzgqqVar;
    }
}
