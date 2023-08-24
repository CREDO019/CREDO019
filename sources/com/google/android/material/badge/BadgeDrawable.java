package com.google.android.material.badge;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.os.Parcel;
import android.os.Parcelable;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import androidx.core.view.ViewCompat;
import com.google.android.material.C2151R;
import com.google.android.material.drawable.DrawableUtils;
import com.google.android.material.internal.TextDrawableHelper;
import com.google.android.material.internal.ThemeEnforcement;
import com.google.android.material.resources.MaterialResources;
import com.google.android.material.resources.TextAppearance;
import com.google.android.material.shape.MaterialShapeDrawable;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.ref.WeakReference;

/* loaded from: classes3.dex */
public class BadgeDrawable extends Drawable implements TextDrawableHelper.TextDrawableDelegate {
    private static final int BADGE_NUMBER_NONE = -1;
    public static final int BOTTOM_END = 8388693;
    public static final int BOTTOM_START = 8388691;
    static final String DEFAULT_EXCEED_MAX_BADGE_NUMBER_SUFFIX = "+";
    private static final int DEFAULT_MAX_BADGE_CHARACTER_COUNT = 4;
    private static final int DEFAULT_STYLE = C2151R.C2157style.Widget_MaterialComponents_Badge;
    private static final int DEFAULT_THEME_ATTR = C2151R.attr.badgeStyle;
    private static final int MAX_CIRCULAR_BADGE_NUMBER_COUNT = 9;
    public static final int TOP_END = 8388661;
    public static final int TOP_START = 8388659;
    private WeakReference<View> anchorViewRef;
    private final Rect badgeBounds;
    private float badgeCenterX;
    private float badgeCenterY;
    private final float badgeRadius;
    private final float badgeWidePadding;
    private final float badgeWithTextRadius;
    private final WeakReference<Context> contextRef;
    private float cornerRadius;
    private WeakReference<ViewGroup> customBadgeParentRef;
    private float halfBadgeHeight;
    private float halfBadgeWidth;
    private int maxBadgeNumber;
    private final SavedState savedState;
    private final MaterialShapeDrawable shapeDrawable;
    private final TextDrawableHelper textDrawableHelper;

    @Retention(RetentionPolicy.SOURCE)
    /* loaded from: classes3.dex */
    public @interface BadgeGravity {
    }

    @Override // android.graphics.drawable.Drawable
    public int getOpacity() {
        return -3;
    }

    @Override // android.graphics.drawable.Drawable
    public boolean isStateful() {
        return false;
    }

    @Override // android.graphics.drawable.Drawable
    public void setColorFilter(ColorFilter colorFilter) {
    }

    /* loaded from: classes3.dex */
    public static final class SavedState implements Parcelable {
        public static final Parcelable.Creator<SavedState> CREATOR = new Parcelable.Creator<SavedState>() { // from class: com.google.android.material.badge.BadgeDrawable.SavedState.1
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // android.os.Parcelable.Creator
            public SavedState createFromParcel(Parcel parcel) {
                return new SavedState(parcel);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // android.os.Parcelable.Creator
            public SavedState[] newArray(int r1) {
                return new SavedState[r1];
            }
        };
        private int alpha;
        private int backgroundColor;
        private int badgeGravity;
        private int badgeTextColor;
        private CharSequence contentDescriptionNumberless;
        private int contentDescriptionQuantityStrings;
        private int maxCharacterCount;
        private int number;

        @Override // android.os.Parcelable
        public int describeContents() {
            return 0;
        }

        public SavedState(Context context) {
            this.alpha = 255;
            this.number = -1;
            this.badgeTextColor = new TextAppearance(context, C2151R.C2157style.TextAppearance_MaterialComponents_Badge).textColor.getDefaultColor();
            this.contentDescriptionNumberless = context.getString(C2151R.string.mtrl_badge_numberless_content_description);
            this.contentDescriptionQuantityStrings = C2151R.plurals.mtrl_badge_content_description;
        }

        protected SavedState(Parcel parcel) {
            this.alpha = 255;
            this.number = -1;
            this.backgroundColor = parcel.readInt();
            this.badgeTextColor = parcel.readInt();
            this.alpha = parcel.readInt();
            this.number = parcel.readInt();
            this.maxCharacterCount = parcel.readInt();
            this.contentDescriptionNumberless = parcel.readString();
            this.contentDescriptionQuantityStrings = parcel.readInt();
            this.badgeGravity = parcel.readInt();
        }

        @Override // android.os.Parcelable
        public void writeToParcel(Parcel parcel, int r2) {
            parcel.writeInt(this.backgroundColor);
            parcel.writeInt(this.badgeTextColor);
            parcel.writeInt(this.alpha);
            parcel.writeInt(this.number);
            parcel.writeInt(this.maxCharacterCount);
            parcel.writeString(this.contentDescriptionNumberless.toString());
            parcel.writeInt(this.contentDescriptionQuantityStrings);
            parcel.writeInt(this.badgeGravity);
        }
    }

    public SavedState getSavedState() {
        return this.savedState;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static BadgeDrawable createFromSavedState(Context context, SavedState savedState) {
        BadgeDrawable badgeDrawable = new BadgeDrawable(context);
        badgeDrawable.restoreFromSavedState(savedState);
        return badgeDrawable;
    }

    public static BadgeDrawable create(Context context) {
        return createFromAttributes(context, null, DEFAULT_THEME_ATTR, DEFAULT_STYLE);
    }

    public static BadgeDrawable createFromResource(Context context, int r3) {
        AttributeSet parseDrawableXml = DrawableUtils.parseDrawableXml(context, r3, "badge");
        int styleAttribute = parseDrawableXml.getStyleAttribute();
        if (styleAttribute == 0) {
            styleAttribute = DEFAULT_STYLE;
        }
        return createFromAttributes(context, parseDrawableXml, DEFAULT_THEME_ATTR, styleAttribute);
    }

    private static BadgeDrawable createFromAttributes(Context context, AttributeSet attributeSet, int r3, int r4) {
        BadgeDrawable badgeDrawable = new BadgeDrawable(context);
        badgeDrawable.loadDefaultStateFromAttributes(context, attributeSet, r3, r4);
        return badgeDrawable;
    }

    public void setVisible(boolean z) {
        setVisible(z, false);
    }

    private void restoreFromSavedState(SavedState savedState) {
        setMaxCharacterCount(savedState.maxCharacterCount);
        if (savedState.number != -1) {
            setNumber(savedState.number);
        }
        setBackgroundColor(savedState.backgroundColor);
        setBadgeTextColor(savedState.badgeTextColor);
        setBadgeGravity(savedState.badgeGravity);
    }

    private void loadDefaultStateFromAttributes(Context context, AttributeSet attributeSet, int r10, int r11) {
        TypedArray obtainStyledAttributes = ThemeEnforcement.obtainStyledAttributes(context, attributeSet, C2151R.styleable.Badge, r10, r11, new int[0]);
        setMaxCharacterCount(obtainStyledAttributes.getInt(C2151R.styleable.Badge_maxCharacterCount, 4));
        if (obtainStyledAttributes.hasValue(C2151R.styleable.Badge_number)) {
            setNumber(obtainStyledAttributes.getInt(C2151R.styleable.Badge_number, 0));
        }
        setBackgroundColor(readColorFromAttributes(context, obtainStyledAttributes, C2151R.styleable.Badge_backgroundColor));
        if (obtainStyledAttributes.hasValue(C2151R.styleable.Badge_badgeTextColor)) {
            setBadgeTextColor(readColorFromAttributes(context, obtainStyledAttributes, C2151R.styleable.Badge_badgeTextColor));
        }
        setBadgeGravity(obtainStyledAttributes.getInt(C2151R.styleable.Badge_badgeGravity, TOP_END));
        obtainStyledAttributes.recycle();
    }

    private static int readColorFromAttributes(Context context, TypedArray typedArray, int r2) {
        return MaterialResources.getColorStateList(context, typedArray, r2).getDefaultColor();
    }

    private BadgeDrawable(Context context) {
        this.contextRef = new WeakReference<>(context);
        ThemeEnforcement.checkMaterialTheme(context);
        Resources resources = context.getResources();
        this.badgeBounds = new Rect();
        this.shapeDrawable = new MaterialShapeDrawable();
        this.badgeRadius = resources.getDimensionPixelSize(C2151R.dimen.mtrl_badge_radius);
        this.badgeWidePadding = resources.getDimensionPixelSize(C2151R.dimen.mtrl_badge_long_text_horizontal_padding);
        this.badgeWithTextRadius = resources.getDimensionPixelSize(C2151R.dimen.mtrl_badge_with_text_radius);
        TextDrawableHelper textDrawableHelper = new TextDrawableHelper(this);
        this.textDrawableHelper = textDrawableHelper;
        textDrawableHelper.getTextPaint().setTextAlign(Paint.Align.CENTER);
        this.savedState = new SavedState(context);
        setTextAppearanceResource(C2151R.C2157style.TextAppearance_MaterialComponents_Badge);
    }

    public void updateBadgeCoordinates(View view, ViewGroup viewGroup) {
        this.anchorViewRef = new WeakReference<>(view);
        this.customBadgeParentRef = new WeakReference<>(viewGroup);
        updateCenterAndBounds();
        invalidateSelf();
    }

    public int getBackgroundColor() {
        return this.shapeDrawable.getFillColor().getDefaultColor();
    }

    public void setBackgroundColor(int r2) {
        this.savedState.backgroundColor = r2;
        ColorStateList valueOf = ColorStateList.valueOf(r2);
        if (this.shapeDrawable.getFillColor() != valueOf) {
            this.shapeDrawable.setFillColor(valueOf);
            invalidateSelf();
        }
    }

    public int getBadgeTextColor() {
        return this.textDrawableHelper.getTextPaint().getColor();
    }

    public void setBadgeTextColor(int r2) {
        this.savedState.badgeTextColor = r2;
        if (this.textDrawableHelper.getTextPaint().getColor() != r2) {
            this.textDrawableHelper.getTextPaint().setColor(r2);
            invalidateSelf();
        }
    }

    public boolean hasNumber() {
        return this.savedState.number != -1;
    }

    public int getNumber() {
        if (hasNumber()) {
            return this.savedState.number;
        }
        return 0;
    }

    public void setNumber(int r2) {
        int max = Math.max(0, r2);
        if (this.savedState.number != max) {
            this.savedState.number = max;
            this.textDrawableHelper.setTextWidthDirty(true);
            updateCenterAndBounds();
            invalidateSelf();
        }
    }

    public void clearNumber() {
        this.savedState.number = -1;
        invalidateSelf();
    }

    public int getMaxCharacterCount() {
        return this.savedState.maxCharacterCount;
    }

    public void setMaxCharacterCount(int r2) {
        if (this.savedState.maxCharacterCount != r2) {
            this.savedState.maxCharacterCount = r2;
            updateMaxBadgeNumber();
            this.textDrawableHelper.setTextWidthDirty(true);
            updateCenterAndBounds();
            invalidateSelf();
        }
    }

    public int getBadgeGravity() {
        return this.savedState.badgeGravity;
    }

    public void setBadgeGravity(int r2) {
        if (this.savedState.badgeGravity != r2) {
            this.savedState.badgeGravity = r2;
            WeakReference<View> weakReference = this.anchorViewRef;
            if (weakReference == null || weakReference.get() == null) {
                return;
            }
            View view = this.anchorViewRef.get();
            WeakReference<ViewGroup> weakReference2 = this.customBadgeParentRef;
            updateBadgeCoordinates(view, weakReference2 != null ? weakReference2.get() : null);
        }
    }

    @Override // android.graphics.drawable.Drawable
    public int getAlpha() {
        return this.savedState.alpha;
    }

    @Override // android.graphics.drawable.Drawable
    public void setAlpha(int r2) {
        this.savedState.alpha = r2;
        this.textDrawableHelper.getTextPaint().setAlpha(r2);
        invalidateSelf();
    }

    @Override // android.graphics.drawable.Drawable
    public int getIntrinsicHeight() {
        return this.badgeBounds.height();
    }

    @Override // android.graphics.drawable.Drawable
    public int getIntrinsicWidth() {
        return this.badgeBounds.width();
    }

    @Override // android.graphics.drawable.Drawable
    public void draw(Canvas canvas) {
        if (getBounds().isEmpty() || getAlpha() == 0 || !isVisible()) {
            return;
        }
        this.shapeDrawable.draw(canvas);
        if (hasNumber()) {
            drawText(canvas);
        }
    }

    @Override // com.google.android.material.internal.TextDrawableHelper.TextDrawableDelegate
    public void onTextSizeChange() {
        invalidateSelf();
    }

    @Override // android.graphics.drawable.Drawable, com.google.android.material.internal.TextDrawableHelper.TextDrawableDelegate
    public boolean onStateChange(int[] r1) {
        return super.onStateChange(r1);
    }

    public void setContentDescriptionNumberless(CharSequence charSequence) {
        this.savedState.contentDescriptionNumberless = charSequence;
    }

    public void setContentDescriptionQuantityStringsResource(int r2) {
        this.savedState.contentDescriptionQuantityStrings = r2;
    }

    public CharSequence getContentDescription() {
        Context context;
        if (isVisible()) {
            if (hasNumber()) {
                if (this.savedState.contentDescriptionQuantityStrings <= 0 || (context = this.contextRef.get()) == null) {
                    return null;
                }
                return context.getResources().getQuantityString(this.savedState.contentDescriptionQuantityStrings, getNumber(), Integer.valueOf(getNumber()));
            }
            return this.savedState.contentDescriptionNumberless;
        }
        return null;
    }

    private void setTextAppearanceResource(int r3) {
        Context context = this.contextRef.get();
        if (context == null) {
            return;
        }
        setTextAppearance(new TextAppearance(context, r3));
    }

    private void setTextAppearance(TextAppearance textAppearance) {
        Context context;
        if (this.textDrawableHelper.getTextAppearance() == textAppearance || (context = this.contextRef.get()) == null) {
            return;
        }
        this.textDrawableHelper.setTextAppearance(textAppearance, context);
        updateCenterAndBounds();
    }

    private void updateCenterAndBounds() {
        Context context = this.contextRef.get();
        WeakReference<View> weakReference = this.anchorViewRef;
        View view = weakReference != null ? weakReference.get() : null;
        if (context == null || view == null) {
            return;
        }
        Rect rect = new Rect();
        rect.set(this.badgeBounds);
        Rect rect2 = new Rect();
        view.getDrawingRect(rect2);
        WeakReference<ViewGroup> weakReference2 = this.customBadgeParentRef;
        ViewGroup viewGroup = weakReference2 != null ? weakReference2.get() : null;
        if (viewGroup != null || BadgeUtils.USE_COMPAT_PARENT) {
            if (viewGroup == null) {
                viewGroup = (ViewGroup) view.getParent();
            }
            viewGroup.offsetDescendantRectToMyCoords(view, rect2);
        }
        calculateCenterAndBounds(context, rect2, view);
        BadgeUtils.updateBadgeBounds(this.badgeBounds, this.badgeCenterX, this.badgeCenterY, this.halfBadgeWidth, this.halfBadgeHeight);
        this.shapeDrawable.setCornerSize(this.cornerRadius);
        if (rect.equals(this.badgeBounds)) {
            return;
        }
        this.shapeDrawable.setBounds(this.badgeBounds);
    }

    private void calculateCenterAndBounds(Context context, Rect rect, View view) {
        int r0 = this.savedState.badgeGravity;
        if (r0 == 8388691 || r0 == 8388693) {
            this.badgeCenterY = rect.bottom;
        } else {
            this.badgeCenterY = rect.top;
        }
        if (getNumber() <= 9) {
            float f = !hasNumber() ? this.badgeRadius : this.badgeWithTextRadius;
            this.cornerRadius = f;
            this.halfBadgeHeight = f;
            this.halfBadgeWidth = f;
        } else {
            float f2 = this.badgeWithTextRadius;
            this.cornerRadius = f2;
            this.halfBadgeHeight = f2;
            this.halfBadgeWidth = (this.textDrawableHelper.getTextWidth(getBadgeText()) / 2.0f) + this.badgeWidePadding;
        }
        int dimensionPixelSize = context.getResources().getDimensionPixelSize(hasNumber() ? C2151R.dimen.mtrl_badge_text_horizontal_edge_offset : C2151R.dimen.mtrl_badge_horizontal_edge_offset);
        int r02 = this.savedState.badgeGravity;
        if (r02 == 8388659 || r02 == 8388691) {
            this.badgeCenterX = ViewCompat.getLayoutDirection(view) == 0 ? (rect.left - this.halfBadgeWidth) + dimensionPixelSize : (rect.right + this.halfBadgeWidth) - dimensionPixelSize;
        } else {
            this.badgeCenterX = ViewCompat.getLayoutDirection(view) == 0 ? (rect.right + this.halfBadgeWidth) - dimensionPixelSize : (rect.left - this.halfBadgeWidth) + dimensionPixelSize;
        }
    }

    private void drawText(Canvas canvas) {
        Rect rect = new Rect();
        String badgeText = getBadgeText();
        this.textDrawableHelper.getTextPaint().getTextBounds(badgeText, 0, badgeText.length(), rect);
        canvas.drawText(badgeText, this.badgeCenterX, this.badgeCenterY + (rect.height() / 2), this.textDrawableHelper.getTextPaint());
    }

    private String getBadgeText() {
        if (getNumber() <= this.maxBadgeNumber) {
            return Integer.toString(getNumber());
        }
        Context context = this.contextRef.get();
        return context == null ? "" : context.getString(C2151R.string.mtrl_exceed_max_badge_number_suffix, Integer.valueOf(this.maxBadgeNumber), DEFAULT_EXCEED_MAX_BADGE_NUMBER_SUFFIX);
    }

    private void updateMaxBadgeNumber() {
        this.maxBadgeNumber = ((int) Math.pow(10.0d, getMaxCharacterCount() - 1.0d)) - 1;
    }
}