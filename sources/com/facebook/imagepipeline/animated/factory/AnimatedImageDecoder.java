package com.facebook.imagepipeline.animated.factory;

import com.facebook.imagepipeline.animated.base.AnimatedImage;
import com.facebook.imagepipeline.common.ImageDecodeOptions;
import java.nio.ByteBuffer;

/* loaded from: classes.dex */
public interface AnimatedImageDecoder {
    AnimatedImage decodeFromByteBuffer(ByteBuffer byteBuffer, ImageDecodeOptions options);

    AnimatedImage decodeFromNativeMemory(long nativePtr, int sizeInBytes, ImageDecodeOptions options);
}
