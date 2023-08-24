package kotlin.reflect.jvm.internal.impl.load.java.typeEnhancement;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import kotlin.NoWhenBranchMatchedException;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.jvm.internal.impl.descriptors.ClassifierDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.TypeParameterDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.annotations.Annotations;
import kotlin.reflect.jvm.internal.impl.load.java.lazy.JavaResolverSettings;
import kotlin.reflect.jvm.internal.impl.load.java.lazy.types.RawTypeImpl;
import kotlin.reflect.jvm.internal.impl.types.FlexibleType;
import kotlin.reflect.jvm.internal.impl.types.FlexibleTypesKt;
import kotlin.reflect.jvm.internal.impl.types.KotlinType;
import kotlin.reflect.jvm.internal.impl.types.KotlinTypeFactory;
import kotlin.reflect.jvm.internal.impl.types.KotlinTypeKt;
import kotlin.reflect.jvm.internal.impl.types.RawType;
import kotlin.reflect.jvm.internal.impl.types.SimpleType;
import kotlin.reflect.jvm.internal.impl.types.SpecialTypesKt;
import kotlin.reflect.jvm.internal.impl.types.TypeConstructor;
import kotlin.reflect.jvm.internal.impl.types.TypeProjection;
import kotlin.reflect.jvm.internal.impl.types.TypeWithEnhancementKt;
import kotlin.reflect.jvm.internal.impl.types.UnwrappedType;
import kotlin.reflect.jvm.internal.impl.types.Variance;
import kotlin.reflect.jvm.internal.impl.types.checker.KotlinTypeRefiner;
import kotlin.reflect.jvm.internal.impl.types.typeUtil.TypeUtils;

/* compiled from: typeEnhancement.kt */
/* loaded from: classes5.dex */
public final class JavaTypeEnhancement {
    private final JavaResolverSettings javaResolverSettings;

    /* JADX INFO: Access modifiers changed from: private */
    /* compiled from: typeEnhancement.kt */
    /* loaded from: classes5.dex */
    public static final class Result {
        private final int subtreeSize;
        private final KotlinType type;

        public Result(KotlinType kotlinType, int r2) {
            this.type = kotlinType;
            this.subtreeSize = r2;
        }

        public final int getSubtreeSize() {
            return this.subtreeSize;
        }

        public final KotlinType getType() {
            return this.type;
        }
    }

    public JavaTypeEnhancement(JavaResolverSettings javaResolverSettings) {
        Intrinsics.checkNotNullParameter(javaResolverSettings, "javaResolverSettings");
        this.javaResolverSettings = javaResolverSettings;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* compiled from: typeEnhancement.kt */
    /* loaded from: classes5.dex */
    public static final class SimpleResult {
        private final boolean forWarnings;
        private final int subtreeSize;
        private final SimpleType type;

        public SimpleResult(SimpleType simpleType, int r2, boolean z) {
            this.type = simpleType;
            this.subtreeSize = r2;
            this.forWarnings = z;
        }

        public final boolean getForWarnings() {
            return this.forWarnings;
        }

        public final int getSubtreeSize() {
            return this.subtreeSize;
        }

        public final SimpleType getType() {
            return this.type;
        }
    }

    public final KotlinType enhance(KotlinType kotlinType, Function1<? super Integer, typeQualifiers> qualifiers, boolean z) {
        Intrinsics.checkNotNullParameter(kotlinType, "<this>");
        Intrinsics.checkNotNullParameter(qualifiers, "qualifiers");
        return enhancePossiblyFlexible(kotlinType.unwrap(), qualifiers, 0, z).getType();
    }

    private final Result enhancePossiblyFlexible(UnwrappedType unwrappedType, Function1<? super Integer, typeQualifiers> function1, int r14, boolean z) {
        SimpleType flexibleType;
        RawTypeImpl rawTypeImpl = null;
        if (KotlinTypeKt.isError(unwrappedType)) {
            return new Result(null, 1);
        }
        if (unwrappedType instanceof FlexibleType) {
            boolean z2 = unwrappedType instanceof RawType;
            FlexibleType flexibleType2 = (FlexibleType) unwrappedType;
            SimpleResult enhanceInflexible = enhanceInflexible(flexibleType2.getLowerBound(), function1, r14, TypeComponentPosition.FLEXIBLE_LOWER, z2, z);
            SimpleResult enhanceInflexible2 = enhanceInflexible(flexibleType2.getUpperBound(), function1, r14, TypeComponentPosition.FLEXIBLE_UPPER, z2, z);
            enhanceInflexible.getSubtreeSize();
            enhanceInflexible2.getSubtreeSize();
            if (enhanceInflexible.getType() != null || enhanceInflexible2.getType() != null) {
                if (enhanceInflexible.getForWarnings() || enhanceInflexible2.getForWarnings()) {
                    SimpleType type = enhanceInflexible2.getType();
                    if (type != null) {
                        SimpleType type2 = enhanceInflexible.getType();
                        if (type2 == null) {
                            type2 = type;
                        }
                        flexibleType = KotlinTypeFactory.flexibleType(type2, type);
                    } else {
                        SimpleType type3 = enhanceInflexible.getType();
                        Intrinsics.checkNotNull(type3);
                        flexibleType = type3;
                    }
                    rawTypeImpl = TypeWithEnhancementKt.wrapEnhancement(unwrappedType, flexibleType);
                } else if (z2) {
                    SimpleType type4 = enhanceInflexible.getType();
                    if (type4 == null) {
                        type4 = flexibleType2.getLowerBound();
                    }
                    SimpleType type5 = enhanceInflexible2.getType();
                    if (type5 == null) {
                        type5 = flexibleType2.getUpperBound();
                    }
                    rawTypeImpl = new RawTypeImpl(type4, type5);
                } else {
                    SimpleType type6 = enhanceInflexible.getType();
                    if (type6 == null) {
                        type6 = flexibleType2.getLowerBound();
                    }
                    SimpleType type7 = enhanceInflexible2.getType();
                    if (type7 == null) {
                        type7 = flexibleType2.getUpperBound();
                    }
                    rawTypeImpl = KotlinTypeFactory.flexibleType(type6, type7);
                }
            }
            return new Result(rawTypeImpl, enhanceInflexible.getSubtreeSize());
        } else if (unwrappedType instanceof SimpleType) {
            SimpleResult enhanceInflexible$default = enhanceInflexible$default(this, (SimpleType) unwrappedType, function1, r14, TypeComponentPosition.INFLEXIBLE, false, z, 8, null);
            return new Result(enhanceInflexible$default.getForWarnings() ? TypeWithEnhancementKt.wrapEnhancement(unwrappedType, enhanceInflexible$default.getType()) : enhanceInflexible$default.getType(), enhanceInflexible$default.getSubtreeSize());
        } else {
            throw new NoWhenBranchMatchedException();
        }
    }

    static /* synthetic */ SimpleResult enhanceInflexible$default(JavaTypeEnhancement javaTypeEnhancement, SimpleType simpleType, Function1 function1, int r12, TypeComponentPosition typeComponentPosition, boolean z, boolean z2, int r16, Object obj) {
        return javaTypeEnhancement.enhanceInflexible(simpleType, function1, r12, typeComponentPosition, (r16 & 8) != 0 ? false : z, (r16 & 16) != 0 ? false : z2);
    }

    private final SimpleResult enhanceInflexible(SimpleType simpleType, Function1<? super Integer, typeQualifiers> function1, int r21, TypeComponentPosition typeComponentPosition, boolean z, boolean z2) {
        ClassifierDescriptor enhanceMutability;
        Boolean enhancedNullability;
        EnhancedTypeAnnotations enhancedTypeAnnotations;
        EnhancedTypeAnnotations enhancedTypeAnnotations2;
        Annotations compositeAnnotationsOrSingle;
        boolean z3;
        boolean z4;
        boolean z5;
        Result result;
        TypeProjection makeStarProjection;
        Function1<? super Integer, typeQualifiers> function12 = function1;
        boolean shouldEnhance = TypeComponentPositionKt.shouldEnhance(typeComponentPosition);
        boolean z6 = (z2 && z) ? false : true;
        KotlinType kotlinType = null;
        if (shouldEnhance || !simpleType.getArguments().isEmpty()) {
            ClassifierDescriptor mo3011getDeclarationDescriptor = simpleType.getConstructor().mo3011getDeclarationDescriptor();
            if (mo3011getDeclarationDescriptor == null) {
                return new SimpleResult(null, 1, false);
            }
            typeQualifiers invoke = function12.invoke(Integer.valueOf(r21));
            enhanceMutability = TypeEnhancementKt.enhanceMutability(mo3011getDeclarationDescriptor, invoke, typeComponentPosition);
            enhancedNullability = TypeEnhancementKt.getEnhancedNullability(invoke, typeComponentPosition);
            TypeConstructor constructor = enhanceMutability == null ? simpleType.getConstructor() : enhanceMutability.getTypeConstructor();
            Intrinsics.checkNotNullExpressionValue(constructor, "enhancedClassifier?.typeConstructor ?: constructor");
            int r10 = r21 + 1;
            List<TypeProjection> arguments = simpleType.getArguments();
            List<TypeParameterDescriptor> parameters = constructor.getParameters();
            Intrinsics.checkNotNullExpressionValue(parameters, "typeConstructor.parameters");
            List<TypeParameterDescriptor> list = parameters;
            Iterator<T> it = arguments.iterator();
            Iterator<T> it2 = list.iterator();
            ArrayList arrayList = new ArrayList(Math.min(CollectionsKt.collectionSizeOrDefault(arguments, 10), CollectionsKt.collectionSizeOrDefault(list, 10)));
            while (it.hasNext() && it2.hasNext()) {
                Object next = it.next();
                TypeParameterDescriptor typeParameterDescriptor = (TypeParameterDescriptor) it2.next();
                TypeProjection typeProjection = (TypeProjection) next;
                if (!z6) {
                    z5 = z6;
                    result = new Result(kotlinType, 0);
                } else {
                    z5 = z6;
                    if (!typeProjection.isStarProjection()) {
                        result = enhancePossiblyFlexible(typeProjection.getType().unwrap(), function12, r10, z2);
                    } else if (function12.invoke(Integer.valueOf(r10)).getNullability() == NullabilityQualifier.FORCE_FLEXIBILITY) {
                        UnwrappedType unwrap = typeProjection.getType().unwrap();
                        result = new Result(KotlinTypeFactory.flexibleType(FlexibleTypesKt.lowerIfFlexible(unwrap).makeNullableAsSpecified(false), FlexibleTypesKt.upperIfFlexible(unwrap).makeNullableAsSpecified(true)), 1);
                    } else {
                        result = new Result(null, 1);
                    }
                }
                r10 += result.getSubtreeSize();
                if (result.getType() != null) {
                    KotlinType type = result.getType();
                    Variance projectionKind = typeProjection.getProjectionKind();
                    Intrinsics.checkNotNullExpressionValue(projectionKind, "arg.projectionKind");
                    makeStarProjection = TypeUtils.createProjection(type, projectionKind, typeParameterDescriptor);
                } else if (enhanceMutability == null || typeProjection.isStarProjection()) {
                    makeStarProjection = enhanceMutability != null ? kotlin.reflect.jvm.internal.impl.types.TypeUtils.makeStarProjection(typeParameterDescriptor) : null;
                } else {
                    KotlinType type2 = typeProjection.getType();
                    Intrinsics.checkNotNullExpressionValue(type2, "arg.type");
                    Variance projectionKind2 = typeProjection.getProjectionKind();
                    Intrinsics.checkNotNullExpressionValue(projectionKind2, "arg.projectionKind");
                    makeStarProjection = TypeUtils.createProjection(type2, projectionKind2, typeParameterDescriptor);
                }
                arrayList.add(makeStarProjection);
                function12 = function1;
                z6 = z5;
                kotlinType = null;
            }
            ArrayList arrayList2 = arrayList;
            int r102 = r10 - r21;
            if (enhanceMutability == null && enhancedNullability == null) {
                ArrayList<TypeProjection> arrayList3 = arrayList2;
                if (!(arrayList3 instanceof Collection) || !arrayList3.isEmpty()) {
                    for (TypeProjection typeProjection2 : arrayList3) {
                        if (typeProjection2 == null) {
                            z3 = true;
                            continue;
                        } else {
                            z3 = false;
                            continue;
                        }
                        if (!z3) {
                            z4 = false;
                            break;
                        }
                    }
                }
                z4 = true;
                if (z4) {
                    return new SimpleResult(null, r102, false);
                }
            }
            Annotations[] annotationsArr = new Annotations[3];
            annotationsArr[0] = simpleType.getAnnotations();
            enhancedTypeAnnotations = TypeEnhancementKt.ENHANCED_MUTABILITY_ANNOTATIONS;
            if (!(enhanceMutability != null)) {
                enhancedTypeAnnotations = null;
            }
            annotationsArr[1] = enhancedTypeAnnotations;
            enhancedTypeAnnotations2 = TypeEnhancementKt.ENHANCED_NULLABILITY_ANNOTATIONS;
            if (!(enhancedNullability != null)) {
                enhancedTypeAnnotations2 = null;
            }
            annotationsArr[2] = enhancedTypeAnnotations2;
            compositeAnnotationsOrSingle = TypeEnhancementKt.compositeAnnotationsOrSingle(CollectionsKt.listOfNotNull((Object[]) annotationsArr));
            ArrayList arrayList4 = arrayList2;
            List<TypeProjection> arguments2 = simpleType.getArguments();
            Iterator it3 = arrayList4.iterator();
            Iterator<T> it4 = arguments2.iterator();
            ArrayList arrayList5 = new ArrayList(Math.min(CollectionsKt.collectionSizeOrDefault(arrayList4, 10), CollectionsKt.collectionSizeOrDefault(arguments2, 10)));
            while (it3.hasNext() && it4.hasNext()) {
                Object next2 = it3.next();
                TypeProjection typeProjection3 = (TypeProjection) it4.next();
                TypeProjection typeProjection4 = (TypeProjection) next2;
                if (typeProjection4 != null) {
                    typeProjection3 = typeProjection4;
                }
                arrayList5.add(typeProjection3);
            }
            SimpleType simpleType$default = KotlinTypeFactory.simpleType$default(compositeAnnotationsOrSingle, constructor, arrayList5, enhancedNullability == null ? simpleType.isMarkedNullable() : enhancedNullability.booleanValue(), (KotlinTypeRefiner) null, 16, (Object) null);
            if (invoke.getDefinitelyNotNull()) {
                simpleType$default = notNullTypeParameter(simpleType$default);
            }
            return new SimpleResult(simpleType$default, r102, enhancedNullability != null && invoke.isNullabilityQualifierForWarning());
        }
        return new SimpleResult(null, 1, false);
    }

    private final SimpleType notNullTypeParameter(SimpleType simpleType) {
        if (this.javaResolverSettings.getCorrectNullabilityForNotNullTypeParameter()) {
            return SpecialTypesKt.makeSimpleTypeDefinitelyNotNullOrNotNull(simpleType, true);
        }
        return new NotNullTypeParameter(simpleType);
    }
}
