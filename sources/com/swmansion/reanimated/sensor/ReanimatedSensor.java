package com.swmansion.reanimated.sensor;

import android.hardware.Sensor;
import android.hardware.SensorManager;
import com.facebook.react.bridge.ReactApplicationContext;
import com.swmansion.reanimated.NativeProxy;
import java.lang.ref.WeakReference;

/* loaded from: classes3.dex */
public class ReanimatedSensor {
    int interval;
    ReanimatedSensorListener listener;
    Sensor sensor;
    SensorManager sensorManager;
    ReanimatedSensorType sensorType;

    /* JADX INFO: Access modifiers changed from: package-private */
    public ReanimatedSensor(WeakReference<ReactApplicationContext> weakReference, ReanimatedSensorType reanimatedSensorType, int r6, NativeProxy.SensorSetter sensorSetter) {
        this.listener = new ReanimatedSensorListener(sensorSetter, r6);
        weakReference.get();
        this.sensorManager = (SensorManager) weakReference.get().getSystemService("sensor");
        this.sensorType = reanimatedSensorType;
        if (r6 == -1) {
            this.interval = 2;
        } else {
            this.interval = r6;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public boolean initialize() {
        Sensor defaultSensor = this.sensorManager.getDefaultSensor(this.sensorType.getType());
        this.sensor = defaultSensor;
        if (defaultSensor != null) {
            this.sensorManager.registerListener(this.listener, defaultSensor, this.interval * 1000);
            return true;
        }
        return false;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void cancel() {
        this.sensorManager.unregisterListener(this.listener, this.sensor);
    }
}
