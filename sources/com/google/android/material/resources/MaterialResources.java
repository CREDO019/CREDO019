package com.google.android.material.resources;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.util.TypedValue;
import androidx.appcompat.content.res.AppCompatResources;
import androidx.appcompat.widget.TintTypedArray;

/* loaded from: classes3.dex */
public class MaterialResources {
    private MaterialResources() {
    }

    public static ColorStateList getColorStateList(Context context, TypedArray typedArray, int r3) {
        int color;
        int resourceId;
        ColorStateList colorStateList;
        if (!typedArray.hasValue(r3) || (resourceId = typedArray.getResourceId(r3, 0)) == 0 || (colorStateList = AppCompatResources.getColorStateList(context, resourceId)) == null) {
            if (Build.VERSION.SDK_INT <= 15 && (color = typedArray.getColor(r3, -1)) != -1) {
                return ColorStateList.valueOf(color);
            }
            return typedArray.getColorStateList(r3);
        }
        return colorStateList;
    }

    public static ColorStateList getColorStateList(Context context, TintTypedArray tintTypedArray, int r3) {
        int color;
        int resourceId;
        ColorStateList colorStateList;
        if (!tintTypedArray.hasValue(r3) || (resourceId = tintTypedArray.getResourceId(r3, 0)) == 0 || (colorStateList = AppCompatResources.getColorStateList(context, resourceId)) == null) {
            if (Build.VERSION.SDK_INT <= 15 && (color = tintTypedArray.getColor(r3, -1)) != -1) {
                return ColorStateList.valueOf(color);
            }
            return tintTypedArray.getColorStateList(r3);
        }
        return colorStateList;
    }

    public static Drawable getDrawable(Context context, TypedArray typedArray, int r3) {
        int resourceId;
        Drawable drawable;
        return (!typedArray.hasValue(r3) || (resourceId = typedArray.getResourceId(r3, 0)) == 0 || (drawable = AppCompatResources.getDrawable(context, resourceId)) == null) ? typedArray.getDrawable(r3) : drawable;
    }

    public static TextAppearance getTextAppearance(Context context, TypedArray typedArray, int r3) {
        int resourceId;
        if (!typedArray.hasValue(r3) || (resourceId = typedArray.getResourceId(r3, 0)) == 0) {
            return null;
        }
        return new TextAppearance(context, resourceId);
    }

    public static int getDimensionPixelSize(Context context, TypedArray typedArray, int r5, int r6) {
        TypedValue typedValue = new TypedValue();
        if (!typedArray.getValue(r5, typedValue) || typedValue.type != 2) {
            return typedArray.getDimensionPixelSize(r5, r6);
        }
        TypedArray obtainStyledAttributes = context.getTheme().obtainStyledAttributes(new int[]{typedValue.data});
        int dimensionPixelSize = obtainStyledAttributes.getDimensionPixelSize(0, r6);
        obtainStyledAttributes.recycle();
        return dimensionPixelSize;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int getIndexWithValue(TypedArray typedArray, int r1, int r2) {
        return typedArray.hasValue(r1) ? r1 : r2;
    }
}
