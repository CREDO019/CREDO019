package expo.modules.imagepicker;

import com.RNFetchBlob.RNFetchBlobConst;
import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: MediaHandler.kt */
@Metadata(m182k = 3, m181mv = {1, 6, 0}, m179xi = 48)
@DebugMetadata(m175c = "expo.modules.imagepicker.MediaHandler", m174f = "MediaHandler.kt", m173i = {0, 0, 0, 0, 1, 1, 1, 1, 1, 2, 2, 2, 2}, m172l = {51, 53, 56}, m171m = "handleImage", m170n = {"this", "sourceUri", "options", "outputFile", "this", "sourceUri", "options", "outputFile", "exportedImage", "sourceUri", "outputFile", "exportedImage", RNFetchBlobConst.RNFB_RESPONSE_BASE64}, m169s = {"L$0", "L$1", "L$2", "L$3", "L$0", "L$1", "L$2", "L$3", "L$4", "L$0", "L$1", "L$2", "L$3"})
/* loaded from: classes4.dex */
public final class MediaHandler$handleImage$1 extends ContinuationImpl {
    Object L$0;
    Object L$1;
    Object L$2;
    Object L$3;
    Object L$4;
    int label;
    /* synthetic */ Object result;
    final /* synthetic */ MediaHandler this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public MediaHandler$handleImage$1(MediaHandler mediaHandler, Continuation<? super MediaHandler$handleImage$1> continuation) {
        super(continuation);
        this.this$0 = mediaHandler;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        Object handleImage;
        this.result = obj;
        this.label |= Integer.MIN_VALUE;
        handleImage = this.this$0.handleImage(null, null, this);
        return handleImage;
    }
}
