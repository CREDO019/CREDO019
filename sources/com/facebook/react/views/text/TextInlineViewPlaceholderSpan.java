package com.facebook.react.views.text;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.text.style.ReplacementSpan;

/* loaded from: classes.dex */
public class TextInlineViewPlaceholderSpan extends ReplacementSpan implements ReactSpan {
    private int mHeight;
    private int mReactTag;
    private int mWidth;

    @Override // android.text.style.ReplacementSpan
    public void draw(Canvas canvas, CharSequence charSequence, int r3, int r4, float f, int r6, int r7, int r8, Paint paint) {
    }

    public TextInlineViewPlaceholderSpan(int r1, int r2, int r3) {
        this.mReactTag = r1;
        this.mWidth = r2;
        this.mHeight = r3;
    }

    public int getReactTag() {
        return this.mReactTag;
    }

    public int getWidth() {
        return this.mWidth;
    }

    public int getHeight() {
        return this.mHeight;
    }

    @Override // android.text.style.ReplacementSpan
    public int getSize(Paint paint, CharSequence charSequence, int r3, int r4, Paint.FontMetricsInt fontMetricsInt) {
        if (fontMetricsInt != null) {
            fontMetricsInt.ascent = -this.mHeight;
            fontMetricsInt.descent = 0;
            fontMetricsInt.top = fontMetricsInt.ascent;
            fontMetricsInt.bottom = 0;
        }
        return this.mWidth;
    }
}
