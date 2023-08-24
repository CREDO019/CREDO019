package io.jsonwebtoken.lang;

import java.io.InputStream;
import java.lang.reflect.Constructor;

/* loaded from: classes5.dex */
public final class Classes {
    private static final Classes INSTANCE = new Classes();
    private static final ClassLoaderAccessor THREAD_CL_ACCESSOR = new ExceptionIgnoringAccessor() { // from class: io.jsonwebtoken.lang.Classes.1
        @Override // io.jsonwebtoken.lang.Classes.ExceptionIgnoringAccessor
        protected ClassLoader doGetClassLoader() throws Throwable {
            return Thread.currentThread().getContextClassLoader();
        }
    };
    private static final ClassLoaderAccessor CLASS_CL_ACCESSOR = new ExceptionIgnoringAccessor() { // from class: io.jsonwebtoken.lang.Classes.2
        @Override // io.jsonwebtoken.lang.Classes.ExceptionIgnoringAccessor
        protected ClassLoader doGetClassLoader() throws Throwable {
            return Classes.class.getClassLoader();
        }
    };
    private static final ClassLoaderAccessor SYSTEM_CL_ACCESSOR = new ExceptionIgnoringAccessor() { // from class: io.jsonwebtoken.lang.Classes.3
        @Override // io.jsonwebtoken.lang.Classes.ExceptionIgnoringAccessor
        protected ClassLoader doGetClassLoader() throws Throwable {
            return ClassLoader.getSystemClassLoader();
        }
    };

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes5.dex */
    public interface ClassLoaderAccessor {
        InputStream getResourceStream(String str);

        Class loadClass(String str);
    }

    private Classes() {
    }

    public static Class forName(String str) throws UnknownClassException {
        Class loadClass = THREAD_CL_ACCESSOR.loadClass(str);
        if (loadClass == null) {
            loadClass = CLASS_CL_ACCESSOR.loadClass(str);
        }
        if (loadClass == null) {
            loadClass = SYSTEM_CL_ACCESSOR.loadClass(str);
        }
        if (loadClass == null) {
            String str2 = "Unable to load class named [" + str + "] from the thread context, current, or system/application ClassLoaders.  All heuristics have been exhausted.  Class could not be found.";
            if (str != null && str.startsWith("com.stormpath.sdk.impl")) {
                str2 = str2 + "  Have you remembered to include the stormpath-sdk-impl .jar in your runtime classpath?";
            }
            throw new UnknownClassException(str2);
        }
        return loadClass;
    }

    public static InputStream getResourceAsStream(String str) {
        InputStream resourceStream = THREAD_CL_ACCESSOR.getResourceStream(str);
        if (resourceStream == null) {
            resourceStream = CLASS_CL_ACCESSOR.getResourceStream(str);
        }
        return resourceStream == null ? SYSTEM_CL_ACCESSOR.getResourceStream(str) : resourceStream;
    }

    public static boolean isAvailable(String str) {
        try {
            forName(str);
            return true;
        } catch (UnknownClassException unused) {
            return false;
        }
    }

    public static Object newInstance(String str) {
        return newInstance(forName(str));
    }

    public static Object newInstance(String str, Object... objArr) {
        return newInstance(forName(str), objArr);
    }

    public static <T> T newInstance(Class<T> cls) {
        if (cls == null) {
            throw new IllegalArgumentException("Class method parameter cannot be null.");
        }
        try {
            return cls.newInstance();
        } catch (Exception e) {
            throw new InstantiationException("Unable to instantiate class [" + cls.getName() + "]", e);
        }
    }

    public static <T> T newInstance(Class<T> cls, Object... objArr) {
        Class[] clsArr = new Class[objArr.length];
        for (int r1 = 0; r1 < objArr.length; r1++) {
            clsArr[r1] = objArr[r1].getClass();
        }
        return (T) instantiate(getConstructor(cls, clsArr), objArr);
    }

    public static <T> Constructor<T> getConstructor(Class<T> cls, Class... clsArr) {
        try {
            return cls.getConstructor(clsArr);
        } catch (NoSuchMethodException e) {
            throw new IllegalStateException(e);
        }
    }

    public static <T> T instantiate(Constructor<T> constructor, Object... objArr) {
        try {
            return constructor.newInstance(objArr);
        } catch (Exception e) {
            throw new InstantiationException("Unable to instantiate instance with constructor [" + constructor + "]", e);
        }
    }

    /* loaded from: classes5.dex */
    private static abstract class ExceptionIgnoringAccessor implements ClassLoaderAccessor {
        protected abstract ClassLoader doGetClassLoader() throws Throwable;

        private ExceptionIgnoringAccessor() {
        }

        @Override // io.jsonwebtoken.lang.Classes.ClassLoaderAccessor
        public Class loadClass(String str) {
            ClassLoader classLoader = getClassLoader();
            if (classLoader != null) {
                try {
                    return classLoader.loadClass(str);
                } catch (ClassNotFoundException unused) {
                }
            }
            return null;
        }

        @Override // io.jsonwebtoken.lang.Classes.ClassLoaderAccessor
        public InputStream getResourceStream(String str) {
            ClassLoader classLoader = getClassLoader();
            if (classLoader != null) {
                return classLoader.getResourceAsStream(str);
            }
            return null;
        }

        protected final ClassLoader getClassLoader() {
            try {
                return doGetClassLoader();
            } catch (Throwable unused) {
                return null;
            }
        }
    }
}
