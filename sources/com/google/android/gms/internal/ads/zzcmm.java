package com.google.android.gms.internal.ads;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.os.Message;
import android.support.p001v4.media.session.PlaybackStateCompat;
import android.view.View;
import android.view.WindowManager;
import android.webkit.ConsoleMessage;
import android.webkit.GeolocationPermissions;
import android.webkit.JsPromptResult;
import android.webkit.JsResult;
import android.webkit.WebChromeClient;
import android.webkit.WebStorage;
import android.webkit.WebView;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.google.android.exoplayer2.upstream.cache.CacheDataSink;
import org.apache.logging.log4j.message.ParameterizedMessage;

/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
public final class zzcmm extends WebChromeClient {
    private final zzcmn zza;

    public zzcmm(zzcmn zzcmnVar) {
        this.zza = zzcmnVar;
    }

    private static final Context zzb(WebView webView) {
        if (!(webView instanceof zzcmn)) {
            return webView.getContext();
        }
        zzcmn zzcmnVar = (zzcmn) webView;
        Activity zzk = zzcmnVar.zzk();
        return zzk != null ? zzk : zzcmnVar.getContext();
    }

    @Override // android.webkit.WebChromeClient
    public final void onCloseWindow(WebView webView) {
        if (!(webView instanceof zzcmn)) {
            com.google.android.gms.ads.internal.util.zze.zzj("Tried to close a WebView that wasn't an AdWebView.");
            return;
        }
        com.google.android.gms.ads.internal.overlay.zzl zzN = ((zzcmn) webView).zzN();
        if (zzN == null) {
            com.google.android.gms.ads.internal.util.zze.zzj("Tried to close an AdWebView not associated with an overlay.");
        } else {
            zzN.zzb();
        }
    }

    @Override // android.webkit.WebChromeClient
    public final boolean onConsoleMessage(ConsoleMessage consoleMessage) {
        String str = "JS: " + consoleMessage.message() + " (" + consoleMessage.sourceId() + ParameterizedMessage.ERROR_MSG_SEPARATOR + consoleMessage.lineNumber() + ")";
        if (str.contains("Application Cache")) {
            return super.onConsoleMessage(consoleMessage);
        }
        int r1 = zzcml.zza[consoleMessage.messageLevel().ordinal()];
        if (r1 == 1) {
            com.google.android.gms.ads.internal.util.zze.zzg(str);
        } else if (r1 == 2) {
            com.google.android.gms.ads.internal.util.zze.zzj(str);
        } else if (r1 == 3 || r1 == 4) {
            com.google.android.gms.ads.internal.util.zze.zzi(str);
        } else if (r1 == 5) {
            com.google.android.gms.ads.internal.util.zze.zze(str);
        } else {
            com.google.android.gms.ads.internal.util.zze.zzi(str);
        }
        return super.onConsoleMessage(consoleMessage);
    }

    @Override // android.webkit.WebChromeClient
    public final boolean onCreateWindow(WebView webView, boolean z, boolean z2, Message message) {
        WebView.WebViewTransport webViewTransport = (WebView.WebViewTransport) message.obj;
        WebView webView2 = new WebView(webView.getContext());
        if (this.zza.zzJ() != null) {
            webView2.setWebViewClient(this.zza.zzJ());
        }
        webViewTransport.setWebView(webView2);
        message.sendToTarget();
        return true;
    }

    @Override // android.webkit.WebChromeClient
    public final void onExceededDatabaseQuota(String str, String str2, long j, long j2, long j3, WebStorage.QuotaUpdater quotaUpdater) {
        long j4 = CacheDataSink.DEFAULT_FRAGMENT_SIZE - j3;
        if (j4 <= 0) {
            quotaUpdater.updateQuota(j);
            return;
        }
        if (j == 0) {
            if (j2 > j4 || j2 > 1048576) {
                j2 = 0;
            }
        } else if (j2 == 0) {
            j2 = Math.min(j + Math.min((long) PlaybackStateCompat.ACTION_PREPARE_FROM_URI, j4), 1048576L);
        } else {
            if (j2 <= Math.min(1048576 - j, j4)) {
                j += j2;
            }
            j2 = j;
        }
        quotaUpdater.updateQuota(j2);
    }

    @Override // android.webkit.WebChromeClient
    public final void onGeolocationPermissionsShowPrompt(String str, GeolocationPermissions.Callback callback) {
        boolean z;
        if (callback != null) {
            com.google.android.gms.ads.internal.zzt.zzq();
            if (!com.google.android.gms.ads.internal.util.zzs.zzx(this.zza.getContext(), "android.permission.ACCESS_FINE_LOCATION")) {
                com.google.android.gms.ads.internal.zzt.zzq();
                if (!com.google.android.gms.ads.internal.util.zzs.zzx(this.zza.getContext(), "android.permission.ACCESS_COARSE_LOCATION")) {
                    z = false;
                    callback.invoke(str, z, true);
                }
            }
            z = true;
            callback.invoke(str, z, true);
        }
    }

    @Override // android.webkit.WebChromeClient
    public final void onHideCustomView() {
        com.google.android.gms.ads.internal.overlay.zzl zzN = this.zza.zzN();
        if (zzN == null) {
            com.google.android.gms.ads.internal.util.zze.zzj("Could not get ad overlay when hiding custom view.");
        } else {
            zzN.zzf();
        }
    }

    @Override // android.webkit.WebChromeClient
    public final boolean onJsAlert(WebView webView, String str, String str2, JsResult jsResult) {
        return zza(zzb(webView), "alert", str, str2, null, jsResult, null, false);
    }

    @Override // android.webkit.WebChromeClient
    public final boolean onJsBeforeUnload(WebView webView, String str, String str2, JsResult jsResult) {
        return zza(zzb(webView), "onBeforeUnload", str, str2, null, jsResult, null, false);
    }

    @Override // android.webkit.WebChromeClient
    public final boolean onJsConfirm(WebView webView, String str, String str2, JsResult jsResult) {
        return zza(zzb(webView), "confirm", str, str2, null, jsResult, null, false);
    }

    @Override // android.webkit.WebChromeClient
    public final boolean onJsPrompt(WebView webView, String str, String str2, String str3, JsPromptResult jsPromptResult) {
        return zza(zzb(webView), "prompt", str, str2, str3, null, jsPromptResult, true);
    }

    @Override // android.webkit.WebChromeClient
    @Deprecated
    public final void onShowCustomView(View view, int r3, WebChromeClient.CustomViewCallback customViewCallback) {
        com.google.android.gms.ads.internal.overlay.zzl zzN = this.zza.zzN();
        if (zzN == null) {
            com.google.android.gms.ads.internal.util.zze.zzj("Could not get ad overlay when showing custom view.");
            customViewCallback.onCustomViewHidden();
            return;
        }
        zzN.zzA(view, customViewCallback);
        zzN.zzy(r3);
    }

    protected final boolean zza(Context context, String str, String str2, String str3, String str4, JsResult jsResult, JsPromptResult jsPromptResult, boolean z) {
        zzcmn zzcmnVar;
        com.google.android.gms.ads.internal.zzb zzd;
        try {
            zzcmnVar = this.zza;
        } catch (WindowManager.BadTokenException e) {
            com.google.android.gms.ads.internal.util.zze.zzk("Fail to display Dialog.", e);
        }
        if (zzcmnVar == null || zzcmnVar.zzP() == null || this.zza.zzP().zzd() == null || (zzd = this.zza.zzP().zzd()) == null || zzd.zzc()) {
            com.google.android.gms.ads.internal.zzt.zzq();
            AlertDialog.Builder zzG = com.google.android.gms.ads.internal.util.zzs.zzG(context);
            zzG.setTitle(str2);
            if (!z) {
                zzG.setMessage(str3).setPositiveButton(17039370, new zzcmh(jsResult)).setNegativeButton(17039360, new zzcmg(jsResult)).setOnCancelListener(new zzcmf(jsResult)).create().show();
            } else {
                LinearLayout linearLayout = new LinearLayout(context);
                linearLayout.setOrientation(1);
                TextView textView = new TextView(context);
                textView.setText(str3);
                EditText editText = new EditText(context);
                editText.setText(str4);
                linearLayout.addView(textView);
                linearLayout.addView(editText);
                zzG.setView(linearLayout).setPositiveButton(17039370, new zzcmk(jsPromptResult, editText)).setNegativeButton(17039360, new zzcmj(jsPromptResult)).setOnCancelListener(new zzcmi(jsPromptResult)).create().show();
            }
            return true;
        }
        zzd.zzb("window." + str + "('" + str3 + "')");
        return false;
    }

    @Override // android.webkit.WebChromeClient
    public final void onShowCustomView(View view, WebChromeClient.CustomViewCallback customViewCallback) {
        onShowCustomView(view, -1, customViewCallback);
    }
}
