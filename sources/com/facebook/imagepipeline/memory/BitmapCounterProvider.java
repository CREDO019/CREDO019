package com.facebook.imagepipeline.memory;

/* loaded from: classes.dex */
public class BitmapCounterProvider {

    /* renamed from: KB */
    private static final long f155KB = 1024;

    /* renamed from: MB */
    private static final long f156MB = 1048576;
    private static volatile BitmapCounter sBitmapCounter;
    public static final int MAX_BITMAP_TOTAL_SIZE = getMaxSizeHardCap();
    private static int sMaxBitmapCount = BitmapCounterConfig.DEFAULT_MAX_BITMAP_COUNT;

    private static int getMaxSizeHardCap() {
        int min = (int) Math.min(Runtime.getRuntime().maxMemory(), 2147483647L);
        if (min > 16777216) {
            return (min / 4) * 3;
        }
        return min / 2;
    }

    public static void initialize(BitmapCounterConfig bitmapCounterConfig) {
        if (sBitmapCounter != null) {
            throw new IllegalStateException("BitmapCounter has already been created! `BitmapCounterProvider.initialize(...)` should only be called before `BitmapCounterProvider.get()` or not at all!");
        }
        sMaxBitmapCount = bitmapCounterConfig.getMaxBitmapCount();
    }

    public static BitmapCounter get() {
        if (sBitmapCounter == null) {
            synchronized (BitmapCounterProvider.class) {
                if (sBitmapCounter == null) {
                    sBitmapCounter = new BitmapCounter(sMaxBitmapCount, MAX_BITMAP_TOTAL_SIZE);
                }
            }
        }
        return sBitmapCounter;
    }
}
