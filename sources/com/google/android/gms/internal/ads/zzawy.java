package com.google.android.gms.internal.ads;

import android.media.MediaCodecInfo;
import android.media.MediaCodecList;
import com.google.android.exoplayer2.util.MimeTypes;

/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
final class zzawy implements zzaww {
    private zzawy() {
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public /* synthetic */ zzawy(zzawx zzawxVar) {
    }

    @Override // com.google.android.gms.internal.ads.zzaww
    public final int zza() {
        return MediaCodecList.getCodecCount();
    }

    @Override // com.google.android.gms.internal.ads.zzaww
    public final MediaCodecInfo zzb(int r1) {
        return MediaCodecList.getCodecInfoAt(r1);
    }

    @Override // com.google.android.gms.internal.ads.zzaww
    public final boolean zzc(String str, MediaCodecInfo.CodecCapabilities codecCapabilities) {
        return MimeTypes.VIDEO_H264.equals(str);
    }

    @Override // com.google.android.gms.internal.ads.zzaww
    public final boolean zzd() {
        return false;
    }
}
