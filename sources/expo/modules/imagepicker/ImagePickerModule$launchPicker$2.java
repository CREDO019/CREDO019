package expo.modules.imagepicker;

import android.os.OperationCanceledException;
import expo.modules.imagepicker.contracts.ContractsUtils;
import kotlin.Metadata;
import kotlin.NoWhenBranchMatchedException;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CoroutineScope;

/* compiled from: ImagePickerModule.kt */
@Metadata(m184d1 = {"\u0000\n\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@"}, m183d2 = {"<anonymous>", "Lexpo/modules/imagepicker/contracts/ImagePickerContractResult$Success;", "Lkotlinx/coroutines/CoroutineScope;"}, m182k = 3, m181mv = {1, 6, 0}, m179xi = 48)
@DebugMetadata(m175c = "expo.modules.imagepicker.ImagePickerModule$launchPicker$2", m174f = "ImagePickerModule.kt", m173i = {}, m172l = {166}, m171m = "invokeSuspend", m170n = {}, m169s = {})
/* loaded from: classes4.dex */
final class ImagePickerModule$launchPicker$2 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super ContractsUtils.Success>, Object> {
    final /* synthetic */ Function1<Continuation<? super ContractsUtils>, Object> $pickerLauncher;
    int label;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    /* JADX WARN: Multi-variable type inference failed */
    public ImagePickerModule$launchPicker$2(Function1<? super Continuation<? super ContractsUtils>, ? extends Object> function1, Continuation<? super ImagePickerModule$launchPicker$2> continuation) {
        super(2, continuation);
        this.$pickerLauncher = function1;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new ImagePickerModule$launchPicker$2(this.$pickerLauncher, continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super ContractsUtils.Success> continuation) {
        return ((ImagePickerModule$launchPicker$2) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        Object obj2 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int r1 = this.label;
        if (r1 == 0) {
            ResultKt.throwOnFailure(obj);
            Function1<Continuation<? super ContractsUtils>, Object> function1 = this.$pickerLauncher;
            this.label = 1;
            obj = function1.invoke(this);
            if (obj == obj2) {
                return obj2;
            }
        } else if (r1 != 1) {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        } else {
            ResultKt.throwOnFailure(obj);
        }
        ContractsUtils contractsUtils = (ContractsUtils) obj;
        if (contractsUtils instanceof ContractsUtils.Success) {
            return (ContractsUtils.Success) contractsUtils;
        }
        if (contractsUtils instanceof ContractsUtils.Cancelled) {
            throw new OperationCanceledException();
        }
        throw new NoWhenBranchMatchedException();
    }
}
