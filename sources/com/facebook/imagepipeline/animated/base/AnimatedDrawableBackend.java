package com.facebook.imagepipeline.animated.base;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Rect;
import com.facebook.common.references.CloseableReference;
import javax.annotation.Nullable;

/* loaded from: classes.dex */
public interface AnimatedDrawableBackend {
    void dropCaches();

    AnimatedDrawableBackend forNewBounds(@Nullable Rect bounds);

    AnimatedImageResult getAnimatedImageResult();

    int getDurationMs();

    int getDurationMsForFrame(int frameNumber);

    int getFrameCount();

    int getFrameForPreview();

    int getFrameForTimestampMs(int timestampMs);

    AnimatedDrawableFrameInfo getFrameInfo(int frameNumber);

    int getHeight();

    int getLoopCount();

    int getMemoryUsage();

    @Nullable
    CloseableReference<Bitmap> getPreDecodedFrame(int frameNumber);

    int getRenderedHeight();

    int getRenderedWidth();

    int getTimestampMsForFrame(int frameNumber);

    int getWidth();

    boolean hasPreDecodedFrame(int frameNumber);

    void renderFrame(int frameNumber, Canvas canvas);
}
