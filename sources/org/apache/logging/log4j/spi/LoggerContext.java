package org.apache.logging.log4j.spi;

import org.apache.logging.log4j.message.MessageFactory;

/* loaded from: classes5.dex */
public interface LoggerContext {
    Object getExternalContext();

    ExtendedLogger getLogger(String str);

    ExtendedLogger getLogger(String str, MessageFactory messageFactory);

    boolean hasLogger(String str);
}
