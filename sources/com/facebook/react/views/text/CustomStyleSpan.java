package com.facebook.react.views.text;

import android.content.res.AssetManager;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.text.TextPaint;
import android.text.style.MetricAffectingSpan;

/* loaded from: classes.dex */
public class CustomStyleSpan extends MetricAffectingSpan implements ReactSpan {
    private final AssetManager mAssetManager;
    private final String mFeatureSettings;
    private final String mFontFamily;
    private final int mStyle;
    private final int mWeight;

    public CustomStyleSpan(int r1, int r2, String str, String str2, AssetManager assetManager) {
        this.mStyle = r1;
        this.mWeight = r2;
        this.mFeatureSettings = str;
        this.mFontFamily = str2;
        this.mAssetManager = assetManager;
    }

    @Override // android.text.style.CharacterStyle
    public void updateDrawState(TextPaint textPaint) {
        apply(textPaint, this.mStyle, this.mWeight, this.mFeatureSettings, this.mFontFamily, this.mAssetManager);
    }

    @Override // android.text.style.MetricAffectingSpan
    public void updateMeasureState(TextPaint textPaint) {
        apply(textPaint, this.mStyle, this.mWeight, this.mFeatureSettings, this.mFontFamily, this.mAssetManager);
    }

    public int getStyle() {
        int r0 = this.mStyle;
        if (r0 == -1) {
            return 0;
        }
        return r0;
    }

    public int getWeight() {
        int r0 = this.mWeight;
        if (r0 == -1) {
            return 400;
        }
        return r0;
    }

    public String getFontFamily() {
        return this.mFontFamily;
    }

    private static void apply(Paint paint, int r2, int r3, String str, String str2, AssetManager assetManager) {
        Typeface applyStyles = ReactTypefaceUtils.applyStyles(paint.getTypeface(), r2, r3, str2, assetManager);
        paint.setFontFeatureSettings(str);
        paint.setTypeface(applyStyles);
        paint.setSubpixelText(true);
    }
}
