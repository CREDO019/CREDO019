package expo.modules.kotlin.allocators;

import expo.modules.kotlin.allocators.UnsafeAllocator;
import java.io.ObjectStreamClass;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: UnsafeAllocator.kt */
@Metadata(m184d1 = {"\u0000\u000e\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0004\bæ\u0080\u0001\u0018\u0000 \u0005*\u0004\b\u0000\u0010\u00012\u00020\u0002:\u0001\u0005J\r\u0010\u0003\u001a\u00028\u0000H&¢\u0006\u0002\u0010\u0004¨\u0006\u0006"}, m183d2 = {"Lexpo/modules/kotlin/allocators/UnsafeAllocator;", "T", "", "newInstance", "()Ljava/lang/Object;", "Companion", "expo-modules-core_release"}, m182k = 1, m181mv = {1, 6, 0}, m179xi = 48)
/* loaded from: classes4.dex */
public interface UnsafeAllocator<T> {
    public static final Companion Companion = Companion.$$INSTANCE;

    T newInstance() throws Exception;

    /* compiled from: UnsafeAllocator.kt */
    @Metadata(m184d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\"\u0010\u0003\u001a\b\u0012\u0004\u0012\u0002H\u00050\u0004\"\u0004\b\u0001\u0010\u00052\f\u0010\u0006\u001a\b\u0012\u0004\u0012\u0002H\u00050\u0007H\u0007¨\u0006\b"}, m183d2 = {"Lexpo/modules/kotlin/allocators/UnsafeAllocator$Companion;", "", "()V", "createAllocator", "Lexpo/modules/kotlin/allocators/UnsafeAllocator;", "T", "clazz", "Ljava/lang/Class;", "expo-modules-core_release"}, m182k = 1, m181mv = {1, 6, 0}, m179xi = 48)
    /* loaded from: classes4.dex */
    public static final class Companion {
        static final /* synthetic */ Companion $$INSTANCE = new Companion();

        private Companion() {
        }

        public final <T> UnsafeAllocator<T> createAllocator(final Class<T> clazz) {
            Intrinsics.checkNotNullParameter(clazz, "clazz");
            try {
                try {
                    Method declaredMethod = ObjectStreamClass.class.getDeclaredMethod("getConstructorId", Class.class);
                    declaredMethod.setAccessible(true);
                    Object invoke = declaredMethod.invoke(null, Object.class);
                    if (invoke == null) {
                        throw new NullPointerException("null cannot be cast to non-null type kotlin.Int");
                    }
                    final int intValue = ((Integer) invoke).intValue();
                    final Method declaredMethod2 = ObjectStreamClass.class.getDeclaredMethod("newInstance", Class.class, Integer.TYPE);
                    declaredMethod2.setAccessible(true);
                    return new UnsafeAllocator() { // from class: expo.modules.kotlin.allocators.UnsafeAllocator$Companion$$ExternalSyntheticLambda1
                        @Override // expo.modules.kotlin.allocators.UnsafeAllocator
                        public final Object newInstance() {
                            Object m1669createAllocator$lambda0;
                            m1669createAllocator$lambda0 = UnsafeAllocator.Companion.m1669createAllocator$lambda0(declaredMethod2, clazz, intValue);
                            return m1669createAllocator$lambda0;
                        }
                    };
                } catch (Throwable unused) {
                    Class<?> cls = Class.forName("sun.misc.Unsafe");
                    Field declaredField = cls.getDeclaredField("theUnsafe");
                    declaredField.setAccessible(true);
                    final Object obj = declaredField.get(null);
                    final Method method = cls.getMethod("allocateInstance", Class.class);
                    return new UnsafeAllocator() { // from class: expo.modules.kotlin.allocators.UnsafeAllocator$Companion$$ExternalSyntheticLambda2
                        @Override // expo.modules.kotlin.allocators.UnsafeAllocator
                        public final Object newInstance() {
                            Object m1670createAllocator$lambda1;
                            m1670createAllocator$lambda1 = UnsafeAllocator.Companion.m1670createAllocator$lambda1(method, obj, clazz);
                            return m1670createAllocator$lambda1;
                        }
                    };
                }
            } catch (Throwable unused2) {
                return new UnsafeAllocator() { // from class: expo.modules.kotlin.allocators.UnsafeAllocator$Companion$$ExternalSyntheticLambda0
                    @Override // expo.modules.kotlin.allocators.UnsafeAllocator
                    public final Object newInstance() {
                        Object m1671createAllocator$lambda2;
                        m1671createAllocator$lambda2 = UnsafeAllocator.Companion.m1671createAllocator$lambda2(clazz);
                        return m1671createAllocator$lambda2;
                    }
                };
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* renamed from: createAllocator$lambda-0  reason: not valid java name */
        public static final Object m1669createAllocator$lambda0(Method method, Class clazz, int r4) {
            Intrinsics.checkNotNullParameter(clazz, "$clazz");
            return method.invoke(null, clazz, Integer.valueOf(r4));
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* renamed from: createAllocator$lambda-1  reason: not valid java name */
        public static final Object m1670createAllocator$lambda1(Method method, Object obj, Class clazz) {
            Intrinsics.checkNotNullParameter(clazz, "$clazz");
            return method.invoke(obj, clazz);
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* renamed from: createAllocator$lambda-2  reason: not valid java name */
        public static final Object m1671createAllocator$lambda2(Class clazz) {
            Intrinsics.checkNotNullParameter(clazz, "$clazz");
            throw new IllegalArgumentException("Cannot allocate " + clazz);
        }
    }
}
