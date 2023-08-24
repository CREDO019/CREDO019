package expo.modules.core.logging;

import android.content.Context;
import com.google.android.exoplayer2.source.rtsp.SessionDescription;
import com.onesignal.OneSignalDbContract;
import java.util.ArrayList;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: Logger.kt */
@Metadata(m184d1 = {"\u0000F\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0010\u0003\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u00002\u00020\u0001B!\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007¢\u0006\u0002\u0010\bJ\u000e\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u0003J\u001a\u0010\u0011\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u00032\n\b\u0002\u0010\u0012\u001a\u0004\u0018\u00010\u0013J\u001a\u0010\u0014\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u00032\n\b\u0002\u0010\u0012\u001a\u0004\u0018\u00010\u0013J\u000e\u0010\u0015\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u0003J$\u0010\u0016\u001a\u00020\u000f2\u0006\u0010\u0017\u001a\u00020\u00182\u0006\u0010\u0010\u001a\u00020\u00032\n\b\u0002\u0010\u0012\u001a\u0004\u0018\u00010\u0013H\u0002J\u000e\u0010\u0019\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u0003J\u001a\u0010\u001a\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u00032\n\b\u0002\u0010\u0012\u001a\u0004\u0018\u00010\u0013R\u0014\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u000b0\nX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\rX\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u001b"}, m183d2 = {"Lexpo/modules/core/logging/Logger;", "", "category", "", "context", "Landroid/content/Context;", "options", "Lexpo/modules/core/logging/LoggerOptions;", "(Ljava/lang/String;Landroid/content/Context;Lexpo/modules/core/logging/LoggerOptions;)V", "handlers", "", "Lexpo/modules/core/logging/LogHandler;", "minOSLevel", "", "debug", "", OneSignalDbContract.NotificationTable.COLUMN_NAME_MESSAGE, "error", "cause", "", "fatal", "info", "log", SessionDescription.ATTR_TYPE, "Lexpo/modules/core/logging/LogType;", "trace", "warn", "expo-modules-core_release"}, m182k = 1, m181mv = {1, 6, 0}, m179xi = 48)
/* loaded from: classes4.dex */
public final class Logger {
    private final List<LogHandler> handlers;
    private final int minOSLevel;

    public Logger(String category, Context context, LoggerOptions options) {
        Intrinsics.checkNotNullParameter(category, "category");
        Intrinsics.checkNotNullParameter(options, "options");
        ArrayList arrayList = new ArrayList();
        if (options.contains(LoggerOptions.Companion.getLogToOS())) {
            arrayList.add(new OSLogHandler(category));
        }
        if (options.contains(LoggerOptions.Companion.getLogToFile())) {
            if (context != null) {
                arrayList.add(new PersistentFileLogHandler(category, context));
            } else {
                throw new IllegalArgumentException("You have to provide the `Context` to create a file logger".toString());
            }
        }
        this.handlers = CollectionsKt.toList(arrayList);
        this.minOSLevel = 4;
    }

    public /* synthetic */ Logger(String str, Context context, LoggerOptions loggerOptions, int r4, DefaultConstructorMarker defaultConstructorMarker) {
        this(str, (r4 & 2) != 0 ? null : context, loggerOptions);
    }

    public final void trace(String message) {
        Intrinsics.checkNotNullParameter(message, "message");
        log$default(this, LogType.Trace, message, null, 4, null);
    }

    public final void debug(String message) {
        Intrinsics.checkNotNullParameter(message, "message");
        log$default(this, LogType.Debug, message, null, 4, null);
    }

    public final void info(String message) {
        Intrinsics.checkNotNullParameter(message, "message");
        log$default(this, LogType.Info, message, null, 4, null);
    }

    public static /* synthetic */ void warn$default(Logger logger, String str, Throwable th, int r3, Object obj) {
        if ((r3 & 2) != 0) {
            th = null;
        }
        logger.warn(str, th);
    }

    public final void warn(String message, Throwable th) {
        Intrinsics.checkNotNullParameter(message, "message");
        log(LogType.Warn, message, th);
    }

    public static /* synthetic */ void error$default(Logger logger, String str, Throwable th, int r3, Object obj) {
        if ((r3 & 2) != 0) {
            th = null;
        }
        logger.error(str, th);
    }

    public final void error(String message, Throwable th) {
        Intrinsics.checkNotNullParameter(message, "message");
        log(LogType.Error, message, th);
    }

    public static /* synthetic */ void fatal$default(Logger logger, String str, Throwable th, int r3, Object obj) {
        if ((r3 & 2) != 0) {
            th = null;
        }
        logger.fatal(str, th);
    }

    public final void fatal(String message, Throwable th) {
        Intrinsics.checkNotNullParameter(message, "message");
        log(LogType.Fatal, message, th);
    }

    static /* synthetic */ void log$default(Logger logger, LogType logType, String str, Throwable th, int r4, Object obj) {
        if ((r4 & 4) != 0) {
            th = null;
        }
        logger.log(logType, str, th);
    }

    private final void log(LogType logType, String str, Throwable th) {
        if (LogType.Companion.toOSLogType(logType) >= this.minOSLevel) {
            for (LogHandler logHandler : this.handlers) {
                logHandler.log$expo_modules_core_release(logType, str, th);
            }
        }
    }
}
