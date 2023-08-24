package com.facebook.imagepipeline.animated.factory;

import android.graphics.Bitmap;
import com.facebook.imagepipeline.common.ImageDecodeOptions;
import com.facebook.imagepipeline.image.CloseableImage;
import com.facebook.imagepipeline.image.EncodedImage;

/* loaded from: classes.dex */
public interface AnimatedImageFactory {
    CloseableImage decodeGif(final EncodedImage encodedImage, final ImageDecodeOptions options, final Bitmap.Config bitmapConfig);

    CloseableImage decodeWebP(final EncodedImage encodedImage, final ImageDecodeOptions options, final Bitmap.Config bitmapConfig);
}
