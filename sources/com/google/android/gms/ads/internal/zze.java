package com.google.android.gms.ads.internal;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.text.TextUtils;
import com.amplitude.api.Constants;
import com.google.android.gms.ads.internal.client.zzay;
import com.google.android.gms.common.wrappers.Wrappers;
import com.google.android.gms.internal.ads.zzbiy;
import com.google.android.gms.internal.ads.zzbtv;
import com.google.android.gms.internal.ads.zzbuc;
import com.google.android.gms.internal.ads.zzcfq;
import com.google.android.gms.internal.ads.zzcgt;
import com.google.android.gms.internal.ads.zzcha;
import com.google.android.gms.internal.ads.zzchd;
import com.google.android.gms.internal.ads.zzfiq;
import com.google.android.gms.internal.ads.zzfir;
import com.google.android.gms.internal.ads.zzfje;
import com.google.android.gms.internal.ads.zzfxv;
import com.google.android.gms.internal.ads.zzfyo;
import com.google.android.gms.internal.ads.zzfyx;
import com.onesignal.outcomes.OSOutcomeConstants;
import javax.annotation.ParametersAreNonnullByDefault;
import org.json.JSONObject;

/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
@ParametersAreNonnullByDefault
/* loaded from: classes2.dex */
public final class zze {
    private Context zza;
    private long zzb = 0;

    public final void zza(Context context, zzcgt zzcgtVar, String str, Runnable runnable, zzfje zzfjeVar) {
        zzb(context, zzcgtVar, true, null, str, null, runnable, zzfjeVar);
    }

    final void zzb(Context context, zzcgt zzcgtVar, boolean z, zzcfq zzcfqVar, String str, String str2, Runnable runnable, final zzfje zzfjeVar) {
        PackageInfo packageInfo;
        if (zzt.zzB().elapsedRealtime() - this.zzb < 5000) {
            com.google.android.gms.ads.internal.util.zze.zzj("Not retrying to fetch app settings");
            return;
        }
        this.zzb = zzt.zzB().elapsedRealtime();
        if (zzcfqVar != null) {
            if (zzt.zzB().currentTimeMillis() - zzcfqVar.zza() <= ((Long) zzay.zzc().zzb(zzbiy.zzdf)).longValue() && zzcfqVar.zzi()) {
                return;
            }
        }
        if (context == null) {
            com.google.android.gms.ads.internal.util.zze.zzj("Context not provided to fetch application settings");
        } else if (!TextUtils.isEmpty(str) || !TextUtils.isEmpty(str2)) {
            Context applicationContext = context.getApplicationContext();
            if (applicationContext == null) {
                applicationContext = context;
            }
            this.zza = applicationContext;
            final zzfir zza = zzfiq.zza(context, 4);
            zza.zzf();
            zzbtv zza2 = zzt.zzf().zza(this.zza, zzcgtVar, zzfjeVar).zza("google.afma.config.fetchAppSettings", zzbuc.zza, zzbuc.zza);
            try {
                JSONObject jSONObject = new JSONObject();
                if (!TextUtils.isEmpty(str)) {
                    jSONObject.put(OSOutcomeConstants.APP_ID, str);
                } else if (!TextUtils.isEmpty(str2)) {
                    jSONObject.put("ad_unit_id", str2);
                }
                jSONObject.put("is_init", z);
                jSONObject.put("pn", context.getPackageName());
                jSONObject.put("experiment_ids", TextUtils.join(",", zzbiy.zza()));
                try {
                    ApplicationInfo applicationInfo = this.zza.getApplicationInfo();
                    if (applicationInfo != null && (packageInfo = Wrappers.packageManager(context).getPackageInfo(applicationInfo.packageName, 0)) != null) {
                        jSONObject.put(Constants.AMP_PLAN_VERSION, packageInfo.versionCode);
                    }
                } catch (PackageManager.NameNotFoundException unused) {
                    com.google.android.gms.ads.internal.util.zze.zza("Error fetching PackageInfo.");
                }
                zzfyx zzb = zza2.zzb(jSONObject);
                zzfyx zzn = zzfyo.zzn(zzb, new zzfxv() { // from class: com.google.android.gms.ads.internal.zzd
                    @Override // com.google.android.gms.internal.ads.zzfxv
                    public final zzfyx zza(Object obj) {
                        zzfje zzfjeVar2 = zzfje.this;
                        zzfir zzfirVar = zza;
                        JSONObject jSONObject2 = (JSONObject) obj;
                        boolean optBoolean = jSONObject2.optBoolean("isSuccessful", false);
                        if (optBoolean) {
                            zzt.zzp().zzh().zzu(jSONObject2.getString("appSettingsJson"));
                        }
                        zzfirVar.zze(optBoolean);
                        zzfjeVar2.zzb(zzfirVar.zzj());
                        return zzfyo.zzi(null);
                    }
                }, zzcha.zzf);
                if (runnable != null) {
                    zzb.zzc(runnable, zzcha.zzf);
                }
                zzchd.zza(zzn, "ConfigLoader.maybeFetchNewAppSettings");
            } catch (Exception e) {
                com.google.android.gms.ads.internal.util.zze.zzh("Error requesting application settings", e);
                zza.zze(false);
                zzfjeVar.zzb(zza.zzj());
            }
        } else {
            com.google.android.gms.ads.internal.util.zze.zzj("App settings could not be fetched. Required parameters missing");
        }
    }

    public final void zzc(Context context, zzcgt zzcgtVar, String str, zzcfq zzcfqVar, zzfje zzfjeVar) {
        zzb(context, zzcgtVar, false, zzcfqVar, zzcfqVar != null ? zzcfqVar.zzb() : null, str, null, zzfjeVar);
    }
}
