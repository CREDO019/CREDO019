package com.google.android.material.color;

import android.content.Context;
import android.graphics.Color;
import android.util.TypedValue;
import android.view.View;
import androidx.core.graphics.ColorUtils;
import com.google.android.material.resources.MaterialAttributes;

/* loaded from: classes3.dex */
public class MaterialColors {
    public static final float ALPHA_DISABLED = 0.38f;
    public static final float ALPHA_DISABLED_LOW = 0.12f;
    public static final float ALPHA_FULL = 1.0f;
    public static final float ALPHA_LOW = 0.32f;
    public static final float ALPHA_MEDIUM = 0.54f;

    public static int getColor(View view, int r1) {
        return MaterialAttributes.resolveOrThrow(view, r1);
    }

    public static int getColor(Context context, int r1, String str) {
        return MaterialAttributes.resolveOrThrow(context, r1, str);
    }

    public static int getColor(View view, int r1, int r2) {
        return getColor(view.getContext(), r1, r2);
    }

    public static int getColor(Context context, int r1, int r2) {
        TypedValue resolve = MaterialAttributes.resolve(context, r1);
        return resolve != null ? resolve.data : r2;
    }

    public static int layer(View view, int r2, int r3) {
        return layer(view, r2, r3, 1.0f);
    }

    public static int layer(View view, int r1, int r2, float f) {
        return layer(getColor(view, r1), getColor(view, r2), f);
    }

    public static int layer(int r1, int r2, float f) {
        return layer(r1, ColorUtils.setAlphaComponent(r2, Math.round(Color.alpha(r2) * f)));
    }

    public static int layer(int r0, int r1) {
        return ColorUtils.compositeColors(r1, r0);
    }
}
