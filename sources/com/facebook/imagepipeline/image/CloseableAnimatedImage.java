package com.facebook.imagepipeline.image;

import com.facebook.imagepipeline.animated.base.AnimatedImage;
import com.facebook.imagepipeline.animated.base.AnimatedImageResult;
import javax.annotation.Nullable;

/* loaded from: classes.dex */
public class CloseableAnimatedImage extends CloseableImage {
    @Nullable
    private AnimatedImageResult mImageResult;
    private boolean mIsStateful;

    public CloseableAnimatedImage(AnimatedImageResult imageResult) {
        this(imageResult, true);
    }

    public CloseableAnimatedImage(AnimatedImageResult imageResult, boolean isStateful) {
        this.mImageResult = imageResult;
        this.mIsStateful = isStateful;
    }

    @Override // com.facebook.imagepipeline.image.ImageInfo
    public synchronized int getWidth() {
        AnimatedImageResult animatedImageResult;
        animatedImageResult = this.mImageResult;
        return animatedImageResult == null ? 0 : animatedImageResult.getImage().getWidth();
    }

    @Override // com.facebook.imagepipeline.image.ImageInfo
    public synchronized int getHeight() {
        AnimatedImageResult animatedImageResult;
        animatedImageResult = this.mImageResult;
        return animatedImageResult == null ? 0 : animatedImageResult.getImage().getHeight();
    }

    @Override // com.facebook.imagepipeline.image.CloseableImage, java.io.Closeable, java.lang.AutoCloseable
    public void close() {
        synchronized (this) {
            AnimatedImageResult animatedImageResult = this.mImageResult;
            if (animatedImageResult == null) {
                return;
            }
            this.mImageResult = null;
            animatedImageResult.dispose();
        }
    }

    @Override // com.facebook.imagepipeline.image.CloseableImage
    public synchronized boolean isClosed() {
        return this.mImageResult == null;
    }

    @Override // com.facebook.imagepipeline.image.CloseableImage
    public synchronized int getSizeInBytes() {
        AnimatedImageResult animatedImageResult;
        animatedImageResult = this.mImageResult;
        return animatedImageResult == null ? 0 : animatedImageResult.getImage().getSizeInBytes();
    }

    @Override // com.facebook.imagepipeline.image.CloseableImage
    public boolean isStateful() {
        return this.mIsStateful;
    }

    @Nullable
    public synchronized AnimatedImageResult getImageResult() {
        return this.mImageResult;
    }

    @Nullable
    public synchronized AnimatedImage getImage() {
        AnimatedImageResult animatedImageResult;
        animatedImageResult = this.mImageResult;
        return animatedImageResult == null ? null : animatedImageResult.getImage();
    }
}
