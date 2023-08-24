package com.facebook.react.views.text;

import android.text.Spannable;

/* loaded from: classes.dex */
public class ReactTextUpdate {
    private final boolean mContainsImages;
    public boolean mContainsMultipleFragments;
    private final int mJsEventCounter;
    private final int mJustificationMode;
    private final float mPaddingBottom;
    private final float mPaddingLeft;
    private final float mPaddingRight;
    private final float mPaddingTop;
    private final int mSelectionEnd;
    private final int mSelectionStart;
    private final Spannable mText;
    private final int mTextAlign;
    private final int mTextBreakStrategy;

    @Deprecated
    public ReactTextUpdate(Spannable spannable, int r15, boolean z, float f, float f2, float f3, float f4, int r21) {
        this(spannable, r15, z, f, f2, f3, f4, r21, 1, 0, -1, -1);
    }

    public ReactTextUpdate(Spannable spannable, int r15, boolean z, float f, float f2, float f3, float f4, int r21, int r22, int r23) {
        this(spannable, r15, z, f, f2, f3, f4, r21, r22, r23, -1, -1);
    }

    public ReactTextUpdate(Spannable spannable, int r15, boolean z, int r17, int r18, int r19) {
        this(spannable, r15, z, -1.0f, -1.0f, -1.0f, -1.0f, r17, r18, r19, -1, -1);
    }

    public ReactTextUpdate(Spannable spannable, int r2, boolean z, float f, float f2, float f3, float f4, int r8, int r9, int r10, int r11, int r12) {
        this.mText = spannable;
        this.mJsEventCounter = r2;
        this.mContainsImages = z;
        this.mPaddingLeft = f;
        this.mPaddingTop = f2;
        this.mPaddingRight = f3;
        this.mPaddingBottom = f4;
        this.mTextAlign = r8;
        this.mTextBreakStrategy = r9;
        this.mSelectionStart = r11;
        this.mSelectionEnd = r12;
        this.mJustificationMode = r10;
    }

    public static ReactTextUpdate buildReactTextUpdateFromState(Spannable spannable, int r9, int r10, int r11, int r12, boolean z) {
        ReactTextUpdate reactTextUpdate = new ReactTextUpdate(spannable, r9, false, r10, r11, r12);
        reactTextUpdate.mContainsMultipleFragments = z;
        return reactTextUpdate;
    }

    public Spannable getText() {
        return this.mText;
    }

    public int getJsEventCounter() {
        return this.mJsEventCounter;
    }

    public boolean containsImages() {
        return this.mContainsImages;
    }

    public float getPaddingLeft() {
        return this.mPaddingLeft;
    }

    public float getPaddingTop() {
        return this.mPaddingTop;
    }

    public float getPaddingRight() {
        return this.mPaddingRight;
    }

    public float getPaddingBottom() {
        return this.mPaddingBottom;
    }

    public int getTextAlign() {
        return this.mTextAlign;
    }

    public int getTextBreakStrategy() {
        return this.mTextBreakStrategy;
    }

    public int getJustificationMode() {
        return this.mJustificationMode;
    }

    public int getSelectionStart() {
        return this.mSelectionStart;
    }

    public int getSelectionEnd() {
        return this.mSelectionEnd;
    }
}
