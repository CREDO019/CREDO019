package org.apache.logging.log4j;

/* loaded from: classes5.dex */
public class LoggingException extends RuntimeException {
    private static final long serialVersionUID = 6366395965071580537L;

    public LoggingException(String str) {
        super(str);
    }

    public LoggingException(String str, Throwable th) {
        super(str, th);
    }

    public LoggingException(Throwable th) {
        super(th);
    }
}
