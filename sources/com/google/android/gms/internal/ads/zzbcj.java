package com.google.android.gms.internal.ads;

import android.webkit.ValueCallback;
import android.webkit.WebView;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
public final class zzbcj implements Runnable {
    final ValueCallback zza;
    final /* synthetic */ zzbcb zzb;
    final /* synthetic */ WebView zzc;
    final /* synthetic */ boolean zzd;
    final /* synthetic */ zzbcl zze;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzbcj(zzbcl zzbclVar, final zzbcb zzbcbVar, final WebView webView, final boolean z) {
        this.zze = zzbclVar;
        this.zzb = zzbcbVar;
        this.zzc = webView;
        this.zzd = z;
        this.zza = new ValueCallback() { // from class: com.google.android.gms.internal.ads.zzbci
            @Override // android.webkit.ValueCallback
            public final void onReceiveValue(Object obj) {
                zzbcj zzbcjVar = zzbcj.this;
                zzbcb zzbcbVar2 = zzbcbVar;
                WebView webView2 = webView;
                boolean z2 = z;
                zzbcjVar.zze.zzd(zzbcbVar2, webView2, (String) obj, z2);
            }
        };
    }

    @Override // java.lang.Runnable
    public final void run() {
        if (this.zzc.getSettings().getJavaScriptEnabled()) {
            try {
                this.zzc.evaluateJavascript("(function() { return  {text:document.body.innerText}})();", this.zza);
            } catch (Throwable unused) {
                this.zza.onReceiveValue("");
            }
        }
    }
}
