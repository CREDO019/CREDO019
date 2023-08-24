package com.airbnb.lottie.utils;

/* loaded from: classes.dex */
public class GammaEvaluator {
    private static float OECF_sRGB(float f) {
        return f <= 0.0031308f ? f * 12.92f : (float) ((Math.pow(f, 0.4166666567325592d) * 1.0549999475479126d) - 0.054999999701976776d);
    }

    private static float EOCF_sRGB(float f) {
        return f <= 0.04045f ? f / 12.92f : (float) Math.pow((f + 0.055f) / 1.055f, 2.4000000953674316d);
    }

    public static int evaluate(float f, int r8, int r9) {
        if (r8 == r9) {
            return r8;
        }
        float f2 = ((r8 >> 24) & 255) / 255.0f;
        float EOCF_sRGB = EOCF_sRGB(((r8 >> 16) & 255) / 255.0f);
        float EOCF_sRGB2 = EOCF_sRGB(((r8 >> 8) & 255) / 255.0f);
        float EOCF_sRGB3 = EOCF_sRGB((r8 & 255) / 255.0f);
        float EOCF_sRGB4 = EOCF_sRGB(((r9 >> 16) & 255) / 255.0f);
        float EOCF_sRGB5 = EOCF_sRGB(((r9 >> 8) & 255) / 255.0f);
        float EOCF_sRGB6 = EOCF_sRGB3 + (f * (EOCF_sRGB((r9 & 255) / 255.0f) - EOCF_sRGB3));
        return (Math.round(OECF_sRGB(EOCF_sRGB + ((EOCF_sRGB4 - EOCF_sRGB) * f)) * 255.0f) << 16) | (Math.round((f2 + (((((r9 >> 24) & 255) / 255.0f) - f2) * f)) * 255.0f) << 24) | (Math.round(OECF_sRGB(EOCF_sRGB2 + ((EOCF_sRGB5 - EOCF_sRGB2) * f)) * 255.0f) << 8) | Math.round(OECF_sRGB(EOCF_sRGB6) * 255.0f);
    }
}
