package com.horcrux.svg;

import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.bridge.ReadableType;
import com.facebook.react.views.text.TypefaceStyle;
import com.google.firebase.messaging.ServiceStarter;
import com.horcrux.svg.TextProperties;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes3.dex */
public class FontData {
    static final double DEFAULT_FONT_SIZE = 12.0d;
    private static final double DEFAULT_KERNING = 0.0d;
    private static final double DEFAULT_LETTER_SPACING = 0.0d;
    private static final double DEFAULT_WORD_SPACING = 0.0d;
    static final FontData Defaults = new FontData();
    private static final String FONT_DATA = "fontData";
    private static final String FONT_FEATURE_SETTINGS = "fontFeatureSettings";
    private static final String FONT_VARIANT_LIGATURES = "fontVariantLigatures";
    private static final String FONT_VARIATION_SETTINGS = "fontVariationSettings";
    private static final String KERNING = "kerning";
    private static final String LETTER_SPACING = "letterSpacing";
    private static final String TEXT_ANCHOR = "textAnchor";
    private static final String TEXT_DECORATION = "textDecoration";
    private static final String WORD_SPACING = "wordSpacing";
    int absoluteFontWeight;
    final ReadableMap fontData;
    final String fontFamily;
    final String fontFeatureSettings;
    final double fontSize;
    final TextProperties.FontStyle fontStyle;
    final TextProperties.FontVariantLigatures fontVariantLigatures;
    final String fontVariationSettings;
    TextProperties.FontWeight fontWeight;
    final double kerning;
    final double letterSpacing;
    final boolean manualKerning;
    final TextProperties.TextAnchor textAnchor;
    private final TextProperties.TextDecoration textDecoration;
    final double wordSpacing;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes3.dex */
    public static class AbsoluteFontWeight {
        private static final TextProperties.FontWeight[] WEIGHTS = {TextProperties.FontWeight.w100, TextProperties.FontWeight.w100, TextProperties.FontWeight.w200, TextProperties.FontWeight.w300, TextProperties.FontWeight.Normal, TextProperties.FontWeight.w500, TextProperties.FontWeight.w600, TextProperties.FontWeight.Bold, TextProperties.FontWeight.w800, TextProperties.FontWeight.w900, TextProperties.FontWeight.w900};
        private static final int[] absoluteFontWeights = {400, TypefaceStyle.BOLD, 100, 200, 300, 400, ServiceStarter.ERROR_UNKNOWN, 600, TypefaceStyle.BOLD, 800, 900};
        static final int normal = 400;

        private static int bolder(int r1) {
            if (r1 < 350) {
                return 400;
            }
            if (r1 < 550) {
                return TypefaceStyle.BOLD;
            }
            if (r1 < 900) {
                return 900;
            }
            return r1;
        }

        private static int lighter(int r2) {
            if (r2 < 100) {
                return r2;
            }
            if (r2 < 550) {
                return 100;
            }
            if (r2 < 750) {
                return 400;
            }
            return TypefaceStyle.BOLD;
        }

        AbsoluteFontWeight() {
        }

        static TextProperties.FontWeight nearestFontWeight(int r2) {
            return WEIGHTS[Math.round(r2 / 100.0f)];
        }

        static int from(TextProperties.FontWeight fontWeight, FontData fontData) {
            if (fontWeight == TextProperties.FontWeight.Bolder) {
                return bolder(fontData.absoluteFontWeight);
            }
            if (fontWeight == TextProperties.FontWeight.Lighter) {
                return lighter(fontData.absoluteFontWeight);
            }
            return absoluteFontWeights[fontWeight.ordinal()];
        }
    }

    private FontData() {
        this.fontData = null;
        this.fontFamily = "";
        this.fontStyle = TextProperties.FontStyle.normal;
        this.fontWeight = TextProperties.FontWeight.Normal;
        this.absoluteFontWeight = 400;
        this.fontFeatureSettings = "";
        this.fontVariationSettings = "";
        this.fontVariantLigatures = TextProperties.FontVariantLigatures.normal;
        this.textAnchor = TextProperties.TextAnchor.start;
        this.textDecoration = TextProperties.TextDecoration.None;
        this.manualKerning = false;
        this.kerning = 0.0d;
        this.fontSize = DEFAULT_FONT_SIZE;
        this.wordSpacing = 0.0d;
        this.letterSpacing = 0.0d;
    }

    private double toAbsolute(ReadableMap readableMap, String str, double d, double d2, double d3) {
        if (readableMap.getType(str) == ReadableType.Number) {
            return readableMap.getDouble(str);
        }
        return PropHelper.fromRelative(readableMap.getString(str), d3, d, d2);
    }

    private void setInheritedWeight(FontData fontData) {
        this.absoluteFontWeight = fontData.absoluteFontWeight;
        this.fontWeight = fontData.fontWeight;
    }

    private void handleNumericWeight(FontData fontData, double d) {
        long round = Math.round(d);
        if (round >= 1 && round <= 1000) {
            int r4 = (int) round;
            this.absoluteFontWeight = r4;
            this.fontWeight = AbsoluteFontWeight.nearestFontWeight(r4);
            return;
        }
        setInheritedWeight(fontData);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public FontData(ReadableMap readableMap, FontData fontData, double d) {
        String str;
        String str2;
        TextProperties.FontVariantLigatures fontVariantLigatures;
        TextProperties.TextAnchor textAnchor;
        TextProperties.TextDecoration textDecoration;
        double d2;
        double d3;
        double d4 = fontData.fontSize;
        if (readableMap.hasKey("fontSize")) {
            this.fontSize = toAbsolute(readableMap, "fontSize", 1.0d, d4, d4);
        } else {
            this.fontSize = d4;
        }
        if (readableMap.hasKey("fontWeight")) {
            if (readableMap.getType("fontWeight") == ReadableType.Number) {
                handleNumericWeight(fontData, readableMap.getDouble("fontWeight"));
            } else {
                String string = readableMap.getString("fontWeight");
                if (TextProperties.FontWeight.hasEnum(string)) {
                    int from = AbsoluteFontWeight.from(TextProperties.FontWeight.get(string), fontData);
                    this.absoluteFontWeight = from;
                    this.fontWeight = AbsoluteFontWeight.nearestFontWeight(from);
                } else if (string != null) {
                    handleNumericWeight(fontData, Double.parseDouble(string));
                } else {
                    setInheritedWeight(fontData);
                }
            }
        } else {
            setInheritedWeight(fontData);
        }
        this.fontData = readableMap.hasKey(FONT_DATA) ? readableMap.getMap(FONT_DATA) : fontData.fontData;
        this.fontFamily = readableMap.hasKey("fontFamily") ? readableMap.getString("fontFamily") : fontData.fontFamily;
        this.fontStyle = readableMap.hasKey("fontStyle") ? TextProperties.FontStyle.valueOf(readableMap.getString("fontStyle")) : fontData.fontStyle;
        if (readableMap.hasKey(FONT_FEATURE_SETTINGS)) {
            str = readableMap.getString(FONT_FEATURE_SETTINGS);
        } else {
            str = fontData.fontFeatureSettings;
        }
        this.fontFeatureSettings = str;
        if (readableMap.hasKey(FONT_VARIATION_SETTINGS)) {
            str2 = readableMap.getString(FONT_VARIATION_SETTINGS);
        } else {
            str2 = fontData.fontVariationSettings;
        }
        this.fontVariationSettings = str2;
        if (readableMap.hasKey(FONT_VARIANT_LIGATURES)) {
            fontVariantLigatures = TextProperties.FontVariantLigatures.valueOf(readableMap.getString(FONT_VARIANT_LIGATURES));
        } else {
            fontVariantLigatures = fontData.fontVariantLigatures;
        }
        this.fontVariantLigatures = fontVariantLigatures;
        if (readableMap.hasKey(TEXT_ANCHOR)) {
            textAnchor = TextProperties.TextAnchor.valueOf(readableMap.getString(TEXT_ANCHOR));
        } else {
            textAnchor = fontData.textAnchor;
        }
        this.textAnchor = textAnchor;
        if (readableMap.hasKey("textDecoration")) {
            textDecoration = TextProperties.TextDecoration.getEnum(readableMap.getString("textDecoration"));
        } else {
            textDecoration = fontData.textDecoration;
        }
        this.textDecoration = textDecoration;
        boolean hasKey = readableMap.hasKey(KERNING);
        this.manualKerning = hasKey || fontData.manualKerning;
        this.kerning = hasKey ? toAbsolute(readableMap, KERNING, d, this.fontSize, 0.0d) : fontData.kerning;
        if (readableMap.hasKey(WORD_SPACING)) {
            d2 = toAbsolute(readableMap, WORD_SPACING, d, this.fontSize, 0.0d);
        } else {
            d2 = fontData.wordSpacing;
        }
        this.wordSpacing = d2;
        if (readableMap.hasKey("letterSpacing")) {
            d3 = toAbsolute(readableMap, "letterSpacing", d, this.fontSize, 0.0d);
        } else {
            d3 = fontData.letterSpacing;
        }
        this.letterSpacing = d3;
    }
}
