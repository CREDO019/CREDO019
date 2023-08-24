package kotlin.reflect.jvm.internal.impl.load.java.lazy;

import java.util.Map;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.jvm.internal.impl.descriptors.DeclarationDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.TypeParameterDescriptor;
import kotlin.reflect.jvm.internal.impl.load.java.lazy.descriptors.LazyJavaTypeParameterDescriptor;
import kotlin.reflect.jvm.internal.impl.load.java.structure.JavaTypeParameter;
import kotlin.reflect.jvm.internal.impl.load.java.structure.JavaTypeParameterListOwner;
import kotlin.reflect.jvm.internal.impl.storage.MemoizedFunctionToNullable;
import kotlin.reflect.jvm.internal.impl.utils.collections;

/* renamed from: kotlin.reflect.jvm.internal.impl.load.java.lazy.LazyJavaTypeParameterResolver */
/* loaded from: classes5.dex */
public final class resolvers implements TypeParameterResolver {

    /* renamed from: c */
    private final LazyJavaResolverContext f1504c;
    private final DeclarationDescriptor containingDeclaration;
    private final MemoizedFunctionToNullable<JavaTypeParameter, LazyJavaTypeParameterDescriptor> resolve;
    private final Map<JavaTypeParameter, Integer> typeParameters;
    private final int typeParametersIndexOffset;

    public resolvers(LazyJavaResolverContext c, DeclarationDescriptor containingDeclaration, JavaTypeParameterListOwner typeParameterOwner, int r5) {
        Intrinsics.checkNotNullParameter(c, "c");
        Intrinsics.checkNotNullParameter(containingDeclaration, "containingDeclaration");
        Intrinsics.checkNotNullParameter(typeParameterOwner, "typeParameterOwner");
        this.f1504c = c;
        this.containingDeclaration = containingDeclaration;
        this.typeParametersIndexOffset = r5;
        this.typeParameters = collections.mapToIndex(typeParameterOwner.getTypeParameters());
        this.resolve = c.getStorageManager().createMemoizedFunctionWithNullableValues(new Function1<JavaTypeParameter, LazyJavaTypeParameterDescriptor>() { // from class: kotlin.reflect.jvm.internal.impl.load.java.lazy.LazyJavaTypeParameterResolver$resolve$1
            /* JADX INFO: Access modifiers changed from: package-private */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public final LazyJavaTypeParameterDescriptor invoke(JavaTypeParameter typeParameter) {
                Map map;
                LazyJavaResolverContext lazyJavaResolverContext;
                DeclarationDescriptor declarationDescriptor;
                int r4;
                DeclarationDescriptor declarationDescriptor2;
                Intrinsics.checkNotNullParameter(typeParameter, "typeParameter");
                map = resolvers.this.typeParameters;
                Integer num = (Integer) map.get(typeParameter);
                if (num == null) {
                    return null;
                }
                resolvers resolversVar = resolvers.this;
                int intValue = num.intValue();
                lazyJavaResolverContext = resolversVar.f1504c;
                LazyJavaResolverContext child = context.child(lazyJavaResolverContext, resolversVar);
                declarationDescriptor = resolversVar.containingDeclaration;
                LazyJavaResolverContext copyWithNewDefaultTypeQualifiers = context.copyWithNewDefaultTypeQualifiers(child, declarationDescriptor.getAnnotations());
                r4 = resolversVar.typeParametersIndexOffset;
                int r42 = r4 + intValue;
                declarationDescriptor2 = resolversVar.containingDeclaration;
                return new LazyJavaTypeParameterDescriptor(copyWithNewDefaultTypeQualifiers, typeParameter, r42, declarationDescriptor2);
            }
        });
    }

    @Override // kotlin.reflect.jvm.internal.impl.load.java.lazy.TypeParameterResolver
    public TypeParameterDescriptor resolveTypeParameter(JavaTypeParameter javaTypeParameter) {
        Intrinsics.checkNotNullParameter(javaTypeParameter, "javaTypeParameter");
        LazyJavaTypeParameterDescriptor invoke = this.resolve.invoke(javaTypeParameter);
        return invoke == null ? this.f1504c.getTypeParameterResolver().resolveTypeParameter(javaTypeParameter) : invoke;
    }
}
