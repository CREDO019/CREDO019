package expo.modules.imagepicker;

import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: ImagePickerModule.kt */
@Metadata(m182k = 3, m181mv = {1, 6, 0}, m179xi = 48)
@DebugMetadata(m175c = "expo.modules.imagepicker.ImagePickerModule", m174f = "ImagePickerModule.kt", m173i = {0, 0, 0, 1, 1, 1}, m172l = {133, 140, 144}, m171m = "launchContract", m170n = {"this", "options", "result", "this", "options", "result"}, m169s = {"L$0", "L$1", "L$2", "L$0", "L$1", "L$2"})
/* loaded from: classes4.dex */
public final class ImagePickerModule$launchContract$1 extends ContinuationImpl {
    Object L$0;
    Object L$1;
    Object L$2;
    Object L$3;
    int label;
    /* synthetic */ Object result;
    final /* synthetic */ ImagePickerModule this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public ImagePickerModule$launchContract$1(ImagePickerModule imagePickerModule, Continuation<? super ImagePickerModule$launchContract$1> continuation) {
        super(continuation);
        this.this$0 = imagePickerModule;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        Object launchContract;
        this.result = obj;
        this.label |= Integer.MIN_VALUE;
        launchContract = this.this$0.launchContract(null, null, this);
        return launchContract;
    }
}
