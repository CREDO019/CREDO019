package com.google.android.gms.internal.ads;

import android.content.Context;
import android.graphics.Color;
import android.os.SystemClock;
import android.text.TextUtils;
import android.view.MotionEvent;
import androidx.core.app.NotificationCompat;
import com.facebook.react.modules.appstate.AppStateModule;
import com.facebook.react.uimanager.ViewProps;
import com.onesignal.influence.OSInfluenceConstants;
import java.util.HashMap;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
public final class zzckn implements zzbpq {
    private boolean zza;

    private static int zzb(Context context, Map map, String str, int r4) {
        String str2 = (String) map.get(str);
        if (str2 != null) {
            try {
                com.google.android.gms.ads.internal.client.zzaw.zzb();
                r4 = zzcgg.zzw(context, Integer.parseInt(str2));
            } catch (NumberFormatException unused) {
                com.google.android.gms.ads.internal.util.zze.zzj("Could not parse " + str + " in a video GMSG: " + str2);
            }
        }
        if (com.google.android.gms.ads.internal.util.zze.zzc()) {
            com.google.android.gms.ads.internal.util.zze.zza("Parse pixels for " + str + ", got string " + str2 + ", int " + r4 + ".");
        }
        return r4;
    }

    private static void zzc(zzcik zzcikVar, Map map) {
        String str = (String) map.get("minBufferMs");
        String str2 = (String) map.get("maxBufferMs");
        String str3 = (String) map.get("bufferForPlaybackMs");
        String str4 = (String) map.get("bufferForPlaybackAfterRebufferMs");
        String str5 = (String) map.get("socketReceiveBufferSize");
        if (str != null) {
            try {
                zzcikVar.zzA(Integer.parseInt(str));
            } catch (NumberFormatException unused) {
                com.google.android.gms.ads.internal.util.zze.zzj(String.format("Could not parse buffer parameters in loadControl video GMSG: (%s, %s)", str, str2));
                return;
            }
        }
        if (str2 != null) {
            zzcikVar.zzz(Integer.parseInt(str2));
        }
        if (str3 != null) {
            zzcikVar.zzx(Integer.parseInt(str3));
        }
        if (str4 != null) {
            zzcikVar.zzy(Integer.parseInt(str4));
        }
        if (str5 != null) {
            zzcikVar.zzC(Integer.parseInt(str5));
        }
    }

    @Override // com.google.android.gms.internal.ads.zzbpq
    public final /* bridge */ /* synthetic */ void zza(Object obj, Map map) {
        int min;
        int min2;
        int r10;
        String[] split;
        zzciw zzciwVar = (zzciw) obj;
        String str = (String) map.get("action");
        if (str == null) {
            com.google.android.gms.ads.internal.util.zze.zzj("Action missing from video GMSG.");
            return;
        }
        if (com.google.android.gms.ads.internal.util.zze.zzm(3)) {
            JSONObject jSONObject = new JSONObject(map);
            jSONObject.remove("google.afma.Notify_dt");
            com.google.android.gms.ads.internal.util.zze.zze("Video GMSG: " + str + " " + jSONObject.toString());
        }
        if (AppStateModule.APP_STATE_BACKGROUND.equals(str)) {
            String str2 = (String) map.get("color");
            if (TextUtils.isEmpty(str2)) {
                com.google.android.gms.ads.internal.util.zze.zzj("Color parameter missing from background video GMSG.");
                return;
            }
            try {
                zzciwVar.setBackgroundColor(Color.parseColor(str2));
            } catch (IllegalArgumentException unused) {
                com.google.android.gms.ads.internal.util.zze.zzj("Invalid color parameter in background video GMSG.");
            }
        } else if ("playerBackground".equals(str)) {
            String str3 = (String) map.get("color");
            if (TextUtils.isEmpty(str3)) {
                com.google.android.gms.ads.internal.util.zze.zzj("Color parameter missing from playerBackground video GMSG.");
                return;
            }
            try {
                zzciwVar.zzD(Color.parseColor(str3));
            } catch (IllegalArgumentException unused2) {
                com.google.android.gms.ads.internal.util.zze.zzj("Invalid color parameter in playerBackground video GMSG.");
            }
        } else {
            if ("decoderProps".equals(str)) {
                String str4 = (String) map.get("mimeTypes");
                if (str4 == null) {
                    com.google.android.gms.ads.internal.util.zze.zzj("No MIME types specified for decoder properties inspection.");
                    HashMap hashMap = new HashMap();
                    hashMap.put(NotificationCompat.CATEGORY_EVENT, "decoderProps");
                    hashMap.put("error", "missingMimeTypes");
                    zzciwVar.zzd("onVideoEvent", hashMap);
                    return;
                }
                HashMap hashMap2 = new HashMap();
                for (String str5 : str4.split(",")) {
                    hashMap2.put(str5, com.google.android.gms.ads.internal.util.zzch.zza(str5.trim()));
                }
                HashMap hashMap3 = new HashMap();
                hashMap3.put(NotificationCompat.CATEGORY_EVENT, "decoderProps");
                hashMap3.put("mimeTypes", hashMap2);
                zzciwVar.zzd("onVideoEvent", hashMap3);
                return;
            }
            zzcil zzbp = zzciwVar.zzbp();
            if (zzbp == null) {
                com.google.android.gms.ads.internal.util.zze.zzj("Could not get underlay container for a video GMSG.");
                return;
            }
            boolean equals = "new".equals(str);
            boolean equals2 = ViewProps.POSITION.equals(str);
            if (equals || equals2) {
                Context context = zzciwVar.getContext();
                int zzb = zzb(context, map, "x", 0);
                int zzb2 = zzb(context, map, "y", 0);
                int zzb3 = zzb(context, map, "w", -1);
                if (!((Boolean) com.google.android.gms.ads.internal.client.zzay.zzc().zzb(zzbiy.zzcV)).booleanValue()) {
                    if (com.google.android.gms.ads.internal.util.zze.zzc()) {
                        com.google.android.gms.ads.internal.util.zze.zza("Calculate width with original width " + zzb3 + ", videoHost.getVideoBoundingWidth() " + zzciwVar.zzj() + ", x " + zzb + ".");
                    }
                    min = Math.min(zzb3, zzciwVar.zzj() - zzb);
                } else if (zzb3 == -1) {
                    min = zzciwVar.zzj();
                } else {
                    min = Math.min(zzb3, zzciwVar.zzj());
                }
                int r8 = min;
                int zzb4 = zzb(context, map, "h", -1);
                if (!((Boolean) com.google.android.gms.ads.internal.client.zzay.zzc().zzb(zzbiy.zzcV)).booleanValue()) {
                    if (com.google.android.gms.ads.internal.util.zze.zzc()) {
                        com.google.android.gms.ads.internal.util.zze.zza("Calculate height with original height " + zzb4 + ", videoHost.getVideoBoundingHeight() " + zzciwVar.zzi() + ", y " + zzb2 + ".");
                    }
                    min2 = Math.min(zzb4, zzciwVar.zzi() - zzb2);
                } else if (zzb4 == -1) {
                    min2 = zzciwVar.zzi();
                } else {
                    min2 = Math.min(zzb4, zzciwVar.zzi());
                }
                int r9 = min2;
                try {
                    r10 = Integer.parseInt((String) map.get("player"));
                } catch (NumberFormatException unused3) {
                    r10 = 0;
                }
                boolean parseBoolean = Boolean.parseBoolean((String) map.get("spherical"));
                if (!equals || zzbp.zza() != null) {
                    zzbp.zzb(zzb, zzb2, r8, r9);
                    return;
                }
                zzbp.zzc(zzb, zzb2, r8, r9, r10, parseBoolean, new zzciv((String) map.get("flags")));
                zzcik zza = zzbp.zza();
                if (zza != null) {
                    zzc(zza, map);
                    return;
                }
                return;
            }
            zzcnj zzs = zzciwVar.zzs();
            if (zzs != null) {
                if ("timeupdate".equals(str)) {
                    String str6 = (String) map.get("currentTime");
                    if (str6 == null) {
                        com.google.android.gms.ads.internal.util.zze.zzj("currentTime parameter missing from timeupdate video GMSG.");
                        return;
                    }
                    try {
                        zzs.zzt(Float.parseFloat(str6));
                        return;
                    } catch (NumberFormatException unused4) {
                        com.google.android.gms.ads.internal.util.zze.zzj("Could not parse currentTime parameter from timeupdate video GMSG: ".concat(str6));
                        return;
                    }
                } else if ("skip".equals(str)) {
                    zzs.zzu();
                    return;
                }
            }
            zzcik zza2 = zzbp.zza();
            if (zza2 == null) {
                HashMap hashMap4 = new HashMap();
                hashMap4.put(NotificationCompat.CATEGORY_EVENT, "no_video_view");
                zzciwVar.zzd("onVideoEvent", hashMap4);
            } else if ("click".equals(str)) {
                Context context2 = zzciwVar.getContext();
                int zzb5 = zzb(context2, map, "x", 0);
                int zzb6 = zzb(context2, map, "y", 0);
                long uptimeMillis = SystemClock.uptimeMillis();
                MotionEvent obtain = MotionEvent.obtain(uptimeMillis, uptimeMillis, 0, zzb5, zzb6, 0);
                zza2.zzw(obtain);
                obtain.recycle();
            } else if ("currentTime".equals(str)) {
                String str7 = (String) map.get(OSInfluenceConstants.TIME);
                if (str7 == null) {
                    com.google.android.gms.ads.internal.util.zze.zzj("Time parameter missing from currentTime video GMSG.");
                    return;
                }
                try {
                    zza2.zzv((int) (Float.parseFloat(str7) * 1000.0f));
                } catch (NumberFormatException unused5) {
                    com.google.android.gms.ads.internal.util.zze.zzj("Could not parse time parameter from currentTime video GMSG: ".concat(str7));
                }
            } else if ("hide".equals(str)) {
                zza2.setVisibility(4);
            } else if ("load".equals(str)) {
                zza2.zzq();
            } else if ("loadControl".equals(str)) {
                zzc(zza2, map);
            } else if ("muted".equals(str)) {
                if (Boolean.parseBoolean((String) map.get("muted"))) {
                    zza2.zzr();
                } else {
                    zza2.zzH();
                }
            } else if ("pause".equals(str)) {
                zza2.zzt();
            } else if ("play".equals(str)) {
                zza2.zzu();
            } else if ("show".equals(str)) {
                zza2.setVisibility(0);
            } else if ("src".equals(str)) {
                String str8 = (String) map.get("src");
                Integer num = null;
                if (map.containsKey("periodicReportIntervalMs")) {
                    try {
                        num = Integer.valueOf(Integer.parseInt((String) map.get("periodicReportIntervalMs")));
                    } catch (NumberFormatException unused6) {
                        com.google.android.gms.ads.internal.util.zze.zzj("Video gmsg invalid numeric parameter 'periodicReportIntervalMs': ".concat(String.valueOf((String) map.get("periodicReportIntervalMs"))));
                    }
                }
                String[] strArr = {str8};
                String str9 = (String) map.get("demuxed");
                if (str9 != null) {
                    try {
                        JSONArray jSONArray = new JSONArray(str9);
                        String[] strArr2 = new String[jSONArray.length()];
                        for (int r7 = 0; r7 < jSONArray.length(); r7++) {
                            strArr2[r7] = jSONArray.getString(r7);
                        }
                        strArr = strArr2;
                    } catch (JSONException unused7) {
                        com.google.android.gms.ads.internal.util.zze.zzj("Malformed demuxed URL list for playback: ".concat(str9));
                        strArr = new String[]{str8};
                    }
                }
                if (num != null) {
                    zzciwVar.zzC(num.intValue());
                }
                zza2.zzD(str8, strArr);
            } else if ("touchMove".equals(str)) {
                Context context3 = zzciwVar.getContext();
                zza2.zzG(zzb(context3, map, "dx", 0), zzb(context3, map, "dy", 0));
                if (this.zza) {
                    return;
                }
                zzciwVar.zzw();
                this.zza = true;
            } else if ("volume".equals(str)) {
                String str10 = (String) map.get("volume");
                if (str10 == null) {
                    com.google.android.gms.ads.internal.util.zze.zzj("Level parameter missing from volume video GMSG.");
                    return;
                }
                try {
                    zza2.zzF(Float.parseFloat(str10));
                } catch (NumberFormatException unused8) {
                    com.google.android.gms.ads.internal.util.zze.zzj("Could not parse volume parameter from volume video GMSG: ".concat(str10));
                }
            } else if ("watermark".equals(str)) {
                zza2.zzm();
            } else {
                com.google.android.gms.ads.internal.util.zze.zzj("Unknown video action: ".concat(str));
            }
        }
    }
}
