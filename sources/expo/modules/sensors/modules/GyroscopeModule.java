package expo.modules.sensors.modules;

import android.content.Context;
import android.hardware.SensorEvent;
import android.hardware.SensorManager;
import android.os.Bundle;
import com.facebook.react.bridge.BaseJavaModule;
import expo.modules.core.Promise;
import expo.modules.core.interfaces.ExpoMethod;
import expo.modules.interfaces.sensors.SensorServiceInterface;
import expo.modules.interfaces.sensors.services.GyroscopeServiceInterface;
import java.util.Objects;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: GyroscopeModule.kt */
@Metadata(m184d1 = {"\u0000D\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0003\u0018\u00002\u00020\u0001B\u000f\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003¢\u0006\u0002\u0010\u0004J\u0010\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\fH\u0014J\b\u0010\r\u001a\u00020\u0006H\u0016J\b\u0010\u000e\u001a\u00020\u000fH\u0014J\u0010\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u0013H\u0007J\u0018\u0010\u0014\u001a\u00020\u00112\u0006\u0010\u0015\u001a\u00020\u00162\u0006\u0010\u0012\u001a\u00020\u0013H\u0007J\u0010\u0010\u0017\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u0013H\u0007J\u0010\u0010\u0018\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u0013H\u0007R\u0014\u0010\u0005\u001a\u00020\u0006X\u0094D¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\b¨\u0006\u0019"}, m183d2 = {"Lexpo/modules/sensors/modules/GyroscopeModule;", "Lexpo/modules/sensors/modules/BaseSensorModule;", "reactContext", "Landroid/content/Context;", "(Landroid/content/Context;)V", "eventName", "", "getEventName", "()Ljava/lang/String;", "eventToMap", "Landroid/os/Bundle;", "sensorEvent", "Landroid/hardware/SensorEvent;", "getName", "getSensorService", "Lexpo/modules/interfaces/sensors/SensorServiceInterface;", "isAvailableAsync", "", BaseJavaModule.METHOD_TYPE_PROMISE, "Lexpo/modules/core/Promise;", "setUpdateInterval", "updateInterval", "", "startObserving", "stopObserving", "expo-sensors_release"}, m182k = 1, m181mv = {1, 6, 0}, m179xi = 48)
/* loaded from: classes4.dex */
public final class GyroscopeModule extends BaseSensorModule {
    private final String eventName;

    @Override // expo.modules.core.ExportedModule
    public String getName() {
        return "ExponentGyroscope";
    }

    public GyroscopeModule(Context context) {
        super(context);
        this.eventName = "gyroscopeDidUpdate";
    }

    @Override // expo.modules.sensors.modules.BaseSensorModule
    protected String getEventName() {
        return this.eventName;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // expo.modules.sensors.modules.BaseSensorModule
    public SensorServiceInterface getSensorService() {
        Object module = getModuleRegistry().getModule(GyroscopeServiceInterface.class);
        Intrinsics.checkNotNullExpressionValue(module, "moduleRegistry.getModule…iceInterface::class.java)");
        return (SensorServiceInterface) module;
    }

    @Override // expo.modules.sensors.modules.BaseSensorModule
    protected Bundle eventToMap(SensorEvent sensorEvent) {
        Intrinsics.checkNotNullParameter(sensorEvent, "sensorEvent");
        Bundle bundle = new Bundle();
        bundle.putDouble("x", sensorEvent.values[0]);
        bundle.putDouble("y", sensorEvent.values[1]);
        bundle.putDouble("z", sensorEvent.values[2]);
        return bundle;
    }

    @ExpoMethod
    public final void startObserving(Promise promise) {
        Intrinsics.checkNotNullParameter(promise, "promise");
        super.startObserving();
        promise.resolve(null);
    }

    @ExpoMethod
    public final void stopObserving(Promise promise) {
        Intrinsics.checkNotNullParameter(promise, "promise");
        super.stopObserving();
        promise.resolve(null);
    }

    @ExpoMethod
    public final void setUpdateInterval(int r2, Promise promise) {
        Intrinsics.checkNotNullParameter(promise, "promise");
        super.setUpdateInterval(r2);
        promise.resolve(null);
    }

    @ExpoMethod
    public final void isAvailableAsync(Promise promise) {
        Intrinsics.checkNotNullParameter(promise, "promise");
        Object systemService = getContext().getSystemService("sensor");
        Objects.requireNonNull(systemService, "null cannot be cast to non-null type android.hardware.SensorManager");
        promise.resolve(Boolean.valueOf(((SensorManager) systemService).getDefaultSensor(4) != null));
    }
}
