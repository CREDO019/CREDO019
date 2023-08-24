package com.google.android.gms.internal.ads;

import android.os.Bundle;
import android.text.TextUtils;
import androidx.core.p005os.EnvironmentCompat;
import com.google.android.exoplayer2.metadata.icy.IcyHeaders;
import com.google.android.exoplayer2.source.rtsp.SessionDescription;
import java.util.HashMap;
import java.util.Map;

/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
public final class zzfhy {
    private final HashMap zza;
    private final zzfie zzb;

    private zzfhy() {
        HashMap hashMap = new HashMap();
        this.zza = hashMap;
        this.zzb = new zzfie(com.google.android.gms.ads.internal.zzt.zzB());
        hashMap.put("new_csi", IcyHeaders.REQUEST_HEADER_ENABLE_METADATA_VALUE);
    }

    public static zzfhy zzb(String str) {
        zzfhy zzfhyVar = new zzfhy();
        zzfhyVar.zza.put("action", str);
        return zzfhyVar;
    }

    public static zzfhy zzc(String str) {
        zzfhy zzfhyVar = new zzfhy();
        zzfhyVar.zza.put("request_id", str);
        return zzfhyVar;
    }

    public final zzfhy zza(String str, String str2) {
        this.zza.put(str, str2);
        return this;
    }

    public final zzfhy zzd(String str) {
        this.zzb.zzb(str);
        return this;
    }

    public final zzfhy zze(String str, String str2) {
        this.zzb.zzc(str, str2);
        return this;
    }

    public final zzfhy zzf(zzfcs zzfcsVar) {
        this.zza.put("aai", zzfcsVar.zzx);
        return this;
    }

    public final zzfhy zzg(zzfcv zzfcvVar) {
        if (!TextUtils.isEmpty(zzfcvVar.zzb)) {
            this.zza.put("gqi", zzfcvVar.zzb);
        }
        return this;
    }

    public final zzfhy zzh(zzfde zzfdeVar, zzcga zzcgaVar) {
        zzfdd zzfddVar = zzfdeVar.zzb;
        zzg(zzfddVar.zzb);
        if (!zzfddVar.zza.isEmpty()) {
            switch (((zzfcs) zzfddVar.zza.get(0)).zzb) {
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
                    if (zzcgaVar != null) {
                        this.zza.put("as", true != zzcgaVar.zzj() ? SessionDescription.SUPPORTED_SDP_VERSION : IcyHeaders.REQUEST_HEADER_ENABLE_METADATA_VALUE);
                        break;
                    }
                    break;
                default:
                    this.zza.put("ad_format", EnvironmentCompat.MEDIA_UNKNOWN);
                    break;
            }
        }
        return this;
    }

    public final zzfhy zzi(Bundle bundle) {
        if (bundle.containsKey("cnt")) {
            this.zza.put("network_coarse", Integer.toString(bundle.getInt("cnt")));
        }
        if (bundle.containsKey("gnt")) {
            this.zza.put("network_fine", Integer.toString(bundle.getInt("gnt")));
        }
        return this;
    }

    public final Map zzj() {
        HashMap hashMap = new HashMap(this.zza);
        for (zzfid zzfidVar : this.zzb.zza()) {
            hashMap.put(zzfidVar.zza, zzfidVar.zzb);
        }
        return hashMap;
    }
}
