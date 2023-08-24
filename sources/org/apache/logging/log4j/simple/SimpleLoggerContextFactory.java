package org.apache.logging.log4j.simple;

import java.net.URI;
import org.apache.logging.log4j.spi.LoggerContext;
import org.apache.logging.log4j.spi.LoggerContextFactory;

/* loaded from: classes5.dex */
public class SimpleLoggerContextFactory implements LoggerContextFactory {
    private static LoggerContext context = new SimpleLoggerContext();

    @Override // org.apache.logging.log4j.spi.LoggerContextFactory
    public void removeContext(LoggerContext loggerContext) {
    }

    @Override // org.apache.logging.log4j.spi.LoggerContextFactory
    public LoggerContext getContext(String str, ClassLoader classLoader, Object obj, boolean z) {
        return context;
    }

    @Override // org.apache.logging.log4j.spi.LoggerContextFactory
    public LoggerContext getContext(String str, ClassLoader classLoader, Object obj, boolean z, URI r5, String str2) {
        return context;
    }
}
