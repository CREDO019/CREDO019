package expo.modules.imagepicker.exporters;

import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: CompressionImageExporter.kt */
@Metadata(m182k = 3, m181mv = {1, 6, 0}, m179xi = 48)
@DebugMetadata(m175c = "expo.modules.imagepicker.exporters.CompressionImageExporter", m174f = "CompressionImageExporter.kt", m173i = {0, 0, 0, 0, 1, 1, 1, 1, 1, 2, 2, 2}, m172l = {34, 36, 37}, m171m = "exportAsync", m170n = {"this", "source", "output", "contentResolver", "this", "source", "output", "contentResolver", "bitmap", "this", "output", "bitmap"}, m169s = {"L$0", "L$1", "L$2", "L$3", "L$0", "L$1", "L$2", "L$3", "L$4", "L$0", "L$1", "L$2"})
/* loaded from: classes4.dex */
public final class CompressionImageExporter$exportAsync$1 extends ContinuationImpl {
    Object L$0;
    Object L$1;
    Object L$2;
    Object L$3;
    Object L$4;
    int label;
    /* synthetic */ Object result;
    final /* synthetic */ CompressionImageExporter this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public CompressionImageExporter$exportAsync$1(CompressionImageExporter compressionImageExporter, Continuation<? super CompressionImageExporter$exportAsync$1> continuation) {
        super(continuation);
        this.this$0 = compressionImageExporter;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        this.result = obj;
        this.label |= Integer.MIN_VALUE;
        return this.this$0.exportAsync(null, null, null, this);
    }
}
