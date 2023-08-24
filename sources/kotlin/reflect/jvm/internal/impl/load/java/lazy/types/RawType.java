package kotlin.reflect.jvm.internal.impl.load.java.lazy.types;

import java.util.ArrayList;
import java.util.List;
import kotlin.NoWhenBranchMatchedException;
import kotlin.Tuples;
import kotlin.TuplesKt;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.jvm.internal.impl.builtins.KotlinBuiltIns;
import kotlin.reflect.jvm.internal.impl.descriptors.ClassDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.ClassifierDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.TypeParameterDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.annotations.Annotations;
import kotlin.reflect.jvm.internal.impl.load.java.components.TypeUsage;
import kotlin.reflect.jvm.internal.impl.name.ClassId;
import kotlin.reflect.jvm.internal.impl.resolve.descriptorUtil.DescriptorUtils;
import kotlin.reflect.jvm.internal.impl.resolve.scopes.MemberScope;
import kotlin.reflect.jvm.internal.impl.types.ErrorUtils;
import kotlin.reflect.jvm.internal.impl.types.FlexibleTypesKt;
import kotlin.reflect.jvm.internal.impl.types.KotlinType;
import kotlin.reflect.jvm.internal.impl.types.KotlinTypeFactory;
import kotlin.reflect.jvm.internal.impl.types.KotlinTypeKt;
import kotlin.reflect.jvm.internal.impl.types.SimpleType;
import kotlin.reflect.jvm.internal.impl.types.TypeConstructor;
import kotlin.reflect.jvm.internal.impl.types.TypeProjection;
import kotlin.reflect.jvm.internal.impl.types.TypeProjectionImpl;
import kotlin.reflect.jvm.internal.impl.types.TypeSubstitution;
import kotlin.reflect.jvm.internal.impl.types.Variance;
import kotlin.reflect.jvm.internal.impl.types.checker.KotlinTypeRefiner;
import kotlin.text.Typography;

/* renamed from: kotlin.reflect.jvm.internal.impl.load.java.lazy.types.RawSubstitution */
/* loaded from: classes5.dex */
public final class RawType extends TypeSubstitution {
    public static final Companion Companion = new Companion(null);
    private static final JavaTypeAttributes lowerTypeAttr = JavaTypeResolverKt.toAttributes$default(TypeUsage.COMMON, false, null, 3, null).withFlexibility(JavaTypeFlexibility.FLEXIBLE_LOWER_BOUND);
    private static final JavaTypeAttributes upperTypeAttr = JavaTypeResolverKt.toAttributes$default(TypeUsage.COMMON, false, null, 3, null).withFlexibility(JavaTypeFlexibility.FLEXIBLE_UPPER_BOUND);
    private final TypeParameterUpperBoundEraser typeParameterUpperBoundEraser;

    /* compiled from: RawType.kt */
    /* renamed from: kotlin.reflect.jvm.internal.impl.load.java.lazy.types.RawSubstitution$WhenMappings */
    /* loaded from: classes5.dex */
    public /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] r0 = new int[JavaTypeFlexibility.values().length];
            r0[JavaTypeFlexibility.FLEXIBLE_LOWER_BOUND.ordinal()] = 1;
            r0[JavaTypeFlexibility.FLEXIBLE_UPPER_BOUND.ordinal()] = 2;
            r0[JavaTypeFlexibility.INFLEXIBLE.ordinal()] = 3;
            $EnumSwitchMapping$0 = r0;
        }
    }

    public RawType() {
        this(null, 1, null);
    }

    @Override // kotlin.reflect.jvm.internal.impl.types.TypeSubstitution
    public boolean isEmpty() {
        return false;
    }

    public RawType(TypeParameterUpperBoundEraser typeParameterUpperBoundEraser) {
        this.typeParameterUpperBoundEraser = typeParameterUpperBoundEraser == null ? new TypeParameterUpperBoundEraser(this) : typeParameterUpperBoundEraser;
    }

    public /* synthetic */ RawType(TypeParameterUpperBoundEraser typeParameterUpperBoundEraser, int r2, DefaultConstructorMarker defaultConstructorMarker) {
        this((r2 & 1) != 0 ? null : typeParameterUpperBoundEraser);
    }

    @Override // kotlin.reflect.jvm.internal.impl.types.TypeSubstitution
    /* renamed from: get */
    public TypeProjectionImpl mo3016get(KotlinType key) {
        Intrinsics.checkNotNullParameter(key, "key");
        return new TypeProjectionImpl(eraseType$default(this, key, null, 2, null));
    }

    static /* synthetic */ KotlinType eraseType$default(RawType rawType, KotlinType kotlinType, JavaTypeAttributes javaTypeAttributes, int r11, Object obj) {
        if ((r11 & 2) != 0) {
            javaTypeAttributes = new JavaTypeAttributes(TypeUsage.COMMON, null, false, null, null, 30, null);
        }
        return rawType.eraseType(kotlinType, javaTypeAttributes);
    }

    private final KotlinType eraseType(KotlinType kotlinType, JavaTypeAttributes javaTypeAttributes) {
        RawTypeImpl rawTypeImpl;
        ClassifierDescriptor mo3011getDeclarationDescriptor = kotlinType.getConstructor().mo3011getDeclarationDescriptor();
        if (mo3011getDeclarationDescriptor instanceof TypeParameterDescriptor) {
            KotlinType erasedUpperBound$descriptors_jvm = this.typeParameterUpperBoundEraser.getErasedUpperBound$descriptors_jvm((TypeParameterDescriptor) mo3011getDeclarationDescriptor, true, javaTypeAttributes);
            Intrinsics.checkNotNullExpressionValue(erasedUpperBound$descriptors_jvm, "typeParameterUpperBoundE…tion, isRaw = true, attr)");
            return eraseType(erasedUpperBound$descriptors_jvm, javaTypeAttributes);
        } else if (mo3011getDeclarationDescriptor instanceof ClassDescriptor) {
            ClassifierDescriptor mo3011getDeclarationDescriptor2 = FlexibleTypesKt.upperIfFlexible(kotlinType).getConstructor().mo3011getDeclarationDescriptor();
            if (!(mo3011getDeclarationDescriptor2 instanceof ClassDescriptor)) {
                throw new IllegalStateException(("For some reason declaration for upper bound is not a class but \"" + mo3011getDeclarationDescriptor2 + "\" while for lower it's \"" + mo3011getDeclarationDescriptor + Typography.quote).toString());
            }
            Tuples<SimpleType, Boolean> eraseInflexibleBasedOnClassDescriptor = eraseInflexibleBasedOnClassDescriptor(FlexibleTypesKt.lowerIfFlexible(kotlinType), (ClassDescriptor) mo3011getDeclarationDescriptor, lowerTypeAttr);
            SimpleType component1 = eraseInflexibleBasedOnClassDescriptor.component1();
            boolean booleanValue = eraseInflexibleBasedOnClassDescriptor.component2().booleanValue();
            Tuples<SimpleType, Boolean> eraseInflexibleBasedOnClassDescriptor2 = eraseInflexibleBasedOnClassDescriptor(FlexibleTypesKt.upperIfFlexible(kotlinType), (ClassDescriptor) mo3011getDeclarationDescriptor2, upperTypeAttr);
            SimpleType component12 = eraseInflexibleBasedOnClassDescriptor2.component1();
            boolean booleanValue2 = eraseInflexibleBasedOnClassDescriptor2.component2().booleanValue();
            if (booleanValue || booleanValue2) {
                rawTypeImpl = new RawTypeImpl(component1, component12);
            } else {
                rawTypeImpl = KotlinTypeFactory.flexibleType(component1, component12);
            }
            return rawTypeImpl;
        } else {
            throw new IllegalStateException(Intrinsics.stringPlus("Unexpected declaration kind: ", mo3011getDeclarationDescriptor).toString());
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final Tuples<SimpleType, Boolean> eraseInflexibleBasedOnClassDescriptor(final SimpleType simpleType, final ClassDescriptor classDescriptor, final JavaTypeAttributes javaTypeAttributes) {
        if (simpleType.getConstructor().getParameters().isEmpty()) {
            return TuplesKt.m176to(simpleType, false);
        }
        SimpleType simpleType2 = simpleType;
        if (KotlinBuiltIns.isArray(simpleType2)) {
            TypeProjection typeProjection = simpleType.getArguments().get(0);
            Variance projectionKind = typeProjection.getProjectionKind();
            KotlinType type = typeProjection.getType();
            Intrinsics.checkNotNullExpressionValue(type, "componentTypeProjection.type");
            return TuplesKt.m176to(KotlinTypeFactory.simpleType$default(simpleType.getAnnotations(), simpleType.getConstructor(), CollectionsKt.listOf(new TypeProjectionImpl(projectionKind, eraseType(type, javaTypeAttributes))), simpleType.isMarkedNullable(), (KotlinTypeRefiner) null, 16, (Object) null), false);
        } else if (KotlinTypeKt.isError(simpleType2)) {
            SimpleType createErrorType = ErrorUtils.createErrorType(Intrinsics.stringPlus("Raw error type: ", simpleType.getConstructor()));
            Intrinsics.checkNotNullExpressionValue(createErrorType, "createErrorType(\"Raw err…pe: ${type.constructor}\")");
            return TuplesKt.m176to(createErrorType, false);
        } else {
            MemberScope memberScope = classDescriptor.getMemberScope(this);
            Intrinsics.checkNotNullExpressionValue(memberScope, "declaration.getMemberScope(this)");
            Annotations annotations = simpleType.getAnnotations();
            TypeConstructor typeConstructor = classDescriptor.getTypeConstructor();
            Intrinsics.checkNotNullExpressionValue(typeConstructor, "declaration.typeConstructor");
            List<TypeParameterDescriptor> parameters = classDescriptor.getTypeConstructor().getParameters();
            Intrinsics.checkNotNullExpressionValue(parameters, "declaration.typeConstructor.parameters");
            List<TypeParameterDescriptor> list = parameters;
            ArrayList arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(list, 10));
            for (TypeParameterDescriptor parameter : list) {
                Intrinsics.checkNotNullExpressionValue(parameter, "parameter");
                arrayList.add(computeProjection$default(this, parameter, javaTypeAttributes, null, 4, null));
            }
            return TuplesKt.m176to(KotlinTypeFactory.simpleTypeWithNonTrivialMemberScope(annotations, typeConstructor, arrayList, simpleType.isMarkedNullable(), memberScope, new Function1<KotlinTypeRefiner, SimpleType>() { // from class: kotlin.reflect.jvm.internal.impl.load.java.lazy.types.RawSubstitution$eraseInflexibleBasedOnClassDescriptor$2
                /* JADX INFO: Access modifiers changed from: package-private */
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super(1);
                }

                @Override // kotlin.jvm.functions.Function1
                public final SimpleType invoke(KotlinTypeRefiner kotlinTypeRefiner) {
                    ClassDescriptor findClassAcrossModuleDependencies;
                    Tuples eraseInflexibleBasedOnClassDescriptor;
                    Intrinsics.checkNotNullParameter(kotlinTypeRefiner, "kotlinTypeRefiner");
                    ClassDescriptor classDescriptor2 = ClassDescriptor.this;
                    if (!(classDescriptor2 instanceof ClassDescriptor)) {
                        classDescriptor2 = null;
                    }
                    ClassId classId = classDescriptor2 == null ? null : DescriptorUtils.getClassId(classDescriptor2);
                    if (classId == null || (findClassAcrossModuleDependencies = kotlinTypeRefiner.findClassAcrossModuleDependencies(classId)) == null || Intrinsics.areEqual(findClassAcrossModuleDependencies, ClassDescriptor.this)) {
                        return null;
                    }
                    eraseInflexibleBasedOnClassDescriptor = this.eraseInflexibleBasedOnClassDescriptor(simpleType, findClassAcrossModuleDependencies, javaTypeAttributes);
                    return (SimpleType) eraseInflexibleBasedOnClassDescriptor.getFirst();
                }
            }), true);
        }
    }

    public static /* synthetic */ TypeProjection computeProjection$default(RawType rawType, TypeParameterDescriptor typeParameterDescriptor, JavaTypeAttributes javaTypeAttributes, KotlinType kotlinType, int r4, Object obj) {
        if ((r4 & 4) != 0) {
            kotlinType = rawType.typeParameterUpperBoundEraser.getErasedUpperBound$descriptors_jvm(typeParameterDescriptor, true, javaTypeAttributes);
            Intrinsics.checkNotNullExpressionValue(kotlinType, "fun computeProjection(\n …er, attr)\n        }\n    }");
        }
        return rawType.computeProjection(typeParameterDescriptor, javaTypeAttributes, kotlinType);
    }

    public final TypeProjection computeProjection(TypeParameterDescriptor parameter, JavaTypeAttributes attr, KotlinType erasedUpperBound) {
        Intrinsics.checkNotNullParameter(parameter, "parameter");
        Intrinsics.checkNotNullParameter(attr, "attr");
        Intrinsics.checkNotNullParameter(erasedUpperBound, "erasedUpperBound");
        int r0 = WhenMappings.$EnumSwitchMapping$0[attr.getFlexibility().ordinal()];
        if (r0 != 1) {
            if (r0 == 2 || r0 == 3) {
                if (!parameter.getVariance().getAllowsOutPosition()) {
                    return new TypeProjectionImpl(Variance.INVARIANT, DescriptorUtils.getBuiltIns(parameter).getNothingType());
                }
                List<TypeParameterDescriptor> parameters = erasedUpperBound.getConstructor().getParameters();
                Intrinsics.checkNotNullExpressionValue(parameters, "erasedUpperBound.constructor.parameters");
                if (!parameters.isEmpty()) {
                    return new TypeProjectionImpl(Variance.OUT_VARIANCE, erasedUpperBound);
                }
                return JavaTypeResolverKt.makeStarProjection(parameter, attr);
            }
            throw new NoWhenBranchMatchedException();
        }
        return new TypeProjectionImpl(Variance.INVARIANT, erasedUpperBound);
    }

    /* compiled from: RawType.kt */
    /* renamed from: kotlin.reflect.jvm.internal.impl.load.java.lazy.types.RawSubstitution$Companion */
    /* loaded from: classes5.dex */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }
    }
}
