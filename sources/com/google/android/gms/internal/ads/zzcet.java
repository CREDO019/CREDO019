package com.google.android.gms.internal.ads;

import android.content.Context;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
public abstract class zzcet {
    static zzcet zza;

    public static synchronized zzcet zzd(Context context) {
        synchronized (zzcet.class) {
            zzcet zzcetVar = zza;
            if (zzcetVar != null) {
                return zzcetVar;
            }
            Context applicationContext = context.getApplicationContext();
            zzbiy.zzc(applicationContext);
            com.google.android.gms.ads.internal.util.zzg zzh = com.google.android.gms.ads.internal.zzt.zzp().zzh();
            zzh.zzr(applicationContext);
            zzcdx zzcdxVar = new zzcdx(null);
            zzcdxVar.zzb(applicationContext);
            zzcdxVar.zzc(com.google.android.gms.ads.internal.zzt.zzB());
            zzcdxVar.zza(zzh);
            zzcdxVar.zzd(com.google.android.gms.ads.internal.zzt.zzo());
            zzcet zze = zzcdxVar.zze();
            zza = zze;
            zze.zza().zza();
            zza.zzb().zzc();
            zzcex zzc = zza.zzc();
            if (((Boolean) com.google.android.gms.ads.internal.client.zzay.zzc().zzb(zzbiy.zzao)).booleanValue()) {
                HashMap hashMap = new HashMap();
                try {
                    JSONObject jSONObject = new JSONObject((String) com.google.android.gms.ads.internal.client.zzay.zzc().zzb(zzbiy.zzaq));
                    Iterator<String> keys = jSONObject.keys();
                    while (keys.hasNext()) {
                        String next = keys.next();
                        HashSet hashSet = new HashSet();
                        JSONArray optJSONArray = jSONObject.optJSONArray(next);
                        if (optJSONArray != null) {
                            for (int r7 = 0; r7 < optJSONArray.length(); r7++) {
                                String optString = optJSONArray.optString(r7);
                                if (optString != null) {
                                    hashSet.add(optString);
                                }
                            }
                            hashMap.put(next, hashSet);
                        }
                    }
                    for (String str : hashMap.keySet()) {
                        zzc.zzc(str);
                    }
                    zzc.zzd(new zzcev(zzc, hashMap));
                } catch (JSONException e) {
                    com.google.android.gms.ads.internal.util.zze.zzf("Failed to parse listening list", e);
                }
            }
            return zza;
        }
    }

    abstract zzcdq zza();

    /* JADX INFO: Access modifiers changed from: package-private */
    public abstract zzcdu zzb();

    abstract zzcex zzc();
}
