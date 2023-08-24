package com.google.android.gms.internal.ads;

import android.content.Context;
import android.net.Uri;
import android.text.TextUtils;
import android.view.View;
import android.webkit.JavascriptInterface;

/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
public final class zzcnn {
    private final zzcno zza;
    private final zzcnm zzb;

    public zzcnn(zzcno zzcnoVar, zzcnm zzcnmVar, byte[] bArr) {
        this.zzb = zzcnmVar;
        this.zza = zzcnoVar;
    }

    /* JADX WARN: Type inference failed for: r0v1, types: [com.google.android.gms.internal.ads.zzcno, com.google.android.gms.internal.ads.zzcnv] */
    @JavascriptInterface
    public String getClickSignals(String str) {
        if (TextUtils.isEmpty(str)) {
            com.google.android.gms.ads.internal.util.zze.zza("Click string is empty, not proceeding.");
            return "";
        }
        ?? r0 = this.zza;
        zzapb zzK = r0.zzK();
        if (zzK == null) {
            com.google.android.gms.ads.internal.util.zze.zza("Signal utils is empty, ignoring.");
            return "";
        }
        zzaox zzc = zzK.zzc();
        if (zzc == null) {
            com.google.android.gms.ads.internal.util.zze.zza("Signals object is empty, ignoring.");
            return "";
        } else if (r0.getContext() == null) {
            com.google.android.gms.ads.internal.util.zze.zza("Context is null, ignoring.");
            return "";
        } else {
            Context context = this.zza.getContext();
            zzcno zzcnoVar = this.zza;
            return zzc.zzf(context, str, (View) zzcnoVar, zzcnoVar.zzk());
        }
    }

    /* JADX WARN: Type inference failed for: r0v0, types: [com.google.android.gms.internal.ads.zzcno, com.google.android.gms.internal.ads.zzcnv] */
    @JavascriptInterface
    public String getViewSignals() {
        ?? r0 = this.zza;
        zzapb zzK = r0.zzK();
        if (zzK == null) {
            com.google.android.gms.ads.internal.util.zze.zza("Signal utils is empty, ignoring.");
            return "";
        }
        zzaox zzc = zzK.zzc();
        if (zzc == null) {
            com.google.android.gms.ads.internal.util.zze.zza("Signals object is empty, ignoring.");
            return "";
        } else if (r0.getContext() == null) {
            com.google.android.gms.ads.internal.util.zze.zza("Context is null, ignoring.");
            return "";
        } else {
            Context context = this.zza.getContext();
            zzcno zzcnoVar = this.zza;
            return zzc.zzh(context, (View) zzcnoVar, zzcnoVar.zzk());
        }
    }

    @JavascriptInterface
    public void notify(final String str) {
        if (TextUtils.isEmpty(str)) {
            com.google.android.gms.ads.internal.util.zze.zzj("URL is empty, ignoring message");
        } else {
            com.google.android.gms.ads.internal.util.zzs.zza.post(new Runnable() { // from class: com.google.android.gms.internal.ads.zzcnl
                @Override // java.lang.Runnable
                public final void run() {
                    zzcnn.this.zza(str);
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final /* synthetic */ void zza(String str) {
        zzcnm zzcnmVar = this.zzb;
        Uri parse = Uri.parse(str);
        zzcmu zzaL = ((zzcng) zzcnmVar.zza).zzaL();
        if (zzaL == null) {
            com.google.android.gms.ads.internal.util.zze.zzg("Unable to pass GMSG, no AdWebViewClient for AdWebView!");
        } else {
            zzaL.zzi(parse);
        }
    }
}
