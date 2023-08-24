package com.google.android.gms.internal.ads;

import com.facebook.react.uimanager.ViewProps;
import com.onesignal.OneSignalDbContract;
import org.json.JSONException;
import org.json.JSONObject;

/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
public class zzbya {
    private final zzcmn zza;
    private final String zzb;

    public zzbya(zzcmn zzcmnVar, String str) {
        this.zza = zzcmnVar;
        this.zzb = str;
    }

    public final void zzf(int r3, int r4, int r5, int r6) {
        try {
            this.zza.zze("onDefaultPositionReceived", new JSONObject().put("x", r3).put("y", r4).put("width", r5).put("height", r6));
        } catch (JSONException e) {
            com.google.android.gms.ads.internal.util.zze.zzh("Error occurred while dispatching default position.", e);
        }
    }

    public final void zzg(String str) {
        try {
            JSONObject put = new JSONObject().put(OneSignalDbContract.NotificationTable.COLUMN_NAME_MESSAGE, str).put("action", this.zzb);
            zzcmn zzcmnVar = this.zza;
            if (zzcmnVar != null) {
                zzcmnVar.zze("onError", put);
            }
        } catch (JSONException e) {
            com.google.android.gms.ads.internal.util.zze.zzh("Error occurred while dispatching error event.", e);
        }
    }

    public final void zzh(String str) {
        try {
            this.zza.zze("onReadyEventReceived", new JSONObject().put("js", str));
        } catch (JSONException e) {
            com.google.android.gms.ads.internal.util.zze.zzh("Error occurred while dispatching ready Event.", e);
        }
    }

    public final void zzi(int r3, int r4, int r5, int r6, float f, int r8) {
        try {
            this.zza.zze("onScreenInfoChanged", new JSONObject().put("width", r3).put("height", r4).put("maxSizeWidth", r5).put("maxSizeHeight", r6).put("density", f).put(ViewProps.ROTATION, r8));
        } catch (JSONException e) {
            com.google.android.gms.ads.internal.util.zze.zzh("Error occurred while obtaining screen information.", e);
        }
    }

    public final void zzj(int r3, int r4, int r5, int r6) {
        try {
            this.zza.zze("onSizeChanged", new JSONObject().put("x", r3).put("y", r4).put("width", r5).put("height", r6));
        } catch (JSONException e) {
            com.google.android.gms.ads.internal.util.zze.zzh("Error occurred while dispatching size change.", e);
        }
    }

    public final void zzk(String str) {
        try {
            this.zza.zze("onStateChanged", new JSONObject().put("state", str));
        } catch (JSONException e) {
            com.google.android.gms.ads.internal.util.zze.zzh("Error occurred while dispatching state change.", e);
        }
    }
}
