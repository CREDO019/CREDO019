package com.onesignal;

import android.view.animation.Interpolator;

/* loaded from: classes3.dex */
class OneSignalBounceInterpolator implements Interpolator {
    private double mAmplitude;
    private double mFrequency;

    /* JADX INFO: Access modifiers changed from: package-private */
    public OneSignalBounceInterpolator(double d, double d2) {
        this.mAmplitude = d;
        this.mFrequency = d2;
    }

    @Override // android.animation.TimeInterpolator
    public float getInterpolation(float f) {
        return (float) ((Math.pow(2.718281828459045d, (-f) / this.mAmplitude) * (-1.0d) * Math.cos(this.mFrequency * f)) + 1.0d);
    }
}
