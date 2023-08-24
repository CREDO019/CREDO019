package kotlin.reflect.jvm.internal.impl.descriptors.runtime.components;

import java.lang.annotation.Annotation;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Objects;
import java.util.Set;
import kotlin.collections.ArraysKt;
import kotlin.jvm.JvmClassMapping;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.jvm.internal.impl.builtins.PrimitiveType;
import kotlin.reflect.jvm.internal.impl.builtins.StandardNames;
import kotlin.reflect.jvm.internal.impl.builtins.jvm.JavaToKotlinClassMap;
import kotlin.reflect.jvm.internal.impl.descriptors.runtime.structure.reflectClassUtil;
import kotlin.reflect.jvm.internal.impl.load.kotlin.KotlinJvmBinaryClass;
import kotlin.reflect.jvm.internal.impl.name.ClassId;
import kotlin.reflect.jvm.internal.impl.name.FqName;
import kotlin.reflect.jvm.internal.impl.name.Name;
import kotlin.reflect.jvm.internal.impl.name.SpecialNames;
import kotlin.reflect.jvm.internal.impl.resolve.constants.ClassLiteralValue;
import kotlin.reflect.jvm.internal.impl.resolve.jvm.JvmPrimitiveType;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: ReflectKotlinClass.kt */
/* loaded from: classes5.dex */
public final class ReflectClassStructure {
    public static final ReflectClassStructure INSTANCE = new ReflectClassStructure();

    private ReflectClassStructure() {
    }

    public final void loadClassAnnotations(Class<?> klass, KotlinJvmBinaryClass.AnnotationVisitor visitor) {
        Intrinsics.checkNotNullParameter(klass, "klass");
        Intrinsics.checkNotNullParameter(visitor, "visitor");
        Annotation[] declaredAnnotations = klass.getDeclaredAnnotations();
        Intrinsics.checkNotNullExpressionValue(declaredAnnotations, "klass.declaredAnnotations");
        int length = declaredAnnotations.length;
        int r1 = 0;
        while (r1 < length) {
            Annotation annotation = declaredAnnotations[r1];
            r1++;
            Intrinsics.checkNotNullExpressionValue(annotation, "annotation");
            processAnnotation(visitor, annotation);
        }
        visitor.visitEnd();
    }

    public final void visitMembers(Class<?> klass, KotlinJvmBinaryClass.MemberVisitor memberVisitor) {
        Intrinsics.checkNotNullParameter(klass, "klass");
        Intrinsics.checkNotNullParameter(memberVisitor, "memberVisitor");
        loadMethodAnnotations(klass, memberVisitor);
        loadConstructorAnnotations(klass, memberVisitor);
        loadFieldAnnotations(klass, memberVisitor);
    }

    private final void loadMethodAnnotations(Class<?> cls, KotlinJvmBinaryClass.MemberVisitor memberVisitor) {
        Method[] methodArr;
        int r17;
        Method[] declaredMethods = cls.getDeclaredMethods();
        Intrinsics.checkNotNullExpressionValue(declaredMethods, "klass.declaredMethods");
        int length = declaredMethods.length;
        int r3 = 0;
        while (r3 < length) {
            Method method = declaredMethods[r3];
            r3++;
            Name identifier = Name.identifier(method.getName());
            Intrinsics.checkNotNullExpressionValue(identifier, "identifier(method.name)");
            SignatureSerializer signatureSerializer = SignatureSerializer.INSTANCE;
            Intrinsics.checkNotNullExpressionValue(method, "method");
            KotlinJvmBinaryClass.MethodAnnotationVisitor visitMethod = memberVisitor.visitMethod(identifier, signatureSerializer.methodDesc(method));
            if (visitMethod == null) {
                methodArr = declaredMethods;
                r17 = length;
            } else {
                Annotation[] declaredAnnotations = method.getDeclaredAnnotations();
                Intrinsics.checkNotNullExpressionValue(declaredAnnotations, "method.declaredAnnotations");
                int length2 = declaredAnnotations.length;
                int r9 = 0;
                while (r9 < length2) {
                    Annotation annotation = declaredAnnotations[r9];
                    r9++;
                    Intrinsics.checkNotNullExpressionValue(annotation, "annotation");
                    processAnnotation(visitMethod, annotation);
                }
                Annotation[][] parameterAnnotations = method.getParameterAnnotations();
                Intrinsics.checkNotNullExpressionValue(parameterAnnotations, "method.parameterAnnotations");
                Annotation[][] annotationArr = parameterAnnotations;
                int length3 = annotationArr.length;
                int r8 = 0;
                while (r8 < length3) {
                    Annotation[] annotations = annotationArr[r8];
                    int r11 = r8 + 1;
                    Intrinsics.checkNotNullExpressionValue(annotations, "annotations");
                    int length4 = annotations.length;
                    int r14 = 0;
                    while (r14 < length4) {
                        Annotation annotation2 = annotations[r14];
                        r14++;
                        Class<?> javaClass = JvmClassMapping.getJavaClass(JvmClassMapping.getAnnotationClass(annotation2));
                        Method[] methodArr2 = declaredMethods;
                        ClassId classId = reflectClassUtil.getClassId(javaClass);
                        int r172 = length;
                        Intrinsics.checkNotNullExpressionValue(annotation2, "annotation");
                        KotlinJvmBinaryClass.AnnotationArgumentVisitor visitParameterAnnotation = visitMethod.visitParameterAnnotation(r8, classId, new ReflectAnnotationSource(annotation2));
                        if (visitParameterAnnotation != null) {
                            INSTANCE.processAnnotationArguments(visitParameterAnnotation, annotation2, javaClass);
                        }
                        declaredMethods = methodArr2;
                        length = r172;
                    }
                    r8 = r11;
                }
                methodArr = declaredMethods;
                r17 = length;
                visitMethod.visitEnd();
            }
            declaredMethods = methodArr;
            length = r17;
        }
    }

    private final void loadConstructorAnnotations(Class<?> cls, KotlinJvmBinaryClass.MemberVisitor memberVisitor) {
        Constructor<?>[] constructorArr;
        int r16;
        int r18;
        Constructor<?>[] declaredConstructors = cls.getDeclaredConstructors();
        Intrinsics.checkNotNullExpressionValue(declaredConstructors, "klass.declaredConstructors");
        int length = declaredConstructors.length;
        int r3 = 0;
        while (r3 < length) {
            Constructor<?> constructor = declaredConstructors[r3];
            int r32 = r3 + 1;
            Name name = SpecialNames.INIT;
            SignatureSerializer signatureSerializer = SignatureSerializer.INSTANCE;
            Intrinsics.checkNotNullExpressionValue(constructor, "constructor");
            KotlinJvmBinaryClass.MethodAnnotationVisitor visitMethod = memberVisitor.visitMethod(name, signatureSerializer.constructorDesc(constructor));
            if (visitMethod == null) {
                constructorArr = declaredConstructors;
                r16 = length;
                r18 = r32;
            } else {
                Annotation[] declaredAnnotations = constructor.getDeclaredAnnotations();
                Intrinsics.checkNotNullExpressionValue(declaredAnnotations, "constructor.declaredAnnotations");
                int length2 = declaredAnnotations.length;
                int r9 = 0;
                while (r9 < length2) {
                    Annotation annotation = declaredAnnotations[r9];
                    r9++;
                    Intrinsics.checkNotNullExpressionValue(annotation, "annotation");
                    processAnnotation(visitMethod, annotation);
                }
                Annotation[][] parameterAnnotations = constructor.getParameterAnnotations();
                Intrinsics.checkNotNullExpressionValue(parameterAnnotations, "parameterAnnotations");
                Annotation[][] annotationArr = parameterAnnotations;
                if (!(annotationArr.length == 0)) {
                    int length3 = constructor.getParameterTypes().length - annotationArr.length;
                    int length4 = annotationArr.length;
                    int r92 = 0;
                    while (r92 < length4) {
                        Annotation[] annotations = parameterAnnotations[r92];
                        int r12 = r92 + 1;
                        Intrinsics.checkNotNullExpressionValue(annotations, "annotations");
                        int length5 = annotations.length;
                        int r15 = 0;
                        while (r15 < length5) {
                            Annotation annotation2 = annotations[r15];
                            r15++;
                            Constructor<?>[] constructorArr2 = declaredConstructors;
                            Class<?> javaClass = JvmClassMapping.getJavaClass(JvmClassMapping.getAnnotationClass(annotation2));
                            int r162 = length;
                            int r182 = r32;
                            ClassId classId = reflectClassUtil.getClassId(javaClass);
                            int r19 = length3;
                            Intrinsics.checkNotNullExpressionValue(annotation2, "annotation");
                            KotlinJvmBinaryClass.AnnotationArgumentVisitor visitParameterAnnotation = visitMethod.visitParameterAnnotation(r92 + length3, classId, new ReflectAnnotationSource(annotation2));
                            if (visitParameterAnnotation != null) {
                                INSTANCE.processAnnotationArguments(visitParameterAnnotation, annotation2, javaClass);
                            }
                            length = r162;
                            declaredConstructors = constructorArr2;
                            r32 = r182;
                            length3 = r19;
                        }
                        r92 = r12;
                    }
                }
                constructorArr = declaredConstructors;
                r16 = length;
                r18 = r32;
                visitMethod.visitEnd();
            }
            length = r16;
            declaredConstructors = constructorArr;
            r3 = r18;
        }
    }

    private final void loadFieldAnnotations(Class<?> cls, KotlinJvmBinaryClass.MemberVisitor memberVisitor) {
        Field[] declaredFields = cls.getDeclaredFields();
        Intrinsics.checkNotNullExpressionValue(declaredFields, "klass.declaredFields");
        int length = declaredFields.length;
        int r2 = 0;
        while (r2 < length) {
            Field field = declaredFields[r2];
            r2++;
            Name identifier = Name.identifier(field.getName());
            Intrinsics.checkNotNullExpressionValue(identifier, "identifier(field.name)");
            SignatureSerializer signatureSerializer = SignatureSerializer.INSTANCE;
            Intrinsics.checkNotNullExpressionValue(field, "field");
            KotlinJvmBinaryClass.AnnotationVisitor visitField = memberVisitor.visitField(identifier, signatureSerializer.fieldDesc(field), null);
            if (visitField != null) {
                Annotation[] declaredAnnotations = field.getDeclaredAnnotations();
                Intrinsics.checkNotNullExpressionValue(declaredAnnotations, "field.declaredAnnotations");
                int length2 = declaredAnnotations.length;
                int r6 = 0;
                while (r6 < length2) {
                    Annotation annotation = declaredAnnotations[r6];
                    r6++;
                    Intrinsics.checkNotNullExpressionValue(annotation, "annotation");
                    processAnnotation(visitField, annotation);
                }
                visitField.visitEnd();
            }
        }
    }

    private final void processAnnotation(KotlinJvmBinaryClass.AnnotationVisitor annotationVisitor, Annotation annotation) {
        Class<?> javaClass = JvmClassMapping.getJavaClass(JvmClassMapping.getAnnotationClass(annotation));
        KotlinJvmBinaryClass.AnnotationArgumentVisitor visitAnnotation = annotationVisitor.visitAnnotation(reflectClassUtil.getClassId(javaClass), new ReflectAnnotationSource(annotation));
        if (visitAnnotation == null) {
            return;
        }
        INSTANCE.processAnnotationArguments(visitAnnotation, annotation, javaClass);
    }

    private final void processAnnotationArguments(KotlinJvmBinaryClass.AnnotationArgumentVisitor annotationArgumentVisitor, Annotation annotation, Class<?> cls) {
        Method[] declaredMethods = cls.getDeclaredMethods();
        Intrinsics.checkNotNullExpressionValue(declaredMethods, "annotationType.declaredMethods");
        int length = declaredMethods.length;
        int r2 = 0;
        while (r2 < length) {
            Method method = declaredMethods[r2];
            r2++;
            try {
                Object invoke = method.invoke(annotation, new Object[0]);
                Intrinsics.checkNotNull(invoke);
                Name identifier = Name.identifier(method.getName());
                Intrinsics.checkNotNullExpressionValue(identifier, "identifier(method.name)");
                processAnnotationArgumentValue(annotationArgumentVisitor, identifier, invoke);
            } catch (IllegalAccessException unused) {
            }
        }
        annotationArgumentVisitor.visitEnd();
    }

    private final ClassLiteralValue classLiteralValue(Class<?> cls) {
        int r0 = 0;
        while (cls.isArray()) {
            r0++;
            cls = cls.getComponentType();
            Intrinsics.checkNotNullExpressionValue(cls, "currentClass.componentType");
        }
        if (cls.isPrimitive()) {
            if (Intrinsics.areEqual(cls, Void.TYPE)) {
                ClassId classId = ClassId.topLevel(StandardNames.FqNames.unit.toSafe());
                Intrinsics.checkNotNullExpressionValue(classId, "topLevel(StandardNames.FqNames.unit.toSafe())");
                return new ClassLiteralValue(classId, r0);
            }
            PrimitiveType primitiveType = JvmPrimitiveType.get(cls.getName()).getPrimitiveType();
            Intrinsics.checkNotNullExpressionValue(primitiveType, "get(currentClass.name).primitiveType");
            if (r0 > 0) {
                ClassId classId2 = ClassId.topLevel(primitiveType.getArrayTypeFqName());
                Intrinsics.checkNotNullExpressionValue(classId2, "topLevel(primitiveType.arrayTypeFqName)");
                return new ClassLiteralValue(classId2, r0 - 1);
            }
            ClassId classId3 = ClassId.topLevel(primitiveType.getTypeFqName());
            Intrinsics.checkNotNullExpressionValue(classId3, "topLevel(primitiveType.typeFqName)");
            return new ClassLiteralValue(classId3, r0);
        }
        ClassId classId4 = reflectClassUtil.getClassId(cls);
        JavaToKotlinClassMap javaToKotlinClassMap = JavaToKotlinClassMap.INSTANCE;
        FqName asSingleFqName = classId4.asSingleFqName();
        Intrinsics.checkNotNullExpressionValue(asSingleFqName, "javaClassId.asSingleFqName()");
        ClassId mapJavaToKotlin = javaToKotlinClassMap.mapJavaToKotlin(asSingleFqName);
        if (mapJavaToKotlin != null) {
            classId4 = mapJavaToKotlin;
        }
        return new ClassLiteralValue(classId4, r0);
    }

    private final void processAnnotationArgumentValue(KotlinJvmBinaryClass.AnnotationArgumentVisitor annotationArgumentVisitor, Name name, Object obj) {
        Set set;
        Class<?> cls = obj.getClass();
        if (!Intrinsics.areEqual(cls, Class.class)) {
            set = ReflectKotlinClassKt.TYPES_ELIGIBLE_FOR_SIMPLE_VISIT;
            if (set.contains(cls)) {
                annotationArgumentVisitor.visit(name, obj);
                return;
            } else if (reflectClassUtil.isEnumClassOrSpecializedEnumEntryClass(cls)) {
                if (!cls.isEnum()) {
                    cls = cls.getEnclosingClass();
                }
                Intrinsics.checkNotNullExpressionValue(cls, "if (clazz.isEnum) clazz else clazz.enclosingClass");
                ClassId classId = reflectClassUtil.getClassId(cls);
                Name identifier = Name.identifier(((Enum) obj).name());
                Intrinsics.checkNotNullExpressionValue(identifier, "identifier((value as Enum<*>).name)");
                annotationArgumentVisitor.visitEnum(name, classId, identifier);
                return;
            } else if (Annotation.class.isAssignableFrom(cls)) {
                Class<?>[] interfaces = cls.getInterfaces();
                Intrinsics.checkNotNullExpressionValue(interfaces, "clazz.interfaces");
                Class<?> annotationClass = (Class) ArraysKt.single(interfaces);
                Intrinsics.checkNotNullExpressionValue(annotationClass, "annotationClass");
                KotlinJvmBinaryClass.AnnotationArgumentVisitor visitAnnotation = annotationArgumentVisitor.visitAnnotation(name, reflectClassUtil.getClassId(annotationClass));
                if (visitAnnotation == null) {
                    return;
                }
                processAnnotationArguments(visitAnnotation, (Annotation) obj, annotationClass);
                return;
            } else if (cls.isArray()) {
                KotlinJvmBinaryClass.AnnotationArrayArgumentVisitor visitArray = annotationArgumentVisitor.visitArray(name);
                if (visitArray == null) {
                    return;
                }
                Class<?> componentType = cls.getComponentType();
                int r2 = 0;
                if (componentType.isEnum()) {
                    Intrinsics.checkNotNullExpressionValue(componentType, "componentType");
                    ClassId classId2 = reflectClassUtil.getClassId(componentType);
                    Object[] objArr = (Object[]) obj;
                    int length = objArr.length;
                    while (r2 < length) {
                        Object obj2 = objArr[r2];
                        r2++;
                        Objects.requireNonNull(obj2, "null cannot be cast to non-null type kotlin.Enum<*>");
                        Name identifier2 = Name.identifier(((Enum) obj2).name());
                        Intrinsics.checkNotNullExpressionValue(identifier2, "identifier((element as Enum<*>).name)");
                        visitArray.visitEnum(classId2, identifier2);
                    }
                } else if (Intrinsics.areEqual(componentType, Class.class)) {
                    Object[] objArr2 = (Object[]) obj;
                    int length2 = objArr2.length;
                    while (r2 < length2) {
                        Object obj3 = objArr2[r2];
                        r2++;
                        Objects.requireNonNull(obj3, "null cannot be cast to non-null type java.lang.Class<*>");
                        visitArray.visitClassLiteral(classLiteralValue((Class) obj3));
                    }
                } else if (Annotation.class.isAssignableFrom(componentType)) {
                    Object[] objArr3 = (Object[]) obj;
                    int length3 = objArr3.length;
                    while (r2 < length3) {
                        Object obj4 = objArr3[r2];
                        r2++;
                        Intrinsics.checkNotNullExpressionValue(componentType, "componentType");
                        KotlinJvmBinaryClass.AnnotationArgumentVisitor visitAnnotation2 = visitArray.visitAnnotation(reflectClassUtil.getClassId(componentType));
                        if (visitAnnotation2 != null) {
                            Objects.requireNonNull(obj4, "null cannot be cast to non-null type kotlin.Annotation");
                            processAnnotationArguments(visitAnnotation2, (Annotation) obj4, componentType);
                        }
                    }
                } else {
                    Object[] objArr4 = (Object[]) obj;
                    int length4 = objArr4.length;
                    while (r2 < length4) {
                        Object obj5 = objArr4[r2];
                        r2++;
                        visitArray.visit(obj5);
                    }
                }
                visitArray.visitEnd();
                return;
            } else {
                throw new UnsupportedOperationException("Unsupported annotation argument value (" + cls + "): " + obj);
            }
        }
        annotationArgumentVisitor.visitClassLiteral(name, classLiteralValue((Class) obj));
    }
}
