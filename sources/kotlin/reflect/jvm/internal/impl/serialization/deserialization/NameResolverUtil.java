package kotlin.reflect.jvm.internal.impl.serialization.deserialization;

import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.jvm.internal.impl.metadata.deserialization.NameResolver;
import kotlin.reflect.jvm.internal.impl.name.ClassId;
import kotlin.reflect.jvm.internal.impl.name.Name;

/* renamed from: kotlin.reflect.jvm.internal.impl.serialization.deserialization.NameResolverUtilKt */
/* loaded from: classes5.dex */
public final class NameResolverUtil {
    public static final ClassId getClassId(NameResolver nameResolver, int r2) {
        Intrinsics.checkNotNullParameter(nameResolver, "<this>");
        ClassId fromString = ClassId.fromString(nameResolver.getQualifiedClassName(r2), nameResolver.isLocalClassName(r2));
        Intrinsics.checkNotNullExpressionValue(fromString, "fromString(getQualifiedC… isLocalClassName(index))");
        return fromString;
    }

    public static final Name getName(NameResolver nameResolver, int r2) {
        Intrinsics.checkNotNullParameter(nameResolver, "<this>");
        Name guessByFirstCharacter = Name.guessByFirstCharacter(nameResolver.getString(r2));
        Intrinsics.checkNotNullExpressionValue(guessByFirstCharacter, "guessByFirstCharacter(getString(index))");
        return guessByFirstCharacter;
    }
}
