package androidx.core.content.res;

import android.content.res.ColorStateList;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import kotlin.Metadata;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;

@Metadata(m184d1 = {"\u0000R\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0007\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0011\n\u0002\u0010\r\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0002\u001a\u0016\u0010\u0000\u001a\u00020\u0001*\u00020\u00022\b\b\u0001\u0010\u0003\u001a\u00020\u0004H\u0002\u001a\u0014\u0010\u0005\u001a\u00020\u0006*\u00020\u00022\b\b\u0001\u0010\u0003\u001a\u00020\u0004\u001a\u0016\u0010\u0007\u001a\u00020\u0004*\u00020\u00022\b\b\u0001\u0010\u0003\u001a\u00020\u0004H\u0007\u001a\u0014\u0010\b\u001a\u00020\t*\u00020\u00022\b\b\u0001\u0010\u0003\u001a\u00020\u0004\u001a\u0014\u0010\n\u001a\u00020\u000b*\u00020\u00022\b\b\u0001\u0010\u0003\u001a\u00020\u0004\u001a\u0016\u0010\f\u001a\u00020\u0004*\u00020\u00022\b\b\u0001\u0010\u0003\u001a\u00020\u0004H\u0007\u001a\u0016\u0010\r\u001a\u00020\u0004*\u00020\u00022\b\b\u0001\u0010\u0003\u001a\u00020\u0004H\u0007\u001a\u0014\u0010\u000e\u001a\u00020\u000f*\u00020\u00022\b\b\u0001\u0010\u0003\u001a\u00020\u0004\u001a\u0014\u0010\u0010\u001a\u00020\u000b*\u00020\u00022\b\b\u0001\u0010\u0003\u001a\u00020\u0004\u001a\u0016\u0010\u0011\u001a\u00020\u0012*\u00020\u00022\b\b\u0001\u0010\u0003\u001a\u00020\u0004H\u0007\u001a\u0014\u0010\u0013\u001a\u00020\u0004*\u00020\u00022\b\b\u0001\u0010\u0003\u001a\u00020\u0004\u001a\u0014\u0010\u0014\u001a\u00020\u0004*\u00020\u00022\b\b\u0001\u0010\u0003\u001a\u00020\u0004\u001a\u0016\u0010\u0015\u001a\u00020\u0004*\u00020\u00022\b\b\u0001\u0010\u0003\u001a\u00020\u0004H\u0007\u001a\u0014\u0010\u0016\u001a\u00020\u0017*\u00020\u00022\b\b\u0001\u0010\u0003\u001a\u00020\u0004\u001a\u001f\u0010\u0018\u001a\b\u0012\u0004\u0012\u00020\u001a0\u0019*\u00020\u00022\b\b\u0001\u0010\u0003\u001a\u00020\u0004¢\u0006\u0002\u0010\u001b\u001a\u0014\u0010\u001c\u001a\u00020\u001a*\u00020\u00022\b\b\u0001\u0010\u0003\u001a\u00020\u0004\u001a/\u0010\u001d\u001a\u0002H\u001e\"\u0004\b\u0000\u0010\u001e*\u00020\u00022\u0012\u0010\u001f\u001a\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u0002H\u001e0 H\u0086\bø\u0001\u0000¢\u0006\u0002\u0010!\u0082\u0002\u0007\n\u0005\b\u009920\u0001¨\u0006\""}, m183d2 = {"checkAttribute", "", "Landroid/content/res/TypedArray;", "index", "", "getBooleanOrThrow", "", "getColorOrThrow", "getColorStateListOrThrow", "Landroid/content/res/ColorStateList;", "getDimensionOrThrow", "", "getDimensionPixelOffsetOrThrow", "getDimensionPixelSizeOrThrow", "getDrawableOrThrow", "Landroid/graphics/drawable/Drawable;", "getFloatOrThrow", "getFontOrThrow", "Landroid/graphics/Typeface;", "getIntOrThrow", "getIntegerOrThrow", "getResourceIdOrThrow", "getStringOrThrow", "", "getTextArrayOrThrow", "", "", "(Landroid/content/res/TypedArray;I)[Ljava/lang/CharSequence;", "getTextOrThrow", "use", "R", "block", "Lkotlin/Function1;", "(Landroid/content/res/TypedArray;Lkotlin/jvm/functions/Function1;)Ljava/lang/Object;", "core-ktx_release"}, m182k = 2, m181mv = {1, 5, 1}, m179xi = 48)
/* renamed from: androidx.core.content.res.TypedArrayKt */
/* loaded from: classes.dex */
public final class TypedArray {
    private static final void checkAttribute(android.content.res.TypedArray typedArray, int r1) {
        if (!typedArray.hasValue(r1)) {
            throw new IllegalArgumentException("Attribute not defined in set.");
        }
    }

    public static final boolean getBooleanOrThrow(android.content.res.TypedArray typedArray, int r2) {
        Intrinsics.checkNotNullParameter(typedArray, "<this>");
        checkAttribute(typedArray, r2);
        return typedArray.getBoolean(r2, false);
    }

    public static final int getColorOrThrow(android.content.res.TypedArray typedArray, int r2) {
        Intrinsics.checkNotNullParameter(typedArray, "<this>");
        checkAttribute(typedArray, r2);
        return typedArray.getColor(r2, 0);
    }

    public static final ColorStateList getColorStateListOrThrow(android.content.res.TypedArray typedArray, int r2) {
        Intrinsics.checkNotNullParameter(typedArray, "<this>");
        checkAttribute(typedArray, r2);
        ColorStateList colorStateList = typedArray.getColorStateList(r2);
        if (colorStateList != null) {
            return colorStateList;
        }
        throw new IllegalStateException("Attribute value was not a color or color state list.".toString());
    }

    public static final float getDimensionOrThrow(android.content.res.TypedArray typedArray, int r2) {
        Intrinsics.checkNotNullParameter(typedArray, "<this>");
        checkAttribute(typedArray, r2);
        return typedArray.getDimension(r2, 0.0f);
    }

    public static final int getDimensionPixelOffsetOrThrow(android.content.res.TypedArray typedArray, int r2) {
        Intrinsics.checkNotNullParameter(typedArray, "<this>");
        checkAttribute(typedArray, r2);
        return typedArray.getDimensionPixelOffset(r2, 0);
    }

    public static final int getDimensionPixelSizeOrThrow(android.content.res.TypedArray typedArray, int r2) {
        Intrinsics.checkNotNullParameter(typedArray, "<this>");
        checkAttribute(typedArray, r2);
        return typedArray.getDimensionPixelSize(r2, 0);
    }

    public static final Drawable getDrawableOrThrow(android.content.res.TypedArray typedArray, int r2) {
        Intrinsics.checkNotNullParameter(typedArray, "<this>");
        checkAttribute(typedArray, r2);
        Drawable drawable = typedArray.getDrawable(r2);
        Intrinsics.checkNotNull(drawable);
        return drawable;
    }

    public static final float getFloatOrThrow(android.content.res.TypedArray typedArray, int r2) {
        Intrinsics.checkNotNullParameter(typedArray, "<this>");
        checkAttribute(typedArray, r2);
        return typedArray.getFloat(r2, 0.0f);
    }

    public static final Typeface getFontOrThrow(android.content.res.TypedArray typedArray, int r2) {
        Intrinsics.checkNotNullParameter(typedArray, "<this>");
        checkAttribute(typedArray, r2);
        Typeface font = typedArray.getFont(r2);
        Intrinsics.checkNotNull(font);
        return font;
    }

    public static final int getIntOrThrow(android.content.res.TypedArray typedArray, int r2) {
        Intrinsics.checkNotNullParameter(typedArray, "<this>");
        checkAttribute(typedArray, r2);
        return typedArray.getInt(r2, 0);
    }

    public static final int getIntegerOrThrow(android.content.res.TypedArray typedArray, int r2) {
        Intrinsics.checkNotNullParameter(typedArray, "<this>");
        checkAttribute(typedArray, r2);
        return typedArray.getInteger(r2, 0);
    }

    public static final int getResourceIdOrThrow(android.content.res.TypedArray typedArray, int r2) {
        Intrinsics.checkNotNullParameter(typedArray, "<this>");
        checkAttribute(typedArray, r2);
        return typedArray.getResourceId(r2, 0);
    }

    public static final String getStringOrThrow(android.content.res.TypedArray typedArray, int r2) {
        Intrinsics.checkNotNullParameter(typedArray, "<this>");
        checkAttribute(typedArray, r2);
        String string = typedArray.getString(r2);
        if (string != null) {
            return string;
        }
        throw new IllegalStateException("Attribute value could not be coerced to String.".toString());
    }

    public static final CharSequence getTextOrThrow(android.content.res.TypedArray typedArray, int r2) {
        Intrinsics.checkNotNullParameter(typedArray, "<this>");
        checkAttribute(typedArray, r2);
        CharSequence text = typedArray.getText(r2);
        if (text != null) {
            return text;
        }
        throw new IllegalStateException("Attribute value could not be coerced to CharSequence.".toString());
    }

    public static final CharSequence[] getTextArrayOrThrow(android.content.res.TypedArray typedArray, int r2) {
        Intrinsics.checkNotNullParameter(typedArray, "<this>");
        checkAttribute(typedArray, r2);
        CharSequence[] textArray = typedArray.getTextArray(r2);
        Intrinsics.checkNotNullExpressionValue(textArray, "getTextArray(index)");
        return textArray;
    }

    public static final <R> R use(android.content.res.TypedArray typedArray, Function1<? super android.content.res.TypedArray, ? extends R> block) {
        Intrinsics.checkNotNullParameter(typedArray, "<this>");
        Intrinsics.checkNotNullParameter(block, "block");
        R invoke = block.invoke(typedArray);
        typedArray.recycle();
        return invoke;
    }
}
