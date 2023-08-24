package com.google.android.gms.internal.ads;

import android.webkit.WebResourceResponse;
import android.webkit.WebView;

/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
public final class zzcnp extends zzcnq {
    public zzcnp(zzcmn zzcmnVar, zzbel zzbelVar, boolean z) {
        super(zzcmnVar, zzbelVar, z);
    }

    @Override // com.google.android.gms.internal.ads.zzcmu, android.webkit.WebViewClient
    public final WebResourceResponse shouldInterceptRequest(WebView webView, String str) {
        return zzM(webView, str, null);
    }
}
