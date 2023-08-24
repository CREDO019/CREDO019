package com.canhub.cropper;

import android.content.Context;
import android.graphics.Bitmap;
import android.net.Uri;
import com.canhub.cropper.CropImageView;
import com.facebook.imagepipeline.producers.DecodeProducer;
import java.lang.ref.WeakReference;
import java.util.concurrent.CancellationException;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.Dispatchers;
import kotlinx.coroutines.Job;
import kotlinx.coroutines.JobKt;

/* compiled from: BitmapCroppingWorkerJob.kt */
@Metadata(m184d1 = {"\u0000j\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0014\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\u0018\u00002\u00020\u0001:\u0001/B©\u0001\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005\u0012\b\u0010\u0007\u001a\u0004\u0018\u00010\b\u0012\b\u0010\t\u001a\u0004\u0018\u00010\n\u0012\u0006\u0010\u000b\u001a\u00020\f\u0012\u0006\u0010\r\u001a\u00020\u000e\u0012\u0006\u0010\u000f\u001a\u00020\u000e\u0012\u0006\u0010\u0010\u001a\u00020\u000e\u0012\u0006\u0010\u0011\u001a\u00020\u0012\u0012\u0006\u0010\u0013\u001a\u00020\u000e\u0012\u0006\u0010\u0014\u001a\u00020\u000e\u0012\u0006\u0010\u0015\u001a\u00020\u000e\u0012\u0006\u0010\u0016\u001a\u00020\u000e\u0012\u0006\u0010\u0017\u001a\u00020\u0012\u0012\u0006\u0010\u0018\u001a\u00020\u0012\u0012\u0006\u0010\u0019\u001a\u00020\u001a\u0012\u0006\u0010\u001b\u001a\u00020\u001c\u0012\u0006\u0010\u001d\u001a\u00020\u000e\u0012\b\u0010\u001e\u001a\u0004\u0018\u00010\b¢\u0006\u0002\u0010\u001fJ\u0006\u0010(\u001a\u00020)J\u0019\u0010*\u001a\u00020)2\u0006\u0010+\u001a\u00020,H\u0082@ø\u0001\u0000¢\u0006\u0002\u0010-J\u0006\u0010.\u001a\u00020)R\u000e\u0010\u0013\u001a\u00020\u000eX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0014\u001a\u00020\u000eX\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\t\u001a\u0004\u0018\u00010\nX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010 \u001a\u00020!8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\"\u0010#R\u0014\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\fX\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u001e\u001a\u0004\u0018\u00010\bX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\u000eX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0011\u001a\u00020\u0012X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0017\u001a\u00020\u0012X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0018\u001a\u00020\u0012X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010$\u001a\u00020%X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0019\u001a\u00020\u001aX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0010\u001a\u00020\u000eX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u000f\u001a\u00020\u000eX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0016\u001a\u00020\u000eX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0015\u001a\u00020\u000eX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u001b\u001a\u00020\u001cX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u001d\u001a\u00020\u000eX\u0082\u0004¢\u0006\u0002\n\u0000R\u0013\u0010\u0007\u001a\u0004\u0018\u00010\b¢\u0006\b\n\u0000\u001a\u0004\b&\u0010'\u0082\u0002\u0004\n\u0002\b\u0019¨\u00060"}, m183d2 = {"Lcom/canhub/cropper/BitmapCroppingWorkerJob;", "Lkotlinx/coroutines/CoroutineScope;", "context", "Landroid/content/Context;", "cropImageViewReference", "Ljava/lang/ref/WeakReference;", "Lcom/canhub/cropper/CropImageView;", "uri", "Landroid/net/Uri;", "bitmap", "Landroid/graphics/Bitmap;", "cropPoints", "", "degreesRotated", "", "orgWidth", "orgHeight", "fixAspectRatio", "", "aspectRatioX", "aspectRatioY", "reqWidth", "reqHeight", "flipHorizontally", "flipVertically", "options", "Lcom/canhub/cropper/CropImageView$RequestSizeOptions;", "saveCompressFormat", "Landroid/graphics/Bitmap$CompressFormat;", "saveCompressQuality", "customOutputUri", "(Landroid/content/Context;Ljava/lang/ref/WeakReference;Landroid/net/Uri;Landroid/graphics/Bitmap;[FIIIZIIIIZZLcom/canhub/cropper/CropImageView$RequestSizeOptions;Landroid/graphics/Bitmap$CompressFormat;ILandroid/net/Uri;)V", "coroutineContext", "Lkotlin/coroutines/CoroutineContext;", "getCoroutineContext", "()Lkotlin/coroutines/CoroutineContext;", "job", "Lkotlinx/coroutines/Job;", "getUri", "()Landroid/net/Uri;", "cancel", "", "onPostExecute", "result", "Lcom/canhub/cropper/BitmapCroppingWorkerJob$Result;", "(Lcom/canhub/cropper/BitmapCroppingWorkerJob$Result;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "start", "Result", "cropper_release"}, m182k = 1, m181mv = {1, 5, 1}, m179xi = 48)
/* loaded from: classes.dex */
public final class BitmapCroppingWorkerJob implements CoroutineScope {
    private final int aspectRatioX;
    private final int aspectRatioY;
    private final Bitmap bitmap;
    private final Context context;
    private final WeakReference<CropImageView> cropImageViewReference;
    private final float[] cropPoints;
    private final Uri customOutputUri;
    private final int degreesRotated;
    private final boolean fixAspectRatio;
    private final boolean flipHorizontally;
    private final boolean flipVertically;
    private Job job;
    private final CropImageView.RequestSizeOptions options;
    private final int orgHeight;
    private final int orgWidth;
    private final int reqHeight;
    private final int reqWidth;
    private final Bitmap.CompressFormat saveCompressFormat;
    private final int saveCompressQuality;
    private final Uri uri;

    public BitmapCroppingWorkerJob(Context context, WeakReference<CropImageView> cropImageViewReference, Uri uri, Bitmap bitmap, float[] cropPoints, int r13, int r14, int r15, boolean z, int r17, int r18, int r19, int r20, boolean z2, boolean z3, CropImageView.RequestSizeOptions options, Bitmap.CompressFormat saveCompressFormat, int r25, Uri uri2) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(cropImageViewReference, "cropImageViewReference");
        Intrinsics.checkNotNullParameter(cropPoints, "cropPoints");
        Intrinsics.checkNotNullParameter(options, "options");
        Intrinsics.checkNotNullParameter(saveCompressFormat, "saveCompressFormat");
        this.context = context;
        this.cropImageViewReference = cropImageViewReference;
        this.uri = uri;
        this.bitmap = bitmap;
        this.cropPoints = cropPoints;
        this.degreesRotated = r13;
        this.orgWidth = r14;
        this.orgHeight = r15;
        this.fixAspectRatio = z;
        this.aspectRatioX = r17;
        this.aspectRatioY = r18;
        this.reqWidth = r19;
        this.reqHeight = r20;
        this.flipHorizontally = z2;
        this.flipVertically = z3;
        this.options = options;
        this.saveCompressFormat = saveCompressFormat;
        this.saveCompressQuality = r25;
        this.customOutputUri = uri2;
        this.job = JobKt.Job$default((Job) null, 1, (Object) null);
    }

    public final Uri getUri() {
        return this.uri;
    }

    @Override // kotlinx.coroutines.CoroutineScope
    public CoroutineContext getCoroutineContext() {
        return Dispatchers.getMain().plus(this.job);
    }

    public final void start() {
        this.job = BuildersKt.launch$default(this, Dispatchers.getDefault(), null, new BitmapCroppingWorkerJob$start$1(this, null), 2, null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final Object onPostExecute(Result result, Continuation<? super Unit> continuation) {
        Object withContext = BuildersKt.withContext(Dispatchers.getMain(), new BitmapCroppingWorkerJob$onPostExecute$2(this, result, null), continuation);
        return withContext == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? withContext : Unit.INSTANCE;
    }

    public final void cancel() {
        Job.DefaultImpls.cancel$default(this.job, (CancellationException) null, 1, (Object) null);
    }

    /* compiled from: BitmapCroppingWorkerJob.kt */
    @Metadata(m184d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u000b\u0018\u00002\u00020\u0001B\u0019\b\u0016\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006B\u0019\b\u0016\u0012\b\u0010\u0007\u001a\u0004\u0018\u00010\b\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\tB\u0019\b\u0016\u0012\b\u0010\n\u001a\u0004\u0018\u00010\u000b\u0012\u0006\u0010\f\u001a\u00020\r¢\u0006\u0002\u0010\u000eR\u0013\u0010\u0002\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0010R\u0013\u0010\n\u001a\u0004\u0018\u00010\u000b¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0012R\u0011\u0010\f\u001a\u00020\r¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\u0013R\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u0015R\u0013\u0010\u0007\u001a\u0004\u0018\u00010\b¢\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\u0017¨\u0006\u0018"}, m183d2 = {"Lcom/canhub/cropper/BitmapCroppingWorkerJob$Result;", "", "bitmap", "Landroid/graphics/Bitmap;", DecodeProducer.SAMPLE_SIZE, "", "(Landroid/graphics/Bitmap;I)V", "uri", "Landroid/net/Uri;", "(Landroid/net/Uri;I)V", "error", "Ljava/lang/Exception;", "isSave", "", "(Ljava/lang/Exception;Z)V", "getBitmap", "()Landroid/graphics/Bitmap;", "getError", "()Ljava/lang/Exception;", "()Z", "getSampleSize", "()I", "getUri", "()Landroid/net/Uri;", "cropper_release"}, m182k = 1, m181mv = {1, 5, 1}, m179xi = 48)
    /* loaded from: classes.dex */
    public static final class Result {
        private final Bitmap bitmap;
        private final Exception error;
        private final boolean isSave;
        private final int sampleSize;
        private final Uri uri;

        public final Bitmap getBitmap() {
            return this.bitmap;
        }

        public final Uri getUri() {
            return this.uri;
        }

        public final Exception getError() {
            return this.error;
        }

        public final boolean isSave() {
            return this.isSave;
        }

        public final int getSampleSize() {
            return this.sampleSize;
        }

        public Result(Bitmap bitmap, int r2) {
            this.bitmap = bitmap;
            this.uri = null;
            this.error = null;
            this.isSave = false;
            this.sampleSize = r2;
        }

        public Result(Uri uri, int r3) {
            this.bitmap = null;
            this.uri = uri;
            this.error = null;
            this.isSave = true;
            this.sampleSize = r3;
        }

        public Result(Exception exc, boolean z) {
            this.bitmap = null;
            this.uri = null;
            this.error = exc;
            this.isSave = z;
            this.sampleSize = 1;
        }
    }
}
