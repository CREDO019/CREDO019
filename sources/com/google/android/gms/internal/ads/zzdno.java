package com.google.android.gms.internal.ads;

import android.content.Context;
import android.graphics.Point;
import android.os.Bundle;
import android.os.RemoteException;
import android.util.DisplayMetrics;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.util.Clock;
import com.google.android.gms.common.util.PlatformVersion;
import java.lang.ref.WeakReference;
import java.util.Map;
import javax.annotation.ParametersAreNonnullByDefault;
import org.json.JSONException;
import org.json.JSONObject;

/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
@ParametersAreNonnullByDefault
/* loaded from: classes2.dex */
public final class zzdno implements zzdow {
    private com.google.android.gms.ads.internal.client.zzcq zzA;
    private final Context zza;
    private final zzdoz zzb;
    private final JSONObject zzc;
    private final zzdtl zzd;
    private final zzdoo zze;
    private final zzapb zzf;
    private final zzddq zzg;
    private final zzdcw zzh;
    private final zzdkj zzi;
    private final zzfcs zzj;
    private final zzcgt zzk;
    private final zzfdn zzl;
    private final zzcvi zzm;
    private final zzdpr zzn;
    private final Clock zzo;
    private final zzdkg zzp;
    private final zzfju zzq;
    private final zzfjc zzr;
    private boolean zzt;
    private boolean zzs = false;
    private boolean zzu = false;
    private boolean zzv = false;
    private Point zzw = new Point();
    private Point zzx = new Point();
    private long zzy = 0;
    private long zzz = 0;

    public zzdno(Context context, zzdoz zzdozVar, JSONObject jSONObject, zzdtl zzdtlVar, zzdoo zzdooVar, zzapb zzapbVar, zzddq zzddqVar, zzdcw zzdcwVar, zzdkj zzdkjVar, zzfcs zzfcsVar, zzcgt zzcgtVar, zzfdn zzfdnVar, zzcvi zzcviVar, zzdpr zzdprVar, Clock clock, zzdkg zzdkgVar, zzfju zzfjuVar, zzfjc zzfjcVar) {
        this.zza = context;
        this.zzb = zzdozVar;
        this.zzc = jSONObject;
        this.zzd = zzdtlVar;
        this.zze = zzdooVar;
        this.zzf = zzapbVar;
        this.zzg = zzddqVar;
        this.zzh = zzdcwVar;
        this.zzi = zzdkjVar;
        this.zzj = zzfcsVar;
        this.zzk = zzcgtVar;
        this.zzl = zzfdnVar;
        this.zzm = zzcviVar;
        this.zzn = zzdprVar;
        this.zzo = clock;
        this.zzp = zzdkgVar;
        this.zzq = zzfjuVar;
        this.zzr = zzfjcVar;
    }

    private final String zzB(View view, Map map) {
        if (map != null && view != null) {
            for (Map.Entry entry : map.entrySet()) {
                if (view.equals((View) ((WeakReference) entry.getValue()).get())) {
                    return (String) entry.getKey();
                }
            }
        }
        int zzc = this.zze.zzc();
        if (zzc != 1) {
            if (zzc != 2) {
                if (zzc != 6) {
                    return null;
                }
                return "3099";
            }
            return "2099";
        }
        return "1099";
    }

    private final boolean zzC(String str) {
        JSONObject optJSONObject = this.zzc.optJSONObject("allow_pub_event_reporting");
        return optJSONObject != null && optJSONObject.optBoolean(str, false);
    }

    private final boolean zzD() {
        return this.zzc.optBoolean("allow_custom_click_gesture", false);
    }

    private final boolean zzE(JSONObject jSONObject, JSONObject jSONObject2, JSONObject jSONObject3, JSONObject jSONObject4, String str, JSONObject jSONObject5, boolean z) {
        Preconditions.checkMainThread("recordImpression must be called on the main UI thread.");
        try {
            JSONObject jSONObject6 = new JSONObject();
            jSONObject6.put("ad", this.zzc);
            jSONObject6.put("asset_view_signal", jSONObject2);
            jSONObject6.put("ad_view_signal", jSONObject);
            jSONObject6.put("scroll_view_signal", jSONObject3);
            jSONObject6.put("lock_screen_signal", jSONObject4);
            jSONObject6.put("provided_signals", jSONObject5);
            if (((Boolean) com.google.android.gms.ads.internal.client.zzay.zzc().zzb(zzbiy.zzcF)).booleanValue()) {
                jSONObject6.put("view_signals", str);
            }
            jSONObject6.put("policy_validator_enabled", z);
            Context context = this.zza;
            JSONObject jSONObject7 = new JSONObject();
            com.google.android.gms.ads.internal.zzt.zzq();
            DisplayMetrics zzr = com.google.android.gms.ads.internal.util.zzs.zzr((WindowManager) context.getSystemService("window"));
            try {
                jSONObject7.put("width", com.google.android.gms.ads.internal.client.zzaw.zzb().zzb(context, zzr.widthPixels));
                jSONObject7.put("height", com.google.android.gms.ads.internal.client.zzaw.zzb().zzb(context, zzr.heightPixels));
            } catch (JSONException unused) {
                jSONObject7 = null;
            }
            jSONObject6.put("screen", jSONObject7);
            if (((Boolean) com.google.android.gms.ads.internal.client.zzay.zzc().zzb(zzbiy.zzhh)).booleanValue()) {
                this.zzd.zzi("/clickRecorded", new zzdnl(this, null));
            } else {
                this.zzd.zzi("/logScionEvent", new zzdnj(this, null));
            }
            this.zzd.zzi("/nativeImpression", new zzdnn(this, null));
            zzchd.zza(this.zzd.zzd("google.afma.nativeAds.handleImpression", jSONObject6), "Error during performing handleImpression");
            if (this.zzs) {
                return true;
            }
            this.zzs = com.google.android.gms.ads.internal.zzt.zzt().zzn(this.zza, this.zzk.zza, this.zzj.zzD.toString(), this.zzl.zzf);
            return true;
        } catch (JSONException e) {
            com.google.android.gms.ads.internal.util.zze.zzh("Unable to create impression JSON.", e);
            return false;
        }
    }

    @Override // com.google.android.gms.internal.ads.zzdow
    public final boolean zzA(Bundle bundle) {
        if (!zzC("impression_reporting")) {
            com.google.android.gms.ads.internal.util.zze.zzg("The ad slot cannot handle external impression events. You must be in the allow list to be able to report your impression events.");
            return false;
        }
        return zzE(null, null, null, null, null, com.google.android.gms.ads.internal.client.zzaw.zzb().zzj(bundle, null), false);
    }

    @Override // com.google.android.gms.internal.ads.zzdow
    public final JSONObject zzd(View view, Map map, Map map2) {
        JSONObject zzd = com.google.android.gms.ads.internal.util.zzbx.zzd(this.zza, map, map2, view);
        JSONObject zzg = com.google.android.gms.ads.internal.util.zzbx.zzg(this.zza, view);
        JSONObject zzf = com.google.android.gms.ads.internal.util.zzbx.zzf(view);
        JSONObject zze = com.google.android.gms.ads.internal.util.zzbx.zze(this.zza, view);
        try {
            JSONObject jSONObject = new JSONObject();
            jSONObject.put("asset_view_signal", zzd);
            jSONObject.put("ad_view_signal", zzg);
            jSONObject.put("scroll_view_signal", zzf);
            jSONObject.put("lock_screen_signal", zze);
            return jSONObject;
        } catch (JSONException e) {
            com.google.android.gms.ads.internal.util.zze.zzh("Unable to create native ad view signals JSON.", e);
            return null;
        }
    }

    @Override // com.google.android.gms.internal.ads.zzdow
    public final JSONObject zze(View view, Map map, Map map2) {
        JSONObject zzd = zzd(view, map, map2);
        JSONObject jSONObject = new JSONObject();
        try {
            if (this.zzv && zzD()) {
                jSONObject.put("custom_click_gesture_eligible", true);
            }
            if (zzd != null) {
                jSONObject.put("nas", zzd);
            }
        } catch (JSONException e) {
            com.google.android.gms.ads.internal.util.zze.zzh("Unable to create native click meta data JSON.", e);
        }
        return jSONObject;
    }

    @Override // com.google.android.gms.internal.ads.zzdow
    public final void zzf() {
        try {
            com.google.android.gms.ads.internal.client.zzcq zzcqVar = this.zzA;
            if (zzcqVar != null) {
                zzcqVar.zze();
            }
        } catch (RemoteException e) {
            com.google.android.gms.ads.internal.util.zze.zzl("#007 Could not call remote method.", e);
        }
    }

    @Override // com.google.android.gms.internal.ads.zzdow
    public final void zzg() {
        if (this.zzc.optBoolean("custom_one_point_five_click_enabled", false)) {
            this.zzn.zzb();
        }
    }

    @Override // com.google.android.gms.internal.ads.zzdow
    public final void zzh() {
        this.zzd.zzf();
    }

    @Override // com.google.android.gms.internal.ads.zzdow
    public final void zzj(View view, View view2, Map map, Map map2, boolean z) {
        JSONObject zzd = com.google.android.gms.ads.internal.util.zzbx.zzd(this.zza, map, map2, view2);
        JSONObject zzg = com.google.android.gms.ads.internal.util.zzbx.zzg(this.zza, view2);
        JSONObject zzf = com.google.android.gms.ads.internal.util.zzbx.zzf(view2);
        JSONObject zze = com.google.android.gms.ads.internal.util.zzbx.zze(this.zza, view2);
        String zzB = zzB(view, map);
        zzm(true == ((Boolean) com.google.android.gms.ads.internal.client.zzay.zzc().zzb(zzbiy.zzcH)).booleanValue() ? view2 : view, zzg, zzd, zzf, zze, zzB, com.google.android.gms.ads.internal.util.zzbx.zzc(zzB, this.zza, this.zzx, this.zzw), null, z, false);
    }

    @Override // com.google.android.gms.internal.ads.zzdow
    public final void zzk(String str) {
        zzm(null, null, null, null, null, str, null, null, false, false);
    }

    @Override // com.google.android.gms.internal.ads.zzdow
    public final void zzl(Bundle bundle) {
        if (bundle == null) {
            com.google.android.gms.ads.internal.util.zze.zze("Click data is null. No click is reported.");
        } else if (!zzC("click_reporting")) {
            com.google.android.gms.ads.internal.util.zze.zzg("The ad slot cannot handle external click events. You must be part of the allow list to be able to report your click events.");
        } else {
            Bundle bundle2 = bundle.getBundle("click_signal");
            zzm(null, null, null, null, null, bundle2 != null ? bundle2.getString("asset_id") : null, null, com.google.android.gms.ads.internal.client.zzaw.zzb().zzj(bundle, null), false, false);
        }
    }

    protected final void zzm(View view, JSONObject jSONObject, JSONObject jSONObject2, JSONObject jSONObject3, JSONObject jSONObject4, String str, JSONObject jSONObject5, JSONObject jSONObject6, boolean z, boolean z2) {
        String str2;
        Preconditions.checkMainThread("performClick must be called on the main UI thread.");
        try {
            JSONObject jSONObject7 = new JSONObject();
            jSONObject7.put("ad", this.zzc);
            jSONObject7.put("asset_view_signal", jSONObject2);
            jSONObject7.put("ad_view_signal", jSONObject);
            jSONObject7.put("click_signal", jSONObject5);
            jSONObject7.put("scroll_view_signal", jSONObject3);
            jSONObject7.put("lock_screen_signal", jSONObject4);
            jSONObject7.put("has_custom_click_handler", this.zzb.zzc(this.zze.zzy()) != null);
            jSONObject7.put("provided_signals", jSONObject6);
            JSONObject jSONObject8 = new JSONObject();
            jSONObject8.put("asset_id", str);
            jSONObject8.put("template", this.zze.zzc());
            jSONObject8.put("view_aware_api_used", z);
            zzblo zzbloVar = this.zzl.zzi;
            jSONObject8.put("custom_mute_requested", zzbloVar != null && zzbloVar.zzg);
            jSONObject8.put("custom_mute_enabled", (this.zze.zzF().isEmpty() || this.zze.zzk() == null) ? false : true);
            if (this.zzn.zza() != null && this.zzc.optBoolean("custom_one_point_five_click_enabled", false)) {
                jSONObject8.put("custom_one_point_five_click_eligible", true);
            }
            jSONObject8.put("timestamp", this.zzo.currentTimeMillis());
            if (this.zzv && zzD()) {
                jSONObject8.put("custom_click_gesture_eligible", true);
            }
            if (z2) {
                jSONObject8.put("is_custom_click_gesture", true);
            }
            jSONObject8.put("has_custom_click_handler", this.zzb.zzc(this.zze.zzy()) != null);
            try {
                JSONObject optJSONObject = this.zzc.optJSONObject("tracking_urls_and_actions");
                if (optJSONObject == null) {
                    optJSONObject = new JSONObject();
                }
                str2 = this.zzf.zzc().zze(this.zza, optJSONObject.optString("click_string"), view);
            } catch (Exception e) {
                com.google.android.gms.ads.internal.util.zze.zzh("Exception obtaining click signals", e);
                str2 = null;
            }
            jSONObject8.put("click_signals", str2);
            if (((Boolean) com.google.android.gms.ads.internal.client.zzay.zzc().zzb(zzbiy.zzdH)).booleanValue()) {
                jSONObject8.put("open_chrome_custom_tab", true);
            }
            if (((Boolean) com.google.android.gms.ads.internal.client.zzay.zzc().zzb(zzbiy.zzhl)).booleanValue() && PlatformVersion.isAtLeastR()) {
                jSONObject8.put("try_fallback_for_deep_link", true);
            }
            if (((Boolean) com.google.android.gms.ads.internal.client.zzay.zzc().zzb(zzbiy.zzhm)).booleanValue() && PlatformVersion.isAtLeastR()) {
                jSONObject8.put("in_app_link_handling_for_android_11_enabled", true);
            }
            jSONObject7.put("click", jSONObject8);
            JSONObject jSONObject9 = new JSONObject();
            long currentTimeMillis = this.zzo.currentTimeMillis();
            jSONObject9.put("time_from_last_touch_down", currentTimeMillis - this.zzy);
            jSONObject9.put("time_from_last_touch", currentTimeMillis - this.zzz);
            jSONObject7.put("touch_signal", jSONObject9);
            zzchd.zza(this.zzd.zzd("google.afma.nativeAds.handleClick", jSONObject7), "Error during performing handleClick");
        } catch (JSONException e2) {
            com.google.android.gms.ads.internal.util.zze.zzh("Unable to create click JSON.", e2);
        }
    }

    @Override // com.google.android.gms.internal.ads.zzdow
    public final void zzn(View view, Map map, Map map2, boolean z) {
        if (!this.zzv) {
            com.google.android.gms.ads.internal.util.zze.zze("Custom click reporting failed. enableCustomClickGesture is not set.");
        } else if (!zzD()) {
            com.google.android.gms.ads.internal.util.zze.zze("Custom click reporting failed. Ad unit id not in the allow list.");
        } else {
            JSONObject zzd = com.google.android.gms.ads.internal.util.zzbx.zzd(this.zza, map, map2, view);
            JSONObject zzg = com.google.android.gms.ads.internal.util.zzbx.zzg(this.zza, view);
            JSONObject zzf = com.google.android.gms.ads.internal.util.zzbx.zzf(view);
            JSONObject zze = com.google.android.gms.ads.internal.util.zzbx.zze(this.zza, view);
            String zzB = zzB(null, map);
            zzm(view, zzg, zzd, zzf, zze, zzB, com.google.android.gms.ads.internal.util.zzbx.zzc(zzB, this.zza, this.zzx, this.zzw), null, z, true);
        }
    }

    @Override // com.google.android.gms.internal.ads.zzdow
    public final void zzo() {
        Preconditions.checkMainThread("recordDownloadedImpression must be called on the main UI thread.");
        try {
            JSONObject jSONObject = new JSONObject();
            jSONObject.put("ad", this.zzc);
            zzchd.zza(this.zzd.zzd("google.afma.nativeAds.handleDownloadedImpression", jSONObject), "Error during performing handleDownloadedImpression");
        } catch (JSONException e) {
            zzcgn.zzh("", e);
        }
    }

    @Override // com.google.android.gms.internal.ads.zzdow
    public final void zzp(View view, Map map, Map map2) {
        String zzh;
        JSONObject zzd = com.google.android.gms.ads.internal.util.zzbx.zzd(this.zza, map, map2, view);
        JSONObject zzg = com.google.android.gms.ads.internal.util.zzbx.zzg(this.zza, view);
        JSONObject zzf = com.google.android.gms.ads.internal.util.zzbx.zzf(view);
        JSONObject zze = com.google.android.gms.ads.internal.util.zzbx.zze(this.zza, view);
        if (((Boolean) com.google.android.gms.ads.internal.client.zzay.zzc().zzb(zzbiy.zzcF)).booleanValue()) {
            try {
                zzh = this.zzf.zzc().zzh(this.zza, view, null);
            } catch (Exception unused) {
                com.google.android.gms.ads.internal.util.zze.zzg("Exception getting data.");
            }
            zzE(zzg, zzd, zzf, zze, zzh, null, com.google.android.gms.ads.internal.util.zzbx.zzh(this.zza, this.zzj));
        }
        zzh = null;
        zzE(zzg, zzd, zzf, zze, zzh, null, com.google.android.gms.ads.internal.util.zzbx.zzh(this.zza, this.zzj));
    }

    @Override // com.google.android.gms.internal.ads.zzdow
    public final void zzq() {
        zzE(null, null, null, null, null, null, false);
    }

    @Override // com.google.android.gms.internal.ads.zzdow
    public final void zzr(View view, MotionEvent motionEvent, View view2) {
        this.zzw = com.google.android.gms.ads.internal.util.zzbx.zza(motionEvent, view2);
        long currentTimeMillis = this.zzo.currentTimeMillis();
        this.zzz = currentTimeMillis;
        if (motionEvent.getAction() == 0) {
            this.zzy = currentTimeMillis;
            this.zzx = this.zzw;
        }
        MotionEvent obtain = MotionEvent.obtain(motionEvent);
        obtain.setLocation(this.zzw.x, this.zzw.y);
        this.zzf.zzd(obtain);
        obtain.recycle();
    }

    @Override // com.google.android.gms.internal.ads.zzdow
    public final void zzs(Bundle bundle) {
        if (bundle == null) {
            com.google.android.gms.ads.internal.util.zze.zze("Touch event data is null. No touch event is reported.");
        } else if (!zzC("touch_reporting")) {
            com.google.android.gms.ads.internal.util.zze.zzg("The ad slot cannot handle external touch events. You must be in the allow list to be able to report your touch events.");
        } else {
            float f = bundle.getFloat("x");
            float f2 = bundle.getFloat("y");
            this.zzf.zzc().zzl((int) f, (int) f2, bundle.getInt("duration_ms"));
        }
    }

    @Override // com.google.android.gms.internal.ads.zzdow
    public final void zzt(View view) {
        if (!this.zzc.optBoolean("custom_one_point_five_click_enabled", false)) {
            com.google.android.gms.ads.internal.util.zze.zzj("setClickConfirmingView: Your account need to be in the allow list to use this feature.\nContact your account manager for more information.");
            return;
        }
        zzdpr zzdprVar = this.zzn;
        if (view == null) {
            return;
        }
        view.setOnClickListener(zzdprVar);
        view.setClickable(true);
        zzdprVar.zzc = new WeakReference(view);
    }

    @Override // com.google.android.gms.internal.ads.zzdow
    public final void zzu() {
        this.zzv = true;
    }

    @Override // com.google.android.gms.internal.ads.zzdow
    public final void zzv(com.google.android.gms.ads.internal.client.zzcq zzcqVar) {
        this.zzA = zzcqVar;
    }

    @Override // com.google.android.gms.internal.ads.zzdow
    public final void zzw(zzbnu zzbnuVar) {
        if (!this.zzc.optBoolean("custom_one_point_five_click_enabled", false)) {
            com.google.android.gms.ads.internal.util.zze.zzj("setUnconfirmedClickListener: Your account need to be in the allow list to use this feature.\nContact your account manager for more information.");
        } else {
            this.zzn.zzc(zzbnuVar);
        }
    }

    @Override // com.google.android.gms.internal.ads.zzdow
    public final void zzx(View view, Map map, Map map2, View.OnTouchListener onTouchListener, View.OnClickListener onClickListener) {
        this.zzw = new Point();
        this.zzx = new Point();
        if (!this.zzt) {
            this.zzp.zza(view);
            this.zzt = true;
        }
        view.setOnTouchListener(onTouchListener);
        view.setClickable(true);
        view.setOnClickListener(onClickListener);
        this.zzm.zzi(this);
        boolean zzi = com.google.android.gms.ads.internal.util.zzbx.zzi(this.zzk.zzc);
        if (map != null) {
            for (Map.Entry entry : map.entrySet()) {
                View view2 = (View) ((WeakReference) entry.getValue()).get();
                if (view2 != null) {
                    if (zzi) {
                        view2.setOnTouchListener(onTouchListener);
                    }
                    view2.setClickable(true);
                    view2.setOnClickListener(onClickListener);
                }
            }
        }
        if (map2 != null) {
            for (Map.Entry entry2 : map2.entrySet()) {
                View view3 = (View) ((WeakReference) entry2.getValue()).get();
                if (view3 != null) {
                    if (zzi) {
                        view3.setOnTouchListener(onTouchListener);
                    }
                    view3.setClickable(false);
                }
            }
        }
    }

    @Override // com.google.android.gms.internal.ads.zzdow
    public final void zzy(View view, Map map) {
        this.zzw = new Point();
        this.zzx = new Point();
        if (view != null) {
            this.zzp.zzb(view);
        }
        this.zzt = false;
    }

    @Override // com.google.android.gms.internal.ads.zzdow
    public final boolean zzz() {
        return zzD();
    }

    @Override // com.google.android.gms.internal.ads.zzdow
    public final void zzi(com.google.android.gms.ads.internal.client.zzcu zzcuVar) {
        try {
            if (this.zzu) {
                return;
            }
            if (zzcuVar == null) {
                zzdoo zzdooVar = this.zze;
                if (zzdooVar.zzk() != null) {
                    this.zzu = true;
                    this.zzq.zzc(zzdooVar.zzk().zzf(), this.zzr);
                    zzf();
                    return;
                }
            }
            this.zzu = true;
            this.zzq.zzc(zzcuVar.zzf(), this.zzr);
            zzf();
        } catch (RemoteException e) {
            com.google.android.gms.ads.internal.util.zze.zzl("#007 Could not call remote method.", e);
        }
    }
}
