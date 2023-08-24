package com.google.android.material.textview;

import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import androidx.appcompat.widget.AppCompatTextView;
import com.google.android.material.C2151R;
import com.google.android.material.resources.MaterialAttributes;
import com.google.android.material.resources.MaterialResources;

/* loaded from: classes3.dex */
public class MaterialTextView extends AppCompatTextView {
    public MaterialTextView(Context context) {
        this(context, null);
    }

    public MaterialTextView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 16842884);
    }

    public MaterialTextView(Context context, AttributeSet attributeSet, int r4) {
        this(context, attributeSet, r4, 0);
    }

    public MaterialTextView(Context context, AttributeSet attributeSet, int r4, int r5) {
        super(context, attributeSet, r4);
        int findViewAppearanceResourceId;
        if (canApplyTextAppearanceLineHeight(context)) {
            Resources.Theme theme = context.getTheme();
            if (viewAttrsHasLineHeight(context, theme, attributeSet, r4, r5) || (findViewAppearanceResourceId = findViewAppearanceResourceId(theme, attributeSet, r4, r5)) == -1) {
                return;
            }
            applyLineHeightFromViewAppearance(theme, findViewAppearanceResourceId);
        }
    }

    @Override // androidx.appcompat.widget.AppCompatTextView, android.widget.TextView
    public void setTextAppearance(Context context, int r3) {
        super.setTextAppearance(context, r3);
        if (canApplyTextAppearanceLineHeight(context)) {
            applyLineHeightFromViewAppearance(context.getTheme(), r3);
        }
    }

    private void applyLineHeightFromViewAppearance(Resources.Theme theme, int r5) {
        TypedArray obtainStyledAttributes = theme.obtainStyledAttributes(r5, C2151R.styleable.MaterialTextAppearance);
        int readFirstAvailableDimension = readFirstAvailableDimension(getContext(), obtainStyledAttributes, C2151R.styleable.MaterialTextAppearance_android_lineHeight, C2151R.styleable.MaterialTextAppearance_lineHeight);
        obtainStyledAttributes.recycle();
        if (readFirstAvailableDimension >= 0) {
            setLineHeight(readFirstAvailableDimension);
        }
    }

    private static boolean canApplyTextAppearanceLineHeight(Context context) {
        return MaterialAttributes.resolveBoolean(context, C2151R.attr.textAppearanceLineHeightEnabled, true);
    }

    private static int readFirstAvailableDimension(Context context, TypedArray typedArray, int... r6) {
        int r2 = -1;
        for (int r1 = 0; r1 < r6.length && r2 < 0; r1++) {
            r2 = MaterialResources.getDimensionPixelSize(context, typedArray, r6[r1], -1);
        }
        return r2;
    }

    private static boolean viewAttrsHasLineHeight(Context context, Resources.Theme theme, AttributeSet attributeSet, int r4, int r5) {
        TypedArray obtainStyledAttributes = theme.obtainStyledAttributes(attributeSet, C2151R.styleable.MaterialTextView, r4, r5);
        int readFirstAvailableDimension = readFirstAvailableDimension(context, obtainStyledAttributes, C2151R.styleable.MaterialTextView_android_lineHeight, C2151R.styleable.MaterialTextView_lineHeight);
        obtainStyledAttributes.recycle();
        return readFirstAvailableDimension != -1;
    }

    private static int findViewAppearanceResourceId(Resources.Theme theme, AttributeSet attributeSet, int r3, int r4) {
        TypedArray obtainStyledAttributes = theme.obtainStyledAttributes(attributeSet, C2151R.styleable.MaterialTextView, r3, r4);
        int resourceId = obtainStyledAttributes.getResourceId(C2151R.styleable.MaterialTextView_android_textAppearance, -1);
        obtainStyledAttributes.recycle();
        return resourceId;
    }
}
