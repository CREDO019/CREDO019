package androidx.core.content.res;

import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.util.AttributeSet;
import android.util.Log;
import android.util.StateSet;
import android.util.TypedValue;
import android.util.Xml;
import androidx.core.C0264R;
import androidx.core.math.MathUtils;
import androidx.core.p005os.BuildCompat;
import androidx.core.view.ViewCompat;
import java.io.IOException;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

/* loaded from: classes.dex */
public final class ColorStateListInflaterCompat {
    private static final ThreadLocal<TypedValue> sTempTypedValue = new ThreadLocal<>();

    private ColorStateListInflaterCompat() {
    }

    public static ColorStateList inflate(Resources resources, int r1, Resources.Theme theme) {
        try {
            return createFromXml(resources, resources.getXml(r1), theme);
        } catch (Exception e) {
            Log.e("CSLCompat", "Failed to inflate ColorStateList.", e);
            return null;
        }
    }

    public static ColorStateList createFromXml(Resources resources, XmlPullParser xmlPullParser, Resources.Theme theme) throws XmlPullParserException, IOException {
        int next;
        AttributeSet asAttributeSet = Xml.asAttributeSet(xmlPullParser);
        do {
            next = xmlPullParser.next();
            if (next == 2) {
                break;
            }
        } while (next != 1);
        if (next != 2) {
            throw new XmlPullParserException("No start tag found");
        }
        return createFromXmlInner(resources, xmlPullParser, asAttributeSet, theme);
    }

    public static ColorStateList createFromXmlInner(Resources resources, XmlPullParser xmlPullParser, AttributeSet attributeSet, Resources.Theme theme) throws XmlPullParserException, IOException {
        String name = xmlPullParser.getName();
        if (!name.equals("selector")) {
            throw new XmlPullParserException(xmlPullParser.getPositionDescription() + ": invalid color state list tag " + name);
        }
        return inflate(resources, xmlPullParser, attributeSet, theme);
    }

    private static ColorStateList inflate(Resources resources, XmlPullParser xmlPullParser, AttributeSet attributeSet, Resources.Theme theme) throws XmlPullParserException, IOException {
        int depth;
        int color;
        float f;
        Resources resources2 = resources;
        int r4 = 1;
        int depth2 = xmlPullParser.getDepth() + 1;
        int[][] r6 = new int[20];
        int[] r5 = new int[20];
        int r8 = 0;
        while (true) {
            int next = xmlPullParser.next();
            if (next == r4 || ((depth = xmlPullParser.getDepth()) < depth2 && next == 3)) {
                break;
            }
            if (next == 2 && depth <= depth2 && xmlPullParser.getName().equals("item")) {
                TypedArray obtainAttributes = obtainAttributes(resources2, theme, attributeSet, C0264R.styleable.ColorStateListItem);
                int resourceId = obtainAttributes.getResourceId(C0264R.styleable.ColorStateListItem_android_color, -1);
                if (resourceId != -1 && !isColorInt(resources2, resourceId)) {
                    try {
                        color = createFromXml(resources2, resources2.getXml(resourceId), theme).getDefaultColor();
                    } catch (Exception unused) {
                        color = obtainAttributes.getColor(C0264R.styleable.ColorStateListItem_android_color, -65281);
                    }
                } else {
                    color = obtainAttributes.getColor(C0264R.styleable.ColorStateListItem_android_color, -65281);
                }
                float f2 = 1.0f;
                if (obtainAttributes.hasValue(C0264R.styleable.ColorStateListItem_android_alpha)) {
                    f2 = obtainAttributes.getFloat(C0264R.styleable.ColorStateListItem_android_alpha, 1.0f);
                } else if (obtainAttributes.hasValue(C0264R.styleable.ColorStateListItem_alpha)) {
                    f2 = obtainAttributes.getFloat(C0264R.styleable.ColorStateListItem_alpha, 1.0f);
                }
                if (BuildCompat.isAtLeastS() && obtainAttributes.hasValue(C0264R.styleable.ColorStateListItem_android_lStar)) {
                    f = obtainAttributes.getFloat(C0264R.styleable.ColorStateListItem_android_lStar, -1.0f);
                } else {
                    f = obtainAttributes.getFloat(C0264R.styleable.ColorStateListItem_lStar, -1.0f);
                }
                obtainAttributes.recycle();
                int attributeCount = attributeSet.getAttributeCount();
                int[] r13 = new int[attributeCount];
                int r15 = 0;
                for (int r14 = 0; r14 < attributeCount; r14++) {
                    int attributeNameResource = attributeSet.getAttributeNameResource(r14);
                    if (attributeNameResource != 16843173 && attributeNameResource != 16843551 && attributeNameResource != C0264R.attr.alpha && attributeNameResource != C0264R.attr.lStar) {
                        int r7 = r15 + 1;
                        if (!attributeSet.getAttributeBooleanValue(r14, false)) {
                            attributeNameResource = -attributeNameResource;
                        }
                        r13[r15] = attributeNameResource;
                        r15 = r7;
                    }
                }
                int[] trimStateSet = StateSet.trimStateSet(r13, r15);
                r5 = GrowingArrayUtils.append(r5, r8, modulateColorAlpha(color, f2, f));
                r6 = (int[][]) GrowingArrayUtils.append(r6, r8, trimStateSet);
                r8++;
            }
            r4 = 1;
            resources2 = resources;
        }
        int[] r0 = new int[r8];
        int[][] r1 = new int[r8];
        System.arraycopy(r5, 0, r0, 0, r8);
        System.arraycopy(r6, 0, r1, 0, r8);
        return new ColorStateList(r1, r0);
    }

    private static boolean isColorInt(Resources resources, int r3) {
        TypedValue typedValue = getTypedValue();
        resources.getValue(r3, typedValue, true);
        return typedValue.type >= 28 && typedValue.type <= 31;
    }

    private static TypedValue getTypedValue() {
        ThreadLocal<TypedValue> threadLocal = sTempTypedValue;
        TypedValue typedValue = threadLocal.get();
        if (typedValue == null) {
            TypedValue typedValue2 = new TypedValue();
            threadLocal.set(typedValue2);
            return typedValue2;
        }
        return typedValue;
    }

    private static TypedArray obtainAttributes(Resources resources, Resources.Theme theme, AttributeSet attributeSet, int[] r3) {
        if (theme == null) {
            return resources.obtainAttributes(attributeSet, r3);
        }
        return theme.obtainStyledAttributes(attributeSet, r3, 0, 0);
    }

    private static int modulateColorAlpha(int r3, float f, float f2) {
        boolean z = f2 >= 0.0f && f2 <= 100.0f;
        if (f != 1.0f || z) {
            int clamp = MathUtils.clamp((int) ((Color.alpha(r3) * f) + 0.5f), 0, 255);
            if (z) {
                CamColor fromColor = CamColor.fromColor(r3);
                r3 = CamColor.toColor(fromColor.getHue(), fromColor.getChroma(), f2);
            }
            return (r3 & ViewCompat.MEASURED_SIZE_MASK) | (clamp << 24);
        }
        return r3;
    }
}
