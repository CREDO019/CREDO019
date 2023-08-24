package com.google.android.material.canvas;

import android.graphics.Canvas;
import android.graphics.RectF;
import android.os.Build;

/* loaded from: classes3.dex */
public class CanvasCompat {
    private CanvasCompat() {
    }

    public static int saveLayerAlpha(Canvas canvas, RectF rectF, int r4) {
        if (Build.VERSION.SDK_INT > 21) {
            return canvas.saveLayerAlpha(rectF, r4);
        }
        return canvas.saveLayerAlpha(rectF, r4, 31);
    }

    public static int saveLayerAlpha(Canvas canvas, float f, float f2, float f3, float f4, int r12) {
        if (Build.VERSION.SDK_INT > 21) {
            return canvas.saveLayerAlpha(f, f2, f3, f4, r12);
        }
        return canvas.saveLayerAlpha(f, f2, f3, f4, r12, 31);
    }
}
