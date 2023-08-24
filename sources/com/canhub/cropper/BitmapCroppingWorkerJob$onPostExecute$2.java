package com.canhub.cropper;

import com.canhub.cropper.BitmapCroppingWorkerJob;
import java.lang.ref.WeakReference;
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
/* compiled from: BitmapCroppingWorkerJob.kt */
@Metadata(m184d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@"}, m183d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, m182k = 3, m181mv = {1, 5, 1}, m179xi = 48)
@DebugMetadata(m175c = "com.canhub.cropper.BitmapCroppingWorkerJob$onPostExecute$2", m174f = "BitmapCroppingWorkerJob.kt", m173i = {}, m172l = {}, m171m = "invokeSuspend", m170n = {}, m169s = {})
/* loaded from: classes.dex */
public final class BitmapCroppingWorkerJob$onPostExecute$2 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ BitmapCroppingWorkerJob.Result $result;
    private /* synthetic */ Object L$0;
    int label;
    final /* synthetic */ BitmapCroppingWorkerJob this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public BitmapCroppingWorkerJob$onPostExecute$2(BitmapCroppingWorkerJob bitmapCroppingWorkerJob, BitmapCroppingWorkerJob.Result result, Continuation<? super BitmapCroppingWorkerJob$onPostExecute$2> continuation) {
        super(2, continuation);
        this.this$0 = bitmapCroppingWorkerJob;
        this.$result = result;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        BitmapCroppingWorkerJob$onPostExecute$2 bitmapCroppingWorkerJob$onPostExecute$2 = new BitmapCroppingWorkerJob$onPostExecute$2(this.this$0, this.$result, continuation);
        bitmapCroppingWorkerJob$onPostExecute$2.L$0 = obj;
        return bitmapCroppingWorkerJob$onPostExecute$2;
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((BitmapCroppingWorkerJob$onPostExecute$2) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        WeakReference weakReference;
        IntrinsicsKt.getCOROUTINE_SUSPENDED();
        if (this.label == 0) {
            ResultKt.throwOnFailure(obj);
            boolean z = false;
            if (CoroutineScopeKt.isActive((CoroutineScope) this.L$0)) {
                weakReference = this.this$0.cropImageViewReference;
                CropImageView cropImageView = (CropImageView) weakReference.get();
                if (cropImageView != null) {
                    cropImageView.onImageCroppingAsyncComplete(this.$result);
                    z = true;
                }
            }
            if (!z && this.$result.getBitmap() != null) {
                this.$result.getBitmap().recycle();
            }
            return Unit.INSTANCE;
        }
        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
    }
}
