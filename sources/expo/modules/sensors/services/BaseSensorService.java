package expo.modules.sensors.services;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.hardware.Sensor;
import android.hardware.SensorEventListener2;
import android.hardware.SensorManager;
import android.os.Build;
import java.util.Objects;
import kotlin.Metadata;
import kotlin.collections.ArraysKt;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: BaseSensorService.kt */
@Metadata(m184d1 = {"\u00008\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\b&\u0018\u00002\u00020\u00012\u00020\u0002B\u0011\b\u0000\u0012\b\u0010\u0003\u001a\u0004\u0018\u00010\u0004¢\u0006\u0002\u0010\u0005J\b\u0010\u0010\u001a\u00020\u0011H\u0002J\b\u0010\u0012\u001a\u00020\u0013H\u0004J\b\u0010\u0014\u001a\u00020\u0013H\u0004R\u0010\u0010\u0006\u001a\u0004\u0018\u00010\u0007X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tX\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\n\u001a\u00020\u000b8BX\u0082\u0004¢\u0006\u0006\u001a\u0004\b\f\u0010\rR\u0012\u0010\u000e\u001a\u00020\u000bX¦\u0004¢\u0006\u0006\u001a\u0004\b\u000f\u0010\r¨\u0006\u0015"}, m183d2 = {"Lexpo/modules/sensors/services/BaseSensorService;", "Lexpo/modules/sensors/services/BaseService;", "Landroid/hardware/SensorEventListener2;", "reactContext", "Landroid/content/Context;", "(Landroid/content/Context;)V", "mSensor", "Landroid/hardware/Sensor;", "mSensorManager", "Landroid/hardware/SensorManager;", "samplingPeriodUs", "", "getSamplingPeriodUs", "()I", "sensorType", "getSensorType", "hasHighSamplingRateSensorsPermission", "", "startObserving", "", "stopObserving", "expo-sensors_release"}, m182k = 1, m181mv = {1, 6, 0}, m179xi = 48)
/* loaded from: classes4.dex */
public abstract class BaseSensorService extends BaseService implements SensorEventListener2 {
    private Sensor mSensor;
    private final SensorManager mSensorManager;

    public abstract int getSensorType();

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public BaseSensorService(Context context) {
        super(context);
        Intrinsics.checkNotNull(context);
        Object systemService = getContext().getSystemService("sensor");
        Objects.requireNonNull(systemService, "null cannot be cast to non-null type android.hardware.SensorManager");
        this.mSensorManager = (SensorManager) systemService;
    }

    private final int getSamplingPeriodUs() {
        return hasHighSamplingRateSensorsPermission() ? 0 : 3;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final void startObserving() {
        Sensor defaultSensor = this.mSensorManager.getDefaultSensor(getSensorType());
        this.mSensor = defaultSensor;
        if (defaultSensor != null) {
            this.mSensorManager.registerListener(this, defaultSensor, getSamplingPeriodUs());
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final void stopObserving() {
        this.mSensorManager.unregisterListener(this);
    }

    private final boolean hasHighSamplingRateSensorsPermission() {
        if (Build.VERSION.SDK_INT < 31) {
            return true;
        }
        try {
            PackageInfo packageInfo = getContext().getPackageManager().getPackageInfo(getContext().getPackageName(), 4096);
            if (packageInfo == null) {
                return false;
            }
            String[] requestedPermissions = packageInfo.requestedPermissions;
            Intrinsics.checkNotNullExpressionValue(requestedPermissions, "requestedPermissions");
            return ArraysKt.contains(requestedPermissions, "android.permission.HIGH_SAMPLING_RATE_SENSORS");
        } catch (PackageManager.NameNotFoundException unused) {
            return false;
        }
    }
}
