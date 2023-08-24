package com.facebook.react.views.text;

import android.graphics.Paint;
import android.text.style.LineHeightSpan;

/* loaded from: classes.dex */
public class CustomLineHeightSpan implements LineHeightSpan, ReactSpan {
    private final int mHeight;

    public CustomLineHeightSpan(float f) {
        this.mHeight = (int) Math.ceil(f);
    }

    @Override // android.text.style.LineHeightSpan
    public void chooseHeight(CharSequence charSequence, int r4, int r5, int r6, int r7, Paint.FontMetricsInt fontMetricsInt) {
        int r3 = fontMetricsInt.descent;
        int r42 = this.mHeight;
        if (r3 > r42) {
            int min = Math.min(r42, fontMetricsInt.descent);
            fontMetricsInt.descent = min;
            fontMetricsInt.bottom = min;
            fontMetricsInt.ascent = 0;
            fontMetricsInt.top = 0;
        } else if ((-fontMetricsInt.ascent) + fontMetricsInt.descent > this.mHeight) {
            fontMetricsInt.bottom = fontMetricsInt.descent;
            int r32 = (-this.mHeight) + fontMetricsInt.descent;
            fontMetricsInt.ascent = r32;
            fontMetricsInt.top = r32;
        } else if ((-fontMetricsInt.ascent) + fontMetricsInt.bottom > this.mHeight) {
            fontMetricsInt.top = fontMetricsInt.ascent;
            fontMetricsInt.bottom = fontMetricsInt.ascent + this.mHeight;
        } else {
            int r33 = (-fontMetricsInt.top) + fontMetricsInt.bottom;
            int r43 = this.mHeight;
            if (r33 > r43) {
                fontMetricsInt.top = fontMetricsInt.bottom - this.mHeight;
                return;
            }
            double d = (r43 - ((-fontMetricsInt.top) + fontMetricsInt.bottom)) / 2.0f;
            fontMetricsInt.top = (int) (fontMetricsInt.top - Math.ceil(d));
            fontMetricsInt.bottom = (int) (fontMetricsInt.bottom + Math.floor(d));
            fontMetricsInt.ascent = fontMetricsInt.top;
            fontMetricsInt.descent = fontMetricsInt.bottom;
        }
    }
}
