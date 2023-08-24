package org.apache.logging.log4j.spi;

import java.io.Serializable;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.Marker;
import org.apache.logging.log4j.MarkerManager;
import org.apache.logging.log4j.message.Message;
import org.apache.logging.log4j.message.MessageFactory;
import org.apache.logging.log4j.message.ParameterizedMessageFactory;
import org.apache.logging.log4j.message.StringFormattedMessage;
import org.apache.logging.log4j.status.StatusLogger;

/* loaded from: classes5.dex */
public abstract class AbstractLogger implements ExtendedLogger, Serializable {
    private static final String CATCHING = "catching";
    public static final Marker CATCHING_MARKER;
    public static final Class<? extends MessageFactory> DEFAULT_MESSAGE_FACTORY_CLASS;
    public static final Marker ENTRY_MARKER;
    public static final Marker EXCEPTION_MARKER;
    public static final Marker EXIT_MARKER;
    public static final Marker FLOW_MARKER;
    private static final String FQCN;
    private static final String THROWING = "throwing";
    public static final Marker THROWING_MARKER;
    private static final long serialVersionUID = 2;
    private final MessageFactory messageFactory;
    private final String name;

    static {
        Marker marker = MarkerManager.getMarker("FLOW");
        FLOW_MARKER = marker;
        ENTRY_MARKER = MarkerManager.getMarker("ENTRY").setParents(marker);
        EXIT_MARKER = MarkerManager.getMarker("EXIT").setParents(marker);
        Marker marker2 = MarkerManager.getMarker("EXCEPTION");
        EXCEPTION_MARKER = marker2;
        THROWING_MARKER = MarkerManager.getMarker("THROWING").setParents(marker2);
        CATCHING_MARKER = MarkerManager.getMarker("CATCHING").setParents(marker2);
        DEFAULT_MESSAGE_FACTORY_CLASS = ParameterizedMessageFactory.class;
        FQCN = AbstractLogger.class.getName();
    }

    public static void checkMessageFactory(ExtendedLogger extendedLogger, MessageFactory messageFactory) {
        String name = extendedLogger.getName();
        MessageFactory messageFactory2 = extendedLogger.getMessageFactory();
        if (messageFactory != null && !messageFactory2.equals(messageFactory)) {
            StatusLogger.getLogger().warn("The Logger {} was created with the message factory {} and is now requested with the message factory {}, which may create log events with unexpected formatting.", name, messageFactory2, messageFactory);
        } else if (messageFactory == null) {
            Class<?> cls = messageFactory2.getClass();
            Class<? extends MessageFactory> cls2 = DEFAULT_MESSAGE_FACTORY_CLASS;
            if (cls.equals(cls2)) {
                return;
            }
            StatusLogger.getLogger().warn("The Logger {} was created with the message factory {} and is now requested with a null message factory (defaults to {}), which may create log events with unexpected formatting.", name, messageFactory2, cls2.getName());
        }
    }

    public AbstractLogger() {
        this.name = getClass().getName();
        this.messageFactory = createDefaultMessageFactory();
    }

    public AbstractLogger(String str) {
        this.name = str;
        this.messageFactory = createDefaultMessageFactory();
    }

    public AbstractLogger(String str, MessageFactory messageFactory) {
        this.name = str;
        this.messageFactory = messageFactory == null ? createDefaultMessageFactory() : messageFactory;
    }

    @Override // org.apache.logging.log4j.Logger
    public void catching(Level level, Throwable th) {
        catching(FQCN, level, th);
    }

    protected void catching(String str, Level level, Throwable th) {
        Marker marker = CATCHING_MARKER;
        if (isEnabled(level, marker, (Object) null, (Throwable) null)) {
            logMessage(str, level, marker, catchingMsg(th), th);
        }
    }

    @Override // org.apache.logging.log4j.Logger
    public void catching(Throwable th) {
        Level level = Level.ERROR;
        Marker marker = CATCHING_MARKER;
        if (isEnabled(level, marker, (Object) null, (Throwable) null)) {
            logMessage(FQCN, Level.ERROR, marker, catchingMsg(th), th);
        }
    }

    protected Message catchingMsg(Throwable th) {
        return this.messageFactory.newMessage(CATCHING);
    }

    private MessageFactory createDefaultMessageFactory() {
        try {
            return DEFAULT_MESSAGE_FACTORY_CLASS.newInstance();
        } catch (IllegalAccessException e) {
            throw new IllegalStateException(e);
        } catch (InstantiationException e2) {
            throw new IllegalStateException(e2);
        }
    }

    @Override // org.apache.logging.log4j.Logger
    public void debug(Marker marker, Message message) {
        logIfEnabled(FQCN, Level.DEBUG, marker, message, (Throwable) null);
    }

    @Override // org.apache.logging.log4j.Logger
    public void debug(Marker marker, Message message, Throwable th) {
        logIfEnabled(FQCN, Level.DEBUG, marker, message, th);
    }

    @Override // org.apache.logging.log4j.Logger
    public void debug(Marker marker, Object obj) {
        logIfEnabled(FQCN, Level.DEBUG, marker, obj, (Throwable) null);
    }

    @Override // org.apache.logging.log4j.Logger
    public void debug(Marker marker, Object obj, Throwable th) {
        logIfEnabled(FQCN, Level.DEBUG, marker, obj, th);
    }

    @Override // org.apache.logging.log4j.Logger
    public void debug(Marker marker, String str) {
        logIfEnabled(FQCN, Level.DEBUG, marker, str, (Throwable) null);
    }

    @Override // org.apache.logging.log4j.Logger
    public void debug(Marker marker, String str, Object... objArr) {
        logIfEnabled(FQCN, Level.DEBUG, marker, str, objArr);
    }

    @Override // org.apache.logging.log4j.Logger
    public void debug(Marker marker, String str, Throwable th) {
        logIfEnabled(FQCN, Level.DEBUG, marker, str, th);
    }

    @Override // org.apache.logging.log4j.Logger
    public void debug(Message message) {
        logIfEnabled(FQCN, Level.DEBUG, (Marker) null, message, (Throwable) null);
    }

    @Override // org.apache.logging.log4j.Logger
    public void debug(Message message, Throwable th) {
        logIfEnabled(FQCN, Level.DEBUG, (Marker) null, message, th);
    }

    @Override // org.apache.logging.log4j.Logger
    public void debug(Object obj) {
        logIfEnabled(FQCN, Level.DEBUG, (Marker) null, obj, (Throwable) null);
    }

    @Override // org.apache.logging.log4j.Logger
    public void debug(Object obj, Throwable th) {
        logIfEnabled(FQCN, Level.DEBUG, (Marker) null, obj, th);
    }

    @Override // org.apache.logging.log4j.Logger
    public void debug(String str) {
        logIfEnabled(FQCN, Level.DEBUG, (Marker) null, str, (Throwable) null);
    }

    @Override // org.apache.logging.log4j.Logger
    public void debug(String str, Object... objArr) {
        logIfEnabled(FQCN, Level.DEBUG, (Marker) null, str, objArr);
    }

    @Override // org.apache.logging.log4j.Logger
    public void debug(String str, Throwable th) {
        logIfEnabled(FQCN, Level.DEBUG, (Marker) null, str, th);
    }

    @Override // org.apache.logging.log4j.Logger
    public void entry() {
        entry(FQCN, new Object[0]);
    }

    @Override // org.apache.logging.log4j.Logger
    public void entry(Object... objArr) {
        entry(FQCN, objArr);
    }

    protected void entry(String str, Object... objArr) {
        Level level = Level.TRACE;
        Marker marker = ENTRY_MARKER;
        if (isEnabled(level, marker, (Object) null, (Throwable) null)) {
            logIfEnabled(str, Level.TRACE, marker, entryMsg(objArr.length, objArr), (Throwable) null);
        }
    }

    protected Message entryMsg(int r5, Object... objArr) {
        if (r5 == 0) {
            return this.messageFactory.newMessage("entry");
        }
        StringBuilder sb = new StringBuilder("entry params(");
        int r2 = 0;
        for (Object obj : objArr) {
            if (obj != null) {
                sb.append(obj.toString());
            } else {
                sb.append("null");
            }
            r2++;
            if (r2 < objArr.length) {
                sb.append(", ");
            }
        }
        sb.append(')');
        return this.messageFactory.newMessage(sb.toString());
    }

    @Override // org.apache.logging.log4j.Logger
    public void error(Marker marker, Message message) {
        logIfEnabled(FQCN, Level.ERROR, marker, message, (Throwable) null);
    }

    @Override // org.apache.logging.log4j.Logger
    public void error(Marker marker, Message message, Throwable th) {
        logIfEnabled(FQCN, Level.ERROR, marker, message, th);
    }

    @Override // org.apache.logging.log4j.Logger
    public void error(Marker marker, Object obj) {
        logIfEnabled(FQCN, Level.ERROR, marker, obj, (Throwable) null);
    }

    @Override // org.apache.logging.log4j.Logger
    public void error(Marker marker, Object obj, Throwable th) {
        logIfEnabled(FQCN, Level.ERROR, marker, obj, th);
    }

    @Override // org.apache.logging.log4j.Logger
    public void error(Marker marker, String str) {
        logIfEnabled(FQCN, Level.ERROR, marker, str, (Throwable) null);
    }

    @Override // org.apache.logging.log4j.Logger
    public void error(Marker marker, String str, Object... objArr) {
        logIfEnabled(FQCN, Level.ERROR, marker, str, objArr);
    }

    @Override // org.apache.logging.log4j.Logger
    public void error(Marker marker, String str, Throwable th) {
        logIfEnabled(FQCN, Level.ERROR, marker, str, th);
    }

    @Override // org.apache.logging.log4j.Logger
    public void error(Message message) {
        logIfEnabled(FQCN, Level.ERROR, (Marker) null, message, (Throwable) null);
    }

    @Override // org.apache.logging.log4j.Logger
    public void error(Message message, Throwable th) {
        logIfEnabled(FQCN, Level.ERROR, (Marker) null, message, th);
    }

    @Override // org.apache.logging.log4j.Logger
    public void error(Object obj) {
        logIfEnabled(FQCN, Level.ERROR, (Marker) null, obj, (Throwable) null);
    }

    @Override // org.apache.logging.log4j.Logger
    public void error(Object obj, Throwable th) {
        logIfEnabled(FQCN, Level.ERROR, (Marker) null, obj, th);
    }

    @Override // org.apache.logging.log4j.Logger
    public void error(String str) {
        logIfEnabled(FQCN, Level.ERROR, (Marker) null, str, (Throwable) null);
    }

    @Override // org.apache.logging.log4j.Logger
    public void error(String str, Object... objArr) {
        logIfEnabled(FQCN, Level.ERROR, (Marker) null, str, objArr);
    }

    @Override // org.apache.logging.log4j.Logger
    public void error(String str, Throwable th) {
        logIfEnabled(FQCN, Level.ERROR, (Marker) null, str, th);
    }

    @Override // org.apache.logging.log4j.Logger
    public void exit() {
        exit(FQCN, null);
    }

    @Override // org.apache.logging.log4j.Logger
    public <R> R exit(R r) {
        return (R) exit(FQCN, r);
    }

    protected <R> R exit(String str, R r) {
        Level level = Level.TRACE;
        Marker marker = EXIT_MARKER;
        if (isEnabled(level, marker, (Object) null, (Throwable) null)) {
            logIfEnabled(str, Level.TRACE, marker, exitMsg(r), (Throwable) null);
        }
        return r;
    }

    protected Message exitMsg(Object obj) {
        if (obj == null) {
            return this.messageFactory.newMessage("exit");
        }
        MessageFactory messageFactory = this.messageFactory;
        return messageFactory.newMessage("exit with(" + obj + ')');
    }

    @Override // org.apache.logging.log4j.Logger
    public void fatal(Marker marker, Message message) {
        logIfEnabled(FQCN, Level.FATAL, marker, message, (Throwable) null);
    }

    @Override // org.apache.logging.log4j.Logger
    public void fatal(Marker marker, Message message, Throwable th) {
        logIfEnabled(FQCN, Level.FATAL, marker, message, th);
    }

    @Override // org.apache.logging.log4j.Logger
    public void fatal(Marker marker, Object obj) {
        logIfEnabled(FQCN, Level.FATAL, marker, obj, (Throwable) null);
    }

    @Override // org.apache.logging.log4j.Logger
    public void fatal(Marker marker, Object obj, Throwable th) {
        logIfEnabled(FQCN, Level.FATAL, marker, obj, th);
    }

    @Override // org.apache.logging.log4j.Logger
    public void fatal(Marker marker, String str) {
        logIfEnabled(FQCN, Level.FATAL, marker, str, (Throwable) null);
    }

    @Override // org.apache.logging.log4j.Logger
    public void fatal(Marker marker, String str, Object... objArr) {
        logIfEnabled(FQCN, Level.FATAL, marker, str, objArr);
    }

    @Override // org.apache.logging.log4j.Logger
    public void fatal(Marker marker, String str, Throwable th) {
        logIfEnabled(FQCN, Level.FATAL, marker, str, th);
    }

    @Override // org.apache.logging.log4j.Logger
    public void fatal(Message message) {
        logIfEnabled(FQCN, Level.FATAL, (Marker) null, message, (Throwable) null);
    }

    @Override // org.apache.logging.log4j.Logger
    public void fatal(Message message, Throwable th) {
        logIfEnabled(FQCN, Level.FATAL, (Marker) null, message, th);
    }

    @Override // org.apache.logging.log4j.Logger
    public void fatal(Object obj) {
        logIfEnabled(FQCN, Level.FATAL, (Marker) null, obj, (Throwable) null);
    }

    @Override // org.apache.logging.log4j.Logger
    public void fatal(Object obj, Throwable th) {
        logIfEnabled(FQCN, Level.FATAL, (Marker) null, obj, th);
    }

    @Override // org.apache.logging.log4j.Logger
    public void fatal(String str) {
        logIfEnabled(FQCN, Level.FATAL, (Marker) null, str, (Throwable) null);
    }

    @Override // org.apache.logging.log4j.Logger
    public void fatal(String str, Object... objArr) {
        logIfEnabled(FQCN, Level.FATAL, (Marker) null, str, objArr);
    }

    @Override // org.apache.logging.log4j.Logger
    public void fatal(String str, Throwable th) {
        logIfEnabled(FQCN, Level.FATAL, (Marker) null, str, th);
    }

    @Override // org.apache.logging.log4j.Logger
    public MessageFactory getMessageFactory() {
        return this.messageFactory;
    }

    @Override // org.apache.logging.log4j.Logger
    public String getName() {
        return this.name;
    }

    @Override // org.apache.logging.log4j.Logger
    public void info(Marker marker, Message message) {
        logIfEnabled(FQCN, Level.INFO, marker, message, (Throwable) null);
    }

    @Override // org.apache.logging.log4j.Logger
    public void info(Marker marker, Message message, Throwable th) {
        logIfEnabled(FQCN, Level.INFO, marker, message, th);
    }

    @Override // org.apache.logging.log4j.Logger
    public void info(Marker marker, Object obj) {
        logIfEnabled(FQCN, Level.INFO, marker, obj, (Throwable) null);
    }

    @Override // org.apache.logging.log4j.Logger
    public void info(Marker marker, Object obj, Throwable th) {
        logIfEnabled(FQCN, Level.INFO, marker, obj, th);
    }

    @Override // org.apache.logging.log4j.Logger
    public void info(Marker marker, String str) {
        logIfEnabled(FQCN, Level.INFO, marker, str, (Throwable) null);
    }

    @Override // org.apache.logging.log4j.Logger
    public void info(Marker marker, String str, Object... objArr) {
        logIfEnabled(FQCN, Level.INFO, marker, str, objArr);
    }

    @Override // org.apache.logging.log4j.Logger
    public void info(Marker marker, String str, Throwable th) {
        logIfEnabled(FQCN, Level.INFO, marker, str, th);
    }

    @Override // org.apache.logging.log4j.Logger
    public void info(Message message) {
        logIfEnabled(FQCN, Level.INFO, (Marker) null, message, (Throwable) null);
    }

    @Override // org.apache.logging.log4j.Logger
    public void info(Message message, Throwable th) {
        logIfEnabled(FQCN, Level.INFO, (Marker) null, message, th);
    }

    @Override // org.apache.logging.log4j.Logger
    public void info(Object obj) {
        logIfEnabled(FQCN, Level.INFO, (Marker) null, obj, (Throwable) null);
    }

    @Override // org.apache.logging.log4j.Logger
    public void info(Object obj, Throwable th) {
        logIfEnabled(FQCN, Level.INFO, (Marker) null, obj, th);
    }

    @Override // org.apache.logging.log4j.Logger
    public void info(String str) {
        logIfEnabled(FQCN, Level.INFO, (Marker) null, str, (Throwable) null);
    }

    @Override // org.apache.logging.log4j.Logger
    public void info(String str, Object... objArr) {
        logIfEnabled(FQCN, Level.INFO, (Marker) null, str, objArr);
    }

    @Override // org.apache.logging.log4j.Logger
    public void info(String str, Throwable th) {
        logIfEnabled(FQCN, Level.INFO, (Marker) null, str, th);
    }

    @Override // org.apache.logging.log4j.Logger
    public boolean isDebugEnabled() {
        return isEnabled(Level.DEBUG, null, null);
    }

    @Override // org.apache.logging.log4j.Logger
    public boolean isDebugEnabled(Marker marker) {
        return isEnabled(Level.DEBUG, marker, (Object) null, (Throwable) null);
    }

    @Override // org.apache.logging.log4j.Logger
    public boolean isEnabled(Level level) {
        return isEnabled(level, (Marker) null, (Object) null, (Throwable) null);
    }

    @Override // org.apache.logging.log4j.Logger
    public boolean isEnabled(Level level, Marker marker) {
        return isEnabled(level, marker, (Object) null, (Throwable) null);
    }

    @Override // org.apache.logging.log4j.Logger
    public boolean isErrorEnabled() {
        return isEnabled(Level.ERROR, (Marker) null, (Object) null, (Throwable) null);
    }

    @Override // org.apache.logging.log4j.Logger
    public boolean isErrorEnabled(Marker marker) {
        return isEnabled(Level.ERROR, marker, (Object) null, (Throwable) null);
    }

    @Override // org.apache.logging.log4j.Logger
    public boolean isFatalEnabled() {
        return isEnabled(Level.FATAL, (Marker) null, (Object) null, (Throwable) null);
    }

    @Override // org.apache.logging.log4j.Logger
    public boolean isFatalEnabled(Marker marker) {
        return isEnabled(Level.FATAL, marker, (Object) null, (Throwable) null);
    }

    @Override // org.apache.logging.log4j.Logger
    public boolean isInfoEnabled() {
        return isEnabled(Level.INFO, (Marker) null, (Object) null, (Throwable) null);
    }

    @Override // org.apache.logging.log4j.Logger
    public boolean isInfoEnabled(Marker marker) {
        return isEnabled(Level.INFO, marker, (Object) null, (Throwable) null);
    }

    @Override // org.apache.logging.log4j.Logger
    public boolean isTraceEnabled() {
        return isEnabled(Level.TRACE, (Marker) null, (Object) null, (Throwable) null);
    }

    @Override // org.apache.logging.log4j.Logger
    public boolean isTraceEnabled(Marker marker) {
        return isEnabled(Level.TRACE, marker, (Object) null, (Throwable) null);
    }

    @Override // org.apache.logging.log4j.Logger
    public boolean isWarnEnabled() {
        return isEnabled(Level.WARN, (Marker) null, (Object) null, (Throwable) null);
    }

    @Override // org.apache.logging.log4j.Logger
    public boolean isWarnEnabled(Marker marker) {
        return isEnabled(Level.WARN, marker, (Object) null, (Throwable) null);
    }

    @Override // org.apache.logging.log4j.Logger
    public void log(Level level, Marker marker, Message message) {
        logIfEnabled(FQCN, level, marker, message, (Throwable) null);
    }

    @Override // org.apache.logging.log4j.Logger
    public void log(Level level, Marker marker, Message message, Throwable th) {
        logIfEnabled(FQCN, level, marker, message, th);
    }

    @Override // org.apache.logging.log4j.Logger
    public void log(Level level, Marker marker, Object obj) {
        logIfEnabled(FQCN, level, marker, obj, (Throwable) null);
    }

    @Override // org.apache.logging.log4j.Logger
    public void log(Level level, Marker marker, Object obj, Throwable th) {
        if (isEnabled(level, marker, obj, th)) {
            logMessage(FQCN, level, marker, obj, th);
        }
    }

    @Override // org.apache.logging.log4j.Logger
    public void log(Level level, Marker marker, String str) {
        logIfEnabled(FQCN, level, marker, str, (Throwable) null);
    }

    @Override // org.apache.logging.log4j.Logger
    public void log(Level level, Marker marker, String str, Object... objArr) {
        logIfEnabled(FQCN, level, marker, str, objArr);
    }

    @Override // org.apache.logging.log4j.Logger
    public void log(Level level, Marker marker, String str, Throwable th) {
        logIfEnabled(FQCN, level, marker, str, th);
    }

    @Override // org.apache.logging.log4j.Logger
    public void log(Level level, Message message) {
        logIfEnabled(FQCN, level, (Marker) null, message, (Throwable) null);
    }

    @Override // org.apache.logging.log4j.Logger
    public void log(Level level, Message message, Throwable th) {
        logIfEnabled(FQCN, level, (Marker) null, message, th);
    }

    @Override // org.apache.logging.log4j.Logger
    public void log(Level level, Object obj) {
        logIfEnabled(FQCN, level, (Marker) null, obj, (Throwable) null);
    }

    @Override // org.apache.logging.log4j.Logger
    public void log(Level level, Object obj, Throwable th) {
        logIfEnabled(FQCN, level, (Marker) null, obj, th);
    }

    @Override // org.apache.logging.log4j.Logger
    public void log(Level level, String str) {
        logIfEnabled(FQCN, level, (Marker) null, str, (Throwable) null);
    }

    @Override // org.apache.logging.log4j.Logger
    public void log(Level level, String str, Object... objArr) {
        logIfEnabled(FQCN, level, (Marker) null, str, objArr);
    }

    @Override // org.apache.logging.log4j.Logger
    public void log(Level level, String str, Throwable th) {
        logIfEnabled(FQCN, level, (Marker) null, str, th);
    }

    @Override // org.apache.logging.log4j.spi.ExtendedLogger
    public void logIfEnabled(String str, Level level, Marker marker, Message message, Throwable th) {
        if (isEnabled(level, marker, message, th)) {
            logMessage(str, level, marker, message, th);
        }
    }

    @Override // org.apache.logging.log4j.spi.ExtendedLogger
    public void logIfEnabled(String str, Level level, Marker marker, Object obj, Throwable th) {
        if (isEnabled(level, marker, obj, th)) {
            logMessage(str, level, marker, obj, th);
        }
    }

    @Override // org.apache.logging.log4j.spi.ExtendedLogger
    public void logIfEnabled(String str, Level level, Marker marker, String str2) {
        if (isEnabled(level, marker, str2)) {
            logMessage(str, level, marker, str2);
        }
    }

    @Override // org.apache.logging.log4j.spi.ExtendedLogger
    public void logIfEnabled(String str, Level level, Marker marker, String str2, Object... objArr) {
        if (isEnabled(level, marker, str2, objArr)) {
            logMessage(str, level, marker, str2, objArr);
        }
    }

    @Override // org.apache.logging.log4j.spi.ExtendedLogger
    public void logIfEnabled(String str, Level level, Marker marker, String str2, Throwable th) {
        if (isEnabled(level, marker, str2, th)) {
            logMessage(str, level, marker, str2, th);
        }
    }

    protected void logMessage(String str, Level level, Marker marker, Object obj, Throwable th) {
        logMessage(str, level, marker, this.messageFactory.newMessage(obj), th);
    }

    protected void logMessage(String str, Level level, Marker marker, String str2, Throwable th) {
        logMessage(str, level, marker, this.messageFactory.newMessage(str2), th);
    }

    protected void logMessage(String str, Level level, Marker marker, String str2) {
        Message newMessage = this.messageFactory.newMessage(str2);
        logMessage(str, level, marker, newMessage, newMessage.getThrowable());
    }

    protected void logMessage(String str, Level level, Marker marker, String str2, Object... objArr) {
        Message newMessage = this.messageFactory.newMessage(str2, objArr);
        logMessage(str, level, marker, newMessage, newMessage.getThrowable());
    }

    @Override // org.apache.logging.log4j.Logger
    public void printf(Level level, Marker marker, String str, Object... objArr) {
        if (isEnabled(level, marker, str, objArr)) {
            StringFormattedMessage stringFormattedMessage = new StringFormattedMessage(str, objArr);
            logMessage(FQCN, level, marker, (Message) stringFormattedMessage, stringFormattedMessage.getThrowable());
        }
    }

    @Override // org.apache.logging.log4j.Logger
    public void printf(Level level, String str, Object... objArr) {
        if (isEnabled(level, (Marker) null, str, objArr)) {
            StringFormattedMessage stringFormattedMessage = new StringFormattedMessage(str, objArr);
            logMessage(FQCN, level, (Marker) null, (Message) stringFormattedMessage, stringFormattedMessage.getThrowable());
        }
    }

    @Override // org.apache.logging.log4j.Logger
    public <T extends Throwable> T throwing(T t) {
        return (T) throwing(FQCN, Level.ERROR, t);
    }

    @Override // org.apache.logging.log4j.Logger
    public <T extends Throwable> T throwing(Level level, T t) {
        return (T) throwing(FQCN, level, t);
    }

    protected <T extends Throwable> T throwing(String str, Level level, T t) {
        Marker marker = THROWING_MARKER;
        if (isEnabled(level, marker, (Object) null, (Throwable) null)) {
            logMessage(str, level, marker, throwingMsg(t), (Throwable) t);
        }
        return t;
    }

    protected Message throwingMsg(Throwable th) {
        return this.messageFactory.newMessage(THROWING);
    }

    @Override // org.apache.logging.log4j.Logger
    public void trace(Marker marker, Message message) {
        logIfEnabled(FQCN, Level.TRACE, marker, message, (Throwable) null);
    }

    @Override // org.apache.logging.log4j.Logger
    public void trace(Marker marker, Message message, Throwable th) {
        logIfEnabled(FQCN, Level.TRACE, marker, message, th);
    }

    @Override // org.apache.logging.log4j.Logger
    public void trace(Marker marker, Object obj) {
        logIfEnabled(FQCN, Level.TRACE, marker, obj, (Throwable) null);
    }

    @Override // org.apache.logging.log4j.Logger
    public void trace(Marker marker, Object obj, Throwable th) {
        logIfEnabled(FQCN, Level.TRACE, marker, obj, th);
    }

    @Override // org.apache.logging.log4j.Logger
    public void trace(Marker marker, String str) {
        logIfEnabled(FQCN, Level.TRACE, marker, str, (Throwable) null);
    }

    @Override // org.apache.logging.log4j.Logger
    public void trace(Marker marker, String str, Object... objArr) {
        logIfEnabled(FQCN, Level.TRACE, marker, str, objArr);
    }

    @Override // org.apache.logging.log4j.Logger
    public void trace(Marker marker, String str, Throwable th) {
        logIfEnabled(FQCN, Level.TRACE, marker, str, th);
    }

    @Override // org.apache.logging.log4j.Logger
    public void trace(Message message) {
        logIfEnabled(FQCN, Level.TRACE, (Marker) null, message, (Throwable) null);
    }

    @Override // org.apache.logging.log4j.Logger
    public void trace(Message message, Throwable th) {
        logIfEnabled(FQCN, Level.TRACE, (Marker) null, message, th);
    }

    @Override // org.apache.logging.log4j.Logger
    public void trace(Object obj) {
        logIfEnabled(FQCN, Level.TRACE, (Marker) null, obj, (Throwable) null);
    }

    @Override // org.apache.logging.log4j.Logger
    public void trace(Object obj, Throwable th) {
        logIfEnabled(FQCN, Level.TRACE, (Marker) null, obj, th);
    }

    @Override // org.apache.logging.log4j.Logger
    public void trace(String str) {
        logIfEnabled(FQCN, Level.TRACE, (Marker) null, str, (Throwable) null);
    }

    @Override // org.apache.logging.log4j.Logger
    public void trace(String str, Object... objArr) {
        logIfEnabled(FQCN, Level.TRACE, (Marker) null, str, objArr);
    }

    @Override // org.apache.logging.log4j.Logger
    public void trace(String str, Throwable th) {
        logIfEnabled(FQCN, Level.TRACE, (Marker) null, str, th);
    }

    @Override // org.apache.logging.log4j.Logger
    public void warn(Marker marker, Message message) {
        logIfEnabled(FQCN, Level.WARN, marker, message, (Throwable) null);
    }

    @Override // org.apache.logging.log4j.Logger
    public void warn(Marker marker, Message message, Throwable th) {
        logIfEnabled(FQCN, Level.WARN, marker, message, th);
    }

    @Override // org.apache.logging.log4j.Logger
    public void warn(Marker marker, Object obj) {
        logIfEnabled(FQCN, Level.WARN, marker, obj, (Throwable) null);
    }

    @Override // org.apache.logging.log4j.Logger
    public void warn(Marker marker, Object obj, Throwable th) {
        logIfEnabled(FQCN, Level.WARN, marker, obj, th);
    }

    @Override // org.apache.logging.log4j.Logger
    public void warn(Marker marker, String str) {
        logIfEnabled(FQCN, Level.WARN, marker, str, (Throwable) null);
    }

    @Override // org.apache.logging.log4j.Logger
    public void warn(Marker marker, String str, Object... objArr) {
        logIfEnabled(FQCN, Level.WARN, marker, str, objArr);
    }

    @Override // org.apache.logging.log4j.Logger
    public void warn(Marker marker, String str, Throwable th) {
        logIfEnabled(FQCN, Level.WARN, marker, str, th);
    }

    @Override // org.apache.logging.log4j.Logger
    public void warn(Message message) {
        logIfEnabled(FQCN, Level.WARN, (Marker) null, message, (Throwable) null);
    }

    @Override // org.apache.logging.log4j.Logger
    public void warn(Message message, Throwable th) {
        logIfEnabled(FQCN, Level.WARN, (Marker) null, message, th);
    }

    @Override // org.apache.logging.log4j.Logger
    public void warn(Object obj) {
        logIfEnabled(FQCN, Level.WARN, (Marker) null, obj, (Throwable) null);
    }

    @Override // org.apache.logging.log4j.Logger
    public void warn(Object obj, Throwable th) {
        logIfEnabled(FQCN, Level.WARN, (Marker) null, obj, th);
    }

    @Override // org.apache.logging.log4j.Logger
    public void warn(String str) {
        logIfEnabled(FQCN, Level.WARN, (Marker) null, str, (Throwable) null);
    }

    @Override // org.apache.logging.log4j.Logger
    public void warn(String str, Object... objArr) {
        logIfEnabled(FQCN, Level.WARN, (Marker) null, str, objArr);
    }

    @Override // org.apache.logging.log4j.Logger
    public void warn(String str, Throwable th) {
        logIfEnabled(FQCN, Level.WARN, (Marker) null, str, th);
    }
}
