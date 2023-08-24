package com.facebook.fresco.animation.bitmap.cache;

import android.graphics.Bitmap;
import com.facebook.common.references.CloseableReference;
import com.facebook.fresco.animation.bitmap.BitmapFrameCache;
import com.facebook.imageutils.BitmapUtil;
import javax.annotation.Nullable;

/* loaded from: classes.dex */
public class KeepLastFrameCache implements BitmapFrameCache {
    private static final int FRAME_NUMBER_UNSET = -1;
    @Nullable
    private BitmapFrameCache.FrameCacheListener mFrameCacheListener;
    @Nullable
    private CloseableReference<Bitmap> mLastBitmapReference;
    private int mLastFrameNumber = -1;

    @Override // com.facebook.fresco.animation.bitmap.BitmapFrameCache
    public void onFramePrepared(int frameNumber, CloseableReference<Bitmap> bitmapReference, int frameType) {
    }

    @Override // com.facebook.fresco.animation.bitmap.BitmapFrameCache
    @Nullable
    public synchronized CloseableReference<Bitmap> getCachedFrame(int frameNumber) {
        if (this.mLastFrameNumber == frameNumber) {
            return CloseableReference.cloneOrNull(this.mLastBitmapReference);
        }
        return null;
    }

    @Override // com.facebook.fresco.animation.bitmap.BitmapFrameCache
    @Nullable
    public synchronized CloseableReference<Bitmap> getFallbackFrame(int frameNumber) {
        return CloseableReference.cloneOrNull(this.mLastBitmapReference);
    }

    @Override // com.facebook.fresco.animation.bitmap.BitmapFrameCache
    @Nullable
    public synchronized CloseableReference<Bitmap> getBitmapToReuseForFrame(int frameNumber, int width, int height) {
        CloseableReference<Bitmap> cloneOrNull;
        cloneOrNull = CloseableReference.cloneOrNull(this.mLastBitmapReference);
        closeAndResetLastBitmapReference();
        return cloneOrNull;
    }

    @Override // com.facebook.fresco.animation.bitmap.BitmapFrameCache
    public synchronized boolean contains(int frameNumber) {
        boolean z;
        if (frameNumber == this.mLastFrameNumber) {
            z = CloseableReference.isValid(this.mLastBitmapReference);
        }
        return z;
    }

    @Override // com.facebook.fresco.animation.bitmap.BitmapFrameCache
    public synchronized int getSizeInBytes() {
        CloseableReference<Bitmap> closeableReference;
        closeableReference = this.mLastBitmapReference;
        return closeableReference == null ? 0 : BitmapUtil.getSizeInBytes(closeableReference.get());
    }

    @Override // com.facebook.fresco.animation.bitmap.BitmapFrameCache
    public synchronized void clear() {
        closeAndResetLastBitmapReference();
    }

    @Override // com.facebook.fresco.animation.bitmap.BitmapFrameCache
    public synchronized void onFrameRendered(int frameNumber, CloseableReference<Bitmap> bitmapReference, int frameType) {
        int r0;
        if (bitmapReference != null) {
            if (this.mLastBitmapReference != null && bitmapReference.get().equals(this.mLastBitmapReference.get())) {
                return;
            }
        }
        CloseableReference.closeSafely(this.mLastBitmapReference);
        BitmapFrameCache.FrameCacheListener frameCacheListener = this.mFrameCacheListener;
        if (frameCacheListener != null && (r0 = this.mLastFrameNumber) != -1) {
            frameCacheListener.onFrameEvicted(this, r0);
        }
        this.mLastBitmapReference = CloseableReference.cloneOrNull(bitmapReference);
        BitmapFrameCache.FrameCacheListener frameCacheListener2 = this.mFrameCacheListener;
        if (frameCacheListener2 != null) {
            frameCacheListener2.onFrameCached(this, frameNumber);
        }
        this.mLastFrameNumber = frameNumber;
    }

    @Override // com.facebook.fresco.animation.bitmap.BitmapFrameCache
    public void setFrameCacheListener(BitmapFrameCache.FrameCacheListener frameCacheListener) {
        this.mFrameCacheListener = frameCacheListener;
    }

    private synchronized void closeAndResetLastBitmapReference() {
        int r2;
        BitmapFrameCache.FrameCacheListener frameCacheListener = this.mFrameCacheListener;
        if (frameCacheListener != null && (r2 = this.mLastFrameNumber) != -1) {
            frameCacheListener.onFrameEvicted(this, r2);
        }
        CloseableReference.closeSafely(this.mLastBitmapReference);
        this.mLastBitmapReference = null;
        this.mLastFrameNumber = -1;
    }
}
