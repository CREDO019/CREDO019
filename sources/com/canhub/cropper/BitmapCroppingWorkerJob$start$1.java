package com.canhub.cropper;

import android.content.Context;
import android.graphics.Bitmap;
import android.net.Uri;
import com.canhub.cropper.BitmapCroppingWorkerJob;
import com.canhub.cropper.BitmapUtils;
import com.canhub.cropper.CropImageView;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.CoroutineScopeKt;
import kotlinx.coroutines.Dispatchers;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: BitmapCroppingWorkerJob.kt */
@Metadata(m184d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@"}, m183d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, m182k = 3, m181mv = {1, 5, 1}, m179xi = 48)
@DebugMetadata(m175c = "com.canhub.cropper.BitmapCroppingWorkerJob$start$1", m174f = "BitmapCroppingWorkerJob.kt", m173i = {}, m172l = {77, 102}, m171m = "invokeSuspend", m170n = {}, m169s = {})
/* loaded from: classes.dex */
public final class BitmapCroppingWorkerJob$start$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    private /* synthetic */ Object L$0;
    int label;
    final /* synthetic */ BitmapCroppingWorkerJob this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public BitmapCroppingWorkerJob$start$1(BitmapCroppingWorkerJob bitmapCroppingWorkerJob, Continuation<? super BitmapCroppingWorkerJob$start$1> continuation) {
        super(2, continuation);
        this.this$0 = bitmapCroppingWorkerJob;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        BitmapCroppingWorkerJob$start$1 bitmapCroppingWorkerJob$start$1 = new BitmapCroppingWorkerJob$start$1(this.this$0, continuation);
        bitmapCroppingWorkerJob$start$1.L$0 = obj;
        return bitmapCroppingWorkerJob$start$1;
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((BitmapCroppingWorkerJob$start$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        Object onPostExecute;
        Bitmap bitmap;
        Object onPostExecute2;
        Bitmap bitmap2;
        float[] fArr;
        int r10;
        boolean z;
        int r12;
        int r13;
        boolean z2;
        boolean z3;
        BitmapUtils.BitmapSampled cropBitmapObjectHandleOOM;
        int r8;
        int r9;
        CropImageView.RequestSizeOptions requestSizeOptions;
        Context context;
        float[] fArr2;
        int r11;
        int r122;
        int r132;
        boolean z4;
        int r15;
        int r16;
        int r17;
        int r18;
        boolean z5;
        boolean z6;
        Object obj2 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int r0 = this.label;
        try {
        } catch (Exception e) {
            this.label = 2;
            onPostExecute = this.this$0.onPostExecute(new BitmapCroppingWorkerJob.Result(e, false), this);
            if (onPostExecute == obj2) {
                return obj2;
            }
        }
        if (r0 == 0) {
            ResultKt.throwOnFailure(obj);
            CoroutineScope coroutineScope = (CoroutineScope) this.L$0;
            if (CoroutineScopeKt.isActive(coroutineScope)) {
                if (this.this$0.getUri() == null) {
                    bitmap = this.this$0.bitmap;
                    if (bitmap == null) {
                        this.label = 1;
                        onPostExecute2 = this.this$0.onPostExecute(new BitmapCroppingWorkerJob.Result((Bitmap) null, 1), this);
                        if (onPostExecute2 == obj2) {
                            return obj2;
                        }
                    } else {
                        BitmapUtils bitmapUtils = BitmapUtils.INSTANCE;
                        bitmap2 = this.this$0.bitmap;
                        fArr = this.this$0.cropPoints;
                        r10 = this.this$0.degreesRotated;
                        z = this.this$0.fixAspectRatio;
                        r12 = this.this$0.aspectRatioX;
                        r13 = this.this$0.aspectRatioY;
                        z2 = this.this$0.flipHorizontally;
                        z3 = this.this$0.flipVertically;
                        cropBitmapObjectHandleOOM = bitmapUtils.cropBitmapObjectHandleOOM(bitmap2, fArr, r10, z, r12, r13, z2, z3);
                    }
                } else {
                    BitmapUtils bitmapUtils2 = BitmapUtils.INSTANCE;
                    context = this.this$0.context;
                    Uri uri = this.this$0.getUri();
                    fArr2 = this.this$0.cropPoints;
                    r11 = this.this$0.degreesRotated;
                    r122 = this.this$0.orgWidth;
                    r132 = this.this$0.orgHeight;
                    z4 = this.this$0.fixAspectRatio;
                    r15 = this.this$0.aspectRatioX;
                    r16 = this.this$0.aspectRatioY;
                    r17 = this.this$0.reqWidth;
                    r18 = this.this$0.reqHeight;
                    z5 = this.this$0.flipHorizontally;
                    z6 = this.this$0.flipVertically;
                    cropBitmapObjectHandleOOM = bitmapUtils2.cropBitmap(context, uri, fArr2, r11, r122, r132, z4, r15, r16, r17, r18, z5, z6);
                }
                BitmapUtils bitmapUtils3 = BitmapUtils.INSTANCE;
                Bitmap bitmap3 = cropBitmapObjectHandleOOM.getBitmap();
                r8 = this.this$0.reqWidth;
                r9 = this.this$0.reqHeight;
                requestSizeOptions = this.this$0.options;
                BuildersKt__Builders_commonKt.launch$default(coroutineScope, Dispatchers.getIO(), null, new C11491(this.this$0, bitmapUtils3.resizeBitmap(bitmap3, r8, r9, requestSizeOptions), cropBitmapObjectHandleOOM, null), 2, null);
            }
            return Unit.INSTANCE;
        } else if (r0 != 1) {
            if (r0 == 2) {
                ResultKt.throwOnFailure(obj);
                return Unit.INSTANCE;
            }
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        } else {
            ResultKt.throwOnFailure(obj);
        }
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: BitmapCroppingWorkerJob.kt */
    @Metadata(m184d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@"}, m183d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, m182k = 3, m181mv = {1, 5, 1}, m179xi = 48)
    @DebugMetadata(m175c = "com.canhub.cropper.BitmapCroppingWorkerJob$start$1$1", m174f = "BitmapCroppingWorkerJob.kt", m173i = {}, m172l = {93}, m171m = "invokeSuspend", m170n = {}, m169s = {})
    /* renamed from: com.canhub.cropper.BitmapCroppingWorkerJob$start$1$1 */
    /* loaded from: classes.dex */
    public static final class C11491 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        final /* synthetic */ BitmapUtils.BitmapSampled $bitmapSampled;
        final /* synthetic */ Bitmap $resizedBitmap;
        int label;
        final /* synthetic */ BitmapCroppingWorkerJob this$0;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        C11491(BitmapCroppingWorkerJob bitmapCroppingWorkerJob, Bitmap bitmap, BitmapUtils.BitmapSampled bitmapSampled, Continuation<? super C11491> continuation) {
            super(2, continuation);
            this.this$0 = bitmapCroppingWorkerJob;
            this.$resizedBitmap = bitmap;
            this.$bitmapSampled = bitmapSampled;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return new C11491(this.this$0, this.$resizedBitmap, this.$bitmapSampled, continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((C11491) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            Context context;
            Bitmap.CompressFormat compressFormat;
            int r7;
            Uri uri;
            Object onPostExecute;
            Object obj2 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int r1 = this.label;
            if (r1 == 0) {
                ResultKt.throwOnFailure(obj);
                BitmapUtils bitmapUtils = BitmapUtils.INSTANCE;
                context = this.this$0.context;
                Bitmap bitmap = this.$resizedBitmap;
                compressFormat = this.this$0.saveCompressFormat;
                r7 = this.this$0.saveCompressQuality;
                uri = this.this$0.customOutputUri;
                Uri writeBitmapToUri = bitmapUtils.writeBitmapToUri(context, bitmap, compressFormat, r7, uri);
                this.$resizedBitmap.recycle();
                this.label = 1;
                onPostExecute = this.this$0.onPostExecute(new BitmapCroppingWorkerJob.Result(writeBitmapToUri, this.$bitmapSampled.getSampleSize()), this);
                if (onPostExecute == obj2) {
                    return obj2;
                }
            } else if (r1 != 1) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            } else {
                ResultKt.throwOnFailure(obj);
            }
            return Unit.INSTANCE;
        }
    }
}
