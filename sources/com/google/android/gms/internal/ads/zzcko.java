package com.google.android.gms.internal.ads;

import android.text.TextUtils;
import com.facebook.react.uimanager.ViewProps;
import com.google.android.exoplayer2.metadata.icy.IcyHeaders;
import java.util.Map;

/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
public final class zzcko implements zzbpq {
    @Override // com.google.android.gms.internal.ads.zzbpq
    public final /* bridge */ /* synthetic */ void zza(Object obj, Map map) {
        zzciw zzciwVar = (zzciw) obj;
        zzcnj zzs = zzciwVar.zzs();
        if (zzs == null) {
            try {
                zzcnj zzcnjVar = new zzcnj(zzciwVar, Float.parseFloat((String) map.get("duration")), IcyHeaders.REQUEST_HEADER_ENABLE_METADATA_VALUE.equals(map.get("customControlsAllowed")), IcyHeaders.REQUEST_HEADER_ENABLE_METADATA_VALUE.equals(map.get("clickToExpandAllowed")));
                zzciwVar.zzE(zzcnjVar);
                zzs = zzcnjVar;
            } catch (NullPointerException e) {
                e = e;
                com.google.android.gms.ads.internal.util.zze.zzh("Unable to parse videoMeta message.", e);
                com.google.android.gms.ads.internal.zzt.zzp().zzt(e, "VideoMetaGmsgHandler.onGmsg");
                return;
            } catch (NumberFormatException e2) {
                e = e2;
                com.google.android.gms.ads.internal.util.zze.zzh("Unable to parse videoMeta message.", e);
                com.google.android.gms.ads.internal.zzt.zzp().zzt(e, "VideoMetaGmsgHandler.onGmsg");
                return;
            }
        }
        float parseFloat = Float.parseFloat((String) map.get("duration"));
        boolean equals = IcyHeaders.REQUEST_HEADER_ENABLE_METADATA_VALUE.equals(map.get("muted"));
        float parseFloat2 = Float.parseFloat((String) map.get("currentTime"));
        int parseInt = Integer.parseInt((String) map.get("playbackState"));
        int r5 = 0;
        if (parseInt >= 0 && parseInt <= 3) {
            r5 = parseInt;
        }
        String str = (String) map.get(ViewProps.ASPECT_RATIO);
        float parseFloat3 = TextUtils.isEmpty(str) ? 0.0f : Float.parseFloat(str);
        if (com.google.android.gms.ads.internal.util.zze.zzm(3)) {
            com.google.android.gms.ads.internal.util.zze.zze("Video Meta GMSG: currentTime : " + parseFloat2 + " , duration : " + parseFloat + " , isMuted : " + equals + " , playbackState : " + r5 + " , aspectRatio : " + str);
        }
        zzs.zzc(parseFloat2, parseFloat, r5, equals, parseFloat3);
    }
}
