package com.facebook.react.views.text;

import android.os.Build;
import android.text.TextUtils;
import androidx.room.FtsOptions;
import com.facebook.react.bridge.JSApplicationIllegalArgumentException;
import com.facebook.react.bridge.ReadableArray;
import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.common.mapbuffer.MapBuffer;
import com.facebook.react.uimanager.PixelUtil;
import com.facebook.react.uimanager.ReactAccessibilityDelegate;
import com.facebook.react.uimanager.ReactStylesDiffMap;
import com.facebook.react.uimanager.ViewProps;
import com.google.android.exoplayer2.text.ttml.TtmlNode;
import com.google.android.gms.ads.AdError;
import java.util.ArrayList;
import java.util.Iterator;

/* loaded from: classes.dex */
public class TextAttributeProps {
    private static final int DEFAULT_BREAK_STRATEGY;
    private static final int DEFAULT_HYPHENATION_FREQUENCY;
    private static final int DEFAULT_JUSTIFICATION_MODE;
    private static final int DEFAULT_TEXT_SHADOW_COLOR = 1426063360;
    private static final String PROP_SHADOW_COLOR = "textShadowColor";
    private static final String PROP_SHADOW_OFFSET = "textShadowOffset";
    private static final String PROP_SHADOW_OFFSET_HEIGHT = "height";
    private static final String PROP_SHADOW_OFFSET_WIDTH = "width";
    private static final String PROP_SHADOW_RADIUS = "textShadowRadius";
    private static final String PROP_TEXT_TRANSFORM = "textTransform";
    public static final short TA_KEY_ACCESSIBILITY_ROLE = 22;
    public static final short TA_KEY_ALIGNMENT = 12;
    public static final short TA_KEY_ALLOW_FONT_SCALING = 9;
    public static final short TA_KEY_BACKGROUND_COLOR = 1;
    public static final short TA_KEY_BEST_WRITING_DIRECTION = 13;
    public static final short TA_KEY_FONT_FAMILY = 3;
    public static final short TA_KEY_FONT_SIZE = 4;
    public static final short TA_KEY_FONT_SIZE_MULTIPLIER = 5;
    public static final short TA_KEY_FONT_STYLE = 7;
    public static final short TA_KEY_FONT_VARIANT = 8;
    public static final short TA_KEY_FONT_WEIGHT = 6;
    public static final short TA_KEY_FOREGROUND_COLOR = 0;
    public static final short TA_KEY_IS_HIGHLIGHTED = 20;
    public static final short TA_KEY_LAYOUT_DIRECTION = 21;
    public static final short TA_KEY_LETTER_SPACING = 10;
    public static final short TA_KEY_LINE_HEIGHT = 11;
    public static final short TA_KEY_OPACITY = 2;
    public static final short TA_KEY_TEXT_DECORATION_COLOR = 14;
    public static final short TA_KEY_TEXT_DECORATION_LINE = 15;
    public static final short TA_KEY_TEXT_DECORATION_STYLE = 16;
    public static final short TA_KEY_TEXT_SHADOW_COLOR = 19;
    public static final short TA_KEY_TEXT_SHADOW_RADIUS = 18;
    public static final int UNSET = -1;
    protected int mBackgroundColor;
    protected int mColor;
    protected float mLineHeight = Float.NaN;
    protected boolean mIsColorSet = false;
    protected boolean mAllowFontScaling = true;
    protected boolean mIsBackgroundColorSet = false;
    protected int mNumberOfLines = -1;
    protected int mFontSize = -1;
    protected float mFontSizeInput = -1.0f;
    protected float mLineHeightInput = -1.0f;
    protected float mLetterSpacingInput = Float.NaN;
    protected int mTextAlign = 0;
    protected int mLayoutDirection = -1;
    protected TextTransform mTextTransform = TextTransform.NONE;
    protected float mTextShadowOffsetDx = 0.0f;
    protected float mTextShadowOffsetDy = 0.0f;
    protected float mTextShadowRadius = 1.0f;
    protected int mTextShadowColor = 1426063360;
    protected boolean mIsUnderlineTextDecorationSet = false;
    protected boolean mIsLineThroughTextDecorationSet = false;
    protected boolean mIncludeFontPadding = true;
    protected ReactAccessibilityDelegate.AccessibilityRole mAccessibilityRole = null;
    protected boolean mIsAccessibilityRoleSet = false;
    protected boolean mIsAccessibilityLink = false;
    protected int mFontStyle = -1;
    protected int mFontWeight = -1;
    protected String mFontFamily = null;
    protected String mFontFeatureSettings = null;
    protected boolean mContainsImages = false;
    protected float mHeightOfTallestInlineImage = Float.NaN;

    static {
        int r0 = Build.VERSION.SDK_INT;
        DEFAULT_JUSTIFICATION_MODE = 0;
        DEFAULT_BREAK_STRATEGY = Build.VERSION.SDK_INT < 23 ? 0 : 1;
        int r1 = Build.VERSION.SDK_INT;
        DEFAULT_HYPHENATION_FREQUENCY = 0;
    }

    private TextAttributeProps() {
    }

    public static TextAttributeProps fromMapBuffer(MapBuffer mapBuffer) {
        TextAttributeProps textAttributeProps = new TextAttributeProps();
        for (MapBuffer.Entry entry : mapBuffer) {
            int key = entry.getKey();
            if (key == 0) {
                textAttributeProps.setColor(Integer.valueOf(entry.getIntValue()));
            } else if (key == 1) {
                textAttributeProps.setBackgroundColor(Integer.valueOf(entry.getIntValue()));
            } else if (key == 3) {
                textAttributeProps.setFontFamily(entry.getStringValue());
            } else if (key == 4) {
                textAttributeProps.setFontSize((float) entry.getDoubleValue());
            } else if (key == 15) {
                textAttributeProps.setTextDecorationLine(entry.getStringValue());
            } else if (key == 18) {
                textAttributeProps.setTextShadowRadius((float) entry.getDoubleValue());
            } else if (key == 19) {
                textAttributeProps.setTextShadowColor(entry.getIntValue());
            } else if (key == 21) {
                textAttributeProps.setLayoutDirection(entry.getStringValue());
            } else if (key != 22) {
                switch (key) {
                    case 6:
                        textAttributeProps.setFontWeight(entry.getStringValue());
                        continue;
                    case 7:
                        textAttributeProps.setFontStyle(entry.getStringValue());
                        continue;
                    case 8:
                        textAttributeProps.setFontVariant(entry.getMapBufferValue());
                        continue;
                    case 9:
                        textAttributeProps.setAllowFontScaling(entry.getBooleanValue());
                        continue;
                    case 10:
                        textAttributeProps.setLetterSpacing((float) entry.getDoubleValue());
                        continue;
                    case 11:
                        textAttributeProps.setLineHeight((float) entry.getDoubleValue());
                        continue;
                }
            } else {
                textAttributeProps.setAccessibilityRole(entry.getStringValue());
            }
        }
        return textAttributeProps;
    }

    public static TextAttributeProps fromReadableMap(ReactStylesDiffMap reactStylesDiffMap) {
        TextAttributeProps textAttributeProps = new TextAttributeProps();
        textAttributeProps.setNumberOfLines(getIntProp(reactStylesDiffMap, ViewProps.NUMBER_OF_LINES, -1));
        textAttributeProps.setLineHeight(getFloatProp(reactStylesDiffMap, ViewProps.LINE_HEIGHT, -1.0f));
        textAttributeProps.setLetterSpacing(getFloatProp(reactStylesDiffMap, ViewProps.LETTER_SPACING, Float.NaN));
        textAttributeProps.setAllowFontScaling(getBooleanProp(reactStylesDiffMap, ViewProps.ALLOW_FONT_SCALING, true));
        textAttributeProps.setFontSize(getFloatProp(reactStylesDiffMap, "fontSize", -1.0f));
        textAttributeProps.setColor(reactStylesDiffMap.hasKey("color") ? Integer.valueOf(reactStylesDiffMap.getInt("color", 0)) : null);
        textAttributeProps.setColor(reactStylesDiffMap.hasKey(ViewProps.FOREGROUND_COLOR) ? Integer.valueOf(reactStylesDiffMap.getInt(ViewProps.FOREGROUND_COLOR, 0)) : null);
        textAttributeProps.setBackgroundColor(reactStylesDiffMap.hasKey("backgroundColor") ? Integer.valueOf(reactStylesDiffMap.getInt("backgroundColor", 0)) : null);
        textAttributeProps.setFontFamily(getStringProp(reactStylesDiffMap, "fontFamily"));
        textAttributeProps.setFontWeight(getStringProp(reactStylesDiffMap, "fontWeight"));
        textAttributeProps.setFontStyle(getStringProp(reactStylesDiffMap, "fontStyle"));
        textAttributeProps.setFontVariant(getArrayProp(reactStylesDiffMap, ViewProps.FONT_VARIANT));
        textAttributeProps.setIncludeFontPadding(getBooleanProp(reactStylesDiffMap, ViewProps.INCLUDE_FONT_PADDING, true));
        textAttributeProps.setTextDecorationLine(getStringProp(reactStylesDiffMap, ViewProps.TEXT_DECORATION_LINE));
        textAttributeProps.setTextShadowOffset(reactStylesDiffMap.hasKey("textShadowOffset") ? reactStylesDiffMap.getMap("textShadowOffset") : null);
        textAttributeProps.setTextShadowRadius(getFloatProp(reactStylesDiffMap, "textShadowRadius", 1.0f));
        textAttributeProps.setTextShadowColor(getIntProp(reactStylesDiffMap, "textShadowColor", 1426063360));
        textAttributeProps.setTextTransform(getStringProp(reactStylesDiffMap, "textTransform"));
        textAttributeProps.setLayoutDirection(getStringProp(reactStylesDiffMap, ViewProps.LAYOUT_DIRECTION));
        textAttributeProps.setAccessibilityRole(getStringProp(reactStylesDiffMap, ViewProps.ACCESSIBILITY_ROLE));
        return textAttributeProps;
    }

    public static int getTextAlignment(ReactStylesDiffMap reactStylesDiffMap, boolean z) {
        String string = reactStylesDiffMap.hasKey("textAlign") ? reactStylesDiffMap.getString("textAlign") : null;
        if ("justify".equals(string)) {
            return 3;
        }
        if (string == null || "auto".equals(string)) {
            return 0;
        }
        if ("left".equals(string)) {
            if (!z) {
                return 3;
            }
        } else if (!"right".equals(string)) {
            if (TtmlNode.CENTER.equals(string)) {
                return 1;
            }
            throw new JSApplicationIllegalArgumentException("Invalid textAlign: " + string);
        } else if (z) {
            return 3;
        }
        return 5;
    }

    public static int getJustificationMode(ReactStylesDiffMap reactStylesDiffMap) {
        if (!"justify".equals(reactStylesDiffMap.hasKey("textAlign") ? reactStylesDiffMap.getString("textAlign") : null) || Build.VERSION.SDK_INT < 26) {
            return DEFAULT_JUSTIFICATION_MODE;
        }
        return 1;
    }

    private static boolean getBooleanProp(ReactStylesDiffMap reactStylesDiffMap, String str, boolean z) {
        return reactStylesDiffMap.hasKey(str) ? reactStylesDiffMap.getBoolean(str, z) : z;
    }

    private static String getStringProp(ReactStylesDiffMap reactStylesDiffMap, String str) {
        if (reactStylesDiffMap.hasKey(str)) {
            return reactStylesDiffMap.getString(str);
        }
        return null;
    }

    private static int getIntProp(ReactStylesDiffMap reactStylesDiffMap, String str, int r3) {
        return reactStylesDiffMap.hasKey(str) ? reactStylesDiffMap.getInt(str, r3) : r3;
    }

    private static float getFloatProp(ReactStylesDiffMap reactStylesDiffMap, String str, float f) {
        return reactStylesDiffMap.hasKey(str) ? reactStylesDiffMap.getFloat(str, f) : f;
    }

    private static ReadableArray getArrayProp(ReactStylesDiffMap reactStylesDiffMap, String str) {
        if (reactStylesDiffMap.hasKey(str)) {
            return reactStylesDiffMap.getArray(str);
        }
        return null;
    }

    public float getEffectiveLineHeight() {
        return !Float.isNaN(this.mLineHeight) && !Float.isNaN(this.mHeightOfTallestInlineImage) && (this.mHeightOfTallestInlineImage > this.mLineHeight ? 1 : (this.mHeightOfTallestInlineImage == this.mLineHeight ? 0 : -1)) > 0 ? this.mHeightOfTallestInlineImage : this.mLineHeight;
    }

    private void setNumberOfLines(int r1) {
        if (r1 == 0) {
            r1 = -1;
        }
        this.mNumberOfLines = r1;
    }

    private void setLineHeight(float f) {
        float pixelFromDIP;
        this.mLineHeightInput = f;
        if (f == -1.0f) {
            this.mLineHeight = Float.NaN;
            return;
        }
        if (this.mAllowFontScaling) {
            pixelFromDIP = PixelUtil.toPixelFromSP(f);
        } else {
            pixelFromDIP = PixelUtil.toPixelFromDIP(f);
        }
        this.mLineHeight = pixelFromDIP;
    }

    private void setLetterSpacing(float f) {
        this.mLetterSpacingInput = f;
    }

    public float getLetterSpacing() {
        float pixelFromDIP;
        if (this.mAllowFontScaling) {
            pixelFromDIP = PixelUtil.toPixelFromSP(this.mLetterSpacingInput);
        } else {
            pixelFromDIP = PixelUtil.toPixelFromDIP(this.mLetterSpacingInput);
        }
        int r1 = this.mFontSize;
        if (r1 > 0) {
            return pixelFromDIP / r1;
        }
        throw new IllegalArgumentException("FontSize should be a positive value. Current value: " + this.mFontSize);
    }

    private void setAllowFontScaling(boolean z) {
        if (z != this.mAllowFontScaling) {
            this.mAllowFontScaling = z;
            setFontSize(this.mFontSizeInput);
            setLineHeight(this.mLineHeightInput);
            setLetterSpacing(this.mLetterSpacingInput);
        }
    }

    private void setFontSize(float f) {
        double ceil;
        this.mFontSizeInput = f;
        if (f != -1.0f) {
            if (this.mAllowFontScaling) {
                ceil = Math.ceil(PixelUtil.toPixelFromSP(f));
            } else {
                ceil = Math.ceil(PixelUtil.toPixelFromDIP(f));
            }
            f = (float) ceil;
        }
        this.mFontSize = (int) f;
    }

    private void setColor(Integer num) {
        boolean z = num != null;
        this.mIsColorSet = z;
        if (z) {
            this.mColor = num.intValue();
        }
    }

    private void setBackgroundColor(Integer num) {
        boolean z = num != null;
        this.mIsBackgroundColorSet = z;
        if (z) {
            this.mBackgroundColor = num.intValue();
        }
    }

    private void setFontFamily(String str) {
        this.mFontFamily = str;
    }

    private void setFontVariant(ReadableArray readableArray) {
        this.mFontFeatureSettings = ReactTypefaceUtils.parseFontVariant(readableArray);
    }

    private void setFontVariant(MapBuffer mapBuffer) {
        if (mapBuffer == null || mapBuffer.getCount() == 0) {
            this.mFontFeatureSettings = null;
            return;
        }
        ArrayList arrayList = new ArrayList();
        Iterator<MapBuffer.Entry> it = mapBuffer.iterator();
        while (it.hasNext()) {
            String stringValue = it.next().getStringValue();
            if (stringValue != null) {
                stringValue.hashCode();
                char c = 65535;
                switch (stringValue.hashCode()) {
                    case -1195362251:
                        if (stringValue.equals("proportional-nums")) {
                            c = 0;
                            break;
                        }
                        break;
                    case -1061392823:
                        if (stringValue.equals("lining-nums")) {
                            c = 1;
                            break;
                        }
                        break;
                    case -771984547:
                        if (stringValue.equals("tabular-nums")) {
                            c = 2;
                            break;
                        }
                        break;
                    case -659678800:
                        if (stringValue.equals("oldstyle-nums")) {
                            c = 3;
                            break;
                        }
                        break;
                    case 1183323111:
                        if (stringValue.equals("small-caps")) {
                            c = 4;
                            break;
                        }
                        break;
                }
                switch (c) {
                    case 0:
                        arrayList.add("'pnum'");
                        continue;
                    case 1:
                        arrayList.add("'lnum'");
                        continue;
                    case 2:
                        arrayList.add("'tnum'");
                        continue;
                    case 3:
                        arrayList.add("'onum'");
                        continue;
                    case 4:
                        arrayList.add("'smcp'");
                        continue;
                }
            }
        }
        this.mFontFeatureSettings = TextUtils.join(", ", arrayList);
    }

    private void setFontWeight(String str) {
        this.mFontWeight = ReactTypefaceUtils.parseFontWeight(str);
    }

    private void setFontStyle(String str) {
        this.mFontStyle = ReactTypefaceUtils.parseFontStyle(str);
    }

    private void setIncludeFontPadding(boolean z) {
        this.mIncludeFontPadding = z;
    }

    private void setTextDecorationLine(String str) {
        String[] split;
        this.mIsUnderlineTextDecorationSet = false;
        this.mIsLineThroughTextDecorationSet = false;
        if (str != null) {
            for (String str2 : str.split("-")) {
                if (TtmlNode.UNDERLINE.equals(str2)) {
                    this.mIsUnderlineTextDecorationSet = true;
                } else if ("strikethrough".equals(str2)) {
                    this.mIsLineThroughTextDecorationSet = true;
                }
            }
        }
    }

    private void setTextShadowOffset(ReadableMap readableMap) {
        this.mTextShadowOffsetDx = 0.0f;
        this.mTextShadowOffsetDy = 0.0f;
        if (readableMap != null) {
            if (readableMap.hasKey("width") && !readableMap.isNull("width")) {
                this.mTextShadowOffsetDx = PixelUtil.toPixelFromDIP(readableMap.getDouble("width"));
            }
            if (!readableMap.hasKey("height") || readableMap.isNull("height")) {
                return;
            }
            this.mTextShadowOffsetDy = PixelUtil.toPixelFromDIP(readableMap.getDouble("height"));
        }
    }

    public static int getLayoutDirection(String str) {
        if (str == null || AdError.UNDEFINED_DOMAIN.equals(str)) {
            return -1;
        }
        if ("rtl".equals(str)) {
            return 1;
        }
        if ("ltr".equals(str)) {
            return 0;
        }
        throw new JSApplicationIllegalArgumentException("Invalid layoutDirection: " + str);
    }

    private void setLayoutDirection(String str) {
        this.mLayoutDirection = getLayoutDirection(str);
    }

    private void setTextShadowRadius(float f) {
        if (f != this.mTextShadowRadius) {
            this.mTextShadowRadius = f;
        }
    }

    private void setTextShadowColor(int r2) {
        if (r2 != this.mTextShadowColor) {
            this.mTextShadowColor = r2;
        }
    }

    private void setTextTransform(String str) {
        if (str == null || "none".equals(str)) {
            this.mTextTransform = TextTransform.NONE;
        } else if ("uppercase".equals(str)) {
            this.mTextTransform = TextTransform.UPPERCASE;
        } else if ("lowercase".equals(str)) {
            this.mTextTransform = TextTransform.LOWERCASE;
        } else if ("capitalize".equals(str)) {
            this.mTextTransform = TextTransform.CAPITALIZE;
        } else {
            throw new JSApplicationIllegalArgumentException("Invalid textTransform: " + str);
        }
    }

    private void setAccessibilityRole(String str) {
        if (str != null) {
            this.mIsAccessibilityRoleSet = true;
            ReactAccessibilityDelegate.AccessibilityRole fromValue = ReactAccessibilityDelegate.AccessibilityRole.fromValue(str);
            this.mAccessibilityRole = fromValue;
            this.mIsAccessibilityLink = fromValue.equals(ReactAccessibilityDelegate.AccessibilityRole.LINK);
        }
    }

    public static int getTextBreakStrategy(String str) {
        int r0 = DEFAULT_BREAK_STRATEGY;
        if (str != null) {
            str.hashCode();
            if (str.equals("balanced")) {
                return 2;
            }
            return !str.equals(FtsOptions.TOKENIZER_SIMPLE) ? 1 : 0;
        }
        return r0;
    }

    public static int getHyphenationFrequency(String str) {
        int r0 = DEFAULT_HYPHENATION_FREQUENCY;
        if (str != null) {
            str.hashCode();
            if (str.equals("normal")) {
                return 1;
            }
            return !str.equals("none") ? 2 : 0;
        }
        return r0;
    }
}
