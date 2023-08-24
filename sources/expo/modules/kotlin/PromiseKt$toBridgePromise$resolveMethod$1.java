package expo.modules.kotlin;

import expo.modules.kotlin.jni.JavaCallback;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.FunctionReferenceImpl;

/* compiled from: Promise.kt */
@Metadata(m182k = 3, m181mv = {1, 6, 0}, m179xi = 48)
/* loaded from: classes4.dex */
/* synthetic */ class PromiseKt$toBridgePromise$resolveMethod$1 extends FunctionReferenceImpl implements Function1<Object, Unit> {
    /* JADX INFO: Access modifiers changed from: package-private */
    public PromiseKt$toBridgePromise$resolveMethod$1(Object obj) {
        super(1, obj, JavaCallback.class, "invoke", "invoke(Ljava/lang/Object;)V", 0);
    }

    @Override // kotlin.jvm.functions.Function1
    public /* bridge */ /* synthetic */ Unit invoke(Object obj) {
        invoke2(obj);
        return Unit.INSTANCE;
    }

    /* renamed from: invoke  reason: avoid collision after fix types in other method */
    public final void invoke2(Object obj) {
        ((JavaCallback) this.receiver).invoke(obj);
    }
}
