package kotlin.reflect.jvm.internal.impl.descriptors;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.jvm.internal.impl.resolve.descriptorUtil.DescriptorUtils;
import kotlin.reflect.jvm.internal.impl.types.ErrorUtils;
import kotlin.reflect.jvm.internal.impl.types.KotlinType;
import kotlin.reflect.jvm.internal.impl.types.TypeProjection;
import kotlin.sequences.Sequence;
import kotlin.sequences.SequencesKt;

/* compiled from: typeParameterUtils.kt */
/* loaded from: classes5.dex */
public final class TypeParameterUtilsKt {
    public static final List<TypeParameterDescriptor> computeConstructorTypeParameters(ClassifierDescriptorWithTypeParameters classifierDescriptorWithTypeParameters) {
        DeclarationDescriptor declarationDescriptor;
        Intrinsics.checkNotNullParameter(classifierDescriptorWithTypeParameters, "<this>");
        List<TypeParameterDescriptor> declaredTypeParameters = classifierDescriptorWithTypeParameters.getDeclaredTypeParameters();
        Intrinsics.checkNotNullExpressionValue(declaredTypeParameters, "declaredTypeParameters");
        if (classifierDescriptorWithTypeParameters.isInner() || (classifierDescriptorWithTypeParameters.getContainingDeclaration() instanceof CallableDescriptor)) {
            ClassifierDescriptorWithTypeParameters classifierDescriptorWithTypeParameters2 = classifierDescriptorWithTypeParameters;
            List list = SequencesKt.toList(SequencesKt.flatMap(SequencesKt.filter(SequencesKt.takeWhile(DescriptorUtils.getParents(classifierDescriptorWithTypeParameters2), new Function1<DeclarationDescriptor, Boolean>() { // from class: kotlin.reflect.jvm.internal.impl.descriptors.TypeParameterUtilsKt$computeConstructorTypeParameters$parametersFromContainingFunctions$1
                @Override // kotlin.jvm.functions.Function1
                public final Boolean invoke(DeclarationDescriptor it) {
                    Intrinsics.checkNotNullParameter(it, "it");
                    return Boolean.valueOf(it instanceof CallableDescriptor);
                }
            }), new Function1<DeclarationDescriptor, Boolean>() { // from class: kotlin.reflect.jvm.internal.impl.descriptors.TypeParameterUtilsKt$computeConstructorTypeParameters$parametersFromContainingFunctions$2
                @Override // kotlin.jvm.functions.Function1
                public final Boolean invoke(DeclarationDescriptor it) {
                    Intrinsics.checkNotNullParameter(it, "it");
                    return Boolean.valueOf(!(it instanceof ConstructorDescriptor));
                }
            }), new Function1<DeclarationDescriptor, Sequence<? extends TypeParameterDescriptor>>() { // from class: kotlin.reflect.jvm.internal.impl.descriptors.TypeParameterUtilsKt$computeConstructorTypeParameters$parametersFromContainingFunctions$3
                @Override // kotlin.jvm.functions.Function1
                public final Sequence<TypeParameterDescriptor> invoke(DeclarationDescriptor it) {
                    Intrinsics.checkNotNullParameter(it, "it");
                    List<TypeParameterDescriptor> typeParameters = ((CallableDescriptor) it).getTypeParameters();
                    Intrinsics.checkNotNullExpressionValue(typeParameters, "it as CallableDescriptor).typeParameters");
                    return CollectionsKt.asSequence(typeParameters);
                }
            }));
            Iterator<DeclarationDescriptor> it = DescriptorUtils.getParents(classifierDescriptorWithTypeParameters2).iterator();
            while (true) {
                if (!it.hasNext()) {
                    declarationDescriptor = null;
                    break;
                }
                declarationDescriptor = it.next();
                if (declarationDescriptor instanceof ClassDescriptor) {
                    break;
                }
            }
            ClassDescriptor classDescriptor = (ClassDescriptor) declarationDescriptor;
            List<TypeParameterDescriptor> parameters = classDescriptor != null ? classDescriptor.getTypeConstructor().getParameters() : null;
            if (parameters == null) {
                parameters = CollectionsKt.emptyList();
            }
            if (list.isEmpty() && parameters.isEmpty()) {
                List<TypeParameterDescriptor> declaredTypeParameters2 = classifierDescriptorWithTypeParameters.getDeclaredTypeParameters();
                Intrinsics.checkNotNullExpressionValue(declaredTypeParameters2, "declaredTypeParameters");
                return declaredTypeParameters2;
            }
            List<TypeParameterDescriptor> plus = CollectionsKt.plus((Collection) list, (Iterable) parameters);
            ArrayList arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(plus, 10));
            for (TypeParameterDescriptor it2 : plus) {
                Intrinsics.checkNotNullExpressionValue(it2, "it");
                arrayList.add(capturedCopyForInnerDeclaration(it2, classifierDescriptorWithTypeParameters2, declaredTypeParameters.size()));
            }
            return CollectionsKt.plus((Collection) declaredTypeParameters, (Iterable) arrayList);
        }
        return declaredTypeParameters;
    }

    private static final typeParameterUtils capturedCopyForInnerDeclaration(TypeParameterDescriptor typeParameterDescriptor, DeclarationDescriptor declarationDescriptor, int r3) {
        return new typeParameterUtils(typeParameterDescriptor, declarationDescriptor, r3);
    }

    public static final PossiblyInnerType buildPossiblyInnerType(KotlinType kotlinType) {
        Intrinsics.checkNotNullParameter(kotlinType, "<this>");
        ClassifierDescriptor mo3011getDeclarationDescriptor = kotlinType.getConstructor().mo3011getDeclarationDescriptor();
        return buildPossiblyInnerType(kotlinType, mo3011getDeclarationDescriptor instanceof ClassifierDescriptorWithTypeParameters ? (ClassifierDescriptorWithTypeParameters) mo3011getDeclarationDescriptor : null, 0);
    }

    private static final PossiblyInnerType buildPossiblyInnerType(KotlinType kotlinType, ClassifierDescriptorWithTypeParameters classifierDescriptorWithTypeParameters, int r7) {
        if (classifierDescriptorWithTypeParameters != null) {
            ClassifierDescriptorWithTypeParameters classifierDescriptorWithTypeParameters2 = classifierDescriptorWithTypeParameters;
            if (!ErrorUtils.isError(classifierDescriptorWithTypeParameters2)) {
                int size = classifierDescriptorWithTypeParameters.getDeclaredTypeParameters().size() + r7;
                if (!classifierDescriptorWithTypeParameters.isInner()) {
                    if (size != kotlinType.getArguments().size()) {
                        kotlin.reflect.jvm.internal.impl.resolve.DescriptorUtils.isLocal(classifierDescriptorWithTypeParameters2);
                    }
                    return new PossiblyInnerType(classifierDescriptorWithTypeParameters, kotlinType.getArguments().subList(r7, kotlinType.getArguments().size()), null);
                }
                List<TypeProjection> subList = kotlinType.getArguments().subList(r7, size);
                DeclarationDescriptor containingDeclaration = classifierDescriptorWithTypeParameters.getContainingDeclaration();
                return new PossiblyInnerType(classifierDescriptorWithTypeParameters, subList, buildPossiblyInnerType(kotlinType, containingDeclaration instanceof ClassifierDescriptorWithTypeParameters ? (ClassifierDescriptorWithTypeParameters) containingDeclaration : null, size));
            }
        }
        return null;
    }
}
