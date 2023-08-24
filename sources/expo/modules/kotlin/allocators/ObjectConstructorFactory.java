package expo.modules.kotlin.allocators;

import java.lang.reflect.Constructor;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: ObjectConstructorFactory.kt */
@Metadata(m184d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J \u0010\u0003\u001a\b\u0012\u0004\u0012\u0002H\u00050\u0004\"\u0004\b\u0000\u0010\u00052\f\u0010\u0006\u001a\b\u0012\u0004\u0012\u0002H\u00050\u0007J$\u0010\b\u001a\n\u0012\u0004\u0012\u0002H\u0005\u0018\u00010\u0004\"\u0004\b\u0000\u0010\u00052\f\u0010\u0006\u001a\b\u0012\u0004\u0012\u0002H\u00050\u0007H\u0002J\"\u0010\t\u001a\b\u0012\u0004\u0012\u0002H\u00050\u0004\"\u0004\b\u0000\u0010\u00052\f\u0010\u0006\u001a\b\u0012\u0004\u0012\u0002H\u00050\u0007H\u0002¨\u0006\n"}, m183d2 = {"Lexpo/modules/kotlin/allocators/ObjectConstructorFactory;", "", "()V", "get", "Lexpo/modules/kotlin/allocators/ObjectConstructor;", "T", "clazz", "Ljava/lang/Class;", "tryToUseDefaultConstructor", "useUnsafeAllocator", "expo-modules-core_release"}, m182k = 1, m181mv = {1, 6, 0}, m179xi = 48)
/* loaded from: classes4.dex */
public final class ObjectConstructorFactory {
    public final <T> ObjectConstructor<T> get(Class<T> clazz) {
        Intrinsics.checkNotNullParameter(clazz, "clazz");
        ObjectConstructor<T> tryToUseDefaultConstructor = tryToUseDefaultConstructor(clazz);
        return tryToUseDefaultConstructor == null ? useUnsafeAllocator(clazz) : tryToUseDefaultConstructor;
    }

    private final <T> ObjectConstructor<T> tryToUseDefaultConstructor(Class<T> cls) {
        try {
            final Constructor<T> declaredConstructor = cls.getDeclaredConstructor(new Class[0]);
            if (!declaredConstructor.isAccessible()) {
                declaredConstructor.setAccessible(true);
            }
            return new ObjectConstructor() { // from class: expo.modules.kotlin.allocators.ObjectConstructorFactory$$ExternalSyntheticLambda1
                @Override // expo.modules.kotlin.allocators.ObjectConstructor
                public final Object construct() {
                    Object m1666tryToUseDefaultConstructor$lambda0;
                    m1666tryToUseDefaultConstructor$lambda0 = ObjectConstructorFactory.m1666tryToUseDefaultConstructor$lambda0(declaredConstructor);
                    return m1666tryToUseDefaultConstructor$lambda0;
                }
            };
        } catch (NoSuchMethodException unused) {
            return null;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: tryToUseDefaultConstructor$lambda-0  reason: not valid java name */
    public static final Object m1666tryToUseDefaultConstructor$lambda0(Constructor constructor) {
        return constructor.newInstance(new Object[0]);
    }

    private final <T> ObjectConstructor<T> useUnsafeAllocator(Class<T> cls) {
        final UnsafeAllocator<T> createAllocator = UnsafeAllocator.Companion.createAllocator(cls);
        return new ObjectConstructor() { // from class: expo.modules.kotlin.allocators.ObjectConstructorFactory$$ExternalSyntheticLambda0
            @Override // expo.modules.kotlin.allocators.ObjectConstructor
            public final Object construct() {
                Object m1667useUnsafeAllocator$lambda1;
                m1667useUnsafeAllocator$lambda1 = ObjectConstructorFactory.m1667useUnsafeAllocator$lambda1(UnsafeAllocator.this);
                return m1667useUnsafeAllocator$lambda1;
            }
        };
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: useUnsafeAllocator$lambda-1  reason: not valid java name */
    public static final Object m1667useUnsafeAllocator$lambda1(UnsafeAllocator allocator) {
        Intrinsics.checkNotNullParameter(allocator, "$allocator");
        return allocator.newInstance();
    }
}
