package com.google.maps.android.p017ui;

import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.PorterDuff;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import com.google.maps.android.C3346R;

/* renamed from: com.google.maps.android.ui.BubbleDrawable */
/* loaded from: classes3.dex */
class BubbleDrawable extends Drawable {
    private int mColor = -1;
    private final Drawable mMask;
    private final Drawable mShadow;

    @Override // android.graphics.drawable.Drawable
    public int getOpacity() {
        return -3;
    }

    public BubbleDrawable(Resources resources) {
        this.mMask = resources.getDrawable(C3346R.C3348drawable.amu_bubble_mask);
        this.mShadow = resources.getDrawable(C3346R.C3348drawable.amu_bubble_shadow);
    }

    public void setColor(int r1) {
        this.mColor = r1;
    }

    @Override // android.graphics.drawable.Drawable
    public void draw(Canvas canvas) {
        this.mMask.draw(canvas);
        canvas.drawColor(this.mColor, PorterDuff.Mode.SRC_IN);
        this.mShadow.draw(canvas);
    }

    @Override // android.graphics.drawable.Drawable
    public void setAlpha(int r1) {
        throw new UnsupportedOperationException();
    }

    @Override // android.graphics.drawable.Drawable
    public void setColorFilter(ColorFilter colorFilter) {
        throw new UnsupportedOperationException();
    }

    @Override // android.graphics.drawable.Drawable
    public void setBounds(int r2, int r3, int r4, int r5) {
        this.mMask.setBounds(r2, r3, r4, r5);
        this.mShadow.setBounds(r2, r3, r4, r5);
    }

    @Override // android.graphics.drawable.Drawable
    public void setBounds(Rect rect) {
        this.mMask.setBounds(rect);
        this.mShadow.setBounds(rect);
    }

    @Override // android.graphics.drawable.Drawable
    public boolean getPadding(Rect rect) {
        return this.mMask.getPadding(rect);
    }
}
