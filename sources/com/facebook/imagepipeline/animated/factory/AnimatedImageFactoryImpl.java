package com.facebook.imagepipeline.animated.factory;

import android.graphics.Bitmap;
import android.os.Build;
import com.facebook.common.internal.Preconditions;
import com.facebook.common.memory.PooledByteBuffer;
import com.facebook.common.references.CloseableReference;
import com.facebook.imagepipeline.animated.base.AnimatedDrawableBackend;
import com.facebook.imagepipeline.animated.base.AnimatedImage;
import com.facebook.imagepipeline.animated.base.AnimatedImageResult;
import com.facebook.imagepipeline.animated.impl.AnimatedDrawableBackendProvider;
import com.facebook.imagepipeline.animated.impl.AnimatedImageCompositor;
import com.facebook.imagepipeline.bitmaps.PlatformBitmapFactory;
import com.facebook.imagepipeline.common.ImageDecodeOptions;
import com.facebook.imagepipeline.image.CloseableAnimatedImage;
import com.facebook.imagepipeline.image.CloseableImage;
import com.facebook.imagepipeline.image.CloseableStaticBitmap;
import com.facebook.imagepipeline.image.EncodedImage;
import com.facebook.imagepipeline.image.ImmutableQualityInfo;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Nullable;

/* loaded from: classes.dex */
public class AnimatedImageFactoryImpl implements AnimatedImageFactory {
    @Nullable
    static AnimatedImageDecoder sGifAnimatedImageDecoder = loadIfPresent("com.facebook.animated.gif.GifImage");
    @Nullable
    static AnimatedImageDecoder sWebpAnimatedImageDecoder = loadIfPresent("com.facebook.animated.webp.WebPImage");
    private final AnimatedDrawableBackendProvider mAnimatedDrawableBackendProvider;
    private final PlatformBitmapFactory mBitmapFactory;

    @Nullable
    private static AnimatedImageDecoder loadIfPresent(final String className) {
        try {
            return (AnimatedImageDecoder) Class.forName(className).newInstance();
        } catch (Throwable unused) {
            return null;
        }
    }

    public AnimatedImageFactoryImpl(AnimatedDrawableBackendProvider animatedDrawableBackendProvider, PlatformBitmapFactory bitmapFactory) {
        this.mAnimatedDrawableBackendProvider = animatedDrawableBackendProvider;
        this.mBitmapFactory = bitmapFactory;
    }

    @Override // com.facebook.imagepipeline.animated.factory.AnimatedImageFactory
    public CloseableImage decodeGif(final EncodedImage encodedImage, final ImageDecodeOptions options, final Bitmap.Config bitmapConfig) {
        AnimatedImage decodeFromNativeMemory;
        if (sGifAnimatedImageDecoder == null) {
            throw new UnsupportedOperationException("To encode animated gif please add the dependency to the animated-gif module");
        }
        CloseableReference<PooledByteBuffer> byteBufferRef = encodedImage.getByteBufferRef();
        Preconditions.checkNotNull(byteBufferRef);
        try {
            PooledByteBuffer pooledByteBuffer = byteBufferRef.get();
            if (pooledByteBuffer.getByteBuffer() != null) {
                decodeFromNativeMemory = sGifAnimatedImageDecoder.decodeFromByteBuffer(pooledByteBuffer.getByteBuffer(), options);
            } else {
                decodeFromNativeMemory = sGifAnimatedImageDecoder.decodeFromNativeMemory(pooledByteBuffer.getNativePtr(), pooledByteBuffer.size(), options);
            }
            return getCloseableImage(options, decodeFromNativeMemory, bitmapConfig);
        } finally {
            CloseableReference.closeSafely(byteBufferRef);
        }
    }

    @Override // com.facebook.imagepipeline.animated.factory.AnimatedImageFactory
    public CloseableImage decodeWebP(final EncodedImage encodedImage, final ImageDecodeOptions options, final Bitmap.Config bitmapConfig) {
        AnimatedImage decodeFromNativeMemory;
        if (sWebpAnimatedImageDecoder == null) {
            throw new UnsupportedOperationException("To encode animated webp please add the dependency to the animated-webp module");
        }
        CloseableReference<PooledByteBuffer> byteBufferRef = encodedImage.getByteBufferRef();
        Preconditions.checkNotNull(byteBufferRef);
        try {
            PooledByteBuffer pooledByteBuffer = byteBufferRef.get();
            if (pooledByteBuffer.getByteBuffer() != null) {
                decodeFromNativeMemory = sWebpAnimatedImageDecoder.decodeFromByteBuffer(pooledByteBuffer.getByteBuffer(), options);
            } else {
                decodeFromNativeMemory = sWebpAnimatedImageDecoder.decodeFromNativeMemory(pooledByteBuffer.getNativePtr(), pooledByteBuffer.size(), options);
            }
            return getCloseableImage(options, decodeFromNativeMemory, bitmapConfig);
        } finally {
            CloseableReference.closeSafely(byteBufferRef);
        }
    }

    private CloseableImage getCloseableImage(ImageDecodeOptions options, AnimatedImage image, Bitmap.Config bitmapConfig) {
        List<CloseableReference<Bitmap>> list;
        CloseableReference<Bitmap> closeableReference = null;
        try {
            int frameCount = options.useLastFrameForPreview ? image.getFrameCount() - 1 : 0;
            if (options.forceStaticImage) {
                CloseableStaticBitmap closeableStaticBitmap = new CloseableStaticBitmap(createPreviewBitmap(image, bitmapConfig, frameCount), ImmutableQualityInfo.FULL_QUALITY, 0);
                CloseableReference.closeSafely((CloseableReference<?>) null);
                CloseableReference.closeSafely((Iterable<? extends CloseableReference<?>>) null);
                return closeableStaticBitmap;
            }
            if (options.decodeAllFrames) {
                list = decodeAllFrames(image, bitmapConfig);
                try {
                    closeableReference = CloseableReference.cloneOrNull(list.get(frameCount));
                } catch (Throwable th) {
                    th = th;
                    CloseableReference.closeSafely(closeableReference);
                    CloseableReference.closeSafely(list);
                    throw th;
                }
            } else {
                list = null;
            }
            if (options.decodePreviewFrame && closeableReference == null) {
                closeableReference = createPreviewBitmap(image, bitmapConfig, frameCount);
            }
            CloseableAnimatedImage closeableAnimatedImage = new CloseableAnimatedImage(AnimatedImageResult.newBuilder(image).setPreviewBitmap(closeableReference).setFrameForPreview(frameCount).setDecodedFrames(list).setBitmapTransformation(options.bitmapTransformation).build());
            CloseableReference.closeSafely(closeableReference);
            CloseableReference.closeSafely(list);
            return closeableAnimatedImage;
        } catch (Throwable th2) {
            th = th2;
            list = null;
        }
    }

    private CloseableReference<Bitmap> createPreviewBitmap(AnimatedImage image, Bitmap.Config bitmapConfig, int frameForPreview) {
        CloseableReference<Bitmap> createBitmap = createBitmap(image.getWidth(), image.getHeight(), bitmapConfig);
        new AnimatedImageCompositor(this.mAnimatedDrawableBackendProvider.get(AnimatedImageResult.forAnimatedImage(image), null), new AnimatedImageCompositor.Callback() { // from class: com.facebook.imagepipeline.animated.factory.AnimatedImageFactoryImpl.1
            @Override // com.facebook.imagepipeline.animated.impl.AnimatedImageCompositor.Callback
            @Nullable
            public CloseableReference<Bitmap> getCachedBitmap(int frameNumber) {
                return null;
            }

            @Override // com.facebook.imagepipeline.animated.impl.AnimatedImageCompositor.Callback
            public void onIntermediateResult(int frameNumber, Bitmap bitmap) {
            }
        }).renderFrame(frameForPreview, createBitmap.get());
        return createBitmap;
    }

    private List<CloseableReference<Bitmap>> decodeAllFrames(AnimatedImage image, Bitmap.Config bitmapConfig) {
        AnimatedDrawableBackend animatedDrawableBackend = this.mAnimatedDrawableBackendProvider.get(AnimatedImageResult.forAnimatedImage(image), null);
        final ArrayList arrayList = new ArrayList(animatedDrawableBackend.getFrameCount());
        AnimatedImageCompositor animatedImageCompositor = new AnimatedImageCompositor(animatedDrawableBackend, new AnimatedImageCompositor.Callback() { // from class: com.facebook.imagepipeline.animated.factory.AnimatedImageFactoryImpl.2
            @Override // com.facebook.imagepipeline.animated.impl.AnimatedImageCompositor.Callback
            public void onIntermediateResult(int frameNumber, Bitmap bitmap) {
            }

            @Override // com.facebook.imagepipeline.animated.impl.AnimatedImageCompositor.Callback
            @Nullable
            public CloseableReference<Bitmap> getCachedBitmap(int frameNumber) {
                return CloseableReference.cloneOrNull((CloseableReference) arrayList.get(frameNumber));
            }
        });
        for (int r2 = 0; r2 < animatedDrawableBackend.getFrameCount(); r2++) {
            CloseableReference<Bitmap> createBitmap = createBitmap(animatedDrawableBackend.getWidth(), animatedDrawableBackend.getHeight(), bitmapConfig);
            animatedImageCompositor.renderFrame(r2, createBitmap.get());
            arrayList.add(createBitmap);
        }
        return arrayList;
    }

    private CloseableReference<Bitmap> createBitmap(int width, int height, Bitmap.Config bitmapConfig) {
        CloseableReference<Bitmap> createBitmapInternal = this.mBitmapFactory.createBitmapInternal(width, height, bitmapConfig);
        createBitmapInternal.get().eraseColor(0);
        if (Build.VERSION.SDK_INT >= 12) {
            createBitmapInternal.get().setHasAlpha(true);
        }
        return createBitmapInternal;
    }
}
