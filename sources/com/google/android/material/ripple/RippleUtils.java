package com.google.android.material.ripple;

import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Build;
import android.util.Log;
import android.util.StateSet;
import androidx.core.graphics.ColorUtils;

/* loaded from: classes3.dex */
public class RippleUtils {
    private static final int[] ENABLED_PRESSED_STATE_SET;
    private static final int[] FOCUSED_STATE_SET;
    private static final int[] HOVERED_FOCUSED_STATE_SET;
    private static final int[] HOVERED_STATE_SET;
    static final String LOG_TAG;
    private static final int[] PRESSED_STATE_SET;
    private static final int[] SELECTED_FOCUSED_STATE_SET;
    private static final int[] SELECTED_HOVERED_FOCUSED_STATE_SET;
    private static final int[] SELECTED_HOVERED_STATE_SET;
    private static final int[] SELECTED_PRESSED_STATE_SET;
    private static final int[] SELECTED_STATE_SET;
    static final String TRANSPARENT_DEFAULT_COLOR_WARNING = "Use a non-transparent color for the default color as it will be used to finish ripple animations.";
    public static final boolean USE_FRAMEWORK_RIPPLE;

    static {
        USE_FRAMEWORK_RIPPLE = Build.VERSION.SDK_INT >= 21;
        PRESSED_STATE_SET = new int[]{16842919};
        HOVERED_FOCUSED_STATE_SET = new int[]{16843623, 16842908};
        FOCUSED_STATE_SET = new int[]{16842908};
        HOVERED_STATE_SET = new int[]{16843623};
        SELECTED_PRESSED_STATE_SET = new int[]{16842913, 16842919};
        SELECTED_HOVERED_FOCUSED_STATE_SET = new int[]{16842913, 16843623, 16842908};
        SELECTED_FOCUSED_STATE_SET = new int[]{16842913, 16842908};
        SELECTED_HOVERED_STATE_SET = new int[]{16842913, 16843623};
        SELECTED_STATE_SET = new int[]{16842913};
        ENABLED_PRESSED_STATE_SET = new int[]{16842910, 16842919};
        LOG_TAG = "RippleUtils";
    }

    private RippleUtils() {
    }

    public static ColorStateList convertToRippleDrawableColor(ColorStateList colorStateList) {
        if (USE_FRAMEWORK_RIPPLE) {
            return new ColorStateList(new int[][]{SELECTED_STATE_SET, StateSet.NOTHING}, new int[]{getColorForState(colorStateList, SELECTED_PRESSED_STATE_SET), getColorForState(colorStateList, PRESSED_STATE_SET)});
        }
        int[] r5 = SELECTED_PRESSED_STATE_SET;
        int[] r52 = SELECTED_HOVERED_FOCUSED_STATE_SET;
        int[] r2 = SELECTED_FOCUSED_STATE_SET;
        int[] r22 = SELECTED_HOVERED_STATE_SET;
        int[] r23 = PRESSED_STATE_SET;
        int[] r24 = HOVERED_FOCUSED_STATE_SET;
        int[] r25 = FOCUSED_STATE_SET;
        int[] r26 = HOVERED_STATE_SET;
        return new ColorStateList(new int[][]{r5, r52, r2, r22, SELECTED_STATE_SET, r23, r24, r25, r26, StateSet.NOTHING}, new int[]{getColorForState(colorStateList, r5), getColorForState(colorStateList, r52), getColorForState(colorStateList, r2), getColorForState(colorStateList, r22), 0, getColorForState(colorStateList, r23), getColorForState(colorStateList, r24), getColorForState(colorStateList, r25), getColorForState(colorStateList, r26), 0});
    }

    public static ColorStateList sanitizeRippleDrawableColor(ColorStateList colorStateList) {
        if (colorStateList != null) {
            if (Build.VERSION.SDK_INT >= 22 && Build.VERSION.SDK_INT <= 27 && Color.alpha(colorStateList.getDefaultColor()) == 0 && Color.alpha(colorStateList.getColorForState(ENABLED_PRESSED_STATE_SET, 0)) != 0) {
                Log.w(LOG_TAG, TRANSPARENT_DEFAULT_COLOR_WARNING);
            }
            return colorStateList;
        }
        return ColorStateList.valueOf(0);
    }

    public static boolean shouldDrawRippleCompat(int[] r8) {
        boolean z = false;
        boolean z2 = false;
        for (int r6 : r8) {
            if (r6 == 16842910) {
                z = true;
            } else if (r6 == 16842908 || r6 == 16842919 || r6 == 16843623) {
                z2 = true;
            }
        }
        return z && z2;
    }

    private static int getColorForState(ColorStateList colorStateList, int[] r2) {
        int colorForState = colorStateList != null ? colorStateList.getColorForState(r2, colorStateList.getDefaultColor()) : 0;
        return USE_FRAMEWORK_RIPPLE ? doubleAlpha(colorForState) : colorForState;
    }

    private static int doubleAlpha(int r2) {
        return ColorUtils.setAlphaComponent(r2, Math.min(Color.alpha(r2) * 2, 255));
    }
}
