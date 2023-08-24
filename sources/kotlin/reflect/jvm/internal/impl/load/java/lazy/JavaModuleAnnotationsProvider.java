package kotlin.reflect.jvm.internal.impl.load.java.lazy;

import java.util.List;
import kotlin.reflect.jvm.internal.impl.load.java.structure.javaElements;
import kotlin.reflect.jvm.internal.impl.name.ClassId;

/* compiled from: JavaModuleAnnotationsProvider.kt */
/* loaded from: classes5.dex */
public interface JavaModuleAnnotationsProvider {
    List<javaElements> getAnnotationsForModuleOwnerOfClass(ClassId classId);
}
