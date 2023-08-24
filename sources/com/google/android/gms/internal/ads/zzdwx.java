package com.google.android.gms.internal.ads;

import android.content.Context;
import android.text.TextUtils;
import androidx.browser.customtabs.CustomTabsCallback;
import androidx.core.app.NotificationCompat;
import com.google.android.exoplayer2.metadata.icy.IcyHeaders;
import com.google.android.gms.ads.MobileAds;
import java.util.regex.Pattern;

/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
public final class zzdwx implements zzdhp, com.google.android.gms.ads.internal.client.zza, zzdds, zzddc {
    private final Context zza;
    private final zzfec zzb;
    private final zzdxo zzc;
    private final zzfde zzd;
    private final zzfcs zze;
    private final zzefz zzf;
    private Boolean zzg;
    private final boolean zzh = ((Boolean) com.google.android.gms.ads.internal.client.zzay.zzc().zzb(zzbiy.zzfR)).booleanValue();

    public zzdwx(Context context, zzfec zzfecVar, zzdxo zzdxoVar, zzfde zzfdeVar, zzfcs zzfcsVar, zzefz zzefzVar) {
        this.zza = context;
        this.zzb = zzfecVar;
        this.zzc = zzdxoVar;
        this.zzd = zzfdeVar;
        this.zze = zzfcsVar;
        this.zzf = zzefzVar;
    }

    private final zzdxn zzf(String str) {
        zzdxn zza = this.zzc.zza();
        zza.zze(this.zzd.zzb.zzb);
        zza.zzd(this.zze);
        zza.zzb("action", str);
        if (!this.zze.zzu.isEmpty()) {
            zza.zzb("ancn", (String) this.zze.zzu.get(0));
        }
        if (this.zze.zzak) {
            zza.zzb("device_connectivity", true != com.google.android.gms.ads.internal.zzt.zzp().zzv(this.zza) ? "offline" : CustomTabsCallback.ONLINE_EXTRAS_KEY);
            zza.zzb("event_timestamp", String.valueOf(com.google.android.gms.ads.internal.zzt.zzB().currentTimeMillis()));
            zza.zzb("offline_ad", IcyHeaders.REQUEST_HEADER_ENABLE_METADATA_VALUE);
        }
        if (((Boolean) com.google.android.gms.ads.internal.client.zzay.zzc().zzb(zzbiy.zzga)).booleanValue()) {
            boolean z = com.google.android.gms.ads.nonagon.signalgeneration.zzf.zzd(this.zzd.zza.zza) != 1;
            zza.zzb("scar", String.valueOf(z));
            if (z) {
                com.google.android.gms.ads.internal.client.zzl zzlVar = this.zzd.zza.zza.zzd;
                zza.zzc("ragent", zzlVar.zzp);
                zza.zzc("rtype", com.google.android.gms.ads.nonagon.signalgeneration.zzf.zza(com.google.android.gms.ads.nonagon.signalgeneration.zzf.zzb(zzlVar)));
            }
        }
        return zza;
    }

    private final void zzg(zzdxn zzdxnVar) {
        if (this.zze.zzak) {
            this.zzf.zzd(new zzegb(com.google.android.gms.ads.internal.zzt.zzB().currentTimeMillis(), this.zzd.zzb.zzb.zzb, zzdxnVar.zzf(), 2));
            return;
        }
        zzdxnVar.zzg();
    }

    private final boolean zzh() {
        if (this.zzg == null) {
            synchronized (this) {
                if (this.zzg == null) {
                    String str = (String) com.google.android.gms.ads.internal.client.zzay.zzc().zzb(zzbiy.zzbm);
                    com.google.android.gms.ads.internal.zzt.zzq();
                    String zzo = com.google.android.gms.ads.internal.util.zzs.zzo(this.zza);
                    boolean z = false;
                    if (str != null && zzo != null) {
                        try {
                            z = Pattern.matches(str, zzo);
                        } catch (RuntimeException e) {
                            com.google.android.gms.ads.internal.zzt.zzp().zzt(e, "CsiActionsListener.isPatternMatched");
                        }
                    }
                    this.zzg = Boolean.valueOf(z);
                }
            }
        }
        return this.zzg.booleanValue();
    }

    @Override // com.google.android.gms.ads.internal.client.zza
    public final void onAdClicked() {
        if (this.zze.zzak) {
            zzg(zzf("click"));
        }
    }

    @Override // com.google.android.gms.internal.ads.zzddc
    public final void zza(com.google.android.gms.ads.internal.client.zze zzeVar) {
        com.google.android.gms.ads.internal.client.zze zzeVar2;
        if (this.zzh) {
            zzdxn zzf = zzf("ifts");
            zzf.zzb("reason", "adapter");
            int r1 = zzeVar.zza;
            String str = zzeVar.zzb;
            if (zzeVar.zzc.equals(MobileAds.ERROR_DOMAIN) && (zzeVar2 = zzeVar.zzd) != null && !zzeVar2.zzc.equals(MobileAds.ERROR_DOMAIN)) {
                com.google.android.gms.ads.internal.client.zze zzeVar3 = zzeVar.zzd;
                r1 = zzeVar3.zza;
                str = zzeVar3.zzb;
            }
            if (r1 >= 0) {
                zzf.zzb("arec", String.valueOf(r1));
            }
            String zza = this.zzb.zza(str);
            if (zza != null) {
                zzf.zzb("areec", zza);
            }
            zzf.zzg();
        }
    }

    @Override // com.google.android.gms.internal.ads.zzddc
    public final void zzb() {
        if (this.zzh) {
            zzdxn zzf = zzf("ifts");
            zzf.zzb("reason", "blocked");
            zzf.zzg();
        }
    }

    @Override // com.google.android.gms.internal.ads.zzddc
    public final void zzc(zzdmm zzdmmVar) {
        if (this.zzh) {
            zzdxn zzf = zzf("ifts");
            zzf.zzb("reason", "exception");
            if (!TextUtils.isEmpty(zzdmmVar.getMessage())) {
                zzf.zzb(NotificationCompat.CATEGORY_MESSAGE, zzdmmVar.getMessage());
            }
            zzf.zzg();
        }
    }

    @Override // com.google.android.gms.internal.ads.zzdhp
    public final void zzd() {
        if (zzh()) {
            zzf("adapter_shown").zzg();
        }
    }

    @Override // com.google.android.gms.internal.ads.zzdhp
    public final void zze() {
        if (zzh()) {
            zzf("adapter_impression").zzg();
        }
    }

    @Override // com.google.android.gms.internal.ads.zzdds
    public final void zzl() {
        if (zzh() || this.zze.zzak) {
            zzg(zzf("impression"));
        }
    }
}
