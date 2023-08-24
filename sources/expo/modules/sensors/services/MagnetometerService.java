package expo.modules.sensors.services;

import android.content.Context;
import expo.modules.core.interfaces.InternalModule;
import expo.modules.interfaces.sensors.services.MagnetometerServiceInterface;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;

/* compiled from: MagnetometerService.kt */
@Metadata(m184d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u00012\u00020\u00022\u00020\u0003B\u000f\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0005¢\u0006\u0002\u0010\u0006J\u0012\u0010\u000b\u001a\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030\r0\fH\u0016R\u0014\u0010\u0007\u001a\u00020\bX\u0096D¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\n¨\u0006\u000e"}, m183d2 = {"Lexpo/modules/sensors/services/MagnetometerService;", "Lexpo/modules/sensors/services/SubscribableSensorService;", "Lexpo/modules/core/interfaces/InternalModule;", "Lexpo/modules/interfaces/sensors/services/MagnetometerServiceInterface;", "reactContext", "Landroid/content/Context;", "(Landroid/content/Context;)V", "sensorType", "", "getSensorType", "()I", "getExportedInterfaces", "", "Ljava/lang/Class;", "expo-sensors_release"}, m182k = 1, m181mv = {1, 6, 0}, m179xi = 48)
/* loaded from: classes4.dex */
public final class MagnetometerService extends SubscribableSensorService implements InternalModule, MagnetometerServiceInterface {
    private final int sensorType;

    public MagnetometerService(Context context) {
        super(context);
        this.sensorType = 2;
    }

    @Override // expo.modules.sensors.services.BaseSensorService
    public int getSensorType() {
        return this.sensorType;
    }

    @Override // expo.modules.core.interfaces.InternalModule
    public List<Class<?>> getExportedInterfaces() {
        return CollectionsKt.listOf(MagnetometerServiceInterface.class);
    }
}
