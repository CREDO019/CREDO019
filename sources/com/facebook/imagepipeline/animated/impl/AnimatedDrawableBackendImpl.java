package com.facebook.imagepipeline.animated.impl;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import com.facebook.common.internal.Preconditions;
import com.facebook.common.references.CloseableReference;
import com.facebook.imagepipeline.animated.base.AnimatedDrawableBackend;
import com.facebook.imagepipeline.animated.base.AnimatedDrawableFrameInfo;
import com.facebook.imagepipeline.animated.base.AnimatedImage;
import com.facebook.imagepipeline.animated.base.AnimatedImageFrame;
import com.facebook.imagepipeline.animated.base.AnimatedImageResult;
import com.facebook.imagepipeline.animated.util.AnimatedDrawableUtil;
import javax.annotation.Nullable;

/* loaded from: classes.dex */
public class AnimatedDrawableBackendImpl implements AnimatedDrawableBackend {
    private final AnimatedDrawableUtil mAnimatedDrawableUtil;
    private final AnimatedImage mAnimatedImage;
    private final AnimatedImageResult mAnimatedImageResult;
    private final boolean mDownscaleFrameToDrawableDimensions;
    private final int mDurationMs;
    private final int[] mFrameDurationsMs;
    private final AnimatedDrawableFrameInfo[] mFrameInfos;
    private final int[] mFrameTimestampsMs;
    private final Rect mRenderedBounds;
    @Nullable
    private Bitmap mTempBitmap;
    private final Rect mRenderSrcRect = new Rect();
    private final Rect mRenderDstRect = new Rect();

    public AnimatedDrawableBackendImpl(AnimatedDrawableUtil animatedDrawableUtil, AnimatedImageResult animatedImageResult, @Nullable Rect bounds, boolean downscaleFrameToDrawableDimensions) {
        this.mAnimatedDrawableUtil = animatedDrawableUtil;
        this.mAnimatedImageResult = animatedImageResult;
        AnimatedImage image = animatedImageResult.getImage();
        this.mAnimatedImage = image;
        int[] frameDurations = image.getFrameDurations();
        this.mFrameDurationsMs = frameDurations;
        animatedDrawableUtil.fixFrameDurations(frameDurations);
        this.mDurationMs = animatedDrawableUtil.getTotalDurationFromFrameDurations(frameDurations);
        this.mFrameTimestampsMs = animatedDrawableUtil.getFrameTimeStampsFromDurations(frameDurations);
        this.mRenderedBounds = getBoundsToUse(image, bounds);
        this.mDownscaleFrameToDrawableDimensions = downscaleFrameToDrawableDimensions;
        this.mFrameInfos = new AnimatedDrawableFrameInfo[image.getFrameCount()];
        for (int r3 = 0; r3 < this.mAnimatedImage.getFrameCount(); r3++) {
            this.mFrameInfos[r3] = this.mAnimatedImage.getFrameInfo(r3);
        }
    }

    private static Rect getBoundsToUse(AnimatedImage image, @Nullable Rect targetBounds) {
        if (targetBounds == null) {
            return new Rect(0, 0, image.getWidth(), image.getHeight());
        }
        return new Rect(0, 0, Math.min(targetBounds.width(), image.getWidth()), Math.min(targetBounds.height(), image.getHeight()));
    }

    @Override // com.facebook.imagepipeline.animated.base.AnimatedDrawableBackend
    public AnimatedImageResult getAnimatedImageResult() {
        return this.mAnimatedImageResult;
    }

    @Override // com.facebook.imagepipeline.animated.base.AnimatedDrawableBackend
    public int getDurationMs() {
        return this.mDurationMs;
    }

    @Override // com.facebook.imagepipeline.animated.base.AnimatedDrawableBackend
    public int getFrameCount() {
        return this.mAnimatedImage.getFrameCount();
    }

    @Override // com.facebook.imagepipeline.animated.base.AnimatedDrawableBackend
    public int getLoopCount() {
        return this.mAnimatedImage.getLoopCount();
    }

    @Override // com.facebook.imagepipeline.animated.base.AnimatedDrawableBackend
    public int getWidth() {
        return this.mAnimatedImage.getWidth();
    }

    @Override // com.facebook.imagepipeline.animated.base.AnimatedDrawableBackend
    public int getHeight() {
        return this.mAnimatedImage.getHeight();
    }

    @Override // com.facebook.imagepipeline.animated.base.AnimatedDrawableBackend
    public int getRenderedWidth() {
        return this.mRenderedBounds.width();
    }

    @Override // com.facebook.imagepipeline.animated.base.AnimatedDrawableBackend
    public int getRenderedHeight() {
        return this.mRenderedBounds.height();
    }

    @Override // com.facebook.imagepipeline.animated.base.AnimatedDrawableBackend
    public AnimatedDrawableFrameInfo getFrameInfo(int frameNumber) {
        return this.mFrameInfos[frameNumber];
    }

    @Override // com.facebook.imagepipeline.animated.base.AnimatedDrawableBackend
    public int getFrameForTimestampMs(int timestampMs) {
        return this.mAnimatedDrawableUtil.getFrameForTimestampMs(this.mFrameTimestampsMs, timestampMs);
    }

    @Override // com.facebook.imagepipeline.animated.base.AnimatedDrawableBackend
    public int getTimestampMsForFrame(int frameNumber) {
        Preconditions.checkElementIndex(frameNumber, this.mFrameTimestampsMs.length);
        return this.mFrameTimestampsMs[frameNumber];
    }

    @Override // com.facebook.imagepipeline.animated.base.AnimatedDrawableBackend
    public int getDurationMsForFrame(int frameNumber) {
        return this.mFrameDurationsMs[frameNumber];
    }

    @Override // com.facebook.imagepipeline.animated.base.AnimatedDrawableBackend
    public int getFrameForPreview() {
        return this.mAnimatedImageResult.getFrameForPreview();
    }

    @Override // com.facebook.imagepipeline.animated.base.AnimatedDrawableBackend
    public AnimatedDrawableBackend forNewBounds(@Nullable Rect bounds) {
        return getBoundsToUse(this.mAnimatedImage, bounds).equals(this.mRenderedBounds) ? this : new AnimatedDrawableBackendImpl(this.mAnimatedDrawableUtil, this.mAnimatedImageResult, bounds, this.mDownscaleFrameToDrawableDimensions);
    }

    @Override // com.facebook.imagepipeline.animated.base.AnimatedDrawableBackend
    public synchronized int getMemoryUsage() {
        Bitmap bitmap;
        bitmap = this.mTempBitmap;
        return (bitmap != null ? 0 + this.mAnimatedDrawableUtil.getSizeOfBitmap(bitmap) : 0) + this.mAnimatedImage.getSizeInBytes();
    }

    @Override // com.facebook.imagepipeline.animated.base.AnimatedDrawableBackend
    @Nullable
    public CloseableReference<Bitmap> getPreDecodedFrame(int frameNumber) {
        return this.mAnimatedImageResult.getDecodedFrame(frameNumber);
    }

    @Override // com.facebook.imagepipeline.animated.base.AnimatedDrawableBackend
    public boolean hasPreDecodedFrame(int index) {
        return this.mAnimatedImageResult.hasDecodedFrame(index);
    }

    @Override // com.facebook.imagepipeline.animated.base.AnimatedDrawableBackend
    public void renderFrame(int frameNumber, Canvas canvas) {
        AnimatedImageFrame frame = this.mAnimatedImage.getFrame(frameNumber);
        try {
            if (this.mAnimatedImage.doesRenderSupportScaling()) {
                renderImageSupportsScaling(canvas, frame);
            } else {
                renderImageDoesNotSupportScaling(canvas, frame);
            }
        } finally {
            frame.dispose();
        }
    }

    private synchronized Bitmap prepareTempBitmapForThisSize(int width, int height) {
        Bitmap bitmap = this.mTempBitmap;
        if (bitmap != null && (bitmap.getWidth() < width || this.mTempBitmap.getHeight() < height)) {
            clearTempBitmap();
        }
        if (this.mTempBitmap == null) {
            this.mTempBitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
        }
        this.mTempBitmap.eraseColor(0);
        return this.mTempBitmap;
    }

    private void renderImageSupportsScaling(Canvas canvas, AnimatedImageFrame frame) {
        double width = this.mRenderedBounds.width() / this.mAnimatedImage.getWidth();
        double height = this.mRenderedBounds.height() / this.mAnimatedImage.getHeight();
        int round = (int) Math.round(frame.getWidth() * width);
        int round2 = (int) Math.round(frame.getHeight() * height);
        int xOffset = (int) (frame.getXOffset() * width);
        int yOffset = (int) (frame.getYOffset() * height);
        synchronized (this) {
            int width2 = this.mRenderedBounds.width();
            int height2 = this.mRenderedBounds.height();
            prepareTempBitmapForThisSize(width2, height2);
            Bitmap bitmap = this.mTempBitmap;
            if (bitmap != null) {
                frame.renderFrame(round, round2, bitmap);
            }
            this.mRenderSrcRect.set(0, 0, width2, height2);
            this.mRenderDstRect.set(xOffset, yOffset, width2 + xOffset, height2 + yOffset);
            Bitmap bitmap2 = this.mTempBitmap;
            if (bitmap2 != null) {
                canvas.drawBitmap(bitmap2, this.mRenderSrcRect, this.mRenderDstRect, (Paint) null);
            }
        }
    }

    private void renderImageDoesNotSupportScaling(Canvas canvas, AnimatedImageFrame frame) {
        int width;
        int height;
        int xOffset;
        int yOffset;
        if (this.mDownscaleFrameToDrawableDimensions) {
            float max = Math.max(frame.getWidth() / Math.min(frame.getWidth(), canvas.getWidth()), frame.getHeight() / Math.min(frame.getHeight(), canvas.getHeight()));
            width = (int) (frame.getWidth() / max);
            height = (int) (frame.getHeight() / max);
            xOffset = (int) (frame.getXOffset() / max);
            yOffset = (int) (frame.getYOffset() / max);
        } else {
            width = frame.getWidth();
            height = frame.getHeight();
            xOffset = frame.getXOffset();
            yOffset = frame.getYOffset();
        }
        synchronized (this) {
            Bitmap prepareTempBitmapForThisSize = prepareTempBitmapForThisSize(width, height);
            this.mTempBitmap = prepareTempBitmapForThisSize;
            frame.renderFrame(width, height, prepareTempBitmapForThisSize);
            canvas.save();
            canvas.translate(xOffset, yOffset);
            canvas.drawBitmap(this.mTempBitmap, 0.0f, 0.0f, (Paint) null);
            canvas.restore();
        }
    }

    @Override // com.facebook.imagepipeline.animated.base.AnimatedDrawableBackend
    public synchronized void dropCaches() {
        clearTempBitmap();
    }

    private synchronized void clearTempBitmap() {
        Bitmap bitmap = this.mTempBitmap;
        if (bitmap != null) {
            bitmap.recycle();
            this.mTempBitmap = null;
        }
    }
}
