package p042rx.internal.util.unsafe;

import java.lang.reflect.Field;
import sun.misc.Unsafe;

/* renamed from: rx.internal.util.unsafe.UnsafeAccess */
/* loaded from: classes6.dex */
public final class UnsafeAccess {
    private static final boolean DISABLED_BY_USER;
    public static final Unsafe UNSAFE;

    static {
        DISABLED_BY_USER = System.getProperty("rx.unsafe-disable") != null;
        Unsafe unsafe = null;
        try {
            Field declaredField = Unsafe.class.getDeclaredField("theUnsafe");
            declaredField.setAccessible(true);
            unsafe = (Unsafe) declaredField.get(null);
        } catch (Throwable unused) {
        }
        UNSAFE = unsafe;
    }

    private UnsafeAccess() {
        throw new IllegalStateException("No instances!");
    }

    public static boolean isUnsafeAvailable() {
        return (UNSAFE == null || DISABLED_BY_USER) ? false : true;
    }

    public static int getAndIncrementInt(Object obj, long j) {
        Unsafe unsafe;
        int intVolatile;
        do {
            unsafe = UNSAFE;
            intVolatile = unsafe.getIntVolatile(obj, j);
        } while (!unsafe.compareAndSwapInt(obj, j, intVolatile, intVolatile + 1));
        return intVolatile;
    }

    public static int getAndAddInt(Object obj, long j, int r10) {
        Unsafe unsafe;
        int intVolatile;
        do {
            unsafe = UNSAFE;
            intVolatile = unsafe.getIntVolatile(obj, j);
        } while (!unsafe.compareAndSwapInt(obj, j, intVolatile, intVolatile + r10));
        return intVolatile;
    }

    public static int getAndSetInt(Object obj, long j, int r10) {
        Unsafe unsafe;
        int intVolatile;
        do {
            unsafe = UNSAFE;
            intVolatile = unsafe.getIntVolatile(obj, j);
        } while (!unsafe.compareAndSwapInt(obj, j, intVolatile, r10));
        return intVolatile;
    }

    public static boolean compareAndSwapInt(Object obj, long j, int r9, int r10) {
        return UNSAFE.compareAndSwapInt(obj, j, r9, r10);
    }

    public static long addressOf(Class<?> cls, String str) {
        try {
            return UNSAFE.objectFieldOffset(cls.getDeclaredField(str));
        } catch (NoSuchFieldException e) {
            InternalError internalError = new InternalError();
            internalError.initCause(e);
            throw internalError;
        }
    }
}
