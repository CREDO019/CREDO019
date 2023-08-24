package com.swmansion.reanimated.sensor;

import android.util.Log;
import com.facebook.react.bridge.ReactApplicationContext;
import com.swmansion.reanimated.NativeProxy;
import java.lang.ref.WeakReference;
import java.util.HashMap;

/* loaded from: classes3.dex */
public class ReanimatedSensorContainer {
    private final WeakReference<ReactApplicationContext> reactContext;
    private int nextSensorId = 0;
    private final HashMap<Integer, ReanimatedSensor> sensors = new HashMap<>();

    public ReanimatedSensorContainer(WeakReference<ReactApplicationContext> weakReference) {
        this.reactContext = weakReference;
    }

    public int registerSensor(ReanimatedSensorType reanimatedSensorType, int r7, NativeProxy.SensorSetter sensorSetter) {
        if (new ReanimatedSensor(this.reactContext, reanimatedSensorType, r7, sensorSetter).initialize()) {
            int r0 = this.nextSensorId;
            this.nextSensorId = r0 + 1;
            this.sensors.put(Integer.valueOf(r0), new ReanimatedSensor(this.reactContext, reanimatedSensorType, r7, sensorSetter));
            return r0;
        }
        return -1;
    }

    public void unregisterSensor(int r3) {
        ReanimatedSensor reanimatedSensor = this.sensors.get(Integer.valueOf(r3));
        if (reanimatedSensor != null) {
            reanimatedSensor.cancel();
            this.sensors.remove(Integer.valueOf(r3));
            return;
        }
        Log.e("Reanimated", "Tried to unregister nonexistent sensor");
    }
}
