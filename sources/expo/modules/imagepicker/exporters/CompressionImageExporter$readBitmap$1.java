package expo.modules.imagepicker.exporters;

import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: CompressionImageExporter.kt */
@Metadata(m182k = 3, m181mv = {1, 6, 0}, m179xi = 48)
@DebugMetadata(m175c = "expo.modules.imagepicker.exporters.CompressionImageExporter", m174f = "CompressionImageExporter.kt", m173i = {}, m172l = {52}, m171m = "readBitmap", m170n = {}, m169s = {})
/* loaded from: classes4.dex */
public final class CompressionImageExporter$readBitmap$1 extends ContinuationImpl {
    int label;
    /* synthetic */ Object result;
    final /* synthetic */ CompressionImageExporter this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public CompressionImageExporter$readBitmap$1(CompressionImageExporter compressionImageExporter, Continuation<? super CompressionImageExporter$readBitmap$1> continuation) {
        super(continuation);
        this.this$0 = compressionImageExporter;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        Object readBitmap;
        this.result = obj;
        this.label |= Integer.MIN_VALUE;
        readBitmap = this.this$0.readBitmap(null, this);
        return readBitmap;
    }
}
