package com.google.android.gms.internal.measurement;

import java.util.Iterator;
import java.util.Map;

/* compiled from: com.google.android.gms:play-services-measurement-base@@20.1.2 */
/* loaded from: classes3.dex */
final class zzle {
    public static final int zza(int r0, Object obj, Object obj2) {
        zzld zzldVar = (zzld) obj;
        zzlc zzlcVar = (zzlc) obj2;
        if (zzldVar.isEmpty()) {
            return 0;
        }
        Iterator it = zzldVar.entrySet().iterator();
        if (it.hasNext()) {
            Map.Entry entry = (Map.Entry) it.next();
            entry.getKey();
            entry.getValue();
            throw null;
        }
        return 0;
    }

    public static final Object zzb(Object obj, Object obj2) {
        zzld zzldVar = (zzld) obj;
        zzld zzldVar2 = (zzld) obj2;
        if (!zzldVar2.isEmpty()) {
            if (!zzldVar.zze()) {
                zzldVar = zzldVar.zzb();
            }
            zzldVar.zzd(zzldVar2);
        }
        return zzldVar;
    }
}
