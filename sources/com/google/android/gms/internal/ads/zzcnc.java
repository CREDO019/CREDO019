package com.google.android.gms.internal.ads;

import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.GradientDrawable;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.FrameLayout;
import android.widget.TextView;
import com.google.android.gms.common.util.Predicate;
import com.google.android.gms.dynamic.IObjectWrapper;
import com.google.android.gms.dynamic.ObjectWrapper;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicBoolean;
import org.json.JSONObject;

/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
public final class zzcnc extends FrameLayout implements zzcmn {
    private final zzcmn zza;
    private final zzcil zzb;
    private final AtomicBoolean zzc;

    public zzcnc(zzcmn zzcmnVar) {
        super(zzcmnVar.getContext());
        this.zzc = new AtomicBoolean();
        this.zza = zzcmnVar;
        this.zzb = new zzcil(zzcmnVar.zzG(), this, this);
        addView((View) zzcmnVar);
    }

    @Override // com.google.android.gms.internal.ads.zzcmn
    public final boolean canGoBack() {
        return this.zza.canGoBack();
    }

    @Override // com.google.android.gms.internal.ads.zzcmn
    public final void destroy() {
        final IObjectWrapper zzS = zzS();
        if (zzS != null) {
            com.google.android.gms.ads.internal.util.zzs.zza.post(new Runnable() { // from class: com.google.android.gms.internal.ads.zzcna
                @Override // java.lang.Runnable
                public final void run() {
                    IObjectWrapper iObjectWrapper = IObjectWrapper.this;
                    com.google.android.gms.ads.internal.zzt.zzh();
                    if (((Boolean) com.google.android.gms.ads.internal.client.zzay.zzc().zzb(zzbiy.zzed)).booleanValue() && zzfjx.zzb()) {
                        Object unwrap = ObjectWrapper.unwrap(iObjectWrapper);
                        if (unwrap instanceof zzfjz) {
                            ((zzfjz) unwrap).zzc();
                        }
                    }
                }
            });
            zzfph zzfphVar = com.google.android.gms.ads.internal.util.zzs.zza;
            final zzcmn zzcmnVar = this.zza;
            zzcmnVar.getClass();
            zzfphVar.postDelayed(new Runnable() { // from class: com.google.android.gms.internal.ads.zzcnb
                @Override // java.lang.Runnable
                public final void run() {
                    zzcmn.this.destroy();
                }
            }, ((Integer) com.google.android.gms.ads.internal.client.zzay.zzc().zzb(zzbiy.zzee)).intValue());
            return;
        }
        this.zza.destroy();
    }

    @Override // com.google.android.gms.internal.ads.zzcmn
    public final void goBack() {
        this.zza.goBack();
    }

    @Override // com.google.android.gms.internal.ads.zzcmn
    public final void loadData(String str, String str2, String str3) {
        this.zza.loadData(str, "text/html", str3);
    }

    @Override // com.google.android.gms.internal.ads.zzcmn
    public final void loadDataWithBaseURL(String str, String str2, String str3, String str4, String str5) {
        this.zza.loadDataWithBaseURL(str, str2, "text/html", "UTF-8", null);
    }

    @Override // com.google.android.gms.internal.ads.zzcmn
    public final void loadUrl(String str) {
        this.zza.loadUrl(str);
    }

    @Override // com.google.android.gms.ads.internal.client.zza
    public final void onAdClicked() {
        zzcmn zzcmnVar = this.zza;
        if (zzcmnVar != null) {
            zzcmnVar.onAdClicked();
        }
    }

    @Override // com.google.android.gms.internal.ads.zzcmn
    public final void onPause() {
        this.zzb.zze();
        this.zza.onPause();
    }

    @Override // com.google.android.gms.internal.ads.zzcmn
    public final void onResume() {
        this.zza.onResume();
    }

    @Override // android.view.View, com.google.android.gms.internal.ads.zzcmn
    public final void setOnClickListener(View.OnClickListener onClickListener) {
        this.zza.setOnClickListener(onClickListener);
    }

    @Override // android.view.View, com.google.android.gms.internal.ads.zzcmn
    public final void setOnTouchListener(View.OnTouchListener onTouchListener) {
        this.zza.setOnTouchListener(onTouchListener);
    }

    @Override // com.google.android.gms.internal.ads.zzcmn
    public final void setWebChromeClient(WebChromeClient webChromeClient) {
        this.zza.setWebChromeClient(webChromeClient);
    }

    @Override // com.google.android.gms.internal.ads.zzcmn
    public final void setWebViewClient(WebViewClient webViewClient) {
        this.zza.setWebViewClient(webViewClient);
    }

    @Override // com.google.android.gms.internal.ads.zzciw
    public final void zzA(int r2) {
        this.zza.zzA(r2);
    }

    @Override // com.google.android.gms.internal.ads.zzciw
    public final void zzB(boolean z) {
        this.zza.zzB(false);
    }

    @Override // com.google.android.gms.internal.ads.zzciw
    public final void zzC(int r2) {
        this.zza.zzC(r2);
    }

    @Override // com.google.android.gms.internal.ads.zzciw
    public final void zzD(int r2) {
        this.zzb.zzf(r2);
    }

    @Override // com.google.android.gms.internal.ads.zzcmn, com.google.android.gms.internal.ads.zzciw
    public final void zzE(zzcnj zzcnjVar) {
        this.zza.zzE(zzcnjVar);
    }

    @Override // com.google.android.gms.internal.ads.zzcmn, com.google.android.gms.internal.ads.zzcme
    public final zzfcs zzF() {
        return this.zza.zzF();
    }

    @Override // com.google.android.gms.internal.ads.zzcmn
    public final Context zzG() {
        return this.zza.zzG();
    }

    @Override // com.google.android.gms.internal.ads.zzcmn, com.google.android.gms.internal.ads.zzcnx
    public final View zzH() {
        return this;
    }

    @Override // com.google.android.gms.internal.ads.zzcmn
    public final WebView zzI() {
        return (WebView) this.zza;
    }

    @Override // com.google.android.gms.internal.ads.zzcmn
    public final WebViewClient zzJ() {
        return this.zza.zzJ();
    }

    @Override // com.google.android.gms.internal.ads.zzcmn, com.google.android.gms.internal.ads.zzcnv
    public final zzapb zzK() {
        return this.zza.zzK();
    }

    @Override // com.google.android.gms.internal.ads.zzcmn
    public final zzbcz zzL() {
        return this.zza.zzL();
    }

    @Override // com.google.android.gms.internal.ads.zzcmn
    public final zzbln zzM() {
        return this.zza.zzM();
    }

    @Override // com.google.android.gms.internal.ads.zzcmn
    public final com.google.android.gms.ads.internal.overlay.zzl zzN() {
        return this.zza.zzN();
    }

    @Override // com.google.android.gms.internal.ads.zzcmn
    public final com.google.android.gms.ads.internal.overlay.zzl zzO() {
        return this.zza.zzO();
    }

    @Override // com.google.android.gms.internal.ads.zzcmn
    public final zzcoa zzP() {
        return ((zzcng) this.zza).zzaL();
    }

    @Override // com.google.android.gms.internal.ads.zzcmn, com.google.android.gms.internal.ads.zzcnu
    public final zzcoc zzQ() {
        return this.zza.zzQ();
    }

    @Override // com.google.android.gms.internal.ads.zzcmn, com.google.android.gms.internal.ads.zzcnk
    public final zzfcv zzR() {
        return this.zza.zzR();
    }

    @Override // com.google.android.gms.internal.ads.zzcmn
    public final IObjectWrapper zzS() {
        return this.zza.zzS();
    }

    @Override // com.google.android.gms.internal.ads.zzcmn
    public final zzfyx zzT() {
        return this.zza.zzT();
    }

    @Override // com.google.android.gms.internal.ads.zzcmn
    public final String zzU() {
        return this.zza.zzU();
    }

    @Override // com.google.android.gms.internal.ads.zzcmn
    public final void zzV(zzfcs zzfcsVar, zzfcv zzfcvVar) {
        this.zza.zzV(zzfcsVar, zzfcvVar);
    }

    @Override // com.google.android.gms.internal.ads.zzcmn
    public final void zzW() {
        this.zzb.zzd();
        this.zza.zzW();
    }

    @Override // com.google.android.gms.internal.ads.zzcmn
    public final void zzX() {
        this.zza.zzX();
    }

    @Override // com.google.android.gms.internal.ads.zzcmn
    public final void zzY(int r2) {
        this.zza.zzY(r2);
    }

    @Override // com.google.android.gms.internal.ads.zzcmn
    public final void zzZ() {
        this.zza.zzZ();
    }

    @Override // com.google.android.gms.internal.ads.zzbsv
    public final void zza(String str) {
        ((zzcng) this.zza).zzaQ(str);
    }

    @Override // com.google.android.gms.internal.ads.zzcmn
    public final boolean zzaA(boolean z, int r6) {
        if (this.zzc.compareAndSet(false, true)) {
            if (((Boolean) com.google.android.gms.ads.internal.client.zzay.zzc().zzb(zzbiy.zzaF)).booleanValue()) {
                return false;
            }
            if (this.zza.getParent() instanceof ViewGroup) {
                ((ViewGroup) this.zza.getParent()).removeView((View) this.zza);
            }
            this.zza.zzaA(z, r6);
            return true;
        }
        return true;
    }

    @Override // com.google.android.gms.internal.ads.zzcmn
    public final boolean zzaB() {
        return this.zza.zzaB();
    }

    @Override // com.google.android.gms.internal.ads.zzcmn
    public final boolean zzaC() {
        return this.zza.zzaC();
    }

    @Override // com.google.android.gms.internal.ads.zzcmn
    public final boolean zzaD() {
        return this.zzc.get();
    }

    @Override // com.google.android.gms.internal.ads.zzcmn
    public final boolean zzaE() {
        return this.zza.zzaE();
    }

    @Override // com.google.android.gms.internal.ads.zzcns
    public final void zzaF(com.google.android.gms.ads.internal.overlay.zzc zzcVar, boolean z) {
        this.zza.zzaF(zzcVar, z);
    }

    @Override // com.google.android.gms.internal.ads.zzcns
    public final void zzaG(com.google.android.gms.ads.internal.util.zzbr zzbrVar, zzefz zzefzVar, zzdxo zzdxoVar, zzfhz zzfhzVar, String str, String str2, int r15) {
        this.zza.zzaG(zzbrVar, zzefzVar, zzdxoVar, zzfhzVar, str, str2, 14);
    }

    @Override // com.google.android.gms.internal.ads.zzcns
    public final void zzaH(boolean z, int r3, boolean z2) {
        this.zza.zzaH(z, r3, z2);
    }

    @Override // com.google.android.gms.internal.ads.zzcns
    public final void zzaI(boolean z, int r3, String str, boolean z2) {
        this.zza.zzaI(z, r3, str, z2);
    }

    @Override // com.google.android.gms.internal.ads.zzcns
    public final void zzaJ(boolean z, int r8, String str, String str2, boolean z2) {
        this.zza.zzaJ(z, r8, str, str2, z2);
    }

    @Override // com.google.android.gms.internal.ads.zzcmn
    public final void zzaa() {
        zzcmn zzcmnVar = this.zza;
        HashMap hashMap = new HashMap(3);
        hashMap.put("app_muted", String.valueOf(com.google.android.gms.ads.internal.zzt.zzs().zze()));
        hashMap.put("app_volume", String.valueOf(com.google.android.gms.ads.internal.zzt.zzs().zza()));
        zzcng zzcngVar = (zzcng) zzcmnVar;
        hashMap.put("device_volume", String.valueOf(com.google.android.gms.ads.internal.util.zzab.zzb(zzcngVar.getContext())));
        zzcngVar.zzd("volume", hashMap);
    }

    @Override // com.google.android.gms.internal.ads.zzcmn
    public final void zzab(boolean z) {
        this.zza.zzab(z);
    }

    @Override // com.google.android.gms.internal.ads.zzcmn
    public final void zzac() {
        this.zza.zzac();
    }

    @Override // com.google.android.gms.internal.ads.zzcmn
    public final void zzad(String str, String str2, String str3) {
        this.zza.zzad(str, str2, null);
    }

    @Override // com.google.android.gms.internal.ads.zzcmn
    public final void zzae() {
        this.zza.zzae();
    }

    @Override // com.google.android.gms.internal.ads.zzcmn
    public final void zzaf(String str, zzbpq zzbpqVar) {
        this.zza.zzaf(str, zzbpqVar);
    }

    @Override // com.google.android.gms.internal.ads.zzcmn
    public final void zzag() {
        TextView textView = new TextView(getContext());
        com.google.android.gms.ads.internal.zzt.zzq();
        textView.setText(com.google.android.gms.ads.internal.util.zzs.zzv());
        textView.setTextSize(15.0f);
        textView.setTextColor(-1);
        textView.setPadding(5, 0, 5, 0);
        GradientDrawable gradientDrawable = new GradientDrawable();
        gradientDrawable.setShape(0);
        gradientDrawable.setColor(-12303292);
        gradientDrawable.setCornerRadius(8.0f);
        textView.setBackground(gradientDrawable);
        addView(textView, new FrameLayout.LayoutParams(-2, -2, 49));
        bringChildToFront(textView);
    }

    @Override // com.google.android.gms.internal.ads.zzcmn
    public final void zzah(com.google.android.gms.ads.internal.overlay.zzl zzlVar) {
        this.zza.zzah(zzlVar);
    }

    @Override // com.google.android.gms.internal.ads.zzcmn
    public final void zzai(zzcoc zzcocVar) {
        this.zza.zzai(zzcocVar);
    }

    @Override // com.google.android.gms.internal.ads.zzcmn
    public final void zzaj(zzbcz zzbczVar) {
        this.zza.zzaj(zzbczVar);
    }

    @Override // com.google.android.gms.internal.ads.zzcmn
    public final void zzak(boolean z) {
        this.zza.zzak(z);
    }

    @Override // com.google.android.gms.internal.ads.zzcmn
    public final void zzal() {
        setBackgroundColor(0);
        this.zza.setBackgroundColor(0);
    }

    @Override // com.google.android.gms.internal.ads.zzcmn
    public final void zzam(Context context) {
        this.zza.zzam(context);
    }

    @Override // com.google.android.gms.internal.ads.zzcmn
    public final void zzan(boolean z) {
        this.zza.zzan(z);
    }

    @Override // com.google.android.gms.internal.ads.zzcmn
    public final void zzao(zzbll zzbllVar) {
        this.zza.zzao(zzbllVar);
    }

    @Override // com.google.android.gms.internal.ads.zzcmn
    public final void zzap(boolean z) {
        this.zza.zzap(z);
    }

    @Override // com.google.android.gms.internal.ads.zzcmn
    public final void zzaq(zzbln zzblnVar) {
        this.zza.zzaq(zzblnVar);
    }

    @Override // com.google.android.gms.internal.ads.zzcmn
    public final void zzar(IObjectWrapper iObjectWrapper) {
        this.zza.zzar(iObjectWrapper);
    }

    @Override // com.google.android.gms.internal.ads.zzcmn
    public final void zzas(int r2) {
        this.zza.zzas(r2);
    }

    @Override // com.google.android.gms.internal.ads.zzcmn
    public final void zzat(com.google.android.gms.ads.internal.overlay.zzl zzlVar) {
        this.zza.zzat(zzlVar);
    }

    @Override // com.google.android.gms.internal.ads.zzcmn
    public final void zzau(boolean z) {
        this.zza.zzau(z);
    }

    @Override // com.google.android.gms.internal.ads.zzcmn
    public final void zzav(boolean z) {
        this.zza.zzav(z);
    }

    @Override // com.google.android.gms.internal.ads.zzcmn
    public final void zzaw(String str, zzbpq zzbpqVar) {
        this.zza.zzaw(str, zzbpqVar);
    }

    @Override // com.google.android.gms.internal.ads.zzcmn
    public final void zzax(String str, Predicate predicate) {
        this.zza.zzax(str, predicate);
    }

    @Override // com.google.android.gms.internal.ads.zzcmn
    public final boolean zzay() {
        return this.zza.zzay();
    }

    @Override // com.google.android.gms.internal.ads.zzcmn
    public final boolean zzaz() {
        return this.zza.zzaz();
    }

    @Override // com.google.android.gms.internal.ads.zzbsv
    public final void zzb(String str, String str2) {
        this.zza.zzb("window.inspectorInfo", str2);
    }

    @Override // com.google.android.gms.ads.internal.zzl
    public final void zzbn() {
        this.zza.zzbn();
    }

    @Override // com.google.android.gms.ads.internal.zzl
    public final void zzbo() {
        this.zza.zzbo();
    }

    @Override // com.google.android.gms.internal.ads.zzciw
    public final zzcil zzbp() {
        return this.zzb;
    }

    @Override // com.google.android.gms.internal.ads.zzbbm
    public final void zzc(zzbbl zzbblVar) {
        this.zza.zzc(zzbblVar);
    }

    @Override // com.google.android.gms.internal.ads.zzbsi
    public final void zzd(String str, Map map) {
        this.zza.zzd(str, map);
    }

    @Override // com.google.android.gms.internal.ads.zzbsi
    public final void zze(String str, JSONObject jSONObject) {
        this.zza.zze(str, jSONObject);
    }

    @Override // com.google.android.gms.internal.ads.zzciw
    public final int zzf() {
        return this.zza.zzf();
    }

    @Override // com.google.android.gms.internal.ads.zzciw
    public final int zzg() {
        return this.zza.zzg();
    }

    @Override // com.google.android.gms.internal.ads.zzciw
    public final int zzh() {
        return this.zza.zzh();
    }

    @Override // com.google.android.gms.internal.ads.zzciw
    public final int zzi() {
        if (((Boolean) com.google.android.gms.ads.internal.client.zzay.zzc().zzb(zzbiy.zzcV)).booleanValue()) {
            return this.zza.getMeasuredHeight();
        }
        return getMeasuredHeight();
    }

    @Override // com.google.android.gms.internal.ads.zzciw
    public final int zzj() {
        if (((Boolean) com.google.android.gms.ads.internal.client.zzay.zzc().zzb(zzbiy.zzcV)).booleanValue()) {
            return this.zza.getMeasuredWidth();
        }
        return getMeasuredWidth();
    }

    @Override // com.google.android.gms.internal.ads.zzcmn, com.google.android.gms.internal.ads.zzcno, com.google.android.gms.internal.ads.zzciw
    public final Activity zzk() {
        return this.zza.zzk();
    }

    @Override // com.google.android.gms.internal.ads.zzbsv
    public final void zzl(String str, JSONObject jSONObject) {
        ((zzcng) this.zza).zzb(str, jSONObject.toString());
    }

    @Override // com.google.android.gms.internal.ads.zzcmn, com.google.android.gms.internal.ads.zzciw
    public final com.google.android.gms.ads.internal.zza zzm() {
        return this.zza.zzm();
    }

    @Override // com.google.android.gms.internal.ads.zzciw
    public final zzbjk zzn() {
        return this.zza.zzn();
    }

    @Override // com.google.android.gms.internal.ads.zzcmn, com.google.android.gms.internal.ads.zzciw
    public final zzbjl zzo() {
        return this.zza.zzo();
    }

    @Override // com.google.android.gms.internal.ads.zzcmn, com.google.android.gms.internal.ads.zzcnw, com.google.android.gms.internal.ads.zzciw
    public final zzcgt zzp() {
        return this.zza.zzp();
    }

    @Override // com.google.android.gms.internal.ads.zzdkl
    public final void zzq() {
        zzcmn zzcmnVar = this.zza;
        if (zzcmnVar != null) {
            zzcmnVar.zzq();
        }
    }

    @Override // com.google.android.gms.internal.ads.zzciw
    public final zzckz zzr(String str) {
        return this.zza.zzr(str);
    }

    @Override // com.google.android.gms.internal.ads.zzcmn, com.google.android.gms.internal.ads.zzciw
    public final zzcnj zzs() {
        return this.zza.zzs();
    }

    @Override // com.google.android.gms.internal.ads.zzciw
    public final String zzt() {
        return this.zza.zzt();
    }

    @Override // com.google.android.gms.internal.ads.zzciw
    public final String zzu() {
        return this.zza.zzu();
    }

    @Override // com.google.android.gms.internal.ads.zzcmn, com.google.android.gms.internal.ads.zzciw
    public final void zzv(String str, zzckz zzckzVar) {
        this.zza.zzv(str, zzckzVar);
    }

    @Override // com.google.android.gms.internal.ads.zzciw
    public final void zzw() {
        this.zza.zzw();
    }

    @Override // com.google.android.gms.internal.ads.zzciw
    public final void zzx(boolean z, long j) {
        this.zza.zzx(z, j);
    }

    @Override // com.google.android.gms.internal.ads.zzciw
    public final void zzy() {
        this.zza.zzy();
    }

    @Override // com.google.android.gms.internal.ads.zzciw
    public final void zzz(int r2) {
        this.zza.zzz(r2);
    }
}
