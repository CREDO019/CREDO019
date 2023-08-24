package com.google.android.gms.internal.ads;

import android.webkit.WebResourceResponse;
import android.webkit.WebView;
import java.io.File;
import java.util.Collections;
import java.util.Map;

/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
public class zzcnq extends zzcmu {
    public zzcnq(zzcmn zzcmnVar, zzbel zzbelVar, boolean z) {
        super(zzcmnVar, zzbelVar, z);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final WebResourceResponse zzM(WebView webView, String str, Map map) {
        String str2;
        if (!(webView instanceof zzcmn)) {
            com.google.android.gms.ads.internal.util.zze.zzj("Tried to intercept request from a WebView that wasn't an AdWebView.");
            return null;
        }
        zzcmn zzcmnVar = (zzcmn) webView;
        zzcdo zzcdoVar = this.zza;
        if (zzcdoVar != null) {
            zzcdoVar.zzd(str, map, 1);
        }
        if (!"mraid.js".equalsIgnoreCase(new File(str).getName())) {
            if (map == null) {
                map = Collections.emptyMap();
            }
            return super.zzc(str, map);
        }
        if (zzcmnVar.zzP() != null) {
            zzcmnVar.zzP().zzD();
        }
        if (zzcmnVar.zzQ().zzi()) {
            str2 = (String) com.google.android.gms.ads.internal.client.zzay.zzc().zzb(zzbiy.zzM);
        } else if (zzcmnVar.zzaC()) {
            str2 = (String) com.google.android.gms.ads.internal.client.zzay.zzc().zzb(zzbiy.zzL);
        } else {
            str2 = (String) com.google.android.gms.ads.internal.client.zzay.zzc().zzb(zzbiy.zzK);
        }
        com.google.android.gms.ads.internal.zzt.zzq();
        return com.google.android.gms.ads.internal.util.zzs.zzu(zzcmnVar.getContext(), zzcmnVar.zzp().zza, str2);
    }
}
