package com.google.android.gms.internal.ads;

import android.os.Build;
import android.webkit.WebView;
import com.amplitude.api.Constants;
import java.util.Iterator;
import org.json.JSONArray;
import org.json.JSONObject;

/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
public class zzfla {
    private zzfly zza;
    private long zzb;
    private int zzc;

    public zzfla() {
        zzb();
        this.zza = new zzfly(null);
    }

    public final WebView zza() {
        return (WebView) this.zza.get();
    }

    public final void zzb() {
        this.zzb = System.nanoTime();
        this.zzc = 1;
    }

    public void zzc() {
        this.zza.clear();
    }

    public final void zzd(String str, long j) {
        if (j < this.zzb || this.zzc == 3) {
            return;
        }
        this.zzc = 3;
        zzfkt.zza().zzf(zza(), str);
    }

    public final void zze(String str, long j) {
        if (j >= this.zzb) {
            this.zzc = 2;
            zzfkt.zza().zzf(zza(), str);
        }
    }

    public void zzf(zzfkd zzfkdVar, zzfkb zzfkbVar) {
        zzg(zzfkdVar, zzfkbVar, null);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final void zzg(zzfkd zzfkdVar, zzfkb zzfkbVar, JSONObject jSONObject) {
        String zzh = zzfkdVar.zzh();
        JSONObject jSONObject2 = new JSONObject();
        zzfle.zzh(jSONObject2, "environment", "app");
        zzfle.zzh(jSONObject2, "adSessionType", zzfkbVar.zzd());
        JSONObject jSONObject3 = new JSONObject();
        String str = Build.MANUFACTURER;
        String str2 = Build.MODEL;
        zzfle.zzh(jSONObject3, "deviceType", str + "; " + str2);
        zzfle.zzh(jSONObject3, "osVersion", Integer.toString(Build.VERSION.SDK_INT));
        zzfle.zzh(jSONObject3, "os", Constants.PLATFORM);
        zzfle.zzh(jSONObject2, "deviceInfo", jSONObject3);
        JSONArray jSONArray = new JSONArray();
        jSONArray.put("clid");
        jSONArray.put("vlid");
        zzfle.zzh(jSONObject2, "supports", jSONArray);
        JSONObject jSONObject4 = new JSONObject();
        zzfle.zzh(jSONObject4, "partnerName", zzfkbVar.zze().zzb());
        zzfle.zzh(jSONObject4, "partnerVersion", zzfkbVar.zze().zzc());
        zzfle.zzh(jSONObject2, "omidNativeInfo", jSONObject4);
        JSONObject jSONObject5 = new JSONObject();
        zzfle.zzh(jSONObject5, "libraryVersion", "1.3.31-google_20220407");
        zzfle.zzh(jSONObject5, "appId", zzfkr.zzb().zza().getApplicationContext().getPackageName());
        zzfle.zzh(jSONObject2, "app", jSONObject5);
        if (zzfkbVar.zzf() != null) {
            zzfle.zzh(jSONObject2, "contentUrl", zzfkbVar.zzf());
        }
        zzfle.zzh(jSONObject2, "customReferenceData", zzfkbVar.zzg());
        JSONObject jSONObject6 = new JSONObject();
        Iterator it = zzfkbVar.zzh().iterator();
        if (it.hasNext()) {
            zzfkj zzfkjVar = (zzfkj) it.next();
            throw null;
        } else {
            zzfkt.zza().zzg(zza(), zzh, jSONObject2, jSONObject6, jSONObject);
        }
    }

    public final void zzh(float f) {
        zzfkt.zza().zze(zza(), f);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final void zzi(WebView webView) {
        this.zza = new zzfly(webView);
    }

    public void zzj() {
    }

    public final boolean zzk() {
        return this.zza.get() != null;
    }
}
