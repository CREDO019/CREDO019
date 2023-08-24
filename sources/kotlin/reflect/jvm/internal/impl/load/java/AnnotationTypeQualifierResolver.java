package kotlin.reflect.jvm.internal.impl.load.java;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.jvm.internal.impl.descriptors.ClassDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.ClassKind;
import kotlin.reflect.jvm.internal.impl.descriptors.annotations.AnnotationDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.annotations.Annotations;
import kotlin.reflect.jvm.internal.impl.descriptors.annotations.KotlinTarget;
import kotlin.reflect.jvm.internal.impl.load.java.components.JavaAnnotationTargetMapper;
import kotlin.reflect.jvm.internal.impl.load.java.typeEnhancement.NullabilityQualifierWithMigrationStatus;
import kotlin.reflect.jvm.internal.impl.name.FqName;
import kotlin.reflect.jvm.internal.impl.name.Name;
import kotlin.reflect.jvm.internal.impl.resolve.constants.ArrayValue;
import kotlin.reflect.jvm.internal.impl.resolve.constants.ConstantValue;
import kotlin.reflect.jvm.internal.impl.resolve.constants.EnumValue;
import kotlin.reflect.jvm.internal.impl.resolve.descriptorUtil.DescriptorUtils;
import kotlin.reflect.jvm.internal.impl.storage.MemoizedFunctionToNullable;
import kotlin.reflect.jvm.internal.impl.storage.StorageManager;

/* compiled from: AnnotationTypeQualifierResolver.kt */
/* loaded from: classes5.dex */
public final class AnnotationTypeQualifierResolver {
    private final JavaTypeEnhancementState javaTypeEnhancementState;
    private final MemoizedFunctionToNullable<ClassDescriptor, AnnotationDescriptor> resolvedNicknames;

    /* compiled from: AnnotationTypeQualifierResolver.kt */
    /* loaded from: classes5.dex */
    public static final class TypeQualifierWithApplicability {
        private final int applicability;
        private final AnnotationDescriptor typeQualifier;

        public TypeQualifierWithApplicability(AnnotationDescriptor typeQualifier, int r3) {
            Intrinsics.checkNotNullParameter(typeQualifier, "typeQualifier");
            this.typeQualifier = typeQualifier;
            this.applicability = r3;
        }

        public final AnnotationDescriptor component1() {
            return this.typeQualifier;
        }

        public final List<AnnotationQualifierApplicabilityType> component2() {
            AnnotationQualifierApplicabilityType[] values = AnnotationQualifierApplicabilityType.values();
            ArrayList arrayList = new ArrayList();
            int length = values.length;
            int r3 = 0;
            while (r3 < length) {
                AnnotationQualifierApplicabilityType annotationQualifierApplicabilityType = values[r3];
                r3++;
                if (isApplicableTo(annotationQualifierApplicabilityType)) {
                    arrayList.add(annotationQualifierApplicabilityType);
                }
            }
            return arrayList;
        }

        private final boolean isApplicableTo(AnnotationQualifierApplicabilityType annotationQualifierApplicabilityType) {
            if (isApplicableConsideringMask(annotationQualifierApplicabilityType)) {
                return true;
            }
            return isApplicableConsideringMask(AnnotationQualifierApplicabilityType.TYPE_USE) && annotationQualifierApplicabilityType != AnnotationQualifierApplicabilityType.TYPE_PARAMETER_BOUNDS;
        }

        private final boolean isApplicableConsideringMask(AnnotationQualifierApplicabilityType annotationQualifierApplicabilityType) {
            return ((1 << annotationQualifierApplicabilityType.ordinal()) & this.applicability) != 0;
        }
    }

    public AnnotationTypeQualifierResolver(StorageManager storageManager, JavaTypeEnhancementState javaTypeEnhancementState) {
        Intrinsics.checkNotNullParameter(storageManager, "storageManager");
        Intrinsics.checkNotNullParameter(javaTypeEnhancementState, "javaTypeEnhancementState");
        this.javaTypeEnhancementState = javaTypeEnhancementState;
        this.resolvedNicknames = storageManager.createMemoizedFunctionWithNullableValues(new AnnotationTypeQualifierResolver$resolvedNicknames$1(this));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final AnnotationDescriptor computeTypeQualifierNickname(ClassDescriptor classDescriptor) {
        if (classDescriptor.getAnnotations().hasAnnotation(AnnotationQualifiersFqNames.getTYPE_QUALIFIER_NICKNAME_FQNAME())) {
            for (AnnotationDescriptor annotationDescriptor : classDescriptor.getAnnotations()) {
                AnnotationDescriptor resolveTypeQualifierAnnotation = resolveTypeQualifierAnnotation(annotationDescriptor);
                if (resolveTypeQualifierAnnotation != null) {
                    return resolveTypeQualifierAnnotation;
                }
            }
            return null;
        }
        return null;
    }

    private final AnnotationDescriptor resolveTypeQualifierNickname(ClassDescriptor classDescriptor) {
        if (classDescriptor.getKind() != ClassKind.ANNOTATION_CLASS) {
            return null;
        }
        return this.resolvedNicknames.invoke(classDescriptor);
    }

    public final AnnotationDescriptor resolveTypeQualifierAnnotation(AnnotationDescriptor annotationDescriptor) {
        ClassDescriptor annotationClass;
        boolean isAnnotatedWithTypeQualifier;
        Intrinsics.checkNotNullParameter(annotationDescriptor, "annotationDescriptor");
        if (this.javaTypeEnhancementState.getJsr305().isDisabled() || (annotationClass = DescriptorUtils.getAnnotationClass(annotationDescriptor)) == null) {
            return null;
        }
        isAnnotatedWithTypeQualifier = AnnotationTypeQualifierResolverKt.isAnnotatedWithTypeQualifier(annotationClass);
        return isAnnotatedWithTypeQualifier ? annotationDescriptor : resolveTypeQualifierNickname(annotationClass);
    }

    public final JavaDefaultQualifiers resolveQualifierBuiltInDefaultAnnotation(AnnotationDescriptor annotationDescriptor) {
        JavaDefaultQualifiers javaDefaultQualifiers;
        Intrinsics.checkNotNullParameter(annotationDescriptor, "annotationDescriptor");
        if (this.javaTypeEnhancementState.getDisabledDefaultAnnotations() || (javaDefaultQualifiers = AnnotationQualifiersFqNames.getBUILT_IN_TYPE_QUALIFIER_DEFAULT_ANNOTATIONS().get(annotationDescriptor.getFqName())) == null) {
            return null;
        }
        ReportLevel resolveDefaultAnnotationState = resolveDefaultAnnotationState(annotationDescriptor);
        if (!(resolveDefaultAnnotationState != ReportLevel.IGNORE)) {
            resolveDefaultAnnotationState = null;
        }
        if (resolveDefaultAnnotationState == null) {
            return null;
        }
        return JavaDefaultQualifiers.copy$default(javaDefaultQualifiers, NullabilityQualifierWithMigrationStatus.copy$default(javaDefaultQualifiers.getNullabilityQualifier(), null, resolveDefaultAnnotationState.isWarning(), 1, null), null, false, 6, null);
    }

    private final ReportLevel resolveDefaultAnnotationState(AnnotationDescriptor annotationDescriptor) {
        FqName fqName = annotationDescriptor.getFqName();
        if (fqName != null && AnnotationQualifiersFqNames.getJSPECIFY_DEFAULT_ANNOTATIONS().containsKey(fqName)) {
            return this.javaTypeEnhancementState.getGetReportLevelForAnnotation().invoke(fqName);
        }
        return resolveJsr305AnnotationState(annotationDescriptor);
    }

    public final TypeQualifierWithApplicability resolveTypeQualifierDefaultAnnotation(AnnotationDescriptor annotationDescriptor) {
        AnnotationDescriptor annotationDescriptor2;
        boolean z;
        List<AnnotationQualifierApplicabilityType> emptyList;
        Intrinsics.checkNotNullParameter(annotationDescriptor, "annotationDescriptor");
        if (this.javaTypeEnhancementState.getJsr305().isDisabled()) {
            return null;
        }
        ClassDescriptor annotationClass = DescriptorUtils.getAnnotationClass(annotationDescriptor);
        if (annotationClass == null || !annotationClass.getAnnotations().hasAnnotation(AnnotationQualifiersFqNames.getTYPE_QUALIFIER_DEFAULT_FQNAME())) {
            annotationClass = null;
        }
        if (annotationClass == null) {
            return null;
        }
        ClassDescriptor annotationClass2 = DescriptorUtils.getAnnotationClass(annotationDescriptor);
        Intrinsics.checkNotNull(annotationClass2);
        AnnotationDescriptor mo3005findAnnotation = annotationClass2.getAnnotations().mo3005findAnnotation(AnnotationQualifiersFqNames.getTYPE_QUALIFIER_DEFAULT_FQNAME());
        Intrinsics.checkNotNull(mo3005findAnnotation);
        Map<Name, ConstantValue<?>> allValueArguments = mo3005findAnnotation.getAllValueArguments();
        ArrayList<AnnotationQualifierApplicabilityType> arrayList = new ArrayList();
        for (Map.Entry<Name, ConstantValue<?>> entry : allValueArguments.entrySet()) {
            ConstantValue<?> value = entry.getValue();
            if (Intrinsics.areEqual(entry.getKey(), JvmAnnotationNames.DEFAULT_ANNOTATION_MEMBER_NAME)) {
                emptyList = mapJavaConstantToQualifierApplicabilityTypes(value);
            } else {
                emptyList = CollectionsKt.emptyList();
            }
            CollectionsKt.addAll(arrayList, emptyList);
        }
        int r3 = 0;
        for (AnnotationQualifierApplicabilityType annotationQualifierApplicabilityType : arrayList) {
            r3 |= 1 << annotationQualifierApplicabilityType.ordinal();
        }
        Iterator<AnnotationDescriptor> it = annotationClass.getAnnotations().iterator();
        while (true) {
            if (!it.hasNext()) {
                annotationDescriptor2 = null;
                break;
            }
            annotationDescriptor2 = it.next();
            if (resolveTypeQualifierAnnotation(annotationDescriptor2) != null) {
                z = true;
                continue;
            } else {
                z = false;
                continue;
            }
            if (z) {
                break;
            }
        }
        AnnotationDescriptor annotationDescriptor3 = annotationDescriptor2;
        if (annotationDescriptor3 == null) {
            return null;
        }
        return new TypeQualifierWithApplicability(annotationDescriptor3, r3);
    }

    public final TypeQualifierWithApplicability resolveAnnotation(AnnotationDescriptor annotationDescriptor) {
        Intrinsics.checkNotNullParameter(annotationDescriptor, "annotationDescriptor");
        ClassDescriptor annotationClass = DescriptorUtils.getAnnotationClass(annotationDescriptor);
        if (annotationClass == null) {
            return null;
        }
        Annotations annotations = annotationClass.getAnnotations();
        FqName TARGET_ANNOTATION = JvmAnnotationNames.TARGET_ANNOTATION;
        Intrinsics.checkNotNullExpressionValue(TARGET_ANNOTATION, "TARGET_ANNOTATION");
        AnnotationDescriptor mo3005findAnnotation = annotations.mo3005findAnnotation(TARGET_ANNOTATION);
        if (mo3005findAnnotation == null) {
            return null;
        }
        Map<Name, ConstantValue<?>> allValueArguments = mo3005findAnnotation.getAllValueArguments();
        ArrayList<AnnotationQualifierApplicabilityType> arrayList = new ArrayList();
        for (Map.Entry<Name, ConstantValue<?>> entry : allValueArguments.entrySet()) {
            CollectionsKt.addAll(arrayList, mapKotlinConstantToQualifierApplicabilityTypes(entry.getValue()));
        }
        int r0 = 0;
        for (AnnotationQualifierApplicabilityType annotationQualifierApplicabilityType : arrayList) {
            r0 |= 1 << annotationQualifierApplicabilityType.ordinal();
        }
        return new TypeQualifierWithApplicability(annotationDescriptor, r0);
    }

    public final ReportLevel resolveJsr305AnnotationState(AnnotationDescriptor annotationDescriptor) {
        Intrinsics.checkNotNullParameter(annotationDescriptor, "annotationDescriptor");
        ReportLevel resolveJsr305CustomState = resolveJsr305CustomState(annotationDescriptor);
        return resolveJsr305CustomState == null ? this.javaTypeEnhancementState.getJsr305().getGlobalLevel() : resolveJsr305CustomState;
    }

    public final ReportLevel resolveJsr305CustomState(AnnotationDescriptor annotationDescriptor) {
        Intrinsics.checkNotNullParameter(annotationDescriptor, "annotationDescriptor");
        ReportLevel reportLevel = this.javaTypeEnhancementState.getJsr305().getUserDefinedLevelForSpecificAnnotation().get(annotationDescriptor.getFqName());
        if (reportLevel == null) {
            ClassDescriptor annotationClass = DescriptorUtils.getAnnotationClass(annotationDescriptor);
            if (annotationClass == null) {
                return null;
            }
            return migrationAnnotationStatus(annotationClass);
        }
        return reportLevel;
    }

    private final ReportLevel migrationAnnotationStatus(ClassDescriptor classDescriptor) {
        AnnotationDescriptor mo3005findAnnotation = classDescriptor.getAnnotations().mo3005findAnnotation(AnnotationQualifiersFqNames.getMIGRATION_ANNOTATION_FQNAME());
        ConstantValue<?> firstArgument = mo3005findAnnotation == null ? null : DescriptorUtils.firstArgument(mo3005findAnnotation);
        EnumValue enumValue = firstArgument instanceof EnumValue ? (EnumValue) firstArgument : null;
        if (enumValue == null) {
            return null;
        }
        ReportLevel migrationLevel = this.javaTypeEnhancementState.getJsr305().getMigrationLevel();
        if (migrationLevel == null) {
            String asString = enumValue.getEnumEntryName().asString();
            int hashCode = asString.hashCode();
            if (hashCode == -2137067054) {
                if (asString.equals("IGNORE")) {
                    return ReportLevel.IGNORE;
                }
                return null;
            } else if (hashCode == -1838656823) {
                if (asString.equals("STRICT")) {
                    return ReportLevel.STRICT;
                }
                return null;
            } else if (hashCode == 2656902 && asString.equals("WARN")) {
                return ReportLevel.WARN;
            } else {
                return null;
            }
        }
        return migrationLevel;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final List<String> toKotlinTargetNames(String str) {
        Set<KotlinTarget> mapJavaTargetArgumentByName = JavaAnnotationTargetMapper.INSTANCE.mapJavaTargetArgumentByName(str);
        ArrayList arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(mapJavaTargetArgumentByName, 10));
        for (KotlinTarget kotlinTarget : mapJavaTargetArgumentByName) {
            arrayList.add(kotlinTarget.name());
        }
        return arrayList;
    }

    private final List<AnnotationQualifierApplicabilityType> mapConstantToQualifierApplicabilityTypes(ConstantValue<?> constantValue, Function2<? super EnumValue, ? super AnnotationQualifierApplicabilityType, Boolean> function2) {
        AnnotationQualifierApplicabilityType annotationQualifierApplicabilityType;
        if (!(constantValue instanceof ArrayValue)) {
            if (constantValue instanceof EnumValue) {
                AnnotationQualifierApplicabilityType[] values = AnnotationQualifierApplicabilityType.values();
                int r1 = 0;
                int length = values.length;
                while (true) {
                    if (r1 >= length) {
                        annotationQualifierApplicabilityType = null;
                        break;
                    }
                    annotationQualifierApplicabilityType = values[r1];
                    r1++;
                    if (function2.invoke(constantValue, annotationQualifierApplicabilityType).booleanValue()) {
                        break;
                    }
                }
                return CollectionsKt.listOfNotNull(annotationQualifierApplicabilityType);
            }
            return CollectionsKt.emptyList();
        }
        ArrayList arrayList = new ArrayList();
        for (ConstantValue<?> constantValue2 : ((ArrayValue) constantValue).getValue()) {
            CollectionsKt.addAll(arrayList, mapConstantToQualifierApplicabilityTypes(constantValue2, function2));
        }
        return arrayList;
    }

    private final List<AnnotationQualifierApplicabilityType> mapJavaConstantToQualifierApplicabilityTypes(ConstantValue<?> constantValue) {
        return mapConstantToQualifierApplicabilityTypes(constantValue, new Function2<EnumValue, AnnotationQualifierApplicabilityType, Boolean>() { // from class: kotlin.reflect.jvm.internal.impl.load.java.AnnotationTypeQualifierResolver$mapJavaConstantToQualifierApplicabilityTypes$1
            @Override // kotlin.jvm.functions.Function2
            public final Boolean invoke(EnumValue mapConstantToQualifierApplicabilityTypes, AnnotationQualifierApplicabilityType it) {
                Intrinsics.checkNotNullParameter(mapConstantToQualifierApplicabilityTypes, "$this$mapConstantToQualifierApplicabilityTypes");
                Intrinsics.checkNotNullParameter(it, "it");
                return Boolean.valueOf(Intrinsics.areEqual(mapConstantToQualifierApplicabilityTypes.getEnumEntryName().getIdentifier(), it.getJavaTarget()));
            }
        });
    }

    private final List<AnnotationQualifierApplicabilityType> mapKotlinConstantToQualifierApplicabilityTypes(ConstantValue<?> constantValue) {
        return mapConstantToQualifierApplicabilityTypes(constantValue, new Function2<EnumValue, AnnotationQualifierApplicabilityType, Boolean>() { // from class: kotlin.reflect.jvm.internal.impl.load.java.AnnotationTypeQualifierResolver$mapKotlinConstantToQualifierApplicabilityTypes$1
            /* JADX INFO: Access modifiers changed from: package-private */
            {
                super(2);
            }

            @Override // kotlin.jvm.functions.Function2
            public final Boolean invoke(EnumValue mapConstantToQualifierApplicabilityTypes, AnnotationQualifierApplicabilityType it) {
                List kotlinTargetNames;
                Intrinsics.checkNotNullParameter(mapConstantToQualifierApplicabilityTypes, "$this$mapConstantToQualifierApplicabilityTypes");
                Intrinsics.checkNotNullParameter(it, "it");
                kotlinTargetNames = AnnotationTypeQualifierResolver.this.toKotlinTargetNames(it.getJavaTarget());
                return Boolean.valueOf(kotlinTargetNames.contains(mapConstantToQualifierApplicabilityTypes.getEnumEntryName().getIdentifier()));
            }
        });
    }
}
