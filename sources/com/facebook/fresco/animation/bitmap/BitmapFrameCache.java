package com.facebook.fresco.animation.bitmap;

import android.graphics.Bitmap;
import com.facebook.common.references.CloseableReference;
import javax.annotation.Nullable;

/* loaded from: classes.dex */
public interface BitmapFrameCache {

    /* loaded from: classes.dex */
    public interface FrameCacheListener {
        void onFrameCached(BitmapFrameCache bitmapFrameCache, int frameNumber);

        void onFrameEvicted(BitmapFrameCache bitmapFrameCache, int frameNumber);
    }

    void clear();

    boolean contains(int frameNumber);

    @Nullable
    CloseableReference<Bitmap> getBitmapToReuseForFrame(int frameNumber, int width, int height);

    @Nullable
    CloseableReference<Bitmap> getCachedFrame(int frameNumber);

    @Nullable
    CloseableReference<Bitmap> getFallbackFrame(int frameNumber);

    int getSizeInBytes();

    void onFramePrepared(int frameNumber, CloseableReference<Bitmap> bitmapReference, int frameType);

    void onFrameRendered(int frameNumber, CloseableReference<Bitmap> bitmapReference, int frameType);

    void setFrameCacheListener(FrameCacheListener frameCacheListener);
}
