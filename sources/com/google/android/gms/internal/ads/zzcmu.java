package com.google.android.gms.internal.ads;

import android.content.Context;
import android.net.TrafficStats;
import android.net.Uri;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewTreeObserver;
import android.webkit.RenderProcessGoneDetail;
import android.webkit.WebResourceResponse;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import androidx.core.view.ViewCompat;
import com.google.android.exoplayer2.extractor.p011ts.TsExtractor;
import com.google.android.gms.ads.internal.overlay.AdOverlayInfoParcel;
import com.google.android.gms.common.util.Predicate;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CopyOnWriteArrayList;
import javax.annotation.ParametersAreNonnullByDefault;

/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
@ParametersAreNonnullByDefault
/* loaded from: classes2.dex */
public class zzcmu extends WebViewClient implements zzcoa {
    public static final /* synthetic */ int zzb = 0;
    private boolean zzA;
    private final HashSet zzB;
    private View.OnAttachStateChangeListener zzC;
    protected zzcdo zza;
    private final zzcmn zzc;
    private final zzbel zzd;
    private final HashMap zze;
    private final Object zzf;
    private com.google.android.gms.ads.internal.client.zza zzg;
    private com.google.android.gms.ads.internal.overlay.zzo zzh;
    private zzcny zzi;
    private zzcnz zzj;
    private zzbol zzk;
    private zzbon zzl;
    private zzdkl zzm;
    private boolean zzn;
    private boolean zzo;
    private boolean zzp;
    private boolean zzq;
    private boolean zzr;
    private com.google.android.gms.ads.internal.overlay.zzz zzs;
    private zzbxz zzt;
    private com.google.android.gms.ads.internal.zzb zzu;
    private zzbxu zzv;
    private zzfju zzw;
    private boolean zzx;
    private boolean zzy;
    private int zzz;

    public zzcmu(zzcmn zzcmnVar, zzbel zzbelVar, boolean z) {
        zzbxz zzbxzVar = new zzbxz(zzcmnVar, zzcmnVar.zzG(), new zzbii(zzcmnVar.getContext()));
        this.zze = new HashMap();
        this.zzf = new Object();
        this.zzd = zzbelVar;
        this.zzc = zzcmnVar;
        this.zzp = z;
        this.zzt = zzbxzVar;
        this.zzv = null;
        this.zzB = new HashSet(Arrays.asList(((String) com.google.android.gms.ads.internal.client.zzay.zzc().zzb(zzbiy.zzeG)).split(",")));
    }

    private static WebResourceResponse zzM() {
        if (((Boolean) com.google.android.gms.ads.internal.client.zzay.zzc().zzb(zzbiy.zzaD)).booleanValue()) {
            return new WebResourceResponse("", "", new ByteArrayInputStream(new byte[0]));
        }
        return null;
    }

    private final WebResourceResponse zzN(String str, Map map) throws IOException {
        HttpURLConnection httpURLConnection;
        URL url = new URL(str);
        try {
            TrafficStats.setThreadStatsTag(264);
            int r11 = 0;
            while (true) {
                r11++;
                if (r11 > 20) {
                    TrafficStats.clearThreadStatsTag();
                    throw new IOException("Too many redirects (20)");
                }
                URLConnection openConnection = url.openConnection();
                openConnection.setConnectTimeout(10000);
                openConnection.setReadTimeout(10000);
                for (Map.Entry entry : map.entrySet()) {
                    openConnection.addRequestProperty((String) entry.getKey(), (String) entry.getValue());
                }
                if (!(openConnection instanceof HttpURLConnection)) {
                    throw new IOException("Invalid protocol.");
                }
                httpURLConnection = (HttpURLConnection) openConnection;
                com.google.android.gms.ads.internal.zzt.zzq().zze(this.zzc.getContext(), this.zzc.zzp().zza, false, httpURLConnection, false, 60000);
                zzcgm zzcgmVar = new zzcgm(null);
                zzcgmVar.zzc(httpURLConnection, null);
                int responseCode = httpURLConnection.getResponseCode();
                zzcgmVar.zze(httpURLConnection, responseCode);
                if (responseCode < 300 || responseCode >= 400) {
                    break;
                }
                String headerField = httpURLConnection.getHeaderField("Location");
                if (headerField == null) {
                    throw new IOException("Missing Location header in redirect");
                }
                if (headerField.startsWith("tel:")) {
                    return null;
                }
                URL url2 = new URL(url, headerField);
                String protocol = url2.getProtocol();
                if (protocol == null) {
                    com.google.android.gms.ads.internal.util.zze.zzj("Protocol is null");
                    return zzM();
                } else if (!protocol.equals("http") && !protocol.equals("https")) {
                    com.google.android.gms.ads.internal.util.zze.zzj("Unsupported scheme: " + protocol);
                    return zzM();
                } else {
                    com.google.android.gms.ads.internal.util.zze.zze("Redirecting to " + headerField);
                    httpURLConnection.disconnect();
                    url = url2;
                }
            }
            com.google.android.gms.ads.internal.zzt.zzq();
            return com.google.android.gms.ads.internal.util.zzs.zzM(httpURLConnection);
        } finally {
            TrafficStats.clearThreadStatsTag();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void zzO(Map map, List list, String str) {
        if (com.google.android.gms.ads.internal.util.zze.zzc()) {
            com.google.android.gms.ads.internal.util.zze.zza("Received GMSG: ".concat(str));
            for (String str2 : map.keySet()) {
                com.google.android.gms.ads.internal.util.zze.zza("  " + str2 + ": " + ((String) map.get(str2)));
            }
        }
        Iterator it = list.iterator();
        while (it.hasNext()) {
            ((zzbpq) it.next()).zza(this.zzc, map);
        }
    }

    private final void zzP() {
        View.OnAttachStateChangeListener onAttachStateChangeListener = this.zzC;
        if (onAttachStateChangeListener == null) {
            return;
        }
        ((View) this.zzc).removeOnAttachStateChangeListener(onAttachStateChangeListener);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void zzQ(final View view, final zzcdo zzcdoVar, final int r5) {
        if (!zzcdoVar.zzi() || r5 <= 0) {
            return;
        }
        zzcdoVar.zzg(view);
        if (zzcdoVar.zzi()) {
            com.google.android.gms.ads.internal.util.zzs.zza.postDelayed(new Runnable() { // from class: com.google.android.gms.internal.ads.zzcmq
                @Override // java.lang.Runnable
                public final void run() {
                    zzcmu.this.zzn(view, zzcdoVar, r5);
                }
            }, 100L);
        }
    }

    private static final boolean zzR(boolean z, zzcmn zzcmnVar) {
        return (!z || zzcmnVar.zzQ().zzi() || zzcmnVar.zzU().equals("interstitial_mb")) ? false : true;
    }

    @Override // com.google.android.gms.ads.internal.client.zza
    public final void onAdClicked() {
        com.google.android.gms.ads.internal.client.zza zzaVar = this.zzg;
        if (zzaVar != null) {
            zzaVar.onAdClicked();
        }
    }

    @Override // android.webkit.WebViewClient
    public final void onLoadResource(WebView webView, String str) {
        com.google.android.gms.ads.internal.util.zze.zza("Loading resource: ".concat(String.valueOf(str)));
        Uri parse = Uri.parse(str);
        if ("gmsg".equalsIgnoreCase(parse.getScheme()) && "mobileads.google.com".equalsIgnoreCase(parse.getHost())) {
            zzi(parse);
        }
    }

    @Override // android.webkit.WebViewClient
    public final void onPageFinished(WebView webView, String str) {
        synchronized (this.zzf) {
            if (this.zzc.zzaB()) {
                com.google.android.gms.ads.internal.util.zze.zza("Blank page loaded, 1...");
                this.zzc.zzW();
                return;
            }
            this.zzx = true;
            zzcnz zzcnzVar = this.zzj;
            if (zzcnzVar != null) {
                zzcnzVar.zza();
                this.zzj = null;
            }
            zzg();
        }
    }

    @Override // android.webkit.WebViewClient
    public final void onReceivedError(WebView webView, int r2, String str, String str2) {
        this.zzo = true;
    }

    @Override // android.webkit.WebViewClient
    public final boolean onRenderProcessGone(WebView webView, RenderProcessGoneDetail renderProcessGoneDetail) {
        return this.zzc.zzaA(renderProcessGoneDetail.didCrash(), renderProcessGoneDetail.rendererPriorityAtExit());
    }

    @Override // android.webkit.WebViewClient
    public WebResourceResponse shouldInterceptRequest(WebView webView, String str) {
        return zzc(str, Collections.emptyMap());
    }

    @Override // android.webkit.WebViewClient
    public final boolean shouldOverrideKeyEvent(WebView webView, KeyEvent keyEvent) {
        int keyCode = keyEvent.getKeyCode();
        if (keyCode == 79 || keyCode == 222) {
            return true;
        }
        switch (keyCode) {
            case 85:
            case 86:
            case 87:
            case 88:
            case 89:
            case 90:
            case 91:
                return true;
            default:
                switch (keyCode) {
                    case 126:
                    case 127:
                    case 128:
                    case TsExtractor.TS_STREAM_TYPE_AC3 /* 129 */:
                    case TsExtractor.TS_STREAM_TYPE_HDMV_DTS /* 130 */:
                        return true;
                    default:
                        return false;
                }
        }
    }

    @Override // android.webkit.WebViewClient
    public final boolean shouldOverrideUrlLoading(WebView webView, String str) {
        com.google.android.gms.ads.internal.util.zze.zza("AdWebView shouldOverrideUrlLoading: ".concat(String.valueOf(str)));
        Uri parse = Uri.parse(str);
        if (!"gmsg".equalsIgnoreCase(parse.getScheme()) || !"mobileads.google.com".equalsIgnoreCase(parse.getHost())) {
            if (this.zzn && webView == this.zzc.zzI()) {
                String scheme = parse.getScheme();
                if ("http".equalsIgnoreCase(scheme) || "https".equalsIgnoreCase(scheme)) {
                    com.google.android.gms.ads.internal.client.zza zzaVar = this.zzg;
                    if (zzaVar != null) {
                        zzaVar.onAdClicked();
                        zzcdo zzcdoVar = this.zza;
                        if (zzcdoVar != null) {
                            zzcdoVar.zzh(str);
                        }
                        this.zzg = null;
                    }
                    zzdkl zzdklVar = this.zzm;
                    if (zzdklVar != null) {
                        zzdklVar.zzq();
                        this.zzm = null;
                    }
                    return super.shouldOverrideUrlLoading(webView, str);
                }
            }
            if (!this.zzc.zzI().willNotDraw()) {
                try {
                    zzapb zzK = this.zzc.zzK();
                    if (zzK != null && zzK.zzf(parse)) {
                        Context context = this.zzc.getContext();
                        zzcmn zzcmnVar = this.zzc;
                        parse = zzK.zza(parse, context, (View) zzcmnVar, zzcmnVar.zzk());
                    }
                } catch (zzapc unused) {
                    com.google.android.gms.ads.internal.util.zze.zzj("Unable to append parameter to URL: ".concat(String.valueOf(str)));
                }
                com.google.android.gms.ads.internal.zzb zzbVar = this.zzu;
                if (zzbVar == null || zzbVar.zzc()) {
                    zzr(new com.google.android.gms.ads.internal.overlay.zzc("android.intent.action.VIEW", parse.toString(), null, null, null, null, null, null), true);
                } else {
                    this.zzu.zzb(str);
                }
            } else {
                com.google.android.gms.ads.internal.util.zze.zzj("AdWebView unable to handle URL: ".concat(String.valueOf(str)));
            }
        } else {
            zzi(parse);
        }
        return true;
    }

    @Override // com.google.android.gms.internal.ads.zzcoa
    public final void zzA(int r2, int r3) {
        zzbxu zzbxuVar = this.zzv;
        if (zzbxuVar != null) {
            zzbxuVar.zzd(r2, r3);
        }
    }

    public final void zzB(boolean z) {
        this.zzn = false;
    }

    @Override // com.google.android.gms.internal.ads.zzcoa
    public final void zzC(boolean z) {
        synchronized (this.zzf) {
            this.zzr = z;
        }
    }

    @Override // com.google.android.gms.internal.ads.zzcoa
    public final void zzD() {
        synchronized (this.zzf) {
            this.zzn = false;
            this.zzp = true;
            zzcha.zze.execute(new Runnable() { // from class: com.google.android.gms.internal.ads.zzcmp
                @Override // java.lang.Runnable
                public final void run() {
                    zzcmu.this.zzm();
                }
            });
        }
    }

    @Override // com.google.android.gms.internal.ads.zzcoa
    public final void zzE(boolean z) {
        synchronized (this.zzf) {
            this.zzq = true;
        }
    }

    @Override // com.google.android.gms.internal.ads.zzcoa
    public final void zzF(zzcnz zzcnzVar) {
        this.zzj = zzcnzVar;
    }

    public final void zzG(String str, zzbpq zzbpqVar) {
        synchronized (this.zzf) {
            List list = (List) this.zze.get(str);
            if (list == null) {
                return;
            }
            list.remove(zzbpqVar);
        }
    }

    public final void zzH(String str, Predicate predicate) {
        synchronized (this.zzf) {
            List<zzbpq> list = (List) this.zze.get(str);
            if (list == null) {
                return;
            }
            ArrayList arrayList = new ArrayList();
            for (zzbpq zzbpqVar : list) {
                if (predicate.apply(zzbpqVar)) {
                    arrayList.add(zzbpqVar);
                }
            }
            list.removeAll(arrayList);
        }
    }

    public final boolean zzI() {
        boolean z;
        synchronized (this.zzf) {
            z = this.zzr;
        }
        return z;
    }

    @Override // com.google.android.gms.internal.ads.zzcoa
    public final boolean zzJ() {
        boolean z;
        synchronized (this.zzf) {
            z = this.zzp;
        }
        return z;
    }

    public final boolean zzK() {
        boolean z;
        synchronized (this.zzf) {
            z = this.zzq;
        }
        return z;
    }

    @Override // com.google.android.gms.internal.ads.zzcoa
    public final void zzL(com.google.android.gms.ads.internal.client.zza zzaVar, zzbol zzbolVar, com.google.android.gms.ads.internal.overlay.zzo zzoVar, zzbon zzbonVar, com.google.android.gms.ads.internal.overlay.zzz zzzVar, boolean z, zzbpt zzbptVar, com.google.android.gms.ads.internal.zzb zzbVar, zzbyb zzbybVar, zzcdo zzcdoVar, final zzefz zzefzVar, final zzfju zzfjuVar, zzdxo zzdxoVar, zzfhz zzfhzVar, zzbpr zzbprVar, final zzdkl zzdklVar, zzbqh zzbqhVar) {
        com.google.android.gms.ads.internal.zzb zzbVar2 = zzbVar == null ? new com.google.android.gms.ads.internal.zzb(this.zzc.getContext(), zzcdoVar, null) : zzbVar;
        this.zzv = new zzbxu(this.zzc, zzbybVar);
        this.zza = zzcdoVar;
        if (((Boolean) com.google.android.gms.ads.internal.client.zzay.zzc().zzb(zzbiy.zzaL)).booleanValue()) {
            zzx("/adMetadata", new zzbok(zzbolVar));
        }
        if (zzbonVar != null) {
            zzx("/appEvent", new zzbom(zzbonVar));
        }
        zzx("/backButton", zzbpp.zzj);
        zzx("/refresh", zzbpp.zzk);
        zzx("/canOpenApp", zzbpp.zzb);
        zzx("/canOpenURLs", zzbpp.zza);
        zzx("/canOpenIntents", zzbpp.zzc);
        zzx("/close", zzbpp.zzd);
        zzx("/customClose", zzbpp.zze);
        zzx("/instrument", zzbpp.zzn);
        zzx("/delayPageLoaded", zzbpp.zzp);
        zzx("/delayPageClosed", zzbpp.zzq);
        zzx("/getLocationInfo", zzbpp.zzr);
        zzx("/log", zzbpp.zzg);
        zzx("/mraid", new zzbpx(zzbVar2, this.zzv, zzbybVar));
        zzbxz zzbxzVar = this.zzt;
        if (zzbxzVar != null) {
            zzx("/mraidLoaded", zzbxzVar);
        }
        com.google.android.gms.ads.internal.zzb zzbVar3 = zzbVar2;
        zzx("/open", new zzbqb(zzbVar2, this.zzv, zzefzVar, zzdxoVar, zzfhzVar));
        zzx("/precache", new zzcla());
        zzx("/touch", zzbpp.zzi);
        zzx("/video", zzbpp.zzl);
        zzx("/videoMeta", zzbpp.zzm);
        if (zzefzVar == null || zzfjuVar == null) {
            zzx("/click", zzbpp.zza(zzdklVar));
            zzx("/httpTrack", zzbpp.zzf);
        } else {
            zzx("/click", new zzbpq() { // from class: com.google.android.gms.internal.ads.zzfdu
                @Override // com.google.android.gms.internal.ads.zzbpq
                public final void zza(Object obj, Map map) {
                    zzdkl zzdklVar2 = zzdkl.this;
                    zzfju zzfjuVar2 = zzfjuVar;
                    zzefz zzefzVar2 = zzefzVar;
                    zzcmn zzcmnVar = (zzcmn) obj;
                    zzbpp.zzd(map, zzdklVar2);
                    String str = (String) map.get("u");
                    if (str == null) {
                        com.google.android.gms.ads.internal.util.zze.zzj("URL missing from click GMSG.");
                    } else {
                        zzfyo.zzr(zzbpp.zzb(zzcmnVar, str), new zzfdv(zzcmnVar, zzfjuVar2, zzefzVar2), zzcha.zza);
                    }
                }
            });
            zzx("/httpTrack", new zzbpq() { // from class: com.google.android.gms.internal.ads.zzfdt
                @Override // com.google.android.gms.internal.ads.zzbpq
                public final void zza(Object obj, Map map) {
                    zzfju zzfjuVar2 = zzfju.this;
                    zzefz zzefzVar2 = zzefzVar;
                    zzcme zzcmeVar = (zzcme) obj;
                    String str = (String) map.get("u");
                    if (str == null) {
                        com.google.android.gms.ads.internal.util.zze.zzj("URL missing from httpTrack GMSG.");
                    } else if (!zzcmeVar.zzF().zzak) {
                        zzfjuVar2.zzc(str, null);
                    } else {
                        zzefzVar2.zzd(new zzegb(com.google.android.gms.ads.internal.zzt.zzB().currentTimeMillis(), ((zzcnk) zzcmeVar).zzR().zzb, str, 2));
                    }
                }
            });
        }
        if (com.google.android.gms.ads.internal.zzt.zzo().zzu(this.zzc.getContext())) {
            zzx("/logScionEvent", new zzbpw(this.zzc.getContext()));
        }
        if (zzbptVar != null) {
            zzx("/setInterstitialProperties", new zzbps(zzbptVar, null));
        }
        if (zzbprVar != null) {
            if (((Boolean) com.google.android.gms.ads.internal.client.zzay.zzc().zzb(zzbiy.zzhz)).booleanValue()) {
                zzx("/inspectorNetworkExtras", zzbprVar);
            }
        }
        if (((Boolean) com.google.android.gms.ads.internal.client.zzay.zzc().zzb(zzbiy.zzhS)).booleanValue() && zzbqhVar != null) {
            zzx("/shareSheet", zzbqhVar);
        }
        if (((Boolean) com.google.android.gms.ads.internal.client.zzay.zzc().zzb(zzbiy.zziN)).booleanValue()) {
            zzx("/bindPlayStoreOverlay", zzbpp.zzu);
            zzx("/presentPlayStoreOverlay", zzbpp.zzv);
            zzx("/expandPlayStoreOverlay", zzbpp.zzw);
            zzx("/collapsePlayStoreOverlay", zzbpp.zzx);
            zzx("/closePlayStoreOverlay", zzbpp.zzy);
        }
        this.zzg = zzaVar;
        this.zzh = zzoVar;
        this.zzk = zzbolVar;
        this.zzl = zzbonVar;
        this.zzs = zzzVar;
        this.zzu = zzbVar3;
        this.zzm = zzdklVar;
        this.zzn = z;
        this.zzw = zzfjuVar;
    }

    public final ViewTreeObserver.OnGlobalLayoutListener zza() {
        synchronized (this.zzf) {
        }
        return null;
    }

    public final ViewTreeObserver.OnScrollChangedListener zzb() {
        synchronized (this.zzf) {
        }
        return null;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final WebResourceResponse zzc(String str, Map map) {
        zzbdu zzb2;
        try {
            if (!((Boolean) zzbkp.zza.zze()).booleanValue() || this.zzw == null || !"oda".equals(Uri.parse(str).getScheme())) {
                String zzc = zzceu.zzc(str, this.zzc.getContext(), this.zzA);
                if (zzc.equals(str)) {
                    zzbdx zza = zzbdx.zza(Uri.parse(str));
                    if (zza == null || (zzb2 = com.google.android.gms.ads.internal.zzt.zzc().zzb(zza)) == null || !zzb2.zze()) {
                        if (zzcgm.zzl() && ((Boolean) zzbkk.zzb.zze()).booleanValue()) {
                            return zzN(str, map);
                        }
                        return null;
                    }
                    return new WebResourceResponse("", "", zzb2.zzc());
                }
                return zzN(zzc, map);
            }
            this.zzw.zzc(str, null);
            return new WebResourceResponse("", "", new ByteArrayInputStream(new byte[0]));
        } catch (Exception | NoClassDefFoundError e) {
            com.google.android.gms.ads.internal.zzt.zzp().zzt(e, "AdWebViewClient.interceptRequest");
            return zzM();
        }
    }

    @Override // com.google.android.gms.internal.ads.zzcoa
    public final com.google.android.gms.ads.internal.zzb zzd() {
        return this.zzu;
    }

    public final void zzg() {
        if (this.zzi != null && ((this.zzx && this.zzz <= 0) || this.zzy || this.zzo)) {
            if (((Boolean) com.google.android.gms.ads.internal.client.zzay.zzc().zzb(zzbiy.zzbB)).booleanValue() && this.zzc.zzo() != null) {
                zzbjf.zza(this.zzc.zzo().zza(), this.zzc.zzn(), "awfllc");
            }
            zzcny zzcnyVar = this.zzi;
            boolean z = false;
            if (!this.zzy && !this.zzo) {
                z = true;
            }
            zzcnyVar.zza(z);
            this.zzi = null;
        }
        this.zzc.zzae();
    }

    public final void zzh(boolean z) {
        this.zzA = z;
    }

    @Override // com.google.android.gms.internal.ads.zzcoa
    public final void zzi(Uri uri) {
        String path = uri.getPath();
        List list = (List) this.zze.get(path);
        if (path == null || list == null) {
            com.google.android.gms.ads.internal.util.zze.zza("No GMSG handler found for GMSG: ".concat(String.valueOf(String.valueOf(uri))));
            if (!((Boolean) com.google.android.gms.ads.internal.client.zzay.zzc().zzb(zzbiy.zzfM)).booleanValue() || com.google.android.gms.ads.internal.zzt.zzp().zzf() == null) {
                return;
            }
            final String substring = (path == null || path.length() < 2) ? "null" : path.substring(1);
            zzcha.zza.execute(new Runnable() { // from class: com.google.android.gms.internal.ads.zzcmo
                @Override // java.lang.Runnable
                public final void run() {
                    String str = substring;
                    int r1 = zzcmu.zzb;
                    com.google.android.gms.ads.internal.zzt.zzp().zzf().zze(str);
                }
            });
            return;
        }
        String encodedQuery = uri.getEncodedQuery();
        if (((Boolean) com.google.android.gms.ads.internal.client.zzay.zzc().zzb(zzbiy.zzeF)).booleanValue() && this.zzB.contains(path) && encodedQuery != null) {
            if (encodedQuery.length() >= ((Integer) com.google.android.gms.ads.internal.client.zzay.zzc().zzb(zzbiy.zzeH)).intValue()) {
                com.google.android.gms.ads.internal.util.zze.zza("Parsing gmsg query params on BG thread: ".concat(path));
                zzfyo.zzr(com.google.android.gms.ads.internal.zzt.zzq().zzb(uri), new zzcms(this, list, path, uri), zzcha.zze);
                return;
            }
        }
        com.google.android.gms.ads.internal.zzt.zzq();
        zzO(com.google.android.gms.ads.internal.util.zzs.zzL(uri), list, path);
    }

    @Override // com.google.android.gms.internal.ads.zzcoa
    public final void zzj() {
        zzbel zzbelVar = this.zzd;
        if (zzbelVar != null) {
            zzbelVar.zzc(10005);
        }
        this.zzy = true;
        zzg();
        this.zzc.destroy();
    }

    @Override // com.google.android.gms.internal.ads.zzcoa
    public final void zzk() {
        synchronized (this.zzf) {
        }
        this.zzz++;
        zzg();
    }

    @Override // com.google.android.gms.internal.ads.zzcoa
    public final void zzl() {
        this.zzz--;
        zzg();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final /* synthetic */ void zzm() {
        this.zzc.zzac();
        com.google.android.gms.ads.internal.overlay.zzl zzN = this.zzc.zzN();
        if (zzN != null) {
            zzN.zzx();
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final /* synthetic */ void zzn(View view, zzcdo zzcdoVar, int r3) {
        zzQ(view, zzcdoVar, r3 - 1);
    }

    @Override // com.google.android.gms.internal.ads.zzcoa
    public final void zzo(int r2, int r3, boolean z) {
        zzbxz zzbxzVar = this.zzt;
        if (zzbxzVar != null) {
            zzbxzVar.zzb(r2, r3);
        }
        zzbxu zzbxuVar = this.zzv;
        if (zzbxuVar != null) {
            zzbxuVar.zzc(r2, r3, false);
        }
    }

    @Override // com.google.android.gms.internal.ads.zzcoa
    public final void zzp() {
        zzcdo zzcdoVar = this.zza;
        if (zzcdoVar != null) {
            WebView zzI = this.zzc.zzI();
            if (!ViewCompat.isAttachedToWindow(zzI)) {
                zzP();
                zzcmr zzcmrVar = new zzcmr(this, zzcdoVar);
                this.zzC = zzcmrVar;
                ((View) this.zzc).addOnAttachStateChangeListener(zzcmrVar);
                return;
            }
            zzQ(zzI, zzcdoVar, 10);
        }
    }

    @Override // com.google.android.gms.internal.ads.zzdkl
    public final void zzq() {
        zzdkl zzdklVar = this.zzm;
        if (zzdklVar != null) {
            zzdklVar.zzq();
        }
    }

    public final void zzr(com.google.android.gms.ads.internal.overlay.zzc zzcVar, boolean z) {
        boolean zzaC = this.zzc.zzaC();
        boolean zzR = zzR(zzaC, this.zzc);
        boolean z2 = true;
        if (!zzR && z) {
            z2 = false;
        }
        zzu(new AdOverlayInfoParcel(zzcVar, zzR ? null : this.zzg, zzaC ? null : this.zzh, this.zzs, this.zzc.zzp(), this.zzc, z2 ? null : this.zzm));
    }

    public final void zzs(com.google.android.gms.ads.internal.util.zzbr zzbrVar, zzefz zzefzVar, zzdxo zzdxoVar, zzfhz zzfhzVar, String str, String str2, int r19) {
        zzcmn zzcmnVar = this.zzc;
        zzu(new AdOverlayInfoParcel(zzcmnVar, zzcmnVar.zzp(), zzbrVar, zzefzVar, zzdxoVar, zzfhzVar, str, str2, 14));
    }

    public final void zzt(boolean z, int r13, boolean z2) {
        boolean zzR = zzR(this.zzc.zzaC(), this.zzc);
        boolean z3 = true;
        if (!zzR && z2) {
            z3 = false;
        }
        com.google.android.gms.ads.internal.client.zza zzaVar = zzR ? null : this.zzg;
        com.google.android.gms.ads.internal.overlay.zzo zzoVar = this.zzh;
        com.google.android.gms.ads.internal.overlay.zzz zzzVar = this.zzs;
        zzcmn zzcmnVar = this.zzc;
        zzu(new AdOverlayInfoParcel(zzaVar, zzoVar, zzzVar, zzcmnVar, z, r13, zzcmnVar.zzp(), z3 ? null : this.zzm));
    }

    public final void zzu(AdOverlayInfoParcel adOverlayInfoParcel) {
        com.google.android.gms.ads.internal.overlay.zzc zzcVar;
        zzbxu zzbxuVar = this.zzv;
        boolean zze = zzbxuVar != null ? zzbxuVar.zze() : false;
        com.google.android.gms.ads.internal.zzt.zzj();
        com.google.android.gms.ads.internal.overlay.zzm.zza(this.zzc.getContext(), adOverlayInfoParcel, !zze);
        zzcdo zzcdoVar = this.zza;
        if (zzcdoVar != null) {
            String str = adOverlayInfoParcel.zzl;
            if (str == null && (zzcVar = adOverlayInfoParcel.zza) != null) {
                str = zzcVar.zzb;
            }
            zzcdoVar.zzh(str);
        }
    }

    public final void zzv(boolean z, int r18, String str, boolean z2) {
        boolean zzaC = this.zzc.zzaC();
        boolean zzR = zzR(zzaC, this.zzc);
        boolean z3 = true;
        if (!zzR && z2) {
            z3 = false;
        }
        com.google.android.gms.ads.internal.client.zza zzaVar = zzR ? null : this.zzg;
        zzcmt zzcmtVar = zzaC ? null : new zzcmt(this.zzc, this.zzh);
        zzbol zzbolVar = this.zzk;
        zzbon zzbonVar = this.zzl;
        com.google.android.gms.ads.internal.overlay.zzz zzzVar = this.zzs;
        zzcmn zzcmnVar = this.zzc;
        zzu(new AdOverlayInfoParcel(zzaVar, zzcmtVar, zzbolVar, zzbonVar, zzzVar, zzcmnVar, z, r18, str, zzcmnVar.zzp(), z3 ? null : this.zzm));
    }

    public final void zzw(boolean z, int r19, String str, String str2, boolean z2) {
        boolean zzaC = this.zzc.zzaC();
        boolean zzR = zzR(zzaC, this.zzc);
        boolean z3 = true;
        if (!zzR && z2) {
            z3 = false;
        }
        com.google.android.gms.ads.internal.client.zza zzaVar = zzR ? null : this.zzg;
        zzcmt zzcmtVar = zzaC ? null : new zzcmt(this.zzc, this.zzh);
        zzbol zzbolVar = this.zzk;
        zzbon zzbonVar = this.zzl;
        com.google.android.gms.ads.internal.overlay.zzz zzzVar = this.zzs;
        zzcmn zzcmnVar = this.zzc;
        zzu(new AdOverlayInfoParcel(zzaVar, zzcmtVar, zzbolVar, zzbonVar, zzzVar, zzcmnVar, z, r19, str, str2, zzcmnVar.zzp(), z3 ? null : this.zzm));
    }

    public final void zzx(String str, zzbpq zzbpqVar) {
        synchronized (this.zzf) {
            List list = (List) this.zze.get(str);
            if (list == null) {
                list = new CopyOnWriteArrayList();
                this.zze.put(str, list);
            }
            list.add(zzbpqVar);
        }
    }

    public final void zzy() {
        zzcdo zzcdoVar = this.zza;
        if (zzcdoVar != null) {
            zzcdoVar.zze();
            this.zza = null;
        }
        zzP();
        synchronized (this.zzf) {
            this.zze.clear();
            this.zzg = null;
            this.zzh = null;
            this.zzi = null;
            this.zzj = null;
            this.zzk = null;
            this.zzl = null;
            this.zzn = false;
            this.zzp = false;
            this.zzq = false;
            this.zzs = null;
            this.zzu = null;
            this.zzt = null;
            zzbxu zzbxuVar = this.zzv;
            if (zzbxuVar != null) {
                zzbxuVar.zza(true);
                this.zzv = null;
            }
            this.zzw = null;
        }
    }

    @Override // com.google.android.gms.internal.ads.zzcoa
    public final void zzz(zzcny zzcnyVar) {
        this.zzi = zzcnyVar;
    }
}
