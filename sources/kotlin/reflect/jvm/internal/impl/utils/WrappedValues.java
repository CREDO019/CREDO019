package kotlin.reflect.jvm.internal.impl.utils;

/* loaded from: classes5.dex */
public class WrappedValues {
    private static final Object NULL_VALUE = new Object() { // from class: kotlin.reflect.jvm.internal.impl.utils.WrappedValues.1
        public String toString() {
            return "NULL_VALUE";
        }
    };
    public static volatile boolean throwWrappedProcessCanceledException = false;

    private static /* synthetic */ void $$$reportNull$$$0(int r8) {
        String str = (r8 == 1 || r8 == 2) ? "@NotNull method %s.%s must not return null" : "Argument for @NotNull parameter '%s' of %s.%s must not be null";
        Object[] objArr = new Object[(r8 == 1 || r8 == 2) ? 2 : 3];
        if (r8 == 1 || r8 == 2) {
            objArr[0] = "kotlin/reflect/jvm/internal/impl/utils/WrappedValues";
        } else if (r8 != 3) {
            objArr[0] = "value";
        } else {
            objArr[0] = "throwable";
        }
        if (r8 == 1 || r8 == 2) {
            objArr[1] = "escapeNull";
        } else {
            objArr[1] = "kotlin/reflect/jvm/internal/impl/utils/WrappedValues";
        }
        if (r8 != 1 && r8 != 2) {
            if (r8 == 3) {
                objArr[2] = "escapeThrowable";
            } else if (r8 != 4) {
                objArr[2] = "unescapeNull";
            } else {
                objArr[2] = "unescapeExceptionOrNull";
            }
        }
        String format = String.format(str, objArr);
        if (r8 != 1 && r8 != 2) {
            throw new IllegalArgumentException(format);
        }
        throw new IllegalStateException(format);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes5.dex */
    public static final class ThrowableWrapper {
        private final Throwable throwable;

        private static /* synthetic */ void $$$reportNull$$$0(int r7) {
            String str = r7 != 1 ? "Argument for @NotNull parameter '%s' of %s.%s must not be null" : "@NotNull method %s.%s must not return null";
            Object[] objArr = new Object[r7 != 1 ? 3 : 2];
            if (r7 != 1) {
                objArr[0] = "throwable";
            } else {
                objArr[0] = "kotlin/reflect/jvm/internal/impl/utils/WrappedValues$ThrowableWrapper";
            }
            if (r7 != 1) {
                objArr[1] = "kotlin/reflect/jvm/internal/impl/utils/WrappedValues$ThrowableWrapper";
            } else {
                objArr[1] = "getThrowable";
            }
            if (r7 != 1) {
                objArr[2] = "<init>";
            }
            String format = String.format(str, objArr);
            if (r7 == 1) {
                throw new IllegalStateException(format);
            }
        }

        private ThrowableWrapper(Throwable th) {
            if (th == null) {
                $$$reportNull$$$0(0);
            }
            this.throwable = th;
        }

        public Throwable getThrowable() {
            Throwable th = this.throwable;
            if (th == null) {
                $$$reportNull$$$0(1);
            }
            return th;
        }

        public String toString() {
            return this.throwable.toString();
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static <V> V unescapeNull(Object obj) {
        if (obj == 0) {
            $$$reportNull$$$0(0);
        }
        if (obj == NULL_VALUE) {
            return null;
        }
        return obj;
    }

    public static <V> Object escapeNull(V v) {
        if (v != null) {
            if (v == null) {
                $$$reportNull$$$0(2);
            }
            return v;
        }
        Object obj = NULL_VALUE;
        if (obj == null) {
            $$$reportNull$$$0(1);
        }
        return obj;
    }

    public static Object escapeThrowable(Throwable th) {
        if (th == null) {
            $$$reportNull$$$0(3);
        }
        return new ThrowableWrapper(th);
    }

    public static <V> V unescapeExceptionOrNull(Object obj) {
        if (obj == null) {
            $$$reportNull$$$0(4);
        }
        return (V) unescapeNull(unescapeThrowable(obj));
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static <V> V unescapeThrowable(Object obj) {
        if (obj instanceof ThrowableWrapper) {
            Throwable throwable = ((ThrowableWrapper) obj).getThrowable();
            if (throwWrappedProcessCanceledException && exceptionUtils.isProcessCanceledException(throwable)) {
                throw new WrappedProcessCanceledException(throwable);
            }
            throw exceptionUtils.rethrow(throwable);
        }
        return obj;
    }

    /* loaded from: classes5.dex */
    public static class WrappedProcessCanceledException extends RuntimeException {
        public WrappedProcessCanceledException(Throwable th) {
            super("Rethrow stored exception", th);
        }
    }
}
