package com.facebook.imagepipeline.nativecode;

import android.os.Build;
import com.facebook.common.internal.Preconditions;
import com.facebook.common.webp.WebpSupportStatus;
import com.facebook.imageformat.DefaultImageFormats;
import com.facebook.imageformat.ImageFormat;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/* loaded from: classes.dex */
public class WebpTranscoderImpl implements WebpTranscoder {
    private static native void nativeTranscodeWebpToJpeg(InputStream inputStream, OutputStream outputStream, int quality) throws IOException;

    private static native void nativeTranscodeWebpToPng(InputStream inputStream, OutputStream outputStream) throws IOException;

    @Override // com.facebook.imagepipeline.nativecode.WebpTranscoder
    public boolean isWebpNativelySupported(ImageFormat webpFormat) {
        if (webpFormat == DefaultImageFormats.WEBP_SIMPLE) {
            return Build.VERSION.SDK_INT >= 14;
        } else if (webpFormat == DefaultImageFormats.WEBP_LOSSLESS || webpFormat == DefaultImageFormats.WEBP_EXTENDED || webpFormat == DefaultImageFormats.WEBP_EXTENDED_WITH_ALPHA) {
            return WebpSupportStatus.sIsExtendedWebpSupported;
        } else {
            if (webpFormat == DefaultImageFormats.WEBP_ANIMATED) {
                return false;
            }
            throw new IllegalArgumentException("Image format is not a WebP.");
        }
    }

    @Override // com.facebook.imagepipeline.nativecode.WebpTranscoder
    public void transcodeWebpToJpeg(InputStream inputStream, OutputStream outputStream, int quality) throws IOException {
        StaticWebpNativeLoader.ensure();
        nativeTranscodeWebpToJpeg((InputStream) Preconditions.checkNotNull(inputStream), (OutputStream) Preconditions.checkNotNull(outputStream), quality);
    }

    @Override // com.facebook.imagepipeline.nativecode.WebpTranscoder
    public void transcodeWebpToPng(InputStream inputStream, OutputStream outputStream) throws IOException {
        StaticWebpNativeLoader.ensure();
        nativeTranscodeWebpToPng((InputStream) Preconditions.checkNotNull(inputStream), (OutputStream) Preconditions.checkNotNull(outputStream));
    }
}
