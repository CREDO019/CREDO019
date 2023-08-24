package expo.modules.sensors.modules;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener2;
import android.os.Bundle;
import android.util.Log;
import expo.modules.core.ExportedModule;
import expo.modules.core.ModuleRegistry;
import expo.modules.core.interfaces.LifecycleEventListener;
import expo.modules.core.interfaces.services.EventEmitter;
import expo.modules.core.interfaces.services.UIManager;
import expo.modules.interfaces.sensors.SensorServiceInterface;
import expo.modules.interfaces.sensors.SensorServiceSubscriptionInterface;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Functions;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: BaseSensorModule.kt */
@Metadata(m184d1 = {"\u0000^\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u000b\b&\u0018\u00002\u00020\u00012\u00020\u00022\u00020\u0003B\u0011\b\u0000\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0005¢\u0006\u0002\u0010\u0006J\u0010\u0010\u0018\u001a\u00020\u00192\u0006\u0010\u001a\u001a\u00020\u001bH$J\b\u0010\u001c\u001a\u00020\u001dH$J\u0018\u0010\u001e\u001a\u00020\u001f2\u0006\u0010 \u001a\u00020!2\u0006\u0010\"\u001a\u00020#H\u0016J\u0010\u0010$\u001a\u00020\u001f2\u0006\u0010\u000f\u001a\u00020\u000eH\u0016J\u0010\u0010%\u001a\u00020\u001f2\u0006\u0010 \u001a\u00020!H\u0016J\b\u0010&\u001a\u00020\u001fH\u0016J\b\u0010'\u001a\u00020\u001fH\u0016J\b\u0010(\u001a\u00020\u001fH\u0016J\u0010\u0010)\u001a\u00020\u001f2\u0006\u0010\u001a\u001a\u00020\u001bH\u0016J\u000e\u0010*\u001a\u00020\u001f2\u0006\u0010+\u001a\u00020#J\u0006\u0010,\u001a\u00020\u001fJ\u0006\u0010-\u001a\u00020\u001fR\u0012\u0010\u0007\u001a\u00020\bX¤\u0004¢\u0006\u0006\u001a\u0004\b\t\u0010\nR\u000e\u0010\u000b\u001a\u00020\fX\u0082\u000e¢\u0006\u0002\n\u0000R\u001e\u0010\u000f\u001a\u00020\u000e2\u0006\u0010\r\u001a\u00020\u000e@BX\u0086.¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u0011R\u001b\u0010\u0012\u001a\u00020\u00138BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\u0016\u0010\u0017\u001a\u0004\b\u0014\u0010\u0015¨\u0006."}, m183d2 = {"Lexpo/modules/sensors/modules/BaseSensorModule;", "Lexpo/modules/core/ExportedModule;", "Landroid/hardware/SensorEventListener2;", "Lexpo/modules/core/interfaces/LifecycleEventListener;", "context", "Landroid/content/Context;", "(Landroid/content/Context;)V", "eventName", "", "getEventName", "()Ljava/lang/String;", "mIsObserving", "", "<set-?>", "Lexpo/modules/core/ModuleRegistry;", "moduleRegistry", "getModuleRegistry", "()Lexpo/modules/core/ModuleRegistry;", "sensorKernelServiceSubscription", "Lexpo/modules/interfaces/sensors/SensorServiceSubscriptionInterface;", "getSensorKernelServiceSubscription", "()Lexpo/modules/interfaces/sensors/SensorServiceSubscriptionInterface;", "sensorKernelServiceSubscription$delegate", "Lkotlin/Lazy;", "eventToMap", "Landroid/os/Bundle;", "sensorEvent", "Landroid/hardware/SensorEvent;", "getSensorService", "Lexpo/modules/interfaces/sensors/SensorServiceInterface;", "onAccuracyChanged", "", "sensor", "Landroid/hardware/Sensor;", "accuracy", "", "onCreate", "onFlushCompleted", "onHostDestroy", "onHostPause", "onHostResume", "onSensorChanged", "setUpdateInterval", "updateInterval", "startObserving", "stopObserving", "expo-sensors_release"}, m182k = 1, m181mv = {1, 6, 0}, m179xi = 48)
/* loaded from: classes4.dex */
public abstract class BaseSensorModule extends ExportedModule implements SensorEventListener2, LifecycleEventListener {
    private boolean mIsObserving;
    private ModuleRegistry moduleRegistry;
    private final Lazy sensorKernelServiceSubscription$delegate;

    protected abstract Bundle eventToMap(SensorEvent sensorEvent);

    protected abstract String getEventName();

    /* JADX INFO: Access modifiers changed from: protected */
    public abstract SensorServiceInterface getSensorService();

    @Override // android.hardware.SensorEventListener
    public void onAccuracyChanged(Sensor sensor, int r2) {
        Intrinsics.checkNotNullParameter(sensor, "sensor");
    }

    @Override // android.hardware.SensorEventListener2
    public void onFlushCompleted(Sensor sensor) {
        Intrinsics.checkNotNullParameter(sensor, "sensor");
    }

    public BaseSensorModule(Context context) {
        super(context);
        this.sensorKernelServiceSubscription$delegate = LazyKt.lazy(new Functions<SensorServiceSubscriptionInterface>() { // from class: expo.modules.sensors.modules.BaseSensorModule$sensorKernelServiceSubscription$2
            /* JADX INFO: Access modifiers changed from: package-private */
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Functions
            public final SensorServiceSubscriptionInterface invoke() {
                return BaseSensorModule.this.getSensorService().createSubscriptionForListener(BaseSensorModule.this);
            }
        });
    }

    public final ModuleRegistry getModuleRegistry() {
        ModuleRegistry moduleRegistry = this.moduleRegistry;
        if (moduleRegistry != null) {
            return moduleRegistry;
        }
        Intrinsics.throwUninitializedPropertyAccessException("moduleRegistry");
        return null;
    }

    private final SensorServiceSubscriptionInterface getSensorKernelServiceSubscription() {
        Object value = this.sensorKernelServiceSubscription$delegate.getValue();
        Intrinsics.checkNotNullExpressionValue(value, "<get-sensorKernelServiceSubscription>(...)");
        return (SensorServiceSubscriptionInterface) value;
    }

    @Override // expo.modules.core.ExportedModule, expo.modules.core.interfaces.RegistryLifecycleListener
    public void onCreate(ModuleRegistry moduleRegistry) {
        Intrinsics.checkNotNullParameter(moduleRegistry, "moduleRegistry");
        this.moduleRegistry = moduleRegistry;
        UIManager uIManager = (UIManager) moduleRegistry.getModule(UIManager.class);
        if (uIManager != null) {
            uIManager.unregisterLifecycleEventListener(this);
        }
        UIManager uIManager2 = (UIManager) moduleRegistry.getModule(UIManager.class);
        if (uIManager2 == null) {
            return;
        }
        uIManager2.registerLifecycleEventListener(this);
    }

    @Override // android.hardware.SensorEventListener
    public void onSensorChanged(SensorEvent sensorEvent) {
        Unit unit;
        Intrinsics.checkNotNullParameter(sensorEvent, "sensorEvent");
        EventEmitter eventEmitter = (EventEmitter) getModuleRegistry().getModule(EventEmitter.class);
        if (eventEmitter == null) {
            unit = null;
        } else {
            eventEmitter.emit(getEventName(), eventToMap(sensorEvent));
            unit = Unit.INSTANCE;
        }
        if (unit == null) {
            String eventName = getEventName();
            Log.e("E_SENSOR_MODULE", "Could not emit " + eventName + " event, no event emitter present.");
        }
    }

    public final void setUpdateInterval(int r4) {
        getSensorKernelServiceSubscription().setUpdateInterval(r4);
    }

    public final void startObserving() {
        this.mIsObserving = true;
        getSensorKernelServiceSubscription().start();
    }

    public final void stopObserving() {
        if (this.mIsObserving) {
            this.mIsObserving = false;
            getSensorKernelServiceSubscription().stop();
        }
    }

    @Override // expo.modules.core.interfaces.LifecycleEventListener
    public void onHostResume() {
        if (this.mIsObserving) {
            getSensorKernelServiceSubscription().start();
        }
    }

    @Override // expo.modules.core.interfaces.LifecycleEventListener
    public void onHostPause() {
        if (this.mIsObserving) {
            getSensorKernelServiceSubscription().stop();
        }
    }

    @Override // expo.modules.core.interfaces.LifecycleEventListener
    public void onHostDestroy() {
        getSensorKernelServiceSubscription().release();
    }
}
