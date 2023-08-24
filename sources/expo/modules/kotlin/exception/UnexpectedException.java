package expo.modules.kotlin.exception;

import com.onesignal.OneSignalDbContract;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: CodedException.kt */
@Metadata(m184d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0003\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0004\b\u0007\u0018\u00002\u00020\u0001B\u000f\b\u0016\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004B\u000f\b\u0016\u0012\u0006\u0010\u0005\u001a\u00020\u0006¢\u0006\u0002\u0010\u0007B\u001b\u0012\b\u0010\u0005\u001a\u0004\u0018\u00010\u0006\u0012\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\u0003¢\u0006\u0002\u0010\t¨\u0006\n"}, m183d2 = {"Lexpo/modules/kotlin/exception/UnexpectedException;", "Lexpo/modules/kotlin/exception/CodedException;", "throwable", "", "(Ljava/lang/Throwable;)V", OneSignalDbContract.NotificationTable.COLUMN_NAME_MESSAGE, "", "(Ljava/lang/String;)V", "cause", "(Ljava/lang/String;Ljava/lang/Throwable;)V", "expo-modules-core_release"}, m182k = 1, m181mv = {1, 6, 0}, m179xi = 48)
/* loaded from: classes4.dex */
public final class UnexpectedException extends CodedException {
    public /* synthetic */ UnexpectedException(String str, Throwable th, int r3, DefaultConstructorMarker defaultConstructorMarker) {
        this(str, (r3 & 2) != 0 ? null : th);
    }

    public UnexpectedException(String str, Throwable th) {
        super(str, th);
    }

    /* JADX WARN: 'thıs' call moved to the top of the method (can break code semantics) */
    public UnexpectedException(Throwable throwable) {
        this(throwable.toString(), throwable);
        Intrinsics.checkNotNullParameter(throwable, "throwable");
    }

    /* JADX WARN: 'thıs' call moved to the top of the method (can break code semantics) */
    public UnexpectedException(String message) {
        this(message, null);
        Intrinsics.checkNotNullParameter(message, "message");
    }
}
