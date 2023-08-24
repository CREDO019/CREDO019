package expo.modules.imagepicker;

import expo.modules.imagepicker.contracts.ContractsUtils;
import expo.modules.imagepicker.contracts.ImageLibraryContractOptions;
import expo.modules.kotlin.activityresult.AppContextActivityResultLauncher;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: ImagePickerModule.kt */
@Metadata(m184d1 = {"\u0000\u0006\n\u0000\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001H\u008a@"}, m183d2 = {"<anonymous>", "Lexpo/modules/imagepicker/contracts/ImagePickerContractResult;"}, m182k = 3, m181mv = {1, 6, 0}, m179xi = 48)
@DebugMetadata(m175c = "expo.modules.imagepicker.ImagePickerModule$definition$1$6$1", m174f = "ImagePickerModule.kt", m173i = {}, m172l = {73}, m171m = "invokeSuspend", m170n = {}, m169s = {})
/* loaded from: classes4.dex */
final class ImagePickerModule$definition$1$6$1 extends SuspendLambda implements Function1<Continuation<? super ContractsUtils>, Object> {
    final /* synthetic */ ImageLibraryContractOptions $contractOptions;
    int label;
    final /* synthetic */ ImagePickerModule this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public ImagePickerModule$definition$1$6$1(ImagePickerModule imagePickerModule, ImageLibraryContractOptions imageLibraryContractOptions, Continuation<? super ImagePickerModule$definition$1$6$1> continuation) {
        super(1, continuation);
        this.this$0 = imagePickerModule;
        this.$contractOptions = imageLibraryContractOptions;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Continuation<?> continuation) {
        return new ImagePickerModule$definition$1$6$1(this.this$0, this.$contractOptions, continuation);
    }

    @Override // kotlin.jvm.functions.Function1
    public final Object invoke(Continuation<? super ContractsUtils> continuation) {
        return ((ImagePickerModule$definition$1$6$1) create(continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        AppContextActivityResultLauncher appContextActivityResultLauncher;
        Object obj2 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int r1 = this.label;
        if (r1 == 0) {
            ResultKt.throwOnFailure(obj);
            appContextActivityResultLauncher = this.this$0.imageLibraryLauncher;
            if (appContextActivityResultLauncher == null) {
                Intrinsics.throwUninitializedPropertyAccessException("imageLibraryLauncher");
                appContextActivityResultLauncher = null;
            }
            this.label = 1;
            obj = appContextActivityResultLauncher.launch((AppContextActivityResultLauncher) this.$contractOptions, (Continuation) this);
            if (obj == obj2) {
                return obj2;
            }
        } else if (r1 != 1) {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        } else {
            ResultKt.throwOnFailure(obj);
        }
        return obj;
    }
}
