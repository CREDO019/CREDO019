package com.facebook.react.views.text;

import android.content.Context;
import android.graphics.Rect;
import android.text.Layout;
import android.text.TextPaint;
import android.util.DisplayMetrics;
import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.WritableArray;
import com.facebook.react.bridge.WritableMap;

/* loaded from: classes.dex */
public class FontMetricsUtil {
    private static final float AMPLIFICATION_FACTOR = 100.0f;
    private static final String CAP_HEIGHT_MEASUREMENT_TEXT = "T";
    private static final String X_HEIGHT_MEASUREMENT_TEXT = "x";

    public static WritableArray getFontMetrics(CharSequence charSequence, Layout layout, TextPaint textPaint, Context context) {
        Rect rect;
        Rect rect2;
        Rect rect3;
        DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();
        WritableArray createArray = Arguments.createArray();
        TextPaint textPaint2 = new TextPaint(textPaint);
        textPaint2.setTextSize(textPaint2.getTextSize() * AMPLIFICATION_FACTOR);
        textPaint2.getTextBounds("T", 0, 1, new Rect());
        double height = (rect.height() / AMPLIFICATION_FACTOR) / displayMetrics.density;
        textPaint2.getTextBounds(X_HEIGHT_MEASUREMENT_TEXT, 0, 1, new Rect());
        double height2 = (rect2.height() / AMPLIFICATION_FACTOR) / displayMetrics.density;
        for (int r4 = 0; r4 < layout.getLineCount(); r4++) {
            layout.getLineBounds(r4, new Rect());
            WritableMap createMap = Arguments.createMap();
            createMap.putDouble(X_HEIGHT_MEASUREMENT_TEXT, layout.getLineLeft(r4) / displayMetrics.density);
            createMap.putDouble("y", rect3.top / displayMetrics.density);
            createMap.putDouble("width", layout.getLineWidth(r4) / displayMetrics.density);
            createMap.putDouble("height", rect3.height() / displayMetrics.density);
            createMap.putDouble("descender", layout.getLineDescent(r4) / displayMetrics.density);
            createMap.putDouble("ascender", (-layout.getLineAscent(r4)) / displayMetrics.density);
            createMap.putDouble("baseline", layout.getLineBaseline(r4) / displayMetrics.density);
            createMap.putDouble("capHeight", height);
            createMap.putDouble("xHeight", height2);
            createMap.putString("text", charSequence.subSequence(layout.getLineStart(r4), layout.getLineEnd(r4)).toString());
            createArray.pushMap(createMap);
        }
        return createArray;
    }
}
