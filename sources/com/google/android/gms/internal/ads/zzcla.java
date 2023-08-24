package com.google.android.gms.internal.ads;

import java.util.Iterator;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
public final class zzcla implements zzbpq {
    private static final Integer zzb(Map map, String str) {
        if (map.containsKey(str)) {
            try {
                return Integer.valueOf(Integer.parseInt((String) map.get(str)));
            } catch (NumberFormatException unused) {
                com.google.android.gms.ads.internal.util.zze.zzj("Precache invalid numeric parameter '" + str + "': " + ((String) map.get(str)));
                return null;
            }
        }
        return null;
    }

    @Override // com.google.android.gms.internal.ads.zzbpq
    public final /* bridge */ /* synthetic */ void zza(Object obj, Map map) {
        zzckz zzckzVar;
        zzckr zza;
        zzciw zzciwVar = (zzciw) obj;
        if (com.google.android.gms.ads.internal.util.zze.zzm(3)) {
            JSONObject jSONObject = new JSONObject(map);
            jSONObject.remove("google.afma.Notify_dt");
            com.google.android.gms.ads.internal.util.zze.zze("Precache GMSG: ".concat(jSONObject.toString()));
        }
        zzcks zzz = com.google.android.gms.ads.internal.zzt.zzz();
        if (map.containsKey("abort")) {
            if (zzz.zzd(zzciwVar)) {
                return;
            }
            com.google.android.gms.ads.internal.util.zze.zzj("Precache abort but no precache task running.");
            return;
        }
        String str = (String) map.get("src");
        Integer zzb = zzb(map, "periodicReportIntervalMs");
        Integer zzb2 = zzb(map, "exoPlayerRenderingIntervalMs");
        Integer zzb3 = zzb(map, "exoPlayerIdleIntervalMs");
        zzciv zzcivVar = new zzciv((String) map.get("flags"));
        boolean z = zzcivVar.zzn;
        if (str != null) {
            String[] strArr = {str};
            String str2 = (String) map.get("demuxed");
            if (str2 != null) {
                try {
                    JSONArray jSONArray = new JSONArray(str2);
                    String[] strArr2 = new String[jSONArray.length()];
                    for (int r15 = 0; r15 < jSONArray.length(); r15++) {
                        strArr2[r15] = jSONArray.getString(r15);
                    }
                    strArr = strArr2;
                } catch (JSONException unused) {
                    com.google.android.gms.ads.internal.util.zze.zzj("Malformed demuxed URL list for precache: ".concat(str2));
                    strArr = null;
                }
            }
            if (strArr == null) {
                strArr = new String[]{str};
            }
            if (z) {
                Iterator it = zzz.iterator();
                while (true) {
                    if (!it.hasNext()) {
                        zza = null;
                        break;
                    }
                    zzckr zzckrVar = (zzckr) it.next();
                    if (zzckrVar.zza == zzciwVar && str.equals(zzckrVar.zze())) {
                        zza = zzckrVar;
                        break;
                    }
                }
            } else {
                zza = zzz.zza(zzciwVar);
            }
            if (zza != null) {
                com.google.android.gms.ads.internal.util.zze.zzj("Precache task is already running.");
                return;
            } else if (zzciwVar.zzm() == null) {
                com.google.android.gms.ads.internal.util.zze.zzj("Precache requires a dependency provider.");
                return;
            } else {
                Integer zzb4 = zzb(map, "player");
                if (zzb4 == null) {
                    zzb4 = 0;
                }
                if (zzb != null) {
                    zzciwVar.zzC(zzb.intValue());
                }
                if (zzb2 != null) {
                    zzciwVar.zzA(zzb2.intValue());
                }
                if (zzb3 != null) {
                    zzciwVar.zzz(zzb3.intValue());
                }
                int intValue = zzb4.intValue();
                zzckl zzcklVar = zzciwVar.zzm().zzb;
                if (intValue > 0) {
                    int zzu = zzcin.zzu();
                    if (zzu < zzcivVar.zzh) {
                        zzckzVar = new zzcli(zzciwVar, zzcivVar);
                    } else if (zzu < zzcivVar.zzb) {
                        zzckzVar = new zzclf(zzciwVar, zzcivVar);
                    } else {
                        zzckzVar = new zzcld(zzciwVar);
                    }
                } else {
                    zzckzVar = new zzclc(zzciwVar);
                }
                new zzckr(zzciwVar, zzckzVar, str, strArr).zzb();
            }
        } else {
            zzckr zza2 = zzz.zza(zzciwVar);
            if (zza2 == null) {
                com.google.android.gms.ads.internal.util.zze.zzj("Precache must specify a source.");
                return;
            }
            zzckzVar = zza2.zzb;
        }
        Integer zzb5 = zzb(map, "minBufferMs");
        if (zzb5 != null) {
            zzckzVar.zzp(zzb5.intValue());
        }
        Integer zzb6 = zzb(map, "maxBufferMs");
        if (zzb6 != null) {
            zzckzVar.zzo(zzb6.intValue());
        }
        Integer zzb7 = zzb(map, "bufferForPlaybackMs");
        if (zzb7 != null) {
            zzckzVar.zzh(zzb7.intValue());
        }
        Integer zzb8 = zzb(map, "bufferForPlaybackAfterRebufferMs");
        if (zzb8 != null) {
            zzckzVar.zzn(zzb8.intValue());
        }
    }
}
