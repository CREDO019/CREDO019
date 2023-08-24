package kotlin.jvm.internal;

import kotlin.reflect.KClass;
import kotlin.reflect.KDeclarationContainer;

/* loaded from: classes5.dex */
public class MutablePropertyReference1Impl extends MutablePropertyReference1 {
    public MutablePropertyReference1Impl(KDeclarationContainer kDeclarationContainer, String str, String str2) {
        super(NO_RECEIVER, ((ClassBasedDeclarationContainer) kDeclarationContainer).getJClass(), str, str2, !(kDeclarationContainer instanceof KClass) ? 1 : 0);
    }

    public MutablePropertyReference1Impl(Class cls, String str, String str2, int r10) {
        super(NO_RECEIVER, cls, str, str2, r10);
    }

    public MutablePropertyReference1Impl(Object obj, Class cls, String str, String str2, int r5) {
        super(obj, cls, str, str2, r5);
    }

    @Override // kotlin.reflect.KProperty1
    public Object get(Object obj) {
        return getGetter().call(obj);
    }

    @Override // kotlin.reflect.KMutableProperty1
    public void set(Object obj, Object obj2) {
        getSetter().call(obj, obj2);
    }
}