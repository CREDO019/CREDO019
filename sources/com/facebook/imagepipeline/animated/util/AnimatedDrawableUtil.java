package com.facebook.imagepipeline.animated.util;

import android.graphics.Bitmap;
import android.os.Build;
import java.util.Arrays;

/* loaded from: classes.dex */
public class AnimatedDrawableUtil {
    private static final int FRAME_DURATION_MS_FOR_MIN = 100;
    private static final int MIN_FRAME_DURATION_MS = 11;

    public static boolean isOutsideRange(int startFrame, int endFrame, int frameNumber) {
        if (startFrame == -1 || endFrame == -1) {
            return true;
        }
        if (startFrame <= endFrame) {
            if (frameNumber < startFrame || frameNumber > endFrame) {
                return true;
            }
        } else if (frameNumber < startFrame && frameNumber > endFrame) {
            return true;
        }
        return false;
    }

    public void fixFrameDurations(int[] frameDurationMs) {
        for (int r0 = 0; r0 < frameDurationMs.length; r0++) {
            if (frameDurationMs[r0] < 11) {
                frameDurationMs[r0] = 100;
            }
        }
    }

    public int getTotalDurationFromFrameDurations(int[] frameDurationMs) {
        int r1 = 0;
        for (int r2 : frameDurationMs) {
            r1 += r2;
        }
        return r1;
    }

    public int[] getFrameTimeStampsFromDurations(int[] frameDurationsMs) {
        int[] r0 = new int[frameDurationsMs.length];
        int r2 = 0;
        for (int r1 = 0; r1 < frameDurationsMs.length; r1++) {
            r0[r1] = r2;
            r2 += frameDurationsMs[r1];
        }
        return r0;
    }

    public int getFrameForTimestampMs(int[] frameTimestampsMs, int timestampMs) {
        int binarySearch = Arrays.binarySearch(frameTimestampsMs, timestampMs);
        return binarySearch < 0 ? ((-binarySearch) - 1) - 1 : binarySearch;
    }

    public int getSizeOfBitmap(Bitmap bitmap) {
        if (Build.VERSION.SDK_INT >= 19) {
            return bitmap.getAllocationByteCount();
        }
        if (Build.VERSION.SDK_INT >= 12) {
            return bitmap.getByteCount();
        }
        return bitmap.getWidth() * bitmap.getHeight() * 4;
    }
}
