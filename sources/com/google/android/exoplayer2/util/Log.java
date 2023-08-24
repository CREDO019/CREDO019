package com.google.android.exoplayer2.util;

import android.text.TextUtils;
import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.net.UnknownHostException;
import org.checkerframework.dataflow.qual.Pure;

/* loaded from: classes2.dex */
public final class Log {
    public static final int LOG_LEVEL_ALL = 0;
    public static final int LOG_LEVEL_ERROR = 3;
    public static final int LOG_LEVEL_INFO = 1;
    public static final int LOG_LEVEL_OFF = Integer.MAX_VALUE;
    public static final int LOG_LEVEL_WARNING = 2;
    private static int logLevel = 0;
    private static boolean logStackTraces = true;
    private static final Object lock = new Object();
    private static Logger logger = Logger.DEFAULT;

    @Target({ElementType.TYPE_USE})
    @Documented
    @Retention(RetentionPolicy.SOURCE)
    /* loaded from: classes2.dex */
    public @interface LogLevel {
    }

    /* loaded from: classes2.dex */
    public interface Logger {
        public static final Logger DEFAULT = new Logger() { // from class: com.google.android.exoplayer2.util.Log.Logger.1
            @Override // com.google.android.exoplayer2.util.Log.Logger
            /* renamed from: d */
            public void mo1130d(String str, String str2) {
                android.util.Log.d(str, str2);
            }

            @Override // com.google.android.exoplayer2.util.Log.Logger
            /* renamed from: i */
            public void mo1128i(String str, String str2) {
                android.util.Log.i(str, str2);
            }

            @Override // com.google.android.exoplayer2.util.Log.Logger
            /* renamed from: w */
            public void mo1127w(String str, String str2) {
                android.util.Log.w(str, str2);
            }

            @Override // com.google.android.exoplayer2.util.Log.Logger
            /* renamed from: e */
            public void mo1129e(String str, String str2) {
                android.util.Log.e(str, str2);
            }
        };

        /* renamed from: d */
        void mo1130d(String str, String str2);

        /* renamed from: e */
        void mo1129e(String str, String str2);

        /* renamed from: i */
        void mo1128i(String str, String str2);

        /* renamed from: w */
        void mo1127w(String str, String str2);
    }

    private Log() {
    }

    @Pure
    public static int getLogLevel() {
        int r1;
        synchronized (lock) {
            r1 = logLevel;
        }
        return r1;
    }

    public static void setLogLevel(int r1) {
        synchronized (lock) {
            logLevel = r1;
        }
    }

    public static void setLogStackTraces(boolean z) {
        synchronized (lock) {
            logStackTraces = z;
        }
    }

    public static void setLogger(Logger logger2) {
        synchronized (lock) {
            logger = logger2;
        }
    }

    @Pure
    /* renamed from: d */
    public static void m1138d(String str, String str2) {
        synchronized (lock) {
            if (logLevel == 0) {
                logger.mo1130d(str, str2);
            }
        }
    }

    @Pure
    /* renamed from: d */
    public static void m1137d(String str, String str2, Throwable th) {
        m1138d(str, appendThrowableString(str2, th));
    }

    @Pure
    /* renamed from: i */
    public static void m1134i(String str, String str2) {
        synchronized (lock) {
            if (logLevel <= 1) {
                logger.mo1128i(str, str2);
            }
        }
    }

    @Pure
    /* renamed from: i */
    public static void m1133i(String str, String str2, Throwable th) {
        m1134i(str, appendThrowableString(str2, th));
    }

    @Pure
    /* renamed from: w */
    public static void m1132w(String str, String str2) {
        synchronized (lock) {
            if (logLevel <= 2) {
                logger.mo1127w(str, str2);
            }
        }
    }

    @Pure
    /* renamed from: w */
    public static void m1131w(String str, String str2, Throwable th) {
        m1132w(str, appendThrowableString(str2, th));
    }

    @Pure
    /* renamed from: e */
    public static void m1136e(String str, String str2) {
        synchronized (lock) {
            if (logLevel <= 3) {
                logger.mo1129e(str, str2);
            }
        }
    }

    @Pure
    /* renamed from: e */
    public static void m1135e(String str, String str2, Throwable th) {
        m1136e(str, appendThrowableString(str2, th));
    }

    @Pure
    public static String getThrowableString(Throwable th) {
        synchronized (lock) {
            if (th == null) {
                return null;
            }
            if (isCausedByUnknownHostException(th)) {
                return "UnknownHostException (no network)";
            }
            if (!logStackTraces) {
                return th.getMessage();
            }
            return android.util.Log.getStackTraceString(th).trim().replace("\t", "    ");
        }
    }

    @Pure
    private static String appendThrowableString(String str, Throwable th) {
        String throwableString = getThrowableString(th);
        if (TextUtils.isEmpty(throwableString)) {
            return str;
        }
        return str + "\n  " + throwableString.replace("\n", "\n  ") + '\n';
    }

    @Pure
    private static boolean isCausedByUnknownHostException(Throwable th) {
        while (th != null) {
            if (th instanceof UnknownHostException) {
                return true;
            }
            th = th.getCause();
        }
        return false;
    }
}
