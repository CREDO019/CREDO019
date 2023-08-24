package com.google.android.gms.internal.ads;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.WindowManager;
import java.util.Map;
import org.json.JSONException;
import org.json.JSONObject;

/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
public final class zzbxz extends zzbya implements zzbpq {
    DisplayMetrics zza;
    int zzb;
    int zzc;
    int zzd;
    int zze;
    int zzf;
    int zzg;
    private final zzcmn zzh;
    private final Context zzi;
    private final WindowManager zzj;
    private final zzbii zzk;
    private float zzl;
    private int zzm;

    public zzbxz(zzcmn zzcmnVar, Context context, zzbii zzbiiVar) {
        super(zzcmnVar, "");
        this.zzb = -1;
        this.zzc = -1;
        this.zzd = -1;
        this.zze = -1;
        this.zzf = -1;
        this.zzg = -1;
        this.zzh = zzcmnVar;
        this.zzi = context;
        this.zzk = zzbiiVar;
        this.zzj = (WindowManager) context.getSystemService("window");
    }

    @Override // com.google.android.gms.internal.ads.zzbpq
    public final /* synthetic */ void zza(Object obj, Map map) {
        boolean z;
        boolean z2;
        boolean z3;
        boolean z4;
        boolean z5;
        JSONObject jSONObject;
        zzcmn zzcmnVar = (zzcmn) obj;
        this.zza = new DisplayMetrics();
        Display defaultDisplay = this.zzj.getDefaultDisplay();
        defaultDisplay.getMetrics(this.zza);
        this.zzl = this.zza.density;
        this.zzm = defaultDisplay.getRotation();
        com.google.android.gms.ads.internal.client.zzaw.zzb();
        DisplayMetrics displayMetrics = this.zza;
        this.zzb = zzcgg.zzu(displayMetrics, displayMetrics.widthPixels);
        com.google.android.gms.ads.internal.client.zzaw.zzb();
        DisplayMetrics displayMetrics2 = this.zza;
        this.zzc = zzcgg.zzu(displayMetrics2, displayMetrics2.heightPixels);
        Activity zzk = this.zzh.zzk();
        if (zzk == null || zzk.getWindow() == null) {
            this.zzd = this.zzb;
            this.zze = this.zzc;
        } else {
            com.google.android.gms.ads.internal.zzt.zzq();
            int[] zzN = com.google.android.gms.ads.internal.util.zzs.zzN(zzk);
            com.google.android.gms.ads.internal.client.zzaw.zzb();
            this.zzd = zzcgg.zzu(this.zza, zzN[0]);
            com.google.android.gms.ads.internal.client.zzaw.zzb();
            this.zze = zzcgg.zzu(this.zza, zzN[1]);
        }
        if (this.zzh.zzQ().zzi()) {
            this.zzf = this.zzb;
            this.zzg = this.zzc;
        } else {
            this.zzh.measure(0, 0);
        }
        zzi(this.zzb, this.zzc, this.zzd, this.zze, this.zzl, this.zzm);
        zzbxy zzbxyVar = new zzbxy();
        zzbii zzbiiVar = this.zzk;
        Intent intent = new Intent("android.intent.action.DIAL");
        intent.setData(Uri.parse("tel:"));
        zzbxyVar.zze(zzbiiVar.zza(intent));
        zzbii zzbiiVar2 = this.zzk;
        Intent intent2 = new Intent("android.intent.action.VIEW");
        intent2.setData(Uri.parse("sms:"));
        zzbxyVar.zzc(zzbiiVar2.zza(intent2));
        zzbxyVar.zza(this.zzk.zzb());
        zzbxyVar.zzd(this.zzk.zzc());
        zzbxyVar.zzb(true);
        z = zzbxyVar.zza;
        z2 = zzbxyVar.zzb;
        z3 = zzbxyVar.zzc;
        z4 = zzbxyVar.zzd;
        z5 = zzbxyVar.zze;
        zzcmn zzcmnVar2 = this.zzh;
        try {
            jSONObject = new JSONObject().put("sms", z).put("tel", z2).put("calendar", z3).put("storePicture", z4).put("inlineVideo", z5);
        } catch (JSONException e) {
            com.google.android.gms.ads.internal.util.zze.zzh("Error occurred while obtaining the MRAID capabilities.", e);
            jSONObject = null;
        }
        zzcmnVar2.zze("onDeviceFeaturesReceived", jSONObject);
        int[] r1 = new int[2];
        this.zzh.getLocationOnScreen(r1);
        zzb(com.google.android.gms.ads.internal.client.zzaw.zzb().zzb(this.zzi, r1[0]), com.google.android.gms.ads.internal.client.zzaw.zzb().zzb(this.zzi, r1[1]));
        if (com.google.android.gms.ads.internal.util.zze.zzm(2)) {
            com.google.android.gms.ads.internal.util.zze.zzi("Dispatching Ready Event.");
        }
        zzh(this.zzh.zzp().zza);
    }

    public final void zzb(int r7, int r8) {
        int r0;
        int r1 = 0;
        if (this.zzi instanceof Activity) {
            com.google.android.gms.ads.internal.zzt.zzq();
            r0 = com.google.android.gms.ads.internal.util.zzs.zzO((Activity) this.zzi)[0];
        } else {
            r0 = 0;
        }
        if (this.zzh.zzQ() == null || !this.zzh.zzQ().zzi()) {
            int width = this.zzh.getWidth();
            int height = this.zzh.getHeight();
            if (((Boolean) com.google.android.gms.ads.internal.client.zzay.zzc().zzb(zzbiy.zzP)).booleanValue()) {
                if (width == 0) {
                    width = this.zzh.zzQ() != null ? this.zzh.zzQ().zzb : 0;
                }
                if (height == 0) {
                    if (this.zzh.zzQ() != null) {
                        r1 = this.zzh.zzQ().zza;
                    }
                    this.zzf = com.google.android.gms.ads.internal.client.zzaw.zzb().zzb(this.zzi, width);
                    this.zzg = com.google.android.gms.ads.internal.client.zzaw.zzb().zzb(this.zzi, r1);
                }
            }
            r1 = height;
            this.zzf = com.google.android.gms.ads.internal.client.zzaw.zzb().zzb(this.zzi, width);
            this.zzg = com.google.android.gms.ads.internal.client.zzaw.zzb().zzb(this.zzi, r1);
        }
        zzf(r7, r8 - r0, this.zzf, this.zzg);
        this.zzh.zzP().zzA(r7, r8);
    }
}
