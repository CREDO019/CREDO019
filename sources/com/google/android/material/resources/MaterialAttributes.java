package com.google.android.material.resources;

import android.content.Context;
import android.util.TypedValue;
import android.view.View;
import com.google.android.material.C2151R;

/* loaded from: classes3.dex */
public class MaterialAttributes {
    public static TypedValue resolve(Context context, int r3) {
        TypedValue typedValue = new TypedValue();
        if (context.getTheme().resolveAttribute(r3, typedValue, true)) {
            return typedValue;
        }
        return null;
    }

    public static int resolveOrThrow(Context context, int r4, String str) {
        TypedValue resolve = resolve(context, r4);
        if (resolve == null) {
            throw new IllegalArgumentException(String.format("%1$s requires a value for the %2$s attribute to be set in your app theme. You can either set the attribute in your theme or update your theme to inherit from Theme.MaterialComponents (or a descendant).", str, context.getResources().getResourceName(r4)));
        }
        return resolve.data;
    }

    public static int resolveOrThrow(View view, int r2) {
        return resolveOrThrow(view.getContext(), r2, view.getClass().getCanonicalName());
    }

    public static boolean resolveBooleanOrThrow(Context context, int r1, String str) {
        return resolveOrThrow(context, r1, str) != 0;
    }

    public static boolean resolveBoolean(Context context, int r2, boolean z) {
        TypedValue resolve = resolve(context, r2);
        return (resolve == null || resolve.type != 18) ? z : resolve.data != 0;
    }

    public static int resolveMinimumAccessibleTouchTarget(Context context) {
        return resolveDimension(context, C2151R.attr.minTouchTargetSize, C2151R.dimen.mtrl_min_touch_target_size);
    }

    public static int resolveDimension(Context context, int r3, int r4) {
        float dimension;
        TypedValue resolve = resolve(context, r3);
        if (resolve == null || resolve.type != 5) {
            dimension = context.getResources().getDimension(r4);
        } else {
            dimension = resolve.getDimension(context.getResources().getDisplayMetrics());
        }
        return (int) dimension;
    }
}
