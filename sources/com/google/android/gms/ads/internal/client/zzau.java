package com.google.android.gms.ads.internal.client;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;
import com.google.android.gms.ads.p013h5.OnH5AdsEventListener;
import com.google.android.gms.internal.ads.zzbme;
import com.google.android.gms.internal.ads.zzbmk;
import com.google.android.gms.internal.ads.zzbnz;
import com.google.android.gms.internal.ads.zzboa;
import com.google.android.gms.internal.ads.zzbqp;
import com.google.android.gms.internal.ads.zzbvf;
import com.google.android.gms.internal.ads.zzbyq;
import com.google.android.gms.internal.ads.zzbyx;
import com.google.android.gms.internal.ads.zzbza;
import com.google.android.gms.internal.ads.zzcaf;
import com.google.android.gms.internal.ads.zzccj;
import com.google.android.gms.internal.ads.zzccv;
import com.google.android.gms.internal.ads.zzcfe;
import com.google.android.gms.internal.ads.zzcgn;
import java.util.HashMap;

/* compiled from: com.google.android.gms:play-services-ads-lite@@21.2.0 */
/* loaded from: classes2.dex */
public final class zzau {
    private final zzk zza;
    private final zzi zzb;
    private final zzek zzc;
    private final zzbnz zzd;
    private final zzccv zze;
    private final zzbyx zzf;
    private final zzboa zzg;
    private zzcaf zzh;

    public zzau(zzk zzkVar, zzi zziVar, zzek zzekVar, zzbnz zzbnzVar, zzccv zzccvVar, zzbyx zzbyxVar, zzboa zzboaVar) {
        this.zza = zzkVar;
        this.zzb = zziVar;
        this.zzc = zzekVar;
        this.zzd = zzbnzVar;
        this.zze = zzccvVar;
        this.zzf = zzbyxVar;
        this.zzg = zzboaVar;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* bridge */ /* synthetic */ void zzs(Context context, String str) {
        Bundle bundle = new Bundle();
        bundle.putString("action", "no_ads_fallback");
        bundle.putString("flow", str);
        zzaw.zzb().zzm(context, zzaw.zzc().zza, "gmob-apps", bundle, true);
    }

    public final zzbo zzc(Context context, String str, zzbvf zzbvfVar) {
        return (zzbo) new zzam(this, context, str, zzbvfVar).zzd(context, false);
    }

    public final zzbs zzd(Context context, zzq zzqVar, String str, zzbvf zzbvfVar) {
        return (zzbs) new zzai(this, context, zzqVar, str, zzbvfVar).zzd(context, false);
    }

    public final zzbs zze(Context context, zzq zzqVar, String str, zzbvf zzbvfVar) {
        return (zzbs) new zzak(this, context, zzqVar, str, zzbvfVar).zzd(context, false);
    }

    public final zzbme zzg(Context context, FrameLayout frameLayout, FrameLayout frameLayout2) {
        return (zzbme) new zzaq(this, frameLayout, frameLayout2, context).zzd(context, false);
    }

    public final zzbmk zzh(View view, HashMap hashMap, HashMap hashMap2) {
        return (zzbmk) new zzas(this, view, hashMap, hashMap2).zzd(view.getContext(), false);
    }

    public final zzbqp zzk(Context context, zzbvf zzbvfVar, OnH5AdsEventListener onH5AdsEventListener) {
        return (zzbqp) new zzag(this, context, zzbvfVar, onH5AdsEventListener).zzd(context, false);
    }

    public final zzbyq zzl(Context context, zzbvf zzbvfVar) {
        return (zzbyq) new zzae(this, context, zzbvfVar).zzd(context, false);
    }

    public final zzbza zzn(Activity activity) {
        zzaa zzaaVar = new zzaa(this, activity);
        Intent intent = activity.getIntent();
        boolean z = false;
        if (!intent.hasExtra("com.google.android.gms.ads.internal.overlay.useClientJar")) {
            zzcgn.zzg("useClientJar flag not found in activity intent extras.");
        } else {
            z = intent.getBooleanExtra("com.google.android.gms.ads.internal.overlay.useClientJar", false);
        }
        return (zzbza) zzaaVar.zzd(activity, z);
    }

    public final zzccj zzp(Context context, String str, zzbvf zzbvfVar) {
        return (zzccj) new zzat(this, context, str, zzbvfVar).zzd(context, false);
    }

    public final zzcfe zzq(Context context, zzbvf zzbvfVar) {
        return (zzcfe) new zzac(this, context, zzbvfVar).zzd(context, false);
    }
}
