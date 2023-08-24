package com.facebook.imagepipeline.animated.base;

import android.graphics.Bitmap;
import com.facebook.common.internal.Preconditions;
import com.facebook.common.references.CloseableReference;
import com.facebook.imagepipeline.transformation.BitmapTransformation;
import java.util.List;
import javax.annotation.Nullable;

/* loaded from: classes.dex */
public class AnimatedImageResult {
    @Nullable
    private BitmapTransformation mBitmapTransformation;
    @Nullable
    private List<CloseableReference<Bitmap>> mDecodedFrames;
    private final int mFrameForPreview;
    private final AnimatedImage mImage;
    @Nullable
    private CloseableReference<Bitmap> mPreviewBitmap;

    /* JADX INFO: Access modifiers changed from: package-private */
    public AnimatedImageResult(AnimatedImageResultBuilder builder) {
        this.mImage = (AnimatedImage) Preconditions.checkNotNull(builder.getImage());
        this.mFrameForPreview = builder.getFrameForPreview();
        this.mPreviewBitmap = builder.getPreviewBitmap();
        this.mDecodedFrames = builder.getDecodedFrames();
        this.mBitmapTransformation = builder.getBitmapTransformation();
    }

    private AnimatedImageResult(AnimatedImage image) {
        this.mImage = (AnimatedImage) Preconditions.checkNotNull(image);
        this.mFrameForPreview = 0;
    }

    public static AnimatedImageResult forAnimatedImage(AnimatedImage image) {
        return new AnimatedImageResult(image);
    }

    public static AnimatedImageResultBuilder newBuilder(AnimatedImage image) {
        return new AnimatedImageResultBuilder(image);
    }

    public AnimatedImage getImage() {
        return this.mImage;
    }

    public int getFrameForPreview() {
        return this.mFrameForPreview;
    }

    @Nullable
    public synchronized CloseableReference<Bitmap> getDecodedFrame(int index) {
        List<CloseableReference<Bitmap>> list = this.mDecodedFrames;
        if (list != null) {
            return CloseableReference.cloneOrNull(list.get(index));
        }
        return null;
    }

    public synchronized boolean hasDecodedFrame(int index) {
        boolean z;
        List<CloseableReference<Bitmap>> list = this.mDecodedFrames;
        if (list != null) {
            z = list.get(index) != null;
        }
        return z;
    }

    @Nullable
    public BitmapTransformation getBitmapTransformation() {
        return this.mBitmapTransformation;
    }

    @Nullable
    public synchronized CloseableReference<Bitmap> getPreviewBitmap() {
        return CloseableReference.cloneOrNull(this.mPreviewBitmap);
    }

    public synchronized void dispose() {
        CloseableReference.closeSafely(this.mPreviewBitmap);
        this.mPreviewBitmap = null;
        CloseableReference.closeSafely(this.mDecodedFrames);
        this.mDecodedFrames = null;
    }
}
