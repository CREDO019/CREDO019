package com.canhub.cropper;

import android.content.ContentResolver;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.graphics.Rect;
import android.graphics.RectF;
import android.net.Uri;
import android.os.Environment;
import android.util.Log;
import android.util.Pair;
import androidx.exifinterface.media.ExifInterface;
import com.canhub.cropper.CropException;
import com.canhub.cropper.CropImageView;
import com.canhub.cropper.common.CommonVersionCheck;
import com.canhub.cropper.utils.GetUriForFile;
import com.facebook.imagepipeline.common.RotationOptions;
import com.facebook.imagepipeline.producers.DecodeProducer;
import java.io.Closeable;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.lang.ref.WeakReference;
import javax.microedition.khronos.egl.EGL;
import javax.microedition.khronos.egl.EGL10;
import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.egl.EGLContext;
import javax.microedition.khronos.egl.EGLDisplay;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.math.MathKt;
import org.apache.commons.p028io.IOUtils;

/* compiled from: BitmapUtils.kt */
@Metadata(m184d1 = {"\u0000 \u0001\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0014\n\u0002\b\u0007\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0002\b\f\n\u0002\u0010\u0007\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0010\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\bÀ\u0002\u0018\u00002\u00020\u0001:\u0002ijB\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u001a\u0010#\u001a\u0004\u0018\u00010$2\u0006\u0010%\u001a\u00020&2\u0006\u0010'\u001a\u00020(H\u0002J\u0018\u0010)\u001a\u00020\f2\u0006\u0010*\u001a\u00020\f2\u0006\u0010+\u001a\u00020\fH\u0002J(\u0010,\u001a\u00020\f2\u0006\u0010*\u001a\u00020\f2\u0006\u0010+\u001a\u00020\f2\u0006\u0010-\u001a\u00020\f2\u0006\u0010.\u001a\u00020\fH\u0002J\u0012\u0010/\u001a\u0002002\b\u00101\u001a\u0004\u0018\u000102H\u0002Jp\u00103\u001a\u0002042\u0006\u0010%\u001a\u00020&2\u0006\u00105\u001a\u00020$2\u0006\u00106\u001a\u00020\u000e2\u0006\u00107\u001a\u00020\f2\u0006\u00108\u001a\u0002092\u0006\u0010:\u001a\u00020\f2\u0006\u0010;\u001a\u00020\f2\u0006\u0010<\u001a\u00020\f2\u0006\u0010=\u001a\u00020\u00042\u0006\u0010*\u001a\u00020\f2\u0006\u0010+\u001a\u00020\f2\u0006\u0010>\u001a\u0002092\u0006\u0010?\u001a\u000209H\u0002Jx\u00103\u001a\u0002042\u0006\u0010%\u001a\u00020&2\u0006\u00105\u001a\u00020$2\u0006\u00106\u001a\u00020\u000e2\u0006\u00107\u001a\u00020\f2\u0006\u0010@\u001a\u00020\f2\u0006\u0010A\u001a\u00020\f2\u0006\u00108\u001a\u0002092\u0006\u0010:\u001a\u00020\f2\u0006\u0010;\u001a\u00020\f2\u0006\u0010-\u001a\u00020\f2\u0006\u0010.\u001a\u00020\f2\u0006\u0010>\u001a\u0002092\u0006\u0010?\u001a\u0002092\u0006\u0010<\u001a\u00020\fH\u0002Jp\u00103\u001a\u0002042\u0006\u0010%\u001a\u00020&2\b\u00105\u001a\u0004\u0018\u00010$2\u0006\u00106\u001a\u00020\u000e2\u0006\u00107\u001a\u00020\f2\u0006\u0010@\u001a\u00020\f2\u0006\u0010A\u001a\u00020\f2\u0006\u00108\u001a\u0002092\u0006\u0010:\u001a\u00020\f2\u0006\u0010;\u001a\u00020\f2\u0006\u0010-\u001a\u00020\f2\u0006\u0010.\u001a\u00020\f2\u0006\u0010>\u001a\u0002092\u0006\u0010?\u001a\u000209JH\u0010B\u001a\u0002042\b\u0010C\u001a\u0004\u0018\u00010\u001b2\u0006\u00106\u001a\u00020\u000e2\u0006\u00107\u001a\u00020\f2\u0006\u00108\u001a\u0002092\u0006\u0010:\u001a\u00020\f2\u0006\u0010;\u001a\u00020\f2\u0006\u0010>\u001a\u0002092\u0006\u0010?\u001a\u000209JR\u0010D\u001a\u0004\u0018\u00010\u001b2\u0006\u0010C\u001a\u00020\u001b2\u0006\u00106\u001a\u00020\u000e2\u0006\u00107\u001a\u00020\f2\u0006\u00108\u001a\u0002092\u0006\u0010:\u001a\u00020\f2\u0006\u0010;\u001a\u00020\f2\u0006\u0010E\u001a\u00020F2\u0006\u0010>\u001a\u0002092\u0006\u0010?\u001a\u000209H\u0002JD\u0010G\u001a\u0004\u0018\u00010\u001b2\b\u0010C\u001a\u0004\u0018\u00010\u001b2\u0006\u00106\u001a\u00020\u000e2\u0006\u0010=\u001a\u00020\u00042\u0006\u00107\u001a\u00020\f2\u0006\u00108\u001a\u0002092\u0006\u0010:\u001a\u00020\f2\u0006\u0010;\u001a\u00020\fH\u0002J\"\u0010H\u001a\u0004\u0018\u00010\u001b2\u0006\u0010I\u001a\u00020J2\u0006\u0010K\u001a\u00020$2\u0006\u0010L\u001a\u00020MH\u0002J\u0018\u0010N\u001a\u00020M2\u0006\u0010I\u001a\u00020J2\u0006\u0010K\u001a\u00020$H\u0002J&\u0010O\u001a\u0002042\u0006\u0010%\u001a\u00020&2\u0006\u0010K\u001a\u00020$2\u0006\u0010-\u001a\u00020\f2\u0006\u0010.\u001a\u00020\fJ8\u0010P\u001a\u0002042\u0006\u0010%\u001a\u00020&2\u0006\u0010K\u001a\u00020$2\u0006\u0010=\u001a\u00020\u00042\u0006\u0010-\u001a\u00020\f2\u0006\u0010.\u001a\u00020\f2\u0006\u0010<\u001a\u00020\fH\u0002J \u0010Q\u001a\u0002002\u0006\u0010=\u001a\u00020\u00042\u0006\u0010:\u001a\u00020\f2\u0006\u0010;\u001a\u00020\fH\u0002J\u000e\u0010R\u001a\u00020F2\u0006\u00106\u001a\u00020\u000eJ\u000e\u0010S\u001a\u00020F2\u0006\u00106\u001a\u00020\u000eJ\u000e\u0010T\u001a\u00020F2\u0006\u00106\u001a\u00020\u000eJ6\u0010U\u001a\u00020\u00042\u0006\u00106\u001a\u00020\u000e2\u0006\u0010V\u001a\u00020\f2\u0006\u0010W\u001a\u00020\f2\u0006\u00108\u001a\u0002092\u0006\u0010:\u001a\u00020\f2\u0006\u0010;\u001a\u00020\fJ\u000e\u0010X\u001a\u00020F2\u0006\u00106\u001a\u00020\u000eJ\u000e\u0010Y\u001a\u00020F2\u0006\u00106\u001a\u00020\u000eJ\u000e\u0010Z\u001a\u00020F2\u0006\u00106\u001a\u00020\u000eJ\u000e\u0010[\u001a\u00020F2\u0006\u00106\u001a\u00020\u000eJ\u000e\u0010\\\u001a\u00020F2\u0006\u00106\u001a\u00020\u000eJ(\u0010]\u001a\u00020\u001b2\b\u0010C\u001a\u0004\u0018\u00010\u001b2\u0006\u0010-\u001a\u00020\f2\u0006\u0010.\u001a\u00020\f2\u0006\u0010L\u001a\u00020^J(\u0010_\u001a\u00020\u001b2\u0006\u0010C\u001a\u00020\u001b2\u0006\u0010`\u001a\u00020\f2\u0006\u0010>\u001a\u0002092\u0006\u0010?\u001a\u000209H\u0002J\"\u0010a\u001a\u00020b2\b\u0010C\u001a\u0004\u0018\u00010\u001b2\u0006\u0010%\u001a\u00020&2\b\u0010K\u001a\u0004\u0018\u00010$J\u0018\u0010a\u001a\u00020b2\b\u0010C\u001a\u0004\u0018\u00010\u001b2\u0006\u0010c\u001a\u00020dJ2\u0010e\u001a\u0004\u0018\u00010$2\u0006\u0010%\u001a\u00020&2\u0006\u0010C\u001a\u00020\u001b2\u0006\u0010'\u001a\u00020(2\u0006\u0010f\u001a\u00020\f2\b\u0010g\u001a\u0004\u0018\u00010$J$\u0010h\u001a\u0004\u0018\u00010$2\u0006\u0010%\u001a\u00020&2\b\u0010C\u001a\u0004\u0018\u00010\u001b2\b\u0010g\u001a\u0004\u0018\u00010$R\u0011\u0010\u0003\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006R\u0011\u0010\u0007\u001a\u00020\b¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u000e\u0010\u000b\u001a\u00020\fX\u0082T¢\u0006\u0002\n\u0000R\u0011\u0010\r\u001a\u00020\u000e¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0010R\u0011\u0010\u0011\u001a\u00020\u000e¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u0010R\u0011\u0010\u0013\u001a\u00020\b¢\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\nR\u000e\u0010\u0015\u001a\u00020\u0016X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0017\u001a\u00020\fX\u0082\u000e¢\u0006\u0002\n\u0000R.\u0010\u0018\u001a\u0016\u0012\u0004\u0012\u00020\u0016\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u001b0\u001a\u0018\u00010\u0019X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001c\u0010\u001d\"\u0004\b\u001e\u0010\u001fR\u0014\u0010 \u001a\u00020\f8BX\u0082\u0004¢\u0006\u0006\u001a\u0004\b!\u0010\"¨\u0006k"}, m183d2 = {"Lcom/canhub/cropper/BitmapUtils;", "", "()V", "EMPTY_RECT", "Landroid/graphics/Rect;", "getEMPTY_RECT", "()Landroid/graphics/Rect;", "EMPTY_RECT_F", "Landroid/graphics/RectF;", "getEMPTY_RECT_F", "()Landroid/graphics/RectF;", "IMAGE_MAX_BITMAP_DIMENSION", "", "POINTS", "", "getPOINTS", "()[F", "POINTS2", "getPOINTS2", "RECT", "getRECT", "WRITE_AND_TRUNCATE", "", "mMaxTextureSize", "mStateBitmap", "Landroid/util/Pair;", "Ljava/lang/ref/WeakReference;", "Landroid/graphics/Bitmap;", "getMStateBitmap", "()Landroid/util/Pair;", "setMStateBitmap", "(Landroid/util/Pair;)V", "maxTextureSize", "getMaxTextureSize", "()I", "buildUri", "Landroid/net/Uri;", "context", "Landroid/content/Context;", "compressFormat", "Landroid/graphics/Bitmap$CompressFormat;", "calculateInSampleSizeByMaxTextureSize", "width", "height", "calculateInSampleSizeByReqestedSize", "reqWidth", "reqHeight", "closeSafe", "", "closeable", "Ljava/io/Closeable;", "cropBitmap", "Lcom/canhub/cropper/BitmapUtils$BitmapSampled;", "loadedImageUri", "points", "degreesRotated", "fixAspectRatio", "", "aspectRatioX", "aspectRatioY", "sampleMulti", "rect", "flipHorizontally", "flipVertically", "orgWidth", "orgHeight", "cropBitmapObjectHandleOOM", "bitmap", "cropBitmapObjectWithScale", "scale", "", "cropForRotatedImage", "decodeImage", "resolver", "Landroid/content/ContentResolver;", "uri", "options", "Landroid/graphics/BitmapFactory$Options;", "decodeImageForOption", "decodeSampledBitmap", "decodeSampledBitmapRegion", "fixRectForAspectRatio", "getRectBottom", "getRectCenterX", "getRectCenterY", "getRectFromPoints", "imageWidth", "imageHeight", "getRectHeight", "getRectLeft", "getRectRight", "getRectTop", "getRectWidth", "resizeBitmap", "Lcom/canhub/cropper/CropImageView$RequestSizeOptions;", "rotateAndFlipBitmapInt", "degrees", "rotateBitmapByExif", "Lcom/canhub/cropper/BitmapUtils$RotateBitmapResult;", "exif", "Landroidx/exifinterface/media/ExifInterface;", "writeBitmapToUri", "compressQuality", "customOutputUri", "writeTempStateStoreBitmap", "BitmapSampled", "RotateBitmapResult", "cropper_release"}, m182k = 1, m181mv = {1, 5, 1}, m179xi = 48)
/* loaded from: classes.dex */
public final class BitmapUtils {
    private static final int IMAGE_MAX_BITMAP_DIMENSION = 2048;
    private static final String WRITE_AND_TRUNCATE = "wt";
    private static int mMaxTextureSize;
    private static Pair<String, WeakReference<Bitmap>> mStateBitmap;
    public static final BitmapUtils INSTANCE = new BitmapUtils();
    private static final Rect EMPTY_RECT = new Rect();
    private static final RectF EMPTY_RECT_F = new RectF();
    private static final RectF RECT = new RectF();
    private static final float[] POINTS = new float[6];
    private static final float[] POINTS2 = new float[6];

    /* compiled from: BitmapUtils.kt */
    @Metadata(m182k = 3, m181mv = {1, 5, 1}, m179xi = 48)
    /* loaded from: classes.dex */
    public /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] r0 = new int[Bitmap.CompressFormat.values().length];
            r0[Bitmap.CompressFormat.JPEG.ordinal()] = 1;
            r0[Bitmap.CompressFormat.PNG.ordinal()] = 2;
            $EnumSwitchMapping$0 = r0;
        }
    }

    private BitmapUtils() {
    }

    public final Rect getEMPTY_RECT() {
        return EMPTY_RECT;
    }

    public final RectF getEMPTY_RECT_F() {
        return EMPTY_RECT_F;
    }

    public final RectF getRECT() {
        return RECT;
    }

    public final float[] getPOINTS() {
        return POINTS;
    }

    public final float[] getPOINTS2() {
        return POINTS2;
    }

    public final Pair<String, WeakReference<Bitmap>> getMStateBitmap() {
        return mStateBitmap;
    }

    public final void setMStateBitmap(Pair<String, WeakReference<Bitmap>> pair) {
        mStateBitmap = pair;
    }

    public final RotateBitmapResult rotateBitmapByExif(Bitmap bitmap, Context context, Uri uri) {
        Intrinsics.checkNotNullParameter(context, "context");
        ExifInterface exifInterface = null;
        try {
            ContentResolver contentResolver = context.getContentResolver();
            Intrinsics.checkNotNull(uri);
            InputStream openInputStream = contentResolver.openInputStream(uri);
            if (openInputStream != null) {
                ExifInterface exifInterface2 = new ExifInterface(openInputStream);
                try {
                    openInputStream.close();
                } catch (Exception unused) {
                }
                exifInterface = exifInterface2;
            }
        } catch (Exception unused2) {
        }
        return exifInterface != null ? rotateBitmapByExif(bitmap, exifInterface) : new RotateBitmapResult(bitmap, 0);
    }

    public final RotateBitmapResult rotateBitmapByExif(Bitmap bitmap, ExifInterface exif) {
        Intrinsics.checkNotNullParameter(exif, "exif");
        int attributeInt = exif.getAttributeInt(ExifInterface.TAG_ORIENTATION, 1);
        return new RotateBitmapResult(bitmap, attributeInt != 3 ? attributeInt != 6 ? attributeInt != 8 ? 0 : 270 : 90 : RotationOptions.ROTATE_180);
    }

    public final BitmapSampled decodeSampledBitmap(Context context, Uri uri, int r6, int r7) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(uri, "uri");
        try {
            ContentResolver resolver = context.getContentResolver();
            Intrinsics.checkNotNullExpressionValue(resolver, "resolver");
            BitmapFactory.Options decodeImageForOption = decodeImageForOption(resolver, uri);
            if (decodeImageForOption.outWidth == -1 && decodeImageForOption.outHeight == -1) {
                throw new RuntimeException("File is not a picture");
            }
            decodeImageForOption.inSampleSize = Math.max(calculateInSampleSizeByReqestedSize(decodeImageForOption.outWidth, decodeImageForOption.outHeight, r6, r7), calculateInSampleSizeByMaxTextureSize(decodeImageForOption.outWidth, decodeImageForOption.outHeight));
            return new BitmapSampled(decodeImage(resolver, uri, decodeImageForOption), decodeImageForOption.inSampleSize);
        } catch (Exception e) {
            throw new CropException.FailedToLoadBitmap(uri, e.getMessage());
        }
    }

    public final BitmapSampled cropBitmapObjectHandleOOM(Bitmap bitmap, float[] points, int r17, boolean z, int r19, int r20, boolean z2, boolean z3) {
        Intrinsics.checkNotNullParameter(points, "points");
        int r13 = 1;
        do {
            try {
                Intrinsics.checkNotNull(bitmap);
                return new BitmapSampled(cropBitmapObjectWithScale(bitmap, points, r17, z, r19, r20, 1 / r13, z2, z3), r13);
            } catch (OutOfMemoryError e) {
                r13 *= 2;
                if (r13 > 8) {
                    throw e;
                }
            }
        } while (r13 > 8);
        throw e;
    }

    private final Bitmap cropBitmapObjectWithScale(Bitmap bitmap, float[] fArr, int r19, boolean z, int r21, int r22, float f, boolean z2, boolean z3) {
        float f2 = f;
        Rect rectFromPoints = getRectFromPoints(fArr, bitmap.getWidth(), bitmap.getHeight(), z, r21, r22);
        Matrix matrix = new Matrix();
        matrix.setRotate(r19, bitmap.getWidth() / 2.0f, bitmap.getHeight() / 2.0f);
        float f3 = z2 ? -f2 : f2;
        if (z3) {
            f2 = -f2;
        }
        matrix.postScale(f3, f2);
        Bitmap createBitmap = Bitmap.createBitmap(bitmap, rectFromPoints.left, rectFromPoints.top, rectFromPoints.width(), rectFromPoints.height(), matrix, true);
        if (Intrinsics.areEqual(createBitmap, bitmap)) {
            createBitmap = bitmap.copy(bitmap.getConfig(), false);
        }
        Bitmap bitmap2 = createBitmap;
        return r19 % 90 != 0 ? cropForRotatedImage(bitmap2, fArr, rectFromPoints, r19, z, r21, r22) : bitmap2;
    }

    public final BitmapSampled cropBitmap(Context context, Uri uri, float[] points, int r21, int r22, int r23, boolean z, int r25, int r26, int r27, int r28, boolean z2, boolean z3) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(points, "points");
        int r16 = 1;
        while (true) {
            try {
                Intrinsics.checkNotNull(uri);
                return cropBitmap(context, uri, points, r21, r22, r23, z, r25, r26, r27, r28, z2, z3, r16);
            } catch (OutOfMemoryError e) {
                int r1 = r16 * 2;
                if (r1 > 16) {
                    String message = e.getMessage();
                    throw new RuntimeException("Failed to handle OOM by sampling (" + r1 + "): " + uri + IOUtils.LINE_SEPARATOR_WINDOWS + message, e);
                }
                r16 = r1;
            }
        }
    }

    public final float getRectLeft(float[] points) {
        Intrinsics.checkNotNullParameter(points, "points");
        return Math.min(Math.min(Math.min(points[0], points[2]), points[4]), points[6]);
    }

    public final float getRectTop(float[] points) {
        Intrinsics.checkNotNullParameter(points, "points");
        return Math.min(Math.min(Math.min(points[1], points[3]), points[5]), points[7]);
    }

    public final float getRectRight(float[] points) {
        Intrinsics.checkNotNullParameter(points, "points");
        return Math.max(Math.max(Math.max(points[0], points[2]), points[4]), points[6]);
    }

    public final float getRectBottom(float[] points) {
        Intrinsics.checkNotNullParameter(points, "points");
        return Math.max(Math.max(Math.max(points[1], points[3]), points[5]), points[7]);
    }

    public final float getRectWidth(float[] points) {
        Intrinsics.checkNotNullParameter(points, "points");
        return getRectRight(points) - getRectLeft(points);
    }

    public final float getRectHeight(float[] points) {
        Intrinsics.checkNotNullParameter(points, "points");
        return getRectBottom(points) - getRectTop(points);
    }

    public final float getRectCenterX(float[] points) {
        Intrinsics.checkNotNullParameter(points, "points");
        return (getRectRight(points) + getRectLeft(points)) / 2.0f;
    }

    public final float getRectCenterY(float[] points) {
        Intrinsics.checkNotNullParameter(points, "points");
        return (getRectBottom(points) + getRectTop(points)) / 2.0f;
    }

    public final Rect getRectFromPoints(float[] points, int r5, int r6, boolean z, int r8, int r9) {
        Intrinsics.checkNotNullParameter(points, "points");
        Rect rect = new Rect(MathKt.roundToInt(Math.max(0.0f, getRectLeft(points))), MathKt.roundToInt(Math.max(0.0f, getRectTop(points))), MathKt.roundToInt(Math.min(r5, getRectRight(points))), MathKt.roundToInt(Math.min(r6, getRectBottom(points))));
        if (z) {
            fixRectForAspectRatio(rect, r8, r9);
        }
        return rect;
    }

    private final void fixRectForAspectRatio(Rect rect, int r3, int r4) {
        if (r3 != r4 || rect.width() == rect.height()) {
            return;
        }
        if (rect.height() > rect.width()) {
            rect.bottom -= rect.height() - rect.width();
        } else {
            rect.right -= rect.width() - rect.height();
        }
    }

    public final Uri writeTempStateStoreBitmap(Context context, Bitmap bitmap, Uri uri) {
        Intrinsics.checkNotNullParameter(context, "context");
        try {
            Intrinsics.checkNotNull(bitmap);
            return writeBitmapToUri(context, bitmap, Bitmap.CompressFormat.JPEG, 95, uri);
        } catch (Exception e) {
            Log.w("AIC", "Failed to write bitmap to temp file for image-cropper save instance state", e);
            return null;
        }
    }

    public final Uri writeBitmapToUri(Context context, Bitmap bitmap, Bitmap.CompressFormat compressFormat, int r6, Uri uri) throws FileNotFoundException {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(bitmap, "bitmap");
        Intrinsics.checkNotNullParameter(compressFormat, "compressFormat");
        if (uri == null) {
            uri = buildUri(context, compressFormat);
        }
        OutputStream outputStream = null;
        try {
            ContentResolver contentResolver = context.getContentResolver();
            Intrinsics.checkNotNull(uri);
            outputStream = contentResolver.openOutputStream(uri, WRITE_AND_TRUNCATE);
            bitmap.compress(compressFormat, r6, outputStream);
            return uri;
        } finally {
            closeSafe(outputStream);
        }
    }

    private final Uri buildUri(Context context, Bitmap.CompressFormat compressFormat) {
        try {
            int r6 = WhenMappings.$EnumSwitchMapping$0[compressFormat.ordinal()];
            String str = r6 != 1 ? r6 != 2 ? ".webp" : ".png" : ".jpg";
            if (CommonVersionCheck.INSTANCE.isAtLeastQ29()) {
                try {
                    File file = File.createTempFile("cropped", str, context.getExternalFilesDir(Environment.DIRECTORY_PICTURES));
                    Intrinsics.checkNotNullExpressionValue(file, "file");
                    return GetUriForFile.getUriForFile(context, file);
                } catch (Exception e) {
                    Log.e("AIC", String.valueOf(e.getMessage()));
                    File file2 = File.createTempFile("cropped", str, context.getCacheDir());
                    Intrinsics.checkNotNullExpressionValue(file2, "file");
                    return GetUriForFile.getUriForFile(context, file2);
                }
            }
            return Uri.fromFile(File.createTempFile("cropped", str, context.getCacheDir()));
        } catch (IOException e2) {
            throw new RuntimeException("Failed to create temp file for output image", e2);
        }
    }

    public final Bitmap resizeBitmap(Bitmap bitmap, int r6, int r7, CropImageView.RequestSizeOptions options) {
        Intrinsics.checkNotNullParameter(options, "options");
        if (r6 > 0 && r7 > 0) {
            try {
                if (options == CropImageView.RequestSizeOptions.RESIZE_FIT || options == CropImageView.RequestSizeOptions.RESIZE_INSIDE || options == CropImageView.RequestSizeOptions.RESIZE_EXACT) {
                    Bitmap bitmap2 = null;
                    if (options == CropImageView.RequestSizeOptions.RESIZE_EXACT) {
                        Intrinsics.checkNotNull(bitmap);
                        bitmap2 = Bitmap.createScaledBitmap(bitmap, r6, r7, false);
                    } else {
                        Intrinsics.checkNotNull(bitmap);
                        float width = bitmap.getWidth();
                        float height = bitmap.getHeight();
                        float max = Math.max(width / r6, height / r7);
                        if (max > 1.0f || options == CropImageView.RequestSizeOptions.RESIZE_FIT) {
                            bitmap2 = Bitmap.createScaledBitmap(bitmap, (int) (width / max), (int) (height / max), false);
                        }
                    }
                    if (bitmap2 != null) {
                        if (!Intrinsics.areEqual(bitmap2, bitmap)) {
                            bitmap.recycle();
                        }
                        return bitmap2;
                    }
                }
            } catch (Exception e) {
                Log.w("AIC", "Failed to resize cropped image, return bitmap before resize", e);
            }
        }
        Intrinsics.checkNotNull(bitmap);
        return bitmap;
    }

    private final BitmapSampled cropBitmap(Context context, Uri uri, float[] fArr, int r20, int r21, int r22, boolean z, int r24, int r25, int r26, int r27, boolean z2, boolean z3, int r30) {
        Bitmap rotateAndFlipBitmapInt;
        Bitmap bitmap;
        Rect rectFromPoints = getRectFromPoints(fArr, r21, r22, z, r24, r25);
        int width = r26 > 0 ? r26 : rectFromPoints.width();
        int height = r27 > 0 ? r27 : rectFromPoints.height();
        Bitmap bitmap2 = null;
        int r9 = 1;
        try {
            BitmapSampled decodeSampledBitmapRegion = decodeSampledBitmapRegion(context, uri, rectFromPoints, width, height, r30);
            bitmap2 = decodeSampledBitmapRegion.getBitmap();
            r9 = decodeSampledBitmapRegion.getSampleSize();
        } catch (Exception unused) {
        }
        if (bitmap2 != null) {
            try {
                rotateAndFlipBitmapInt = rotateAndFlipBitmapInt(bitmap2, r20, z2, z3);
            } catch (OutOfMemoryError e) {
                e = e;
            }
            try {
                if (r20 % 90 != 0) {
                    rotateAndFlipBitmapInt = cropForRotatedImage(rotateAndFlipBitmapInt, fArr, rectFromPoints, r20, z, r24, r25);
                }
                return new BitmapSampled(rotateAndFlipBitmapInt, r9);
            } catch (OutOfMemoryError e2) {
                e = e2;
                bitmap2 = bitmap;
                bitmap2.recycle();
                throw e;
            }
        }
        return cropBitmap(context, uri, fArr, r20, z, r24, r25, r30, rectFromPoints, width, height, z2, z3);
    }

    /* JADX WARN: Finally extract failed */
    private final BitmapSampled cropBitmap(Context context, Uri uri, float[] fArr, int r20, boolean z, int r22, int r23, int r24, Rect rect, int r26, int r27, boolean z2, boolean z3) {
        Bitmap bitmap = null;
        try {
            BitmapFactory.Options options = new BitmapFactory.Options();
            int calculateInSampleSizeByReqestedSize = r24 * calculateInSampleSizeByReqestedSize(rect.width(), rect.height(), r26, r27);
            options.inSampleSize = calculateInSampleSizeByReqestedSize;
            ContentResolver contentResolver = context.getContentResolver();
            Intrinsics.checkNotNullExpressionValue(contentResolver, "context.contentResolver");
            Bitmap decodeImage = decodeImage(contentResolver, uri, options);
            if (decodeImage != null) {
                try {
                    int length = fArr.length;
                    float[] fArr2 = new float[length];
                    int r5 = 0;
                    System.arraycopy(fArr, 0, fArr2, 0, fArr.length);
                    int r2 = length - 1;
                    if (r2 >= 0) {
                        while (true) {
                            int r0 = r5 + 1;
                            fArr2[r5] = fArr2[r5] / options.inSampleSize;
                            if (r0 > r2) {
                                break;
                            }
                            r5 = r0;
                        }
                    }
                    bitmap = cropBitmapObjectWithScale(decodeImage, fArr2, r20, z, r22, r23, 1.0f, z2, z3);
                    if (!Intrinsics.areEqual(bitmap, decodeImage)) {
                        decodeImage.recycle();
                    }
                } catch (Throwable th) {
                    if (!Intrinsics.areEqual((Object) null, decodeImage)) {
                        decodeImage.recycle();
                    }
                    throw th;
                }
            }
            return new BitmapSampled(bitmap, calculateInSampleSizeByReqestedSize);
        } catch (Exception e) {
            throw new CropException.FailedToLoadBitmap(uri, e.getMessage());
        } catch (OutOfMemoryError e2) {
            if (0 != 0) {
                bitmap.recycle();
            }
            throw e2;
        }
    }

    private final BitmapFactory.Options decodeImageForOption(ContentResolver contentResolver, Uri uri) throws FileNotFoundException {
        InputStream inputStream;
        try {
            inputStream = contentResolver.openInputStream(uri);
        } catch (Throwable th) {
            th = th;
            inputStream = null;
        }
        try {
            BitmapFactory.Options options = new BitmapFactory.Options();
            options.inJustDecodeBounds = true;
            BitmapFactory.decodeStream(inputStream, EMPTY_RECT, options);
            options.inJustDecodeBounds = false;
            closeSafe(inputStream);
            return options;
        } catch (Throwable th2) {
            th = th2;
            closeSafe(inputStream);
            throw th;
        }
    }

    private final Bitmap decodeImage(ContentResolver contentResolver, Uri uri, BitmapFactory.Options options) throws FileNotFoundException {
        do {
            InputStream inputStream = null;
            try {
                try {
                    inputStream = contentResolver.openInputStream(uri);
                    return BitmapFactory.decodeStream(inputStream, EMPTY_RECT, options);
                } catch (OutOfMemoryError unused) {
                    options.inSampleSize *= 2;
                    closeSafe(inputStream);
                    if (options.inSampleSize > 512) {
                        throw new CropException.FailedToDecodeImage(uri);
                    }
                }
            } finally {
                closeSafe(inputStream);
            }
        } while (options.inSampleSize > 512);
        throw new CropException.FailedToDecodeImage(uri);
    }

    /* JADX WARN: Removed duplicated region for block: B:10:0x0048 A[Catch: Exception -> 0x0053, TRY_LEAVE, TryCatch #1 {Exception -> 0x0053, blocks: (B:2:0x0000, B:3:0x0025, B:5:0x0034, B:7:0x0040, B:10:0x0048), top: B:18:0x0000, inners: #0 }] */
    /* JADX WARN: Removed duplicated region for block: B:9:0x0047  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private final com.canhub.cropper.BitmapUtils.BitmapSampled decodeSampledBitmapRegion(android.content.Context r4, android.net.Uri r5, android.graphics.Rect r6, int r7, int r8, int r9) {
        /*
            r3 = this;
            android.graphics.BitmapFactory$Options r0 = new android.graphics.BitmapFactory$Options     // Catch: java.lang.Exception -> L53
            r0.<init>()     // Catch: java.lang.Exception -> L53
            int r1 = r6.width()     // Catch: java.lang.Exception -> L53
            int r2 = r6.height()     // Catch: java.lang.Exception -> L53
            int r7 = r3.calculateInSampleSizeByReqestedSize(r1, r2, r7, r8)     // Catch: java.lang.Exception -> L53
            int r9 = r9 * r7
            r0.inSampleSize = r9     // Catch: java.lang.Exception -> L53
            android.content.ContentResolver r4 = r4.getContentResolver()     // Catch: java.lang.Exception -> L53
            java.io.InputStream r4 = r4.openInputStream(r5)     // Catch: java.lang.Exception -> L53
            kotlin.jvm.internal.Intrinsics.checkNotNull(r4)     // Catch: java.lang.Exception -> L53
            r7 = 0
            android.graphics.BitmapRegionDecoder r7 = android.graphics.BitmapRegionDecoder.newInstance(r4, r7)     // Catch: java.lang.Exception -> L53
        L25:
            com.canhub.cropper.BitmapUtils$BitmapSampled r8 = new com.canhub.cropper.BitmapUtils$BitmapSampled     // Catch: java.lang.OutOfMemoryError -> L34 java.lang.Exception -> L53
            kotlin.jvm.internal.Intrinsics.checkNotNull(r7)     // Catch: java.lang.OutOfMemoryError -> L34 java.lang.Exception -> L53
            android.graphics.Bitmap r9 = r7.decodeRegion(r6, r0)     // Catch: java.lang.OutOfMemoryError -> L34 java.lang.Exception -> L53
            int r1 = r0.inSampleSize     // Catch: java.lang.OutOfMemoryError -> L34 java.lang.Exception -> L53
            r8.<init>(r9, r1)     // Catch: java.lang.OutOfMemoryError -> L34 java.lang.Exception -> L53
            return r8
        L34:
            int r8 = r0.inSampleSize     // Catch: java.lang.Exception -> L53
            int r8 = r8 * 2
            r0.inSampleSize = r8     // Catch: java.lang.Exception -> L53
            int r8 = r0.inSampleSize     // Catch: java.lang.Exception -> L53
            r9 = 512(0x200, float:7.175E-43)
            if (r8 <= r9) goto L25
            java.io.Closeable r4 = (java.io.Closeable) r4     // Catch: java.lang.Exception -> L53
            r3.closeSafe(r4)     // Catch: java.lang.Exception -> L53
            if (r7 != 0) goto L48
            goto L4b
        L48:
            r7.recycle()     // Catch: java.lang.Exception -> L53
        L4b:
            com.canhub.cropper.BitmapUtils$BitmapSampled r4 = new com.canhub.cropper.BitmapUtils$BitmapSampled
            r5 = 0
            r6 = 1
            r4.<init>(r5, r6)
            return r4
        L53:
            r4 = move-exception
            com.canhub.cropper.CropException$FailedToLoadBitmap r6 = new com.canhub.cropper.CropException$FailedToLoadBitmap
            java.lang.String r4 = r4.getMessage()
            r6.<init>(r5, r4)
            throw r6
        */
        throw new UnsupportedOperationException("Method not decompiled: com.canhub.cropper.BitmapUtils.decodeSampledBitmapRegion(android.content.Context, android.net.Uri, android.graphics.Rect, int, int, int):com.canhub.cropper.BitmapUtils$BitmapSampled");
    }

    /* JADX WARN: Removed duplicated region for block: B:19:0x002c  */
    /* JADX WARN: Removed duplicated region for block: B:28:0x0094  */
    /* JADX WARN: Removed duplicated region for block: B:37:0x0089 A[SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private final android.graphics.Bitmap cropForRotatedImage(android.graphics.Bitmap r14, float[] r15, android.graphics.Rect r16, int r17, boolean r18, int r19, int r20) {
        /*
            Method dump skipped, instructions count: 190
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.canhub.cropper.BitmapUtils.cropForRotatedImage(android.graphics.Bitmap, float[], android.graphics.Rect, int, boolean, int, int):android.graphics.Bitmap");
    }

    private final int calculateInSampleSizeByReqestedSize(int r3, int r4, int r5, int r6) {
        int r0 = 1;
        if (r4 > r6 || r3 > r5) {
            while ((r4 / 2) / r0 > r6 && (r3 / 2) / r0 > r5) {
                r0 *= 2;
            }
        }
        return r0;
    }

    private final int calculateInSampleSizeByMaxTextureSize(int r4, int r5) {
        if (mMaxTextureSize == 0) {
            mMaxTextureSize = getMaxTextureSize();
        }
        int r1 = 1;
        if (mMaxTextureSize > 0) {
            while (true) {
                int r0 = r5 / r1;
                int r2 = mMaxTextureSize;
                if (r0 <= r2 && r4 / r1 <= r2) {
                    break;
                }
                r1 *= 2;
            }
        }
        return r1;
    }

    private final Bitmap rotateAndFlipBitmapInt(Bitmap bitmap, int r9, boolean z, boolean z2) {
        if (r9 > 0 || z || z2) {
            Matrix matrix = new Matrix();
            matrix.setRotate(r9);
            matrix.postScale(z ? -1 : 1, z2 ? -1 : 1);
            Bitmap createBitmap = Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(), bitmap.getHeight(), matrix, false);
            if (!Intrinsics.areEqual(createBitmap, bitmap)) {
                bitmap.recycle();
            }
            Intrinsics.checkNotNullExpressionValue(createBitmap, "{\n            val matrix = Matrix()\n            matrix.setRotate(degrees.toFloat())\n            matrix.postScale(\n                (if (flipHorizontally) -1 else 1).toFloat(),\n                (if (flipVertically) -1 else 1).toFloat()\n            )\n            val newBitmap =\n                Bitmap.createBitmap(bitmap, 0, 0, bitmap.width, bitmap.height, matrix, false)\n            if (newBitmap != bitmap) {\n                bitmap.recycle()\n            }\n            newBitmap\n        }");
            return createBitmap;
        }
        return bitmap;
    }

    private final int getMaxTextureSize() {
        try {
            EGL egl = EGLContext.getEGL();
            if (egl == null) {
                throw new NullPointerException("null cannot be cast to non-null type javax.microedition.khronos.egl.EGL10");
            }
            EGL10 egl10 = (EGL10) egl;
            EGLDisplay eglGetDisplay = egl10.eglGetDisplay(EGL10.EGL_DEFAULT_DISPLAY);
            egl10.eglInitialize(eglGetDisplay, new int[2]);
            int[] r4 = new int[1];
            int r6 = 0;
            egl10.eglGetConfigs(eglGetDisplay, null, 0, r4);
            EGLConfig[] eGLConfigArr = new EGLConfig[r4[0]];
            egl10.eglGetConfigs(eglGetDisplay, eGLConfigArr, r4[0], r4);
            int[] r3 = new int[1];
            int r42 = r4[0];
            if (r42 > 0) {
                int r7 = 0;
                int r8 = 0;
                while (true) {
                    int r9 = r7 + 1;
                    egl10.eglGetConfigAttrib(eglGetDisplay, eGLConfigArr[r7], 12332, r3);
                    if (r8 < r3[0]) {
                        r8 = r3[0];
                    }
                    if (r9 >= r42) {
                        break;
                    }
                    r7 = r9;
                }
                r6 = r8;
            }
            egl10.eglTerminate(eglGetDisplay);
            return Math.max(r6, 2048);
        } catch (Exception unused) {
            return 2048;
        }
    }

    private final void closeSafe(Closeable closeable) {
        if (closeable != null) {
            try {
                closeable.close();
            } catch (IOException unused) {
            }
        }
    }

    /* compiled from: BitmapUtils.kt */
    @Metadata(m184d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0006\b\u0000\u0018\u00002\u00020\u0001B\u0017\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006R\u0013\u0010\u0002\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\n¨\u0006\u000b"}, m183d2 = {"Lcom/canhub/cropper/BitmapUtils$BitmapSampled;", "", "bitmap", "Landroid/graphics/Bitmap;", DecodeProducer.SAMPLE_SIZE, "", "(Landroid/graphics/Bitmap;I)V", "getBitmap", "()Landroid/graphics/Bitmap;", "getSampleSize", "()I", "cropper_release"}, m182k = 1, m181mv = {1, 5, 1}, m179xi = 48)
    /* loaded from: classes.dex */
    public static final class BitmapSampled {
        private final Bitmap bitmap;
        private final int sampleSize;

        public BitmapSampled(Bitmap bitmap, int r2) {
            this.bitmap = bitmap;
            this.sampleSize = r2;
        }

        public final Bitmap getBitmap() {
            return this.bitmap;
        }

        public final int getSampleSize() {
            return this.sampleSize;
        }
    }

    /* compiled from: BitmapUtils.kt */
    @Metadata(m184d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0006\b\u0000\u0018\u00002\u00020\u0001B\u0017\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006R\u0013\u0010\u0002\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\n¨\u0006\u000b"}, m183d2 = {"Lcom/canhub/cropper/BitmapUtils$RotateBitmapResult;", "", "bitmap", "Landroid/graphics/Bitmap;", "degrees", "", "(Landroid/graphics/Bitmap;I)V", "getBitmap", "()Landroid/graphics/Bitmap;", "getDegrees", "()I", "cropper_release"}, m182k = 1, m181mv = {1, 5, 1}, m179xi = 48)
    /* loaded from: classes.dex */
    public static final class RotateBitmapResult {
        private final Bitmap bitmap;
        private final int degrees;

        public RotateBitmapResult(Bitmap bitmap, int r2) {
            this.bitmap = bitmap;
            this.degrees = r2;
        }

        public final Bitmap getBitmap() {
            return this.bitmap;
        }

        public final int getDegrees() {
            return this.degrees;
        }
    }
}
