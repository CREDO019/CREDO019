package com.facebook.react.fabric.mounting;

import android.view.View;
import com.facebook.react.uimanager.PixelUtil;
import com.facebook.yoga.YogaMeasureMode;

/* loaded from: classes.dex */
public interface LayoutMetricsConversions {

    /* renamed from: com.facebook.react.fabric.mounting.LayoutMetricsConversions$-CC  reason: invalid class name */
    /* loaded from: classes.dex */
    public final /* synthetic */ class CC {
        public static float getMinSize(int r2) {
            int mode = View.MeasureSpec.getMode(r2);
            int size = View.MeasureSpec.getSize(r2);
            if (mode == 1073741824) {
                return size;
            }
            return 0.0f;
        }

        public static float getMaxSize(int r1) {
            int mode = View.MeasureSpec.getMode(r1);
            int size = View.MeasureSpec.getSize(r1);
            if (mode == 0) {
                return Float.POSITIVE_INFINITY;
            }
            return size;
        }

        public static float getYogaSize(float f, float f2) {
            if (f == f2) {
                return PixelUtil.toPixelFromDIP(f2);
            }
            if (Float.isInfinite(f2)) {
                return Float.POSITIVE_INFINITY;
            }
            return PixelUtil.toPixelFromDIP(f2);
        }

        public static YogaMeasureMode getYogaMeasureMode(float f, float f2) {
            if (f == f2) {
                return YogaMeasureMode.EXACTLY;
            }
            if (Float.isInfinite(f2)) {
                return YogaMeasureMode.UNDEFINED;
            }
            return YogaMeasureMode.AT_MOST;
        }
    }
}
