package com.google.android.gms.ads.internal.overlay;

import android.content.Context;
import android.text.TextUtils;
import com.google.android.gms.ads.internal.client.zzay;
import com.google.android.gms.internal.ads.zzbiy;
import com.google.android.gms.internal.ads.zzcha;
import com.google.android.gms.internal.ads.zzcmn;
import com.google.android.gms.internal.ads.zzfpy;
import com.google.android.gms.internal.ads.zzfpz;
import com.google.android.gms.internal.ads.zzfqa;
import com.google.android.gms.internal.ads.zzfqb;
import com.google.android.gms.internal.ads.zzfqk;
import com.google.android.gms.internal.ads.zzfqm;
import com.google.android.gms.internal.ads.zzfqn;
import com.google.android.gms.internal.ads.zzfqo;
import com.google.android.gms.internal.ads.zzfqp;
import com.google.android.gms.internal.ads.zzfrj;
import com.onesignal.OneSignalDbContract;
import java.util.HashMap;
import java.util.Map;

/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
public final class zzw {
    private zzfqn zzf;
    private zzcmn zzc = null;
    private boolean zze = false;
    private String zza = null;
    private zzfqa zzd = null;
    private String zzb = null;

    private final zzfqp zzl() {
        zzfqo zzc = zzfqp.zzc();
        if (!((Boolean) zzay.zzc().zzb(zzbiy.zziM)).booleanValue() || TextUtils.isEmpty(this.zzb)) {
            String str = this.zza;
            if (str != null) {
                zzc.zzb(str);
            } else {
                zzf("Missing session token and/or appId", "onLMDupdate");
            }
        } else {
            zzc.zza(this.zzb);
        }
        return zzc.zzc();
    }

    private final void zzm() {
        if (this.zzf == null) {
            this.zzf = new zzv(this);
        }
    }

    public final synchronized void zza(zzcmn zzcmnVar, Context context) {
        this.zzc = zzcmnVar;
        if (!zzk(context)) {
            zzf("Unable to bind", "on_play_store_bind");
            return;
        }
        HashMap hashMap = new HashMap();
        hashMap.put("action", "fetch_completed");
        zze("on_play_store_bind", hashMap);
    }

    public final void zzb() {
        zzfqa zzfqaVar;
        if (!this.zze || (zzfqaVar = this.zzd) == null) {
            com.google.android.gms.ads.internal.util.zze.zza("LastMileDelivery not connected");
            return;
        }
        zzfqaVar.zza(zzl(), this.zzf);
        zzd("onLMDOverlayCollapse");
    }

    public final void zzc() {
        zzfqa zzfqaVar;
        if (!this.zze || (zzfqaVar = this.zzd) == null) {
            com.google.android.gms.ads.internal.util.zze.zza("LastMileDelivery not connected");
            return;
        }
        zzfpy zzc = zzfpz.zzc();
        if (((Boolean) zzay.zzc().zzb(zzbiy.zziM)).booleanValue() && !TextUtils.isEmpty(this.zzb)) {
            zzc.zza(this.zzb);
        } else {
            String str = this.zza;
            if (str != null) {
                zzc.zzb(str);
            } else {
                zzf("Missing session token and/or appId", "onLMDupdate");
            }
        }
        zzfqaVar.zzb(zzc.zzc(), this.zzf);
    }

    final void zzd(String str) {
        zze(str, new HashMap());
    }

    final void zze(final String str, final Map map) {
        zzcha.zze.execute(new Runnable() { // from class: com.google.android.gms.ads.internal.overlay.zzu
            @Override // java.lang.Runnable
            public final void run() {
                zzw.this.zzh(str, map);
            }
        });
    }

    final void zzf(String str, String str2) {
        com.google.android.gms.ads.internal.util.zze.zza(str);
        if (this.zzc != null) {
            HashMap hashMap = new HashMap();
            hashMap.put(OneSignalDbContract.NotificationTable.COLUMN_NAME_MESSAGE, str);
            hashMap.put("action", str2);
            zze("onError", hashMap);
        }
    }

    public final void zzg() {
        zzfqa zzfqaVar;
        if (!this.zze || (zzfqaVar = this.zzd) == null) {
            com.google.android.gms.ads.internal.util.zze.zza("LastMileDelivery not connected");
            return;
        }
        zzfqaVar.zzc(zzl(), this.zzf);
        zzd("onLMDOverlayExpand");
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final /* synthetic */ void zzh(String str, Map map) {
        zzcmn zzcmnVar = this.zzc;
        if (zzcmnVar != null) {
            zzcmnVar.zzd(str, map);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final void zzi(zzfqm zzfqmVar) {
        if (!TextUtils.isEmpty(zzfqmVar.zzb())) {
            if (!((Boolean) zzay.zzc().zzb(zzbiy.zziM)).booleanValue()) {
                this.zza = zzfqmVar.zzb();
            }
        }
        switch (zzfqmVar.zza()) {
            case 8152:
                zzd("onLMDOverlayOpened");
                return;
            case 8153:
                zzd("onLMDOverlayClicked");
                return;
            case 8154:
            case 8156:
            case 8158:
            case 8159:
            default:
                return;
            case 8155:
                zzd("onLMDOverlayClose");
                return;
            case 8157:
                this.zza = null;
                this.zzb = null;
                this.zze = false;
                return;
            case 8160:
            case 8161:
            case 8162:
                HashMap hashMap = new HashMap();
                hashMap.put("error", String.valueOf(zzfqmVar.zza()));
                zze("onLMDOverlayFailedToOpen", hashMap);
                return;
        }
    }

    public final void zzj(zzcmn zzcmnVar, zzfqk zzfqkVar) {
        if (zzcmnVar == null) {
            zzf("adWebview missing", "onLMDShow");
            return;
        }
        this.zzc = zzcmnVar;
        if (this.zze || zzk(zzcmnVar.getContext())) {
            if (((Boolean) zzay.zzc().zzb(zzbiy.zziM)).booleanValue()) {
                this.zzb = zzfqkVar.zzg();
            }
            zzm();
            zzfqa zzfqaVar = this.zzd;
            if (zzfqaVar != null) {
                zzfqaVar.zzd(zzfqkVar, this.zzf);
                return;
            }
            return;
        }
        zzf("LMDOverlay not bound", "on_play_store_bind");
    }

    public final synchronized boolean zzk(Context context) {
        if (zzfrj.zza(context)) {
            try {
                this.zzd = zzfqb.zza(context);
            } catch (NullPointerException e) {
                com.google.android.gms.ads.internal.util.zze.zza("Error connecting LMD Overlay service");
                com.google.android.gms.ads.internal.zzt.zzp().zzt(e, "LastMileDeliveryOverlay.bindLastMileDeliveryService");
            }
            if (this.zzd == null) {
                this.zze = false;
                return false;
            }
            zzm();
            this.zze = true;
            return true;
        }
        return false;
    }
}
