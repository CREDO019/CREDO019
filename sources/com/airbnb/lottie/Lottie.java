package com.airbnb.lottie;

/* loaded from: classes.dex */
public class Lottie {
    private Lottie() {
    }

    public static void initialize(LottieConfig lottieConfig) {
        C0991L.setFetcher(lottieConfig.networkFetcher);
        C0991L.setCacheProvider(lottieConfig.cacheProvider);
        C0991L.setTraceEnabled(lottieConfig.enableSystraceMarkers);
    }
}
