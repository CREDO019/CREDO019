package kotlin.reflect.jvm.internal.impl.descriptors.runtime.structure;

import java.lang.annotation.Annotation;
import java.util.ArrayList;
import java.util.List;
import kotlin.jvm.JvmClassMapping;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.jvm.internal.impl.name.FqName;

/* compiled from: ReflectJavaAnnotationOwner.kt */
/* loaded from: classes5.dex */
public final class ReflectJavaAnnotationOwnerKt {
    public static final List<ReflectJavaAnnotation> getAnnotations(Annotation[] annotationArr) {
        Intrinsics.checkNotNullParameter(annotationArr, "<this>");
        ArrayList arrayList = new ArrayList(annotationArr.length);
        int length = annotationArr.length;
        int r2 = 0;
        while (r2 < length) {
            Annotation annotation = annotationArr[r2];
            r2++;
            arrayList.add(new ReflectJavaAnnotation(annotation));
        }
        return arrayList;
    }

    public static final ReflectJavaAnnotation findAnnotation(Annotation[] annotationArr, FqName fqName) {
        Annotation annotation;
        Intrinsics.checkNotNullParameter(annotationArr, "<this>");
        Intrinsics.checkNotNullParameter(fqName, "fqName");
        int length = annotationArr.length;
        int r1 = 0;
        while (true) {
            if (r1 >= length) {
                annotation = null;
                break;
            }
            annotation = annotationArr[r1];
            r1++;
            if (Intrinsics.areEqual(reflectClassUtil.getClassId(JvmClassMapping.getJavaClass(JvmClassMapping.getAnnotationClass(annotation))).asSingleFqName(), fqName)) {
                break;
            }
        }
        if (annotation == null) {
            return null;
        }
        return new ReflectJavaAnnotation(annotation);
    }
}
