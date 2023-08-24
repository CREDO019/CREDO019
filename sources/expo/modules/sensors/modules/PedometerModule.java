package expo.modules.sensors.modules;

import android.content.Context;
import android.hardware.SensorEvent;
import android.os.Bundle;
import com.facebook.react.bridge.BaseJavaModule;
import expo.modules.core.Promise;
import expo.modules.core.interfaces.ExpoMethod;
import expo.modules.interfaces.sensors.SensorServiceInterface;
import expo.modules.interfaces.sensors.services.PedometerServiceInterface;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: PedometerModule.kt */
@Metadata(m184d1 = {"\u0000F\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0007\u0018\u00002\u00020\u0001B\u000f\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003¢\u0006\u0002\u0010\u0004J\u0010\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u000fH\u0014J\b\u0010\u0010\u001a\u00020\u0006H\u0016J\b\u0010\u0011\u001a\u00020\u0012H\u0014J)\u0010\u0013\u001a\u00020\u00142\b\u0010\u0015\u001a\u0004\u0018\u00010\n2\b\u0010\u0016\u001a\u0004\u0018\u00010\n2\u0006\u0010\u0017\u001a\u00020\u0018H\u0007¢\u0006\u0002\u0010\u0019J\u0010\u0010\u001a\u001a\u00020\u00142\u0006\u0010\u0017\u001a\u00020\u0018H\u0007J\u0018\u0010\u001b\u001a\u00020\u00142\u0006\u0010\u001c\u001a\u00020\n2\u0006\u0010\u0017\u001a\u00020\u0018H\u0007J\u0010\u0010\u001d\u001a\u00020\u00142\u0006\u0010\u0017\u001a\u00020\u0018H\u0007J\u0010\u0010\u001e\u001a\u00020\u00142\u0006\u0010\u0017\u001a\u00020\u0018H\u0007R\u0014\u0010\u0005\u001a\u00020\u0006X\u0094D¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0012\u0010\t\u001a\u0004\u0018\u00010\nX\u0082\u000e¢\u0006\u0004\n\u0002\u0010\u000b¨\u0006\u001f"}, m183d2 = {"Lexpo/modules/sensors/modules/PedometerModule;", "Lexpo/modules/sensors/modules/BaseSensorModule;", "reactContext", "Landroid/content/Context;", "(Landroid/content/Context;)V", "eventName", "", "getEventName", "()Ljava/lang/String;", "stepsAtTheBeginning", "", "Ljava/lang/Integer;", "eventToMap", "Landroid/os/Bundle;", "sensorEvent", "Landroid/hardware/SensorEvent;", "getName", "getSensorService", "Lexpo/modules/interfaces/sensors/SensorServiceInterface;", "getStepCountAsync", "", "startDate", "endDate", BaseJavaModule.METHOD_TYPE_PROMISE, "Lexpo/modules/core/Promise;", "(Ljava/lang/Integer;Ljava/lang/Integer;Lexpo/modules/core/Promise;)V", "isAvailableAsync", "setUpdateInterval", "updateInterval", "startObserving", "stopObserving", "expo-sensors_release"}, m182k = 1, m181mv = {1, 6, 0}, m179xi = 48)
/* loaded from: classes4.dex */
public final class PedometerModule extends BaseSensorModule {
    private final String eventName;
    private Integer stepsAtTheBeginning;

    @Override // expo.modules.core.ExportedModule
    public String getName() {
        return "ExponentPedometer";
    }

    public PedometerModule(Context context) {
        super(context);
        this.eventName = "Exponent.pedometerUpdate";
    }

    @Override // expo.modules.sensors.modules.BaseSensorModule
    protected String getEventName() {
        return this.eventName;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // expo.modules.sensors.modules.BaseSensorModule
    public SensorServiceInterface getSensorService() {
        Object module = getModuleRegistry().getModule(PedometerServiceInterface.class);
        Intrinsics.checkNotNullExpressionValue(module, "moduleRegistry.getModule…iceInterface::class.java)");
        return (SensorServiceInterface) module;
    }

    @Override // expo.modules.sensors.modules.BaseSensorModule
    protected Bundle eventToMap(SensorEvent sensorEvent) {
        Intrinsics.checkNotNullParameter(sensorEvent, "sensorEvent");
        if (this.stepsAtTheBeginning == null) {
            this.stepsAtTheBeginning = Integer.valueOf(((int) sensorEvent.values[0]) - 1);
        }
        Bundle bundle = new Bundle();
        float f = sensorEvent.values[0];
        Integer num = this.stepsAtTheBeginning;
        Intrinsics.checkNotNull(num);
        bundle.putDouble("steps", f - num.intValue());
        return bundle;
    }

    @ExpoMethod
    public final void startObserving(Promise promise) {
        Intrinsics.checkNotNullParameter(promise, "promise");
        super.startObserving();
        this.stepsAtTheBeginning = null;
        promise.resolve(null);
    }

    @ExpoMethod
    public final void stopObserving(Promise promise) {
        Intrinsics.checkNotNullParameter(promise, "promise");
        super.stopObserving();
        this.stepsAtTheBeginning = null;
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
        promise.resolve(Boolean.valueOf(getContext().getPackageManager().hasSystemFeature("android.hardware.sensor.stepcounter")));
    }

    @ExpoMethod
    public final void getStepCountAsync(Integer num, Integer num2, Promise promise) {
        Intrinsics.checkNotNullParameter(promise, "promise");
        promise.reject("E_NOT_AVAILABLE", "Getting step count for date range is not supported on Android yet.");
    }
}
