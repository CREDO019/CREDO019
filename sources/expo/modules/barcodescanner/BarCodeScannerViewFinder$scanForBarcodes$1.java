package expo.modules.barcodescanner;

import android.hardware.Camera;
import android.util.Log;
import expo.modules.core.errors.ModuleDestroyedException;
import expo.modules.interfaces.barcodescanner.BarCodeScannerInterface;
import expo.modules.interfaces.barcodescanner.BarCodeScannerResult;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.CoroutineScopeKt;
import kotlinx.coroutines.Dispatchers;
import kotlinx.coroutines.Job;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: BarCodeScannerViewFinder.kt */
@Metadata(m184d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@"}, m183d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, m182k = 3, m181mv = {1, 6, 0}, m179xi = 48)
@DebugMetadata(m175c = "expo.modules.barcodescanner.BarCodeScannerViewFinder$scanForBarcodes$1", m174f = "BarCodeScannerViewFinder.kt", m173i = {}, m172l = {205}, m171m = "invokeSuspend", m170n = {}, m169s = {})
/* loaded from: classes4.dex */
public final class BarCodeScannerViewFinder$scanForBarcodes$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ Camera $camera;
    final /* synthetic */ byte[] $mImageData;
    int label;
    final /* synthetic */ BarCodeScannerViewFinder this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public BarCodeScannerViewFinder$scanForBarcodes$1(BarCodeScannerViewFinder barCodeScannerViewFinder, Camera camera, byte[] bArr, Continuation<? super BarCodeScannerViewFinder$scanForBarcodes$1> continuation) {
        super(2, continuation);
        this.this$0 = barCodeScannerViewFinder;
        this.$camera = camera;
        this.$mImageData = bArr;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new BarCodeScannerViewFinder$scanForBarcodes$1(this.this$0, this.$camera, this.$mImageData, continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((BarCodeScannerViewFinder$scanForBarcodes$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        CoroutineScope coroutineScope;
        boolean z;
        Camera camera;
        BarCodeScannerInterface barCodeScannerInterface;
        Object obj2 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int r1 = this.label;
        try {
            try {
                if (r1 == 0) {
                    ResultKt.throwOnFailure(obj);
                    coroutineScope = this.this$0.coroutineScope;
                    if (CoroutineScopeKt.isActive(coroutineScope)) {
                        z = this.this$0.isChanging;
                        if (!z && (camera = this.$camera) != null) {
                            Camera.Size previewSize = camera.getParameters().getPreviewSize();
                            int r12 = previewSize.width;
                            int r9 = previewSize.height;
                            int rotation = ExpoBarCodeScanner.Companion.getInstance().getRotation();
                            barCodeScannerInterface = this.this$0.barCodeScanner;
                            if (barCodeScannerInterface == null) {
                                Intrinsics.throwUninitializedPropertyAccessException("barCodeScanner");
                                barCodeScannerInterface = null;
                            }
                            BarCodeScannerResult scan = barCodeScannerInterface.scan(this.$mImageData, r12, r9, rotation);
                            if (scan != null) {
                                this.label = 1;
                                if (BuildersKt.withContext(Dispatchers.getMain(), new C43291(this.this$0, scan, null), this) == obj2) {
                                    return obj2;
                                }
                            }
                        }
                    } else {
                        return Unit.INSTANCE;
                    }
                } else if (r1 != 1) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                } else {
                    ResultKt.throwOnFailure(obj);
                }
            } catch (ModuleDestroyedException e) {
                String message = e.getMessage();
                if (message == null) {
                    message = "";
                }
                Log.w("BarCodeScanner", message, e);
            }
            return Unit.INSTANCE;
        } finally {
            BarCodeScannerViewFinder.Companion.setBarCodeScannerTaskLock(false);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: BarCodeScannerViewFinder.kt */
    @Metadata(m184d1 = {"\u0000\n\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@"}, m183d2 = {"<anonymous>", "Lkotlinx/coroutines/Job;", "Lkotlinx/coroutines/CoroutineScope;"}, m182k = 3, m181mv = {1, 6, 0}, m179xi = 48)
    @DebugMetadata(m175c = "expo.modules.barcodescanner.BarCodeScannerViewFinder$scanForBarcodes$1$1", m174f = "BarCodeScannerViewFinder.kt", m173i = {}, m172l = {}, m171m = "invokeSuspend", m170n = {}, m169s = {})
    /* renamed from: expo.modules.barcodescanner.BarCodeScannerViewFinder$scanForBarcodes$1$1 */
    /* loaded from: classes4.dex */
    public static final class C43291 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Job>, Object> {
        final /* synthetic */ BarCodeScannerResult $result;
        private /* synthetic */ Object L$0;
        int label;
        final /* synthetic */ BarCodeScannerViewFinder this$0;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        C43291(BarCodeScannerViewFinder barCodeScannerViewFinder, BarCodeScannerResult barCodeScannerResult, Continuation<? super C43291> continuation) {
            super(2, continuation);
            this.this$0 = barCodeScannerViewFinder;
            this.$result = barCodeScannerResult;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            C43291 c43291 = new C43291(this.this$0, this.$result, continuation);
            c43291.L$0 = obj;
            return c43291;
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Job> continuation) {
            return ((C43291) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        /* compiled from: BarCodeScannerViewFinder.kt */
        @Metadata(m184d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@"}, m183d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, m182k = 3, m181mv = {1, 6, 0}, m179xi = 48)
        @DebugMetadata(m175c = "expo.modules.barcodescanner.BarCodeScannerViewFinder$scanForBarcodes$1$1$1", m174f = "BarCodeScannerViewFinder.kt", m173i = {}, m172l = {}, m171m = "invokeSuspend", m170n = {}, m169s = {})
        /* renamed from: expo.modules.barcodescanner.BarCodeScannerViewFinder$scanForBarcodes$1$1$1 */
        /* loaded from: classes4.dex */
        public static final class C43301 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
            final /* synthetic */ BarCodeScannerResult $result;
            int label;
            final /* synthetic */ BarCodeScannerViewFinder this$0;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            C43301(BarCodeScannerViewFinder barCodeScannerViewFinder, BarCodeScannerResult barCodeScannerResult, Continuation<? super C43301> continuation) {
                super(2, continuation);
                this.this$0 = barCodeScannerViewFinder;
                this.$result = barCodeScannerResult;
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
                return new C43301(this.this$0, this.$result, continuation);
            }

            @Override // kotlin.jvm.functions.Function2
            public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
                return ((C43301) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Object invokeSuspend(Object obj) {
                CoroutineScope coroutineScope;
                BarCodeScannerView barCodeScannerView;
                IntrinsicsKt.getCOROUTINE_SUSPENDED();
                if (this.label == 0) {
                    ResultKt.throwOnFailure(obj);
                    coroutineScope = this.this$0.coroutineScope;
                    if (CoroutineScopeKt.isActive(coroutineScope)) {
                        barCodeScannerView = this.this$0.barCodeScannerView;
                        BarCodeScannerResult result = this.$result;
                        Intrinsics.checkNotNullExpressionValue(result, "result");
                        barCodeScannerView.onBarCodeScanned(result);
                    }
                    return Unit.INSTANCE;
                }
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            Job launch$default;
            IntrinsicsKt.getCOROUTINE_SUSPENDED();
            if (this.label == 0) {
                ResultKt.throwOnFailure(obj);
                launch$default = BuildersKt__Builders_commonKt.launch$default((CoroutineScope) this.L$0, null, null, new C43301(this.this$0, this.$result, null), 3, null);
                return launch$default;
            }
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
    }
}
