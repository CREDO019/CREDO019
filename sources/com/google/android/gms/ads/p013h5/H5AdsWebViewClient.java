package com.google.android.gms.ads.p013h5;

import android.content.Context;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import com.google.android.gms.internal.ads.zzbqi;
import com.google.android.gms.internal.ads.zzbqv;

/* compiled from: com.google.android.gms:play-services-ads-lite@@21.2.0 */
/* renamed from: com.google.android.gms.ads.h5.H5AdsWebViewClient */
/* loaded from: classes2.dex */
public final class H5AdsWebViewClient extends zzbqi {
    private final zzbqv zza;

    public H5AdsWebViewClient(Context context, WebView webView) {
        this.zza = new zzbqv(context, webView);
    }

    public void clearAdObjects() {
        this.zza.zza();
    }

    @Override // com.google.android.gms.internal.ads.zzbqi
    protected WebViewClient getDelegate() {
        return this.zza;
    }

    public WebViewClient getDelegateWebViewClient() {
        return this.zza.getDelegate();
    }

    public void setDelegateWebViewClient(WebViewClient webViewClient) {
        this.zza.zzb(webViewClient);
    }
}
