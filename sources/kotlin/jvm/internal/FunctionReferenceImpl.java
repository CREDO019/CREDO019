package kotlin.jvm.internal;

import kotlin.reflect.KClass;
import kotlin.reflect.KDeclarationContainer;

/* loaded from: classes5.dex */
public class FunctionReferenceImpl extends FunctionReference {
    public FunctionReferenceImpl(int r8, KDeclarationContainer kDeclarationContainer, String str, String str2) {
        super(r8, NO_RECEIVER, ((ClassBasedDeclarationContainer) kDeclarationContainer).getJClass(), str, str2, !(kDeclarationContainer instanceof KClass) ? 1 : 0);
    }

    public FunctionReferenceImpl(int r8, Class cls, String str, String str2, int r12) {
        super(r8, NO_RECEIVER, cls, str, str2, r12);
    }

    public FunctionReferenceImpl(int r1, Object obj, Class cls, String str, String str2, int r6) {
        super(r1, obj, cls, str, str2, r6);
    }
}
