package com.facebook.drawee.drawable;

import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Matrix;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;
import com.facebook.common.internal.Preconditions;
import javax.annotation.Nullable;

/* loaded from: classes.dex */
public class ArrayDrawable extends Drawable implements Drawable.Callback, TransformCallback, TransformAwareDrawable {
    private final DrawableParent[] mDrawableParents;
    private final Drawable[] mLayers;
    @Nullable
    private TransformCallback mTransformCallback;
    private final DrawableProperties mDrawableProperties = new DrawableProperties();
    private final Rect mTmpRect = new Rect();
    private boolean mIsStateful = false;
    private boolean mIsStatefulCalculated = false;
    private boolean mIsMutated = false;

    public ArrayDrawable(Drawable[] layers) {
        int r0 = 0;
        Preconditions.checkNotNull(layers);
        this.mLayers = layers;
        while (true) {
            Drawable[] drawableArr = this.mLayers;
            if (r0 < drawableArr.length) {
                DrawableUtils.setCallbacks(drawableArr[r0], this, this);
                r0++;
            } else {
                this.mDrawableParents = new DrawableParent[drawableArr.length];
                return;
            }
        }
    }

    public int getNumberOfLayers() {
        return this.mLayers.length;
    }

    @Nullable
    public Drawable getDrawable(int index) {
        Preconditions.checkArgument(Boolean.valueOf(index >= 0));
        Preconditions.checkArgument(Boolean.valueOf(index < this.mLayers.length));
        return this.mLayers[index];
    }

    @Nullable
    public Drawable setDrawable(int index, @Nullable Drawable drawable) {
        Preconditions.checkArgument(Boolean.valueOf(index >= 0));
        Preconditions.checkArgument(Boolean.valueOf(index < this.mLayers.length));
        Drawable drawable2 = this.mLayers[index];
        if (drawable != drawable2) {
            if (drawable != null && this.mIsMutated) {
                drawable.mutate();
            }
            DrawableUtils.setCallbacks(this.mLayers[index], null, null);
            DrawableUtils.setCallbacks(drawable, null, null);
            DrawableUtils.setDrawableProperties(drawable, this.mDrawableProperties);
            DrawableUtils.copyProperties(drawable, this);
            DrawableUtils.setCallbacks(drawable, this, this);
            this.mIsStatefulCalculated = false;
            this.mLayers[index] = drawable;
            invalidateSelf();
        }
        return drawable2;
    }

    @Override // android.graphics.drawable.Drawable
    public int getIntrinsicWidth() {
        int r1 = 0;
        int r2 = -1;
        while (true) {
            Drawable[] drawableArr = this.mLayers;
            if (r1 >= drawableArr.length) {
                break;
            }
            Drawable drawable = drawableArr[r1];
            if (drawable != null) {
                r2 = Math.max(r2, drawable.getIntrinsicWidth());
            }
            r1++;
        }
        if (r2 > 0) {
            return r2;
        }
        return -1;
    }

    @Override // android.graphics.drawable.Drawable
    public int getIntrinsicHeight() {
        int r1 = 0;
        int r2 = -1;
        while (true) {
            Drawable[] drawableArr = this.mLayers;
            if (r1 >= drawableArr.length) {
                break;
            }
            Drawable drawable = drawableArr[r1];
            if (drawable != null) {
                r2 = Math.max(r2, drawable.getIntrinsicHeight());
            }
            r1++;
        }
        if (r2 > 0) {
            return r2;
        }
        return -1;
    }

    @Override // android.graphics.drawable.Drawable
    protected void onBoundsChange(Rect bounds) {
        int r0 = 0;
        while (true) {
            Drawable[] drawableArr = this.mLayers;
            if (r0 >= drawableArr.length) {
                return;
            }
            Drawable drawable = drawableArr[r0];
            if (drawable != null) {
                drawable.setBounds(bounds);
            }
            r0++;
        }
    }

    @Override // android.graphics.drawable.Drawable
    public boolean isStateful() {
        if (!this.mIsStatefulCalculated) {
            this.mIsStateful = false;
            int r1 = 0;
            while (true) {
                Drawable[] drawableArr = this.mLayers;
                boolean z = true;
                if (r1 >= drawableArr.length) {
                    break;
                }
                Drawable drawable = drawableArr[r1];
                boolean z2 = this.mIsStateful;
                if (drawable == null || !drawable.isStateful()) {
                    z = false;
                }
                this.mIsStateful = z2 | z;
                r1++;
            }
            this.mIsStatefulCalculated = true;
        }
        return this.mIsStateful;
    }

    @Override // android.graphics.drawable.Drawable
    protected boolean onStateChange(int[] state) {
        int r0 = 0;
        boolean z = false;
        while (true) {
            Drawable[] drawableArr = this.mLayers;
            if (r0 >= drawableArr.length) {
                return z;
            }
            Drawable drawable = drawableArr[r0];
            if (drawable != null && drawable.setState(state)) {
                z = true;
            }
            r0++;
        }
    }

    @Override // android.graphics.drawable.Drawable
    protected boolean onLevelChange(int level) {
        int r0 = 0;
        boolean z = false;
        while (true) {
            Drawable[] drawableArr = this.mLayers;
            if (r0 >= drawableArr.length) {
                return z;
            }
            Drawable drawable = drawableArr[r0];
            if (drawable != null && drawable.setLevel(level)) {
                z = true;
            }
            r0++;
        }
    }

    @Override // android.graphics.drawable.Drawable
    public void draw(Canvas canvas) {
        int r0 = 0;
        while (true) {
            Drawable[] drawableArr = this.mLayers;
            if (r0 >= drawableArr.length) {
                return;
            }
            Drawable drawable = drawableArr[r0];
            if (drawable != null) {
                drawable.draw(canvas);
            }
            r0++;
        }
    }

    @Override // android.graphics.drawable.Drawable
    public boolean getPadding(Rect padding) {
        int r0 = 0;
        padding.left = 0;
        padding.top = 0;
        padding.right = 0;
        padding.bottom = 0;
        Rect rect = this.mTmpRect;
        while (true) {
            Drawable[] drawableArr = this.mLayers;
            if (r0 >= drawableArr.length) {
                return true;
            }
            Drawable drawable = drawableArr[r0];
            if (drawable != null) {
                drawable.getPadding(rect);
                padding.left = Math.max(padding.left, rect.left);
                padding.top = Math.max(padding.top, rect.top);
                padding.right = Math.max(padding.right, rect.right);
                padding.bottom = Math.max(padding.bottom, rect.bottom);
            }
            r0++;
        }
    }

    @Override // android.graphics.drawable.Drawable
    public Drawable mutate() {
        int r0 = 0;
        while (true) {
            Drawable[] drawableArr = this.mLayers;
            if (r0 < drawableArr.length) {
                Drawable drawable = drawableArr[r0];
                if (drawable != null) {
                    drawable.mutate();
                }
                r0++;
            } else {
                this.mIsMutated = true;
                return this;
            }
        }
    }

    @Override // android.graphics.drawable.Drawable
    public int getOpacity() {
        if (this.mLayers.length == 0) {
            return -2;
        }
        int r0 = -1;
        int r1 = 1;
        while (true) {
            Drawable[] drawableArr = this.mLayers;
            if (r1 >= drawableArr.length) {
                return r0;
            }
            Drawable drawable = drawableArr[r1];
            if (drawable != null) {
                r0 = Drawable.resolveOpacity(r0, drawable.getOpacity());
            }
            r1++;
        }
    }

    @Override // android.graphics.drawable.Drawable
    public void setAlpha(int alpha) {
        this.mDrawableProperties.setAlpha(alpha);
        int r0 = 0;
        while (true) {
            Drawable[] drawableArr = this.mLayers;
            if (r0 >= drawableArr.length) {
                return;
            }
            Drawable drawable = drawableArr[r0];
            if (drawable != null) {
                drawable.setAlpha(alpha);
            }
            r0++;
        }
    }

    @Override // android.graphics.drawable.Drawable
    public void setColorFilter(ColorFilter colorFilter) {
        this.mDrawableProperties.setColorFilter(colorFilter);
        int r0 = 0;
        while (true) {
            Drawable[] drawableArr = this.mLayers;
            if (r0 >= drawableArr.length) {
                return;
            }
            Drawable drawable = drawableArr[r0];
            if (drawable != null) {
                drawable.setColorFilter(colorFilter);
            }
            r0++;
        }
    }

    @Override // android.graphics.drawable.Drawable
    public void setDither(boolean dither) {
        this.mDrawableProperties.setDither(dither);
        int r0 = 0;
        while (true) {
            Drawable[] drawableArr = this.mLayers;
            if (r0 >= drawableArr.length) {
                return;
            }
            Drawable drawable = drawableArr[r0];
            if (drawable != null) {
                drawable.setDither(dither);
            }
            r0++;
        }
    }

    @Override // android.graphics.drawable.Drawable
    public void setFilterBitmap(boolean filterBitmap) {
        this.mDrawableProperties.setFilterBitmap(filterBitmap);
        int r0 = 0;
        while (true) {
            Drawable[] drawableArr = this.mLayers;
            if (r0 >= drawableArr.length) {
                return;
            }
            Drawable drawable = drawableArr[r0];
            if (drawable != null) {
                drawable.setFilterBitmap(filterBitmap);
            }
            r0++;
        }
    }

    @Override // android.graphics.drawable.Drawable
    public boolean setVisible(boolean visible, boolean restart) {
        boolean visible2 = super.setVisible(visible, restart);
        int r1 = 0;
        while (true) {
            Drawable[] drawableArr = this.mLayers;
            if (r1 >= drawableArr.length) {
                return visible2;
            }
            Drawable drawable = drawableArr[r1];
            if (drawable != null) {
                drawable.setVisible(visible, restart);
            }
            r1++;
        }
    }

    public DrawableParent getDrawableParentForIndex(int index) {
        Preconditions.checkArgument(Boolean.valueOf(index >= 0));
        Preconditions.checkArgument(Boolean.valueOf(index < this.mDrawableParents.length));
        DrawableParent[] drawableParentArr = this.mDrawableParents;
        if (drawableParentArr[index] == null) {
            drawableParentArr[index] = createDrawableParentForIndex(index);
        }
        return this.mDrawableParents[index];
    }

    private DrawableParent createDrawableParentForIndex(final int index) {
        return new DrawableParent() { // from class: com.facebook.drawee.drawable.ArrayDrawable.1
            @Override // com.facebook.drawee.drawable.DrawableParent
            public Drawable setDrawable(Drawable newDrawable) {
                return ArrayDrawable.this.setDrawable(index, newDrawable);
            }

            @Override // com.facebook.drawee.drawable.DrawableParent
            public Drawable getDrawable() {
                return ArrayDrawable.this.getDrawable(index);
            }
        };
    }

    @Override // android.graphics.drawable.Drawable.Callback
    public void invalidateDrawable(Drawable who) {
        invalidateSelf();
    }

    @Override // android.graphics.drawable.Drawable.Callback
    public void scheduleDrawable(Drawable who, Runnable what, long when) {
        scheduleSelf(what, when);
    }

    @Override // android.graphics.drawable.Drawable.Callback
    public void unscheduleDrawable(Drawable who, Runnable what) {
        unscheduleSelf(what);
    }

    @Override // com.facebook.drawee.drawable.TransformAwareDrawable
    public void setTransformCallback(TransformCallback transformCallback) {
        this.mTransformCallback = transformCallback;
    }

    @Override // com.facebook.drawee.drawable.TransformCallback
    public void getTransform(Matrix transform) {
        TransformCallback transformCallback = this.mTransformCallback;
        if (transformCallback != null) {
            transformCallback.getTransform(transform);
        } else {
            transform.reset();
        }
    }

    @Override // com.facebook.drawee.drawable.TransformCallback
    public void getRootBounds(RectF bounds) {
        TransformCallback transformCallback = this.mTransformCallback;
        if (transformCallback != null) {
            transformCallback.getRootBounds(bounds);
        } else {
            bounds.set(getBounds());
        }
    }

    @Override // android.graphics.drawable.Drawable
    public void setHotspot(float x, float y) {
        int r0 = 0;
        while (true) {
            Drawable[] drawableArr = this.mLayers;
            if (r0 >= drawableArr.length) {
                return;
            }
            Drawable drawable = drawableArr[r0];
            if (drawable != null) {
                drawable.setHotspot(x, y);
            }
            r0++;
        }
    }
}
