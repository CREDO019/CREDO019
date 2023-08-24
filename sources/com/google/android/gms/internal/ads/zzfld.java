package com.google.android.gms.internal.ads;

import android.os.Handler;
import android.text.TextUtils;
import android.webkit.WebView;
import com.google.android.exoplayer2.ExoPlayer;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import org.json.JSONObject;

/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
public final class zzfld extends zzfla {
    private WebView zza;
    private Long zzb = null;
    private final Map zzc;

    public zzfld(Map map, String str) {
        this.zzc = map;
    }

    @Override // com.google.android.gms.internal.ads.zzfla
    public final void zzc() {
        super.zzc();
        new Handler().postDelayed(new zzflc(this), Math.max(4000 - (this.zzb == null ? 4000L : TimeUnit.MILLISECONDS.convert(System.nanoTime() - this.zzb.longValue(), TimeUnit.NANOSECONDS)), (long) ExoPlayer.DEFAULT_DETACH_SURFACE_TIMEOUT_MS));
        this.zza = null;
    }

    @Override // com.google.android.gms.internal.ads.zzfla
    public final void zzf(zzfkd zzfkdVar, zzfkb zzfkbVar) {
        JSONObject jSONObject = new JSONObject();
        Map zzi = zzfkbVar.zzi();
        for (String str : zzi.keySet()) {
            zzfle.zzh(jSONObject, str, (zzfkj) zzi.get(str));
        }
        zzg(zzfkdVar, zzfkbVar, jSONObject);
    }

    @Override // com.google.android.gms.internal.ads.zzfla
    public final void zzj() {
        WebView webView = new WebView(zzfkr.zzb().zza());
        this.zza = webView;
        webView.getSettings().setJavaScriptEnabled(true);
        zzi(this.zza);
        WebView webView2 = this.zza;
        if (webView2 != null && !TextUtils.isEmpty(null)) {
            webView2.loadUrl("javascript: null");
        }
        Iterator it = this.zzc.keySet().iterator();
        if (!it.hasNext()) {
            this.zzb = Long.valueOf(System.nanoTime());
            return;
        }
        zzfkj zzfkjVar = (zzfkj) this.zzc.get((String) it.next());
        throw null;
    }
}
