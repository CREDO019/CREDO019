package com.bumptech.glide.load.resource.bitmap;

import com.bumptech.glide.load.Option;

/* loaded from: classes.dex */
public abstract class DownsampleStrategy {
    public static final DownsampleStrategy AT_LEAST;
    public static final DownsampleStrategy AT_MOST;
    public static final DownsampleStrategy CENTER_INSIDE;
    public static final DownsampleStrategy CENTER_OUTSIDE;
    public static final DownsampleStrategy DEFAULT;
    public static final DownsampleStrategy FIT_CENTER = new FitCenter();
    public static final DownsampleStrategy NONE;
    public static final Option<DownsampleStrategy> OPTION;

    /* loaded from: classes.dex */
    public enum SampleSizeRounding {
        MEMORY,
        QUALITY
    }

    public abstract SampleSizeRounding getSampleSizeRounding(int r1, int r2, int r3, int r4);

    public abstract float getScaleFactor(int r1, int r2, int r3, int r4);

    static {
        CenterOutside centerOutside = new CenterOutside();
        CENTER_OUTSIDE = centerOutside;
        AT_LEAST = new AtLeast();
        AT_MOST = new AtMost();
        CENTER_INSIDE = new CenterInside();
        NONE = new None();
        DEFAULT = centerOutside;
        OPTION = Option.memory("com.bumptech.glide.load.resource.bitmap.Downsampler.DownsampleStrategy", centerOutside);
    }

    /* loaded from: classes.dex */
    private static class FitCenter extends DownsampleStrategy {
        FitCenter() {
        }

        @Override // com.bumptech.glide.load.resource.bitmap.DownsampleStrategy
        public float getScaleFactor(int r1, int r2, int r3, int r4) {
            return Math.min(r3 / r1, r4 / r2);
        }

        @Override // com.bumptech.glide.load.resource.bitmap.DownsampleStrategy
        public SampleSizeRounding getSampleSizeRounding(int r1, int r2, int r3, int r4) {
            return SampleSizeRounding.QUALITY;
        }
    }

    /* loaded from: classes.dex */
    private static class CenterOutside extends DownsampleStrategy {
        CenterOutside() {
        }

        @Override // com.bumptech.glide.load.resource.bitmap.DownsampleStrategy
        public float getScaleFactor(int r1, int r2, int r3, int r4) {
            return Math.max(r3 / r1, r4 / r2);
        }

        @Override // com.bumptech.glide.load.resource.bitmap.DownsampleStrategy
        public SampleSizeRounding getSampleSizeRounding(int r1, int r2, int r3, int r4) {
            return SampleSizeRounding.QUALITY;
        }
    }

    /* loaded from: classes.dex */
    private static class AtLeast extends DownsampleStrategy {
        AtLeast() {
        }

        @Override // com.bumptech.glide.load.resource.bitmap.DownsampleStrategy
        public float getScaleFactor(int r1, int r2, int r3, int r4) {
            int min = Math.min(r2 / r4, r1 / r3);
            if (min == 0) {
                return 1.0f;
            }
            return 1.0f / Integer.highestOneBit(min);
        }

        @Override // com.bumptech.glide.load.resource.bitmap.DownsampleStrategy
        public SampleSizeRounding getSampleSizeRounding(int r1, int r2, int r3, int r4) {
            return SampleSizeRounding.QUALITY;
        }
    }

    /* loaded from: classes.dex */
    private static class AtMost extends DownsampleStrategy {
        AtMost() {
        }

        @Override // com.bumptech.glide.load.resource.bitmap.DownsampleStrategy
        public float getScaleFactor(int r1, int r2, int r3, int r4) {
            int ceil = (int) Math.ceil(Math.max(r2 / r4, r1 / r3));
            int max = Math.max(1, Integer.highestOneBit(ceil));
            return 1.0f / (max << (max >= ceil ? 0 : 1));
        }

        @Override // com.bumptech.glide.load.resource.bitmap.DownsampleStrategy
        public SampleSizeRounding getSampleSizeRounding(int r1, int r2, int r3, int r4) {
            return SampleSizeRounding.MEMORY;
        }
    }

    /* loaded from: classes.dex */
    private static class None extends DownsampleStrategy {
        @Override // com.bumptech.glide.load.resource.bitmap.DownsampleStrategy
        public float getScaleFactor(int r1, int r2, int r3, int r4) {
            return 1.0f;
        }

        None() {
        }

        @Override // com.bumptech.glide.load.resource.bitmap.DownsampleStrategy
        public SampleSizeRounding getSampleSizeRounding(int r1, int r2, int r3, int r4) {
            return SampleSizeRounding.QUALITY;
        }
    }

    /* loaded from: classes.dex */
    private static class CenterInside extends DownsampleStrategy {
        CenterInside() {
        }

        @Override // com.bumptech.glide.load.resource.bitmap.DownsampleStrategy
        public float getScaleFactor(int r2, int r3, int r4, int r5) {
            return Math.min(1.0f, FIT_CENTER.getScaleFactor(r2, r3, r4, r5));
        }

        @Override // com.bumptech.glide.load.resource.bitmap.DownsampleStrategy
        public SampleSizeRounding getSampleSizeRounding(int r1, int r2, int r3, int r4) {
            return SampleSizeRounding.QUALITY;
        }
    }
}
