package com.swmansion.reanimated.sensor;

/* loaded from: classes3.dex */
public enum ReanimatedSensorType {
    ACCELEROMETER(10),
    GYROSCOPE(4),
    GRAVITY(9),
    MAGNETIC_FIELD(2),
    ROTATION_VECTOR(11);
    
    private final int type;

    ReanimatedSensorType(int r3) {
        this.type = r3;
    }

    public int getType() {
        return this.type;
    }

    public static ReanimatedSensorType getInstanceById(int r1) {
        if (r1 != 1) {
            if (r1 != 2) {
                if (r1 != 3) {
                    if (r1 != 4) {
                        if (r1 == 5) {
                            return ROTATION_VECTOR;
                        }
                        throw new IllegalArgumentException("[Reanimated] Unknown sensor type");
                    }
                    return MAGNETIC_FIELD;
                }
                return GRAVITY;
            }
            return GYROSCOPE;
        }
        return ACCELEROMETER;
    }
}
