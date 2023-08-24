package com.bumptech.glide.util;

import com.bumptech.glide.ListPreloader;

/* loaded from: classes.dex */
public class FixedPreloadSizeProvider<T> implements ListPreloader.PreloadSizeProvider<T> {
    private final int[] size;

    public FixedPreloadSizeProvider(int r3, int r4) {
        this.size = new int[]{r3, r4};
    }

    @Override // com.bumptech.glide.ListPreloader.PreloadSizeProvider
    public int[] getPreloadSize(T t, int r2, int r3) {
        return this.size;
    }
}
