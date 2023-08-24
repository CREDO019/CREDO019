package com.google.android.gms.internal.ads;

import android.webkit.WebView;

/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
final class zzflc implements Runnable {
    final /* synthetic */ zzfld zza;
    private final WebView zzb;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzflc(zzfld zzfldVar) {
        WebView webView;
        this.zza = zzfldVar;
        webView = zzfldVar.zza;
        this.zzb = webView;
    }

    @Override // java.lang.Runnable
    public final void run() {
        this.zzb.destroy();
    }
}
