package expo.modules.camera;

import expo.modules.kotlin.exception.CodedException;
import kotlin.Metadata;

/* compiled from: CameraExceptions.kt */
@Metadata(m184d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\u0018\u00002\u00020\u0001:\u0001\u0003B\u0005¢\u0006\u0002\u0010\u0002¨\u0006\u0004"}, m183d2 = {"Lexpo/modules/camera/CameraExceptions;", "", "()V", "CameraIsNotRunning", "expo-camera_release"}, m182k = 1, m181mv = {1, 6, 0}, m179xi = 48)
/* loaded from: classes4.dex */
public final class CameraExceptions {

    /* compiled from: CameraExceptions.kt */
    @Metadata(m184d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002¨\u0006\u0003"}, m183d2 = {"Lexpo/modules/camera/CameraExceptions$CameraIsNotRunning;", "Lexpo/modules/kotlin/exception/CodedException;", "()V", "expo-camera_release"}, m182k = 1, m181mv = {1, 6, 0}, m179xi = 48)
    /* loaded from: classes4.dex */
    public static final class CameraIsNotRunning extends CodedException {
        public CameraIsNotRunning() {
            super("Camera is not running", null, 2, null);
        }
    }
}
