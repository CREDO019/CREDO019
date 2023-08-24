package com.google.android.gms.internal.ads;

import android.media.MediaCodecInfo;
import android.media.MediaCodecList;
import com.google.android.exoplayer2.util.MimeTypes;

/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
final class zzra implements zzqy {
    private zzra() {
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public /* synthetic */ zzra(zzqz zzqzVar) {
    }

    @Override // com.google.android.gms.internal.ads.zzqy
    public final int zza() {
        return MediaCodecList.getCodecCount();
    }

    @Override // com.google.android.gms.internal.ads.zzqy
    public final MediaCodecInfo zzb(int r1) {
        return MediaCodecList.getCodecInfoAt(r1);
    }

    @Override // com.google.android.gms.internal.ads.zzqy
    public final boolean zzc(String str, String str2, MediaCodecInfo.CodecCapabilities codecCapabilities) {
        return false;
    }

    @Override // com.google.android.gms.internal.ads.zzqy
    public final boolean zzd(String str, String str2, MediaCodecInfo.CodecCapabilities codecCapabilities) {
        return "secure-playback".equals(str) && MimeTypes.VIDEO_H264.equals(str2);
    }

    @Override // com.google.android.gms.internal.ads.zzqy
    public final boolean zze() {
        return false;
    }
}
