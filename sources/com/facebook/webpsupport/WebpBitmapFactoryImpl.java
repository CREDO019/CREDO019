package com.facebook.webpsupport;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Rect;
import android.os.Build;
import android.util.TypedValue;
import com.facebook.common.webp.BitmapCreator;
import com.facebook.common.webp.WebpBitmapFactory;
import com.facebook.common.webp.WebpSupportStatus;
import com.facebook.imagepipeline.nativecode.StaticWebpNativeLoader;
import java.io.BufferedInputStream;
import java.io.FileDescriptor;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import javax.annotation.Nullable;

/* loaded from: classes.dex */
public class WebpBitmapFactoryImpl implements WebpBitmapFactory {
    private static final int HEADER_SIZE = 20;
    public static final boolean IN_BITMAP_SUPPORTED;
    private static final int IN_TEMP_BUFFER_SIZE = 8192;
    private static BitmapCreator mBitmapCreator;
    private static WebpBitmapFactory.WebpErrorLogger mWebpErrorLogger;

    @Nullable
    private static native Bitmap nativeDecodeByteArray(byte[] data, int offset, int length, @Nullable BitmapFactory.Options opts, float scale, byte[] inTempStorage);

    @Nullable
    private static native Bitmap nativeDecodeStream(InputStream is, @Nullable BitmapFactory.Options options, float scale, byte[] inTempStorage);

    private static native long nativeSeek(FileDescriptor fd, long offset, boolean absolute);

    static {
        IN_BITMAP_SUPPORTED = Build.VERSION.SDK_INT >= 11;
    }

    @Override // com.facebook.common.webp.WebpBitmapFactory
    public void setBitmapCreator(final BitmapCreator bitmapCreator) {
        mBitmapCreator = bitmapCreator;
    }

    private static InputStream wrapToMarkSupportedStream(InputStream inputStream) {
        return !inputStream.markSupported() ? new BufferedInputStream(inputStream, 20) : inputStream;
    }

    @Nullable
    private static byte[] getWebpHeader(InputStream inputStream, @Nullable BitmapFactory.Options opts) {
        inputStream.mark(20);
        byte[] bArr = (opts == null || opts.inTempStorage == null || opts.inTempStorage.length < 20) ? new byte[20] : opts.inTempStorage;
        try {
            inputStream.read(bArr, 0, 20);
            inputStream.reset();
            return bArr;
        } catch (IOException unused) {
            return null;
        }
    }

    private static void setDensityFromOptions(@Nullable Bitmap outputBitmap, @Nullable BitmapFactory.Options opts) {
        if (outputBitmap == null || opts == null) {
            return;
        }
        int r0 = opts.inDensity;
        if (r0 != 0) {
            outputBitmap.setDensity(r0);
            int r1 = opts.inTargetDensity;
            if (r1 == 0 || r0 == r1 || r0 == opts.inScreenDensity || !opts.inScaled) {
                return;
            }
            outputBitmap.setDensity(r1);
        } else if (!IN_BITMAP_SUPPORTED || opts.inBitmap == null) {
        } else {
            outputBitmap.setDensity(160);
        }
    }

    @Override // com.facebook.common.webp.WebpBitmapFactory
    public void setWebpErrorLogger(WebpBitmapFactory.WebpErrorLogger webpErrorLogger) {
        mWebpErrorLogger = webpErrorLogger;
    }

    @Override // com.facebook.common.webp.WebpBitmapFactory
    @Nullable
    public Bitmap decodeFileDescriptor(FileDescriptor fd, @Nullable Rect outPadding, @Nullable BitmapFactory.Options opts) {
        return hookDecodeFileDescriptor(fd, outPadding, opts);
    }

    @Override // com.facebook.common.webp.WebpBitmapFactory
    @Nullable
    public Bitmap decodeStream(InputStream inputStream, @Nullable Rect outPadding, @Nullable BitmapFactory.Options opts) {
        return hookDecodeStream(inputStream, outPadding, opts);
    }

    @Override // com.facebook.common.webp.WebpBitmapFactory
    @Nullable
    public Bitmap decodeFile(String pathName, @Nullable BitmapFactory.Options opts) {
        return hookDecodeFile(pathName, opts);
    }

    @Override // com.facebook.common.webp.WebpBitmapFactory
    @Nullable
    public Bitmap decodeByteArray(byte[] array, int offset, int length, @Nullable BitmapFactory.Options opts) {
        return hookDecodeByteArray(array, offset, length, opts);
    }

    @Nullable
    public static Bitmap hookDecodeByteArray(byte[] array, int offset, int length, @Nullable BitmapFactory.Options opts) {
        Bitmap originalDecodeByteArray;
        StaticWebpNativeLoader.ensure();
        if (WebpSupportStatus.sIsWebpSupportRequired && WebpSupportStatus.isWebpHeader(array, offset, length)) {
            originalDecodeByteArray = nativeDecodeByteArray(array, offset, length, opts, getScaleFromOptions(opts), getInTempStorageFromOptions(opts));
            if (originalDecodeByteArray == null) {
                sendWebpErrorLog("webp_direct_decode_array");
            }
            setWebpBitmapOptions(originalDecodeByteArray, opts);
        } else {
            originalDecodeByteArray = originalDecodeByteArray(array, offset, length, opts);
            if (originalDecodeByteArray == null) {
                sendWebpErrorLog("webp_direct_decode_array_failed_on_no_webp");
            }
        }
        return originalDecodeByteArray;
    }

    @Nullable
    private static Bitmap originalDecodeByteArray(byte[] array, int offset, int length, @Nullable BitmapFactory.Options opts) {
        return BitmapFactory.decodeByteArray(array, offset, length, opts);
    }

    @Nullable
    public static Bitmap hookDecodeByteArray(byte[] array, int offset, int length) {
        return hookDecodeByteArray(array, offset, length, null);
    }

    @Nullable
    private static Bitmap originalDecodeByteArray(byte[] array, int offset, int length) {
        return BitmapFactory.decodeByteArray(array, offset, length);
    }

    @Nullable
    public static Bitmap hookDecodeStream(InputStream inputStream, @Nullable Rect outPadding, @Nullable BitmapFactory.Options opts) {
        Bitmap originalDecodeStream;
        StaticWebpNativeLoader.ensure();
        InputStream wrapToMarkSupportedStream = wrapToMarkSupportedStream(inputStream);
        byte[] webpHeader = getWebpHeader(wrapToMarkSupportedStream, opts);
        if (WebpSupportStatus.sIsWebpSupportRequired && webpHeader != null && WebpSupportStatus.isWebpHeader(webpHeader, 0, 20)) {
            originalDecodeStream = nativeDecodeStream(wrapToMarkSupportedStream, opts, getScaleFromOptions(opts), getInTempStorageFromOptions(opts));
            if (originalDecodeStream == null) {
                sendWebpErrorLog("webp_direct_decode_stream");
            }
            setWebpBitmapOptions(originalDecodeStream, opts);
            setPaddingDefaultValues(outPadding);
        } else {
            originalDecodeStream = originalDecodeStream(wrapToMarkSupportedStream, outPadding, opts);
            if (originalDecodeStream == null) {
                sendWebpErrorLog("webp_direct_decode_stream_failed_on_no_webp");
            }
        }
        return originalDecodeStream;
    }

    @Nullable
    private static Bitmap originalDecodeStream(InputStream inputStream, @Nullable Rect outPadding, @Nullable BitmapFactory.Options opts) {
        return BitmapFactory.decodeStream(inputStream, outPadding, opts);
    }

    @Nullable
    public static Bitmap hookDecodeStream(InputStream inputStream) {
        return hookDecodeStream(inputStream, null, null);
    }

    @Nullable
    private static Bitmap originalDecodeStream(InputStream inputStream) {
        return BitmapFactory.decodeStream(inputStream);
    }

    @Nullable
    public static Bitmap hookDecodeFile(String pathName, @Nullable BitmapFactory.Options opts) {
        Bitmap bitmap = null;
        try {
            FileInputStream fileInputStream = new FileInputStream(pathName);
            bitmap = hookDecodeStream(fileInputStream, null, opts);
            fileInputStream.close();
        } catch (Exception unused) {
        }
        return bitmap;
    }

    @Nullable
    public static Bitmap hookDecodeFile(String pathName) {
        return hookDecodeFile(pathName, null);
    }

    @Nullable
    public static Bitmap hookDecodeResourceStream(@Nullable Resources res, @Nullable TypedValue value, InputStream is, @Nullable Rect pad, @Nullable BitmapFactory.Options opts) {
        if (opts == null) {
            opts = new BitmapFactory.Options();
        }
        if (opts.inDensity == 0 && value != null) {
            int r2 = value.density;
            if (r2 == 0) {
                opts.inDensity = 160;
            } else if (r2 != 65535) {
                opts.inDensity = r2;
            }
        }
        if (opts.inTargetDensity == 0 && res != null) {
            opts.inTargetDensity = res.getDisplayMetrics().densityDpi;
        }
        return hookDecodeStream(is, pad, opts);
    }

    @Nullable
    private static Bitmap originalDecodeResourceStream(Resources res, TypedValue value, InputStream is, Rect pad, BitmapFactory.Options opts) {
        return BitmapFactory.decodeResourceStream(res, value, is, pad, opts);
    }

    @Nullable
    public static Bitmap hookDecodeResource(Resources res, int id, @Nullable BitmapFactory.Options opts) {
        TypedValue typedValue = new TypedValue();
        Bitmap bitmap = null;
        try {
            InputStream openRawResource = res.openRawResource(id, typedValue);
            bitmap = hookDecodeResourceStream(res, typedValue, openRawResource, null, opts);
            if (openRawResource != null) {
                openRawResource.close();
            }
        } catch (Exception unused) {
        }
        if (!IN_BITMAP_SUPPORTED || bitmap != null || opts == null || opts.inBitmap == null) {
            return bitmap;
        }
        throw new IllegalArgumentException("Problem decoding into existing bitmap");
    }

    @Nullable
    private static Bitmap originalDecodeResource(Resources res, int id, BitmapFactory.Options opts) {
        return BitmapFactory.decodeResource(res, id, opts);
    }

    @Nullable
    public static Bitmap hookDecodeResource(Resources res, int id) {
        return hookDecodeResource(res, id, null);
    }

    @Nullable
    private static Bitmap originalDecodeResource(Resources res, int id) {
        return BitmapFactory.decodeResource(res, id);
    }

    private static boolean setOutDimensions(@Nullable BitmapFactory.Options options, int imageWidth, int imageHeight) {
        if (options == null || !options.inJustDecodeBounds) {
            return false;
        }
        options.outWidth = imageWidth;
        options.outHeight = imageHeight;
        return true;
    }

    private static void setPaddingDefaultValues(@Nullable Rect padding) {
        if (padding != null) {
            padding.top = -1;
            padding.left = -1;
            padding.bottom = -1;
            padding.right = -1;
        }
    }

    private static void setBitmapSize(@Nullable BitmapFactory.Options options, int width, int height) {
        if (options != null) {
            options.outWidth = width;
            options.outHeight = height;
        }
    }

    @Nullable
    private static Bitmap originalDecodeFile(String pathName, @Nullable BitmapFactory.Options opts) {
        return BitmapFactory.decodeFile(pathName, opts);
    }

    @Nullable
    private static Bitmap originalDecodeFile(String pathName) {
        return BitmapFactory.decodeFile(pathName);
    }

    @Nullable
    public static Bitmap hookDecodeFileDescriptor(FileDescriptor fd, @Nullable Rect outPadding, @Nullable BitmapFactory.Options opts) {
        Bitmap originalDecodeFileDescriptor;
        StaticWebpNativeLoader.ensure();
        long nativeSeek = nativeSeek(fd, 0L, false);
        if (nativeSeek != -1) {
            InputStream wrapToMarkSupportedStream = wrapToMarkSupportedStream(new FileInputStream(fd));
            try {
                byte[] webpHeader = getWebpHeader(wrapToMarkSupportedStream, opts);
                if (WebpSupportStatus.sIsWebpSupportRequired && webpHeader != null && WebpSupportStatus.isWebpHeader(webpHeader, 0, 20)) {
                    originalDecodeFileDescriptor = nativeDecodeStream(wrapToMarkSupportedStream, opts, getScaleFromOptions(opts), getInTempStorageFromOptions(opts));
                    if (originalDecodeFileDescriptor == null) {
                        sendWebpErrorLog("webp_direct_decode_fd");
                    }
                    setPaddingDefaultValues(outPadding);
                    setWebpBitmapOptions(originalDecodeFileDescriptor, opts);
                } else {
                    nativeSeek(fd, nativeSeek, true);
                    originalDecodeFileDescriptor = originalDecodeFileDescriptor(fd, outPadding, opts);
                    if (originalDecodeFileDescriptor == null) {
                        sendWebpErrorLog("webp_direct_decode_fd_failed_on_no_webp");
                    }
                }
                try {
                } catch (Throwable unused) {
                    return originalDecodeFileDescriptor;
                }
            } finally {
                try {
                    wrapToMarkSupportedStream.close();
                } catch (Throwable unused2) {
                }
            }
        } else {
            Bitmap hookDecodeStream = hookDecodeStream(new FileInputStream(fd), outPadding, opts);
            setPaddingDefaultValues(outPadding);
            return hookDecodeStream;
        }
    }

    @Nullable
    private static Bitmap originalDecodeFileDescriptor(FileDescriptor fd, @Nullable Rect outPadding, @Nullable BitmapFactory.Options opts) {
        return BitmapFactory.decodeFileDescriptor(fd, outPadding, opts);
    }

    @Nullable
    public static Bitmap hookDecodeFileDescriptor(FileDescriptor fd) {
        return hookDecodeFileDescriptor(fd, null, null);
    }

    @Nullable
    private static Bitmap originalDecodeFileDescriptor(FileDescriptor fd) {
        return BitmapFactory.decodeFileDescriptor(fd);
    }

    private static void setWebpBitmapOptions(@Nullable Bitmap bitmap, @Nullable BitmapFactory.Options opts) {
        setDensityFromOptions(bitmap, opts);
        if (opts != null) {
            opts.outMimeType = "image/webp";
        }
    }

    private static boolean shouldPremultiply(@Nullable BitmapFactory.Options options) {
        if (Build.VERSION.SDK_INT < 19 || options == null) {
            return true;
        }
        return options.inPremultiplied;
    }

    @Nullable
    private static Bitmap createBitmap(int width, int height, @Nullable BitmapFactory.Options options) {
        if (IN_BITMAP_SUPPORTED && options != null && options.inBitmap != null && options.inBitmap.isMutable()) {
            return options.inBitmap;
        }
        return mBitmapCreator.createNakedBitmap(width, height, Bitmap.Config.ARGB_8888);
    }

    private static byte[] getInTempStorageFromOptions(@Nullable final BitmapFactory.Options options) {
        return (options == null || options.inTempStorage == null) ? new byte[8192] : options.inTempStorage;
    }

    private static float getScaleFromOptions(@Nullable BitmapFactory.Options options) {
        if (options != null) {
            int r1 = options.inSampleSize;
            float f = r1 > 1 ? 1.0f / r1 : 1.0f;
            if (options.inScaled) {
                int r12 = options.inDensity;
                int r2 = options.inTargetDensity;
                return (r12 == 0 || r2 == 0 || r12 == options.inScreenDensity) ? f : r2 / r12;
            }
            return f;
        }
        return 1.0f;
    }

    private static void sendWebpErrorLog(String message) {
        WebpBitmapFactory.WebpErrorLogger webpErrorLogger = mWebpErrorLogger;
        if (webpErrorLogger != null) {
            webpErrorLogger.onWebpErrorLog(message, "decoding_failure");
        }
    }
}
