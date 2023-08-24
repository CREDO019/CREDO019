package com.facebook.fresco.animation.backend;

import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import javax.annotation.Nullable;

/* loaded from: classes.dex */
public interface AnimationBackend extends AnimationInformation {
    public static final int INTRINSIC_DIMENSION_UNSET = -1;

    void clear();

    boolean drawFrame(Drawable parent, Canvas canvas, int frameNumber);

    int getIntrinsicHeight();

    int getIntrinsicWidth();

    int getSizeInBytes();

    void setAlpha(int alpha);

    void setBounds(Rect bounds);

    void setColorFilter(@Nullable ColorFilter colorFilter);
}
