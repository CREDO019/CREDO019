package ai.api.p000ui;

import ai.api.C0001R;
import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.graphics.PorterDuff;
import android.os.Build;
import android.util.AttributeSet;
import android.widget.ImageView;

/* renamed from: ai.api.ui.MaskedColorView */
/* loaded from: classes.dex */
public class MaskedColorView extends ImageView {
    private ColorStateList colorStateList;

    public MaskedColorView(Context context) {
        super(context, null);
        this.colorStateList = null;
    }

    public MaskedColorView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet, 16842866);
        this.colorStateList = null;
        configure(attributeSet);
    }

    public MaskedColorView(Context context, AttributeSet attributeSet, int r3) {
        super(context, attributeSet, r3);
        this.colorStateList = null;
        configure(attributeSet);
    }

    @Override // android.widget.ImageView, android.view.View
    protected void drawableStateChanged() {
        super.drawableStateChanged();
        setColorFilter(getCurrentColor(getDrawableState()), PorterDuff.Mode.SRC_ATOP);
        if (Build.VERSION.SDK_INT >= 11) {
            jumpDrawablesToCurrentState();
        }
    }

    public void setColorStateList(ColorStateList colorStateList) {
        this.colorStateList = colorStateList;
    }

    private int getCurrentColor(int[] r3) {
        ColorStateList colorStateList = this.colorStateList;
        if (colorStateList == null) {
            return -65281;
        }
        return colorStateList.getColorForState(r3, colorStateList.getDefaultColor());
    }

    private void configure(AttributeSet attributeSet) {
        if (attributeSet != null) {
            TypedArray obtainStyledAttributes = getContext().obtainStyledAttributes(attributeSet, C0001R.styleable.MaskedColorView);
            try {
                ColorStateList colorStateList = obtainStyledAttributes.getColorStateList(C0001R.styleable.MaskedColorView_mainColor);
                if (colorStateList != null) {
                    this.colorStateList = colorStateList;
                }
            } finally {
                obtainStyledAttributes.recycle();
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public String getDebugState() {
        StringBuilder sb = new StringBuilder();
        sb.append("====\ncsl is ");
        sb.append(this.colorStateList != null ? "NOT" : "");
        sb.append(" null");
        return sb.toString();
    }
}
