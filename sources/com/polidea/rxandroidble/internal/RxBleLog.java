package com.polidea.rxandroidble.internal;

import android.util.Log;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/* loaded from: classes3.dex */
public class RxBleLog {
    public static final int DEBUG = 3;
    public static final int ERROR = 6;
    public static final int INFO = 4;
    public static final int NONE = Integer.MAX_VALUE;
    public static final int VERBOSE = 2;
    public static final int WARN = 5;
    private static int logLevel;
    private static Logger logcatLogger;
    private static Logger logger;
    private static final Pattern ANONYMOUS_CLASS = Pattern.compile("\\$\\d+$");
    private static final ThreadLocal<String> NEXT_TAG = new ThreadLocal<>();

    @Retention(RetentionPolicy.SOURCE)
    /* loaded from: classes3.dex */
    public @interface LogLevel {
    }

    /* loaded from: classes3.dex */
    public interface Logger {
        void log(int r1, String str, String str2);
    }

    static {
        Logger logger2 = new Logger() { // from class: com.polidea.rxandroidble.internal.RxBleLog.1
            @Override // com.polidea.rxandroidble.internal.RxBleLog.Logger
            public void log(int r1, String str, String str2) {
                Log.println(r1, str, str2);
            }
        };
        logcatLogger = logger2;
        logLevel = Integer.MAX_VALUE;
        logger = logger2;
    }

    private RxBleLog() {
    }

    public static void setLogger(Logger logger2) {
        if (logger2 == null) {
            logger = logcatLogger;
        } else {
            logger = logger2;
        }
    }

    public static void setLogLevel(int r0) {
        logLevel = r0;
    }

    private static String createTag() {
        ThreadLocal<String> threadLocal = NEXT_TAG;
        String str = threadLocal.get();
        if (str != null) {
            threadLocal.remove();
            return str;
        }
        StackTraceElement[] stackTrace = new Throwable().getStackTrace();
        if (stackTrace.length < 5) {
            throw new IllegalStateException("Synthetic stacktrace didn't have enough elements: are you using proguard?");
        }
        String className = stackTrace[4].getClassName();
        Matcher matcher = ANONYMOUS_CLASS.matcher(className);
        if (matcher.find()) {
            className = matcher.replaceAll("");
        }
        String replace = className.replace("Impl", "").replace("RxBle", "");
        return "RxBle#" + replace.substring(replace.lastIndexOf(46) + 1);
    }

    private static String formatString(String str, Object... objArr) {
        return objArr.length == 0 ? str : String.format(str, objArr);
    }

    /* renamed from: v */
    public static void m237v(String str, Object... objArr) {
        throwShade(2, null, str, objArr);
    }

    /* renamed from: v */
    public static void m236v(Throwable th, String str, Object... objArr) {
        throwShade(2, th, str, objArr);
    }

    /* renamed from: d */
    public static void m243d(String str, Object... objArr) {
        throwShade(3, null, str, objArr);
    }

    /* renamed from: d */
    public static void m242d(Throwable th, String str, Object... objArr) {
        throwShade(3, th, str, objArr);
    }

    /* renamed from: i */
    public static void m239i(String str, Object... objArr) {
        throwShade(4, null, str, objArr);
    }

    /* renamed from: i */
    public static void m238i(Throwable th, String str, Object... objArr) {
        throwShade(4, th, str, objArr);
    }

    /* renamed from: w */
    public static void m235w(String str, Object... objArr) {
        throwShade(5, null, str, objArr);
    }

    /* renamed from: w */
    public static void m234w(Throwable th, String str, Object... objArr) {
        throwShade(5, th, str, objArr);
    }

    /* renamed from: e */
    public static void m241e(String str, Object... objArr) {
        throwShade(6, null, str, objArr);
    }

    /* renamed from: e */
    public static void m240e(Throwable th, String str, Object... objArr) {
        throwShade(6, th, str, objArr);
    }

    private static void throwShade(int r1, Throwable th, String str, Object... objArr) {
        if (r1 < logLevel) {
            return;
        }
        String formatString = formatString(str, objArr);
        if (formatString == null || formatString.length() == 0) {
            if (th == null) {
                return;
            }
            formatString = Log.getStackTraceString(th);
        } else if (th != null) {
            formatString = formatString + "\n" + Log.getStackTraceString(th);
        }
        println(r1, createTag(), formatString);
    }

    private static void println(int r4, String str, String str2) {
        if (str2.length() < 4000) {
            logger.log(r4, str, str2);
            return;
        }
        for (String str3 : str2.split("\n")) {
            logger.log(r4, str, str3);
        }
    }

    public static boolean isAtLeast(int r1) {
        return logLevel <= r1;
    }
}
