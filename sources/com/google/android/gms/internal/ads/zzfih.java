package com.google.android.gms.internal.ads;

import android.content.Context;
import android.os.Build;
import android.text.TextUtils;
import androidx.exifinterface.media.ExifInterface;
import com.google.android.exoplayer2.metadata.icy.IcyHeaders;
import com.google.android.exoplayer2.source.rtsp.SessionDescription;
import com.google.android.gms.common.util.DeviceProperties;
import expo.modules.updates.UpdatesConfiguration;
import java.util.List;
import java.util.Map;

/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
public final class zzfih {
    private final Context zza;
    private final String zzb;
    private final String zzc;

    public zzfih(Context context, zzcgt zzcgtVar) {
        this.zza = context;
        this.zzb = context.getPackageName();
        this.zzc = zzcgtVar.zza;
    }

    public final void zza(Map map) {
        map.put("s", "gmob_sdk");
        map.put("v", ExifInterface.GPS_MEASUREMENT_3D);
        map.put("os", Build.VERSION.RELEASE);
        map.put("api_v", Build.VERSION.SDK);
        com.google.android.gms.ads.internal.zzt.zzq();
        map.put("device", com.google.android.gms.ads.internal.util.zzs.zzq());
        map.put("app", this.zzb);
        com.google.android.gms.ads.internal.zzt.zzq();
        boolean zzA = com.google.android.gms.ads.internal.util.zzs.zzA(this.zza);
        String str = SessionDescription.SUPPORTED_SDP_VERSION;
        map.put("is_lite_sdk", true != zzA ? SessionDescription.SUPPORTED_SDP_VERSION : IcyHeaders.REQUEST_HEADER_ENABLE_METADATA_VALUE);
        List zzb = zzbiy.zzb();
        if (((Boolean) com.google.android.gms.ads.internal.client.zzay.zzc().zzb(zzbiy.zzfU)).booleanValue()) {
            zzb.addAll(com.google.android.gms.ads.internal.zzt.zzp().zzh().zzh().zzd());
        }
        map.put("e", TextUtils.join(",", zzb));
        map.put(UpdatesConfiguration.UPDATES_CONFIGURATION_SDK_VERSION_KEY, this.zzc);
        if (((Boolean) com.google.android.gms.ads.internal.client.zzay.zzc().zzb(zzbiy.zziJ)).booleanValue()) {
            if (true == DeviceProperties.isBstar(this.zza)) {
                str = IcyHeaders.REQUEST_HEADER_ENABLE_METADATA_VALUE;
            }
            map.put("is_bstar", str);
        }
    }
}
