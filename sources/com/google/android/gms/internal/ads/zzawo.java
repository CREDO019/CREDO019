package com.google.android.gms.internal.ads;

import android.media.MediaCodecInfo;
import android.util.Log;
import android.util.Pair;
import com.google.android.exoplayer2.util.MimeTypes;
import java.util.Objects;

/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
public final class zzawo {
    public final String zza;
    public final boolean zzb;
    public final boolean zzc;
    public final boolean zzd;
    private final String zze;
    private final MediaCodecInfo.CodecCapabilities zzf;

    private zzawo(String str, String str2, MediaCodecInfo.CodecCapabilities codecCapabilities, boolean z, boolean z2) {
        Objects.requireNonNull(str);
        this.zza = str;
        this.zze = str2;
        this.zzf = codecCapabilities;
        boolean z3 = true;
        this.zzb = !z && codecCapabilities != null && zzban.zza >= 19 && codecCapabilities.isFeatureSupported("adaptive-playback");
        this.zzc = codecCapabilities != null && zzban.zza >= 21 && codecCapabilities.isFeatureSupported("tunneled-playback");
        if (!z2 && (codecCapabilities == null || zzban.zza < 21 || !codecCapabilities.isFeatureSupported("secure-playback"))) {
            z3 = false;
        }
        this.zzd = z3;
    }

    public static zzawo zza(String str, String str2, MediaCodecInfo.CodecCapabilities codecCapabilities, boolean z, boolean z2) {
        return new zzawo(str, str2, codecCapabilities, z, z2);
    }

    public static zzawo zzb(String str) {
        return new zzawo("OMX.google.raw.decoder", null, null, false, false);
    }

    private final void zzh(String str) {
        String str2 = this.zza;
        String str3 = this.zze;
        String str4 = zzban.zze;
        Log.d(com.google.android.exoplayer2.mediacodec.MediaCodecInfo.TAG, "NoSupport [" + str + "] [" + str2 + ", " + str3 + "] [" + str4 + "]");
    }

    public final boolean zzc(int r4) {
        MediaCodecInfo.CodecCapabilities codecCapabilities = this.zzf;
        if (codecCapabilities == null) {
            zzh("channelCount.caps");
            return false;
        }
        MediaCodecInfo.AudioCapabilities audioCapabilities = codecCapabilities.getAudioCapabilities();
        if (audioCapabilities == null) {
            zzh("channelCount.aCaps");
            return false;
        } else if (audioCapabilities.getMaxInputChannelCount() < r4) {
            zzh("channelCount.support, " + r4);
            return false;
        } else {
            return true;
        }
    }

    public final boolean zzd(int r4) {
        MediaCodecInfo.CodecCapabilities codecCapabilities = this.zzf;
        if (codecCapabilities == null) {
            zzh("sampleRate.caps");
            return false;
        }
        MediaCodecInfo.AudioCapabilities audioCapabilities = codecCapabilities.getAudioCapabilities();
        if (audioCapabilities == null) {
            zzh("sampleRate.aCaps");
            return false;
        } else if (audioCapabilities.isSampleRateSupported(r4)) {
            return true;
        } else {
            zzh("sampleRate.support, " + r4);
            return false;
        }
    }

    public final boolean zze(String str) {
        String str2;
        MediaCodecInfo.CodecProfileLevel[] zzg;
        if (str == null || this.zze == null) {
            return true;
        }
        String trim = str.trim();
        if (trim.startsWith("avc1") || trim.startsWith("avc3")) {
            str2 = MimeTypes.VIDEO_H264;
        } else if (trim.startsWith("hev1") || trim.startsWith("hvc1")) {
            str2 = MimeTypes.VIDEO_H265;
        } else if (trim.startsWith("vp9")) {
            str2 = MimeTypes.VIDEO_VP9;
        } else if (trim.startsWith("vp8")) {
            str2 = MimeTypes.VIDEO_VP8;
        } else if (trim.startsWith("mp4a")) {
            str2 = MimeTypes.AUDIO_AAC;
        } else if (trim.startsWith("ac-3") || trim.startsWith("dac3")) {
            str2 = MimeTypes.AUDIO_AC3;
        } else if (trim.startsWith("ec-3") || trim.startsWith("dec3")) {
            str2 = MimeTypes.AUDIO_E_AC3;
        } else if (trim.startsWith("dtsc") || trim.startsWith("dtse")) {
            str2 = MimeTypes.AUDIO_DTS;
        } else if (trim.startsWith("dtsh") || trim.startsWith("dtsl")) {
            str2 = MimeTypes.AUDIO_DTS_HD;
        } else if (trim.startsWith("opus")) {
            str2 = MimeTypes.AUDIO_OPUS;
        } else {
            str2 = trim.startsWith("vorbis") ? MimeTypes.AUDIO_VORBIS : null;
        }
        if (str2 == null) {
            return true;
        }
        if (!this.zze.equals(str2)) {
            zzh("codec.mime " + str + ", " + str2);
            return false;
        }
        Pair zzb = zzaxa.zzb(str);
        if (zzb == null) {
            return true;
        }
        for (MediaCodecInfo.CodecProfileLevel codecProfileLevel : zzg()) {
            if (codecProfileLevel.profile == ((Integer) zzb.first).intValue() && codecProfileLevel.level >= ((Integer) zzb.second).intValue()) {
                return true;
            }
        }
        zzh("codec.profileLevel, " + str + ", " + str2);
        return false;
    }

    public final boolean zzf(int r5, int r6, double d) {
        MediaCodecInfo.CodecCapabilities codecCapabilities = this.zzf;
        if (codecCapabilities == null) {
            zzh("sizeAndRate.caps");
            return false;
        }
        MediaCodecInfo.VideoCapabilities videoCapabilities = codecCapabilities.getVideoCapabilities();
        if (videoCapabilities == null) {
            zzh("sizeAndRate.vCaps");
            return false;
        } else if (zzi(videoCapabilities, r5, r6, d)) {
            return true;
        } else {
            if (r5 >= r6 || !zzi(videoCapabilities, r6, r5, d)) {
                zzh("sizeAndRate.support, " + r5 + "x" + r6 + "x" + d);
                return false;
            }
            String str = this.zza;
            String str2 = this.zze;
            String str3 = zzban.zze;
            StringBuilder sb = new StringBuilder();
            sb.append("AssumedSupport [");
            sb.append("sizeAndRate.rotated, " + r5 + "x" + r6 + "x" + d);
            sb.append("] [");
            sb.append(str);
            sb.append(", ");
            sb.append(str2);
            sb.append("] [");
            sb.append(str3);
            sb.append("]");
            Log.d(com.google.android.exoplayer2.mediacodec.MediaCodecInfo.TAG, sb.toString());
            return true;
        }
    }

    public final MediaCodecInfo.CodecProfileLevel[] zzg() {
        MediaCodecInfo.CodecCapabilities codecCapabilities = this.zzf;
        return (codecCapabilities == null || codecCapabilities.profileLevels == null) ? new MediaCodecInfo.CodecProfileLevel[0] : this.zzf.profileLevels;
    }

    private static boolean zzi(MediaCodecInfo.VideoCapabilities videoCapabilities, int r4, int r5, double d) {
        if (d == -1.0d || d <= 0.0d) {
            return videoCapabilities.isSizeSupported(r4, r5);
        }
        return videoCapabilities.areSizeAndRateSupported(r4, r5, d);
    }
}
