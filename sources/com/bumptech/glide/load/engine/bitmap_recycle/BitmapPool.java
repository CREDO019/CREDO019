package com.bumptech.glide.load.engine.bitmap_recycle;

import android.graphics.Bitmap;

/* loaded from: classes.dex */
public interface BitmapPool {
    void clearMemory();

    Bitmap get(int r1, int r2, Bitmap.Config config);

    Bitmap getDirty(int r1, int r2, Bitmap.Config config);

    long getMaxSize();

    void put(Bitmap bitmap);

    void setSizeMultiplier(float f);

    void trimMemory(int r1);
}
