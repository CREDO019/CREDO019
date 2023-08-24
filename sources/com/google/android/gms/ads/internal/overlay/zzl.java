package com.google.android.gms.ads.internal.overlay;

import android.app.Activity;
import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.view.Window;
import android.webkit.WebChromeClient;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;
import androidx.core.view.InputDeviceCompat;
import androidx.core.view.ViewCompat;
import com.google.android.gms.ads.internal.client.zzay;
import com.google.android.gms.dynamic.IObjectWrapper;
import com.google.android.gms.dynamic.ObjectWrapper;
import com.google.android.gms.internal.ads.zzbel;
import com.google.android.gms.internal.ads.zzbiy;
import com.google.android.gms.internal.ads.zzbol;
import com.google.android.gms.internal.ads.zzbon;
import com.google.android.gms.internal.ads.zzbya;
import com.google.android.gms.internal.ads.zzbyz;
import com.google.android.gms.internal.ads.zzcgt;
import com.google.android.gms.internal.ads.zzcmn;
import com.google.android.gms.internal.ads.zzcmz;
import com.google.android.gms.internal.ads.zzcny;
import com.google.android.gms.internal.ads.zzcoa;
import com.google.android.gms.internal.ads.zzcoc;
import com.google.android.gms.internal.ads.zzegh;
import java.util.Collections;

/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
public class zzl extends zzbyz implements zzad {
    static final int zza = Color.argb(0, 0, 0, 0);
    protected final Activity zzb;
    AdOverlayInfoParcel zzc;
    zzcmn zzd;
    zzh zze;
    zzr zzf;
    FrameLayout zzh;
    WebChromeClient.CustomViewCallback zzi;
    zzg zzl;
    private Runnable zzp;
    private boolean zzq;
    private boolean zzr;
    boolean zzg = false;
    boolean zzj = false;
    boolean zzk = false;
    boolean zzm = false;
    int zzn = 1;
    private final Object zzo = new Object();
    private boolean zzs = false;
    private boolean zzt = false;
    private boolean zzu = true;

    public zzl(Activity activity) {
        this.zzb = activity;
    }

    private final void zzG(Configuration configuration) {
        com.google.android.gms.ads.internal.zzj zzjVar;
        com.google.android.gms.ads.internal.zzj zzjVar2;
        AdOverlayInfoParcel adOverlayInfoParcel = this.zzc;
        boolean z = true;
        boolean z2 = false;
        boolean z3 = (adOverlayInfoParcel == null || (zzjVar2 = adOverlayInfoParcel.zzo) == null || !zzjVar2.zzb) ? false : true;
        boolean zze = com.google.android.gms.ads.internal.zzt.zzr().zze(this.zzb, configuration);
        if ((!this.zzk || z3) && !zze) {
            AdOverlayInfoParcel adOverlayInfoParcel2 = this.zzc;
            if (adOverlayInfoParcel2 != null && (zzjVar = adOverlayInfoParcel2.zzo) != null && zzjVar.zzg) {
                z2 = true;
            }
        } else {
            z = false;
        }
        Window window = this.zzb.getWindow();
        if (((Boolean) zzay.zzc().zzb(zzbiy.zzaY)).booleanValue()) {
            window.getDecorView().setSystemUiVisibility(z ? z2 ? 5894 : 5380 : 256);
        } else if (z) {
            window.addFlags(1024);
            window.clearFlags(2048);
            if (z2) {
                window.getDecorView().setSystemUiVisibility(InputDeviceCompat.SOURCE_TOUCHSCREEN);
            }
        } else {
            window.addFlags(2048);
            window.clearFlags(1024);
        }
    }

    private static final void zzH(IObjectWrapper iObjectWrapper, View view) {
        if (iObjectWrapper == null || view == null) {
            return;
        }
        com.google.android.gms.ads.internal.zzt.zzh().zzc(iObjectWrapper, view);
    }

    public final void zzA(View view, WebChromeClient.CustomViewCallback customViewCallback) {
        FrameLayout frameLayout = new FrameLayout(this.zzb);
        this.zzh = frameLayout;
        frameLayout.setBackgroundColor(ViewCompat.MEASURED_STATE_MASK);
        this.zzh.addView(view, -1, -1);
        this.zzb.setContentView(this.zzh);
        this.zzr = true;
        this.zzi = customViewCallback;
        this.zzg = true;
    }

    protected final void zzB(boolean z) throws zzf {
        if (!this.zzr) {
            this.zzb.requestWindowFeature(1);
        }
        Window window = this.zzb.getWindow();
        if (window == null) {
            throw new zzf("Invalid activity, no window available.");
        }
        zzcmn zzcmnVar = this.zzc.zzd;
        zzcoa zzP = zzcmnVar != null ? zzcmnVar.zzP() : null;
        boolean z2 = zzP != null && zzP.zzJ();
        this.zzm = false;
        if (z2) {
            int r5 = this.zzc.zzj;
            if (r5 == 6) {
                r4 = this.zzb.getResources().getConfiguration().orientation == 1;
                this.zzm = r4;
            } else if (r5 == 7) {
                r4 = this.zzb.getResources().getConfiguration().orientation == 2;
                this.zzm = r4;
            }
        }
        com.google.android.gms.ads.internal.util.zze.zze("Delay onShow to next orientation change: " + r4);
        zzy(this.zzc.zzj);
        window.setFlags(16777216, 16777216);
        com.google.android.gms.ads.internal.util.zze.zze("Hardware acceleration on the AdActivity window enabled.");
        if (!this.zzk) {
            this.zzl.setBackgroundColor(ViewCompat.MEASURED_STATE_MASK);
        } else {
            this.zzl.setBackgroundColor(zza);
        }
        this.zzb.setContentView(this.zzl);
        this.zzr = true;
        if (z) {
            try {
                com.google.android.gms.ads.internal.zzt.zzA();
                Activity activity = this.zzb;
                zzcmn zzcmnVar2 = this.zzc.zzd;
                zzcoc zzQ = zzcmnVar2 != null ? zzcmnVar2.zzQ() : null;
                zzcmn zzcmnVar3 = this.zzc.zzd;
                String zzU = zzcmnVar3 != null ? zzcmnVar3.zzU() : null;
                AdOverlayInfoParcel adOverlayInfoParcel = this.zzc;
                zzcgt zzcgtVar = adOverlayInfoParcel.zzm;
                zzcmn zzcmnVar4 = adOverlayInfoParcel.zzd;
                zzcmn zza2 = zzcmz.zza(activity, zzQ, zzU, true, z2, null, null, zzcgtVar, null, null, zzcmnVar4 != null ? zzcmnVar4.zzm() : null, zzbel.zza(), null, null);
                this.zzd = zza2;
                zzcoa zzP2 = zza2.zzP();
                AdOverlayInfoParcel adOverlayInfoParcel2 = this.zzc;
                zzbol zzbolVar = adOverlayInfoParcel2.zzp;
                zzbon zzbonVar = adOverlayInfoParcel2.zze;
                zzz zzzVar = adOverlayInfoParcel2.zzi;
                zzcmn zzcmnVar5 = adOverlayInfoParcel2.zzd;
                zzP2.zzL(null, zzbolVar, null, zzbonVar, zzzVar, true, null, zzcmnVar5 != null ? zzcmnVar5.zzP().zzd() : null, null, null, null, null, null, null, null, null, null);
                this.zzd.zzP().zzz(new zzcny() { // from class: com.google.android.gms.ads.internal.overlay.zzd
                    @Override // com.google.android.gms.internal.ads.zzcny
                    public final void zza(boolean z3) {
                        zzcmn zzcmnVar6 = zzl.this.zzd;
                        if (zzcmnVar6 != null) {
                            zzcmnVar6.zzZ();
                        }
                    }
                });
                AdOverlayInfoParcel adOverlayInfoParcel3 = this.zzc;
                String str = adOverlayInfoParcel3.zzl;
                if (str != null) {
                    this.zzd.loadUrl(str);
                } else {
                    String str2 = adOverlayInfoParcel3.zzh;
                    if (str2 != null) {
                        this.zzd.loadDataWithBaseURL(adOverlayInfoParcel3.zzf, str2, "text/html", "UTF-8", null);
                    } else {
                        throw new zzf("No URL or HTML to display in ad overlay.");
                    }
                }
                zzcmn zzcmnVar6 = this.zzc.zzd;
                if (zzcmnVar6 != null) {
                    zzcmnVar6.zzat(this);
                }
            } catch (Exception e) {
                com.google.android.gms.ads.internal.util.zze.zzh("Error obtaining webview.", e);
                throw new zzf("Could not obtain webview for the overlay.", e);
            }
        } else {
            zzcmn zzcmnVar7 = this.zzc.zzd;
            this.zzd = zzcmnVar7;
            zzcmnVar7.zzam(this.zzb);
        }
        this.zzd.zzah(this);
        zzcmn zzcmnVar8 = this.zzc.zzd;
        if (zzcmnVar8 != null) {
            zzH(zzcmnVar8.zzS(), this.zzl);
        }
        if (this.zzc.zzk != 5) {
            ViewParent parent = this.zzd.getParent();
            if (parent != null && (parent instanceof ViewGroup)) {
                ((ViewGroup) parent).removeView(this.zzd.zzH());
            }
            if (this.zzk) {
                this.zzd.zzal();
            }
            this.zzl.addView(this.zzd.zzH(), -1, -1);
        }
        if (!z && !this.zzm) {
            zze();
        }
        AdOverlayInfoParcel adOverlayInfoParcel4 = this.zzc;
        if (adOverlayInfoParcel4.zzk != 5) {
            zzu(z2);
            if (this.zzd.zzay()) {
                zzw(z2, true);
                return;
            }
            return;
        }
        zzegh.zzh(this.zzb, this, adOverlayInfoParcel4.zzu, adOverlayInfoParcel4.zzr, adOverlayInfoParcel4.zzs, adOverlayInfoParcel4.zzt, adOverlayInfoParcel4.zzq, adOverlayInfoParcel4.zzv);
    }

    public final void zzC() {
        synchronized (this.zzo) {
            this.zzq = true;
            if (this.zzp != null) {
                com.google.android.gms.ads.internal.util.zzs.zza.removeCallbacks(this.zzp);
                com.google.android.gms.ads.internal.util.zzs.zza.post(this.zzp);
            }
        }
    }

    protected final void zzD() {
        AdOverlayInfoParcel adOverlayInfoParcel;
        zzo zzoVar;
        if (!this.zzb.isFinishing() || this.zzs) {
            return;
        }
        this.zzs = true;
        zzcmn zzcmnVar = this.zzd;
        if (zzcmnVar != null) {
            zzcmnVar.zzY(this.zzn - 1);
            synchronized (this.zzo) {
                if (!this.zzq && this.zzd.zzaz()) {
                    if (((Boolean) zzay.zzc().zzb(zzbiy.zzdS)).booleanValue() && !this.zzt && (adOverlayInfoParcel = this.zzc) != null && (zzoVar = adOverlayInfoParcel.zzc) != null) {
                        zzoVar.zzbC();
                    }
                    this.zzp = new Runnable() { // from class: com.google.android.gms.ads.internal.overlay.zze
                        @Override // java.lang.Runnable
                        public final void run() {
                            zzl.this.zzc();
                        }
                    };
                    com.google.android.gms.ads.internal.util.zzs.zza.postDelayed(this.zzp, ((Long) zzay.zzc().zzb(zzbiy.zzaR)).longValue());
                    return;
                }
            }
        }
        zzc();
    }

    @Override // com.google.android.gms.internal.ads.zzbza
    public final boolean zzE() {
        this.zzn = 1;
        if (this.zzd == null) {
            return true;
        }
        if (!((Boolean) zzay.zzc().zzb(zzbiy.zzhz)).booleanValue() || !this.zzd.canGoBack()) {
            boolean zzaE = this.zzd.zzaE();
            if (!zzaE) {
                this.zzd.zzd("onbackblocked", Collections.emptyMap());
            }
            return zzaE;
        }
        this.zzd.goBack();
        return false;
    }

    public final void zzb() {
        this.zzn = 3;
        this.zzb.finish();
        AdOverlayInfoParcel adOverlayInfoParcel = this.zzc;
        if (adOverlayInfoParcel == null || adOverlayInfoParcel.zzk != 5) {
            return;
        }
        this.zzb.overridePendingTransition(0, 0);
    }

    @Override // com.google.android.gms.ads.internal.overlay.zzad
    public final void zzbJ() {
        this.zzn = 2;
        this.zzb.finish();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final void zzc() {
        zzcmn zzcmnVar;
        zzo zzoVar;
        if (this.zzt) {
            return;
        }
        this.zzt = true;
        zzcmn zzcmnVar2 = this.zzd;
        if (zzcmnVar2 != null) {
            this.zzl.removeView(zzcmnVar2.zzH());
            zzh zzhVar = this.zze;
            if (zzhVar != null) {
                this.zzd.zzam(zzhVar.zzd);
                this.zzd.zzap(false);
                ViewGroup viewGroup = this.zze.zzc;
                View zzH = this.zzd.zzH();
                zzh zzhVar2 = this.zze;
                viewGroup.addView(zzH, zzhVar2.zza, zzhVar2.zzb);
                this.zze = null;
            } else if (this.zzb.getApplicationContext() != null) {
                this.zzd.zzam(this.zzb.getApplicationContext());
            }
            this.zzd = null;
        }
        AdOverlayInfoParcel adOverlayInfoParcel = this.zzc;
        if (adOverlayInfoParcel != null && (zzoVar = adOverlayInfoParcel.zzc) != null) {
            zzoVar.zzf(this.zzn);
        }
        AdOverlayInfoParcel adOverlayInfoParcel2 = this.zzc;
        if (adOverlayInfoParcel2 == null || (zzcmnVar = adOverlayInfoParcel2.zzd) == null) {
            return;
        }
        zzH(zzcmnVar.zzS(), this.zzc.zzd.zzH());
    }

    public final void zzd() {
        this.zzl.zzb = true;
    }

    protected final void zze() {
        this.zzd.zzZ();
    }

    public final void zzf() {
        AdOverlayInfoParcel adOverlayInfoParcel = this.zzc;
        if (adOverlayInfoParcel != null && this.zzg) {
            zzy(adOverlayInfoParcel.zzj);
        }
        if (this.zzh != null) {
            this.zzb.setContentView(this.zzl);
            this.zzr = true;
            this.zzh.removeAllViews();
            this.zzh = null;
        }
        WebChromeClient.CustomViewCallback customViewCallback = this.zzi;
        if (customViewCallback != null) {
            customViewCallback.onCustomViewHidden();
            this.zzi = null;
        }
        this.zzg = false;
    }

    @Override // com.google.android.gms.internal.ads.zzbza
    public final void zzg(int r1, int r2, Intent intent) {
    }

    @Override // com.google.android.gms.internal.ads.zzbza
    public final void zzh() {
        this.zzn = 1;
    }

    @Override // com.google.android.gms.internal.ads.zzbza
    public final void zzj(IObjectWrapper iObjectWrapper) {
        zzG((Configuration) ObjectWrapper.unwrap(iObjectWrapper));
    }

    /* JADX WARN: Removed duplicated region for block: B:32:0x0071 A[Catch: zzf -> 0x00f5, TryCatch #0 {zzf -> 0x00f5, blocks: (B:9:0x0017, B:11:0x0025, B:13:0x002e, B:14:0x0030, B:16:0x0038, B:17:0x0046, B:19:0x004d, B:25:0x005a, B:27:0x005e, B:29:0x0063, B:32:0x0071, B:34:0x0075, B:36:0x007b, B:37:0x007e, B:39:0x0084, B:40:0x0087, B:42:0x008d, B:44:0x0091, B:45:0x0094, B:47:0x009a, B:48:0x009d, B:55:0x00cc, B:57:0x00d0, B:58:0x00d7, B:59:0x00d8, B:61:0x00dc, B:63:0x00e9, B:22:0x0054, B:24:0x0058, B:30:0x006d, B:65:0x00ed, B:66:0x00f4), top: B:70:0x0017 }] */
    /* JADX WARN: Removed duplicated region for block: B:50:0x00c4  */
    /* JADX WARN: Removed duplicated region for block: B:63:0x00e9 A[Catch: zzf -> 0x00f5, TryCatch #0 {zzf -> 0x00f5, blocks: (B:9:0x0017, B:11:0x0025, B:13:0x002e, B:14:0x0030, B:16:0x0038, B:17:0x0046, B:19:0x004d, B:25:0x005a, B:27:0x005e, B:29:0x0063, B:32:0x0071, B:34:0x0075, B:36:0x007b, B:37:0x007e, B:39:0x0084, B:40:0x0087, B:42:0x008d, B:44:0x0091, B:45:0x0094, B:47:0x009a, B:48:0x009d, B:55:0x00cc, B:57:0x00d0, B:58:0x00d7, B:59:0x00d8, B:61:0x00dc, B:63:0x00e9, B:22:0x0054, B:24:0x0058, B:30:0x006d, B:65:0x00ed, B:66:0x00f4), top: B:70:0x0017 }] */
    @Override // com.google.android.gms.internal.ads.zzbza
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void zzk(android.os.Bundle r9) {
        /*
            Method dump skipped, instructions count: 261
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.ads.internal.overlay.zzl.zzk(android.os.Bundle):void");
    }

    @Override // com.google.android.gms.internal.ads.zzbza
    public final void zzl() {
        zzcmn zzcmnVar = this.zzd;
        if (zzcmnVar != null) {
            try {
                this.zzl.removeView(zzcmnVar.zzH());
            } catch (NullPointerException unused) {
            }
        }
        zzD();
    }

    public final void zzm() {
        if (this.zzm) {
            this.zzm = false;
            zze();
        }
    }

    @Override // com.google.android.gms.internal.ads.zzbza
    public final void zzn() {
        zzo zzoVar;
        zzf();
        AdOverlayInfoParcel adOverlayInfoParcel = this.zzc;
        if (adOverlayInfoParcel != null && (zzoVar = adOverlayInfoParcel.zzc) != null) {
            zzoVar.zzbr();
        }
        if (!((Boolean) zzay.zzc().zzb(zzbiy.zzdU)).booleanValue() && this.zzd != null && (!this.zzb.isFinishing() || this.zze == null)) {
            this.zzd.onPause();
        }
        zzD();
    }

    @Override // com.google.android.gms.internal.ads.zzbza
    public final void zzo() {
    }

    @Override // com.google.android.gms.internal.ads.zzbza
    public final void zzp() {
        zzo zzoVar;
        AdOverlayInfoParcel adOverlayInfoParcel = this.zzc;
        if (adOverlayInfoParcel != null && (zzoVar = adOverlayInfoParcel.zzc) != null) {
            zzoVar.zzbK();
        }
        zzG(this.zzb.getResources().getConfiguration());
        if (((Boolean) zzay.zzc().zzb(zzbiy.zzdU)).booleanValue()) {
            return;
        }
        zzcmn zzcmnVar = this.zzd;
        if (zzcmnVar == null || zzcmnVar.zzaB()) {
            com.google.android.gms.ads.internal.util.zze.zzj("The webview does not exist. Ignoring action.");
        } else {
            this.zzd.onResume();
        }
    }

    @Override // com.google.android.gms.internal.ads.zzbza
    public final void zzq(Bundle bundle) {
        bundle.putBoolean("com.google.android.gms.ads.internal.overlay.hasResumed", this.zzj);
    }

    @Override // com.google.android.gms.internal.ads.zzbza
    public final void zzr() {
        if (((Boolean) zzay.zzc().zzb(zzbiy.zzdU)).booleanValue()) {
            zzcmn zzcmnVar = this.zzd;
            if (zzcmnVar == null || zzcmnVar.zzaB()) {
                com.google.android.gms.ads.internal.util.zze.zzj("The webview does not exist. Ignoring action.");
            } else {
                this.zzd.onResume();
            }
        }
    }

    @Override // com.google.android.gms.internal.ads.zzbza
    public final void zzs() {
        if (((Boolean) zzay.zzc().zzb(zzbiy.zzdU)).booleanValue() && this.zzd != null && (!this.zzb.isFinishing() || this.zze == null)) {
            this.zzd.onPause();
        }
        zzD();
    }

    @Override // com.google.android.gms.internal.ads.zzbza
    public final void zzt() {
        zzo zzoVar;
        AdOverlayInfoParcel adOverlayInfoParcel = this.zzc;
        if (adOverlayInfoParcel == null || (zzoVar = adOverlayInfoParcel.zzc) == null) {
            return;
        }
        zzoVar.zze();
    }

    public final void zzu(boolean z) {
        int intValue = ((Integer) zzay.zzc().zzb(zzbiy.zzdW)).intValue();
        boolean z2 = ((Boolean) zzay.zzc().zzb(zzbiy.zzaU)).booleanValue() || z;
        zzq zzqVar = new zzq();
        zzqVar.zzd = 50;
        zzqVar.zza = true != z2 ? 0 : intValue;
        zzqVar.zzb = true != z2 ? intValue : 0;
        zzqVar.zzc = intValue;
        this.zzf = new zzr(this.zzb, zzqVar, this);
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(-2, -2);
        layoutParams.addRule(10);
        layoutParams.addRule(true != z2 ? 9 : 11);
        zzw(z, this.zzc.zzg);
        this.zzl.addView(this.zzf, layoutParams);
    }

    @Override // com.google.android.gms.internal.ads.zzbza
    public final void zzv() {
        this.zzr = true;
    }

    public final void zzw(boolean z, boolean z2) {
        AdOverlayInfoParcel adOverlayInfoParcel;
        com.google.android.gms.ads.internal.zzj zzjVar;
        AdOverlayInfoParcel adOverlayInfoParcel2;
        com.google.android.gms.ads.internal.zzj zzjVar2;
        boolean z3 = true;
        boolean z4 = ((Boolean) zzay.zzc().zzb(zzbiy.zzaS)).booleanValue() && (adOverlayInfoParcel2 = this.zzc) != null && (zzjVar2 = adOverlayInfoParcel2.zzo) != null && zzjVar2.zzh;
        boolean z5 = ((Boolean) zzay.zzc().zzb(zzbiy.zzaT)).booleanValue() && (adOverlayInfoParcel = this.zzc) != null && (zzjVar = adOverlayInfoParcel.zzo) != null && zzjVar.zzi;
        if (z && z2 && z4 && !z5) {
            new zzbya(this.zzd, "useCustomClose").zzg("Custom close has been disabled for interstitial ads in this ad slot.");
        }
        zzr zzrVar = this.zzf;
        if (zzrVar != null) {
            if (!z5 && (!z2 || z4)) {
                z3 = false;
            }
            zzrVar.zzb(z3);
        }
    }

    public final void zzx() {
        this.zzl.removeView(this.zzf);
        zzu(true);
    }

    public final void zzy(int r4) {
        if (this.zzb.getApplicationInfo().targetSdkVersion >= ((Integer) zzay.zzc().zzb(zzbiy.zzeY)).intValue()) {
            if (this.zzb.getApplicationInfo().targetSdkVersion <= ((Integer) zzay.zzc().zzb(zzbiy.zzeZ)).intValue()) {
                if (Build.VERSION.SDK_INT >= ((Integer) zzay.zzc().zzb(zzbiy.zzfa)).intValue()) {
                    if (Build.VERSION.SDK_INT <= ((Integer) zzay.zzc().zzb(zzbiy.zzfb)).intValue()) {
                        return;
                    }
                }
            }
        }
        try {
            this.zzb.setRequestedOrientation(r4);
        } catch (Throwable th) {
            com.google.android.gms.ads.internal.zzt.zzp().zzs(th, "AdOverlay.setRequestedOrientation");
        }
    }

    public final void zzz(boolean z) {
        if (z) {
            this.zzl.setBackgroundColor(0);
        } else {
            this.zzl.setBackgroundColor(ViewCompat.MEASURED_STATE_MASK);
        }
    }
}
