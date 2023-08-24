package com.google.android.gms.internal.ads;

import com.google.android.gms.common.util.Clock;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
public final class zzdxr implements zzfhq {
    private final zzdxj zzb;
    private final Clock zzc;
    private final Map zza = new HashMap();
    private final Map zzd = new HashMap();

    public zzdxr(zzdxj zzdxjVar, Set set, Clock clock) {
        zzfhj zzfhjVar;
        this.zzb = zzdxjVar;
        Iterator it = set.iterator();
        while (it.hasNext()) {
            zzdxq zzdxqVar = (zzdxq) it.next();
            Map map = this.zzd;
            zzfhjVar = zzdxqVar.zzc;
            map.put(zzfhjVar, zzdxqVar);
        }
        this.zzc = clock;
    }

    private final void zze(zzfhj zzfhjVar, boolean z) {
        zzfhj zzfhjVar2;
        String str;
        zzfhjVar2 = ((zzdxq) this.zzd.get(zzfhjVar)).zzb;
        String str2 = true != z ? "f." : "s.";
        if (this.zza.containsKey(zzfhjVar2)) {
            long elapsedRealtime = this.zzc.elapsedRealtime();
            long longValue = ((Long) this.zza.get(zzfhjVar2)).longValue();
            Map zza = this.zzb.zza();
            str = ((zzdxq) this.zzd.get(zzfhjVar)).zza;
            zza.put("label.".concat(str), str2.concat(String.valueOf(Long.toString(elapsedRealtime - longValue))));
        }
    }

    @Override // com.google.android.gms.internal.ads.zzfhq
    public final void zzbF(zzfhj zzfhjVar, String str) {
    }

    @Override // com.google.android.gms.internal.ads.zzfhq
    public final void zzbG(zzfhj zzfhjVar, String str, Throwable th) {
        if (this.zza.containsKey(zzfhjVar)) {
            this.zzb.zza().put("task.".concat(String.valueOf(str)), "f.".concat(String.valueOf(Long.toString(this.zzc.elapsedRealtime() - ((Long) this.zza.get(zzfhjVar)).longValue()))));
        }
        if (this.zzd.containsKey(zzfhjVar)) {
            zze(zzfhjVar, false);
        }
    }

    @Override // com.google.android.gms.internal.ads.zzfhq
    public final void zzc(zzfhj zzfhjVar, String str) {
        this.zza.put(zzfhjVar, Long.valueOf(this.zzc.elapsedRealtime()));
    }

    @Override // com.google.android.gms.internal.ads.zzfhq
    public final void zzd(zzfhj zzfhjVar, String str) {
        if (this.zza.containsKey(zzfhjVar)) {
            this.zzb.zza().put("task.".concat(String.valueOf(str)), "s.".concat(String.valueOf(Long.toString(this.zzc.elapsedRealtime() - ((Long) this.zza.get(zzfhjVar)).longValue()))));
        }
        if (this.zzd.containsKey(zzfhjVar)) {
            zze(zzfhjVar, true);
        }
    }
}
