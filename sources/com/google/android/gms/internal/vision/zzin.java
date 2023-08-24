package com.google.android.gms.internal.vision;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: com.google.android.gms:play-services-vision-common@@19.0.0 */
/* loaded from: classes3.dex */
public final class zzin {
    private static final zzin zzzn = new zzin();
    private final ConcurrentMap<Class<?>, zzir<?>> zzzp = new ConcurrentHashMap();
    private final zziu zzzo = new zzhp();

    public static zzin zzho() {
        return zzzn;
    }

    public final <T> zzir<T> zzf(Class<T> cls) {
        zzgt.zza(cls, "messageType");
        zzir<T> zzirVar = (zzir<T>) this.zzzp.get(cls);
        if (zzirVar == null) {
            zzir<T> zze = this.zzzo.zze(cls);
            zzgt.zza(cls, "messageType");
            zzgt.zza(zze, "schema");
            zzir<T> zzirVar2 = (zzir<T>) this.zzzp.putIfAbsent(cls, zze);
            return zzirVar2 != null ? zzirVar2 : zze;
        }
        return zzirVar;
    }

    public final <T> zzir<T> zzu(T t) {
        return zzf(t.getClass());
    }

    private zzin() {
    }
}
