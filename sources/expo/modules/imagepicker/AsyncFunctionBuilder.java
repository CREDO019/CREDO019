package expo.modules.imagepicker;

import expo.modules.imagepicker.contracts.CameraContractOptions;
import java.io.File;
import java.util.Objects;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function3;
import kotlinx.coroutines.CoroutineScope;

@Metadata(m184d1 = {"\u0000\u0018\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0011\n\u0002\b\u0002\u0010\u0000\u001a\u0004\u0018\u00010\u0001\"\u0006\b\u0000\u0010\u0002\u0018\u0001\"\u0006\b\u0001\u0010\u0003\u0018\u0001*\u00020\u00042\u0010\u0010\u0005\u001a\f\u0012\b\b\u0001\u0012\u0004\u0018\u00010\u00010\u0006H\u008a@¨\u0006\b"}, m183d2 = {"<anonymous>", "", "R", "P0", "Lkotlinx/coroutines/CoroutineScope;", "it", "", "expo/modules/kotlin/functions/AsyncFunctionBuilder$SuspendBody$3", "expo/modules/kotlin/functions/AsyncFunctionBuilderKt$Coroutine$$inlined$SuspendBody$2"}, m182k = 3, m181mv = {1, 6, 0}, m179xi = 48)
@DebugMetadata(m175c = "expo.modules.imagepicker.ImagePickerModule$definition$lambda-7$$inlined$Coroutine$1", m174f = "ImagePickerModule.kt", m173i = {0}, m172l = {81, 87}, m171m = "invokeSuspend", m170n = {"options"}, m169s = {"L$0"})
/* renamed from: expo.modules.imagepicker.ImagePickerModule$definition$lambda-7$$inlined$Coroutine$1 */
/* loaded from: classes4.dex */
public final class AsyncFunctionBuilder extends SuspendLambda implements Function3<CoroutineScope, Object[], Continuation<? super Object>, Object> {
    /* synthetic */ Object L$0;
    int label;
    final /* synthetic */ ImagePickerModule this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public AsyncFunctionBuilder(Continuation continuation, ImagePickerModule imagePickerModule) {
        super(3, continuation);
        this.this$0 = imagePickerModule;
    }

    @Override // kotlin.jvm.functions.Function3
    public /* bridge */ /* synthetic */ Object invoke(CoroutineScope coroutineScope, Object[] objArr, Continuation<? super Object> continuation) {
        return invoke2(coroutineScope, objArr, (Continuation<Object>) continuation);
    }

    /* renamed from: invoke  reason: avoid collision after fix types in other method */
    public final Object invoke2(CoroutineScope coroutineScope, Object[] objArr, Continuation<Object> continuation) {
        AsyncFunctionBuilder asyncFunctionBuilder = new AsyncFunctionBuilder(continuation, this.this$0);
        asyncFunctionBuilder.L$0 = objArr;
        return asyncFunctionBuilder.invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        ImagePickerOptions imagePickerOptions;
        Object ensureCameraPermissionsAreGranted;
        File cacheDirectory;
        Object obj2 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int r1 = this.label;
        if (r1 == 0) {
            ResultKt.throwOnFailure(obj);
            Object obj3 = ((Object[]) this.L$0)[0];
            Objects.requireNonNull(obj3, "null cannot be cast to non-null type expo.modules.imagepicker.ImagePickerOptions");
            imagePickerOptions = (ImagePickerOptions) obj3;
            this.this$0.ensureTargetActivityIsAvailable(imagePickerOptions);
            ImagePickerModule imagePickerModule = this.this$0;
            this.L$0 = imagePickerOptions;
            this.label = 1;
            ensureCameraPermissionsAreGranted = imagePickerModule.ensureCameraPermissionsAreGranted(this);
            if (ensureCameraPermissionsAreGranted == obj2) {
                return obj2;
            }
        } else if (r1 != 1) {
            if (r1 != 2) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            ResultKt.throwOnFailure(obj);
        } else {
            imagePickerOptions = (ImagePickerOptions) this.L$0;
            ResultKt.throwOnFailure(obj);
        }
        cacheDirectory = this.this$0.getCacheDirectory();
        CameraContractOptions cameraContractOptions = imagePickerOptions.toCameraContractOptions(ImagePickerUtils.toContentUri(ImagePickerUtils.createOutputFile(cacheDirectory, imagePickerOptions.getMediaTypes().toFileExtension()), this.this$0.getContext()));
        this.L$0 = null;
        this.label = 2;
        obj = this.this$0.launchContract(new ImagePickerModule$definition$1$5$1(this.this$0, cameraContractOptions, null), imagePickerOptions, this);
        return obj == obj2 ? obj2 : obj;
    }
}
