package com.google.android.gms.internal.ads;

import android.location.Location;
import android.os.Bundle;
import android.text.TextUtils;
import com.google.android.gms.common.internal.ImagesContract;
import com.google.android.gms.common.internal.Preconditions;
import com.onesignal.influence.OSInfluenceConstants;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
public final class zzeov implements zzeum {
    final zzfdn zza;
    private final long zzb;

    public zzeov(zzfdn zzfdnVar, long j) {
        Preconditions.checkNotNull(zzfdnVar, "the targeting must not be null");
        this.zza = zzfdnVar;
        this.zzb = j;
    }

    @Override // com.google.android.gms.internal.ads.zzeum
    public final /* bridge */ /* synthetic */ void zzf(Object obj) {
        Bundle bundle = (Bundle) obj;
        com.google.android.gms.ads.internal.client.zzl zzlVar = this.zza.zzd;
        bundle.putInt("http_timeout_millis", zzlVar.zzw);
        bundle.putString("slotname", this.zza.zzf);
        int r1 = this.zza.zzo.zza;
        int r2 = r1 - 1;
        if (r1 == 0) {
            throw null;
        }
        if (r2 == 1) {
            bundle.putBoolean("is_new_rewarded", true);
        } else if (r2 == 2) {
            bundle.putBoolean("is_rewarded_interstitial", true);
        }
        bundle.putLong("start_signals_timestamp", this.zzb);
        zzfdy.zzg(bundle, "cust_age", new SimpleDateFormat("yyyyMMdd", Locale.US).format(new Date(zzlVar.zzb)), zzlVar.zzb != -1);
        zzfdy.zzb(bundle, "extras", zzlVar.zzc);
        zzfdy.zzf(bundle, "cust_gender", Integer.valueOf(zzlVar.zzd), zzlVar.zzd != -1);
        zzfdy.zzd(bundle, "kw", zzlVar.zze);
        zzfdy.zzf(bundle, "tag_for_child_directed_treatment", Integer.valueOf(zzlVar.zzg), zzlVar.zzg != -1);
        if (zzlVar.zzf) {
            bundle.putBoolean("test_request", true);
        }
        zzfdy.zzf(bundle, "d_imp_hdr", 1, zzlVar.zza >= 2 && zzlVar.zzh);
        String str = zzlVar.zzi;
        zzfdy.zzg(bundle, "ppid", str, zzlVar.zza >= 2 && !TextUtils.isEmpty(str));
        Location location = zzlVar.zzk;
        if (location != null) {
            Float valueOf = Float.valueOf(location.getAccuracy() * 1000.0f);
            Long valueOf2 = Long.valueOf(location.getTime() * 1000);
            Long valueOf3 = Long.valueOf((long) (location.getLatitude() * 1.0E7d));
            Long valueOf4 = Long.valueOf((long) (location.getLongitude() * 1.0E7d));
            Bundle bundle2 = new Bundle();
            bundle2.putFloat("radius", valueOf.floatValue());
            bundle2.putLong("lat", valueOf3.longValue());
            bundle2.putLong("long", valueOf4.longValue());
            bundle2.putLong(OSInfluenceConstants.TIME, valueOf2.longValue());
            bundle.putBundle("uule", bundle2);
        }
        zzfdy.zzc(bundle, ImagesContract.URL, zzlVar.zzl);
        zzfdy.zzd(bundle, "neighboring_content_urls", zzlVar.zzv);
        zzfdy.zzb(bundle, "custom_targeting", zzlVar.zzn);
        zzfdy.zzd(bundle, "category_exclusions", zzlVar.zzo);
        zzfdy.zzc(bundle, "request_agent", zzlVar.zzp);
        zzfdy.zzc(bundle, "request_pkg", zzlVar.zzq);
        zzfdy.zze(bundle, "is_designed_for_families", Boolean.valueOf(zzlVar.zzr), zzlVar.zza >= 7);
        if (zzlVar.zza >= 8) {
            zzfdy.zzf(bundle, "tag_for_under_age_of_consent", Integer.valueOf(zzlVar.zzt), zzlVar.zzt != -1);
            zzfdy.zzc(bundle, "max_ad_content_rating", zzlVar.zzu);
        }
    }
}
