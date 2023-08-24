package kotlin.reflect.jvm.internal.impl.types;

import kotlin.jvm.internal.Intrinsics;

/* compiled from: TypeCapabilities.kt */
/* loaded from: classes5.dex */
public final class TypeCapabilitiesKt {
    public static final boolean isCustomTypeVariable(KotlinType kotlinType) {
        Intrinsics.checkNotNullParameter(kotlinType, "<this>");
        UnwrappedType unwrap = kotlinType.unwrap();
        TypeCapabilities typeCapabilities = unwrap instanceof TypeCapabilities ? (TypeCapabilities) unwrap : null;
        if (typeCapabilities == null) {
            return false;
        }
        return typeCapabilities.isTypeVariable();
    }

    public static final TypeCapabilities getCustomTypeVariable(KotlinType kotlinType) {
        Intrinsics.checkNotNullParameter(kotlinType, "<this>");
        UnwrappedType unwrap = kotlinType.unwrap();
        TypeCapabilities typeCapabilities = unwrap instanceof TypeCapabilities ? (TypeCapabilities) unwrap : null;
        if (typeCapabilities != null && typeCapabilities.isTypeVariable()) {
            return typeCapabilities;
        }
        return null;
    }
}
