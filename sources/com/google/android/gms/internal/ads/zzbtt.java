package com.google.android.gms.internal.ads;

import java.util.AbstractMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import org.json.JSONObject;

/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
public final class zzbtt implements zzbsk, zzbts {
    private final zzbts zza;
    private final HashSet zzb = new HashSet();

    public zzbtt(zzbts zzbtsVar) {
        this.zza = zzbtsVar;
    }

    @Override // com.google.android.gms.internal.ads.zzbsk, com.google.android.gms.internal.ads.zzbsv
    public final void zza(String str) {
        this.zza.zza(str);
    }

    @Override // com.google.android.gms.internal.ads.zzbsk, com.google.android.gms.internal.ads.zzbsv
    public final /* synthetic */ void zzb(String str, String str2) {
        zzbsj.zzc(this, str, str2);
    }

    public final void zzc() {
        Iterator it = this.zzb.iterator();
        while (it.hasNext()) {
            AbstractMap.SimpleEntry simpleEntry = (AbstractMap.SimpleEntry) it.next();
            com.google.android.gms.ads.internal.util.zze.zza("Unregistering eventhandler: ".concat(String.valueOf(((zzbpq) simpleEntry.getValue()).toString())));
            this.zza.zzr((String) simpleEntry.getKey(), (zzbpq) simpleEntry.getValue());
        }
        this.zzb.clear();
    }

    @Override // com.google.android.gms.internal.ads.zzbsi
    public final /* synthetic */ void zzd(String str, Map map) {
        zzbsj.zza(this, str, map);
    }

    @Override // com.google.android.gms.internal.ads.zzbsk, com.google.android.gms.internal.ads.zzbsi
    public final /* synthetic */ void zze(String str, JSONObject jSONObject) {
        zzbsj.zzb(this, str, jSONObject);
    }

    @Override // com.google.android.gms.internal.ads.zzbsv
    public final /* synthetic */ void zzl(String str, JSONObject jSONObject) {
        zzbsj.zzd(this, str, jSONObject);
    }

    @Override // com.google.android.gms.internal.ads.zzbts
    public final void zzq(String str, zzbpq zzbpqVar) {
        this.zza.zzq(str, zzbpqVar);
        this.zzb.add(new AbstractMap.SimpleEntry(str, zzbpqVar));
    }

    @Override // com.google.android.gms.internal.ads.zzbts
    public final void zzr(String str, zzbpq zzbpqVar) {
        this.zza.zzr(str, zzbpqVar);
        this.zzb.remove(new AbstractMap.SimpleEntry(str, zzbpqVar));
    }
}
