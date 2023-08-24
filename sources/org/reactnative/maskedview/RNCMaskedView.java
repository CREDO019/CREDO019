package org.reactnative.maskedview;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.view.View;
import com.facebook.react.views.view.ReactViewGroup;

/* loaded from: classes5.dex */
public class RNCMaskedView extends ReactViewGroup {
    private static final String TAG = "RNCMaskedView";
    private Bitmap mBitmapMask;
    private Paint mPaint;
    private PorterDuffXfermode mPorterDuffXferMode;

    public RNCMaskedView(Context context) {
        super(context);
        this.mBitmapMask = null;
        setLayerType(1, null);
        this.mPaint = new Paint(1);
        this.mPorterDuffXferMode = new PorterDuffXfermode(PorterDuff.Mode.DST_IN);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.facebook.react.views.view.ReactViewGroup, android.view.ViewGroup, android.view.View
    public void dispatchDraw(Canvas canvas) {
        super.dispatchDraw(canvas);
        updateBitmapMask();
        if (this.mBitmapMask != null) {
            this.mPaint.setXfermode(this.mPorterDuffXferMode);
            canvas.drawBitmap(this.mBitmapMask, 0.0f, 0.0f, this.mPaint);
            this.mPaint.setXfermode(null);
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.facebook.react.views.view.ReactViewGroup, android.view.ViewGroup, android.view.View
    public void onLayout(boolean z, int r2, int r3, int r4, int r5) {
        super.onLayout(z, r2, r3, r4, r5);
        if (z) {
            updateBitmapMask();
        }
    }

    private void updateBitmapMask() {
        Bitmap bitmap = this.mBitmapMask;
        if (bitmap != null) {
            bitmap.recycle();
        }
        View childAt = getChildAt(0);
        childAt.setVisibility(0);
        this.mBitmapMask = getBitmapFromView(childAt);
        childAt.setVisibility(4);
    }

    public static Bitmap getBitmapFromView(View view) {
        view.layout(0, 0, view.getMeasuredWidth(), view.getMeasuredHeight());
        if (view.getMeasuredWidth() <= 0 || view.getMeasuredHeight() <= 0) {
            return null;
        }
        Bitmap createBitmap = Bitmap.createBitmap(view.getMeasuredWidth(), view.getMeasuredHeight(), Bitmap.Config.ARGB_8888);
        view.draw(new Canvas(createBitmap));
        return createBitmap;
    }
}
