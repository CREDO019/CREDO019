package com.google.android.gms.internal.ads;

import android.content.Context;
import android.graphics.Rect;
import android.media.AudioManager;
import android.os.Build;
import android.os.PowerManager;
import android.text.TextUtils;
import android.view.Display;
import android.view.WindowManager;
import com.facebook.react.uimanager.ViewProps;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
public final class zzcve implements zzbty {
    private final Context zza;
    private final zzbbi zzb;
    private final PowerManager zzc;

    public zzcve(Context context, zzbbi zzbbiVar) {
        this.zza = context;
        this.zzb = zzbbiVar;
        this.zzc = (PowerManager) context.getSystemService("power");
    }

    @Override // com.google.android.gms.internal.ads.zzbty
    /* renamed from: zza */
    public final JSONObject zzb(zzcvh zzcvhVar) throws JSONException {
        boolean isScreenOn;
        JSONObject jSONObject;
        JSONArray jSONArray = new JSONArray();
        JSONObject jSONObject2 = new JSONObject();
        zzbbl zzbblVar = zzcvhVar.zzf;
        if (zzbblVar == null) {
            jSONObject = new JSONObject();
        } else if (this.zzb.zzd() != null) {
            boolean z = zzbblVar.zza;
            JSONObject jSONObject3 = new JSONObject();
            JSONObject put = jSONObject3.put("afmaVersion", this.zzb.zzb()).put("activeViewJSON", this.zzb.zzd()).put("timestamp", zzcvhVar.zzd).put("adFormat", this.zzb.zza()).put("hashCode", this.zzb.zzc()).put("isMraid", false);
            boolean z2 = zzcvhVar.zzc;
            JSONObject put2 = put.put("isStopped", false).put("isPaused", zzcvhVar.zzb).put("isNative", this.zzb.zze());
            if (Build.VERSION.SDK_INT >= 20) {
                isScreenOn = this.zzc.isInteractive();
            } else {
                isScreenOn = this.zzc.isScreenOn();
            }
            put2.put("isScreenOn", isScreenOn).put("appMuted", com.google.android.gms.ads.internal.zzt.zzs().zze()).put("appVolume", com.google.android.gms.ads.internal.zzt.zzs().zza()).put("deviceVolume", com.google.android.gms.ads.internal.util.zzab.zzb(this.zza.getApplicationContext()));
            if (((Boolean) com.google.android.gms.ads.internal.client.zzay.zzc().zzb(zzbiy.zzeK)).booleanValue()) {
                AudioManager audioManager = (AudioManager) this.zza.getApplicationContext().getSystemService("audio");
                Integer valueOf = audioManager == null ? null : Integer.valueOf(audioManager.getMode());
                if (valueOf != null) {
                    jSONObject3.put("audioMode", valueOf);
                }
            }
            Rect rect = new Rect();
            Display defaultDisplay = ((WindowManager) this.zza.getSystemService("window")).getDefaultDisplay();
            rect.right = defaultDisplay.getWidth();
            rect.bottom = defaultDisplay.getHeight();
            jSONObject3.put("windowVisibility", zzbblVar.zzb).put("isAttachedToWindow", z).put("viewBox", new JSONObject().put(ViewProps.TOP, zzbblVar.zzc.top).put(ViewProps.BOTTOM, zzbblVar.zzc.bottom).put("left", zzbblVar.zzc.left).put("right", zzbblVar.zzc.right)).put("adBox", new JSONObject().put(ViewProps.TOP, zzbblVar.zzd.top).put(ViewProps.BOTTOM, zzbblVar.zzd.bottom).put("left", zzbblVar.zzd.left).put("right", zzbblVar.zzd.right)).put("globalVisibleBox", new JSONObject().put(ViewProps.TOP, zzbblVar.zze.top).put(ViewProps.BOTTOM, zzbblVar.zze.bottom).put("left", zzbblVar.zze.left).put("right", zzbblVar.zze.right)).put("globalVisibleBoxVisible", zzbblVar.zzf).put("localVisibleBox", new JSONObject().put(ViewProps.TOP, zzbblVar.zzg.top).put(ViewProps.BOTTOM, zzbblVar.zzg.bottom).put("left", zzbblVar.zzg.left).put("right", zzbblVar.zzg.right)).put("localVisibleBoxVisible", zzbblVar.zzh).put("hitBox", new JSONObject().put(ViewProps.TOP, zzbblVar.zzi.top).put(ViewProps.BOTTOM, zzbblVar.zzi.bottom).put("left", zzbblVar.zzi.left).put("right", zzbblVar.zzi.right)).put("screenDensity", this.zza.getResources().getDisplayMetrics().density);
            jSONObject3.put("isVisible", zzcvhVar.zza);
            if (((Boolean) com.google.android.gms.ads.internal.client.zzay.zzc().zzb(zzbiy.zzbi)).booleanValue()) {
                JSONArray jSONArray2 = new JSONArray();
                List<Rect> list = zzbblVar.zzk;
                if (list != null) {
                    for (Rect rect2 : list) {
                        jSONArray2.put(new JSONObject().put(ViewProps.TOP, rect2.top).put(ViewProps.BOTTOM, rect2.bottom).put("left", rect2.left).put("right", rect2.right));
                    }
                }
                jSONObject3.put("scrollableContainerBoxes", jSONArray2);
            }
            if (!TextUtils.isEmpty(zzcvhVar.zze)) {
                jSONObject3.put("doneReasonCode", "u");
            }
            jSONObject = jSONObject3;
        } else {
            throw new JSONException("Active view Info cannot be null.");
        }
        jSONArray.put(jSONObject);
        jSONObject2.put("units", jSONArray);
        return jSONObject2;
    }
}
