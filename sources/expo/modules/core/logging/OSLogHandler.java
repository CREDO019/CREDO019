package expo.modules.core.logging;

import android.util.Log;
import com.google.android.exoplayer2.source.rtsp.SessionDescription;
import com.onesignal.OneSignalDbContract;
import java.io.PrintStream;
import kotlin.Exceptions;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: OSLogHandler.kt */
@Metadata(m184d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0003\n\u0002\b\u0002\b\u0000\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J'\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\u00032\b\u0010\n\u001a\u0004\u0018\u00010\u000bH\u0010¢\u0006\u0002\b\f¨\u0006\r"}, m183d2 = {"Lexpo/modules/core/logging/OSLogHandler;", "Lexpo/modules/core/logging/LogHandler;", "category", "", "(Ljava/lang/String;)V", "log", "", SessionDescription.ATTR_TYPE, "Lexpo/modules/core/logging/LogType;", OneSignalDbContract.NotificationTable.COLUMN_NAME_MESSAGE, "cause", "", "log$expo_modules_core_release", "expo-modules-core_release"}, m182k = 1, m181mv = {1, 6, 0}, m179xi = 48)
/* loaded from: classes4.dex */
public final class OSLogHandler extends LogHandler {
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public OSLogHandler(String category) {
        super(category);
        Intrinsics.checkNotNullParameter(category, "category");
    }

    @Override // expo.modules.core.logging.LogHandler
    public void log$expo_modules_core_release(LogType type, String message, Throwable th) {
        boolean z;
        Intrinsics.checkNotNullParameter(type, "type");
        Intrinsics.checkNotNullParameter(message, "message");
        z = OSLogHandlerKt.isAndroid;
        if (!z) {
            PrintStream printStream = System.out;
            String type2 = type.getType();
            String category = getCategory();
            printStream.println((Object) ("[" + type2 + "] " + category + "\t" + message));
            if (th == null) {
                return;
            }
            PrintStream printStream2 = System.out;
            String localizedMessage = th.getLocalizedMessage();
            String stackTraceToString = Exceptions.stackTraceToString(th);
            printStream2.println((Object) (localizedMessage + "\n" + stackTraceToString));
            return;
        }
        int oSLogType = LogType.Companion.toOSLogType(type);
        if (oSLogType == 3) {
            Log.d(getCategory(), message, th);
        } else if (oSLogType == 4) {
            Log.i(getCategory(), message, th);
        } else if (oSLogType == 5) {
            Log.w(getCategory(), message, th);
        } else if (oSLogType == 6) {
            Log.e(getCategory(), message, th);
        } else if (oSLogType != 7) {
        } else {
            Log.e(getCategory(), message, th);
        }
    }
}
