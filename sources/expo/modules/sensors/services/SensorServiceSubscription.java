package expo.modules.sensors.services;

import android.hardware.SensorEventListener2;
import expo.modules.interfaces.sensors.SensorServiceSubscriptionInterface;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: SensorServiceSubscription.kt */
@Metadata(m184d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\t\n\u0002\b\u0006\n\u0002\u0010\u0002\n\u0002\b\u0005\u0018\u00002\u00020\u0001B\u0017\b\u0000\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J\r\u0010\u000e\u001a\u00020\u000bH\u0016¢\u0006\u0002\u0010\u000fJ\b\u0010\u0010\u001a\u00020\bH\u0016J\b\u0010\u0011\u001a\u00020\u0012H\u0016J\u0010\u0010\u0013\u001a\u00020\u00122\u0006\u0010\u0014\u001a\u00020\u000bH\u0016J\b\u0010\u0015\u001a\u00020\u0012H\u0016J\b\u0010\u0016\u001a\u00020\u0012H\u0016R\u000e\u0010\u0007\u001a\u00020\bX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\bX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u000bX\u0082\u000e¢\u0006\u0002\n\u0000R\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\r¨\u0006\u0017"}, m183d2 = {"Lexpo/modules/sensors/services/SensorServiceSubscription;", "Lexpo/modules/interfaces/sensors/SensorServiceSubscriptionInterface;", "mSubscribableSensorService", "Lexpo/modules/sensors/services/SubscribableSensorService;", "sensorEventListener", "Landroid/hardware/SensorEventListener2;", "(Lexpo/modules/sensors/services/SubscribableSensorService;Landroid/hardware/SensorEventListener2;)V", "mHasBeenReleased", "", "mIsEnabled", "mUpdateInterval", "", "getSensorEventListener", "()Landroid/hardware/SensorEventListener2;", "getUpdateInterval", "()Ljava/lang/Long;", "isEnabled", "release", "", "setUpdateInterval", "updateInterval", "start", "stop", "expo-sensors_release"}, m182k = 1, m181mv = {1, 6, 0}, m179xi = 48)
/* loaded from: classes4.dex */
public final class SensorServiceSubscription implements SensorServiceSubscriptionInterface {
    private boolean mHasBeenReleased;
    private boolean mIsEnabled;
    private final SubscribableSensorService mSubscribableSensorService;
    private long mUpdateInterval;
    private final SensorEventListener2 sensorEventListener;

    public SensorServiceSubscription(SubscribableSensorService mSubscribableSensorService, SensorEventListener2 sensorEventListener) {
        Intrinsics.checkNotNullParameter(mSubscribableSensorService, "mSubscribableSensorService");
        Intrinsics.checkNotNullParameter(sensorEventListener, "sensorEventListener");
        this.mSubscribableSensorService = mSubscribableSensorService;
        this.sensorEventListener = sensorEventListener;
        this.mUpdateInterval = 100L;
    }

    public final SensorEventListener2 getSensorEventListener() {
        return this.sensorEventListener;
    }

    @Override // expo.modules.interfaces.sensors.SensorServiceSubscriptionInterface
    public void start() {
        if (this.mHasBeenReleased || this.mIsEnabled) {
            return;
        }
        this.mIsEnabled = true;
        this.mSubscribableSensorService.onSubscriptionEnabledChanged(this);
    }

    @Override // expo.modules.interfaces.sensors.SensorServiceSubscriptionInterface
    public boolean isEnabled() {
        return this.mIsEnabled;
    }

    @Override // expo.modules.interfaces.sensors.SensorServiceSubscriptionInterface
    public Long getUpdateInterval() {
        return Long.valueOf(this.mUpdateInterval);
    }

    @Override // expo.modules.interfaces.sensors.SensorServiceSubscriptionInterface
    public void setUpdateInterval(long j) {
        if (this.mHasBeenReleased) {
            return;
        }
        this.mUpdateInterval = j;
    }

    @Override // expo.modules.interfaces.sensors.SensorServiceSubscriptionInterface
    public void stop() {
        if (this.mIsEnabled) {
            this.mIsEnabled = false;
            this.mSubscribableSensorService.onSubscriptionEnabledChanged(this);
        }
    }

    @Override // expo.modules.interfaces.sensors.SensorServiceSubscriptionInterface
    public void release() {
        if (this.mHasBeenReleased) {
            return;
        }
        this.mSubscribableSensorService.removeSubscription(this);
        this.mHasBeenReleased = true;
    }
}
