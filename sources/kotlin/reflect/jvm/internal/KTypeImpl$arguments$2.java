package kotlin.reflect.jvm.internal;

import java.lang.reflect.GenericArrayType;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.lang.reflect.WildcardType;
import java.util.ArrayList;
import java.util.List;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.LazyThreadSafetyMode;
import kotlin.Metadata;
import kotlin.NoWhenBranchMatchedException;
import kotlin.collections.ArraysKt;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Functions;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import kotlin.reflect.KTypeProjection;
import kotlin.reflect.jvm.internal.impl.descriptors.runtime.structure.reflectClassUtil;
import kotlin.reflect.jvm.internal.impl.types.KotlinType;
import kotlin.reflect.jvm.internal.impl.types.TypeProjection;
import kotlin.reflect.jvm.internal.impl.types.Variance;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: KTypeImpl.kt */
@Metadata(m184d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u0016\u0012\u0004\u0012\u00020\u0002 \u0003*\n\u0012\u0004\u0012\u00020\u0002\u0018\u00010\u00010\u0001H\n¢\u0006\u0002\b\u0004"}, m183d2 = {"<anonymous>", "", "Lkotlin/reflect/KTypeProjection;", "kotlin.jvm.PlatformType", "invoke"}, m182k = 3, m181mv = {1, 6, 0}, m179xi = 48)
/* loaded from: classes5.dex */
public final class KTypeImpl$arguments$2 extends Lambda implements Functions<List<? extends KTypeProjection>> {
    final /* synthetic */ Functions<Type> $computeJavaType;
    final /* synthetic */ KTypeImpl this$0;

    /* compiled from: KTypeImpl.kt */
    @Metadata(m182k = 3, m181mv = {1, 6, 0}, m179xi = 48)
    /* loaded from: classes5.dex */
    public /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] r0 = new int[Variance.values().length];
            r0[Variance.INVARIANT.ordinal()] = 1;
            r0[Variance.IN_VARIANCE.ordinal()] = 2;
            r0[Variance.OUT_VARIANCE.ordinal()] = 3;
            $EnumSwitchMapping$0 = r0;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    /* JADX WARN: Multi-variable type inference failed */
    public KTypeImpl$arguments$2(KTypeImpl kTypeImpl, Functions<? extends Type> functions) {
        super(0);
        this.this$0 = kTypeImpl;
        this.$computeJavaType = functions;
    }

    @Override // kotlin.jvm.functions.Functions
    public final List<? extends KTypeProjection> invoke() {
        KTypeProjection invariant;
        List<TypeProjection> arguments = this.this$0.getType().getArguments();
        if (arguments.isEmpty()) {
            return CollectionsKt.emptyList();
        }
        LazyThreadSafetyMode lazyThreadSafetyMode = LazyThreadSafetyMode.PUBLICATION;
        final KTypeImpl kTypeImpl = this.this$0;
        final Lazy lazy = LazyKt.lazy(lazyThreadSafetyMode, (Functions) new Functions<List<? extends Type>>() { // from class: kotlin.reflect.jvm.internal.KTypeImpl$arguments$2$parameterizedTypeArguments$2
            /* JADX INFO: Access modifiers changed from: package-private */
            {
                super(0);
            }

            @Override // kotlin.jvm.functions.Functions
            public final List<? extends Type> invoke() {
                Type javaType = KTypeImpl.this.getJavaType();
                Intrinsics.checkNotNull(javaType);
                return reflectClassUtil.getParameterizedTypeArguments(javaType);
            }
        });
        List<TypeProjection> list = arguments;
        Functions<Type> functions = this.$computeJavaType;
        final KTypeImpl kTypeImpl2 = this.this$0;
        ArrayList arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(list, 10));
        final int r5 = 0;
        for (Object obj : list) {
            int r7 = r5 + 1;
            if (r5 < 0) {
                CollectionsKt.throwIndexOverflow();
            }
            TypeProjection typeProjection = (TypeProjection) obj;
            if (typeProjection.isStarProjection()) {
                invariant = KTypeProjection.Companion.getSTAR();
            } else {
                KotlinType type = typeProjection.getType();
                Intrinsics.checkNotNullExpressionValue(type, "typeProjection.type");
                KTypeImpl kTypeImpl3 = new KTypeImpl(type, functions == null ? null : new Functions<Type>() { // from class: kotlin.reflect.jvm.internal.KTypeImpl$arguments$2$1$type$1
                    /* JADX INFO: Access modifiers changed from: package-private */
                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    /* JADX WARN: Multi-variable type inference failed */
                    {
                        super(0);
                    }

                    @Override // kotlin.jvm.functions.Functions
                    public final Type invoke() {
                        List m2997invoke$lambda0;
                        Type javaType = KTypeImpl.this.getJavaType();
                        if (javaType instanceof Class) {
                            Class cls = (Class) javaType;
                            Class componentType = cls.isArray() ? cls.getComponentType() : Object.class;
                            Intrinsics.checkNotNullExpressionValue(componentType, "{\n                      …                        }");
                            return componentType;
                        } else if (javaType instanceof GenericArrayType) {
                            if (r5 != 0) {
                                throw new KotlinReflectionInternalError(Intrinsics.stringPlus("Array type has been queried for a non-0th argument: ", KTypeImpl.this));
                            }
                            Type genericComponentType = ((GenericArrayType) javaType).getGenericComponentType();
                            Intrinsics.checkNotNullExpressionValue(genericComponentType, "{\n                      …                        }");
                            return genericComponentType;
                        } else if (javaType instanceof ParameterizedType) {
                            m2997invoke$lambda0 = KTypeImpl$arguments$2.m2997invoke$lambda0(lazy);
                            Type type2 = (Type) m2997invoke$lambda0.get(r5);
                            if (type2 instanceof WildcardType) {
                                WildcardType wildcardType = (WildcardType) type2;
                                Type[] lowerBounds = wildcardType.getLowerBounds();
                                Intrinsics.checkNotNullExpressionValue(lowerBounds, "argument.lowerBounds");
                                Type type3 = (Type) ArraysKt.firstOrNull(lowerBounds);
                                if (type3 == null) {
                                    Type[] upperBounds = wildcardType.getUpperBounds();
                                    Intrinsics.checkNotNullExpressionValue(upperBounds, "argument.upperBounds");
                                    type2 = (Type) ArraysKt.first(upperBounds);
                                } else {
                                    type2 = type3;
                                }
                            }
                            Intrinsics.checkNotNullExpressionValue(type2, "{\n                      …                        }");
                            return type2;
                        } else {
                            throw new KotlinReflectionInternalError(Intrinsics.stringPlus("Non-generic type has been queried for arguments: ", KTypeImpl.this));
                        }
                    }
                });
                int r52 = WhenMappings.$EnumSwitchMapping$0[typeProjection.getProjectionKind().ordinal()];
                if (r52 == 1) {
                    invariant = KTypeProjection.Companion.invariant(kTypeImpl3);
                } else if (r52 == 2) {
                    invariant = KTypeProjection.Companion.contravariant(kTypeImpl3);
                } else if (r52 != 3) {
                    throw new NoWhenBranchMatchedException();
                } else {
                    invariant = KTypeProjection.Companion.covariant(kTypeImpl3);
                }
            }
            arrayList.add(invariant);
            r5 = r7;
        }
        return arrayList;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: invoke$lambda-0  reason: not valid java name */
    public static final List<Type> m2997invoke$lambda0(Lazy<? extends List<? extends Type>> lazy) {
        return (List) lazy.getValue();
    }
}
