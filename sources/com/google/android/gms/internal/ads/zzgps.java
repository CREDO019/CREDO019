package com.google.android.gms.internal.ads;

import java.util.Iterator;
import java.util.Map;

/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
final class zzgps {
    public static final int zza(int r0, Object obj, Object obj2) {
        zzgpr zzgprVar = (zzgpr) obj;
        zzgpq zzgpqVar = (zzgpq) obj2;
        if (zzgprVar.isEmpty()) {
            return 0;
        }
        Iterator it = zzgprVar.entrySet().iterator();
        if (it.hasNext()) {
            Map.Entry entry = (Map.Entry) it.next();
            entry.getKey();
            entry.getValue();
            throw null;
        }
        return 0;
    }

    public static final boolean zzb(Object obj) {
        return !((zzgpr) obj).zze();
    }

    public static final Object zzc(Object obj, Object obj2) {
        zzgpr zzgprVar = (zzgpr) obj;
        zzgpr zzgprVar2 = (zzgpr) obj2;
        if (!zzgprVar2.isEmpty()) {
            if (!zzgprVar.zze()) {
                zzgprVar = zzgprVar.zzb();
            }
            zzgprVar.zzd(zzgprVar2);
        }
        return zzgprVar;
    }
}
