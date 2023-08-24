package androidx.core.content.res;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.util.TypedValue;
import org.xmlpull.v1.XmlPullParser;

/* loaded from: classes.dex */
public class TypedArrayUtils {
    private static final String NAMESPACE = "http://schemas.android.com/apk/res/android";

    public static boolean hasAttribute(XmlPullParser xmlPullParser, String str) {
        return xmlPullParser.getAttributeValue(NAMESPACE, str) != null;
    }

    public static float getNamedFloat(TypedArray typedArray, XmlPullParser xmlPullParser, String str, int r3, float f) {
        return !hasAttribute(xmlPullParser, str) ? f : typedArray.getFloat(r3, f);
    }

    public static boolean getNamedBoolean(TypedArray typedArray, XmlPullParser xmlPullParser, String str, int r3, boolean z) {
        return !hasAttribute(xmlPullParser, str) ? z : typedArray.getBoolean(r3, z);
    }

    public static int getNamedInt(TypedArray typedArray, XmlPullParser xmlPullParser, String str, int r3, int r4) {
        return !hasAttribute(xmlPullParser, str) ? r4 : typedArray.getInt(r3, r4);
    }

    public static int getNamedColor(TypedArray typedArray, XmlPullParser xmlPullParser, String str, int r3, int r4) {
        return !hasAttribute(xmlPullParser, str) ? r4 : typedArray.getColor(r3, r4);
    }

    public static ComplexColorCompat getNamedComplexColor(TypedArray typedArray, XmlPullParser xmlPullParser, Resources.Theme theme, String str, int r5, int r6) {
        if (hasAttribute(xmlPullParser, str)) {
            TypedValue typedValue = new TypedValue();
            typedArray.getValue(r5, typedValue);
            if (typedValue.type >= 28 && typedValue.type <= 31) {
                return ComplexColorCompat.from(typedValue.data);
            }
            ComplexColorCompat inflate = ComplexColorCompat.inflate(typedArray.getResources(), typedArray.getResourceId(r5, 0), theme);
            if (inflate != null) {
                return inflate;
            }
        }
        return ComplexColorCompat.from(r6);
    }

    public static ColorStateList getNamedColorStateList(TypedArray typedArray, XmlPullParser xmlPullParser, Resources.Theme theme, String str, int r5) {
        if (hasAttribute(xmlPullParser, str)) {
            TypedValue typedValue = new TypedValue();
            typedArray.getValue(r5, typedValue);
            if (typedValue.type == 2) {
                throw new UnsupportedOperationException("Failed to resolve attribute at index " + r5 + ": " + typedValue);
            } else if (typedValue.type >= 28 && typedValue.type <= 31) {
                return getNamedColorStateListFromInt(typedValue);
            } else {
                return ColorStateListInflaterCompat.inflate(typedArray.getResources(), typedArray.getResourceId(r5, 0), theme);
            }
        }
        return null;
    }

    private static ColorStateList getNamedColorStateListFromInt(TypedValue typedValue) {
        return ColorStateList.valueOf(typedValue.data);
    }

    public static int getNamedResourceId(TypedArray typedArray, XmlPullParser xmlPullParser, String str, int r3, int r4) {
        return !hasAttribute(xmlPullParser, str) ? r4 : typedArray.getResourceId(r3, r4);
    }

    public static String getNamedString(TypedArray typedArray, XmlPullParser xmlPullParser, String str, int r3) {
        if (hasAttribute(xmlPullParser, str)) {
            return typedArray.getString(r3);
        }
        return null;
    }

    public static TypedValue peekNamedValue(TypedArray typedArray, XmlPullParser xmlPullParser, String str, int r3) {
        if (hasAttribute(xmlPullParser, str)) {
            return typedArray.peekValue(r3);
        }
        return null;
    }

    public static TypedArray obtainAttributes(Resources resources, Resources.Theme theme, AttributeSet attributeSet, int[] r3) {
        if (theme == null) {
            return resources.obtainAttributes(attributeSet, r3);
        }
        return theme.obtainStyledAttributes(attributeSet, r3, 0, 0);
    }

    public static boolean getBoolean(TypedArray typedArray, int r1, int r2, boolean z) {
        return typedArray.getBoolean(r1, typedArray.getBoolean(r2, z));
    }

    public static Drawable getDrawable(TypedArray typedArray, int r1, int r2) {
        Drawable drawable = typedArray.getDrawable(r1);
        return drawable == null ? typedArray.getDrawable(r2) : drawable;
    }

    public static int getInt(TypedArray typedArray, int r1, int r2, int r3) {
        return typedArray.getInt(r1, typedArray.getInt(r2, r3));
    }

    public static int getResourceId(TypedArray typedArray, int r1, int r2, int r3) {
        return typedArray.getResourceId(r1, typedArray.getResourceId(r2, r3));
    }

    public static String getString(TypedArray typedArray, int r1, int r2) {
        String string = typedArray.getString(r1);
        return string == null ? typedArray.getString(r2) : string;
    }

    public static CharSequence getText(TypedArray typedArray, int r1, int r2) {
        CharSequence text = typedArray.getText(r1);
        return text == null ? typedArray.getText(r2) : text;
    }

    public static CharSequence[] getTextArray(TypedArray typedArray, int r1, int r2) {
        CharSequence[] textArray = typedArray.getTextArray(r1);
        return textArray == null ? typedArray.getTextArray(r2) : textArray;
    }

    public static int getAttr(Context context, int r3, int r4) {
        TypedValue typedValue = new TypedValue();
        context.getTheme().resolveAttribute(r3, typedValue, true);
        return typedValue.resourceId != 0 ? r3 : r4;
    }

    private TypedArrayUtils() {
    }
}
