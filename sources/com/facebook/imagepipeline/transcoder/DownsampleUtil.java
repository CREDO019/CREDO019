package com.facebook.imagepipeline.transcoder;

import com.facebook.common.internal.Preconditions;
import com.facebook.common.logging.FLog;
import com.facebook.imageformat.DefaultImageFormats;
import com.facebook.imagepipeline.common.ResizeOptions;
import com.facebook.imagepipeline.common.RotationOptions;
import com.facebook.imagepipeline.image.EncodedImage;
import javax.annotation.Nullable;

/* loaded from: classes.dex */
public class DownsampleUtil {
    public static final int DEFAULT_SAMPLE_SIZE = 1;
    private static final float INTERVAL_ROUNDING = 0.33333334f;

    public static int ratioToSampleSizeJPEG(final float ratio) {
        if (ratio > 0.6666667f) {
            return 1;
        }
        int r0 = 2;
        while (true) {
            int r3 = r0 * 2;
            double d = 1.0d / r3;
            if (d + (0.3333333432674408d * d) <= ratio) {
                return r0;
            }
            r0 = r3;
        }
    }

    public static int roundToPowerOfTwo(final int sampleSize) {
        int r0 = 1;
        while (r0 < sampleSize) {
            r0 *= 2;
        }
        return r0;
    }

    private DownsampleUtil() {
    }

    public static int determineSampleSize(final RotationOptions rotationOptions, @Nullable final ResizeOptions resizeOptions, final EncodedImage encodedImage, final int maxBitmapSize) {
        int ratioToSampleSize;
        if (EncodedImage.isMetaDataAvailable(encodedImage)) {
            float determineDownsampleRatio = determineDownsampleRatio(rotationOptions, resizeOptions, encodedImage);
            if (encodedImage.getImageFormat() == DefaultImageFormats.JPEG) {
                ratioToSampleSize = ratioToSampleSizeJPEG(determineDownsampleRatio);
            } else {
                ratioToSampleSize = ratioToSampleSize(determineDownsampleRatio);
            }
            int max = Math.max(encodedImage.getHeight(), encodedImage.getWidth());
            float f = resizeOptions != null ? resizeOptions.maxBitmapSize : maxBitmapSize;
            while (max / ratioToSampleSize > f) {
                ratioToSampleSize = encodedImage.getImageFormat() == DefaultImageFormats.JPEG ? ratioToSampleSize * 2 : ratioToSampleSize + 1;
            }
            return ratioToSampleSize;
        }
        return 1;
    }

    public static int determineSampleSizeJPEG(final EncodedImage encodedImage, final int pixelSize, final int maxBitmapSizeInBytes) {
        int sampleSize = encodedImage.getSampleSize();
        while ((((encodedImage.getWidth() * encodedImage.getHeight()) * pixelSize) / sampleSize) / sampleSize > maxBitmapSizeInBytes) {
            sampleSize *= 2;
        }
        return sampleSize;
    }

    public static float determineDownsampleRatio(final RotationOptions rotationOptions, @Nullable final ResizeOptions resizeOptions, final EncodedImage encodedImage) {
        Preconditions.checkArgument(Boolean.valueOf(EncodedImage.isMetaDataAvailable(encodedImage)));
        if (resizeOptions == null || resizeOptions.height <= 0 || resizeOptions.width <= 0 || encodedImage.getWidth() == 0 || encodedImage.getHeight() == 0) {
            return 1.0f;
        }
        int rotationAngle = getRotationAngle(rotationOptions, encodedImage);
        boolean z = rotationAngle == 90 || rotationAngle == 270;
        int height = z ? encodedImage.getHeight() : encodedImage.getWidth();
        int width = z ? encodedImage.getWidth() : encodedImage.getHeight();
        float f = resizeOptions.width / height;
        float f2 = resizeOptions.height / width;
        float max = Math.max(f, f2);
        FLog.m1294v("DownsampleUtil", "Downsample - Specified size: %dx%d, image size: %dx%d ratio: %.1f x %.1f, ratio: %.3f", Integer.valueOf(resizeOptions.width), Integer.valueOf(resizeOptions.height), Integer.valueOf(height), Integer.valueOf(width), Float.valueOf(f), Float.valueOf(f2), Float.valueOf(max));
        return max;
    }

    public static int ratioToSampleSize(final float ratio) {
        if (ratio > 0.6666667f) {
            return 1;
        }
        int r1 = 2;
        while (true) {
            double d = r1;
            if ((1.0d / d) + ((1.0d / (Math.pow(d, 2.0d) - d)) * 0.3333333432674408d) <= ratio) {
                return r1 - 1;
            }
            r1++;
        }
    }

    private static int getRotationAngle(final RotationOptions rotationOptions, final EncodedImage encodedImage) {
        boolean z = false;
        if (rotationOptions.useImageMetadata()) {
            int rotationAngle = encodedImage.getRotationAngle();
            Preconditions.checkArgument(Boolean.valueOf((rotationAngle == 0 || rotationAngle == 90 || rotationAngle == 180 || rotationAngle == 270) ? true : true));
            return rotationAngle;
        }
        return 0;
    }
}
