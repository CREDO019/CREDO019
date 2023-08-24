package com.facebook.fresco.animation.factory;

import android.content.Context;
import android.graphics.Rect;
import com.facebook.cache.common.CacheKey;
import com.facebook.common.executors.DefaultSerialExecutorService;
import com.facebook.common.executors.SerialExecutorService;
import com.facebook.common.executors.UiThreadImmediateExecutorService;
import com.facebook.common.internal.Supplier;
import com.facebook.common.internal.Suppliers;
import com.facebook.common.time.RealtimeSinceBootClock;
import com.facebook.imagepipeline.animated.base.AnimatedDrawableBackend;
import com.facebook.imagepipeline.animated.base.AnimatedImageResult;
import com.facebook.imagepipeline.animated.factory.AnimatedFactory;
import com.facebook.imagepipeline.animated.factory.AnimatedImageFactory;
import com.facebook.imagepipeline.animated.factory.AnimatedImageFactoryImpl;
import com.facebook.imagepipeline.animated.impl.AnimatedDrawableBackendImpl;
import com.facebook.imagepipeline.animated.impl.AnimatedDrawableBackendProvider;
import com.facebook.imagepipeline.animated.util.AnimatedDrawableUtil;
import com.facebook.imagepipeline.bitmaps.PlatformBitmapFactory;
import com.facebook.imagepipeline.cache.CountingMemoryCache;
import com.facebook.imagepipeline.common.ImageDecodeOptions;
import com.facebook.imagepipeline.core.ExecutorSupplier;
import com.facebook.imagepipeline.decoder.ImageDecoder;
import com.facebook.imagepipeline.drawable.DrawableFactory;
import com.facebook.imagepipeline.image.CloseableImage;
import com.facebook.imagepipeline.image.EncodedImage;
import com.facebook.imagepipeline.image.QualityInfo;
import java.util.concurrent.ExecutorService;
import javax.annotation.Nullable;

/* loaded from: classes.dex */
public class AnimatedFactoryV2Impl implements AnimatedFactory {
    private static final int NUMBER_OF_FRAMES_TO_PREPARE = 3;
    @Nullable
    private AnimatedDrawableBackendProvider mAnimatedDrawableBackendProvider;
    @Nullable
    private DrawableFactory mAnimatedDrawableFactory;
    @Nullable
    private AnimatedDrawableUtil mAnimatedDrawableUtil;
    @Nullable
    private AnimatedImageFactory mAnimatedImageFactory;
    private final CountingMemoryCache<CacheKey, CloseableImage> mBackingCache;
    private final boolean mDownscaleFrameToDrawableDimensions;
    private final ExecutorSupplier mExecutorSupplier;
    private final PlatformBitmapFactory mPlatformBitmapFactory;
    @Nullable
    private SerialExecutorService mSerialExecutorService;

    public AnimatedFactoryV2Impl(PlatformBitmapFactory platformBitmapFactory, ExecutorSupplier executorSupplier, CountingMemoryCache<CacheKey, CloseableImage> backingCache, boolean downscaleFrameToDrawableDimensions, SerialExecutorService serialExecutorServiceForFramePreparing) {
        this.mPlatformBitmapFactory = platformBitmapFactory;
        this.mExecutorSupplier = executorSupplier;
        this.mBackingCache = backingCache;
        this.mDownscaleFrameToDrawableDimensions = downscaleFrameToDrawableDimensions;
        this.mSerialExecutorService = serialExecutorServiceForFramePreparing;
    }

    @Override // com.facebook.imagepipeline.animated.factory.AnimatedFactory
    @Nullable
    public DrawableFactory getAnimatedDrawableFactory(@Nullable Context context) {
        if (this.mAnimatedDrawableFactory == null) {
            this.mAnimatedDrawableFactory = createDrawableFactory();
        }
        return this.mAnimatedDrawableFactory;
    }

    @Override // com.facebook.imagepipeline.animated.factory.AnimatedFactory
    public ImageDecoder getGifDecoder() {
        return new ImageDecoder() { // from class: com.facebook.fresco.animation.factory.AnimatedFactoryV2Impl.1
            @Override // com.facebook.imagepipeline.decoder.ImageDecoder
            public CloseableImage decode(EncodedImage encodedImage, int length, QualityInfo qualityInfo, ImageDecodeOptions options) {
                return AnimatedFactoryV2Impl.this.getAnimatedImageFactory().decodeGif(encodedImage, options, options.animatedBitmapConfig);
            }
        };
    }

    @Override // com.facebook.imagepipeline.animated.factory.AnimatedFactory
    public ImageDecoder getWebPDecoder() {
        return new ImageDecoder() { // from class: com.facebook.fresco.animation.factory.AnimatedFactoryV2Impl.2
            @Override // com.facebook.imagepipeline.decoder.ImageDecoder
            public CloseableImage decode(EncodedImage encodedImage, int length, QualityInfo qualityInfo, ImageDecodeOptions options) {
                return AnimatedFactoryV2Impl.this.getAnimatedImageFactory().decodeWebP(encodedImage, options, options.animatedBitmapConfig);
            }
        };
    }

    private ExperimentalBitmapAnimationDrawableFactory createDrawableFactory() {
        Supplier<Integer> supplier = new Supplier<Integer>() { // from class: com.facebook.fresco.animation.factory.AnimatedFactoryV2Impl.3
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // com.facebook.common.internal.Supplier
            public Integer get() {
                return 2;
            }
        };
        ExecutorService executorService = this.mSerialExecutorService;
        if (executorService == null) {
            executorService = new DefaultSerialExecutorService(this.mExecutorSupplier.forDecode());
        }
        Supplier<Integer> supplier2 = new Supplier<Integer>() { // from class: com.facebook.fresco.animation.factory.AnimatedFactoryV2Impl.4
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // com.facebook.common.internal.Supplier
            public Integer get() {
                return 3;
            }
        };
        Supplier<Boolean> supplier3 = Suppliers.BOOLEAN_FALSE;
        return new ExperimentalBitmapAnimationDrawableFactory(getAnimatedDrawableBackendProvider(), UiThreadImmediateExecutorService.getInstance(), executorService, RealtimeSinceBootClock.get(), this.mPlatformBitmapFactory, this.mBackingCache, supplier, supplier2, supplier3);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public AnimatedDrawableUtil getAnimatedDrawableUtil() {
        if (this.mAnimatedDrawableUtil == null) {
            this.mAnimatedDrawableUtil = new AnimatedDrawableUtil();
        }
        return this.mAnimatedDrawableUtil;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public AnimatedImageFactory getAnimatedImageFactory() {
        if (this.mAnimatedImageFactory == null) {
            this.mAnimatedImageFactory = buildAnimatedImageFactory();
        }
        return this.mAnimatedImageFactory;
    }

    private AnimatedDrawableBackendProvider getAnimatedDrawableBackendProvider() {
        if (this.mAnimatedDrawableBackendProvider == null) {
            this.mAnimatedDrawableBackendProvider = new AnimatedDrawableBackendProvider() { // from class: com.facebook.fresco.animation.factory.AnimatedFactoryV2Impl.5
                @Override // com.facebook.imagepipeline.animated.impl.AnimatedDrawableBackendProvider
                public AnimatedDrawableBackend get(AnimatedImageResult animatedImageResult, @Nullable Rect bounds) {
                    return new AnimatedDrawableBackendImpl(AnimatedFactoryV2Impl.this.getAnimatedDrawableUtil(), animatedImageResult, bounds, AnimatedFactoryV2Impl.this.mDownscaleFrameToDrawableDimensions);
                }
            };
        }
        return this.mAnimatedDrawableBackendProvider;
    }

    private AnimatedImageFactory buildAnimatedImageFactory() {
        return new AnimatedImageFactoryImpl(new AnimatedDrawableBackendProvider() { // from class: com.facebook.fresco.animation.factory.AnimatedFactoryV2Impl.6
            @Override // com.facebook.imagepipeline.animated.impl.AnimatedDrawableBackendProvider
            public AnimatedDrawableBackend get(AnimatedImageResult imageResult, @Nullable Rect bounds) {
                return new AnimatedDrawableBackendImpl(AnimatedFactoryV2Impl.this.getAnimatedDrawableUtil(), imageResult, bounds, AnimatedFactoryV2Impl.this.mDownscaleFrameToDrawableDimensions);
            }
        }, this.mPlatformBitmapFactory);
    }
}
