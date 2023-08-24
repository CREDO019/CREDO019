package kotlin.reflect.jvm.internal.impl.load.java.structure;

import java.util.List;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: javaElements.kt */
/* loaded from: classes5.dex */
public interface JavaMethod extends JavaMember, JavaTypeParameterListOwner {
    annotationArguments getAnnotationParameterDefaultValue();

    boolean getHasAnnotationParameterDefaultValue();

    JavaType getReturnType();

    List<JavaValueParameter> getValueParameters();

    /* compiled from: javaElements.kt */
    /* loaded from: classes5.dex */
    public static final class DefaultImpls {
        public static boolean getHasAnnotationParameterDefaultValue(JavaMethod javaMethod) {
            Intrinsics.checkNotNullParameter(javaMethod, "this");
            return javaMethod.getAnnotationParameterDefaultValue() != null;
        }
    }
}
