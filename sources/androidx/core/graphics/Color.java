package androidx.core.graphics;

import android.graphics.ColorSpace;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(m184d1 = {"\u0000>\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u0007\n\u0002\u0010\t\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000e\n\u0002\b\u0002\u001a\r\u0010\u0018\u001a\u00020\u0004*\u00020\u0019H\u0087\n\u001a\r\u0010\u0018\u001a\u00020\u0001*\u00020\u0001H\u0086\n\u001a\r\u0010\u0018\u001a\u00020\u0004*\u00020\u0005H\u0087\n\u001a\r\u0010\u001a\u001a\u00020\u0004*\u00020\u0019H\u0087\n\u001a\r\u0010\u001a\u001a\u00020\u0001*\u00020\u0001H\u0086\n\u001a\r\u0010\u001a\u001a\u00020\u0004*\u00020\u0005H\u0087\n\u001a\r\u0010\u001b\u001a\u00020\u0004*\u00020\u0019H\u0087\n\u001a\r\u0010\u001b\u001a\u00020\u0001*\u00020\u0001H\u0086\n\u001a\r\u0010\u001b\u001a\u00020\u0004*\u00020\u0005H\u0087\n\u001a\r\u0010\u001c\u001a\u00020\u0004*\u00020\u0019H\u0087\n\u001a\r\u0010\u001c\u001a\u00020\u0001*\u00020\u0001H\u0086\n\u001a\r\u0010\u001c\u001a\u00020\u0004*\u00020\u0005H\u0087\n\u001a\u0015\u0010\u001d\u001a\u00020\u0019*\u00020\u00192\u0006\u0010\t\u001a\u00020\nH\u0087\f\u001a\u0015\u0010\u001d\u001a\u00020\u0019*\u00020\u00192\u0006\u0010\t\u001a\u00020\u001eH\u0087\f\u001a\u0015\u0010\u001d\u001a\u00020\u0005*\u00020\u00012\u0006\u0010\t\u001a\u00020\nH\u0087\f\u001a\u0015\u0010\u001d\u001a\u00020\u0005*\u00020\u00012\u0006\u0010\t\u001a\u00020\u001eH\u0087\f\u001a\u0015\u0010\u001d\u001a\u00020\u0005*\u00020\u00052\u0006\u0010\t\u001a\u00020\nH\u0087\f\u001a\u0015\u0010\u001d\u001a\u00020\u0005*\u00020\u00052\u0006\u0010\t\u001a\u00020\u001eH\u0087\f\u001a\u0015\u0010\u001f\u001a\u00020\u0019*\u00020\u00192\u0006\u0010 \u001a\u00020\u0019H\u0087\u0002\u001a\r\u0010!\u001a\u00020\u0019*\u00020\u0001H\u0087\b\u001a\r\u0010!\u001a\u00020\u0019*\u00020\u0005H\u0087\b\u001a\r\u0010\"\u001a\u00020\u0001*\u00020\u0005H\u0087\b\u001a\r\u0010\"\u001a\u00020\u0001*\u00020#H\u0087\b\u001a\r\u0010$\u001a\u00020\u0005*\u00020\u0001H\u0087\b\"\u0016\u0010\u0000\u001a\u00020\u0001*\u00020\u00018Æ\u0002¢\u0006\u0006\u001a\u0004\b\u0002\u0010\u0003\"\u0016\u0010\u0000\u001a\u00020\u0004*\u00020\u00058Ç\u0002¢\u0006\u0006\u001a\u0004\b\u0002\u0010\u0006\"\u0016\u0010\u0007\u001a\u00020\u0001*\u00020\u00018Æ\u0002¢\u0006\u0006\u001a\u0004\b\b\u0010\u0003\"\u0016\u0010\u0007\u001a\u00020\u0004*\u00020\u00058Ç\u0002¢\u0006\u0006\u001a\u0004\b\b\u0010\u0006\"\u0016\u0010\t\u001a\u00020\n*\u00020\u00058Ç\u0002¢\u0006\u0006\u001a\u0004\b\u000b\u0010\f\"\u0016\u0010\r\u001a\u00020\u0001*\u00020\u00018Æ\u0002¢\u0006\u0006\u001a\u0004\b\u000e\u0010\u0003\"\u0016\u0010\r\u001a\u00020\u0004*\u00020\u00058Ç\u0002¢\u0006\u0006\u001a\u0004\b\u000e\u0010\u0006\"\u0016\u0010\u000f\u001a\u00020\u0010*\u00020\u00058Ç\u0002¢\u0006\u0006\u001a\u0004\b\u000f\u0010\u0011\"\u0016\u0010\u0012\u001a\u00020\u0010*\u00020\u00058Ç\u0002¢\u0006\u0006\u001a\u0004\b\u0012\u0010\u0011\"\u0016\u0010\u0013\u001a\u00020\u0004*\u00020\u00018Ç\u0002¢\u0006\u0006\u001a\u0004\b\u0014\u0010\u0015\"\u0016\u0010\u0013\u001a\u00020\u0004*\u00020\u00058Ç\u0002¢\u0006\u0006\u001a\u0004\b\u0014\u0010\u0006\"\u0016\u0010\u0016\u001a\u00020\u0001*\u00020\u00018Æ\u0002¢\u0006\u0006\u001a\u0004\b\u0017\u0010\u0003\"\u0016\u0010\u0016\u001a\u00020\u0004*\u00020\u00058Ç\u0002¢\u0006\u0006\u001a\u0004\b\u0017\u0010\u0006¨\u0006%"}, m183d2 = {"alpha", "", "getAlpha", "(I)I", "", "", "(J)F", "blue", "getBlue", "colorSpace", "Landroid/graphics/ColorSpace;", "getColorSpace", "(J)Landroid/graphics/ColorSpace;", "green", "getGreen", "isSrgb", "", "(J)Z", "isWideGamut", "luminance", "getLuminance", "(I)F", "red", "getRed", "component1", "Landroid/graphics/Color;", "component2", "component3", "component4", "convertTo", "Landroid/graphics/ColorSpace$Named;", "plus", "c", "toColor", "toColorInt", "", "toColorLong", "core-ktx_release"}, m182k = 2, m181mv = {1, 5, 1}, m179xi = 48)
/* renamed from: androidx.core.graphics.ColorKt */
/* loaded from: classes.dex */
public final class Color {
    public static final int component1(int r0) {
        return (r0 >> 24) & 255;
    }

    public static final int component2(int r0) {
        return (r0 >> 16) & 255;
    }

    public static final int component3(int r0) {
        return (r0 >> 8) & 255;
    }

    public static final int component4(int r0) {
        return r0 & 255;
    }

    public static final int getAlpha(int r0) {
        return (r0 >> 24) & 255;
    }

    public static final int getBlue(int r0) {
        return r0 & 255;
    }

    public static final int getGreen(int r0) {
        return (r0 >> 8) & 255;
    }

    public static final int getRed(int r0) {
        return (r0 >> 16) & 255;
    }

    public static final float component1(android.graphics.Color color) {
        Intrinsics.checkNotNullParameter(color, "<this>");
        return color.getComponent(0);
    }

    public static final float component2(android.graphics.Color color) {
        Intrinsics.checkNotNullParameter(color, "<this>");
        return color.getComponent(1);
    }

    public static final float component3(android.graphics.Color color) {
        Intrinsics.checkNotNullParameter(color, "<this>");
        return color.getComponent(2);
    }

    public static final float component4(android.graphics.Color color) {
        Intrinsics.checkNotNullParameter(color, "<this>");
        return color.getComponent(3);
    }

    public static final android.graphics.Color plus(android.graphics.Color color, android.graphics.Color c) {
        Intrinsics.checkNotNullParameter(color, "<this>");
        Intrinsics.checkNotNullParameter(c, "c");
        android.graphics.Color compositeColors = ColorUtils.compositeColors(c, color);
        Intrinsics.checkNotNullExpressionValue(compositeColors, "compositeColors(c, this)");
        return compositeColors;
    }

    public static final float getLuminance(int r0) {
        return android.graphics.Color.luminance(r0);
    }

    public static final android.graphics.Color toColor(int r1) {
        android.graphics.Color valueOf = android.graphics.Color.valueOf(r1);
        Intrinsics.checkNotNullExpressionValue(valueOf, "valueOf(this)");
        return valueOf;
    }

    public static final long toColorLong(int r2) {
        return android.graphics.Color.pack(r2);
    }

    public static final float component1(long j) {
        return android.graphics.Color.red(j);
    }

    public static final float component2(long j) {
        return android.graphics.Color.green(j);
    }

    public static final float component3(long j) {
        return android.graphics.Color.blue(j);
    }

    public static final float component4(long j) {
        return android.graphics.Color.alpha(j);
    }

    public static final float getAlpha(long j) {
        return android.graphics.Color.alpha(j);
    }

    public static final float getRed(long j) {
        return android.graphics.Color.red(j);
    }

    public static final float getGreen(long j) {
        return android.graphics.Color.green(j);
    }

    public static final float getBlue(long j) {
        return android.graphics.Color.blue(j);
    }

    public static final float getLuminance(long j) {
        return android.graphics.Color.luminance(j);
    }

    public static final android.graphics.Color toColor(long j) {
        android.graphics.Color valueOf = android.graphics.Color.valueOf(j);
        Intrinsics.checkNotNullExpressionValue(valueOf, "valueOf(this)");
        return valueOf;
    }

    public static final int toColorInt(long j) {
        return android.graphics.Color.toArgb(j);
    }

    public static final boolean isSrgb(long j) {
        return android.graphics.Color.isSrgb(j);
    }

    public static final boolean isWideGamut(long j) {
        return android.graphics.Color.isWideGamut(j);
    }

    public static final ColorSpace getColorSpace(long j) {
        ColorSpace colorSpace = android.graphics.Color.colorSpace(j);
        Intrinsics.checkNotNullExpressionValue(colorSpace, "colorSpace(this)");
        return colorSpace;
    }

    public static final long convertTo(int r1, ColorSpace.Named colorSpace) {
        Intrinsics.checkNotNullParameter(colorSpace, "colorSpace");
        return android.graphics.Color.convert(r1, ColorSpace.get(colorSpace));
    }

    public static final long convertTo(int r1, ColorSpace colorSpace) {
        Intrinsics.checkNotNullParameter(colorSpace, "colorSpace");
        return android.graphics.Color.convert(r1, colorSpace);
    }

    public static final long convertTo(long j, ColorSpace.Named colorSpace) {
        Intrinsics.checkNotNullParameter(colorSpace, "colorSpace");
        return android.graphics.Color.convert(j, ColorSpace.get(colorSpace));
    }

    public static final long convertTo(long j, ColorSpace colorSpace) {
        Intrinsics.checkNotNullParameter(colorSpace, "colorSpace");
        return android.graphics.Color.convert(j, colorSpace);
    }

    public static final android.graphics.Color convertTo(android.graphics.Color color, ColorSpace.Named colorSpace) {
        Intrinsics.checkNotNullParameter(color, "<this>");
        Intrinsics.checkNotNullParameter(colorSpace, "colorSpace");
        android.graphics.Color convert = color.convert(ColorSpace.get(colorSpace));
        Intrinsics.checkNotNullExpressionValue(convert, "convert(ColorSpace.get(colorSpace))");
        return convert;
    }

    public static final android.graphics.Color convertTo(android.graphics.Color color, ColorSpace colorSpace) {
        Intrinsics.checkNotNullParameter(color, "<this>");
        Intrinsics.checkNotNullParameter(colorSpace, "colorSpace");
        android.graphics.Color convert = color.convert(colorSpace);
        Intrinsics.checkNotNullExpressionValue(convert, "convert(colorSpace)");
        return convert;
    }

    public static final int toColorInt(String str) {
        Intrinsics.checkNotNullParameter(str, "<this>");
        return android.graphics.Color.parseColor(str);
    }
}
