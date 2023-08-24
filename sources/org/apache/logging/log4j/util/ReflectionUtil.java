package org.apache.logging.log4j.util;

import java.lang.reflect.Method;
import java.util.Stack;
import org.apache.logging.log4j.Logger;

/* loaded from: classes5.dex */
public final class ReflectionUtil {
    private static final Method GET_CALLER_CLASS;
    static final int JDK_7u25_OFFSET;
    private static final Logger LOGGER;
    private static final PrivateSecurityManager SECURITY_MANAGER;
    private static final boolean SUN_REFLECTION_SUPPORTED;

    /* JADX WARN: Can't wrap try/catch for region: R(14:1|(2:2|3)|(11:7|(1:9)(1:24)|(1:11)|12|13|14|(1:16)|17|18|19|20)|25|26|(0)|12|13|14|(0)|17|18|19|20) */
    /* JADX WARN: Code restructure failed: missing block: B:25:0x008e, code lost:
        org.apache.logging.log4j.util.ReflectionUtil.LOGGER.debug("Not allowed to create SecurityManager. Falling back to slowest ReflectionUtil implementation.");
     */
    /* JADX WARN: Removed duplicated region for block: B:18:0x0070  */
    /* JADX WARN: Removed duplicated region for block: B:22:0x007d A[Catch: SecurityException -> 0x008e, TryCatch #1 {SecurityException -> 0x008e, blocks: (B:20:0x0077, B:22:0x007d, B:23:0x0087), top: B:29:0x0077 }] */
    static {
        /*
            org.apache.logging.log4j.status.StatusLogger r0 = org.apache.logging.log4j.status.StatusLogger.getLogger()
            org.apache.logging.log4j.util.ReflectionUtil.LOGGER = r0
            r1 = -1
            r2 = 0
            r3 = 1
            r4 = 0
            java.lang.String r5 = "sun.reflect.Reflection"
            java.lang.Class r5 = org.apache.logging.log4j.util.LoaderUtil.loadClass(r5)     // Catch: java.lang.Exception -> L64
            java.lang.String r6 = "getCallerClass"
            java.lang.Class[] r7 = new java.lang.Class[r3]     // Catch: java.lang.Exception -> L64
            java.lang.Class r8 = java.lang.Integer.TYPE     // Catch: java.lang.Exception -> L64
            r7[r4] = r8     // Catch: java.lang.Exception -> L64
            java.lang.reflect.Method r6 = r5.getDeclaredMethod(r6, r7)     // Catch: java.lang.Exception -> L64
            java.lang.Object[] r7 = new java.lang.Object[r3]     // Catch: java.lang.Exception -> L64
            java.lang.Integer r8 = java.lang.Integer.valueOf(r4)     // Catch: java.lang.Exception -> L64
            r7[r4] = r8     // Catch: java.lang.Exception -> L64
            java.lang.Object r7 = r6.invoke(r2, r7)     // Catch: java.lang.Exception -> L64
            java.lang.Object[] r8 = new java.lang.Object[r3]     // Catch: java.lang.Exception -> L64
            java.lang.Integer r9 = java.lang.Integer.valueOf(r4)     // Catch: java.lang.Exception -> L64
            r8[r4] = r9     // Catch: java.lang.Exception -> L64
            java.lang.Object r8 = r6.invoke(r2, r8)     // Catch: java.lang.Exception -> L64
            if (r7 == 0) goto L5a
            if (r7 == r5) goto L39
            goto L5a
        L39:
            java.lang.Object[] r7 = new java.lang.Object[r3]     // Catch: java.lang.Exception -> L64
            java.lang.Integer r8 = java.lang.Integer.valueOf(r3)     // Catch: java.lang.Exception -> L64
            r7[r4] = r8     // Catch: java.lang.Exception -> L64
            java.lang.Object r7 = r6.invoke(r2, r7)     // Catch: java.lang.Exception -> L64
            if (r7 != r5) goto L58
            java.lang.String r5 = "You are using Java 1.7.0_25 which has a broken implementation of Reflection.getCallerClass."
            r0.warn(r5)     // Catch: java.lang.Exception -> L64
            java.lang.String r5 = "You should upgrade to at least Java 1.7.0_40 or later."
            r0.warn(r5)     // Catch: java.lang.Exception -> L64
            java.lang.String r5 = "Using stack depth compensation offset of 1 due to Java 7u25."
            r0.debug(r5)     // Catch: java.lang.Exception -> L64
            r1 = 1
            goto L6d
        L58:
            r1 = 0
            goto L6d
        L5a:
            java.lang.String r5 = "Unexpected return value from Reflection.getCallerClass(): {}"
            java.lang.Object[] r6 = new java.lang.Object[r3]     // Catch: java.lang.Exception -> L64
            r6[r4] = r8     // Catch: java.lang.Exception -> L64
            r0.warn(r5, r6)     // Catch: java.lang.Exception -> L64
            goto L6c
        L64:
            r0 = move-exception
            org.apache.logging.log4j.Logger r5 = org.apache.logging.log4j.util.ReflectionUtil.LOGGER
            java.lang.String r6 = "sun.reflect.Reflection.getCallerClass is not supported. ReflectionUtil.getCallerClass will be much slower due to this."
            r5.info(r6, r0)
        L6c:
            r6 = r2
        L6d:
            if (r6 == 0) goto L70
            goto L71
        L70:
            r3 = 0
        L71:
            org.apache.logging.log4j.util.ReflectionUtil.SUN_REFLECTION_SUPPORTED = r3
            org.apache.logging.log4j.util.ReflectionUtil.GET_CALLER_CLASS = r6
            org.apache.logging.log4j.util.ReflectionUtil.JDK_7u25_OFFSET = r1
            java.lang.SecurityManager r0 = java.lang.System.getSecurityManager()     // Catch: java.lang.SecurityException -> L8e
            if (r0 == 0) goto L87
            java.lang.RuntimePermission r1 = new java.lang.RuntimePermission     // Catch: java.lang.SecurityException -> L8e
            java.lang.String r3 = "createSecurityManager"
            r1.<init>(r3)     // Catch: java.lang.SecurityException -> L8e
            r0.checkPermission(r1)     // Catch: java.lang.SecurityException -> L8e
        L87:
            org.apache.logging.log4j.util.ReflectionUtil$PrivateSecurityManager r0 = new org.apache.logging.log4j.util.ReflectionUtil$PrivateSecurityManager     // Catch: java.lang.SecurityException -> L8e
            r0.<init>()     // Catch: java.lang.SecurityException -> L8e
            r2 = r0
            goto L95
        L8e:
            org.apache.logging.log4j.Logger r0 = org.apache.logging.log4j.util.ReflectionUtil.LOGGER
            java.lang.String r1 = "Not allowed to create SecurityManager. Falling back to slowest ReflectionUtil implementation."
            r0.debug(r1)
        L95:
            org.apache.logging.log4j.util.ReflectionUtil.SECURITY_MANAGER = r2
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: org.apache.logging.log4j.util.ReflectionUtil.<clinit>():void");
    }

    public static boolean supportsFastReflection() {
        return SUN_REFLECTION_SUPPORTED;
    }

    public static Class<?> getCallerClass(int r8) {
        if (r8 < 0) {
            throw new IndexOutOfBoundsException(Integer.toString(r8));
        }
        if (supportsFastReflection()) {
            try {
                return (Class) GET_CALLER_CLASS.invoke(null, Integer.valueOf(r8 + 1 + JDK_7u25_OFFSET));
            } catch (Exception e) {
                LOGGER.error("Error in ReflectionUtil.getCallerClass({}).", Integer.valueOf(r8), e);
                return null;
            }
        }
        try {
            return LoaderUtil.loadClass(getEquivalentStackTraceElement(r8 + 1).getClassName());
        } catch (ClassNotFoundException e2) {
            LOGGER.error("Could not find class in ReflectionUtil.getCallerClass({}).", Integer.valueOf(r8), e2);
            return null;
        }
    }

    static StackTraceElement getEquivalentStackTraceElement(int r7) {
        StackTraceElement[] stackTrace;
        int r4 = 0;
        for (StackTraceElement stackTraceElement : new Throwable().getStackTrace()) {
            if (isValid(stackTraceElement)) {
                if (r4 == r7) {
                    return stackTraceElement;
                }
                r4++;
            }
        }
        LOGGER.error("Could not find an appropriate StackTraceElement at index {}", Integer.valueOf(r7));
        throw new IndexOutOfBoundsException(Integer.toString(r7));
    }

    private static boolean isValid(StackTraceElement stackTraceElement) {
        if (stackTraceElement.isNativeMethod()) {
            return false;
        }
        String className = stackTraceElement.getClassName();
        if (className.startsWith("sun.reflect.")) {
            return false;
        }
        String methodName = stackTraceElement.getMethodName();
        if (className.startsWith("java.lang.reflect.") && (methodName.equals("invoke") || methodName.equals("newInstance"))) {
            return false;
        }
        if (className.equals("java.lang.Class") && methodName.equals("newInstance")) {
            return false;
        }
        return (className.equals("java.lang.invoke.MethodHandle") && methodName.startsWith("invoke")) ? false : true;
    }

    public static Class<?> getCallerClass(String str) {
        return getCallerClass(str, "");
    }

    public static Class<?> getCallerClass(String str, String str2) {
        if (!supportsFastReflection()) {
            PrivateSecurityManager privateSecurityManager = SECURITY_MANAGER;
            if (privateSecurityManager != null) {
                return privateSecurityManager.getCallerClass(str, str2);
            }
            try {
                return LoaderUtil.loadClass(getCallerClassName(str, str2, new Throwable().getStackTrace()));
            } catch (ClassNotFoundException unused) {
                return null;
            }
        }
        boolean z = false;
        int r2 = 2;
        while (true) {
            Class<?> callerClass = getCallerClass(r2);
            if (callerClass == null) {
                return null;
            }
            if (str.equals(callerClass.getName())) {
                z = true;
            } else if (z && callerClass.getName().startsWith(str2)) {
                return callerClass;
            }
            r2++;
        }
    }

    public static Class<?> getCallerClass(Class<?> cls) {
        if (!supportsFastReflection()) {
            PrivateSecurityManager privateSecurityManager = SECURITY_MANAGER;
            if (privateSecurityManager != null) {
                return privateSecurityManager.getCallerClass(cls);
            }
            try {
                return LoaderUtil.loadClass(getCallerClassName(cls.getName(), "", new Throwable().getStackTrace()));
            } catch (ClassNotFoundException unused) {
                return Object.class;
            }
        }
        boolean z = false;
        int r1 = 2;
        while (true) {
            Class<?> callerClass = getCallerClass(r1);
            if (callerClass != null) {
                if (cls.equals(callerClass)) {
                    z = true;
                } else if (z) {
                    return callerClass;
                }
                r1++;
            } else {
                return Object.class;
            }
        }
    }

    private static String getCallerClassName(String str, String str2, StackTraceElement... stackTraceElementArr) {
        boolean z = false;
        for (StackTraceElement stackTraceElement : stackTraceElementArr) {
            String className = stackTraceElement.getClassName();
            if (className.equals(str)) {
                z = true;
            } else if (z && className.startsWith(str2)) {
                return className;
            }
        }
        return Object.class.getName();
    }

    public static Stack<Class<?>> getCurrentStackTrace() {
        PrivateSecurityManager privateSecurityManager = SECURITY_MANAGER;
        if (privateSecurityManager != null) {
            Class<?>[] classContext = privateSecurityManager.getClassContext();
            Stack<Class<?>> stack = new Stack<>();
            stack.ensureCapacity(classContext.length);
            for (Class<?> cls : classContext) {
                stack.push(cls);
            }
            return stack;
        } else if (supportsFastReflection()) {
            Stack<Class<?>> stack2 = new Stack<>();
            int r1 = 1;
            while (true) {
                Class<?> callerClass = getCallerClass(r1);
                if (callerClass == null) {
                    return stack2;
                }
                stack2.push(callerClass);
                r1++;
            }
        } else {
            return new Stack<>();
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes5.dex */
    public static final class PrivateSecurityManager extends SecurityManager {
        PrivateSecurityManager() {
        }

        @Override // java.lang.SecurityManager
        protected Class<?>[] getClassContext() {
            return super.getClassContext();
        }

        protected Class<?> getCallerClass(String str, String str2) {
            Class<?>[] classContext;
            boolean z = false;
            for (Class<?> cls : getClassContext()) {
                if (str.equals(cls.getName())) {
                    z = true;
                } else if (z && cls.getName().startsWith(str2)) {
                    return cls;
                }
            }
            return null;
        }

        protected Class<?> getCallerClass(Class<?> cls) {
            Class<?>[] classContext;
            boolean z = false;
            for (Class<?> cls2 : getClassContext()) {
                if (cls.equals(cls2)) {
                    z = true;
                } else if (z) {
                    return cls2;
                }
            }
            return Object.class;
        }
    }

    private ReflectionUtil() {
    }
}
