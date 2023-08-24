package com.google.android.gms.ads.nonagon.signalgeneration;

import android.content.Context;
import android.graphics.Point;
import android.net.Uri;
import android.os.Build;
import android.os.RemoteException;
import android.text.TextUtils;
import android.view.MotionEvent;
import android.view.View;
import android.webkit.WebView;
import com.google.android.gms.ads.AdFormat;
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.ads.internal.client.zzay;
import com.google.android.gms.ads.internal.util.zzbx;
import com.google.android.gms.dynamic.IObjectWrapper;
import com.google.android.gms.dynamic.ObjectWrapper;
import com.google.android.gms.internal.ads.zzapb;
import com.google.android.gms.internal.ads.zzapc;
import com.google.android.gms.internal.ads.zzbiy;
import com.google.android.gms.internal.ads.zzbkh;
import com.google.android.gms.internal.ads.zzbzp;
import com.google.android.gms.internal.ads.zzbzy;
import com.google.android.gms.internal.ads.zzcfb;
import com.google.android.gms.internal.ads.zzcfd;
import com.google.android.gms.internal.ads.zzcfi;
import com.google.android.gms.internal.ads.zzcgn;
import com.google.android.gms.internal.ads.zzcgt;
import com.google.android.gms.internal.ads.zzcha;
import com.google.android.gms.internal.ads.zzcok;
import com.google.android.gms.internal.ads.zzdci;
import com.google.android.gms.internal.ads.zzdii;
import com.google.android.gms.internal.ads.zzdtl;
import com.google.android.gms.internal.ads.zzdxj;
import com.google.android.gms.internal.ads.zzdxt;
import com.google.android.gms.internal.ads.zzfdl;
import com.google.android.gms.internal.ads.zzfej;
import com.google.android.gms.internal.ads.zzfiq;
import com.google.android.gms.internal.ads.zzfir;
import com.google.android.gms.internal.ads.zzfjc;
import com.google.android.gms.internal.ads.zzfje;
import com.google.android.gms.internal.ads.zzfju;
import com.google.android.gms.internal.ads.zzfru;
import com.google.android.gms.internal.ads.zzfsu;
import com.google.android.gms.internal.ads.zzfxu;
import com.google.android.gms.internal.ads.zzfxv;
import com.google.android.gms.internal.ads.zzfyf;
import com.google.android.gms.internal.ads.zzfyo;
import com.google.android.gms.internal.ads.zzfyx;
import com.google.android.gms.internal.ads.zzfyy;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.WeakHashMap;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import org.json.JSONObject;

/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
public final class zzaa extends zzcfd {
    protected static final List zza = new ArrayList(Arrays.asList("/aclk", "/pcs/click", "/dbm/clk"));
    protected static final List zzb = new ArrayList(Arrays.asList(".doubleclick.net", ".googleadservices.com"));
    protected static final List zzc = new ArrayList(Arrays.asList("/pagead/adview", "/pcs/view", "/pagead/conversion", "/dbm/ad"));
    protected static final List zzd = new ArrayList(Arrays.asList(".doubleclick.net", ".googleadservices.com", ".googlesyndication.com"));
    public static final /* synthetic */ int zze = 0;
    private final zzcgt zzA;
    private String zzB;
    private final List zzD;
    private final List zzE;
    private final List zzF;
    private final List zzG;
    private final zzcok zzf;
    private Context zzg;
    private final zzapb zzh;
    private final zzfej zzi;
    private final zzfyy zzk;
    private final ScheduledExecutorService zzl;
    private zzbzy zzm;
    private final zzc zzq;
    private final zzdxt zzr;
    private final zzfju zzs;
    private zzdxj zzj = null;
    private Point zzn = new Point();
    private Point zzo = new Point();
    private final Set zzp = Collections.newSetFromMap(new WeakHashMap());
    private final AtomicInteger zzz = new AtomicInteger(0);
    private final boolean zzt = ((Boolean) zzay.zzc().zzb(zzbiy.zzgm)).booleanValue();
    private final boolean zzu = ((Boolean) zzay.zzc().zzb(zzbiy.zzgl)).booleanValue();
    private final boolean zzv = ((Boolean) zzay.zzc().zzb(zzbiy.zzgn)).booleanValue();
    private final boolean zzw = ((Boolean) zzay.zzc().zzb(zzbiy.zzgp)).booleanValue();
    private final String zzx = (String) zzay.zzc().zzb(zzbiy.zzgo);
    private final String zzy = (String) zzay.zzc().zzb(zzbiy.zzgq);
    private final String zzC = (String) zzay.zzc().zzb(zzbiy.zzgr);

    public zzaa(zzcok zzcokVar, Context context, zzapb zzapbVar, zzfej zzfejVar, zzfyy zzfyyVar, ScheduledExecutorService scheduledExecutorService, zzdxt zzdxtVar, zzfju zzfjuVar, zzcgt zzcgtVar) {
        List list;
        this.zzf = zzcokVar;
        this.zzg = context;
        this.zzh = zzapbVar;
        this.zzi = zzfejVar;
        this.zzk = zzfyyVar;
        this.zzl = scheduledExecutorService;
        this.zzq = zzcokVar.zzm();
        this.zzr = zzdxtVar;
        this.zzs = zzfjuVar;
        this.zzA = zzcgtVar;
        if (((Boolean) zzay.zzc().zzb(zzbiy.zzgs)).booleanValue()) {
            this.zzD = zzX((String) zzay.zzc().zzb(zzbiy.zzgt));
            this.zzE = zzX((String) zzay.zzc().zzb(zzbiy.zzgu));
            this.zzF = zzX((String) zzay.zzc().zzb(zzbiy.zzgv));
            list = zzX((String) zzay.zzc().zzb(zzbiy.zzgw));
        } else {
            this.zzD = zza;
            this.zzE = zzb;
            this.zzF = zzc;
            list = zzd;
        }
        this.zzG = list;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* bridge */ /* synthetic */ void zzF(zzaa zzaaVar, List list) {
        Iterator it = list.iterator();
        while (it.hasNext()) {
            if (zzaaVar.zzN((Uri) it.next())) {
                zzaaVar.zzz.getAndIncrement();
                return;
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* bridge */ /* synthetic */ void zzG(final zzaa zzaaVar, final String str, final String str2, final zzdxj zzdxjVar) {
        if (((Boolean) zzay.zzc().zzb(zzbiy.zzfX)).booleanValue()) {
            if (((Boolean) zzay.zzc().zzb(zzbiy.zzgd)).booleanValue()) {
                zzcha.zza.execute(new Runnable() { // from class: com.google.android.gms.ads.nonagon.signalgeneration.zzi
                    @Override // java.lang.Runnable
                    public final void run() {
                        zzaa.this.zzI(str, str2, zzdxjVar);
                    }
                });
            } else {
                zzaaVar.zzq.zzd(str, str2, zzdxjVar);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static final /* synthetic */ Uri zzP(Uri uri, String str) {
        return !TextUtils.isEmpty(str) ? zzW(uri, "nas", str) : uri;
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    private final zzh zzQ(Context context, String str, String str2, com.google.android.gms.ads.internal.client.zzq zzqVar, com.google.android.gms.ads.internal.client.zzl zzlVar) {
        char c;
        zzfdl zzfdlVar = new zzfdl();
        if (((Boolean) zzay.zzc().zzb(zzbiy.zzgy)).booleanValue()) {
            if ("REWARDED".equals(str2)) {
                zzfdlVar.zzo().zza(2);
            } else if ("REWARDED_INTERSTITIAL".equals(str2)) {
                zzfdlVar.zzo().zza(3);
            }
        }
        zzg zzn = this.zzf.zzn();
        zzdci zzdciVar = new zzdci();
        zzdciVar.zzc(context);
        if (str == null) {
            str = "adUnitId";
        }
        zzfdlVar.zzs(str);
        if (zzlVar == null) {
            zzlVar = new com.google.android.gms.ads.internal.client.zzm().zza();
        }
        zzfdlVar.zzE(zzlVar);
        if (zzqVar == null) {
            if (((Boolean) zzay.zzc().zzb(zzbiy.zzgy)).booleanValue()) {
                switch (str2.hashCode()) {
                    case -1999289321:
                        if (str2.equals("NATIVE")) {
                            c = 3;
                            break;
                        }
                        c = 65535;
                        break;
                    case 543046670:
                        if (str2.equals("REWARDED")) {
                            c = 1;
                            break;
                        }
                        c = 65535;
                        break;
                    case 1854800829:
                        if (str2.equals("REWARDED_INTERSTITIAL")) {
                            c = 2;
                            break;
                        }
                        c = 65535;
                        break;
                    case 1951953708:
                        if (str2.equals("BANNER")) {
                            c = 0;
                            break;
                        }
                        c = 65535;
                        break;
                    default:
                        c = 65535;
                        break;
                }
                if (c == 0) {
                    zzqVar = new com.google.android.gms.ads.internal.client.zzq(context, AdSize.BANNER);
                } else if (c == 1 || c == 2) {
                    zzqVar = com.google.android.gms.ads.internal.client.zzq.zzd();
                } else if (c == 3) {
                    zzqVar = com.google.android.gms.ads.internal.client.zzq.zzc();
                } else {
                    zzqVar = new com.google.android.gms.ads.internal.client.zzq();
                }
            } else {
                zzqVar = new com.google.android.gms.ads.internal.client.zzq();
            }
        }
        zzfdlVar.zzr(zzqVar);
        zzfdlVar.zzx(true);
        zzdciVar.zzf(zzfdlVar.zzG());
        zzn.zza(zzdciVar.zzg());
        zzac zzacVar = new zzac();
        zzacVar.zza(str2);
        zzn.zzb(new zzae(zzacVar, null));
        new zzdii();
        zzh zzc2 = zzn.zzc();
        this.zzj = zzc2.zza();
        return zzc2;
    }

    private final zzfyx zzR(final String str) {
        final zzdtl[] zzdtlVarArr = new zzdtl[1];
        zzfyx zzn = zzfyo.zzn(this.zzi.zza(), new zzfxv() { // from class: com.google.android.gms.ads.nonagon.signalgeneration.zzk
            @Override // com.google.android.gms.internal.ads.zzfxv
            public final zzfyx zza(Object obj) {
                return zzaa.this.zzv(zzdtlVarArr, str, (zzdtl) obj);
            }
        }, this.zzk);
        zzn.zzc(new Runnable() { // from class: com.google.android.gms.ads.nonagon.signalgeneration.zzl
            @Override // java.lang.Runnable
            public final void run() {
                zzaa.this.zzH(zzdtlVarArr);
            }
        }, this.zzk);
        return zzfyo.zzf(zzfyo.zzm((zzfyf) zzfyo.zzo(zzfyf.zzv(zzn), ((Integer) zzay.zzc().zzb(zzbiy.zzgC)).intValue(), TimeUnit.MILLISECONDS, this.zzl), new zzfru() { // from class: com.google.android.gms.ads.nonagon.signalgeneration.zzv
            @Override // com.google.android.gms.internal.ads.zzfru
            public final Object apply(Object obj) {
                int r0 = zzaa.zze;
                return ((JSONObject) obj).optString("nas");
            }
        }, this.zzk), Exception.class, new zzfru() { // from class: com.google.android.gms.ads.nonagon.signalgeneration.zzj
            @Override // com.google.android.gms.internal.ads.zzfru
            public final Object apply(Object obj) {
                int r0 = zzaa.zze;
                zzcgn.zzh("", (Exception) obj);
                return null;
            }
        }, this.zzk);
    }

    private final void zzS(List list, final IObjectWrapper iObjectWrapper, zzbzp zzbzpVar, boolean z) {
        zzfyx zzb2;
        if (!((Boolean) zzay.zzc().zzb(zzbiy.zzgB)).booleanValue()) {
            com.google.android.gms.ads.internal.util.zze.zzj("The updating URL feature is not enabled.");
            try {
                zzbzpVar.zze("The updating URL feature is not enabled.");
                return;
            } catch (RemoteException e) {
                zzcgn.zzh("", e);
                return;
            }
        }
        Iterator it = list.iterator();
        int r1 = 0;
        while (it.hasNext()) {
            if (zzN((Uri) it.next())) {
                r1++;
            }
        }
        if (r1 > 1) {
            com.google.android.gms.ads.internal.util.zze.zzj("Multiple google urls found: ".concat(String.valueOf(String.valueOf(list))));
        }
        ArrayList arrayList = new ArrayList();
        Iterator it2 = list.iterator();
        while (it2.hasNext()) {
            final Uri uri = (Uri) it2.next();
            if (!zzN(uri)) {
                com.google.android.gms.ads.internal.util.zze.zzj("Not a Google URL: ".concat(String.valueOf(String.valueOf(uri))));
                zzb2 = zzfyo.zzi(uri);
            } else {
                zzb2 = this.zzk.zzb(new Callable() { // from class: com.google.android.gms.ads.nonagon.signalgeneration.zzq
                    @Override // java.util.concurrent.Callable
                    public final Object call() {
                        return zzaa.this.zzm(uri, iObjectWrapper);
                    }
                });
                if (zzV()) {
                    zzb2 = zzfyo.zzn(zzb2, new zzfxv() { // from class: com.google.android.gms.ads.nonagon.signalgeneration.zzr
                        @Override // com.google.android.gms.internal.ads.zzfxv
                        public final zzfyx zza(Object obj) {
                            zzfyx zzm;
                            zzm = zzfyo.zzm(r0.zzR("google.afma.nativeAds.getPublisherCustomRenderedClickSignals"), new zzfru() { // from class: com.google.android.gms.ads.nonagon.signalgeneration.zzm
                                @Override // com.google.android.gms.internal.ads.zzfru
                                public final Object apply(Object obj2) {
                                    return zzaa.zzP(r2, (String) obj2);
                                }
                            }, zzaa.this.zzk);
                            return zzm;
                        }
                    }, this.zzk);
                } else {
                    com.google.android.gms.ads.internal.util.zze.zzi("Asset view map is empty.");
                }
            }
            arrayList.add(zzb2);
        }
        zzfyo.zzr(zzfyo.zze(arrayList), new zzy(this, zzbzpVar, z), this.zzf.zzA());
    }

    private final void zzT(final List list, final IObjectWrapper iObjectWrapper, zzbzp zzbzpVar, boolean z) {
        if (!((Boolean) zzay.zzc().zzb(zzbiy.zzgB)).booleanValue()) {
            try {
                zzbzpVar.zze("The updating URL feature is not enabled.");
                return;
            } catch (RemoteException e) {
                zzcgn.zzh("", e);
                return;
            }
        }
        zzfyx zzb2 = this.zzk.zzb(new Callable() { // from class: com.google.android.gms.ads.nonagon.signalgeneration.zzs
            @Override // java.util.concurrent.Callable
            public final Object call() {
                return zzaa.this.zzC(list, iObjectWrapper);
            }
        });
        if (zzV()) {
            zzb2 = zzfyo.zzn(zzb2, new zzfxv() { // from class: com.google.android.gms.ads.nonagon.signalgeneration.zzt
                @Override // com.google.android.gms.internal.ads.zzfxv
                public final zzfyx zza(Object obj) {
                    return zzaa.this.zzw((ArrayList) obj);
                }
            }, this.zzk);
        } else {
            com.google.android.gms.ads.internal.util.zze.zzi("Asset view map is empty.");
        }
        zzfyo.zzr(zzb2, new zzx(this, zzbzpVar, z), this.zzf.zzA());
    }

    private static boolean zzU(Uri uri, List list, List list2) {
        String host = uri.getHost();
        String path = uri.getPath();
        if (host != null && path != null) {
            Iterator it = list.iterator();
            while (it.hasNext()) {
                if (path.contains((String) it.next())) {
                    Iterator it2 = list2.iterator();
                    while (it2.hasNext()) {
                        if (host.endsWith((String) it2.next())) {
                            return true;
                        }
                    }
                    continue;
                }
            }
        }
        return false;
    }

    private final boolean zzV() {
        Map map;
        zzbzy zzbzyVar = this.zzm;
        return (zzbzyVar == null || (map = zzbzyVar.zzb) == null || map.isEmpty()) ? false : true;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Uri zzW(Uri uri, String str, String str2) {
        String uri2 = uri.toString();
        int indexOf = uri2.indexOf("&adurl=");
        if (indexOf == -1) {
            indexOf = uri2.indexOf("?adurl=");
        }
        if (indexOf != -1) {
            int r1 = indexOf + 1;
            return Uri.parse(uri2.substring(0, r1) + str + "=" + str2 + "&" + uri2.substring(r1));
        }
        return uri.buildUpon().appendQueryParameter(str, str2).build();
    }

    private static final List zzX(String str) {
        String[] split = TextUtils.split(str, ",");
        ArrayList arrayList = new ArrayList();
        for (String str2 : split) {
            if (!zzfsu.zzd(str2)) {
                arrayList.add(str2);
            }
        }
        return arrayList;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* bridge */ /* synthetic */ zzfjc zzr(zzfyx zzfyxVar, zzcfi zzcfiVar) {
        if (zzfje.zza() && ((Boolean) zzbkh.zze.zze()).booleanValue()) {
            try {
                zzfjc zzb2 = ((zzh) zzfyo.zzp(zzfyxVar)).zzb();
                zzb2.zzd(new ArrayList(Collections.singletonList(zzcfiVar.zzb)));
                com.google.android.gms.ads.internal.client.zzl zzlVar = zzcfiVar.zzd;
                zzb2.zzb(zzlVar == null ? "" : zzlVar.zzp);
                return zzb2;
            } catch (ExecutionException e) {
                com.google.android.gms.ads.internal.zzt.zzp().zzt(e, "SignalGeneratorImpl.getConfiguredCriticalUserJourney");
                return null;
            }
        }
        return null;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final /* synthetic */ ArrayList zzB(List list, String str) {
        ArrayList arrayList = new ArrayList();
        Iterator it = list.iterator();
        while (it.hasNext()) {
            Uri uri = (Uri) it.next();
            if (!zzO(uri) || TextUtils.isEmpty(str)) {
                arrayList.add(uri);
            } else {
                arrayList.add(zzW(uri, "nas", str));
            }
        }
        return arrayList;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final /* synthetic */ ArrayList zzC(List list, IObjectWrapper iObjectWrapper) throws Exception {
        String zzh = this.zzh.zzc() != null ? this.zzh.zzc().zzh(this.zzg, (View) ObjectWrapper.unwrap(iObjectWrapper), null) : "";
        if (TextUtils.isEmpty(zzh)) {
            throw new Exception("Failed to get view signals.");
        }
        ArrayList arrayList = new ArrayList();
        Iterator it = list.iterator();
        while (it.hasNext()) {
            Uri uri = (Uri) it.next();
            if (!zzO(uri)) {
                com.google.android.gms.ads.internal.util.zze.zzj("Not a Google URL: ".concat(String.valueOf(String.valueOf(uri))));
                arrayList.add(uri);
            } else {
                arrayList.add(zzW(uri, "ms", zzh));
            }
        }
        if (arrayList.isEmpty()) {
            throw new Exception("Empty impression URLs result.");
        }
        return arrayList;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final /* synthetic */ void zzH(zzdtl[] zzdtlVarArr) {
        zzdtl zzdtlVar = zzdtlVarArr[0];
        if (zzdtlVar != null) {
            this.zzi.zzb(zzfyo.zzi(zzdtlVar));
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final /* synthetic */ void zzI(String str, String str2, zzdxj zzdxjVar) {
        this.zzq.zzd(str, str2, zzdxjVar);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final boolean zzN(Uri uri) {
        return zzU(uri, this.zzD, this.zzE);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final boolean zzO(Uri uri) {
        return zzU(uri, this.zzF, this.zzG);
    }

    @Override // com.google.android.gms.internal.ads.zzcfe
    public final void zze(IObjectWrapper iObjectWrapper, final zzcfi zzcfiVar, zzcfb zzcfbVar) {
        zzfyx zzc2;
        zzfyx zzfyxVar;
        Context context = (Context) ObjectWrapper.unwrap(iObjectWrapper);
        this.zzg = context;
        zzfir zza2 = zzfiq.zza(context, 22);
        zza2.zzf();
        if (((Boolean) zzay.zzc().zzb(zzbiy.zziB)).booleanValue()) {
            zzfyx zzb2 = zzcha.zza.zzb(new Callable() { // from class: com.google.android.gms.ads.nonagon.signalgeneration.zzo
                @Override // java.util.concurrent.Callable
                public final Object call() {
                    return zzaa.this.zzq(zzcfiVar);
                }
            });
            zzfyxVar = zzb2;
            zzc2 = zzfyo.zzn(zzb2, new zzfxv() { // from class: com.google.android.gms.ads.nonagon.signalgeneration.zzp
                @Override // com.google.android.gms.internal.ads.zzfxv
                public final zzfyx zza(Object obj) {
                    return ((zzh) obj).zzc();
                }
            }, zzcha.zza);
        } else {
            zzh zzQ = zzQ(this.zzg, zzcfiVar.zza, zzcfiVar.zzb, zzcfiVar.zzc, zzcfiVar.zzd);
            zzfyx zzi = zzfyo.zzi(zzQ);
            zzc2 = zzQ.zzc();
            zzfyxVar = zzi;
        }
        zzfyo.zzr(zzc2, new zzw(this, zzfyxVar, zzcfiVar, zzcfbVar, zza2, com.google.android.gms.ads.internal.zzt.zzB().currentTimeMillis()), this.zzf.zzA());
    }

    @Override // com.google.android.gms.internal.ads.zzcfe
    public final void zzf(zzbzy zzbzyVar) {
        this.zzm = zzbzyVar;
        this.zzi.zzc(1);
    }

    @Override // com.google.android.gms.internal.ads.zzcfe
    public final void zzg(List list, IObjectWrapper iObjectWrapper, zzbzp zzbzpVar) {
        zzS(list, iObjectWrapper, zzbzpVar, true);
    }

    @Override // com.google.android.gms.internal.ads.zzcfe
    public final void zzh(List list, IObjectWrapper iObjectWrapper, zzbzp zzbzpVar) {
        zzT(list, iObjectWrapper, zzbzpVar, true);
    }

    @Override // com.google.android.gms.internal.ads.zzcfe
    public final void zzi(IObjectWrapper iObjectWrapper) {
        zzfyx zzc2;
        if (((Boolean) zzay.zzc().zzb(zzbiy.zzhV)).booleanValue()) {
            if (Build.VERSION.SDK_INT < 21) {
                com.google.android.gms.ads.internal.util.zze.zzj("Not registering the webview because the Android API level is lower than Lollopop which has security risks on webviews.");
                return;
            }
            if (((Boolean) zzay.zzc().zzb(zzbiy.zzhW)).booleanValue()) {
                if (((Boolean) zzay.zzc().zzb(zzbiy.zziB)).booleanValue()) {
                    zzc2 = zzfyo.zzl(new zzfxu() { // from class: com.google.android.gms.ads.nonagon.signalgeneration.zzu
                        @Override // com.google.android.gms.internal.ads.zzfxu
                        public final zzfyx zza() {
                            return zzaa.this.zzu();
                        }
                    }, zzcha.zza);
                } else {
                    zzc2 = zzQ(this.zzg, null, AdFormat.BANNER.name(), null, null).zzc();
                }
                zzfyo.zzr(zzc2, new zzz(this), this.zzf.zzA());
            }
            WebView webView = (WebView) ObjectWrapper.unwrap(iObjectWrapper);
            if (webView == null) {
                com.google.android.gms.ads.internal.util.zze.zzg("The webView cannot be null.");
            } else if (this.zzp.contains(webView)) {
                com.google.android.gms.ads.internal.util.zze.zzi("This webview has already been registered.");
            } else {
                this.zzp.add(webView);
                webView.addJavascriptInterface(new TaggingLibraryJsInterface(webView, this.zzh, this.zzr), "gmaSdk");
            }
        }
    }

    @Override // com.google.android.gms.internal.ads.zzcfe
    public final void zzj(IObjectWrapper iObjectWrapper) {
        if (((Boolean) zzay.zzc().zzb(zzbiy.zzgB)).booleanValue()) {
            MotionEvent motionEvent = (MotionEvent) ObjectWrapper.unwrap(iObjectWrapper);
            zzbzy zzbzyVar = this.zzm;
            this.zzn = zzbx.zza(motionEvent, zzbzyVar == null ? null : zzbzyVar.zza);
            if (motionEvent.getAction() == 0) {
                this.zzo = this.zzn;
            }
            MotionEvent obtain = MotionEvent.obtain(motionEvent);
            obtain.setLocation(this.zzn.x, this.zzn.y);
            this.zzh.zzd(obtain);
            obtain.recycle();
        }
    }

    @Override // com.google.android.gms.internal.ads.zzcfe
    public final void zzk(List list, IObjectWrapper iObjectWrapper, zzbzp zzbzpVar) {
        zzS(list, iObjectWrapper, zzbzpVar, false);
    }

    @Override // com.google.android.gms.internal.ads.zzcfe
    public final void zzl(List list, IObjectWrapper iObjectWrapper, zzbzp zzbzpVar) {
        zzT(list, iObjectWrapper, zzbzpVar, false);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final /* synthetic */ Uri zzm(Uri uri, IObjectWrapper iObjectWrapper) throws Exception {
        try {
            uri = this.zzh.zza(uri, this.zzg, (View) ObjectWrapper.unwrap(iObjectWrapper), null);
        } catch (zzapc e) {
            zzcgn.zzk("", e);
        }
        if (uri.getQueryParameter("ms") != null) {
            return uri;
        }
        throw new Exception("Failed to append spam signals to click url.");
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final /* synthetic */ zzh zzq(zzcfi zzcfiVar) throws Exception {
        return zzQ(this.zzg, zzcfiVar.zza, zzcfiVar.zzb, zzcfiVar.zzc, zzcfiVar.zzd);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final /* synthetic */ zzfyx zzu() throws Exception {
        return zzQ(this.zzg, null, AdFormat.BANNER.name(), null, null).zzc();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final /* synthetic */ zzfyx zzv(zzdtl[] zzdtlVarArr, String str, zzdtl zzdtlVar) throws Exception {
        zzdtlVarArr[0] = zzdtlVar;
        Context context = this.zzg;
        zzbzy zzbzyVar = this.zzm;
        Map map = zzbzyVar.zzb;
        JSONObject zzd2 = zzbx.zzd(context, map, map, zzbzyVar.zza);
        JSONObject zzg = zzbx.zzg(this.zzg, this.zzm.zza);
        JSONObject zzf = zzbx.zzf(this.zzm.zza);
        JSONObject zze2 = zzbx.zze(this.zzg, this.zzm.zza);
        JSONObject jSONObject = new JSONObject();
        jSONObject.put("asset_view_signal", zzd2);
        jSONObject.put("ad_view_signal", zzg);
        jSONObject.put("scroll_view_signal", zzf);
        jSONObject.put("lock_screen_signal", zze2);
        if (str == "google.afma.nativeAds.getPublisherCustomRenderedClickSignals") {
            jSONObject.put("click_signal", zzbx.zzc(null, this.zzg, this.zzo, this.zzn));
        }
        return zzdtlVar.zzd(str, jSONObject);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final /* synthetic */ zzfyx zzw(final ArrayList arrayList) throws Exception {
        return zzfyo.zzm(zzR("google.afma.nativeAds.getPublisherCustomRenderedImpressionSignals"), new zzfru() { // from class: com.google.android.gms.ads.nonagon.signalgeneration.zzn
            @Override // com.google.android.gms.internal.ads.zzfru
            public final Object apply(Object obj) {
                return zzaa.this.zzB(arrayList, (String) obj);
            }
        }, this.zzk);
    }
}
