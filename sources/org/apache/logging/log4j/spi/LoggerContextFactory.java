package org.apache.logging.log4j.spi;

import java.net.URI;

/* loaded from: classes5.dex */
public interface LoggerContextFactory {
    LoggerContext getContext(String str, ClassLoader classLoader, Object obj, boolean z);

    LoggerContext getContext(String str, ClassLoader classLoader, Object obj, boolean z, URI r5, String str2);

    void removeContext(LoggerContext loggerContext);
}
