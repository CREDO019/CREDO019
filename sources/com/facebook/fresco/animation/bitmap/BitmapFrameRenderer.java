package com.facebook.fresco.animation.bitmap;

import android.graphics.Bitmap;
import android.graphics.Rect;
import javax.annotation.Nullable;

/* loaded from: classes.dex */
public interface BitmapFrameRenderer {
    int getIntrinsicHeight();

    int getIntrinsicWidth();

    boolean renderFrame(int frameNumber, Bitmap targetBitmap);

    void setBounds(@Nullable Rect bounds);
}
