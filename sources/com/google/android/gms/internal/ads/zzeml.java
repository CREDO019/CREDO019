package com.google.android.gms.internal.ads;

import android.os.Bundle;
import android.text.TextUtils;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.concurrent.Executor;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
public final class zzeml {
    private final Map zza = new HashMap();
    private final Map zzb = new HashMap();
    private final Map zzc = new HashMap();
    private final Executor zzd;
    private JSONObject zze;

    public zzeml(Executor executor) {
        this.zzd = executor;
    }

    private final synchronized List zzg(JSONObject jSONObject, String str) {
        ArrayList arrayList = new ArrayList();
        if (jSONObject == null) {
            return arrayList;
        }
        Bundle zzl = zzl(jSONObject.optJSONObject("data"));
        JSONArray optJSONArray = jSONObject.optJSONArray("rtb_adapters");
        if (optJSONArray == null) {
            return arrayList;
        }
        ArrayList arrayList2 = new ArrayList();
        for (int r4 = 0; r4 < optJSONArray.length(); r4++) {
            String optString = optJSONArray.optString(r4, "");
            if (!TextUtils.isEmpty(optString)) {
                arrayList2.add(optString);
            }
        }
        int size = arrayList2.size();
        for (int r3 = 0; r3 < size; r3++) {
            String str2 = (String) arrayList2.get(r3);
            zzf(str2);
            if (((zzemn) this.zza.get(str2)) != null) {
                arrayList.add(new zzemn(str2, str, zzl));
            }
        }
        return arrayList;
    }

    public final synchronized void zzh() {
        this.zzb.clear();
        this.zza.clear();
        zzj();
        zzk();
    }

    private final synchronized void zzi(String str, String str2, List list) {
        if (TextUtils.isEmpty(str) || TextUtils.isEmpty(str2)) {
            return;
        }
        Map map = (Map) this.zzc.get(str);
        if (map == null) {
            map = new HashMap();
        }
        this.zzc.put(str, map);
        List list2 = (List) map.get(str2);
        if (list2 == null) {
            list2 = new ArrayList();
        }
        list2.addAll(list);
        map.put(str2, list2);
    }

    private final synchronized void zzj() {
        String optString;
        JSONArray optJSONArray;
        JSONObject zzf = com.google.android.gms.ads.internal.zzt.zzp().zzh().zzh().zzf();
        if (zzf != null) {
            try {
                JSONArray optJSONArray2 = zzf.optJSONArray("ad_unit_id_settings");
                this.zze = zzf.optJSONObject("ad_unit_patterns");
                if (optJSONArray2 != null) {
                    for (int r2 = 0; r2 < optJSONArray2.length(); r2++) {
                        JSONObject jSONObject = optJSONArray2.getJSONObject(r2);
                        if (((Boolean) com.google.android.gms.ads.internal.client.zzay.zzc().zzb(zzbiy.zzit)).booleanValue()) {
                            optString = jSONObject.optString("ad_unit_id", "").toLowerCase(Locale.ROOT);
                        } else {
                            optString = jSONObject.optString("ad_unit_id", "");
                        }
                        String optString2 = jSONObject.optString("format", "");
                        ArrayList arrayList = new ArrayList();
                        JSONObject optJSONObject = jSONObject.optJSONObject("mediation_config");
                        if (optJSONObject != null && (optJSONArray = optJSONObject.optJSONArray("ad_networks")) != null) {
                            for (int r7 = 0; r7 < optJSONArray.length(); r7++) {
                                arrayList.addAll(zzg(optJSONArray.getJSONObject(r7), optString2));
                            }
                        }
                        zzi(optString2, optString, arrayList);
                    }
                }
            } catch (JSONException e) {
                com.google.android.gms.ads.internal.util.zze.zzb("Malformed config loading JSON.", e);
            }
        }
    }

    private final synchronized void zzk() {
        if (!((Boolean) zzbkt.zze.zze()).booleanValue()) {
            if (((Boolean) com.google.android.gms.ads.internal.client.zzay.zzc().zzb(zzbiy.zzbx)).booleanValue()) {
                JSONObject zzf = com.google.android.gms.ads.internal.zzt.zzp().zzh().zzh().zzf();
                if (zzf == null) {
                    return;
                }
                try {
                    JSONArray jSONArray = zzf.getJSONArray("signal_adapters");
                    for (int r2 = 0; r2 < jSONArray.length(); r2++) {
                        JSONObject jSONObject = jSONArray.getJSONObject(r2);
                        Bundle zzl = zzl(jSONObject.optJSONObject("data"));
                        String optString = jSONObject.optString("adapter_class_name");
                        boolean optBoolean = jSONObject.optBoolean("render", false);
                        boolean optBoolean2 = jSONObject.optBoolean("collect_signals", false);
                        if (!TextUtils.isEmpty(optString)) {
                            this.zzb.put(optString, new zzemp(optString, optBoolean2, optBoolean, zzl));
                        }
                    }
                } catch (JSONException e) {
                    com.google.android.gms.ads.internal.util.zze.zzb("Malformed config loading JSON.", e);
                }
            }
        }
    }

    private static final Bundle zzl(JSONObject jSONObject) {
        Bundle bundle = new Bundle();
        if (jSONObject != null) {
            Iterator<String> keys = jSONObject.keys();
            while (keys.hasNext()) {
                String next = keys.next();
                bundle.putString(next, jSONObject.optString(next, ""));
            }
        }
        return bundle;
    }

    public final synchronized Map zza(String str, String str2) {
        if (!TextUtils.isEmpty(str) && !TextUtils.isEmpty(str2)) {
            Map map = (Map) this.zzc.get(str);
            if (map == null) {
                return zzfuy.zzd();
            }
            List<zzemn> list = (List) map.get(str2);
            if (list == null) {
                String zza = zzdvq.zza(this.zze, str2, str);
                if (((Boolean) com.google.android.gms.ads.internal.client.zzay.zzc().zzb(zzbiy.zzit)).booleanValue()) {
                    zza = zza.toLowerCase(Locale.ROOT);
                }
                list = (List) map.get(zza);
            }
            if (list == null) {
                return zzfuy.zzd();
            }
            HashMap hashMap = new HashMap();
            for (zzemn zzemnVar : list) {
                String str3 = zzemnVar.zza;
                if (!hashMap.containsKey(str3)) {
                    hashMap.put(str3, new ArrayList());
                }
                ((List) hashMap.get(str3)).add(zzemnVar.zzc);
            }
            return zzfuy.zzc(hashMap);
        }
        return zzfuy.zzd();
    }

    public final synchronized Map zzb() {
        return zzfuy.zzc(this.zzb);
    }

    public final void zzd() {
        com.google.android.gms.ads.internal.zzt.zzp().zzh().zzq(new Runnable() { // from class: com.google.android.gms.internal.ads.zzemj
            @Override // java.lang.Runnable
            public final void run() {
                zzeml.this.zze();
            }
        });
        this.zzd.execute(new zzemk(this));
    }

    public final /* synthetic */ void zze() {
        this.zzd.execute(new zzemk(this));
    }

    public final synchronized void zzf(String str) {
        if (TextUtils.isEmpty(str)) {
            return;
        }
        if (this.zza.containsKey(str)) {
            return;
        }
        this.zza.put(str, new zzemn(str, "", new Bundle()));
    }
}
