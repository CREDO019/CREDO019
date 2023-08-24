package expo.modules.imagepicker.exporters;

import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: RawImageExporter.kt */
@Metadata(m182k = 3, m181mv = {1, 6, 0}, m179xi = 48)
@DebugMetadata(m175c = "expo.modules.imagepicker.exporters.RawImageExporter", m174f = "RawImageExporter.kt", m173i = {0}, m172l = {15}, m171m = "exportAsync", m170n = {"output"}, m169s = {"L$0"})
/* loaded from: classes4.dex */
public final class RawImageExporter$exportAsync$1 extends ContinuationImpl {
    Object L$0;
    int label;
    /* synthetic */ Object result;
    final /* synthetic */ RawImageExporter this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public RawImageExporter$exportAsync$1(RawImageExporter rawImageExporter, Continuation<? super RawImageExporter$exportAsync$1> continuation) {
        super(continuation);
        this.this$0 = rawImageExporter;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        this.result = obj;
        this.label |= Integer.MIN_VALUE;
        return this.this$0.exportAsync(null, null, null, this);
    }
}
