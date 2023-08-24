package org.apache.logging.log4j.util;

import com.facebook.hermes.intl.Constants;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.net.URL;
import java.security.AccessController;
import java.security.PrivilegedAction;
import java.util.Collection;
import java.util.Enumeration;
import java.util.LinkedHashSet;

/* loaded from: classes5.dex */
public final class LoaderUtil {
    private static final boolean GET_CLASS_LOADER_DISABLED;
    public static final String IGNORE_TCCL_PROPERTY = "log4j.ignoreTCL";
    private static final SecurityManager SECURITY_MANAGER;
    private static final PrivilegedAction<ClassLoader> TCCL_GETTER;
    private static Boolean ignoreTCCL;

    private LoaderUtil() {
    }

    static {
        SecurityManager securityManager = System.getSecurityManager();
        SECURITY_MANAGER = securityManager;
        TCCL_GETTER = new ThreadContextClassLoaderGetter();
        boolean z = false;
        if (securityManager != null) {
            try {
                securityManager.checkPermission(new RuntimePermission("getClassLoader"));
            } catch (SecurityException unused) {
                z = true;
            }
            GET_CLASS_LOADER_DISABLED = z;
            return;
        }
        GET_CLASS_LOADER_DISABLED = false;
    }

    public static ClassLoader getThreadContextClassLoader() {
        if (GET_CLASS_LOADER_DISABLED) {
            return LoaderUtil.class.getClassLoader();
        }
        return (ClassLoader) (SECURITY_MANAGER == null ? TCCL_GETTER.run() : AccessController.doPrivileged(TCCL_GETTER));
    }

    /* loaded from: classes5.dex */
    private static class ThreadContextClassLoaderGetter implements PrivilegedAction<ClassLoader> {
        private ThreadContextClassLoaderGetter() {
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // java.security.PrivilegedAction
        public ClassLoader run() {
            ClassLoader contextClassLoader = Thread.currentThread().getContextClassLoader();
            if (contextClassLoader != null) {
                return contextClassLoader;
            }
            ClassLoader classLoader = LoaderUtil.class.getClassLoader();
            return classLoader == null ? ClassLoader.getSystemClassLoader() : classLoader;
        }
    }

    public static Class<?> loadClass(String str) throws ClassNotFoundException {
        if (isIgnoreTccl()) {
            return Class.forName(str);
        }
        try {
            return getThreadContextClassLoader().loadClass(str);
        } catch (Throwable unused) {
            return Class.forName(str);
        }
    }

    public static Object newInstanceOf(String str) throws ClassNotFoundException, IllegalAccessException, InstantiationException, NoSuchMethodException, InvocationTargetException {
        Class<?> loadClass = loadClass(str);
        try {
            return loadClass.getConstructor(new Class[0]).newInstance(new Object[0]);
        } catch (NoSuchMethodException unused) {
            return loadClass.newInstance();
        }
    }

    public static <T> T newCheckedInstanceOf(String str, Class<T> cls) throws ClassNotFoundException, NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        return cls.cast(newInstanceOf(str));
    }

    private static boolean isIgnoreTccl() {
        if (ignoreTCCL == null) {
            String stringProperty = PropertiesUtil.getProperties().getStringProperty(IGNORE_TCCL_PROPERTY, null);
            ignoreTCCL = Boolean.valueOf((stringProperty == null || Constants.CASEFIRST_FALSE.equalsIgnoreCase(stringProperty.trim())) ? false : true);
        }
        return ignoreTCCL.booleanValue();
    }

    public static Collection<URL> findResources(String str) {
        Collection<UrlResource> findUrlResources = findUrlResources(str);
        LinkedHashSet linkedHashSet = new LinkedHashSet(findUrlResources.size());
        for (UrlResource urlResource : findUrlResources) {
            linkedHashSet.add(urlResource.getUrl());
        }
        return linkedHashSet;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static Collection<UrlResource> findUrlResources(String str) {
        ClassLoader[] classLoaderArr = {getThreadContextClassLoader(), LoaderUtil.class.getClassLoader(), ClassLoader.getSystemClassLoader()};
        LinkedHashSet linkedHashSet = new LinkedHashSet();
        for (int r3 = 0; r3 < 3; r3++) {
            ClassLoader classLoader = classLoaderArr[r3];
            if (classLoader != null) {
                try {
                    Enumeration<URL> resources = classLoader.getResources(str);
                    while (resources.hasMoreElements()) {
                        linkedHashSet.add(new UrlResource(classLoader, resources.nextElement()));
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return linkedHashSet;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes5.dex */
    public static class UrlResource {
        private final ClassLoader classLoader;
        private final URL url;

        public UrlResource(ClassLoader classLoader, URL url) {
            this.classLoader = classLoader;
            this.url = url;
        }

        public ClassLoader getClassLoader() {
            return this.classLoader;
        }

        public URL getUrl() {
            return this.url;
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj == null || getClass() != obj.getClass()) {
                return false;
            }
            UrlResource urlResource = (UrlResource) obj;
            ClassLoader classLoader = this.classLoader;
            if (classLoader == null ? urlResource.classLoader == null : classLoader.equals(urlResource.classLoader)) {
                URL url = this.url;
                URL url2 = urlResource.url;
                return url == null ? url2 == null : url.equals(url2);
            }
            return false;
        }

        public int hashCode() {
            ClassLoader classLoader = this.classLoader;
            int hashCode = (classLoader != null ? classLoader.hashCode() : 0) * 31;
            URL url = this.url;
            return hashCode + (url != null ? url.hashCode() : 0);
        }
    }
}
