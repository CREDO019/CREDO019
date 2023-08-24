package com.th3rdwave.safeareacontext;

import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.WritableMap;
import com.facebook.react.uimanager.PixelUtil;
import com.facebook.react.uimanager.ViewProps;
import java.util.Map;
import kotlin.Metadata;
import kotlin.TuplesKt;
import kotlin.collections.MapsKt;
import kotlin.jvm.internal.Intrinsics;

@Metadata(m184d1 = {"\u0000&\n\u0000\n\u0002\u0010$\n\u0002\u0010\u000e\n\u0002\u0010\u0007\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u001a\u001a\u0010\u0000\u001a\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00030\u00012\u0006\u0010\u0004\u001a\u00020\u0005\u001a\u000e\u0010\u0006\u001a\u00020\u00072\u0006\u0010\u0004\u001a\u00020\u0005\u001a\u001a\u0010\b\u001a\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00030\u00012\u0006\u0010\t\u001a\u00020\n\u001a\u000e\u0010\u000b\u001a\u00020\u00072\u0006\u0010\t\u001a\u00020\n¨\u0006\f"}, m183d2 = {"edgeInsetsToJavaMap", "", "", "", "insets", "Lcom/th3rdwave/safeareacontext/EdgeInsets;", "edgeInsetsToJsMap", "Lcom/facebook/react/bridge/WritableMap;", "rectToJavaMap", "rect", "Lcom/th3rdwave/safeareacontext/Rect;", "rectToJsMap", "react-native-safe-area-context_release"}, m182k = 2, m181mv = {1, 6, 0}, m179xi = 48)
/* renamed from: com.th3rdwave.safeareacontext.SerializationUtilsKt */
/* loaded from: classes4.dex */
public final class SerializationUtils {
    public static final WritableMap edgeInsetsToJsMap(EdgeInsets insets) {
        Intrinsics.checkNotNullParameter(insets, "insets");
        WritableMap insetsMap = Arguments.createMap();
        insetsMap.putDouble(ViewProps.TOP, PixelUtil.toDIPFromPixel(insets.getTop()));
        insetsMap.putDouble("right", PixelUtil.toDIPFromPixel(insets.getRight()));
        insetsMap.putDouble(ViewProps.BOTTOM, PixelUtil.toDIPFromPixel(insets.getBottom()));
        insetsMap.putDouble("left", PixelUtil.toDIPFromPixel(insets.getLeft()));
        Intrinsics.checkNotNullExpressionValue(insetsMap, "insetsMap");
        return insetsMap;
    }

    public static final Map<String, Float> edgeInsetsToJavaMap(EdgeInsets insets) {
        Intrinsics.checkNotNullParameter(insets, "insets");
        return MapsKt.mapOf(TuplesKt.m176to(ViewProps.TOP, Float.valueOf(PixelUtil.toDIPFromPixel(insets.getTop()))), TuplesKt.m176to("right", Float.valueOf(PixelUtil.toDIPFromPixel(insets.getRight()))), TuplesKt.m176to(ViewProps.BOTTOM, Float.valueOf(PixelUtil.toDIPFromPixel(insets.getBottom()))), TuplesKt.m176to("left", Float.valueOf(PixelUtil.toDIPFromPixel(insets.getLeft()))));
    }

    public static final WritableMap rectToJsMap(Rect rect) {
        Intrinsics.checkNotNullParameter(rect, "rect");
        WritableMap rectMap = Arguments.createMap();
        rectMap.putDouble("x", PixelUtil.toDIPFromPixel(rect.getX()));
        rectMap.putDouble("y", PixelUtil.toDIPFromPixel(rect.getY()));
        rectMap.putDouble("width", PixelUtil.toDIPFromPixel(rect.getWidth()));
        rectMap.putDouble("height", PixelUtil.toDIPFromPixel(rect.getHeight()));
        Intrinsics.checkNotNullExpressionValue(rectMap, "rectMap");
        return rectMap;
    }

    public static final Map<String, Float> rectToJavaMap(Rect rect) {
        Intrinsics.checkNotNullParameter(rect, "rect");
        return MapsKt.mapOf(TuplesKt.m176to("x", Float.valueOf(PixelUtil.toDIPFromPixel(rect.getX()))), TuplesKt.m176to("y", Float.valueOf(PixelUtil.toDIPFromPixel(rect.getY()))), TuplesKt.m176to("width", Float.valueOf(PixelUtil.toDIPFromPixel(rect.getWidth()))), TuplesKt.m176to("height", Float.valueOf(PixelUtil.toDIPFromPixel(rect.getHeight()))));
    }
}
