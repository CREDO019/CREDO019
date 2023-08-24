package com.google.android.gms.ads.nonagon.signalgeneration;

import android.content.Context;
import android.os.Bundle;
import android.util.Pair;
import android.view.MotionEvent;
import android.webkit.JavascriptInterface;
import android.webkit.WebView;
import com.google.ads.mediation.admob.AdMobAdapter;
import com.google.android.exoplayer2.source.rtsp.SessionDescription;
import com.google.android.gms.ads.AdFormat;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.internal.client.zzay;
import com.google.android.gms.ads.query.QueryInfo;
import com.google.android.gms.internal.ads.zzapb;
import com.google.android.gms.internal.ads.zzbiy;
import com.google.android.gms.internal.ads.zzcha;
import com.google.android.gms.internal.ads.zzdxt;
import java.util.UUID;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
public final class TaggingLibraryJsInterface {
    private final Context zza;
    private final WebView zzb;
    private final zzapb zzc;
    private final int zzd;
    private final zzdxt zze;
    private final boolean zzf;

    /* JADX INFO: Access modifiers changed from: package-private */
    public TaggingLibraryJsInterface(WebView webView, zzapb zzapbVar, zzdxt zzdxtVar) {
        this.zzb = webView;
        Context context = webView.getContext();
        this.zza = context;
        this.zzc = zzapbVar;
        this.zze = zzdxtVar;
        zzbiy.zzc(context);
        this.zzd = ((Integer) zzay.zzc().zzb(zzbiy.zzhX)).intValue();
        this.zzf = ((Boolean) zzay.zzc().zzb(zzbiy.zzhY)).booleanValue();
    }

    @JavascriptInterface
    public String getClickSignals(String str) {
        try {
            long currentTimeMillis = com.google.android.gms.ads.internal.zzt.zzB().currentTimeMillis();
            String zze = this.zzc.zzc().zze(this.zza, str, this.zzb);
            if (this.zzf) {
                zzf.zzc(this.zze, null, "csg", new Pair("clat", String.valueOf(com.google.android.gms.ads.internal.zzt.zzB().currentTimeMillis() - currentTimeMillis)));
            }
            return zze;
        } catch (RuntimeException e) {
            com.google.android.gms.ads.internal.util.zze.zzh("Exception getting click signals. ", e);
            com.google.android.gms.ads.internal.zzt.zzp().zzt(e, "TaggingLibraryJsInterface.getClickSignals");
            return "";
        }
    }

    @JavascriptInterface
    public String getClickSignalsWithTimeout(final String str, int r5) {
        if (r5 <= 0) {
            com.google.android.gms.ads.internal.util.zze.zzg("Invalid timeout for getting click signals. Timeout=" + r5);
            return "";
        }
        try {
            return (String) zzcha.zza.zzb(new Callable() { // from class: com.google.android.gms.ads.nonagon.signalgeneration.zzao
                @Override // java.util.concurrent.Callable
                public final Object call() {
                    return TaggingLibraryJsInterface.this.getClickSignals(str);
                }
            }).get(Math.min(r5, this.zzd), TimeUnit.MILLISECONDS);
        } catch (InterruptedException | ExecutionException | TimeoutException e) {
            com.google.android.gms.ads.internal.util.zze.zzh("Exception getting click signals with timeout. ", e);
            com.google.android.gms.ads.internal.zzt.zzp().zzt(e, "TaggingLibraryJsInterface.getClickSignalsWithTimeout");
            return e instanceof TimeoutException ? "17" : "";
        }
    }

    @JavascriptInterface
    public String getQueryInfo() {
        com.google.android.gms.ads.internal.zzt.zzq();
        String str = UUID.randomUUID().toString();
        Bundle bundle = new Bundle();
        bundle.putString("query_info_type", "requester_type_6");
        Context context = this.zza;
        AdFormat adFormat = AdFormat.BANNER;
        AdRequest.Builder builder = new AdRequest.Builder();
        builder.addNetworkExtrasBundle(AdMobAdapter.class, bundle);
        QueryInfo.generate(context, adFormat, builder.build(), new zzap(this, str));
        return str;
    }

    @JavascriptInterface
    public String getViewSignals() {
        try {
            long currentTimeMillis = com.google.android.gms.ads.internal.zzt.zzB().currentTimeMillis();
            String zzh = this.zzc.zzc().zzh(this.zza, this.zzb, null);
            if (this.zzf) {
                zzf.zzc(this.zze, null, "vsg", new Pair("vlat", String.valueOf(com.google.android.gms.ads.internal.zzt.zzB().currentTimeMillis() - currentTimeMillis)));
            }
            return zzh;
        } catch (RuntimeException e) {
            com.google.android.gms.ads.internal.util.zze.zzh("Exception getting view signals. ", e);
            com.google.android.gms.ads.internal.zzt.zzp().zzt(e, "TaggingLibraryJsInterface.getViewSignals");
            return "";
        }
    }

    @JavascriptInterface
    public String getViewSignalsWithTimeout(int r5) {
        if (r5 <= 0) {
            com.google.android.gms.ads.internal.util.zze.zzg("Invalid timeout for getting view signals. Timeout=" + r5);
            return "";
        }
        try {
            return (String) zzcha.zza.zzb(new Callable() { // from class: com.google.android.gms.ads.nonagon.signalgeneration.zzan
                @Override // java.util.concurrent.Callable
                public final Object call() {
                    return TaggingLibraryJsInterface.this.getViewSignals();
                }
            }).get(Math.min(r5, this.zzd), TimeUnit.MILLISECONDS);
        } catch (InterruptedException | ExecutionException | TimeoutException e) {
            com.google.android.gms.ads.internal.util.zze.zzh("Exception getting view signals with timeout. ", e);
            com.google.android.gms.ads.internal.zzt.zzp().zzt(e, "TaggingLibraryJsInterface.getViewSignalsWithTimeout");
            return e instanceof TimeoutException ? "17" : "";
        }
    }

    @JavascriptInterface
    public void reportTouchEvent(String str) {
        int r1;
        int r2;
        int r3;
        float f;
        int r0;
        try {
            JSONObject jSONObject = new JSONObject(str);
            r1 = jSONObject.getInt("x");
            r2 = jSONObject.getInt("y");
            r3 = jSONObject.getInt("duration_ms");
            f = (float) jSONObject.getDouble("force");
            r0 = jSONObject.getInt(SessionDescription.ATTR_TYPE);
        } catch (RuntimeException | JSONException e) {
            e = e;
        }
        try {
            this.zzc.zzd(MotionEvent.obtain(0L, r3, r0 != 0 ? r0 != 1 ? r0 != 2 ? r0 != 3 ? -1 : 3 : 2 : 1 : 0, r1, r2, f, 1.0f, 0, 1.0f, 1.0f, 0, 0));
        } catch (RuntimeException e2) {
            e = e2;
            com.google.android.gms.ads.internal.util.zze.zzh("Failed to parse the touch string. ", e);
            com.google.android.gms.ads.internal.zzt.zzp().zzt(e, "TaggingLibraryJsInterface.reportTouchEvent");
        } catch (JSONException e3) {
            e = e3;
            com.google.android.gms.ads.internal.util.zze.zzh("Failed to parse the touch string. ", e);
            com.google.android.gms.ads.internal.zzt.zzp().zzt(e, "TaggingLibraryJsInterface.reportTouchEvent");
        }
    }
}
