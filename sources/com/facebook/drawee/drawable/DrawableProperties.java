package com.facebook.drawee.drawable;

import android.graphics.ColorFilter;
import android.graphics.drawable.Drawable;
import javax.annotation.Nullable;

/* loaded from: classes.dex */
public class DrawableProperties {
    private static final int UNSET = -1;
    private int mAlpha = -1;
    private boolean mIsSetColorFilter = false;
    @Nullable
    private ColorFilter mColorFilter = null;
    private int mDither = -1;
    private int mFilterBitmap = -1;

    public void setAlpha(int alpha) {
        this.mAlpha = alpha;
    }

    public void setColorFilter(@Nullable ColorFilter colorFilter) {
        this.mColorFilter = colorFilter;
        this.mIsSetColorFilter = colorFilter != null;
    }

    public void setDither(boolean dither) {
        this.mDither = dither ? 1 : 0;
    }

    public void setFilterBitmap(boolean filterBitmap) {
        this.mFilterBitmap = filterBitmap ? 1 : 0;
    }

    public void applyTo(Drawable drawable) {
        if (drawable == null) {
            return;
        }
        int r0 = this.mAlpha;
        if (r0 != -1) {
            drawable.setAlpha(r0);
        }
        if (this.mIsSetColorFilter) {
            drawable.setColorFilter(this.mColorFilter);
        }
        int r02 = this.mDither;
        if (r02 != -1) {
            drawable.setDither(r02 != 0);
        }
        int r03 = this.mFilterBitmap;
        if (r03 != -1) {
            drawable.setFilterBitmap(r03 != 0);
        }
    }
}
