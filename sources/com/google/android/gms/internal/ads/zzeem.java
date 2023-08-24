package com.google.android.gms.internal.ads;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
public final class zzeem implements zzfhq {
    private final Map zza = new HashMap();
    private final Map zzb = new HashMap();
    private final zzfhy zzc;

    public zzeem(Set set, zzfhy zzfhyVar) {
        zzfhj zzfhjVar;
        String str;
        zzfhj zzfhjVar2;
        String str2;
        this.zzc = zzfhyVar;
        Iterator it = set.iterator();
        while (it.hasNext()) {
            zzeel zzeelVar = (zzeel) it.next();
            Map map = this.zza;
            zzfhjVar = zzeelVar.zzb;
            str = zzeelVar.zza;
            map.put(zzfhjVar, str);
            Map map2 = this.zzb;
            zzfhjVar2 = zzeelVar.zzc;
            str2 = zzeelVar.zza;
            map2.put(zzfhjVar2, str2);
        }
    }

    @Override // com.google.android.gms.internal.ads.zzfhq
    public final void zzbF(zzfhj zzfhjVar, String str) {
    }

    @Override // com.google.android.gms.internal.ads.zzfhq
    public final void zzbG(zzfhj zzfhjVar, String str, Throwable th) {
        this.zzc.zze("task.".concat(String.valueOf(str)), "f.");
        if (this.zzb.containsKey(zzfhjVar)) {
            this.zzc.zze("label.".concat(String.valueOf((String) this.zzb.get(zzfhjVar))), "f.");
        }
    }

    @Override // com.google.android.gms.internal.ads.zzfhq
    public final void zzc(zzfhj zzfhjVar, String str) {
        this.zzc.zzd("task.".concat(String.valueOf(str)));
        if (this.zza.containsKey(zzfhjVar)) {
            this.zzc.zzd("label.".concat(String.valueOf((String) this.zza.get(zzfhjVar))));
        }
    }

    @Override // com.google.android.gms.internal.ads.zzfhq
    public final void zzd(zzfhj zzfhjVar, String str) {
        this.zzc.zze("task.".concat(String.valueOf(str)), "s.");
        if (this.zzb.containsKey(zzfhjVar)) {
            this.zzc.zze("label.".concat(String.valueOf((String) this.zzb.get(zzfhjVar))), "s.");
        }
    }
}
