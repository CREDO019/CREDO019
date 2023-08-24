package com.canhub.cropper;

import android.graphics.Matrix;
import android.graphics.RectF;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.Animation;
import android.view.animation.Transformation;
import android.widget.ImageView;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: CropImageAnimation.kt */
@Metadata(m184d1 = {"\u0000F\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0014\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u0007\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0000\u0018\u00002\u00020\u00012\u00020\u0002B\u0015\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\u0006\u0010\u0005\u001a\u00020\u0006¢\u0006\u0002\u0010\u0007J\u0018\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u00132\u0006\u0010\u0014\u001a\u00020\u0015H\u0014J\u0010\u0010\u0016\u001a\u00020\u00112\u0006\u0010\u0017\u001a\u00020\u0001H\u0016J\u0010\u0010\u0018\u001a\u00020\u00112\u0006\u0010\u0017\u001a\u00020\u0001H\u0016J\u0010\u0010\u0019\u001a\u00020\u00112\u0006\u0010\u0017\u001a\u00020\u0001H\u0016J\u0016\u0010\u001a\u001a\u00020\u00112\u0006\u0010\u001b\u001a\u00020\t2\u0006\u0010\u001c\u001a\u00020\u001dJ\u0016\u0010\u001e\u001a\u00020\u00112\u0006\u0010\u001b\u001a\u00020\t2\u0006\u0010\u001c\u001a\u00020\u001dR\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u000bX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\tX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\tX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\u000bX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u000f\u001a\u00020\tX\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u001f"}, m183d2 = {"Lcom/canhub/cropper/CropImageAnimation;", "Landroid/view/animation/Animation;", "Landroid/view/animation/Animation$AnimationListener;", "imageView", "Landroid/widget/ImageView;", "cropOverlayView", "Lcom/canhub/cropper/CropOverlayView;", "(Landroid/widget/ImageView;Lcom/canhub/cropper/CropOverlayView;)V", "endBoundPoints", "", "endCropWindowRect", "Landroid/graphics/RectF;", "endImageMatrix", "startBoundPoints", "startCropWindowRect", "startImageMatrix", "applyTransformation", "", "interpolatedTime", "", "t", "Landroid/view/animation/Transformation;", "onAnimationEnd", "animation", "onAnimationRepeat", "onAnimationStart", "setEndState", "boundPoints", "imageMatrix", "Landroid/graphics/Matrix;", "setStartState", "cropper_release"}, m182k = 1, m181mv = {1, 5, 1}, m179xi = 48)
/* loaded from: classes.dex */
public final class CropImageAnimation extends Animation implements Animation.AnimationListener {
    private final CropOverlayView cropOverlayView;
    private final float[] endBoundPoints;
    private final RectF endCropWindowRect;
    private final float[] endImageMatrix;
    private final ImageView imageView;
    private final float[] startBoundPoints;
    private final RectF startCropWindowRect;
    private final float[] startImageMatrix;

    @Override // android.view.animation.Animation.AnimationListener
    public void onAnimationRepeat(Animation animation) {
        Intrinsics.checkNotNullParameter(animation, "animation");
    }

    @Override // android.view.animation.Animation.AnimationListener
    public void onAnimationStart(Animation animation) {
        Intrinsics.checkNotNullParameter(animation, "animation");
    }

    public CropImageAnimation(ImageView imageView, CropOverlayView cropOverlayView) {
        Intrinsics.checkNotNullParameter(imageView, "imageView");
        Intrinsics.checkNotNullParameter(cropOverlayView, "cropOverlayView");
        this.imageView = imageView;
        this.cropOverlayView = cropOverlayView;
        this.startBoundPoints = new float[8];
        this.endBoundPoints = new float[8];
        this.startCropWindowRect = new RectF();
        this.endCropWindowRect = new RectF();
        this.startImageMatrix = new float[9];
        this.endImageMatrix = new float[9];
        setDuration(300L);
        setFillAfter(true);
        setInterpolator(new AccelerateDecelerateInterpolator());
        setAnimationListener(this);
    }

    public final void setStartState(float[] boundPoints, Matrix imageMatrix) {
        Intrinsics.checkNotNullParameter(boundPoints, "boundPoints");
        Intrinsics.checkNotNullParameter(imageMatrix, "imageMatrix");
        reset();
        System.arraycopy(boundPoints, 0, this.startBoundPoints, 0, 8);
        this.startCropWindowRect.set(this.cropOverlayView.getCropWindowRect());
        imageMatrix.getValues(this.startImageMatrix);
    }

    public final void setEndState(float[] boundPoints, Matrix imageMatrix) {
        Intrinsics.checkNotNullParameter(boundPoints, "boundPoints");
        Intrinsics.checkNotNullParameter(imageMatrix, "imageMatrix");
        System.arraycopy(boundPoints, 0, this.endBoundPoints, 0, 8);
        this.endCropWindowRect.set(this.cropOverlayView.getCropWindowRect());
        imageMatrix.getValues(this.endImageMatrix);
    }

    @Override // android.view.animation.Animation
    protected void applyTransformation(float f, Transformation t) {
        Intrinsics.checkNotNullParameter(t, "t");
        RectF rectF = new RectF();
        rectF.left = this.startCropWindowRect.left + ((this.endCropWindowRect.left - this.startCropWindowRect.left) * f);
        rectF.top = this.startCropWindowRect.top + ((this.endCropWindowRect.top - this.startCropWindowRect.top) * f);
        rectF.right = this.startCropWindowRect.right + ((this.endCropWindowRect.right - this.startCropWindowRect.right) * f);
        rectF.bottom = this.startCropWindowRect.bottom + ((this.endCropWindowRect.bottom - this.startCropWindowRect.bottom) * f);
        float[] fArr = new float[8];
        int r2 = 0;
        int r3 = 0;
        while (true) {
            int r4 = r3 + 1;
            float[] fArr2 = this.startBoundPoints;
            fArr[r3] = fArr2[r3] + ((this.endBoundPoints[r3] - fArr2[r3]) * f);
            if (r4 > 7) {
                break;
            }
            r3 = r4;
        }
        CropOverlayView cropOverlayView = this.cropOverlayView;
        cropOverlayView.setCropWindowRect(rectF);
        cropOverlayView.setBounds(fArr, this.imageView.getWidth(), this.imageView.getHeight());
        cropOverlayView.invalidate();
        float[] fArr3 = new float[9];
        while (true) {
            int r10 = r2 + 1;
            float[] fArr4 = this.startImageMatrix;
            fArr3[r2] = fArr4[r2] + ((this.endImageMatrix[r2] - fArr4[r2]) * f);
            if (r10 > 8) {
                ImageView imageView = this.imageView;
                imageView.getImageMatrix().setValues(fArr3);
                imageView.invalidate();
                return;
            }
            r2 = r10;
        }
    }

    @Override // android.view.animation.Animation.AnimationListener
    public void onAnimationEnd(Animation animation) {
        Intrinsics.checkNotNullParameter(animation, "animation");
        this.imageView.clearAnimation();
    }
}
