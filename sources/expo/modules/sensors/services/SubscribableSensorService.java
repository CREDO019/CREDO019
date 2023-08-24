package expo.modules.sensors.services;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener2;
import com.google.android.gms.common.internal.ServiceSpecificExtraArgs;
import expo.modules.interfaces.sensors.SensorServiceInterface;
import expo.modules.interfaces.sensors.SensorServiceSubscriptionInterface;
import java.util.Map;
import java.util.WeakHashMap;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: SubscribableSensorService.kt */
@Metadata(m184d1 = {"\u0000L\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010%\n\u0002\u0018\u0002\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0005\b&\u0018\u00002\u00020\u00012\u00020\u0002B\u0011\b\u0000\u0012\b\u0010\u0003\u001a\u0004\u0018\u00010\u0004¢\u0006\u0002\u0010\u0005J\u0010\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u000fH\u0016J\u0018\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u00132\u0006\u0010\u0014\u001a\u00020\u0007H\u0016J\b\u0010\u0015\u001a\u00020\u0011H\u0016J\b\u0010\u0016\u001a\u00020\u0011H\u0016J\u0010\u0010\u0017\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u0013H\u0016J\u0010\u0010\u0018\u001a\u00020\u00112\u0006\u0010\u0019\u001a\u00020\u001aH\u0016J\u000e\u0010\u001b\u001a\u00020\u00112\u0006\u0010\u001c\u001a\u00020\nJ\u000e\u0010\u001d\u001a\u00020\u00112\u0006\u0010\u001c\u001a\u00020\nJ\b\u0010\u001e\u001a\u00020\u0011H\u0002R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u000e¢\u0006\u0002\n\u0000R\u001a\u0010\b\u001a\u000e\u0012\u0004\u0012\u00020\n\u0012\u0004\u0012\u00020\u000b0\tX\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u001f"}, m183d2 = {"Lexpo/modules/sensors/services/SubscribableSensorService;", "Lexpo/modules/sensors/services/BaseSensorService;", "Lexpo/modules/interfaces/sensors/SensorServiceInterface;", "reactContext", "Landroid/content/Context;", "(Landroid/content/Context;)V", "mListenersCount", "", "mSensorEventListenerLastUpdateMap", "", "Lexpo/modules/sensors/services/SensorServiceSubscription;", "", "createSubscriptionForListener", "Lexpo/modules/interfaces/sensors/SensorServiceSubscriptionInterface;", ServiceSpecificExtraArgs.CastExtraArgs.LISTENER, "Landroid/hardware/SensorEventListener2;", "onAccuracyChanged", "", "sensor", "Landroid/hardware/Sensor;", "accuracy", "onExperienceBackgrounded", "onExperienceForegrounded", "onFlushCompleted", "onSensorChanged", "sensorEvent", "Landroid/hardware/SensorEvent;", "onSubscriptionEnabledChanged", "sensorServiceSubscription", "removeSubscription", "updateObserving", "expo-sensors_release"}, m182k = 1, m181mv = {1, 6, 0}, m179xi = 48)
/* loaded from: classes4.dex */
public abstract class SubscribableSensorService extends BaseSensorService implements SensorServiceInterface {
    private int mListenersCount;
    private final Map<SensorServiceSubscription, Long> mSensorEventListenerLastUpdateMap;

    public SubscribableSensorService(Context context) {
        super(context);
        this.mSensorEventListenerLastUpdateMap = new WeakHashMap();
    }

    @Override // expo.modules.sensors.services.BaseService
    public void onExperienceForegrounded() {
        updateObserving();
    }

    @Override // expo.modules.sensors.services.BaseService
    public void onExperienceBackgrounded() {
        updateObserving();
    }

    @Override // expo.modules.interfaces.sensors.SensorServiceInterface
    public SensorServiceSubscriptionInterface createSubscriptionForListener(SensorEventListener2 listener) {
        Intrinsics.checkNotNullParameter(listener, "listener");
        SensorServiceSubscription sensorServiceSubscription = new SensorServiceSubscription(this, listener);
        this.mSensorEventListenerLastUpdateMap.put(sensorServiceSubscription, 0L);
        return sensorServiceSubscription;
    }

    public final void onSubscriptionEnabledChanged(SensorServiceSubscription sensorServiceSubscription) {
        Intrinsics.checkNotNullParameter(sensorServiceSubscription, "sensorServiceSubscription");
        if (sensorServiceSubscription.isEnabled()) {
            this.mListenersCount++;
        } else {
            this.mListenersCount--;
        }
        updateObserving();
    }

    public final void removeSubscription(SensorServiceSubscription sensorServiceSubscription) {
        Intrinsics.checkNotNullParameter(sensorServiceSubscription, "sensorServiceSubscription");
        this.mSensorEventListenerLastUpdateMap.remove(sensorServiceSubscription);
    }

    @Override // android.hardware.SensorEventListener
    public void onSensorChanged(SensorEvent sensorEvent) {
        Intrinsics.checkNotNullParameter(sensorEvent, "sensorEvent");
        if (sensorEvent.sensor.getType() == getSensorType()) {
            long currentTimeMillis = System.currentTimeMillis();
            for (SensorServiceSubscription sensorServiceSubscription : this.mSensorEventListenerLastUpdateMap.keySet()) {
                if (sensorServiceSubscription.isEnabled()) {
                    Long l = this.mSensorEventListenerLastUpdateMap.get(sensorServiceSubscription);
                    if (currentTimeMillis - (l == null ? 0L : l.longValue()) > sensorServiceSubscription.getUpdateInterval().longValue()) {
                        sensorServiceSubscription.getSensorEventListener().onSensorChanged(sensorEvent);
                        this.mSensorEventListenerLastUpdateMap.put(sensorServiceSubscription, Long.valueOf(currentTimeMillis));
                    }
                }
            }
        }
    }

    @Override // android.hardware.SensorEventListener
    public void onAccuracyChanged(Sensor sensor, int r5) {
        Intrinsics.checkNotNullParameter(sensor, "sensor");
        if (sensor.getType() == getSensorType()) {
            for (SensorServiceSubscription sensorServiceSubscription : this.mSensorEventListenerLastUpdateMap.keySet()) {
                if (sensorServiceSubscription.isEnabled()) {
                    sensorServiceSubscription.getSensorEventListener().onAccuracyChanged(sensor, r5);
                }
            }
        }
    }

    @Override // android.hardware.SensorEventListener2
    public void onFlushCompleted(Sensor sensor) {
        Intrinsics.checkNotNullParameter(sensor, "sensor");
        if (sensor.getType() == getSensorType()) {
            for (SensorServiceSubscription sensorServiceSubscription : this.mSensorEventListenerLastUpdateMap.keySet()) {
                if (sensorServiceSubscription.isEnabled()) {
                    sensorServiceSubscription.getSensorEventListener().onFlushCompleted(sensor);
                }
            }
        }
    }

    private final void updateObserving() {
        if (this.mListenersCount > 0 && getExperienceIsForegrounded()) {
            super.startObserving();
        } else {
            super.stopObserving();
        }
    }
}
