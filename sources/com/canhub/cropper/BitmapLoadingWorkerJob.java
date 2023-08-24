package com.canhub.cropper;

import android.content.Context;
import android.graphics.Bitmap;
import android.net.Uri;
import android.util.DisplayMetrics;
import com.canhub.cropper.utils.GetFilePathFromUri;
import java.lang.ref.WeakReference;
import java.util.concurrent.CancellationException;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.Dispatchers;
import kotlinx.coroutines.Job;
import kotlinx.coroutines.JobKt;

/* compiled from: BitmapLoadingWorkerJob.kt */
@Metadata(m184d1 = {"\u0000L\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\u0018\u0000 \u001e2\u00020\u0001:\u0002\u001e\u001fB\u001f\b\u0000\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007¢\u0006\u0002\u0010\bJ\u0006\u0010\u0017\u001a\u00020\u0018J\u0019\u0010\u0019\u001a\u00020\u00182\u0006\u0010\u001a\u001a\u00020\u001bH\u0082@ø\u0001\u0000¢\u0006\u0002\u0010\u001cJ\u0006\u0010\u001d\u001a\u00020\u0018R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\t\u001a\u00020\n8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u000b\u0010\fR\u001c\u0010\r\u001a\u0010\u0012\f\u0012\n \u000f*\u0004\u0018\u00010\u00050\u00050\u000eX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0010\u001a\u00020\u0011X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0012\u001a\u00020\u0013X\u0082\u0004¢\u0006\u0002\n\u0000R\u0011\u0010\u0006\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u0015R\u000e\u0010\u0016\u001a\u00020\u0013X\u0082\u0004¢\u0006\u0002\n\u0000\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006 "}, m183d2 = {"Lcom/canhub/cropper/BitmapLoadingWorkerJob;", "Lkotlinx/coroutines/CoroutineScope;", "context", "Landroid/content/Context;", "cropImageView", "Lcom/canhub/cropper/CropImageView;", "uri", "Landroid/net/Uri;", "(Landroid/content/Context;Lcom/canhub/cropper/CropImageView;Landroid/net/Uri;)V", "coroutineContext", "Lkotlin/coroutines/CoroutineContext;", "getCoroutineContext", "()Lkotlin/coroutines/CoroutineContext;", "cropImageViewReference", "Ljava/lang/ref/WeakReference;", "kotlin.jvm.PlatformType", "currentJob", "Lkotlinx/coroutines/Job;", "height", "", "getUri", "()Landroid/net/Uri;", "width", "cancel", "", "onPostExecute", "result", "Lcom/canhub/cropper/BitmapLoadingWorkerJob$Result;", "(Lcom/canhub/cropper/BitmapLoadingWorkerJob$Result;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "start", "Companion", "Result", "cropper_release"}, m182k = 1, m181mv = {1, 5, 1}, m179xi = 48)
/* loaded from: classes.dex */
public final class BitmapLoadingWorkerJob implements CoroutineScope {
    public static final Companion Companion = new Companion(null);
    private final Context context;
    private final WeakReference<CropImageView> cropImageViewReference;
    private Job currentJob;
    private final int height;
    private final Uri uri;
    private final int width;

    public BitmapLoadingWorkerJob(Context context, CropImageView cropImageView, Uri uri) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(cropImageView, "cropImageView");
        Intrinsics.checkNotNullParameter(uri, "uri");
        this.context = context;
        this.uri = uri;
        this.cropImageViewReference = new WeakReference<>(cropImageView);
        this.currentJob = JobKt.Job$default((Job) null, 1, (Object) null);
        DisplayMetrics displayMetrics = cropImageView.getResources().getDisplayMetrics();
        double d = displayMetrics.density > 1.0f ? 1.0d / displayMetrics.density : 1.0d;
        this.width = (int) (displayMetrics.widthPixels * d);
        this.height = (int) (displayMetrics.heightPixels * d);
    }

    public final Uri getUri() {
        return this.uri;
    }

    @Override // kotlinx.coroutines.CoroutineScope
    public CoroutineContext getCoroutineContext() {
        return Dispatchers.getMain().plus(this.currentJob);
    }

    public final void start() {
        this.currentJob = BuildersKt.launch$default(this, Dispatchers.getDefault(), null, new BitmapLoadingWorkerJob$start$1(this, null), 2, null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final Object onPostExecute(Result result, Continuation<? super Unit> continuation) {
        Object withContext = BuildersKt.withContext(Dispatchers.getMain(), new BitmapLoadingWorkerJob$onPostExecute$2(this, result, null), continuation);
        return withContext == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? withContext : Unit.INSTANCE;
    }

    public final void cancel() {
        Job.DefaultImpls.cancel$default(this.currentJob, (CancellationException) null, 1, (Object) null);
    }

    /* compiled from: BitmapLoadingWorkerJob.kt */
    @Metadata(m184d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002¨\u0006\u0003"}, m183d2 = {"Lcom/canhub/cropper/BitmapLoadingWorkerJob$Companion;", "", "()V", "cropper_release"}, m182k = 1, m181mv = {1, 5, 1}, m179xi = 48)
    /* loaded from: classes.dex */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }
    }

    /* compiled from: BitmapLoadingWorkerJob.kt */
    @Metadata(m184d1 = {"\u0000<\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\f\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\u0018\u00002\u00020\u0001B)\b\u0010\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\u0007¢\u0006\u0002\u0010\tB\u001f\b\u0010\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u000e\u0010\n\u001a\n\u0018\u00010\u000bj\u0004\u0018\u0001`\f¢\u0006\u0002\u0010\rJ\u0018\u0010\u0018\u001a\u00020\u00192\u0006\u0010\u001a\u001a\u00020\u001b2\b\b\u0002\u0010\u001c\u001a\u00020\u001dR\u0013\u0010\u0004\u001a\u0004\u0018\u00010\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000fR\u0011\u0010\b\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u0011R\u0019\u0010\n\u001a\n\u0018\u00010\u000bj\u0004\u0018\u0001`\f¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u0013R\u0011\u0010\u0006\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u0011R\u0011\u0010\u0015\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\u0017¨\u0006\u001e"}, m183d2 = {"Lcom/canhub/cropper/BitmapLoadingWorkerJob$Result;", "", "uri", "Landroid/net/Uri;", "bitmap", "Landroid/graphics/Bitmap;", "loadSampleSize", "", "degreesRotated", "(Landroid/net/Uri;Landroid/graphics/Bitmap;II)V", "error", "Ljava/lang/Exception;", "Lkotlin/Exception;", "(Landroid/net/Uri;Ljava/lang/Exception;)V", "getBitmap", "()Landroid/graphics/Bitmap;", "getDegreesRotated", "()I", "getError", "()Ljava/lang/Exception;", "getLoadSampleSize", "uriContent", "getUriContent", "()Landroid/net/Uri;", "getUriFilePath", "", "context", "Landroid/content/Context;", "uniqueName", "", "cropper_release"}, m182k = 1, m181mv = {1, 5, 1}, m179xi = 48)
    /* loaded from: classes.dex */
    public static final class Result {
        private final Bitmap bitmap;
        private final int degreesRotated;
        private final Exception error;
        private final int loadSampleSize;
        private final Uri uriContent;

        public final Uri getUriContent() {
            return this.uriContent;
        }

        public final Bitmap getBitmap() {
            return this.bitmap;
        }

        public final int getLoadSampleSize() {
            return this.loadSampleSize;
        }

        public final int getDegreesRotated() {
            return this.degreesRotated;
        }

        public final Exception getError() {
            return this.error;
        }

        public static /* synthetic */ String getUriFilePath$default(Result result, Context context, boolean z, int r3, Object obj) {
            if ((r3 & 2) != 0) {
                z = false;
            }
            return result.getUriFilePath(context, z);
        }

        public final String getUriFilePath(Context context, boolean z) {
            Intrinsics.checkNotNullParameter(context, "context");
            return GetFilePathFromUri.getFilePathFromUri(context, this.uriContent, z);
        }

        public Result(Uri uri, Bitmap bitmap, int r4, int r5) {
            Intrinsics.checkNotNullParameter(uri, "uri");
            this.uriContent = uri;
            this.bitmap = bitmap;
            this.loadSampleSize = r4;
            this.degreesRotated = r5;
            this.error = null;
        }

        public Result(Uri uri, Exception exc) {
            Intrinsics.checkNotNullParameter(uri, "uri");
            this.uriContent = uri;
            this.bitmap = null;
            this.loadSampleSize = 0;
            this.degreesRotated = 0;
            this.error = exc;
        }
    }
}
