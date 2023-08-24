package expo.modules.kotlin.exception;

import kotlin.Metadata;

/* compiled from: CodedException.kt */
@Metadata(m184d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0000\u0018\u00002\u00020\u0001B%\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\t¢\u0006\u0002\u0010\n¨\u0006\u000b"}, m183d2 = {"Lexpo/modules/kotlin/exception/FieldCastException;", "Lexpo/modules/kotlin/exception/DecoratedException;", "fieldName", "", "fieldType", "Lkotlin/reflect/KType;", "providedType", "Lcom/facebook/react/bridge/ReadableType;", "cause", "Lexpo/modules/kotlin/exception/CodedException;", "(Ljava/lang/String;Lkotlin/reflect/KType;Lcom/facebook/react/bridge/ReadableType;Lexpo/modules/kotlin/exception/CodedException;)V", "expo-modules-core_release"}, m182k = 1, m181mv = {1, 6, 0}, m179xi = 48)
/* loaded from: classes4.dex */
public final class FieldCastException extends DecoratedException {
    /* JADX WARN: Illegal instructions before constructor call */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public FieldCastException(java.lang.String r3, kotlin.reflect.KType r4, com.facebook.react.bridge.ReadableType r5, expo.modules.kotlin.exception.CodedException r6) {
        /*
            r2 = this;
            java.lang.String r0 = "fieldName"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r3, r0)
            java.lang.String r0 = "fieldType"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r4, r0)
            java.lang.String r0 = "providedType"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r5, r0)
            java.lang.String r0 = "cause"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r6, r0)
            java.lang.String r5 = r5.name()
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            r0.<init>()
            java.lang.String r1 = "Cannot cast '"
            r0.append(r1)
            r0.append(r5)
            java.lang.String r5 = "' for field '"
            r0.append(r5)
            r0.append(r3)
            java.lang.String r3 = "' ('"
            r0.append(r3)
            r0.append(r4)
            java.lang.String r3 = "')."
            r0.append(r3)
            java.lang.String r3 = r0.toString()
            r2.<init>(r3, r6)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: expo.modules.kotlin.exception.FieldCastException.<init>(java.lang.String, kotlin.reflect.KType, com.facebook.react.bridge.ReadableType, expo.modules.kotlin.exception.CodedException):void");
    }
}
