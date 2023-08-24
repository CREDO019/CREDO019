package expo.modules.core.logging;

import com.google.android.exoplayer2.source.rtsp.SessionDescription;
import com.onesignal.OneSignalDbContract;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: LogHandler.kt */
@Metadata(m184d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0003\n\u0002\b\u0002\b&\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J)\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\u00032\n\b\u0002\u0010\f\u001a\u0004\u0018\u00010\rH ¢\u0006\u0002\b\u000eR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006¨\u0006\u000f"}, m183d2 = {"Lexpo/modules/core/logging/LogHandler;", "", "category", "", "(Ljava/lang/String;)V", "getCategory", "()Ljava/lang/String;", "log", "", SessionDescription.ATTR_TYPE, "Lexpo/modules/core/logging/LogType;", OneSignalDbContract.NotificationTable.COLUMN_NAME_MESSAGE, "cause", "", "log$expo_modules_core_release", "expo-modules-core_release"}, m182k = 1, m181mv = {1, 6, 0}, m179xi = 48)
/* loaded from: classes4.dex */
public abstract class LogHandler {
    private final String category;

    public abstract void log$expo_modules_core_release(LogType logType, String str, Throwable th);

    public LogHandler(String category) {
        Intrinsics.checkNotNullParameter(category, "category");
        this.category = category;
    }

    public final String getCategory() {
        return this.category;
    }

    public static /* synthetic */ void log$expo_modules_core_release$default(LogHandler logHandler, LogType logType, String str, Throwable th, int r4, Object obj) {
        if (obj != null) {
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: log");
        }
        if ((r4 & 4) != 0) {
            th = null;
        }
        logHandler.log$expo_modules_core_release(logType, str, th);
    }
}
