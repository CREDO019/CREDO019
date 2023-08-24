package org.apache.logging.log4j.util;

import java.io.IOException;
import java.net.URL;
import java.util.Collection;
import java.util.Enumeration;
import java.util.HashSet;
import java.util.Properties;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.spi.Provider;
import org.apache.logging.log4j.status.StatusLogger;
import org.apache.logging.log4j.util.LoaderUtil;

/* loaded from: classes5.dex */
public final class ProviderUtil {
    private static final String API_VERSION = "Log4jAPIVersion";
    private static volatile ProviderUtil INSTANCE = null;
    protected static final String PROVIDER_RESOURCE = "META-INF/log4j-provider.properties";
    private static final String[] COMPATIBLE_API_VERSIONS = {"2.0.0", "2.1.0"};
    private static final Logger LOGGER = StatusLogger.getLogger();
    protected static final Collection<Provider> PROVIDERS = new HashSet();
    protected static final Lock STARTUP_LOCK = new ReentrantLock();

    private ProviderUtil() {
        for (LoaderUtil.UrlResource urlResource : LoaderUtil.findUrlResources(PROVIDER_RESOURCE)) {
            loadProvider(urlResource.getUrl(), urlResource.getClassLoader());
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public static void loadProvider(URL url, ClassLoader classLoader) {
        try {
            Properties loadClose = PropertiesUtil.loadClose(url.openStream(), url);
            if (validVersion(loadClose.getProperty(API_VERSION))) {
                PROVIDERS.add(new Provider(loadClose, url, classLoader));
            }
        } catch (IOException e) {
            LOGGER.error("Unable to open {}", url, e);
        }
    }

    @Deprecated
    protected static void loadProviders(Enumeration<URL> enumeration, ClassLoader classLoader) {
        if (enumeration != null) {
            while (enumeration.hasMoreElements()) {
                loadProvider(enumeration.nextElement(), classLoader);
            }
        }
    }

    public static Iterable<Provider> getProviders() {
        lazyInit();
        return PROVIDERS;
    }

    public static boolean hasProviders() {
        lazyInit();
        return !PROVIDERS.isEmpty();
    }

    protected static void lazyInit() {
        Lock lock;
        try {
            if (INSTANCE == null) {
                try {
                    lock = STARTUP_LOCK;
                    lock.lockInterruptibly();
                    if (INSTANCE == null) {
                        INSTANCE = new ProviderUtil();
                    }
                } catch (InterruptedException e) {
                    LOGGER.fatal("Interrupted before Log4j Providers could be loaded.", (Throwable) e);
                    Thread.currentThread().interrupt();
                    lock = STARTUP_LOCK;
                }
                lock.unlock();
            }
        } catch (Throwable th) {
            STARTUP_LOCK.unlock();
            throw th;
        }
    }

    public static ClassLoader findClassLoader() {
        return LoaderUtil.getThreadContextClassLoader();
    }

    private static boolean validVersion(String str) {
        for (String str2 : COMPATIBLE_API_VERSIONS) {
            if (str.startsWith(str2)) {
                return true;
            }
        }
        return false;
    }
}
