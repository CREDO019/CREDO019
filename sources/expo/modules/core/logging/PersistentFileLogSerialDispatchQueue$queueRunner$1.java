package expo.modules.core.logging;

import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CoroutineScope;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: PersistentFileLogSerialDispatchQueue.kt */
@Metadata(m184d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@"}, m183d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, m182k = 3, m181mv = {1, 6, 0}, m179xi = 48)
@DebugMetadata(m175c = "expo.modules.core.logging.PersistentFileLogSerialDispatchQueue$queueRunner$1", m174f = "PersistentFileLogSerialDispatchQueue.kt", m173i = {}, m172l = {23}, m171m = "invokeSuspend", m170n = {}, m169s = {})
/* loaded from: classes4.dex */
public final class PersistentFileLogSerialDispatchQueue$queueRunner$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    int label;
    final /* synthetic */ PersistentFileLogSerialDispatchQueue this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public PersistentFileLogSerialDispatchQueue$queueRunner$1(PersistentFileLogSerialDispatchQueue persistentFileLogSerialDispatchQueue, Continuation<? super PersistentFileLogSerialDispatchQueue$queueRunner$1> continuation) {
        super(2, continuation);
        this.this$0 = persistentFileLogSerialDispatchQueue;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new PersistentFileLogSerialDispatchQueue$queueRunner$1(this.this$0, continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((PersistentFileLogSerialDispatchQueue$queueRunner$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    /* JADX WARN: Removed duplicated region for block: B:11:0x002e A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:12:0x002f  */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:12:0x002f -> B:13:0x0033). Please submit an issue!!! */
    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final java.lang.Object invokeSuspend(java.lang.Object r6) {
        /*
            r5 = this;
            java.lang.Object r0 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r1 = r5.label
            r2 = 1
            if (r1 == 0) goto L19
            if (r1 != r2) goto L11
            kotlin.ResultKt.throwOnFailure(r6)
            r1 = r0
            r0 = r5
            goto L33
        L11:
            java.lang.IllegalStateException r6 = new java.lang.IllegalStateException
            java.lang.String r0 = "call to 'resume' before 'invoke' with coroutine"
            r6.<init>(r0)
            throw r6
        L19:
            kotlin.ResultKt.throwOnFailure(r6)
            r6 = r5
        L1d:
            expo.modules.core.logging.PersistentFileLogSerialDispatchQueue r1 = r6.this$0
            kotlinx.coroutines.channels.Channel r1 = expo.modules.core.logging.PersistentFileLogSerialDispatchQueue.access$getChannel$p(r1)
            r3 = r6
            kotlin.coroutines.Continuation r3 = (kotlin.coroutines.Continuation) r3
            r6.label = r2
            java.lang.Object r1 = r1.receive(r3)
            if (r1 != r0) goto L2f
            return r0
        L2f:
            r4 = r0
            r0 = r6
            r6 = r1
            r1 = r4
        L33:
            kotlin.jvm.functions.Function0 r6 = (kotlin.jvm.functions.Functions) r6
            r6.invoke()
            r6 = r0
            r0 = r1
            goto L1d
        */
        throw new UnsupportedOperationException("Method not decompiled: expo.modules.core.logging.PersistentFileLogSerialDispatchQueue$queueRunner$1.invokeSuspend(java.lang.Object):java.lang.Object");
    }
}
