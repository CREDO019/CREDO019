package com.facebook.react.views.text;

import android.graphics.Color;
import android.os.Build;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.TextUtils;
import androidx.room.FtsOptions;
import com.facebook.infer.annotation.Assertions;
import com.facebook.react.bridge.JSApplicationIllegalArgumentException;
import com.facebook.react.bridge.ReadableArray;
import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.uimanager.IllegalViewOperationException;
import com.facebook.react.uimanager.LayoutShadowNode;
import com.facebook.react.uimanager.NativeViewHierarchyOptimizer;
import com.facebook.react.uimanager.PixelUtil;
import com.facebook.react.uimanager.ReactShadowNode;
import com.facebook.react.uimanager.ReactShadowNodeImpl;
import com.facebook.react.uimanager.ViewProps;
import com.facebook.react.uimanager.annotations.ReactProp;
import com.facebook.yoga.YogaDirection;
import com.facebook.yoga.YogaUnit;
import com.facebook.yoga.YogaValue;
import com.google.android.exoplayer2.text.ttml.TtmlNode;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/* loaded from: classes.dex */
public abstract class ReactBaseTextShadowNode extends LayoutShadowNode {
    public static final int DEFAULT_TEXT_SHADOW_COLOR = 1426063360;
    private static final String INLINE_VIEW_PLACEHOLDER = "0";
    public static final String PROP_SHADOW_COLOR = "textShadowColor";
    public static final String PROP_SHADOW_OFFSET = "textShadowOffset";
    public static final String PROP_SHADOW_OFFSET_HEIGHT = "height";
    public static final String PROP_SHADOW_OFFSET_WIDTH = "width";
    public static final String PROP_SHADOW_RADIUS = "textShadowRadius";
    public static final String PROP_TEXT_TRANSFORM = "textTransform";
    public static final int UNSET = -1;
    protected boolean mAdjustsFontSizeToFit;
    protected int mBackgroundColor;
    protected int mColor;
    protected boolean mContainsImages;
    protected String mFontFamily;
    protected String mFontFeatureSettings;
    protected int mFontStyle;
    protected int mFontWeight;
    protected int mHyphenationFrequency;
    protected boolean mIncludeFontPadding;
    protected Map<Integer, ReactShadowNode> mInlineViews;
    protected boolean mIsAccessibilityLink;
    protected boolean mIsBackgroundColorSet;
    protected boolean mIsColorSet;
    protected boolean mIsLineThroughTextDecorationSet;
    protected boolean mIsUnderlineTextDecorationSet;
    protected int mJustificationMode;
    protected float mMinimumFontScale;
    protected int mNumberOfLines;
    protected ReactTextViewManagerCallback mReactTextViewManagerCallback;
    protected int mTextAlign;
    protected TextAttributes mTextAttributes;
    protected int mTextBreakStrategy;
    protected int mTextShadowColor;
    protected float mTextShadowOffsetDx;
    protected float mTextShadowOffsetDy;
    protected float mTextShadowRadius;

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public static class SetSpanOperation {
        protected int end;
        protected int start;
        protected ReactSpan what;

        SetSpanOperation(int r1, int r2, ReactSpan reactSpan) {
            this.start = r1;
            this.end = r2;
            this.what = reactSpan;
        }

        public void execute(SpannableStringBuilder spannableStringBuilder, int r5) {
            int r0 = this.start;
            spannableStringBuilder.setSpan(this.what, r0, this.end, ((r5 << 16) & 16711680) | ((r0 == 0 ? 18 : 34) & (-16711681)));
        }
    }

    private static void buildSpannedFromShadowNode(ReactBaseTextShadowNode reactBaseTextShadowNode, SpannableStringBuilder spannableStringBuilder, List<SetSpanOperation> list, TextAttributes textAttributes, boolean z, Map<Integer, ReactShadowNode> map, int r22) {
        TextAttributes textAttributes2;
        float layoutWidth;
        float layoutHeight;
        if (textAttributes != null) {
            textAttributes2 = textAttributes.applyChild(reactBaseTextShadowNode.mTextAttributes);
        } else {
            textAttributes2 = reactBaseTextShadowNode.mTextAttributes;
        }
        TextAttributes textAttributes3 = textAttributes2;
        int childCount = reactBaseTextShadowNode.getChildCount();
        for (int r14 = 0; r14 < childCount; r14++) {
            ReactShadowNodeImpl childAt = reactBaseTextShadowNode.getChildAt(r14);
            if (childAt instanceof ReactRawTextShadowNode) {
                spannableStringBuilder.append((CharSequence) TextTransform.apply(((ReactRawTextShadowNode) childAt).getText(), textAttributes3.getTextTransform()));
            } else if (childAt instanceof ReactBaseTextShadowNode) {
                buildSpannedFromShadowNode((ReactBaseTextShadowNode) childAt, spannableStringBuilder, list, textAttributes3, z, map, spannableStringBuilder.length());
            } else if (childAt instanceof ReactTextInlineImageShadowNode) {
                spannableStringBuilder.append("0");
                list.add(new SetSpanOperation(spannableStringBuilder.length() - 1, spannableStringBuilder.length(), ((ReactTextInlineImageShadowNode) childAt).buildInlineImageSpan()));
            } else if (z) {
                int reactTag = childAt.getReactTag();
                YogaValue styleWidth = childAt.getStyleWidth();
                YogaValue styleHeight = childAt.getStyleHeight();
                if (styleWidth.unit != YogaUnit.POINT || styleHeight.unit != YogaUnit.POINT) {
                    childAt.calculateLayout();
                    layoutWidth = childAt.getLayoutWidth();
                    layoutHeight = childAt.getLayoutHeight();
                } else {
                    layoutWidth = styleWidth.value;
                    layoutHeight = styleHeight.value;
                }
                spannableStringBuilder.append("0");
                list.add(new SetSpanOperation(spannableStringBuilder.length() - 1, spannableStringBuilder.length(), new TextInlineViewPlaceholderSpan(reactTag, (int) layoutWidth, (int) layoutHeight)));
                map.put(Integer.valueOf(reactTag), childAt);
                childAt.markUpdateSeen();
            } else {
                throw new IllegalViewOperationException("Unexpected view type nested under a <Text> or <TextInput> node: " + childAt.getClass());
            }
            childAt.markUpdateSeen();
        }
        int length = spannableStringBuilder.length();
        if (length >= r22) {
            if (reactBaseTextShadowNode.mIsColorSet) {
                list.add(new SetSpanOperation(r22, length, new ReactForegroundColorSpan(reactBaseTextShadowNode.mColor)));
            }
            if (reactBaseTextShadowNode.mIsBackgroundColorSet) {
                list.add(new SetSpanOperation(r22, length, new ReactBackgroundColorSpan(reactBaseTextShadowNode.mBackgroundColor)));
            }
            if (reactBaseTextShadowNode.mIsAccessibilityLink) {
                list.add(new SetSpanOperation(r22, length, new ReactClickableSpan(reactBaseTextShadowNode.getReactTag())));
            }
            float effectiveLetterSpacing = textAttributes3.getEffectiveLetterSpacing();
            if (!Float.isNaN(effectiveLetterSpacing) && (textAttributes == null || textAttributes.getEffectiveLetterSpacing() != effectiveLetterSpacing)) {
                list.add(new SetSpanOperation(r22, length, new CustomLetterSpacingSpan(effectiveLetterSpacing)));
            }
            int effectiveFontSize = textAttributes3.getEffectiveFontSize();
            if (textAttributes == null || textAttributes.getEffectiveFontSize() != effectiveFontSize) {
                list.add(new SetSpanOperation(r22, length, new ReactAbsoluteSizeSpan(effectiveFontSize)));
            }
            if (reactBaseTextShadowNode.mFontStyle != -1 || reactBaseTextShadowNode.mFontWeight != -1 || reactBaseTextShadowNode.mFontFamily != null) {
                list.add(new SetSpanOperation(r22, length, new CustomStyleSpan(reactBaseTextShadowNode.mFontStyle, reactBaseTextShadowNode.mFontWeight, reactBaseTextShadowNode.mFontFeatureSettings, reactBaseTextShadowNode.mFontFamily, reactBaseTextShadowNode.getThemedContext().getAssets())));
            }
            if (reactBaseTextShadowNode.mIsUnderlineTextDecorationSet) {
                list.add(new SetSpanOperation(r22, length, new ReactUnderlineSpan()));
            }
            if (reactBaseTextShadowNode.mIsLineThroughTextDecorationSet) {
                list.add(new SetSpanOperation(r22, length, new ReactStrikethroughSpan()));
            }
            if ((reactBaseTextShadowNode.mTextShadowOffsetDx != 0.0f || reactBaseTextShadowNode.mTextShadowOffsetDy != 0.0f || reactBaseTextShadowNode.mTextShadowRadius != 0.0f) && Color.alpha(reactBaseTextShadowNode.mTextShadowColor) != 0) {
                list.add(new SetSpanOperation(r22, length, new ShadowStyleSpan(reactBaseTextShadowNode.mTextShadowOffsetDx, reactBaseTextShadowNode.mTextShadowOffsetDy, reactBaseTextShadowNode.mTextShadowRadius, reactBaseTextShadowNode.mTextShadowColor)));
            }
            float effectiveLineHeight = textAttributes3.getEffectiveLineHeight();
            if (!Float.isNaN(effectiveLineHeight) && (textAttributes == null || textAttributes.getEffectiveLineHeight() != effectiveLineHeight)) {
                list.add(new SetSpanOperation(r22, length, new CustomLineHeightSpan(effectiveLineHeight)));
            }
            list.add(new SetSpanOperation(r22, length, new ReactTagSpan(reactBaseTextShadowNode.getReactTag())));
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public Spannable spannedFromShadowNode(ReactBaseTextShadowNode reactBaseTextShadowNode, String str, boolean z, NativeViewHierarchyOptimizer nativeViewHierarchyOptimizer) {
        int r3;
        int r9 = 0;
        Assertions.assertCondition((z && nativeViewHierarchyOptimizer == null) ? false : true, "nativeViewHierarchyOptimizer is required when inline views are supported");
        SpannableStringBuilder spannableStringBuilder = new SpannableStringBuilder();
        ArrayList<SetSpanOperation> arrayList = new ArrayList();
        HashMap hashMap = z ? new HashMap() : null;
        if (str != null) {
            spannableStringBuilder.append((CharSequence) TextTransform.apply(str, reactBaseTextShadowNode.mTextAttributes.getTextTransform()));
        }
        buildSpannedFromShadowNode(reactBaseTextShadowNode, spannableStringBuilder, arrayList, null, z, hashMap, 0);
        reactBaseTextShadowNode.mContainsImages = false;
        reactBaseTextShadowNode.mInlineViews = hashMap;
        float f = Float.NaN;
        for (SetSpanOperation setSpanOperation : arrayList) {
            boolean z2 = setSpanOperation.what instanceof TextInlineImageSpan;
            if (z2 || (setSpanOperation.what instanceof TextInlineViewPlaceholderSpan)) {
                if (z2) {
                    r3 = ((TextInlineImageSpan) setSpanOperation.what).getHeight();
                    reactBaseTextShadowNode.mContainsImages = true;
                } else {
                    TextInlineViewPlaceholderSpan textInlineViewPlaceholderSpan = (TextInlineViewPlaceholderSpan) setSpanOperation.what;
                    int height = textInlineViewPlaceholderSpan.getHeight();
                    ReactShadowNode reactShadowNode = (ReactShadowNode) hashMap.get(Integer.valueOf(textInlineViewPlaceholderSpan.getReactTag()));
                    nativeViewHierarchyOptimizer.handleForceViewToBeNonLayoutOnly(reactShadowNode);
                    reactShadowNode.setLayoutParent(reactBaseTextShadowNode);
                    r3 = height;
                }
                if (Float.isNaN(f) || r3 > f) {
                    f = r3;
                }
            }
            setSpanOperation.execute(spannableStringBuilder, r9);
            r9++;
        }
        reactBaseTextShadowNode.mTextAttributes.setHeightOfTallestInlineViewOrImage(f);
        ReactTextViewManagerCallback reactTextViewManagerCallback = this.mReactTextViewManagerCallback;
        if (reactTextViewManagerCallback != null) {
            reactTextViewManagerCallback.onPostProcessSpannable(spannableStringBuilder);
        }
        return spannableStringBuilder;
    }

    public ReactBaseTextShadowNode() {
        this(null);
    }

    public ReactBaseTextShadowNode(ReactTextViewManagerCallback reactTextViewManagerCallback) {
        this.mIsColorSet = false;
        this.mIsBackgroundColorSet = false;
        this.mIsAccessibilityLink = false;
        this.mNumberOfLines = -1;
        this.mTextAlign = 0;
        this.mTextBreakStrategy = Build.VERSION.SDK_INT < 23 ? 0 : 1;
        int r2 = Build.VERSION.SDK_INT;
        this.mHyphenationFrequency = 0;
        int r22 = Build.VERSION.SDK_INT;
        this.mJustificationMode = 0;
        this.mTextShadowOffsetDx = 0.0f;
        this.mTextShadowOffsetDy = 0.0f;
        this.mTextShadowRadius = 0.0f;
        this.mTextShadowColor = DEFAULT_TEXT_SHADOW_COLOR;
        this.mIsUnderlineTextDecorationSet = false;
        this.mIsLineThroughTextDecorationSet = false;
        this.mIncludeFontPadding = true;
        this.mAdjustsFontSizeToFit = false;
        this.mMinimumFontScale = 0.0f;
        this.mFontStyle = -1;
        this.mFontWeight = -1;
        this.mFontFamily = null;
        this.mFontFeatureSettings = null;
        this.mContainsImages = false;
        this.mTextAttributes = new TextAttributes();
        this.mReactTextViewManagerCallback = reactTextViewManagerCallback;
    }

    private int getTextAlign() {
        int r0 = this.mTextAlign;
        if (getLayoutDirection() == YogaDirection.RTL) {
            if (r0 == 5) {
                return 3;
            }
            if (r0 == 3) {
                return 5;
            }
            return r0;
        }
        return r0;
    }

    @ReactProp(defaultInt = -1, name = ViewProps.NUMBER_OF_LINES)
    public void setNumberOfLines(int r1) {
        if (r1 == 0) {
            r1 = -1;
        }
        this.mNumberOfLines = r1;
        markUpdated();
    }

    @ReactProp(defaultFloat = Float.NaN, name = ViewProps.LINE_HEIGHT)
    public void setLineHeight(float f) {
        this.mTextAttributes.setLineHeight(f);
        markUpdated();
    }

    @ReactProp(defaultFloat = Float.NaN, name = ViewProps.LETTER_SPACING)
    public void setLetterSpacing(float f) {
        this.mTextAttributes.setLetterSpacing(f);
        markUpdated();
    }

    @ReactProp(defaultBoolean = true, name = ViewProps.ALLOW_FONT_SCALING)
    public void setAllowFontScaling(boolean z) {
        if (z != this.mTextAttributes.getAllowFontScaling()) {
            this.mTextAttributes.setAllowFontScaling(z);
            markUpdated();
        }
    }

    @ReactProp(defaultFloat = Float.NaN, name = ViewProps.MAX_FONT_SIZE_MULTIPLIER)
    public void setMaxFontSizeMultiplier(float f) {
        if (f != this.mTextAttributes.getMaxFontSizeMultiplier()) {
            this.mTextAttributes.setMaxFontSizeMultiplier(f);
            markUpdated();
        }
    }

    @ReactProp(name = "textAlign")
    public void setTextAlign(String str) {
        if ("justify".equals(str)) {
            if (Build.VERSION.SDK_INT >= 26) {
                this.mJustificationMode = 1;
            }
            this.mTextAlign = 3;
        } else {
            if (Build.VERSION.SDK_INT >= 26) {
                this.mJustificationMode = 0;
            }
            if (str == null || "auto".equals(str)) {
                this.mTextAlign = 0;
            } else if ("left".equals(str)) {
                this.mTextAlign = 3;
            } else if ("right".equals(str)) {
                this.mTextAlign = 5;
            } else if (TtmlNode.CENTER.equals(str)) {
                this.mTextAlign = 1;
            } else {
                throw new JSApplicationIllegalArgumentException("Invalid textAlign: " + str);
            }
        }
        markUpdated();
    }

    @ReactProp(defaultFloat = Float.NaN, name = "fontSize")
    public void setFontSize(float f) {
        this.mTextAttributes.setFontSize(f);
        markUpdated();
    }

    @ReactProp(customType = "Color", name = "color")
    public void setColor(Integer num) {
        boolean z = num != null;
        this.mIsColorSet = z;
        if (z) {
            this.mColor = num.intValue();
        }
        markUpdated();
    }

    @ReactProp(customType = "Color", name = "backgroundColor")
    public void setBackgroundColor(Integer num) {
        if (isVirtual()) {
            boolean z = num != null;
            this.mIsBackgroundColorSet = z;
            if (z) {
                this.mBackgroundColor = num.intValue();
            }
            markUpdated();
        }
    }

    @ReactProp(name = ViewProps.ACCESSIBILITY_ROLE)
    public void setIsAccessibilityLink(String str) {
        if (isVirtual()) {
            this.mIsAccessibilityLink = Objects.equals(str, "link");
            markUpdated();
        }
    }

    @ReactProp(name = "fontFamily")
    public void setFontFamily(String str) {
        this.mFontFamily = str;
        markUpdated();
    }

    @ReactProp(name = "fontWeight")
    public void setFontWeight(String str) {
        int parseFontWeight = ReactTypefaceUtils.parseFontWeight(str);
        if (parseFontWeight != this.mFontWeight) {
            this.mFontWeight = parseFontWeight;
            markUpdated();
        }
    }

    @ReactProp(name = ViewProps.FONT_VARIANT)
    public void setFontVariant(ReadableArray readableArray) {
        String parseFontVariant = ReactTypefaceUtils.parseFontVariant(readableArray);
        if (TextUtils.equals(parseFontVariant, this.mFontFeatureSettings)) {
            return;
        }
        this.mFontFeatureSettings = parseFontVariant;
        markUpdated();
    }

    @ReactProp(name = "fontStyle")
    public void setFontStyle(String str) {
        int parseFontStyle = ReactTypefaceUtils.parseFontStyle(str);
        if (parseFontStyle != this.mFontStyle) {
            this.mFontStyle = parseFontStyle;
            markUpdated();
        }
    }

    @ReactProp(defaultBoolean = true, name = ViewProps.INCLUDE_FONT_PADDING)
    public void setIncludeFontPadding(boolean z) {
        this.mIncludeFontPadding = z;
    }

    @ReactProp(name = ViewProps.TEXT_DECORATION_LINE)
    public void setTextDecorationLine(String str) {
        String[] split;
        this.mIsUnderlineTextDecorationSet = false;
        this.mIsLineThroughTextDecorationSet = false;
        if (str != null) {
            for (String str2 : str.split(" ")) {
                if (TtmlNode.UNDERLINE.equals(str2)) {
                    this.mIsUnderlineTextDecorationSet = true;
                } else if ("line-through".equals(str2)) {
                    this.mIsLineThroughTextDecorationSet = true;
                }
            }
        }
        markUpdated();
    }

    @ReactProp(name = ViewProps.TEXT_BREAK_STRATEGY)
    public void setTextBreakStrategy(String str) {
        if (Build.VERSION.SDK_INT < 23) {
            return;
        }
        if (str == null || "highQuality".equals(str)) {
            this.mTextBreakStrategy = 1;
        } else if (FtsOptions.TOKENIZER_SIMPLE.equals(str)) {
            this.mTextBreakStrategy = 0;
        } else if ("balanced".equals(str)) {
            this.mTextBreakStrategy = 2;
        } else {
            throw new JSApplicationIllegalArgumentException("Invalid textBreakStrategy: " + str);
        }
        markUpdated();
    }

    @ReactProp(name = PROP_SHADOW_OFFSET)
    public void setTextShadowOffset(ReadableMap readableMap) {
        this.mTextShadowOffsetDx = 0.0f;
        this.mTextShadowOffsetDy = 0.0f;
        if (readableMap != null) {
            if (readableMap.hasKey("width") && !readableMap.isNull("width")) {
                this.mTextShadowOffsetDx = PixelUtil.toPixelFromDIP(readableMap.getDouble("width"));
            }
            if (readableMap.hasKey("height") && !readableMap.isNull("height")) {
                this.mTextShadowOffsetDy = PixelUtil.toPixelFromDIP(readableMap.getDouble("height"));
            }
        }
        markUpdated();
    }

    @ReactProp(defaultInt = 1, name = PROP_SHADOW_RADIUS)
    public void setTextShadowRadius(float f) {
        if (f != this.mTextShadowRadius) {
            this.mTextShadowRadius = f;
            markUpdated();
        }
    }

    @ReactProp(customType = "Color", defaultInt = DEFAULT_TEXT_SHADOW_COLOR, name = PROP_SHADOW_COLOR)
    public void setTextShadowColor(int r2) {
        if (r2 != this.mTextShadowColor) {
            this.mTextShadowColor = r2;
            markUpdated();
        }
    }

    @ReactProp(name = PROP_TEXT_TRANSFORM)
    public void setTextTransform(String str) {
        if (str == null) {
            this.mTextAttributes.setTextTransform(TextTransform.UNSET);
        } else if ("none".equals(str)) {
            this.mTextAttributes.setTextTransform(TextTransform.NONE);
        } else if ("uppercase".equals(str)) {
            this.mTextAttributes.setTextTransform(TextTransform.UPPERCASE);
        } else if ("lowercase".equals(str)) {
            this.mTextAttributes.setTextTransform(TextTransform.LOWERCASE);
        } else if ("capitalize".equals(str)) {
            this.mTextAttributes.setTextTransform(TextTransform.CAPITALIZE);
        } else {
            throw new JSApplicationIllegalArgumentException("Invalid textTransform: " + str);
        }
        markUpdated();
    }

    @ReactProp(name = ViewProps.ADJUSTS_FONT_SIZE_TO_FIT)
    public void setAdjustFontSizeToFit(boolean z) {
        if (z != this.mAdjustsFontSizeToFit) {
            this.mAdjustsFontSizeToFit = z;
            markUpdated();
        }
    }

    @ReactProp(name = ViewProps.MINIMUM_FONT_SCALE)
    public void setMinimumFontScale(float f) {
        if (f != this.mMinimumFontScale) {
            this.mMinimumFontScale = f;
            markUpdated();
        }
    }
}