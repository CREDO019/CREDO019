package com.google.android.material.internal;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.util.TypedValue;
import androidx.appcompat.view.ContextThemeWrapper;
import androidx.appcompat.widget.TintTypedArray;
import com.google.android.material.C2151R;

/* loaded from: classes3.dex */
public final class ThemeEnforcement {
    private static final String APPCOMPAT_THEME_NAME = "Theme.AppCompat";
    private static final String MATERIAL_THEME_NAME = "Theme.MaterialComponents";
    private static final int[] APPCOMPAT_CHECK_ATTRS = {C2151R.attr.colorPrimary};
    private static final int[] MATERIAL_CHECK_ATTRS = {C2151R.attr.colorPrimaryVariant};
    private static final int[] ANDROID_THEME_OVERLAY_ATTRS = {16842752, C2151R.attr.theme};
    private static final int[] MATERIAL_THEME_OVERLAY_ATTR = {C2151R.attr.materialThemeOverlay};

    private ThemeEnforcement() {
    }

    public static TypedArray obtainStyledAttributes(Context context, AttributeSet attributeSet, int[] r2, int r3, int r4, int... r5) {
        checkCompatibleTheme(context, attributeSet, r3, r4);
        checkTextAppearance(context, attributeSet, r2, r3, r4, r5);
        return context.obtainStyledAttributes(attributeSet, r2, r3, r4);
    }

    public static TintTypedArray obtainTintedStyledAttributes(Context context, AttributeSet attributeSet, int[] r2, int r3, int r4, int... r5) {
        checkCompatibleTheme(context, attributeSet, r3, r4);
        checkTextAppearance(context, attributeSet, r2, r3, r4, r5);
        return TintTypedArray.obtainStyledAttributes(context, attributeSet, r2, r3, r4);
    }

    private static void checkCompatibleTheme(Context context, AttributeSet attributeSet, int r3, int r4) {
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, C2151R.styleable.ThemeEnforcement, r3, r4);
        boolean z = obtainStyledAttributes.getBoolean(C2151R.styleable.ThemeEnforcement_enforceMaterialTheme, false);
        obtainStyledAttributes.recycle();
        if (z) {
            TypedValue typedValue = new TypedValue();
            if (!context.getTheme().resolveAttribute(C2151R.attr.isMaterialTheme, typedValue, true) || (typedValue.type == 18 && typedValue.data == 0)) {
                checkMaterialTheme(context);
            }
        }
        checkAppCompatTheme(context);
    }

    private static void checkTextAppearance(Context context, AttributeSet attributeSet, int[] r5, int r6, int r7, int... r8) {
        boolean z;
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, C2151R.styleable.ThemeEnforcement, r6, r7);
        if (!obtainStyledAttributes.getBoolean(C2151R.styleable.ThemeEnforcement_enforceTextAppearance, false)) {
            obtainStyledAttributes.recycle();
            return;
        }
        if (r8 == null || r8.length == 0) {
            z = obtainStyledAttributes.getResourceId(C2151R.styleable.ThemeEnforcement_android_textAppearance, -1) != -1;
        } else {
            z = isCustomTextAppearanceValid(context, attributeSet, r5, r6, r7, r8);
        }
        obtainStyledAttributes.recycle();
        if (!z) {
            throw new IllegalArgumentException("This component requires that you specify a valid TextAppearance attribute. Update your app theme to inherit from Theme.MaterialComponents (or a descendant).");
        }
    }

    private static boolean isCustomTextAppearanceValid(Context context, AttributeSet attributeSet, int[] r3, int r4, int r5, int... r6) {
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, r3, r4, r5);
        for (int r52 : r6) {
            if (obtainStyledAttributes.getResourceId(r52, -1) == -1) {
                obtainStyledAttributes.recycle();
                return false;
            }
        }
        obtainStyledAttributes.recycle();
        return true;
    }

    public static void checkAppCompatTheme(Context context) {
        checkTheme(context, APPCOMPAT_CHECK_ATTRS, APPCOMPAT_THEME_NAME);
    }

    public static void checkMaterialTheme(Context context) {
        checkTheme(context, MATERIAL_CHECK_ATTRS, MATERIAL_THEME_NAME);
    }

    public static boolean isAppCompatTheme(Context context) {
        return isTheme(context, APPCOMPAT_CHECK_ATTRS);
    }

    public static boolean isMaterialTheme(Context context) {
        return isTheme(context, MATERIAL_CHECK_ATTRS);
    }

    private static boolean isTheme(Context context, int[] r4) {
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(r4);
        for (int r1 = 0; r1 < r4.length; r1++) {
            if (!obtainStyledAttributes.hasValue(r1)) {
                obtainStyledAttributes.recycle();
                return false;
            }
        }
        obtainStyledAttributes.recycle();
        return true;
    }

    private static void checkTheme(Context context, int[] r2, String str) {
        if (isTheme(context, r2)) {
            return;
        }
        throw new IllegalArgumentException("The style on this component requires your app theme to be " + str + " (or a descendant).");
    }

    public static Context createThemedContext(Context context, AttributeSet attributeSet, int r2, int r3) {
        int obtainMaterialThemeOverlayId = obtainMaterialThemeOverlayId(context, attributeSet, r2, r3);
        if (obtainMaterialThemeOverlayId != 0) {
            if ((context instanceof ContextThemeWrapper) && ((ContextThemeWrapper) context).getThemeResId() == obtainMaterialThemeOverlayId) {
                return context;
            }
            ContextThemeWrapper contextThemeWrapper = new ContextThemeWrapper(context, obtainMaterialThemeOverlayId);
            int obtainAndroidThemeOverlayId = obtainAndroidThemeOverlayId(contextThemeWrapper, attributeSet);
            return obtainAndroidThemeOverlayId != 0 ? new ContextThemeWrapper(contextThemeWrapper, obtainAndroidThemeOverlayId) : contextThemeWrapper;
        }
        return context;
    }

    private static int obtainAndroidThemeOverlayId(Context context, AttributeSet attributeSet) {
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, ANDROID_THEME_OVERLAY_ATTRS);
        int resourceId = obtainStyledAttributes.getResourceId(0, 0);
        int resourceId2 = obtainStyledAttributes.getResourceId(1, 0);
        obtainStyledAttributes.recycle();
        return resourceId != 0 ? resourceId : resourceId2;
    }

    private static int obtainMaterialThemeOverlayId(Context context, AttributeSet attributeSet, int r3, int r4) {
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, MATERIAL_THEME_OVERLAY_ATTR, r3, r4);
        int resourceId = obtainStyledAttributes.getResourceId(0, 0);
        obtainStyledAttributes.recycle();
        return resourceId;
    }
}
