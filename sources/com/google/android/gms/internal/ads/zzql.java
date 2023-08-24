package com.google.android.gms.internal.ads;

import android.graphics.Point;
import android.media.MediaCodecInfo;
import android.util.Log;
import android.util.Pair;
import com.google.android.exoplayer2.util.MimeTypes;
import java.util.Objects;

/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
public final class zzql {
    public final String zza;
    public final String zzb;
    public final String zzc;
    public final MediaCodecInfo.CodecCapabilities zzd;
    public final boolean zze;
    public final boolean zzf;
    public final boolean zzg;
    private final boolean zzh;

    public static zzql zzc(String str, String str2, String str3, MediaCodecInfo.CodecCapabilities codecCapabilities, boolean z, boolean z2, boolean z3, boolean z4, boolean z5) {
        boolean z6;
        if (codecCapabilities != null) {
            int r3 = zzel.zza;
            if (codecCapabilities.isFeatureSupported("adaptive-playback") && (zzel.zza > 22 || ((!"ODROID-XU3".equals(zzel.zzd) && !"Nexus 10".equals(zzel.zzd)) || (!"OMX.Exynos.AVC.Decoder".equals(str) && !"OMX.Exynos.AVC.Decoder.secure".equals(str))))) {
                z6 = true;
                return new zzql(str, str2, str3, codecCapabilities, z, z2, z3, z6, codecCapabilities == null && zzel.zza >= 21 && codecCapabilities.isFeatureSupported("tunneled-playback"), !z5 || (codecCapabilities != null && zzel.zza >= 21 && codecCapabilities.isFeatureSupported("secure-playback")));
            }
        }
        z6 = false;
        return new zzql(str, str2, str3, codecCapabilities, z, z2, z3, z6, codecCapabilities == null && zzel.zza >= 21 && codecCapabilities.isFeatureSupported("tunneled-playback"), !z5 || (codecCapabilities != null && zzel.zza >= 21 && codecCapabilities.isFeatureSupported("secure-playback")));
    }

    private static Point zzh(MediaCodecInfo.VideoCapabilities videoCapabilities, int r3, int r4) {
        int widthAlignment = videoCapabilities.getWidthAlignment();
        int heightAlignment = videoCapabilities.getHeightAlignment();
        return new Point(zzel.zze(r3, widthAlignment) * widthAlignment, zzel.zze(r4, heightAlignment) * heightAlignment);
    }

    private final void zzi(String str) {
        String str2 = this.zza;
        String str3 = this.zzb;
        String str4 = zzel.zze;
        Log.d(com.google.android.exoplayer2.mediacodec.MediaCodecInfo.TAG, "NoSupport [" + str + "] [" + str2 + ", " + str3 + "] [" + str4 + "]");
    }

    private static boolean zzj(MediaCodecInfo.VideoCapabilities videoCapabilities, int r4, int r5, double d) {
        Point zzh = zzh(videoCapabilities, r4, r5);
        int r52 = zzh.x;
        int r42 = zzh.y;
        if (d == -1.0d || d < 1.0d) {
            return videoCapabilities.isSizeSupported(r52, r42);
        }
        return videoCapabilities.areSizeAndRateSupported(r52, r42, Math.floor(d));
    }

    public final String toString() {
        return this.zza;
    }

    public final Point zza(int r3, int r4) {
        MediaCodecInfo.VideoCapabilities videoCapabilities;
        MediaCodecInfo.CodecCapabilities codecCapabilities = this.zzd;
        if (codecCapabilities == null || (videoCapabilities = codecCapabilities.getVideoCapabilities()) == null) {
            return null;
        }
        return zzh(videoCapabilities, r3, r4);
    }

    public final zzgr zzb(zzaf zzafVar, zzaf zzafVar2) {
        int r0 = true != zzel.zzT(zzafVar.zzm, zzafVar2.zzm) ? 8 : 0;
        if (this.zzh) {
            if (zzafVar.zzu != zzafVar2.zzu) {
                r0 |= 1024;
            }
            if (!this.zze && (zzafVar.zzr != zzafVar2.zzr || zzafVar.zzs != zzafVar2.zzs)) {
                r0 |= 512;
            }
            if (!zzel.zzT(zzafVar.zzy, zzafVar2.zzy)) {
                r0 |= 2048;
            }
            String str = this.zza;
            if (zzel.zzd.startsWith("SM-T230") && "OMX.MARVELL.VIDEO.HW.CODA7542DECODER".equals(str) && !zzafVar.zzd(zzafVar2)) {
                r0 |= 2;
            }
            if (r0 == 0) {
                return new zzgr(this.zza, zzafVar, zzafVar2, true != zzafVar.zzd(zzafVar2) ? 2 : 3, 0);
            }
        } else {
            if (zzafVar.zzz != zzafVar2.zzz) {
                r0 |= 4096;
            }
            if (zzafVar.zzA != zzafVar2.zzA) {
                r0 |= 8192;
            }
            if (zzafVar.zzB != zzafVar2.zzB) {
                r0 |= 16384;
            }
            if (r0 == 0 && MimeTypes.AUDIO_AAC.equals(this.zzb)) {
                Pair zzb = zzrd.zzb(zzafVar);
                Pair zzb2 = zzrd.zzb(zzafVar2);
                if (zzb != null && zzb2 != null) {
                    int intValue = ((Integer) zzb.first).intValue();
                    int intValue2 = ((Integer) zzb2.first).intValue();
                    if (intValue == 42 && intValue2 == 42) {
                        return new zzgr(this.zza, zzafVar, zzafVar2, 3, 0);
                    }
                }
            }
            if (!zzafVar.zzd(zzafVar2)) {
                r0 |= 32;
            }
            if (MimeTypes.AUDIO_OPUS.equals(this.zzb)) {
                r0 |= 2;
            }
            if (r0 == 0) {
                return new zzgr(this.zza, zzafVar, zzafVar2, 1, 0);
            }
        }
        return new zzgr(this.zza, zzafVar, zzafVar2, 0, r0);
    }

    public final boolean zzd(zzaf zzafVar) throws zzqx {
        Pair zzb;
        MediaCodecInfo.VideoCapabilities videoCapabilities;
        if (this.zzb.equals(zzafVar.zzm) || this.zzb.equals(zzrd.zze(zzafVar))) {
            int r2 = 16;
            if (zzafVar.zzj != null && (zzb = zzrd.zzb(zzafVar)) != null) {
                int intValue = ((Integer) zzb.first).intValue();
                int intValue2 = ((Integer) zzb.second).intValue();
                int r6 = 2;
                if (MimeTypes.VIDEO_DOLBY_VISION.equals(zzafVar.zzm)) {
                    if (MimeTypes.VIDEO_H264.equals(this.zzb)) {
                        intValue2 = 0;
                        intValue = 8;
                    } else if (MimeTypes.VIDEO_H265.equals(this.zzb)) {
                        intValue2 = 0;
                        intValue = 2;
                    }
                }
                if (!this.zzh) {
                    if (intValue == 42) {
                        intValue = 42;
                    }
                }
                MediaCodecInfo.CodecProfileLevel[] zzg = zzg();
                if (zzel.zza <= 23 && MimeTypes.VIDEO_VP9.equals(this.zzb) && zzg.length == 0) {
                    MediaCodecInfo.CodecCapabilities codecCapabilities = this.zzd;
                    int intValue3 = (codecCapabilities == null || (videoCapabilities = codecCapabilities.getVideoCapabilities()) == null) ? 0 : videoCapabilities.getBitrateRange().getUpper().intValue();
                    if (intValue3 >= 180000000) {
                        r6 = 1024;
                    } else if (intValue3 >= 120000000) {
                        r6 = 512;
                    } else if (intValue3 >= 60000000) {
                        r6 = 256;
                    } else if (intValue3 >= 30000000) {
                        r6 = 128;
                    } else if (intValue3 >= 18000000) {
                        r6 = 64;
                    } else if (intValue3 >= 12000000) {
                        r6 = 32;
                    } else if (intValue3 >= 7200000) {
                        r6 = 16;
                    } else if (intValue3 >= 3600000) {
                        r6 = 8;
                    } else if (intValue3 >= 1800000) {
                        r6 = 4;
                    } else if (intValue3 < 800000) {
                        r6 = 1;
                    }
                    MediaCodecInfo.CodecProfileLevel codecProfileLevel = new MediaCodecInfo.CodecProfileLevel();
                    codecProfileLevel.profile = 1;
                    codecProfileLevel.level = r6;
                    zzg = new MediaCodecInfo.CodecProfileLevel[]{codecProfileLevel};
                }
                for (MediaCodecInfo.CodecProfileLevel codecProfileLevel2 : zzg) {
                    if (codecProfileLevel2.profile != intValue || codecProfileLevel2.level < intValue2) {
                    }
                }
                zzi("codec.profileLevel, " + zzafVar.zzj + ", " + this.zzc);
                return false;
            }
            if (this.zzh) {
                if (zzafVar.zzr <= 0 || zzafVar.zzs <= 0) {
                    return true;
                }
                if (zzel.zza >= 21) {
                    return zzf(zzafVar.zzr, zzafVar.zzs, zzafVar.zzt);
                }
                boolean z = zzafVar.zzr * zzafVar.zzs <= zzrd.zza();
                if (!z) {
                    zzi("legacyFrameSize, " + zzafVar.zzr + "x" + zzafVar.zzs);
                }
                return z;
            }
            if (zzel.zza >= 21) {
                int r0 = zzafVar.zzA;
                if (r0 != -1) {
                    MediaCodecInfo.CodecCapabilities codecCapabilities2 = this.zzd;
                    if (codecCapabilities2 == null) {
                        zzi("sampleRate.caps");
                        return false;
                    }
                    MediaCodecInfo.AudioCapabilities audioCapabilities = codecCapabilities2.getAudioCapabilities();
                    if (audioCapabilities == null) {
                        zzi("sampleRate.aCaps");
                        return false;
                    } else if (!audioCapabilities.isSampleRateSupported(r0)) {
                        zzi("sampleRate.support, " + r0);
                        return false;
                    }
                }
                int r11 = zzafVar.zzz;
                if (r11 != -1) {
                    MediaCodecInfo.CodecCapabilities codecCapabilities3 = this.zzd;
                    if (codecCapabilities3 == null) {
                        zzi("channelCount.caps");
                    } else {
                        MediaCodecInfo.AudioCapabilities audioCapabilities2 = codecCapabilities3.getAudioCapabilities();
                        if (audioCapabilities2 == null) {
                            zzi("channelCount.aCaps");
                        } else {
                            String str = this.zza;
                            String str2 = this.zzb;
                            int maxInputChannelCount = audioCapabilities2.getMaxInputChannelCount();
                            if (maxInputChannelCount <= 1 && ((zzel.zza < 26 || maxInputChannelCount <= 0) && !MimeTypes.AUDIO_MPEG.equals(str2) && !MimeTypes.AUDIO_AMR_NB.equals(str2) && !MimeTypes.AUDIO_AMR_WB.equals(str2) && !MimeTypes.AUDIO_AAC.equals(str2) && !MimeTypes.AUDIO_VORBIS.equals(str2) && !MimeTypes.AUDIO_OPUS.equals(str2) && !MimeTypes.AUDIO_RAW.equals(str2) && !MimeTypes.AUDIO_FLAC.equals(str2) && !MimeTypes.AUDIO_ALAW.equals(str2) && !MimeTypes.AUDIO_MLAW.equals(str2) && !MimeTypes.AUDIO_MSGSM.equals(str2))) {
                                if (MimeTypes.AUDIO_AC3.equals(str2)) {
                                    r2 = 6;
                                } else if (!MimeTypes.AUDIO_E_AC3.equals(str2)) {
                                    r2 = 30;
                                }
                                Log.w(com.google.android.exoplayer2.mediacodec.MediaCodecInfo.TAG, "AssumedMaxChannelAdjustment: " + str + ", [" + maxInputChannelCount + " to " + r2 + "]");
                                maxInputChannelCount = r2;
                            }
                            if (maxInputChannelCount < r11) {
                                zzi("channelCount.support, " + r11);
                            }
                        }
                    }
                    return false;
                }
            }
            return true;
        }
        return false;
    }

    public final boolean zze(zzaf zzafVar) {
        if (this.zzh) {
            return this.zze;
        }
        Pair zzb = zzrd.zzb(zzafVar);
        return zzb != null && ((Integer) zzb.first).intValue() == 42;
    }

    public final boolean zzf(int r6, int r7, double d) {
        MediaCodecInfo.CodecCapabilities codecCapabilities = this.zzd;
        if (codecCapabilities == null) {
            zzi("sizeAndRate.caps");
            return false;
        }
        MediaCodecInfo.VideoCapabilities videoCapabilities = codecCapabilities.getVideoCapabilities();
        if (videoCapabilities == null) {
            zzi("sizeAndRate.vCaps");
            return false;
        } else if (zzj(videoCapabilities, r6, r7, d)) {
            return true;
        } else {
            if (r6 < r7 && ((!"OMX.MTK.VIDEO.DECODER.HEVC".equals(this.zza) || !"mcv5a".equals(zzel.zzb)) && zzj(videoCapabilities, r7, r6, d))) {
                String str = this.zza;
                String str2 = this.zzb;
                String str3 = zzel.zze;
                StringBuilder sb = new StringBuilder();
                sb.append("AssumedSupport [");
                sb.append("sizeAndRate.rotated, " + r6 + "x" + r7 + "x" + d);
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
            zzi("sizeAndRate.support, " + r6 + "x" + r7 + "x" + d);
            return false;
        }
    }

    public final MediaCodecInfo.CodecProfileLevel[] zzg() {
        MediaCodecInfo.CodecCapabilities codecCapabilities = this.zzd;
        return (codecCapabilities == null || codecCapabilities.profileLevels == null) ? new MediaCodecInfo.CodecProfileLevel[0] : this.zzd.profileLevels;
    }

    zzql(String str, String str2, String str3, MediaCodecInfo.CodecCapabilities codecCapabilities, boolean z, boolean z2, boolean z3, boolean z4, boolean z5, boolean z6) {
        Objects.requireNonNull(str);
        this.zza = str;
        this.zzb = str2;
        this.zzc = str3;
        this.zzd = codecCapabilities;
        this.zzg = z;
        this.zze = z4;
        this.zzf = z6;
        this.zzh = zzbt.zzh(str2);
    }
}
