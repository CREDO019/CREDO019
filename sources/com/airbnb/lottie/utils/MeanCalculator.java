package com.airbnb.lottie.utils;

/* loaded from: classes.dex */
public class MeanCalculator {

    /* renamed from: n */
    private int f80n;
    private float sum;

    public void add(float f) {
        float f2 = this.sum + f;
        this.sum = f2;
        int r3 = this.f80n + 1;
        this.f80n = r3;
        if (r3 == Integer.MAX_VALUE) {
            this.sum = f2 / 2.0f;
            this.f80n = r3 / 2;
        }
    }

    public float getMean() {
        int r0 = this.f80n;
        if (r0 == 0) {
            return 0.0f;
        }
        return this.sum / r0;
    }
}
