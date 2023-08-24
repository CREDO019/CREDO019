package com.google.maps.android.p017ui;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.widget.FrameLayout;
import com.canhub.cropper.CropImageOptions;

/* renamed from: com.google.maps.android.ui.RotationLayout */
/* loaded from: classes3.dex */
public class RotationLayout extends FrameLayout {
    private int mRotation;

    public RotationLayout(Context context) {
        super(context);
    }

    public RotationLayout(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public RotationLayout(Context context, AttributeSet attributeSet, int r3) {
        super(context, attributeSet, r3);
    }

    @Override // android.widget.FrameLayout, android.view.View
    protected void onMeasure(int r3, int r4) {
        int r0 = this.mRotation;
        if (r0 == 1 || r0 == 3) {
            super.onMeasure(r3, r4);
            setMeasuredDimension(getMeasuredHeight(), getMeasuredWidth());
            return;
        }
        super.onMeasure(r3, r4);
    }

    public void setViewRotation(int r1) {
        this.mRotation = ((r1 + CropImageOptions.DEGREES_360) % CropImageOptions.DEGREES_360) / 90;
    }

    @Override // android.view.ViewGroup, android.view.View
    public void dispatchDraw(Canvas canvas) {
        int r0 = this.mRotation;
        if (r0 == 0) {
            super.dispatchDraw(canvas);
            return;
        }
        if (r0 == 1) {
            canvas.translate(getWidth(), 0.0f);
            canvas.rotate(90.0f, getWidth() / 2, 0.0f);
            canvas.translate(getHeight() / 2, getWidth() / 2);
        } else if (r0 == 2) {
            canvas.rotate(180.0f, getWidth() / 2, getHeight() / 2);
        } else {
            canvas.translate(0.0f, getHeight());
            canvas.rotate(270.0f, getWidth() / 2, 0.0f);
            canvas.translate(getHeight() / 2, (-getWidth()) / 2);
        }
        super.dispatchDraw(canvas);
    }
}
