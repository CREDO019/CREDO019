package com.google.android.gms.internal.ads;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.graphics.Canvas;
import android.net.Uri;
import android.os.Build;
import android.os.RemoteException;
import android.util.DisplayMetrics;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewTreeObserver;
import android.view.WindowManager;
import android.webkit.DownloadListener;
import android.webkit.ValueCallback;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import com.amplitude.api.Constants;
import com.google.android.exoplayer2.metadata.icy.IcyHeaders;
import com.google.android.exoplayer2.source.rtsp.SessionDescription;
import com.google.android.gms.common.util.PlatformVersion;
import com.google.android.gms.common.util.Predicate;
import com.google.android.gms.dynamic.IObjectWrapper;
import com.google.android.gms.internal.ads.zzbiy;
import com.google.android.gms.internal.ads.zzfph;
import expo.modules.updates.UpdatesConfiguration;
import io.nlopez.smartlocation.geofencing.providers.GeofencingGooglePlayServicesProvider;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Callable;
import javax.annotation.ParametersAreNonnullByDefault;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
@ParametersAreNonnullByDefault
/* loaded from: classes2.dex */
public final class zzcng extends WebView implements DownloadListener, ViewTreeObserver.OnGlobalLayoutListener, zzcmn {
    public static final /* synthetic */ int zza = 0;
    private boolean zzA;
    private boolean zzB;
    private zzbln zzC;
    private zzbll zzD;
    private zzbcz zzE;
    private int zzF;
    private int zzG;
    private zzbjk zzH;
    private final zzbjk zzI;
    private zzbjk zzJ;
    private final zzbjl zzK;
    private int zzL;
    private int zzM;
    private int zzN;
    private com.google.android.gms.ads.internal.overlay.zzl zzO;
    private boolean zzP;
    private final com.google.android.gms.ads.internal.util.zzci zzQ;
    private int zzR;
    private int zzS;
    private int zzT;
    private int zzU;
    private Map zzV;
    private final WindowManager zzW;
    private final zzbel zzX;
    private final zzcob zzb;
    private final zzapb zzc;
    private final zzbjx zzd;
    private final zzcgt zze;
    private com.google.android.gms.ads.internal.zzl zzf;
    private final com.google.android.gms.ads.internal.zza zzg;
    private final DisplayMetrics zzh;
    private final float zzi;
    private zzfcs zzj;
    private zzfcv zzk;
    private boolean zzl;
    private boolean zzm;
    private zzcmu zzn;
    private com.google.android.gms.ads.internal.overlay.zzl zzo;
    private IObjectWrapper zzp;
    private zzcoc zzq;
    private final String zzr;
    private boolean zzs;
    private boolean zzt;
    private boolean zzu;
    private boolean zzv;
    private Boolean zzw;
    private boolean zzx;
    private final String zzy;
    private zzcnj zzz;

    /* JADX INFO: Access modifiers changed from: protected */
    public zzcng(zzcob zzcobVar, zzcoc zzcocVar, String str, boolean z, boolean z2, zzapb zzapbVar, zzbjx zzbjxVar, zzcgt zzcgtVar, zzbjn zzbjnVar, com.google.android.gms.ads.internal.zzl zzlVar, com.google.android.gms.ads.internal.zza zzaVar, zzbel zzbelVar, zzfcs zzfcsVar, zzfcv zzfcvVar) {
        super(zzcobVar);
        zzfcv zzfcvVar2;
        this.zzl = false;
        this.zzm = false;
        this.zzx = true;
        this.zzy = "";
        this.zzR = -1;
        this.zzS = -1;
        this.zzT = -1;
        this.zzU = -1;
        this.zzb = zzcobVar;
        this.zzq = zzcocVar;
        this.zzr = str;
        this.zzu = z;
        this.zzc = zzapbVar;
        this.zzd = zzbjxVar;
        this.zze = zzcgtVar;
        this.zzf = zzlVar;
        this.zzg = zzaVar;
        WindowManager windowManager = (WindowManager) getContext().getSystemService("window");
        this.zzW = windowManager;
        com.google.android.gms.ads.internal.zzt.zzq();
        DisplayMetrics zzr = com.google.android.gms.ads.internal.util.zzs.zzr(windowManager);
        this.zzh = zzr;
        this.zzi = zzr.density;
        this.zzX = zzbelVar;
        this.zzj = zzfcsVar;
        this.zzk = zzfcvVar;
        this.zzQ = new com.google.android.gms.ads.internal.util.zzci(zzcobVar.zza(), this, this, null);
        setBackgroundColor(0);
        final WebSettings settings = getSettings();
        settings.setAllowFileAccess(false);
        try {
            settings.setJavaScriptEnabled(true);
        } catch (NullPointerException e) {
            com.google.android.gms.ads.internal.util.zze.zzh("Unable to enable Javascript.", e);
        }
        settings.setSavePassword(false);
        settings.setSupportMultipleWindows(true);
        settings.setJavaScriptCanOpenWindowsAutomatically(true);
        if (Build.VERSION.SDK_INT >= 21) {
            settings.setMixedContentMode(2);
        }
        settings.setUserAgentString(com.google.android.gms.ads.internal.zzt.zzq().zzc(zzcobVar, zzcgtVar.zza));
        com.google.android.gms.ads.internal.zzt.zzq();
        final Context context = getContext();
        com.google.android.gms.ads.internal.util.zzcb.zza(context, new Callable() { // from class: com.google.android.gms.ads.internal.util.zzm
            @Override // java.util.concurrent.Callable
            public final Object call() {
                WebSettings webSettings = settings;
                Context context2 = context;
                zzfph zzfphVar = zzs.zza;
                webSettings.setDatabasePath(context2.getDatabasePath("com.google.android.gms.ads.db").getAbsolutePath());
                webSettings.setDatabaseEnabled(true);
                webSettings.setDomStorageEnabled(true);
                webSettings.setDisplayZoomControls(false);
                webSettings.setBuiltInZoomControls(true);
                webSettings.setSupportZoom(true);
                if (((Boolean) com.google.android.gms.ads.internal.client.zzay.zzc().zzb(zzbiy.zzaE)).booleanValue()) {
                    webSettings.setTextZoom(100);
                }
                webSettings.setAllowContentAccess(false);
                return true;
            }
        });
        settings.setAllowFileAccessFromFileURLs(false);
        settings.setAllowUniversalAccessFromFileURLs(false);
        settings.setMediaPlaybackRequiresUserGesture(false);
        setDownloadListener(this);
        zzaU();
        addJavascriptInterface(new zzcnn(this, new zzcnm(this), null), "googleAdsJsInterface");
        removeJavascriptInterface("accessibility");
        removeJavascriptInterface("accessibilityTraversal");
        zzbc();
        zzbjl zzbjlVar = new zzbjl(new zzbjn(true, "make_wv", this.zzr));
        this.zzK = zzbjlVar;
        zzbjlVar.zza().zzc(null);
        if (((Boolean) com.google.android.gms.ads.internal.client.zzay.zzc().zzb(zzbiy.zzbB)).booleanValue() && (zzfcvVar2 = this.zzk) != null && zzfcvVar2.zzb != null) {
            zzbjlVar.zza().zzd("gqi", this.zzk.zzb);
        }
        zzbjlVar.zza();
        zzbjk zzf = zzbjn.zzf();
        this.zzI = zzf;
        zzbjlVar.zzb("native:view_create", zzf);
        this.zzJ = null;
        this.zzH = null;
        com.google.android.gms.ads.internal.util.zzce.zza().zzb(zzcobVar);
        com.google.android.gms.ads.internal.zzt.zzp().zzq();
    }

    private final synchronized void zzaU() {
        zzfcs zzfcsVar = this.zzj;
        if (zzfcsVar != null && zzfcsVar.zzao) {
            com.google.android.gms.ads.internal.util.zze.zze("Disabling hardware acceleration on an overlay.");
            zzaW();
            return;
        }
        if (!this.zzu && !this.zzq.zzi()) {
            com.google.android.gms.ads.internal.util.zze.zze("Enabling hardware acceleration on an AdView.");
            zzaY();
            return;
        }
        com.google.android.gms.ads.internal.util.zze.zze("Enabling hardware acceleration on an overlay.");
        zzaY();
    }

    private final synchronized void zzaV() {
        if (this.zzP) {
            return;
        }
        this.zzP = true;
        com.google.android.gms.ads.internal.zzt.zzp().zzp();
    }

    private final synchronized void zzaW() {
        if (!this.zzv) {
            setLayerType(1, null);
        }
        this.zzv = true;
    }

    private final void zzaX(boolean z) {
        HashMap hashMap = new HashMap();
        hashMap.put("isVisible", true != z ? SessionDescription.SUPPORTED_SDP_VERSION : IcyHeaders.REQUEST_HEADER_ENABLE_METADATA_VALUE);
        zzd("onAdVisibilityChanged", hashMap);
    }

    private final synchronized void zzaY() {
        if (this.zzv) {
            setLayerType(0, null);
        }
        this.zzv = false;
    }

    private final synchronized void zzaZ(String str) {
        try {
            super.loadUrl("about:blank");
        } catch (Throwable th) {
            com.google.android.gms.ads.internal.zzt.zzp().zzt(th, "AdWebViewImpl.loadUrlUnsafe");
            com.google.android.gms.ads.internal.util.zze.zzk("Could not call loadUrl in destroy(). ", th);
        }
    }

    private final void zzba() {
        zzbjf.zza(this.zzK.zza(), this.zzI, "aeh2");
    }

    private final synchronized void zzbb() {
        Map map = this.zzV;
        if (map != null) {
            for (zzckz zzckzVar : map.values()) {
                zzckzVar.release();
            }
        }
        this.zzV = null;
    }

    private final void zzbc() {
        zzbjl zzbjlVar = this.zzK;
        if (zzbjlVar == null) {
            return;
        }
        zzbjn zza2 = zzbjlVar.zza();
        zzbjd zzf = com.google.android.gms.ads.internal.zzt.zzp().zzf();
        if (zzf != null) {
            zzf.zzf(zza2);
        }
    }

    private final synchronized void zzbd() {
        Boolean zzk = com.google.android.gms.ads.internal.zzt.zzp().zzk();
        this.zzw = zzk;
        if (zzk == null) {
            try {
                evaluateJavascript("(function(){})()", null);
                zzaS(true);
            } catch (IllegalStateException unused) {
                zzaS(false);
            }
        }
    }

    @Override // android.webkit.WebView, com.google.android.gms.internal.ads.zzcmn
    public final synchronized void destroy() {
        zzbc();
        this.zzQ.zza();
        com.google.android.gms.ads.internal.overlay.zzl zzlVar = this.zzo;
        if (zzlVar != null) {
            zzlVar.zzb();
            this.zzo.zzl();
            this.zzo = null;
        }
        this.zzp = null;
        this.zzn.zzy();
        this.zzE = null;
        this.zzf = null;
        setOnClickListener(null);
        setOnTouchListener(null);
        if (this.zzt) {
            return;
        }
        com.google.android.gms.ads.internal.zzt.zzz().zzd(this);
        zzbb();
        this.zzt = true;
        if (((Boolean) com.google.android.gms.ads.internal.client.zzay.zzc().zzb(zzbiy.zzir)).booleanValue()) {
            com.google.android.gms.ads.internal.util.zze.zza("Initiating WebView self destruct sequence in 3...");
            com.google.android.gms.ads.internal.util.zze.zza("Loading blank page in WebView, 2...");
            zzaZ("about:blank");
            return;
        }
        com.google.android.gms.ads.internal.util.zze.zza("Destroying the WebView immediately...");
        zzW();
    }

    @Override // android.webkit.WebView
    public final synchronized void evaluateJavascript(String str, ValueCallback valueCallback) {
        if (!zzaB()) {
            super.evaluateJavascript(str, valueCallback);
            return;
        }
        zzcgn.zzl("#004 The webview is destroyed. Ignoring action.", null);
        if (valueCallback != null) {
            valueCallback.onReceiveValue(null);
        }
    }

    protected final void finalize() throws Throwable {
        try {
            synchronized (this) {
                if (!this.zzt) {
                    this.zzn.zzy();
                    com.google.android.gms.ads.internal.zzt.zzz().zzd(this);
                    zzbb();
                    zzaV();
                }
            }
        } finally {
            super.finalize();
        }
    }

    @Override // android.webkit.WebView, com.google.android.gms.internal.ads.zzcmn
    public final synchronized void loadData(String str, String str2, String str3) {
        if (zzaB()) {
            com.google.android.gms.ads.internal.util.zze.zzj("#004 The webview is destroyed. Ignoring action.");
        } else {
            super.loadData(str, str2, str3);
        }
    }

    @Override // android.webkit.WebView, com.google.android.gms.internal.ads.zzcmn
    public final synchronized void loadDataWithBaseURL(String str, String str2, String str3, String str4, String str5) {
        if (zzaB()) {
            com.google.android.gms.ads.internal.util.zze.zzj("#004 The webview is destroyed. Ignoring action.");
        } else {
            super.loadDataWithBaseURL(str, str2, str3, str4, str5);
        }
    }

    @Override // android.webkit.WebView, com.google.android.gms.internal.ads.zzcmn
    public final synchronized void loadUrl(String str) {
        if (zzaB()) {
            com.google.android.gms.ads.internal.util.zze.zzj("#004 The webview is destroyed. Ignoring action.");
        } else {
            super.loadUrl(str);
        }
    }

    @Override // com.google.android.gms.ads.internal.client.zza
    public final void onAdClicked() {
        zzcmu zzcmuVar = this.zzn;
        if (zzcmuVar != null) {
            zzcmuVar.onAdClicked();
        }
    }

    @Override // android.webkit.WebView, android.view.ViewGroup, android.view.View
    protected final synchronized void onAttachedToWindow() {
        super.onAttachedToWindow();
        if (!zzaB()) {
            this.zzQ.zzc();
        }
        boolean z = this.zzA;
        zzcmu zzcmuVar = this.zzn;
        if (zzcmuVar != null && zzcmuVar.zzK()) {
            if (!this.zzB) {
                this.zzn.zza();
                this.zzn.zzb();
                this.zzB = true;
            }
            zzaT();
            z = true;
        }
        zzaX(z);
    }

    @Override // android.view.ViewGroup, android.view.View
    protected final void onDetachedFromWindow() {
        zzcmu zzcmuVar;
        synchronized (this) {
            if (!zzaB()) {
                this.zzQ.zzd();
            }
            super.onDetachedFromWindow();
            if (this.zzB && (zzcmuVar = this.zzn) != null && zzcmuVar.zzK() && getViewTreeObserver() != null && getViewTreeObserver().isAlive()) {
                this.zzn.zza();
                this.zzn.zzb();
                this.zzB = false;
            }
        }
        zzaX(false);
    }

    @Override // android.webkit.DownloadListener
    public final void onDownloadStart(String str, String str2, String str3, String str4, long j) {
        try {
            Intent intent = new Intent("android.intent.action.VIEW");
            intent.setDataAndType(Uri.parse(str), str4);
            com.google.android.gms.ads.internal.zzt.zzq();
            com.google.android.gms.ads.internal.util.zzs.zzJ(getContext(), intent);
        } catch (ActivityNotFoundException unused) {
            com.google.android.gms.ads.internal.util.zze.zze("Couldn't find an Activity to view url/mimetype: " + str + " / " + str4);
        }
    }

    @Override // android.webkit.WebView, android.view.View
    protected final void onDraw(Canvas canvas) {
        if (zzaB()) {
            return;
        }
        if (Build.VERSION.SDK_INT == 21 && canvas.isHardwareAccelerated() && !isAttachedToWindow()) {
            return;
        }
        super.onDraw(canvas);
    }

    @Override // android.webkit.WebView, android.view.View
    public final boolean onGenericMotionEvent(MotionEvent motionEvent) {
        float axisValue = motionEvent.getAxisValue(9);
        float axisValue2 = motionEvent.getAxisValue(10);
        if (motionEvent.getActionMasked() == 8) {
            if (axisValue > 0.0f && !canScrollVertically(-1)) {
                return false;
            }
            if (axisValue < 0.0f && !canScrollVertically(1)) {
                return false;
            }
            if (axisValue2 > 0.0f && !canScrollHorizontally(-1)) {
                return false;
            }
            if (axisValue2 < 0.0f && !canScrollHorizontally(1)) {
                return false;
            }
        }
        return super.onGenericMotionEvent(motionEvent);
    }

    @Override // android.view.ViewTreeObserver.OnGlobalLayoutListener
    public final void onGlobalLayout() {
        boolean zzaT = zzaT();
        com.google.android.gms.ads.internal.overlay.zzl zzN = zzN();
        if (zzN == null || !zzaT) {
            return;
        }
        zzN.zzm();
    }

    /* JADX WARN: Removed duplicated region for block: B:104:0x01bd A[Catch: all -> 0x01e3, TRY_ENTER, TryCatch #0 {, blocks: (B:3:0x0001, B:5:0x0008, B:8:0x000d, B:10:0x0013, B:12:0x0017, B:15:0x0021, B:17:0x0029, B:20:0x002e, B:22:0x0036, B:24:0x0048, B:27:0x004d, B:29:0x0054, B:33:0x005e, B:36:0x0063, B:39:0x0076, B:45:0x0088, B:43:0x0082, B:48:0x0095, B:50:0x009d, B:52:0x00af, B:55:0x00b4, B:57:0x00d0, B:59:0x00d9, B:58:0x00d5, B:62:0x00de, B:64:0x00e6, B:67:0x00f3, B:76:0x0119, B:78:0x0120, B:83:0x0128, B:85:0x013a, B:87:0x0148, B:91:0x0155, B:94:0x015a, B:96:0x01a5, B:97:0x01a9, B:99:0x01b0, B:104:0x01bd, B:106:0x01c3, B:107:0x01c6, B:109:0x01ca, B:110:0x01d3, B:113:0x01de), top: B:119:0x0001 }] */
    /* JADX WARN: Removed duplicated region for block: B:85:0x013a A[Catch: all -> 0x01e3, TryCatch #0 {, blocks: (B:3:0x0001, B:5:0x0008, B:8:0x000d, B:10:0x0013, B:12:0x0017, B:15:0x0021, B:17:0x0029, B:20:0x002e, B:22:0x0036, B:24:0x0048, B:27:0x004d, B:29:0x0054, B:33:0x005e, B:36:0x0063, B:39:0x0076, B:45:0x0088, B:43:0x0082, B:48:0x0095, B:50:0x009d, B:52:0x00af, B:55:0x00b4, B:57:0x00d0, B:59:0x00d9, B:58:0x00d5, B:62:0x00de, B:64:0x00e6, B:67:0x00f3, B:76:0x0119, B:78:0x0120, B:83:0x0128, B:85:0x013a, B:87:0x0148, B:91:0x0155, B:94:0x015a, B:96:0x01a5, B:97:0x01a9, B:99:0x01b0, B:104:0x01bd, B:106:0x01c3, B:107:0x01c6, B:109:0x01ca, B:110:0x01d3, B:113:0x01de), top: B:119:0x0001 }] */
    /* JADX WARN: Removed duplicated region for block: B:94:0x015a A[Catch: all -> 0x01e3, TryCatch #0 {, blocks: (B:3:0x0001, B:5:0x0008, B:8:0x000d, B:10:0x0013, B:12:0x0017, B:15:0x0021, B:17:0x0029, B:20:0x002e, B:22:0x0036, B:24:0x0048, B:27:0x004d, B:29:0x0054, B:33:0x005e, B:36:0x0063, B:39:0x0076, B:45:0x0088, B:43:0x0082, B:48:0x0095, B:50:0x009d, B:52:0x00af, B:55:0x00b4, B:57:0x00d0, B:59:0x00d9, B:58:0x00d5, B:62:0x00de, B:64:0x00e6, B:67:0x00f3, B:76:0x0119, B:78:0x0120, B:83:0x0128, B:85:0x013a, B:87:0x0148, B:91:0x0155, B:94:0x015a, B:96:0x01a5, B:97:0x01a9, B:99:0x01b0, B:104:0x01bd, B:106:0x01c3, B:107:0x01c6, B:109:0x01ca, B:110:0x01d3, B:113:0x01de), top: B:119:0x0001 }] */
    @Override // android.webkit.WebView, android.widget.AbsoluteLayout, android.view.View
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    protected final synchronized void onMeasure(int r9, int r10) {
        /*
            Method dump skipped, instructions count: 486
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.ads.zzcng.onMeasure(int, int):void");
    }

    @Override // android.webkit.WebView, com.google.android.gms.internal.ads.zzcmn
    public final void onPause() {
        if (zzaB()) {
            return;
        }
        try {
            super.onPause();
        } catch (Exception e) {
            com.google.android.gms.ads.internal.util.zze.zzh("Could not pause webview.", e);
        }
    }

    @Override // android.webkit.WebView, com.google.android.gms.internal.ads.zzcmn
    public final void onResume() {
        if (zzaB()) {
            return;
        }
        try {
            super.onResume();
        } catch (Exception e) {
            com.google.android.gms.ads.internal.util.zze.zzh("Could not resume webview.", e);
        }
    }

    @Override // android.webkit.WebView, android.view.View
    public final boolean onTouchEvent(MotionEvent motionEvent) {
        if (!this.zzn.zzK() || this.zzn.zzI()) {
            zzapb zzapbVar = this.zzc;
            if (zzapbVar != null) {
                zzapbVar.zzd(motionEvent);
            }
            zzbjx zzbjxVar = this.zzd;
            if (zzbjxVar != null) {
                zzbjxVar.zzb(motionEvent);
            }
        } else {
            synchronized (this) {
                zzbln zzblnVar = this.zzC;
                if (zzblnVar != null) {
                    zzblnVar.zzd(motionEvent);
                }
            }
        }
        if (zzaB()) {
            return false;
        }
        return super.onTouchEvent(motionEvent);
    }

    @Override // android.webkit.WebView, com.google.android.gms.internal.ads.zzcmn
    public final void setWebViewClient(WebViewClient webViewClient) {
        super.setWebViewClient(webViewClient);
        if (webViewClient instanceof zzcmu) {
            this.zzn = (zzcmu) webViewClient;
        }
    }

    @Override // android.webkit.WebView
    public final void stopLoading() {
        if (zzaB()) {
            return;
        }
        try {
            super.stopLoading();
        } catch (Exception e) {
            com.google.android.gms.ads.internal.util.zze.zzh("Could not stop loading webview.", e);
        }
    }

    @Override // com.google.android.gms.internal.ads.zzciw
    public final void zzA(int r1) {
        this.zzM = r1;
    }

    @Override // com.google.android.gms.internal.ads.zzciw
    public final void zzB(boolean z) {
        this.zzn.zzB(false);
    }

    @Override // com.google.android.gms.internal.ads.zzciw
    public final synchronized void zzC(int r1) {
        this.zzL = r1;
    }

    @Override // com.google.android.gms.internal.ads.zzciw
    public final void zzD(int r1) {
    }

    @Override // com.google.android.gms.internal.ads.zzcmn, com.google.android.gms.internal.ads.zzciw
    public final synchronized void zzE(zzcnj zzcnjVar) {
        if (this.zzz != null) {
            com.google.android.gms.ads.internal.util.zze.zzg("Attempt to create multiple AdWebViewVideoControllers.");
        } else {
            this.zzz = zzcnjVar;
        }
    }

    @Override // com.google.android.gms.internal.ads.zzcmn, com.google.android.gms.internal.ads.zzcme
    public final zzfcs zzF() {
        return this.zzj;
    }

    @Override // com.google.android.gms.internal.ads.zzcmn
    public final Context zzG() {
        return this.zzb.zzb();
    }

    @Override // com.google.android.gms.internal.ads.zzcmn, com.google.android.gms.internal.ads.zzcnx
    public final View zzH() {
        return this;
    }

    @Override // com.google.android.gms.internal.ads.zzcmn
    public final WebView zzI() {
        return this;
    }

    @Override // com.google.android.gms.internal.ads.zzcmn
    public final WebViewClient zzJ() {
        return this.zzn;
    }

    @Override // com.google.android.gms.internal.ads.zzcmn, com.google.android.gms.internal.ads.zzcnv
    public final zzapb zzK() {
        return this.zzc;
    }

    @Override // com.google.android.gms.internal.ads.zzcmn
    public final synchronized zzbcz zzL() {
        return this.zzE;
    }

    @Override // com.google.android.gms.internal.ads.zzcmn
    public final synchronized zzbln zzM() {
        return this.zzC;
    }

    @Override // com.google.android.gms.internal.ads.zzcmn
    public final synchronized com.google.android.gms.ads.internal.overlay.zzl zzN() {
        return this.zzo;
    }

    @Override // com.google.android.gms.internal.ads.zzcmn
    public final synchronized com.google.android.gms.ads.internal.overlay.zzl zzO() {
        return this.zzO;
    }

    @Override // com.google.android.gms.internal.ads.zzcmn
    public final /* synthetic */ zzcoa zzP() {
        return this.zzn;
    }

    @Override // com.google.android.gms.internal.ads.zzcmn, com.google.android.gms.internal.ads.zzcnu
    public final synchronized zzcoc zzQ() {
        return this.zzq;
    }

    @Override // com.google.android.gms.internal.ads.zzcmn, com.google.android.gms.internal.ads.zzcnk
    public final zzfcv zzR() {
        return this.zzk;
    }

    @Override // com.google.android.gms.internal.ads.zzcmn
    public final synchronized IObjectWrapper zzS() {
        return this.zzp;
    }

    @Override // com.google.android.gms.internal.ads.zzcmn
    public final zzfyx zzT() {
        zzbjx zzbjxVar = this.zzd;
        return zzbjxVar == null ? zzfyo.zzi(null) : zzbjxVar.zza();
    }

    @Override // com.google.android.gms.internal.ads.zzcmn
    public final synchronized String zzU() {
        return this.zzr;
    }

    @Override // com.google.android.gms.internal.ads.zzcmn
    public final void zzV(zzfcs zzfcsVar, zzfcv zzfcvVar) {
        this.zzj = zzfcsVar;
        this.zzk = zzfcvVar;
    }

    @Override // com.google.android.gms.internal.ads.zzcmn
    public final synchronized void zzW() {
        com.google.android.gms.ads.internal.util.zze.zza("Destroying WebView!");
        zzaV();
        com.google.android.gms.ads.internal.util.zzs.zza.post(new zzcnf(this));
    }

    @Override // com.google.android.gms.internal.ads.zzcmn
    public final void zzX() {
        zzba();
        HashMap hashMap = new HashMap(1);
        hashMap.put(Constants.AMP_PLAN_VERSION, this.zze.zza);
        zzd("onhide", hashMap);
    }

    @Override // com.google.android.gms.internal.ads.zzcmn
    public final void zzY(int r4) {
        if (r4 == 0) {
            zzbjf.zza(this.zzK.zza(), this.zzI, "aebb2");
        }
        zzba();
        this.zzK.zza();
        this.zzK.zza().zzd("close_type", String.valueOf(r4));
        HashMap hashMap = new HashMap(2);
        hashMap.put("closetype", String.valueOf(r4));
        hashMap.put(Constants.AMP_PLAN_VERSION, this.zze.zza);
        zzd("onhide", hashMap);
    }

    @Override // com.google.android.gms.internal.ads.zzcmn
    public final void zzZ() {
        if (this.zzH == null) {
            zzbjf.zza(this.zzK.zza(), this.zzI, "aes2");
            this.zzK.zza();
            zzbjk zzf = zzbjn.zzf();
            this.zzH = zzf;
            this.zzK.zzb("native:view_show", zzf);
        }
        HashMap hashMap = new HashMap(1);
        hashMap.put(Constants.AMP_PLAN_VERSION, this.zze.zza);
        zzd("onshow", hashMap);
    }

    @Override // com.google.android.gms.internal.ads.zzbsv
    public final void zza(String str) {
        throw null;
    }

    @Override // com.google.android.gms.internal.ads.zzcmn
    public final boolean zzaA(final boolean z, final int r4) {
        destroy();
        this.zzX.zzb(new zzbek() { // from class: com.google.android.gms.internal.ads.zzcnd
            @Override // com.google.android.gms.internal.ads.zzbek
            public final void zza(zzbga zzbgaVar) {
                boolean z2 = z;
                int r1 = r4;
                int r2 = zzcng.zza;
                zzbif zza2 = zzbig.zza();
                if (zza2.zzc() != z2) {
                    zza2.zza(z2);
                }
                zza2.zzb(r1);
                zzbgaVar.zzj((zzbig) zza2.zzal());
            }
        });
        this.zzX.zzc(GeofencingGooglePlayServicesProvider.RESULT_CODE);
        return true;
    }

    @Override // com.google.android.gms.internal.ads.zzcmn
    public final synchronized boolean zzaB() {
        return this.zzt;
    }

    @Override // com.google.android.gms.internal.ads.zzcmn
    public final synchronized boolean zzaC() {
        return this.zzu;
    }

    @Override // com.google.android.gms.internal.ads.zzcmn
    public final boolean zzaD() {
        return false;
    }

    @Override // com.google.android.gms.internal.ads.zzcmn
    public final synchronized boolean zzaE() {
        return this.zzx;
    }

    @Override // com.google.android.gms.internal.ads.zzcns
    public final void zzaF(com.google.android.gms.ads.internal.overlay.zzc zzcVar, boolean z) {
        this.zzn.zzr(zzcVar, z);
    }

    @Override // com.google.android.gms.internal.ads.zzcns
    public final void zzaG(com.google.android.gms.ads.internal.util.zzbr zzbrVar, zzefz zzefzVar, zzdxo zzdxoVar, zzfhz zzfhzVar, String str, String str2, int r15) {
        this.zzn.zzs(zzbrVar, zzefzVar, zzdxoVar, zzfhzVar, str, str2, 14);
    }

    @Override // com.google.android.gms.internal.ads.zzcns
    public final void zzaH(boolean z, int r3, boolean z2) {
        this.zzn.zzt(z, r3, z2);
    }

    @Override // com.google.android.gms.internal.ads.zzcns
    public final void zzaI(boolean z, int r3, String str, boolean z2) {
        this.zzn.zzv(z, r3, str, z2);
    }

    @Override // com.google.android.gms.internal.ads.zzcns
    public final void zzaJ(boolean z, int r8, String str, String str2, boolean z2) {
        this.zzn.zzw(z, r8, str, str2, z2);
    }

    public final zzcmu zzaL() {
        return this.zzn;
    }

    final synchronized Boolean zzaM() {
        return this.zzw;
    }

    protected final synchronized void zzaP(String str, ValueCallback valueCallback) {
        if (zzaB()) {
            com.google.android.gms.ads.internal.util.zze.zzj("#004 The webview is destroyed. Ignoring action.");
        } else {
            evaluateJavascript(str, null);
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final void zzaQ(String str) {
        if (PlatformVersion.isAtLeastKitKat()) {
            if (zzaM() == null) {
                zzbd();
            }
            if (zzaM().booleanValue()) {
                zzaP(str, null);
                return;
            } else {
                zzaR("javascript:".concat(str));
                return;
            }
        }
        zzaR("javascript:".concat(str));
    }

    protected final synchronized void zzaR(String str) {
        if (zzaB()) {
            com.google.android.gms.ads.internal.util.zze.zzj("#004 The webview is destroyed. Ignoring action.");
        } else {
            loadUrl(str);
        }
    }

    final void zzaS(Boolean bool) {
        synchronized (this) {
            this.zzw = bool;
        }
        com.google.android.gms.ads.internal.zzt.zzp().zzu(bool);
    }

    public final boolean zzaT() {
        int r6;
        int r7;
        boolean z = false;
        if (this.zzn.zzJ() || this.zzn.zzK()) {
            com.google.android.gms.ads.internal.client.zzaw.zzb();
            DisplayMetrics displayMetrics = this.zzh;
            int zzu = zzcgg.zzu(displayMetrics, displayMetrics.widthPixels);
            com.google.android.gms.ads.internal.client.zzaw.zzb();
            DisplayMetrics displayMetrics2 = this.zzh;
            int zzu2 = zzcgg.zzu(displayMetrics2, displayMetrics2.heightPixels);
            Activity zza2 = this.zzb.zza();
            if (zza2 == null || zza2.getWindow() == null) {
                r6 = zzu;
                r7 = zzu2;
            } else {
                com.google.android.gms.ads.internal.zzt.zzq();
                int[] zzN = com.google.android.gms.ads.internal.util.zzs.zzN(zza2);
                com.google.android.gms.ads.internal.client.zzaw.zzb();
                int zzu3 = zzcgg.zzu(this.zzh, zzN[0]);
                com.google.android.gms.ads.internal.client.zzaw.zzb();
                r7 = zzcgg.zzu(this.zzh, zzN[1]);
                r6 = zzu3;
            }
            int r0 = this.zzS;
            if (r0 == zzu && this.zzR == zzu2 && this.zzT == r6 && this.zzU == r7) {
                return false;
            }
            z = (r0 == zzu && this.zzR == zzu2) ? true : true;
            this.zzS = zzu;
            this.zzR = zzu2;
            this.zzT = r6;
            this.zzU = r7;
            new zzbya(this, "").zzi(zzu, zzu2, r6, r7, this.zzh.density, this.zzW.getDefaultDisplay().getRotation());
            return z;
        }
        return false;
    }

    @Override // com.google.android.gms.internal.ads.zzcmn
    public final void zzaa() {
        throw null;
    }

    @Override // com.google.android.gms.internal.ads.zzcmn
    public final void zzab(boolean z) {
        this.zzn.zzh(z);
    }

    @Override // com.google.android.gms.internal.ads.zzcmn
    public final void zzac() {
        this.zzQ.zzb();
    }

    @Override // com.google.android.gms.internal.ads.zzcmn
    public final synchronized void zzad(String str, String str2, String str3) {
        String str4;
        if (!zzaB()) {
            String[] strArr = new String[1];
            String str5 = (String) com.google.android.gms.ads.internal.client.zzay.zzc().zzb(zzbiy.zzN);
            JSONObject jSONObject = new JSONObject();
            try {
                jSONObject.put(Constants.AMP_PLAN_VERSION, str5);
                jSONObject.put("sdk", "Google Mobile Ads");
                jSONObject.put(UpdatesConfiguration.UPDATES_CONFIGURATION_SDK_VERSION_KEY, "12.4.51-000");
                str4 = "<script>Object.defineProperty(window,'MRAID_ENV',{get:function(){return " + jSONObject.toString() + "}});</script>";
            } catch (JSONException e) {
                com.google.android.gms.ads.internal.util.zze.zzk("Unable to build MRAID_ENV", e);
                str4 = null;
            }
            strArr[0] = str4;
            super.loadDataWithBaseURL(str, zzcnt.zza(str2, strArr), "text/html", "UTF-8", null);
            return;
        }
        com.google.android.gms.ads.internal.util.zze.zzj("#004 The webview is destroyed. Ignoring action.");
    }

    @Override // com.google.android.gms.internal.ads.zzcmn
    public final void zzae() {
        if (this.zzJ == null) {
            this.zzK.zza();
            zzbjk zzf = zzbjn.zzf();
            this.zzJ = zzf;
            this.zzK.zzb("native:view_load", zzf);
        }
    }

    @Override // com.google.android.gms.internal.ads.zzcmn
    public final void zzaf(String str, zzbpq zzbpqVar) {
        zzcmu zzcmuVar = this.zzn;
        if (zzcmuVar != null) {
            zzcmuVar.zzx(str, zzbpqVar);
        }
    }

    @Override // com.google.android.gms.internal.ads.zzcmn
    public final void zzag() {
        throw null;
    }

    @Override // com.google.android.gms.internal.ads.zzcmn
    public final synchronized void zzah(com.google.android.gms.ads.internal.overlay.zzl zzlVar) {
        this.zzo = zzlVar;
    }

    @Override // com.google.android.gms.internal.ads.zzcmn
    public final synchronized void zzai(zzcoc zzcocVar) {
        this.zzq = zzcocVar;
        requestLayout();
    }

    @Override // com.google.android.gms.internal.ads.zzcmn
    public final synchronized void zzaj(zzbcz zzbczVar) {
        this.zzE = zzbczVar;
    }

    @Override // com.google.android.gms.internal.ads.zzcmn
    public final synchronized void zzak(boolean z) {
        this.zzx = z;
    }

    @Override // com.google.android.gms.internal.ads.zzcmn
    public final void zzal() {
        setBackgroundColor(0);
    }

    @Override // com.google.android.gms.internal.ads.zzcmn
    public final void zzam(Context context) {
        this.zzb.setBaseContext(context);
        this.zzQ.zze(this.zzb.zza());
    }

    @Override // com.google.android.gms.internal.ads.zzcmn
    public final synchronized void zzan(boolean z) {
        com.google.android.gms.ads.internal.overlay.zzl zzlVar = this.zzo;
        if (zzlVar != null) {
            zzlVar.zzw(this.zzn.zzJ(), z);
        } else {
            this.zzs = z;
        }
    }

    @Override // com.google.android.gms.internal.ads.zzcmn
    public final synchronized void zzao(zzbll zzbllVar) {
        this.zzD = zzbllVar;
    }

    @Override // com.google.android.gms.internal.ads.zzcmn
    public final synchronized void zzap(boolean z) {
        boolean z2 = this.zzu;
        this.zzu = z;
        zzaU();
        if (z != z2) {
            if (!((Boolean) com.google.android.gms.ads.internal.client.zzay.zzc().zzb(zzbiy.zzO)).booleanValue() || !this.zzq.zzi()) {
                new zzbya(this, "").zzk(true != z ? com.facebook.hermes.intl.Constants.COLLATION_DEFAULT : "expanded");
            }
        }
    }

    @Override // com.google.android.gms.internal.ads.zzcmn
    public final synchronized void zzaq(zzbln zzblnVar) {
        this.zzC = zzblnVar;
    }

    @Override // com.google.android.gms.internal.ads.zzcmn
    public final synchronized void zzar(IObjectWrapper iObjectWrapper) {
        this.zzp = iObjectWrapper;
    }

    @Override // com.google.android.gms.internal.ads.zzcmn
    public final synchronized void zzas(int r2) {
        com.google.android.gms.ads.internal.overlay.zzl zzlVar = this.zzo;
        if (zzlVar != null) {
            zzlVar.zzy(r2);
        }
    }

    @Override // com.google.android.gms.internal.ads.zzcmn
    public final synchronized void zzat(com.google.android.gms.ads.internal.overlay.zzl zzlVar) {
        this.zzO = zzlVar;
    }

    @Override // com.google.android.gms.internal.ads.zzcmn
    public final synchronized void zzau(boolean z) {
        com.google.android.gms.ads.internal.overlay.zzl zzlVar;
        int r0 = this.zzF + (true != z ? -1 : 1);
        this.zzF = r0;
        if (r0 > 0 || (zzlVar = this.zzo) == null) {
            return;
        }
        zzlVar.zzC();
    }

    @Override // com.google.android.gms.internal.ads.zzcmn
    public final synchronized void zzav(boolean z) {
        if (z) {
            setBackgroundColor(0);
        }
        com.google.android.gms.ads.internal.overlay.zzl zzlVar = this.zzo;
        if (zzlVar != null) {
            zzlVar.zzz(z);
        }
    }

    @Override // com.google.android.gms.internal.ads.zzcmn
    public final void zzaw(String str, zzbpq zzbpqVar) {
        zzcmu zzcmuVar = this.zzn;
        if (zzcmuVar != null) {
            zzcmuVar.zzG(str, zzbpqVar);
        }
    }

    @Override // com.google.android.gms.internal.ads.zzcmn
    public final void zzax(String str, Predicate predicate) {
        zzcmu zzcmuVar = this.zzn;
        if (zzcmuVar != null) {
            zzcmuVar.zzH(str, predicate);
        }
    }

    @Override // com.google.android.gms.internal.ads.zzcmn
    public final synchronized boolean zzay() {
        return this.zzs;
    }

    @Override // com.google.android.gms.internal.ads.zzcmn
    public final synchronized boolean zzaz() {
        return this.zzF > 0;
    }

    @Override // com.google.android.gms.internal.ads.zzbsv
    public final void zzb(String str, String str2) {
        zzaQ(str + "(" + str2 + ");");
    }

    @Override // com.google.android.gms.ads.internal.zzl
    public final synchronized void zzbn() {
        com.google.android.gms.ads.internal.zzl zzlVar = this.zzf;
        if (zzlVar != null) {
            zzlVar.zzbn();
        }
    }

    @Override // com.google.android.gms.ads.internal.zzl
    public final synchronized void zzbo() {
        com.google.android.gms.ads.internal.zzl zzlVar = this.zzf;
        if (zzlVar != null) {
            zzlVar.zzbo();
        }
    }

    @Override // com.google.android.gms.internal.ads.zzciw
    public final zzcil zzbp() {
        return null;
    }

    @Override // com.google.android.gms.internal.ads.zzbbm
    public final void zzc(zzbbl zzbblVar) {
        synchronized (this) {
            this.zzA = zzbblVar.zzj;
        }
        zzaX(zzbblVar.zzj);
    }

    @Override // com.google.android.gms.internal.ads.zzbsi
    public final void zzd(String str, Map map) {
        try {
            zze(str, com.google.android.gms.ads.internal.client.zzaw.zzb().zzi(map));
        } catch (JSONException unused) {
            com.google.android.gms.ads.internal.util.zze.zzj("Could not convert parameters to JSON.");
        }
    }

    @Override // com.google.android.gms.internal.ads.zzbsi
    public final void zze(String str, JSONObject jSONObject) {
        if (jSONObject == null) {
            jSONObject = new JSONObject();
        }
        String jSONObject2 = jSONObject.toString();
        StringBuilder sb = new StringBuilder();
        sb.append("(window.AFMA_ReceiveMessage || function() {})('");
        sb.append(str);
        sb.append("',");
        sb.append(jSONObject2);
        sb.append(");");
        com.google.android.gms.ads.internal.util.zze.zze("Dispatching AFMA event: ".concat(sb.toString()));
        zzaQ(sb.toString());
    }

    @Override // com.google.android.gms.internal.ads.zzciw
    public final int zzf() {
        return this.zzN;
    }

    @Override // com.google.android.gms.internal.ads.zzciw
    public final int zzg() {
        return this.zzM;
    }

    @Override // com.google.android.gms.internal.ads.zzciw
    public final synchronized int zzh() {
        return this.zzL;
    }

    @Override // com.google.android.gms.internal.ads.zzciw
    public final int zzi() {
        return getMeasuredHeight();
    }

    @Override // com.google.android.gms.internal.ads.zzciw
    public final int zzj() {
        return getMeasuredWidth();
    }

    @Override // com.google.android.gms.internal.ads.zzcmn, com.google.android.gms.internal.ads.zzcno, com.google.android.gms.internal.ads.zzciw
    public final Activity zzk() {
        return this.zzb.zza();
    }

    @Override // com.google.android.gms.internal.ads.zzbsv
    public final void zzl(String str, JSONObject jSONObject) {
        zzb(str, jSONObject.toString());
    }

    @Override // com.google.android.gms.internal.ads.zzcmn, com.google.android.gms.internal.ads.zzciw
    public final com.google.android.gms.ads.internal.zza zzm() {
        return this.zzg;
    }

    @Override // com.google.android.gms.internal.ads.zzciw
    public final zzbjk zzn() {
        return this.zzI;
    }

    @Override // com.google.android.gms.internal.ads.zzcmn, com.google.android.gms.internal.ads.zzciw
    public final zzbjl zzo() {
        return this.zzK;
    }

    @Override // com.google.android.gms.internal.ads.zzcmn, com.google.android.gms.internal.ads.zzcnw, com.google.android.gms.internal.ads.zzciw
    public final zzcgt zzp() {
        return this.zze;
    }

    @Override // com.google.android.gms.internal.ads.zzdkl
    public final void zzq() {
        zzcmu zzcmuVar = this.zzn;
        if (zzcmuVar != null) {
            zzcmuVar.zzq();
        }
    }

    @Override // com.google.android.gms.internal.ads.zzciw
    public final synchronized zzckz zzr(String str) {
        Map map = this.zzV;
        if (map == null) {
            return null;
        }
        return (zzckz) map.get(str);
    }

    @Override // com.google.android.gms.internal.ads.zzcmn, com.google.android.gms.internal.ads.zzciw
    public final synchronized zzcnj zzs() {
        return this.zzz;
    }

    @Override // com.google.android.gms.internal.ads.zzciw
    public final synchronized String zzt() {
        zzfcv zzfcvVar = this.zzk;
        if (zzfcvVar != null) {
            return zzfcvVar.zzb;
        }
        return null;
    }

    @Override // com.google.android.gms.internal.ads.zzciw
    public final synchronized String zzu() {
        return this.zzy;
    }

    @Override // com.google.android.gms.internal.ads.zzcmn, com.google.android.gms.internal.ads.zzciw
    public final synchronized void zzv(String str, zzckz zzckzVar) {
        if (this.zzV == null) {
            this.zzV = new HashMap();
        }
        this.zzV.put(str, zzckzVar);
    }

    @Override // com.google.android.gms.internal.ads.zzciw
    public final void zzw() {
        com.google.android.gms.ads.internal.overlay.zzl zzN = zzN();
        if (zzN != null) {
            zzN.zzd();
        }
    }

    @Override // com.google.android.gms.internal.ads.zzciw
    public final void zzx(boolean z, long j) {
        HashMap hashMap = new HashMap(2);
        hashMap.put("success", true != z ? SessionDescription.SUPPORTED_SDP_VERSION : IcyHeaders.REQUEST_HEADER_ENABLE_METADATA_VALUE);
        hashMap.put("duration", Long.toString(j));
        zzd("onCacheAccessComplete", hashMap);
    }

    @Override // com.google.android.gms.internal.ads.zzciw
    public final synchronized void zzy() {
        zzbll zzbllVar = this.zzD;
        if (zzbllVar != null) {
            final zzdsl zzdslVar = (zzdsl) zzbllVar;
            com.google.android.gms.ads.internal.util.zzs.zza.post(new Runnable() { // from class: com.google.android.gms.internal.ads.zzdsj
                @Override // java.lang.Runnable
                public final void run() {
                    try {
                        zzdsl.this.zzd();
                    } catch (RemoteException e) {
                        com.google.android.gms.ads.internal.util.zze.zzl("#007 Could not call remote method.", e);
                    }
                }
            });
        }
    }

    @Override // com.google.android.gms.internal.ads.zzciw
    public final void zzz(int r1) {
        this.zzN = r1;
    }
}
