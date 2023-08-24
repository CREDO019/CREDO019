package expo.modules.imagepicker;

import expo.modules.imagepicker.contracts.CameraContractOptions;
import expo.modules.imagepicker.contracts.ContractsUtils;
import expo.modules.imagepicker.contracts.CropImageContractOptions;
import expo.modules.imagepicker.contracts.ImageLibraryContractOptions;
import expo.modules.kotlin.activityresult.AppContextActivityResultCaller;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;

/* compiled from: ImagePickerModule.kt */
@Metadata(m184d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@"}, m183d2 = {"<anonymous>", "", "Lexpo/modules/kotlin/activityresult/AppContextActivityResultCaller;"}, m182k = 3, m181mv = {1, 6, 0}, m179xi = 48)
@DebugMetadata(m175c = "expo.modules.imagepicker.ImagePickerModule$definition$1$8", m174f = "ImagePickerModule.kt", m173i = {0, 1}, m172l = {87, 91, 95}, m171m = "invokeSuspend", m170n = {"$this$RegisterActivityContracts", "$this$RegisterActivityContracts"}, m169s = {"L$0", "L$0"})
/* loaded from: classes4.dex */
final class ImagePickerModule$definition$1$8 extends SuspendLambda implements Function2<AppContextActivityResultCaller, Continuation<? super Unit>, Object> {
    private /* synthetic */ Object L$0;
    Object L$1;
    int label;
    final /* synthetic */ ImagePickerModule this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public ImagePickerModule$definition$1$8(ImagePickerModule imagePickerModule, Continuation<? super ImagePickerModule$definition$1$8> continuation) {
        super(2, continuation);
        this.this$0 = imagePickerModule;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        ImagePickerModule$definition$1$8 imagePickerModule$definition$1$8 = new ImagePickerModule$definition$1$8(this.this$0, continuation);
        imagePickerModule$definition$1$8.L$0 = obj;
        return imagePickerModule$definition$1$8;
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(AppContextActivityResultCaller appContextActivityResultCaller, Continuation<? super Unit> continuation) {
        return ((ImagePickerModule$definition$1$8) create(appContextActivityResultCaller, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    /* JADX WARN: Removed duplicated region for block: B:22:0x00b8 A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:23:0x00b9  */
    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final java.lang.Object invokeSuspend(java.lang.Object r10) {
        /*
            r9 = this;
            java.lang.Object r0 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r1 = r9.label
            r2 = 3
            r3 = 2
            r4 = 1
            if (r1 == 0) goto L3a
            if (r1 == r4) goto L2e
            if (r1 == r3) goto L22
            if (r1 != r2) goto L1a
            java.lang.Object r0 = r9.L$0
            expo.modules.imagepicker.ImagePickerModule r0 = (expo.modules.imagepicker.ImagePickerModule) r0
            kotlin.ResultKt.throwOnFailure(r10)
            goto Lbb
        L1a:
            java.lang.IllegalStateException r10 = new java.lang.IllegalStateException
            java.lang.String r0 = "call to 'resume' before 'invoke' with coroutine"
            r10.<init>(r0)
            throw r10
        L22:
            java.lang.Object r1 = r9.L$1
            expo.modules.imagepicker.ImagePickerModule r1 = (expo.modules.imagepicker.ImagePickerModule) r1
            java.lang.Object r3 = r9.L$0
            expo.modules.kotlin.activityresult.AppContextActivityResultCaller r3 = (expo.modules.kotlin.activityresult.AppContextActivityResultCaller) r3
            kotlin.ResultKt.throwOnFailure(r10)
            goto L90
        L2e:
            java.lang.Object r1 = r9.L$1
            expo.modules.imagepicker.ImagePickerModule r1 = (expo.modules.imagepicker.ImagePickerModule) r1
            java.lang.Object r4 = r9.L$0
            expo.modules.kotlin.activityresult.AppContextActivityResultCaller r4 = (expo.modules.kotlin.activityresult.AppContextActivityResultCaller) r4
            kotlin.ResultKt.throwOnFailure(r10)
            goto L67
        L3a:
            kotlin.ResultKt.throwOnFailure(r10)
            java.lang.Object r10 = r9.L$0
            expo.modules.kotlin.activityresult.AppContextActivityResultCaller r10 = (expo.modules.kotlin.activityresult.AppContextActivityResultCaller) r10
            expo.modules.imagepicker.ImagePickerModule r1 = r9.this$0
            expo.modules.imagepicker.contracts.CameraContract r5 = new expo.modules.imagepicker.contracts.CameraContract
            r6 = r1
            expo.modules.kotlin.providers.AppContextProvider r6 = (expo.modules.kotlin.providers.AppContextProvider) r6
            r5.<init>(r6)
            expo.modules.kotlin.activityresult.AppContextActivityResultContract r5 = (expo.modules.kotlin.activityresult.AppContextActivityResultContract) r5
            expo.modules.imagepicker.ImagePickerModule r6 = r9.this$0
            expo.modules.imagepicker.ImagePickerModule$definition$1$8$$ExternalSyntheticLambda0 r7 = new expo.modules.imagepicker.ImagePickerModule$definition$1$8$$ExternalSyntheticLambda0
            r7.<init>()
            r6 = r9
            kotlin.coroutines.Continuation r6 = (kotlin.coroutines.Continuation) r6
            r9.L$0 = r10
            r9.L$1 = r1
            r9.label = r4
            java.lang.Object r4 = r10.registerForActivityResult(r5, r7, r6)
            if (r4 != r0) goto L64
            return r0
        L64:
            r8 = r4
            r4 = r10
            r10 = r8
        L67:
            expo.modules.kotlin.activityresult.AppContextActivityResultLauncher r10 = (expo.modules.kotlin.activityresult.AppContextActivityResultLauncher) r10
            expo.modules.imagepicker.ImagePickerModule.access$setCameraLauncher$p(r1, r10)
            expo.modules.imagepicker.ImagePickerModule r1 = r9.this$0
            expo.modules.imagepicker.contracts.ImageLibraryContract r10 = new expo.modules.imagepicker.contracts.ImageLibraryContract
            r5 = r1
            expo.modules.kotlin.providers.AppContextProvider r5 = (expo.modules.kotlin.providers.AppContextProvider) r5
            r10.<init>(r5)
            expo.modules.kotlin.activityresult.AppContextActivityResultContract r10 = (expo.modules.kotlin.activityresult.AppContextActivityResultContract) r10
            expo.modules.imagepicker.ImagePickerModule r5 = r9.this$0
            expo.modules.imagepicker.ImagePickerModule$definition$1$8$$ExternalSyntheticLambda2 r6 = new expo.modules.imagepicker.ImagePickerModule$definition$1$8$$ExternalSyntheticLambda2
            r6.<init>()
            r5 = r9
            kotlin.coroutines.Continuation r5 = (kotlin.coroutines.Continuation) r5
            r9.L$0 = r4
            r9.L$1 = r1
            r9.label = r3
            java.lang.Object r10 = r4.registerForActivityResult(r10, r6, r5)
            if (r10 != r0) goto L8f
            return r0
        L8f:
            r3 = r4
        L90:
            expo.modules.kotlin.activityresult.AppContextActivityResultLauncher r10 = (expo.modules.kotlin.activityresult.AppContextActivityResultLauncher) r10
            expo.modules.imagepicker.ImagePickerModule.access$setImageLibraryLauncher$p(r1, r10)
            expo.modules.imagepicker.ImagePickerModule r10 = r9.this$0
            expo.modules.imagepicker.contracts.CropImageContract r1 = new expo.modules.imagepicker.contracts.CropImageContract
            r4 = r10
            expo.modules.kotlin.providers.AppContextProvider r4 = (expo.modules.kotlin.providers.AppContextProvider) r4
            r1.<init>(r4)
            expo.modules.kotlin.activityresult.AppContextActivityResultContract r1 = (expo.modules.kotlin.activityresult.AppContextActivityResultContract) r1
            expo.modules.imagepicker.ImagePickerModule r4 = r9.this$0
            expo.modules.imagepicker.ImagePickerModule$definition$1$8$$ExternalSyntheticLambda1 r5 = new expo.modules.imagepicker.ImagePickerModule$definition$1$8$$ExternalSyntheticLambda1
            r5.<init>()
            r4 = r9
            kotlin.coroutines.Continuation r4 = (kotlin.coroutines.Continuation) r4
            r9.L$0 = r10
            r6 = 0
            r9.L$1 = r6
            r9.label = r2
            java.lang.Object r1 = r3.registerForActivityResult(r1, r5, r4)
            if (r1 != r0) goto Lb9
            return r0
        Lb9:
            r0 = r10
            r10 = r1
        Lbb:
            expo.modules.kotlin.activityresult.AppContextActivityResultLauncher r10 = (expo.modules.kotlin.activityresult.AppContextActivityResultLauncher) r10
            expo.modules.imagepicker.ImagePickerModule.access$setCropImageLauncher$p(r0, r10)
            kotlin.Unit r10 = kotlin.Unit.INSTANCE
            return r10
        */
        throw new UnsupportedOperationException("Method not decompiled: expo.modules.imagepicker.ImagePickerModule$definition$1$8.invokeSuspend(java.lang.Object):java.lang.Object");
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: invokeSuspend$lambda-0  reason: not valid java name */
    public static final void m1641invokeSuspend$lambda0(ImagePickerModule imagePickerModule, CameraContractOptions cameraContractOptions, ContractsUtils contractsUtils) {
        imagePickerModule.handleResultUponActivityDestruction(contractsUtils, cameraContractOptions.getOptions());
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: invokeSuspend$lambda-1  reason: not valid java name */
    public static final void m1642invokeSuspend$lambda1(ImagePickerModule imagePickerModule, ImageLibraryContractOptions imageLibraryContractOptions, ContractsUtils contractsUtils) {
        imagePickerModule.handleResultUponActivityDestruction(contractsUtils, imageLibraryContractOptions.getOptions());
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: invokeSuspend$lambda-2  reason: not valid java name */
    public static final void m1643invokeSuspend$lambda2(ImagePickerModule imagePickerModule, CropImageContractOptions cropImageContractOptions, ContractsUtils contractsUtils) {
        imagePickerModule.handleResultUponActivityDestruction(contractsUtils, cropImageContractOptions.getOptions());
    }
}
