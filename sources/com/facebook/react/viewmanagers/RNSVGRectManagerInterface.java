package com.facebook.react.viewmanagers;

import android.view.View;
import com.facebook.react.bridge.ReadableArray;
import com.facebook.react.bridge.ReadableMap;

/* loaded from: classes.dex */
public interface RNSVGRectManagerInterface<T extends View> {
    void setClipPath(T t, String str);

    void setClipRule(T t, int r2);

    void setDisplay(T t, String str);

    void setFill(T t, ReadableMap readableMap);

    void setFillOpacity(T t, float f);

    void setFillRule(T t, int r2);

    void setHeight(T t, Double d);

    void setHeight(T t, String str);

    void setMarkerEnd(T t, String str);

    void setMarkerMid(T t, String str);

    void setMarkerStart(T t, String str);

    void setMask(T t, String str);

    void setMatrix(T t, ReadableArray readableArray);

    void setName(T t, String str);

    void setOpacity(T t, float f);

    void setPointerEvents(T t, String str);

    void setPropList(T t, ReadableArray readableArray);

    void setResponsible(T t, boolean z);

    void setRx(T t, Double d);

    void setRx(T t, String str);

    void setRy(T t, Double d);

    void setRy(T t, String str);

    void setStroke(T t, ReadableMap readableMap);

    void setStrokeDasharray(T t, ReadableArray readableArray);

    void setStrokeDashoffset(T t, float f);

    void setStrokeLinecap(T t, int r2);

    void setStrokeLinejoin(T t, int r2);

    void setStrokeMiterlimit(T t, float f);

    void setStrokeOpacity(T t, float f);

    void setStrokeWidth(T t, Double d);

    void setStrokeWidth(T t, String str);

    void setVectorEffect(T t, int r2);

    void setWidth(T t, Double d);

    void setWidth(T t, String str);

    void setX(T t, Double d);

    void setX(T t, String str);

    void setY(T t, Double d);

    void setY(T t, String str);
}
