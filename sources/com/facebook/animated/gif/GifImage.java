package com.facebook.animated.gif;

import android.graphics.Bitmap;
import com.facebook.common.internal.Preconditions;
import com.facebook.imagepipeline.animated.base.AnimatedDrawableFrameInfo;
import com.facebook.imagepipeline.animated.base.AnimatedImage;
import com.facebook.imagepipeline.animated.factory.AnimatedImageDecoder;
import com.facebook.imagepipeline.common.ImageDecodeOptions;
import com.facebook.soloader.nativeloader.NativeLoader;
import java.nio.ByteBuffer;
import javax.annotation.Nullable;

/* loaded from: classes.dex */
public class GifImage implements AnimatedImage, AnimatedImageDecoder {
    private static final int LOOP_COUNT_FOREVER = 0;
    private static final int LOOP_COUNT_MISSING = -1;
    private static volatile boolean sInitialized;
    @Nullable
    private Bitmap.Config mDecodeBitmapConfig = null;
    private long mNativeContext;

    private static native GifImage nativeCreateFromDirectByteBuffer(ByteBuffer buffer, int maxDimension, boolean forceStatic);

    private static native GifImage nativeCreateFromFileDescriptor(int fileDescriptor, int maxDimension, boolean forceStatic);

    private static native GifImage nativeCreateFromNativeMemory(long nativePtr, int sizeInBytes, int maxDimension, boolean forceStatic);

    private native void nativeDispose();

    private native void nativeFinalize();

    private native int nativeGetDuration();

    private native GifFrame nativeGetFrame(int frameNumber);

    private native int nativeGetFrameCount();

    private native int[] nativeGetFrameDurations();

    private native int nativeGetHeight();

    private native int nativeGetLoopCount();

    private native int nativeGetSizeInBytes();

    private native int nativeGetWidth();

    private native boolean nativeIsAnimated();

    @Override // com.facebook.imagepipeline.animated.base.AnimatedImage
    public boolean doesRenderSupportScaling() {
        return false;
    }

    private static synchronized void ensure() {
        synchronized (GifImage.class) {
            if (!sInitialized) {
                sInitialized = true;
                NativeLoader.loadLibrary("gifimage");
            }
        }
    }

    public static GifImage createFromByteArray(byte[] source) {
        Preconditions.checkNotNull(source);
        ByteBuffer allocateDirect = ByteBuffer.allocateDirect(source.length);
        allocateDirect.put(source);
        allocateDirect.rewind();
        return createFromByteBuffer(allocateDirect, ImageDecodeOptions.defaults());
    }

    public static GifImage createFromByteBuffer(ByteBuffer byteBuffer) {
        return createFromByteBuffer(byteBuffer, ImageDecodeOptions.defaults());
    }

    public static GifImage createFromByteBuffer(ByteBuffer byteBuffer, ImageDecodeOptions options) {
        ensure();
        byteBuffer.rewind();
        GifImage nativeCreateFromDirectByteBuffer = nativeCreateFromDirectByteBuffer(byteBuffer, options.maxDimensionPx, options.forceStaticImage);
        nativeCreateFromDirectByteBuffer.mDecodeBitmapConfig = options.animatedBitmapConfig;
        return nativeCreateFromDirectByteBuffer;
    }

    public static GifImage createFromNativeMemory(long nativePtr, int sizeInBytes, ImageDecodeOptions options) {
        ensure();
        Preconditions.checkArgument(Boolean.valueOf(nativePtr != 0));
        GifImage nativeCreateFromNativeMemory = nativeCreateFromNativeMemory(nativePtr, sizeInBytes, options.maxDimensionPx, options.forceStaticImage);
        nativeCreateFromNativeMemory.mDecodeBitmapConfig = options.animatedBitmapConfig;
        return nativeCreateFromNativeMemory;
    }

    public static GifImage createFromFileDescriptor(int fileDescriptor, ImageDecodeOptions options) {
        ensure();
        return nativeCreateFromFileDescriptor(fileDescriptor, options.maxDimensionPx, options.forceStaticImage);
    }

    @Override // com.facebook.imagepipeline.animated.factory.AnimatedImageDecoder
    public AnimatedImage decodeFromNativeMemory(long nativePtr, int sizeInBytes, ImageDecodeOptions options) {
        return createFromNativeMemory(nativePtr, sizeInBytes, options);
    }

    @Override // com.facebook.imagepipeline.animated.factory.AnimatedImageDecoder
    public AnimatedImage decodeFromByteBuffer(ByteBuffer byteBuffer, ImageDecodeOptions options) {
        return createFromByteBuffer(byteBuffer, options);
    }

    public GifImage() {
    }

    GifImage(long nativeContext) {
        this.mNativeContext = nativeContext;
    }

    protected void finalize() {
        nativeFinalize();
    }

    @Override // com.facebook.imagepipeline.animated.base.AnimatedImage
    public void dispose() {
        nativeDispose();
    }

    @Override // com.facebook.imagepipeline.animated.base.AnimatedImage
    public int getWidth() {
        return nativeGetWidth();
    }

    @Override // com.facebook.imagepipeline.animated.base.AnimatedImage
    public int getHeight() {
        return nativeGetHeight();
    }

    @Override // com.facebook.imagepipeline.animated.base.AnimatedImage
    public int getFrameCount() {
        return nativeGetFrameCount();
    }

    @Override // com.facebook.imagepipeline.animated.base.AnimatedImage
    public int getDuration() {
        return nativeGetDuration();
    }

    @Override // com.facebook.imagepipeline.animated.base.AnimatedImage
    public int[] getFrameDurations() {
        return nativeGetFrameDurations();
    }

    @Override // com.facebook.imagepipeline.animated.base.AnimatedImage
    public int getLoopCount() {
        int nativeGetLoopCount = nativeGetLoopCount();
        if (nativeGetLoopCount != -1) {
            if (nativeGetLoopCount != 0) {
                return nativeGetLoopCount + 1;
            }
            return 0;
        }
        return 1;
    }

    @Override // com.facebook.imagepipeline.animated.base.AnimatedImage
    public GifFrame getFrame(int frameNumber) {
        return nativeGetFrame(frameNumber);
    }

    @Override // com.facebook.imagepipeline.animated.base.AnimatedImage
    public int getSizeInBytes() {
        return nativeGetSizeInBytes();
    }

    public boolean isAnimated() {
        return nativeIsAnimated();
    }

    @Override // com.facebook.imagepipeline.animated.base.AnimatedImage
    public AnimatedDrawableFrameInfo getFrameInfo(int frameNumber) {
        GifFrame frame = getFrame(frameNumber);
        try {
            return new AnimatedDrawableFrameInfo(frameNumber, frame.getXOffset(), frame.getYOffset(), frame.getWidth(), frame.getHeight(), AnimatedDrawableFrameInfo.BlendOperation.BLEND_WITH_PREVIOUS, fromGifDisposalMethod(frame.getDisposalMode()));
        } finally {
            frame.dispose();
        }
    }

    @Override // com.facebook.imagepipeline.animated.base.AnimatedImage
    @Nullable
    public Bitmap.Config getAnimatedBitmapConfig() {
        return this.mDecodeBitmapConfig;
    }

    private static AnimatedDrawableFrameInfo.DisposalMethod fromGifDisposalMethod(int disposalMode) {
        if (disposalMode == 0) {
            return AnimatedDrawableFrameInfo.DisposalMethod.DISPOSE_DO_NOT;
        }
        if (disposalMode == 1) {
            return AnimatedDrawableFrameInfo.DisposalMethod.DISPOSE_DO_NOT;
        }
        if (disposalMode == 2) {
            return AnimatedDrawableFrameInfo.DisposalMethod.DISPOSE_TO_BACKGROUND;
        }
        if (disposalMode == 3) {
            return AnimatedDrawableFrameInfo.DisposalMethod.DISPOSE_TO_PREVIOUS;
        }
        return AnimatedDrawableFrameInfo.DisposalMethod.DISPOSE_DO_NOT;
    }
}
