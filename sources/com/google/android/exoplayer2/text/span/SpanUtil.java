package com.google.android.exoplayer2.text.span;

import android.text.Spannable;

/* loaded from: classes2.dex */
public final class SpanUtil {
    public static void addOrReplaceSpan(Spannable spannable, Object obj, int r7, int r8, int r9) {
        Object[] spans;
        for (Object obj2 : spannable.getSpans(r7, r8, obj.getClass())) {
            if (spannable.getSpanStart(obj2) == r7 && spannable.getSpanEnd(obj2) == r8 && spannable.getSpanFlags(obj2) == r9) {
                spannable.removeSpan(obj2);
            }
        }
        spannable.setSpan(obj, r7, r8, r9);
    }

    private SpanUtil() {
    }
}
