package expo.modules.imagepicker.contracts;

import android.content.ContentResolver;
import android.net.Uri;
import expo.modules.imagepicker.ImagePickerUtils;
import java.io.File;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.CoroutineScope;

/* compiled from: CropImageContract.kt */
@Metadata(m184d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@"}, m183d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, m182k = 3, m181mv = {1, 6, 0}, m179xi = 48)
@DebugMetadata(m175c = "expo.modules.imagepicker.contracts.CropImageContract$parseResult$1", m174f = "CropImageContract.kt", m173i = {}, m172l = {63}, m171m = "invokeSuspend", m170n = {}, m169s = {})
/* loaded from: classes4.dex */
final class CropImageContract$parseResult$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ ContentResolver $contentResolver;
    final /* synthetic */ CropImageContractOptions $input;
    final /* synthetic */ Uri $targetUri;
    int label;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public CropImageContract$parseResult$1(CropImageContractOptions cropImageContractOptions, Uri uri, ContentResolver contentResolver, Continuation<? super CropImageContract$parseResult$1> continuation) {
        super(2, continuation);
        this.$input = cropImageContractOptions;
        this.$targetUri = uri;
        this.$contentResolver = contentResolver;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new CropImageContract$parseResult$1(this.$input, this.$targetUri, this.$contentResolver, continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((CropImageContract$parseResult$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        Object obj2 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int r1 = this.label;
        if (r1 == 0) {
            ResultKt.throwOnFailure(obj);
            Uri sourceUri = this.$input.getSourceUri();
            File file = androidx.core.net.Uri.toFile(this.$targetUri);
            ContentResolver contentResolver = this.$contentResolver;
            Intrinsics.checkNotNullExpressionValue(contentResolver, "contentResolver");
            this.label = 1;
            if (ImagePickerUtils.copyExifData(sourceUri, file, contentResolver, this) == obj2) {
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
