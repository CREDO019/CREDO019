package com.google.android.exoplayer2.p012ui;

import android.graphics.Color;
import com.google.android.exoplayer2.util.Util;

/* renamed from: com.google.android.exoplayer2.ui.HtmlUtils */
/* loaded from: classes2.dex */
final class HtmlUtils {
    private HtmlUtils() {
    }

    public static String toCssRgba(int r5) {
        return Util.formatInvariant("rgba(%d,%d,%d,%.3f)", Integer.valueOf(Color.red(r5)), Integer.valueOf(Color.green(r5)), Integer.valueOf(Color.blue(r5)), Double.valueOf(Color.alpha(r5) / 255.0d));
    }

    public static String cssAllClassDescendantsSelector(String str) {
        return "." + str + ",." + str + " *";
    }
}
