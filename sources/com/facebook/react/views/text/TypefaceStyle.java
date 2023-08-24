package com.facebook.react.views.text;

import android.graphics.Typeface;
import android.os.Build;

/* loaded from: classes.dex */
class TypefaceStyle {
    public static final int BOLD = 700;
    private static final int MAX_WEIGHT = 1000;
    private static final int MIN_WEIGHT = 1;
    public static final int NORMAL = 400;
    private final boolean mItalic;
    private final int mWeight;

    public TypefaceStyle(int r1, boolean z) {
        this.mItalic = z;
        this.mWeight = r1 == -1 ? 400 : r1;
    }

    public TypefaceStyle(int r4) {
        r4 = r4 == -1 ? 0 : r4;
        this.mItalic = (r4 & 2) != 0;
        this.mWeight = (r4 & 1) != 0 ? BOLD : 400;
    }

    public TypefaceStyle(int r5, int r6) {
        r5 = r5 == -1 ? 0 : r5;
        this.mItalic = (r5 & 2) != 0;
        this.mWeight = r6 == -1 ? (r5 & 1) != 0 ? BOLD : 400 : r6;
    }

    public int getNearestStyle() {
        return this.mWeight < 700 ? this.mItalic ? 2 : 0 : this.mItalic ? 3 : 1;
    }

    public Typeface apply(Typeface typeface) {
        if (Build.VERSION.SDK_INT < 28) {
            return Typeface.create(typeface, getNearestStyle());
        }
        return Typeface.create(typeface, this.mWeight, this.mItalic);
    }
}
