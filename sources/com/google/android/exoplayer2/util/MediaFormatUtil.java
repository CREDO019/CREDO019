package com.google.android.exoplayer2.util;

import android.media.MediaFormat;
import com.amplitude.api.Constants;
import com.google.android.exoplayer2.Format;
import com.google.android.exoplayer2.video.ColorInfo;
import java.nio.ByteBuffer;
import java.util.List;

/* loaded from: classes2.dex */
public final class MediaFormatUtil {
    public static final String KEY_MAX_BIT_RATE = "max-bitrate";
    public static final String KEY_PCM_ENCODING_EXTENDED = "exo-pcm-encoding-int";
    public static final String KEY_PIXEL_WIDTH_HEIGHT_RATIO_FLOAT = "exo-pixel-width-height-ratio-float";
    private static final int MAX_POWER_OF_TWO_INT = 1073741824;

    public static MediaFormat createMediaFormatFromFormat(Format format) {
        MediaFormat mediaFormat = new MediaFormat();
        maybeSetInteger(mediaFormat, "bitrate", format.bitrate);
        maybeSetInteger(mediaFormat, KEY_MAX_BIT_RATE, format.peakBitrate);
        maybeSetInteger(mediaFormat, "channel-count", format.channelCount);
        maybeSetColorInfo(mediaFormat, format.colorInfo);
        maybeSetString(mediaFormat, "mime", format.sampleMimeType);
        maybeSetString(mediaFormat, "codecs-string", format.codecs);
        maybeSetFloat(mediaFormat, "frame-rate", format.frameRate);
        maybeSetInteger(mediaFormat, "width", format.width);
        maybeSetInteger(mediaFormat, "height", format.height);
        setCsdBuffers(mediaFormat, format.initializationData);
        maybeSetPcmEncoding(mediaFormat, format.pcmEncoding);
        maybeSetString(mediaFormat, Constants.AMP_TRACKING_OPTION_LANGUAGE, format.language);
        maybeSetInteger(mediaFormat, "max-input-size", format.maxInputSize);
        maybeSetInteger(mediaFormat, "sample-rate", format.sampleRate);
        maybeSetInteger(mediaFormat, "caption-service-number", format.accessibilityChannel);
        mediaFormat.setInteger("rotation-degrees", format.rotationDegrees);
        int r1 = format.selectionFlags;
        setBooleanAsInt(mediaFormat, "is-autoselect", r1 & 4);
        setBooleanAsInt(mediaFormat, "is-default", r1 & 1);
        setBooleanAsInt(mediaFormat, "is-forced-subtitle", r1 & 2);
        mediaFormat.setInteger("encoder-delay", format.encoderDelay);
        mediaFormat.setInteger("encoder-padding", format.encoderPadding);
        maybeSetPixelAspectRatio(mediaFormat, format.pixelWidthHeightRatio);
        return mediaFormat;
    }

    public static void maybeSetString(MediaFormat mediaFormat, String str, String str2) {
        if (str2 != null) {
            mediaFormat.setString(str, str2);
        }
    }

    public static void setCsdBuffers(MediaFormat mediaFormat, List<byte[]> list) {
        for (int r0 = 0; r0 < list.size(); r0++) {
            mediaFormat.setByteBuffer("csd-" + r0, ByteBuffer.wrap(list.get(r0)));
        }
    }

    public static void maybeSetInteger(MediaFormat mediaFormat, String str, int r3) {
        if (r3 != -1) {
            mediaFormat.setInteger(str, r3);
        }
    }

    public static void maybeSetFloat(MediaFormat mediaFormat, String str, float f) {
        if (f != -1.0f) {
            mediaFormat.setFloat(str, f);
        }
    }

    public static void maybeSetByteBuffer(MediaFormat mediaFormat, String str, byte[] bArr) {
        if (bArr != null) {
            mediaFormat.setByteBuffer(str, ByteBuffer.wrap(bArr));
        }
    }

    public static void maybeSetColorInfo(MediaFormat mediaFormat, ColorInfo colorInfo) {
        if (colorInfo != null) {
            maybeSetInteger(mediaFormat, "color-transfer", colorInfo.colorTransfer);
            maybeSetInteger(mediaFormat, "color-standard", colorInfo.colorSpace);
            maybeSetInteger(mediaFormat, "color-range", colorInfo.colorRange);
            maybeSetByteBuffer(mediaFormat, "hdr-static-info", colorInfo.hdrStaticInfo);
        }
    }

    private static void setBooleanAsInt(MediaFormat mediaFormat, String str, int r2) {
        mediaFormat.setInteger(str, r2 != 0 ? 1 : 0);
    }

    private static void maybeSetPixelAspectRatio(MediaFormat mediaFormat, float f) {
        int r5;
        mediaFormat.setFloat(KEY_PIXEL_WIDTH_HEIGHT_RATIO_FLOAT, f);
        int r0 = 1073741824;
        if (f < 1.0f) {
            r0 = (int) (f * 1073741824);
            r5 = 1073741824;
        } else if (f > 1.0f) {
            r5 = (int) (1073741824 / f);
        } else {
            r5 = 1;
            r0 = 1;
        }
        mediaFormat.setInteger("sar-width", r0);
        mediaFormat.setInteger("sar-height", r5);
    }

    private static void maybeSetPcmEncoding(MediaFormat mediaFormat, int r5) {
        if (r5 == -1) {
            return;
        }
        maybeSetInteger(mediaFormat, KEY_PCM_ENCODING_EXTENDED, r5);
        int r0 = 4;
        if (r5 == 0) {
            r0 = 0;
        } else if (r5 == 536870912) {
            r0 = 21;
        } else if (r5 == 805306368) {
            r0 = 22;
        } else if (r5 == 2) {
            r0 = 2;
        } else if (r5 == 3) {
            r0 = 3;
        } else if (r5 != 4) {
            return;
        }
        mediaFormat.setInteger("pcm-encoding", r0);
    }

    private MediaFormatUtil() {
    }
}
