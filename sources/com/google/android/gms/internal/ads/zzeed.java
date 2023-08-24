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
public final class zzeed implements zzdhp, com.google.android.gms.ads.internal.client.zza, zzdds, zzddc {
    private final Context zza;
    private final zzfec zzb;
    private final zzfde zzc;
    private final zzfcs zzd;
    private final zzefz zze;
    private Boolean zzf;
    private final boolean zzg = ((Boolean) com.google.android.gms.ads.internal.client.zzay.zzc().zzb(zzbiy.zzfR)).booleanValue();
    private final zzfhz zzh;
    private final String zzi;

    public zzeed(Context context, zzfec zzfecVar, zzfde zzfdeVar, zzfcs zzfcsVar, zzefz zzefzVar, zzfhz zzfhzVar, String str) {
        this.zza = context;
        this.zzb = zzfecVar;
        this.zzc = zzfdeVar;
        this.zzd = zzfcsVar;
        this.zze = zzefzVar;
        this.zzh = zzfhzVar;
        this.zzi = str;
    }

    private final zzfhy zzf(String str) {
        zzfhy zzb = zzfhy.zzb(str);
        zzb.zzh(this.zzc, null);
        zzb.zzf(this.zzd);
        zzb.zza("request_id", this.zzi);
        if (!this.zzd.zzu.isEmpty()) {
            zzb.zza("ancn", (String) this.zzd.zzu.get(0));
        }
        if (this.zzd.zzak) {
            zzb.zza("device_connectivity", true != com.google.android.gms.ads.internal.zzt.zzp().zzv(this.zza) ? "offline" : CustomTabsCallback.ONLINE_EXTRAS_KEY);
            zzb.zza("event_timestamp", String.valueOf(com.google.android.gms.ads.internal.zzt.zzB().currentTimeMillis()));
            zzb.zza("offline_ad", IcyHeaders.REQUEST_HEADER_ENABLE_METADATA_VALUE);
        }
        return zzb;
    }

    private final void zzg(zzfhy zzfhyVar) {
        if (this.zzd.zzak) {
            this.zze.zzd(new zzegb(com.google.android.gms.ads.internal.zzt.zzB().currentTimeMillis(), this.zzc.zzb.zzb.zzb, this.zzh.zza(zzfhyVar), 2));
            return;
        }
        this.zzh.zzb(zzfhyVar);
    }

    private final boolean zzh() {
        if (this.zzf == null) {
            synchronized (this) {
                if (this.zzf == null) {
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
                    this.zzf = Boolean.valueOf(z);
                }
            }
        }
        return this.zzf.booleanValue();
    }

    @Override // com.google.android.gms.ads.internal.client.zza
    public final void onAdClicked() {
        if (this.zzd.zzak) {
            zzg(zzf("click"));
        }
    }

    @Override // com.google.android.gms.internal.ads.zzddc
    public final void zza(com.google.android.gms.ads.internal.client.zze zzeVar) {
        com.google.android.gms.ads.internal.client.zze zzeVar2;
        if (this.zzg) {
            int r0 = zzeVar.zza;
            String str = zzeVar.zzb;
            if (zzeVar.zzc.equals(MobileAds.ERROR_DOMAIN) && (zzeVar2 = zzeVar.zzd) != null && !zzeVar2.zzc.equals(MobileAds.ERROR_DOMAIN)) {
                com.google.android.gms.ads.internal.client.zze zzeVar3 = zzeVar.zzd;
                r0 = zzeVar3.zza;
                str = zzeVar3.zzb;
            }
            String zza = this.zzb.zza(str);
            zzfhy zzf = zzf("ifts");
            zzf.zza("reason", "adapter");
            if (r0 >= 0) {
                zzf.zza("arec", String.valueOf(r0));
            }
            if (zza != null) {
                zzf.zza("areec", zza);
            }
            this.zzh.zzb(zzf);
        }
    }

    @Override // com.google.android.gms.internal.ads.zzddc
    public final void zzb() {
        if (this.zzg) {
            zzfhz zzfhzVar = this.zzh;
            zzfhy zzf = zzf("ifts");
            zzf.zza("reason", "blocked");
            zzfhzVar.zzb(zzf);
        }
    }

    @Override // com.google.android.gms.internal.ads.zzddc
    public final void zzc(zzdmm zzdmmVar) {
        if (this.zzg) {
            zzfhy zzf = zzf("ifts");
            zzf.zza("reason", "exception");
            if (!TextUtils.isEmpty(zzdmmVar.getMessage())) {
                zzf.zza(NotificationCompat.CATEGORY_MESSAGE, zzdmmVar.getMessage());
            }
            this.zzh.zzb(zzf);
        }
    }

    @Override // com.google.android.gms.internal.ads.zzdhp
    public final void zzd() {
        if (zzh()) {
            this.zzh.zzb(zzf("adapter_shown"));
        }
    }

    @Override // com.google.android.gms.internal.ads.zzdhp
    public final void zze() {
        if (zzh()) {
            this.zzh.zzb(zzf("adapter_impression"));
        }
    }

    @Override // com.google.android.gms.internal.ads.zzdds
    public final void zzl() {
        if (zzh() || this.zzd.zzak) {
            zzg(zzf("impression"));
        }
    }
}
