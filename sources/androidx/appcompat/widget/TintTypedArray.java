package androidx.appcompat.widget;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.util.AttributeSet;
import android.util.TypedValue;
import androidx.appcompat.content.res.AppCompatResources;
import androidx.core.content.res.ResourcesCompat;

/* loaded from: classes.dex */
public class TintTypedArray {
    private final Context mContext;
    private TypedValue mTypedValue;
    private final TypedArray mWrapped;

    public static TintTypedArray obtainStyledAttributes(Context context, AttributeSet attributeSet, int[] r3) {
        return new TintTypedArray(context, context.obtainStyledAttributes(attributeSet, r3));
    }

    public static TintTypedArray obtainStyledAttributes(Context context, AttributeSet attributeSet, int[] r3, int r4, int r5) {
        return new TintTypedArray(context, context.obtainStyledAttributes(attributeSet, r3, r4, r5));
    }

    public static TintTypedArray obtainStyledAttributes(Context context, int r2, int[] r3) {
        return new TintTypedArray(context, context.obtainStyledAttributes(r2, r3));
    }

    private TintTypedArray(Context context, TypedArray typedArray) {
        this.mContext = context;
        this.mWrapped = typedArray;
    }

    public TypedArray getWrappedTypeArray() {
        return this.mWrapped;
    }

    public Drawable getDrawable(int r3) {
        int resourceId;
        if (this.mWrapped.hasValue(r3) && (resourceId = this.mWrapped.getResourceId(r3, 0)) != 0) {
            return AppCompatResources.getDrawable(this.mContext, resourceId);
        }
        return this.mWrapped.getDrawable(r3);
    }

    public Drawable getDrawableIfKnown(int r4) {
        int resourceId;
        if (!this.mWrapped.hasValue(r4) || (resourceId = this.mWrapped.getResourceId(r4, 0)) == 0) {
            return null;
        }
        return AppCompatDrawableManager.get().getDrawable(this.mContext, resourceId, true);
    }

    public Typeface getFont(int r3, int r4, ResourcesCompat.FontCallback fontCallback) {
        int resourceId = this.mWrapped.getResourceId(r3, 0);
        if (resourceId == 0) {
            return null;
        }
        if (this.mTypedValue == null) {
            this.mTypedValue = new TypedValue();
        }
        return ResourcesCompat.getFont(this.mContext, resourceId, this.mTypedValue, r4, fontCallback);
    }

    public int length() {
        return this.mWrapped.length();
    }

    public int getIndexCount() {
        return this.mWrapped.getIndexCount();
    }

    public int getIndex(int r2) {
        return this.mWrapped.getIndex(r2);
    }

    public Resources getResources() {
        return this.mWrapped.getResources();
    }

    public CharSequence getText(int r2) {
        return this.mWrapped.getText(r2);
    }

    public String getString(int r2) {
        return this.mWrapped.getString(r2);
    }

    public String getNonResourceString(int r2) {
        return this.mWrapped.getNonResourceString(r2);
    }

    public boolean getBoolean(int r2, boolean z) {
        return this.mWrapped.getBoolean(r2, z);
    }

    public int getInt(int r2, int r3) {
        return this.mWrapped.getInt(r2, r3);
    }

    public float getFloat(int r2, float f) {
        return this.mWrapped.getFloat(r2, f);
    }

    public int getColor(int r2, int r3) {
        return this.mWrapped.getColor(r2, r3);
    }

    public ColorStateList getColorStateList(int r3) {
        int resourceId;
        ColorStateList colorStateList;
        return (!this.mWrapped.hasValue(r3) || (resourceId = this.mWrapped.getResourceId(r3, 0)) == 0 || (colorStateList = AppCompatResources.getColorStateList(this.mContext, resourceId)) == null) ? this.mWrapped.getColorStateList(r3) : colorStateList;
    }

    public int getInteger(int r2, int r3) {
        return this.mWrapped.getInteger(r2, r3);
    }

    public float getDimension(int r2, float f) {
        return this.mWrapped.getDimension(r2, f);
    }

    public int getDimensionPixelOffset(int r2, int r3) {
        return this.mWrapped.getDimensionPixelOffset(r2, r3);
    }

    public int getDimensionPixelSize(int r2, int r3) {
        return this.mWrapped.getDimensionPixelSize(r2, r3);
    }

    public int getLayoutDimension(int r2, String str) {
        return this.mWrapped.getLayoutDimension(r2, str);
    }

    public int getLayoutDimension(int r2, int r3) {
        return this.mWrapped.getLayoutDimension(r2, r3);
    }

    public float getFraction(int r2, int r3, int r4, float f) {
        return this.mWrapped.getFraction(r2, r3, r4, f);
    }

    public int getResourceId(int r2, int r3) {
        return this.mWrapped.getResourceId(r2, r3);
    }

    public CharSequence[] getTextArray(int r2) {
        return this.mWrapped.getTextArray(r2);
    }

    public boolean getValue(int r2, TypedValue typedValue) {
        return this.mWrapped.getValue(r2, typedValue);
    }

    public int getType(int r3) {
        if (Build.VERSION.SDK_INT >= 21) {
            return this.mWrapped.getType(r3);
        }
        if (this.mTypedValue == null) {
            this.mTypedValue = new TypedValue();
        }
        this.mWrapped.getValue(r3, this.mTypedValue);
        return this.mTypedValue.type;
    }

    public boolean hasValue(int r2) {
        return this.mWrapped.hasValue(r2);
    }

    public TypedValue peekValue(int r2) {
        return this.mWrapped.peekValue(r2);
    }

    public String getPositionDescription() {
        return this.mWrapped.getPositionDescription();
    }

    public void recycle() {
        this.mWrapped.recycle();
    }

    public int getChangingConfigurations() {
        return this.mWrapped.getChangingConfigurations();
    }
}
