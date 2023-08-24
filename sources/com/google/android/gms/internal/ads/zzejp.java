package com.google.android.gms.internal.ads;

import android.os.Bundle;
import android.text.TextUtils;
import com.google.ads.mediation.AbstractAdViewAdapter;
import java.util.ArrayList;
import java.util.Iterator;

/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
public abstract class zzejp implements zzegk {
    private static Bundle zzd(Bundle bundle) {
        return bundle == null ? new Bundle() : new Bundle(bundle);
    }

    @Override // com.google.android.gms.internal.ads.zzegk
    public final zzfyx zza(zzfde zzfdeVar, zzfcs zzfcsVar) {
        String optString = zzfcsVar.zzw.optString(AbstractAdViewAdapter.AD_UNIT_ID_PARAMETER, "");
        zzfdn zzfdnVar = zzfdeVar.zza.zza;
        zzfdl zzfdlVar = new zzfdl();
        zzfdlVar.zzp(zzfdnVar);
        zzfdlVar.zzs(optString);
        Bundle zzd = zzd(zzfdnVar.zzd.zzm);
        Bundle zzd2 = zzd(zzd.getBundle("com.google.ads.mediation.admob.AdMobAdapter"));
        zzd2.putInt("gw", 1);
        String optString2 = zzfcsVar.zzw.optString("mad_hac", null);
        if (optString2 != null) {
            zzd2.putString("mad_hac", optString2);
        }
        String optString3 = zzfcsVar.zzw.optString("adJson", null);
        if (optString3 != null) {
            zzd2.putString("_ad", optString3);
        }
        zzd2.putBoolean("_noRefresh", true);
        Iterator<String> keys = zzfcsVar.zzE.keys();
        while (keys.hasNext()) {
            String next = keys.next();
            String optString4 = zzfcsVar.zzE.optString(next, null);
            if (next != null) {
                zzd2.putString(next, optString4);
            }
        }
        zzd.putBundle("com.google.ads.mediation.admob.AdMobAdapter", zzd2);
        com.google.android.gms.ads.internal.client.zzl zzlVar = zzfdnVar.zzd;
        zzfdlVar.zzE(new com.google.android.gms.ads.internal.client.zzl(zzlVar.zza, zzlVar.zzb, zzd2, zzlVar.zzd, zzlVar.zze, zzlVar.zzf, zzlVar.zzg, zzlVar.zzh, zzlVar.zzi, zzlVar.zzj, zzlVar.zzk, zzlVar.zzl, zzd, zzlVar.zzn, zzlVar.zzo, zzlVar.zzp, zzlVar.zzq, zzlVar.zzr, zzlVar.zzs, zzlVar.zzt, zzlVar.zzu, zzlVar.zzv, zzlVar.zzw, zzlVar.zzx));
        zzfdn zzG = zzfdlVar.zzG();
        Bundle bundle = new Bundle();
        zzfcv zzfcvVar = zzfdeVar.zzb.zzb;
        Bundle bundle2 = new Bundle();
        bundle2.putStringArrayList("nofill_urls", new ArrayList<>(zzfcvVar.zza));
        bundle2.putInt("refresh_interval", zzfcvVar.zzc);
        bundle2.putString("gws_query_id", zzfcvVar.zzb);
        bundle.putBundle("parent_common_config", bundle2);
        String str = zzfdeVar.zza.zza.zzf;
        Bundle bundle3 = new Bundle();
        bundle3.putString("initial_ad_unit_id", str);
        bundle3.putString("allocation_id", zzfcsVar.zzx);
        bundle3.putStringArrayList("click_urls", new ArrayList<>(zzfcsVar.zzc));
        bundle3.putStringArrayList("imp_urls", new ArrayList<>(zzfcsVar.zzd));
        bundle3.putStringArrayList("manual_tracking_urls", new ArrayList<>(zzfcsVar.zzq));
        bundle3.putStringArrayList("fill_urls", new ArrayList<>(zzfcsVar.zzn));
        bundle3.putStringArrayList("video_start_urls", new ArrayList<>(zzfcsVar.zzh));
        bundle3.putStringArrayList("video_reward_urls", new ArrayList<>(zzfcsVar.zzi));
        bundle3.putStringArrayList("video_complete_urls", new ArrayList<>(zzfcsVar.zzj));
        bundle3.putString("transaction_id", zzfcsVar.zzk);
        bundle3.putString("valid_from_timestamp", zzfcsVar.zzl);
        bundle3.putBoolean("is_closable_area_disabled", zzfcsVar.zzQ);
        if (zzfcsVar.zzm != null) {
            Bundle bundle4 = new Bundle();
            bundle4.putInt("rb_amount", zzfcsVar.zzm.zzb);
            bundle4.putString("rb_type", zzfcsVar.zzm.zza);
            bundle3.putParcelableArray("rewards", new Bundle[]{bundle4});
        }
        bundle.putBundle("parent_ad_config", bundle3);
        return zzc(zzG, bundle);
    }

    @Override // com.google.android.gms.internal.ads.zzegk
    public final boolean zzb(zzfde zzfdeVar, zzfcs zzfcsVar) {
        return !TextUtils.isEmpty(zzfcsVar.zzw.optString(AbstractAdViewAdapter.AD_UNIT_ID_PARAMETER, ""));
    }

    protected abstract zzfyx zzc(zzfdn zzfdnVar, Bundle bundle);
}
