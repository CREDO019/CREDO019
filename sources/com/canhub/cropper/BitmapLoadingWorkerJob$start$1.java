package com.canhub.cropper;

import android.content.Context;
import android.graphics.Bitmap;
import android.net.Uri;
import com.canhub.cropper.BitmapLoadingWorkerJob;
import com.canhub.cropper.BitmapUtils;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.CoroutineScopeKt;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: BitmapLoadingWorkerJob.kt */
@Metadata(m184d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@"}, m183d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, m182k = 3, m181mv = {1, 5, 1}, m179xi = 48)
@DebugMetadata(m175c = "com.canhub.cropper.BitmapLoadingWorkerJob$start$1", m174f = "BitmapLoadingWorkerJob.kt", m173i = {}, m172l = {45, 56}, m171m = "invokeSuspend", m170n = {}, m169s = {})
/* loaded from: classes.dex */
public final class BitmapLoadingWorkerJob$start$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    private /* synthetic */ Object L$0;
    int label;
    final /* synthetic */ BitmapLoadingWorkerJob this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public BitmapLoadingWorkerJob$start$1(BitmapLoadingWorkerJob bitmapLoadingWorkerJob, Continuation<? super BitmapLoadingWorkerJob$start$1> continuation) {
        super(2, continuation);
        this.this$0 = bitmapLoadingWorkerJob;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        BitmapLoadingWorkerJob$start$1 bitmapLoadingWorkerJob$start$1 = new BitmapLoadingWorkerJob$start$1(this.this$0, continuation);
        bitmapLoadingWorkerJob$start$1.L$0 = obj;
        return bitmapLoadingWorkerJob$start$1;
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((BitmapLoadingWorkerJob$start$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        Object onPostExecute;
        Context context;
        int r6;
        int r7;
        Context context2;
        Object onPostExecute2;
        Object obj2 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int r1 = this.label;
        try {
        } catch (Exception e) {
            BitmapLoadingWorkerJob bitmapLoadingWorkerJob = this.this$0;
            this.label = 2;
            onPostExecute = bitmapLoadingWorkerJob.onPostExecute(new BitmapLoadingWorkerJob.Result(bitmapLoadingWorkerJob.getUri(), e), this);
            if (onPostExecute == obj2) {
                return obj2;
            }
        }
        if (r1 == 0) {
            ResultKt.throwOnFailure(obj);
            CoroutineScope coroutineScope = (CoroutineScope) this.L$0;
            if (CoroutineScopeKt.isActive(coroutineScope)) {
                BitmapUtils bitmapUtils = BitmapUtils.INSTANCE;
                context = this.this$0.context;
                Uri uri = this.this$0.getUri();
                r6 = this.this$0.width;
                r7 = this.this$0.height;
                BitmapUtils.BitmapSampled decodeSampledBitmap = bitmapUtils.decodeSampledBitmap(context, uri, r6, r7);
                if (CoroutineScopeKt.isActive(coroutineScope)) {
                    BitmapUtils bitmapUtils2 = BitmapUtils.INSTANCE;
                    Bitmap bitmap = decodeSampledBitmap.getBitmap();
                    context2 = this.this$0.context;
                    BitmapUtils.RotateBitmapResult rotateBitmapByExif = bitmapUtils2.rotateBitmapByExif(bitmap, context2, this.this$0.getUri());
                    BitmapLoadingWorkerJob bitmapLoadingWorkerJob2 = this.this$0;
                    this.label = 1;
                    onPostExecute2 = bitmapLoadingWorkerJob2.onPostExecute(new BitmapLoadingWorkerJob.Result(bitmapLoadingWorkerJob2.getUri(), rotateBitmapByExif.getBitmap(), decodeSampledBitmap.getSampleSize(), rotateBitmapByExif.getDegrees()), this);
                    if (onPostExecute2 == obj2) {
                        return obj2;
                    }
                }
            }
        } else if (r1 != 1) {
            if (r1 == 2) {
                ResultKt.throwOnFailure(obj);
                return Unit.INSTANCE;
            }
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        } else {
            ResultKt.throwOnFailure(obj);
        }
        return Unit.INSTANCE;
    }
}
