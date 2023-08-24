package com.google.android.gms.internal.ads;

import android.os.Bundle;
import android.text.TextUtils;
import androidx.core.p005os.EnvironmentCompat;
import com.facebook.hermes.intl.Constants;
import com.google.android.exoplayer2.metadata.icy.IcyHeaders;
import com.google.android.exoplayer2.source.rtsp.SessionDescription;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
@Deprecated
/* loaded from: classes2.dex */
public final class zzdxj {
    private final ConcurrentHashMap zza;
    private final zzcga zzb;
    private final zzfdn zzc;
    private final String zzd;
    private final String zze;

    public zzdxj(zzdxt zzdxtVar, zzcga zzcgaVar, zzfdn zzfdnVar, String str, String str2) {
        ConcurrentHashMap zzc = zzdxtVar.zzc();
        this.zza = zzc;
        this.zzb = zzcgaVar;
        this.zzc = zzfdnVar;
        this.zzd = str;
        this.zze = str2;
        if (((Boolean) com.google.android.gms.ads.internal.client.zzay.zzc().zzb(zzbiy.zzga)).booleanValue()) {
            int zzd = com.google.android.gms.ads.nonagon.signalgeneration.zzf.zzd(zzfdnVar);
            int r0 = zzd - 1;
            if (r0 != 0) {
                if (r0 == 1) {
                    zzc.put("se", "query_g");
                } else if (r0 == 2) {
                    zzc.put("se", "r_adinfo");
                } else if (r0 != 3) {
                    zzc.put("se", "r_both");
                } else {
                    zzc.put("se", "r_adstring");
                }
                zzc.put("scar", "true");
                if (((Boolean) com.google.android.gms.ads.internal.client.zzay.zzc().zzb(zzbiy.zzgA)).booleanValue()) {
                    zzc.put("ad_format", str2);
                }
                if (zzd == 2) {
                    zzc.put("rid", str);
                }
                zzd("ragent", zzfdnVar.zzd.zzp);
                zzd("rtype", com.google.android.gms.ads.nonagon.signalgeneration.zzf.zza(com.google.android.gms.ads.nonagon.signalgeneration.zzf.zzb(zzfdnVar.zzd)));
                return;
            }
            zzc.put("scar", Constants.CASEFIRST_FALSE);
        }
    }

    private final void zzd(String str, String str2) {
        if (TextUtils.isEmpty(str2)) {
            return;
        }
        this.zza.put(str, str2);
    }

    public final Map zza() {
        return this.zza;
    }

    public final void zzb(zzfde zzfdeVar) {
        if (zzfdeVar.zzb.zza.size() > 0) {
            switch (((zzfcs) zzfdeVar.zzb.zza.get(0)).zzb) {
                case 1:
                    this.zza.put("ad_format", "banner");
                    break;
                case 2:
                    this.zza.put("ad_format", "interstitial");
                    break;
                case 3:
                    this.zza.put("ad_format", "native_express");
                    break;
                case 4:
                    this.zza.put("ad_format", "native_advanced");
                    break;
                case 5:
                    this.zza.put("ad_format", "rewarded");
                    break;
                case 6:
                    this.zza.put("ad_format", "app_open_ad");
                    this.zza.put("as", true != this.zzb.zzj() ? SessionDescription.SUPPORTED_SDP_VERSION : IcyHeaders.REQUEST_HEADER_ENABLE_METADATA_VALUE);
                    break;
                default:
                    this.zza.put("ad_format", EnvironmentCompat.MEDIA_UNKNOWN);
                    break;
            }
        }
        zzd("gqi", zzfdeVar.zzb.zzb.zzb);
    }

    public final void zzc(Bundle bundle) {
        if (bundle.containsKey("cnt")) {
            this.zza.put("network_coarse", Integer.toString(bundle.getInt("cnt")));
        }
        if (bundle.containsKey("gnt")) {
            this.zza.put("network_fine", Integer.toString(bundle.getInt("gnt")));
        }
    }
}
