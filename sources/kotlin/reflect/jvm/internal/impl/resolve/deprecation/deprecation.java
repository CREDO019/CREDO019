package kotlin.reflect.jvm.internal.impl.resolve.deprecation;

import kotlin.reflect.jvm.internal.impl.descriptors.CallableDescriptor;

/* renamed from: kotlin.reflect.jvm.internal.impl.resolve.deprecation.DeprecationKt */
/* loaded from: classes5.dex */
public final class deprecation {
    private static final CallableDescriptor.UserDataKey<Object> DEPRECATED_FUNCTION_KEY = new CallableDescriptor.UserDataKey<Object>() { // from class: kotlin.reflect.jvm.internal.impl.resolve.deprecation.DeprecationKt$DEPRECATED_FUNCTION_KEY$1
    };

    public static final CallableDescriptor.UserDataKey<Object> getDEPRECATED_FUNCTION_KEY() {
        return DEPRECATED_FUNCTION_KEY;
    }
}
