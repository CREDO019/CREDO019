package com.google.android.gms.internal.consent_sdk;

import android.util.Log;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: com.google.android.ump:user-messaging-platform@@2.0.0 */
/* loaded from: classes.dex */
public final class zzbf extends WebViewClient {
    final /* synthetic */ zzbg zza;

    /* JADX INFO: Access modifiers changed from: package-private */
    public /* synthetic */ zzbf(zzbg zzbgVar, zzbe zzbeVar) {
        this.zza = zzbgVar;
    }

    @Override // android.webkit.WebViewClient
    public final void onLoadResource(WebView webView, String str) {
        zzbm zzbmVar;
        if (zzbg.zzc(this.zza, str)) {
            zzbmVar = this.zza.zzb;
            zzbmVar.zzd(str);
        }
    }

    @Override // android.webkit.WebViewClient
    public final void onPageFinished(WebView webView, String str) {
        boolean z;
        z = this.zza.zzc;
        if (z) {
            return;
        }
        Log.d("UserMessagingPlatform", "Wall html loaded.");
        zzbg.zze(this.zza, true);
    }

    @Override // android.webkit.WebViewClient
    public final void onReceivedError(WebView webView, int r2, String str, String str2) {
        zzbm zzbmVar;
        zzbmVar = this.zza.zzb;
        zzbmVar.zze(r2, str, str2);
    }

    @Override // android.webkit.WebViewClient
    public final boolean shouldOverrideUrlLoading(WebView webView, WebResourceRequest webResourceRequest) {
        zzbm zzbmVar;
        String uri = webResourceRequest.getUrl().toString();
        if (zzbg.zzc(this.zza, uri)) {
            zzbmVar = this.zza.zzb;
            zzbmVar.zzd(uri);
            return true;
        }
        return false;
    }

    @Override // android.webkit.WebViewClient
    public final boolean shouldOverrideUrlLoading(WebView webView, String str) {
        zzbm zzbmVar;
        if (zzbg.zzc(this.zza, str)) {
            zzbmVar = this.zza.zzb;
            zzbmVar.zzd(str);
            return true;
        }
        return false;
    }
}
