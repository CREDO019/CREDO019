package expo.modules.updates.logging;

import android.content.Context;
import com.onesignal.OneSignalDbContract;
import expo.modules.core.logging.LogType;
import expo.modules.core.logging.Logger;
import expo.modules.core.logging.LoggerOptions;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.ArraysKt;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: UpdatesLogger.kt */
@Metadata(m184d1 = {"\u0000<\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\u0018\u0000 \u00192\u00020\u0001:\u0001\u0019B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u0018\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\n2\b\b\u0002\u0010\u000b\u001a\u00020\fJ,\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\n2\b\b\u0002\u0010\u000b\u001a\u00020\f2\b\u0010\r\u001a\u0004\u0018\u00010\n2\b\u0010\u000e\u001a\u0004\u0018\u00010\nJ$\u0010\u000f\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\n2\b\b\u0002\u0010\u000b\u001a\u00020\f2\n\b\u0002\u0010\u0010\u001a\u0004\u0018\u00010\u0011J8\u0010\u000f\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\n2\b\b\u0002\u0010\u000b\u001a\u00020\f2\b\u0010\r\u001a\u0004\u0018\u00010\n2\b\u0010\u000e\u001a\u0004\u0018\u00010\n2\n\b\u0002\u0010\u0010\u001a\u0004\u0018\u00010\u0011J$\u0010\u0012\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\n2\b\b\u0002\u0010\u000b\u001a\u00020\f2\n\b\u0002\u0010\u0010\u001a\u0004\u0018\u00010\u0011J8\u0010\u0012\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\n2\b\b\u0002\u0010\u000b\u001a\u00020\f2\b\u0010\r\u001a\u0004\u0018\u00010\n2\b\u0010\u000e\u001a\u0004\u0018\u00010\n2\n\b\u0002\u0010\u0010\u001a\u0004\u0018\u00010\u0011J\u0018\u0010\u0013\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\n2\b\b\u0002\u0010\u000b\u001a\u00020\fJ,\u0010\u0013\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\n2\b\b\u0002\u0010\u000b\u001a\u00020\f2\b\u0010\r\u001a\u0004\u0018\u00010\n2\b\u0010\u000e\u001a\u0004\u0018\u00010\nJ@\u0010\u0014\u001a\u00020\n2\u0006\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010\u0015\u001a\u00020\u00162\b\u0010\r\u001a\u0004\u0018\u00010\n2\b\u0010\u000e\u001a\u0004\u0018\u00010\n2\n\b\u0002\u0010\u0010\u001a\u0004\u0018\u00010\u0011H\u0002J\u0018\u0010\u0017\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\n2\b\b\u0002\u0010\u000b\u001a\u00020\fJ,\u0010\u0017\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\n2\b\b\u0002\u0010\u000b\u001a\u00020\f2\b\u0010\r\u001a\u0004\u0018\u00010\n2\b\u0010\u000e\u001a\u0004\u0018\u00010\nJ\u0018\u0010\u0018\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\n2\b\b\u0002\u0010\u000b\u001a\u00020\fJ,\u0010\u0018\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\n2\b\b\u0002\u0010\u000b\u001a\u00020\f2\b\u0010\r\u001a\u0004\u0018\u00010\n2\b\u0010\u000e\u001a\u0004\u0018\u00010\nR\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u001a"}, m183d2 = {"Lexpo/modules/updates/logging/UpdatesLogger;", "", "context", "Landroid/content/Context;", "(Landroid/content/Context;)V", "logger", "Lexpo/modules/core/logging/Logger;", "debug", "", OneSignalDbContract.NotificationTable.COLUMN_NAME_MESSAGE, "", "code", "Lexpo/modules/updates/logging/UpdatesErrorCode;", "updateId", "assetId", "error", "exception", "Ljava/lang/Exception;", "fatal", "info", "logEntryString", "level", "Lexpo/modules/core/logging/LogType;", "trace", "warn", "Companion", "expo-updates_release"}, m182k = 1, m181mv = {1, 6, 0}, m179xi = 48)
/* loaded from: classes5.dex */
public final class UpdatesLogger {
    public static final Companion Companion = new Companion(null);
    public static final String EXPO_UPDATES_LOGGING_TAG = "dev.expo.updates";
    public static final int MAX_FRAMES_IN_STACKTRACE = 20;
    private final Logger logger;

    /* compiled from: UpdatesLogger.kt */
    @Metadata(m182k = 3, m181mv = {1, 6, 0}, m179xi = 48)
    /* loaded from: classes5.dex */
    public /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] r0 = new int[LogType.values().length];
            r0[LogType.Error.ordinal()] = 1;
            r0[LogType.Fatal.ordinal()] = 2;
            $EnumSwitchMapping$0 = r0;
        }
    }

    public UpdatesLogger(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        this.logger = new Logger(EXPO_UPDATES_LOGGING_TAG, context, LoggerOptions.Companion.union(CollectionsKt.listOf((Object[]) new LoggerOptions[]{LoggerOptions.Companion.getLogToOS(), LoggerOptions.Companion.getLogToFile()})));
    }

    public static /* synthetic */ void trace$default(UpdatesLogger updatesLogger, String str, UpdatesErrorCode updatesErrorCode, int r3, Object obj) {
        if ((r3 & 2) != 0) {
            updatesErrorCode = UpdatesErrorCode.None;
        }
        updatesLogger.trace(str, updatesErrorCode);
    }

    public final void trace(String message, UpdatesErrorCode code) {
        Intrinsics.checkNotNullParameter(message, "message");
        Intrinsics.checkNotNullParameter(code, "code");
        trace(message, code, null, null);
    }

    public static /* synthetic */ void trace$default(UpdatesLogger updatesLogger, String str, UpdatesErrorCode updatesErrorCode, String str2, String str3, int r5, Object obj) {
        if ((r5 & 2) != 0) {
            updatesErrorCode = UpdatesErrorCode.None;
        }
        updatesLogger.trace(str, updatesErrorCode, str2, str3);
    }

    public final void trace(String message, UpdatesErrorCode code, String str, String str2) {
        Intrinsics.checkNotNullParameter(message, "message");
        Intrinsics.checkNotNullParameter(code, "code");
        this.logger.trace(logEntryString$default(this, message, code, LogType.Trace, str, str2, null, 32, null));
    }

    public static /* synthetic */ void debug$default(UpdatesLogger updatesLogger, String str, UpdatesErrorCode updatesErrorCode, int r3, Object obj) {
        if ((r3 & 2) != 0) {
            updatesErrorCode = UpdatesErrorCode.None;
        }
        updatesLogger.debug(str, updatesErrorCode);
    }

    public final void debug(String message, UpdatesErrorCode code) {
        Intrinsics.checkNotNullParameter(message, "message");
        Intrinsics.checkNotNullParameter(code, "code");
        debug(message, code, null, null);
    }

    public static /* synthetic */ void debug$default(UpdatesLogger updatesLogger, String str, UpdatesErrorCode updatesErrorCode, String str2, String str3, int r5, Object obj) {
        if ((r5 & 2) != 0) {
            updatesErrorCode = UpdatesErrorCode.None;
        }
        updatesLogger.debug(str, updatesErrorCode, str2, str3);
    }

    public final void debug(String message, UpdatesErrorCode code, String str, String str2) {
        Intrinsics.checkNotNullParameter(message, "message");
        Intrinsics.checkNotNullParameter(code, "code");
        this.logger.debug(logEntryString$default(this, message, code, LogType.Debug, str, str2, null, 32, null));
    }

    public static /* synthetic */ void info$default(UpdatesLogger updatesLogger, String str, UpdatesErrorCode updatesErrorCode, int r3, Object obj) {
        if ((r3 & 2) != 0) {
            updatesErrorCode = UpdatesErrorCode.None;
        }
        updatesLogger.info(str, updatesErrorCode);
    }

    public final void info(String message, UpdatesErrorCode code) {
        Intrinsics.checkNotNullParameter(message, "message");
        Intrinsics.checkNotNullParameter(code, "code");
        info(message, code, null, null);
    }

    public static /* synthetic */ void info$default(UpdatesLogger updatesLogger, String str, UpdatesErrorCode updatesErrorCode, String str2, String str3, int r5, Object obj) {
        if ((r5 & 2) != 0) {
            updatesErrorCode = UpdatesErrorCode.None;
        }
        updatesLogger.info(str, updatesErrorCode, str2, str3);
    }

    public final void info(String message, UpdatesErrorCode code, String str, String str2) {
        Intrinsics.checkNotNullParameter(message, "message");
        Intrinsics.checkNotNullParameter(code, "code");
        this.logger.info(logEntryString$default(this, message, code, LogType.Info, str, str2, null, 32, null));
    }

    public static /* synthetic */ void warn$default(UpdatesLogger updatesLogger, String str, UpdatesErrorCode updatesErrorCode, int r3, Object obj) {
        if ((r3 & 2) != 0) {
            updatesErrorCode = UpdatesErrorCode.None;
        }
        updatesLogger.warn(str, updatesErrorCode);
    }

    public final void warn(String message, UpdatesErrorCode code) {
        Intrinsics.checkNotNullParameter(message, "message");
        Intrinsics.checkNotNullParameter(code, "code");
        warn(message, code, null, null);
    }

    public static /* synthetic */ void warn$default(UpdatesLogger updatesLogger, String str, UpdatesErrorCode updatesErrorCode, String str2, String str3, int r5, Object obj) {
        if ((r5 & 2) != 0) {
            updatesErrorCode = UpdatesErrorCode.None;
        }
        updatesLogger.warn(str, updatesErrorCode, str2, str3);
    }

    public final void warn(String message, UpdatesErrorCode code, String str, String str2) {
        Intrinsics.checkNotNullParameter(message, "message");
        Intrinsics.checkNotNullParameter(code, "code");
        Logger.warn$default(this.logger, logEntryString$default(this, message, code, LogType.Warn, str, str2, null, 32, null), null, 2, null);
    }

    public static /* synthetic */ void error$default(UpdatesLogger updatesLogger, String str, UpdatesErrorCode updatesErrorCode, Exception exc, int r4, Object obj) {
        if ((r4 & 2) != 0) {
            updatesErrorCode = UpdatesErrorCode.None;
        }
        if ((r4 & 4) != 0) {
            exc = null;
        }
        updatesLogger.error(str, updatesErrorCode, exc);
    }

    public final void error(String message, UpdatesErrorCode code, Exception exc) {
        Intrinsics.checkNotNullParameter(message, "message");
        Intrinsics.checkNotNullParameter(code, "code");
        error(message, code, null, null, exc);
    }

    public static /* synthetic */ void error$default(UpdatesLogger updatesLogger, String str, UpdatesErrorCode updatesErrorCode, String str2, String str3, Exception exc, int r12, Object obj) {
        if ((r12 & 2) != 0) {
            updatesErrorCode = UpdatesErrorCode.None;
        }
        UpdatesErrorCode updatesErrorCode2 = updatesErrorCode;
        if ((r12 & 16) != 0) {
            exc = null;
        }
        updatesLogger.error(str, updatesErrorCode2, str2, str3, exc);
    }

    public final void error(String message, UpdatesErrorCode code, String str, String str2, Exception exc) {
        Intrinsics.checkNotNullParameter(message, "message");
        Intrinsics.checkNotNullParameter(code, "code");
        Logger.error$default(this.logger, logEntryString(message, code, LogType.Error, str, str2, exc), null, 2, null);
    }

    public static /* synthetic */ void fatal$default(UpdatesLogger updatesLogger, String str, UpdatesErrorCode updatesErrorCode, Exception exc, int r4, Object obj) {
        if ((r4 & 2) != 0) {
            updatesErrorCode = UpdatesErrorCode.None;
        }
        if ((r4 & 4) != 0) {
            exc = null;
        }
        updatesLogger.fatal(str, updatesErrorCode, exc);
    }

    public final void fatal(String message, UpdatesErrorCode code, Exception exc) {
        Intrinsics.checkNotNullParameter(message, "message");
        Intrinsics.checkNotNullParameter(code, "code");
        fatal(message, code, null, null, exc);
    }

    public static /* synthetic */ void fatal$default(UpdatesLogger updatesLogger, String str, UpdatesErrorCode updatesErrorCode, String str2, String str3, Exception exc, int r12, Object obj) {
        if ((r12 & 2) != 0) {
            updatesErrorCode = UpdatesErrorCode.None;
        }
        UpdatesErrorCode updatesErrorCode2 = updatesErrorCode;
        if ((r12 & 16) != 0) {
            exc = null;
        }
        updatesLogger.fatal(str, updatesErrorCode2, str2, str3, exc);
    }

    public final void fatal(String message, UpdatesErrorCode code, String str, String str2, Exception exc) {
        Intrinsics.checkNotNullParameter(message, "message");
        Intrinsics.checkNotNullParameter(code, "code");
        Logger.fatal$default(this.logger, logEntryString(message, code, LogType.Fatal, str, str2, exc), null, 2, null);
    }

    static /* synthetic */ String logEntryString$default(UpdatesLogger updatesLogger, String str, UpdatesErrorCode updatesErrorCode, LogType logType, String str2, String str3, Exception exc, int r14, Object obj) {
        if ((r14 & 32) != 0) {
            exc = null;
        }
        return updatesLogger.logEntryString(str, updatesErrorCode, logType, str2, str3, exc);
    }

    private final String logEntryString(String str, UpdatesErrorCode updatesErrorCode, LogType logType, String str2, String str3, Exception exc) {
        ArrayList arrayList;
        ArrayList arrayList2;
        long time = new Date().getTime();
        Exception exc2 = exc instanceof Throwable ? exc : null;
        if (exc2 == null) {
            exc2 = new Throwable();
        }
        int r1 = WhenMappings.$EnumSwitchMapping$0[logType.ordinal()];
        if (r1 == 1) {
            StackTraceElement[] stackTrace = exc2.getStackTrace();
            Intrinsics.checkNotNullExpressionValue(stackTrace, "throwable.stackTrace");
            List<StackTraceElement> take = ArraysKt.take(stackTrace, 20);
            ArrayList arrayList3 = new ArrayList(CollectionsKt.collectionSizeOrDefault(take, 10));
            for (StackTraceElement stackTraceElement : take) {
                arrayList3.add(stackTraceElement.toString());
            }
            arrayList = arrayList3;
        } else if (r1 == 2) {
            StackTraceElement[] stackTrace2 = exc2.getStackTrace();
            Intrinsics.checkNotNullExpressionValue(stackTrace2, "throwable.stackTrace");
            List<StackTraceElement> take2 = ArraysKt.take(stackTrace2, 20);
            ArrayList arrayList4 = new ArrayList(CollectionsKt.collectionSizeOrDefault(take2, 10));
            for (StackTraceElement stackTraceElement2 : take2) {
                arrayList4.add(stackTraceElement2.toString());
            }
            arrayList = arrayList4;
        } else {
            arrayList2 = null;
            return new UpdatesLogEntry(time, str, updatesErrorCode.getCode(), logType.getType(), str2, str3, arrayList2).asString();
        }
        arrayList2 = arrayList;
        return new UpdatesLogEntry(time, str, updatesErrorCode.getCode(), logType.getType(), str2, str3, arrayList2).asString();
    }

    /* compiled from: UpdatesLogger.kt */
    @Metadata(m184d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0086T¢\u0006\u0002\n\u0000¨\u0006\u0007"}, m183d2 = {"Lexpo/modules/updates/logging/UpdatesLogger$Companion;", "", "()V", "EXPO_UPDATES_LOGGING_TAG", "", "MAX_FRAMES_IN_STACKTRACE", "", "expo-updates_release"}, m182k = 1, m181mv = {1, 6, 0}, m179xi = 48)
    /* loaded from: classes5.dex */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }
    }
}
