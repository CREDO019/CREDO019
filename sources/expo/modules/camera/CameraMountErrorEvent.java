package expo.modules.camera;

import com.onesignal.OneSignalDbContract;
import expo.modules.kotlin.records.Field;
import expo.modules.kotlin.records.Record;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: Events.kt */
@Metadata(m184d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\b\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\t\u0010\t\u001a\u00020\u0003HÆ\u0003J\u0013\u0010\n\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\u000b\u001a\u00020\f2\b\u0010\r\u001a\u0004\u0018\u00010\u000eHÖ\u0003J\t\u0010\u000f\u001a\u00020\u0010HÖ\u0001J\t\u0010\u0011\u001a\u00020\u0003HÖ\u0001R\u001c\u0010\u0002\u001a\u00020\u00038\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b\u0005\u0010\u0006\u001a\u0004\b\u0007\u0010\b¨\u0006\u0012"}, m183d2 = {"Lexpo/modules/camera/CameraMountErrorEvent;", "Lexpo/modules/kotlin/records/Record;", OneSignalDbContract.NotificationTable.COLUMN_NAME_MESSAGE, "", "(Ljava/lang/String;)V", "getMessage$annotations", "()V", "getMessage", "()Ljava/lang/String;", "component1", "copy", "equals", "", "other", "", "hashCode", "", "toString", "expo-camera_release"}, m182k = 1, m181mv = {1, 6, 0}, m179xi = 48)
/* loaded from: classes4.dex */
public final class CameraMountErrorEvent implements Record {
    private final String message;

    public static /* synthetic */ CameraMountErrorEvent copy$default(CameraMountErrorEvent cameraMountErrorEvent, String str, int r2, Object obj) {
        if ((r2 & 1) != 0) {
            str = cameraMountErrorEvent.message;
        }
        return cameraMountErrorEvent.copy(str);
    }

    @Field
    public static /* synthetic */ void getMessage$annotations() {
    }

    public final String component1() {
        return this.message;
    }

    public final CameraMountErrorEvent copy(String message) {
        Intrinsics.checkNotNullParameter(message, "message");
        return new CameraMountErrorEvent(message);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        return (obj instanceof CameraMountErrorEvent) && Intrinsics.areEqual(this.message, ((CameraMountErrorEvent) obj).message);
    }

    public int hashCode() {
        return this.message.hashCode();
    }

    public String toString() {
        String str = this.message;
        return "CameraMountErrorEvent(message=" + str + ")";
    }

    public CameraMountErrorEvent(String message) {
        Intrinsics.checkNotNullParameter(message, "message");
        this.message = message;
    }

    public final String getMessage() {
        return this.message;
    }
}
