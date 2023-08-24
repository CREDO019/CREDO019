package com.facebook.drawee.drawable;

import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;
import com.facebook.imagepipeline.common.RotationOptions;

/* loaded from: classes.dex */
public class OrientedDrawable extends ForwardingDrawable {
    private int mExifOrientation;
    private int mRotationAngle;
    final Matrix mRotationMatrix;
    private final Matrix mTempMatrix;
    private final RectF mTempRectF;

    public OrientedDrawable(Drawable drawable, int rotationAngle) {
        this(drawable, rotationAngle, 0);
    }

    public OrientedDrawable(Drawable drawable, int rotationAngle, int exifOrientation) {
        super(drawable);
        this.mTempMatrix = new Matrix();
        this.mTempRectF = new RectF();
        this.mRotationMatrix = new Matrix();
        this.mRotationAngle = rotationAngle - (rotationAngle % 90);
        exifOrientation = (exifOrientation < 0 || exifOrientation > 8) ? 0 : 0;
        this.mExifOrientation = exifOrientation;
    }

    @Override // com.facebook.drawee.drawable.ForwardingDrawable, android.graphics.drawable.Drawable
    public void draw(Canvas canvas) {
        int r0;
        if (this.mRotationAngle <= 0 && ((r0 = this.mExifOrientation) == 0 || r0 == 1)) {
            super.draw(canvas);
            return;
        }
        int save = canvas.save();
        canvas.concat(this.mRotationMatrix);
        super.draw(canvas);
        canvas.restoreToCount(save);
    }

    @Override // com.facebook.drawee.drawable.ForwardingDrawable, android.graphics.drawable.Drawable
    public int getIntrinsicWidth() {
        int r0 = this.mExifOrientation;
        if (r0 == 5 || r0 == 7 || this.mRotationAngle % RotationOptions.ROTATE_180 != 0) {
            return super.getIntrinsicHeight();
        }
        return super.getIntrinsicWidth();
    }

    @Override // com.facebook.drawee.drawable.ForwardingDrawable, android.graphics.drawable.Drawable
    public int getIntrinsicHeight() {
        int r0 = this.mExifOrientation;
        if (r0 == 5 || r0 == 7 || this.mRotationAngle % RotationOptions.ROTATE_180 != 0) {
            return super.getIntrinsicWidth();
        }
        return super.getIntrinsicHeight();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.facebook.drawee.drawable.ForwardingDrawable, android.graphics.drawable.Drawable
    public void onBoundsChange(Rect bounds) {
        int r2;
        Drawable current = getCurrent();
        int r1 = this.mRotationAngle;
        if (r1 > 0 || ((r2 = this.mExifOrientation) != 0 && r2 != 1)) {
            int r22 = this.mExifOrientation;
            if (r22 == 2) {
                this.mRotationMatrix.setScale(-1.0f, 1.0f);
            } else if (r22 == 7) {
                this.mRotationMatrix.setRotate(270.0f, bounds.centerX(), bounds.centerY());
                this.mRotationMatrix.postScale(-1.0f, 1.0f);
            } else if (r22 == 4) {
                this.mRotationMatrix.setScale(1.0f, -1.0f);
            } else if (r22 == 5) {
                this.mRotationMatrix.setRotate(270.0f, bounds.centerX(), bounds.centerY());
                this.mRotationMatrix.postScale(1.0f, -1.0f);
            } else {
                this.mRotationMatrix.setRotate(r1, bounds.centerX(), bounds.centerY());
            }
            this.mTempMatrix.reset();
            this.mRotationMatrix.invert(this.mTempMatrix);
            this.mTempRectF.set(bounds);
            this.mTempMatrix.mapRect(this.mTempRectF);
            current.setBounds((int) this.mTempRectF.left, (int) this.mTempRectF.top, (int) this.mTempRectF.right, (int) this.mTempRectF.bottom);
            return;
        }
        current.setBounds(bounds);
    }

    @Override // com.facebook.drawee.drawable.ForwardingDrawable, com.facebook.drawee.drawable.TransformCallback
    public void getTransform(Matrix transform) {
        getParentTransform(transform);
        if (this.mRotationMatrix.isIdentity()) {
            return;
        }
        transform.preConcat(this.mRotationMatrix);
    }
}
