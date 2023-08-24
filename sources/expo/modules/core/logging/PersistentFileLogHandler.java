package expo.modules.core.logging;

import android.content.Context;
import com.google.android.exoplayer2.source.rtsp.SessionDescription;
import com.onesignal.OneSignalDbContract;
import kotlin.Exceptions;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: PersistentFileLogHandler.kt */
@Metadata(m184d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0003\n\u0002\b\u0002\b\u0000\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J'\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u00032\b\u0010\u000e\u001a\u0004\u0018\u00010\u000fH\u0010¢\u0006\u0002\b\u0010R\u000e\u0010\u0007\u001a\u00020\bX\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0011"}, m183d2 = {"Lexpo/modules/core/logging/PersistentFileLogHandler;", "Lexpo/modules/core/logging/LogHandler;", "category", "", "context", "Landroid/content/Context;", "(Ljava/lang/String;Landroid/content/Context;)V", "persistentFileLog", "Lexpo/modules/core/logging/PersistentFileLog;", "log", "", SessionDescription.ATTR_TYPE, "Lexpo/modules/core/logging/LogType;", OneSignalDbContract.NotificationTable.COLUMN_NAME_MESSAGE, "cause", "", "log$expo_modules_core_release", "expo-modules-core_release"}, m182k = 1, m181mv = {1, 6, 0}, m179xi = 48)
/* loaded from: classes4.dex */
public final class PersistentFileLogHandler extends LogHandler {
    private final PersistentFileLog persistentFileLog;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public PersistentFileLogHandler(String category, Context context) {
        super(category);
        Intrinsics.checkNotNullParameter(category, "category");
        Intrinsics.checkNotNullParameter(context, "context");
        this.persistentFileLog = new PersistentFileLog(category, context);
    }

    @Override // expo.modules.core.logging.LogHandler
    public void log$expo_modules_core_release(LogType type, String message, Throwable th) {
        Intrinsics.checkNotNullParameter(type, "type");
        Intrinsics.checkNotNullParameter(message, "message");
        PersistentFileLog.appendEntry$default(this.persistentFileLog, message, null, 2, null);
        if (th == null) {
            return;
        }
        PersistentFileLog persistentFileLog = this.persistentFileLog;
        String localizedMessage = th.getLocalizedMessage();
        String stackTraceToString = Exceptions.stackTraceToString(th);
        PersistentFileLog.appendEntry$default(persistentFileLog, localizedMessage + "\n" + stackTraceToString, null, 2, null);
    }
}
