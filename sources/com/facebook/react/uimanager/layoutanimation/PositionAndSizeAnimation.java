package com.facebook.react.uimanager.layoutanimation;

import android.view.View;
import android.view.animation.Animation;
import android.view.animation.Transformation;

/* loaded from: classes.dex */
class PositionAndSizeAnimation extends Animation implements LayoutHandlingAnimation {
    private int mDeltaHeight;
    private int mDeltaWidth;
    private float mDeltaX;
    private float mDeltaY;
    private int mStartHeight;
    private int mStartWidth;
    private float mStartX;
    private float mStartY;
    private final View mView;

    @Override // android.view.animation.Animation
    public boolean willChangeBounds() {
        return true;
    }

    public PositionAndSizeAnimation(View view, int r2, int r3, int r4, int r5) {
        this.mView = view;
        calculateAnimation(r2, r3, r4, r5);
    }

    @Override // android.view.animation.Animation
    protected void applyTransformation(float f, Transformation transformation) {
        float f2 = this.mStartX + (this.mDeltaX * f);
        float f3 = this.mStartY + (this.mDeltaY * f);
        this.mView.layout(Math.round(f2), Math.round(f3), Math.round(f2 + this.mStartWidth + (this.mDeltaWidth * f)), Math.round(f3 + this.mStartHeight + (this.mDeltaHeight * f)));
    }

    @Override // com.facebook.react.uimanager.layoutanimation.LayoutHandlingAnimation
    public void onLayoutUpdate(int r1, int r2, int r3, int r4) {
        calculateAnimation(r1, r2, r3, r4);
    }

    private void calculateAnimation(int r3, int r4, int r5, int r6) {
        this.mStartX = this.mView.getX() - this.mView.getTranslationX();
        this.mStartY = this.mView.getY() - this.mView.getTranslationY();
        this.mStartWidth = this.mView.getWidth();
        int height = this.mView.getHeight();
        this.mStartHeight = height;
        this.mDeltaX = r3 - this.mStartX;
        this.mDeltaY = r4 - this.mStartY;
        this.mDeltaWidth = r5 - this.mStartWidth;
        this.mDeltaHeight = r6 - height;
    }
}
